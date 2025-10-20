package com.tosan.bpms.bean.provider;

/**
 * Created by kasra.haghpanah on 24/06/2018.
 */
public class Gum {

    int id;
    String name;

    public Gum() {
    }

    public Gum(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "{\"Gum\":{"
                + "\"id\":\"" + id + "\""
                + ",\"name\":\"" + name + "\""
                + "}}";
    }
}
