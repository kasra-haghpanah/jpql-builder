package com.tosan.bpms.bean.provider;

import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * Created by kasra.haghpanah on 24/06/2018.
 */
public class GumProviderUseBean {

    //when the Provider will be hit by multiple threads
    //concurrently. In such cases, using constructor injection combined with final
    //fields is a much more robust option

    //If you want to learn about safe publication or concurrency in general,
    //I highly recommend Java Concurrency in Practice by Brian Goetz, Tim Peierls,
    //Joshua Bloch, Joseph Bowbeer, David Holmes, and Doug Lea (Addison-Wesley Professional, 2006)
    private final Provider<Gum> gumProvider;

    @Inject
    public GumProviderUseBean(Provider<Gum> gumProvider) {
        this.gumProvider = gumProvider;
    }

    public Gum dispense() {
        return gumProvider.get();
    }
}
