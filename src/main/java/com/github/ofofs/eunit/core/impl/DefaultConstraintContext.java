package com.github.ofofs.eunit.core.impl;

import com.github.ofofs.eunit.core.ConstraintContext;
import com.github.ofofs.eunit.exception.RuleRuntimeException;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * <p> 默认的约束上下文实现 </p>
 *
 * <pre> Created: 2018/8/13 上午10:31  </pre>
 * <pre> Project: eunit  </pre>
 *
 * @author houbinbin
 * @version 1.0
 * @since JDK 1.7
 */
public class DefaultConstraintContext<T> implements ConstraintContext<T> {

    /**
     * 实例对象
     */
    private Object instance;

    /**
     * 当前字段
     */
    private Field currentField;

    /**
     * 其他字段
     */
    private final Map<String, Object> otherFieldsMap = new LinkedHashMap<>();

    /**
     * 其他字段名称
     */
    private List<String> otherFieldNames;

    public DefaultConstraintContext(final Object instance, Field currentField,
                                    final List<Field> otherFields) {
        this.instance = instance;
        this.currentField = currentField;
        initOtherFields(otherFields);
    }

    /**
     * 初始化其他字段信息
     * @param otherFields 其他字段
     */
    private void initOtherFields(final List<Field> otherFields) {
        for(Field field : otherFields) {
            try {
                final String name = field.getName();
                final Object value = field.get(instance);
                otherFieldsMap.put(name, value);
            } catch (IllegalAccessException e) {
                throw new RuleRuntimeException(e);
            }
        }

        otherFieldNames = Collections.unmodifiableList(new ArrayList<>(otherFieldsMap.keySet()));
    }

    @Override
    public String currentName() {
        return currentField.getName();
    }

    @Override
    public List<String> otherNames() {
        return otherFieldNames;
    }

    @Override
    public Object otherValue(String fieldName) {
        //arg check?

        return otherFieldsMap.get(fieldName);
    }

}
