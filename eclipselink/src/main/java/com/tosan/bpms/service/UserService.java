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
import com.tosan.bpms.model.sql.Economic;
import com.tosan.bpms.model.sql.LogBox;
import com.tosan.bpms.model.sql.Measurment;
import java.io.IOException;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.validation.Valid;
import javax.validation.executable.ValidateOnExecution;
@Singleton
@RequestScoped
@ValidateOnExecution
public class UserService extends Service {
    @Inject
    @Named("kafka")
    Repository repository;
    @Inject
    @Named("kafka_log")
    Repository repository2;

    public UserService() {
    }

    @PostConstruct
    public void init() {
        System.out.println("UserNoSQL******************PostConstruct");
    }

    @Logged
    @Transactional(
            rollbackOn = {IOException.class, RuntimeException.class, ArithmeticException.class}
    )
    public <M extends Model> M save(@Valid M entity) {
        M user = this.repository.save(entity);
        Economic economic = new Economic();
        economic.setEnTitle("economic");
        economic.setFaTitle("اقتصادی");
        economic = (Economic)this.repository.save(economic);
        Measurment measurment = new Measurment();
        measurment.setName("Measurment");
        measurment = (Measurment)this.repository.save(measurment);
        LogBox logBox = new LogBox();
        logBox.setFirstName("kasra");
        logBox.setLastName("hk");
        logBox = (LogBox)this.repository2.save(logBox);
        if(user != null) {
            ;
        }

        return user;
    }

    @Logged
    @Transactional(
            rollbackOn = {IOException.class, RuntimeException.class, ArithmeticException.class}
    )
    public <M extends Model> void delete(@Valid M entity) {
        this.repository.delete(entity);
    }

    public <T extends Model> JPABuilder jpql(T... model) {
        return this.repository.jpql(model);
    }

    public <T extends Model> JPABuilder jpql(List<T> models) {
        return this.repository.jpql(models);
    }

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
