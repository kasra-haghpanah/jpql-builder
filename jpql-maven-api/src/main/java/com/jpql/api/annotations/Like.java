package com.jpql.api.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by kasra.haghpanah on 05/11/2016.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface Like {

    STATUS status() default STATUS.NONE;

    public enum STATUS{
        AFTER,BEFORE,NONE;

        @Override
        public String toString() {
            return this.name();
        }
    }

}
