package com.github.ofofs.eunit.core;

import java.util.List;

/**
 * <p> 约束上下文 </p>
 *
 * 1. 用户使用时，根据获取到其他已经定义的字段信息及其值
 *
 * <pre> Created: 2018/8/13 上午10:17  </pre>
 * <pre> Project: eunit  </pre>
 *
 * @author houbinbin
 * @version 1.0
 * @since JDK 1.7
 */
public interface ConstraintContext<T> {

    /**
     * 当前字段名称
     * @return 字段名称
     */
    String currentName();

    /**
     * 获取已经初始化的字段名称
     * @return 字段名称列表
     */
    List<String> otherNames();

    /**
     * 获取字段的值
     * @param fieldName 字段名称
     * @return 字段的值
     */
    Object otherValue(final String fieldName);

}
