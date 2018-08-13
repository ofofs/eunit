package com.github.ofofs.eunit.test;

import com.github.ofofs.eunit.DataFactory;
import com.github.ofofs.eunit.test.model.ConstantUser;
import com.github.ofofs.eunit.test.model.User;

/**
 * @author houbinbin
 * @since 2018/7/21 0021
 */
public class ConstraintTest {

    public static void main(String[] args) {
        ConstantUser constantUser = DataFactory.instance(ConstantUser.class);
        System.out.println(constantUser.getUsername());
    }

}
