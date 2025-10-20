package com.tosan.bpms.bean.provider;

import com.google.inject.Provider;

/**
 * Created by kasra.haghpanah on 24/06/2018.
 */
public class GumProvider implements Provider<Gum> {
    public Gum get() {
        return new Gum(1, "kasra");
    }
}
