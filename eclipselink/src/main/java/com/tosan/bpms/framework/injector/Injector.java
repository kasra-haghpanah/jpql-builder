package com.tosan.bpms.framework.injector;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.matcher.Matchers;
import com.google.inject.name.Names;
import com.google.inject.persist.PersistService;
import com.google.inject.persist.jpa.JpaPersistModule;
import com.tosan.bpms.bean.scope.CustomScopes;
import com.tosan.bpms.bean.scope.Person;
import com.tosan.bpms.bean.statics.StaticInjection;
import com.tosan.bpms.framework.config.Config;
import com.tosan.bpms.framework.interceptor.AOP;
import com.tosan.bpms.framework.orm.repository.jpql.Repository;
import com.tosan.bpms.framework.orm.service.Service;
import com.tosan.bpms.bean.provider.Gum;
import com.tosan.bpms.bean.provider.GumProvider;
import com.tosan.bpms.framework.validator.custom.ComplexBeanValid;
import com.tosan.bpms.framework.validator.custom.ComplexBeanValidator;
import com.tosan.bpms.framework.validator.custom.CustomService;
//import com.tosan.bpms.service.GroupService;
import org.aopalliance.intercept.MethodInterceptor;
import org.reflections.Reflections;
import ru.vyarus.guice.validator.ImplicitValidationModule;

import javax.inject.Singleton;
import java.lang.reflect.Constructor;
import java.util.*;

/**
 * Created by kasra.haghpanah on 02/07/2017.
 */
public class Injector {

    static final Map<String, com.google.inject.Injector> injectorPersistMap = new HashMap<String, com.google.inject.Injector>();
    static final List<String> unitnames = Config.getPersistenceUnitnameList();

    public static <M extends Repository> M getReository(String unitname, Class<M> implementation) {

        if (injectorPersistMap.keySet().size() == 0) {
            synchronized (Injector.class) {
                if (unitnames.size() > 0) {
                    for (final String jpaUnit : unitnames) {
                        com.google.inject.Injector injectorPersist = injectorPersistMap.get(jpaUnit);
                        if (injectorPersist == null) {
                            if (injectorPersist == null) {
                                injectorPersist = Guice.createInjector(
                                        new AbstractModule() {
                                            @Override
                                            protected void configure() {
                                                install(new JpaPersistModule(jpaUnit));
                                                //for validation
                                                install(new ImplicitValidationModule());
                                            }
                                        });
                                PersistService persistService = injectorPersist.getInstance(PersistService.class);
                                persistService.start();
                                injectorPersistMap.put(jpaUnit, injectorPersist);
                            }
                        }
                    }
                }
            }
        }

        com.google.inject.Injector injectorPersist = injectorPersistMap.get(unitname);
        M instance = (M) injectorPersist.getInstance(implementation);
        return instance;
    }


    public static <M extends Service> M getService(Class<M> implementation) {

        return getBean(implementation);
    }


    public static <M> M getBean(Class<M> implementation) {

        com.google.inject.Injector injectorPersist = injectorPersistMap.get("simple-bean");

        if (injectorPersist == null) {

            synchronized (Injector.class) {
                injectorPersist = Guice.createInjector(
                        new AbstractModule() {
                            @Override
                            protected void configure() {


                                //bind(UserService.class).annotatedWith(Names.named("persist")).toInstance(getReository("kafka", UserService.class));
                                //bind(new TypeLiteral<Service<User>>() {}).annotatedWith(Names.named("persist")).toInstance(getReository("kafka", UserService.class));

                                //LogService logService = getReository("kafka_log", LogService.class);
                                //UserService userService = getReository("kafka", UserService.class);
                                //GroupService groupService = getReository("kafka", GroupService.class);
                                ////////////////////////////////////////////////////////////////////////////////////////////
                                for (String jpaUnit : unitnames) {
                                    bind(Repository.class).annotatedWith(Names.named(jpaUnit)).toInstance(getReository(jpaUnit, Repository.class));
                                }

//                                bind(Repository.class).annotatedWith(Names.named("kafka")).toInstance(getReository("kafka", Repository.class));
//                                bind(Repository.class).annotatedWith(Names.named("kafka_log")).toInstance(getReository("kafka_log", Repository.class));


                                //bind(new TypeLiteral<Service<User>>() {}).toInstance(getReository("kafka", UserService.class));
                                ////////////////////////////////////////////////////////////////////////////////////////////
                                //bind(GroupService.class).toInstance(getReository("kafka", GroupService.class));
                                //bind(new TypeLiteral<Service<Group>>() {}).toInstance(getReository("kafka", GroupService.class));
                                ////////////////////////////////////////////////////////////////////////////////////////////
                                //bind(LogService.class).toInstance(getReository("kafka_log", LogService.class));
                                //bind(new TypeLiteral<Service<Log>>() {}).toInstance(getReository("kafka_log", LogService.class));
                                ////////////////////////////////////////////////////////////////////////////////////////////
//                                bind(MyService.class);
//                                bind(UserService.class);

                                // for property
                                Config.addResource("/config.properties");
                                Config.addResource("/META-INF/config2.properties");

                                Reflections reflections = new Reflections(Config.getPropertyByResource("/config.properties", "base-package"));
                                //Set<Class<? extends Service>> subTypes = reflections.getSubTypesOf(Service.class);

                                Set<Class<?>> annotatedWith = reflections.getTypesAnnotatedWith(Singleton.class);
                                for (Class aClass : annotatedWith) {
                                    bind(aClass);//.annotatedWith(Singleton.class);
                                }

                                annotatedWith = reflections.getTypesAnnotatedWith(com.google.inject.Singleton.class);
                                for (Class aClass : annotatedWith) {
                                    bind(aClass);//.annotatedWith(com.google.inject.Singleton.class);
                                }


                                //bind(GroupService.class);
                                //bind(LogService.class);
                                //bind(LogInterceptor.class);

                                //inteceptor
                                annotatedWith = reflections.getTypesAnnotatedWith(AOP.class);
                                for (Class aClass : annotatedWith) {
                                    Constructor<?>[] constructors = aClass.getConstructors();
                                    AOP aop = (AOP) aClass.getAnnotation(AOP.class);
                                    Object newInstance = null;
                                    try {
                                        newInstance = aClass.newInstance();
                                    } catch (InstantiationException e) {
                                        e.printStackTrace();
                                    } catch (IllegalAccessException e) {
                                        e.printStackTrace();
                                    }
                                    bindInterceptor(Matchers.any(), Matchers.annotatedWith(aop.annotated()), (MethodInterceptor) newInstance);
                                }
//                                bindInterceptor(Matchers.any(), Matchers.annotatedWith(Logged.class), new LogInterceptor(getReository("kafka_log", Repository.class)));
//                                bindInterceptor(Matchers.any(), Matchers.annotatedWith(Transactional.class), new JpaLocalTxnInterceptor2());


                                ////////////////////////////////////////////////////////////////////////////////////////////
                                bind(ComplexBeanValidator.class);
                                bind(CustomService.class);

                                //for validation
                                //install(new ImplicitValidationModule());

                                // custom provider
                                bind(Gum.class).toProvider(GumProvider.class);


                                Map<String, String> properties = Config.getResources().get("/config.properties"); //Config.getProperty("/config.properties");
                                Names.bindProperties(binder(), properties);
                                // for property


                                //for static injection
                                bindConstant().annotatedWith(Names.named("my.static")).to("My static method injection!");
                                requestStaticInjection(StaticInjection.class);
                                //for static injection


                                // custom scope
                                bind(Person.class).in(CustomScopes.DEFAULT);
                                // custom scope


                                //for validation
                                install(new ImplicitValidationModule()
                                        .withMatcher(
                                                Matchers
                                                        .not(Matchers.annotatedWith(ComplexBeanValid.class))
                                                        .and(Matchers.any())
                                        )
                                );


                            }
                        }
                );

            }

            injectorPersistMap.put("simple-bean", injectorPersist);

        }


        M instance = (M) injectorPersist.getInstance(implementation);

        return instance;
    }

}

