package com.tosan.bpms.framework.orm.repository.jpql;
import com.jpql.api.interfaces.*;
import com.jpql.api.interfaces.Parameter;
import javax.persistence.*;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.NoResultException;
import java.util.*;

/**
 * Created by kasra.haghpanah on 1/05/2019.
 */


public class Repository {


    private EntityManager entityManager;


    @javax.inject.Inject
    public Repository(com.google.inject.Provider<EntityManager> entityManager) {
        this.entityManager = entityManager.get();
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }



    //private static final Logger LOGGER = LoggerFactory.getLogger(MongoMapperImpl.class);

public <M extends Model> List<M> save(List<M> entities) {
        return Facade.save(entityManager, entities);
    }

    public <M extends Model> List<M> save(M... entities) {

        if (entities == null || entities.length == 0) {
            return null;
        }

        List<M> entitieList = new ArrayList<M>();

        for (int i = 0; i < entities.length; i++) {
            entitieList.add(entities[i]);
        }

        return Facade.save(entityManager, entitieList);
    }

    public <D extends DTO> List<D> save(D... dtos) {

        if (dtos == null || dtos.length == 0) {
            return null;
        }

        List<Model> converts = new ArrayList<Model>();

        for (int i = 0; i < dtos.length; i++) {
            converts.add(dtos[i].convertToJPAModel());
        }

        List<Model> models = Facade.save(entityManager, converts);

        if (models == null || models.size() == 0) {
            return null;
        }

        List<DTO> result = new ArrayList<DTO>();
        DTO d = dtos[0];

        for (Model model : models) {
            result.add((DTO) d.convertToDTO(model));
        }

        return (List<D>) result;

    }

    public <D extends DTO> List<D> save(ArrayList<D> dtos) {

        if (dtos == null || dtos.size() == 0) {
            return null;
        }

        List<Model> converts = new ArrayList<Model>();
        int length = dtos.size();

        for (D dto : dtos) {
            converts.add(dto.convertToJPAModel());
        }

        List<Model> models = Facade.save(entityManager, converts);

        if (models == null || models.size() == 0) {
            return null;
        }

        List<DTO> result = new ArrayList<DTO>();
        DTO d = dtos.get(0);

        for (Model model : models) {
            result.add((DTO) d.convertToDTO(model));
        }

        return (List<D>) result;

    }

    public <M extends Model> M save(M entity) {

        List<M> entities = new ArrayList<M>();
        entities.add(entity);
        entities = Facade.save(entityManager, entities);
        if (entities == null) {
            return null;
        }
        return entities.get(0);
    }

    public <D extends DTO<D, M>, M extends Model> D save(D dto) {

        if (dto == null) {
            return null;
        }
        M entity = save(dto.convertToJPAModel());
        if (entity == null) {
            return null;
        }
        return dto.convertToDTO(entity);
    }

    public <M extends Model> void delete(List<M> entities) {

        if (entities == null || entities.size() == 0) {
            return;
        }
        Facade.getByFilter(entityManager, entities, false, 0, 100, null, null, null, null, false, "AND", FetchType.LAZY, null, true, "");
    }

    public <D extends DTO> void delete(D... dtos) {

        if (dtos == null || dtos.length == 0) {
            return;
        }

        List<Model> models = new ArrayList<Model>();
        int length = dtos.length;
        for (int i = 0; i < length; i++) {
            models.add(dtos[i].convertToJPAModel());
        }

        Facade.getByFilter(entityManager, models, false, 0, 100, null, null, null, null, false, "AND", FetchType.LAZY, null, true, "");
    }

    public <D extends DTO> void delete(ArrayList<D> dtos) {

        if (dtos == null || dtos.size() == 0) {
            return;
        }

        List<Model> models = new ArrayList<Model>();
        for (D dto : dtos) {
            models.add(dto.convertToJPAModel());
        }

        Facade.getByFilter(entityManager, models, false, 0, 100, null, null, null, null, false, "AND", FetchType.LAZY, null, true, "");
    }

    public <M extends Model> void delete(M... entities) {

        if (entities == null || entities.length == 0) {
            return;
        }
        List<M> models = new ArrayList<M>();
        int length = entities.length;
        for (int i = 0; i < length; i++) {
            models.add(entities[i]);
        }

        Facade.getByFilter(entityManager, models, false, 0, 100, null, null, null, null, false, "AND", FetchType.LAZY, null, true, "");
    }

    public <T extends Model> JPABuilder jpql(List<T> models) {
        return new JPABuilder(this.entityManager, models, null);
    }

    public <T extends Model> JPABuilder jpql(T... models) {

        if (models == null || models.length == 0) {
            return null;
        }
        int length = models.length;
        List<T> tList = new ArrayList<T>();
        for (int i = 0; i < length; i++) {
            tList.add(models[i]);
        }

        return new JPABuilder(this.entityManager, tList, null);
    }

    public <T extends DTO> JPABuilder jpql(ArrayList<T> dtos) {
        if (dtos == null || dtos.size() == 0) {
            return null;
        }
        int size = dtos.size();
        T dto = null;
        List<Model> models = new ArrayList<Model>();
        for (int i = 0; i < size; i++) {
            if (dto == null && dtos.get(i) != null) {
                dto = dtos.get(i);
            }
            models.add(dtos.get(i).convertToJPAModel());
        }
        return new JPABuilder(this.entityManager, models, dto);
    }

    public <T extends DTO> JPABuilder jpql(T... dtos) {

        if (dtos == null || dtos.length == 0) {
            return null;
        }
        int length = dtos.length;
        T dto = null;
        List<Model> tList = new ArrayList<Model>();
        for (int i = 0; i < length; i++) {
            if (dto == null && dtos[i] != null) {
                dto = dtos[i];
            }
            tList.add(dtos[i].convertToJPAModel());
        }
        return new JPABuilder(this.entityManager, tList, dto);
    }

    public SQLInterface jpql(String jpql) {
        return new SQLBuilder(entityManager, "jpql", jpql + " ");
    }

    public SQLInterface sql(String sql) {
        return new SQLBuilder(entityManager, "sql", sql + " ");
    }

    public ProcedureInterface procedure(String procedureName) {
        return new SQLBuilder(entityManager, "procedure", procedureName);
    }


}