//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.tosan.bpms.framework.orm.service;

import com.jpql.api.interfaces.Cast;
import com.jpql.api.interfaces.Model;
import com.jpql.api.interfaces.Parameter;
import com.jpql.api.interfaces.QueryParameter;
import com.tosan.bpms.framework.orm.repository.jpql.JPABuilder;
import com.tosan.bpms.framework.orm.repository.jpql.Repository;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.FetchType;
import javax.validation.Valid;

public abstract class Service {
    final List<EntityManager> entityManagers = new ArrayList();

    public Service() {
    }

    public final List<EntityManager> getEntityManagers() {
        if (this.entityManagers.size() == 0) {
            Class clazz = this.getClass().getSuperclass();
            String className = clazz.getName();
            Field[] fields = clazz.getDeclaredFields();
            String repositoryClassName = Repository.class.getName();
            Field[] var5 = fields;
            int var6 = fields.length;

            for (int var7 = 0; var7 < var6; ++var7) {
                Field field = var5[var7];
                if (field.getType().getName().equals(repositoryClassName)) {
                    Repository repository = null;

                    try {
                        Object o = clazz.cast(this);
                        field.setAccessible(true);
                        repository = (Repository) field.get(o);
                    } catch (IllegalAccessException var11) {
                        var11.printStackTrace();
                    }

                    this.entityManagers.add(repository.getEntityManager());
                }
            }
        }

        return this.entityManagers;
    }

    public abstract <M extends Model> M save(@Valid M var1) throws Exception;

    public abstract <M extends Model> void delete(@Valid M var1);

    public abstract <M extends Model> JPABuilder jpql(M... var1);

    public abstract <M extends Model> JPABuilder jpql(List<M> models);

    public abstract <M extends Model> List<M> save(List<M> entities);

    public abstract <M extends Model> void delete(List<M> entities);
}
