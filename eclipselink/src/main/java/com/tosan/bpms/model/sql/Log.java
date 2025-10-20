package com.tosan.bpms.model.sql;



import com.jpql.api.interfaces.Model;

import javax.persistence.*;

import java.util.Date;

/**
 * Created by kasra.haghpanah on 16/09/2016.
 */
@Cacheable(false)
@Entity
@Table(name = "log")
@NamedQueries(
        {
                @NamedQuery(name = "getAllLog", query = "SELECT l From com.tosan.bpms.model.sql.Log l")
        }
)
public class Log implements Model {

    public Log() {
    }


    public final static String GET_ALL = "getAllLog";


    @Id
    @Column(name = "id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "class")
    private String className;

    @Column(name = "method")
    private String method;


    @Temporal(TemporalType.DATE)
    @Column(name = "date")
    private Date date;

    @Column(name = "exception" , length = 60000)
    private String exception;

    //@Version
    @Column(name = "version")
    private int version;


    private boolean bool;

    private Boolean boolCapital;

    //private char achar;

    //private Character character;


    //private char achar;

    //private Character character;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public boolean isBool() {
        return bool;
    }

    public void setBool(boolean bool) {
        this.bool = bool;
    }

    public Boolean getBoolCapital() {
        return boolCapital;
    }

    public void setBoolCapital(Boolean boolCapital) {
        this.boolCapital = boolCapital;
    }
/*
    public char getAchar() {
        return achar;
    }

    public void setAchar(char achar) {
        this.achar = achar;
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }
*/
    @Override
    public String toString() {
        return "{\"Log\":{"
                + "\"id\":\"" + id + "\""
                + ",\"className\":\"" + className + "\""
                + ",\"method\":\"" + method + "\""
                + ",\"date\":" + date
                + ",\"exception\":\"" + exception + "\""
                + ",\"version\":\"" + version + "\""
                + ",\"bool\":\"" + bool + "\""
                + ",\"boolCapital\":\"" + boolCapital + "\""
               // + ",\"achar\":\"" + achar + "\""
              //  + ",\"character\":" + character
                + "}}";
    }
}
