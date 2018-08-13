package com.github.ofofs.eunit.test.model;

import com.github.ofofs.eunit.annotation.Constraint;
import com.github.ofofs.eunit.test.constraint.ConstantValueRuler;

/**
 * <p> </p>
 *
 * <pre> Created: 2018/8/13 上午11:31  </pre>
 * <pre> Project: eunit  </pre>
 *
 * @author houbinbin
 * @version 1.0
 * @since JDK 1.7
 */
public class ConstantUser {

    @Constraint(ConstantValueRuler.class)
    private String username;

    public String getUsername() {
        return username;
    }


}
