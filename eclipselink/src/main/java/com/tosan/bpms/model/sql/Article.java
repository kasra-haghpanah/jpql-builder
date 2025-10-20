package com.tosan.bpms.model.sql;


import com.jpql.api.interfaces.Model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


/**
 * Created by kasra.haghpanah on 27/11/2016.
 */
@Entity
@Table(name = "Article")
@NamedQueries(
        {
                //@NamedQuery(name = "getAllUser", query = "SELECT u From com.tosan.loan.model.Reception u"),
                //@NamedQuery(name = "getByIdUser", query = "SELECT u From com.tosan.loan.model.Reception u WHERE u.id =:id"),
                // @NamedQuery(name = "getByUserNameUser", query = "SELECT u From com.tosan.loan.model.Reception u WHERE u.username =:username")
        }
)
public class Article implements Model {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Integer articleId;

    //  شناسه نوع صنعت
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "industryId",// (user_username) for this table
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "FK_ARTICLE_INDUSTURY")
    )
    Industry industry;

    //  شناسه زمینه فعالیت
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "economicId",// (user_username) for this table
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "FK_ARTICLE_ECONOMIC")
    )
    Economic economic;


    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "measurementId",// (user_username) for this table
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "FK_ARTICLE_MEASURMENT")
    )
    Measurment measurement;


//    @ManyToOne(
//            mappedBy = "article" ,
//            cascade = CascadeType.ALL ,
//            fetch = FetchType.LAZY
//    )
//    List<ProductsOfProject> productsOfProjects;


    @Column(name = "faTitle")
    String faTitle;

    @Column(name = "enTitle")
    String enTitle;

    //  کد تعرفه گمرکی
    @Column(name = "customsTariffCode")
    String customsTariffCode;

    @Column(name = "iSICCode" , unique = true)
    String iSICCode;


    //@Version
    //@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "VERSION")
    private int version;


    public Article() {
    }


    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public String getFaTitle() {
        return faTitle;
    }

    public void setFaTitle(String faTitle) {
        this.faTitle = faTitle;
    }

    public String getEnTitle() {
        return enTitle;
    }

    public void setEnTitle(String enTitle) {
        this.enTitle = enTitle;
    }

    public String getCustomsTariffCode() {
        return customsTariffCode;
    }

    public void setCustomsTariffCode(String customsTariffCode) {
        this.customsTariffCode = customsTariffCode;
    }

    public String getISICCode() {
        return iSICCode;
    }

    public void setISICCode(String iSICCode) {
        this.iSICCode = iSICCode;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public Industry getIndustry() {
        return industry;
    }

    public void setIndustry(Industry industry) {
        this.industry = industry;
    }

    public Economic getEconomic() {
        return economic;
    }

    public void setEconomic(Economic economic) {
        this.economic = economic;
    }

    public Measurment getMeasurement() {
        return measurement;
    }

    public void setMeasurement(Measurment measurement) {
        this.measurement = measurement;
    }

//    public List<ProductsOfProject> getProductsOfProjects() {
//        return productsOfProjects;
//    }
//
//    public void setProductsOfProjects(List<ProductsOfProject> productsOfProjects) {
//        this.productsOfProjects = productsOfProjects;
//    }
}
