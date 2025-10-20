package com.tosan.bpms.framework.orm.repository.jpql;

import com.jpql.api.interfaces.Cast;
import com.jpql.api.interfaces.Parameter;
import com.jpql.api.interfaces.ProcedureParameter;
import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by swb on 26/07/2019.
 */
public interface ProcedureInterface {

    public EntityManager getEntityManager();

    public ProcedureInterface query(ProcedureParameter procedureParameter);

    public ProcedureInterface cast(Cast cast);

    public ProcedureInterface page(int page);

    public ProcedureInterface size(int size);

    public ProcedureInterface update(boolean update);

    public ProcedureInterface outputParameter(ProcedureParameter procedureParameter);

    public ProcedureInterface showQuery(Parameter jpql);

    public <T> List<T> build();
}