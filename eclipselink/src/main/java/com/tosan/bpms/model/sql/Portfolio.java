package com.tosan.bpms.model.sql;



import com.jpql.api.interfaces.Model;

import javax.persistence.*;

import java.util.Date;
import java.util.List;

/**
 * Created by kasra.haghpanah on 24/08/2016.
 */

@Entity
@Table(name = "Portfolio")
@NamedQueries(
        {
                //@NamedQuery(name = "getAllUser", query = "SELECT u From com.tosan.loan.model.Reception u"),
                //@NamedQuery(name = "getByIdUser", query = "SELECT u From com.tosan.loan.model.Reception u WHERE u.id =:id"),
                // @NamedQuery(name = "getByUserNameUser", query = "SELECT u From com.tosan.loan.model.Reception u WHERE u.username =:username")
        }
)
public class Portfolio implements Model {

    @Id
    @Column(name = "portfolioId", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Integer portfolioId;


    @OneToMany(
            mappedBy = "portfolio" ,
            cascade = CascadeType.ALL ,
            fetch = FetchType.LAZY
    )
    List<PortfolioLicense> portfolioLicenses;


    @OneToMany(
            mappedBy = "portfolio" ,
            cascade = CascadeType.ALL ,
            fetch = FetchType.LAZY
    )
    List<PortfolioLocation> portfolioLocations;


    @OneToMany(
            mappedBy = "portfolio" ,
            cascade = CascadeType.ALL ,
            fetch = FetchType.LAZY
    )
    List<Reception> receptions;

    @Embedded
    Address address;

    @Column(name = "customerId")
    String customerId;

    @Column(name = "title")
    String title;

    @Column(name = "branchCode")
    String branchCode;


    // شناسه زمینه فعالیت
    @Column(name = "economicType")
    String economicType;


    // شناسه نوع صنعت
    @Column(name = "industryType")
    String industryType;


    @Column(name = "techDescription")
    String technologyDescription;

    @Column(name = "stateType")
    Integer stateType;

    @Column(name = "lifeType")
    Integer lifeType;

    @Temporal(TemporalType.DATE)
    @Column(name = "startDate")
    Date startDate;


    @Temporal(TemporalType.DATE)
    @Column(name = "endDate")
    Date endDate;


    @Temporal(TemporalType.DATE)
    @Column(name = "requestDate")
    Date requestDate;


    @Column(name = "capitalAMT")
    String capitalAMT;


    @Column(name = "investableAMT")
    String investableAMT;


    @Column(name = "assessdAMT")
    String assessdAMT;


    @Column(name = "bankAPPRInvestAMT")
    String bankAPPRInvestAMT;


    @Column(name = "reagent")
    String reagent;


    // شناسه کسب و کار
    @Column(name = "businessId")
    String businessId;


    //@Version
    @Column(name = "VERSION")
    private int version;
    //String sort = "portfolioId";


    public Portfolio() {

    }


    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public Integer getPortfolioId() {
        return portfolioId;
    }

    public void setPortfolioId(Integer portfolioId) {
        this.portfolioId = portfolioId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getEconomicType() {
        return economicType;
    }

    public void setEconomicType(String economicType) {
        this.economicType = economicType;
    }

    public String getIndustryType() {
        return industryType;
    }

    public void setIndustryType(String industryType) {
        this.industryType = industryType;
    }

    public String getTechnologyDescription() {
        return technologyDescription;
    }

    public void setTechnologyDescription(String technologyDescription) {
        this.technologyDescription = technologyDescription;
    }

    public Integer getStateType() {
        return stateType;
    }

    public void setStateType(Integer stateType) {
        this.stateType = stateType;
    }

    public Integer getLifeType() {
        return lifeType;
    }

    public void setLifeType(Integer lifeType) {
        this.lifeType = lifeType;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public String getCapitalAMT() {
        return capitalAMT;
    }

    public void setCapitalAMT(String capitalAMT) {
        this.capitalAMT = capitalAMT;
    }

    public String getInvestableAMT() {
        return investableAMT;
    }

    public void setInvestableAMT(String investableAMT) {
        this.capitalAMT = investableAMT;
    }

    public String getAssessdAMT() {
        return assessdAMT;
    }

    public void setAssessdAMT(String assessdAMT) {
        this.assessdAMT = assessdAMT;
    }

    public String getBankAPPRInvestAMT() {
        return bankAPPRInvestAMT;
    }

    public void setBankAPPRInvestAMT(String bankAPPRInvestAMT) {
        this.bankAPPRInvestAMT = bankAPPRInvestAMT;
    }

    public String getReagent() {
        return reagent;
    }

    public void setReagent(String reagent) {
        this.reagent = reagent;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }


    public List<PortfolioLicense> getPortfolioLicenses() {
        return portfolioLicenses;
    }

    public void setPortfolioLicenses(List<PortfolioLicense> portfolioLicenses) {
        this.portfolioLicenses = portfolioLicenses;
    }

    public List<PortfolioLocation> getPortfolioLocations() {
        return portfolioLocations;
    }

    public void setPortfolioLocations(List<PortfolioLocation> portfolioLocations) {
        this.portfolioLocations = portfolioLocations;
    }

    public List<Reception> getReceptions() {
        return receptions;
    }

    public void setReceptions(List<Reception> receptions) {
        this.receptions = receptions;
    }


    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
