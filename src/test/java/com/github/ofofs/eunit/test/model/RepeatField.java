package com.github.ofofs.eunit.test.model;

import com.github.ofofs.eunit.annotation.Rule;
import com.github.ofofs.eunit.test.annotation.RepeatFieldRule;

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
public class RepeatField {

    /**
     * 密码
     */
    @Rule
    private String password;

    /**
     * 重复密码
     */
    @RepeatFieldRule(value = "password", desc="测试")
    private String repeatPassword;

    @Override
    public String toString() {
        return "RepeatField{" +
                "password='" + password + '\'' +
                ", repeatPassword='" + repeatPassword + '\'' +

                '}';
    }
}
