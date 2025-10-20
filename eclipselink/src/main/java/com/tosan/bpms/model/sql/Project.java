package com.tosan.bpms.model.sql;

import com.jpql.api.interfaces.Model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by k.haghpanah on 4/22/2019.
 */


@Entity
@Table(name = "Project")
public class Project implements Model {

    public Project() {
    }

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @ManyToMany(mappedBy = "projects", cascade = CascadeType.PERSIST)
    private List<Employee> employees;


    @Column(name = "prjctName")
    private String projectName;


    @Column(name = "prjctDate")
    private Date projectDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Date getProjectDate() {
        return projectDate;
    }

    public void setProjectDate(Date projectDate) {
        this.projectDate = projectDate;
    }
}
