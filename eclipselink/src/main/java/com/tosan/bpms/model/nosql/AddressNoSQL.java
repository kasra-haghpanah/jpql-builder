package com.tosan.bpms.model.nosql;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created by kasra.haghpanah on 01/09/2018.
 */
@Embeddable
public class AddressNoSQL  implements Serializable {
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


    public AddressNoSQL() {

    }

    public AddressNoSQL(String addressLine1, String addressLine2, String city,
                        String state, String country, String zipCode) {
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.state = state;
        this.country = country;
        this.zipCode = zipCode;
    }

    @Override
    public String toString() {
        return "{\"AddressNoSQL\":{"
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
