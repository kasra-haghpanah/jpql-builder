package com.tosan.bpms.model.sql;

import com.jpql.api.interfaces.Model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by k.haghpanah on 4/22/2019.
 */
@Entity
@Table(name = "Employee")
public class Employee implements Model {

    public Employee() {
    }

    @Id
    @Column(name = "ID", unique = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "EMP_PROJ",
            joinColumns = {@JoinColumn(name = "EMP_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "PROJ_ID", referencedColumnName = "ID")}
    )
    private List<Project> projects;

    @Column(name = "age")
    private Integer age;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "version")
    private int version;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
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
}
