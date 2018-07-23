package com.github.ofofs.eunit;

import com.github.ofofs.eunit.annotation.Rule;
import com.github.ofofs.eunit.util.RandomUtil;
import com.github.ofofs.eunit.util.Reflections;

import java.lang.reflect.Field;
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
        }

        Reflections.setFieldValue(instance, field.getName(), value);
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
