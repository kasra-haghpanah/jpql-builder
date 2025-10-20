package com.jpql.plugin;

import java.util.*;

/**
 * Created by k.haghpanah on 5/20/2019.
 */
public class ManyToMany extends Relation {

/*    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "EMP_PROJ",
            joinColumns = @JoinColumn(name = "EMP_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "PROJ_ID", referencedColumnName = "ID"))
    private List<Project> projects;
    */


    /*
    DELETE EMP_PROJ , Employee1 , Project2
    FROM EMP_PROJ EMP_PROJ
    LEFT JOIN Employee Employee1 ON(EMP_PROJ.EMP_ID = Employee1.ID)
    LEFT JOIN Project Project2 ON(EMP_PROJ.PROJ_ID = Project2.ID)
    WHERE 1=1
    */

    String middleTableName;
    JoinColumnDetail joinColumns;
    JoinColumnDetail inverseJoinColumns;


    public ManyToMany() {
        super.setType("ManyToMany");
    }

    public ManyToMany(String middleTableName, String parentTableName, String childTableName, JoinColumnDetail joinColumns, JoinColumnDetail inverseJoinColumns) {
        super.setType("ManyToMany");
        super.parentTableName = parentTableName;
        super.childTableName = childTableName;
        this.middleTableName = middleTableName;
        this.joinColumns = joinColumns;
        this.inverseJoinColumns = inverseJoinColumns;
    }

    public String getMiddleTableName() {
        return middleTableName;
    }

    public void setMiddleTableName(String middleTableName) {
        this.middleTableName = middleTableName;
    }

    public JoinColumnDetail getJoinColumns() {
        return joinColumns;
    }

    public void setJoinColumns(JoinColumnDetail joinColumns) {
        this.joinColumns = joinColumns;
    }

    public JoinColumnDetail getInverseJoinColumns() {
        return inverseJoinColumns;
    }

    public void setInverseJoinColumns(JoinColumnDetail inverseJoinColumns) {
        this.inverseJoinColumns = inverseJoinColumns;
    }

    public Relation clone() {
        ManyToMany manyToMany = new ManyToMany(this.middleTableName, this.parentTableName, this.childTableName, joinColumns.clone(), inverseJoinColumns.clone());
        manyToMany.setType(super.getType());
        manyToMany.setEnabled(super.isEnabled());
        manyToMany.setParentTableAliasName(super.getParentTableAliasName());
        manyToMany.setChildTableAliasName(super.getChildTableAliasName());
        return manyToMany;
    }
}
