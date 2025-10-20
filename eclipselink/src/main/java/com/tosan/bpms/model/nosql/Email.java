package com.tosan.bpms.model.nosql;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Created by kasra.haghpanah on 01/09/2018.
 */
@Entity
@Table(name = "EMAIL"/*, schema = "loan2Core@spark_mongo_pu"*/)
public class Email  implements Serializable {

    @Id
    @Column(name = "MESSAGE_ID")
    private String messageId;

    @Column(name = "SUBJECT")
    private String subject;

    @Column(name = "BODY")
    private String body;

    @Embedded
    private Contact from;

    @ElementCollection
    @CollectionTable(name = "TO")
    private List<Contact> to;


    public void addTo(Contact... contacts) {
        if (this.to == null) {
            this.to = new ArrayList<Contact>();
        }

        if (contacts != null && contacts.length > 0) {
            for (Contact contact : contacts) {
                this.to.add(contact);
            }
        }

    }

    public void addAttachment(Attachment... attachments) {
        if (this.attachments == null) {
            this.attachments = new ArrayList<Attachment>();
        }

        if (attachments != null && attachments.length > 0) {
            for (Attachment attachment : attachments) {
                this.attachments.add(attachment);
            }
        }

    }

    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name = "MESSAGE_ID")
    private List<Attachment> attachments;

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Contact getFrom() {
        return from;
    }

    public void setFrom(Contact from) {
        this.from = from;
    }

    public List<Contact> getTo() {
        return to;
    }

    public void setTo(List<Contact> to) {
        this.to = to;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }

    //Getters, setters, constructors and utility methods omitted.
}
