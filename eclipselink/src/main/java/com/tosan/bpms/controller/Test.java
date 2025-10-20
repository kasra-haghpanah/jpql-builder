//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.tosan.bpms.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
    public Test() {
    }

    public static void main(String[] args) throws Exception{




       String x = "12345TO".replaceAll("TO","");

       System.out.println(x);

        Encryptor encryptor = new Encryptor();
        System.out.println(encryptor.encrypt("123456"));
        System.out.println("llllllllllllllllllllllllllllllllllllllllll");


        String path = "D:/Project/Java/SE/jpa/eclipselink/src/main/java/com/tosan/bpms/framework/orm/repository/jpql";
        String target = path.substring(0, path.indexOf("/src")) + "/target/classes";
        System.out.println(target);



        String spyProperties = "driverlist=com.p6spy.engine.spy.P6SpyDriverdateformat=yyyy-MM-dd hh:mm:ss a#appender=com.p6spy.engine.spy.appender.Slf4JLogger#appender=com.p6spy.engine.spy.appender.StdoutLoggerappender=com.p6spy.engine.spy.appender.FileLogger\n" +
                "logfile=./spy.log\n" +
                "logMessageFormat=com.p6spy.engine.spy.appender.SingleLineFormat";

        Pattern pattern = Pattern.compile("(logfile=)(.)+\n", Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(spyProperties);

        String spyLogAddress = "";
        if (matcher.find()) {
            String model = matcher.group();
             spyLogAddress = model.substring(model.indexOf("=") + 1, model.length() - 1);
        }

        System.out.println(spyLogAddress);


//        Map<String, String> map = new HashMap<String, String>();
//        map.put("Article0.articleId", " @field > ? AND @field <= ? ");
//
//        String query = " @field > ? AND @field <= ? ";
//        String variable = "Article0articleId";
//        String key = "Article0.articleId";
//        query = query.replaceAll("\\?", " :" + variable);
//        query = query.replaceAll("@field", key);
//        // String value = Facade.getStatement(map, "Article0.articleId");
//        System.out.println(query);

        /*
        ArrayList result = new ArrayList();
        result.add(Integer.valueOf(1));
        result.add(new Object[]{Integer.valueOf(1), "3"});
        boolean isObject = true;
        if(result.get(0) instanceof Object[]) {
            isObject = false;
        }

        System.out.println(Object[].class.getName());
        String methodName = "model.getTest().getName()";
        int lastIndexOf = methodName.lastIndexOf(".get");
        if(lastIndexOf == -1) {
            lastIndexOf = methodName.lastIndexOf(".is");
        }

        String setter = methodName.substring(0, lastIndexOf) + ".s" + methodName.substring(lastIndexOf + 2, methodName.length() - 1);
        System.out.println(setter);
        */
    }
}
