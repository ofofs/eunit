/*
 * Copyright (c)  2018. ofofs Inc.
 * eunit All rights reserved.
 */

package com.github.ofofs.eunit.annotation;

import com.github.ofofs.eunit.core.ConstraintRuler;
import com.github.ofofs.eunit.core.impl.DefaultConstraintRuler;

import java.lang.annotation.*;

/**
 * <p> 生成规则 </p>
 * <pre> Created: 2018/7/21 上午8:28  </pre>
 * <pre> Project: eunit  </pre>
 *
 * @author houbinbin
 * @version 1.0
 * @since JDK 1.7
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Inherited
@Documented
public @interface Rule {

    /**
     * 是否忽略此字段
     *
     * @return 默认不忽略
     */
    boolean ignore() default false;

    /**
     * 最大长度。只作用于String类型的字段
     *
     * @return 返回最大长度
     */
    int maxLength() default 30;

    /**
     * 最小长度。只作用于String类型的字段
     *
     * @return 返回最小长度
     */
    int minLength() default 3;

    /**
     * 正则表达式。只作用域String类型的字段，提供有常用正则的枚举，
     * 有正则表达式的情况下不再使用maxLength、minLength等其他规则。
     *
     * @return 返回正则表达式
     */
    String regex() default "";

    /**
     * 最大值。只作用于数字类型的字段
     *
     * @return 返回最大值
     */
    int max() default 1000;

    /**
     * 最小值。只作用于数字类型的字段
     *
     * @return 返回最小值
     */
    int min() default 0;

    /**
     * 精度。只作用于Float类型和和Double类型。
     *
     * @return 返回精度
     */
    int precision() default 2;

    /**
     * 指定约束类的规则
     * @return 约束类
     */
    Class<? extends ConstraintRuler<?>> ruler() default DefaultConstraintRuler.class;

}
