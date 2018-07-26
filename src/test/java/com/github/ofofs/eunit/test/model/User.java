package com.github.ofofs.eunit.test.model;

import com.github.ofofs.eunit.annotation.Rule;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author kangyonggan
 * @since 2018/7/21 0021
 */
public class User extends Person {

    private Long id;

    @Rule(minLength = 5, maxLength = 20)
    private String username;

    @Rule
    private String password;

    private Date birthday;

    @Rule(max = 1)
    private Byte sex;

    @Rule(min = 10, max = 20)
    private Integer level;

    @Rule(regex = "^1[3458]\\d{9}$")
    private String mobile;

    private float weight;

    @Rule(precision = 3)
    private Double height;

    private Character ch;

    private Boolean isEnable;

    private BigDecimal amount;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", birthday=" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(birthday) +
                ", sex=" + sex +
                ", level=" + level +
                ", mobile='" + mobile + '\'' +
                ", weight=" + weight +
                ", height=" + height +
                ", ch=" + ch +
                ", isEnable=" + isEnable +
                ", amount=" + amount +
                '}';
    }

}
