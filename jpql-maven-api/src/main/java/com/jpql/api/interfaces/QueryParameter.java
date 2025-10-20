package com.jpql.api.interfaces;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * Created by kasra.haghpanah on 28/03/2019.
 */
@FunctionalInterface
public interface QueryParameter {
    public void createCondition(Query query);
}
