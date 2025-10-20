package com.tosan.bpms.model.sql;


import com.jpql.api.interfaces.Model;

import javax.persistence.*;

/**
 * Created by kasra.haghpanah on 19/11/2016.
 */
@Entity
@Table(name = "Measurment")
@NamedQueries(
        {
                //@NamedQuery(name = "getAllUser", query = "SELECT u From com.tosan.loan.model.Reception u"),
                //@NamedQuery(name = "getByIdUser", query = "SELECT u From com.tosan.loan.model.Reception u WHERE u.id =:id"),
                // @NamedQuery(name = "getByUserNameUser", query = "SELECT u From com.tosan.loan.model.Reception u WHERE u.username =:username")
        }
)
public class Measurment implements Model {


    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(generator = "system-uuid")
    //@GenericGenerator(name = "uuid", strategy = "uuid2")
    String measurementId;


//    @ManyToOne(
//            mappedBy = "measurement" ,
//            cascade = CascadeType.ALL ,
//            fetch = FetchType.LAZY
//    )
//    List<Article> articles;

    //نام واحد اندازه گیری
    @Column(name = "name")
    String name;

    //@Version
    @Column(name = "VERSION")
    private int version;

    public Measurment() {
    }



    public String getMeasurementId() {
        return measurementId;
    }

    public void setMeasurementId(String measurementId) {
        this.measurementId = measurementId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

//    public List<Article> getArticles() {
//        return articles;
//    }
//
//    public void setArticles(List<Article> articles) {
//        this.articles = articles;
//    }
}

