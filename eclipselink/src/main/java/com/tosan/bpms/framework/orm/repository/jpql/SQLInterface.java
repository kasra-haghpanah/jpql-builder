package com.tosan.bpms.framework.orm.repository.jpql;

import com.jpql.api.interfaces.Cast;
import com.jpql.api.interfaces.Parameter;
import com.jpql.api.interfaces.QueryParameter;
import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by kasra.haghpanah on 26/07/2019.
 */
public interface SQLInterface {

    public EntityManager getEntityManager();

    public SQLInterface query(QueryParameter queryParameter);

    public SQLInterface disabledPagination(boolean disabledPagination);

    public SQLInterface cast(Cast cast);

    public SQLInterface page(int page);

    public SQLInterface size(int size);

    public SQLInterface update(boolean update);

    public SQLInterface showQuery(Parameter jpql);

    public <T> List<T> build();
}