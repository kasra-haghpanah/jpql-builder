package com.tosan.bpms.model.sql;

import com.jpql.api.interfaces.Model;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * Created by kasra.haghpanah on 01/09/2018.
 */
@Embeddable
public class Address implements Model {
    @NotNull
    @Size(max = 100)
    @Column(name = "addressLine1")
    private String addressLine1;

    @NotNull
    @Size(max = 100)
    @Column(name = "addressLine2")
    private String addressLine2;

    @NotNull
    @Size(max = 100)
    @Column(name = "city")
    private String city;

    @NotNull
    @Size(max = 100)
    @Column(name = "state")
    private String state;

    @NotNull
    @Size(max = 100)
    @Column(name = "country")
    private String country;

    @NotNull
    @Size(max = 6)
    @Column(name = "zipCode")
    private String zipCode;


    public Address() {

    }

    public Address(String addressLine1, String addressLine2, String city, String state, String country, String zipCode) {
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.state = state;
        this.country = country;
        this.zipCode = zipCode;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public String toString() {
        return "{\"Address\":{"
                + "\"addressLine1\":\"" + addressLine1 + "\""
                + ",\"addressLine2\":\"" + addressLine2 + "\""
                + ",\"city\":\"" + city + "\""
                + ",\"state\":\"" + state + "\""
                + ",\"country\":\"" + country + "\""
                + ",\"zipCode\":\"" + zipCode + "\""
                + "}}";
    }


    // Getters and Setters (Omitted for brevity)
}
