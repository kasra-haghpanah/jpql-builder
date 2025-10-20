package com.tosan.bpms.model.nosql;

//import com.impetus.kundera.index.Index;
//import com.impetus.kundera.index.IndexCollection;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by kasra.haghpanah on 9/9/2018.
 */
@Entity
@Table(
        name = "spark_person"
)
//@IndexCollection(
//        columns = {@Index(
//                name = "personName"
//        ), @Index(
//                name = "age"
//        ), @Index(
//                name = "salary"
//        )}
//)
public class Person implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private String personId;
    private String personName;
    private long age;
    private Double salary;

    public Person() {
    }

    public Person(String personId, String personName, long age, Double salary) {
        this.personId = personId;
        this.personName = personName;
        this.age = age;
        this.salary = salary;
    }

    public String getPersonId() {
        return this.personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getPersonName() {
        return this.personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public long getAge() {
        return this.age;
    }

    public void setAge(long age) {
        this.age = age;
    }

    public Double getSalary() {
        return this.salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }
}