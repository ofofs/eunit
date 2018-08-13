package com.github.ofofs.eunit.test.constraint;

import com.github.ofofs.eunit.core.ConstraintContext;
import com.github.ofofs.eunit.core.ConstraintRuler;

/**
 * <p> </p>
 *
 * <pre> Created: 2018/8/13 上午11:29  </pre>
 * <pre> Project: eunit  </pre>
 *
 * @author houbinbin
 * @version 1.0
 * @since JDK 1.7
 */
public class ConstantValueRuler implements ConstraintRuler<String> {
    @Override
    public String rule(ConstraintContext<String> constraintContext) {
        System.out.println("当前字段: " + constraintContext.currentName());
        return "我是一个固定的值";
    }
}
