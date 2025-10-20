package com.tosan.bpms.model.nosql;

import org.eclipse.persistence.annotations.Cache;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * Created by kasra.haghpanah on 11/01/2017.
 */
@Cacheable(true)
@Entity
@Table(name = "groups"/*, schema = "loan2Core@spark_mongo_pu"*/)
@Cache(size = 200)
@NamedQueries(
        {
                @NamedQuery(name = "getAllGroupNoSQL", query = "SELECT g From GroupNoSQL g"),
                @NamedQuery(name = "getByIdGroupNoSQL", query = "SELECT g From GroupNoSQL g WHERE g.id =:id"),
                @NamedQuery(name = "getByUsernameGroupNoSQL", query = "SELECT g From GroupNoSQL g JOIN g.userNoSQL u WHERE u.username =:username")
        }
)

public class GroupNoSQL implements Serializable{


    public static class Role {

        public final static String ADMIN = "admin";
        public final static String USER = "user";

    }

    public final static String GET_BY_ID = "getByIdGroupNoSQL";
    public final static String GET_ALL = "getAllGroupNoSQL";
    public final static String GET_BY_USERNAME = "getByUsernameGroupNoSQL";

    public GroupNoSQL(){}


    @Id
    @Basic(optional = false)
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;


    @NotNull
    @ManyToOne//(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "user_username",// user_username
            referencedColumnName = "username",
            foreignKey = @ForeignKey(name = "FK_groups_user_username")
    )
    private UserNoSQL userNoSQL;

    @NotBlank
    @Pattern(regexp="(.){3,10}")
    @Column(name = "role")
    private String role;

    @Version
    @Column(name = "version")
    private int version;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UserNoSQL getUserNoSQL() {
        return userNoSQL;
    }

    public void setUserNoSQL(UserNoSQL userNoSQL) {
        this.userNoSQL = userNoSQL;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "{\"GroupNoSQL\":{"
                + "\"id\":\"" + id + "\""
                + ",\"role\":\"" + role + "\""
                + ",\"version\":\"" + version + "\""
                + "}}";
    }
}
