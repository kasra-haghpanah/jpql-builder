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
        name = "LocationDetail"
)
@NamedQueries({})
public class LocationDetails implements Model {
    @Id
    @Column(
            name = "id",
            nullable = false,
            unique = true
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE
    )
    Integer locationDetailId;
    @NotNull
    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "portfolioLocationId",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "FK_Portfolio_Location_Detail"
            )
    )
    private PortfolioLocation portfolioLocation;
    @OneToOne(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL}
    )
    @JoinColumn(
            name = "fileMetaDataId",
            referencedColumnName = "l4610flmtdataid",
            foreignKey = @ForeignKey(
                    name = "FK_FILE_Location_Detail"
            )
    )
    private File file;
    @Embedded
    private Address address;

    public LocationDetails() {
    }

    public Integer getLocationDetailId() {
        return this.locationDetailId;
    }

    public void setLocationDetailId(Integer locationDetailId) {
        this.locationDetailId = locationDetailId;
    }

    public PortfolioLocation getPortfolioLocation() {
        return this.portfolioLocation;
    }

    public void setPortfolioLocation(PortfolioLocation portfolioLocation) {
        this.portfolioLocation = portfolioLocation;
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
