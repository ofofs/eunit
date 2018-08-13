package com.github.ofofs.eunit.test.constraint;

import com.github.ofofs.eunit.core.ConstraintAnnotationRuler;
import com.github.ofofs.eunit.core.ConstraintContext;
import com.github.ofofs.eunit.core.ConstraintRuler;
import com.github.ofofs.eunit.test.annotation.RepeatFieldRule;

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
public class RepeatFieldRuler implements ConstraintAnnotationRuler<RepeatFieldRule, String> {

    private String fieldName;

    private String desc;

    @Override
    public void init(RepeatFieldRule constraintAnnotation) {
        this.fieldName = constraintAnnotation.value();
        this.desc = constraintAnnotation.desc();
    }

    @Override
    public String rule(ConstraintContext<String> constraintContext) {
        final String fieldVal = (String) constraintContext.otherValue(fieldName);
        return fieldVal + "-" + desc;
    }

}
