package com.tosan.bpms.model.sql;




import com.jpql.api.annotations.LeftJoin;
import com.jpql.api.interfaces.Model;

import javax.persistence.*;
import javax.xml.bind.DatatypeConverter;

/**
 * Created by kasra.haghpanah on 11/13/2018.
 */
@Entity
@Table(name = "LF4FLMETADATA1")
@LeftJoin
public class File implements Model {


    @Id
    @Column(name = "l4610flmtdataid", length = 100, nullable = false, unique = true)
    String id;


    @Column(name = "l4610extension", length = 5, nullable = false)
    String extension;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "l4610filecontent", nullable = false)
    byte[] content;


    //@Version
    @Column(name = "VERSION")
    private int version;


    public File() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {

        int lastIndexOf = extension.lastIndexOf('.');

        if (lastIndexOf > -1) {
            extension = extension.substring(lastIndexOf + 1, extension.length());
        }

        this.extension = extension;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {

        if (content != null) {
            try {
                content = DatatypeConverter.parseBase64Binary(new String(content));
            } catch (Exception e) {

            }

            this.content = content;
        }
    }


    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
