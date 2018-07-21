package com.ofofs.eunit.test;

import com.ofofs.eunit.DataFactory;
import com.ofofs.eunit.model.User;

/**
 * @author kangyonggan
 * @since 2018/7/21 0021
 */
public class UserSimpleTest {

    public static void main(String[] args) {
        User user = DataFactory.instance(User.class);
        System.out.println(user.getUsername());
        System.out.println(user.getName());
    }

}
