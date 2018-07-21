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
        System.out.println("用户名规则（5~20位）:" + user.getUsername());
        System.out.println("密码规则（8~30位）:" + user.getPassword());
    }

}
