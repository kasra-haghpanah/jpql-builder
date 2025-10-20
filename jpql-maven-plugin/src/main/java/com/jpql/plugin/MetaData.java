package com.jpql.plugin;

import com.jpql.api.interfaces.Model;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by swb on 03/04/2019.
 */
public class MetaData {

    // List<Object> 0- deep   1- model    2- aliasname     3- aliasnameWithField      4- is Unique    5- fieldName
    // 6 - annotationType   7- isLike   8- ObjectValue     9- path    10- index    11- type

    private Integer deep = new Integer("0");
    private Class model;
    private String aliasName;
    private String aliasnameWithField;
    private boolean isUnique;
    private Field field;
    private Field parentField;
    private String annotationType;
    private String likeValue;
    //ObjectValue
    private Object defaultValue;
    private String path;
    private String classPath;
    private Integer index = new Integer("0");
    private Class type;
    private MetaData parent;
    private final List<MetaData> childs = new ArrayList<MetaData>();
    private String assosiation = "";
    private boolean isModel;
    private boolean isLeftJoin = false;
    private boolean isList = false;
    private String methodName = "";
    private boolean subClassList;
    private String position = "";
    private boolean isDeleteOraphanRemoval = true;
    private String tableName = "";
    private String columnName = "";
    private Relation relation;
    private boolean isUp = false;

    public Integer getDeep() {
        return deep;
    }

    public void setDeep(Integer deep) {
        this.deep = deep;
    }

    public Class getModel() {
        return model;
    }

    public void setModel(Class model) {
        this.model = model;
    }

    public String getAliasName() {
        return aliasName;
    }

    public void setAliasName(String aliasName) {
        this.aliasName = aliasName;
    }

    public String getAliasnameWithField() {
        return aliasnameWithField;
    }

    public void setAliasnameWithField(String aliasnameWithField) {
        this.aliasnameWithField = aliasnameWithField;
    }

    public boolean isUnique() {
        return isUnique;
    }

    public void setUnique(boolean unique) {
        isUnique = unique;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public Field getParentField() {
        return parentField;
    }

    public void setParentField(Field parentField) {
        this.parentField = parentField;
    }

    public String getAnnotationType() {
        return annotationType;
    }

    public void setAnnotationType(String annotationType) {
        this.annotationType = annotationType;
    }

    public String getLikeValue() {
        return likeValue;
    }

    public void setLikeValue(String likeValue) {
        this.likeValue = likeValue;
    }

    public Object getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(Object defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getClassPath() {
        return classPath;
    }

    public void setClassPath(String classPath) {
        this.classPath = classPath;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Class getType() {
        return type;
    }

    public void setType(Class type) {
        this.type = type;
    }

    public MetaData getParent() {
        return parent;
    }

    public void setParent(MetaData parent) {
        this.parent = parent;
    }

    public List<MetaData> getChilds() {
        return childs;
    }

    public void addChild(MetaData child) {
        this.childs.add(child);
    }

    public String getAssosiation() {
        return assosiation;
    }

    public void setAssosiation(String assosiation) {
        this.assosiation = assosiation;
    }

    public boolean isModel() {
        return isModel;
    }

    public void setModel(boolean model) {
        isModel = model;
    }

    public boolean isLeftJoin() {
        return isLeftJoin;
    }

    public void setLeftJoin(boolean leftJoin) {
        isLeftJoin = leftJoin;
    }

    public boolean isList() {
        return isList;
    }

    public void setList(boolean list) {
        isList = list;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public boolean isSubClassList() {
        return subClassList;
    }

    public void setSubClassList(boolean subClassList) {
        this.subClassList = subClassList;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public boolean isDeleteOraphanRemoval() {
        return isDeleteOraphanRemoval;
    }

    public void setDeleteOraphanRemoval(boolean deleteOraphanRemoval) {
        isDeleteOraphanRemoval = deleteOraphanRemoval;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public Relation getRelation() {
        return relation;
    }

    public void setRelation(Relation relation) {
        this.relation = relation;
    }

    public boolean isUp() {
        return isUp;
    }

    public void setUp(boolean up) {
        isUp = up;
    }
}
