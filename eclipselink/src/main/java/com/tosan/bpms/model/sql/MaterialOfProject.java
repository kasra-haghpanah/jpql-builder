package com.tosan.bpms.model.sql;

import com.jpql.api.interfaces.Model;

import javax.validation.constraints.NotNull;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by kasra.haghpanah on 26/10/2016.
 */
@Entity
@Table(name = "MaterialOfProject")
@NamedQueries(
        {
                //@NamedQuery(name = "getAllUser", query = "SELECT u From com.tosan.loan.model.Reception u"),
                //@NamedQuery(name = "getByIdUser", query = "SELECT u From com.tosan.loan.model.Reception u WHERE u.id =:id"),
                // @NamedQuery(name = "getByUserNameUser", query = "SELECT u From com.tosan.loan.model.Reception u WHERE u.username =:username")
        }
)
public class MaterialOfProject implements Model {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Integer materialOfProjectId;


    // شناسه محصول
    // productsOfProjectId
    //Integer name;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "productsOfProjectId",// (user_username) for this table
            referencedColumnName = "productsOfProjectId",
            foreignKey = @ForeignKey(name = "FK_ProducPrj_mtrPrj")
    )
    private ProductsOfProject productsOfProject;

    //  عنوان ماده اوليه
    @Column(name = "materialName")
    String materialName;



    //  واحد اندازه گيري
    @Column(name = "evaluationUnit")
    String evaluationUnit;

    //  ميزان استفاده در هر واحد محصول

    @Column(name = "quantityOfUsage" , columnDefinition = "FLOAT(25)")
    Double quantityOfUsage = 0d;

    //نحوه تامين
    @Column(name = "providingType")
    Integer providingType;

    //  قيمت داخلي خريد هر واحد
    @Column(name = "intPurchasePr")
    BigDecimal intPurchasePr = new BigDecimal(0);

    //  قيمت خارجي (صادرات) هر واحد
    @Column(name = "exPurchasePr")
    BigDecimal exPurchasePr = new BigDecimal(0);

    //  توضیحات
    @Column(name = "description")
    String description;

    //  تاریخ ارزشگذاری
    @Temporal(TemporalType.DATE)
    @Column(name = "evaluationDate")
    Date evaluationDate;


    //@Version
    @Column(name = "VERSION")
    private int version;

    public MaterialOfProject() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getEvaluationDate() {
        return evaluationDate;
    }

    public void setEvaluationDate(Date evaluationDate) {
        this.evaluationDate = evaluationDate;
    }

    public Integer getMaterialOfProjectId() {
        return materialOfProjectId;
    }

    public void setMaterialOfProjectId(Integer materialOfProjectId) {
        this.materialOfProjectId = materialOfProjectId;
    }

    public ProductsOfProject getProductsOfProject() {
        return productsOfProject;
    }

    public void setProductsOfProject(ProductsOfProject productsOfProject) {
        this.productsOfProject = productsOfProject;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getEvaluationUnit() {
        return evaluationUnit;
    }

    public void setEvaluationUnit(String evaluationUnit) {
        this.evaluationUnit = evaluationUnit;
    }

    public Double getQuantityOfUsage() {
        return quantityOfUsage;
    }

    public void setQuantityOfUsage(Double quantityOfUsage) {
        this.quantityOfUsage = quantityOfUsage;
    }

    public Integer getProvidingType() {
        return providingType;
    }

    public void setProvidingType(Integer providingType) {
        this.providingType = providingType;
    }

    public BigDecimal getIntPurchasePr() {
        return intPurchasePr;
    }

    public void setIntPurchasePr(BigDecimal intPurchasePr) {
        this.intPurchasePr = intPurchasePr;
    }

    public BigDecimal getExPurchasePr() {
        return exPurchasePr;
    }

    public void setExPurchasePr(BigDecimal exPurchasePr) {
        this.exPurchasePr = exPurchasePr;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }


}
