package com.tosan.bpms.model.nosql;

import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by kasra.haghpanah on 11/01/2017.
 */
@Cacheable(true)
@Entity
@Table(name = "user"/*, schema = "loan2Core@spark_mongo_pu"*/)
@NamedQueries(
        {
                @NamedQuery(name = "getAllUserNoSQL", query = "SELECT u From UserNoSQL u"),
                @NamedQuery(name = "getByIdUserNoSQL", query = "SELECT u From UserNoSQL u WHERE u.id =:id"),
                @NamedQuery(name = "getByUserNameUserNoSQL", query = "SELECT u From UserNoSQL u WHERE u.username =:username")
        }
)
public class UserNoSQL implements Serializable {


    public final static String GET_BY_ID = "getByIdUserNoSQL";
    public final static String GET_ALL = "getAllUserNoSQL";
    public final static String GET_BY_NAME = "getByUserNameUserNoSQL";

    public UserNoSQL() {
    }


    @Id
    @Basic(optional = false)
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;



    //@Basic(optional = false)

    @Email
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Version
    @Column(name = "version")
    private int version;

    @OneToMany(
            mappedBy = "userNoSQL" ,
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    List<GroupNoSQL> groups = new ArrayList<GroupNoSQL>();


    @Embedded
    @AttributeOverrides(value = {
            @AttributeOverride(name = "addressLine1", column = @Column(name = "house_number")),
            @AttributeOverride(name = "addressLine2", column = @Column(name = "street"))
    })
    private AddressNoSQL address;


    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public List<GroupNoSQL> getGroups() {
        return groups;
    }

    public void setGroups(List<GroupNoSQL> groups) {
        this.groups = groups;
    }

    public AddressNoSQL getAddress() {
        return address;
    }

    public void setAddress(AddressNoSQL address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "{\"UserNoSQL\":{"
                + "\"id\":\"" + id + "\""
                + ",\"username\":\"" + username + "\""
                + ",\"password\":\"" + password + "\""
                + ",\"version\":\"" + version + "\""
                + ",\"groups\":" + groups + "\""
                + ",\"address\":" + address + "\""
                + "}}";
    }
}
