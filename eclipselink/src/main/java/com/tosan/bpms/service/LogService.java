//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.tosan.bpms.service;

import com.google.inject.persist.Transactional;
import com.jpql.api.interfaces.Model;
import com.tosan.bpms.interceptor.Logged;
import com.tosan.bpms.framework.orm.repository.jpql.JPABuilder;
import com.tosan.bpms.framework.orm.repository.jpql.Repository;
import com.tosan.bpms.framework.orm.service.Service;

import java.io.IOException;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

@Singleton
public class LogService extends Service {
    @Inject
    @Named("kafka_log")
    Repository repository;

    public LogService() {
    }

    @PostConstruct
    public void start() {
        System.out.println("Log******************PostConstruct");
    }

    @Transactional(
            rollbackOn = {IOException.class, RuntimeException.class, ArithmeticException.class}
    )
    public <M extends Model> M save(M entity) {
        return this.repository.save(entity);
    }

    @Transactional(
            rollbackOn = {IOException.class, RuntimeException.class, ArithmeticException.class}
    )
    public <M extends Model> void delete(M entity) {
        this.repository.delete(entity);
    }

    public <M extends Model> JPABuilder jpql(M... model) {
        return this.repository.jpql(model);
    }

    public <M extends Model> JPABuilder jpql(List<M> models) {
        return this.repository.jpql(models);
    }

//    public <M extends Model, R> JPABuilder jpql( M... model) {
//        return this.repository.jpql(cast, model);
//    }
//
//    public <M extends Model, R> JPABuilder jpql( List<M> models) {
//        return this.repository.jpql(models);
//    }

    @Logged
    @Transactional(
            rollbackOn = {IOException.class, RuntimeException.class, ArithmeticException.class}
    )
    public <M extends Model> List<M> save(List<M> entities) {
        return repository.save(entities);
    }

    @Logged
    @Transactional(
            rollbackOn = {IOException.class, RuntimeException.class, ArithmeticException.class}
    )
    public <M extends Model> void delete(List<M> entities) {
        repository.delete(entities);
    }

}
