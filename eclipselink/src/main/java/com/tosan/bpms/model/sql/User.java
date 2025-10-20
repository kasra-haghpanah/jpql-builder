package com.tosan.bpms.model.sql;

import com.jpql.api.annotations.Like;
import com.jpql.api.interfaces.Model;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by kasra.haghpanah on 11/01/2017.
 */
@Cacheable(true)
@Entity
@Table(name = "user")
public class User implements Model {


    public final static String GET_BY_ID = "getByIdUser";
    public final static String GET_ALL = "getAllUser";
    public final static String GET_BY_NAME = "getByUserNameUser";

    public User() {
    }


    @Id
    @Basic(optional = false)
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;



    //@Basic(optional = false)

    //@Email
    @Like
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Version
    @Column(name = "version")
    private int version;

    @OneToMany(
            mappedBy = "user" ,
            cascade = CascadeType.ALL,
            //orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    List<Group> groups = new ArrayList<Group>();


    @Embedded
    @AttributeOverrides(value = {
            @AttributeOverride(name = "addressLine1", column = @Column(name = "house_number")),
            @AttributeOverride(name = "addressLine2", column = @Column(name = "street"))
    })
    private Address address;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "{\"User\":{"
                + "\"id\":\"" + id + "\""
                + ",\"username\":\"" + username + "\""
                + ",\"password\":\"" + password + "\""
                + ",\"version\":\"" + version + "\""
                + ",\"groups\":" + groups + "\""
                + ",\"address\":" + address + "\""
                + "}}";
    }
}
