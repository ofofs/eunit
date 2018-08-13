package com.github.ofofs.eunit.test.constraint;

import com.github.ofofs.eunit.core.ConstraintContext;
import com.github.ofofs.eunit.core.ConstraintRuler;

/**
 * <p> 重复密码规则 </p>
 *
 * 1. 作用：将当前字段和 password 字段的值设置的一样
 *
 * <pre> Created: 2018/8/13 上午11:29  </pre>
 * <pre> Project: eunit  </pre>
 *
 * @author houbinbin
 * @version 1.0
 * @since JDK 1.7
 */
public class RepeatPasswordRuler implements ConstraintRuler<String> {
    @Override
    public String rule(ConstraintContext<String> constraintContext) {
        return (String) constraintContext.otherValue("password");
    }
}
