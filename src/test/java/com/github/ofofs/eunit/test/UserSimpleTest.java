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
        System.out.println("用户名（5~20位）:" + user.getUsername());
        System.out.println("密码（8~30位）:" + user.getPassword());
        System.out.println("身份证（正则）:" + user.getIdNo());
        System.out.println("手机号（正则）:" + user.getMobile());
    }

}
