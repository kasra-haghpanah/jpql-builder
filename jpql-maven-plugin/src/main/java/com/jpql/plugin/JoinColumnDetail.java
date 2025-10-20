package com.jpql.plugin;

import java.util.*;

/**
 * Created by k.haghpanah on 7/17/2019.
 */
public class JoinColumnDetail implements Cloneable {

    final Map<String, String> names = new HashMap<String, String>();

    public Map<String, String> getNames() {
        return names;
    }

    public void put(String name, String referencedColumnName) {
        this.names.put(name, referencedColumnName);
    }

    public String get(String name) {
        return this.names.get(name);
    }

    public JoinColumnDetail clone() {

        JoinColumnDetail joinColumnDetail = new JoinColumnDetail();
        Set set = this.names.keySet();
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            String key = (String) iterator.next();
            joinColumnDetail.put(key, this.names.get(key));
        }

        return joinColumnDetail;
    }


}
