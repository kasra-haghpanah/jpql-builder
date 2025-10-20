//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.tosan.bpms.model.sql;

import com.jpql.api.interfaces.Model;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(
        name = "LicenseDetails"
)
@NamedQueries({})
public class LicenseDetails implements Model {
    @Id
    @Column(
            name = "id",
            nullable = false,
            unique = true
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE
    )
    Integer licenseDetailId;
    @NotNull
    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "portfolioLicenseId",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "FK_Portfolio_License_Detail"
            )
    )
    private PortfolioLicense portfolioLicense;
    @OneToOne(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL}
    )
    @JoinColumn(
            name = "fileMetaDataId",
            referencedColumnName = "l4610flmtdataid",
            foreignKey = @ForeignKey(
                    name = "FK_FILE_License_Detail"
            )
    )
    private File file;
    @Embedded
    private Address address;

    public LicenseDetails() {
    }

    public Integer getLicenseDetailId() {
        return this.licenseDetailId;
    }

    public void setLicenseDetailId(Integer licenseDetailId) {
        this.licenseDetailId = licenseDetailId;
    }

    public PortfolioLicense getPortfolioLicense() {
        return this.portfolioLicense;
    }

    public void setPortfolioLicense(PortfolioLicense portfolioLicense) {
        this.portfolioLicense = portfolioLicense;
    }

    public File getFile() {
        return this.file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public Address getAddress() {
        return this.address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
