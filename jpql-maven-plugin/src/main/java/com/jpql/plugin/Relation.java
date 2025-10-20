package com.jpql.plugin;

/**
 * Created by k.haghpanah on 5/20/2019.
 */
public abstract class Relation implements Cloneable {

    protected String type;
    protected String parentTableName;
    protected String childTableName;
    protected String parentTableAliasName = "";
    protected String childTableAliasName = "";

    public boolean enabled = false;

    public abstract Relation clone();

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getParentTableName() {
        return parentTableName;
    }

    public void setParentTableName(String parentTableName) {
        this.parentTableName = parentTableName;
    }

    public String getChildTableName() {
        return childTableName;
    }

    public void setChildTableName(String childTableName) {
        this.childTableName = childTableName;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getParentTableAliasName() {
        return parentTableAliasName;
    }

    public void setParentTableAliasName(String parentTableAliasName) {
        this.parentTableAliasName = parentTableAliasName;
    }

    public String getChildTableAliasName() {
        return childTableAliasName;
    }

    public void setChildTableAliasName(String childTableAliasName) {
        this.childTableAliasName = childTableAliasName;
    }
}
