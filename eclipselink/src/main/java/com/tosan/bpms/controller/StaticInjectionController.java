package com.tosan.bpms.controller;

import com.tosan.bpms.bean.statics.StaticInjection;
import com.tosan.bpms.framework.injector.Injector;

/**
 * Created by kasra.haghpanah on 30/06/2018.
 */
public class StaticInjectionController {

    public static void main(String[] args) {

        StaticInjection bean = Injector.getBean(StaticInjection.class);



    }

}
