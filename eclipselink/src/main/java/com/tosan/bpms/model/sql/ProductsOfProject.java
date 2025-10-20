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
@Table(name = "ProductsOfProject")
@NamedQueries(
        {
                //@NamedQuery(name = "getAllUser", query = "SELECT u From com.tosan.loan.model.Reception u"),
                //@NamedQuery(name = "getByIdUser", query = "SELECT u From com.tosan.loan.model.Reception u WHERE u.id =:id"),
                // @NamedQuery(name = "getByUserNameUser", query = "SELECT u From com.tosan.loan.model.Reception u WHERE u.username =:username")
        }
)
public class ProductsOfProject implements Model {

    @Id
    @Column(name = "productsOfProjectId", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Integer productsOfProjectId;


    //product   ==    article
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "articleId",// (user_username) for this table
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "FK_Article_PrductOfPrj")
    )
    Article article;


    //  مجوز/مجوزها
    // authorityNo  == portfolioLicense
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "portfolioLicenseId",// (user_username) for this table
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "FK_prfLicense_PrductOfPrj")
    )
    PortfolioLicense portfolioLicense;

    @Embedded
    Address address;


//    @ManyToOne(
//            mappedBy = "productsOfProject" ,
//            cascade = CascadeType.ALL ,
//            fetch = FetchType.LAZY
//    )
//    List<MaterialOfProject> materialOfProjects;

    //  ظرفيت اسمي (سالانه)
    @Column(name = "nominalCapacity" , columnDefinition = "FLOAT(25)")
    Double nominalCapacity = 0d;

    //  ظرفيت عملي (سالانه)
    @Column(name = "practicalCapacity" , columnDefinition = "FLOAT(25)")
    Double practicalCapacity = 0d;

    // درصد استفاده از ظرفيت عملي
    @Column(name = "pracCapacityPrcntg" , columnDefinition = "FLOAT(25)")
    Double pracCapacityPrcntg = 0d;

    //  نحوه فروش
    @Column(name = "typeOfSelling")
    Integer typeOfSelling;//////////////////////////////////

    @Column(name = "marketplace")
    Integer marketplace;//////////////////////////////////

    @Column(name = "mainProduct")
    Boolean mainProduct = false;//////////////////////////////////

    //   قيمت فروش داخلي هر واحد محصول
    @Column(name = "intSellingPr")
    BigDecimal intSellingPr = new BigDecimal(0);

    //  قيمت فروش خارجي (صادرات) هر واحد محصول
    @Column(name = "exSellingPr")
    BigDecimal exSellingPr = new BigDecimal(0);

    //  توضیحات
    @Column(name = "description")
    String description;
    //  تاریخ ارزشگذاری
    @Temporal(TemporalType.DATE)
    @Column(name = "evaluationDate")
    Date evaluationDate;
    // احتساب در محاسبه سرمایه در گردش
    @Column(name = "calSelection")
    Boolean calSelection;

    //@Version
    @Column(name = "VERSION")
    private int version;

    public ProductsOfProject() {

    }

    public Integer getProductsOfProjectId() {
        return productsOfProjectId;
    }

    public void setProductsOfProjectId(Integer productsOfProjectId) {
        this.productsOfProjectId = productsOfProjectId;
    }

    public PortfolioLicense getPortfolioLicense() {
        return portfolioLicense;
    }

    public void setPortfolioLicense(PortfolioLicense portfolioLicense) {
        this.portfolioLicense = portfolioLicense;
    }

    public Double getNominalCapacity() {
        return nominalCapacity;
    }

    public void setNominalCapacity(Double nominalCapacity) {
        this.nominalCapacity = nominalCapacity;
    }

    public Double getPracticalCapacity() {
        return practicalCapacity;
    }

    public void setPracticalCapacity(Double practicalCapacity) {
        this.practicalCapacity = practicalCapacity;
    }

    public Double getPracCapacityPrcntg() {
        return pracCapacityPrcntg;
    }

    public void setPracCapacityPrcntg(Double pracCapacityPrcntg) {
        this.pracCapacityPrcntg = pracCapacityPrcntg;
    }

    public Integer getTypeOfSelling() {
        return typeOfSelling;
    }

    public void setTypeOfSelling(Integer typeOfSelling) {
        this.typeOfSelling = typeOfSelling;
    }

    public Integer getMarketplace() {
        return marketplace;
    }

    public void setMarketplace(Integer marketplace) {
        this.marketplace = marketplace;
    }

    public Boolean getMainProduct() {
        return mainProduct;
    }

    public void setMainProduct(Boolean mainProduct) {
        this.mainProduct = mainProduct;
    }


    public BigDecimal getIntSellingPr() {
        return intSellingPr;
    }

    public void setIntSellingPr(BigDecimal intSellingPr) {
        this.intSellingPr = intSellingPr;
    }

    public BigDecimal getExSellingPr() {
        return exSellingPr;
    }

    public void setExSellingPr(BigDecimal exSellingPr) {
        this.exSellingPr = exSellingPr;
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

    public Boolean getCalSelection() {
        return calSelection;
    }

    public void setCalSelection(Boolean calSelection) {
        this.calSelection = calSelection;
    }


    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    //    public List<MaterialOfProject> getMaterialOfProjects() {
//        return materialOfProjects;
//    }
//
//    public void setMaterialOfProjects(List<MaterialOfProject> materialOfProjects) {
//        this.materialOfProjects = materialOfProjects;
//    }
}
