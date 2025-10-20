package com.tosan.bpms.model.sql;

import com.jpql.api.interfaces.Model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by kasra.haghpanah on 05/02/2017.
 */
@Entity
@Table(name = "OngoingPrjct")
@NamedQueries(
        {
                //@NamedQuery(name = "getAllUser", query = "SELECT u From com.tosan.loan.model.Reception u"),
                //@NamedQuery(name = "getByIdUser", query = "SELECT u From com.tosan.loan.model.Reception u WHERE u.id =:id"),
                // @NamedQuery(name = "getByUserNameUser", query = "SELECT u From com.tosan.loan.model.Reception u WHERE u.username =:username")
        }
)
public class OngoingPrjct implements Model {

    //@EmbeddedId

    @EmbeddedId
    @Column(name = "id")
    OngoingPrjctKey ongoingPrjctId;


    // اقلام سرمايه گذاري
    @Column(name = "investType")
    Integer investType;

    // ارز
    @Column(name = "currency")
    String currency;

    // انجام شده (مبلغ)
    @Column(name = "priceDone")
    BigDecimal priceDone;


    // هزينه هاي انجام شده تا كنونCTD
    @Column(name = "ctd")
    BigDecimal ctd;

    // مورد نياز(مبلغ)
    @Column(name = "priceNeeded")
    BigDecimal priceNeeded;

    //  هزينه هاي مورد نياز تا كنون CTC
    @Column(name = "ctc")
    BigDecimal ctc;

    // موجود
    @Column(name = "exists1")
    BigDecimal exists;


    //  مجموع هزينه هاي تكميل CAC
    @Column(name = "cac")
    BigDecimal cac;

    // مجموع مصوب هزينه ها BC
    @Column(name = "totalBC")
    BigDecimal totalBC;

    // انحراف هزينه
    @Column(name = "costVariation")
    BigDecimal costVariation;

    //   زمان شروع مصوب
    @Temporal(TemporalType.DATE)
    @Column(name = "bgnDate")
    Date bgnDate;

    //   زمان پايان مصوب
    @Temporal(TemporalType.DATE)
    @Column(name = "endDate")
    Date endDate;

    //    زمان شروع واقعي
    @Temporal(TemporalType.DATE)
    @Column(name = "rlStrtDate")
    Date rlStrtDate;

    //  زمان پايان واقعي
    @Temporal(TemporalType.DATE)
    @Column(name = "rlEndDate")
    Date rlEndDate;

    //  انحراف زماني
    @Column(name = "variation" , columnDefinition = "FLOAT(25)")
    Double variation;

    //    پيش بيني هزينه دوره آتي
    @Column(name = "fcstPredict")
    BigDecimal fcstPredict;

    //   پيشرفت زماني
    @Column(name = "ongoingDate" , columnDefinition = "FLOAT(25)")
    Double ongoingDate;

    //   پيشرفت هزينه اي
    @Column(name = "ongoingCst" , columnDefinition = "FLOAT(25)")
    Double ongoingCst;

    //   پيشرفت فيزيكي
    @Column(name = "ongoingPrjct" , columnDefinition = "FLOAT(25)")
    Double ongoingPrjct;

    //   پيشنهاد پرداخت
    @Column(name = "paymntPredict")
    BigDecimal paymntPredict;


    //   وضعیت پرداخت
    @Column(name = "paymentAmount")
    BigDecimal paymentAmount;

    //  انجام شده طی دوره
    @Column(name = "doneInPeriod")
    BigDecimal doneInPeriod;


    //   زیر سرفصل سرمایه گذاری
    @Column(name = "subInvestType")
    String subInvestType;

    // مانده پرداخت نشده
    @Column(name = "remaindAmount")
    BigDecimal remaindAmount; //= new BigDecimal(0);

    //توضیحات
    @Column(name = "desc1")
    String desc;


    //@Version
    @Column(name = "VERSION")
    private int version;


    public OngoingPrjct() {
    }

    public Integer getInvestType() {
        return investType;
    }

    public void setInvestType(Integer investType) {
        this.investType = investType;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getPriceDone() {
        return priceDone;
    }

    public void setPriceDone(BigDecimal priceDone) {
        this.priceDone = priceDone;
    }

    public BigDecimal getCtd() {
        return ctd;
    }

    public void setCtd(BigDecimal ctd) {
        this.ctd = ctd;
    }

    public BigDecimal getPriceNeeded() {
        return priceNeeded;
    }

    public void setPriceNeeded(BigDecimal priceNeeded) {
        this.priceNeeded = priceNeeded;
    }

    public BigDecimal getCtc() {
        return ctc;
    }

    public void setCtc(BigDecimal ctc) {
        this.ctc = ctc;
    }

    public BigDecimal getExists() {
        return exists;
    }

    public void setExists(BigDecimal exists) {
        this.exists = exists;
    }

    public BigDecimal getCac() {
        return cac;
    }

    public void setCac(BigDecimal cac) {
        this.cac = cac;
    }

    public BigDecimal getTotalBC() {
        return totalBC;
    }

    public void setTotalBC(BigDecimal totalBC) {
        this.totalBC = totalBC;
    }

    public BigDecimal getCostVariation() {
        return costVariation;
    }

    public void setCostVariation(BigDecimal costVariation) {
        this.costVariation = costVariation;
    }

    public Date getBgnDate() {
        return bgnDate;
    }

    public void setBgnDate(Date bgnDate) {
        this.bgnDate = bgnDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getRlStrtDate() {
        return rlStrtDate;
    }

    public void setRlStrtDate(Date rlStrtDate) {
        this.rlStrtDate = rlStrtDate;
    }

    public Date getRlEndDate() {
        return rlEndDate;
    }

    public void setRlEndDate(Date rlEndDate) {
        this.rlEndDate = rlEndDate;
    }

    public Double getVariation() {
        return variation;
    }

    public void setVariation(Double variation) {
        this.variation = variation;
    }

    public BigDecimal getFcstPredict() {
        return fcstPredict;
    }

    public void setFcstPredict(BigDecimal fcstPredict) {
        this.fcstPredict = fcstPredict;
    }

    public Double getOngoingDate() {
        return ongoingDate;
    }

    public void setOngoingDate(Double ongoingDate) {
        this.ongoingDate = ongoingDate;
    }

    public Double getOngoingCst() {
        return ongoingCst;
    }

    public void setOngoingCst(Double ongoingCst) {
        this.ongoingCst = ongoingCst;
    }

    public Double getOngoingPrjct() {
        return ongoingPrjct;
    }

    public void setOngoingPrjct(Double ongoingPrjct) {
        this.ongoingPrjct = ongoingPrjct;
    }

    public BigDecimal getPaymntPredict() {
        return paymntPredict;
    }

    public void setPaymntPredict(BigDecimal paymntPredict) {
        this.paymntPredict = paymntPredict;
    }



    public BigDecimal getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(BigDecimal paymentAmount) {
        this.paymentAmount = paymentAmount;
    }



    public BigDecimal getDoneInPeriod() {
        return doneInPeriod;
    }

    public void setDoneInPeriod(BigDecimal doneInPeriod) {
        this.doneInPeriod = doneInPeriod;
    }

    public String getSubInvestType() {
        return subInvestType;
    }

    public void setSubInvestType(String subInvestType) {
        this.subInvestType = subInvestType;
    }

    public BigDecimal getRemaindAmount() {
        return remaindAmount;
    }

    public void setRemaindAmount(BigDecimal remaindAmount) {
        this.remaindAmount = remaindAmount;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public OngoingPrjctKey getOngoingPrjctId() {
        return ongoingPrjctId;
    }

    public void setOngoingPrjctId(OngoingPrjctKey ongoingPrjctId) {
        this.ongoingPrjctId = ongoingPrjctId;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
