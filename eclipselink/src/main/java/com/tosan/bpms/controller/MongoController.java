package com.tosan.bpms.controller;

/**
 * Created by kasra.haghpanah on 01/09/2018.
 */
public class MongoController {

//    public static void run() {
//        SparkSession spark = SparkSession.builder()
//        .master("local")
//                .appName("MongoSparkConnectorIntro")
//                .config("spark.mongodb.input.uri", "mongodb://127.0.0.1/db.myCollection")
//                .config("spark.mongodb.output.uri", "mongodb://127.0.0.1/db.myCollection")
//                .getOrCreate();
//
//        // Create a JavaSparkContext using the SparkSession's SparkContext object
//        JavaSparkContext jsc = new JavaSparkContext(spark.sparkContext());
//    }
//
//    public static void main(String[] args) {
//
//        //spark-submit --class "MongoController" --master local[2] "E:\playProject\eclipselink\target\eclipselink.jar"
//        run();
//        MyClass myClass = new MyClass(29);
//        System.out.println(myClass.myMethod(12));
//        myClass.id_$eq(37);
//        System.out.println(myClass.id());
//
//
//
//        Email email = new Email();
//
//        email.setMessageId("1");
//        email.setSubject("Please Join Meeting");
//        email.setBody("Agenda: RFP discussion");
//        email.setFrom(new Contact("a", "Amresh", "Singh", "xamry@gmail.com"));
//
//        email.addTo(
//                new Contact("b", "Vivek", "Mishra", "mevivs@gmail.com"),
//                new Contact("c", "Saurabh", "Singh", "saurabh@gmail.com")
//        );
//
//        email.addAttachment(
//                new Attachment("aaa", "Agenda.doc", "MS Word"),
//                new Attachment("bbb", "MOM_Last_Meeting.xls", "MS Excel"),
//                new Attachment("ccc", "Client_Feedback.txt", "Text")
//        );
//
//
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("spark_mongo_pu");
//        EntityManager em = emf.createEntityManager();
//
//        //em.persist(email);
//        em.persist(new Person("201595","kasra" , 33 ,4500000d));
//        em.close();
//
//        emf.close();
//
//
//        String[] roles = new String[]{"admin", "user", "worker", "supervisor", "programmer", "doctor"};
//
//        deleteUserById("test@yahoo.com");
//
//        insertUser("test@yahoo.com", "123", roles);
//
//
//        emf = Persistence.createEntityManagerFactory("spark_mongo_pu");
//        em = emf.createEntityManager();
//
//        List<GroupNoSQL> userNoSQLS = em.createQuery("SELECT g From GroupNoSQL g  WHERE g.userNoSQL =:username").setParameter("username", "test@yahoo.com").getResultList();
//
//        System.out.println(userNoSQLS);
//
//        em.close();
//
//        emf.close();
//    }
//
//
//    public static void deleteUserById(String username) {
//
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("spark_mongo_pu");
//        EntityManager em = emf.createEntityManager();
//
//        List<UserNoSQL> userNoSQLS = em.createQuery("SELECT u From UserNoSQL u WHERE u.username =:username").setParameter("username", username).getResultList();
//
//
//        if (userNoSQLS != null && userNoSQLS.size() > 0) {
//            for (UserNoSQL userNoSQL : userNoSQLS) {
//                em.remove(userNoSQL);
//            }
//        }
//        em.close();
//        emf.close();
//
//    }
//
//
//    public static void insertUser(String username, String password, String[] roles) {
//
//        UserNoSQL user = new UserNoSQL();
//        user.setUsername(username);
//        user.setPassword(password);
//
//        AddressNoSQL address = new AddressNoSQL("addressLine1", "addressLine2", "tehran", "Tehran", "iran", "1234567890");
//        user.setAddress(address);
//
//        List<GroupNoSQL> groupList = new ArrayList<GroupNoSQL>();
//
//        if (roles != null && roles.length > 0) {
//            for (String role : roles) {
//                GroupNoSQL group = new GroupNoSQL();
//                group.setUserNoSQL(user);
//                group.setRole(role);
//                groupList.add(group);
//            }
//        }
//
//        user.setGroups(groupList);
//        //*************************************************************
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("spark_mongo_pu");
//        EntityManager em = emf.createEntityManager();
//        em.remove(user);
//
//        em.persist(user);
//        em.close();
//
//        emf.close();
//
//    }
//

}
