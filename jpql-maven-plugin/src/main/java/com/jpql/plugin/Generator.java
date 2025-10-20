package com.jpql.plugin;

import com.google.googlejavaformat.java.*;
import com.jpql.api.enums.DependencyInjectionType;
import com.jpql.api.interfaces.Model;

import javax.tools.*;
import java.io.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.Charset;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by kasra.haghpanah on 3/16/2019.
 */
public class Generator {

    private static int index;
    private static String whereClause = "";
    private static String basicSQLEager = "";
    private static String basicSQLLazy = "";
    private static String keyExistsMap = "";
    private static ClassLoader classLoader;
    private static final List<String> javaListFiles = new ArrayList<String>();
    private static final Map<String, String> tableNames = new HashMap<String, String>();


    private static synchronized void setClassLoader(String[] jarFiles) {

        URL[] urls = new URL[jarFiles.length];
        if (jarFiles != null) {
            try {
                for (int i = 0; i < jarFiles.length; i++) {

                    if (jarFiles[i] != null) {
                        urls[i] = new File(jarFiles[i]).toURI().toURL();
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        classLoader = new URLClassLoader(urls);
    }

    private static synchronized Class<?> forName(String classname) throws ClassNotFoundException {

        classLoader.setDefaultAssertionStatus(true);
        return classLoader.loadClass(classname);
        //todo
        //return Class.forName(classname);
    }

    private static Map<String, Annotation> getAnnotation(Field field) {

        Map<String, Annotation> map = new HashMap<String, Annotation>();
        if (field == null) {
            return map;
        }

        Annotation[] annotations = field.getAnnotations();
        if (annotations != null && annotations.length > 0) {
            for (int i = 0; i < annotations.length; i++) {
                map.put(annotations[i].annotationType().getSimpleName(), annotations[i]);
            }
        }
        return map;
    }

    private static Map<String, Annotation> getAnnotation(Class clazz) {

        Map<String, Annotation> map = new HashMap<String, Annotation>();
        if (clazz == null) {
            return map;
        }

        Annotation[] annotations = clazz.getAnnotations();
        if (annotations != null && annotations.length > 0) {
            for (int i = 0; i < annotations.length; i++) {
                map.put(annotations[i].annotationType().getSimpleName(), annotations[i]);
            }
        }
        return map;
    }

    private static String getLikeValue(Field field) {

        Map<String, Annotation> map = getAnnotation(field);

        if (!field.getType().getName().equals("java.lang.String")) {
            return "";
        }

        Annotation annotation = map.get("Like");
        if (annotation == null) {
            return "";
        }
        String value = null;
        try {
            value = "" + annotation.annotationType().getMethod("status").invoke(annotation);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        return value;

    }

    private static Map<String, String> getJoinColumnAttribute(Class parentModel, Class childModel, Annotation annotation) {

        Map<String, String> map = new HashMap<String, String>();

        Map<String, Annotation> parentModelMap = getAnnotation(parentModel);
        Map<String, Annotation> childModelMap = getAnnotation(childModel);
        Annotation parentTable = parentModelMap.get("Table");
        Annotation childTable = childModelMap.get("Table");

        String parentTableName = parentModel.getSimpleName();
        String childTableName = childModel.getSimpleName();

        try {
            String value;
            value = "" + annotation.annotationType().getMethod("name").invoke(annotation);
            map.put("name", value);

            value = "" + annotation.annotationType().getMethod("referencedColumnName").invoke(annotation);

            if (value.equals("")) {
                Field[] fields = parentModel.getDeclaredFields();
                for (int i = 0; i < fields.length; i++) {
                    Map<String, Annotation> mapAnnotate = getAnnotation(fields[i]);
                    if (mapAnnotate.get("Id") != null) {
                        Annotation column = mapAnnotate.get("Column");
                        if (column == null) {
                            value = fields[i].getName();
                            break;
                        } else {
                            value = "" + column.annotationType().getMethod("name").invoke(column);
                            break;
                        }
                    }
                }
            }

            map.put("referencedColumnName", value);


            value = "" + parentTable.annotationType().getMethod("name").invoke(parentTable);
            if (!value.equals("")) {
                parentTableName = value;
            }

            value = "" + childTable.annotationType().getMethod("name").invoke(childTable);
            if (!value.equals("")) {
                childTableName = value;
            }


        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        map.put(parentModel.getName(), parentTableName);
        map.put(childModel.getName(), childTableName);

        return map;

    }

    private static boolean hasJoinColumn(Field field) {

        Map<String, Annotation> map = getAnnotation(field);
        if (map.get("JoinColumn") == null) {
            return false;
        }
        return true;
    }

    private static boolean isLeftJoin(Class clazz) {
        return classHasDestAnnotation(clazz, "LeftJoin");
    }

    private static boolean isEmbeddable(Class clazz) {
        return classHasDestAnnotation(clazz, "Embeddable");
    }

    private static boolean classHasDestAnnotation(Class clazz, String anntationType) {

        Annotation[] annotations = clazz.getAnnotations();
        if (annotations != null && annotations.length > 0) {
            for (int i = 0; i < annotations.length; i++) {
                if (annotations[i].annotationType().getSimpleName().equals(anntationType)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static Object getAnnotationAttribueValue(Annotation annotation, String attribute) {

        if (annotation == null) {
            return null;
        }

        Object vlaue = null;
        try {
            vlaue = annotation.annotationType().getMethod(attribute).invoke(annotation);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        return vlaue;
    }


    private static String getSqlTableName(Class clazz) {

        Map<String, Annotation> map = getAnnotation(clazz);
        Annotation Table = map.get("Table");

        if (Table == null) {
            return clazz.getSimpleName();
        }

        Object newChildTableName = getAnnotationAttribueValue(map.get("Table"), "name");
        if (newChildTableName != null && !newChildTableName.equals("")) {
            return newChildTableName + "";
        }

        return clazz.getSimpleName();
    }

    private static Field getField(Class clazz, String fieldName) {
        try {
            return clazz.getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static JoinColumnDetail getJoinColumns(Field field, Annotation joinColumns, Annotation joinColumn) {
        return getJoinColumns(field, joinColumns, joinColumn, null);
    }

    private static JoinColumnDetail getJoinColumns(Field field, Annotation[] joinColumns) {
        return getJoinColumns(field, null, null, joinColumns);
    }

    private static String getReferencedColumnNameIfIsNull(Field field) {

        if (field == null) {
            return "";
        }
        boolean isList = List.class.isAssignableFrom(field.getType());
        String referencedColumnName = "";

        Class classParent = null;
        if (isList) {
            classParent = getListGenericType(field);
        } else {
            classParent = field.getType();
        }

        Field[] fields = classParent.getDeclaredFields();

        if (fields != null) {
            for (int z = 0; z < fields.length; z++) {
                Map<String, Annotation> annotation = getAnnotation(fields[z]);
                if (annotation.get("Id") != null) {
                    if (annotation.get("Column") != null) {
                        referencedColumnName = getAnnotationAttribueValue(annotation.get("Column"), "name") + "";
                    } else {
                        referencedColumnName = fields[z].getName();
                    }
                    break;
                }

            }
        }

        return referencedColumnName;

    }

    private static String getNameIfIsNull(Field field) {
        if (field == null) {
            return "";
        }

        Map<String, Annotation> annotation = getAnnotation(field);

        Annotation Column = annotation.get("Column");

        if (Column != null) {
            return getAnnotationAttribueValue(annotation.get("Column"), "name") + "";
        }

        return field.getName();

    }

    private static JoinColumnDetail getJoinColumns(Field field, Annotation joinColumns, Annotation joinColumn, Annotation[] joinColumnsForManyToMany) {

        if (field == null) {
            return null;
        }

        if (joinColumnsForManyToMany == null && (joinColumns != null && field.getAnnotation(joinColumns.getClass()) == null) && (joinColumn != null && field.getAnnotation(joinColumn.getClass()) == null)) {
            return null;
        }


        JoinColumnDetail joinColumnDetail = new JoinColumnDetail();

        if (joinColumns != null || joinColumnsForManyToMany != null) {

            Annotation[] annotations = null;
            String value = "value";

            if (joinColumns != null) {
                annotations = (Annotation[]) getAnnotationAttribueValue(joinColumns, "value");
            } else if (joinColumnsForManyToMany != null) {
                value = "name";
                annotations = joinColumnsForManyToMany;
            }

            if (annotations != null) {
                value = "name";
                for (int i = 0; i < annotations.length; i++) {
                    String name = (String) getAnnotationAttribueValue(annotations[i], value);
                    String referencedColumnName = (String) getAnnotationAttribueValue(annotations[i], "referencedColumnName");
                    if (name == null || name.equals("")) {
                        name = getNameIfIsNull(field);
                    }

                    if (referencedColumnName == null || referencedColumnName.equals("")) {
                        referencedColumnName = getReferencedColumnNameIfIsNull(field);
                    }

                    joinColumnDetail.put(name, referencedColumnName);
                }
            }

            return joinColumnDetail;
        } else if (joinColumn != null && joinColumnsForManyToMany == null) {

            String name = (String) getAnnotationAttribueValue(joinColumn, "name");
            String referencedColumnName = (String) getAnnotationAttribueValue(joinColumn, "referencedColumnName");

            if (referencedColumnName == null || referencedColumnName.equals("")) {
                referencedColumnName = getReferencedColumnNameIfIsNull(field);
            }

            if (name == null || name.equals("")) {
                name = getNameIfIsNull(field);
            }

            joinColumnDetail.put(name, referencedColumnName);
            return joinColumnDetail;
        }
        return null;

    }


    public static Class getListGenericType(Field field) {
        if (field == null) {
            return null;
        }
        boolean isList = List.class.isAssignableFrom(field.getType());
        if (!isList) {
            return null;
        }

        String genericType = field.getGenericType().toString();
        genericType = genericType.substring(genericType.indexOf("<") + 1, genericType.indexOf(">"));
        Class clazz = null;
        try {
            return forName(genericType);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Relation getAssosiationDetail(MetaData metaData) {

        Field field = metaData.getField();

        if (field == null) {
            return null;
        }

        Map<String, Annotation> map = getAnnotation(field);
        Annotation OneToMany = map.get("OneToMany");
        Annotation OneToOne = map.get("OneToOne");
        Annotation ManyToMany = map.get("ManyToMany");
        Annotation JoinColumn = map.get("JoinColumn");
        Annotation JoinColumns = map.get("JoinColumns");
        Annotation ManyToOne = map.get("ManyToOne");

        String parentTableName = "";
        String childTableName = "";
        Field fieldHasJoinCoulmn = null;
        //boolean isEnabled = false;

        if (OneToMany != null || ManyToOne != null) {

            if (ManyToOne == null) {
                parentTableName = getSqlTableName(field.getDeclaringClass());
                Class clazz = getListGenericType(field);

                childTableName = getSqlTableName(clazz);
                String mappedBy = (String) getAnnotationAttribueValue(OneToMany, "mappedBy");
                fieldHasJoinCoulmn = getField(clazz, mappedBy);
                JoinColumn = getAnnotation(fieldHasJoinCoulmn).get("JoinColumn");
                JoinColumns = getAnnotation(fieldHasJoinCoulmn).get("JoinColumns");
                //isEnabled = true;
            } else {
                metaData.setUp(true);
                fieldHasJoinCoulmn = field;
                parentTableName = getSqlTableName(field.getType());
                childTableName = getSqlTableName(field.getDeclaringClass());
            }

            com.jpql.plugin.OneORMany manyToOne = new OneORMany(parentTableName, childTableName, getJoinColumns(fieldHasJoinCoulmn, JoinColumns, JoinColumn));
            //manyToOne.setEnabled(isEnabled);
            manyToOne.setType("ManyToOne");
            return manyToOne;
        } else if (OneToOne != null) {

            if (JoinColumn == null && JoinColumns == null) {
                parentTableName = getSqlTableName(field.getDeclaringClass());
                childTableName = getSqlTableName(field.getType());
                String mappedBy = (String) getAnnotationAttribueValue(OneToOne, "mappedBy");
                fieldHasJoinCoulmn = getField(field.getType(), mappedBy);
                JoinColumn = getAnnotation(fieldHasJoinCoulmn).get("JoinColumn");
                JoinColumns = getAnnotation(fieldHasJoinCoulmn).get("JoinColumns");
                //isEnabled = true;
            } else {
                metaData.setUp(true);
                fieldHasJoinCoulmn = field;
                parentTableName = getSqlTableName(field.getType());
                childTableName = getSqlTableName(field.getDeclaringClass());
            }

            OneORMany oneORMany = new OneORMany(parentTableName, childTableName, getJoinColumns(fieldHasJoinCoulmn, JoinColumns, JoinColumn));
            //oneORMany.setEnabled(isEnabled);
            oneORMany.setType("OneToOne");
            return oneORMany;

        } else if (ManyToMany != null) {


            Annotation JoinTable = map.get("JoinTable");

            if (JoinTable == null) {
                parentTableName = getSqlTableName(field.getDeclaringClass());
                Class clazz = getListGenericType(field);
                childTableName = getSqlTableName(clazz);
                String mappedBy = (String) getAnnotationAttribueValue(ManyToMany, "mappedBy");
                fieldHasJoinCoulmn = getField(clazz, mappedBy);
                JoinTable = getAnnotation(fieldHasJoinCoulmn).get("JoinTable");
            } else {
                //isEnabled = true;
                parentTableName = getSqlTableName(getListGenericType(field));
                childTableName = getSqlTableName(field.getDeclaringClass());
            }


            ManyToMany manyToMany = new ManyToMany(
                    (String) getAnnotationAttribueValue(JoinTable, "name"),
                    parentTableName,
                    childTableName,
                    getJoinColumns(field, (Annotation[]) getAnnotationAttribueValue(JoinTable, "joinColumns")),
                    getJoinColumns(field, (Annotation[]) getAnnotationAttribueValue(JoinTable, "inverseJoinColumns"))
            );

            /*

            @ManyToMany(cascade = CascadeType.PERSIST)
            @JoinTable(
                    name = "EMP_PROJ",
                    joinColumns = {@JoinColumn(name = "EMP_ID", referencedColumnName = "ID")},
                    inverseJoinColumns = {@JoinColumn(name = "PROJ_ID", referencedColumnName = "ID")}
            )
*/

            //manyToMany.setEnabled(isEnabled);
            return manyToMany;

        }


        return null;
    }

/*
    private static boolean isOrphanRemoval(String baseEntity, Field field) {

        Map<String, Annotation> map = getAnnotation(field);
        Annotation ManyToOne = map.get("ManyToOne");
        if (ManyToOne != null) {
            String type = field.getType().getName();
            boolean isList = List.class.isAssignableFrom(field.getType());
            if (isList) {
                type = field.getGenericType() + "";
                type = type.substring(type.indexOf("<") + 1, type.indexOf(">"));
            }
            if (!type.equals(baseEntity)) {
                return false;
            }
        }

        //Annotation oneToMany = map.get("ManyToOne");


        return true;
    }
*/

    private static synchronized <M extends Object> MetaData getFieldAndValue(String baseEntity, Field field, M entity, Field parentField, int deep, String path, int index, String classPath, boolean isModel, MetaData parentMetaData, boolean isFirstField, boolean isLastField, final Map<String, String> aliasNameMap) {

        String tableName = "";
        String className = "";

        if (isModel) {

            boolean isList = false;
            if (field != null) {
                List.class.isAssignableFrom(field.getType());
            }
            className = entity.getClass().getName();
            if (isList) {
                className = className.substring(className.indexOf("<") + 1, className.indexOf(">"));
            }

        } else {
            className = field.getDeclaringClass().getName();
        }

        try {
            Class cc = forName(className);
            Map<String, Annotation> mapAnnotation = getAnnotation(cc);
            Annotation tableAnnotation = mapAnnotation.get("Table");
            if (tableAnnotation != null) {
                tableName = "" + tableAnnotation.annotationType().getMethod("name").invoke(tableAnnotation);
            }
            if (tableName.equals("")) {
                tableName = cc.getSimpleName();
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        String columnName = "";

        if (!isModel && field != null) {

            try {
                Map<String, Annotation> mapAnnotation = getAnnotation(field);
                Annotation columnAnnotation = mapAnnotation.get("Column");
                if (columnAnnotation != null) {
                    columnName = "" + columnAnnotation.annotationType().getMethod("name").invoke(columnAnnotation);
                }

                if (columnName.equals("")) {
                    columnName = field.getName();
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }


        if (deep == 0 && isModel) {

            MetaData metaData = new MetaData();
            metaData.setDeep(deep);
            Class aClass = entity.getClass();
            metaData.setModel(aClass);
            path = aClass.getSimpleName();
            metaData.setAliasName(path);
            metaData.setAliasnameWithField(path);
            metaData.setUnique(false);
            metaData.setField(null);//////////////////////////
            metaData.setParentField(parentField);
            metaData.setAnnotationType("");
            metaData.setLikeValue("");
            metaData.setDefaultValue(null);
            metaData.setPath(path);
            metaData.setClassPath(aClass.getName());
            metaData.setIndex(index);
            metaData.setType(aClass);
            metaData.setModel(isModel);
            metaData.setMethodName("model");
            metaData.setSubClassList(false);
            if (aliasNameMap.get(path) == null) {
                aliasNameMap.put(path, path + index);
            }

            metaData.setTableName(tableName);
            metaData.setColumnName(columnName);

            if (isModel) {
                metaData.setRelation(getAssosiationDetail(metaData));
            }

            return metaData;
        }

        String getterMethod = getMethodName(field);
        if (field.getType().getName().equals("boolean")) {
            getterMethod = "is" + getterMethod.substring(2, getterMethod.length());
        }
        try {
            field.getDeclaringClass().getDeclaredMethod(getterMethod, null);
        } catch (NoSuchMethodException e) {
            return null;
            //e.printStackTrace();
        }

        Map<String, Annotation> map = getAnnotation(field);
        //Annotation column = map.get("Column");
        Annotation aTransient = map.get("Transient");

        if (aTransient != null) {
            return null;
        }

        Annotation version = map.get("Version"); //field.getAnnotation(Version.class);
        String like = getLikeValue(field);
        MetaData metaData = new MetaData();
        boolean isList = List.class.isAssignableFrom(field.getType());
        String fieldName = field.getName();
        String annotationType = getAnnotationType(parentField, field);
        Class model = field.getDeclaringClass();
        String aliasnameWithField = path;
        boolean isLeftJoin = false;
        String signature = "";
        String beforePath = parentMetaData.getPath();
        String methodName = "";
        if (parentMetaData.getParent() == null) {
            methodName = "model." + getMethodName(field) + "()";
        } else if (parentMetaData.isList()) {
            methodName = parentMetaData.getMethodName() + ".get(i" + parentMetaData.getIndex() + ")." + getMethodName(field) + "()";
        } else {
            methodName = parentMetaData.getMethodName() + "." + getMethodName(field) + "()";
        }

        if (!isModel && !isList) {
            path = path + "." + fieldName;
            aliasnameWithField = path;
        }

        if (isList) {
            signature = field.getGenericType() + "";
            signature = signature.substring(signature.indexOf("<") + 1, signature.indexOf(">"));
            try {
                model = forName(signature);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            isLeftJoin = isLeftJoin(model);
            beforePath = path;
        } else {
            isLeftJoin = isLeftJoin(field.getType());
        }

        metaData.setDeep(deep);
        metaData.setModel(field.getDeclaringClass());
        metaData.setAliasnameWithField(aliasnameWithField);
        metaData.setAliasName(beforePath);
        metaData.setUnique(isUnique(field));
        metaData.setField(field);
        metaData.setParentField(parentField);
        metaData.setAnnotationType(annotationType);
        metaData.setLikeValue(like);
        metaData.setDefaultValue(getValue(field, entity));
        metaData.setPath(path);
        metaData.setMethodName(methodName);
        metaData.setClassPath(classPath);
        metaData.setIndex(index);
        metaData.setType(field.getType());
        metaData.setModel(isModel);
        metaData.setLeftJoin(isLeftJoin);
        metaData.setTableName(tableName);
        metaData.setColumnName(columnName);


//        if (parentMetaData.isDeleteOraphanRemoval()) {
//            metaData.setDeleteOraphanRemoval(isOrphanRemoval(baseEntity, field));
//        } else {
//            metaData.setDeleteOraphanRemoval(false);
//        }


        Map<String, Annotation> parenTFieldMap = getAnnotation(parentField);
        Map<String, Annotation> fieldMap = getAnnotation(field);

        if (parenTFieldMap.get("OneToOne") != null) {
            metaData.setAssosiation("OneToOne");
        } else if (parenTFieldMap.get("OneToMany") != null) {
            metaData.setAssosiation("OneToMany");
        } else if (parenTFieldMap.get("ManyToOne") != null) {
            metaData.setAssosiation("ManyToOne");
        } else if (parenTFieldMap.get("ManyToMany") != null) {
            metaData.setAssosiation("ManyToMany");
        } else if (parenTFieldMap.get("Embedded") != null) {
            metaData.setAssosiation("Embedded");
        } else if (parenTFieldMap.get("EmbeddedId") != null) {
            metaData.setAssosiation("EmbeddedId");
        }

        metaData.setUp(parentMetaData.isUp());

        if (isModel) {
            Relation relation = getAssosiationDetail(metaData);
            if (relation != null) {
                relation.setEnabled(!metaData.isUp());
            }
            metaData.setRelation(relation);
        }

        boolean isEmbeddable = isEmbeddable(field.getType());
        boolean isEmbedded = (annotationType.indexOf("Embedded") > -1);
        MetaData saveParentMetaData = parentMetaData;
        if (isEmbedded && !isEmbeddable) {
            parentMetaData = parentMetaData.getParent();
        }

        if (parentMetaData != null) {
            if (!isEmbeddable) {
                parentMetaData.addChild(metaData);
                //  for put aliasNameMap
                if (isList || isModel) {
                    if (aliasNameMap.get(path) == null) {
                        if (!isList) {
                            aliasNameMap.put(path, field.getType().getSimpleName() + index);
                        } else {
                            aliasNameMap.put(path, model.getSimpleName() + index);
                        }
                    }
                }
            }
            metaData.setParent(parentMetaData);
        }

        if (isEmbedded) {
            if (isFirstField && isLastField) {
                metaData.setPosition("first,last");
            } else if (isFirstField) {
                metaData.setPosition("first");
            } else if (isLastField) {
                metaData.setPosition("last");
            }

            if (isLastField) {
                saveParentMetaData.setParent(null);
            }
        }
        metaData.setList(isList);

        if (metaData.isList()) {
            metaData.setSubClassList(true);
        } else if (metaData.getParent() != null) {
            metaData.setSubClassList(metaData.getParent().isSubClassList());
        }

        return metaData;
    }


    private static boolean isLoop(String path) {
        return isLoop(path, false);
    }

    private static boolean isLoop(String path, boolean isDelete) {

        if (path == null || path.equals("") || path.indexOf("|") == -1) {
            return false;
        }
        String paths[] = path.split("\\|");

        Map<String, String> map = new HashMap<String, String>();

        for (int i = 0; i < paths.length; i++) {

            String pathWithoutList = paths[i];

            if (isDelete) {
                if (pathWithoutList.indexOf("java.util.List") > -1) {
                    pathWithoutList = pathWithoutList.substring(15, pathWithoutList.length() - 1);
                }
            }

            if (map.get(pathWithoutList) != null) {
                return true;
            }
            map.put(pathWithoutList, "");
        }

        return false;

    }

    private static String createSaveMethod(String keyExistsMap, Object entity, DependencyInjectionType diType) {

        int last = keyExistsMap.lastIndexOf("+ \"-\" +");
        if (last > -1) {
            keyExistsMap = keyExistsMap.substring(0, last - 1) + " + \"\";";
        }

        String className = entity.getClass().getName();
        String methodName = "getByFilter";//+ entity.getClass().getSimpleName().replaceAll("\\.", "");

        String flush = "entityManager.flush();\n";

        if (diType.equals(DependencyInjectionType.GUICE) || diType.equals(DependencyInjectionType.NONE)) {
            flush = "//" + flush;
        }

        String save = "public static java.util.List save(EntityManager entityManager, java.util.List models) {\n" +
                "\n" +
                "        if (models == null || models.size() == 0) {\n" +
                "            return null;\n" +
                "        }\n" +
                "\n" +
                "        java.util.List afterInsert = new java.util.ArrayList();\n" +
                "\n" +
                "        java.util.Map existMap = new java.util.HashMap();\n" +
                "        java.util.List fetch = " + methodName + "(entityManager, models, false, 0, 100000, null, null, null, null, true, \"AND\", FetchType.LAZY, null , false , \"\");\n" +
                "\n" +
                "        if (fetch != null && fetch.size() > 0) {\n" +
                "            java.util.Iterator fetchIterator = fetch.iterator();\n" +
                "            while (fetchIterator.hasNext()) {\n" +
                "\n" +
                "                " + className + " model = (" + className + ") fetchIterator.next();\n" +
                "\n" +
                "                if (model != null) {\n" +
                "\n" + keyExistsMap +
                "                        existMap.put(key, model);\n" +
                "                }\n" +
                "\n" +
                "\n" +
                "            }\n" +
                "\n" +
                "        }\n" +
                "\n" +
                "\n" +
                "        java.util.Iterator iterator = models.iterator();\n" +
                "\n" +
                "        while (iterator.hasNext()) {\n" +
                "            " + className + " model = (" + className + ") iterator.next();\n" +
                "\n" +
                "            if (model != null) {\n" +
                keyExistsMap +
                "                    " + className + " fetchModel = (" + className + ") existMap.get(key);\n" +
                "\n" +
                "                    if (fetchModel == null) {// is not exist\n" +
                "                        entityManager.persist(model);\n" +
                flush + "\n" +
                "                    } else {\n" +
                "                        model = entityManager.merge(model);\n" +
                "                    }\n" +
                "\n" +
                "                    afterInsert.add(model);\n" +

                "            }\n" +
                "\n" +
                "        }\n" +
                "\n" +
                "        return afterInsert;\n" +
                "    }";

        return save;
    }

    public static String createOnSQL(String parentTableAliasName, String childTableAliasName, Map<String, String> map) {

        if (map == null) {
            return "";
        }

        String on = " ON( ";
        Iterator iterator = map.keySet().iterator();

        while (iterator.hasNext()) {
            String key = (String) iterator.next();
            String value = map.get(key);
            on += (childTableAliasName + "." + key + " = " + parentTableAliasName + "." + value);
            if (iterator.hasNext()) {
                on += " AND ";
            }

        }

        on += " ) ";
        return on;
    }

    public static String createLeftJoinSQL(String tableName, String tableAliasName, Map<String, String> aliasMap, List<String> select) {

        if (aliasMap.get(tableAliasName) != null) {
            return "";
        }
        String sql = "";
        if (aliasMap.get(tableAliasName) == null) {
            aliasMap.put(tableAliasName, tableName);
            if (select.size() == 0) {
                sql += (" FROM " + tableName + " " + tableAliasName);
            } else {
                sql += (" LEFT JOIN " + tableName + " " + tableAliasName);
            }
            select.add(tableAliasName);
        }

        return sql;
    }

    public static void setTableAliasName(MetaData metaData) {

        Relation relation = metaData.getRelation();
        if (relation == null) {
            return;
        }

        String parentClassName = tableNames.get(relation.getParentTableName());
        String childClassName = tableNames.get(relation.getChildTableName());

        String alias1 = metaData.getAliasName();
        String alias2 = metaData.getAliasnameWithField();
        int index = alias2.indexOf(".");

        if (index > -1) {
            alias2 = alias2.substring(0, index);
        }


        int parentLike = alias1.indexOf(parentClassName);
        int childLike = alias1.indexOf(childClassName);

        if (parentLike > -1 && childLike > -1) {
            if (parentClassName.length() > childClassName.length()) {
                childLike = -1;
            } else {
                parentLike = -1;
            }
        }

        if (parentLike > -1) {
            relation.setParentTableAliasName(alias1);
            relation.setChildTableAliasName(alias2);
        } else {
            relation.setParentTableAliasName(alias2);
            relation.setChildTableAliasName(alias1);
        }

    }

    public static String createDeleteQueryForOrphanRemoval(List<MetaData> metaDataList) {

        //String x = "DELETE EMP_PROJ,Employee1,Project2 FROM EMP_PROJ EMP_PROJ LEFT JOIN Employee Employee1 ON(EMP_PROJ.EMP_ID = Employee1.ID)  LEFT JOIN Project Project2 ON(EMP_PROJ.PROJ_ID = Project2.ID) WHERE 1=1 ";

        String sql = "";
        if (metaDataList == null) {
            return "";
        }

        int size = metaDataList.size();

        if (size == 0) {
            return "";
        }

        Map<String, String> aliasMap = new HashMap<String, String>();
        List<String> select = new ArrayList<String>();
        String type = "";
        ManyToMany manyToMany = null;
        OneORMany oneORMany = null;
        Relation relation = null;

        if (size > 1) {
            //for (int i = size - 1; i > -1; i--) {
            for (int i = 0; i < size; i++) {

                relation = metaDataList.get(i).getRelation();
                manyToMany = null;
                oneORMany = null;

                if (relation == null && !metaDataList.get(i + 1).getRelation().getType().equals("ManyToMany")) {
                    sql += (" FROM " + metaDataList.get(i).getTableName() + " " + metaDataList.get(i).getAliasName());
                    aliasMap.put(metaDataList.get(i).getAliasName(), metaDataList.get(i).getTableName());
                    select.add(metaDataList.get(i).getAliasName());
                } else if (relation != null && relation.isEnabled()) {

                    setTableAliasName(metaDataList.get(i));
                    type = relation.getType();

                    if (type.equals("ManyToMany")) {
                        manyToMany = (ManyToMany) relation;
                    } else {
                        oneORMany = (OneORMany) relation;
                    }

                    if (manyToMany != null) {
                        sql += createLeftJoinSQL(manyToMany.getMiddleTableName(), manyToMany.getMiddleTableName(), aliasMap, select);
                    }

                    sql += createLeftJoinSQL(relation.getChildTableName(), relation.getChildTableAliasName(), aliasMap, select);

                    if (manyToMany != null) {
                        sql += createOnSQL(relation.getChildTableAliasName(), manyToMany.getMiddleTableName(), manyToMany.getJoinColumns().getNames());
                    }


                    sql += createLeftJoinSQL(relation.getParentTableName(), relation.getParentTableAliasName(), aliasMap, select);


                    if (oneORMany != null) {
                        sql += createOnSQL(relation.getParentTableAliasName(), relation.getChildTableAliasName(), oneORMany.getJoinColumnDetail().getNames());
                    } else if (manyToMany != null) {
                        sql += createOnSQL(relation.getParentTableAliasName(), manyToMany.getMiddleTableName(), manyToMany.getInverseJoinColumns().getNames());
                    }
                }

            }


            String selectString = "DELETE ";
            for (int i = 0; i < select.size(); i++) {
                selectString += select.get(i);
                if (i < select.size() - 1) {
                    selectString += ",";
                }
            }
            sql = selectString + sql;


        } else if (sql.equals("")) {
            sql = "DELETE " + metaDataList.get(0).getTableName() + " " + metaDataList.get(0).getAliasName();
        }

        sql = "\"" + sql + " WHERE 1=1 \";";
        return sql;
    }

    private static synchronized <M extends Object> void createFacadeMethod(M entity, String targetPackage, String targetPackagePath, DependencyInjectionType diType) {

        //targetPackagePath + "/Facade.java";
        index = 0;
        final Map<String, String> aliasNameMap = new HashMap<String, String>();
        Map<String, String> tableNameMap = new HashMap<String, String>();
        MetaData metaData = getFieldAndValue(entity.getClass().getName(), null, entity, null, 0, "", 0, "", true, null, false, false, aliasNameMap);
        walkTree(entity.getClass().getName(), entity, null, 1, "", 0, "", metaData, aliasNameMap);
        whereClause = "";
        basicSQLEager = "";
        basicSQLLazy = "";
        String basicDeleteEager = "";
        keyExistsMap = "";
        List<String> mapTablesForDelete = new ArrayList<String>();

        List<MetaData> metaDataList = new ArrayList<MetaData>();
        repairAliasName(metaDataList, metaData, aliasNameMap, -1);

        basicDeleteEager = createDeleteQueryForOrphanRemoval(metaDataList);


        String saveMethod = createSaveMethod(keyExistsMap, entity, diType);


        String method = ("public static java.util.List getByFilter( EntityManager entityManager , java.util.List models , boolean isFetch, int page, int size,String selectParam, Parameter jpql, QueryParameter queryParameter , Cast cast , boolean isExistRecord , java.lang.String orAnd , FetchType fetchType , final java.util.Map statement , boolean isDelete , String furthermore){\n\n" +
                "if(models == null || models.size() == 0){\n return null;\n}" +
                "\nif(isDelete){\nisExistRecord = false;\nisFetch = false;\nfetchType = FetchType.EAGER;\nselectParam = null;\ncast = null;\n}\n" +
                "\n\nif(page <= 0)page = 0;\nif(size <= 0) size = 100;" +
                "\nint counter = 0;\n" +
                "\nboolean isOpenParentheses = false;" +
                "\njava.lang.String andCondition = \" AND \";" +
                "\n boolean isChangeKey = false;\n" +
                "\njava.lang.String whereQuery = \"\";" +
                "\njava.lang.Object whereValue = null;" +
                "\njava.lang.String fetch = \"FETCH\";\n" +
                "\nboolean addWhereCondition = false;" +
                "\n" + entity.getClass().getName() + " model = null;" +
                "\nif(isFetch == false){\nfetch = \"\";\n}" +
                "\njava.util.ArrayList mapVariable = new java.util.ArrayList();" +
                "\njava.lang.String orAndId = \"AND\";" +
                "\njava.lang.String query =\"\";\n" +
                "if(fetchType == null){\nfetchType = FetchType.EAGER;\n}" +
                "\n java.lang.String basicQuery = \"\";" +
                "\njava.lang.String select = \"SELECT " + entity.getClass().getSimpleName() + "0\";" +
                "if(selectParam != null && !selectParam.equals(\"\") ){select = selectParam;}" +
                "\nif(!isExistRecord && !isDelete){\nif(fetchType == FetchType.LAZY){\nbasicQuery = select + " + basicSQLLazy + " WHERE 1=1 \";\n}\n" +
                "\nelse if(fetchType == FetchType.EAGER){\nbasicQuery = select + " + basicSQLEager + " WHERE 1=1 \";\n}\n}\n" +

                "else if(isExistRecord){\n basicQuery = \"SELECT " + entity.getClass().getSimpleName() + "0 FROM " + entity.getClass().getName() + " " + entity.getClass().getSimpleName() + "0 WHERE 1=1 \";\n}\n" +

                "else if(isDelete){\n if(fetchType == FetchType.LAZY){\nbasicQuery = \"DELETE FROM " + entity.getClass().getName() + " " + entity.getClass().getSimpleName() + "0 WHERE 1=1 \";\n}" +
                "else if(fetchType == FetchType.EAGER){\n basicQuery = " + basicDeleteEager + "\n}\n}\n" +

                "int modelsLength = models.size();\n" +
                "java.util.Iterator modelsIterator = models.iterator();" +
                "\n int j = 0;" +
                "if(modelsLength > 0){\n" +
                " while(modelsIterator.hasNext()){\n" +
                " model = (" + entity.getClass().getName() + ")modelsIterator.next();\n" +
                " query = \"\";\n" +
                "isOpenParentheses = true;\n" +
                whereClause +
                "if( !query.equals(\"\") ){\n" +
                "String postfix = \"\";\n" +
                "String prefix = \"\";\n" +
                "if(!addWhereCondition) {\naddWhereCondition = true;\nif(modelsLength > 1){\nprefix = \"(\";\n} basicQuery = ( basicQuery + \" AND ( \" + prefix + query + \" ) \" ); \n}" +
                "else { \npostfix = \"\";\nif(modelsLength > 1 && !modelsIterator.hasNext()){\n postfix = \")\"; \n}\n basicQuery = ( basicQuery + \" OR ( \" + query + postfix + \" ) \" ); \n}" +
                "\n}" +
                "isOpenParentheses = false;\n"
                + " j++;"
                + "}\n"  // end of for
                + "}\n" // end of if(modelsLength > 0)
                + "\n query = basicQuery;" +
                "javax.persistence.Query query1 = null;\n" +
                "if(isDelete && fetchType == FetchType.EAGER){\nquery1 = entityManager.createNativeQuery(query);\n}\n" +
                "\nelse{\n query1 = entityManager.createQuery(query + furthermore );\n}\n" +
                "if(!isDelete){\n query1.setFirstResult(page * size);\nquery1.setMaxResults(size);\n}\n" +
                "java.util.Iterator iterator = mapVariable.iterator();\n" +
                "\nif (iterator != null) {\n" +
                "while (iterator.hasNext()) {" +
                "\njava.lang.String variableName = (java.lang.String) iterator.next();" +
                "\njava.lang.Object counditionValue = iterator.next();" +
                "\nquery1.setParameter( variableName, counditionValue);" +
                "\n  " +
                "}\n}\n" +
                "\nif(queryParameter != null) {" +
                "\nqueryParameter.createCondition(query1);" +
                "\n}" +
                "java.util.List result = null;" +
                "\nif(!isDelete){\nresult = query1.getResultList();" +
                "\n if(cast != null && result != null && result.size() > 0 && (!select.trim().equals(\"" + entity.getClass().getSimpleName() + "0\") && select.indexOf(\"new\") == -1 ) ){\n" +
                "boolean isObject = true;\n" +
                "        if(result.get(0) instanceof Object[]){\n" +
                "            isObject = false;\n" +
                "        }\n" +
                " java.util.List resultCast = new java.util.ArrayList();" +
                "\nfor(int i = 0;i < result.size();i++){\n" +
                " Object[] record;\n" +
                "                if(!isObject){\n" +
                "                    record = (Object[])result.get(i);\n" +
                "                } else{\n" +
                "                    record = new Object[]{result.get(i)};\n" +
                "                }\n" +
                "\n" +
                "                java.lang.Object convert = (java.lang.Object) cast.to(record);" +
                "if(convert != null){\n" +
                "resultCast.add( convert );" +
                "\n}\n" +
                "\n  }" +
                "\n result = resultCast;" +
                "\n}\n" +
                // "return result;" +
                "\n\n}\nif(isDelete){\nquery1.executeUpdate();\n}" +
                "                if (jpql != null) {\n" +
                "                    java.util.Set set = query1.getParameters();\n" +
                "                    java.util.Iterator iterator1 = set.iterator();\n" +
                "                    String variables = \"\";\n" +
                "\n" +
                "                    if (iterator1.hasNext()) {\n" +
                "                        variables = \"\\n\\n************ Variables ************\\n\";\n" +
                "                        while (iterator1.hasNext()) {\n" +
                "                            javax.persistence.Parameter key = (javax.persistence.Parameter) iterator1.next();\n" +
                "\n" +
                "                            Object name = key.getPosition();\n" +
                "                            Object value = null;\n" +
                "                            if (name == null) {\n" +
                "                                name = key.getName();\n" +
                "                            }\n" +
                "\n" +
                "                            try {\n" +
                "                                value = query1.getParameterValue(key);\n" +
                "                            } catch (IllegalStateException e) {\n" +
                //"                                System.out.println(e.getClass());\n" +
                "                            }\n" +
                "\n" +
                "                            if (key.getParameterType().equals(java.lang.String.class)) {\n" +
                "                                variables += (name + \" : '\" + value + \"'\\n\");\n" +
                "                            } else {\n" +
                "                                variables += (name + \" : \" + value + \"\\n\");\n" +
                "                            }\n" +
                "\n" +
                "                        }\n" +
                "                        variables += \"************ Variables ************\";\n" +
                "                    }\n" +
                "                    jpql.get(query + furthermore + variables);\n" +
                "                }\n" +
                "\n" +
                "\nreturn result; }");


        String simpleName = entity.getClass().getSimpleName();
        String packageName = entity.getClass().getPackage().getName().replaceAll("\\.", "");
        String className = simpleName + packageName + "DAO";
        String classContent = "package " + targetPackage + ";"
                + "import com.jpql.api.interfaces.*;\n" +
                "import com.jpql.api.interfaces.Parameter;\n"
                + "import javax.persistence.FetchType;\n"
                + "import javax.persistence.EntityManager;\n"
                + "/**\n" +
                " * Created by kasra.haghpanah on 1/05/2019.\n" +
                " */\n\n\n"
                + "public class " + className + " {\n\n" + saveMethod + method + "\n\n}";

        classContent = reformatSourceCode(classContent);
        String path = targetPackagePath + "/" + className + ".java";

        FileUtility.writeTextFile(path, classContent);
        javaListFiles.add(path);
        System.out.println("Generate: " + path);
        javaListFiles.add(path);

    }

    private static synchronized <M extends Object> void createFacadeClass(String pathPersistence, String targetPackage, String targetPackagePath, DependencyInjectionType diType) {

        List<String> models = getPersistenceModelList(pathPersistence);
        if (models == null) {
            return;
        }

        for (int i = 0; i < models.size(); i++) {
            Class clazz = null;

            try {
                clazz = forName(models.get(i));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            tableNames.put(getSqlTableName(clazz), clazz.getSimpleName());
        }

        Class aClass = null;
        Object model = null;
        String getByFilterMethod = "public static java.util.List getByFilter(EntityManager entityManager , java.util.List models, boolean isFetch, int page, int size, String selectParam , Parameter jpql, QueryParameter queryCondition, Cast cast , boolean isExistRecord, String orAnd , FetchType fetchType, final java.util.Map statement , boolean isDelete , String furthermore) {\n\nif(models == null || models.size() == 0){\nreturn null;\n}\njava.util.Iterator iterator = models.iterator();\nObject node = iterator.next();\nif(false){}\n";
        String save = "public static java.util.List save(EntityManager entityManager, java.util.List models) {\nif (models == null || models.size() == 0) {\nreturn null;\n}\njava.util.Iterator iterator = models.iterator();\nObject node = iterator.next();\nif(false){}";

        String modelName = "";

        String simpleName;
        String packageName;
        String className;

        for (int i = 0; i < models.size(); i++) {
            try {
                aClass = forName(models.get(i));
                model = (Object) aClass.newInstance();
                modelName = aClass.getSimpleName().replaceAll("\\.", "");

                simpleName = model.getClass().getSimpleName();
                packageName = model.getClass().getPackage().getName().replaceAll("\\.", "");
                className = simpleName + packageName + "DAO";

                createFacadeMethod(model, targetPackage, targetPackagePath, diType);
                getByFilterMethod += (
                        "\nelse if(node instanceof " + aClass.getName() + "){\n"
                                + "return " + className + ".getByFilter(entityManager , models , isFetch, page, size, selectParam, jpql , queryCondition , cast , isExistRecord , orAnd , fetchType , statement , isDelete , furthermore);\n}"
                );

                save += ("\nelse if(node instanceof " + aClass.getName() + "){\n"
                        + "return " + className + ".save(entityManager , models);\n}"
                );

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
        getByFilterMethod += ("return null;\n}\n");
        save += ("return null;\n}\n");


        className = "Facade";
        String classContent = "package " + targetPackage + ";" +
                "import com.jpql.api.interfaces.*;\n" +
                "import com.jpql.api.interfaces.Parameter;\n" +
                "import javax.persistence.FetchType;\n" +
                "import javax.persistence.EntityManager;\n" +
                "import javax.persistence.Query;\n" +
                "import java.util.Iterator;\n" +
                "import java.util.Set;\n" +
                "/**\n" +
                " * Created by kasra.haghpanah on 1/05/2019.\n" +
                " */\n\n\n"
                + "public class " + className + " {\n\n" +
                "public static Long getTime(java.util.Date date){if(date == null){return null;}return date.getTime();}" +
                save + getByFilterMethod +
                "\n    public static String getStatement(\n" +
                "      final java.util.Map<String, String> map,\n" +
                "      String key,\n" +
                "      String variable,\n" +
                "      final java.util.List mapVariable,\n" +
                "      String whereQuery,\n" +
                "      Object whereValue,\n" +
                "      boolean isExistRecord,boolean isDelete ," +
                "      FetchType fetchType ," +
                "      int counter" +
                "      ) {\n" +
                "    \n" +
                "    if (isDelete && fetchType == FetchType.EAGER) {\n" +
                "      mapVariable.add(counter + \"\");\n" +
                "      mapVariable.add(whereValue);\n" +
                "      return whereQuery;\n" +
                "    }\n" +
                "    if (isExistRecord && !isDelete) {\n" +
                "      mapVariable.add(variable);\n" +
                "      mapVariable.add(whereValue);\n" +
                "      return whereQuery;\n" +
                "    }\n" +
                "\n" +
                "    if (map == null || map.keySet().size() == 0) {\n" +
                "      mapVariable.add(variable);\n" +
                "      mapVariable.add(whereValue);\n" +
                "      return whereQuery;\n" +
                "    }\n" +
                "\n" +
                "    String query = map.get(key);\n" +
                "    if (query == null) {\n" +
                "      mapVariable.add(variable);\n" +
                "      mapVariable.add(whereValue);\n" +
                "      return whereQuery;\n" +
                "    }\n" +
                "\n" +
                "    if (query.indexOf(\"?\") > -1) {\n" +
                "      query = query.replaceAll(\"\\\\?\", \" :\" +variable);\n" +
                "      mapVariable.add(variable);\n" +
                "      mapVariable.add(whereValue);\n" +
                "    }\n" +
                "\n" +
                "    return query;\n" +
                "  }\n" +
                "\n\n}";


        String path = targetPackagePath + "/" + className + ".java";

        classContent = reformatSourceCode(classContent);
        FileUtility.writeTextFile(path, classContent);
        System.out.println("Generate: " + path);
        javaListFiles.add(path);

    }

    private static void beforeCondition(MetaData metaData, String withoutDott, int listIndex) {
        if (metaData.getParent() != null && metaData.isSubClassList()) {
            // todo beforeCondition

            String whereConditionEqual = metaData.getAliasnameWithField() + " = :" + withoutDott;
            String whereConditionEqualForEagerDelete = metaData.getAliasName() + "." + metaData.getColumnName() + " = ?";

            String likeValue = metaData.getLikeValue();

            if (!likeValue.equals("")) {
                whereConditionEqual = metaData.getAliasnameWithField() + " LIKE :" + withoutDott;
                if (likeValue.equals("AFTER")) {
                    whereClause += "\nwhereValue = " + metaData.getMethodName() + " + \"%\";\n";
                } else if (likeValue.equals("BEFORE")) {
                    whereClause += ("\nwhereValue = " + " \"%\" + " + metaData.getMethodName() + ";\n");
                } else {
                    whereClause += ("\nwhereValue =  \"%\" + " + metaData.getMethodName() + " + \"%\";\n");
                }
            } else {
                whereClause += "whereValue = " + createVariabeForMapVariable(metaData.getMethodName(), metaData.getType()) + ";";
            }

            whereClause += ("if(isDelete && fetchType == FetchType.EAGER){\nwhereQuery =  \" " + whereConditionEqualForEagerDelete + " \";\ncounter++;\n}\n");
            whereClause += ("\nelse{\n whereQuery =  \" " + whereConditionEqual + "\" + j + \" \";\n}\n");
            whereClause += "\nisOpenParentheses = false;";
        }
    }


    private static void createKeyForSaveAll(MetaData metaData) {

        if (metaData != null && metaData.getParent() != null && metaData.getParent().getParent() == null) {
            if (keyExistsMap.equals("")) {
                keyExistsMap = "String key = ";
            }
            String annotationType = metaData.getAnnotationType();
            if (annotationType.equals("Id") || annotationType.equals("EmbeddedId")) {
                String methodName = metaData.getMethodName();
                if (metaData.getType().equals(Date.class)) {
                    methodName = ("Facade.getTime(" + methodName + ")");
                }
                keyExistsMap += (methodName + " + \"-\" + ");
            }

        }

    }

    private static String createVariabeForMapVariable(String methodName, Class type) {

        String value = methodName;
        String typeField = type.getName();
        if (typeField.equals("int")) {
            return "new java.lang.Integer( \"\" + " + methodName + ")";
        } else if (typeField.equals("long")) {
            return "new java.lang.Long( \"\" + " + methodName + ")";
        } else if (typeField.equals("double")) {
            return "new java.lang.Double( \"\" + " + methodName + ")";
        } else if (typeField.equals("float")) {
            return "new java.lang.Float( \"\" + " + methodName + ")";
        } else if (typeField.equals("short")) {
            return "new java.lang.Short( \"\" + " + methodName + ")";
        } else if (typeField.equals("byte")) {
            return "new java.lang.Byte( \"\" + " + methodName + ")";
        } else if (typeField.equals("boolean")) {
            return "new java.lang.Boolean( \"\" + " + methodName + ")";
        } else if (typeField.equals("char")) {
            return "new java.lang.Character( \"\" + " + methodName + ")";
        }
        return value;

    }

    private static synchronized void repairAliasName(final List<MetaData> basicDeleteEagerList, final MetaData metaData, final Map<String, String> aliasNameMap, int listIndex) {

        String aliasnameWithField = metaData.getAliasnameWithField();
        int lastIndexOf = aliasnameWithField.lastIndexOf(".");
        String aliasname = "";

        if (lastIndexOf > -1) {

            if (metaData.getAnnotationType().indexOf("Embedded") > -1) {
                lastIndexOf = aliasnameWithField.lastIndexOf(".", lastIndexOf - 1);
            }

            if (!metaData.isModel()) {
                aliasname = aliasNameMap.get(aliasnameWithField.substring(0, lastIndexOf));
                aliasnameWithField = aliasname + aliasnameWithField.substring(lastIndexOf, aliasnameWithField.length());
            } else {
                aliasname = aliasNameMap.get(aliasnameWithField);
                aliasnameWithField = aliasNameMap.get(aliasnameWithField.substring(0, lastIndexOf)) + aliasnameWithField.substring(lastIndexOf);
            }

        } else {
            aliasname = aliasnameWithField + metaData.getIndex();
            aliasnameWithField = aliasname;
        }

        metaData.setAliasName(aliasname);
        metaData.setAliasnameWithField(aliasnameWithField);
        int length = metaData.getChilds().size();

        if (metaData.isModel()) {

            if (metaData.getDeep() == 0) {
                basicSQLEager = "\" FROM " + metaData.getModel().getName() + " " + metaData.getAliasName() + " ";
                basicSQLLazy = basicSQLEager;
            } else {
                String left = "";
                if (metaData.isLeftJoin()) {
                    left = " LEFT";
                }
                basicSQLEager = basicSQLEager + left + " JOIN \"\n + fetch + \n\" " + aliasnameWithField + " " + aliasname;
                if (!metaData.isSubClassList()) {
                    basicSQLLazy = basicSQLLazy + left + " JOIN \"\n + fetch + \n\" " + aliasnameWithField + " " + aliasname;
                }

            }

            // for DELETE
            if (!isLoop(metaData.getClassPath(), true)) {
                basicDeleteEagerList.add(metaData);
            }
            // for DELETE

        }

        //////////////////////////////////////////////////////////////////
        String isExistRecordStatus = " !isExistRecord && ";

        if (metaData.getParent() == null || metaData.getPosition().toLowerCase().indexOf("first") > -1) {
            isExistRecordStatus = "";
        } else if (
                (metaData.getAnnotationType().equals("Id") || metaData.getAnnotationType().equals("EmbeddedId"))
                        &&
                        metaData.getParent() != null
                        &&
                        metaData.getParent().getParent() == null
                ) {
            isExistRecordStatus = " true && ";
        }


        if (metaData.isModel()) {

            String isBaseModelCondition = isExistRecordStatus + "!isDelete && ";

            if (metaData.getParent() == null) {
                isBaseModelCondition = isExistRecordStatus;
            }

            if (!metaData.isList()) {
                whereClause += ("\nif(" + isBaseModelCondition + metaData.getMethodName() + " != null){\n\n");
            } else {

                //todo oneToMany
                whereClause += "\n if(" + isBaseModelCondition + "fetchType == FetchType.EAGER &&" + metaData.getMethodName() + isNotNull(metaData.getType()) + "){\n";//---
                String iIndex = "i" + metaData.getIndex();
                String iLength = "length" + metaData.getIndex();
                whereClause += "int " + iLength + " = " + metaData.getMethodName() + ".size();\n";
                whereClause += " \nif(" + iLength + " > 0){\n";
                //todo
                whereClause += " if(isOpenParentheses){\nquery += \"  ( \";\n}\n else{\nquery += \" AND ( \";\n}";
                whereClause += "\n isOpenParentheses = true;\n";
                //todo
                whereClause += "\nfor( int " + iIndex + " = 0;" + iIndex + " < " + iLength + ";" + iIndex + "++){\n";//-----
                whereClause += ("\njava.lang.String beforeCondition" + listIndex + " = \"\";\n");
                whereClause += "java.lang.String prefix" + metaData.getIndex() + " = \" OR \";\n ";
                whereClause += " if( i" + metaData.getIndex() + " == 0 ){\n " + "prefix" + metaData.getIndex() + " = \"\";\n}\n ";
                whereClause += " if( " + iLength + " > 1){\n";
                whereClause += "\n query += (prefix" + metaData.getIndex() + " + \"(\" );";
                whereClause += "\n isOpenParentheses = true;\n";//--
                whereClause += " \n}\n";
                whereClause += " else{\n";
                whereClause += "\n query += (prefix" + metaData.getIndex() + " );";
                //todo important
                whereClause += ("if(!prefix" + metaData.getIndex() + ".trim().equals(\"\") ){");
                whereClause += "\n isOpenParentheses = false;\n}\n";
                whereClause += " \n}\n";

            }

        } else if (!metaData.isModel()) {
            createKeyForSaveAll(metaData);
            String withoutDott = metaData.getAliasnameWithField().replaceAll("\\.", "");
            String isNoTNull = "";
            boolean isBoolean = metaData.getType().getName().equals("boolean");
            if (!isBoolean) {
                isNoTNull = isNotNull(metaData.getType());
            }


            String condition = "";
            String and = " && ";
            boolean isBaseField = false;

            if (metaData.getParent() != null && metaData.getParent().getParent() == null) {
                isBaseField = true;
            }

            String isNOTBaseFieldCondition = isExistRecordStatus;
            ;
            if (!isBaseField) {
                isNOTBaseFieldCondition = isExistRecordStatus + "!isDelete && ";
            }

            if (!metaData.getAnnotationType().equals("Id") && !metaData.getAnnotationType().equals("EmbeddedId")) {
                condition = isNOTBaseFieldCondition + " true ";//+ "isExistRecord == false";
            } else {
                if (metaData.getAnnotationType().equals("Id")) {
                    whereClause += setNumberMinusOne(metaData);
                }
                and = "";
            }

            String setCondition = "andCondition = \" AND \";\n" + "if (isOpenParentheses) {andCondition = \" \";}";

            if (!isBoolean) {
                if (metaData.getAnnotationType().indexOf("Embedded") > -1) {
                    if (metaData.getPosition().indexOf("first") > -1) {
                        whereClause += ("if(" + isNOTBaseFieldCondition + metaData.getMethodName().substring(0, metaData.getMethodName().lastIndexOf(".")) + " != null ){");
                        if (metaData.getAnnotationType().equals("EmbeddedId")) {
                            whereClause += setNumberMinusOne(metaData);
                        }
                    }
                }
                condition += (and + metaData.getMethodName() + " " + isNoTNull);

            } else {
                if (condition.equals("")) {
                    condition = "true";
                }
            }
            whereClause += ("\nif(" + condition + "){\n\n ");
            //todo
            whereClause += setCondition;
            whereClause += "query += \" \";\n";
            String likeValue = metaData.getLikeValue();

            String whereConditionEqual = metaData.getAliasnameWithField() + " = :" + withoutDott;
            String whereConditionLike = metaData.getAliasnameWithField() + " LIKE :" + withoutDott;
            String whereConditionEqualForEagerDelete = metaData.getAliasName() + "." + metaData.getColumnName() + " = ?";

            boolean isBeforeCondition = false;

            if (metaData.isUnique()) {


                if (likeValue.equals("")) {
                    if (metaData.getParent() != null && metaData.isSubClassList()) {
                        // todo beforeCondition
                        beforeCondition(metaData, withoutDott, listIndex);
                        isBeforeCondition = true;
                    } else {
                        whereClause += ("if(isDelete && fetchType == FetchType.EAGER){\nwhereQuery =  \" " + whereConditionEqualForEagerDelete + " \";\ncounter++;\n}\n");
                        whereClause += ("\nelse{\nwhereQuery =  \" " + whereConditionEqual + "\" + j + \" \";\n}\n");
                    }


                } else {
                    if (metaData.getParent() != null && metaData.isSubClassList()) {
                        // todo beforeCondition
                        beforeCondition(metaData, withoutDott, listIndex);
                        isBeforeCondition = true;
                    } else {


                        whereClause += ("if(isExistRecord || isDelete){\n" +
                                ("if(isDelete && fetchType == FetchType.EAGER){\nwhereQuery =  \" " + whereConditionEqualForEagerDelete + " \";\ncounter++;\n}\n") +
                                ("\nelse{\nwhereQuery =  \" " + whereConditionEqual + "\" + j + \" \";\n") + "\n}\n}\n");
                        //"query += (( (isExistRecord == true && " +
                        //metaData.getMethodName() + " " + isNoTNull
                        //+ " && mapVariable.size() > 0) ? \" OR \" : orAndId) + \" " +
                        //+ " && mapVariable.size() > 0) ? \" AND \" : orAndId) + \" " +
                        //"query += ( \" AND " + metaData.getAliasnameWithField() + " = :" + withoutDott + "\");\nisOpenParentheses = false;}\n");
                        //whereClause += ("\nisOpenParentheses = false;\n");
                        whereClause += (" else{ \n" + "\nwhereQuery =  \" " + whereConditionLike + "\" + j + \" \";\n" + "\n}\n");
                    }
                }

            } else {///is not unique
                if (likeValue.equals("")) {

                    if (metaData.getParent() != null && metaData.isSubClassList()) {
                        // todo beforeCondition
                        beforeCondition(metaData, withoutDott, listIndex);
                        isBeforeCondition = true;
                    } else {
                        whereClause += ("if(isDelete && fetchType == FetchType.EAGER){\nwhereQuery =  \" " + whereConditionEqualForEagerDelete + " \";\ncounter++;\n}\n");
                        whereClause += ("\nelse{\nwhereQuery =  \" " + whereConditionEqual + "\" + j + \" \";\n}\n");
                    }

                } else {
                    if (metaData.getAnnotationType().equals("Id") || metaData.getAnnotationType().equals("EmbeddedId")) {
                        if (metaData.getParent() != null && metaData.isSubClassList()) {
                            // todo beforeCondition
                            beforeCondition(metaData, withoutDott, listIndex);
                            isBeforeCondition = true;
                        } else {
                            whereClause += ("if(isExistRecord || isDelete){\n" +
                                    ("if(isDelete && fetchType == FetchType.EAGER){\nwhereQuery =  \" " + whereConditionEqualForEagerDelete + " \";\ncounter++;\n}\n") +
                                    "\nelse{\nwhereQuery =  \" " + whereConditionEqual + "\" + j + \" \";\n}\n" +
                                    "}\n");
                            whereClause += ("else{\n" + "\nwhereQuery =  \" " + whereConditionLike + "\" + j + \" \";\n" + "}\n");
                        }
                    } else {
                        if (metaData.getParent() != null && metaData.isSubClassList()) {
                            // todo beforeCondition
                            beforeCondition(metaData, withoutDott, listIndex);
                            isBeforeCondition = true;
                        } else {
                            whereClause += ("whereQuery =  \"" + whereConditionLike + "\" + j + \" \";\n");
                        }
                    }
                }
            }

            if (!isBeforeCondition) {
                whereClause += "\nisOpenParentheses = false;";
            }

            if (likeValue.equals("")) {
                whereClause += "\nwhereValue = " + createVariabeForMapVariable(metaData.getMethodName(), metaData.getType()) + ";";
            } else {
                whereClause += "if(isExistRecord || isDelete){\n" +
                        ("if(isDelete && fetchType == FetchType.EAGER){\nwhereQuery =  \" " + whereConditionEqualForEagerDelete + " \";\ncounter++;\n}\n") +
                        "\nelse{\nwhereValue = " + metaData.getMethodName() + ";\n}\n" +
                        "}\n";
                if (likeValue.equals("BEFORE")) {
                    whereClause += "else{\nwhereValue = " + " \"%\" + " + metaData.getMethodName() + ";\n}\n";
                } else if (likeValue.equals("AFTER")) {
                    whereClause += "else{\nwhereValue = " + metaData.getMethodName() + " + \"%\";\n}\n";
                } else {
                    whereClause += "else{\nwhereValue =  \"%\" + " + metaData.getMethodName() + " + \"%\";}\n";
                }
            }

            /////todo oooooooooo
            if (metaData.getParent() != null && metaData.getParent().getParent() == null) {
                if (metaData.getAnnotationType().equals("Id") || metaData.getAnnotationType().equals("EmbeddedId")) {
                    whereClause += setDefaultNullValue(metaData);
                }
            }
            whereClause += "\nquery += (andCondition + Facade.getStatement(statement , \"" + metaData.getAliasnameWithField() + "\" , \"" + withoutDott + "\" + j , mapVariable,whereQuery,whereValue,isExistRecord,isDelete,fetchType,counter) );\nwhereQuery = \"\";";
            whereClause += " \n\n}";


        }

        //////////////////////////////////////////////////////////////////
        for (int i = 0; i < length; i++) {
            if (metaData.getChilds().size() > 0) {
                MetaData child = metaData.getChilds().get(i);

                if (child.isList()) {
                    repairAliasName(basicDeleteEagerList, metaData.getChilds().get(i), aliasNameMap, child.getIndex());
                } else {
                    repairAliasName(basicDeleteEagerList, metaData.getChilds().get(i), aliasNameMap, listIndex);
                }
            }

        }
        /////////////////////////////////////////////////////////////////

        if (metaData.isModel()) {

            if (!metaData.isList()) {
                whereClause += ("\n\n}");
            } else {
                //todo onetomany
                ///**&&&&&&&&&&&&&&&&
                String iLength = "length" + metaData.getIndex();
                whereClause += " if( " + iLength + " > 1){\nquery += \" ) \";\n \n}\n";
                whereClause += "\n}\n"; // out of for
                whereClause += "\n query += \")\";\n isOpenParentheses = false;\n";//--
                whereClause += "\n}\n"; // end of if (list.size() > 0 )
                whereClause += "\n}\n";  // end of if( !=null)
            }

        } else {
            if (metaData.getPosition().indexOf("last") > -1) {
                whereClause += " \n\n}";
            }
        }


    }

    private static synchronized <M extends Object> void walkTree(String baseEntity, M entity, Field parentField, int deep, String path, int currentIndex, String classPath, MetaData parentMetaData, final Map<String, String> aliasNameMap) {

        if (entity == null) {
            return;
        }

        Field[] fields = entity.getClass().getDeclaredFields();
        if (path.equals("")) {
            path = entity.getClass().getSimpleName();
        }

        if (classPath.equals("")) {
            classPath = entity.getClass().getName();
        }

        for (int i = 0; i < fields.length; i++) {

            Field field = fields[i];
            String newPath = path;
            String newClassPath = classPath;

            boolean isList = List.class.isAssignableFrom(field.getType());
            boolean isModel = isModel(field.getType());


            if (isModel || isList) {
                M modelEntity = (M) getValue(field, entity);
                Map<String, Annotation> map = getAnnotation(field);
                Annotation manyToOne = map.get("ManyToOne");
                Annotation oneToOne = map.get("OneToOne");
                Annotation oneToMany = map.get("ManyToOne");
                Annotation embeddedId = map.get("EmbeddedId");
                Annotation embedded = map.get("Embedded");

                if (embeddedId == null && embedded == null) {
                    ++index;
                }

                newPath = path;
                if (!path.equals("")) {
                    newPath = path + "." + field.getName();
                }

                newClassPath = classPath;
                String signature = "";

                if (isList) {
                    signature = field.getGenericType() + "";
                    signature = signature.substring(signature.indexOf("<") + 1, signature.indexOf(">"));
                }

                if (!classPath.equals("")) {

                    if ((isModel || isList)) {
                        newClassPath = classPath + "|" + field.getType().getName();
                    }

                    if (!signature.equals("")) {
                        newClassPath = newClassPath + "<" + signature + ">";
                    }

                }

                boolean isLoop = isLoop(newClassPath);
                boolean isModelNotOneToMany = (manyToOne != null || oneToOne != null || oneToMany != null) || embeddedId != null || embedded != null;

                //todo ?????????????????

                if (isModelNotOneToMany || isList) {
                    MetaData fieldMetaData = null;
                    if (!isLoop) {
                        fieldMetaData = getFieldAndValue(baseEntity, field, entity, parentField, deep, newPath, index, newClassPath, true, parentMetaData, (i == 0), (i == fields.length - 1), aliasNameMap);
                    }

                    if (isList) {
                        Class signatureClass = null;
                        try {
                            signatureClass = forName(signature);

                            if (isModel(signatureClass) && !isLoop) {
                                walkTree(baseEntity, signatureClass.newInstance(), field, /*result,*/ deep + 1, newPath, index, newClassPath, fieldMetaData, aliasNameMap);
                            }

                            signatureClass.newInstance();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InstantiationException e) {
                            e.printStackTrace();
                        }

                    } else if (isModelNotOneToMany && !isLoop) {
                        walkTree(baseEntity, modelEntity, field, /*result,*/ deep + 1, newPath, index, newClassPath, fieldMetaData, aliasNameMap);
                    }
                }
            } else if (!isModel && !isList) {

                if (!field.getType().getName().equals("[B")) {
                    newPath = path;
                    MetaData fieldMetaData = getFieldAndValue(baseEntity, field, entity, parentField, deep, newPath, currentIndex, newClassPath, isModel, parentMetaData, (i == 0), (i == fields.length - 1), aliasNameMap);
                }
            }

        }

    }

    private static synchronized <M extends Object> Object getValue(Field field, M entity) {

        boolean isModel = isModel(field.getType()); //Model.class.isAssignableFrom(field.getType());

        if (entity == null && !isModel) {
            return entity;
        }

        if (entity == null && isModel) {
            try {
                entity = (M) Model.class.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }


        String methodName = getMethodName(field);
        Method method = null;
        try {
            method = entity.getClass().getDeclaredMethod(methodName, null);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return null;
        }
        Object o = null;
        try {
            o = (Object) method.invoke(entity, null);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        if (o == null && isModel) {
            try {
                o = forName(field.getType().getName()).newInstance();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }

        return o;
    }

    private static synchronized <M extends Object> String getMethodName(Field field) {
        String prefix = "get";
        if (field.getType().getName().equals("boolean")) {
            prefix = "is";
        }
        return prefix + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
    }

    private static synchronized String getAnnotationType(Field parentField, Field field) {

        if (field == null) return "";

        Map<String, Annotation> map = getAnnotation(field);
        Map<String, Annotation> parentMap = getAnnotation(parentField);


        if (map.get("Id") != null) {
            return "Id";
        } else if (map.get("ManyToOne") != null) {
            return "ManyToOne";
        } else if (map.get("OneToMany") != null) {
            return "OneToMany";
        } else if (map.get("OneToOne") != null) {
            return "OneToOne";
        } else if (map.get("ManyToMany") != null) {
            return "ManyToMany";
        } else if (map.get("EmbeddedId") != null) {
            return "EmbeddedId";
        } else if (parentField != null) {
            if (parentMap.get("EmbeddedId") != null) {
                return "EmbeddedId";
            } else if (parentMap.get("Embedded") != null) {
                return "Embedded";
            }
        }


        return "";
    }

    private static synchronized Boolean isUnique(Field field) {

        Map<String, Annotation> map = getAnnotation(field);

        Annotation column = map.get("Column");
        if (column == null) return false;
        Object isUnique = false;
        try {
            isUnique = column.annotationType().getMethod("unique").invoke(column);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        return new Boolean(isUnique.toString());
    }

    private static boolean isSameType(Class aClass, String type, boolean isInterface) {

        if (aClass == null) {
            return false;
        }
        Class[] interfaces = null;

        if (isInterface) {
            interfaces = aClass.getInterfaces();
        } else {

            if (aClass.getSuperclass() == null) {
                return false;
            }
            interfaces = new Class[]{aClass.getSuperclass()};
        }

        if (interfaces == null || interfaces.length == 0) {
            return false;
        }

        for (int i = 0; i < interfaces.length; i++) {

            if (interfaces[i].getName().equals(type)) {
                return true;
            }
        }

        return false;
    }

    private static boolean isModel(Class aClass) {
        return isSameType(aClass, "com.jpql.api.interfaces.Model", true);
    }

    private static boolean isNumber(Class aClass) {
        String className = aClass.getName();

        if (className.equals("int") || className.equals("long") || className.equals("double") || className.equals("float") || className.equals("short")) {
            return true;
        }

        return isSameType(aClass, "java.lang.Number", false);
    }

    private static String isNull(Class type) {
        return isOrNoTNull(type, "=", "0", false);
    }

    private static String isNotNull(Class type) {
        return isOrNoTNull(type, "!=", "0", false);
    }

    private static String isOrNoTNull(Class type, String status, String numberValue, boolean createValue) {

        String typeField = type.getName();
        String equal = status;
        String value = "null";

        if (typeField.equals("int")) {
            if (numberValue.equals("null")) {
                numberValue = "0";
            }
            value = numberValue;

        } else if (typeField.equals("long")) {
            if (numberValue.equals("null")) {
                numberValue = "0";
            }
            value = numberValue + "l";

        } else if (typeField.equals("double")) {
            if (numberValue.equals("null")) {
                numberValue = "0";
            }
            value = numberValue + "d";

        } else if (typeField.equals("float")) {
            if (numberValue.equals("null")) {
                numberValue = "0";
            }
            value = numberValue + "f";

        } else if (typeField.equals("short")) {
            if (numberValue.equals("null")) {
                numberValue = "0";
            }
            value = numberValue;
        } else if (typeField.equals("byte")) {
            value = numberValue;
        } else if (typeField.equals("boolean")) {
            value = "false";
        } else if (createValue && isNumber(type)) {
            value = " new " + type.getName() + "(\"" + numberValue + "\")";
        } else if (typeField.equals("char")) {
            value = "' '";
        }
        return " " + equal + " " + value;

    }


    private static String setNumberMinusOne(MetaData metaData) {
        return setDefaultValue(metaData, "-1");
    }

    private static String setDefaultNullValue(MetaData metaData) {
        return setDefaultValue(metaData, "null");
    }


    private static String setDefaultValue(MetaData metaData, String defaultValue) {

        Field field = metaData.getField();

        if (metaData == null || field == null) {
            return "";
        }

        String type = metaData.getType().getName();

        if (!metaData.isSubClassList() && metaData.getParent().getParent() == null && (type.equals("java.lang.String") || isNumber(metaData.getType()))) {

            Map<String, Annotation> map = getAnnotation(metaData.getField());
            if (map.get("GeneratedValue") == null) {
                //return "";
            }

            String isEqualZero = isOrNoTNull(metaData.getType(), " == ", "0", false);

            if (defaultValue.trim().equals("null")) {
                defaultValue = isOrNoTNull(metaData.getType(), " ", defaultValue /*"-1"*/, true);
                if (defaultValue.indexOf("null") > -1) {
                    defaultValue = "null";
                }
            }

            String minusOne = isOrNoTNull(metaData.getType(), " ", defaultValue /*"-1"*/, true);
            if (metaData.getType().getName().equals("java.lang.String") && defaultValue.trim().equals("-1")) {

                minusOne = "\"\"";
            }
            String methodName = metaData.getMethodName();
            int lastIndexOf = methodName.lastIndexOf(".get");
            if (lastIndexOf == -1) {
                lastIndexOf = methodName.lastIndexOf(".is");
            }
            String setter = methodName.substring(0, lastIndexOf) + ".s" + methodName.substring(lastIndexOf + 2, methodName.length() - 1);
            String condition = "";
            if (defaultValue.trim().equals("-1")) {
                condition = "\nif( isExistRecord == true && " + metaData.getMethodName() + isEqualZero + "){\n" + setter + minusOne + "\n);\nisChangeKey = true;}\n";
                return condition;
            } else if (defaultValue.trim().indexOf("null") > -1 || defaultValue.trim().indexOf("0") > -1) {
                condition = "if(isExistRecord && isChangeKey){" + setter + defaultValue + ");\nisChangeKey = false;\n}\n";
                return condition;
            }
        }

        return "";
    }


    public static List<String> getPersistenceData(String path, String regular) {

        String persistence = FileUtility.readTextFile(path);

        persistence = persistence.replaceAll("(?s)<!--.*?-->", "");

        List<String> allMatches = new ArrayList<String>();

        Pattern pattern = Pattern.compile(regular, Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(persistence);

        while (matcher.find()) {
            String model = matcher.group();
            model = model.substring(7, model.length() - 1);
            allMatches.add(model);
        }


        return allMatches;

    }

    public static List<String> getPersistenceModelList(String path) {
        return getPersistenceData(path, "<class>(\\w|\\.)+<");
    }

    public static List<String> getPersistenceUnitnameList(String path) {
        List<String> unitnames = new ArrayList<String>();
        List<String> data = getPersistenceData(path, "(persistence-unit)[^>]{1,}>");
        if (data != null) {
            int length = data.size();
            for (int i = 0; i < length; i++) {
                String tag = data.get(i);
                if (tag != null) {
                    String[] split = tag.split(" ");
                    for (int j = 0; j < split.length; j++) {
                        if (split[j].indexOf("name=") > -1) {
                            unitnames.add(split[j].substring(5, split[j].length()));
                        }
                    }
                }
            }
        }

        return unitnames;
    }

    private static void createRepositoryClass(String pathPersistence, String targetPackagePath, String targetPackage, String[] jarFiles, DependencyInjectionType diType) {

        List<String> unitnames = getPersistenceUnitnameList(pathPersistence);
        String repositoryAddress = targetPackagePath + "/Repository.java";
        javaListFiles.add(repositoryAddress);

        int size = javaListFiles.size();
        String[] javaFiles = new String[size];

        createJPABuilder(targetPackage, targetPackagePath);

        createSQLInterface(targetPackage, targetPackagePath);
        createProcedureInterface(targetPackage, targetPackagePath);
        createSQLBuilder(targetPackage, targetPackagePath);

        FileUtility.writeTextFile(repositoryAddress, createRepository("Repository", targetPackage, diType, unitnames.get(0)));
        System.out.println("Generate: " + repositoryAddress);


        if (diType.equals(DependencyInjectionType.EJB) || diType.equals(DependencyInjectionType.SPRING)) {
            for (int i = 1; i < unitnames.size(); i++) {
                repositoryAddress = targetPackagePath + "/Repository" + i + ".java";
                javaListFiles.add(repositoryAddress);
                FileUtility.writeTextFile(repositoryAddress, createRepository("Repository" + i, targetPackage, diType, unitnames.get(i)));
                System.out.println("Generate: " + repositoryAddress);
            }
        }


        for (int i = 0; i < size; i++) {
            javaFiles[i] = javaListFiles.get(i);
        }


        if (size > 0) {
            String target = targetPackagePath.substring(0, targetPackagePath.indexOf("/src")) + "/target/classes";
            compile(target, jarFiles, javaFiles);
        }

    }

    public static synchronized <M extends Object> void analyzer(String project) {

        String localRepository = "C:/Users/k.haghpanah/.m2/repository";
        String projectAddress = "D:/ReceptionEvaluation2/framework";
        String persistence = "D:/ReceptionEvaluation2/reception-evaluation/src/main/resources/META-INF/persistence.xml";
        String packageName = "com.tosan.loan.core.framework.dao";


        if (project.equals("home")) {
            localRepository = "C:/Users/swb/.m2/repository";
            projectAddress = "E:/playProject/jpa/eclipselink";
            persistence = "E:/playProject/jpa/eclipselink/src/main/resources/META-INF/persistence.xml";
            packageName = "com.tosan.bpms.framework.orm.repository.jpql";
        }

        if (project.equals("work")) {
            localRepository = "C:/Users/k.haghpanah/.m2/repository";
            projectAddress = "D:/Project/Java/SE/jpa/eclipselink";
            persistence = "D:/Project/Java/SE/jpa/eclipselink/src/main/resources/META-INF/persistence.xml";
            packageName = "com.tosan.bpms.framework.orm.repository.jpql";
        }


        ////////////////////////////////////////////////////


        ////////////////////////////////////////////////////
        String target = projectAddress + "/target/classes";


        String[] jarFiles;

        jarFiles = new String[]{
                target,
                localRepository + "/com/jpa/developers/jpql-maven-plugin/1.0.0/jpql-maven-plugin-1.0.0.jar",
                localRepository + "/org/apache/tomcat/tomcat-dbcp/7.0.62/tomcat-dbcp-7.0.62.jar",
                localRepository + "/com/tosan/bpmsprocess/domain.fileProcessing.api/4.19.0.0/domain.fileProcessing.api-4.19.0.0.jar",
                localRepository + "/com/tosan/bpmsprocess/model/4.19.0.0/model-4.19.0.0.jar",
                localRepository + "/org/hibernate/javax/persistence/hibernate-jpa-2.1-api/1.0.2.Final/hibernate-jpa-2.1-api-1.0.2.Final.jar",
                localRepository + "/org/springframework/spring-context/4.0.9.RELEASE/spring-context-4.0.9.RELEASE.jar",
                localRepository + "/org/springframework/data/spring-data-mongodb/1.8.4.RELEASE/spring-data-mongodb-1.8.4.RELEASE.jar",
                localRepository + "/org/slf4j/slf4j-api/1.7.25/slf4j-api-1.7.25.jar",
                localRepository + "/org/mongodb/mongo-java-driver/3.4.0/mongo-java-driver-3.4.0.jar",
                localRepository + "/commons-io/commons-io/1.3.1/commons-io-1.3.1.jar",
                localRepository + "/org/codehaus/jackson/jackson-jaxrs/1.9.2/jackson-jaxrs-1.9.2.jar",
                localRepository + "/com/fasterxml/jackson/core/jackson-core/2.0.0/jackson-core-2.0.0.jar",
                localRepository + "/commons-cli/commons-cli/1.2/commons-cli-1.2.jar",
                localRepository + "/org/apache/commons/commons-lang3/3.2.1/commons-lang3-3.2.1.jar",
                localRepository + "/com/github/tony19/named-regexp/0.1.9/named-regexp-0.1.9.jar",
                localRepository + "/org/apache/cxf/cxf-rt-bindings-soap/2.7.8/cxf-rt-bindings-soap-2.7.8.jar",
                localRepository + "/org/apache/cxf/cxf-api/2.7.8/cxf-api-2.7.8.jar",
                localRepository + "/org/apache/cxf/cxf-rt-core/2.7.8/cxf-rt-core-2.7.8.jar",
                localRepository + "/org/apache/cxf/cxf-rt-ws-security/2.7.8/cxf-rt-ws-security-2.7.8.jar",
                localRepository + "/org/apache/cxf/cxf-rt-frontend-jaxws/2.7.8/cxf-rt-frontend-jaxws-2.7.8.jar",
                localRepository + "/org/apache/cxf/cxf-rt-transports-http/2.7.8/cxf-rt-transports-http-2.7.8.jar",
                localRepository + "/com/sun/xml/bind/jaxb-xjc/2.2.6/jaxb-xjc-2.2.6.jar",
                localRepository + "/com/sun/xml/bind/jaxb-impl/2.2.6/jaxb-impl-2.2.6.jar",
                localRepository + "/net/sf/saxon/saxon-dom/9.1.0.8/saxon-dom-9.1.0.8.jar",
                localRepository + "/org/mybatis/mybatis-spring/1.2.2/mybatis-spring-1.2.2.jar",
                localRepository + "/cglib/cglib/2.2.2/cglib-2.2.2.jar",
                localRepository + "/org/mybatis/mybatis/3.2.3/mybatis-3.2.3.jar",
                localRepository + "/com/jolbox/bonecp/0.8.0.RELEASE/bonecp-0.8.0.RELEASE.jar",
                localRepository + "/com/google/guava/guava/13.0.1/guava-13.0.1.jar",
                localRepository + "/com/oracle/ojdbc6/11.2.0.1.0/ojdbc6-11.2.0.1.0.jar",
                localRepository + "/org/aspectj/aspectjrt/1.6.6/aspectjrt-1.6.6.jar",
                localRepository + "/javax/servlet/com.springsource.javax.servlet/2.5.0/com.springsource.javax.servlet-2.5.0.jar",
                localRepository + "/com/ibm/icu/icu4j/55.1/icu4j-55.1.jar",
                localRepository + "/org/springframework/spring-tx/4.0.9.RELEASE/spring-tx-4.0.9.RELEASE.jar",
                localRepository + "/org/springframework/spring-web/4.0.9.RELEASE/spring-web-4.0.9.RELEASE.jar",
                localRepository + "/org/springframework/security/spring-security-core/3.1.4.RELEASE/spring-security-core-3.1.4.RELEASE.jar",
                localRepository + "/org/springframework/security/spring-security-web/3.1.4.RELEASE/spring-security-web-3.1.4.RELEASE.jar",
                localRepository + "/org/springframework/security/spring-security-config/3.1.4.RELEASE/spring-security-config-3.1.4.RELEASE.jar",
                localRepository + "/org/springframework/spring-test/4.0.9.RELEASE/spring-test-4.0.9.RELEASE.jar",
                localRepository + "/org/springframework/spring-aop/4.0.9.RELEASE/spring-aop-4.0.9.RELEASE.jar",
                localRepository + "/ch/qos/logback/logback-classic/1.0.13/logback-classic-1.0.13.jar",
                localRepository + "/org/slf4j/jcl-over-slf4j/1.7.6/jcl-over-slf4j-1.7.6.jar",
                localRepository + "/joda-time/joda-time/2.4/joda-time-2.4.jar",
                localRepository + "/ch/qos/logback/logback-core/1.0.13/logback-core-1.0.13.jar",
                localRepository + "/commons-beanutils/commons-beanutils/1.8.3/commons-beanutils-1.8.3.jar",
                localRepository + "/ch/qos/logback/logback-access/1.0.13/logback-access-1.0.13.jar",
                localRepository + "/junit/junit/4.11/junit-4.11.jar",
                localRepository + "/org/hamcrest/hamcrest-core/1.3/hamcrest-core-1.3.jar",
                localRepository + "/org/codehaus/jackson/jackson-core-asl/1.9.2/jackson-core-asl-1.9.2.jar",
                localRepository + "/org/mockito/mockito-all/1.9.5/mockito-all-1.9.5.jar",
                localRepository + "/com/jpa/developers/jpql-maven-api/1.0.0/jpql-maven-api-1.0.0.jar",
                localRepository + "/org/springframework/spring-orm/4.0.9.RELEASE/spring-orm-4.0.9.RELEASE.jar",
                localRepository + "/org/springframework/spring-jdbc/4.0.9.RELEASE/spring-jdbc-4.0.9.RELEASE.jar"
        };


        jarFiles = new String[]{
                target,
                localRepository + "/com/jpa/developers/jpql-maven-api/1.0.0/jpql-maven-api-1.0.0.jar",
                localRepository + "/com/jpa/developers/jpql-maven-plugin/1.0.0/jpql-maven-plugin-1.0.0.jar",
                localRepository + "/mysql/mysql-connector-java/5.1.36/mysql-connector-java-5.1.36.jar",
                localRepository + "/org/eclipse/persistence/eclipselink/2.6.0/eclipselink-2.6.0.jar",
                localRepository + "/com/google/inject/guice/4.2.0/guice-4.2.0.jar",
                localRepository + "/com/google/inject/extensions/guice-persist/4.2.0/guice-persist-4.2.0.jar",
                localRepository + "/ru/vyarus/guice-validator/1.2.0/guice-validator-1.2.0.jar",
                localRepository + "/org/hibernate/hibernate-validator/5.4.1.Final/hibernate-validator-5.4.1.Final.jar",
                localRepository + "/org/glassfish/javax.el/3.0.1-b08/javax.el-3.0.1-b08.jar",
                localRepository + "/commons-io/commons-io/2.6/commons-io-2.6.jar",
                localRepository + "/commons-lang/commons-lang/2.6/commons-lang-2.6.jar",
                localRepository + "/javax/javaee-api/7.0/javaee-api-7.0.jar",
                localRepository + "/org/scala-lang/scala-library/2.10.4/scala-library-2.10.4.jar",
                localRepository + "/org/scalatest/scalatest_2.10/3.2.0-SNAP4/scalatest_2.10-3.2.0-SNAP4.jar"
        };

        Generator.analyzer(
                "", projectAddress,
                persistence,
                packageName,
                // projectAddress + "/src/main/java/com/tosan/loan/core/framework/dao"
                projectAddress + "/src/main/java/" + packageName.replaceAll("\\.", "\\/")
                , jarFiles, DependencyInjectionType.GUICE
        );
    }

    public static synchronized <M extends Object> void analyzer(String mvnBat, String projectAddress, String pathPersistence, String targetPackage, String targetPackagePath, String[] jarFiles, DependencyInjectionType diType) {

        setClassLoader(jarFiles);

        //D:/Project/Java/SE/jpa/eclipselink/src/main/resources/META-INF/persistence.xml
        int index = pathPersistence.lastIndexOf("/resources") + 10;
        String pathSpyPropertie = pathPersistence.substring(0, index) + "/";
        File file = new File(pathSpyPropertie + "spy.properties");
        String spyLogAddress = "";

        if (!file.exists()) {
            String config = "driverlist=com.p6spy.engine.spy.P6SpyDriver\n" +
                    "dateformat=yyyy-MM-dd hh:mm:ss a\n" +
                    "#appender=com.p6spy.engine.spy.appender.Slf4JLogger\n" +
                    "#appender=com.p6spy.engine.spy.appender.StdoutLogger\n" +
                    "appender=com.p6spy.engine.spy.appender.FileLogger\n" +
                    "logfile=./spy.log\n" +
                    "logMessageFormat=com.p6spy.engine.spy.appender.MultiLineFormat";
            FileUtility.writeTextFile(pathSpyPropertie + "spy.properties", config);
            //javaListFiles.add(pathSpyPropertie + "spy.properties");
            //javaListFiles.add(pathSpyPropertie + "spy.log");
        }


        createFacadeClass(pathPersistence, targetPackage, targetPackagePath, diType);
        createRepositoryClass(pathPersistence, targetPackagePath, targetPackage, jarFiles, diType);

        String cmd = "install";

        file = new File(projectAddress + "/target");
        if (file.isDirectory()) {
            file = new File(projectAddress + "/target/classes");
            if (file.isDirectory()) {
                cmd = "clean install";
            }
        }


        //ProcessBuilder processBuilder = new ProcessBuilder();
        ////processBuilder.command("C:/Program Files/Maven/apache-maven-3.5.4/bin/mvn.bat", "D:/Project/Java/SE/jpa/eclipselink", "clean install");
        //processBuilder.command(mvnBat, projectAddress, cmd);


    }

    public static void compile(String target, String[] jarFiles, String... javaFiles) {


        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(diagnostics, Locale.getDefault(), Charset.forName("UTF-8"));
        Iterable<? extends JavaFileObject> input = fileManager.getJavaFileObjects(javaFiles);
        //List<JavaFileObject> input = new ArrayList<JavaFileObject>(); //fileManager.getJavaFileObjects(javaFiles);


        List<String> optionList = new ArrayList<String>();

        optionList.add("-classpath");
        optionList.add("\";" + createClassPath(jarFiles) + "\"");

        optionList.add("-d");
        optionList.add(target);
        StringWriter out = new StringWriter();
        PrintWriter printWriter = new PrintWriter(out);

        String javac = "javac ";
        for (String option : optionList) {
            javac += (option + " ");
        }

        for (String javafile : javaFiles) {
            javac += (javafile + " ");
        }

        System.out.println(javac);

        JavaCompiler.CompilationTask task = compiler.getTask(printWriter, fileManager, diagnostics, optionList, null, input);
        if (task.call()) {
        }

        printWriter.println();

        StringBuilder errorMsg = new StringBuilder();
        for (Diagnostic d : diagnostics.getDiagnostics()) {

            String bug = " lineNumber: " + d.getLineNumber() + " - columnNumber: " + d.getColumnNumber() + " - source: ";
            String source = d.getSource().toString();
            source = source.replaceAll("\\\\", "/");

            if (source != null && !source.equals("")) {
                source.substring(source.indexOf("/") + 1, source.length());
            }

            bug = bug + source;

            String err = String.format("Compilation error: Line %d - %s%n " + bug, d.getLineNumber(), d.getMessage(null));
            errorMsg.append(err);
            System.out.println(err);
        }


    }

    private static String createClassPath(String... jarFiles) {

        String os = System.getProperty("os.name").toLowerCase();
        boolean isWindows = false;
        if (os.indexOf("windows") > -1) {
            isWindows = true;
        }

        String jars = "";

        if (jarFiles != null && jarFiles.length > 0) {
            for (int i = 0; i < jarFiles.length; i++) {

                String doubleColon = ":";
                if (isWindows) {
                    doubleColon = ";";
                }
                if (i == jarFiles.length - 1) {
                    doubleColon = "";
                }
                jars += ("" + jarFiles[i] + "" + doubleColon);
            }

            if (!isWindows) {
                jars += ":.";  // linux
            } else {
                jars = jars + ";";
            }
        }
        return jars;
    }

    public static String reformatSourceCode(String context) {
        //if (context != null)
        //return context;
        JavaFormatterOptions.JavadocFormatter javadocFormatter = JavaFormatterOptions.JavadocFormatter.ECLIPSE;
        JavaFormatterOptions.Style style = JavaFormatterOptions.Style.GOOGLE;
        JavaFormatterOptions.SortImports sortImports = JavaFormatterOptions.SortImports.NO;

        JavaFormatterOptions javaFormatterOptions = new JavaFormatterOptions(javadocFormatter, style, sortImports);
        try {
            return new com.google.googlejavaformat.java.Formatter(javaFormatterOptions).formatSource(context);
        } catch (FormatterException e) {
            e.printStackTrace();
        }
        return "";

    }

    private static void createJPABuilder(String targetPackage, String targetPackagePath) {

        String content = "package " + targetPackage + ";\n" +
                "import com.jpql.api.interfaces.*;\n" +
                "import com.jpql.api.interfaces.Parameter;\n" +
                "import javax.persistence.EntityManager;\n" +
                "import javax.persistence.FetchType;\n" +
                "import java.util.*;\n" +
                "\n" +
                "/**\n" +
                " * Created by k.haghpanah on 5/01/2019.\n" +
                " */\n" +
                "public class JPABuilder {\n" +
                "\n" +
                "    EntityManager entityManager;\n" +
                "    final Map<String, List<String>> sqlMap = new HashMap<String, List<String>>();\n" +
                "    Parameter jpql;\n" +
                "    QueryParameter queryParameter;\n" +
                "    final Map<String, String> statement = new HashMap<String, String>();\n" +
                "    Cast cast;\n" +
                "    List models;\n" +
                "    FetchType fetchType = FetchType.EAGER;\n" +
                "    boolean isFetch;\n" +
                "    int page;\n" +
                "    int size;\n" +
                "    boolean isExistRecord;\n" +
                "    DTO dto = null;\n" +
                "    boolean isOrState = false;\n" +
                "\n" +
                "    private void put(String key, String value) {\n" +
                "\n" +
                "        int size = 0;\n" +
                "        List<String> conditions = sqlMap.get(key);\n" +
                "        if (conditions == null) {\n" +
                "            conditions = new ArrayList<String>();\n" +
                "            sqlMap.put(key, conditions);\n" +
                "        } else {\n" +
                "            size = conditions.size();\n" +
                "        }\n" +
                "\n" +
                "        if ((key.equals(\"WHERE\") || key.equals(\"HAVING\")) && size % 2 == 1) {\n" +
                "            if (this.isOrState) {\n" +
                "                conditions.add(\"OR\");\n" +
                "            } else {\n" +
                "                conditions.add(\"AND\");\n" +
                "            }\n" +
                "        }\n" +
                "\n" +
                "        if (size == 0 && key.equals(\"WHERE\")) {\n" +
                "            if (this.isOrState) {\n" +
                "                value = \" OR \" + value;\n" +
                "            } else {\n" +
                "                value = \" AND \" + value;\n" +
                "            }\n" +
                "        }\n" +
                "        conditions.add(value);\n" +
                "        this.isOrState = false;\n" +
                "    }\n" +
                "\n" +
                "    private String get(String key) {\n" +
                "\n" +
                "        List<String> conditions = sqlMap.get(key);\n" +
                "        if (conditions == null || conditions.size() == 0) {\n" +
                "            return \"\";\n" +
                "        }\n" +
                "\n" +
                "        String result = key;\n" +
                "        if (key.equals(\"WHERE\")) {\n" +
                "            result = \"\";\n" +
                "        }\n" +
                "        String operator = \"\";\n" +
                "\n" +
                "        Iterator<String> iterator = conditions.iterator();\n" +
                "        while (iterator.hasNext()) {\n" +
                "\n" +
                "            String next = iterator.next();\n" +
                "            String lowerNext = next.toLowerCase();\n" +
                "            result += (\" \" + next);\n" +
                "\n" +
                "            if (iterator.hasNext()) {\n" +
                "\n" +
                "                if (key.equals(\"WHERE\") || key.equals(\"HAVING\")) {\n" +
                "\n" +
                "                } else {\n" +
                "                    result += (\" ,\");\n" +
                "                }\n" +
                "\n" +
                "            }\n" +
                "        }\n" +
                "\n" +
                "        return result + \" \";\n" +
                "    }\n" +
                "\n" +
                "    public <M extends Model> JPABuilder(EntityManager entityManager, List<M> models, DTO dto) {\n" +
                "        this.entityManager = entityManager;\n" +
                "        if (models == null || models.size() == 0) {\n" +
                "            return;\n" +
                "        }\n" +
                "        this.models = models;\n" +
                "        this.dto = dto;\n" +
                "\n" +
                "    }\n" +
                "\n" +
                "    public JPABuilder showQuery(Parameter jpql) {\n" +
                "        this.jpql = jpql;\n" +
                "        this.isOrState = false;\n" +
                "        return this;\n" +
                "    }\n" +
                "\n" +
                "\n" +
                "    public JPABuilder or() {\n" +
                "\n" +
                "        this.isOrState = true;\n" +
                "        return this;\n" +
                "    }\n" +
                "\n" +
                "    public JPABuilder and() {\n" +
                "        this.isOrState = false;\n" +
                "        return this;\n" +
                "    }\n" +
                "\n" +
                "    public JPABuilder select(String item) {\n" +
                "        this.put(\"SELECT\", item);\n" +
                "        this.isOrState = false;\n" +
                "        return this;\n" +
                "    }\n" +
                "\n" +
                "    public JPABuilder where(String item) {\n" +
                "        this.put(\"WHERE\", item);\n" +
                "        return this;\n" +
                "    }\n" +
                "\n" +
                "    public JPABuilder groupBy(String item) {\n" +
                "        this.put(\"GROUP BY\", item);\n" +
                "        this.isOrState = false;\n" +
                "        return this;\n" +
                "    }\n" +
                "\n" +
                "    public JPABuilder having(String item) {\n" +
                "        this.put(\"HAVING\", item);\n" +
                "        return this;\n" +
                "    }\n" +
                "\n" +
                "    public JPABuilder orderBy(String item) {\n" +
                "        this.put(\"ORDER BY\", item);\n" +
                "        this.isOrState = false;\n" +
                "        return this;\n" +
                "    }\n" +
                "\n" +
                "    public JPABuilder query(QueryParameter queryParameter) {\n" +
                "        this.queryParameter = queryParameter;\n" +
                "        this.isOrState = false;\n" +
                "        return this;\n" +
                "    }\n" +
                "\n" +
                "    public JPABuilder cast(Cast cast) {\n" +
                "        this.isOrState = false;\n" +
                "        this.cast = cast;\n" +
                "        return this;\n" +
                "    }\n" +
                "\n" +
                "\n" +
                "    public JPABuilder fetchType(FetchType fetchType) {\n" +
                "        this.fetchType = fetchType;\n" +
                "        this.isOrState = false;\n" +
                "        return this;\n" +
                "    }\n" +
                "\n" +
                "\n" +
                "    public JPABuilder fetch(boolean fetch) {\n" +
                "        isFetch = fetch;\n" +
                "        this.isOrState = false;\n" +
                "        return this;\n" +
                "    }\n" +
                "\n" +
                "    public JPABuilder page(int page) {\n" +
                "        this.page = page;\n" +
                "        this.isOrState = false;\n" +
                "        return this;\n" +
                "    }\n" +
                "\n" +
                "\n" +
                "    public JPABuilder size(int size) {\n" +
                "        this.size = size;\n" +
                "        this.isOrState = false;\n" +
                "        return this;\n" +
                "    }\n" +
                "\n" +
                "    public JPABuilder setExistRecord(boolean existRecord) {\n" +
                "        isExistRecord = existRecord;\n" +
                "        this.isOrState = false;\n" +
                "        return this;\n" +
                "    }\n" +
                "\n" +
                "\n" +
                "    public JPABuilder statement(Statement statement) {\n" +
                "        if (statement != null) {\n" +
                "            statement.set(this.statement);\n" +
                "        }\n" +
                "        this.isOrState = false;\n" +
                "        return this;\n" +
                "    }\n" +
                "\n" +
                "\n" +
                "    public <D extends DTO, M extends Model, T> List<T> build() {\n" +
                "\n" +
                "        String furthermore = get(\"WHERE\") + get(\"GROUP BY\") + get(\"HAVING\") + get(\"ORDER BY\");\n" +
                "\n" +
                "        if (this.dto != null) {\n" +
                "\n" +
                "            if (this.cast == null) {\n" +
                "\n" +
                "                List<M> result = Facade.getByFilter(entityManager, models, isFetch, page, size, get(\"SELECT\"), jpql, queryParameter, cast, false, \"AND\", fetchType, statement, false, furthermore);\n" +
                "                List<DTO> dtoList = new ArrayList<DTO>();\n" +
                "                if (result != null) {\n" +
                "                    if (cast == null && result != null) {\n" +
                "                        for (int i = 0; i < result.size(); i++) {\n" +
                "                            D o = (D) dto.convertToDTO(result.get(i));\n" +
                "                            dtoList.add(o);\n" +
                "                        }\n" +
                "                    }\n" +
                "                }\n" +
                "                return (List<T>) dtoList;\n" +
                "\n" +
                "            }\n" +
                "\n" +
                "        }\n" +
                "\n" +
                "        return Facade.getByFilter(entityManager, models, isFetch, page, size, get(\"SELECT\"), jpql, queryParameter, cast, false, \"AND\", fetchType, statement, false, furthermore);\n" +
                "\n" +
                "\n" +
                "    }\n" +
                "\n" +
                "}";

        String path = targetPackagePath + "/JPABuilder.java";
        FileUtility.writeTextFile(path, content);
        System.out.println("Generate: " + path);
        javaListFiles.add(path);

    }

    private static void createSQLInterface(String targetPackage, String targetPackagePath) {

        String content = "package " + targetPackage + ";\n" +
                "\n" +
                "import com.jpql.api.interfaces.Cast;\n" +
                "import com.jpql.api.interfaces.Parameter;\n" +
                "import com.jpql.api.interfaces.QueryParameter;\n" +
                "import javax.persistence.EntityManager;\n" +
                "import java.util.List;\n" +
                "\n" +
                "/**\n" +
                " * Created by kasra.haghpanah on 26/07/2019.\n" +
                " */\n" +
                "public interface SQLInterface {\n" +
                "\n" +
                "    public EntityManager getEntityManager();\n" +
                "\n" +
                "    public SQLInterface query(QueryParameter queryParameter);\n" +
                "\n" +
                "    public SQLInterface disabledPagination(boolean disabledPagination);\n" +
                "\n" +
                "    public SQLInterface cast(Cast cast);\n" +
                "\n" +
                "    public SQLInterface page(int page);\n" +
                "\n" +
                "    public SQLInterface size(int size);\n" +
                "\n" +
                "    public SQLInterface update(boolean update);\n" +
                "\n" +
                "    public SQLInterface showQuery(Parameter jpql);\n" +
                "\n" +
                "    public <T> List<T> build();\n" +
                "}";

        String path = targetPackagePath + "/SQLInterface.java";
        FileUtility.writeTextFile(path, content);
        System.out.println("Generate: " + path);
        javaListFiles.add(path);

    }

    private static void createProcedureInterface(String targetPackage, String targetPackagePath) {

        String content = "package " + targetPackage + ";\n" +
                "\n" +
                "import com.jpql.api.interfaces.Cast;\n" +
                "import com.jpql.api.interfaces.Parameter;\n" +
                "import com.jpql.api.interfaces.ProcedureParameter;\n" +
                "import javax.persistence.EntityManager;\n" +
                "import java.util.List;\n" +
                "\n" +
                "/**\n" +
                " * Created by swb on 26/07/2019.\n" +
                " */\n" +
                "public interface ProcedureInterface {\n" +
                "\n" +
                "    public EntityManager getEntityManager();\n" +
                "\n" +
                "    public ProcedureInterface query(ProcedureParameter procedureParameter);\n" +
                "\n" +
                "    public ProcedureInterface cast(Cast cast);\n" +
                "\n" +
                "    public ProcedureInterface page(int page);\n" +
                "\n" +
                "    public ProcedureInterface size(int size);\n" +
                "\n" +
                "    public ProcedureInterface update(boolean update);\n" +
                "\n" +
                "    public ProcedureInterface outputParameter(ProcedureParameter procedureParameter);\n" +
                "\n" +
                "    public ProcedureInterface showQuery(Parameter jpql);\n" +
                "\n" +
                "    public <T> List<T> build();\n" +
                "}";

        String path = targetPackagePath + "/ProcedureInterface.java";
        FileUtility.writeTextFile(path, content);
        System.out.println("Generate: " + path);
        javaListFiles.add(path);

    }

    private static void createSQLBuilder(String targetPackage, String targetPackagePath) {

        String content = "package " + targetPackage + ";\n" +
                "\n" +
                "import com.jpql.api.interfaces.*;\n" +
                "import javax.persistence.EntityManager;\n" +
                "import javax.persistence.NoResultException;\n" +
                "import javax.persistence.Query;\n" +
                "import javax.persistence.StoredProcedureQuery;\n" +
                "import java.util.ArrayList;\n" +
                "import java.util.List;\n" +
                "\n" +
                "/**\n" +
                " * Created by k.haghpanah on 5/01/2019.\n" +
                " */\n" +
                "public class SQLBuilder implements SQLInterface, ProcedureInterface {\n" +
                "\n" +
                "    EntityManager entityManager;\n" +
                "    String sql;\n" +
                "    Parameter jpql;\n" +
                "    QueryParameter queryParameter;\n" +
                "    ProcedureParameter storedProcedureQuery;\n" +
                "    ProcedureParameter outputParameter;\n" +
                "    Cast cast;\n" +
                "    int page = 0;\n" +
                "    int size = 0;\n" +
                "    boolean isUpdate = false;\n" +
                "    String sqlType;\n" +
                "    boolean disabledPagination = false;\n" +
                "\n" +
                "    public SQLBuilder(EntityManager entityManager, String sqlType, String sql) {\n" +
                "        this.entityManager = entityManager;\n" +
                "        this.sqlType = sqlType;\n" +
                "        this.sql = sql;\n" +
                "    }\n" +
                "\n" +
                "    public EntityManager getEntityManager() {\n" +
                "        return entityManager;\n" +
                "    }\n" +
                "\n" +
                "    public SQLBuilder showQuery(Parameter jpql) {\n" +
                "        this.jpql = jpql;\n" +
                "        return this;\n" +
                "    }\n" +
                "\n" +
                "    public SQLBuilder query(QueryParameter queryParameter) {\n" +
                "        this.queryParameter = queryParameter;\n" +
                "        return this;\n" +
                "    }\n" +
                "\n" +
                "    public SQLBuilder query(ProcedureParameter procedureParameter) {\n" +
                "        this.storedProcedureQuery = procedureParameter;\n" +
                "        return this;\n" +
                "    }\n" +
                "\n" +
                "    public SQLBuilder disabledPagination(boolean disabledPagination) {\n" +
                "        this.disabledPagination = disabledPagination;\n" +
                "        return this;\n" +
                "    }\n" +
                "\n" +
                "    public SQLBuilder cast(Cast cast) {\n" +
                "        this.cast = cast;\n" +
                "        return this;\n" +
                "    }\n" +
                "\n" +
                "    public SQLBuilder page(int page) {\n" +
                "        this.page = page;\n" +
                "        return this;\n" +
                "    }\n" +
                "\n" +
                "    public SQLBuilder size(int size) {\n" +
                "        this.size = size;\n" +
                "        return this;\n" +
                "    }\n" +
                "\n" +
                "    public SQLBuilder update(boolean update) {\n" +
                "        isUpdate = update;\n" +
                "        return this;\n" +
                "    }\n" +
                "\n" +
                "    public SQLBuilder outputParameter(ProcedureParameter procedureParameter) {\n" +
                "        this.outputParameter = procedureParameter;\n" +
                "        return this;\n" +
                "    }\n" +
                "\n" +
                "    public <T> List<T> build() {\n" +
                "\n" +
                "        boolean isStoredProcedure = this.sqlType.equals(\"procedure\");\n" +
                "        Query query = null;\n" +
                "        if (size <= 0) size = 100;\n" +
                "        if (page <= 0) page = 0;\n" +
                "        if (!isStoredProcedure) {\n" +
                "            outputParameter = null;\n" +
                "        }\n" +
                "\n" +
                "        if (this.sqlType.equals(\"sql\")) {\n" +
                "            query = entityManager.createNativeQuery(sql);\n" +
                "        } else if (this.sqlType.equals(\"jpql\")) {\n" +
                "            query = entityManager.createQuery(sql);\n" +
                "        } else if (this.sqlType.equals(\"procedure\")) {\n" +
                "            query = entityManager.createStoredProcedureQuery(sql);\n" +
                "        }\n" +
                "\n" +
                "\n" +
                "        if (queryParameter != null) {\n" +
                "            queryParameter.createCondition(query);\n" +
                "        }\n" +
                "\n" +
                "\n" +
                "        if (storedProcedureQuery != null) {\n" +
                "            storedProcedureQuery.get((StoredProcedureQuery) query);\n" +
                "        }\n" +
                "\n" +
                "        if (isUpdate) {\n" +
                "            query.executeUpdate();\n" +
                "        } else if (isStoredProcedure) {\n" +
                "            ((StoredProcedureQuery) query).execute();\n" +
                "        }\n" +
                "            if (outputParameter != null) {\n" +
                "                outputParameter.get((StoredProcedureQuery) query);\n" +
                "            }\n" +
                "\n" +
                "\n" +
                "        try {\n" +
                "            if (cast != null) {\n" +
                "                if (!isStoredProcedure && !disabledPagination) {\n" +
                "                    query.setFirstResult(page * size);\n" +
                "                    query.setMaxResults(size);\n" +
                "                }\n" +
                "                List<Object> result = query.getResultList();\n" +
                "\n" +
                "                if (jpql != null) {\n" +
                "                    java.util.Set set = query.getParameters();\n" +
                "                    java.util.Iterator iterator1 = set.iterator();\n" +
                "                    String variables = \"\";\n" +
                "\n" +
                "                    if (iterator1.hasNext()) {\n" +
                "                        variables = \"\\n\\n************ Variables ************\\n\";\n" +
                "                        while (iterator1.hasNext()) {\n" +
                "                            javax.persistence.Parameter key = (javax.persistence.Parameter) iterator1.next();\n" +
                "\n" +
                "                            Object name = key.getPosition();\n" +
                "                            Object value = null;\n" +
                "                            if (name == null) {\n" +
                "                                name = key.getName();\n" +
                "                            }\n" +
                "\n" +
                "                            try {\n" +
                "                                value = query.getParameterValue(key);\n" +
                "                            } catch (IllegalStateException e) {\n" +
                //"                                System.out.println(e.getClass());\n" +
                "                            }\n" +
                "\n" +
                "                            if (key.getParameterType().equals(java.lang.String.class)) {\n" +
                "                                variables += (name + \" : '\" + value + \"'\\n\");\n" +
                "                            } else {\n" +
                "                                variables += (name + \" : \" + value + \"\\n\");\n" +
                "                            }\n" +
                "\n" +
                "                        }\n" +
                "                        variables += \"************ Variables ************\";\n" +
                "                    }\n" +
                "                    jpql.get(sql + variables);\n" +
                "                }\n" +
                "\n" +
                "\n" +
                "                List<T> tList = new ArrayList<T>();\n" +
                "                if (result != null && result.size() > 0) {\n" +
                "                    for (Object object : result) {\n" +
                "                        Object[] objects;\n" +
                "                        if (object instanceof Object[]) {\n" +
                "                            objects = (Object[]) object;\n" +
                "                        } else {\n" +
                "                            objects = new Object[]{object};\n" +
                "                        }\n" +
                "                        tList.add((T) cast.to(objects));\n" +
                "                    }\n" +
                "                }\n" +
                "                return tList;\n" +
                "            }\n" +
                "        } catch (NoResultException e) {\n" +
                "            return null;\n" +
                "        }\n" +
                "\n" +
                "        return null;\n" +
                "\n" +
                "    }\n" +
                "\n" +
                "\n" +
                "}";

        String path = targetPackagePath + "/SQLBuilder.java";
        FileUtility.writeTextFile(path, content);
        System.out.println("Generate: " + path);
        javaListFiles.add(path);
    }

    private static String createRepository(String className, String targetPackage, DependencyInjectionType diType, String unitname) {


        String entityManager = "\n    private EntityManager entityManager;\n";
        String constructor = "\n    public " + className + "(String unitname) {\n        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(unitname);\n        entityManager = entityManagerFactory.createEntityManager();\n    }\n";

        String beanAnnotation = "";
        String flush = "            //entityManager.flush();\n";

        if (diType.equals(DependencyInjectionType.GUICE)) {
            constructor = "    @javax.inject.Inject\n    public " + className + "(com.google.inject.Provider<EntityManager> entityManager) {\n        this.entityManager = entityManager.get();\n    }\n";
        } else if (diType.equals(DependencyInjectionType.SPRING) || diType.equals(DependencyInjectionType.EJB)) {
            flush = "            entityManager.flush();\n";
            entityManager = "\n    @PersistenceContext(unitName=" + unitname + ")\n    private EntityManager entityManager;\n";
            constructor = "";
            if (diType.equals(DependencyInjectionType.SPRING)) {
                beanAnnotation = "@org.springframework.stereotype.Component\n";
            } else {
                beanAnnotation = "@javax.ejb.Stateless\n";
            }
        }

        String content = "package " + targetPackage + ";\n" +
                "import com.jpql.api.interfaces.*;\n" +
                "import com.jpql.api.interfaces.Parameter;\n" +
                "import javax.persistence.*;\n" +
                "import javax.persistence.EntityManager;\n" +
                "import javax.persistence.Query;\n" +
                "import javax.persistence.NoResultException;\n" +
                "import java.util.*;\n" +
                "\n" +
                "/**\n" +
                " * Created by kasra.haghpanah on 1/05/2019.\n" +
                " */\n" +
                "\n" +
                "\n" +
                beanAnnotation +
                "public class " + className + " {\n" +
                "\n" +
                entityManager +
                "\n" +
                "\n" +
                constructor +
                "\n" +
                "    public EntityManager getEntityManager() {\n" +
                "        return entityManager;\n" +
                "    }\n\n" +
                "\n" +
                "\n" +
                "    //private static final Logger LOGGER = LoggerFactory.getLogger(MongoMapperImpl.class);\n" +
                "\n" +
                "public <M extends Model> List<M> save(List<M> entities) {\n" +
                "        return Facade.save(entityManager, entities);\n" +
                "    }\n" +
                "\n" +
                "    public <M extends Model> List<M> save(M... entities) {\n" +
                "\n" +
                "        if (entities == null || entities.length == 0) {\n" +
                "            return null;\n" +
                "        }\n" +
                "\n" +
                "        List<M> entitieList = new ArrayList<M>();\n" +
                "\n" +
                "        for (int i = 0; i < entities.length; i++) {\n" +
                "            entitieList.add(entities[i]);\n" +
                "        }\n" +
                "\n" +
                "        return Facade.save(entityManager, entitieList);\n" +
                "    }\n" +
                "\n" +
                "    public <D extends DTO> List<D> save(D... dtos) {\n" +
                "\n" +
                "        if (dtos == null || dtos.length == 0) {\n" +
                "            return null;\n" +
                "        }\n" +
                "\n" +
                "        List<Model> converts = new ArrayList<Model>();\n" +
                "\n" +
                "        for (int i = 0; i < dtos.length; i++) {\n" +
                "            converts.add(dtos[i].convertToJPAModel());\n" +
                "        }\n" +
                "\n" +
                "        List<Model> models = Facade.save(entityManager, converts);\n" +
                "\n" +
                "        if (models == null || models.size() == 0) {\n" +
                "            return null;\n" +
                "        }\n" +
                "\n" +
                "        List<DTO> result = new ArrayList<DTO>();\n" +
                "        DTO d = dtos[0];\n" +
                "\n" +
                "        for (Model model : models) {\n" +
                "            result.add((DTO) d.convertToDTO(model));\n" +
                "        }\n" +
                "\n" +
                "        return (List<D>) result;\n" +
                "\n" +
                "    }\n" +
                "\n" +
                "    public <D extends DTO> List<D> save(ArrayList<D> dtos) {\n" +
                "\n" +
                "        if (dtos == null || dtos.size() == 0) {\n" +
                "            return null;\n" +
                "        }\n" +
                "\n" +
                "        List<Model> converts = new ArrayList<Model>();\n" +
                "        int length = dtos.size();\n" +
                "\n" +
                "        for (D dto : dtos) {\n" +
                "            converts.add(dto.convertToJPAModel());\n" +
                "        }\n" +
                "\n" +
                "        List<Model> models = Facade.save(entityManager, converts);\n" +
                "\n" +
                "        if (models == null || models.size() == 0) {\n" +
                "            return null;\n" +
                "        }\n" +
                "\n" +
                "        List<DTO> result = new ArrayList<DTO>();\n" +
                "        DTO d = dtos.get(0);\n" +
                "\n" +
                "        for (Model model : models) {\n" +
                "            result.add((DTO) d.convertToDTO(model));\n" +
                "        }\n" +
                "\n" +
                "        return (List<D>) result;\n" +
                "\n" +
                "    }\n" +
                "\n" +
                "    public <M extends Model> M save(M entity) {\n" +
                "\n" +
                "        List<M> entities = new ArrayList<M>();\n" +
                "        entities.add(entity);\n" +
                "        entities = Facade.save(entityManager, entities);\n" +
                "        if (entities == null) {\n" +
                "            return null;\n" +
                "        }\n" +
                "        return entities.get(0);\n" +
                "    }\n" +
                "\n" +
                "    public <D extends DTO<D, M>, M extends Model> D save(D dto) {\n" +
                "\n" +
                "        if (dto == null) {\n" +
                "            return null;\n" +
                "        }\n" +
                "        M entity = save(dto.convertToJPAModel());\n" +
                "        if (entity == null) {\n" +
                "            return null;\n" +
                "        }\n" +
                "        return dto.convertToDTO(entity);\n" +
                "    }\n" +
                "\n" +
                "    public <M extends Model> void delete(List<M> entities) {\n" +
                "\n" +
                "        if (entities == null || entities.size() == 0) {\n" +
                "            return;\n" +
                "        }\n" +
                "        Facade.getByFilter(entityManager, entities, false, 0, 100, null, null, null, null, false, \"AND\", FetchType.LAZY, null, true, \"\");\n" +
                "    }\n" +
                "\n" +
                "    public <D extends DTO> void delete(D... dtos) {\n" +
                "\n" +
                "        if (dtos == null || dtos.length == 0) {\n" +
                "            return;\n" +
                "        }\n" +
                "\n" +
                "        List<Model> models = new ArrayList<Model>();\n" +
                "        int length = dtos.length;\n" +
                "        for (int i = 0; i < length; i++) {\n" +
                "            models.add(dtos[i].convertToJPAModel());\n" +
                "        }\n" +
                "\n" +
                "        Facade.getByFilter(entityManager, models, false, 0, 100, null, null, null, null, false, \"AND\", FetchType.LAZY, null, true, \"\");\n" +
                "    }\n" +
                "\n" +
                "    public <D extends DTO> void delete(ArrayList<D> dtos) {\n" +
                "\n" +
                "        if (dtos == null || dtos.size() == 0) {\n" +
                "            return;\n" +
                "        }\n" +
                "\n" +
                "        List<Model> models = new ArrayList<Model>();\n" +
                "        for (D dto : dtos) {\n" +
                "            models.add(dto.convertToJPAModel());\n" +
                "        }\n" +
                "\n" +
                "        Facade.getByFilter(entityManager, models, false, 0, 100, null, null, null, null, false, \"AND\", FetchType.LAZY, null, true, \"\");\n" +
                "    }\n" +
                "\n" +
                "    public <M extends Model> void delete(M... entities) {\n" +
                "\n" +
                "        if (entities == null || entities.length == 0) {\n" +
                "            return;\n" +
                "        }\n" +
                "        List<M> models = new ArrayList<M>();\n" +
                "        int length = entities.length;\n" +
                "        for (int i = 0; i < length; i++) {\n" +
                "            models.add(entities[i]);\n" +
                "        }\n" +
                "\n" +
                "        Facade.getByFilter(entityManager, models, false, 0, 100, null, null, null, null, false, \"AND\", FetchType.LAZY, null, true, \"\");\n" +
                "    }\n" +
                "\n" +
                "    public <T extends Model> JPABuilder jpql(List<T> models) {\n" +
                "        return new JPABuilder(this.entityManager, models, null);\n" +
                "    }\n" +
                "\n" +
                "    public <T extends Model> JPABuilder jpql(T... models) {\n" +
                "\n" +
                "        if (models == null || models.length == 0) {\n" +
                "            return null;\n" +
                "        }\n" +
                "        int length = models.length;\n" +
                "        List<T> tList = new ArrayList<T>();\n" +
                "        for (int i = 0; i < length; i++) {\n" +
                "            tList.add(models[i]);\n" +
                "        }\n" +
                "\n" +
                "        return new JPABuilder(this.entityManager, tList, null);\n" +
                "    }\n" +
                "\n" +
                "    public <T extends DTO> JPABuilder jpql(ArrayList<T> dtos) {\n" +
                "        if (dtos == null || dtos.size() == 0) {\n" +
                "            return null;\n" +
                "        }\n" +
                "        int size = dtos.size();\n" +
                "        T dto = null;\n" +
                "        List<Model> models = new ArrayList<Model>();\n" +
                "        for (int i = 0; i < size; i++) {\n" +
                "            if (dto == null && dtos.get(i) != null) {\n" +
                "                dto = dtos.get(i);\n" +
                "            }\n" +
                "            models.add(dtos.get(i).convertToJPAModel());\n" +
                "        }\n" +
                "        return new JPABuilder(this.entityManager, models, dto);\n" +
                "    }\n" +
                "\n" +
                "    public <T extends DTO> JPABuilder jpql(T... dtos) {\n" +
                "\n" +
                "        if (dtos == null || dtos.length == 0) {\n" +
                "            return null;\n" +
                "        }\n" +
                "        int length = dtos.length;\n" +
                "        T dto = null;\n" +
                "        List<Model> tList = new ArrayList<Model>();\n" +
                "        for (int i = 0; i < length; i++) {\n" +
                "            if (dto == null && dtos[i] != null) {\n" +
                "                dto = dtos[i];\n" +
                "            }\n" +
                "            tList.add(dtos[i].convertToJPAModel());\n" +
                "        }\n" +
                "        return new JPABuilder(this.entityManager, tList, dto);\n" +
                "    }\n" +
                "\n" +
                "    public SQLInterface jpql(String jpql) {\n" +
                "        return new SQLBuilder(entityManager, \"jpql\", jpql + \" \");\n" +
                "    }\n" +
                "\n" +
                "    public SQLInterface sql(String sql) {\n" +
                "        return new SQLBuilder(entityManager, \"sql\", sql + \" \");\n" +
                "    }\n" +
                "\n" +
                "    public ProcedureInterface procedure(String procedureName) {\n" +
                "        return new SQLBuilder(entityManager, \"procedure\", procedureName);\n" +
                "    }\n" +
                "\n" +
                "\n" +
                "}";


        return content;
    }

    public void compile(String sourceCode, String className) {

    }


}
