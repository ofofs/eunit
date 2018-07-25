package com.github.ofofs.eunit.test;

import com.github.ofofs.eunit.DataFactory;
import com.github.ofofs.eunit.model.User;

/**
 * @author kangyonggan
 * @since 2018/7/21 0021
 */
public class UserSimpleTest {

    public static void main(String[] args) {
        User user = DataFactory.instance(User.class);
        System.out.println(user);
    }

}
