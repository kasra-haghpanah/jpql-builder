package com.tosan.bpms.bean.statics;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

/**
 * Created by kasra.haghpanah on 30/06/2018.
 */
//  You can usually avoid the use of static methods and fields by scoping
//  your objects correctly.
public class StaticInjection {
    @Inject
    public static void staticMethod(@Named("my.static") String str) {
        System.out.println(str);
    }
}
