package com.tosan.bpms.controller;

import com.tosan.bpms.framework.injector.Injector;
import com.tosan.bpms.framework.validator.custom.ComplexBean;
import com.tosan.bpms.framework.validator.custom.CustomService;


/**
 * Created by kasra.haghpanah on 22/06/2018.
 */
public class CuntextValidateController {

    public static void main(String[] args) {

        CustomService customService = Injector.getBean(CustomService.class);

        ComplexBean complexBean = new ComplexBean();
        complexBean.setValue(1);
        complexBean.setUser("perfect");

        customService.doAction(complexBean);

        System.out.println("****************************************");

    }

}
