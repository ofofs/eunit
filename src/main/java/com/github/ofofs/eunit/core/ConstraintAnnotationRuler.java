package com.github.ofofs.eunit.core;

import java.lang.annotation.Annotation;

/**
 * <p> 约束规则自定义注解-接口 </p>
 *
 * <pre> Created: 2018/8/13 上午10:10  </pre>
 * <pre> Project: eunit  </pre>
 *
 * @author houbinbin
 * @version 1.0
 * @since JDK 1.8
 */
public interface ConstraintAnnotationRuler<A extends Annotation, T> {

    /**
     * 初始化注解相关信息
     * @param constraintAnnotation 自定义注解
     */
    default void init(A constraintAnnotation) {
    }

    /**
     * 生成规则
     * @param constraintContext 上下文
     * @return 返回构建的对象值
     */
    T rule(ConstraintContext<T> constraintContext);

}
