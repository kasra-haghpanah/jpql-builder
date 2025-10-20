package com.tosan.bpms.service;

import com.tosan.bpms.interceptor.Logged;

import javax.inject.Singleton;

/**
 * Created by kasra.haghpanah on 14/12/2018.
 */
@Singleton
public class MyService {

    @Logged
    public String myMethod(){
        return "My Name is Kasra!";
    }
}
