package com.jpql.api.interfaces;

import javax.persistence.StoredProcedureQuery;

/**
 * Created by k.haghpanah on 5/01/2019.
 */
@FunctionalInterface
public interface ProcedureParameter {
    public void get(StoredProcedureQuery query);
}
