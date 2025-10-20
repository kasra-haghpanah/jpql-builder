package com.tosan.bpms.model.sql;

import com.jpql.api.annotations.Like;
import com.jpql.api.interfaces.Model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by kasra.haghpanah on 10/22/2018.
 */
@Embeddable
public class OngoingPrjctKey implements Model {

    //   کد پذیرش
    @Column(name = "receptionNo")
    @Like
    String receptionNo;

    //  تاریخ کنترل پروژه
    @Temporal(TemporalType.DATE)
    @Column(name = "cntrlPrjctDate")
    @Like
    Date cntrlPrjctDate;

    //  تاریخ پرداخت
    @Temporal(TemporalType.DATE)
    @Column(name = "lastPaymentDate", nullable = false)
    Date lastPaymentDate;


    //سریال رکورد زیر سرفصل
    @Column(name = "investId")
    Integer subInvestSerial;

    public String getReceptionNo() {
        return receptionNo;
    }

    public void setReceptionNo(String receptionNo) {
        this.receptionNo = receptionNo;
    }

    public Date getCntrlPrjctDate() {
        return cntrlPrjctDate;
    }

    public void setCntrlPrjctDate(Date cntrlPrjctDate) {
        this.cntrlPrjctDate = cntrlPrjctDate;
    }

    public Date getLastPaymentDate() {
        return lastPaymentDate;
    }

    public void setLastPaymentDate(Date lastPaymentDate) {
        this.lastPaymentDate = lastPaymentDate;
    }

    public Integer getSubInvestSerial() {
        return subInvestSerial;
    }

    public void setSubInvestSerial(Integer subInvestSerial) {
        this.subInvestSerial = subInvestSerial;
    }
}
