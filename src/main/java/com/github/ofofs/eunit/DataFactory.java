package com.github.ofofs.eunit;

import com.github.ofofs.eunit.annotation.Rule;
import com.github.ofofs.eunit.util.RandomUtil;
import com.github.ofofs.eunit.util.Reflections;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author kangyonggan
 * @since 2018/7/21 0021
 */
public final class DataFactory {

    private DataFactory() {
    }

    /**
     * 实例化一个对象，并构造数据
     *
     * @param clazz 类
     * @param <T>   对象的泛型
     * @return 返回对象
     * @throws RuntimeException 可能抛出异常
     */
    public static <T> T instance(Class<T> clazz) throws RuntimeException {
        try {
            // 实例化（要有无参构造）
            T instance = clazz.newInstance();

            // 获取对象所有字段，包括父类字段
            List<Field> fields = Reflections.getAccessibleFields(instance);

            // 遍历所有字段并赋值
            for (Field field : fields) {
                Rule rule = field.getAnnotation(Rule.class);
                if (rule != null && rule.ignore()) {
                    continue;
                }
                processField(field, instance);
            }
            return instance;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 给字段赋值
     *
     * @param field    字段
     * @param instance 对象
     * @param <T>      对象的泛型
     */
    private static <T> void processField(Field field, T instance) {
        Object value = null;

        Class<?> type = field.getType();

        if (String.class.getName().equals(type.getName())) {
            value = generateString(field);
        } else if (Integer.class.getName().equals(type.getName()) || int.class.getName().equals(type.getName())) {
            value = generateInteger(field);
        } else if (Float.class.getName().equals(type.getName()) || float.class.getName().equals(type.getName())) {
            value = generateFloat(field);
        } else if (Double.class.getName().equals(type.getName()) || double.class.getName().equals(type.getName())) {
            value = generateDouble(field);
        } else if (Byte.class.getName().equals(type.getName()) || byte.class.getName().equals(type.getName())) {
            value = generateByte(field);
        } else if (Character.class.getName().equals(type.getName()) || char.class.getName().equals(type.getName())) {
            value = generateChar();
        } else if (Short.class.getName().equals(type.getName()) || short.class.getName().equals(type.getName())) {
            value = generateShort(field);
        } else if (Long.class.getName().equals(type.getName()) || long.class.getName().equals(type.getName())) {
            value = generateLong(field);
        } else if (Boolean.class.getName().equals(type.getName()) || boolean.class.getName().equals(type.getName())) {
            value = generateBoolean();
        } else if (BigDecimal.class.getName().equals(type.getName())) {
            value = generateBigDecimal(field);
        } else if (Date.class.getName().equals(type.getName())) {
            value = generateBigDate();
        }

        Reflections.setFieldValue(instance, field.getName(), value);
    }

    /**
     * 生成一个Date类型的值
     *
     * @return 返回Date类型的值
     */
    private static Date generateBigDate() {
        return RandomUtil.randomBigDate();
    }

    /**
     * 生成一个Character类型的值
     *
     * @param field 字段
     * @return 返回Character类型的值
     */
    private static BigDecimal generateBigDecimal(Field field) {
        Rule rule = field.getAnnotation(Rule.class);

        if (rule != null) {
            return RandomUtil.randomBigDecimal(rule);
        }

        return RandomUtil.randomBigDecimal();
    }

    /**
     * 生成一个Character类型的值
     *
     * @return 返回Character类型的值
     */
    private static Character generateChar() {
        return RandomUtil.randomCharacter();
    }

    /**
     * 生成一个Byte类型的值
     *
     * @param field 字段
     * @return 返回Byte类型的值
     */
    private static Byte generateByte(Field field) {
        Rule rule = field.getAnnotation(Rule.class);

        if (rule != null) {
            return RandomUtil.randomByte(rule);
        }

        return RandomUtil.randomByte();
    }

    /**
     * 生成一个Long类型的值
     *
     * @param field 字段
     * @return 返回Long类型的值
     */
    private static Long generateLong(Field field) {
        Rule rule = field.getAnnotation(Rule.class);

        if (rule != null) {
            return RandomUtil.randomLong(rule);
        }

        return RandomUtil.randomLong();
    }

    /**
     * 生成一个Boolean类型的值
     *
     * @return 返回Boolean类型的值
     */
    private static Boolean generateBoolean() {
        return RandomUtil.randomBoolean();
    }

    /**
     * 生成一个Short类型的值
     *
     * @param field 字段
     * @return 返回Short类型的值
     */
    private static Short generateShort(Field field) {
        Rule rule = field.getAnnotation(Rule.class);

        if (rule != null) {
            return RandomUtil.randomShort(rule);
        }

        return RandomUtil.randomShort();
    }

    /**
     * 生成一个Double类型的值
     *
     * @param field 字段
     * @return 返回Float类型的值
     */
    private static Double generateDouble(Field field) {
        Rule rule = field.getAnnotation(Rule.class);

        Double result;
        int precision = 2;
        if (rule != null) {
            precision = rule.precision();
            result = Double.valueOf(RandomUtil.randomFloat(rule));
        } else {
            result = Double.valueOf(RandomUtil.randomFloat());
        }

        BigDecimal bg = new BigDecimal(result);
        return bg.setScale(precision, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 生成一个Float类型的值
     *
     * @param field 字段
     * @return 返回Float类型的值
     */
    private static Float generateFloat(Field field) {
        Rule rule = field.getAnnotation(Rule.class);
        if (rule != null) {
            return RandomUtil.randomFloat(rule);
        }

        return RandomUtil.randomFloat();
    }

    /**
     * 生成一个Integer类型的值
     *
     * @param field 字段
     * @return 返回Integer类型的值
     */
    private static Integer generateInteger(Field field) {
        Rule rule = field.getAnnotation(Rule.class);
        if (rule != null) {
            return RandomUtil.randomInteger(rule);
        }

        return RandomUtil.randomInteger();
    }

    /**
     * 生成一个String类型的值
     *
     * @param field 字段
     * @return 返回String类型的值
     */
    private static String generateString(Field field) {
        Rule rule = field.getAnnotation(Rule.class);
        if (rule != null) {
            return RandomUtil.randomString(rule);
        }

        return RandomUtil.randomString();
    }
}
