package com.github.ofofs.eunit;

import com.github.ofofs.eunit.annotation.Constraint;
import com.github.ofofs.eunit.annotation.Rule;
import com.github.ofofs.eunit.core.ConstraintAnnotationRuler;
import com.github.ofofs.eunit.core.ConstraintContext;
import com.github.ofofs.eunit.core.ConstraintRuler;
import com.github.ofofs.eunit.core.impl.DefaultConstraintContext;
import com.github.ofofs.eunit.core.impl.DefaultConstraintRuler;
import com.github.ofofs.eunit.exception.RuleRuntimeException;
import com.github.ofofs.eunit.util.RandomUtil;
import com.github.ofofs.eunit.util.Reflections;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author kangyonggan
 * @author houbinbin
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

            // 存储处理过的字段列表
            List<Field> processedField = new ArrayList<>();

            // 遍历所有字段并赋值
            for (Field field : fields) {
                Rule rule = field.getAnnotation(Rule.class);
                if (rule != null) {
                    if (!rule.ignore()) {
                        Class ruledByClass = rule.ruler();
                        if (ruledByClass.equals(DefaultConstraintRuler.class)) {
                            processField(field, instance);
                        } else {
                            processRulerField(ruledByClass, field, instance, processedField);
                        }
                    }
                } else {
                    processDefineAnnotationField(field, instance, processedField);
                }

                processedField.add(field);
            }
            return instance;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 执行约束字段
     *
     * @param ruledByClass   规则类
     * @param field          字段信息
     * @param instance       当前实例化对象
     * @param processedField 已经执行过的字段信息
     */
    private static void processRulerField(final Class ruledByClass,
                                          final Field field,
                                          final Object instance,
                                          final List<Field> processedField) {
        //TODO: 如何根据字段获取对应的泛型？
        ConstraintContext constraintContext = new DefaultConstraintContext<>(instance,
                field, processedField);

        try {
            ConstraintRuler constraintRuler = (ConstraintRuler) ruledByClass.newInstance();
            Object ruleValue = constraintRuler.rule(constraintContext);
            field.setAccessible(true);

            field.set(instance, ruleValue);
        } catch (IllegalAccessException | InstantiationException e) {
            throw new RuleRuntimeException(e);
        }
    }

    /**
     * 执行自定义注解字段
     *
     * @param field          字段信息
     * @param instance       当前实例化对象
     * @param processedField 已经执行过的字段信息
     */
    private static void processDefineAnnotationField(final Field field, final Object instance,
                                                     final List<Field> processedField) {
        Optional<Annotation> annotationOptional = containsDefineAnnotation(field);
        if (annotationOptional.isPresent()) {
            //TODO: 如何根据字段获取对应的泛型？
            Annotation annotation = annotationOptional.get();
            Constraint constraint = annotation.annotationType().getAnnotation(Constraint.class);

            Class ruledByClass = constraint.value();
            ConstraintContext constraintContext = new DefaultConstraintContext<>(instance,
                    field, processedField);
            try {
                ConstraintAnnotationRuler constraintRuler = (ConstraintAnnotationRuler) ruledByClass.newInstance();
                constraintRuler.init(annotation);
                Object ruleValue = constraintRuler.rule(constraintContext);
                field.setAccessible(true);

                field.set(instance, ruleValue);
            } catch (InstantiationException | IllegalAccessException e) {
                throw new RuleRuntimeException(e);
            }
        }
    }

    /**
     * 是否包含自定义约束类注解
     *
     * @param field 字段信息
     * @return 是否包含
     */
    private static Optional<Annotation> containsDefineAnnotation(final Field field) {
        Annotation[] annotations = field.getAnnotations();
        for (Annotation annotation : annotations) {
            Constraint constraint = annotation.annotationType().getAnnotation(Constraint.class);
            if (constraint != null) {
                return Optional.of(annotation);
            }
        }
        return Optional.empty();
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
