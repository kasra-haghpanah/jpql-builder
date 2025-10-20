package com.tosan.bpms.framework.orm.repository.jpql;

import com.jpql.api.interfaces.*;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by k.haghpanah on 5/01/2019.
 */
public class SQLBuilder implements SQLInterface, ProcedureInterface {

    EntityManager entityManager;
    String sql;
    Parameter jpql;
    QueryParameter queryParameter;
    ProcedureParameter storedProcedureQuery;
    ProcedureParameter outputParameter;
    Cast cast;
    int page = 0;
    int size = 0;
    boolean isUpdate = false;
    String sqlType;
    boolean disabledPagination = false;

    public SQLBuilder(EntityManager entityManager, String sqlType, String sql) {
        this.entityManager = entityManager;
        this.sqlType = sqlType;
        this.sql = sql;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public SQLBuilder showQuery(Parameter jpql) {
        this.jpql = jpql;
        return this;
    }

    public SQLBuilder query(QueryParameter queryParameter) {
        this.queryParameter = queryParameter;
        return this;
    }

    public SQLBuilder query(ProcedureParameter procedureParameter) {
        this.storedProcedureQuery = procedureParameter;
        return this;
    }

    public SQLBuilder disabledPagination(boolean disabledPagination) {
        this.disabledPagination = disabledPagination;
        return this;
    }

    public SQLBuilder cast(Cast cast) {
        this.cast = cast;
        return this;
    }

    public SQLBuilder page(int page) {
        this.page = page;
        return this;
    }

    public SQLBuilder size(int size) {
        this.size = size;
        return this;
    }

    public SQLBuilder update(boolean update) {
        isUpdate = update;
        return this;
    }

    public SQLBuilder outputParameter(ProcedureParameter procedureParameter) {
        this.outputParameter = procedureParameter;
        return this;
    }

    public <T> List<T> build() {

        boolean isStoredProcedure = this.sqlType.equals("procedure");
        Query query = null;
        if (size <= 0) size = 100;
        if (page <= 0) page = 0;
        if (!isStoredProcedure) {
            outputParameter = null;
        }

        if (this.sqlType.equals("sql")) {
            query = entityManager.createNativeQuery(sql);
        } else if (this.sqlType.equals("jpql")) {
            query = entityManager.createQuery(sql);
        } else if (this.sqlType.equals("procedure")) {
            query = entityManager.createStoredProcedureQuery(sql);
        }


        if (queryParameter != null) {
            queryParameter.createCondition(query);
        }


        if (storedProcedureQuery != null) {
            storedProcedureQuery.get((StoredProcedureQuery) query);
        }

        if (isUpdate) {
            query.executeUpdate();
        } else if (isStoredProcedure) {
            ((StoredProcedureQuery) query).execute();
        }
            if (outputParameter != null) {
                outputParameter.get((StoredProcedureQuery) query);
            }


        try {
            if (cast != null) {
                if (!isStoredProcedure && !disabledPagination) {
                    query.setFirstResult(page * size);
                    query.setMaxResults(size);
                }
                List<Object> result = query.getResultList();

                if (jpql != null) {
                    java.util.Set set = query.getParameters();
                    java.util.Iterator iterator1 = set.iterator();
                    String variables = "";

                    if (iterator1.hasNext()) {
                        variables = "\n\n************ Variables ************\n";
                        while (iterator1.hasNext()) {
                            javax.persistence.Parameter key = (javax.persistence.Parameter) iterator1.next();

                            Object name = key.getPosition();
                            Object value = null;
                            if (name == null) {
                                name = key.getName();
                            }

                            try {
                                value = query.getParameterValue(key);
                            } catch (IllegalStateException e) {
                            }

                            if (key.getParameterType().equals(java.lang.String.class)) {
                                variables += (name + " : '" + value + "'\n");
                            } else {
                                variables += (name + " : " + value + "\n");
                            }

                        }
                        variables += "************ Variables ************";
                    }
                    jpql.get(sql + variables);
                }


                List<T> tList = new ArrayList<T>();
                if (result != null && result.size() > 0) {
                    for (Object object : result) {
                        Object[] objects;
                        if (object instanceof Object[]) {
                            objects = (Object[]) object;
                        } else {
                            objects = new Object[]{object};
                        }
                        tList.add((T) cast.to(objects));
                    }
                }
                return tList;
            }
        } catch (NoResultException e) {
            return null;
        }

        return null;

    }


}