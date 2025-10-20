package com.jpql.plugin;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by k.haghpanah on 7/17/2019.
 */
public class OneORMany extends Relation {

    JoinColumnDetail joinColumnDetail;

    public OneORMany() {
    }

    public OneORMany(String parentTableName, String childTableName, JoinColumnDetail joinColumnDetail) {
        super.parentTableName = parentTableName;
        super.childTableName = childTableName;
        this.joinColumnDetail = joinColumnDetail;
    }

    public JoinColumnDetail getJoinColumnDetail() {
        return joinColumnDetail;
    }

    public void setJoinColumnDetail(JoinColumnDetail joinColumnDetail) {
        this.joinColumnDetail = joinColumnDetail;
    }

    public Relation clone() {
        OneORMany oneORMany = new OneORMany(this.parentTableName, this.childTableName, joinColumnDetail.clone());
        oneORMany.setType(super.getType());
        oneORMany.setEnabled(super.isEnabled());
        oneORMany.setParentTableAliasName(super.getParentTableAliasName());
        oneORMany.setChildTableAliasName(super.getChildTableAliasName());
        return oneORMany;
    }
}
