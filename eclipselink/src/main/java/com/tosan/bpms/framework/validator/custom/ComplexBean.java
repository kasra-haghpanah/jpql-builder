package com.tosan.bpms.framework.validator.custom;

import javax.validation.constraints.NotNull;

/**
 * Created by kasra.haghpanah on 22/06/2018.
 */
@ComplexBeanValid
public class ComplexBean {

    @NotNull
    private String user;
    private int value;

    public ComplexBean() {
    }

    public ComplexBean(String user, int value) {
        this.user = user;
        this.value = value;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}