package com.tosan.bpms.interceptor;

import com.google.inject.persist.Transactional;
import com.google.inject.persist.UnitOfWork;
import com.tosan.bpms.framework.interceptor.AOP;
import com.tosan.bpms.framework.orm.service.Service;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import javax.persistence.EntityManager;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by kasra.haghpanah on 15/12/2018.
 */
@AOP(annotated = Transactional.class)
public class JpaLocalTxnInterceptor2 implements MethodInterceptor {

    private UnitOfWork unitOfWork = null;
    //private final ThreadLocal<Boolean> didWeStartWork = new ThreadLocal();

    //@Transactional
    public Object invoke(MethodInvocation invocation) throws Throwable {

        Transactional transactional = invocation.getMethod().getAnnotation(Transactional.class);
        List<EntityManager> entityManagers = ((Service) invocation.getThis()).getEntityManagers();

        //begin(entityManagers);

        //repository.getEntityManager().getTransaction().begin();
        Throwable exception = null;
        Method method = invocation.getMethod();
        boolean isRollback = false;
        int size = 0;

        if (entityManagers != null) {
            size = entityManagers.size();
        }

        Object o = null;
        begin(entityManagers);

        try {
            o = invocation.proceed();
            commit(entityManagers);
        } catch (Throwable throwable) {
            exception = throwable;
            throwable.printStackTrace();
            rollback(entityManagers, transactional);
        } finally {

            merge(entityManagers, transactional, exception);
//            if (exception != null && contain(exception, transactional.rollbackOn())) {
//                rollback(entityManagers, transactional);
//            } else {
//                commit(entityManagers, transactional);
//            }
        }


        return o;
    }


    private void begin(List<EntityManager> entityManagers) {

        int size = 0;

        if (entityManagers != null) {
            size = entityManagers.size();
        }

        if (size > 0) {
            for (EntityManager entityManager : entityManagers) {

                if (!entityManager.getTransaction().isActive()) {
                    entityManager.getTransaction().begin();
                }

            }
        }

    }

    private void commit(List<EntityManager> entityManagers) {

        int size = 0;

        if (entityManagers != null) {
            size = entityManagers.size();
        }

        if (size > 0) {
            for (EntityManager entityManager : entityManagers) {
                if (entityManager.getTransaction().isActive()) {
                    entityManager.getTransaction().commit();
                }
            }
        }

    }


    private void rollback(List<EntityManager> entityManagers, Transactional transactional) {

        int size = 0;

        if (entityManagers != null) {
            size = entityManagers.size();
        }

        if (size > 0) {
            for (EntityManager entityManager : entityManagers) {
                if (entityManager.getTransaction().isActive()) {
                    entityManager.getTransaction().rollback();
                }
            }
        }

    }

    private void commit(List<EntityManager> entityManagers, Transactional transactional) {

        int size = 0;

        if (entityManagers != null) {
            size = entityManagers.size();
        }

        if (size > 0) {
            for (EntityManager entityManager : entityManagers) {

                if (entityManager.getTransaction().isActive()) {
                    // didWeStartWork.set(true);
                    // if (didWeStartWork.get() != null) {
                    entityManager.getTransaction().commit();
                    //  didWeStartWork.remove();
                    //}
                }
            }
        }

    }


    private void merge(List<EntityManager> entityManagers, Transactional transactional, Throwable exception) {

        int size = 0;

        if (entityManagers != null) {
            size = entityManagers.size();
        }

        if (size > 0) {
            for (EntityManager entityManager : entityManagers) {
                if (!entityManager.getTransaction().isActive()) {
                    entityManager.getTransaction().begin();
                    if (exception != null && contain(exception, transactional.rollbackOn())) {
                        if (entityManager.getTransaction().isActive()) {
                            entityManager.getTransaction().rollback();
                        }
                    } else {
                        if (entityManager.getTransaction().isActive()) {
                            entityManager.getTransaction().commit();
                        }
                    }
                }
            }
        }

    }

    private boolean contain(Throwable throwable, Class<? extends Exception>[] exceptions) {

        if (exceptions == null || exceptions.length == 0) {
            return false;
        }

        for (Class<? extends Exception> exception : exceptions) {
            if (exception == throwable.getClass()) {
                return true;
            }
        }

        return false;

    }
}
