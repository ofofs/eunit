package com.github.ofofs.eunit.model;

import com.github.ofofs.eunit.annotation.Rule;

/**
 * @author kangyonggan
 * @since 2018/7/21 0021
 */
public class Person {

    private String name;

    /**
     * 身份证
     */
    @Rule(regex = "^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$")
    private String idNo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }
}
