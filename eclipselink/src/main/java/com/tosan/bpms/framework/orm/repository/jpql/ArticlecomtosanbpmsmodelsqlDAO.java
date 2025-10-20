package com.tosan.bpms.framework.orm.repository.jpql;

import com.jpql.api.interfaces.*;
import com.jpql.api.interfaces.Parameter;
import javax.persistence.FetchType;
import javax.persistence.EntityManager;
/**
 * Created by kasra.haghpanah on 1/05/2019.
 */
public class ArticlecomtosanbpmsmodelsqlDAO {

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

        com.tosan.bpms.model.sql.Article model =
            (com.tosan.bpms.model.sql.Article) fetchIterator.next();

        if (model != null) {

          String key = model.getArticleId() + "";
          existMap.put(key, model);
        }
      }
    }

    java.util.Iterator iterator = models.iterator();

    while (iterator.hasNext()) {
      com.tosan.bpms.model.sql.Article model = (com.tosan.bpms.model.sql.Article) iterator.next();

      if (model != null) {
        String key = model.getArticleId() + "";
        com.tosan.bpms.model.sql.Article fetchModel =
            (com.tosan.bpms.model.sql.Article) existMap.get(key);

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
    com.tosan.bpms.model.sql.Article model = null;
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
    java.lang.String select = "SELECT Article0";
    if (selectParam != null && !selectParam.equals("")) {
      select = selectParam;
    }
    if (!isExistRecord && !isDelete) {
      if (fetchType == FetchType.LAZY) {
        basicQuery =
            select
                + " FROM com.tosan.bpms.model.sql.Article Article0  JOIN "
                + fetch
                + " Article0.industry Industry1 JOIN "
                + fetch
                + " Article0.economic Economic2 JOIN "
                + fetch
                + " Article0.measurement Measurment3 WHERE 1=1 ";
      } else if (fetchType == FetchType.EAGER) {
        basicQuery =
            select
                + " FROM com.tosan.bpms.model.sql.Article Article0  JOIN "
                + fetch
                + " Article0.industry Industry1 JOIN "
                + fetch
                + " Article0.economic Economic2 JOIN "
                + fetch
                + " Article0.measurement Measurment3 WHERE 1=1 ";
      }
    } else if (isExistRecord) {
      basicQuery = "SELECT Article0 FROM com.tosan.bpms.model.sql.Article Article0 WHERE 1=1 ";
    } else if (isDelete) {
      if (fetchType == FetchType.LAZY) {
        basicQuery = "DELETE FROM com.tosan.bpms.model.sql.Article Article0 WHERE 1=1 ";
      } else if (fetchType == FetchType.EAGER) {
        basicQuery = "DELETE Article0 FROM Article Article0 WHERE 1=1 ";
      }
    }
    int modelsLength = models.size();
    java.util.Iterator modelsIterator = models.iterator();
    int j = 0;
    if (modelsLength > 0) {
      while (modelsIterator.hasNext()) {
        model = (com.tosan.bpms.model.sql.Article) modelsIterator.next();
        query = "";
        isOpenParentheses = true;

        if (model != null) {

          if (isExistRecord == true && model.getArticleId() == null) {
            model.setArticleId(new java.lang.Integer("-1"));
            isChangeKey = true;
          }

          if (model.getArticleId() != null) {

            andCondition = " AND ";
            if (isOpenParentheses) {
              andCondition = " ";
            }
            query += " ";
            if (isDelete && fetchType == FetchType.EAGER) {
              whereQuery = " Article0.id = ? ";
              counter++;
            } else {
              whereQuery = " Article0.articleId = :Article0articleId" + j + " ";
            }

            isOpenParentheses = false;
            whereValue = model.getArticleId();
            if (isExistRecord && isChangeKey) {
              model.setArticleId(null);
              isChangeKey = false;
            }

            query +=
                (andCondition
                    + Facade.getStatement(
                        statement,
                        "Article0.articleId",
                        "Article0articleId" + j,
                        mapVariable,
                        whereQuery,
                        whereValue,
                        isExistRecord,
                        isDelete,
                        fetchType,
                        counter));
            whereQuery = "";
          }
          if (!isExistRecord && !isDelete && model.getIndustry() != null) {

            if (model.getIndustry().getIndustryId() != null) {

              andCondition = " AND ";
              if (isOpenParentheses) {
                andCondition = " ";
              }
              query += " ";
              if (isExistRecord || isDelete) {
                if (isDelete && fetchType == FetchType.EAGER) {
                  whereQuery = " Industry1.id = ? ";
                  counter++;
                } else {
                  whereQuery = " Industry1.industryId = :Industry1industryId" + j + " ";
                }
              } else {

                whereQuery = " Industry1.industryId LIKE :Industry1industryId" + j + " ";
              }

              isOpenParentheses = false;
              if (isExistRecord || isDelete) {
                if (isDelete && fetchType == FetchType.EAGER) {
                  whereQuery = " Industry1.id = ? ";
                  counter++;
                } else {
                  whereValue = model.getIndustry().getIndustryId();
                }
              } else {
                whereValue = "%" + model.getIndustry().getIndustryId() + "%";
              }

              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "Industry1.industryId",
                          "Industry1industryId" + j,
                          mapVariable,
                          whereQuery,
                          whereValue,
                          isExistRecord,
                          isDelete,
                          fetchType,
                          counter));
              whereQuery = "";
            }
            if (!isExistRecord && !isDelete && true && model.getIndustry().getFaTitle() != null) {

              andCondition = " AND ";
              if (isOpenParentheses) {
                andCondition = " ";
              }
              query += " ";
              if (isDelete && fetchType == FetchType.EAGER) {
                whereQuery = " Industry1.faTitle = ? ";
                counter++;
              } else {
                whereQuery = " Industry1.faTitle = :Industry1faTitle" + j + " ";
              }

              isOpenParentheses = false;
              whereValue = model.getIndustry().getFaTitle();
              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "Industry1.faTitle",
                          "Industry1faTitle" + j,
                          mapVariable,
                          whereQuery,
                          whereValue,
                          isExistRecord,
                          isDelete,
                          fetchType,
                          counter));
              whereQuery = "";
            }
            if (!isExistRecord && !isDelete && true && model.getIndustry().getEnTitle() != null) {

              andCondition = " AND ";
              if (isOpenParentheses) {
                andCondition = " ";
              }
              query += " ";
              if (isDelete && fetchType == FetchType.EAGER) {
                whereQuery = " Industry1.enTitle = ? ";
                counter++;
              } else {
                whereQuery = " Industry1.enTitle = :Industry1enTitle" + j + " ";
              }

              isOpenParentheses = false;
              whereValue = model.getIndustry().getEnTitle();
              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "Industry1.enTitle",
                          "Industry1enTitle" + j,
                          mapVariable,
                          whereQuery,
                          whereValue,
                          isExistRecord,
                          isDelete,
                          fetchType,
                          counter));
              whereQuery = "";
            }
            if (!isExistRecord && !isDelete && true && model.getIndustry().getVersion() != 0) {

              andCondition = " AND ";
              if (isOpenParentheses) {
                andCondition = " ";
              }
              query += " ";
              if (isDelete && fetchType == FetchType.EAGER) {
                whereQuery = " Industry1.VERSION = ? ";
                counter++;
              } else {
                whereQuery = " Industry1.version = :Industry1version" + j + " ";
              }

              isOpenParentheses = false;
              whereValue = new java.lang.Integer("" + model.getIndustry().getVersion());
              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "Industry1.version",
                          "Industry1version" + j,
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
          if (!isExistRecord && !isDelete && model.getEconomic() != null) {

            if (model.getEconomic().getEconomicId() != null) {

              andCondition = " AND ";
              if (isOpenParentheses) {
                andCondition = " ";
              }
              query += " ";
              if (isDelete && fetchType == FetchType.EAGER) {
                whereQuery = " Economic2.id = ? ";
                counter++;
              } else {
                whereQuery = " Economic2.economicId = :Economic2economicId" + j + " ";
              }

              isOpenParentheses = false;
              whereValue = model.getEconomic().getEconomicId();
              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "Economic2.economicId",
                          "Economic2economicId" + j,
                          mapVariable,
                          whereQuery,
                          whereValue,
                          isExistRecord,
                          isDelete,
                          fetchType,
                          counter));
              whereQuery = "";
            }
            if (!isExistRecord && !isDelete && true && model.getEconomic().getFaTitle() != null) {

              andCondition = " AND ";
              if (isOpenParentheses) {
                andCondition = " ";
              }
              query += " ";
              if (isDelete && fetchType == FetchType.EAGER) {
                whereQuery = " Economic2.faTitle = ? ";
                counter++;
              } else {
                whereQuery = " Economic2.faTitle = :Economic2faTitle" + j + " ";
              }

              isOpenParentheses = false;
              whereValue = model.getEconomic().getFaTitle();
              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "Economic2.faTitle",
                          "Economic2faTitle" + j,
                          mapVariable,
                          whereQuery,
                          whereValue,
                          isExistRecord,
                          isDelete,
                          fetchType,
                          counter));
              whereQuery = "";
            }
            if (!isExistRecord && !isDelete && true && model.getEconomic().getEnTitle() != null) {

              andCondition = " AND ";
              if (isOpenParentheses) {
                andCondition = " ";
              }
              query += " ";
              if (isDelete && fetchType == FetchType.EAGER) {
                whereQuery = " Economic2.enTitle = ? ";
                counter++;
              } else {
                whereQuery = " Economic2.enTitle = :Economic2enTitle" + j + " ";
              }

              isOpenParentheses = false;
              whereValue = model.getEconomic().getEnTitle();
              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "Economic2.enTitle",
                          "Economic2enTitle" + j,
                          mapVariable,
                          whereQuery,
                          whereValue,
                          isExistRecord,
                          isDelete,
                          fetchType,
                          counter));
              whereQuery = "";
            }
            if (!isExistRecord && !isDelete && true && model.getEconomic().getVersion() != 0) {

              andCondition = " AND ";
              if (isOpenParentheses) {
                andCondition = " ";
              }
              query += " ";
              if (isDelete && fetchType == FetchType.EAGER) {
                whereQuery = " Economic2.VERSION = ? ";
                counter++;
              } else {
                whereQuery = " Economic2.version = :Economic2version" + j + " ";
              }

              isOpenParentheses = false;
              whereValue = new java.lang.Integer("" + model.getEconomic().getVersion());
              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "Economic2.version",
                          "Economic2version" + j,
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
          if (!isExistRecord && !isDelete && model.getMeasurement() != null) {

            if (model.getMeasurement().getMeasurementId() != null) {

              andCondition = " AND ";
              if (isOpenParentheses) {
                andCondition = " ";
              }
              query += " ";
              if (isDelete && fetchType == FetchType.EAGER) {
                whereQuery = " Measurment3.id = ? ";
                counter++;
              } else {
                whereQuery = " Measurment3.measurementId = :Measurment3measurementId" + j + " ";
              }

              isOpenParentheses = false;
              whereValue = model.getMeasurement().getMeasurementId();
              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "Measurment3.measurementId",
                          "Measurment3measurementId" + j,
                          mapVariable,
                          whereQuery,
                          whereValue,
                          isExistRecord,
                          isDelete,
                          fetchType,
                          counter));
              whereQuery = "";
            }
            if (!isExistRecord && !isDelete && true && model.getMeasurement().getName() != null) {

              andCondition = " AND ";
              if (isOpenParentheses) {
                andCondition = " ";
              }
              query += " ";
              if (isDelete && fetchType == FetchType.EAGER) {
                whereQuery = " Measurment3.name = ? ";
                counter++;
              } else {
                whereQuery = " Measurment3.name = :Measurment3name" + j + " ";
              }

              isOpenParentheses = false;
              whereValue = model.getMeasurement().getName();
              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "Measurment3.name",
                          "Measurment3name" + j,
                          mapVariable,
                          whereQuery,
                          whereValue,
                          isExistRecord,
                          isDelete,
                          fetchType,
                          counter));
              whereQuery = "";
            }
            if (!isExistRecord && !isDelete && true && model.getMeasurement().getVersion() != 0) {

              andCondition = " AND ";
              if (isOpenParentheses) {
                andCondition = " ";
              }
              query += " ";
              if (isDelete && fetchType == FetchType.EAGER) {
                whereQuery = " Measurment3.VERSION = ? ";
                counter++;
              } else {
                whereQuery = " Measurment3.version = :Measurment3version" + j + " ";
              }

              isOpenParentheses = false;
              whereValue = new java.lang.Integer("" + model.getMeasurement().getVersion());
              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "Measurment3.version",
                          "Measurment3version" + j,
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
          if (!isExistRecord && true && model.getFaTitle() != null) {

            andCondition = " AND ";
            if (isOpenParentheses) {
              andCondition = " ";
            }
            query += " ";
            if (isDelete && fetchType == FetchType.EAGER) {
              whereQuery = " Article0.faTitle = ? ";
              counter++;
            } else {
              whereQuery = " Article0.faTitle = :Article0faTitle" + j + " ";
            }

            isOpenParentheses = false;
            whereValue = model.getFaTitle();
            query +=
                (andCondition
                    + Facade.getStatement(
                        statement,
                        "Article0.faTitle",
                        "Article0faTitle" + j,
                        mapVariable,
                        whereQuery,
                        whereValue,
                        isExistRecord,
                        isDelete,
                        fetchType,
                        counter));
            whereQuery = "";
          }
          if (!isExistRecord && true && model.getEnTitle() != null) {

            andCondition = " AND ";
            if (isOpenParentheses) {
              andCondition = " ";
            }
            query += " ";
            if (isDelete && fetchType == FetchType.EAGER) {
              whereQuery = " Article0.enTitle = ? ";
              counter++;
            } else {
              whereQuery = " Article0.enTitle = :Article0enTitle" + j + " ";
            }

            isOpenParentheses = false;
            whereValue = model.getEnTitle();
            query +=
                (andCondition
                    + Facade.getStatement(
                        statement,
                        "Article0.enTitle",
                        "Article0enTitle" + j,
                        mapVariable,
                        whereQuery,
                        whereValue,
                        isExistRecord,
                        isDelete,
                        fetchType,
                        counter));
            whereQuery = "";
          }
          if (!isExistRecord && true && model.getCustomsTariffCode() != null) {

            andCondition = " AND ";
            if (isOpenParentheses) {
              andCondition = " ";
            }
            query += " ";
            if (isDelete && fetchType == FetchType.EAGER) {
              whereQuery = " Article0.customsTariffCode = ? ";
              counter++;
            } else {
              whereQuery = " Article0.customsTariffCode = :Article0customsTariffCode" + j + " ";
            }

            isOpenParentheses = false;
            whereValue = model.getCustomsTariffCode();
            query +=
                (andCondition
                    + Facade.getStatement(
                        statement,
                        "Article0.customsTariffCode",
                        "Article0customsTariffCode" + j,
                        mapVariable,
                        whereQuery,
                        whereValue,
                        isExistRecord,
                        isDelete,
                        fetchType,
                        counter));
            whereQuery = "";
          }
          if (!isExistRecord && true && model.getISICCode() != null) {

            andCondition = " AND ";
            if (isOpenParentheses) {
              andCondition = " ";
            }
            query += " ";
            if (isDelete && fetchType == FetchType.EAGER) {
              whereQuery = " Article0.iSICCode = ? ";
              counter++;
            } else {
              whereQuery = " Article0.iSICCode = :Article0iSICCode" + j + " ";
            }

            isOpenParentheses = false;
            whereValue = model.getISICCode();
            query +=
                (andCondition
                    + Facade.getStatement(
                        statement,
                        "Article0.iSICCode",
                        "Article0iSICCode" + j,
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
              whereQuery = " Article0.VERSION = ? ";
              counter++;
            } else {
              whereQuery = " Article0.version = :Article0version" + j + " ";
            }

            isOpenParentheses = false;
            whereValue = new java.lang.Integer("" + model.getVersion());
            query +=
                (andCondition
                    + Facade.getStatement(
                        statement,
                        "Article0.version",
                        "Article0version" + j,
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
          && (!select.trim().equals("Article0") && select.indexOf("new") == -1)) {
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
