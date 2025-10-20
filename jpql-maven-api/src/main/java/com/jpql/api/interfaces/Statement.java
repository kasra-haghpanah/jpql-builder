package com.jpql.api.interfaces;

import java.util.Map;

/**
 * Created by k.haghpanah on 4/23/2019.
 */
@FunctionalInterface
public interface Statement {
    public void set(final Map<String, String> statement);
}
