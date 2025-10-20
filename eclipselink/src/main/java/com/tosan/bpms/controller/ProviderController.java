package com.tosan.bpms.controller;

import com.tosan.bpms.framework.injector.Injector;
import com.tosan.bpms.bean.provider.GumProviderUseBean;

/**
 * Created by kasra.haghpanah on 24/06/2018.
 */
public class ProviderController {

    public static void main(String[] args) {

        GumProviderUseBean machine = Injector.getBean(GumProviderUseBean.class);
        System.out.println(machine.dispense());

    }

}
