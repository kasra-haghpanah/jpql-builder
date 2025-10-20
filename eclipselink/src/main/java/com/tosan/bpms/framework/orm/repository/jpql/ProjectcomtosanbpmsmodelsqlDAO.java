package com.tosan.bpms.framework.orm.repository.jpql;

import com.jpql.api.interfaces.*;
import com.jpql.api.interfaces.Parameter;
import javax.persistence.FetchType;
import javax.persistence.EntityManager;
/**
 * Created by kasra.haghpanah on 1/05/2019.
 */
public class ProjectcomtosanbpmsmodelsqlDAO {

  public static java.util.List save(EntityManager entityManager, java.util.List models) {

    if (models == null || models.size() == 0) {
      return null;
    }

    java.util.List afterInsert = new java.util.ArrayList();

    java.util.Map existMap = new java.util.HashMap();
    java.util.List fetch =
        getByFilter(
            entityManager,
            models,
            false,
            0,
            100000,
            null,
            null,
            null,
            null,
            true,
            "AND",
            FetchType.LAZY,
            null,
            false,
            "");

    if (fetch != null && fetch.size() > 0) {
      java.util.Iterator fetchIterator = fetch.iterator();
      while (fetchIterator.hasNext()) {

        com.tosan.bpms.model.sql.Project model =
            (com.tosan.bpms.model.sql.Project) fetchIterator.next();

        if (model != null) {

          String key = model.getId() + "";
          existMap.put(key, model);
        }
      }
    }

    java.util.Iterator iterator = models.iterator();

    while (iterator.hasNext()) {
      com.tosan.bpms.model.sql.Project model = (com.tosan.bpms.model.sql.Project) iterator.next();

      if (model != null) {
        String key = model.getId() + "";
        com.tosan.bpms.model.sql.Project fetchModel =
            (com.tosan.bpms.model.sql.Project) existMap.get(key);

        if (fetchModel == null) { // is not exist
          entityManager.persist(model);
          //entityManager.flush();

        } else {
          model = entityManager.merge(model);
        }

        afterInsert.add(model);
      }
    }

    return afterInsert;
  }

  public static java.util.List getByFilter(
      EntityManager entityManager,
      java.util.List models,
      boolean isFetch,
      int page,
      int size,
      String selectParam,
      Parameter jpql,
      QueryParameter queryParameter,
      Cast cast,
      boolean isExistRecord,
      java.lang.String orAnd,
      FetchType fetchType,
      final java.util.Map statement,
      boolean isDelete,
      String furthermore) {

    if (models == null || models.size() == 0) {
      return null;
    }
    if (isDelete) {
      isExistRecord = false;
      isFetch = false;
      fetchType = FetchType.EAGER;
      selectParam = null;
      cast = null;
    }

    if (page <= 0) page = 0;
    if (size <= 0) size = 100;
    int counter = 0;

    boolean isOpenParentheses = false;
    java.lang.String andCondition = " AND ";
    boolean isChangeKey = false;

    java.lang.String whereQuery = "";
    java.lang.Object whereValue = null;
    java.lang.String fetch = "FETCH";

    boolean addWhereCondition = false;
    com.tosan.bpms.model.sql.Project model = null;
    if (isFetch == false) {
      fetch = "";
    }
    java.util.ArrayList mapVariable = new java.util.ArrayList();
    java.lang.String orAndId = "AND";
    java.lang.String query = "";
    if (fetchType == null) {
      fetchType = FetchType.EAGER;
    }
    java.lang.String basicQuery = "";
    java.lang.String select = "SELECT Project0";
    if (selectParam != null && !selectParam.equals("")) {
      select = selectParam;
    }
    if (!isExistRecord && !isDelete) {
      if (fetchType == FetchType.LAZY) {
        basicQuery = select + " FROM com.tosan.bpms.model.sql.Project Project0  WHERE 1=1 ";
      } else if (fetchType == FetchType.EAGER) {
        basicQuery =
            select
                + " FROM com.tosan.bpms.model.sql.Project Project0  JOIN "
                + fetch
                + " Project0.employees Employee1 JOIN "
                + fetch
                + " Employee1.projects Project2 WHERE 1=1 ";
      }
    } else if (isExistRecord) {
      basicQuery = "SELECT Project0 FROM com.tosan.bpms.model.sql.Project Project0 WHERE 1=1 ";
    } else if (isDelete) {
      if (fetchType == FetchType.LAZY) {
        basicQuery = "DELETE FROM com.tosan.bpms.model.sql.Project Project0 WHERE 1=1 ";
      } else if (fetchType == FetchType.EAGER) {
        basicQuery =
            "DELETE EMP_PROJ,Employee1,Project0 FROM EMP_PROJ EMP_PROJ LEFT JOIN Employee Employee1 ON( EMP_PROJ.EMP_ID = Employee1.ID )  LEFT JOIN Project Project0 ON( EMP_PROJ.PROJ_ID = Project0.ID )  WHERE 1=1 ";
      }
    }
    int modelsLength = models.size();
    java.util.Iterator modelsIterator = models.iterator();
    int j = 0;
    if (modelsLength > 0) {
      while (modelsIterator.hasNext()) {
        model = (com.tosan.bpms.model.sql.Project) modelsIterator.next();
        query = "";
        isOpenParentheses = true;

        if (model != null) {

          if (isExistRecord == true && model.getId() == 0l) {
            model.setId(-1l);
            isChangeKey = true;
          }

          if (model.getId() != 0l) {

            andCondition = " AND ";
            if (isOpenParentheses) {
              andCondition = " ";
            }
            query += " ";
            if (isDelete && fetchType == FetchType.EAGER) {
              whereQuery = " Project0.ID = ? ";
              counter++;
            } else {
              whereQuery = " Project0.id = :Project0id" + j + " ";
            }

            isOpenParentheses = false;
            whereValue = new java.lang.Long("" + model.getId());
            if (isExistRecord && isChangeKey) {
              model.setId(0l);
              isChangeKey = false;
            }

            query +=
                (andCondition
                    + Facade.getStatement(
                        statement,
                        "Project0.id",
                        "Project0id" + j,
                        mapVariable,
                        whereQuery,
                        whereValue,
                        isExistRecord,
                        isDelete,
                        fetchType,
                        counter));
            whereQuery = "";
          }
          if (!isExistRecord
              && !isDelete
              && fetchType == FetchType.EAGER
              && model.getEmployees() != null) {
            int length1 = model.getEmployees().size();

            if (length1 > 0) {
              if (isOpenParentheses) {
                query += "  ( ";
              } else {
                query += " AND ( ";
              }
              isOpenParentheses = true;

              for (int i1 = 0; i1 < length1; i1++) {

                java.lang.String beforeCondition1 = "";
                java.lang.String prefix1 = " OR ";
                if (i1 == 0) {
                  prefix1 = "";
                }
                if (length1 > 1) {

                  query += (prefix1 + "(");
                  isOpenParentheses = true;

                } else {

                  query += (prefix1);
                  if (!prefix1.trim().equals("")) {
                    isOpenParentheses = false;
                  }
                }

                if (model.getEmployees().get(i1).getId() != 0l) {

                  andCondition = " AND ";
                  if (isOpenParentheses) {
                    andCondition = " ";
                  }
                  query += " ";
                  whereValue = new java.lang.Long("" + model.getEmployees().get(i1).getId());
                  if (isDelete && fetchType == FetchType.EAGER) {
                    whereQuery = " Employee1.ID = ? ";
                    counter++;
                  } else {
                    whereQuery = " Employee1.id = :Employee1id" + j + " ";
                  }

                  isOpenParentheses = false;
                  whereValue = new java.lang.Long("" + model.getEmployees().get(i1).getId());
                  query +=
                      (andCondition
                          + Facade.getStatement(
                              statement,
                              "Employee1.id",
                              "Employee1id" + j,
                              mapVariable,
                              whereQuery,
                              whereValue,
                              isExistRecord,
                              isDelete,
                              fetchType,
                              counter));
                  whereQuery = "";
                }
                if (!isExistRecord
                    && !isDelete
                    && fetchType == FetchType.EAGER
                    && model.getEmployees().get(i1).getProjects() != null) {
                  int length2 = model.getEmployees().get(i1).getProjects().size();

                  if (length2 > 0) {
                    if (isOpenParentheses) {
                      query += "  ( ";
                    } else {
                      query += " AND ( ";
                    }
                    isOpenParentheses = true;

                    for (int i2 = 0; i2 < length2; i2++) {

                      java.lang.String beforeCondition2 = "";
                      java.lang.String prefix2 = " OR ";
                      if (i2 == 0) {
                        prefix2 = "";
                      }
                      if (length2 > 1) {

                        query += (prefix2 + "(");
                        isOpenParentheses = true;

                      } else {

                        query += (prefix2);
                        if (!prefix2.trim().equals("")) {
                          isOpenParentheses = false;
                        }
                      }

                      if (model.getEmployees().get(i1).getProjects().get(i2).getId() != 0l) {

                        andCondition = " AND ";
                        if (isOpenParentheses) {
                          andCondition = " ";
                        }
                        query += " ";
                        whereValue =
                            new java.lang.Long(
                                "" + model.getEmployees().get(i1).getProjects().get(i2).getId());
                        if (isDelete && fetchType == FetchType.EAGER) {
                          whereQuery = " Project2.ID = ? ";
                          counter++;
                        } else {
                          whereQuery = " Project2.id = :Project2id" + j + " ";
                        }

                        isOpenParentheses = false;
                        whereValue =
                            new java.lang.Long(
                                "" + model.getEmployees().get(i1).getProjects().get(i2).getId());
                        query +=
                            (andCondition
                                + Facade.getStatement(
                                    statement,
                                    "Project2.id",
                                    "Project2id" + j,
                                    mapVariable,
                                    whereQuery,
                                    whereValue,
                                    isExistRecord,
                                    isDelete,
                                    fetchType,
                                    counter));
                        whereQuery = "";
                      }
                      if (!isExistRecord
                          && !isDelete
                          && true
                          && model.getEmployees().get(i1).getProjects().get(i2).getProjectName()
                              != null) {

                        andCondition = " AND ";
                        if (isOpenParentheses) {
                          andCondition = " ";
                        }
                        query += " ";
                        whereValue =
                            model.getEmployees().get(i1).getProjects().get(i2).getProjectName();
                        if (isDelete && fetchType == FetchType.EAGER) {
                          whereQuery = " Project2.prjctName = ? ";
                          counter++;
                        } else {
                          whereQuery = " Project2.projectName = :Project2projectName" + j + " ";
                        }

                        isOpenParentheses = false;
                        whereValue =
                            model.getEmployees().get(i1).getProjects().get(i2).getProjectName();
                        query +=
                            (andCondition
                                + Facade.getStatement(
                                    statement,
                                    "Project2.projectName",
                                    "Project2projectName" + j,
                                    mapVariable,
                                    whereQuery,
                                    whereValue,
                                    isExistRecord,
                                    isDelete,
                                    fetchType,
                                    counter));
                        whereQuery = "";
                      }
                      if (!isExistRecord
                          && !isDelete
                          && true
                          && model.getEmployees().get(i1).getProjects().get(i2).getProjectDate()
                              != null) {

                        andCondition = " AND ";
                        if (isOpenParentheses) {
                          andCondition = " ";
                        }
                        query += " ";
                        whereValue =
                            model.getEmployees().get(i1).getProjects().get(i2).getProjectDate();
                        if (isDelete && fetchType == FetchType.EAGER) {
                          whereQuery = " Project2.prjctDate = ? ";
                          counter++;
                        } else {
                          whereQuery = " Project2.projectDate = :Project2projectDate" + j + " ";
                        }

                        isOpenParentheses = false;
                        whereValue =
                            model.getEmployees().get(i1).getProjects().get(i2).getProjectDate();
                        query +=
                            (andCondition
                                + Facade.getStatement(
                                    statement,
                                    "Project2.projectDate",
                                    "Project2projectDate" + j,
                                    mapVariable,
                                    whereQuery,
                                    whereValue,
                                    isExistRecord,
                                    isDelete,
                                    fetchType,
                                    counter));
                        whereQuery = "";
                      }
                      if (length2 > 1) {
                        query += " ) ";
                      }
                    }

                    query += ")";
                    isOpenParentheses = false;
                  }
                }

                if (!isExistRecord
                    && !isDelete
                    && true
                    && model.getEmployees().get(i1).getAge() != null) {

                  andCondition = " AND ";
                  if (isOpenParentheses) {
                    andCondition = " ";
                  }
                  query += " ";
                  whereValue = model.getEmployees().get(i1).getAge();
                  if (isDelete && fetchType == FetchType.EAGER) {
                    whereQuery = " Employee1.age = ? ";
                    counter++;
                  } else {
                    whereQuery = " Employee1.age = :Employee1age" + j + " ";
                  }

                  isOpenParentheses = false;
                  whereValue = model.getEmployees().get(i1).getAge();
                  query +=
                      (andCondition
                          + Facade.getStatement(
                              statement,
                              "Employee1.age",
                              "Employee1age" + j,
                              mapVariable,
                              whereQuery,
                              whereValue,
                              isExistRecord,
                              isDelete,
                              fetchType,
                              counter));
                  whereQuery = "";
                }
                if (!isExistRecord
                    && !isDelete
                    && true
                    && model.getEmployees().get(i1).getFirstName() != null) {

                  andCondition = " AND ";
                  if (isOpenParentheses) {
                    andCondition = " ";
                  }
                  query += " ";
                  whereValue = model.getEmployees().get(i1).getFirstName();
                  if (isDelete && fetchType == FetchType.EAGER) {
                    whereQuery = " Employee1.firstName = ? ";
                    counter++;
                  } else {
                    whereQuery = " Employee1.firstName = :Employee1firstName" + j + " ";
                  }

                  isOpenParentheses = false;
                  whereValue = model.getEmployees().get(i1).getFirstName();
                  query +=
                      (andCondition
                          + Facade.getStatement(
                              statement,
                              "Employee1.firstName",
                              "Employee1firstName" + j,
                              mapVariable,
                              whereQuery,
                              whereValue,
                              isExistRecord,
                              isDelete,
                              fetchType,
                              counter));
                  whereQuery = "";
                }
                if (!isExistRecord
                    && !isDelete
                    && true
                    && model.getEmployees().get(i1).getLastName() != null) {

                  andCondition = " AND ";
                  if (isOpenParentheses) {
                    andCondition = " ";
                  }
                  query += " ";
                  whereValue = model.getEmployees().get(i1).getLastName();
                  if (isDelete && fetchType == FetchType.EAGER) {
                    whereQuery = " Employee1.lastName = ? ";
                    counter++;
                  } else {
                    whereQuery = " Employee1.lastName = :Employee1lastName" + j + " ";
                  }

                  isOpenParentheses = false;
                  whereValue = model.getEmployees().get(i1).getLastName();
                  query +=
                      (andCondition
                          + Facade.getStatement(
                              statement,
                              "Employee1.lastName",
                              "Employee1lastName" + j,
                              mapVariable,
                              whereQuery,
                              whereValue,
                              isExistRecord,
                              isDelete,
                              fetchType,
                              counter));
                  whereQuery = "";
                }
                if (!isExistRecord
                    && !isDelete
                    && true
                    && model.getEmployees().get(i1).getVersion() != 0) {

                  andCondition = " AND ";
                  if (isOpenParentheses) {
                    andCondition = " ";
                  }
                  query += " ";
                  whereValue =
                      new java.lang.Integer("" + model.getEmployees().get(i1).getVersion());
                  if (isDelete && fetchType == FetchType.EAGER) {
                    whereQuery = " Employee1.version = ? ";
                    counter++;
                  } else {
                    whereQuery = " Employee1.version = :Employee1version" + j + " ";
                  }

                  isOpenParentheses = false;
                  whereValue =
                      new java.lang.Integer("" + model.getEmployees().get(i1).getVersion());
                  query +=
                      (andCondition
                          + Facade.getStatement(
                              statement,
                              "Employee1.version",
                              "Employee1version" + j,
                              mapVariable,
                              whereQuery,
                              whereValue,
                              isExistRecord,
                              isDelete,
                              fetchType,
                              counter));
                  whereQuery = "";
                }
                if (length1 > 1) {
                  query += " ) ";
                }
              }

              query += ")";
              isOpenParentheses = false;
            }
          }

          if (!isExistRecord && true && model.getProjectName() != null) {

            andCondition = " AND ";
            if (isOpenParentheses) {
              andCondition = " ";
            }
            query += " ";
            if (isDelete && fetchType == FetchType.EAGER) {
              whereQuery = " Project0.prjctName = ? ";
              counter++;
            } else {
              whereQuery = " Project0.projectName = :Project0projectName" + j + " ";
            }

            isOpenParentheses = false;
            whereValue = model.getProjectName();
            query +=
                (andCondition
                    + Facade.getStatement(
                        statement,
                        "Project0.projectName",
                        "Project0projectName" + j,
                        mapVariable,
                        whereQuery,
                        whereValue,
                        isExistRecord,
                        isDelete,
                        fetchType,
                        counter));
            whereQuery = "";
          }
          if (!isExistRecord && true && model.getProjectDate() != null) {

            andCondition = " AND ";
            if (isOpenParentheses) {
              andCondition = " ";
            }
            query += " ";
            if (isDelete && fetchType == FetchType.EAGER) {
              whereQuery = " Project0.prjctDate = ? ";
              counter++;
            } else {
              whereQuery = " Project0.projectDate = :Project0projectDate" + j + " ";
            }

            isOpenParentheses = false;
            whereValue = model.getProjectDate();
            query +=
                (andCondition
                    + Facade.getStatement(
                        statement,
                        "Project0.projectDate",
                        "Project0projectDate" + j,
                        mapVariable,
                        whereQuery,
                        whereValue,
                        isExistRecord,
                        isDelete,
                        fetchType,
                        counter));
            whereQuery = "";
          }
        }
        if (!query.equals("")) {
          String postfix = "";
          String prefix = "";
          if (!addWhereCondition) {
            addWhereCondition = true;
            if (modelsLength > 1) {
              prefix = "(";
            }
            basicQuery = (basicQuery + " AND ( " + prefix + query + " ) ");
          } else {
            postfix = "";
            if (modelsLength > 1 && !modelsIterator.hasNext()) {
              postfix = ")";
            }
            basicQuery = (basicQuery + " OR ( " + query + postfix + " ) ");
          }
        }
        isOpenParentheses = false;
        j++;
      }
    }

    query = basicQuery;
    javax.persistence.Query query1 = null;
    if (isDelete && fetchType == FetchType.EAGER) {
      query1 = entityManager.createNativeQuery(query);
    } else {
      query1 = entityManager.createQuery(query + furthermore);
    }
    if (!isDelete) {
      query1.setFirstResult(page * size);
      query1.setMaxResults(size);
    }
    java.util.Iterator iterator = mapVariable.iterator();

    if (iterator != null) {
      while (iterator.hasNext()) {
        java.lang.String variableName = (java.lang.String) iterator.next();
        java.lang.Object counditionValue = iterator.next();
        query1.setParameter(variableName, counditionValue);
      }
    }

    if (queryParameter != null) {
      queryParameter.createCondition(query1);
    }
    java.util.List result = null;
    if (!isDelete) {
      result = query1.getResultList();
      if (cast != null
          && result != null
          && result.size() > 0
          && (!select.trim().equals("Project0") && select.indexOf("new") == -1)) {
        boolean isObject = true;
        if (result.get(0) instanceof Object[]) {
          isObject = false;
        }
        java.util.List resultCast = new java.util.ArrayList();
        for (int i = 0; i < result.size(); i++) {
          Object[] record;
          if (!isObject) {
            record = (Object[]) result.get(i);
          } else {
            record = new Object[] {result.get(i)};
          }

          java.lang.Object convert = (java.lang.Object) cast.to(record);
          if (convert != null) {
            resultCast.add(convert);
          }
        }
        result = resultCast;
      }
    }
    if (isDelete) {
      query1.executeUpdate();
    }
    if (jpql != null) {
      java.util.Set set = query1.getParameters();
      java.util.Iterator iterator1 = set.iterator();
      String variables = "";

      if (iterator1.hasNext()) {
        variables = "\n\n************ Variables ************\n";
        while (iterator1.hasNext()) {
          javax.persistence.Parameter key = (javax.persistence.Parameter) iterator1.next();

          Object name = key.getPosition();
          Object value = null;
          if (name == null) {
            name = key.getName();
          }

          try {
            value = query1.getParameterValue(key);
          } catch (IllegalStateException e) {
          }

          if (key.getParameterType().equals(java.lang.String.class)) {
            variables += (name + " : '" + value + "'\n");
          } else {
            variables += (name + " : " + value + "\n");
          }
        }
        variables += "************ Variables ************";
      }
      jpql.get(query + furthermore + variables);
    }

    return result;
  }
}
