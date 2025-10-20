package com.tosan.bpms.framework.orm.repository.jpql;

import com.jpql.api.interfaces.*;
import com.jpql.api.interfaces.Parameter;
import javax.persistence.FetchType;
import javax.persistence.EntityManager;
/**
 * Created by kasra.haghpanah on 1/05/2019.
 */
public class MaterialOfProjectcomtosanbpmsmodelsqlDAO {

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

        com.tosan.bpms.model.sql.MaterialOfProject model =
            (com.tosan.bpms.model.sql.MaterialOfProject) fetchIterator.next();

        if (model != null) {

          String key = model.getMaterialOfProjectId() + "";
          existMap.put(key, model);
        }
      }
    }

    java.util.Iterator iterator = models.iterator();

    while (iterator.hasNext()) {
      com.tosan.bpms.model.sql.MaterialOfProject model =
          (com.tosan.bpms.model.sql.MaterialOfProject) iterator.next();

      if (model != null) {
        String key = model.getMaterialOfProjectId() + "";
        com.tosan.bpms.model.sql.MaterialOfProject fetchModel =
            (com.tosan.bpms.model.sql.MaterialOfProject) existMap.get(key);

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
    com.tosan.bpms.model.sql.MaterialOfProject model = null;
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
    java.lang.String select = "SELECT MaterialOfProject0";
    if (selectParam != null && !selectParam.equals("")) {
      select = selectParam;
    }
    if (!isExistRecord && !isDelete) {
      if (fetchType == FetchType.LAZY) {
        basicQuery =
            select
                + " FROM com.tosan.bpms.model.sql.MaterialOfProject MaterialOfProject0  JOIN "
                + fetch
                + " MaterialOfProject0.productsOfProject ProductsOfProject1 JOIN "
                + fetch
                + " ProductsOfProject1.article Article2 JOIN "
                + fetch
                + " Article2.industry Industry3 JOIN "
                + fetch
                + " Article2.economic Economic4 JOIN "
                + fetch
                + " Article2.measurement Measurment5 JOIN "
                + fetch
                + " ProductsOfProject1.portfolioLicense PortfolioLicense6 JOIN "
                + fetch
                + " PortfolioLicense6.portfolio Portfolio7 LEFT JOIN "
                + fetch
                + " PortfolioLicense6.file File25 WHERE 1=1 ";
      } else if (fetchType == FetchType.EAGER) {
        basicQuery =
            select
                + " FROM com.tosan.bpms.model.sql.MaterialOfProject MaterialOfProject0  JOIN "
                + fetch
                + " MaterialOfProject0.productsOfProject ProductsOfProject1 JOIN "
                + fetch
                + " ProductsOfProject1.article Article2 JOIN "
                + fetch
                + " Article2.industry Industry3 JOIN "
                + fetch
                + " Article2.economic Economic4 JOIN "
                + fetch
                + " Article2.measurement Measurment5 JOIN "
                + fetch
                + " ProductsOfProject1.portfolioLicense PortfolioLicense6 JOIN "
                + fetch
                + " PortfolioLicense6.portfolio Portfolio7 JOIN "
                + fetch
                + " Portfolio7.portfolioLicenses PortfolioLicense8 LEFT JOIN "
                + fetch
                + " PortfolioLicense8.file File10 JOIN "
                + fetch
                + " PortfolioLicense8.licenseDetails LicenseDetails11 LEFT JOIN "
                + fetch
                + " LicenseDetails11.file File13 JOIN "
                + fetch
                + " Portfolio7.portfolioLocations PortfolioLocation14 LEFT JOIN "
                + fetch
                + " PortfolioLocation14.file File16 JOIN "
                + fetch
                + " PortfolioLocation14.locationDetails LocationDetails17 JOIN "
                + fetch
                + " LocationDetails17.portfolioLocation PortfolioLocation18 LEFT JOIN "
                + fetch
                + " PortfolioLocation18.file File20 LEFT JOIN "
                + fetch
                + " LocationDetails17.file File22 JOIN "
                + fetch
                + " Portfolio7.receptions Reception23 LEFT JOIN "
                + fetch
                + " PortfolioLicense6.file File25 JOIN "
                + fetch
                + " PortfolioLicense6.licenseDetails LicenseDetails26 LEFT JOIN "
                + fetch
                + " LicenseDetails26.file File28 WHERE 1=1 ";
      }
    } else if (isExistRecord) {
      basicQuery =
          "SELECT MaterialOfProject0 FROM com.tosan.bpms.model.sql.MaterialOfProject MaterialOfProject0 WHERE 1=1 ";
    } else if (isDelete) {
      if (fetchType == FetchType.LAZY) {
        basicQuery =
            "DELETE FROM com.tosan.bpms.model.sql.MaterialOfProject MaterialOfProject0 WHERE 1=1 ";
      } else if (fetchType == FetchType.EAGER) {
        basicQuery =
            "DELETE MaterialOfProject0 FROM MaterialOfProject MaterialOfProject0 WHERE 1=1 ";
      }
    }
    int modelsLength = models.size();
    java.util.Iterator modelsIterator = models.iterator();
    int j = 0;
    if (modelsLength > 0) {
      while (modelsIterator.hasNext()) {
        model = (com.tosan.bpms.model.sql.MaterialOfProject) modelsIterator.next();
        query = "";
        isOpenParentheses = true;

        if (model != null) {

          if (isExistRecord == true && model.getMaterialOfProjectId() == null) {
            model.setMaterialOfProjectId(new java.lang.Integer("-1"));
            isChangeKey = true;
          }

          if (model.getMaterialOfProjectId() != null) {

            andCondition = " AND ";
            if (isOpenParentheses) {
              andCondition = " ";
            }
            query += " ";
            if (isDelete && fetchType == FetchType.EAGER) {
              whereQuery = " MaterialOfProject0.id = ? ";
              counter++;
            } else {
              whereQuery =
                  " MaterialOfProject0.materialOfProjectId = :MaterialOfProject0materialOfProjectId"
                      + j
                      + " ";
            }

            isOpenParentheses = false;
            whereValue = model.getMaterialOfProjectId();
            if (isExistRecord && isChangeKey) {
              model.setMaterialOfProjectId(null);
              isChangeKey = false;
            }

            query +=
                (andCondition
                    + Facade.getStatement(
                        statement,
                        "MaterialOfProject0.materialOfProjectId",
                        "MaterialOfProject0materialOfProjectId" + j,
                        mapVariable,
                        whereQuery,
                        whereValue,
                        isExistRecord,
                        isDelete,
                        fetchType,
                        counter));
            whereQuery = "";
          }
          if (!isExistRecord && !isDelete && model.getProductsOfProject() != null) {

            if (model.getProductsOfProject().getProductsOfProjectId() != null) {

              andCondition = " AND ";
              if (isOpenParentheses) {
                andCondition = " ";
              }
              query += " ";
              if (isDelete && fetchType == FetchType.EAGER) {
                whereQuery = " ProductsOfProject1.productsOfProjectId = ? ";
                counter++;
              } else {
                whereQuery =
                    " ProductsOfProject1.productsOfProjectId = :ProductsOfProject1productsOfProjectId"
                        + j
                        + " ";
              }

              isOpenParentheses = false;
              whereValue = model.getProductsOfProject().getProductsOfProjectId();
              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "ProductsOfProject1.productsOfProjectId",
                          "ProductsOfProject1productsOfProjectId" + j,
                          mapVariable,
                          whereQuery,
                          whereValue,
                          isExistRecord,
                          isDelete,
                          fetchType,
                          counter));
              whereQuery = "";
            }
            if (!isExistRecord && !isDelete && model.getProductsOfProject().getArticle() != null) {

              if (model.getProductsOfProject().getArticle().getArticleId() != null) {

                andCondition = " AND ";
                if (isOpenParentheses) {
                  andCondition = " ";
                }
                query += " ";
                if (isDelete && fetchType == FetchType.EAGER) {
                  whereQuery = " Article2.id = ? ";
                  counter++;
                } else {
                  whereQuery = " Article2.articleId = :Article2articleId" + j + " ";
                }

                isOpenParentheses = false;
                whereValue = model.getProductsOfProject().getArticle().getArticleId();
                query +=
                    (andCondition
                        + Facade.getStatement(
                            statement,
                            "Article2.articleId",
                            "Article2articleId" + j,
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
                  && model.getProductsOfProject().getArticle().getIndustry() != null) {

                if (model.getProductsOfProject().getArticle().getIndustry().getIndustryId()
                    != null) {

                  andCondition = " AND ";
                  if (isOpenParentheses) {
                    andCondition = " ";
                  }
                  query += " ";
                  if (isExistRecord || isDelete) {
                    if (isDelete && fetchType == FetchType.EAGER) {
                      whereQuery = " Industry3.id = ? ";
                      counter++;
                    } else {
                      whereQuery = " Industry3.industryId = :Industry3industryId" + j + " ";
                    }
                  } else {

                    whereQuery = " Industry3.industryId LIKE :Industry3industryId" + j + " ";
                  }

                  isOpenParentheses = false;
                  if (isExistRecord || isDelete) {
                    if (isDelete && fetchType == FetchType.EAGER) {
                      whereQuery = " Industry3.id = ? ";
                      counter++;
                    } else {
                      whereValue =
                          model.getProductsOfProject().getArticle().getIndustry().getIndustryId();
                    }
                  } else {
                    whereValue =
                        "%"
                            + model
                                .getProductsOfProject()
                                .getArticle()
                                .getIndustry()
                                .getIndustryId()
                            + "%";
                  }

                  query +=
                      (andCondition
                          + Facade.getStatement(
                              statement,
                              "Industry3.industryId",
                              "Industry3industryId" + j,
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
                    && model.getProductsOfProject().getArticle().getIndustry().getFaTitle()
                        != null) {

                  andCondition = " AND ";
                  if (isOpenParentheses) {
                    andCondition = " ";
                  }
                  query += " ";
                  if (isDelete && fetchType == FetchType.EAGER) {
                    whereQuery = " Industry3.faTitle = ? ";
                    counter++;
                  } else {
                    whereQuery = " Industry3.faTitle = :Industry3faTitle" + j + " ";
                  }

                  isOpenParentheses = false;
                  whereValue = model.getProductsOfProject().getArticle().getIndustry().getFaTitle();
                  query +=
                      (andCondition
                          + Facade.getStatement(
                              statement,
                              "Industry3.faTitle",
                              "Industry3faTitle" + j,
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
                    && model.getProductsOfProject().getArticle().getIndustry().getEnTitle()
                        != null) {

                  andCondition = " AND ";
                  if (isOpenParentheses) {
                    andCondition = " ";
                  }
                  query += " ";
                  if (isDelete && fetchType == FetchType.EAGER) {
                    whereQuery = " Industry3.enTitle = ? ";
                    counter++;
                  } else {
                    whereQuery = " Industry3.enTitle = :Industry3enTitle" + j + " ";
                  }

                  isOpenParentheses = false;
                  whereValue = model.getProductsOfProject().getArticle().getIndustry().getEnTitle();
                  query +=
                      (andCondition
                          + Facade.getStatement(
                              statement,
                              "Industry3.enTitle",
                              "Industry3enTitle" + j,
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
                    && model.getProductsOfProject().getArticle().getIndustry().getVersion() != 0) {

                  andCondition = " AND ";
                  if (isOpenParentheses) {
                    andCondition = " ";
                  }
                  query += " ";
                  if (isDelete && fetchType == FetchType.EAGER) {
                    whereQuery = " Industry3.VERSION = ? ";
                    counter++;
                  } else {
                    whereQuery = " Industry3.version = :Industry3version" + j + " ";
                  }

                  isOpenParentheses = false;
                  whereValue =
                      new java.lang.Integer(
                          ""
                              + model
                                  .getProductsOfProject()
                                  .getArticle()
                                  .getIndustry()
                                  .getVersion());
                  query +=
                      (andCondition
                          + Facade.getStatement(
                              statement,
                              "Industry3.version",
                              "Industry3version" + j,
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
              if (!isExistRecord
                  && !isDelete
                  && model.getProductsOfProject().getArticle().getEconomic() != null) {

                if (model.getProductsOfProject().getArticle().getEconomic().getEconomicId()
                    != null) {

                  andCondition = " AND ";
                  if (isOpenParentheses) {
                    andCondition = " ";
                  }
                  query += " ";
                  if (isDelete && fetchType == FetchType.EAGER) {
                    whereQuery = " Economic4.id = ? ";
                    counter++;
                  } else {
                    whereQuery = " Economic4.economicId = :Economic4economicId" + j + " ";
                  }

                  isOpenParentheses = false;
                  whereValue =
                      model.getProductsOfProject().getArticle().getEconomic().getEconomicId();
                  query +=
                      (andCondition
                          + Facade.getStatement(
                              statement,
                              "Economic4.economicId",
                              "Economic4economicId" + j,
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
                    && model.getProductsOfProject().getArticle().getEconomic().getFaTitle()
                        != null) {

                  andCondition = " AND ";
                  if (isOpenParentheses) {
                    andCondition = " ";
                  }
                  query += " ";
                  if (isDelete && fetchType == FetchType.EAGER) {
                    whereQuery = " Economic4.faTitle = ? ";
                    counter++;
                  } else {
                    whereQuery = " Economic4.faTitle = :Economic4faTitle" + j + " ";
                  }

                  isOpenParentheses = false;
                  whereValue = model.getProductsOfProject().getArticle().getEconomic().getFaTitle();
                  query +=
                      (andCondition
                          + Facade.getStatement(
                              statement,
                              "Economic4.faTitle",
                              "Economic4faTitle" + j,
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
                    && model.getProductsOfProject().getArticle().getEconomic().getEnTitle()
                        != null) {

                  andCondition = " AND ";
                  if (isOpenParentheses) {
                    andCondition = " ";
                  }
                  query += " ";
                  if (isDelete && fetchType == FetchType.EAGER) {
                    whereQuery = " Economic4.enTitle = ? ";
                    counter++;
                  } else {
                    whereQuery = " Economic4.enTitle = :Economic4enTitle" + j + " ";
                  }

                  isOpenParentheses = false;
                  whereValue = model.getProductsOfProject().getArticle().getEconomic().getEnTitle();
                  query +=
                      (andCondition
                          + Facade.getStatement(
                              statement,
                              "Economic4.enTitle",
                              "Economic4enTitle" + j,
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
                    && model.getProductsOfProject().getArticle().getEconomic().getVersion() != 0) {

                  andCondition = " AND ";
                  if (isOpenParentheses) {
                    andCondition = " ";
                  }
                  query += " ";
                  if (isDelete && fetchType == FetchType.EAGER) {
                    whereQuery = " Economic4.VERSION = ? ";
                    counter++;
                  } else {
                    whereQuery = " Economic4.version = :Economic4version" + j + " ";
                  }

                  isOpenParentheses = false;
                  whereValue =
                      new java.lang.Integer(
                          ""
                              + model
                                  .getProductsOfProject()
                                  .getArticle()
                                  .getEconomic()
                                  .getVersion());
                  query +=
                      (andCondition
                          + Facade.getStatement(
                              statement,
                              "Economic4.version",
                              "Economic4version" + j,
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
              if (!isExistRecord
                  && !isDelete
                  && model.getProductsOfProject().getArticle().getMeasurement() != null) {

                if (model.getProductsOfProject().getArticle().getMeasurement().getMeasurementId()
                    != null) {

                  andCondition = " AND ";
                  if (isOpenParentheses) {
                    andCondition = " ";
                  }
                  query += " ";
                  if (isDelete && fetchType == FetchType.EAGER) {
                    whereQuery = " Measurment5.id = ? ";
                    counter++;
                  } else {
                    whereQuery = " Measurment5.measurementId = :Measurment5measurementId" + j + " ";
                  }

                  isOpenParentheses = false;
                  whereValue =
                      model.getProductsOfProject().getArticle().getMeasurement().getMeasurementId();
                  query +=
                      (andCondition
                          + Facade.getStatement(
                              statement,
                              "Measurment5.measurementId",
                              "Measurment5measurementId" + j,
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
                    && model.getProductsOfProject().getArticle().getMeasurement().getName()
                        != null) {

                  andCondition = " AND ";
                  if (isOpenParentheses) {
                    andCondition = " ";
                  }
                  query += " ";
                  if (isDelete && fetchType == FetchType.EAGER) {
                    whereQuery = " Measurment5.name = ? ";
                    counter++;
                  } else {
                    whereQuery = " Measurment5.name = :Measurment5name" + j + " ";
                  }

                  isOpenParentheses = false;
                  whereValue = model.getProductsOfProject().getArticle().getMeasurement().getName();
                  query +=
                      (andCondition
                          + Facade.getStatement(
                              statement,
                              "Measurment5.name",
                              "Measurment5name" + j,
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
                    && model.getProductsOfProject().getArticle().getMeasurement().getVersion()
                        != 0) {

                  andCondition = " AND ";
                  if (isOpenParentheses) {
                    andCondition = " ";
                  }
                  query += " ";
                  if (isDelete && fetchType == FetchType.EAGER) {
                    whereQuery = " Measurment5.VERSION = ? ";
                    counter++;
                  } else {
                    whereQuery = " Measurment5.version = :Measurment5version" + j + " ";
                  }

                  isOpenParentheses = false;
                  whereValue =
                      new java.lang.Integer(
                          ""
                              + model
                                  .getProductsOfProject()
                                  .getArticle()
                                  .getMeasurement()
                                  .getVersion());
                  query +=
                      (andCondition
                          + Facade.getStatement(
                              statement,
                              "Measurment5.version",
                              "Measurment5version" + j,
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
              if (!isExistRecord
                  && !isDelete
                  && true
                  && model.getProductsOfProject().getArticle().getFaTitle() != null) {

                andCondition = " AND ";
                if (isOpenParentheses) {
                  andCondition = " ";
                }
                query += " ";
                if (isDelete && fetchType == FetchType.EAGER) {
                  whereQuery = " Article2.faTitle = ? ";
                  counter++;
                } else {
                  whereQuery = " Article2.faTitle = :Article2faTitle" + j + " ";
                }

                isOpenParentheses = false;
                whereValue = model.getProductsOfProject().getArticle().getFaTitle();
                query +=
                    (andCondition
                        + Facade.getStatement(
                            statement,
                            "Article2.faTitle",
                            "Article2faTitle" + j,
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
                  && model.getProductsOfProject().getArticle().getEnTitle() != null) {

                andCondition = " AND ";
                if (isOpenParentheses) {
                  andCondition = " ";
                }
                query += " ";
                if (isDelete && fetchType == FetchType.EAGER) {
                  whereQuery = " Article2.enTitle = ? ";
                  counter++;
                } else {
                  whereQuery = " Article2.enTitle = :Article2enTitle" + j + " ";
                }

                isOpenParentheses = false;
                whereValue = model.getProductsOfProject().getArticle().getEnTitle();
                query +=
                    (andCondition
                        + Facade.getStatement(
                            statement,
                            "Article2.enTitle",
                            "Article2enTitle" + j,
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
                  && model.getProductsOfProject().getArticle().getCustomsTariffCode() != null) {

                andCondition = " AND ";
                if (isOpenParentheses) {
                  andCondition = " ";
                }
                query += " ";
                if (isDelete && fetchType == FetchType.EAGER) {
                  whereQuery = " Article2.customsTariffCode = ? ";
                  counter++;
                } else {
                  whereQuery = " Article2.customsTariffCode = :Article2customsTariffCode" + j + " ";
                }

                isOpenParentheses = false;
                whereValue = model.getProductsOfProject().getArticle().getCustomsTariffCode();
                query +=
                    (andCondition
                        + Facade.getStatement(
                            statement,
                            "Article2.customsTariffCode",
                            "Article2customsTariffCode" + j,
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
                  && model.getProductsOfProject().getArticle().getISICCode() != null) {

                andCondition = " AND ";
                if (isOpenParentheses) {
                  andCondition = " ";
                }
                query += " ";
                if (isDelete && fetchType == FetchType.EAGER) {
                  whereQuery = " Article2.iSICCode = ? ";
                  counter++;
                } else {
                  whereQuery = " Article2.iSICCode = :Article2iSICCode" + j + " ";
                }

                isOpenParentheses = false;
                whereValue = model.getProductsOfProject().getArticle().getISICCode();
                query +=
                    (andCondition
                        + Facade.getStatement(
                            statement,
                            "Article2.iSICCode",
                            "Article2iSICCode" + j,
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
                  && model.getProductsOfProject().getArticle().getVersion() != 0) {

                andCondition = " AND ";
                if (isOpenParentheses) {
                  andCondition = " ";
                }
                query += " ";
                if (isDelete && fetchType == FetchType.EAGER) {
                  whereQuery = " Article2.VERSION = ? ";
                  counter++;
                } else {
                  whereQuery = " Article2.version = :Article2version" + j + " ";
                }

                isOpenParentheses = false;
                whereValue =
                    new java.lang.Integer(
                        "" + model.getProductsOfProject().getArticle().getVersion());
                query +=
                    (andCondition
                        + Facade.getStatement(
                            statement,
                            "Article2.version",
                            "Article2version" + j,
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
            if (!isExistRecord
                && !isDelete
                && model.getProductsOfProject().getPortfolioLicense() != null) {

              if (model.getProductsOfProject().getPortfolioLicense().getPortfolioLicenseId()
                  != null) {

                andCondition = " AND ";
                if (isOpenParentheses) {
                  andCondition = " ";
                }
                query += " ";
                if (isDelete && fetchType == FetchType.EAGER) {
                  whereQuery = " PortfolioLicense6.id = ? ";
                  counter++;
                } else {
                  whereQuery =
                      " PortfolioLicense6.portfolioLicenseId = :PortfolioLicense6portfolioLicenseId"
                          + j
                          + " ";
                }

                isOpenParentheses = false;
                whereValue =
                    model.getProductsOfProject().getPortfolioLicense().getPortfolioLicenseId();
                query +=
                    (andCondition
                        + Facade.getStatement(
                            statement,
                            "PortfolioLicense6.portfolioLicenseId",
                            "PortfolioLicense6portfolioLicenseId" + j,
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
                  && model.getProductsOfProject().getPortfolioLicense().getPortfolio() != null) {

                if (model
                        .getProductsOfProject()
                        .getPortfolioLicense()
                        .getPortfolio()
                        .getPortfolioId()
                    != null) {

                  andCondition = " AND ";
                  if (isOpenParentheses) {
                    andCondition = " ";
                  }
                  query += " ";
                  if (isDelete && fetchType == FetchType.EAGER) {
                    whereQuery = " Portfolio7.portfolioId = ? ";
                    counter++;
                  } else {
                    whereQuery = " Portfolio7.portfolioId = :Portfolio7portfolioId" + j + " ";
                  }

                  isOpenParentheses = false;
                  whereValue =
                      model
                          .getProductsOfProject()
                          .getPortfolioLicense()
                          .getPortfolio()
                          .getPortfolioId();
                  query +=
                      (andCondition
                          + Facade.getStatement(
                              statement,
                              "Portfolio7.portfolioId",
                              "Portfolio7portfolioId" + j,
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
                    && model
                            .getProductsOfProject()
                            .getPortfolioLicense()
                            .getPortfolio()
                            .getPortfolioLicenses()
                        != null) {
                  int length8 =
                      model
                          .getProductsOfProject()
                          .getPortfolioLicense()
                          .getPortfolio()
                          .getPortfolioLicenses()
                          .size();

                  if (length8 > 0) {
                    if (isOpenParentheses) {
                      query += "  ( ";
                    } else {
                      query += " AND ( ";
                    }
                    isOpenParentheses = true;

                    for (int i8 = 0; i8 < length8; i8++) {

                      java.lang.String beforeCondition8 = "";
                      java.lang.String prefix8 = " OR ";
                      if (i8 == 0) {
                        prefix8 = "";
                      }
                      if (length8 > 1) {

                        query += (prefix8 + "(");
                        isOpenParentheses = true;

                      } else {

                        query += (prefix8);
                        if (!prefix8.trim().equals("")) {
                          isOpenParentheses = false;
                        }
                      }

                      if (model
                              .getProductsOfProject()
                              .getPortfolioLicense()
                              .getPortfolio()
                              .getPortfolioLicenses()
                              .get(i8)
                              .getPortfolioLicenseId()
                          != null) {

                        andCondition = " AND ";
                        if (isOpenParentheses) {
                          andCondition = " ";
                        }
                        query += " ";
                        whereValue =
                            model
                                .getProductsOfProject()
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getPortfolioLicenses()
                                .get(i8)
                                .getPortfolioLicenseId();
                        if (isDelete && fetchType == FetchType.EAGER) {
                          whereQuery = " PortfolioLicense8.id = ? ";
                          counter++;
                        } else {
                          whereQuery =
                              " PortfolioLicense8.portfolioLicenseId = :PortfolioLicense8portfolioLicenseId"
                                  + j
                                  + " ";
                        }

                        isOpenParentheses = false;
                        whereValue =
                            model
                                .getProductsOfProject()
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getPortfolioLicenses()
                                .get(i8)
                                .getPortfolioLicenseId();
                        query +=
                            (andCondition
                                + Facade.getStatement(
                                    statement,
                                    "PortfolioLicense8.portfolioLicenseId",
                                    "PortfolioLicense8portfolioLicenseId" + j,
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
                          && model
                                  .getProductsOfProject()
                                  .getPortfolioLicense()
                                  .getPortfolio()
                                  .getPortfolioLicenses()
                                  .get(i8)
                                  .getFile()
                              != null) {

                        if (model
                                .getProductsOfProject()
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getPortfolioLicenses()
                                .get(i8)
                                .getFile()
                                .getId()
                            != null) {

                          andCondition = " AND ";
                          if (isOpenParentheses) {
                            andCondition = " ";
                          }
                          query += " ";
                          whereValue =
                              model
                                  .getProductsOfProject()
                                  .getPortfolioLicense()
                                  .getPortfolio()
                                  .getPortfolioLicenses()
                                  .get(i8)
                                  .getFile()
                                  .getId();
                          if (isDelete && fetchType == FetchType.EAGER) {
                            whereQuery = " File10.l4610flmtdataid = ? ";
                            counter++;
                          } else {
                            whereQuery = " File10.id = :File10id" + j + " ";
                          }

                          isOpenParentheses = false;
                          whereValue =
                              model
                                  .getProductsOfProject()
                                  .getPortfolioLicense()
                                  .getPortfolio()
                                  .getPortfolioLicenses()
                                  .get(i8)
                                  .getFile()
                                  .getId();
                          query +=
                              (andCondition
                                  + Facade.getStatement(
                                      statement,
                                      "File10.id",
                                      "File10id" + j,
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
                            && model
                                    .getProductsOfProject()
                                    .getPortfolioLicense()
                                    .getPortfolio()
                                    .getPortfolioLicenses()
                                    .get(i8)
                                    .getFile()
                                    .getExtension()
                                != null) {

                          andCondition = " AND ";
                          if (isOpenParentheses) {
                            andCondition = " ";
                          }
                          query += " ";
                          whereValue =
                              model
                                  .getProductsOfProject()
                                  .getPortfolioLicense()
                                  .getPortfolio()
                                  .getPortfolioLicenses()
                                  .get(i8)
                                  .getFile()
                                  .getExtension();
                          if (isDelete && fetchType == FetchType.EAGER) {
                            whereQuery = " File10.l4610extension = ? ";
                            counter++;
                          } else {
                            whereQuery = " File10.extension = :File10extension" + j + " ";
                          }

                          isOpenParentheses = false;
                          whereValue =
                              model
                                  .getProductsOfProject()
                                  .getPortfolioLicense()
                                  .getPortfolio()
                                  .getPortfolioLicenses()
                                  .get(i8)
                                  .getFile()
                                  .getExtension();
                          query +=
                              (andCondition
                                  + Facade.getStatement(
                                      statement,
                                      "File10.extension",
                                      "File10extension" + j,
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
                            && model
                                    .getProductsOfProject()
                                    .getPortfolioLicense()
                                    .getPortfolio()
                                    .getPortfolioLicenses()
                                    .get(i8)
                                    .getFile()
                                    .getVersion()
                                != 0) {

                          andCondition = " AND ";
                          if (isOpenParentheses) {
                            andCondition = " ";
                          }
                          query += " ";
                          whereValue =
                              new java.lang.Integer(
                                  ""
                                      + model
                                          .getProductsOfProject()
                                          .getPortfolioLicense()
                                          .getPortfolio()
                                          .getPortfolioLicenses()
                                          .get(i8)
                                          .getFile()
                                          .getVersion());
                          if (isDelete && fetchType == FetchType.EAGER) {
                            whereQuery = " File10.VERSION = ? ";
                            counter++;
                          } else {
                            whereQuery = " File10.version = :File10version" + j + " ";
                          }

                          isOpenParentheses = false;
                          whereValue =
                              new java.lang.Integer(
                                  ""
                                      + model
                                          .getProductsOfProject()
                                          .getPortfolioLicense()
                                          .getPortfolio()
                                          .getPortfolioLicenses()
                                          .get(i8)
                                          .getFile()
                                          .getVersion());
                          query +=
                              (andCondition
                                  + Facade.getStatement(
                                      statement,
                                      "File10.version",
                                      "File10version" + j,
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
                      if (!isExistRecord
                          && !isDelete
                          && fetchType == FetchType.EAGER
                          && model
                                  .getProductsOfProject()
                                  .getPortfolioLicense()
                                  .getPortfolio()
                                  .getPortfolioLicenses()
                                  .get(i8)
                                  .getLicenseDetails()
                              != null) {
                        int length11 =
                            model
                                .getProductsOfProject()
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getPortfolioLicenses()
                                .get(i8)
                                .getLicenseDetails()
                                .size();

                        if (length11 > 0) {
                          if (isOpenParentheses) {
                            query += "  ( ";
                          } else {
                            query += " AND ( ";
                          }
                          isOpenParentheses = true;

                          for (int i11 = 0; i11 < length11; i11++) {

                            java.lang.String beforeCondition11 = "";
                            java.lang.String prefix11 = " OR ";
                            if (i11 == 0) {
                              prefix11 = "";
                            }
                            if (length11 > 1) {

                              query += (prefix11 + "(");
                              isOpenParentheses = true;

                            } else {

                              query += (prefix11);
                              if (!prefix11.trim().equals("")) {
                                isOpenParentheses = false;
                              }
                            }

                            if (model
                                    .getProductsOfProject()
                                    .getPortfolioLicense()
                                    .getPortfolio()
                                    .getPortfolioLicenses()
                                    .get(i8)
                                    .getLicenseDetails()
                                    .get(i11)
                                    .getLicenseDetailId()
                                != null) {

                              andCondition = " AND ";
                              if (isOpenParentheses) {
                                andCondition = " ";
                              }
                              query += " ";
                              whereValue =
                                  model
                                      .getProductsOfProject()
                                      .getPortfolioLicense()
                                      .getPortfolio()
                                      .getPortfolioLicenses()
                                      .get(i8)
                                      .getLicenseDetails()
                                      .get(i11)
                                      .getLicenseDetailId();
                              if (isDelete && fetchType == FetchType.EAGER) {
                                whereQuery = " LicenseDetails11.id = ? ";
                                counter++;
                              } else {
                                whereQuery =
                                    " LicenseDetails11.licenseDetailId = :LicenseDetails11licenseDetailId"
                                        + j
                                        + " ";
                              }

                              isOpenParentheses = false;
                              whereValue =
                                  model
                                      .getProductsOfProject()
                                      .getPortfolioLicense()
                                      .getPortfolio()
                                      .getPortfolioLicenses()
                                      .get(i8)
                                      .getLicenseDetails()
                                      .get(i11)
                                      .getLicenseDetailId();
                              query +=
                                  (andCondition
                                      + Facade.getStatement(
                                          statement,
                                          "LicenseDetails11.licenseDetailId",
                                          "LicenseDetails11licenseDetailId" + j,
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
                                && model
                                        .getProductsOfProject()
                                        .getPortfolioLicense()
                                        .getPortfolio()
                                        .getPortfolioLicenses()
                                        .get(i8)
                                        .getLicenseDetails()
                                        .get(i11)
                                        .getFile()
                                    != null) {

                              if (model
                                      .getProductsOfProject()
                                      .getPortfolioLicense()
                                      .getPortfolio()
                                      .getPortfolioLicenses()
                                      .get(i8)
                                      .getLicenseDetails()
                                      .get(i11)
                                      .getFile()
                                      .getId()
                                  != null) {

                                andCondition = " AND ";
                                if (isOpenParentheses) {
                                  andCondition = " ";
                                }
                                query += " ";
                                whereValue =
                                    model
                                        .getProductsOfProject()
                                        .getPortfolioLicense()
                                        .getPortfolio()
                                        .getPortfolioLicenses()
                                        .get(i8)
                                        .getLicenseDetails()
                                        .get(i11)
                                        .getFile()
                                        .getId();
                                if (isDelete && fetchType == FetchType.EAGER) {
                                  whereQuery = " File13.l4610flmtdataid = ? ";
                                  counter++;
                                } else {
                                  whereQuery = " File13.id = :File13id" + j + " ";
                                }

                                isOpenParentheses = false;
                                whereValue =
                                    model
                                        .getProductsOfProject()
                                        .getPortfolioLicense()
                                        .getPortfolio()
                                        .getPortfolioLicenses()
                                        .get(i8)
                                        .getLicenseDetails()
                                        .get(i11)
                                        .getFile()
                                        .getId();
                                query +=
                                    (andCondition
                                        + Facade.getStatement(
                                            statement,
                                            "File13.id",
                                            "File13id" + j,
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
                                  && model
                                          .getProductsOfProject()
                                          .getPortfolioLicense()
                                          .getPortfolio()
                                          .getPortfolioLicenses()
                                          .get(i8)
                                          .getLicenseDetails()
                                          .get(i11)
                                          .getFile()
                                          .getExtension()
                                      != null) {

                                andCondition = " AND ";
                                if (isOpenParentheses) {
                                  andCondition = " ";
                                }
                                query += " ";
                                whereValue =
                                    model
                                        .getProductsOfProject()
                                        .getPortfolioLicense()
                                        .getPortfolio()
                                        .getPortfolioLicenses()
                                        .get(i8)
                                        .getLicenseDetails()
                                        .get(i11)
                                        .getFile()
                                        .getExtension();
                                if (isDelete && fetchType == FetchType.EAGER) {
                                  whereQuery = " File13.l4610extension = ? ";
                                  counter++;
                                } else {
                                  whereQuery = " File13.extension = :File13extension" + j + " ";
                                }

                                isOpenParentheses = false;
                                whereValue =
                                    model
                                        .getProductsOfProject()
                                        .getPortfolioLicense()
                                        .getPortfolio()
                                        .getPortfolioLicenses()
                                        .get(i8)
                                        .getLicenseDetails()
                                        .get(i11)
                                        .getFile()
                                        .getExtension();
                                query +=
                                    (andCondition
                                        + Facade.getStatement(
                                            statement,
                                            "File13.extension",
                                            "File13extension" + j,
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
                                  && model
                                          .getProductsOfProject()
                                          .getPortfolioLicense()
                                          .getPortfolio()
                                          .getPortfolioLicenses()
                                          .get(i8)
                                          .getLicenseDetails()
                                          .get(i11)
                                          .getFile()
                                          .getVersion()
                                      != 0) {

                                andCondition = " AND ";
                                if (isOpenParentheses) {
                                  andCondition = " ";
                                }
                                query += " ";
                                whereValue =
                                    new java.lang.Integer(
                                        ""
                                            + model
                                                .getProductsOfProject()
                                                .getPortfolioLicense()
                                                .getPortfolio()
                                                .getPortfolioLicenses()
                                                .get(i8)
                                                .getLicenseDetails()
                                                .get(i11)
                                                .getFile()
                                                .getVersion());
                                if (isDelete && fetchType == FetchType.EAGER) {
                                  whereQuery = " File13.VERSION = ? ";
                                  counter++;
                                } else {
                                  whereQuery = " File13.version = :File13version" + j + " ";
                                }

                                isOpenParentheses = false;
                                whereValue =
                                    new java.lang.Integer(
                                        ""
                                            + model
                                                .getProductsOfProject()
                                                .getPortfolioLicense()
                                                .getPortfolio()
                                                .getPortfolioLicenses()
                                                .get(i8)
                                                .getLicenseDetails()
                                                .get(i11)
                                                .getFile()
                                                .getVersion());
                                query +=
                                    (andCondition
                                        + Facade.getStatement(
                                            statement,
                                            "File13.version",
                                            "File13version" + j,
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
                            if (!isDelete
                                && model
                                        .getProductsOfProject()
                                        .getPortfolioLicense()
                                        .getPortfolio()
                                        .getPortfolioLicenses()
                                        .get(i8)
                                        .getLicenseDetails()
                                        .get(i11)
                                        .getAddress()
                                    != null) {
                              if (!isDelete
                                  && true
                                  && model
                                          .getProductsOfProject()
                                          .getPortfolioLicense()
                                          .getPortfolio()
                                          .getPortfolioLicenses()
                                          .get(i8)
                                          .getLicenseDetails()
                                          .get(i11)
                                          .getAddress()
                                          .getAddressLine1()
                                      != null) {

                                andCondition = " AND ";
                                if (isOpenParentheses) {
                                  andCondition = " ";
                                }
                                query += " ";
                                whereValue =
                                    model
                                        .getProductsOfProject()
                                        .getPortfolioLicense()
                                        .getPortfolio()
                                        .getPortfolioLicenses()
                                        .get(i8)
                                        .getLicenseDetails()
                                        .get(i11)
                                        .getAddress()
                                        .getAddressLine1();
                                if (isDelete && fetchType == FetchType.EAGER) {
                                  whereQuery = " LicenseDetails11.addressLine1 = ? ";
                                  counter++;
                                } else {
                                  whereQuery =
                                      " LicenseDetails11.address.addressLine1 = :LicenseDetails11addressaddressLine1"
                                          + j
                                          + " ";
                                }

                                isOpenParentheses = false;
                                whereValue =
                                    model
                                        .getProductsOfProject()
                                        .getPortfolioLicense()
                                        .getPortfolio()
                                        .getPortfolioLicenses()
                                        .get(i8)
                                        .getLicenseDetails()
                                        .get(i11)
                                        .getAddress()
                                        .getAddressLine1();
                                query +=
                                    (andCondition
                                        + Facade.getStatement(
                                            statement,
                                            "LicenseDetails11.address.addressLine1",
                                            "LicenseDetails11addressaddressLine1" + j,
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
                                  && model
                                          .getProductsOfProject()
                                          .getPortfolioLicense()
                                          .getPortfolio()
                                          .getPortfolioLicenses()
                                          .get(i8)
                                          .getLicenseDetails()
                                          .get(i11)
                                          .getAddress()
                                          .getAddressLine2()
                                      != null) {

                                andCondition = " AND ";
                                if (isOpenParentheses) {
                                  andCondition = " ";
                                }
                                query += " ";
                                whereValue =
                                    model
                                        .getProductsOfProject()
                                        .getPortfolioLicense()
                                        .getPortfolio()
                                        .getPortfolioLicenses()
                                        .get(i8)
                                        .getLicenseDetails()
                                        .get(i11)
                                        .getAddress()
                                        .getAddressLine2();
                                if (isDelete && fetchType == FetchType.EAGER) {
                                  whereQuery = " LicenseDetails11.addressLine2 = ? ";
                                  counter++;
                                } else {
                                  whereQuery =
                                      " LicenseDetails11.address.addressLine2 = :LicenseDetails11addressaddressLine2"
                                          + j
                                          + " ";
                                }

                                isOpenParentheses = false;
                                whereValue =
                                    model
                                        .getProductsOfProject()
                                        .getPortfolioLicense()
                                        .getPortfolio()
                                        .getPortfolioLicenses()
                                        .get(i8)
                                        .getLicenseDetails()
                                        .get(i11)
                                        .getAddress()
                                        .getAddressLine2();
                                query +=
                                    (andCondition
                                        + Facade.getStatement(
                                            statement,
                                            "LicenseDetails11.address.addressLine2",
                                            "LicenseDetails11addressaddressLine2" + j,
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
                                  && model
                                          .getProductsOfProject()
                                          .getPortfolioLicense()
                                          .getPortfolio()
                                          .getPortfolioLicenses()
                                          .get(i8)
                                          .getLicenseDetails()
                                          .get(i11)
                                          .getAddress()
                                          .getCity()
                                      != null) {

                                andCondition = " AND ";
                                if (isOpenParentheses) {
                                  andCondition = " ";
                                }
                                query += " ";
                                whereValue =
                                    model
                                        .getProductsOfProject()
                                        .getPortfolioLicense()
                                        .getPortfolio()
                                        .getPortfolioLicenses()
                                        .get(i8)
                                        .getLicenseDetails()
                                        .get(i11)
                                        .getAddress()
                                        .getCity();
                                if (isDelete && fetchType == FetchType.EAGER) {
                                  whereQuery = " LicenseDetails11.city = ? ";
                                  counter++;
                                } else {
                                  whereQuery =
                                      " LicenseDetails11.address.city = :LicenseDetails11addresscity"
                                          + j
                                          + " ";
                                }

                                isOpenParentheses = false;
                                whereValue =
                                    model
                                        .getProductsOfProject()
                                        .getPortfolioLicense()
                                        .getPortfolio()
                                        .getPortfolioLicenses()
                                        .get(i8)
                                        .getLicenseDetails()
                                        .get(i11)
                                        .getAddress()
                                        .getCity();
                                query +=
                                    (andCondition
                                        + Facade.getStatement(
                                            statement,
                                            "LicenseDetails11.address.city",
                                            "LicenseDetails11addresscity" + j,
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
                                  && model
                                          .getProductsOfProject()
                                          .getPortfolioLicense()
                                          .getPortfolio()
                                          .getPortfolioLicenses()
                                          .get(i8)
                                          .getLicenseDetails()
                                          .get(i11)
                                          .getAddress()
                                          .getState()
                                      != null) {

                                andCondition = " AND ";
                                if (isOpenParentheses) {
                                  andCondition = " ";
                                }
                                query += " ";
                                whereValue =
                                    model
                                        .getProductsOfProject()
                                        .getPortfolioLicense()
                                        .getPortfolio()
                                        .getPortfolioLicenses()
                                        .get(i8)
                                        .getLicenseDetails()
                                        .get(i11)
                                        .getAddress()
                                        .getState();
                                if (isDelete && fetchType == FetchType.EAGER) {
                                  whereQuery = " LicenseDetails11.state = ? ";
                                  counter++;
                                } else {
                                  whereQuery =
                                      " LicenseDetails11.address.state = :LicenseDetails11addressstate"
                                          + j
                                          + " ";
                                }

                                isOpenParentheses = false;
                                whereValue =
                                    model
                                        .getProductsOfProject()
                                        .getPortfolioLicense()
                                        .getPortfolio()
                                        .getPortfolioLicenses()
                                        .get(i8)
                                        .getLicenseDetails()
                                        .get(i11)
                                        .getAddress()
                                        .getState();
                                query +=
                                    (andCondition
                                        + Facade.getStatement(
                                            statement,
                                            "LicenseDetails11.address.state",
                                            "LicenseDetails11addressstate" + j,
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
                                  && model
                                          .getProductsOfProject()
                                          .getPortfolioLicense()
                                          .getPortfolio()
                                          .getPortfolioLicenses()
                                          .get(i8)
                                          .getLicenseDetails()
                                          .get(i11)
                                          .getAddress()
                                          .getCountry()
                                      != null) {

                                andCondition = " AND ";
                                if (isOpenParentheses) {
                                  andCondition = " ";
                                }
                                query += " ";
                                whereValue =
                                    model
                                        .getProductsOfProject()
                                        .getPortfolioLicense()
                                        .getPortfolio()
                                        .getPortfolioLicenses()
                                        .get(i8)
                                        .getLicenseDetails()
                                        .get(i11)
                                        .getAddress()
                                        .getCountry();
                                if (isDelete && fetchType == FetchType.EAGER) {
                                  whereQuery = " LicenseDetails11.country = ? ";
                                  counter++;
                                } else {
                                  whereQuery =
                                      " LicenseDetails11.address.country = :LicenseDetails11addresscountry"
                                          + j
                                          + " ";
                                }

                                isOpenParentheses = false;
                                whereValue =
                                    model
                                        .getProductsOfProject()
                                        .getPortfolioLicense()
                                        .getPortfolio()
                                        .getPortfolioLicenses()
                                        .get(i8)
                                        .getLicenseDetails()
                                        .get(i11)
                                        .getAddress()
                                        .getCountry();
                                query +=
                                    (andCondition
                                        + Facade.getStatement(
                                            statement,
                                            "LicenseDetails11.address.country",
                                            "LicenseDetails11addresscountry" + j,
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
                                  && model
                                          .getProductsOfProject()
                                          .getPortfolioLicense()
                                          .getPortfolio()
                                          .getPortfolioLicenses()
                                          .get(i8)
                                          .getLicenseDetails()
                                          .get(i11)
                                          .getAddress()
                                          .getZipCode()
                                      != null) {

                                andCondition = " AND ";
                                if (isOpenParentheses) {
                                  andCondition = " ";
                                }
                                query += " ";
                                whereValue =
                                    model
                                        .getProductsOfProject()
                                        .getPortfolioLicense()
                                        .getPortfolio()
                                        .getPortfolioLicenses()
                                        .get(i8)
                                        .getLicenseDetails()
                                        .get(i11)
                                        .getAddress()
                                        .getZipCode();
                                if (isDelete && fetchType == FetchType.EAGER) {
                                  whereQuery = " LicenseDetails11.zipCode = ? ";
                                  counter++;
                                } else {
                                  whereQuery =
                                      " LicenseDetails11.address.zipCode = :LicenseDetails11addresszipCode"
                                          + j
                                          + " ";
                                }

                                isOpenParentheses = false;
                                whereValue =
                                    model
                                        .getProductsOfProject()
                                        .getPortfolioLicense()
                                        .getPortfolio()
                                        .getPortfolioLicenses()
                                        .get(i8)
                                        .getLicenseDetails()
                                        .get(i11)
                                        .getAddress()
                                        .getZipCode();
                                query +=
                                    (andCondition
                                        + Facade.getStatement(
                                            statement,
                                            "LicenseDetails11.address.zipCode",
                                            "LicenseDetails11addresszipCode" + j,
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
                            if (length11 > 1) {
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
                          && model
                                  .getProductsOfProject()
                                  .getPortfolioLicense()
                                  .getPortfolio()
                                  .getPortfolioLicenses()
                                  .get(i8)
                                  .getIssueVerifierType()
                              != null) {

                        andCondition = " AND ";
                        if (isOpenParentheses) {
                          andCondition = " ";
                        }
                        query += " ";
                        whereValue =
                            model
                                .getProductsOfProject()
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getPortfolioLicenses()
                                .get(i8)
                                .getIssueVerifierType();
                        if (isDelete && fetchType == FetchType.EAGER) {
                          whereQuery = " PortfolioLicense8.issueVerifierType = ? ";
                          counter++;
                        } else {
                          whereQuery =
                              " PortfolioLicense8.issueVerifierType = :PortfolioLicense8issueVerifierType"
                                  + j
                                  + " ";
                        }

                        isOpenParentheses = false;
                        whereValue =
                            model
                                .getProductsOfProject()
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getPortfolioLicenses()
                                .get(i8)
                                .getIssueVerifierType();
                        query +=
                            (andCondition
                                + Facade.getStatement(
                                    statement,
                                    "PortfolioLicense8.issueVerifierType",
                                    "PortfolioLicense8issueVerifierType" + j,
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
                          && model
                                  .getProductsOfProject()
                                  .getPortfolioLicense()
                                  .getPortfolio()
                                  .getPortfolioLicenses()
                                  .get(i8)
                                  .getLicenseNumber()
                              != null) {

                        andCondition = " AND ";
                        if (isOpenParentheses) {
                          andCondition = " ";
                        }
                        query += " ";
                        whereValue =
                            model
                                .getProductsOfProject()
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getPortfolioLicenses()
                                .get(i8)
                                .getLicenseNumber();
                        if (isDelete && fetchType == FetchType.EAGER) {
                          whereQuery = " PortfolioLicense8.licenseNumber = ? ";
                          counter++;
                        } else {
                          whereQuery =
                              " PortfolioLicense8.licenseNumber = :PortfolioLicense8licenseNumber"
                                  + j
                                  + " ";
                        }

                        isOpenParentheses = false;
                        whereValue =
                            model
                                .getProductsOfProject()
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getPortfolioLicenses()
                                .get(i8)
                                .getLicenseNumber();
                        query +=
                            (andCondition
                                + Facade.getStatement(
                                    statement,
                                    "PortfolioLicense8.licenseNumber",
                                    "PortfolioLicense8licenseNumber" + j,
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
                          && model
                                  .getProductsOfProject()
                                  .getPortfolioLicense()
                                  .getPortfolio()
                                  .getPortfolioLicenses()
                                  .get(i8)
                                  .getLicenseType()
                              != null) {

                        andCondition = " AND ";
                        if (isOpenParentheses) {
                          andCondition = " ";
                        }
                        query += " ";
                        whereValue =
                            model
                                .getProductsOfProject()
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getPortfolioLicenses()
                                .get(i8)
                                .getLicenseType();
                        if (isDelete && fetchType == FetchType.EAGER) {
                          whereQuery = " PortfolioLicense8.licenseType = ? ";
                          counter++;
                        } else {
                          whereQuery =
                              " PortfolioLicense8.licenseType = :PortfolioLicense8licenseType"
                                  + j
                                  + " ";
                        }

                        isOpenParentheses = false;
                        whereValue =
                            model
                                .getProductsOfProject()
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getPortfolioLicenses()
                                .get(i8)
                                .getLicenseType();
                        query +=
                            (andCondition
                                + Facade.getStatement(
                                    statement,
                                    "PortfolioLicense8.licenseType",
                                    "PortfolioLicense8licenseType" + j,
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
                          && model
                                  .getProductsOfProject()
                                  .getPortfolioLicense()
                                  .getPortfolio()
                                  .getPortfolioLicenses()
                                  .get(i8)
                                  .getIssueDate()
                              != null) {

                        andCondition = " AND ";
                        if (isOpenParentheses) {
                          andCondition = " ";
                        }
                        query += " ";
                        whereValue =
                            model
                                .getProductsOfProject()
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getPortfolioLicenses()
                                .get(i8)
                                .getIssueDate();
                        if (isDelete && fetchType == FetchType.EAGER) {
                          whereQuery = " PortfolioLicense8.issueDate = ? ";
                          counter++;
                        } else {
                          whereQuery =
                              " PortfolioLicense8.issueDate = :PortfolioLicense8issueDate"
                                  + j
                                  + " ";
                        }

                        isOpenParentheses = false;
                        whereValue =
                            model
                                .getProductsOfProject()
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getPortfolioLicenses()
                                .get(i8)
                                .getIssueDate();
                        query +=
                            (andCondition
                                + Facade.getStatement(
                                    statement,
                                    "PortfolioLicense8.issueDate",
                                    "PortfolioLicense8issueDate" + j,
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
                          && model
                                  .getProductsOfProject()
                                  .getPortfolioLicense()
                                  .getPortfolio()
                                  .getPortfolioLicenses()
                                  .get(i8)
                                  .getEndDate()
                              != null) {

                        andCondition = " AND ";
                        if (isOpenParentheses) {
                          andCondition = " ";
                        }
                        query += " ";
                        whereValue =
                            model
                                .getProductsOfProject()
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getPortfolioLicenses()
                                .get(i8)
                                .getEndDate();
                        if (isDelete && fetchType == FetchType.EAGER) {
                          whereQuery = " PortfolioLicense8.endDate = ? ";
                          counter++;
                        } else {
                          whereQuery =
                              " PortfolioLicense8.endDate = :PortfolioLicense8endDate" + j + " ";
                        }

                        isOpenParentheses = false;
                        whereValue =
                            model
                                .getProductsOfProject()
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getPortfolioLicenses()
                                .get(i8)
                                .getEndDate();
                        query +=
                            (andCondition
                                + Facade.getStatement(
                                    statement,
                                    "PortfolioLicense8.endDate",
                                    "PortfolioLicense8endDate" + j,
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
                          && model
                                  .getProductsOfProject()
                                  .getPortfolioLicense()
                                  .getPortfolio()
                                  .getPortfolioLicenses()
                                  .get(i8)
                                  .getVersion()
                              != 0) {

                        andCondition = " AND ";
                        if (isOpenParentheses) {
                          andCondition = " ";
                        }
                        query += " ";
                        whereValue =
                            new java.lang.Integer(
                                ""
                                    + model
                                        .getProductsOfProject()
                                        .getPortfolioLicense()
                                        .getPortfolio()
                                        .getPortfolioLicenses()
                                        .get(i8)
                                        .getVersion());
                        if (isDelete && fetchType == FetchType.EAGER) {
                          whereQuery = " PortfolioLicense8.VERSION = ? ";
                          counter++;
                        } else {
                          whereQuery =
                              " PortfolioLicense8.version = :PortfolioLicense8version" + j + " ";
                        }

                        isOpenParentheses = false;
                        whereValue =
                            new java.lang.Integer(
                                ""
                                    + model
                                        .getProductsOfProject()
                                        .getPortfolioLicense()
                                        .getPortfolio()
                                        .getPortfolioLicenses()
                                        .get(i8)
                                        .getVersion());
                        query +=
                            (andCondition
                                + Facade.getStatement(
                                    statement,
                                    "PortfolioLicense8.version",
                                    "PortfolioLicense8version" + j,
                                    mapVariable,
                                    whereQuery,
                                    whereValue,
                                    isExistRecord,
                                    isDelete,
                                    fetchType,
                                    counter));
                        whereQuery = "";
                      }
                      if (length8 > 1) {
                        query += " ) ";
                      }
                    }

                    query += ")";
                    isOpenParentheses = false;
                  }
                }

                if (!isExistRecord
                    && !isDelete
                    && fetchType == FetchType.EAGER
                    && model
                            .getProductsOfProject()
                            .getPortfolioLicense()
                            .getPortfolio()
                            .getPortfolioLocations()
                        != null) {
                  int length14 =
                      model
                          .getProductsOfProject()
                          .getPortfolioLicense()
                          .getPortfolio()
                          .getPortfolioLocations()
                          .size();

                  if (length14 > 0) {
                    if (isOpenParentheses) {
                      query += "  ( ";
                    } else {
                      query += " AND ( ";
                    }
                    isOpenParentheses = true;

                    for (int i14 = 0; i14 < length14; i14++) {

                      java.lang.String beforeCondition14 = "";
                      java.lang.String prefix14 = " OR ";
                      if (i14 == 0) {
                        prefix14 = "";
                      }
                      if (length14 > 1) {

                        query += (prefix14 + "(");
                        isOpenParentheses = true;

                      } else {

                        query += (prefix14);
                        if (!prefix14.trim().equals("")) {
                          isOpenParentheses = false;
                        }
                      }

                      if (model
                              .getProductsOfProject()
                              .getPortfolioLicense()
                              .getPortfolio()
                              .getPortfolioLocations()
                              .get(i14)
                              .getPortfolioLocationId()
                          != null) {

                        andCondition = " AND ";
                        if (isOpenParentheses) {
                          andCondition = " ";
                        }
                        query += " ";
                        whereValue =
                            model
                                .getProductsOfProject()
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getPortfolioLocations()
                                .get(i14)
                                .getPortfolioLocationId();
                        if (isDelete && fetchType == FetchType.EAGER) {
                          whereQuery = " PortfolioLocation14.id = ? ";
                          counter++;
                        } else {
                          whereQuery =
                              " PortfolioLocation14.portfolioLocationId = :PortfolioLocation14portfolioLocationId"
                                  + j
                                  + " ";
                        }

                        isOpenParentheses = false;
                        whereValue =
                            model
                                .getProductsOfProject()
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getPortfolioLocations()
                                .get(i14)
                                .getPortfolioLocationId();
                        query +=
                            (andCondition
                                + Facade.getStatement(
                                    statement,
                                    "PortfolioLocation14.portfolioLocationId",
                                    "PortfolioLocation14portfolioLocationId" + j,
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
                          && model
                                  .getProductsOfProject()
                                  .getPortfolioLicense()
                                  .getPortfolio()
                                  .getPortfolioLocations()
                                  .get(i14)
                                  .getFile()
                              != null) {

                        if (model
                                .getProductsOfProject()
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getPortfolioLocations()
                                .get(i14)
                                .getFile()
                                .getId()
                            != null) {

                          andCondition = " AND ";
                          if (isOpenParentheses) {
                            andCondition = " ";
                          }
                          query += " ";
                          whereValue =
                              model
                                  .getProductsOfProject()
                                  .getPortfolioLicense()
                                  .getPortfolio()
                                  .getPortfolioLocations()
                                  .get(i14)
                                  .getFile()
                                  .getId();
                          if (isDelete && fetchType == FetchType.EAGER) {
                            whereQuery = " File16.l4610flmtdataid = ? ";
                            counter++;
                          } else {
                            whereQuery = " File16.id = :File16id" + j + " ";
                          }

                          isOpenParentheses = false;
                          whereValue =
                              model
                                  .getProductsOfProject()
                                  .getPortfolioLicense()
                                  .getPortfolio()
                                  .getPortfolioLocations()
                                  .get(i14)
                                  .getFile()
                                  .getId();
                          query +=
                              (andCondition
                                  + Facade.getStatement(
                                      statement,
                                      "File16.id",
                                      "File16id" + j,
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
                            && model
                                    .getProductsOfProject()
                                    .getPortfolioLicense()
                                    .getPortfolio()
                                    .getPortfolioLocations()
                                    .get(i14)
                                    .getFile()
                                    .getExtension()
                                != null) {

                          andCondition = " AND ";
                          if (isOpenParentheses) {
                            andCondition = " ";
                          }
                          query += " ";
                          whereValue =
                              model
                                  .getProductsOfProject()
                                  .getPortfolioLicense()
                                  .getPortfolio()
                                  .getPortfolioLocations()
                                  .get(i14)
                                  .getFile()
                                  .getExtension();
                          if (isDelete && fetchType == FetchType.EAGER) {
                            whereQuery = " File16.l4610extension = ? ";
                            counter++;
                          } else {
                            whereQuery = " File16.extension = :File16extension" + j + " ";
                          }

                          isOpenParentheses = false;
                          whereValue =
                              model
                                  .getProductsOfProject()
                                  .getPortfolioLicense()
                                  .getPortfolio()
                                  .getPortfolioLocations()
                                  .get(i14)
                                  .getFile()
                                  .getExtension();
                          query +=
                              (andCondition
                                  + Facade.getStatement(
                                      statement,
                                      "File16.extension",
                                      "File16extension" + j,
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
                            && model
                                    .getProductsOfProject()
                                    .getPortfolioLicense()
                                    .getPortfolio()
                                    .getPortfolioLocations()
                                    .get(i14)
                                    .getFile()
                                    .getVersion()
                                != 0) {

                          andCondition = " AND ";
                          if (isOpenParentheses) {
                            andCondition = " ";
                          }
                          query += " ";
                          whereValue =
                              new java.lang.Integer(
                                  ""
                                      + model
                                          .getProductsOfProject()
                                          .getPortfolioLicense()
                                          .getPortfolio()
                                          .getPortfolioLocations()
                                          .get(i14)
                                          .getFile()
                                          .getVersion());
                          if (isDelete && fetchType == FetchType.EAGER) {
                            whereQuery = " File16.VERSION = ? ";
                            counter++;
                          } else {
                            whereQuery = " File16.version = :File16version" + j + " ";
                          }

                          isOpenParentheses = false;
                          whereValue =
                              new java.lang.Integer(
                                  ""
                                      + model
                                          .getProductsOfProject()
                                          .getPortfolioLicense()
                                          .getPortfolio()
                                          .getPortfolioLocations()
                                          .get(i14)
                                          .getFile()
                                          .getVersion());
                          query +=
                              (andCondition
                                  + Facade.getStatement(
                                      statement,
                                      "File16.version",
                                      "File16version" + j,
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
                      if (!isExistRecord
                          && !isDelete
                          && fetchType == FetchType.EAGER
                          && model
                                  .getProductsOfProject()
                                  .getPortfolioLicense()
                                  .getPortfolio()
                                  .getPortfolioLocations()
                                  .get(i14)
                                  .getLocationDetails()
                              != null) {
                        int length17 =
                            model
                                .getProductsOfProject()
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getPortfolioLocations()
                                .get(i14)
                                .getLocationDetails()
                                .size();

                        if (length17 > 0) {
                          if (isOpenParentheses) {
                            query += "  ( ";
                          } else {
                            query += " AND ( ";
                          }
                          isOpenParentheses = true;

                          for (int i17 = 0; i17 < length17; i17++) {

                            java.lang.String beforeCondition17 = "";
                            java.lang.String prefix17 = " OR ";
                            if (i17 == 0) {
                              prefix17 = "";
                            }
                            if (length17 > 1) {

                              query += (prefix17 + "(");
                              isOpenParentheses = true;

                            } else {

                              query += (prefix17);
                              if (!prefix17.trim().equals("")) {
                                isOpenParentheses = false;
                              }
                            }

                            if (model
                                    .getProductsOfProject()
                                    .getPortfolioLicense()
                                    .getPortfolio()
                                    .getPortfolioLocations()
                                    .get(i14)
                                    .getLocationDetails()
                                    .get(i17)
                                    .getLocationDetailId()
                                != null) {

                              andCondition = " AND ";
                              if (isOpenParentheses) {
                                andCondition = " ";
                              }
                              query += " ";
                              whereValue =
                                  model
                                      .getProductsOfProject()
                                      .getPortfolioLicense()
                                      .getPortfolio()
                                      .getPortfolioLocations()
                                      .get(i14)
                                      .getLocationDetails()
                                      .get(i17)
                                      .getLocationDetailId();
                              if (isDelete && fetchType == FetchType.EAGER) {
                                whereQuery = " LocationDetails17.id = ? ";
                                counter++;
                              } else {
                                whereQuery =
                                    " LocationDetails17.locationDetailId = :LocationDetails17locationDetailId"
                                        + j
                                        + " ";
                              }

                              isOpenParentheses = false;
                              whereValue =
                                  model
                                      .getProductsOfProject()
                                      .getPortfolioLicense()
                                      .getPortfolio()
                                      .getPortfolioLocations()
                                      .get(i14)
                                      .getLocationDetails()
                                      .get(i17)
                                      .getLocationDetailId();
                              query +=
                                  (andCondition
                                      + Facade.getStatement(
                                          statement,
                                          "LocationDetails17.locationDetailId",
                                          "LocationDetails17locationDetailId" + j,
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
                                && model
                                        .getProductsOfProject()
                                        .getPortfolioLicense()
                                        .getPortfolio()
                                        .getPortfolioLocations()
                                        .get(i14)
                                        .getLocationDetails()
                                        .get(i17)
                                        .getPortfolioLocation()
                                    != null) {

                              if (model
                                      .getProductsOfProject()
                                      .getPortfolioLicense()
                                      .getPortfolio()
                                      .getPortfolioLocations()
                                      .get(i14)
                                      .getLocationDetails()
                                      .get(i17)
                                      .getPortfolioLocation()
                                      .getPortfolioLocationId()
                                  != null) {

                                andCondition = " AND ";
                                if (isOpenParentheses) {
                                  andCondition = " ";
                                }
                                query += " ";
                                whereValue =
                                    model
                                        .getProductsOfProject()
                                        .getPortfolioLicense()
                                        .getPortfolio()
                                        .getPortfolioLocations()
                                        .get(i14)
                                        .getLocationDetails()
                                        .get(i17)
                                        .getPortfolioLocation()
                                        .getPortfolioLocationId();
                                if (isDelete && fetchType == FetchType.EAGER) {
                                  whereQuery = " PortfolioLocation18.id = ? ";
                                  counter++;
                                } else {
                                  whereQuery =
                                      " PortfolioLocation18.portfolioLocationId = :PortfolioLocation18portfolioLocationId"
                                          + j
                                          + " ";
                                }

                                isOpenParentheses = false;
                                whereValue =
                                    model
                                        .getProductsOfProject()
                                        .getPortfolioLicense()
                                        .getPortfolio()
                                        .getPortfolioLocations()
                                        .get(i14)
                                        .getLocationDetails()
                                        .get(i17)
                                        .getPortfolioLocation()
                                        .getPortfolioLocationId();
                                query +=
                                    (andCondition
                                        + Facade.getStatement(
                                            statement,
                                            "PortfolioLocation18.portfolioLocationId",
                                            "PortfolioLocation18portfolioLocationId" + j,
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
                                  && model
                                          .getProductsOfProject()
                                          .getPortfolioLicense()
                                          .getPortfolio()
                                          .getPortfolioLocations()
                                          .get(i14)
                                          .getLocationDetails()
                                          .get(i17)
                                          .getPortfolioLocation()
                                          .getFile()
                                      != null) {

                                if (model
                                        .getProductsOfProject()
                                        .getPortfolioLicense()
                                        .getPortfolio()
                                        .getPortfolioLocations()
                                        .get(i14)
                                        .getLocationDetails()
                                        .get(i17)
                                        .getPortfolioLocation()
                                        .getFile()
                                        .getId()
                                    != null) {

                                  andCondition = " AND ";
                                  if (isOpenParentheses) {
                                    andCondition = " ";
                                  }
                                  query += " ";
                                  whereValue =
                                      model
                                          .getProductsOfProject()
                                          .getPortfolioLicense()
                                          .getPortfolio()
                                          .getPortfolioLocations()
                                          .get(i14)
                                          .getLocationDetails()
                                          .get(i17)
                                          .getPortfolioLocation()
                                          .getFile()
                                          .getId();
                                  if (isDelete && fetchType == FetchType.EAGER) {
                                    whereQuery = " File20.l4610flmtdataid = ? ";
                                    counter++;
                                  } else {
                                    whereQuery = " File20.id = :File20id" + j + " ";
                                  }

                                  isOpenParentheses = false;
                                  whereValue =
                                      model
                                          .getProductsOfProject()
                                          .getPortfolioLicense()
                                          .getPortfolio()
                                          .getPortfolioLocations()
                                          .get(i14)
                                          .getLocationDetails()
                                          .get(i17)
                                          .getPortfolioLocation()
                                          .getFile()
                                          .getId();
                                  query +=
                                      (andCondition
                                          + Facade.getStatement(
                                              statement,
                                              "File20.id",
                                              "File20id" + j,
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
                                    && model
                                            .getProductsOfProject()
                                            .getPortfolioLicense()
                                            .getPortfolio()
                                            .getPortfolioLocations()
                                            .get(i14)
                                            .getLocationDetails()
                                            .get(i17)
                                            .getPortfolioLocation()
                                            .getFile()
                                            .getExtension()
                                        != null) {

                                  andCondition = " AND ";
                                  if (isOpenParentheses) {
                                    andCondition = " ";
                                  }
                                  query += " ";
                                  whereValue =
                                      model
                                          .getProductsOfProject()
                                          .getPortfolioLicense()
                                          .getPortfolio()
                                          .getPortfolioLocations()
                                          .get(i14)
                                          .getLocationDetails()
                                          .get(i17)
                                          .getPortfolioLocation()
                                          .getFile()
                                          .getExtension();
                                  if (isDelete && fetchType == FetchType.EAGER) {
                                    whereQuery = " File20.l4610extension = ? ";
                                    counter++;
                                  } else {
                                    whereQuery = " File20.extension = :File20extension" + j + " ";
                                  }

                                  isOpenParentheses = false;
                                  whereValue =
                                      model
                                          .getProductsOfProject()
                                          .getPortfolioLicense()
                                          .getPortfolio()
                                          .getPortfolioLocations()
                                          .get(i14)
                                          .getLocationDetails()
                                          .get(i17)
                                          .getPortfolioLocation()
                                          .getFile()
                                          .getExtension();
                                  query +=
                                      (andCondition
                                          + Facade.getStatement(
                                              statement,
                                              "File20.extension",
                                              "File20extension" + j,
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
                                    && model
                                            .getProductsOfProject()
                                            .getPortfolioLicense()
                                            .getPortfolio()
                                            .getPortfolioLocations()
                                            .get(i14)
                                            .getLocationDetails()
                                            .get(i17)
                                            .getPortfolioLocation()
                                            .getFile()
                                            .getVersion()
                                        != 0) {

                                  andCondition = " AND ";
                                  if (isOpenParentheses) {
                                    andCondition = " ";
                                  }
                                  query += " ";
                                  whereValue =
                                      new java.lang.Integer(
                                          ""
                                              + model
                                                  .getProductsOfProject()
                                                  .getPortfolioLicense()
                                                  .getPortfolio()
                                                  .getPortfolioLocations()
                                                  .get(i14)
                                                  .getLocationDetails()
                                                  .get(i17)
                                                  .getPortfolioLocation()
                                                  .getFile()
                                                  .getVersion());
                                  if (isDelete && fetchType == FetchType.EAGER) {
                                    whereQuery = " File20.VERSION = ? ";
                                    counter++;
                                  } else {
                                    whereQuery = " File20.version = :File20version" + j + " ";
                                  }

                                  isOpenParentheses = false;
                                  whereValue =
                                      new java.lang.Integer(
                                          ""
                                              + model
                                                  .getProductsOfProject()
                                                  .getPortfolioLicense()
                                                  .getPortfolio()
                                                  .getPortfolioLocations()
                                                  .get(i14)
                                                  .getLocationDetails()
                                                  .get(i17)
                                                  .getPortfolioLocation()
                                                  .getFile()
                                                  .getVersion());
                                  query +=
                                      (andCondition
                                          + Facade.getStatement(
                                              statement,
                                              "File20.version",
                                              "File20version" + j,
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
                              if (!isExistRecord
                                  && !isDelete
                                  && true
                                  && model
                                          .getProductsOfProject()
                                          .getPortfolioLicense()
                                          .getPortfolio()
                                          .getPortfolioLocations()
                                          .get(i14)
                                          .getLocationDetails()
                                          .get(i17)
                                          .getPortfolioLocation()
                                          .getProvince()
                                      != null) {

                                andCondition = " AND ";
                                if (isOpenParentheses) {
                                  andCondition = " ";
                                }
                                query += " ";
                                whereValue =
                                    model
                                        .getProductsOfProject()
                                        .getPortfolioLicense()
                                        .getPortfolio()
                                        .getPortfolioLocations()
                                        .get(i14)
                                        .getLocationDetails()
                                        .get(i17)
                                        .getPortfolioLocation()
                                        .getProvince();
                                if (isDelete && fetchType == FetchType.EAGER) {
                                  whereQuery = " PortfolioLocation18.province = ? ";
                                  counter++;
                                } else {
                                  whereQuery =
                                      " PortfolioLocation18.province = :PortfolioLocation18province"
                                          + j
                                          + " ";
                                }

                                isOpenParentheses = false;
                                whereValue =
                                    model
                                        .getProductsOfProject()
                                        .getPortfolioLicense()
                                        .getPortfolio()
                                        .getPortfolioLocations()
                                        .get(i14)
                                        .getLocationDetails()
                                        .get(i17)
                                        .getPortfolioLocation()
                                        .getProvince();
                                query +=
                                    (andCondition
                                        + Facade.getStatement(
                                            statement,
                                            "PortfolioLocation18.province",
                                            "PortfolioLocation18province" + j,
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
                                  && model
                                          .getProductsOfProject()
                                          .getPortfolioLicense()
                                          .getPortfolio()
                                          .getPortfolioLocations()
                                          .get(i14)
                                          .getLocationDetails()
                                          .get(i17)
                                          .getPortfolioLocation()
                                          .getCity()
                                      != null) {

                                andCondition = " AND ";
                                if (isOpenParentheses) {
                                  andCondition = " ";
                                }
                                query += " ";
                                whereValue =
                                    model
                                        .getProductsOfProject()
                                        .getPortfolioLicense()
                                        .getPortfolio()
                                        .getPortfolioLocations()
                                        .get(i14)
                                        .getLocationDetails()
                                        .get(i17)
                                        .getPortfolioLocation()
                                        .getCity();
                                if (isDelete && fetchType == FetchType.EAGER) {
                                  whereQuery = " PortfolioLocation18.city = ? ";
                                  counter++;
                                } else {
                                  whereQuery =
                                      " PortfolioLocation18.city = :PortfolioLocation18city"
                                          + j
                                          + " ";
                                }

                                isOpenParentheses = false;
                                whereValue =
                                    model
                                        .getProductsOfProject()
                                        .getPortfolioLicense()
                                        .getPortfolio()
                                        .getPortfolioLocations()
                                        .get(i14)
                                        .getLocationDetails()
                                        .get(i17)
                                        .getPortfolioLocation()
                                        .getCity();
                                query +=
                                    (andCondition
                                        + Facade.getStatement(
                                            statement,
                                            "PortfolioLocation18.city",
                                            "PortfolioLocation18city" + j,
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
                                  && model
                                          .getProductsOfProject()
                                          .getPortfolioLicense()
                                          .getPortfolio()
                                          .getPortfolioLocations()
                                          .get(i14)
                                          .getLocationDetails()
                                          .get(i17)
                                          .getPortfolioLocation()
                                          .getIndstrltwn()
                                      != null) {

                                andCondition = " AND ";
                                if (isOpenParentheses) {
                                  andCondition = " ";
                                }
                                query += " ";
                                whereValue =
                                    model
                                        .getProductsOfProject()
                                        .getPortfolioLicense()
                                        .getPortfolio()
                                        .getPortfolioLocations()
                                        .get(i14)
                                        .getLocationDetails()
                                        .get(i17)
                                        .getPortfolioLocation()
                                        .getIndstrltwn();
                                if (isDelete && fetchType == FetchType.EAGER) {
                                  whereQuery = " PortfolioLocation18.indstrltwn = ? ";
                                  counter++;
                                } else {
                                  whereQuery =
                                      " PortfolioLocation18.indstrltwn = :PortfolioLocation18indstrltwn"
                                          + j
                                          + " ";
                                }

                                isOpenParentheses = false;
                                whereValue =
                                    model
                                        .getProductsOfProject()
                                        .getPortfolioLicense()
                                        .getPortfolio()
                                        .getPortfolioLocations()
                                        .get(i14)
                                        .getLocationDetails()
                                        .get(i17)
                                        .getPortfolioLocation()
                                        .getIndstrltwn();
                                query +=
                                    (andCondition
                                        + Facade.getStatement(
                                            statement,
                                            "PortfolioLocation18.indstrltwn",
                                            "PortfolioLocation18indstrltwn" + j,
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
                                  && model
                                          .getProductsOfProject()
                                          .getPortfolioLicense()
                                          .getPortfolio()
                                          .getPortfolioLocations()
                                          .get(i14)
                                          .getLocationDetails()
                                          .get(i17)
                                          .getPortfolioLocation()
                                          .getAddress()
                                      != null) {

                                andCondition = " AND ";
                                if (isOpenParentheses) {
                                  andCondition = " ";
                                }
                                query += " ";
                                whereValue =
                                    model
                                        .getProductsOfProject()
                                        .getPortfolioLicense()
                                        .getPortfolio()
                                        .getPortfolioLocations()
                                        .get(i14)
                                        .getLocationDetails()
                                        .get(i17)
                                        .getPortfolioLocation()
                                        .getAddress();
                                if (isDelete && fetchType == FetchType.EAGER) {
                                  whereQuery = " PortfolioLocation18.address = ? ";
                                  counter++;
                                } else {
                                  whereQuery =
                                      " PortfolioLocation18.address = :PortfolioLocation18address"
                                          + j
                                          + " ";
                                }

                                isOpenParentheses = false;
                                whereValue =
                                    model
                                        .getProductsOfProject()
                                        .getPortfolioLicense()
                                        .getPortfolio()
                                        .getPortfolioLocations()
                                        .get(i14)
                                        .getLocationDetails()
                                        .get(i17)
                                        .getPortfolioLocation()
                                        .getAddress();
                                query +=
                                    (andCondition
                                        + Facade.getStatement(
                                            statement,
                                            "PortfolioLocation18.address",
                                            "PortfolioLocation18address" + j,
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
                                  && model
                                          .getProductsOfProject()
                                          .getPortfolioLicense()
                                          .getPortfolio()
                                          .getPortfolioLocations()
                                          .get(i14)
                                          .getLocationDetails()
                                          .get(i17)
                                          .getPortfolioLocation()
                                          .getStableStatusType()
                                      != null) {

                                andCondition = " AND ";
                                if (isOpenParentheses) {
                                  andCondition = " ";
                                }
                                query += " ";
                                whereValue =
                                    model
                                        .getProductsOfProject()
                                        .getPortfolioLicense()
                                        .getPortfolio()
                                        .getPortfolioLocations()
                                        .get(i14)
                                        .getLocationDetails()
                                        .get(i17)
                                        .getPortfolioLocation()
                                        .getStableStatusType();
                                if (isDelete && fetchType == FetchType.EAGER) {
                                  whereQuery = " PortfolioLocation18.stableStatusType = ? ";
                                  counter++;
                                } else {
                                  whereQuery =
                                      " PortfolioLocation18.stableStatusType = :PortfolioLocation18stableStatusType"
                                          + j
                                          + " ";
                                }

                                isOpenParentheses = false;
                                whereValue =
                                    model
                                        .getProductsOfProject()
                                        .getPortfolioLicense()
                                        .getPortfolio()
                                        .getPortfolioLocations()
                                        .get(i14)
                                        .getLocationDetails()
                                        .get(i17)
                                        .getPortfolioLocation()
                                        .getStableStatusType();
                                query +=
                                    (andCondition
                                        + Facade.getStatement(
                                            statement,
                                            "PortfolioLocation18.stableStatusType",
                                            "PortfolioLocation18stableStatusType" + j,
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
                                  && model
                                          .getProductsOfProject()
                                          .getPortfolioLicense()
                                          .getPortfolio()
                                          .getPortfolioLocations()
                                          .get(i14)
                                          .getLocationDetails()
                                          .get(i17)
                                          .getPortfolioLocation()
                                          .getOwnerStatusType()
                                      != null) {

                                andCondition = " AND ";
                                if (isOpenParentheses) {
                                  andCondition = " ";
                                }
                                query += " ";
                                whereValue =
                                    model
                                        .getProductsOfProject()
                                        .getPortfolioLicense()
                                        .getPortfolio()
                                        .getPortfolioLocations()
                                        .get(i14)
                                        .getLocationDetails()
                                        .get(i17)
                                        .getPortfolioLocation()
                                        .getOwnerStatusType();
                                if (isDelete && fetchType == FetchType.EAGER) {
                                  whereQuery = " PortfolioLocation18.ownerStatusType = ? ";
                                  counter++;
                                } else {
                                  whereQuery =
                                      " PortfolioLocation18.ownerStatusType = :PortfolioLocation18ownerStatusType"
                                          + j
                                          + " ";
                                }

                                isOpenParentheses = false;
                                whereValue =
                                    model
                                        .getProductsOfProject()
                                        .getPortfolioLicense()
                                        .getPortfolio()
                                        .getPortfolioLocations()
                                        .get(i14)
                                        .getLocationDetails()
                                        .get(i17)
                                        .getPortfolioLocation()
                                        .getOwnerStatusType();
                                query +=
                                    (andCondition
                                        + Facade.getStatement(
                                            statement,
                                            "PortfolioLocation18.ownerStatusType",
                                            "PortfolioLocation18ownerStatusType" + j,
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
                                  && model
                                          .getProductsOfProject()
                                          .getPortfolioLicense()
                                          .getPortfolio()
                                          .getPortfolioLocations()
                                          .get(i14)
                                          .getLocationDetails()
                                          .get(i17)
                                          .getPortfolioLocation()
                                          .getRegionalStatusType()
                                      != null) {

                                andCondition = " AND ";
                                if (isOpenParentheses) {
                                  andCondition = " ";
                                }
                                query += " ";
                                whereValue =
                                    model
                                        .getProductsOfProject()
                                        .getPortfolioLicense()
                                        .getPortfolio()
                                        .getPortfolioLocations()
                                        .get(i14)
                                        .getLocationDetails()
                                        .get(i17)
                                        .getPortfolioLocation()
                                        .getRegionalStatusType();
                                if (isDelete && fetchType == FetchType.EAGER) {
                                  whereQuery = " PortfolioLocation18.regionalStatusType = ? ";
                                  counter++;
                                } else {
                                  whereQuery =
                                      " PortfolioLocation18.regionalStatusType = :PortfolioLocation18regionalStatusType"
                                          + j
                                          + " ";
                                }

                                isOpenParentheses = false;
                                whereValue =
                                    model
                                        .getProductsOfProject()
                                        .getPortfolioLicense()
                                        .getPortfolio()
                                        .getPortfolioLocations()
                                        .get(i14)
                                        .getLocationDetails()
                                        .get(i17)
                                        .getPortfolioLocation()
                                        .getRegionalStatusType();
                                query +=
                                    (andCondition
                                        + Facade.getStatement(
                                            statement,
                                            "PortfolioLocation18.regionalStatusType",
                                            "PortfolioLocation18regionalStatusType" + j,
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
                                  && model
                                          .getProductsOfProject()
                                          .getPortfolioLicense()
                                          .getPortfolio()
                                          .getPortfolioLocations()
                                          .get(i14)
                                          .getLocationDetails()
                                          .get(i17)
                                          .getPortfolioLocation()
                                          .getVersion()
                                      != 0) {

                                andCondition = " AND ";
                                if (isOpenParentheses) {
                                  andCondition = " ";
                                }
                                query += " ";
                                whereValue =
                                    new java.lang.Integer(
                                        ""
                                            + model
                                                .getProductsOfProject()
                                                .getPortfolioLicense()
                                                .getPortfolio()
                                                .getPortfolioLocations()
                                                .get(i14)
                                                .getLocationDetails()
                                                .get(i17)
                                                .getPortfolioLocation()
                                                .getVersion());
                                if (isDelete && fetchType == FetchType.EAGER) {
                                  whereQuery = " PortfolioLocation18.VERSION = ? ";
                                  counter++;
                                } else {
                                  whereQuery =
                                      " PortfolioLocation18.version = :PortfolioLocation18version"
                                          + j
                                          + " ";
                                }

                                isOpenParentheses = false;
                                whereValue =
                                    new java.lang.Integer(
                                        ""
                                            + model
                                                .getProductsOfProject()
                                                .getPortfolioLicense()
                                                .getPortfolio()
                                                .getPortfolioLocations()
                                                .get(i14)
                                                .getLocationDetails()
                                                .get(i17)
                                                .getPortfolioLocation()
                                                .getVersion());
                                query +=
                                    (andCondition
                                        + Facade.getStatement(
                                            statement,
                                            "PortfolioLocation18.version",
                                            "PortfolioLocation18version" + j,
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
                            if (!isExistRecord
                                && !isDelete
                                && model
                                        .getProductsOfProject()
                                        .getPortfolioLicense()
                                        .getPortfolio()
                                        .getPortfolioLocations()
                                        .get(i14)
                                        .getLocationDetails()
                                        .get(i17)
                                        .getFile()
                                    != null) {

                              if (model
                                      .getProductsOfProject()
                                      .getPortfolioLicense()
                                      .getPortfolio()
                                      .getPortfolioLocations()
                                      .get(i14)
                                      .getLocationDetails()
                                      .get(i17)
                                      .getFile()
                                      .getId()
                                  != null) {

                                andCondition = " AND ";
                                if (isOpenParentheses) {
                                  andCondition = " ";
                                }
                                query += " ";
                                whereValue =
                                    model
                                        .getProductsOfProject()
                                        .getPortfolioLicense()
                                        .getPortfolio()
                                        .getPortfolioLocations()
                                        .get(i14)
                                        .getLocationDetails()
                                        .get(i17)
                                        .getFile()
                                        .getId();
                                if (isDelete && fetchType == FetchType.EAGER) {
                                  whereQuery = " File22.l4610flmtdataid = ? ";
                                  counter++;
                                } else {
                                  whereQuery = " File22.id = :File22id" + j + " ";
                                }

                                isOpenParentheses = false;
                                whereValue =
                                    model
                                        .getProductsOfProject()
                                        .getPortfolioLicense()
                                        .getPortfolio()
                                        .getPortfolioLocations()
                                        .get(i14)
                                        .getLocationDetails()
                                        .get(i17)
                                        .getFile()
                                        .getId();
                                query +=
                                    (andCondition
                                        + Facade.getStatement(
                                            statement,
                                            "File22.id",
                                            "File22id" + j,
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
                                  && model
                                          .getProductsOfProject()
                                          .getPortfolioLicense()
                                          .getPortfolio()
                                          .getPortfolioLocations()
                                          .get(i14)
                                          .getLocationDetails()
                                          .get(i17)
                                          .getFile()
                                          .getExtension()
                                      != null) {

                                andCondition = " AND ";
                                if (isOpenParentheses) {
                                  andCondition = " ";
                                }
                                query += " ";
                                whereValue =
                                    model
                                        .getProductsOfProject()
                                        .getPortfolioLicense()
                                        .getPortfolio()
                                        .getPortfolioLocations()
                                        .get(i14)
                                        .getLocationDetails()
                                        .get(i17)
                                        .getFile()
                                        .getExtension();
                                if (isDelete && fetchType == FetchType.EAGER) {
                                  whereQuery = " File22.l4610extension = ? ";
                                  counter++;
                                } else {
                                  whereQuery = " File22.extension = :File22extension" + j + " ";
                                }

                                isOpenParentheses = false;
                                whereValue =
                                    model
                                        .getProductsOfProject()
                                        .getPortfolioLicense()
                                        .getPortfolio()
                                        .getPortfolioLocations()
                                        .get(i14)
                                        .getLocationDetails()
                                        .get(i17)
                                        .getFile()
                                        .getExtension();
                                query +=
                                    (andCondition
                                        + Facade.getStatement(
                                            statement,
                                            "File22.extension",
                                            "File22extension" + j,
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
                                  && model
                                          .getProductsOfProject()
                                          .getPortfolioLicense()
                                          .getPortfolio()
                                          .getPortfolioLocations()
                                          .get(i14)
                                          .getLocationDetails()
                                          .get(i17)
                                          .getFile()
                                          .getVersion()
                                      != 0) {

                                andCondition = " AND ";
                                if (isOpenParentheses) {
                                  andCondition = " ";
                                }
                                query += " ";
                                whereValue =
                                    new java.lang.Integer(
                                        ""
                                            + model
                                                .getProductsOfProject()
                                                .getPortfolioLicense()
                                                .getPortfolio()
                                                .getPortfolioLocations()
                                                .get(i14)
                                                .getLocationDetails()
                                                .get(i17)
                                                .getFile()
                                                .getVersion());
                                if (isDelete && fetchType == FetchType.EAGER) {
                                  whereQuery = " File22.VERSION = ? ";
                                  counter++;
                                } else {
                                  whereQuery = " File22.version = :File22version" + j + " ";
                                }

                                isOpenParentheses = false;
                                whereValue =
                                    new java.lang.Integer(
                                        ""
                                            + model
                                                .getProductsOfProject()
                                                .getPortfolioLicense()
                                                .getPortfolio()
                                                .getPortfolioLocations()
                                                .get(i14)
                                                .getLocationDetails()
                                                .get(i17)
                                                .getFile()
                                                .getVersion());
                                query +=
                                    (andCondition
                                        + Facade.getStatement(
                                            statement,
                                            "File22.version",
                                            "File22version" + j,
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
                            if (!isDelete
                                && model
                                        .getProductsOfProject()
                                        .getPortfolioLicense()
                                        .getPortfolio()
                                        .getPortfolioLocations()
                                        .get(i14)
                                        .getLocationDetails()
                                        .get(i17)
                                        .getAddress()
                                    != null) {
                              if (!isDelete
                                  && true
                                  && model
                                          .getProductsOfProject()
                                          .getPortfolioLicense()
                                          .getPortfolio()
                                          .getPortfolioLocations()
                                          .get(i14)
                                          .getLocationDetails()
                                          .get(i17)
                                          .getAddress()
                                          .getAddressLine1()
                                      != null) {

                                andCondition = " AND ";
                                if (isOpenParentheses) {
                                  andCondition = " ";
                                }
                                query += " ";
                                whereValue =
                                    model
                                        .getProductsOfProject()
                                        .getPortfolioLicense()
                                        .getPortfolio()
                                        .getPortfolioLocations()
                                        .get(i14)
                                        .getLocationDetails()
                                        .get(i17)
                                        .getAddress()
                                        .getAddressLine1();
                                if (isDelete && fetchType == FetchType.EAGER) {
                                  whereQuery = " LocationDetails17.addressLine1 = ? ";
                                  counter++;
                                } else {
                                  whereQuery =
                                      " LocationDetails17.address.addressLine1 = :LocationDetails17addressaddressLine1"
                                          + j
                                          + " ";
                                }

                                isOpenParentheses = false;
                                whereValue =
                                    model
                                        .getProductsOfProject()
                                        .getPortfolioLicense()
                                        .getPortfolio()
                                        .getPortfolioLocations()
                                        .get(i14)
                                        .getLocationDetails()
                                        .get(i17)
                                        .getAddress()
                                        .getAddressLine1();
                                query +=
                                    (andCondition
                                        + Facade.getStatement(
                                            statement,
                                            "LocationDetails17.address.addressLine1",
                                            "LocationDetails17addressaddressLine1" + j,
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
                                  && model
                                          .getProductsOfProject()
                                          .getPortfolioLicense()
                                          .getPortfolio()
                                          .getPortfolioLocations()
                                          .get(i14)
                                          .getLocationDetails()
                                          .get(i17)
                                          .getAddress()
                                          .getAddressLine2()
                                      != null) {

                                andCondition = " AND ";
                                if (isOpenParentheses) {
                                  andCondition = " ";
                                }
                                query += " ";
                                whereValue =
                                    model
                                        .getProductsOfProject()
                                        .getPortfolioLicense()
                                        .getPortfolio()
                                        .getPortfolioLocations()
                                        .get(i14)
                                        .getLocationDetails()
                                        .get(i17)
                                        .getAddress()
                                        .getAddressLine2();
                                if (isDelete && fetchType == FetchType.EAGER) {
                                  whereQuery = " LocationDetails17.addressLine2 = ? ";
                                  counter++;
                                } else {
                                  whereQuery =
                                      " LocationDetails17.address.addressLine2 = :LocationDetails17addressaddressLine2"
                                          + j
                                          + " ";
                                }

                                isOpenParentheses = false;
                                whereValue =
                                    model
                                        .getProductsOfProject()
                                        .getPortfolioLicense()
                                        .getPortfolio()
                                        .getPortfolioLocations()
                                        .get(i14)
                                        .getLocationDetails()
                                        .get(i17)
                                        .getAddress()
                                        .getAddressLine2();
                                query +=
                                    (andCondition
                                        + Facade.getStatement(
                                            statement,
                                            "LocationDetails17.address.addressLine2",
                                            "LocationDetails17addressaddressLine2" + j,
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
                                  && model
                                          .getProductsOfProject()
                                          .getPortfolioLicense()
                                          .getPortfolio()
                                          .getPortfolioLocations()
                                          .get(i14)
                                          .getLocationDetails()
                                          .get(i17)
                                          .getAddress()
                                          .getCity()
                                      != null) {

                                andCondition = " AND ";
                                if (isOpenParentheses) {
                                  andCondition = " ";
                                }
                                query += " ";
                                whereValue =
                                    model
                                        .getProductsOfProject()
                                        .getPortfolioLicense()
                                        .getPortfolio()
                                        .getPortfolioLocations()
                                        .get(i14)
                                        .getLocationDetails()
                                        .get(i17)
                                        .getAddress()
                                        .getCity();
                                if (isDelete && fetchType == FetchType.EAGER) {
                                  whereQuery = " LocationDetails17.city = ? ";
                                  counter++;
                                } else {
                                  whereQuery =
                                      " LocationDetails17.address.city = :LocationDetails17addresscity"
                                          + j
                                          + " ";
                                }

                                isOpenParentheses = false;
                                whereValue =
                                    model
                                        .getProductsOfProject()
                                        .getPortfolioLicense()
                                        .getPortfolio()
                                        .getPortfolioLocations()
                                        .get(i14)
                                        .getLocationDetails()
                                        .get(i17)
                                        .getAddress()
                                        .getCity();
                                query +=
                                    (andCondition
                                        + Facade.getStatement(
                                            statement,
                                            "LocationDetails17.address.city",
                                            "LocationDetails17addresscity" + j,
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
                                  && model
                                          .getProductsOfProject()
                                          .getPortfolioLicense()
                                          .getPortfolio()
                                          .getPortfolioLocations()
                                          .get(i14)
                                          .getLocationDetails()
                                          .get(i17)
                                          .getAddress()
                                          .getState()
                                      != null) {

                                andCondition = " AND ";
                                if (isOpenParentheses) {
                                  andCondition = " ";
                                }
                                query += " ";
                                whereValue =
                                    model
                                        .getProductsOfProject()
                                        .getPortfolioLicense()
                                        .getPortfolio()
                                        .getPortfolioLocations()
                                        .get(i14)
                                        .getLocationDetails()
                                        .get(i17)
                                        .getAddress()
                                        .getState();
                                if (isDelete && fetchType == FetchType.EAGER) {
                                  whereQuery = " LocationDetails17.state = ? ";
                                  counter++;
                                } else {
                                  whereQuery =
                                      " LocationDetails17.address.state = :LocationDetails17addressstate"
                                          + j
                                          + " ";
                                }

                                isOpenParentheses = false;
                                whereValue =
                                    model
                                        .getProductsOfProject()
                                        .getPortfolioLicense()
                                        .getPortfolio()
                                        .getPortfolioLocations()
                                        .get(i14)
                                        .getLocationDetails()
                                        .get(i17)
                                        .getAddress()
                                        .getState();
                                query +=
                                    (andCondition
                                        + Facade.getStatement(
                                            statement,
                                            "LocationDetails17.address.state",
                                            "LocationDetails17addressstate" + j,
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
                                  && model
                                          .getProductsOfProject()
                                          .getPortfolioLicense()
                                          .getPortfolio()
                                          .getPortfolioLocations()
                                          .get(i14)
                                          .getLocationDetails()
                                          .get(i17)
                                          .getAddress()
                                          .getCountry()
                                      != null) {

                                andCondition = " AND ";
                                if (isOpenParentheses) {
                                  andCondition = " ";
                                }
                                query += " ";
                                whereValue =
                                    model
                                        .getProductsOfProject()
                                        .getPortfolioLicense()
                                        .getPortfolio()
                                        .getPortfolioLocations()
                                        .get(i14)
                                        .getLocationDetails()
                                        .get(i17)
                                        .getAddress()
                                        .getCountry();
                                if (isDelete && fetchType == FetchType.EAGER) {
                                  whereQuery = " LocationDetails17.country = ? ";
                                  counter++;
                                } else {
                                  whereQuery =
                                      " LocationDetails17.address.country = :LocationDetails17addresscountry"
                                          + j
                                          + " ";
                                }

                                isOpenParentheses = false;
                                whereValue =
                                    model
                                        .getProductsOfProject()
                                        .getPortfolioLicense()
                                        .getPortfolio()
                                        .getPortfolioLocations()
                                        .get(i14)
                                        .getLocationDetails()
                                        .get(i17)
                                        .getAddress()
                                        .getCountry();
                                query +=
                                    (andCondition
                                        + Facade.getStatement(
                                            statement,
                                            "LocationDetails17.address.country",
                                            "LocationDetails17addresscountry" + j,
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
                                  && model
                                          .getProductsOfProject()
                                          .getPortfolioLicense()
                                          .getPortfolio()
                                          .getPortfolioLocations()
                                          .get(i14)
                                          .getLocationDetails()
                                          .get(i17)
                                          .getAddress()
                                          .getZipCode()
                                      != null) {

                                andCondition = " AND ";
                                if (isOpenParentheses) {
                                  andCondition = " ";
                                }
                                query += " ";
                                whereValue =
                                    model
                                        .getProductsOfProject()
                                        .getPortfolioLicense()
                                        .getPortfolio()
                                        .getPortfolioLocations()
                                        .get(i14)
                                        .getLocationDetails()
                                        .get(i17)
                                        .getAddress()
                                        .getZipCode();
                                if (isDelete && fetchType == FetchType.EAGER) {
                                  whereQuery = " LocationDetails17.zipCode = ? ";
                                  counter++;
                                } else {
                                  whereQuery =
                                      " LocationDetails17.address.zipCode = :LocationDetails17addresszipCode"
                                          + j
                                          + " ";
                                }

                                isOpenParentheses = false;
                                whereValue =
                                    model
                                        .getProductsOfProject()
                                        .getPortfolioLicense()
                                        .getPortfolio()
                                        .getPortfolioLocations()
                                        .get(i14)
                                        .getLocationDetails()
                                        .get(i17)
                                        .getAddress()
                                        .getZipCode();
                                query +=
                                    (andCondition
                                        + Facade.getStatement(
                                            statement,
                                            "LocationDetails17.address.zipCode",
                                            "LocationDetails17addresszipCode" + j,
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
                            if (length17 > 1) {
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
                          && model
                                  .getProductsOfProject()
                                  .getPortfolioLicense()
                                  .getPortfolio()
                                  .getPortfolioLocations()
                                  .get(i14)
                                  .getProvince()
                              != null) {

                        andCondition = " AND ";
                        if (isOpenParentheses) {
                          andCondition = " ";
                        }
                        query += " ";
                        whereValue =
                            model
                                .getProductsOfProject()
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getPortfolioLocations()
                                .get(i14)
                                .getProvince();
                        if (isDelete && fetchType == FetchType.EAGER) {
                          whereQuery = " PortfolioLocation14.province = ? ";
                          counter++;
                        } else {
                          whereQuery =
                              " PortfolioLocation14.province = :PortfolioLocation14province"
                                  + j
                                  + " ";
                        }

                        isOpenParentheses = false;
                        whereValue =
                            model
                                .getProductsOfProject()
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getPortfolioLocations()
                                .get(i14)
                                .getProvince();
                        query +=
                            (andCondition
                                + Facade.getStatement(
                                    statement,
                                    "PortfolioLocation14.province",
                                    "PortfolioLocation14province" + j,
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
                          && model
                                  .getProductsOfProject()
                                  .getPortfolioLicense()
                                  .getPortfolio()
                                  .getPortfolioLocations()
                                  .get(i14)
                                  .getCity()
                              != null) {

                        andCondition = " AND ";
                        if (isOpenParentheses) {
                          andCondition = " ";
                        }
                        query += " ";
                        whereValue =
                            model
                                .getProductsOfProject()
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getPortfolioLocations()
                                .get(i14)
                                .getCity();
                        if (isDelete && fetchType == FetchType.EAGER) {
                          whereQuery = " PortfolioLocation14.city = ? ";
                          counter++;
                        } else {
                          whereQuery =
                              " PortfolioLocation14.city = :PortfolioLocation14city" + j + " ";
                        }

                        isOpenParentheses = false;
                        whereValue =
                            model
                                .getProductsOfProject()
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getPortfolioLocations()
                                .get(i14)
                                .getCity();
                        query +=
                            (andCondition
                                + Facade.getStatement(
                                    statement,
                                    "PortfolioLocation14.city",
                                    "PortfolioLocation14city" + j,
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
                          && model
                                  .getProductsOfProject()
                                  .getPortfolioLicense()
                                  .getPortfolio()
                                  .getPortfolioLocations()
                                  .get(i14)
                                  .getIndstrltwn()
                              != null) {

                        andCondition = " AND ";
                        if (isOpenParentheses) {
                          andCondition = " ";
                        }
                        query += " ";
                        whereValue =
                            model
                                .getProductsOfProject()
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getPortfolioLocations()
                                .get(i14)
                                .getIndstrltwn();
                        if (isDelete && fetchType == FetchType.EAGER) {
                          whereQuery = " PortfolioLocation14.indstrltwn = ? ";
                          counter++;
                        } else {
                          whereQuery =
                              " PortfolioLocation14.indstrltwn = :PortfolioLocation14indstrltwn"
                                  + j
                                  + " ";
                        }

                        isOpenParentheses = false;
                        whereValue =
                            model
                                .getProductsOfProject()
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getPortfolioLocations()
                                .get(i14)
                                .getIndstrltwn();
                        query +=
                            (andCondition
                                + Facade.getStatement(
                                    statement,
                                    "PortfolioLocation14.indstrltwn",
                                    "PortfolioLocation14indstrltwn" + j,
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
                          && model
                                  .getProductsOfProject()
                                  .getPortfolioLicense()
                                  .getPortfolio()
                                  .getPortfolioLocations()
                                  .get(i14)
                                  .getAddress()
                              != null) {

                        andCondition = " AND ";
                        if (isOpenParentheses) {
                          andCondition = " ";
                        }
                        query += " ";
                        whereValue =
                            model
                                .getProductsOfProject()
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getPortfolioLocations()
                                .get(i14)
                                .getAddress();
                        if (isDelete && fetchType == FetchType.EAGER) {
                          whereQuery = " PortfolioLocation14.address = ? ";
                          counter++;
                        } else {
                          whereQuery =
                              " PortfolioLocation14.address = :PortfolioLocation14address"
                                  + j
                                  + " ";
                        }

                        isOpenParentheses = false;
                        whereValue =
                            model
                                .getProductsOfProject()
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getPortfolioLocations()
                                .get(i14)
                                .getAddress();
                        query +=
                            (andCondition
                                + Facade.getStatement(
                                    statement,
                                    "PortfolioLocation14.address",
                                    "PortfolioLocation14address" + j,
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
                          && model
                                  .getProductsOfProject()
                                  .getPortfolioLicense()
                                  .getPortfolio()
                                  .getPortfolioLocations()
                                  .get(i14)
                                  .getStableStatusType()
                              != null) {

                        andCondition = " AND ";
                        if (isOpenParentheses) {
                          andCondition = " ";
                        }
                        query += " ";
                        whereValue =
                            model
                                .getProductsOfProject()
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getPortfolioLocations()
                                .get(i14)
                                .getStableStatusType();
                        if (isDelete && fetchType == FetchType.EAGER) {
                          whereQuery = " PortfolioLocation14.stableStatusType = ? ";
                          counter++;
                        } else {
                          whereQuery =
                              " PortfolioLocation14.stableStatusType = :PortfolioLocation14stableStatusType"
                                  + j
                                  + " ";
                        }

                        isOpenParentheses = false;
                        whereValue =
                            model
                                .getProductsOfProject()
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getPortfolioLocations()
                                .get(i14)
                                .getStableStatusType();
                        query +=
                            (andCondition
                                + Facade.getStatement(
                                    statement,
                                    "PortfolioLocation14.stableStatusType",
                                    "PortfolioLocation14stableStatusType" + j,
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
                          && model
                                  .getProductsOfProject()
                                  .getPortfolioLicense()
                                  .getPortfolio()
                                  .getPortfolioLocations()
                                  .get(i14)
                                  .getOwnerStatusType()
                              != null) {

                        andCondition = " AND ";
                        if (isOpenParentheses) {
                          andCondition = " ";
                        }
                        query += " ";
                        whereValue =
                            model
                                .getProductsOfProject()
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getPortfolioLocations()
                                .get(i14)
                                .getOwnerStatusType();
                        if (isDelete && fetchType == FetchType.EAGER) {
                          whereQuery = " PortfolioLocation14.ownerStatusType = ? ";
                          counter++;
                        } else {
                          whereQuery =
                              " PortfolioLocation14.ownerStatusType = :PortfolioLocation14ownerStatusType"
                                  + j
                                  + " ";
                        }

                        isOpenParentheses = false;
                        whereValue =
                            model
                                .getProductsOfProject()
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getPortfolioLocations()
                                .get(i14)
                                .getOwnerStatusType();
                        query +=
                            (andCondition
                                + Facade.getStatement(
                                    statement,
                                    "PortfolioLocation14.ownerStatusType",
                                    "PortfolioLocation14ownerStatusType" + j,
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
                          && model
                                  .getProductsOfProject()
                                  .getPortfolioLicense()
                                  .getPortfolio()
                                  .getPortfolioLocations()
                                  .get(i14)
                                  .getRegionalStatusType()
                              != null) {

                        andCondition = " AND ";
                        if (isOpenParentheses) {
                          andCondition = " ";
                        }
                        query += " ";
                        whereValue =
                            model
                                .getProductsOfProject()
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getPortfolioLocations()
                                .get(i14)
                                .getRegionalStatusType();
                        if (isDelete && fetchType == FetchType.EAGER) {
                          whereQuery = " PortfolioLocation14.regionalStatusType = ? ";
                          counter++;
                        } else {
                          whereQuery =
                              " PortfolioLocation14.regionalStatusType = :PortfolioLocation14regionalStatusType"
                                  + j
                                  + " ";
                        }

                        isOpenParentheses = false;
                        whereValue =
                            model
                                .getProductsOfProject()
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getPortfolioLocations()
                                .get(i14)
                                .getRegionalStatusType();
                        query +=
                            (andCondition
                                + Facade.getStatement(
                                    statement,
                                    "PortfolioLocation14.regionalStatusType",
                                    "PortfolioLocation14regionalStatusType" + j,
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
                          && model
                                  .getProductsOfProject()
                                  .getPortfolioLicense()
                                  .getPortfolio()
                                  .getPortfolioLocations()
                                  .get(i14)
                                  .getVersion()
                              != 0) {

                        andCondition = " AND ";
                        if (isOpenParentheses) {
                          andCondition = " ";
                        }
                        query += " ";
                        whereValue =
                            new java.lang.Integer(
                                ""
                                    + model
                                        .getProductsOfProject()
                                        .getPortfolioLicense()
                                        .getPortfolio()
                                        .getPortfolioLocations()
                                        .get(i14)
                                        .getVersion());
                        if (isDelete && fetchType == FetchType.EAGER) {
                          whereQuery = " PortfolioLocation14.VERSION = ? ";
                          counter++;
                        } else {
                          whereQuery =
                              " PortfolioLocation14.version = :PortfolioLocation14version"
                                  + j
                                  + " ";
                        }

                        isOpenParentheses = false;
                        whereValue =
                            new java.lang.Integer(
                                ""
                                    + model
                                        .getProductsOfProject()
                                        .getPortfolioLicense()
                                        .getPortfolio()
                                        .getPortfolioLocations()
                                        .get(i14)
                                        .getVersion());
                        query +=
                            (andCondition
                                + Facade.getStatement(
                                    statement,
                                    "PortfolioLocation14.version",
                                    "PortfolioLocation14version" + j,
                                    mapVariable,
                                    whereQuery,
                                    whereValue,
                                    isExistRecord,
                                    isDelete,
                                    fetchType,
                                    counter));
                        whereQuery = "";
                      }
                      if (length14 > 1) {
                        query += " ) ";
                      }
                    }

                    query += ")";
                    isOpenParentheses = false;
                  }
                }

                if (!isExistRecord
                    && !isDelete
                    && fetchType == FetchType.EAGER
                    && model
                            .getProductsOfProject()
                            .getPortfolioLicense()
                            .getPortfolio()
                            .getReceptions()
                        != null) {
                  int length23 =
                      model
                          .getProductsOfProject()
                          .getPortfolioLicense()
                          .getPortfolio()
                          .getReceptions()
                          .size();

                  if (length23 > 0) {
                    if (isOpenParentheses) {
                      query += "  ( ";
                    } else {
                      query += " AND ( ";
                    }
                    isOpenParentheses = true;

                    for (int i23 = 0; i23 < length23; i23++) {

                      java.lang.String beforeCondition23 = "";
                      java.lang.String prefix23 = " OR ";
                      if (i23 == 0) {
                        prefix23 = "";
                      }
                      if (length23 > 1) {

                        query += (prefix23 + "(");
                        isOpenParentheses = true;

                      } else {

                        query += (prefix23);
                        if (!prefix23.trim().equals("")) {
                          isOpenParentheses = false;
                        }
                      }

                      if (model
                              .getProductsOfProject()
                              .getPortfolioLicense()
                              .getPortfolio()
                              .getReceptions()
                              .get(i23)
                              .getReceptionNo()
                          != null) {

                        andCondition = " AND ";
                        if (isOpenParentheses) {
                          andCondition = " ";
                        }
                        query += " ";
                        whereValue =
                            model
                                .getProductsOfProject()
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getReceptions()
                                .get(i23)
                                .getReceptionNo();
                        if (isDelete && fetchType == FetchType.EAGER) {
                          whereQuery = " Reception23.receptionNo = ? ";
                          counter++;
                        } else {
                          whereQuery =
                              " Reception23.receptionNo = :Reception23receptionNo" + j + " ";
                        }

                        isOpenParentheses = false;
                        whereValue =
                            model
                                .getProductsOfProject()
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getReceptions()
                                .get(i23)
                                .getReceptionNo();
                        query +=
                            (andCondition
                                + Facade.getStatement(
                                    statement,
                                    "Reception23.receptionNo",
                                    "Reception23receptionNo" + j,
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
                          && model
                                  .getProductsOfProject()
                                  .getPortfolioLicense()
                                  .getPortfolio()
                                  .getReceptions()
                                  .get(i23)
                                  .getChannel()
                              != null) {

                        andCondition = " AND ";
                        if (isOpenParentheses) {
                          andCondition = " ";
                        }
                        query += " ";
                        whereValue =
                            model
                                .getProductsOfProject()
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getReceptions()
                                .get(i23)
                                .getChannel();
                        if (isDelete && fetchType == FetchType.EAGER) {
                          whereQuery = " Reception23.channel = ? ";
                          counter++;
                        } else {
                          whereQuery = " Reception23.channel = :Reception23channel" + j + " ";
                        }

                        isOpenParentheses = false;
                        whereValue =
                            model
                                .getProductsOfProject()
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getReceptions()
                                .get(i23)
                                .getChannel();
                        query +=
                            (andCondition
                                + Facade.getStatement(
                                    statement,
                                    "Reception23.channel",
                                    "Reception23channel" + j,
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
                          && model
                                  .getProductsOfProject()
                                  .getPortfolioLicense()
                                  .getPortfolio()
                                  .getReceptions()
                                  .get(i23)
                                  .getExternalResource()
                              != null) {

                        andCondition = " AND ";
                        if (isOpenParentheses) {
                          andCondition = " ";
                        }
                        query += " ";
                        whereValue =
                            model
                                .getProductsOfProject()
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getReceptions()
                                .get(i23)
                                .getExternalResource();
                        if (isDelete && fetchType == FetchType.EAGER) {
                          whereQuery = " Reception23.externalResource = ? ";
                          counter++;
                        } else {
                          whereQuery =
                              " Reception23.externalResource = :Reception23externalResource"
                                  + j
                                  + " ";
                        }

                        isOpenParentheses = false;
                        whereValue =
                            model
                                .getProductsOfProject()
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getReceptions()
                                .get(i23)
                                .getExternalResource();
                        query +=
                            (andCondition
                                + Facade.getStatement(
                                    statement,
                                    "Reception23.externalResource",
                                    "Reception23externalResource" + j,
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
                          && model
                                  .getProductsOfProject()
                                  .getPortfolioLicense()
                                  .getPortfolio()
                                  .getReceptions()
                                  .get(i23)
                                  .getReceptionTitle()
                              != null) {

                        andCondition = " AND ";
                        if (isOpenParentheses) {
                          andCondition = " ";
                        }
                        query += " ";

                        whereValue =
                            model
                                    .getProductsOfProject()
                                    .getPortfolioLicense()
                                    .getPortfolio()
                                    .getReceptions()
                                    .get(i23)
                                    .getReceptionTitle()
                                + "%";
                        if (isDelete && fetchType == FetchType.EAGER) {
                          whereQuery = " Reception23.receptionTitle = ? ";
                          counter++;
                        } else {
                          whereQuery =
                              " Reception23.receptionTitle LIKE :Reception23receptionTitle"
                                  + j
                                  + " ";
                        }

                        isOpenParentheses = false;
                        if (isExistRecord || isDelete) {
                          if (isDelete && fetchType == FetchType.EAGER) {
                            whereQuery = " Reception23.receptionTitle = ? ";
                            counter++;
                          } else {
                            whereValue =
                                model
                                    .getProductsOfProject()
                                    .getPortfolioLicense()
                                    .getPortfolio()
                                    .getReceptions()
                                    .get(i23)
                                    .getReceptionTitle();
                          }
                        } else {
                          whereValue =
                              model
                                      .getProductsOfProject()
                                      .getPortfolioLicense()
                                      .getPortfolio()
                                      .getReceptions()
                                      .get(i23)
                                      .getReceptionTitle()
                                  + "%";
                        }

                        query +=
                            (andCondition
                                + Facade.getStatement(
                                    statement,
                                    "Reception23.receptionTitle",
                                    "Reception23receptionTitle" + j,
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
                          && model
                                  .getProductsOfProject()
                                  .getPortfolioLicense()
                                  .getPortfolio()
                                  .getReceptions()
                                  .get(i23)
                                  .getProductType()
                              != null) {

                        andCondition = " AND ";
                        if (isOpenParentheses) {
                          andCondition = " ";
                        }
                        query += " ";
                        whereValue =
                            model
                                .getProductsOfProject()
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getReceptions()
                                .get(i23)
                                .getProductType();
                        if (isDelete && fetchType == FetchType.EAGER) {
                          whereQuery = " Reception23.productType = ? ";
                          counter++;
                        } else {
                          whereQuery =
                              " Reception23.productType = :Reception23productType" + j + " ";
                        }

                        isOpenParentheses = false;
                        whereValue =
                            model
                                .getProductsOfProject()
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getReceptions()
                                .get(i23)
                                .getProductType();
                        query +=
                            (andCondition
                                + Facade.getStatement(
                                    statement,
                                    "Reception23.productType",
                                    "Reception23productType" + j,
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
                          && model
                                  .getProductsOfProject()
                                  .getPortfolioLicense()
                                  .getPortfolio()
                                  .getReceptions()
                                  .get(i23)
                                  .getLongLoanType()
                              != null) {

                        andCondition = " AND ";
                        if (isOpenParentheses) {
                          andCondition = " ";
                        }
                        query += " ";
                        whereValue =
                            model
                                .getProductsOfProject()
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getReceptions()
                                .get(i23)
                                .getLongLoanType();
                        if (isDelete && fetchType == FetchType.EAGER) {
                          whereQuery = " Reception23.longLoanType = ? ";
                          counter++;
                        } else {
                          whereQuery =
                              " Reception23.longLoanType = :Reception23longLoanType" + j + " ";
                        }

                        isOpenParentheses = false;
                        whereValue =
                            model
                                .getProductsOfProject()
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getReceptions()
                                .get(i23)
                                .getLongLoanType();
                        query +=
                            (andCondition
                                + Facade.getStatement(
                                    statement,
                                    "Reception23.longLoanType",
                                    "Reception23longLoanType" + j,
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
                          && model
                                  .getProductsOfProject()
                                  .getPortfolioLicense()
                                  .getPortfolio()
                                  .getReceptions()
                                  .get(i23)
                                  .getFinancingPurpose()
                              != null) {

                        andCondition = " AND ";
                        if (isOpenParentheses) {
                          andCondition = " ";
                        }
                        query += " ";
                        whereValue =
                            model
                                .getProductsOfProject()
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getReceptions()
                                .get(i23)
                                .getFinancingPurpose();
                        if (isDelete && fetchType == FetchType.EAGER) {
                          whereQuery = " Reception23.financingPurpose = ? ";
                          counter++;
                        } else {
                          whereQuery =
                              " Reception23.financingPurpose = :Reception23financingPurpose"
                                  + j
                                  + " ";
                        }

                        isOpenParentheses = false;
                        whereValue =
                            model
                                .getProductsOfProject()
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getReceptions()
                                .get(i23)
                                .getFinancingPurpose();
                        query +=
                            (andCondition
                                + Facade.getStatement(
                                    statement,
                                    "Reception23.financingPurpose",
                                    "Reception23financingPurpose" + j,
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
                          && model
                                  .getProductsOfProject()
                                  .getPortfolioLicense()
                                  .getPortfolio()
                                  .getReceptions()
                                  .get(i23)
                                  .getLow141()
                              != null) {

                        andCondition = " AND ";
                        if (isOpenParentheses) {
                          andCondition = " ";
                        }
                        query += " ";
                        whereValue =
                            model
                                .getProductsOfProject()
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getReceptions()
                                .get(i23)
                                .getLow141();
                        if (isDelete && fetchType == FetchType.EAGER) {
                          whereQuery = " Reception23.low141 = ? ";
                          counter++;
                        } else {
                          whereQuery = " Reception23.low141 = :Reception23low141" + j + " ";
                        }

                        isOpenParentheses = false;
                        whereValue =
                            model
                                .getProductsOfProject()
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getReceptions()
                                .get(i23)
                                .getLow141();
                        query +=
                            (andCondition
                                + Facade.getStatement(
                                    statement,
                                    "Reception23.low141",
                                    "Reception23low141" + j,
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
                          && model
                                  .getProductsOfProject()
                                  .getPortfolioLicense()
                                  .getPortfolio()
                                  .getReceptions()
                                  .get(i23)
                                  .getShortTermLoanType()
                              != null) {

                        andCondition = " AND ";
                        if (isOpenParentheses) {
                          andCondition = " ";
                        }
                        query += " ";
                        whereValue =
                            model
                                .getProductsOfProject()
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getReceptions()
                                .get(i23)
                                .getShortTermLoanType();
                        if (isDelete && fetchType == FetchType.EAGER) {
                          whereQuery = " Reception23.shortTermLoanType = ? ";
                          counter++;
                        } else {
                          whereQuery =
                              " Reception23.shortTermLoanType = :Reception23shortTermLoanType"
                                  + j
                                  + " ";
                        }

                        isOpenParentheses = false;
                        whereValue =
                            model
                                .getProductsOfProject()
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getReceptions()
                                .get(i23)
                                .getShortTermLoanType();
                        query +=
                            (andCondition
                                + Facade.getStatement(
                                    statement,
                                    "Reception23.shortTermLoanType",
                                    "Reception23shortTermLoanType" + j,
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
                          && model
                                  .getProductsOfProject()
                                  .getPortfolioLicense()
                                  .getPortfolio()
                                  .getReceptions()
                                  .get(i23)
                                  .getDate()
                              != null) {

                        andCondition = " AND ";
                        if (isOpenParentheses) {
                          andCondition = " ";
                        }
                        query += " ";
                        whereValue =
                            model
                                .getProductsOfProject()
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getReceptions()
                                .get(i23)
                                .getDate();
                        if (isDelete && fetchType == FetchType.EAGER) {
                          whereQuery = " Reception23.date1 = ? ";
                          counter++;
                        } else {
                          whereQuery = " Reception23.date = :Reception23date" + j + " ";
                        }

                        isOpenParentheses = false;
                        whereValue =
                            model
                                .getProductsOfProject()
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getReceptions()
                                .get(i23)
                                .getDate();
                        query +=
                            (andCondition
                                + Facade.getStatement(
                                    statement,
                                    "Reception23.date",
                                    "Reception23date" + j,
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
                          && model
                                  .getProductsOfProject()
                                  .getPortfolioLicense()
                                  .getPortfolio()
                                  .getReceptions()
                                  .get(i23)
                                  .getRejLetterDate()
                              != null) {

                        andCondition = " AND ";
                        if (isOpenParentheses) {
                          andCondition = " ";
                        }
                        query += " ";
                        whereValue =
                            model
                                .getProductsOfProject()
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getReceptions()
                                .get(i23)
                                .getRejLetterDate();
                        if (isDelete && fetchType == FetchType.EAGER) {
                          whereQuery = " Reception23.rejLetterDate = ? ";
                          counter++;
                        } else {
                          whereQuery =
                              " Reception23.rejLetterDate = :Reception23rejLetterDate" + j + " ";
                        }

                        isOpenParentheses = false;
                        whereValue =
                            model
                                .getProductsOfProject()
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getReceptions()
                                .get(i23)
                                .getRejLetterDate();
                        query +=
                            (andCondition
                                + Facade.getStatement(
                                    statement,
                                    "Reception23.rejLetterDate",
                                    "Reception23rejLetterDate" + j,
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
                          && model
                                  .getProductsOfProject()
                                  .getPortfolioLicense()
                                  .getPortfolio()
                                  .getReceptions()
                                  .get(i23)
                                  .getCustomerCode()
                              != null) {

                        andCondition = " AND ";
                        if (isOpenParentheses) {
                          andCondition = " ";
                        }
                        query += " ";

                        whereValue =
                            "%"
                                + model
                                    .getProductsOfProject()
                                    .getPortfolioLicense()
                                    .getPortfolio()
                                    .getReceptions()
                                    .get(i23)
                                    .getCustomerCode();
                        if (isDelete && fetchType == FetchType.EAGER) {
                          whereQuery = " Reception23.customerCode = ? ";
                          counter++;
                        } else {
                          whereQuery =
                              " Reception23.customerCode LIKE :Reception23customerCode" + j + " ";
                        }

                        isOpenParentheses = false;
                        if (isExistRecord || isDelete) {
                          if (isDelete && fetchType == FetchType.EAGER) {
                            whereQuery = " Reception23.customerCode = ? ";
                            counter++;
                          } else {
                            whereValue =
                                model
                                    .getProductsOfProject()
                                    .getPortfolioLicense()
                                    .getPortfolio()
                                    .getReceptions()
                                    .get(i23)
                                    .getCustomerCode();
                          }
                        } else {
                          whereValue =
                              "%"
                                  + model
                                      .getProductsOfProject()
                                      .getPortfolioLicense()
                                      .getPortfolio()
                                      .getReceptions()
                                      .get(i23)
                                      .getCustomerCode();
                        }

                        query +=
                            (andCondition
                                + Facade.getStatement(
                                    statement,
                                    "Reception23.customerCode",
                                    "Reception23customerCode" + j,
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
                          && model
                                  .getProductsOfProject()
                                  .getPortfolioLicense()
                                  .getPortfolio()
                                  .getReceptions()
                                  .get(i23)
                                  .getBranch()
                              != null) {

                        andCondition = " AND ";
                        if (isOpenParentheses) {
                          andCondition = " ";
                        }
                        query += " ";

                        whereValue =
                            "%"
                                + model
                                    .getProductsOfProject()
                                    .getPortfolioLicense()
                                    .getPortfolio()
                                    .getReceptions()
                                    .get(i23)
                                    .getBranch()
                                + "%";
                        if (isDelete && fetchType == FetchType.EAGER) {
                          whereQuery = " Reception23.branch = ? ";
                          counter++;
                        } else {
                          whereQuery = " Reception23.branch LIKE :Reception23branch" + j + " ";
                        }

                        isOpenParentheses = false;
                        if (isExistRecord || isDelete) {
                          if (isDelete && fetchType == FetchType.EAGER) {
                            whereQuery = " Reception23.branch = ? ";
                            counter++;
                          } else {
                            whereValue =
                                model
                                    .getProductsOfProject()
                                    .getPortfolioLicense()
                                    .getPortfolio()
                                    .getReceptions()
                                    .get(i23)
                                    .getBranch();
                          }
                        } else {
                          whereValue =
                              "%"
                                  + model
                                      .getProductsOfProject()
                                      .getPortfolioLicense()
                                      .getPortfolio()
                                      .getReceptions()
                                      .get(i23)
                                      .getBranch()
                                  + "%";
                        }

                        query +=
                            (andCondition
                                + Facade.getStatement(
                                    statement,
                                    "Reception23.branch",
                                    "Reception23branch" + j,
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
                          && model
                                  .getProductsOfProject()
                                  .getPortfolioLicense()
                                  .getPortfolio()
                                  .getReceptions()
                                  .get(i23)
                                  .getReceptionState()
                              != null) {

                        andCondition = " AND ";
                        if (isOpenParentheses) {
                          andCondition = " ";
                        }
                        query += " ";
                        whereValue =
                            model
                                .getProductsOfProject()
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getReceptions()
                                .get(i23)
                                .getReceptionState();
                        if (isDelete && fetchType == FetchType.EAGER) {
                          whereQuery = " Reception23.receptionState = ? ";
                          counter++;
                        } else {
                          whereQuery =
                              " Reception23.receptionState = :Reception23receptionState" + j + " ";
                        }

                        isOpenParentheses = false;
                        whereValue =
                            model
                                .getProductsOfProject()
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getReceptions()
                                .get(i23)
                                .getReceptionState();
                        query +=
                            (andCondition
                                + Facade.getStatement(
                                    statement,
                                    "Reception23.receptionState",
                                    "Reception23receptionState" + j,
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
                          && model
                                  .getProductsOfProject()
                                  .getPortfolioLicense()
                                  .getPortfolio()
                                  .getReceptions()
                                  .get(i23)
                                  .getRepresentativeRefType()
                              != null) {

                        andCondition = " AND ";
                        if (isOpenParentheses) {
                          andCondition = " ";
                        }
                        query += " ";
                        whereValue =
                            model
                                .getProductsOfProject()
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getReceptions()
                                .get(i23)
                                .getRepresentativeRefType();
                        if (isDelete && fetchType == FetchType.EAGER) {
                          whereQuery = " Reception23.representativeRefType = ? ";
                          counter++;
                        } else {
                          whereQuery =
                              " Reception23.representativeRefType = :Reception23representativeRefType"
                                  + j
                                  + " ";
                        }

                        isOpenParentheses = false;
                        whereValue =
                            model
                                .getProductsOfProject()
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getReceptions()
                                .get(i23)
                                .getRepresentativeRefType();
                        query +=
                            (andCondition
                                + Facade.getStatement(
                                    statement,
                                    "Reception23.representativeRefType",
                                    "Reception23representativeRefType" + j,
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
                          && model
                                  .getProductsOfProject()
                                  .getPortfolioLicense()
                                  .getPortfolio()
                                  .getReceptions()
                                  .get(i23)
                                  .getApplicantCreditType()
                              != null) {

                        andCondition = " AND ";
                        if (isOpenParentheses) {
                          andCondition = " ";
                        }
                        query += " ";
                        whereValue =
                            model
                                .getProductsOfProject()
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getReceptions()
                                .get(i23)
                                .getApplicantCreditType();
                        if (isDelete && fetchType == FetchType.EAGER) {
                          whereQuery = " Reception23.applicantCreditType = ? ";
                          counter++;
                        } else {
                          whereQuery =
                              " Reception23.applicantCreditType = :Reception23applicantCreditType"
                                  + j
                                  + " ";
                        }

                        isOpenParentheses = false;
                        whereValue =
                            model
                                .getProductsOfProject()
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getReceptions()
                                .get(i23)
                                .getApplicantCreditType();
                        query +=
                            (andCondition
                                + Facade.getStatement(
                                    statement,
                                    "Reception23.applicantCreditType",
                                    "Reception23applicantCreditType" + j,
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
                          && model
                                  .getProductsOfProject()
                                  .getPortfolioLicense()
                                  .getPortfolio()
                                  .getReceptions()
                                  .get(i23)
                                  .getUnderTakeHisType()
                              != null) {

                        andCondition = " AND ";
                        if (isOpenParentheses) {
                          andCondition = " ";
                        }
                        query += " ";
                        whereValue =
                            model
                                .getProductsOfProject()
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getReceptions()
                                .get(i23)
                                .getUnderTakeHisType();
                        if (isDelete && fetchType == FetchType.EAGER) {
                          whereQuery = " Reception23.underTakeHisType = ? ";
                          counter++;
                        } else {
                          whereQuery =
                              " Reception23.underTakeHisType = :Reception23underTakeHisType"
                                  + j
                                  + " ";
                        }

                        isOpenParentheses = false;
                        whereValue =
                            model
                                .getProductsOfProject()
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getReceptions()
                                .get(i23)
                                .getUnderTakeHisType();
                        query +=
                            (andCondition
                                + Facade.getStatement(
                                    statement,
                                    "Reception23.underTakeHisType",
                                    "Reception23underTakeHisType" + j,
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
                          && model
                                  .getProductsOfProject()
                                  .getPortfolioLicense()
                                  .getPortfolio()
                                  .getReceptions()
                                  .get(i23)
                                  .getCheckRejectType()
                              != null) {

                        andCondition = " AND ";
                        if (isOpenParentheses) {
                          andCondition = " ";
                        }
                        query += " ";
                        whereValue =
                            model
                                .getProductsOfProject()
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getReceptions()
                                .get(i23)
                                .getCheckRejectType();
                        if (isDelete && fetchType == FetchType.EAGER) {
                          whereQuery = " Reception23.checkRejectType = ? ";
                          counter++;
                        } else {
                          whereQuery =
                              " Reception23.checkRejectType = :Reception23checkRejectType"
                                  + j
                                  + " ";
                        }

                        isOpenParentheses = false;
                        whereValue =
                            model
                                .getProductsOfProject()
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getReceptions()
                                .get(i23)
                                .getCheckRejectType();
                        query +=
                            (andCondition
                                + Facade.getStatement(
                                    statement,
                                    "Reception23.checkRejectType",
                                    "Reception23checkRejectType" + j,
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
                          && model
                                  .getProductsOfProject()
                                  .getPortfolioLicense()
                                  .getPortfolio()
                                  .getReceptions()
                                  .get(i23)
                                  .getCashFocusStatusType()
                              != null) {

                        andCondition = " AND ";
                        if (isOpenParentheses) {
                          andCondition = " ";
                        }
                        query += " ";
                        whereValue =
                            model
                                .getProductsOfProject()
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getReceptions()
                                .get(i23)
                                .getCashFocusStatusType();
                        if (isDelete && fetchType == FetchType.EAGER) {
                          whereQuery = " Reception23.cashFocusStatusType = ? ";
                          counter++;
                        } else {
                          whereQuery =
                              " Reception23.cashFocusStatusType = :Reception23cashFocusStatusType"
                                  + j
                                  + " ";
                        }

                        isOpenParentheses = false;
                        whereValue =
                            model
                                .getProductsOfProject()
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getReceptions()
                                .get(i23)
                                .getCashFocusStatusType();
                        query +=
                            (andCondition
                                + Facade.getStatement(
                                    statement,
                                    "Reception23.cashFocusStatusType",
                                    "Reception23cashFocusStatusType" + j,
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
                          && model
                                  .getProductsOfProject()
                                  .getPortfolioLicense()
                                  .getPortfolio()
                                  .getReceptions()
                                  .get(i23)
                                  .getEquation()
                              != null) {

                        andCondition = " AND ";
                        if (isOpenParentheses) {
                          andCondition = " ";
                        }
                        query += " ";
                        whereValue =
                            model
                                .getProductsOfProject()
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getReceptions()
                                .get(i23)
                                .getEquation();
                        if (isDelete && fetchType == FetchType.EAGER) {
                          whereQuery = " Reception23.equation = ? ";
                          counter++;
                        } else {
                          whereQuery = " Reception23.equation = :Reception23equation" + j + " ";
                        }

                        isOpenParentheses = false;
                        whereValue =
                            model
                                .getProductsOfProject()
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getReceptions()
                                .get(i23)
                                .getEquation();
                        query +=
                            (andCondition
                                + Facade.getStatement(
                                    statement,
                                    "Reception23.equation",
                                    "Reception23equation" + j,
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
                          && model
                                  .getProductsOfProject()
                                  .getPortfolioLicense()
                                  .getPortfolio()
                                  .getReceptions()
                                  .get(i23)
                                  .getIrrEstimate()
                              != null) {

                        andCondition = " AND ";
                        if (isOpenParentheses) {
                          andCondition = " ";
                        }
                        query += " ";
                        whereValue =
                            model
                                .getProductsOfProject()
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getReceptions()
                                .get(i23)
                                .getIrrEstimate();
                        if (isDelete && fetchType == FetchType.EAGER) {
                          whereQuery = " Reception23.irrEstimate = ? ";
                          counter++;
                        } else {
                          whereQuery =
                              " Reception23.irrEstimate = :Reception23irrEstimate" + j + " ";
                        }

                        isOpenParentheses = false;
                        whereValue =
                            model
                                .getProductsOfProject()
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getReceptions()
                                .get(i23)
                                .getIrrEstimate();
                        query +=
                            (andCondition
                                + Facade.getStatement(
                                    statement,
                                    "Reception23.irrEstimate",
                                    "Reception23irrEstimate" + j,
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
                          && model
                                  .getProductsOfProject()
                                  .getPortfolioLicense()
                                  .getPortfolio()
                                  .getReceptions()
                                  .get(i23)
                                  .getHeadPoint()
                              != null) {

                        andCondition = " AND ";
                        if (isOpenParentheses) {
                          andCondition = " ";
                        }
                        query += " ";
                        whereValue =
                            model
                                .getProductsOfProject()
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getReceptions()
                                .get(i23)
                                .getHeadPoint();
                        if (isDelete && fetchType == FetchType.EAGER) {
                          whereQuery = " Reception23.headPoint = ? ";
                          counter++;
                        } else {
                          whereQuery = " Reception23.headPoint = :Reception23headPoint" + j + " ";
                        }

                        isOpenParentheses = false;
                        whereValue =
                            model
                                .getProductsOfProject()
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getReceptions()
                                .get(i23)
                                .getHeadPoint();
                        query +=
                            (andCondition
                                + Facade.getStatement(
                                    statement,
                                    "Reception23.headPoint",
                                    "Reception23headPoint" + j,
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
                          && model
                                  .getProductsOfProject()
                                  .getPortfolioLicense()
                                  .getPortfolio()
                                  .getReceptions()
                                  .get(i23)
                                  .getCashControl()
                              != null) {

                        andCondition = " AND ";
                        if (isOpenParentheses) {
                          andCondition = " ";
                        }
                        query += " ";
                        whereValue =
                            model
                                .getProductsOfProject()
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getReceptions()
                                .get(i23)
                                .getCashControl();
                        if (isDelete && fetchType == FetchType.EAGER) {
                          whereQuery = " Reception23.cashControl = ? ";
                          counter++;
                        } else {
                          whereQuery =
                              " Reception23.cashControl = :Reception23cashControl" + j + " ";
                        }

                        isOpenParentheses = false;
                        whereValue =
                            model
                                .getProductsOfProject()
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getReceptions()
                                .get(i23)
                                .getCashControl();
                        query +=
                            (andCondition
                                + Facade.getStatement(
                                    statement,
                                    "Reception23.cashControl",
                                    "Reception23cashControl" + j,
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
                          && model
                                  .getProductsOfProject()
                                  .getPortfolioLicense()
                                  .getPortfolio()
                                  .getReceptions()
                                  .get(i23)
                                  .getExportMarketDsc()
                              != null) {

                        andCondition = " AND ";
                        if (isOpenParentheses) {
                          andCondition = " ";
                        }
                        query += " ";
                        whereValue =
                            model
                                .getProductsOfProject()
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getReceptions()
                                .get(i23)
                                .getExportMarketDsc();
                        if (isDelete && fetchType == FetchType.EAGER) {
                          whereQuery = " Reception23.exportMarketDsc = ? ";
                          counter++;
                        } else {
                          whereQuery =
                              " Reception23.exportMarketDsc = :Reception23exportMarketDsc"
                                  + j
                                  + " ";
                        }

                        isOpenParentheses = false;
                        whereValue =
                            model
                                .getProductsOfProject()
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getReceptions()
                                .get(i23)
                                .getExportMarketDsc();
                        query +=
                            (andCondition
                                + Facade.getStatement(
                                    statement,
                                    "Reception23.exportMarketDsc",
                                    "Reception23exportMarketDsc" + j,
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
                          && model
                                  .getProductsOfProject()
                                  .getPortfolioLicense()
                                  .getPortfolio()
                                  .getReceptions()
                                  .get(i23)
                                  .getInternalMarketDsc()
                              != null) {

                        andCondition = " AND ";
                        if (isOpenParentheses) {
                          andCondition = " ";
                        }
                        query += " ";
                        whereValue =
                            model
                                .getProductsOfProject()
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getReceptions()
                                .get(i23)
                                .getInternalMarketDsc();
                        if (isDelete && fetchType == FetchType.EAGER) {
                          whereQuery = " Reception23.internalMarketDsc = ? ";
                          counter++;
                        } else {
                          whereQuery =
                              " Reception23.internalMarketDsc = :Reception23internalMarketDsc"
                                  + j
                                  + " ";
                        }

                        isOpenParentheses = false;
                        whereValue =
                            model
                                .getProductsOfProject()
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getReceptions()
                                .get(i23)
                                .getInternalMarketDsc();
                        query +=
                            (andCondition
                                + Facade.getStatement(
                                    statement,
                                    "Reception23.internalMarketDsc",
                                    "Reception23internalMarketDsc" + j,
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
                          && model
                                  .getProductsOfProject()
                                  .getPortfolioLicense()
                                  .getPortfolio()
                                  .getReceptions()
                                  .get(i23)
                                  .getRowMaterialDsc()
                              != null) {

                        andCondition = " AND ";
                        if (isOpenParentheses) {
                          andCondition = " ";
                        }
                        query += " ";
                        whereValue =
                            model
                                .getProductsOfProject()
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getReceptions()
                                .get(i23)
                                .getRowMaterialDsc();
                        if (isDelete && fetchType == FetchType.EAGER) {
                          whereQuery = " Reception23.rowMaterialDsc = ? ";
                          counter++;
                        } else {
                          whereQuery =
                              " Reception23.rowMaterialDsc = :Reception23rowMaterialDsc" + j + " ";
                        }

                        isOpenParentheses = false;
                        whereValue =
                            model
                                .getProductsOfProject()
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getReceptions()
                                .get(i23)
                                .getRowMaterialDsc();
                        query +=
                            (andCondition
                                + Facade.getStatement(
                                    statement,
                                    "Reception23.rowMaterialDsc",
                                    "Reception23rowMaterialDsc" + j,
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
                          && model
                                  .getProductsOfProject()
                                  .getPortfolioLicense()
                                  .getPortfolio()
                                  .getReceptions()
                                  .get(i23)
                                  .getMarketProductDesc()
                              != null) {

                        andCondition = " AND ";
                        if (isOpenParentheses) {
                          andCondition = " ";
                        }
                        query += " ";
                        whereValue =
                            model
                                .getProductsOfProject()
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getReceptions()
                                .get(i23)
                                .getMarketProductDesc();
                        if (isDelete && fetchType == FetchType.EAGER) {
                          whereQuery = " Reception23.marketProductDesc = ? ";
                          counter++;
                        } else {
                          whereQuery =
                              " Reception23.marketProductDesc = :Reception23marketProductDesc"
                                  + j
                                  + " ";
                        }

                        isOpenParentheses = false;
                        whereValue =
                            model
                                .getProductsOfProject()
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getReceptions()
                                .get(i23)
                                .getMarketProductDesc();
                        query +=
                            (andCondition
                                + Facade.getStatement(
                                    statement,
                                    "Reception23.marketProductDesc",
                                    "Reception23marketProductDesc" + j,
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
                          && model
                                  .getProductsOfProject()
                                  .getPortfolioLicense()
                                  .getPortfolio()
                                  .getReceptions()
                                  .get(i23)
                                  .getProductiveEmployee()
                              != null) {

                        andCondition = " AND ";
                        if (isOpenParentheses) {
                          andCondition = " ";
                        }
                        query += " ";
                        whereValue =
                            model
                                .getProductsOfProject()
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getReceptions()
                                .get(i23)
                                .getProductiveEmployee();
                        if (isDelete && fetchType == FetchType.EAGER) {
                          whereQuery = " Reception23.productiveEmployee = ? ";
                          counter++;
                        } else {
                          whereQuery =
                              " Reception23.productiveEmployee = :Reception23productiveEmployee"
                                  + j
                                  + " ";
                        }

                        isOpenParentheses = false;
                        whereValue =
                            model
                                .getProductsOfProject()
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getReceptions()
                                .get(i23)
                                .getProductiveEmployee();
                        query +=
                            (andCondition
                                + Facade.getStatement(
                                    statement,
                                    "Reception23.productiveEmployee",
                                    "Reception23productiveEmployee" + j,
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
                          && model
                                  .getProductsOfProject()
                                  .getPortfolioLicense()
                                  .getPortfolio()
                                  .getReceptions()
                                  .get(i23)
                                  .getNonProductiveEmployee()
                              != null) {

                        andCondition = " AND ";
                        if (isOpenParentheses) {
                          andCondition = " ";
                        }
                        query += " ";
                        whereValue =
                            model
                                .getProductsOfProject()
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getReceptions()
                                .get(i23)
                                .getNonProductiveEmployee();
                        if (isDelete && fetchType == FetchType.EAGER) {
                          whereQuery = " Reception23.nonProductiveEmployee = ? ";
                          counter++;
                        } else {
                          whereQuery =
                              " Reception23.nonProductiveEmployee = :Reception23nonProductiveEmployee"
                                  + j
                                  + " ";
                        }

                        isOpenParentheses = false;
                        whereValue =
                            model
                                .getProductsOfProject()
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getReceptions()
                                .get(i23)
                                .getNonProductiveEmployee();
                        query +=
                            (andCondition
                                + Facade.getStatement(
                                    statement,
                                    "Reception23.nonProductiveEmployee",
                                    "Reception23nonProductiveEmployee" + j,
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
                          && model
                                  .getProductsOfProject()
                                  .getPortfolioLicense()
                                  .getPortfolio()
                                  .getReceptions()
                                  .get(i23)
                                  .getForecastProductiveEmployee()
                              != null) {

                        andCondition = " AND ";
                        if (isOpenParentheses) {
                          andCondition = " ";
                        }
                        query += " ";
                        whereValue =
                            model
                                .getProductsOfProject()
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getReceptions()
                                .get(i23)
                                .getForecastProductiveEmployee();
                        if (isDelete && fetchType == FetchType.EAGER) {
                          whereQuery = " Reception23.forecastProductiveEmployee = ? ";
                          counter++;
                        } else {
                          whereQuery =
                              " Reception23.forecastProductiveEmployee = :Reception23forecastProductiveEmployee"
                                  + j
                                  + " ";
                        }

                        isOpenParentheses = false;
                        whereValue =
                            model
                                .getProductsOfProject()
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getReceptions()
                                .get(i23)
                                .getForecastProductiveEmployee();
                        query +=
                            (andCondition
                                + Facade.getStatement(
                                    statement,
                                    "Reception23.forecastProductiveEmployee",
                                    "Reception23forecastProductiveEmployee" + j,
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
                          && model
                                  .getProductsOfProject()
                                  .getPortfolioLicense()
                                  .getPortfolio()
                                  .getReceptions()
                                  .get(i23)
                                  .getForecastNonProductiveEmployee()
                              != null) {

                        andCondition = " AND ";
                        if (isOpenParentheses) {
                          andCondition = " ";
                        }
                        query += " ";
                        whereValue =
                            model
                                .getProductsOfProject()
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getReceptions()
                                .get(i23)
                                .getForecastNonProductiveEmployee();
                        if (isDelete && fetchType == FetchType.EAGER) {
                          whereQuery = " Reception23.forecastNonProductiveEmployee = ? ";
                          counter++;
                        } else {
                          whereQuery =
                              " Reception23.forecastNonProductiveEmployee = :Reception23forecastNonProductiveEmployee"
                                  + j
                                  + " ";
                        }

                        isOpenParentheses = false;
                        whereValue =
                            model
                                .getProductsOfProject()
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getReceptions()
                                .get(i23)
                                .getForecastNonProductiveEmployee();
                        query +=
                            (andCondition
                                + Facade.getStatement(
                                    statement,
                                    "Reception23.forecastNonProductiveEmployee",
                                    "Reception23forecastNonProductiveEmployee" + j,
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
                          && model
                                  .getProductsOfProject()
                                  .getPortfolioLicense()
                                  .getPortfolio()
                                  .getReceptions()
                                  .get(i23)
                                  .getCusPrePayAmnt()
                              != null) {

                        andCondition = " AND ";
                        if (isOpenParentheses) {
                          andCondition = " ";
                        }
                        query += " ";
                        whereValue =
                            model
                                .getProductsOfProject()
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getReceptions()
                                .get(i23)
                                .getCusPrePayAmnt();
                        if (isDelete && fetchType == FetchType.EAGER) {
                          whereQuery = " Reception23.cusPrePayAmnt = ? ";
                          counter++;
                        } else {
                          whereQuery =
                              " Reception23.cusPrePayAmnt = :Reception23cusPrePayAmnt" + j + " ";
                        }

                        isOpenParentheses = false;
                        whereValue =
                            model
                                .getProductsOfProject()
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getReceptions()
                                .get(i23)
                                .getCusPrePayAmnt();
                        query +=
                            (andCondition
                                + Facade.getStatement(
                                    statement,
                                    "Reception23.cusPrePayAmnt",
                                    "Reception23cusPrePayAmnt" + j,
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
                          && model
                                  .getProductsOfProject()
                                  .getPortfolioLicense()
                                  .getPortfolio()
                                  .getReceptions()
                                  .get(i23)
                                  .getFinancialUser()
                              != null) {

                        andCondition = " AND ";
                        if (isOpenParentheses) {
                          andCondition = " ";
                        }
                        query += " ";
                        whereValue =
                            model
                                .getProductsOfProject()
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getReceptions()
                                .get(i23)
                                .getFinancialUser();
                        if (isDelete && fetchType == FetchType.EAGER) {
                          whereQuery = " Reception23.financialUser = ? ";
                          counter++;
                        } else {
                          whereQuery =
                              " Reception23.financialUser = :Reception23financialUser" + j + " ";
                        }

                        isOpenParentheses = false;
                        whereValue =
                            model
                                .getProductsOfProject()
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getReceptions()
                                .get(i23)
                                .getFinancialUser();
                        query +=
                            (andCondition
                                + Facade.getStatement(
                                    statement,
                                    "Reception23.financialUser",
                                    "Reception23financialUser" + j,
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
                          && model
                                  .getProductsOfProject()
                                  .getPortfolioLicense()
                                  .getPortfolio()
                                  .getReceptions()
                                  .get(i23)
                                  .getMarketUser()
                              != null) {

                        andCondition = " AND ";
                        if (isOpenParentheses) {
                          andCondition = " ";
                        }
                        query += " ";
                        whereValue =
                            model
                                .getProductsOfProject()
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getReceptions()
                                .get(i23)
                                .getMarketUser();
                        if (isDelete && fetchType == FetchType.EAGER) {
                          whereQuery = " Reception23.marketUser = ? ";
                          counter++;
                        } else {
                          whereQuery = " Reception23.marketUser = :Reception23marketUser" + j + " ";
                        }

                        isOpenParentheses = false;
                        whereValue =
                            model
                                .getProductsOfProject()
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getReceptions()
                                .get(i23)
                                .getMarketUser();
                        query +=
                            (andCondition
                                + Facade.getStatement(
                                    statement,
                                    "Reception23.marketUser",
                                    "Reception23marketUser" + j,
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
                          && model
                                  .getProductsOfProject()
                                  .getPortfolioLicense()
                                  .getPortfolio()
                                  .getReceptions()
                                  .get(i23)
                                  .getTechnicalUser()
                              != null) {

                        andCondition = " AND ";
                        if (isOpenParentheses) {
                          andCondition = " ";
                        }
                        query += " ";
                        whereValue =
                            model
                                .getProductsOfProject()
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getReceptions()
                                .get(i23)
                                .getTechnicalUser();
                        if (isDelete && fetchType == FetchType.EAGER) {
                          whereQuery = " Reception23.technicalUser = ? ";
                          counter++;
                        } else {
                          whereQuery =
                              " Reception23.technicalUser = :Reception23technicalUser" + j + " ";
                        }

                        isOpenParentheses = false;
                        whereValue =
                            model
                                .getProductsOfProject()
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getReceptions()
                                .get(i23)
                                .getTechnicalUser();
                        query +=
                            (andCondition
                                + Facade.getStatement(
                                    statement,
                                    "Reception23.technicalUser",
                                    "Reception23technicalUser" + j,
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
                          && model
                                  .getProductsOfProject()
                                  .getPortfolioLicense()
                                  .getPortfolio()
                                  .getReceptions()
                                  .get(i23)
                                  .getCurrentAssetReceptionNo()
                              != null) {

                        andCondition = " AND ";
                        if (isOpenParentheses) {
                          andCondition = " ";
                        }
                        query += " ";
                        whereValue =
                            model
                                .getProductsOfProject()
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getReceptions()
                                .get(i23)
                                .getCurrentAssetReceptionNo();
                        if (isDelete && fetchType == FetchType.EAGER) {
                          whereQuery = " Reception23.currentAssetReceptionNo = ? ";
                          counter++;
                        } else {
                          whereQuery =
                              " Reception23.currentAssetReceptionNo = :Reception23currentAssetReceptionNo"
                                  + j
                                  + " ";
                        }

                        isOpenParentheses = false;
                        whereValue =
                            model
                                .getProductsOfProject()
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getReceptions()
                                .get(i23)
                                .getCurrentAssetReceptionNo();
                        query +=
                            (andCondition
                                + Facade.getStatement(
                                    statement,
                                    "Reception23.currentAssetReceptionNo",
                                    "Reception23currentAssetReceptionNo" + j,
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
                          && model
                                  .getProductsOfProject()
                                  .getPortfolioLicense()
                                  .getPortfolio()
                                  .getReceptions()
                                  .get(i23)
                                  .getVersion()
                              != 0) {

                        andCondition = " AND ";
                        if (isOpenParentheses) {
                          andCondition = " ";
                        }
                        query += " ";
                        whereValue =
                            new java.lang.Integer(
                                ""
                                    + model
                                        .getProductsOfProject()
                                        .getPortfolioLicense()
                                        .getPortfolio()
                                        .getReceptions()
                                        .get(i23)
                                        .getVersion());
                        if (isDelete && fetchType == FetchType.EAGER) {
                          whereQuery = " Reception23.VERSION = ? ";
                          counter++;
                        } else {
                          whereQuery = " Reception23.version = :Reception23version" + j + " ";
                        }

                        isOpenParentheses = false;
                        whereValue =
                            new java.lang.Integer(
                                ""
                                    + model
                                        .getProductsOfProject()
                                        .getPortfolioLicense()
                                        .getPortfolio()
                                        .getReceptions()
                                        .get(i23)
                                        .getVersion());
                        query +=
                            (andCondition
                                + Facade.getStatement(
                                    statement,
                                    "Reception23.version",
                                    "Reception23version" + j,
                                    mapVariable,
                                    whereQuery,
                                    whereValue,
                                    isExistRecord,
                                    isDelete,
                                    fetchType,
                                    counter));
                        whereQuery = "";
                      }
                      if (length23 > 1) {
                        query += " ) ";
                      }
                    }

                    query += ")";
                    isOpenParentheses = false;
                  }
                }
                if (!isDelete
                    && model
                            .getProductsOfProject()
                            .getPortfolioLicense()
                            .getPortfolio()
                            .getAddress()
                        != null) {
                  if (!isDelete
                      && true
                      && model
                              .getProductsOfProject()
                              .getPortfolioLicense()
                              .getPortfolio()
                              .getAddress()
                              .getAddressLine1()
                          != null) {

                    andCondition = " AND ";
                    if (isOpenParentheses) {
                      andCondition = " ";
                    }
                    query += " ";
                    if (isDelete && fetchType == FetchType.EAGER) {
                      whereQuery = " Portfolio7.addressLine1 = ? ";
                      counter++;
                    } else {
                      whereQuery =
                          " Portfolio7.address.addressLine1 = :Portfolio7addressaddressLine1"
                              + j
                              + " ";
                    }

                    isOpenParentheses = false;
                    whereValue =
                        model
                            .getProductsOfProject()
                            .getPortfolioLicense()
                            .getPortfolio()
                            .getAddress()
                            .getAddressLine1();
                    query +=
                        (andCondition
                            + Facade.getStatement(
                                statement,
                                "Portfolio7.address.addressLine1",
                                "Portfolio7addressaddressLine1" + j,
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
                      && model
                              .getProductsOfProject()
                              .getPortfolioLicense()
                              .getPortfolio()
                              .getAddress()
                              .getAddressLine2()
                          != null) {

                    andCondition = " AND ";
                    if (isOpenParentheses) {
                      andCondition = " ";
                    }
                    query += " ";
                    if (isDelete && fetchType == FetchType.EAGER) {
                      whereQuery = " Portfolio7.addressLine2 = ? ";
                      counter++;
                    } else {
                      whereQuery =
                          " Portfolio7.address.addressLine2 = :Portfolio7addressaddressLine2"
                              + j
                              + " ";
                    }

                    isOpenParentheses = false;
                    whereValue =
                        model
                            .getProductsOfProject()
                            .getPortfolioLicense()
                            .getPortfolio()
                            .getAddress()
                            .getAddressLine2();
                    query +=
                        (andCondition
                            + Facade.getStatement(
                                statement,
                                "Portfolio7.address.addressLine2",
                                "Portfolio7addressaddressLine2" + j,
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
                      && model
                              .getProductsOfProject()
                              .getPortfolioLicense()
                              .getPortfolio()
                              .getAddress()
                              .getCity()
                          != null) {

                    andCondition = " AND ";
                    if (isOpenParentheses) {
                      andCondition = " ";
                    }
                    query += " ";
                    if (isDelete && fetchType == FetchType.EAGER) {
                      whereQuery = " Portfolio7.city = ? ";
                      counter++;
                    } else {
                      whereQuery = " Portfolio7.address.city = :Portfolio7addresscity" + j + " ";
                    }

                    isOpenParentheses = false;
                    whereValue =
                        model
                            .getProductsOfProject()
                            .getPortfolioLicense()
                            .getPortfolio()
                            .getAddress()
                            .getCity();
                    query +=
                        (andCondition
                            + Facade.getStatement(
                                statement,
                                "Portfolio7.address.city",
                                "Portfolio7addresscity" + j,
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
                      && model
                              .getProductsOfProject()
                              .getPortfolioLicense()
                              .getPortfolio()
                              .getAddress()
                              .getState()
                          != null) {

                    andCondition = " AND ";
                    if (isOpenParentheses) {
                      andCondition = " ";
                    }
                    query += " ";
                    if (isDelete && fetchType == FetchType.EAGER) {
                      whereQuery = " Portfolio7.state = ? ";
                      counter++;
                    } else {
                      whereQuery = " Portfolio7.address.state = :Portfolio7addressstate" + j + " ";
                    }

                    isOpenParentheses = false;
                    whereValue =
                        model
                            .getProductsOfProject()
                            .getPortfolioLicense()
                            .getPortfolio()
                            .getAddress()
                            .getState();
                    query +=
                        (andCondition
                            + Facade.getStatement(
                                statement,
                                "Portfolio7.address.state",
                                "Portfolio7addressstate" + j,
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
                      && model
                              .getProductsOfProject()
                              .getPortfolioLicense()
                              .getPortfolio()
                              .getAddress()
                              .getCountry()
                          != null) {

                    andCondition = " AND ";
                    if (isOpenParentheses) {
                      andCondition = " ";
                    }
                    query += " ";
                    if (isDelete && fetchType == FetchType.EAGER) {
                      whereQuery = " Portfolio7.country = ? ";
                      counter++;
                    } else {
                      whereQuery =
                          " Portfolio7.address.country = :Portfolio7addresscountry" + j + " ";
                    }

                    isOpenParentheses = false;
                    whereValue =
                        model
                            .getProductsOfProject()
                            .getPortfolioLicense()
                            .getPortfolio()
                            .getAddress()
                            .getCountry();
                    query +=
                        (andCondition
                            + Facade.getStatement(
                                statement,
                                "Portfolio7.address.country",
                                "Portfolio7addresscountry" + j,
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
                      && model
                              .getProductsOfProject()
                              .getPortfolioLicense()
                              .getPortfolio()
                              .getAddress()
                              .getZipCode()
                          != null) {

                    andCondition = " AND ";
                    if (isOpenParentheses) {
                      andCondition = " ";
                    }
                    query += " ";
                    if (isDelete && fetchType == FetchType.EAGER) {
                      whereQuery = " Portfolio7.zipCode = ? ";
                      counter++;
                    } else {
                      whereQuery =
                          " Portfolio7.address.zipCode = :Portfolio7addresszipCode" + j + " ";
                    }

                    isOpenParentheses = false;
                    whereValue =
                        model
                            .getProductsOfProject()
                            .getPortfolioLicense()
                            .getPortfolio()
                            .getAddress()
                            .getZipCode();
                    query +=
                        (andCondition
                            + Facade.getStatement(
                                statement,
                                "Portfolio7.address.zipCode",
                                "Portfolio7addresszipCode" + j,
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
                if (!isExistRecord
                    && !isDelete
                    && true
                    && model
                            .getProductsOfProject()
                            .getPortfolioLicense()
                            .getPortfolio()
                            .getCustomerId()
                        != null) {

                  andCondition = " AND ";
                  if (isOpenParentheses) {
                    andCondition = " ";
                  }
                  query += " ";
                  if (isDelete && fetchType == FetchType.EAGER) {
                    whereQuery = " Portfolio7.customerId = ? ";
                    counter++;
                  } else {
                    whereQuery = " Portfolio7.customerId = :Portfolio7customerId" + j + " ";
                  }

                  isOpenParentheses = false;
                  whereValue =
                      model
                          .getProductsOfProject()
                          .getPortfolioLicense()
                          .getPortfolio()
                          .getCustomerId();
                  query +=
                      (andCondition
                          + Facade.getStatement(
                              statement,
                              "Portfolio7.customerId",
                              "Portfolio7customerId" + j,
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
                    && model.getProductsOfProject().getPortfolioLicense().getPortfolio().getTitle()
                        != null) {

                  andCondition = " AND ";
                  if (isOpenParentheses) {
                    andCondition = " ";
                  }
                  query += " ";
                  if (isDelete && fetchType == FetchType.EAGER) {
                    whereQuery = " Portfolio7.title = ? ";
                    counter++;
                  } else {
                    whereQuery = " Portfolio7.title = :Portfolio7title" + j + " ";
                  }

                  isOpenParentheses = false;
                  whereValue =
                      model.getProductsOfProject().getPortfolioLicense().getPortfolio().getTitle();
                  query +=
                      (andCondition
                          + Facade.getStatement(
                              statement,
                              "Portfolio7.title",
                              "Portfolio7title" + j,
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
                    && model
                            .getProductsOfProject()
                            .getPortfolioLicense()
                            .getPortfolio()
                            .getBranchCode()
                        != null) {

                  andCondition = " AND ";
                  if (isOpenParentheses) {
                    andCondition = " ";
                  }
                  query += " ";
                  if (isDelete && fetchType == FetchType.EAGER) {
                    whereQuery = " Portfolio7.branchCode = ? ";
                    counter++;
                  } else {
                    whereQuery = " Portfolio7.branchCode = :Portfolio7branchCode" + j + " ";
                  }

                  isOpenParentheses = false;
                  whereValue =
                      model
                          .getProductsOfProject()
                          .getPortfolioLicense()
                          .getPortfolio()
                          .getBranchCode();
                  query +=
                      (andCondition
                          + Facade.getStatement(
                              statement,
                              "Portfolio7.branchCode",
                              "Portfolio7branchCode" + j,
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
                    && model
                            .getProductsOfProject()
                            .getPortfolioLicense()
                            .getPortfolio()
                            .getEconomicType()
                        != null) {

                  andCondition = " AND ";
                  if (isOpenParentheses) {
                    andCondition = " ";
                  }
                  query += " ";
                  if (isDelete && fetchType == FetchType.EAGER) {
                    whereQuery = " Portfolio7.economicType = ? ";
                    counter++;
                  } else {
                    whereQuery = " Portfolio7.economicType = :Portfolio7economicType" + j + " ";
                  }

                  isOpenParentheses = false;
                  whereValue =
                      model
                          .getProductsOfProject()
                          .getPortfolioLicense()
                          .getPortfolio()
                          .getEconomicType();
                  query +=
                      (andCondition
                          + Facade.getStatement(
                              statement,
                              "Portfolio7.economicType",
                              "Portfolio7economicType" + j,
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
                    && model
                            .getProductsOfProject()
                            .getPortfolioLicense()
                            .getPortfolio()
                            .getIndustryType()
                        != null) {

                  andCondition = " AND ";
                  if (isOpenParentheses) {
                    andCondition = " ";
                  }
                  query += " ";
                  if (isDelete && fetchType == FetchType.EAGER) {
                    whereQuery = " Portfolio7.industryType = ? ";
                    counter++;
                  } else {
                    whereQuery = " Portfolio7.industryType = :Portfolio7industryType" + j + " ";
                  }

                  isOpenParentheses = false;
                  whereValue =
                      model
                          .getProductsOfProject()
                          .getPortfolioLicense()
                          .getPortfolio()
                          .getIndustryType();
                  query +=
                      (andCondition
                          + Facade.getStatement(
                              statement,
                              "Portfolio7.industryType",
                              "Portfolio7industryType" + j,
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
                    && model
                            .getProductsOfProject()
                            .getPortfolioLicense()
                            .getPortfolio()
                            .getTechnologyDescription()
                        != null) {

                  andCondition = " AND ";
                  if (isOpenParentheses) {
                    andCondition = " ";
                  }
                  query += " ";
                  if (isDelete && fetchType == FetchType.EAGER) {
                    whereQuery = " Portfolio7.techDescription = ? ";
                    counter++;
                  } else {
                    whereQuery =
                        " Portfolio7.technologyDescription = :Portfolio7technologyDescription"
                            + j
                            + " ";
                  }

                  isOpenParentheses = false;
                  whereValue =
                      model
                          .getProductsOfProject()
                          .getPortfolioLicense()
                          .getPortfolio()
                          .getTechnologyDescription();
                  query +=
                      (andCondition
                          + Facade.getStatement(
                              statement,
                              "Portfolio7.technologyDescription",
                              "Portfolio7technologyDescription" + j,
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
                    && model
                            .getProductsOfProject()
                            .getPortfolioLicense()
                            .getPortfolio()
                            .getStateType()
                        != null) {

                  andCondition = " AND ";
                  if (isOpenParentheses) {
                    andCondition = " ";
                  }
                  query += " ";
                  if (isDelete && fetchType == FetchType.EAGER) {
                    whereQuery = " Portfolio7.stateType = ? ";
                    counter++;
                  } else {
                    whereQuery = " Portfolio7.stateType = :Portfolio7stateType" + j + " ";
                  }

                  isOpenParentheses = false;
                  whereValue =
                      model
                          .getProductsOfProject()
                          .getPortfolioLicense()
                          .getPortfolio()
                          .getStateType();
                  query +=
                      (andCondition
                          + Facade.getStatement(
                              statement,
                              "Portfolio7.stateType",
                              "Portfolio7stateType" + j,
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
                    && model
                            .getProductsOfProject()
                            .getPortfolioLicense()
                            .getPortfolio()
                            .getLifeType()
                        != null) {

                  andCondition = " AND ";
                  if (isOpenParentheses) {
                    andCondition = " ";
                  }
                  query += " ";
                  if (isDelete && fetchType == FetchType.EAGER) {
                    whereQuery = " Portfolio7.lifeType = ? ";
                    counter++;
                  } else {
                    whereQuery = " Portfolio7.lifeType = :Portfolio7lifeType" + j + " ";
                  }

                  isOpenParentheses = false;
                  whereValue =
                      model
                          .getProductsOfProject()
                          .getPortfolioLicense()
                          .getPortfolio()
                          .getLifeType();
                  query +=
                      (andCondition
                          + Facade.getStatement(
                              statement,
                              "Portfolio7.lifeType",
                              "Portfolio7lifeType" + j,
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
                    && model
                            .getProductsOfProject()
                            .getPortfolioLicense()
                            .getPortfolio()
                            .getStartDate()
                        != null) {

                  andCondition = " AND ";
                  if (isOpenParentheses) {
                    andCondition = " ";
                  }
                  query += " ";
                  if (isDelete && fetchType == FetchType.EAGER) {
                    whereQuery = " Portfolio7.startDate = ? ";
                    counter++;
                  } else {
                    whereQuery = " Portfolio7.startDate = :Portfolio7startDate" + j + " ";
                  }

                  isOpenParentheses = false;
                  whereValue =
                      model
                          .getProductsOfProject()
                          .getPortfolioLicense()
                          .getPortfolio()
                          .getStartDate();
                  query +=
                      (andCondition
                          + Facade.getStatement(
                              statement,
                              "Portfolio7.startDate",
                              "Portfolio7startDate" + j,
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
                    && model
                            .getProductsOfProject()
                            .getPortfolioLicense()
                            .getPortfolio()
                            .getEndDate()
                        != null) {

                  andCondition = " AND ";
                  if (isOpenParentheses) {
                    andCondition = " ";
                  }
                  query += " ";
                  if (isDelete && fetchType == FetchType.EAGER) {
                    whereQuery = " Portfolio7.endDate = ? ";
                    counter++;
                  } else {
                    whereQuery = " Portfolio7.endDate = :Portfolio7endDate" + j + " ";
                  }

                  isOpenParentheses = false;
                  whereValue =
                      model
                          .getProductsOfProject()
                          .getPortfolioLicense()
                          .getPortfolio()
                          .getEndDate();
                  query +=
                      (andCondition
                          + Facade.getStatement(
                              statement,
                              "Portfolio7.endDate",
                              "Portfolio7endDate" + j,
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
                    && model
                            .getProductsOfProject()
                            .getPortfolioLicense()
                            .getPortfolio()
                            .getRequestDate()
                        != null) {

                  andCondition = " AND ";
                  if (isOpenParentheses) {
                    andCondition = " ";
                  }
                  query += " ";
                  if (isDelete && fetchType == FetchType.EAGER) {
                    whereQuery = " Portfolio7.requestDate = ? ";
                    counter++;
                  } else {
                    whereQuery = " Portfolio7.requestDate = :Portfolio7requestDate" + j + " ";
                  }

                  isOpenParentheses = false;
                  whereValue =
                      model
                          .getProductsOfProject()
                          .getPortfolioLicense()
                          .getPortfolio()
                          .getRequestDate();
                  query +=
                      (andCondition
                          + Facade.getStatement(
                              statement,
                              "Portfolio7.requestDate",
                              "Portfolio7requestDate" + j,
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
                    && model
                            .getProductsOfProject()
                            .getPortfolioLicense()
                            .getPortfolio()
                            .getCapitalAMT()
                        != null) {

                  andCondition = " AND ";
                  if (isOpenParentheses) {
                    andCondition = " ";
                  }
                  query += " ";
                  if (isDelete && fetchType == FetchType.EAGER) {
                    whereQuery = " Portfolio7.capitalAMT = ? ";
                    counter++;
                  } else {
                    whereQuery = " Portfolio7.capitalAMT = :Portfolio7capitalAMT" + j + " ";
                  }

                  isOpenParentheses = false;
                  whereValue =
                      model
                          .getProductsOfProject()
                          .getPortfolioLicense()
                          .getPortfolio()
                          .getCapitalAMT();
                  query +=
                      (andCondition
                          + Facade.getStatement(
                              statement,
                              "Portfolio7.capitalAMT",
                              "Portfolio7capitalAMT" + j,
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
                    && model
                            .getProductsOfProject()
                            .getPortfolioLicense()
                            .getPortfolio()
                            .getInvestableAMT()
                        != null) {

                  andCondition = " AND ";
                  if (isOpenParentheses) {
                    andCondition = " ";
                  }
                  query += " ";
                  if (isDelete && fetchType == FetchType.EAGER) {
                    whereQuery = " Portfolio7.investableAMT = ? ";
                    counter++;
                  } else {
                    whereQuery = " Portfolio7.investableAMT = :Portfolio7investableAMT" + j + " ";
                  }

                  isOpenParentheses = false;
                  whereValue =
                      model
                          .getProductsOfProject()
                          .getPortfolioLicense()
                          .getPortfolio()
                          .getInvestableAMT();
                  query +=
                      (andCondition
                          + Facade.getStatement(
                              statement,
                              "Portfolio7.investableAMT",
                              "Portfolio7investableAMT" + j,
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
                    && model
                            .getProductsOfProject()
                            .getPortfolioLicense()
                            .getPortfolio()
                            .getAssessdAMT()
                        != null) {

                  andCondition = " AND ";
                  if (isOpenParentheses) {
                    andCondition = " ";
                  }
                  query += " ";
                  if (isDelete && fetchType == FetchType.EAGER) {
                    whereQuery = " Portfolio7.assessdAMT = ? ";
                    counter++;
                  } else {
                    whereQuery = " Portfolio7.assessdAMT = :Portfolio7assessdAMT" + j + " ";
                  }

                  isOpenParentheses = false;
                  whereValue =
                      model
                          .getProductsOfProject()
                          .getPortfolioLicense()
                          .getPortfolio()
                          .getAssessdAMT();
                  query +=
                      (andCondition
                          + Facade.getStatement(
                              statement,
                              "Portfolio7.assessdAMT",
                              "Portfolio7assessdAMT" + j,
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
                    && model
                            .getProductsOfProject()
                            .getPortfolioLicense()
                            .getPortfolio()
                            .getBankAPPRInvestAMT()
                        != null) {

                  andCondition = " AND ";
                  if (isOpenParentheses) {
                    andCondition = " ";
                  }
                  query += " ";
                  if (isDelete && fetchType == FetchType.EAGER) {
                    whereQuery = " Portfolio7.bankAPPRInvestAMT = ? ";
                    counter++;
                  } else {
                    whereQuery =
                        " Portfolio7.bankAPPRInvestAMT = :Portfolio7bankAPPRInvestAMT" + j + " ";
                  }

                  isOpenParentheses = false;
                  whereValue =
                      model
                          .getProductsOfProject()
                          .getPortfolioLicense()
                          .getPortfolio()
                          .getBankAPPRInvestAMT();
                  query +=
                      (andCondition
                          + Facade.getStatement(
                              statement,
                              "Portfolio7.bankAPPRInvestAMT",
                              "Portfolio7bankAPPRInvestAMT" + j,
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
                    && model
                            .getProductsOfProject()
                            .getPortfolioLicense()
                            .getPortfolio()
                            .getReagent()
                        != null) {

                  andCondition = " AND ";
                  if (isOpenParentheses) {
                    andCondition = " ";
                  }
                  query += " ";
                  if (isDelete && fetchType == FetchType.EAGER) {
                    whereQuery = " Portfolio7.reagent = ? ";
                    counter++;
                  } else {
                    whereQuery = " Portfolio7.reagent = :Portfolio7reagent" + j + " ";
                  }

                  isOpenParentheses = false;
                  whereValue =
                      model
                          .getProductsOfProject()
                          .getPortfolioLicense()
                          .getPortfolio()
                          .getReagent();
                  query +=
                      (andCondition
                          + Facade.getStatement(
                              statement,
                              "Portfolio7.reagent",
                              "Portfolio7reagent" + j,
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
                    && model
                            .getProductsOfProject()
                            .getPortfolioLicense()
                            .getPortfolio()
                            .getBusinessId()
                        != null) {

                  andCondition = " AND ";
                  if (isOpenParentheses) {
                    andCondition = " ";
                  }
                  query += " ";
                  if (isDelete && fetchType == FetchType.EAGER) {
                    whereQuery = " Portfolio7.businessId = ? ";
                    counter++;
                  } else {
                    whereQuery = " Portfolio7.businessId = :Portfolio7businessId" + j + " ";
                  }

                  isOpenParentheses = false;
                  whereValue =
                      model
                          .getProductsOfProject()
                          .getPortfolioLicense()
                          .getPortfolio()
                          .getBusinessId();
                  query +=
                      (andCondition
                          + Facade.getStatement(
                              statement,
                              "Portfolio7.businessId",
                              "Portfolio7businessId" + j,
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
                    && model
                            .getProductsOfProject()
                            .getPortfolioLicense()
                            .getPortfolio()
                            .getVersion()
                        != 0) {

                  andCondition = " AND ";
                  if (isOpenParentheses) {
                    andCondition = " ";
                  }
                  query += " ";
                  if (isDelete && fetchType == FetchType.EAGER) {
                    whereQuery = " Portfolio7.VERSION = ? ";
                    counter++;
                  } else {
                    whereQuery = " Portfolio7.version = :Portfolio7version" + j + " ";
                  }

                  isOpenParentheses = false;
                  whereValue =
                      new java.lang.Integer(
                          ""
                              + model
                                  .getProductsOfProject()
                                  .getPortfolioLicense()
                                  .getPortfolio()
                                  .getVersion());
                  query +=
                      (andCondition
                          + Facade.getStatement(
                              statement,
                              "Portfolio7.version",
                              "Portfolio7version" + j,
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
              if (!isExistRecord
                  && !isDelete
                  && model.getProductsOfProject().getPortfolioLicense().getFile() != null) {

                if (model.getProductsOfProject().getPortfolioLicense().getFile().getId() != null) {

                  andCondition = " AND ";
                  if (isOpenParentheses) {
                    andCondition = " ";
                  }
                  query += " ";
                  if (isDelete && fetchType == FetchType.EAGER) {
                    whereQuery = " File25.l4610flmtdataid = ? ";
                    counter++;
                  } else {
                    whereQuery = " File25.id = :File25id" + j + " ";
                  }

                  isOpenParentheses = false;
                  whereValue = model.getProductsOfProject().getPortfolioLicense().getFile().getId();
                  query +=
                      (andCondition
                          + Facade.getStatement(
                              statement,
                              "File25.id",
                              "File25id" + j,
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
                    && model.getProductsOfProject().getPortfolioLicense().getFile().getExtension()
                        != null) {

                  andCondition = " AND ";
                  if (isOpenParentheses) {
                    andCondition = " ";
                  }
                  query += " ";
                  if (isDelete && fetchType == FetchType.EAGER) {
                    whereQuery = " File25.l4610extension = ? ";
                    counter++;
                  } else {
                    whereQuery = " File25.extension = :File25extension" + j + " ";
                  }

                  isOpenParentheses = false;
                  whereValue =
                      model.getProductsOfProject().getPortfolioLicense().getFile().getExtension();
                  query +=
                      (andCondition
                          + Facade.getStatement(
                              statement,
                              "File25.extension",
                              "File25extension" + j,
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
                    && model.getProductsOfProject().getPortfolioLicense().getFile().getVersion()
                        != 0) {

                  andCondition = " AND ";
                  if (isOpenParentheses) {
                    andCondition = " ";
                  }
                  query += " ";
                  if (isDelete && fetchType == FetchType.EAGER) {
                    whereQuery = " File25.VERSION = ? ";
                    counter++;
                  } else {
                    whereQuery = " File25.version = :File25version" + j + " ";
                  }

                  isOpenParentheses = false;
                  whereValue =
                      new java.lang.Integer(
                          ""
                              + model
                                  .getProductsOfProject()
                                  .getPortfolioLicense()
                                  .getFile()
                                  .getVersion());
                  query +=
                      (andCondition
                          + Facade.getStatement(
                              statement,
                              "File25.version",
                              "File25version" + j,
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
              if (!isExistRecord
                  && !isDelete
                  && fetchType == FetchType.EAGER
                  && model.getProductsOfProject().getPortfolioLicense().getLicenseDetails()
                      != null) {
                int length26 =
                    model.getProductsOfProject().getPortfolioLicense().getLicenseDetails().size();

                if (length26 > 0) {
                  if (isOpenParentheses) {
                    query += "  ( ";
                  } else {
                    query += " AND ( ";
                  }
                  isOpenParentheses = true;

                  for (int i26 = 0; i26 < length26; i26++) {

                    java.lang.String beforeCondition26 = "";
                    java.lang.String prefix26 = " OR ";
                    if (i26 == 0) {
                      prefix26 = "";
                    }
                    if (length26 > 1) {

                      query += (prefix26 + "(");
                      isOpenParentheses = true;

                    } else {

                      query += (prefix26);
                      if (!prefix26.trim().equals("")) {
                        isOpenParentheses = false;
                      }
                    }

                    if (model
                            .getProductsOfProject()
                            .getPortfolioLicense()
                            .getLicenseDetails()
                            .get(i26)
                            .getLicenseDetailId()
                        != null) {

                      andCondition = " AND ";
                      if (isOpenParentheses) {
                        andCondition = " ";
                      }
                      query += " ";
                      whereValue =
                          model
                              .getProductsOfProject()
                              .getPortfolioLicense()
                              .getLicenseDetails()
                              .get(i26)
                              .getLicenseDetailId();
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " LicenseDetails26.id = ? ";
                        counter++;
                      } else {
                        whereQuery =
                            " LicenseDetails26.licenseDetailId = :LicenseDetails26licenseDetailId"
                                + j
                                + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          model
                              .getProductsOfProject()
                              .getPortfolioLicense()
                              .getLicenseDetails()
                              .get(i26)
                              .getLicenseDetailId();
                      query +=
                          (andCondition
                              + Facade.getStatement(
                                  statement,
                                  "LicenseDetails26.licenseDetailId",
                                  "LicenseDetails26licenseDetailId" + j,
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
                        && model
                                .getProductsOfProject()
                                .getPortfolioLicense()
                                .getLicenseDetails()
                                .get(i26)
                                .getFile()
                            != null) {

                      if (model
                              .getProductsOfProject()
                              .getPortfolioLicense()
                              .getLicenseDetails()
                              .get(i26)
                              .getFile()
                              .getId()
                          != null) {

                        andCondition = " AND ";
                        if (isOpenParentheses) {
                          andCondition = " ";
                        }
                        query += " ";
                        whereValue =
                            model
                                .getProductsOfProject()
                                .getPortfolioLicense()
                                .getLicenseDetails()
                                .get(i26)
                                .getFile()
                                .getId();
                        if (isDelete && fetchType == FetchType.EAGER) {
                          whereQuery = " File28.l4610flmtdataid = ? ";
                          counter++;
                        } else {
                          whereQuery = " File28.id = :File28id" + j + " ";
                        }

                        isOpenParentheses = false;
                        whereValue =
                            model
                                .getProductsOfProject()
                                .getPortfolioLicense()
                                .getLicenseDetails()
                                .get(i26)
                                .getFile()
                                .getId();
                        query +=
                            (andCondition
                                + Facade.getStatement(
                                    statement,
                                    "File28.id",
                                    "File28id" + j,
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
                          && model
                                  .getProductsOfProject()
                                  .getPortfolioLicense()
                                  .getLicenseDetails()
                                  .get(i26)
                                  .getFile()
                                  .getExtension()
                              != null) {

                        andCondition = " AND ";
                        if (isOpenParentheses) {
                          andCondition = " ";
                        }
                        query += " ";
                        whereValue =
                            model
                                .getProductsOfProject()
                                .getPortfolioLicense()
                                .getLicenseDetails()
                                .get(i26)
                                .getFile()
                                .getExtension();
                        if (isDelete && fetchType == FetchType.EAGER) {
                          whereQuery = " File28.l4610extension = ? ";
                          counter++;
                        } else {
                          whereQuery = " File28.extension = :File28extension" + j + " ";
                        }

                        isOpenParentheses = false;
                        whereValue =
                            model
                                .getProductsOfProject()
                                .getPortfolioLicense()
                                .getLicenseDetails()
                                .get(i26)
                                .getFile()
                                .getExtension();
                        query +=
                            (andCondition
                                + Facade.getStatement(
                                    statement,
                                    "File28.extension",
                                    "File28extension" + j,
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
                          && model
                                  .getProductsOfProject()
                                  .getPortfolioLicense()
                                  .getLicenseDetails()
                                  .get(i26)
                                  .getFile()
                                  .getVersion()
                              != 0) {

                        andCondition = " AND ";
                        if (isOpenParentheses) {
                          andCondition = " ";
                        }
                        query += " ";
                        whereValue =
                            new java.lang.Integer(
                                ""
                                    + model
                                        .getProductsOfProject()
                                        .getPortfolioLicense()
                                        .getLicenseDetails()
                                        .get(i26)
                                        .getFile()
                                        .getVersion());
                        if (isDelete && fetchType == FetchType.EAGER) {
                          whereQuery = " File28.VERSION = ? ";
                          counter++;
                        } else {
                          whereQuery = " File28.version = :File28version" + j + " ";
                        }

                        isOpenParentheses = false;
                        whereValue =
                            new java.lang.Integer(
                                ""
                                    + model
                                        .getProductsOfProject()
                                        .getPortfolioLicense()
                                        .getLicenseDetails()
                                        .get(i26)
                                        .getFile()
                                        .getVersion());
                        query +=
                            (andCondition
                                + Facade.getStatement(
                                    statement,
                                    "File28.version",
                                    "File28version" + j,
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
                    if (!isDelete
                        && model
                                .getProductsOfProject()
                                .getPortfolioLicense()
                                .getLicenseDetails()
                                .get(i26)
                                .getAddress()
                            != null) {
                      if (!isDelete
                          && true
                          && model
                                  .getProductsOfProject()
                                  .getPortfolioLicense()
                                  .getLicenseDetails()
                                  .get(i26)
                                  .getAddress()
                                  .getAddressLine1()
                              != null) {

                        andCondition = " AND ";
                        if (isOpenParentheses) {
                          andCondition = " ";
                        }
                        query += " ";
                        whereValue =
                            model
                                .getProductsOfProject()
                                .getPortfolioLicense()
                                .getLicenseDetails()
                                .get(i26)
                                .getAddress()
                                .getAddressLine1();
                        if (isDelete && fetchType == FetchType.EAGER) {
                          whereQuery = " LicenseDetails26.addressLine1 = ? ";
                          counter++;
                        } else {
                          whereQuery =
                              " LicenseDetails26.address.addressLine1 = :LicenseDetails26addressaddressLine1"
                                  + j
                                  + " ";
                        }

                        isOpenParentheses = false;
                        whereValue =
                            model
                                .getProductsOfProject()
                                .getPortfolioLicense()
                                .getLicenseDetails()
                                .get(i26)
                                .getAddress()
                                .getAddressLine1();
                        query +=
                            (andCondition
                                + Facade.getStatement(
                                    statement,
                                    "LicenseDetails26.address.addressLine1",
                                    "LicenseDetails26addressaddressLine1" + j,
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
                          && model
                                  .getProductsOfProject()
                                  .getPortfolioLicense()
                                  .getLicenseDetails()
                                  .get(i26)
                                  .getAddress()
                                  .getAddressLine2()
                              != null) {

                        andCondition = " AND ";
                        if (isOpenParentheses) {
                          andCondition = " ";
                        }
                        query += " ";
                        whereValue =
                            model
                                .getProductsOfProject()
                                .getPortfolioLicense()
                                .getLicenseDetails()
                                .get(i26)
                                .getAddress()
                                .getAddressLine2();
                        if (isDelete && fetchType == FetchType.EAGER) {
                          whereQuery = " LicenseDetails26.addressLine2 = ? ";
                          counter++;
                        } else {
                          whereQuery =
                              " LicenseDetails26.address.addressLine2 = :LicenseDetails26addressaddressLine2"
                                  + j
                                  + " ";
                        }

                        isOpenParentheses = false;
                        whereValue =
                            model
                                .getProductsOfProject()
                                .getPortfolioLicense()
                                .getLicenseDetails()
                                .get(i26)
                                .getAddress()
                                .getAddressLine2();
                        query +=
                            (andCondition
                                + Facade.getStatement(
                                    statement,
                                    "LicenseDetails26.address.addressLine2",
                                    "LicenseDetails26addressaddressLine2" + j,
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
                          && model
                                  .getProductsOfProject()
                                  .getPortfolioLicense()
                                  .getLicenseDetails()
                                  .get(i26)
                                  .getAddress()
                                  .getCity()
                              != null) {

                        andCondition = " AND ";
                        if (isOpenParentheses) {
                          andCondition = " ";
                        }
                        query += " ";
                        whereValue =
                            model
                                .getProductsOfProject()
                                .getPortfolioLicense()
                                .getLicenseDetails()
                                .get(i26)
                                .getAddress()
                                .getCity();
                        if (isDelete && fetchType == FetchType.EAGER) {
                          whereQuery = " LicenseDetails26.city = ? ";
                          counter++;
                        } else {
                          whereQuery =
                              " LicenseDetails26.address.city = :LicenseDetails26addresscity"
                                  + j
                                  + " ";
                        }

                        isOpenParentheses = false;
                        whereValue =
                            model
                                .getProductsOfProject()
                                .getPortfolioLicense()
                                .getLicenseDetails()
                                .get(i26)
                                .getAddress()
                                .getCity();
                        query +=
                            (andCondition
                                + Facade.getStatement(
                                    statement,
                                    "LicenseDetails26.address.city",
                                    "LicenseDetails26addresscity" + j,
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
                          && model
                                  .getProductsOfProject()
                                  .getPortfolioLicense()
                                  .getLicenseDetails()
                                  .get(i26)
                                  .getAddress()
                                  .getState()
                              != null) {

                        andCondition = " AND ";
                        if (isOpenParentheses) {
                          andCondition = " ";
                        }
                        query += " ";
                        whereValue =
                            model
                                .getProductsOfProject()
                                .getPortfolioLicense()
                                .getLicenseDetails()
                                .get(i26)
                                .getAddress()
                                .getState();
                        if (isDelete && fetchType == FetchType.EAGER) {
                          whereQuery = " LicenseDetails26.state = ? ";
                          counter++;
                        } else {
                          whereQuery =
                              " LicenseDetails26.address.state = :LicenseDetails26addressstate"
                                  + j
                                  + " ";
                        }

                        isOpenParentheses = false;
                        whereValue =
                            model
                                .getProductsOfProject()
                                .getPortfolioLicense()
                                .getLicenseDetails()
                                .get(i26)
                                .getAddress()
                                .getState();
                        query +=
                            (andCondition
                                + Facade.getStatement(
                                    statement,
                                    "LicenseDetails26.address.state",
                                    "LicenseDetails26addressstate" + j,
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
                          && model
                                  .getProductsOfProject()
                                  .getPortfolioLicense()
                                  .getLicenseDetails()
                                  .get(i26)
                                  .getAddress()
                                  .getCountry()
                              != null) {

                        andCondition = " AND ";
                        if (isOpenParentheses) {
                          andCondition = " ";
                        }
                        query += " ";
                        whereValue =
                            model
                                .getProductsOfProject()
                                .getPortfolioLicense()
                                .getLicenseDetails()
                                .get(i26)
                                .getAddress()
                                .getCountry();
                        if (isDelete && fetchType == FetchType.EAGER) {
                          whereQuery = " LicenseDetails26.country = ? ";
                          counter++;
                        } else {
                          whereQuery =
                              " LicenseDetails26.address.country = :LicenseDetails26addresscountry"
                                  + j
                                  + " ";
                        }

                        isOpenParentheses = false;
                        whereValue =
                            model
                                .getProductsOfProject()
                                .getPortfolioLicense()
                                .getLicenseDetails()
                                .get(i26)
                                .getAddress()
                                .getCountry();
                        query +=
                            (andCondition
                                + Facade.getStatement(
                                    statement,
                                    "LicenseDetails26.address.country",
                                    "LicenseDetails26addresscountry" + j,
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
                          && model
                                  .getProductsOfProject()
                                  .getPortfolioLicense()
                                  .getLicenseDetails()
                                  .get(i26)
                                  .getAddress()
                                  .getZipCode()
                              != null) {

                        andCondition = " AND ";
                        if (isOpenParentheses) {
                          andCondition = " ";
                        }
                        query += " ";
                        whereValue =
                            model
                                .getProductsOfProject()
                                .getPortfolioLicense()
                                .getLicenseDetails()
                                .get(i26)
                                .getAddress()
                                .getZipCode();
                        if (isDelete && fetchType == FetchType.EAGER) {
                          whereQuery = " LicenseDetails26.zipCode = ? ";
                          counter++;
                        } else {
                          whereQuery =
                              " LicenseDetails26.address.zipCode = :LicenseDetails26addresszipCode"
                                  + j
                                  + " ";
                        }

                        isOpenParentheses = false;
                        whereValue =
                            model
                                .getProductsOfProject()
                                .getPortfolioLicense()
                                .getLicenseDetails()
                                .get(i26)
                                .getAddress()
                                .getZipCode();
                        query +=
                            (andCondition
                                + Facade.getStatement(
                                    statement,
                                    "LicenseDetails26.address.zipCode",
                                    "LicenseDetails26addresszipCode" + j,
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
                    if (length26 > 1) {
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
                  && model.getProductsOfProject().getPortfolioLicense().getIssueVerifierType()
                      != null) {

                andCondition = " AND ";
                if (isOpenParentheses) {
                  andCondition = " ";
                }
                query += " ";
                if (isDelete && fetchType == FetchType.EAGER) {
                  whereQuery = " PortfolioLicense6.issueVerifierType = ? ";
                  counter++;
                } else {
                  whereQuery =
                      " PortfolioLicense6.issueVerifierType = :PortfolioLicense6issueVerifierType"
                          + j
                          + " ";
                }

                isOpenParentheses = false;
                whereValue =
                    model.getProductsOfProject().getPortfolioLicense().getIssueVerifierType();
                query +=
                    (andCondition
                        + Facade.getStatement(
                            statement,
                            "PortfolioLicense6.issueVerifierType",
                            "PortfolioLicense6issueVerifierType" + j,
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
                  && model.getProductsOfProject().getPortfolioLicense().getLicenseNumber()
                      != null) {

                andCondition = " AND ";
                if (isOpenParentheses) {
                  andCondition = " ";
                }
                query += " ";
                if (isDelete && fetchType == FetchType.EAGER) {
                  whereQuery = " PortfolioLicense6.licenseNumber = ? ";
                  counter++;
                } else {
                  whereQuery =
                      " PortfolioLicense6.licenseNumber = :PortfolioLicense6licenseNumber"
                          + j
                          + " ";
                }

                isOpenParentheses = false;
                whereValue = model.getProductsOfProject().getPortfolioLicense().getLicenseNumber();
                query +=
                    (andCondition
                        + Facade.getStatement(
                            statement,
                            "PortfolioLicense6.licenseNumber",
                            "PortfolioLicense6licenseNumber" + j,
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
                  && model.getProductsOfProject().getPortfolioLicense().getLicenseType() != null) {

                andCondition = " AND ";
                if (isOpenParentheses) {
                  andCondition = " ";
                }
                query += " ";
                if (isDelete && fetchType == FetchType.EAGER) {
                  whereQuery = " PortfolioLicense6.licenseType = ? ";
                  counter++;
                } else {
                  whereQuery =
                      " PortfolioLicense6.licenseType = :PortfolioLicense6licenseType" + j + " ";
                }

                isOpenParentheses = false;
                whereValue = model.getProductsOfProject().getPortfolioLicense().getLicenseType();
                query +=
                    (andCondition
                        + Facade.getStatement(
                            statement,
                            "PortfolioLicense6.licenseType",
                            "PortfolioLicense6licenseType" + j,
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
                  && model.getProductsOfProject().getPortfolioLicense().getIssueDate() != null) {

                andCondition = " AND ";
                if (isOpenParentheses) {
                  andCondition = " ";
                }
                query += " ";
                if (isDelete && fetchType == FetchType.EAGER) {
                  whereQuery = " PortfolioLicense6.issueDate = ? ";
                  counter++;
                } else {
                  whereQuery =
                      " PortfolioLicense6.issueDate = :PortfolioLicense6issueDate" + j + " ";
                }

                isOpenParentheses = false;
                whereValue = model.getProductsOfProject().getPortfolioLicense().getIssueDate();
                query +=
                    (andCondition
                        + Facade.getStatement(
                            statement,
                            "PortfolioLicense6.issueDate",
                            "PortfolioLicense6issueDate" + j,
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
                  && model.getProductsOfProject().getPortfolioLicense().getEndDate() != null) {

                andCondition = " AND ";
                if (isOpenParentheses) {
                  andCondition = " ";
                }
                query += " ";
                if (isDelete && fetchType == FetchType.EAGER) {
                  whereQuery = " PortfolioLicense6.endDate = ? ";
                  counter++;
                } else {
                  whereQuery = " PortfolioLicense6.endDate = :PortfolioLicense6endDate" + j + " ";
                }

                isOpenParentheses = false;
                whereValue = model.getProductsOfProject().getPortfolioLicense().getEndDate();
                query +=
                    (andCondition
                        + Facade.getStatement(
                            statement,
                            "PortfolioLicense6.endDate",
                            "PortfolioLicense6endDate" + j,
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
                  && model.getProductsOfProject().getPortfolioLicense().getVersion() != 0) {

                andCondition = " AND ";
                if (isOpenParentheses) {
                  andCondition = " ";
                }
                query += " ";
                if (isDelete && fetchType == FetchType.EAGER) {
                  whereQuery = " PortfolioLicense6.VERSION = ? ";
                  counter++;
                } else {
                  whereQuery = " PortfolioLicense6.version = :PortfolioLicense6version" + j + " ";
                }

                isOpenParentheses = false;
                whereValue =
                    new java.lang.Integer(
                        "" + model.getProductsOfProject().getPortfolioLicense().getVersion());
                query +=
                    (andCondition
                        + Facade.getStatement(
                            statement,
                            "PortfolioLicense6.version",
                            "PortfolioLicense6version" + j,
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
            if (!isDelete && model.getProductsOfProject().getAddress() != null) {
              if (!isDelete
                  && true
                  && model.getProductsOfProject().getAddress().getAddressLine1() != null) {

                andCondition = " AND ";
                if (isOpenParentheses) {
                  andCondition = " ";
                }
                query += " ";
                if (isDelete && fetchType == FetchType.EAGER) {
                  whereQuery = " ProductsOfProject1.addressLine1 = ? ";
                  counter++;
                } else {
                  whereQuery =
                      " ProductsOfProject1.address.addressLine1 = :ProductsOfProject1addressaddressLine1"
                          + j
                          + " ";
                }

                isOpenParentheses = false;
                whereValue = model.getProductsOfProject().getAddress().getAddressLine1();
                query +=
                    (andCondition
                        + Facade.getStatement(
                            statement,
                            "ProductsOfProject1.address.addressLine1",
                            "ProductsOfProject1addressaddressLine1" + j,
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
                  && model.getProductsOfProject().getAddress().getAddressLine2() != null) {

                andCondition = " AND ";
                if (isOpenParentheses) {
                  andCondition = " ";
                }
                query += " ";
                if (isDelete && fetchType == FetchType.EAGER) {
                  whereQuery = " ProductsOfProject1.addressLine2 = ? ";
                  counter++;
                } else {
                  whereQuery =
                      " ProductsOfProject1.address.addressLine2 = :ProductsOfProject1addressaddressLine2"
                          + j
                          + " ";
                }

                isOpenParentheses = false;
                whereValue = model.getProductsOfProject().getAddress().getAddressLine2();
                query +=
                    (andCondition
                        + Facade.getStatement(
                            statement,
                            "ProductsOfProject1.address.addressLine2",
                            "ProductsOfProject1addressaddressLine2" + j,
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
                  && model.getProductsOfProject().getAddress().getCity() != null) {

                andCondition = " AND ";
                if (isOpenParentheses) {
                  andCondition = " ";
                }
                query += " ";
                if (isDelete && fetchType == FetchType.EAGER) {
                  whereQuery = " ProductsOfProject1.city = ? ";
                  counter++;
                } else {
                  whereQuery =
                      " ProductsOfProject1.address.city = :ProductsOfProject1addresscity" + j + " ";
                }

                isOpenParentheses = false;
                whereValue = model.getProductsOfProject().getAddress().getCity();
                query +=
                    (andCondition
                        + Facade.getStatement(
                            statement,
                            "ProductsOfProject1.address.city",
                            "ProductsOfProject1addresscity" + j,
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
                  && model.getProductsOfProject().getAddress().getState() != null) {

                andCondition = " AND ";
                if (isOpenParentheses) {
                  andCondition = " ";
                }
                query += " ";
                if (isDelete && fetchType == FetchType.EAGER) {
                  whereQuery = " ProductsOfProject1.state = ? ";
                  counter++;
                } else {
                  whereQuery =
                      " ProductsOfProject1.address.state = :ProductsOfProject1addressstate"
                          + j
                          + " ";
                }

                isOpenParentheses = false;
                whereValue = model.getProductsOfProject().getAddress().getState();
                query +=
                    (andCondition
                        + Facade.getStatement(
                            statement,
                            "ProductsOfProject1.address.state",
                            "ProductsOfProject1addressstate" + j,
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
                  && model.getProductsOfProject().getAddress().getCountry() != null) {

                andCondition = " AND ";
                if (isOpenParentheses) {
                  andCondition = " ";
                }
                query += " ";
                if (isDelete && fetchType == FetchType.EAGER) {
                  whereQuery = " ProductsOfProject1.country = ? ";
                  counter++;
                } else {
                  whereQuery =
                      " ProductsOfProject1.address.country = :ProductsOfProject1addresscountry"
                          + j
                          + " ";
                }

                isOpenParentheses = false;
                whereValue = model.getProductsOfProject().getAddress().getCountry();
                query +=
                    (andCondition
                        + Facade.getStatement(
                            statement,
                            "ProductsOfProject1.address.country",
                            "ProductsOfProject1addresscountry" + j,
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
                  && model.getProductsOfProject().getAddress().getZipCode() != null) {

                andCondition = " AND ";
                if (isOpenParentheses) {
                  andCondition = " ";
                }
                query += " ";
                if (isDelete && fetchType == FetchType.EAGER) {
                  whereQuery = " ProductsOfProject1.zipCode = ? ";
                  counter++;
                } else {
                  whereQuery =
                      " ProductsOfProject1.address.zipCode = :ProductsOfProject1addresszipCode"
                          + j
                          + " ";
                }

                isOpenParentheses = false;
                whereValue = model.getProductsOfProject().getAddress().getZipCode();
                query +=
                    (andCondition
                        + Facade.getStatement(
                            statement,
                            "ProductsOfProject1.address.zipCode",
                            "ProductsOfProject1addresszipCode" + j,
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
            if (!isExistRecord
                && !isDelete
                && true
                && model.getProductsOfProject().getNominalCapacity() != null) {

              andCondition = " AND ";
              if (isOpenParentheses) {
                andCondition = " ";
              }
              query += " ";
              if (isDelete && fetchType == FetchType.EAGER) {
                whereQuery = " ProductsOfProject1.nominalCapacity = ? ";
                counter++;
              } else {
                whereQuery =
                    " ProductsOfProject1.nominalCapacity = :ProductsOfProject1nominalCapacity"
                        + j
                        + " ";
              }

              isOpenParentheses = false;
              whereValue = model.getProductsOfProject().getNominalCapacity();
              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "ProductsOfProject1.nominalCapacity",
                          "ProductsOfProject1nominalCapacity" + j,
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
                && model.getProductsOfProject().getPracticalCapacity() != null) {

              andCondition = " AND ";
              if (isOpenParentheses) {
                andCondition = " ";
              }
              query += " ";
              if (isDelete && fetchType == FetchType.EAGER) {
                whereQuery = " ProductsOfProject1.practicalCapacity = ? ";
                counter++;
              } else {
                whereQuery =
                    " ProductsOfProject1.practicalCapacity = :ProductsOfProject1practicalCapacity"
                        + j
                        + " ";
              }

              isOpenParentheses = false;
              whereValue = model.getProductsOfProject().getPracticalCapacity();
              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "ProductsOfProject1.practicalCapacity",
                          "ProductsOfProject1practicalCapacity" + j,
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
                && model.getProductsOfProject().getPracCapacityPrcntg() != null) {

              andCondition = " AND ";
              if (isOpenParentheses) {
                andCondition = " ";
              }
              query += " ";
              if (isDelete && fetchType == FetchType.EAGER) {
                whereQuery = " ProductsOfProject1.pracCapacityPrcntg = ? ";
                counter++;
              } else {
                whereQuery =
                    " ProductsOfProject1.pracCapacityPrcntg = :ProductsOfProject1pracCapacityPrcntg"
                        + j
                        + " ";
              }

              isOpenParentheses = false;
              whereValue = model.getProductsOfProject().getPracCapacityPrcntg();
              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "ProductsOfProject1.pracCapacityPrcntg",
                          "ProductsOfProject1pracCapacityPrcntg" + j,
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
                && model.getProductsOfProject().getTypeOfSelling() != null) {

              andCondition = " AND ";
              if (isOpenParentheses) {
                andCondition = " ";
              }
              query += " ";
              if (isDelete && fetchType == FetchType.EAGER) {
                whereQuery = " ProductsOfProject1.typeOfSelling = ? ";
                counter++;
              } else {
                whereQuery =
                    " ProductsOfProject1.typeOfSelling = :ProductsOfProject1typeOfSelling"
                        + j
                        + " ";
              }

              isOpenParentheses = false;
              whereValue = model.getProductsOfProject().getTypeOfSelling();
              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "ProductsOfProject1.typeOfSelling",
                          "ProductsOfProject1typeOfSelling" + j,
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
                && model.getProductsOfProject().getMarketplace() != null) {

              andCondition = " AND ";
              if (isOpenParentheses) {
                andCondition = " ";
              }
              query += " ";
              if (isDelete && fetchType == FetchType.EAGER) {
                whereQuery = " ProductsOfProject1.marketplace = ? ";
                counter++;
              } else {
                whereQuery =
                    " ProductsOfProject1.marketplace = :ProductsOfProject1marketplace" + j + " ";
              }

              isOpenParentheses = false;
              whereValue = model.getProductsOfProject().getMarketplace();
              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "ProductsOfProject1.marketplace",
                          "ProductsOfProject1marketplace" + j,
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
                && model.getProductsOfProject().getMainProduct() != null) {

              andCondition = " AND ";
              if (isOpenParentheses) {
                andCondition = " ";
              }
              query += " ";
              if (isDelete && fetchType == FetchType.EAGER) {
                whereQuery = " ProductsOfProject1.mainProduct = ? ";
                counter++;
              } else {
                whereQuery =
                    " ProductsOfProject1.mainProduct = :ProductsOfProject1mainProduct" + j + " ";
              }

              isOpenParentheses = false;
              whereValue = model.getProductsOfProject().getMainProduct();
              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "ProductsOfProject1.mainProduct",
                          "ProductsOfProject1mainProduct" + j,
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
                && model.getProductsOfProject().getIntSellingPr() != null) {

              andCondition = " AND ";
              if (isOpenParentheses) {
                andCondition = " ";
              }
              query += " ";
              if (isDelete && fetchType == FetchType.EAGER) {
                whereQuery = " ProductsOfProject1.intSellingPr = ? ";
                counter++;
              } else {
                whereQuery =
                    " ProductsOfProject1.intSellingPr = :ProductsOfProject1intSellingPr" + j + " ";
              }

              isOpenParentheses = false;
              whereValue = model.getProductsOfProject().getIntSellingPr();
              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "ProductsOfProject1.intSellingPr",
                          "ProductsOfProject1intSellingPr" + j,
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
                && model.getProductsOfProject().getExSellingPr() != null) {

              andCondition = " AND ";
              if (isOpenParentheses) {
                andCondition = " ";
              }
              query += " ";
              if (isDelete && fetchType == FetchType.EAGER) {
                whereQuery = " ProductsOfProject1.exSellingPr = ? ";
                counter++;
              } else {
                whereQuery =
                    " ProductsOfProject1.exSellingPr = :ProductsOfProject1exSellingPr" + j + " ";
              }

              isOpenParentheses = false;
              whereValue = model.getProductsOfProject().getExSellingPr();
              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "ProductsOfProject1.exSellingPr",
                          "ProductsOfProject1exSellingPr" + j,
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
                && model.getProductsOfProject().getDescription() != null) {

              andCondition = " AND ";
              if (isOpenParentheses) {
                andCondition = " ";
              }
              query += " ";
              if (isDelete && fetchType == FetchType.EAGER) {
                whereQuery = " ProductsOfProject1.description = ? ";
                counter++;
              } else {
                whereQuery =
                    " ProductsOfProject1.description = :ProductsOfProject1description" + j + " ";
              }

              isOpenParentheses = false;
              whereValue = model.getProductsOfProject().getDescription();
              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "ProductsOfProject1.description",
                          "ProductsOfProject1description" + j,
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
                && model.getProductsOfProject().getEvaluationDate() != null) {

              andCondition = " AND ";
              if (isOpenParentheses) {
                andCondition = " ";
              }
              query += " ";
              if (isDelete && fetchType == FetchType.EAGER) {
                whereQuery = " ProductsOfProject1.evaluationDate = ? ";
                counter++;
              } else {
                whereQuery =
                    " ProductsOfProject1.evaluationDate = :ProductsOfProject1evaluationDate"
                        + j
                        + " ";
              }

              isOpenParentheses = false;
              whereValue = model.getProductsOfProject().getEvaluationDate();
              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "ProductsOfProject1.evaluationDate",
                          "ProductsOfProject1evaluationDate" + j,
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
                && model.getProductsOfProject().getCalSelection() != null) {

              andCondition = " AND ";
              if (isOpenParentheses) {
                andCondition = " ";
              }
              query += " ";
              if (isDelete && fetchType == FetchType.EAGER) {
                whereQuery = " ProductsOfProject1.calSelection = ? ";
                counter++;
              } else {
                whereQuery =
                    " ProductsOfProject1.calSelection = :ProductsOfProject1calSelection" + j + " ";
              }

              isOpenParentheses = false;
              whereValue = model.getProductsOfProject().getCalSelection();
              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "ProductsOfProject1.calSelection",
                          "ProductsOfProject1calSelection" + j,
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
                && model.getProductsOfProject().getVersion() != 0) {

              andCondition = " AND ";
              if (isOpenParentheses) {
                andCondition = " ";
              }
              query += " ";
              if (isDelete && fetchType == FetchType.EAGER) {
                whereQuery = " ProductsOfProject1.VERSION = ? ";
                counter++;
              } else {
                whereQuery = " ProductsOfProject1.version = :ProductsOfProject1version" + j + " ";
              }

              isOpenParentheses = false;
              whereValue = new java.lang.Integer("" + model.getProductsOfProject().getVersion());
              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "ProductsOfProject1.version",
                          "ProductsOfProject1version" + j,
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
          if (!isExistRecord && true && model.getMaterialName() != null) {

            andCondition = " AND ";
            if (isOpenParentheses) {
              andCondition = " ";
            }
            query += " ";
            if (isDelete && fetchType == FetchType.EAGER) {
              whereQuery = " MaterialOfProject0.materialName = ? ";
              counter++;
            } else {
              whereQuery =
                  " MaterialOfProject0.materialName = :MaterialOfProject0materialName" + j + " ";
            }

            isOpenParentheses = false;
            whereValue = model.getMaterialName();
            query +=
                (andCondition
                    + Facade.getStatement(
                        statement,
                        "MaterialOfProject0.materialName",
                        "MaterialOfProject0materialName" + j,
                        mapVariable,
                        whereQuery,
                        whereValue,
                        isExistRecord,
                        isDelete,
                        fetchType,
                        counter));
            whereQuery = "";
          }
          if (!isExistRecord && true && model.getEvaluationUnit() != null) {

            andCondition = " AND ";
            if (isOpenParentheses) {
              andCondition = " ";
            }
            query += " ";
            if (isDelete && fetchType == FetchType.EAGER) {
              whereQuery = " MaterialOfProject0.evaluationUnit = ? ";
              counter++;
            } else {
              whereQuery =
                  " MaterialOfProject0.evaluationUnit = :MaterialOfProject0evaluationUnit"
                      + j
                      + " ";
            }

            isOpenParentheses = false;
            whereValue = model.getEvaluationUnit();
            query +=
                (andCondition
                    + Facade.getStatement(
                        statement,
                        "MaterialOfProject0.evaluationUnit",
                        "MaterialOfProject0evaluationUnit" + j,
                        mapVariable,
                        whereQuery,
                        whereValue,
                        isExistRecord,
                        isDelete,
                        fetchType,
                        counter));
            whereQuery = "";
          }
          if (!isExistRecord && true && model.getQuantityOfUsage() != null) {

            andCondition = " AND ";
            if (isOpenParentheses) {
              andCondition = " ";
            }
            query += " ";
            if (isDelete && fetchType == FetchType.EAGER) {
              whereQuery = " MaterialOfProject0.quantityOfUsage = ? ";
              counter++;
            } else {
              whereQuery =
                  " MaterialOfProject0.quantityOfUsage = :MaterialOfProject0quantityOfUsage"
                      + j
                      + " ";
            }

            isOpenParentheses = false;
            whereValue = model.getQuantityOfUsage();
            query +=
                (andCondition
                    + Facade.getStatement(
                        statement,
                        "MaterialOfProject0.quantityOfUsage",
                        "MaterialOfProject0quantityOfUsage" + j,
                        mapVariable,
                        whereQuery,
                        whereValue,
                        isExistRecord,
                        isDelete,
                        fetchType,
                        counter));
            whereQuery = "";
          }
          if (!isExistRecord && true && model.getProvidingType() != null) {

            andCondition = " AND ";
            if (isOpenParentheses) {
              andCondition = " ";
            }
            query += " ";
            if (isDelete && fetchType == FetchType.EAGER) {
              whereQuery = " MaterialOfProject0.providingType = ? ";
              counter++;
            } else {
              whereQuery =
                  " MaterialOfProject0.providingType = :MaterialOfProject0providingType" + j + " ";
            }

            isOpenParentheses = false;
            whereValue = model.getProvidingType();
            query +=
                (andCondition
                    + Facade.getStatement(
                        statement,
                        "MaterialOfProject0.providingType",
                        "MaterialOfProject0providingType" + j,
                        mapVariable,
                        whereQuery,
                        whereValue,
                        isExistRecord,
                        isDelete,
                        fetchType,
                        counter));
            whereQuery = "";
          }
          if (!isExistRecord && true && model.getIntPurchasePr() != null) {

            andCondition = " AND ";
            if (isOpenParentheses) {
              andCondition = " ";
            }
            query += " ";
            if (isDelete && fetchType == FetchType.EAGER) {
              whereQuery = " MaterialOfProject0.intPurchasePr = ? ";
              counter++;
            } else {
              whereQuery =
                  " MaterialOfProject0.intPurchasePr = :MaterialOfProject0intPurchasePr" + j + " ";
            }

            isOpenParentheses = false;
            whereValue = model.getIntPurchasePr();
            query +=
                (andCondition
                    + Facade.getStatement(
                        statement,
                        "MaterialOfProject0.intPurchasePr",
                        "MaterialOfProject0intPurchasePr" + j,
                        mapVariable,
                        whereQuery,
                        whereValue,
                        isExistRecord,
                        isDelete,
                        fetchType,
                        counter));
            whereQuery = "";
          }
          if (!isExistRecord && true && model.getExPurchasePr() != null) {

            andCondition = " AND ";
            if (isOpenParentheses) {
              andCondition = " ";
            }
            query += " ";
            if (isDelete && fetchType == FetchType.EAGER) {
              whereQuery = " MaterialOfProject0.exPurchasePr = ? ";
              counter++;
            } else {
              whereQuery =
                  " MaterialOfProject0.exPurchasePr = :MaterialOfProject0exPurchasePr" + j + " ";
            }

            isOpenParentheses = false;
            whereValue = model.getExPurchasePr();
            query +=
                (andCondition
                    + Facade.getStatement(
                        statement,
                        "MaterialOfProject0.exPurchasePr",
                        "MaterialOfProject0exPurchasePr" + j,
                        mapVariable,
                        whereQuery,
                        whereValue,
                        isExistRecord,
                        isDelete,
                        fetchType,
                        counter));
            whereQuery = "";
          }
          if (!isExistRecord && true && model.getDescription() != null) {

            andCondition = " AND ";
            if (isOpenParentheses) {
              andCondition = " ";
            }
            query += " ";
            if (isDelete && fetchType == FetchType.EAGER) {
              whereQuery = " MaterialOfProject0.description = ? ";
              counter++;
            } else {
              whereQuery =
                  " MaterialOfProject0.description = :MaterialOfProject0description" + j + " ";
            }

            isOpenParentheses = false;
            whereValue = model.getDescription();
            query +=
                (andCondition
                    + Facade.getStatement(
                        statement,
                        "MaterialOfProject0.description",
                        "MaterialOfProject0description" + j,
                        mapVariable,
                        whereQuery,
                        whereValue,
                        isExistRecord,
                        isDelete,
                        fetchType,
                        counter));
            whereQuery = "";
          }
          if (!isExistRecord && true && model.getEvaluationDate() != null) {

            andCondition = " AND ";
            if (isOpenParentheses) {
              andCondition = " ";
            }
            query += " ";
            if (isDelete && fetchType == FetchType.EAGER) {
              whereQuery = " MaterialOfProject0.evaluationDate = ? ";
              counter++;
            } else {
              whereQuery =
                  " MaterialOfProject0.evaluationDate = :MaterialOfProject0evaluationDate"
                      + j
                      + " ";
            }

            isOpenParentheses = false;
            whereValue = model.getEvaluationDate();
            query +=
                (andCondition
                    + Facade.getStatement(
                        statement,
                        "MaterialOfProject0.evaluationDate",
                        "MaterialOfProject0evaluationDate" + j,
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
              whereQuery = " MaterialOfProject0.VERSION = ? ";
              counter++;
            } else {
              whereQuery = " MaterialOfProject0.version = :MaterialOfProject0version" + j + " ";
            }

            isOpenParentheses = false;
            whereValue = new java.lang.Integer("" + model.getVersion());
            query +=
                (andCondition
                    + Facade.getStatement(
                        statement,
                        "MaterialOfProject0.version",
                        "MaterialOfProject0version" + j,
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
          && (!select.trim().equals("MaterialOfProject0") && select.indexOf("new") == -1)) {
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
