//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.tosan.bpms.controller;

import com.jpql.api.enums.DependencyInjectionType;
import com.jpql.api.interfaces.Model;
//import com.jpql.plugin.Generator;
import com.jpql.plugin.Generator;
import com.tosan.bpms.framework.injector.Injector;
import com.tosan.bpms.framework.orm.repository.jpql.Repository;
import com.tosan.bpms.model.sql.*;
import com.tosan.bpms.service.LogService;
import com.tosan.bpms.service.MyService;
import com.tosan.bpms.service.UserService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.persistence.FetchType;
import javax.persistence.ParameterMode;


public class UserController {

    public UserController() {
    }

    public List<String> test(String fname, String lname) {
        List<String> strings = new ArrayList();
        strings.add(fname);
        strings.add(lname);
        return strings;
    }

    public static void main(String[] args) {

        //this is a test

        String localRepository = "C:/Users/98911/.m2/repository";
        String projectAddress = "C:/PC/javaProject/SpringBoot/eclipselink";
        String persistence = "C:/PC/javaProject/SpringBoot/eclipselink/src/main/resources/META-INF/persistence.xml";
        String packageName = "com.tosan.bpms.dao";
        String[] jarFiles = new String[]{};


        Generator.analyzer(
                "", projectAddress,
                persistence,
                packageName,
                // projectAddress + "/src/main/java/com/tosan/loan/core/framework/dao"
                projectAddress + "/src/main/java/" + packageName.replaceAll("\\.", "\\/")
                , jarFiles, DependencyInjectionType.GUICE
        );


        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.getTime();
        Repository myRepository = Injector.getReository("kafka", Repository.class);
        List<Group> groupList10 = myRepository
                .jpql("SELECT group0.id , group0.role , user1.username , user1.password from com.tosan.bpms.model.sql.Group group0 JOIN group0.user  user1 WHERE group0.id >= :id")
                .showQuery(query -> {
                    System.out.println("**********Log**********");
                    System.out.println(query);
                    System.out.println("**********Log**********");
                })
                .query(
                        query -> query.setParameter("id", 1)
                )
                .cast(
                        record -> {
                            Group group = new Group();
                            group.setId(new Integer(record[0] + ""));
                            group.setRole(record[1] + "");

                            User user = new User();
                            user.setUsername(record[2] + "");
                            user.setPassword(record[3] + "");
                            group.setUser(user);

                            return group;
                        }
                )
                .disabledPagination(false)
                .page(0)
                .size(1000)
                .update(false)
                .build();


        List<OngoingPrjct> ongoingPrjctList123 = myRepository
                .sql("SELECT o.receptionNo , o.cntrlPrjctDate , o.lastPaymentDate , o.investId , o.ctc , o.ctd FROM ongoingprjct o WHERE o.ctc = ? ")
                .showQuery(query -> {
                    System.out.println("**********Log**********");
                    System.out.println(query);
                    System.out.println("**********Log**********");
                })
                .query(query -> {
                    query.setParameter(1, new BigDecimal(16000));
                })
                .cast(record -> {
                    //o.receptionNo , o.cntrlPrjctDate , o.lastPaymentDate , o.investId , o.ctc , o.ctd
                    OngoingPrjct ongoingPrjct = new OngoingPrjct();
                    OngoingPrjctKey key = new OngoingPrjctKey();
                    ongoingPrjct.setOngoingPrjctId(key);

                    key.setReceptionNo((String) record[0]);
                    key.setCntrlPrjctDate((Date) record[1]);
                    key.setLastPaymentDate((Date) record[2]);
                    key.setSubInvestSerial((Integer) record[3]);
                    ongoingPrjct.setCtc((BigDecimal) record[4]);
                    ongoingPrjct.setCtd((BigDecimal) record[5]);

                    return ongoingPrjct;
                })
                .update(false)
                .build();


        final List<Integer> sum = new ArrayList<Integer>();

        List<Cartizian> cartizianList = myRepository
                .procedure("calculate")
                .showQuery(query -> {
                    System.out.println("**********Log**********");
                    System.out.println(query);
                    System.out.println("**********Log**********");
                })
                .query(
                        query -> {

                            query.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
                            query.registerStoredProcedureParameter(2, Integer.class, ParameterMode.IN);
                            query.registerStoredProcedureParameter(3, Integer.class, ParameterMode.OUT);

                            query.setParameter(1, 4);
                            query.setParameter(2, 10);
                            //query.setParameter(3, new Double(0));
                        }
                )
                .cast(
                        record -> {
                            Cartizian cartizian = new Cartizian();
                            cartizian.setX((Double) record[0]);
                            cartizian.setY((Double) record[1]);
                            cartizian.setZ((Double) record[2]);
                            return cartizian;
                        }
                )
                .outputParameter(
                        query -> {
                            sum.add(new Integer(query.getOutputParameterValue(3) + ""));
                        }
                )
                .update(false)  // for insert,update,delete
                .build();

        Employee employee = new Employee();

        Project project = new Project();
        project.setProjectDate(new Date());
        project.setProjectName("project111");
        project.setId(5);

        List<Project> projects = new ArrayList<Project>();
        projects.add(project);

        project = new Project();
        project.setProjectDate(new Date());
        project.setProjectName("project222");
        project.setId(6);
        projects.add(project);


        myRepository.getEntityManager().getTransaction().begin();
        //myRepository.save(projects.get(0));

        //myRepository.save(projects.get(1));


        Portfolio ppd = new Portfolio();
        ppd.setPortfolioId(12);
        Address address33 = new Address();
        address33.setCity("Rasht");
        ppd.setAddress(address33);
        //////////////////////////////////////////////////////
        List<Portfolio> pfs = new ArrayList<Portfolio>();
        pfs.add(ppd);
        //////////////////////////////////////////////////////
        ppd = new Portfolio();
        ppd.setPortfolioId(14);
        address33 = new Address();
        //address33.setCity("Tehran");
        address33.setAddressLine1("adress1");
        address33.setState("us");
        ppd.setAddress(address33);
        //////////////////////////////////////////////////////
        //pfs.add(ppd);
        //////////////////////////////////////////////////////

        User uu = new User();
        uu.setUsername("alireza@yahoo.com");
        //myRepository.delete(pfs);
        myRepository.delete(uu);

        myRepository.getEntityManager().getTransaction().commit();


        employee.setProjects(projects);
        employee.setAge(15);
        employee.setFirstName("kasra");
        employee.setLastName("diba");
        employee.setId(2);

        myRepository.getEntityManager().getTransaction().begin();
        myRepository.save(employee);
        myRepository.getEntityManager().getTransaction().commit();

        project = new Project();
        project.setProjectName("project2");

        //////////////////////////////////////////
        employee = new Employee();
        employee.setLastName("haghpanah");

        List<Employee> employeesList = myRepository.jpql(employee).build();
        //////////////////////////////////////////
        List<Project> projects1 = myRepository.jpql(project).build();
        //List<String> unitnames = Generator.getPersistenceUnitnameList("E:/playProject/jpa/eclipselink/src/main/resources/META-INF/javax.persistence.xml");

        MyService myService = (MyService) Injector.getBean(MyService.class);
        UserService repository = Injector.getBean(UserService.class);

        Group g = new Group();
        g.setRole("admin");
        //g.set();

        List<Group> groupFilterList = new ArrayList<Group>();
        groupFilterList.add(g);

        g = new Group();
        g.setRole("user");

        groupFilterList.add(g);


        List<Group> groups4 = repository
                .jpql(groupFilterList)
                .fetch(true)
                .fetchType(FetchType.LAZY)
                .page(0)
                .size(100)
                .select("Group0.role , Group0.id ")
                .showQuery(query -> {
                    System.out.println("**********Log**********");
                    System.out.println(query);
                    System.out.println("**********Log**********");
                }).cast(
                        record -> {
                            Group group = new Group();
                            group.setRole((String) record[0]);
                            group.setId(((Integer) record[1]).intValue());
                            return group;
                            //return new User();
                        }
                ).build();


        Evaluation evaluation4 = new Evaluation();
        File file3 = new File();
        file3.setExtension("jpg");
        evaluation4.setFile1(file3);

        Reception rec = new Reception();
        rec.setBranch("123");
        Portfolio por = new Portfolio();

        List<Reception> receptionList = new ArrayList<Reception>();
        receptionList.add(rec);
        receptionList.add(rec);
        por.setReceptions(receptionList);


        Address add = new Address();
        add.setAddressLine1("line1");
        por.setAddress(add);
        List<PortfolioLicense> licenses = new ArrayList();
        PortfolioLicense pl = new PortfolioLicense();
        file3 = new File();
        file3.setId("12");
        pl.setFile(file3);
        pl.setPortfolioLicenseId(Integer.valueOf(12));
        pl.setPortfolioLicenseId(Integer.valueOf(34));
        LicenseDetails licenseDetail = new LicenseDetails();
        licenseDetail.setAddress(add);
        licenseDetail.setFile(file3);
        licenseDetail.setLicenseDetailId(Integer.valueOf(15));
        licenseDetail.setPortfolioLicense(pl);
        ArrayList<LicenseDetails> licenseDetails = new ArrayList();
        licenseDetails.add(licenseDetail);
        licenseDetails.add(licenseDetail);
        pl.setLicenseDetails(licenseDetails);
        licenses.add(pl);
        licenses.add(pl);
        por.setPortfolioLicenses(licenses);
        List<PortfolioLocation> locations = new ArrayList();
        PortfolioLocation plo = new PortfolioLocation();
        locations.add(plo);
        plo.setCity("rasht");
        List<LocationDetails> locationDetails = new ArrayList();
        LocationDetails locationDetail = new LocationDetails();
        locationDetail.setAddress(add);
        locationDetail.setFile(file3);
        locationDetail.setLocationDetailId(Integer.valueOf(12));
        locationDetails.add(locationDetail);
        locationDetails.add(locationDetail);
        plo.setLocationDetails(locationDetails);
        por.setPortfolioLocations(locations);
        rec.setPortfolio(por);
        evaluation4.setReception(rec);

        System.out.println("*********************************************************");
        long time = System.currentTimeMillis();
        List<Evaluation> evaluationList = repository
                .jpql(evaluation4)
                .fetch(true)
                .fetchType(FetchType.EAGER)
                .page(0)
                .size(100)
                .select("Reception3.receptionNo")
                .select("Evaluation0.evaluationId")
                .select("Portfolio4.portfolioId")
                .cast(
                        record -> {
                            Evaluation evaluation = new Evaluation();
                            evaluation.setEvaluationId(new Integer("" + record[1]));
                            Reception reception = new Reception();
                            reception.setReceptionNo(new Long("" + record[0]));
                            Portfolio portfolio = new Portfolio();
                            portfolio.setPortfolioId(new Integer("" + record[2]));
                            reception.setPortfolio(portfolio);
                            evaluation.setReception(reception);
                            return evaluation;
                        }
                )
                .showQuery(
                        query -> {
                            System.out.println("**********Log**********");
                            System.out.println(query);
                            System.out.println("**********Log**********");
                        }
                )
                .or().where("Reception3.receptionNo > 0 ")
                .or().where("Portfolio4.portfolioId != 0")
                .and().where("Portfolio4.economicType > 0")
                .groupBy("Reception3.receptionNo")
                .groupBy("Portfolio4.portfolioId")
                .or().having("COUNT( Reception3.receptionNo ) > 0")
                .and().having("COUNT(Portfolio4.portfolioId) > 0")
                .or().having("COUNT(Portfolio4.economicType) > 0")
                .orderBy("Reception3.receptionNo DESC")
                .orderBy("Portfolio4.portfolioId")
                .statement(map -> {
                    map.put("File7.id", "File7.id <= ? AND File7.id != '203' ");
                    map.put("LocationDetails17.address.addressLine1", "LocationDetails17.address.addressLine1 != :myAddress");
                }).query(query -> {
                    query.setParameter("myAddress", "usa");
                })
                .build();
        System.out.println((System.currentTimeMillis() - time) + "ttttttttttttttttt");
        System.out.println("*********************************************************");


        User user = new User();
        user.setUsername("testJPA@yahoo.com");

        Group group12 = new Group();
        group12.setRole("admin");

        List<Group> groups5 = new ArrayList<Group>();
        groups5.add(group12);
        user.setGroups(groups5);


        List<User> users = repository.jpql(user).fetch(true).page(0).size(100)//.select((select) -> {return select;})
                .showQuery((query) -> {
                    System.out.println("**********Log**********");
                    System.out.println(query);
                    System.out.println("**********Log**********");
                }).build();
        Log log = new Log();
        log.setId(59);
        LogService logService = (LogService) Injector.getBean(LogService.class);

        List<Log> logs = logService.jpql(log).fetch(true).build();

        if (logs != null && logs.size() > 0) {
            log = logs.get(0);
        }
        //////////////////////////////////////////////////test
        List<OngoingPrjct> ongoingPrjcts = new ArrayList<OngoingPrjct>();
        OngoingPrjctKey ongoingPrjctKey = new OngoingPrjctKey();
        Date date = new Date(2019, 3, 20);
        ongoingPrjctKey.setCntrlPrjctDate(date);
        ongoingPrjctKey.setLastPaymentDate(date);
        ongoingPrjctKey.setReceptionNo("123");
        ongoingPrjctKey.setSubInvestSerial(Integer.valueOf(12));
        OngoingPrjct ongoingPrjct = new OngoingPrjct();
        ongoingPrjct.setOngoingPrjctId(ongoingPrjctKey);
        ongoingPrjct.setCtc(new BigDecimal("12432"));
        ongoingPrjct.setInvestType(Integer.valueOf(1));
        ongoingPrjct.setCtd(new BigDecimal(2020));
        ongoingPrjct.setCtc(new BigDecimal("15575"));
        ongoingPrjcts.add(ongoingPrjct);
        //////////////////////////////////////////////////test
        ongoingPrjctKey = new OngoingPrjctKey();
        date = new Date(2025, 3, 20);
        ongoingPrjctKey.setCntrlPrjctDate(date);
        ongoingPrjctKey.setLastPaymentDate(date);
        ongoingPrjctKey.setReceptionNo("123");
        ongoingPrjctKey.setSubInvestSerial(Integer.valueOf(12));
        ongoingPrjct = new OngoingPrjct();
        ongoingPrjct.setOngoingPrjctId(ongoingPrjctKey);
        ongoingPrjct.setCtc(new BigDecimal("13210"));
        ongoingPrjct.setInvestType(Integer.valueOf(1));
        ongoingPrjct.setCtd(new BigDecimal(2020));
        ongoingPrjct.setCtc(new BigDecimal("16654"));
        ongoingPrjcts.add(ongoingPrjct);
        //////////////////////////////////////////////////test
        ongoingPrjctKey = new OngoingPrjctKey();
        date = new Date(2027, 3, 20);
        ongoingPrjctKey.setCntrlPrjctDate(date);
        ongoingPrjctKey.setLastPaymentDate(date);
        ongoingPrjctKey.setReceptionNo("123");
        ongoingPrjctKey.setSubInvestSerial(Integer.valueOf(12));
        ongoingPrjct = new OngoingPrjct();
        ongoingPrjct.setOngoingPrjctId(ongoingPrjctKey);
        ongoingPrjct.setCtc(new BigDecimal("13000"));
        ongoingPrjct.setInvestType(Integer.valueOf(1));
        ongoingPrjct.setCtd(new BigDecimal(2020));
        ongoingPrjct.setCtc(new BigDecimal("16000"));
        ongoingPrjcts.add(ongoingPrjct);
        //////////////////////////////////////////////////test
        ongoingPrjctKey = new OngoingPrjctKey();
        date = new Date(2029, 3, 20);
        ongoingPrjctKey.setCntrlPrjctDate(date);
        ongoingPrjctKey.setLastPaymentDate(date);
        ongoingPrjctKey.setReceptionNo("123");
        ongoingPrjctKey.setSubInvestSerial(Integer.valueOf(12));
        ongoingPrjct = new OngoingPrjct();
        ongoingPrjct.setOngoingPrjctId(ongoingPrjctKey);
        ongoingPrjct.setCtc(new BigDecimal("13000"));
        ongoingPrjct.setInvestType(Integer.valueOf(1));
        ongoingPrjct.setCtd(new BigDecimal(2020));
        ongoingPrjct.setCtc(new BigDecimal("16000"));
        ongoingPrjcts.add(ongoingPrjct);
        //////////////////////////////////////////////////test
        List<OngoingPrjct> ongoingPrjctList2 = repository.save(ongoingPrjcts);

        //repository.delete(ongoingPrjctList2);
        OngoingPrjct ongoingPrjctSearch = new OngoingPrjct();
        OngoingPrjctKey ongoingPrjctKeySearch = new OngoingPrjctKey();
        ongoingPrjctKeySearch.setReceptionNo("123");
        ongoingPrjctSearch.setOngoingPrjctId(ongoingPrjctKeySearch);

        //entity, boolean isFetch, int page, int size, Parameter select, Parameter jpql, QueryParameter queryParameter, Cast<T> cast, FetchType fetchType


        List<OngoingPrjct> ongoingPrjctList =
                repository
                        .jpql(ongoingPrjctSearch)
                        .fetch(true)
                        .fetchType(FetchType.EAGER)
                        .page(0)
                        .size(100)
                        .select("OngoingPrjct0.ctc")
                        .select("OngoingPrjct0.ongoingPrjctId")
                        .select("OngoingPrjct0.investType")
                        .select("OngoingPrjct0.ctd")
                        .select("OngoingPrjct0.priceDone")
                        .showQuery((query) -> {
                            System.out.println("**********Log**********");
                            System.out.println(query);
                            System.out.println("**********Log**********");
                        })
                        .cast(
                                (record) -> {
                                    OngoingPrjct prjct = new OngoingPrjct();
                                    if (record != null && record.length != 0) {
                                        prjct.setCtc((BigDecimal) record[0]);
                                        prjct.setOngoingPrjctId((OngoingPrjctKey) record[1]);
                                        prjct.setInvestType((Integer) record[2]);
                                        prjct.setCtd((BigDecimal) record[3]);
                                        prjct.setPriceDone((BigDecimal) record[4]);
                                        return prjct;
                                    } else {
                                        return prjct;
                                    }
                                }
                        )
                        .build();


        for (int i = 0; i < ongoingPrjctList.size(); ++i) {
            OngoingPrjct ongoingPrjct1 = (OngoingPrjct) ongoingPrjctList.get(i);
            System.out.println(ongoingPrjct1);
        }

        System.out.println(ongoingPrjctList.size());
        Evaluation evaluation = new Evaluation();
        evaluation.setEvaluationId(Integer.valueOf(12));
        File file1 = new File();
        file1.setId("file1");
        evaluation.setFile1(file1);
        File file2 = new File();
        file2.setId("file2");
        evaluation.setFile2(file2);
        Reception reception = new Reception();
        reception.setReceptionNo(Long.valueOf(2L));
        Portfolio portfolio = new Portfolio();
        portfolio.setPortfolioId(Integer.valueOf(12345));
        portfolio.setBranchCode("7890");
        reception.setPortfolio(portfolio);
        evaluation.setReception(reception);
        User user1 = new User();
        Address address = new Address();
        address.setAddressLine1("lin1");
        address.setAddressLine2("lin2");
        address.setZipCode("123456789");
        user1.setAddress(address);
        Group group1 = new Group();
        group1.setId(123);
        List<Group> groups1 = new ArrayList();
        user1.setGroups(groups1);
        portfolio.setAddress(address);
        repository.jpql(evaluation).build();
        Group group2 = new Group();
        group2.setRole("admin");
        User user2 = new User();
        user2.setUsername("kasra");
        group2.setUser(user2);
        List<Group> groups2 = repository.jpql(group2).build();
        System.out.println(groups2);
        MaterialOfProject materialOfProject = new MaterialOfProject();
        materialOfProject.setDescription("test!");
        materialOfProject.setMaterialName("materialName");
        ProductsOfProject productsOfProject = new ProductsOfProject();
        productsOfProject.setCalSelection(Boolean.valueOf(true));
        productsOfProject.setDescription("description");
        Article article = new Article();
        article.setEnTitle("My Article");
        Measurment measurment = new Measurment();
        measurment.setName("Measurment");
        Economic economic = new Economic();
        economic.setEnTitle("Economic");
        Industry industry = new Industry();
        industry.setEnTitle("Industry");
        article.setIndustry(industry);
        article.setMeasurement(measurment);
        article.setEconomic(economic);
        materialOfProject.setProductsOfProject(productsOfProject);
        productsOfProject.setArticle(article);
        PortfolioLicense portfolioLicense = new PortfolioLicense();
        portfolioLicense.setFile(file1);
        portfolioLicense.setIssueDate(new Date());
        portfolioLicense.setPortfolio(portfolio);
        productsOfProject.setPortfolioLicense(portfolioLicense);
        productsOfProject.setAddress(address);
        repository.jpql(materialOfProject).build();
        String jpql = "SELECT MaterialOfProject FROM com.tosan.bpms.model.sql.MaterialOfProject MaterialOfProject  \nJOIN FETCH MaterialOfProject.productsOfProject MaterialOfProjectproductsOfProject \nJOIN FETCH MaterialOfProjectproductsOfProject.article MaterialOfProjectproductsOfProjectarticle  \nJOIN FETCH MaterialOfProjectproductsOfProjectarticle.economic MaterialOfProjectproductsOfProjectarticleeconomic\nJOIN FETCH MaterialOfProjectproductsOfProjectarticle.industry MaterialOfProjectproductsOfProjectarticleindustry \nJOIN FETCH MaterialOfProjectproductsOfProjectarticle.measurement MaterialOfProjectproductsOfProjectarticlemeasurement \nJOIN FETCH MaterialOfProjectproductsOfProject.portfolioLicense MaterialOfProjectproductsOfProjectportfolioLicense   \nJOIN FETCH MaterialOfProjectproductsOfProjectportfolioLicense.file MaterialOfProjectproductsOfProjectportfolioLicensefile  \nJOIN FETCH MaterialOfProjectproductsOfProjectportfolioLicense.portfolio MaterialOfProjectproductsOfProjectportfolioLicenseportfolio  \nWHERE 1=1  AND MaterialOfProject.evaluationUnit LIKE :MaterialOfProjectevaluationUnit ";
        jpql = "SELECT Evaluation0 FROM com.tosan.bpms.model.sql.Evaluation Evaluation0  JOIN FETCH Evaluation0.file1 File3  JOIN FETCH Evaluation0.file2 File4  JOIN FETCH Evaluation0.reception Reception1  JOIN FETCH Reception1.portfolio Portfolio2   WHERE 1=1  AND Evaluation0.evaluationId = :Evaluation0evaluationId  AND Reception1.receptionNo = :Reception1receptionNo  AND Portfolio2.branchCode = :Portfolio2branchCode  AND File3.id = :File3id AND File4.id = :File4id ";

        List<Evaluation> evaluationList1 = myRepository
                .jpql(jpql)
                .query(

                        query -> {
                            query.setParameter("Evaluation0evaluationId", Integer.valueOf(12));
                            query.setParameter("Reception1receptionNo", Long.valueOf(10L));
                            query.setParameter("Portfolio2branchCode", "123");
                            query.setParameter("File3id", "rerqw");
                            query.setParameter("File4id", "1qwrqwr23");
                        }
                )
                .cast(
                        record -> {
                            return record[0];
                        }
                )
                .build();
        //GroupService groupService = (GroupService) Injector.getBean(GroupService.class);
        Group group = new Group();
        group.setRole("admin");
        group.setVersion(1);

        evaluation = new Evaluation();
        evaluation.setEvaluationId(Integer.valueOf(333));
        reception = new Reception();
        reception.setBranch("123");
        portfolio.setAssessdAMT("test");
        portfolio.setBranchCode("123");
        reception.setPortfolio(portfolio);
        evaluation.setReception(reception);
        File file = new File();
        file.setId("1");
        evaluation.setFile1(file);
        file2 = new File();
        file2.setId("2");
        evaluation.setFile2(file2);

        String[] var10000 = new String[]{"admin", "user", "worker", "supervisor", "programmer", "doctor"};
        List<Industry> industries = new ArrayList();

        for (int i = 0; i < 10; ++i) {
            (new Thread(() -> {
                Industry industry1 = new Industry();
                industry1.setIndustryId((String) null);
                industry1.setFaTitle("title");
                industries.add(repository.save(industry1));
            })).run();
        }

        System.out.println(industries);
    }

    public static void insertUser(String username, String password, String[] roles) {
        UserService userService = (UserService) Injector.getBean(UserService.class);
        User myUser = new User();
        myUser.setUsername(username);
        List<User> users = userService.jpql(myUser).build();
        User user = new User();
        if (users != null && users.size() > 0) {
            user = (User) users.get(0);
        }

        user.setUsername(username);
        user.setPassword(password);
        Address address = new Address("addressLine1", "addressLine2", "tehran", "Tehran", "iran", "1234567890");
        user.setAddress(address);
        List<Group> groupList = new ArrayList();
        if (roles != null && roles.length > 0) {
            String[] var9 = roles;
            int var10 = roles.length;

            for (int var11 = 0; var11 < var10; ++var11) {
                String role = var9[var11];
                Group group = new Group();
                group.setUser(user);
                group.setRole(role);
                groupList.add(group);
            }
        }

        user.setGroups(groupList);
        user = (User) userService.save(user);
    }

    public static void deleteUserById(String username) {
        UserService userService = (UserService) Injector.getBean(UserService.class);
        User user = new User();
        user.setUsername(username);
        List<User> users = userService.jpql(user).build();
        if (users != null && users.size() > 0) {
            userService.delete((Model) users.get(0));
        }

    }


}
