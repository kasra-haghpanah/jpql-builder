//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.tosan.bpms.model.sql;

import com.jpql.api.interfaces.Model;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table(
        name = "PortfolioLicense"
)
@NamedQueries({})
public class PortfolioLicense implements Model {
    @Id
    @Column(
            name = "id",
            nullable = false,
            unique = true
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE
    )
    Integer portfolioLicenseId;
    @NotNull
    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "portfolioId",
            referencedColumnName = "portfolioId",
            foreignKey = @ForeignKey(
                    name = "FK_Portfolio_License"
            )
    )
    private Portfolio portfolio;
    @OneToOne(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL}
    )
    @JoinColumn(
            name = "fileMetaDataId",
            referencedColumnName = "l4610flmtdataid",
            foreignKey = @ForeignKey(
                    name = "FK_FILE_License"
            )
    )
    private File file;
    @OneToMany(
            mappedBy = "portfolioLicense",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY
    )
    List<LicenseDetails> licenseDetails;
    @Column(
            name = "issueVerifierType"
    )
    Integer issueVerifierType;
    @Column(
            name = "licenseNumber"
    )
    String licenseNumber ;
    @Column(
            name = "licenseType"
    )
    Integer licenseType = Integer.valueOf(0);
    @Temporal(TemporalType.DATE)
    @Column(
            name = "issueDate"
    )
    Date issueDate;
    @Temporal(TemporalType.DATE)
    @Column(
            name = "endDate"
    )
    Date endDate;
    @Column(
            name = "VERSION"
    )
    private int version;

    public PortfolioLicense() {
    }

    public Integer getPortfolioLicenseId() {
        return this.portfolioLicenseId;
    }

    public void setPortfolioLicenseId(Integer portfolioLicenseId) {
        this.portfolioLicenseId = portfolioLicenseId;
    }

    public Portfolio getPortfolio() {
        return this.portfolio;
    }

    public void setPortfolio(Portfolio portfolio) {
        this.portfolio = portfolio;
    }

    public Integer getIssueVerifierType() {
        return this.issueVerifierType;
    }

    public void setIssueVerifierType(Integer issueVerifierType) {
        this.issueVerifierType = issueVerifierType;
    }

    public String getLicenseNumber() {
        return this.licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public Integer getLicenseType() {
        return this.licenseType;
    }

    public void setLicenseType(Integer licenseType) {
        this.licenseType = licenseType;
    }

    public Date getIssueDate() {
        return this.issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Date getEndDate() {
        return this.endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public File getFile() {
        return this.file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public List<LicenseDetails> getLicenseDetails() {
        return this.licenseDetails;
    }

    public void setLicenseDetails(List<LicenseDetails> licenseDetails) {
        this.licenseDetails = licenseDetails;
    }

    public int getVersion() {
        return this.version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
