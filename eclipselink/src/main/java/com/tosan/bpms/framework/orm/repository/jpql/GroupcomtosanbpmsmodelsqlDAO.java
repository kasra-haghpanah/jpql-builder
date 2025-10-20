package com.tosan.bpms.framework.orm.repository.jpql;

import com.jpql.api.interfaces.*;
import com.jpql.api.interfaces.Parameter;
import javax.persistence.FetchType;
import javax.persistence.EntityManager;
/**
 * Created by kasra.haghpanah on 1/05/2019.
 */
public class GroupcomtosanbpmsmodelsqlDAO {

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

        com.tosan.bpms.model.sql.Group model =
            (com.tosan.bpms.model.sql.Group) fetchIterator.next();

        if (model != null) {

          String key = model.getId() + "";
          existMap.put(key, model);
        }
      }
    }

    java.util.Iterator iterator = models.iterator();

    while (iterator.hasNext()) {
      com.tosan.bpms.model.sql.Group model = (com.tosan.bpms.model.sql.Group) iterator.next();

      if (model != null) {
        String key = model.getId() + "";
        com.tosan.bpms.model.sql.Group fetchModel =
            (com.tosan.bpms.model.sql.Group) existMap.get(key);

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
    com.tosan.bpms.model.sql.Group model = null;
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
    java.lang.String select = "SELECT Group0";
    if (selectParam != null && !selectParam.equals("")) {
      select = selectParam;
    }
    if (!isExistRecord && !isDelete) {
      if (fetchType == FetchType.LAZY) {
        basicQuery =
            select
                + " FROM com.tosan.bpms.model.sql.Group Group0  JOIN "
                + fetch
                + " Group0.user User1 WHERE 1=1 ";
      } else if (fetchType == FetchType.EAGER) {
        basicQuery =
            select
                + " FROM com.tosan.bpms.model.sql.Group Group0  JOIN "
                + fetch
                + " Group0.user User1 LEFT JOIN "
                + fetch
                + " User1.groups Group2 WHERE 1=1 ";
      }
    } else if (isExistRecord) {
      basicQuery = "SELECT Group0 FROM com.tosan.bpms.model.sql.Group Group0 WHERE 1=1 ";
    } else if (isDelete) {
      if (fetchType == FetchType.LAZY) {
        basicQuery = "DELETE FROM com.tosan.bpms.model.sql.Group Group0 WHERE 1=1 ";
      } else if (fetchType == FetchType.EAGER) {
        basicQuery = "DELETE Group0 FROM groups Group0 WHERE 1=1 ";
      }
    }
    int modelsLength = models.size();
    java.util.Iterator modelsIterator = models.iterator();
    int j = 0;
    if (modelsLength > 0) {
      while (modelsIterator.hasNext()) {
        model = (com.tosan.bpms.model.sql.Group) modelsIterator.next();
        query = "";
        isOpenParentheses = true;

        if (model != null) {

          if (isExistRecord == true && model.getId() == 0) {
            model.setId(-1);
            isChangeKey = true;
          }

          if (model.getId() != 0) {

            andCondition = " AND ";
            if (isOpenParentheses) {
              andCondition = " ";
            }
            query += " ";
            if (isDelete && fetchType == FetchType.EAGER) {
              whereQuery = " Group0.id = ? ";
              counter++;
            } else {
              whereQuery = " Group0.id = :Group0id" + j + " ";
            }

            isOpenParentheses = false;
            whereValue = new java.lang.Integer("" + model.getId());
            if (isExistRecord && isChangeKey) {
              model.setId(0);
              isChangeKey = false;
            }

            query +=
                (andCondition
                    + Facade.getStatement(
                        statement,
                        "Group0.id",
                        "Group0id" + j,
                        mapVariable,
                        whereQuery,
                        whereValue,
                        isExistRecord,
                        isDelete,
                        fetchType,
                        counter));
            whereQuery = "";
          }
          if (!isExistRecord && !isDelete && model.getUser() != null) {

            if (model.getUser().getId() != 0) {

              andCondition = " AND ";
              if (isOpenParentheses) {
                andCondition = " ";
              }
              query += " ";
              if (isDelete && fetchType == FetchType.EAGER) {
                whereQuery = " User1.id = ? ";
                counter++;
              } else {
                whereQuery = " User1.id = :User1id" + j + " ";
              }

              isOpenParentheses = false;
              whereValue = new java.lang.Integer("" + model.getUser().getId());
              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "User1.id",
                          "User1id" + j,
                          mapVariable,
                          whereQuery,
                          whereValue,
                          isExistRecord,
                          isDelete,
                          fetchType,
                          counter));
              whereQuery = "";
            }
            if (!isExistRecord && !isDelete && true && model.getUser().getUsername() != null) {

              andCondition = " AND ";
              if (isOpenParentheses) {
                andCondition = " ";
              }
              query += " ";
              if (isExistRecord || isDelete) {
                if (isDelete && fetchType == FetchType.EAGER) {
                  whereQuery = " User1.username = ? ";
                  counter++;
                } else {
                  whereQuery = " User1.username = :User1username" + j + " ";
                }
              } else {

                whereQuery = " User1.username LIKE :User1username" + j + " ";
              }

              isOpenParentheses = false;
              if (isExistRecord || isDelete) {
                if (isDelete && fetchType == FetchType.EAGER) {
                  whereQuery = " User1.username = ? ";
                  counter++;
                } else {
                  whereValue = model.getUser().getUsername();
                }
              } else {
                whereValue = "%" + model.getUser().getUsername() + "%";
              }

              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "User1.username",
                          "User1username" + j,
                          mapVariable,
                          whereQuery,
                          whereValue,
                          isExistRecord,
                          isDelete,
                          fetchType,
                          counter));
              whereQuery = "";
            }
            if (!isExistRecord && !isDelete && true && model.getUser().getPassword() != null) {

              andCondition = " AND ";
              if (isOpenParentheses) {
                andCondition = " ";
              }
              query += " ";
              if (isDelete && fetchType == FetchType.EAGER) {
                whereQuery = " User1.password = ? ";
                counter++;
              } else {
                whereQuery = " User1.password = :User1password" + j + " ";
              }

              isOpenParentheses = false;
              whereValue = model.getUser().getPassword();
              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "User1.password",
                          "User1password" + j,
                          mapVariable,
                          whereQuery,
                          whereValue,
                          isExistRecord,
                          isDelete,
                          fetchType,
                          counter));
              whereQuery = "";
            }
            if (!isExistRecord && !isDelete && true && model.getUser().getVersion() != 0) {

              andCondition = " AND ";
              if (isOpenParentheses) {
                andCondition = " ";
              }
              query += " ";
              if (isDelete && fetchType == FetchType.EAGER) {
                whereQuery = " User1.version = ? ";
                counter++;
              } else {
                whereQuery = " User1.version = :User1version" + j + " ";
              }

              isOpenParentheses = false;
              whereValue = new java.lang.Integer("" + model.getUser().getVersion());
              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "User1.version",
                          "User1version" + j,
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
                && model.getUser().getGroups() != null) {
              int length2 = model.getUser().getGroups().size();

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

                  if (model.getUser().getGroups().get(i2).getId() != 0) {

                    andCondition = " AND ";
                    if (isOpenParentheses) {
                      andCondition = " ";
                    }
                    query += " ";
                    whereValue =
                        new java.lang.Integer("" + model.getUser().getGroups().get(i2).getId());
                    if (isDelete && fetchType == FetchType.EAGER) {
                      whereQuery = " Group2.id = ? ";
                      counter++;
                    } else {
                      whereQuery = " Group2.id = :Group2id" + j + " ";
                    }

                    isOpenParentheses = false;
                    whereValue =
                        new java.lang.Integer("" + model.getUser().getGroups().get(i2).getId());
                    query +=
                        (andCondition
                            + Facade.getStatement(
                                statement,
                                "Group2.id",
                                "Group2id" + j,
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
                      && model.getUser().getGroups().get(i2).getRole() != null) {

                    andCondition = " AND ";
                    if (isOpenParentheses) {
                      andCondition = " ";
                    }
                    query += " ";
                    whereValue = model.getUser().getGroups().get(i2).getRole();
                    if (isDelete && fetchType == FetchType.EAGER) {
                      whereQuery = " Group2.role = ? ";
                      counter++;
                    } else {
                      whereQuery = " Group2.role = :Group2role" + j + " ";
                    }

                    isOpenParentheses = false;
                    whereValue = model.getUser().getGroups().get(i2).getRole();
                    query +=
                        (andCondition
                            + Facade.getStatement(
                                statement,
                                "Group2.role",
                                "Group2role" + j,
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
                      && model.getUser().getGroups().get(i2).getVersion() != 0) {

                    andCondition = " AND ";
                    if (isOpenParentheses) {
                      andCondition = " ";
                    }
                    query += " ";
                    whereValue =
                        new java.lang.Integer(
                            "" + model.getUser().getGroups().get(i2).getVersion());
                    if (isDelete && fetchType == FetchType.EAGER) {
                      whereQuery = " Group2.version = ? ";
                      counter++;
                    } else {
                      whereQuery = " Group2.version = :Group2version" + j + " ";
                    }

                    isOpenParentheses = false;
                    whereValue =
                        new java.lang.Integer(
                            "" + model.getUser().getGroups().get(i2).getVersion());
                    query +=
                        (andCondition
                            + Facade.getStatement(
                                statement,
                                "Group2.version",
                                "Group2version" + j,
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
            if (!isDelete && model.getUser().getAddress() != null) {
              if (!isDelete && true && model.getUser().getAddress().getAddressLine1() != null) {

                andCondition = " AND ";
                if (isOpenParentheses) {
                  andCondition = " ";
                }
                query += " ";
                if (isDelete && fetchType == FetchType.EAGER) {
                  whereQuery = " User1.addressLine1 = ? ";
                  counter++;
                } else {
                  whereQuery = " User1.address.addressLine1 = :User1addressaddressLine1" + j + " ";
                }

                isOpenParentheses = false;
                whereValue = model.getUser().getAddress().getAddressLine1();
                query +=
                    (andCondition
                        + Facade.getStatement(
                            statement,
                            "User1.address.addressLine1",
                            "User1addressaddressLine1" + j,
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
                  && model.getUser().getAddress().getAddressLine2() != null) {

                andCondition = " AND ";
                if (isOpenParentheses) {
                  andCondition = " ";
                }
                query += " ";
                if (isDelete && fetchType == FetchType.EAGER) {
                  whereQuery = " User1.addressLine2 = ? ";
                  counter++;
                } else {
                  whereQuery = " User1.address.addressLine2 = :User1addressaddressLine2" + j + " ";
                }

                isOpenParentheses = false;
                whereValue = model.getUser().getAddress().getAddressLine2();
                query +=
                    (andCondition
                        + Facade.getStatement(
                            statement,
                            "User1.address.addressLine2",
                            "User1addressaddressLine2" + j,
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
                  && model.getUser().getAddress().getCity() != null) {

                andCondition = " AND ";
                if (isOpenParentheses) {
                  andCondition = " ";
                }
                query += " ";
                if (isDelete && fetchType == FetchType.EAGER) {
                  whereQuery = " User1.city = ? ";
                  counter++;
                } else {
                  whereQuery = " User1.address.city = :User1addresscity" + j + " ";
                }

                isOpenParentheses = false;
                whereValue = model.getUser().getAddress().getCity();
                query +=
                    (andCondition
                        + Facade.getStatement(
                            statement,
                            "User1.address.city",
                            "User1addresscity" + j,
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
                  && model.getUser().getAddress().getState() != null) {

                andCondition = " AND ";
                if (isOpenParentheses) {
                  andCondition = " ";
                }
                query += " ";
                if (isDelete && fetchType == FetchType.EAGER) {
                  whereQuery = " User1.state = ? ";
                  counter++;
                } else {
                  whereQuery = " User1.address.state = :User1addressstate" + j + " ";
                }

                isOpenParentheses = false;
                whereValue = model.getUser().getAddress().getState();
                query +=
                    (andCondition
                        + Facade.getStatement(
                            statement,
                            "User1.address.state",
                            "User1addressstate" + j,
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
                  && model.getUser().getAddress().getCountry() != null) {

                andCondition = " AND ";
                if (isOpenParentheses) {
                  andCondition = " ";
                }
                query += " ";
                if (isDelete && fetchType == FetchType.EAGER) {
                  whereQuery = " User1.country = ? ";
                  counter++;
                } else {
                  whereQuery = " User1.address.country = :User1addresscountry" + j + " ";
                }

                isOpenParentheses = false;
                whereValue = model.getUser().getAddress().getCountry();
                query +=
                    (andCondition
                        + Facade.getStatement(
                            statement,
                            "User1.address.country",
                            "User1addresscountry" + j,
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
                  && model.getUser().getAddress().getZipCode() != null) {

                andCondition = " AND ";
                if (isOpenParentheses) {
                  andCondition = " ";
                }
                query += " ";
                if (isDelete && fetchType == FetchType.EAGER) {
                  whereQuery = " User1.zipCode = ? ";
                  counter++;
                } else {
                  whereQuery = " User1.address.zipCode = :User1addresszipCode" + j + " ";
                }

                isOpenParentheses = false;
                whereValue = model.getUser().getAddress().getZipCode();
                query +=
                    (andCondition
                        + Facade.getStatement(
                            statement,
                            "User1.address.zipCode",
                            "User1addresszipCode" + j,
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
          }
          if (!isExistRecord && true && model.getRole() != null) {

            andCondition = " AND ";
            if (isOpenParentheses) {
              andCondition = " ";
            }
            query += " ";
            if (isDelete && fetchType == FetchType.EAGER) {
              whereQuery = " Group0.role = ? ";
              counter++;
            } else {
              whereQuery = " Group0.role = :Group0role" + j + " ";
            }

            isOpenParentheses = false;
            whereValue = model.getRole();
            query +=
                (andCondition
                    + Facade.getStatement(
                        statement,
                        "Group0.role",
                        "Group0role" + j,
                        mapVariable,
                        whereQuery,
                        whereValue,
                        isExistRecord,
                        isDelete,
                        fetchType,
                        counter));
            whereQuery = "";
          }
          if (!isExistRecord && true && model.getVersion() != 0) {

            andCondition = " AND ";
            if (isOpenParentheses) {
              andCondition = " ";
            }
            query += " ";
            if (isDelete && fetchType == FetchType.EAGER) {
              whereQuery = " Group0.version = ? ";
              counter++;
            } else {
              whereQuery = " Group0.version = :Group0version" + j + " ";
            }

            isOpenParentheses = false;
            whereValue = new java.lang.Integer("" + model.getVersion());
            query +=
                (andCondition
                    + Facade.getStatement(
                        statement,
                        "Group0.version",
                        "Group0version" + j,
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
          && (!select.trim().equals("Group0") && select.indexOf("new") == -1)) {
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
