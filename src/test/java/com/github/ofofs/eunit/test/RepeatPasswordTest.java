package com.github.ofofs.eunit.test;

import com.github.ofofs.eunit.DataFactory;
import com.github.ofofs.eunit.test.model.RepeatUser;

import org.junit.jupiter.api.Test;

/**
 * @author houbinbin
 * @since 2018/7/21 0021
 */
public class RepeatPasswordTest {

    @Test
    public void repeatUserTest() {
        RepeatUser repeatUser = DataFactory.instance(RepeatUser.class);
        System.out.println(repeatUser);
    }

}
