package com.tosan.bpms.model.nosql;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by kasra.haghpanah on 01/09/2018.
 */
@Entity
@Table(name = "ATTACHMENT"/*, schema = "loan2Core@spark_mongo_pu"*/)
public class Attachment implements Serializable{

    @Id
    @Column(name = "ATTCHMENT_ID")
    private String attachmentId;

    @Column(name = "FILE_NAME")
    private String fileName;

    @Column(name = "FILE_TYPE")
    private String fileType;
    //Getters, setters, constructors and utility methods omitted.


    public Attachment() {
    }

    public Attachment(String attachmentId, String fileName, String fileType) {
        this.attachmentId = attachmentId;
        this.fileName = fileName;
        this.fileType = fileType;
    }

    public String getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(String attachmentId) {
        this.attachmentId = attachmentId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }
}
