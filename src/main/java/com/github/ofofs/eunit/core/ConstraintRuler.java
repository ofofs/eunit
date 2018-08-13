package com.github.ofofs.eunit.core;

/**
 * <p> 约束规则接口 </p>
 *
 * <pre> Created: 2018/8/13 上午10:10  </pre>
 * <pre> Project: eunit  </pre>
 *
 * @author houbinbin
 * @version 1.0
 * @since JDK 1.8
 */
public interface ConstraintRuler<T> {

    /**
     * 生成规则
     * @param constraintContext 上下文
     * @return 返回构建的对象值
     */
    T rule(ConstraintContext<T> constraintContext);

}
