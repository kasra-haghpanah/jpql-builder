package com.tosan.bpms.framework.config;

import com.tosan.bpms.framework.utility.BrowserUtility;
import org.apache.commons.io.FileUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by kasra.haghpanah on 25/06/2018.
 */
public class Config {

    static final Map<String, Map<String, String>> resources = new HashMap<String, Map<String, String>>();
    static final Map<String, Properties> properties = new HashMap<String, Properties>();

    public static void addResource(String resource) {
        synchronized (Config.class) {
            Properties properties = load(resource);
            resources.put(resource, getMapProperty(properties));
            properties.put(resource, properties);
        }
    }

    private static Properties load(String resource) {

        Properties propertiesObj = null;

        synchronized (Config.class) {
            InputStream inputStream = Config.class.getClass().getResourceAsStream(resource);

            try {
                propertiesObj = new Properties();
                propertiesObj.load(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return propertiesObj;
    }

    public static Properties getProperty(String resource) {
        return load(resource);
    }

    public static Object getPropertyByResource(String resource, String key) {
        Map<String, String> map = resources.get(resource);
        if (map == null) {
            return null;
        }
        return map.get(key);
    }

    private static Map<String, String> getMapProperty(Properties property) {

        Map<String, String> map = new HashMap<String, String>();
        Enumeration<String> enums = (Enumeration<String>) property.propertyNames();

        while (enums.hasMoreElements()) {
            String key = enums.nextElement();
            String value = property.getProperty(key);
            map.put(key, value);
        }
        return map;

    }

    public static Map<String, Map<String, String>> getResources() {
        return resources;
    }


    public static List<String> getPersistenceModelList() {
        return getPersistenceData("<class>(\\w|\\.)+<");
    }

    public static List<String> getPersistenceUnitnameList() {
        List<String> unitnames = new ArrayList<String>();
        List<String> data = getPersistenceData("(persistence-unit)[^>]{1,}>");
        if (data != null) {
            int length = data.size();
            for (int i = 0; i < length; i++) {
                String tag = data.get(i);
                if (tag != null) {
                    String[] split = tag.split(" ");
                    for (int j = 0; j < split.length; j++) {
                        if (split[j].indexOf("name=") > -1) {
                            unitnames.add(split[j].substring(6, split[j].length()-1));
                        }
                    }
                }
            }
        }

        return unitnames;
    }

    public static String convertToString(InputStream inputStream){

        StringBuilder stringBuilder = new StringBuilder();
        String line = null;

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stringBuilder.toString();
    }


    public static List<String> getPersistenceData(String regular) {


        String persistence = convertToString(Config.class.getResourceAsStream("/META-INF/persistence.xml")); //FileUtility.readTextFile(path);

        persistence = persistence.replaceAll("(?s)<!--.*?-->", "");

        List<String> allMatches = new ArrayList<String>();

        Pattern pattern = Pattern.compile(regular, Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(persistence);

        while (matcher.find()) {
            String model = matcher.group();
            model = model.substring(7, model.length() - 1);
            allMatches.add(model);
        }


        return allMatches;

    }
}
