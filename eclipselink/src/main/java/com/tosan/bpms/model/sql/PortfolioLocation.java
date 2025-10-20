//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.tosan.bpms.model.sql;

import com.jpql.api.interfaces.Model;
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
import javax.validation.constraints.NotNull;

@Entity
@Table(
        name = "PortfolioLocation"
)
@NamedQueries({})
public class PortfolioLocation implements Model {
    @Id
    @Column(
            name = "id",
            nullable = false,
            unique = true
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE
    )
    Integer portfolioLocationId;
    @NotNull
    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "portfolioId",
            referencedColumnName = "portfolioId",
            foreignKey = @ForeignKey(
                    name = "FK_Portfolio_Location"
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
                    name = "FK_FILE_Location"
            )
    )
    private File file;
    @OneToMany(
            mappedBy = "portfolioLocation",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY
    )
    List<LocationDetails> locationDetails;
    @Column(
            name = "province"
    )
    String province = "";
    @Column(
            name = "city"
    )
    String city = "";
    @Column(
            name = "indstrltwn"
    )
    String indstrltwn = "";
    @Column(
            name = "address"
    )
    String address = "";
    @Column(
            name = "stableStatusType"
    )
    Integer stableStatusType = Integer.valueOf(0);
    @Column(
            name = "ownerStatusType"
    )
    Integer ownerStatusType = Integer.valueOf(0);
    @Column(
            name = "regionalStatusType"
    )
    Integer regionalStatusType = Integer.valueOf(0);
    @Column(
            name = "VERSION"
    )
    private int version;

    public File getFile() {
        return this.file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public PortfolioLocation() {
    }

    public Integer getPortfolioLocationId() {
        return this.portfolioLocationId;
    }

    public void setPortfolioLocationId(Integer portfolioLocationId) {
        this.portfolioLocationId = portfolioLocationId;
    }

    public Portfolio getPortfolio() {
        return this.portfolio;
    }

    public void setPortfolio(Portfolio portfolio) {
        this.portfolio = portfolio;
    }

    public String getProvince() {
        return this.province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getIndstrltwn() {
        return this.indstrltwn;
    }

    public void setIndstrltwn(String indstrltwn) {
        this.indstrltwn = indstrltwn;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getStableStatusType() {
        return this.stableStatusType;
    }

    public void setStableStatusType(Integer stableStatusType) {
        this.stableStatusType = stableStatusType;
    }

    public Integer getOwnerStatusType() {
        return this.ownerStatusType;
    }

    public void setOwnerStatusType(Integer ownerStatusType) {
        this.ownerStatusType = ownerStatusType;
    }

    public Integer getRegionalStatusType() {
        return this.regionalStatusType;
    }

    public void setRegionalStatusType(Integer regionalStatusType) {
        this.regionalStatusType = regionalStatusType;
    }

    public List<LocationDetails> getLocationDetails() {
        return this.locationDetails;
    }

    public void setLocationDetails(List<LocationDetails> locationDetails) {
        this.locationDetails = locationDetails;
    }

    public int getVersion() {
        return this.version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
