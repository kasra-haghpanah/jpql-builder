package com.tosan.bpms.model.sql;

import com.jpql.api.annotations.LeftJoin;
import com.jpql.api.interfaces.Model;
import org.eclipse.persistence.annotations.Cache;
import org.hibernate.validator.constraints.NotBlank;
import javax.persistence.*;
import javax.validation.constraints.Pattern;


/**
 * Created by kasra.haghpanah on 11/01/2017.
 */
@Cacheable(true)
@Cache(size = 200)
@Entity
@Table(name = "groups")
@LeftJoin
public class Group implements Model {


    public static class Role {
        public final static String ADMIN = "admin";
        public final static String USER = "user";
    }

    public Group(){}


    @Id
    @Basic(optional = false)
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    //@NotNull
    @ManyToOne//(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "user_username",// user_username
            referencedColumnName = "username",
            foreignKey = @ForeignKey(name = "FK_groups_user_username")
    )
    private User user;

    @NotBlank
    @Pattern(regexp="(.){3,20}")
    @Column(name = "role")
    private String role;

    @Version
    @Column(name = "version")
    private int version;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
        return "{\"Groups\":{"
                + "\"id\":\"" + id + "\""
                + ",\"role\":\"" + role + "\""
                + ",\"version\":\"" + version + "\""
                + "}}";
    }
}
