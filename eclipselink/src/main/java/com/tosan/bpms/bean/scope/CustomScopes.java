package com.tosan.bpms.bean.scope;

import com.google.inject.Key;
import com.google.inject.Provider;
import com.google.inject.Scope;
import com.tosan.bpms.bean.color.Color;

/**
 * Created by kasra.haghpanah on 30/06/2018.
 */
public class CustomScopes {

    public static final Scope DEFAULT = new Scope() {

        public <M> Provider<M> scope(Key<M> key, Provider<M> creator) {

            System.out.println(Color.ANSI_GREEN_BACKGROUND.getValue() + "Scoping " + key);
            System.out.println(Color.ANSI_WHITE_BACKGROUND.getValue());
            return creator;
        }

        public String toString() {
            return Color.ANSI_GREEN_BACKGROUND.getValue() + CustomScopes.class.getSimpleName() + ".DEFAULT\n" + Color.ANSI_WHITE_BACKGROUND.getValue();
        }

    };
}
