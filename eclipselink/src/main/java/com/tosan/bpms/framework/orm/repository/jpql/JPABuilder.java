package com.tosan.bpms.framework.orm.repository.jpql;
import com.jpql.api.interfaces.*;
import com.jpql.api.interfaces.Parameter;
import javax.persistence.EntityManager;
import javax.persistence.FetchType;
import java.util.*;

/**
 * Created by k.haghpanah on 5/01/2019.
 */
public class JPABuilder {

    EntityManager entityManager;
    final Map<String, List<String>> sqlMap = new HashMap<String, List<String>>();
    Parameter jpql;
    QueryParameter queryParameter;
    final Map<String, String> statement = new HashMap<String, String>();
    Cast cast;
    List models;
    FetchType fetchType = FetchType.EAGER;
    boolean isFetch;
    int page;
    int size;
    boolean isExistRecord;
    DTO dto = null;
    boolean isOrState = false;

    private void put(String key, String value) {

        int size = 0;
        List<String> conditions = sqlMap.get(key);
        if (conditions == null) {
            conditions = new ArrayList<String>();
            sqlMap.put(key, conditions);
        } else {
            size = conditions.size();
        }

        if ((key.equals("WHERE") || key.equals("HAVING")) && size % 2 == 1) {
            if (this.isOrState) {
                conditions.add("OR");
            } else {
                conditions.add("AND");
            }
        }

        if (size == 0 && key.equals("WHERE")) {
            if (this.isOrState) {
                value = " OR " + value;
            } else {
                value = " AND " + value;
            }
        }
        conditions.add(value);
        this.isOrState = false;
    }

    private String get(String key) {

        List<String> conditions = sqlMap.get(key);
        if (conditions == null || conditions.size() == 0) {
            return "";
        }

        String result = key;
        if (key.equals("WHERE")) {
            result = "";
        }
        String operator = "";

        Iterator<String> iterator = conditions.iterator();
        while (iterator.hasNext()) {

            String next = iterator.next();
            String lowerNext = next.toLowerCase();
            result += (" " + next);

            if (iterator.hasNext()) {

                if (key.equals("WHERE") || key.equals("HAVING")) {

                } else {
                    result += (" ,");
                }

            }
        }

        return result + " ";
    }

    public <M extends Model> JPABuilder(EntityManager entityManager, List<M> models, DTO dto) {
        this.entityManager = entityManager;
        if (models == null || models.size() == 0) {
            return;
        }
        this.models = models;
        this.dto = dto;

    }

    public JPABuilder showQuery(Parameter jpql) {
        this.jpql = jpql;
        this.isOrState = false;
        return this;
    }


    public JPABuilder or() {

        this.isOrState = true;
        return this;
    }

    public JPABuilder and() {
        this.isOrState = false;
        return this;
    }

    public JPABuilder select(String item) {
        this.put("SELECT", item);
        this.isOrState = false;
        return this;
    }

    public JPABuilder where(String item) {
        this.put("WHERE", item);
        return this;
    }

    public JPABuilder groupBy(String item) {
        this.put("GROUP BY", item);
        this.isOrState = false;
        return this;
    }

    public JPABuilder having(String item) {
        this.put("HAVING", item);
        return this;
    }

    public JPABuilder orderBy(String item) {
        this.put("ORDER BY", item);
        this.isOrState = false;
        return this;
    }

    public JPABuilder query(QueryParameter queryParameter) {
        this.queryParameter = queryParameter;
        this.isOrState = false;
        return this;
    }

    public JPABuilder cast(Cast cast) {
        this.isOrState = false;
        this.cast = cast;
        return this;
    }


    public JPABuilder fetchType(FetchType fetchType) {
        this.fetchType = fetchType;
        this.isOrState = false;
        return this;
    }


    public JPABuilder fetch(boolean fetch) {
        isFetch = fetch;
        this.isOrState = false;
        return this;
    }

    public JPABuilder page(int page) {
        this.page = page;
        this.isOrState = false;
        return this;
    }


    public JPABuilder size(int size) {
        this.size = size;
        this.isOrState = false;
        return this;
    }

    public JPABuilder setExistRecord(boolean existRecord) {
        isExistRecord = existRecord;
        this.isOrState = false;
        return this;
    }


    public JPABuilder statement(Statement statement) {
        if (statement != null) {
            statement.set(this.statement);
        }
        this.isOrState = false;
        return this;
    }


    public <D extends DTO, M extends Model, T> List<T> build() {

        String furthermore = get("WHERE") + get("GROUP BY") + get("HAVING") + get("ORDER BY");

        if (this.dto != null) {

            if (this.cast == null) {

                List<M> result = Facade.getByFilter(entityManager, models, isFetch, page, size, get("SELECT"), jpql, queryParameter, cast, false, "AND", fetchType, statement, false, furthermore);
                List<DTO> dtoList = new ArrayList<DTO>();
                if (result != null) {
                    if (cast == null && result != null) {
                        for (int i = 0; i < result.size(); i++) {
                            D o = (D) dto.convertToDTO(result.get(i));
                            dtoList.add(o);
                        }
                    }
                }
                return (List<T>) dtoList;

            }

        }

        return Facade.getByFilter(entityManager, models, isFetch, page, size, get("SELECT"), jpql, queryParameter, cast, false, "AND", fetchType, statement, false, furthermore);


    }

}