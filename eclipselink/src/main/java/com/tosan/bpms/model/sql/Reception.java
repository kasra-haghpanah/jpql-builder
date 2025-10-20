package com.tosan.bpms.model.sql;


import com.jpql.api.annotations.LeftJoin;
import com.jpql.api.annotations.Like;
import com.jpql.api.interfaces.Model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by kasra.haghpanah on 14/10/2018.
 */
@Entity
@Table(name = "Reception")
@NamedQueries(
        {
                //@NamedQuery(name = "getAllUser", query = "SELECT u From com.tosan.loan.model.Reception u"),
                //@NamedQuery(name = "getByIdUser", query = "SELECT u From com.tosan.loan.model.Reception u WHERE u.id =:id"),
                // @NamedQuery(name = "getByUserNameUser", query = "SELECT u From com.tosan.loan.model.Reception u WHERE u.username =:username")
        }
)
public class Reception implements Model {

//    @OneORMany(mappedBy = "reception")
//    private Evaluation evaluation;


    @Id
    @Column(name = "receptionNo", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long receptionNo;


    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "portfolioId",// (user_username) for this table
            referencedColumnName = "portfolioId",
            foreignKey = @ForeignKey(name = "FK_Portfolio_Reception")
    )
    private Portfolio portfolio;


    @Column(name = "channel")
    private Integer channel;

    @Column(name = "externalResource")
    private Boolean externalResource = false;

    @Column(name = "receptionTitle")
    @Like(status = Like.STATUS.AFTER)
    private String receptionTitle;


    @Column(name = "productType")
    private Integer productType;

    @Column(name = "longLoanType")
    private Integer longLoanType;
    //    هدف متقاضي از اخذ تسهيلات

    @Column(name = "financingPurpose")
    private Integer financingPurpose;

    @Column(name = "low141")
    private Boolean low141;

    @Column(name = "shortTermLoanType")
    private Integer shortTermLoanType;

    @Temporal(TemporalType.DATE)
    @Column(name = "date1")
    private Date date;

    @Like
    @Temporal(TemporalType.DATE)
    @Column(name = "rejLetterDate")
    private Date rejLetterDate;

    @Column(name = "customerCode")
    @Like(status = Like.STATUS.BEFORE)
    private String customerCode;

    @Column(name = "branch")
    @Like
    private String branch;

    @Column(name = "receptionState")
    private Integer receptionState;

    @Column(name = "representativeRefType")
    private Integer representativeRefType;


    //    حسن شهرت متقاضی
    @Column(name = "applicantCreditType")
    private Integer applicantCreditType;


    //    نحوه ايفاي تعهدات قبلي
    @Column(name = "underTakeHisType")
    private Integer underTakeHisType;


    //    سابقه چك برگشتي متقاضي
    @Column(name = "checkRejectType")
    private Integer checkRejectType;


    //    وضعيت تمركز نقدينگي متقاضي
    @Column(name = "cashFocusStatusType")
    private Integer cashFocusStatusType;


    //    نسبت مالکانه
    @Column(name = "equation")
    private BigDecimal equation = new BigDecimal(0);


    //    نرخ بازده داخلي (IRR)
    @Column(name = "irrEstimate")
    private BigDecimal irrEstimate = new BigDecimal(0);


    //    نقطه سربه سر
    @Column(name = "headPoint")
    private BigDecimal headPoint = new BigDecimal(0);


    //    كنترل آورده نقدي متقاضي
    @Column(name = "cashControl")
    private Boolean cashControl;


    //    توضیحات بازار صادرات
    @Column(name = "exportMarketDsc")
    private String exportMarketDsc;


    //    توضیحات بازار داخلی
    @Column(name = "internalMarketDsc")
    private String internalMarketDsc;


    //    توضیحات بازار موارد اولیه
    @Column(name = "rowMaterialDsc")
    private String rowMaterialDsc;

    @Column(name = "marketProductDesc")
    private String marketProductDesc;

    @Column(name = "productiveEmployee")
    private Integer productiveEmployee;

    @Column(name = "nonProductiveEmployee")
    private Integer nonProductiveEmployee;

    @Column(name = "forecastProductiveEmployee")
    private Integer forecastProductiveEmployee;

    @Column(name = "forecastNonProductiveEmployee")
    private Integer forecastNonProductiveEmployee;

    @Column(name = "cusPrePayAmnt")
    private BigDecimal cusPrePayAmnt = new BigDecimal(0);

    @Column(name = "financialUser")
    private String financialUser;

    @Column(name = "marketUser")
    private String marketUser;

    @Column(name = "technicalUser")
    private String technicalUser;

    @Column(name = "currentAssetReceptionNo")
    private String currentAssetReceptionNo;


    //@Version
    @Column(name = "VERSION")
    private int version;


    public Reception() {
    }


    public String getReceptionTitle() {
        return receptionTitle;
    }

    public void setReceptionTitle(String receptionTitle) {
        this.receptionTitle = receptionTitle;
    }

    public Integer getProductType() {
        return productType;
    }

    public void setProductType(Integer productType) {
        this.productType = productType;
    }

    public Integer getLongLoanType() {
        return longLoanType;
    }

    public void setLongLoanType(Integer longLoanType) {
        this.longLoanType = longLoanType;
    }

    public Integer getFinancingPurpose() {
        return financingPurpose;
    }

    public void setFinancingPurpose(Integer financingPurpose) {
        this.financingPurpose = financingPurpose;
    }

    public Boolean getLow141() {
        return low141;
    }

    public void setLow141(Boolean low141) {
        this.low141 = low141;
    }

    public Integer getShortTermLoanType() {
        return shortTermLoanType;
    }

    public void setShortTermLoanType(Integer shortTermLoanType) {
        this.shortTermLoanType = shortTermLoanType;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getRejLetterDate() {
        return rejLetterDate;
    }

    public void setRejLetterDate(Date rejLetterDate) {
        this.rejLetterDate = rejLetterDate;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public Long getReceptionNo() {
        return receptionNo;
    }

    public void setReceptionNo(Long receptionNo) {
        this.receptionNo = receptionNo;
    }

    public Integer getReceptionState() {
        return receptionState;
    }

    public void setReceptionState(Integer receptionState) {
        this.receptionState = receptionState;
    }

    public Integer getRepresentativeRefType() {
        return representativeRefType;
    }

    public void setRepresentativeRefType(Integer representativeRefType) {
        this.representativeRefType = representativeRefType;
    }

    public Integer getApplicantCreditType() {
        return applicantCreditType;
    }

    public void setApplicantCreditType(Integer applicantCreditType) {
        this.applicantCreditType = applicantCreditType;
    }

    public Integer getUnderTakeHisType() {
        return underTakeHisType;
    }

    public void setUnderTakeHisType(Integer underTakeHisType) {
        this.underTakeHisType = underTakeHisType;
    }

    public Integer getCheckRejectType() {
        return checkRejectType;
    }

    public void setCheckRejectType(Integer checkRejectType) {
        this.checkRejectType = checkRejectType;
    }

    public Integer getCashFocusStatusType() {
        return cashFocusStatusType;
    }

    public void setCashFocusStatusType(Integer cashFocusStatusType) {
        this.cashFocusStatusType = cashFocusStatusType;
    }

    public BigDecimal getEquation() {
        return equation;
    }

    public void setEquation(BigDecimal equation) {
        this.equation = equation;
    }

    public BigDecimal getIrrEstimate() {
        return irrEstimate;
    }

    public void setIrrEstimate(BigDecimal irrEstimate) {
        this.irrEstimate = irrEstimate;
    }

    public BigDecimal getHeadPoint() {
        return headPoint;
    }

    public void setHeadPoint(BigDecimal headPoint) {
        this.headPoint = headPoint;
    }

    public Boolean getCashControl() {
        return cashControl;
    }

    public void setCashControl(Boolean cashControl) {
        this.cashControl = cashControl;
    }

    public String getExportMarketDsc() {
        return exportMarketDsc;
    }

    public void setExportMarketDsc(String exportMarketDsc) {
        this.exportMarketDsc = exportMarketDsc;
    }

    public String getInternalMarketDsc() {
        return internalMarketDsc;
    }

    public void setInternalMarketDsc(String internalMarketDsc) {
        this.internalMarketDsc = internalMarketDsc;
    }

    public String getRowMaterialDsc() {
        return rowMaterialDsc;
    }

    public void setRowMaterialDsc(String rowMaterialDsc) {
        this.rowMaterialDsc = rowMaterialDsc;
    }

    public Portfolio getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(Portfolio portfolio) {
        this.portfolio = portfolio;
    }

    public String getMarketProductDesc() {
        return marketProductDesc;
    }

    public void setMarketProductDesc(String marketProductDesc) {
        this.marketProductDesc = marketProductDesc;
    }

    public Integer getProductiveEmployee() {
        return productiveEmployee;
    }

    public void setProductiveEmployee(Integer productiveEmployee) {
        this.productiveEmployee = productiveEmployee;
    }

    public Integer getNonProductiveEmployee() {
        return nonProductiveEmployee;
    }

    public void setNonProductiveEmployee(Integer nonProductiveEmployee) {
        this.nonProductiveEmployee = nonProductiveEmployee;
    }

    public Integer getForecastProductiveEmployee() {
        return forecastProductiveEmployee;
    }

    public void setForecastProductiveEmployee(Integer forecastProductiveEmployee) {
        this.forecastProductiveEmployee = forecastProductiveEmployee;
    }

    public Integer getForecastNonProductiveEmployee() {
        return forecastNonProductiveEmployee;
    }

    public void setForecastNonProductiveEmployee(Integer forecastNonProductiveEmployee) {
        this.forecastNonProductiveEmployee = forecastNonProductiveEmployee;
    }

    public BigDecimal getCusPrePayAmnt() {
        return cusPrePayAmnt;
    }

    public void setCusPrePayAmnt(BigDecimal cusPrePayAmnt) {
        this.cusPrePayAmnt = cusPrePayAmnt;
    }

    public String getFinancialUser() {
        return financialUser;
    }

    public void setFinancialUser(String financialUser) {
        this.financialUser = financialUser;
    }

    public String getMarketUser() {
        return marketUser;
    }

    public void setMarketUser(String marketUser) {
        this.marketUser = marketUser;
    }

    public String getTechnicalUser() {
        return technicalUser;
    }

    public void setTechnicalUser(String technicalUser) {
        this.technicalUser = technicalUser;
    }

    public String getCurrentAssetReceptionNo() {
        return currentAssetReceptionNo;
    }

    public void setCurrentAssetReceptionNo(String currentAssetReceptionNo) {
        this.currentAssetReceptionNo = currentAssetReceptionNo;
    }

    public Integer getChannel() {
        return channel;
    }

    public void setChannel(Integer channel) {
        this.channel = channel;
    }

    public Boolean getExternalResource() {
        return externalResource;
    }

    public void setExternalResource(Boolean externalResource) {
        this.externalResource = externalResource;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

/*    public Evaluation getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(Evaluation evaluation) {
        this.evaluation = evaluation;
    }*/
}
