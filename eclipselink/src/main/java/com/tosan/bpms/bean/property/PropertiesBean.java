package com.tosan.bpms.bean.property;

import com.google.inject.Inject;

import javax.inject.Named;

/**
 * Created by kasra.haghpanah on 30/06/2018.
 */
public class PropertiesBean {

    String framework;
    String basePackage;

    @Inject
    public void framework(@Named("framework") String framework) {
        this.framework = framework;
    }

    @Inject
    public void basePackage(@Named("base-package") String basePackage) {
        this.basePackage = basePackage;
    }

    public String getFramework() {
        return framework;
    }

    public String getBasePackage() {
        return basePackage;
    }

    @Override
    public String toString() {
        return "{\"PropertiesBean\":{"
                + "\"framework\":\"" + framework + "\""
                + ",\"basePackage\":\"" + basePackage + "\""
                + "}}";
    }
}
