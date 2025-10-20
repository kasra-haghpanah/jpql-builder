package com.jpql.api.interfaces;

/**
 * Created by kasra.haghpanah on 02/04/2019.
 */
@FunctionalInterface
public interface Cast<R> {
    public R to(Object[] record);
}
