package com.github.ofofs.eunit.test;

import com.github.ofofs.eunit.DataFactory;
import com.github.ofofs.eunit.test.model.RepeatField;
import com.github.ofofs.eunit.test.model.RepeatUser;

import org.junit.jupiter.api.Test;

/**
 * @author houbinbin
 * @since 2018/7/21 0021
 */
public class RepeatFieldTest {

    @Test
    public void repeatFieldTest() {
        RepeatField repeatField = DataFactory.instance(RepeatField.class);
        System.out.println(repeatField);
    }

}
