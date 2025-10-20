package com.tosan.bpms.controller;

import com.tosan.bpms.framework.config.Config;

import java.util.*;

/**
 * Created by kasra.haghpanah on 25/06/2018.
 */
public class ConfigController {

    public static void main(String[] args) {

        Date date = null;

        System.out.println(date != null);

        System.out.println("*******************************");
        Config.addResource("/config.properties");
        Config.addResource("/META-INF/config2.properties");
        Object obj = Config.getPropertyByResource("/config.properties" , "base-package");
        System.out.println(obj);
        System.out.println("*******************************");


    }
}
