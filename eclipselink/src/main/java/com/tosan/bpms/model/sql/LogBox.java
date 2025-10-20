package com.tosan.bpms.model.sql;



import com.jpql.api.interfaces.Model;

import javax.persistence.*;

/**
 * Created by kasra.haghpanah on 16/09/2016.
 */
@Cacheable(false)
@Entity
@Table(name = "LogBox")
@NamedQueries(
        {
                //@NamedQuery(name = "getAllLog", query = "SELECT l From com.tosan.bpms.model.sql.Log l")
        }
)
public class LogBox implements Model {

    public LogBox() {
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Version
    @Column(name = "version")
    private int version;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "{\"LogBox\":{"
                + "\"id\":\"" + id + "\""
                + ",\"firstName\":\"" + firstName + "\""
                + ",\"lastName\":\"" + lastName + "\""
                + ",\"version\":\"" + version + "\""
                + "}}";
    }
}
