package com.tosan.bpms.model.nosql;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by kasra.haghpanah on 01/09/2018.
 */
@Embeddable
public class Contact implements Serializable
{

    @Column(name = "CONTACT_ID")
    String contactId;

    @Column(name = "LAST_NAME")
    String lastName;

    @Column(name = "FIRST_NAME")
    String firstName;

    @Column(name = "EMAIL_ID")
    String emailId;

    //Getters, setters, constructors and utility methods omitted.


    public Contact() {
    }

    public Contact(String contactId, String lastName, String firstName, String emailId) {
        this.contactId = contactId;
        this.lastName = lastName;
        this.firstName = firstName;
        this.emailId = emailId;
    }

    public String getContactId() {
        return contactId;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    @Override
    public String toString() {
        return "{\"Contact\":{"
                + "\"contactId\":\"" + contactId + "\""
                + ",\"lastName\":\"" + lastName + "\""
                + ",\"firstName\":\"" + firstName + "\""
                + ",\"emailId\":\"" + emailId + "\""
                + "}}";
    }
}
