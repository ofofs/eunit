package com.github.ofofs.eunit.core.impl;

import com.github.ofofs.eunit.core.ConstraintContext;
import com.github.ofofs.eunit.core.ConstraintRuler;

/**
 * <p> 默认约束规则接口 </p>
 *
 * <pre> Created: 2018/8/13 上午10:10  </pre>
 * <pre> Project: eunit  </pre>
 *
 * @author houbinbin
 * @version 1.0
 * @since JDK 1.8
 */
public class DefaultConstraintRuler implements ConstraintRuler<Object> {

    @Override
    public Object rule(ConstraintContext<Object> constraintContext) {
        //do nothing...
        return null;
    }
}
