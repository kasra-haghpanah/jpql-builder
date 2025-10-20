package com.tosan.bpms.framework.orm.repository.jpql;

import com.jpql.api.interfaces.*;
import com.jpql.api.interfaces.Parameter;
import javax.persistence.FetchType;
import javax.persistence.EntityManager;
/**
 * Created by kasra.haghpanah on 1/05/2019.
 */
public class ProductsOfProjectcomtosanbpmsmodelsqlDAO {

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

        com.tosan.bpms.model.sql.ProductsOfProject model =
            (com.tosan.bpms.model.sql.ProductsOfProject) fetchIterator.next();

        if (model != null) {

          String key = model.getProductsOfProjectId() + "";
          existMap.put(key, model);
        }
      }
    }

    java.util.Iterator iterator = models.iterator();

    while (iterator.hasNext()) {
      com.tosan.bpms.model.sql.ProductsOfProject model =
          (com.tosan.bpms.model.sql.ProductsOfProject) iterator.next();

      if (model != null) {
        String key = model.getProductsOfProjectId() + "";
        com.tosan.bpms.model.sql.ProductsOfProject fetchModel =
            (com.tosan.bpms.model.sql.ProductsOfProject) existMap.get(key);

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
    com.tosan.bpms.model.sql.ProductsOfProject model = null;
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
    java.lang.String select = "SELECT ProductsOfProject0";
    if (selectParam != null && !selectParam.equals("")) {
      select = selectParam;
    }
    if (!isExistRecord && !isDelete) {
      if (fetchType == FetchType.LAZY) {
        basicQuery =
            select
                + " FROM com.tosan.bpms.model.sql.ProductsOfProject ProductsOfProject0  JOIN "
                + fetch
                + " ProductsOfProject0.article Article1 JOIN "
                + fetch
                + " Article1.industry Industry2 JOIN "
                + fetch
                + " Article1.economic Economic3 JOIN "
                + fetch
                + " Article1.measurement Measurment4 JOIN "
                + fetch
                + " ProductsOfProject0.portfolioLicense PortfolioLicense5 JOIN "
                + fetch
                + " PortfolioLicense5.portfolio Portfolio6 LEFT JOIN "
                + fetch
                + " PortfolioLicense5.file File24 WHERE 1=1 ";
      } else if (fetchType == FetchType.EAGER) {
        basicQuery =
            select
                + " FROM com.tosan.bpms.model.sql.ProductsOfProject ProductsOfProject0  JOIN "
                + fetch
                + " ProductsOfProject0.article Article1 JOIN "
                + fetch
                + " Article1.industry Industry2 JOIN "
                + fetch
                + " Article1.economic Economic3 JOIN "
                + fetch
                + " Article1.measurement Measurment4 JOIN "
                + fetch
                + " ProductsOfProject0.portfolioLicense PortfolioLicense5 JOIN "
                + fetch
                + " PortfolioLicense5.portfolio Portfolio6 JOIN "
                + fetch
                + " Portfolio6.portfolioLicenses PortfolioLicense7 LEFT JOIN "
                + fetch
                + " PortfolioLicense7.file File9 JOIN "
                + fetch
                + " PortfolioLicense7.licenseDetails LicenseDetails10 LEFT JOIN "
                + fetch
                + " LicenseDetails10.file File12 JOIN "
                + fetch
                + " Portfolio6.portfolioLocations PortfolioLocation13 LEFT JOIN "
                + fetch
                + " PortfolioLocation13.file File15 JOIN "
                + fetch
                + " PortfolioLocation13.locationDetails LocationDetails16 JOIN "
                + fetch
                + " LocationDetails16.portfolioLocation PortfolioLocation17 LEFT JOIN "
                + fetch
                + " PortfolioLocation17.file File19 LEFT JOIN "
                + fetch
                + " LocationDetails16.file File21 JOIN "
                + fetch
                + " Portfolio6.receptions Reception22 LEFT JOIN "
                + fetch
                + " PortfolioLicense5.file File24 JOIN "
                + fetch
                + " PortfolioLicense5.licenseDetails LicenseDetails25 LEFT JOIN "
                + fetch
                + " LicenseDetails25.file File27 WHERE 1=1 ";
      }
    } else if (isExistRecord) {
      basicQuery =
          "SELECT ProductsOfProject0 FROM com.tosan.bpms.model.sql.ProductsOfProject ProductsOfProject0 WHERE 1=1 ";
    } else if (isDelete) {
      if (fetchType == FetchType.LAZY) {
        basicQuery =
            "DELETE FROM com.tosan.bpms.model.sql.ProductsOfProject ProductsOfProject0 WHERE 1=1 ";
      } else if (fetchType == FetchType.EAGER) {
        basicQuery =
            "DELETE ProductsOfProject0 FROM ProductsOfProject ProductsOfProject0 WHERE 1=1 ";
      }
    }
    int modelsLength = models.size();
    java.util.Iterator modelsIterator = models.iterator();
    int j = 0;
    if (modelsLength > 0) {
      while (modelsIterator.hasNext()) {
        model = (com.tosan.bpms.model.sql.ProductsOfProject) modelsIterator.next();
        query = "";
        isOpenParentheses = true;

        if (model != null) {

          if (isExistRecord == true && model.getProductsOfProjectId() == null) {
            model.setProductsOfProjectId(new java.lang.Integer("-1"));
            isChangeKey = true;
          }

          if (model.getProductsOfProjectId() != null) {

            andCondition = " AND ";
            if (isOpenParentheses) {
              andCondition = " ";
            }
            query += " ";
            if (isDelete && fetchType == FetchType.EAGER) {
              whereQuery = " ProductsOfProject0.productsOfProjectId = ? ";
              counter++;
            } else {
              whereQuery =
                  " ProductsOfProject0.productsOfProjectId = :ProductsOfProject0productsOfProjectId"
                      + j
                      + " ";
            }

            isOpenParentheses = false;
            whereValue = model.getProductsOfProjectId();
            if (isExistRecord && isChangeKey) {
              model.setProductsOfProjectId(null);
              isChangeKey = false;
            }

            query +=
                (andCondition
                    + Facade.getStatement(
                        statement,
                        "ProductsOfProject0.productsOfProjectId",
                        "ProductsOfProject0productsOfProjectId" + j,
                        mapVariable,
                        whereQuery,
                        whereValue,
                        isExistRecord,
                        isDelete,
                        fetchType,
                        counter));
            whereQuery = "";
          }
          if (!isExistRecord && !isDelete && model.getArticle() != null) {

            if (model.getArticle().getArticleId() != null) {

              andCondition = " AND ";
              if (isOpenParentheses) {
                andCondition = " ";
              }
              query += " ";
              if (isDelete && fetchType == FetchType.EAGER) {
                whereQuery = " Article1.id = ? ";
                counter++;
              } else {
                whereQuery = " Article1.articleId = :Article1articleId" + j + " ";
              }

              isOpenParentheses = false;
              whereValue = model.getArticle().getArticleId();
              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "Article1.articleId",
                          "Article1articleId" + j,
                          mapVariable,
                          whereQuery,
                          whereValue,
                          isExistRecord,
                          isDelete,
                          fetchType,
                          counter));
              whereQuery = "";
            }
            if (!isExistRecord && !isDelete && model.getArticle().getIndustry() != null) {

              if (model.getArticle().getIndustry().getIndustryId() != null) {

                andCondition = " AND ";
                if (isOpenParentheses) {
                  andCondition = " ";
                }
                query += " ";
                if (isExistRecord || isDelete) {
                  if (isDelete && fetchType == FetchType.EAGER) {
                    whereQuery = " Industry2.id = ? ";
                    counter++;
                  } else {
                    whereQuery = " Industry2.industryId = :Industry2industryId" + j + " ";
                  }
                } else {

                  whereQuery = " Industry2.industryId LIKE :Industry2industryId" + j + " ";
                }

                isOpenParentheses = false;
                if (isExistRecord || isDelete) {
                  if (isDelete && fetchType == FetchType.EAGER) {
                    whereQuery = " Industry2.id = ? ";
                    counter++;
                  } else {
                    whereValue = model.getArticle().getIndustry().getIndustryId();
                  }
                } else {
                  whereValue = "%" + model.getArticle().getIndustry().getIndustryId() + "%";
                }

                query +=
                    (andCondition
                        + Facade.getStatement(
                            statement,
                            "Industry2.industryId",
                            "Industry2industryId" + j,
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
                  && model.getArticle().getIndustry().getFaTitle() != null) {

                andCondition = " AND ";
                if (isOpenParentheses) {
                  andCondition = " ";
                }
                query += " ";
                if (isDelete && fetchType == FetchType.EAGER) {
                  whereQuery = " Industry2.faTitle = ? ";
                  counter++;
                } else {
                  whereQuery = " Industry2.faTitle = :Industry2faTitle" + j + " ";
                }

                isOpenParentheses = false;
                whereValue = model.getArticle().getIndustry().getFaTitle();
                query +=
                    (andCondition
                        + Facade.getStatement(
                            statement,
                            "Industry2.faTitle",
                            "Industry2faTitle" + j,
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
                  && model.getArticle().getIndustry().getEnTitle() != null) {

                andCondition = " AND ";
                if (isOpenParentheses) {
                  andCondition = " ";
                }
                query += " ";
                if (isDelete && fetchType == FetchType.EAGER) {
                  whereQuery = " Industry2.enTitle = ? ";
                  counter++;
                } else {
                  whereQuery = " Industry2.enTitle = :Industry2enTitle" + j + " ";
                }

                isOpenParentheses = false;
                whereValue = model.getArticle().getIndustry().getEnTitle();
                query +=
                    (andCondition
                        + Facade.getStatement(
                            statement,
                            "Industry2.enTitle",
                            "Industry2enTitle" + j,
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
                  && model.getArticle().getIndustry().getVersion() != 0) {

                andCondition = " AND ";
                if (isOpenParentheses) {
                  andCondition = " ";
                }
                query += " ";
                if (isDelete && fetchType == FetchType.EAGER) {
                  whereQuery = " Industry2.VERSION = ? ";
                  counter++;
                } else {
                  whereQuery = " Industry2.version = :Industry2version" + j + " ";
                }

                isOpenParentheses = false;
                whereValue =
                    new java.lang.Integer("" + model.getArticle().getIndustry().getVersion());
                query +=
                    (andCondition
                        + Facade.getStatement(
                            statement,
                            "Industry2.version",
                            "Industry2version" + j,
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
            if (!isExistRecord && !isDelete && model.getArticle().getEconomic() != null) {

              if (model.getArticle().getEconomic().getEconomicId() != null) {

                andCondition = " AND ";
                if (isOpenParentheses) {
                  andCondition = " ";
                }
                query += " ";
                if (isDelete && fetchType == FetchType.EAGER) {
                  whereQuery = " Economic3.id = ? ";
                  counter++;
                } else {
                  whereQuery = " Economic3.economicId = :Economic3economicId" + j + " ";
                }

                isOpenParentheses = false;
                whereValue = model.getArticle().getEconomic().getEconomicId();
                query +=
                    (andCondition
                        + Facade.getStatement(
                            statement,
                            "Economic3.economicId",
                            "Economic3economicId" + j,
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
                  && model.getArticle().getEconomic().getFaTitle() != null) {

                andCondition = " AND ";
                if (isOpenParentheses) {
                  andCondition = " ";
                }
                query += " ";
                if (isDelete && fetchType == FetchType.EAGER) {
                  whereQuery = " Economic3.faTitle = ? ";
                  counter++;
                } else {
                  whereQuery = " Economic3.faTitle = :Economic3faTitle" + j + " ";
                }

                isOpenParentheses = false;
                whereValue = model.getArticle().getEconomic().getFaTitle();
                query +=
                    (andCondition
                        + Facade.getStatement(
                            statement,
                            "Economic3.faTitle",
                            "Economic3faTitle" + j,
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
                  && model.getArticle().getEconomic().getEnTitle() != null) {

                andCondition = " AND ";
                if (isOpenParentheses) {
                  andCondition = " ";
                }
                query += " ";
                if (isDelete && fetchType == FetchType.EAGER) {
                  whereQuery = " Economic3.enTitle = ? ";
                  counter++;
                } else {
                  whereQuery = " Economic3.enTitle = :Economic3enTitle" + j + " ";
                }

                isOpenParentheses = false;
                whereValue = model.getArticle().getEconomic().getEnTitle();
                query +=
                    (andCondition
                        + Facade.getStatement(
                            statement,
                            "Economic3.enTitle",
                            "Economic3enTitle" + j,
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
                  && model.getArticle().getEconomic().getVersion() != 0) {

                andCondition = " AND ";
                if (isOpenParentheses) {
                  andCondition = " ";
                }
                query += " ";
                if (isDelete && fetchType == FetchType.EAGER) {
                  whereQuery = " Economic3.VERSION = ? ";
                  counter++;
                } else {
                  whereQuery = " Economic3.version = :Economic3version" + j + " ";
                }

                isOpenParentheses = false;
                whereValue =
                    new java.lang.Integer("" + model.getArticle().getEconomic().getVersion());
                query +=
                    (andCondition
                        + Facade.getStatement(
                            statement,
                            "Economic3.version",
                            "Economic3version" + j,
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
            if (!isExistRecord && !isDelete && model.getArticle().getMeasurement() != null) {

              if (model.getArticle().getMeasurement().getMeasurementId() != null) {

                andCondition = " AND ";
                if (isOpenParentheses) {
                  andCondition = " ";
                }
                query += " ";
                if (isDelete && fetchType == FetchType.EAGER) {
                  whereQuery = " Measurment4.id = ? ";
                  counter++;
                } else {
                  whereQuery = " Measurment4.measurementId = :Measurment4measurementId" + j + " ";
                }

                isOpenParentheses = false;
                whereValue = model.getArticle().getMeasurement().getMeasurementId();
                query +=
                    (andCondition
                        + Facade.getStatement(
                            statement,
                            "Measurment4.measurementId",
                            "Measurment4measurementId" + j,
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
                  && model.getArticle().getMeasurement().getName() != null) {

                andCondition = " AND ";
                if (isOpenParentheses) {
                  andCondition = " ";
                }
                query += " ";
                if (isDelete && fetchType == FetchType.EAGER) {
                  whereQuery = " Measurment4.name = ? ";
                  counter++;
                } else {
                  whereQuery = " Measurment4.name = :Measurment4name" + j + " ";
                }

                isOpenParentheses = false;
                whereValue = model.getArticle().getMeasurement().getName();
                query +=
                    (andCondition
                        + Facade.getStatement(
                            statement,
                            "Measurment4.name",
                            "Measurment4name" + j,
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
                  && model.getArticle().getMeasurement().getVersion() != 0) {

                andCondition = " AND ";
                if (isOpenParentheses) {
                  andCondition = " ";
                }
                query += " ";
                if (isDelete && fetchType == FetchType.EAGER) {
                  whereQuery = " Measurment4.VERSION = ? ";
                  counter++;
                } else {
                  whereQuery = " Measurment4.version = :Measurment4version" + j + " ";
                }

                isOpenParentheses = false;
                whereValue =
                    new java.lang.Integer("" + model.getArticle().getMeasurement().getVersion());
                query +=
                    (andCondition
                        + Facade.getStatement(
                            statement,
                            "Measurment4.version",
                            "Measurment4version" + j,
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
            if (!isExistRecord && !isDelete && true && model.getArticle().getFaTitle() != null) {

              andCondition = " AND ";
              if (isOpenParentheses) {
                andCondition = " ";
              }
              query += " ";
              if (isDelete && fetchType == FetchType.EAGER) {
                whereQuery = " Article1.faTitle = ? ";
                counter++;
              } else {
                whereQuery = " Article1.faTitle = :Article1faTitle" + j + " ";
              }

              isOpenParentheses = false;
              whereValue = model.getArticle().getFaTitle();
              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "Article1.faTitle",
                          "Article1faTitle" + j,
                          mapVariable,
                          whereQuery,
                          whereValue,
                          isExistRecord,
                          isDelete,
                          fetchType,
                          counter));
              whereQuery = "";
            }
            if (!isExistRecord && !isDelete && true && model.getArticle().getEnTitle() != null) {

              andCondition = " AND ";
              if (isOpenParentheses) {
                andCondition = " ";
              }
              query += " ";
              if (isDelete && fetchType == FetchType.EAGER) {
                whereQuery = " Article1.enTitle = ? ";
                counter++;
              } else {
                whereQuery = " Article1.enTitle = :Article1enTitle" + j + " ";
              }

              isOpenParentheses = false;
              whereValue = model.getArticle().getEnTitle();
              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "Article1.enTitle",
                          "Article1enTitle" + j,
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
                && model.getArticle().getCustomsTariffCode() != null) {

              andCondition = " AND ";
              if (isOpenParentheses) {
                andCondition = " ";
              }
              query += " ";
              if (isDelete && fetchType == FetchType.EAGER) {
                whereQuery = " Article1.customsTariffCode = ? ";
                counter++;
              } else {
                whereQuery = " Article1.customsTariffCode = :Article1customsTariffCode" + j + " ";
              }

              isOpenParentheses = false;
              whereValue = model.getArticle().getCustomsTariffCode();
              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "Article1.customsTariffCode",
                          "Article1customsTariffCode" + j,
                          mapVariable,
                          whereQuery,
                          whereValue,
                          isExistRecord,
                          isDelete,
                          fetchType,
                          counter));
              whereQuery = "";
            }
            if (!isExistRecord && !isDelete && true && model.getArticle().getISICCode() != null) {

              andCondition = " AND ";
              if (isOpenParentheses) {
                andCondition = " ";
              }
              query += " ";
              if (isDelete && fetchType == FetchType.EAGER) {
                whereQuery = " Article1.iSICCode = ? ";
                counter++;
              } else {
                whereQuery = " Article1.iSICCode = :Article1iSICCode" + j + " ";
              }

              isOpenParentheses = false;
              whereValue = model.getArticle().getISICCode();
              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "Article1.iSICCode",
                          "Article1iSICCode" + j,
                          mapVariable,
                          whereQuery,
                          whereValue,
                          isExistRecord,
                          isDelete,
                          fetchType,
                          counter));
              whereQuery = "";
            }
            if (!isExistRecord && !isDelete && true && model.getArticle().getVersion() != 0) {

              andCondition = " AND ";
              if (isOpenParentheses) {
                andCondition = " ";
              }
              query += " ";
              if (isDelete && fetchType == FetchType.EAGER) {
                whereQuery = " Article1.VERSION = ? ";
                counter++;
              } else {
                whereQuery = " Article1.version = :Article1version" + j + " ";
              }

              isOpenParentheses = false;
              whereValue = new java.lang.Integer("" + model.getArticle().getVersion());
              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "Article1.version",
                          "Article1version" + j,
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
          if (!isExistRecord && !isDelete && model.getPortfolioLicense() != null) {

            if (model.getPortfolioLicense().getPortfolioLicenseId() != null) {

              andCondition = " AND ";
              if (isOpenParentheses) {
                andCondition = " ";
              }
              query += " ";
              if (isDelete && fetchType == FetchType.EAGER) {
                whereQuery = " PortfolioLicense5.id = ? ";
                counter++;
              } else {
                whereQuery =
                    " PortfolioLicense5.portfolioLicenseId = :PortfolioLicense5portfolioLicenseId"
                        + j
                        + " ";
              }

              isOpenParentheses = false;
              whereValue = model.getPortfolioLicense().getPortfolioLicenseId();
              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "PortfolioLicense5.portfolioLicenseId",
                          "PortfolioLicense5portfolioLicenseId" + j,
                          mapVariable,
                          whereQuery,
                          whereValue,
                          isExistRecord,
                          isDelete,
                          fetchType,
                          counter));
              whereQuery = "";
            }
            if (!isExistRecord && !isDelete && model.getPortfolioLicense().getPortfolio() != null) {

              if (model.getPortfolioLicense().getPortfolio().getPortfolioId() != null) {

                andCondition = " AND ";
                if (isOpenParentheses) {
                  andCondition = " ";
                }
                query += " ";
                if (isDelete && fetchType == FetchType.EAGER) {
                  whereQuery = " Portfolio6.portfolioId = ? ";
                  counter++;
                } else {
                  whereQuery = " Portfolio6.portfolioId = :Portfolio6portfolioId" + j + " ";
                }

                isOpenParentheses = false;
                whereValue = model.getPortfolioLicense().getPortfolio().getPortfolioId();
                query +=
                    (andCondition
                        + Facade.getStatement(
                            statement,
                            "Portfolio6.portfolioId",
                            "Portfolio6portfolioId" + j,
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
                  && model.getPortfolioLicense().getPortfolio().getPortfolioLicenses() != null) {
                int length7 =
                    model.getPortfolioLicense().getPortfolio().getPortfolioLicenses().size();

                if (length7 > 0) {
                  if (isOpenParentheses) {
                    query += "  ( ";
                  } else {
                    query += " AND ( ";
                  }
                  isOpenParentheses = true;

                  for (int i7 = 0; i7 < length7; i7++) {

                    java.lang.String beforeCondition7 = "";
                    java.lang.String prefix7 = " OR ";
                    if (i7 == 0) {
                      prefix7 = "";
                    }
                    if (length7 > 1) {

                      query += (prefix7 + "(");
                      isOpenParentheses = true;

                    } else {

                      query += (prefix7);
                      if (!prefix7.trim().equals("")) {
                        isOpenParentheses = false;
                      }
                    }

                    if (model
                            .getPortfolioLicense()
                            .getPortfolio()
                            .getPortfolioLicenses()
                            .get(i7)
                            .getPortfolioLicenseId()
                        != null) {

                      andCondition = " AND ";
                      if (isOpenParentheses) {
                        andCondition = " ";
                      }
                      query += " ";
                      whereValue =
                          model
                              .getPortfolioLicense()
                              .getPortfolio()
                              .getPortfolioLicenses()
                              .get(i7)
                              .getPortfolioLicenseId();
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " PortfolioLicense7.id = ? ";
                        counter++;
                      } else {
                        whereQuery =
                            " PortfolioLicense7.portfolioLicenseId = :PortfolioLicense7portfolioLicenseId"
                                + j
                                + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          model
                              .getPortfolioLicense()
                              .getPortfolio()
                              .getPortfolioLicenses()
                              .get(i7)
                              .getPortfolioLicenseId();
                      query +=
                          (andCondition
                              + Facade.getStatement(
                                  statement,
                                  "PortfolioLicense7.portfolioLicenseId",
                                  "PortfolioLicense7portfolioLicenseId" + j,
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
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getPortfolioLicenses()
                                .get(i7)
                                .getFile()
                            != null) {

                      if (model
                              .getPortfolioLicense()
                              .getPortfolio()
                              .getPortfolioLicenses()
                              .get(i7)
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
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getPortfolioLicenses()
                                .get(i7)
                                .getFile()
                                .getId();
                        if (isDelete && fetchType == FetchType.EAGER) {
                          whereQuery = " File9.l4610flmtdataid = ? ";
                          counter++;
                        } else {
                          whereQuery = " File9.id = :File9id" + j + " ";
                        }

                        isOpenParentheses = false;
                        whereValue =
                            model
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getPortfolioLicenses()
                                .get(i7)
                                .getFile()
                                .getId();
                        query +=
                            (andCondition
                                + Facade.getStatement(
                                    statement,
                                    "File9.id",
                                    "File9id" + j,
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
                                  .getPortfolioLicense()
                                  .getPortfolio()
                                  .getPortfolioLicenses()
                                  .get(i7)
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
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getPortfolioLicenses()
                                .get(i7)
                                .getFile()
                                .getExtension();
                        if (isDelete && fetchType == FetchType.EAGER) {
                          whereQuery = " File9.l4610extension = ? ";
                          counter++;
                        } else {
                          whereQuery = " File9.extension = :File9extension" + j + " ";
                        }

                        isOpenParentheses = false;
                        whereValue =
                            model
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getPortfolioLicenses()
                                .get(i7)
                                .getFile()
                                .getExtension();
                        query +=
                            (andCondition
                                + Facade.getStatement(
                                    statement,
                                    "File9.extension",
                                    "File9extension" + j,
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
                                  .getPortfolioLicense()
                                  .getPortfolio()
                                  .getPortfolioLicenses()
                                  .get(i7)
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
                                        .getPortfolioLicense()
                                        .getPortfolio()
                                        .getPortfolioLicenses()
                                        .get(i7)
                                        .getFile()
                                        .getVersion());
                        if (isDelete && fetchType == FetchType.EAGER) {
                          whereQuery = " File9.VERSION = ? ";
                          counter++;
                        } else {
                          whereQuery = " File9.version = :File9version" + j + " ";
                        }

                        isOpenParentheses = false;
                        whereValue =
                            new java.lang.Integer(
                                ""
                                    + model
                                        .getPortfolioLicense()
                                        .getPortfolio()
                                        .getPortfolioLicenses()
                                        .get(i7)
                                        .getFile()
                                        .getVersion());
                        query +=
                            (andCondition
                                + Facade.getStatement(
                                    statement,
                                    "File9.version",
                                    "File9version" + j,
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
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getPortfolioLicenses()
                                .get(i7)
                                .getLicenseDetails()
                            != null) {
                      int length10 =
                          model
                              .getPortfolioLicense()
                              .getPortfolio()
                              .getPortfolioLicenses()
                              .get(i7)
                              .getLicenseDetails()
                              .size();

                      if (length10 > 0) {
                        if (isOpenParentheses) {
                          query += "  ( ";
                        } else {
                          query += " AND ( ";
                        }
                        isOpenParentheses = true;

                        for (int i10 = 0; i10 < length10; i10++) {

                          java.lang.String beforeCondition10 = "";
                          java.lang.String prefix10 = " OR ";
                          if (i10 == 0) {
                            prefix10 = "";
                          }
                          if (length10 > 1) {

                            query += (prefix10 + "(");
                            isOpenParentheses = true;

                          } else {

                            query += (prefix10);
                            if (!prefix10.trim().equals("")) {
                              isOpenParentheses = false;
                            }
                          }

                          if (model
                                  .getPortfolioLicense()
                                  .getPortfolio()
                                  .getPortfolioLicenses()
                                  .get(i7)
                                  .getLicenseDetails()
                                  .get(i10)
                                  .getLicenseDetailId()
                              != null) {

                            andCondition = " AND ";
                            if (isOpenParentheses) {
                              andCondition = " ";
                            }
                            query += " ";
                            whereValue =
                                model
                                    .getPortfolioLicense()
                                    .getPortfolio()
                                    .getPortfolioLicenses()
                                    .get(i7)
                                    .getLicenseDetails()
                                    .get(i10)
                                    .getLicenseDetailId();
                            if (isDelete && fetchType == FetchType.EAGER) {
                              whereQuery = " LicenseDetails10.id = ? ";
                              counter++;
                            } else {
                              whereQuery =
                                  " LicenseDetails10.licenseDetailId = :LicenseDetails10licenseDetailId"
                                      + j
                                      + " ";
                            }

                            isOpenParentheses = false;
                            whereValue =
                                model
                                    .getPortfolioLicense()
                                    .getPortfolio()
                                    .getPortfolioLicenses()
                                    .get(i7)
                                    .getLicenseDetails()
                                    .get(i10)
                                    .getLicenseDetailId();
                            query +=
                                (andCondition
                                    + Facade.getStatement(
                                        statement,
                                        "LicenseDetails10.licenseDetailId",
                                        "LicenseDetails10licenseDetailId" + j,
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
                                      .getPortfolioLicense()
                                      .getPortfolio()
                                      .getPortfolioLicenses()
                                      .get(i7)
                                      .getLicenseDetails()
                                      .get(i10)
                                      .getFile()
                                  != null) {

                            if (model
                                    .getPortfolioLicense()
                                    .getPortfolio()
                                    .getPortfolioLicenses()
                                    .get(i7)
                                    .getLicenseDetails()
                                    .get(i10)
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
                                      .getPortfolioLicense()
                                      .getPortfolio()
                                      .getPortfolioLicenses()
                                      .get(i7)
                                      .getLicenseDetails()
                                      .get(i10)
                                      .getFile()
                                      .getId();
                              if (isDelete && fetchType == FetchType.EAGER) {
                                whereQuery = " File12.l4610flmtdataid = ? ";
                                counter++;
                              } else {
                                whereQuery = " File12.id = :File12id" + j + " ";
                              }

                              isOpenParentheses = false;
                              whereValue =
                                  model
                                      .getPortfolioLicense()
                                      .getPortfolio()
                                      .getPortfolioLicenses()
                                      .get(i7)
                                      .getLicenseDetails()
                                      .get(i10)
                                      .getFile()
                                      .getId();
                              query +=
                                  (andCondition
                                      + Facade.getStatement(
                                          statement,
                                          "File12.id",
                                          "File12id" + j,
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
                                        .getPortfolioLicense()
                                        .getPortfolio()
                                        .getPortfolioLicenses()
                                        .get(i7)
                                        .getLicenseDetails()
                                        .get(i10)
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
                                      .getPortfolioLicense()
                                      .getPortfolio()
                                      .getPortfolioLicenses()
                                      .get(i7)
                                      .getLicenseDetails()
                                      .get(i10)
                                      .getFile()
                                      .getExtension();
                              if (isDelete && fetchType == FetchType.EAGER) {
                                whereQuery = " File12.l4610extension = ? ";
                                counter++;
                              } else {
                                whereQuery = " File12.extension = :File12extension" + j + " ";
                              }

                              isOpenParentheses = false;
                              whereValue =
                                  model
                                      .getPortfolioLicense()
                                      .getPortfolio()
                                      .getPortfolioLicenses()
                                      .get(i7)
                                      .getLicenseDetails()
                                      .get(i10)
                                      .getFile()
                                      .getExtension();
                              query +=
                                  (andCondition
                                      + Facade.getStatement(
                                          statement,
                                          "File12.extension",
                                          "File12extension" + j,
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
                                        .getPortfolioLicense()
                                        .getPortfolio()
                                        .getPortfolioLicenses()
                                        .get(i7)
                                        .getLicenseDetails()
                                        .get(i10)
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
                                              .getPortfolioLicense()
                                              .getPortfolio()
                                              .getPortfolioLicenses()
                                              .get(i7)
                                              .getLicenseDetails()
                                              .get(i10)
                                              .getFile()
                                              .getVersion());
                              if (isDelete && fetchType == FetchType.EAGER) {
                                whereQuery = " File12.VERSION = ? ";
                                counter++;
                              } else {
                                whereQuery = " File12.version = :File12version" + j + " ";
                              }

                              isOpenParentheses = false;
                              whereValue =
                                  new java.lang.Integer(
                                      ""
                                          + model
                                              .getPortfolioLicense()
                                              .getPortfolio()
                                              .getPortfolioLicenses()
                                              .get(i7)
                                              .getLicenseDetails()
                                              .get(i10)
                                              .getFile()
                                              .getVersion());
                              query +=
                                  (andCondition
                                      + Facade.getStatement(
                                          statement,
                                          "File12.version",
                                          "File12version" + j,
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
                                      .getPortfolioLicense()
                                      .getPortfolio()
                                      .getPortfolioLicenses()
                                      .get(i7)
                                      .getLicenseDetails()
                                      .get(i10)
                                      .getAddress()
                                  != null) {
                            if (!isDelete
                                && true
                                && model
                                        .getPortfolioLicense()
                                        .getPortfolio()
                                        .getPortfolioLicenses()
                                        .get(i7)
                                        .getLicenseDetails()
                                        .get(i10)
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
                                      .getPortfolioLicense()
                                      .getPortfolio()
                                      .getPortfolioLicenses()
                                      .get(i7)
                                      .getLicenseDetails()
                                      .get(i10)
                                      .getAddress()
                                      .getAddressLine1();
                              if (isDelete && fetchType == FetchType.EAGER) {
                                whereQuery = " LicenseDetails10.addressLine1 = ? ";
                                counter++;
                              } else {
                                whereQuery =
                                    " LicenseDetails10.address.addressLine1 = :LicenseDetails10addressaddressLine1"
                                        + j
                                        + " ";
                              }

                              isOpenParentheses = false;
                              whereValue =
                                  model
                                      .getPortfolioLicense()
                                      .getPortfolio()
                                      .getPortfolioLicenses()
                                      .get(i7)
                                      .getLicenseDetails()
                                      .get(i10)
                                      .getAddress()
                                      .getAddressLine1();
                              query +=
                                  (andCondition
                                      + Facade.getStatement(
                                          statement,
                                          "LicenseDetails10.address.addressLine1",
                                          "LicenseDetails10addressaddressLine1" + j,
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
                                        .getPortfolioLicense()
                                        .getPortfolio()
                                        .getPortfolioLicenses()
                                        .get(i7)
                                        .getLicenseDetails()
                                        .get(i10)
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
                                      .getPortfolioLicense()
                                      .getPortfolio()
                                      .getPortfolioLicenses()
                                      .get(i7)
                                      .getLicenseDetails()
                                      .get(i10)
                                      .getAddress()
                                      .getAddressLine2();
                              if (isDelete && fetchType == FetchType.EAGER) {
                                whereQuery = " LicenseDetails10.addressLine2 = ? ";
                                counter++;
                              } else {
                                whereQuery =
                                    " LicenseDetails10.address.addressLine2 = :LicenseDetails10addressaddressLine2"
                                        + j
                                        + " ";
                              }

                              isOpenParentheses = false;
                              whereValue =
                                  model
                                      .getPortfolioLicense()
                                      .getPortfolio()
                                      .getPortfolioLicenses()
                                      .get(i7)
                                      .getLicenseDetails()
                                      .get(i10)
                                      .getAddress()
                                      .getAddressLine2();
                              query +=
                                  (andCondition
                                      + Facade.getStatement(
                                          statement,
                                          "LicenseDetails10.address.addressLine2",
                                          "LicenseDetails10addressaddressLine2" + j,
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
                                        .getPortfolioLicense()
                                        .getPortfolio()
                                        .getPortfolioLicenses()
                                        .get(i7)
                                        .getLicenseDetails()
                                        .get(i10)
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
                                      .getPortfolioLicense()
                                      .getPortfolio()
                                      .getPortfolioLicenses()
                                      .get(i7)
                                      .getLicenseDetails()
                                      .get(i10)
                                      .getAddress()
                                      .getCity();
                              if (isDelete && fetchType == FetchType.EAGER) {
                                whereQuery = " LicenseDetails10.city = ? ";
                                counter++;
                              } else {
                                whereQuery =
                                    " LicenseDetails10.address.city = :LicenseDetails10addresscity"
                                        + j
                                        + " ";
                              }

                              isOpenParentheses = false;
                              whereValue =
                                  model
                                      .getPortfolioLicense()
                                      .getPortfolio()
                                      .getPortfolioLicenses()
                                      .get(i7)
                                      .getLicenseDetails()
                                      .get(i10)
                                      .getAddress()
                                      .getCity();
                              query +=
                                  (andCondition
                                      + Facade.getStatement(
                                          statement,
                                          "LicenseDetails10.address.city",
                                          "LicenseDetails10addresscity" + j,
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
                                        .getPortfolioLicense()
                                        .getPortfolio()
                                        .getPortfolioLicenses()
                                        .get(i7)
                                        .getLicenseDetails()
                                        .get(i10)
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
                                      .getPortfolioLicense()
                                      .getPortfolio()
                                      .getPortfolioLicenses()
                                      .get(i7)
                                      .getLicenseDetails()
                                      .get(i10)
                                      .getAddress()
                                      .getState();
                              if (isDelete && fetchType == FetchType.EAGER) {
                                whereQuery = " LicenseDetails10.state = ? ";
                                counter++;
                              } else {
                                whereQuery =
                                    " LicenseDetails10.address.state = :LicenseDetails10addressstate"
                                        + j
                                        + " ";
                              }

                              isOpenParentheses = false;
                              whereValue =
                                  model
                                      .getPortfolioLicense()
                                      .getPortfolio()
                                      .getPortfolioLicenses()
                                      .get(i7)
                                      .getLicenseDetails()
                                      .get(i10)
                                      .getAddress()
                                      .getState();
                              query +=
                                  (andCondition
                                      + Facade.getStatement(
                                          statement,
                                          "LicenseDetails10.address.state",
                                          "LicenseDetails10addressstate" + j,
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
                                        .getPortfolioLicense()
                                        .getPortfolio()
                                        .getPortfolioLicenses()
                                        .get(i7)
                                        .getLicenseDetails()
                                        .get(i10)
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
                                      .getPortfolioLicense()
                                      .getPortfolio()
                                      .getPortfolioLicenses()
                                      .get(i7)
                                      .getLicenseDetails()
                                      .get(i10)
                                      .getAddress()
                                      .getCountry();
                              if (isDelete && fetchType == FetchType.EAGER) {
                                whereQuery = " LicenseDetails10.country = ? ";
                                counter++;
                              } else {
                                whereQuery =
                                    " LicenseDetails10.address.country = :LicenseDetails10addresscountry"
                                        + j
                                        + " ";
                              }

                              isOpenParentheses = false;
                              whereValue =
                                  model
                                      .getPortfolioLicense()
                                      .getPortfolio()
                                      .getPortfolioLicenses()
                                      .get(i7)
                                      .getLicenseDetails()
                                      .get(i10)
                                      .getAddress()
                                      .getCountry();
                              query +=
                                  (andCondition
                                      + Facade.getStatement(
                                          statement,
                                          "LicenseDetails10.address.country",
                                          "LicenseDetails10addresscountry" + j,
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
                                        .getPortfolioLicense()
                                        .getPortfolio()
                                        .getPortfolioLicenses()
                                        .get(i7)
                                        .getLicenseDetails()
                                        .get(i10)
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
                                      .getPortfolioLicense()
                                      .getPortfolio()
                                      .getPortfolioLicenses()
                                      .get(i7)
                                      .getLicenseDetails()
                                      .get(i10)
                                      .getAddress()
                                      .getZipCode();
                              if (isDelete && fetchType == FetchType.EAGER) {
                                whereQuery = " LicenseDetails10.zipCode = ? ";
                                counter++;
                              } else {
                                whereQuery =
                                    " LicenseDetails10.address.zipCode = :LicenseDetails10addresszipCode"
                                        + j
                                        + " ";
                              }

                              isOpenParentheses = false;
                              whereValue =
                                  model
                                      .getPortfolioLicense()
                                      .getPortfolio()
                                      .getPortfolioLicenses()
                                      .get(i7)
                                      .getLicenseDetails()
                                      .get(i10)
                                      .getAddress()
                                      .getZipCode();
                              query +=
                                  (andCondition
                                      + Facade.getStatement(
                                          statement,
                                          "LicenseDetails10.address.zipCode",
                                          "LicenseDetails10addresszipCode" + j,
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
                          if (length10 > 1) {
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
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getPortfolioLicenses()
                                .get(i7)
                                .getIssueVerifierType()
                            != null) {

                      andCondition = " AND ";
                      if (isOpenParentheses) {
                        andCondition = " ";
                      }
                      query += " ";
                      whereValue =
                          model
                              .getPortfolioLicense()
                              .getPortfolio()
                              .getPortfolioLicenses()
                              .get(i7)
                              .getIssueVerifierType();
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " PortfolioLicense7.issueVerifierType = ? ";
                        counter++;
                      } else {
                        whereQuery =
                            " PortfolioLicense7.issueVerifierType = :PortfolioLicense7issueVerifierType"
                                + j
                                + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          model
                              .getPortfolioLicense()
                              .getPortfolio()
                              .getPortfolioLicenses()
                              .get(i7)
                              .getIssueVerifierType();
                      query +=
                          (andCondition
                              + Facade.getStatement(
                                  statement,
                                  "PortfolioLicense7.issueVerifierType",
                                  "PortfolioLicense7issueVerifierType" + j,
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
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getPortfolioLicenses()
                                .get(i7)
                                .getLicenseNumber()
                            != null) {

                      andCondition = " AND ";
                      if (isOpenParentheses) {
                        andCondition = " ";
                      }
                      query += " ";
                      whereValue =
                          model
                              .getPortfolioLicense()
                              .getPortfolio()
                              .getPortfolioLicenses()
                              .get(i7)
                              .getLicenseNumber();
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " PortfolioLicense7.licenseNumber = ? ";
                        counter++;
                      } else {
                        whereQuery =
                            " PortfolioLicense7.licenseNumber = :PortfolioLicense7licenseNumber"
                                + j
                                + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          model
                              .getPortfolioLicense()
                              .getPortfolio()
                              .getPortfolioLicenses()
                              .get(i7)
                              .getLicenseNumber();
                      query +=
                          (andCondition
                              + Facade.getStatement(
                                  statement,
                                  "PortfolioLicense7.licenseNumber",
                                  "PortfolioLicense7licenseNumber" + j,
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
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getPortfolioLicenses()
                                .get(i7)
                                .getLicenseType()
                            != null) {

                      andCondition = " AND ";
                      if (isOpenParentheses) {
                        andCondition = " ";
                      }
                      query += " ";
                      whereValue =
                          model
                              .getPortfolioLicense()
                              .getPortfolio()
                              .getPortfolioLicenses()
                              .get(i7)
                              .getLicenseType();
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " PortfolioLicense7.licenseType = ? ";
                        counter++;
                      } else {
                        whereQuery =
                            " PortfolioLicense7.licenseType = :PortfolioLicense7licenseType"
                                + j
                                + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          model
                              .getPortfolioLicense()
                              .getPortfolio()
                              .getPortfolioLicenses()
                              .get(i7)
                              .getLicenseType();
                      query +=
                          (andCondition
                              + Facade.getStatement(
                                  statement,
                                  "PortfolioLicense7.licenseType",
                                  "PortfolioLicense7licenseType" + j,
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
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getPortfolioLicenses()
                                .get(i7)
                                .getIssueDate()
                            != null) {

                      andCondition = " AND ";
                      if (isOpenParentheses) {
                        andCondition = " ";
                      }
                      query += " ";
                      whereValue =
                          model
                              .getPortfolioLicense()
                              .getPortfolio()
                              .getPortfolioLicenses()
                              .get(i7)
                              .getIssueDate();
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " PortfolioLicense7.issueDate = ? ";
                        counter++;
                      } else {
                        whereQuery =
                            " PortfolioLicense7.issueDate = :PortfolioLicense7issueDate" + j + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          model
                              .getPortfolioLicense()
                              .getPortfolio()
                              .getPortfolioLicenses()
                              .get(i7)
                              .getIssueDate();
                      query +=
                          (andCondition
                              + Facade.getStatement(
                                  statement,
                                  "PortfolioLicense7.issueDate",
                                  "PortfolioLicense7issueDate" + j,
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
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getPortfolioLicenses()
                                .get(i7)
                                .getEndDate()
                            != null) {

                      andCondition = " AND ";
                      if (isOpenParentheses) {
                        andCondition = " ";
                      }
                      query += " ";
                      whereValue =
                          model
                              .getPortfolioLicense()
                              .getPortfolio()
                              .getPortfolioLicenses()
                              .get(i7)
                              .getEndDate();
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " PortfolioLicense7.endDate = ? ";
                        counter++;
                      } else {
                        whereQuery =
                            " PortfolioLicense7.endDate = :PortfolioLicense7endDate" + j + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          model
                              .getPortfolioLicense()
                              .getPortfolio()
                              .getPortfolioLicenses()
                              .get(i7)
                              .getEndDate();
                      query +=
                          (andCondition
                              + Facade.getStatement(
                                  statement,
                                  "PortfolioLicense7.endDate",
                                  "PortfolioLicense7endDate" + j,
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
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getPortfolioLicenses()
                                .get(i7)
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
                                      .getPortfolioLicense()
                                      .getPortfolio()
                                      .getPortfolioLicenses()
                                      .get(i7)
                                      .getVersion());
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " PortfolioLicense7.VERSION = ? ";
                        counter++;
                      } else {
                        whereQuery =
                            " PortfolioLicense7.version = :PortfolioLicense7version" + j + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          new java.lang.Integer(
                              ""
                                  + model
                                      .getPortfolioLicense()
                                      .getPortfolio()
                                      .getPortfolioLicenses()
                                      .get(i7)
                                      .getVersion());
                      query +=
                          (andCondition
                              + Facade.getStatement(
                                  statement,
                                  "PortfolioLicense7.version",
                                  "PortfolioLicense7version" + j,
                                  mapVariable,
                                  whereQuery,
                                  whereValue,
                                  isExistRecord,
                                  isDelete,
                                  fetchType,
                                  counter));
                      whereQuery = "";
                    }
                    if (length7 > 1) {
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
                  && model.getPortfolioLicense().getPortfolio().getPortfolioLocations() != null) {
                int length13 =
                    model.getPortfolioLicense().getPortfolio().getPortfolioLocations().size();

                if (length13 > 0) {
                  if (isOpenParentheses) {
                    query += "  ( ";
                  } else {
                    query += " AND ( ";
                  }
                  isOpenParentheses = true;

                  for (int i13 = 0; i13 < length13; i13++) {

                    java.lang.String beforeCondition13 = "";
                    java.lang.String prefix13 = " OR ";
                    if (i13 == 0) {
                      prefix13 = "";
                    }
                    if (length13 > 1) {

                      query += (prefix13 + "(");
                      isOpenParentheses = true;

                    } else {

                      query += (prefix13);
                      if (!prefix13.trim().equals("")) {
                        isOpenParentheses = false;
                      }
                    }

                    if (model
                            .getPortfolioLicense()
                            .getPortfolio()
                            .getPortfolioLocations()
                            .get(i13)
                            .getPortfolioLocationId()
                        != null) {

                      andCondition = " AND ";
                      if (isOpenParentheses) {
                        andCondition = " ";
                      }
                      query += " ";
                      whereValue =
                          model
                              .getPortfolioLicense()
                              .getPortfolio()
                              .getPortfolioLocations()
                              .get(i13)
                              .getPortfolioLocationId();
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " PortfolioLocation13.id = ? ";
                        counter++;
                      } else {
                        whereQuery =
                            " PortfolioLocation13.portfolioLocationId = :PortfolioLocation13portfolioLocationId"
                                + j
                                + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          model
                              .getPortfolioLicense()
                              .getPortfolio()
                              .getPortfolioLocations()
                              .get(i13)
                              .getPortfolioLocationId();
                      query +=
                          (andCondition
                              + Facade.getStatement(
                                  statement,
                                  "PortfolioLocation13.portfolioLocationId",
                                  "PortfolioLocation13portfolioLocationId" + j,
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
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getPortfolioLocations()
                                .get(i13)
                                .getFile()
                            != null) {

                      if (model
                              .getPortfolioLicense()
                              .getPortfolio()
                              .getPortfolioLocations()
                              .get(i13)
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
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getPortfolioLocations()
                                .get(i13)
                                .getFile()
                                .getId();
                        if (isDelete && fetchType == FetchType.EAGER) {
                          whereQuery = " File15.l4610flmtdataid = ? ";
                          counter++;
                        } else {
                          whereQuery = " File15.id = :File15id" + j + " ";
                        }

                        isOpenParentheses = false;
                        whereValue =
                            model
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getPortfolioLocations()
                                .get(i13)
                                .getFile()
                                .getId();
                        query +=
                            (andCondition
                                + Facade.getStatement(
                                    statement,
                                    "File15.id",
                                    "File15id" + j,
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
                                  .getPortfolioLicense()
                                  .getPortfolio()
                                  .getPortfolioLocations()
                                  .get(i13)
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
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getPortfolioLocations()
                                .get(i13)
                                .getFile()
                                .getExtension();
                        if (isDelete && fetchType == FetchType.EAGER) {
                          whereQuery = " File15.l4610extension = ? ";
                          counter++;
                        } else {
                          whereQuery = " File15.extension = :File15extension" + j + " ";
                        }

                        isOpenParentheses = false;
                        whereValue =
                            model
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getPortfolioLocations()
                                .get(i13)
                                .getFile()
                                .getExtension();
                        query +=
                            (andCondition
                                + Facade.getStatement(
                                    statement,
                                    "File15.extension",
                                    "File15extension" + j,
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
                                  .getPortfolioLicense()
                                  .getPortfolio()
                                  .getPortfolioLocations()
                                  .get(i13)
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
                                        .getPortfolioLicense()
                                        .getPortfolio()
                                        .getPortfolioLocations()
                                        .get(i13)
                                        .getFile()
                                        .getVersion());
                        if (isDelete && fetchType == FetchType.EAGER) {
                          whereQuery = " File15.VERSION = ? ";
                          counter++;
                        } else {
                          whereQuery = " File15.version = :File15version" + j + " ";
                        }

                        isOpenParentheses = false;
                        whereValue =
                            new java.lang.Integer(
                                ""
                                    + model
                                        .getPortfolioLicense()
                                        .getPortfolio()
                                        .getPortfolioLocations()
                                        .get(i13)
                                        .getFile()
                                        .getVersion());
                        query +=
                            (andCondition
                                + Facade.getStatement(
                                    statement,
                                    "File15.version",
                                    "File15version" + j,
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
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getPortfolioLocations()
                                .get(i13)
                                .getLocationDetails()
                            != null) {
                      int length16 =
                          model
                              .getPortfolioLicense()
                              .getPortfolio()
                              .getPortfolioLocations()
                              .get(i13)
                              .getLocationDetails()
                              .size();

                      if (length16 > 0) {
                        if (isOpenParentheses) {
                          query += "  ( ";
                        } else {
                          query += " AND ( ";
                        }
                        isOpenParentheses = true;

                        for (int i16 = 0; i16 < length16; i16++) {

                          java.lang.String beforeCondition16 = "";
                          java.lang.String prefix16 = " OR ";
                          if (i16 == 0) {
                            prefix16 = "";
                          }
                          if (length16 > 1) {

                            query += (prefix16 + "(");
                            isOpenParentheses = true;

                          } else {

                            query += (prefix16);
                            if (!prefix16.trim().equals("")) {
                              isOpenParentheses = false;
                            }
                          }

                          if (model
                                  .getPortfolioLicense()
                                  .getPortfolio()
                                  .getPortfolioLocations()
                                  .get(i13)
                                  .getLocationDetails()
                                  .get(i16)
                                  .getLocationDetailId()
                              != null) {

                            andCondition = " AND ";
                            if (isOpenParentheses) {
                              andCondition = " ";
                            }
                            query += " ";
                            whereValue =
                                model
                                    .getPortfolioLicense()
                                    .getPortfolio()
                                    .getPortfolioLocations()
                                    .get(i13)
                                    .getLocationDetails()
                                    .get(i16)
                                    .getLocationDetailId();
                            if (isDelete && fetchType == FetchType.EAGER) {
                              whereQuery = " LocationDetails16.id = ? ";
                              counter++;
                            } else {
                              whereQuery =
                                  " LocationDetails16.locationDetailId = :LocationDetails16locationDetailId"
                                      + j
                                      + " ";
                            }

                            isOpenParentheses = false;
                            whereValue =
                                model
                                    .getPortfolioLicense()
                                    .getPortfolio()
                                    .getPortfolioLocations()
                                    .get(i13)
                                    .getLocationDetails()
                                    .get(i16)
                                    .getLocationDetailId();
                            query +=
                                (andCondition
                                    + Facade.getStatement(
                                        statement,
                                        "LocationDetails16.locationDetailId",
                                        "LocationDetails16locationDetailId" + j,
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
                                      .getPortfolioLicense()
                                      .getPortfolio()
                                      .getPortfolioLocations()
                                      .get(i13)
                                      .getLocationDetails()
                                      .get(i16)
                                      .getPortfolioLocation()
                                  != null) {

                            if (model
                                    .getPortfolioLicense()
                                    .getPortfolio()
                                    .getPortfolioLocations()
                                    .get(i13)
                                    .getLocationDetails()
                                    .get(i16)
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
                                      .getPortfolioLicense()
                                      .getPortfolio()
                                      .getPortfolioLocations()
                                      .get(i13)
                                      .getLocationDetails()
                                      .get(i16)
                                      .getPortfolioLocation()
                                      .getPortfolioLocationId();
                              if (isDelete && fetchType == FetchType.EAGER) {
                                whereQuery = " PortfolioLocation17.id = ? ";
                                counter++;
                              } else {
                                whereQuery =
                                    " PortfolioLocation17.portfolioLocationId = :PortfolioLocation17portfolioLocationId"
                                        + j
                                        + " ";
                              }

                              isOpenParentheses = false;
                              whereValue =
                                  model
                                      .getPortfolioLicense()
                                      .getPortfolio()
                                      .getPortfolioLocations()
                                      .get(i13)
                                      .getLocationDetails()
                                      .get(i16)
                                      .getPortfolioLocation()
                                      .getPortfolioLocationId();
                              query +=
                                  (andCondition
                                      + Facade.getStatement(
                                          statement,
                                          "PortfolioLocation17.portfolioLocationId",
                                          "PortfolioLocation17portfolioLocationId" + j,
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
                                        .getPortfolioLicense()
                                        .getPortfolio()
                                        .getPortfolioLocations()
                                        .get(i13)
                                        .getLocationDetails()
                                        .get(i16)
                                        .getPortfolioLocation()
                                        .getFile()
                                    != null) {

                              if (model
                                      .getPortfolioLicense()
                                      .getPortfolio()
                                      .getPortfolioLocations()
                                      .get(i13)
                                      .getLocationDetails()
                                      .get(i16)
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
                                        .getPortfolioLicense()
                                        .getPortfolio()
                                        .getPortfolioLocations()
                                        .get(i13)
                                        .getLocationDetails()
                                        .get(i16)
                                        .getPortfolioLocation()
                                        .getFile()
                                        .getId();
                                if (isDelete && fetchType == FetchType.EAGER) {
                                  whereQuery = " File19.l4610flmtdataid = ? ";
                                  counter++;
                                } else {
                                  whereQuery = " File19.id = :File19id" + j + " ";
                                }

                                isOpenParentheses = false;
                                whereValue =
                                    model
                                        .getPortfolioLicense()
                                        .getPortfolio()
                                        .getPortfolioLocations()
                                        .get(i13)
                                        .getLocationDetails()
                                        .get(i16)
                                        .getPortfolioLocation()
                                        .getFile()
                                        .getId();
                                query +=
                                    (andCondition
                                        + Facade.getStatement(
                                            statement,
                                            "File19.id",
                                            "File19id" + j,
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
                                          .getPortfolioLicense()
                                          .getPortfolio()
                                          .getPortfolioLocations()
                                          .get(i13)
                                          .getLocationDetails()
                                          .get(i16)
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
                                        .getPortfolioLicense()
                                        .getPortfolio()
                                        .getPortfolioLocations()
                                        .get(i13)
                                        .getLocationDetails()
                                        .get(i16)
                                        .getPortfolioLocation()
                                        .getFile()
                                        .getExtension();
                                if (isDelete && fetchType == FetchType.EAGER) {
                                  whereQuery = " File19.l4610extension = ? ";
                                  counter++;
                                } else {
                                  whereQuery = " File19.extension = :File19extension" + j + " ";
                                }

                                isOpenParentheses = false;
                                whereValue =
                                    model
                                        .getPortfolioLicense()
                                        .getPortfolio()
                                        .getPortfolioLocations()
                                        .get(i13)
                                        .getLocationDetails()
                                        .get(i16)
                                        .getPortfolioLocation()
                                        .getFile()
                                        .getExtension();
                                query +=
                                    (andCondition
                                        + Facade.getStatement(
                                            statement,
                                            "File19.extension",
                                            "File19extension" + j,
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
                                          .getPortfolioLicense()
                                          .getPortfolio()
                                          .getPortfolioLocations()
                                          .get(i13)
                                          .getLocationDetails()
                                          .get(i16)
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
                                                .getPortfolioLicense()
                                                .getPortfolio()
                                                .getPortfolioLocations()
                                                .get(i13)
                                                .getLocationDetails()
                                                .get(i16)
                                                .getPortfolioLocation()
                                                .getFile()
                                                .getVersion());
                                if (isDelete && fetchType == FetchType.EAGER) {
                                  whereQuery = " File19.VERSION = ? ";
                                  counter++;
                                } else {
                                  whereQuery = " File19.version = :File19version" + j + " ";
                                }

                                isOpenParentheses = false;
                                whereValue =
                                    new java.lang.Integer(
                                        ""
                                            + model
                                                .getPortfolioLicense()
                                                .getPortfolio()
                                                .getPortfolioLocations()
                                                .get(i13)
                                                .getLocationDetails()
                                                .get(i16)
                                                .getPortfolioLocation()
                                                .getFile()
                                                .getVersion());
                                query +=
                                    (andCondition
                                        + Facade.getStatement(
                                            statement,
                                            "File19.version",
                                            "File19version" + j,
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
                                        .getPortfolioLicense()
                                        .getPortfolio()
                                        .getPortfolioLocations()
                                        .get(i13)
                                        .getLocationDetails()
                                        .get(i16)
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
                                      .getPortfolioLicense()
                                      .getPortfolio()
                                      .getPortfolioLocations()
                                      .get(i13)
                                      .getLocationDetails()
                                      .get(i16)
                                      .getPortfolioLocation()
                                      .getProvince();
                              if (isDelete && fetchType == FetchType.EAGER) {
                                whereQuery = " PortfolioLocation17.province = ? ";
                                counter++;
                              } else {
                                whereQuery =
                                    " PortfolioLocation17.province = :PortfolioLocation17province"
                                        + j
                                        + " ";
                              }

                              isOpenParentheses = false;
                              whereValue =
                                  model
                                      .getPortfolioLicense()
                                      .getPortfolio()
                                      .getPortfolioLocations()
                                      .get(i13)
                                      .getLocationDetails()
                                      .get(i16)
                                      .getPortfolioLocation()
                                      .getProvince();
                              query +=
                                  (andCondition
                                      + Facade.getStatement(
                                          statement,
                                          "PortfolioLocation17.province",
                                          "PortfolioLocation17province" + j,
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
                                        .getPortfolioLicense()
                                        .getPortfolio()
                                        .getPortfolioLocations()
                                        .get(i13)
                                        .getLocationDetails()
                                        .get(i16)
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
                                      .getPortfolioLicense()
                                      .getPortfolio()
                                      .getPortfolioLocations()
                                      .get(i13)
                                      .getLocationDetails()
                                      .get(i16)
                                      .getPortfolioLocation()
                                      .getCity();
                              if (isDelete && fetchType == FetchType.EAGER) {
                                whereQuery = " PortfolioLocation17.city = ? ";
                                counter++;
                              } else {
                                whereQuery =
                                    " PortfolioLocation17.city = :PortfolioLocation17city"
                                        + j
                                        + " ";
                              }

                              isOpenParentheses = false;
                              whereValue =
                                  model
                                      .getPortfolioLicense()
                                      .getPortfolio()
                                      .getPortfolioLocations()
                                      .get(i13)
                                      .getLocationDetails()
                                      .get(i16)
                                      .getPortfolioLocation()
                                      .getCity();
                              query +=
                                  (andCondition
                                      + Facade.getStatement(
                                          statement,
                                          "PortfolioLocation17.city",
                                          "PortfolioLocation17city" + j,
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
                                        .getPortfolioLicense()
                                        .getPortfolio()
                                        .getPortfolioLocations()
                                        .get(i13)
                                        .getLocationDetails()
                                        .get(i16)
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
                                      .getPortfolioLicense()
                                      .getPortfolio()
                                      .getPortfolioLocations()
                                      .get(i13)
                                      .getLocationDetails()
                                      .get(i16)
                                      .getPortfolioLocation()
                                      .getIndstrltwn();
                              if (isDelete && fetchType == FetchType.EAGER) {
                                whereQuery = " PortfolioLocation17.indstrltwn = ? ";
                                counter++;
                              } else {
                                whereQuery =
                                    " PortfolioLocation17.indstrltwn = :PortfolioLocation17indstrltwn"
                                        + j
                                        + " ";
                              }

                              isOpenParentheses = false;
                              whereValue =
                                  model
                                      .getPortfolioLicense()
                                      .getPortfolio()
                                      .getPortfolioLocations()
                                      .get(i13)
                                      .getLocationDetails()
                                      .get(i16)
                                      .getPortfolioLocation()
                                      .getIndstrltwn();
                              query +=
                                  (andCondition
                                      + Facade.getStatement(
                                          statement,
                                          "PortfolioLocation17.indstrltwn",
                                          "PortfolioLocation17indstrltwn" + j,
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
                                        .getPortfolioLicense()
                                        .getPortfolio()
                                        .getPortfolioLocations()
                                        .get(i13)
                                        .getLocationDetails()
                                        .get(i16)
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
                                      .getPortfolioLicense()
                                      .getPortfolio()
                                      .getPortfolioLocations()
                                      .get(i13)
                                      .getLocationDetails()
                                      .get(i16)
                                      .getPortfolioLocation()
                                      .getAddress();
                              if (isDelete && fetchType == FetchType.EAGER) {
                                whereQuery = " PortfolioLocation17.address = ? ";
                                counter++;
                              } else {
                                whereQuery =
                                    " PortfolioLocation17.address = :PortfolioLocation17address"
                                        + j
                                        + " ";
                              }

                              isOpenParentheses = false;
                              whereValue =
                                  model
                                      .getPortfolioLicense()
                                      .getPortfolio()
                                      .getPortfolioLocations()
                                      .get(i13)
                                      .getLocationDetails()
                                      .get(i16)
                                      .getPortfolioLocation()
                                      .getAddress();
                              query +=
                                  (andCondition
                                      + Facade.getStatement(
                                          statement,
                                          "PortfolioLocation17.address",
                                          "PortfolioLocation17address" + j,
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
                                        .getPortfolioLicense()
                                        .getPortfolio()
                                        .getPortfolioLocations()
                                        .get(i13)
                                        .getLocationDetails()
                                        .get(i16)
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
                                      .getPortfolioLicense()
                                      .getPortfolio()
                                      .getPortfolioLocations()
                                      .get(i13)
                                      .getLocationDetails()
                                      .get(i16)
                                      .getPortfolioLocation()
                                      .getStableStatusType();
                              if (isDelete && fetchType == FetchType.EAGER) {
                                whereQuery = " PortfolioLocation17.stableStatusType = ? ";
                                counter++;
                              } else {
                                whereQuery =
                                    " PortfolioLocation17.stableStatusType = :PortfolioLocation17stableStatusType"
                                        + j
                                        + " ";
                              }

                              isOpenParentheses = false;
                              whereValue =
                                  model
                                      .getPortfolioLicense()
                                      .getPortfolio()
                                      .getPortfolioLocations()
                                      .get(i13)
                                      .getLocationDetails()
                                      .get(i16)
                                      .getPortfolioLocation()
                                      .getStableStatusType();
                              query +=
                                  (andCondition
                                      + Facade.getStatement(
                                          statement,
                                          "PortfolioLocation17.stableStatusType",
                                          "PortfolioLocation17stableStatusType" + j,
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
                                        .getPortfolioLicense()
                                        .getPortfolio()
                                        .getPortfolioLocations()
                                        .get(i13)
                                        .getLocationDetails()
                                        .get(i16)
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
                                      .getPortfolioLicense()
                                      .getPortfolio()
                                      .getPortfolioLocations()
                                      .get(i13)
                                      .getLocationDetails()
                                      .get(i16)
                                      .getPortfolioLocation()
                                      .getOwnerStatusType();
                              if (isDelete && fetchType == FetchType.EAGER) {
                                whereQuery = " PortfolioLocation17.ownerStatusType = ? ";
                                counter++;
                              } else {
                                whereQuery =
                                    " PortfolioLocation17.ownerStatusType = :PortfolioLocation17ownerStatusType"
                                        + j
                                        + " ";
                              }

                              isOpenParentheses = false;
                              whereValue =
                                  model
                                      .getPortfolioLicense()
                                      .getPortfolio()
                                      .getPortfolioLocations()
                                      .get(i13)
                                      .getLocationDetails()
                                      .get(i16)
                                      .getPortfolioLocation()
                                      .getOwnerStatusType();
                              query +=
                                  (andCondition
                                      + Facade.getStatement(
                                          statement,
                                          "PortfolioLocation17.ownerStatusType",
                                          "PortfolioLocation17ownerStatusType" + j,
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
                                        .getPortfolioLicense()
                                        .getPortfolio()
                                        .getPortfolioLocations()
                                        .get(i13)
                                        .getLocationDetails()
                                        .get(i16)
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
                                      .getPortfolioLicense()
                                      .getPortfolio()
                                      .getPortfolioLocations()
                                      .get(i13)
                                      .getLocationDetails()
                                      .get(i16)
                                      .getPortfolioLocation()
                                      .getRegionalStatusType();
                              if (isDelete && fetchType == FetchType.EAGER) {
                                whereQuery = " PortfolioLocation17.regionalStatusType = ? ";
                                counter++;
                              } else {
                                whereQuery =
                                    " PortfolioLocation17.regionalStatusType = :PortfolioLocation17regionalStatusType"
                                        + j
                                        + " ";
                              }

                              isOpenParentheses = false;
                              whereValue =
                                  model
                                      .getPortfolioLicense()
                                      .getPortfolio()
                                      .getPortfolioLocations()
                                      .get(i13)
                                      .getLocationDetails()
                                      .get(i16)
                                      .getPortfolioLocation()
                                      .getRegionalStatusType();
                              query +=
                                  (andCondition
                                      + Facade.getStatement(
                                          statement,
                                          "PortfolioLocation17.regionalStatusType",
                                          "PortfolioLocation17regionalStatusType" + j,
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
                                        .getPortfolioLicense()
                                        .getPortfolio()
                                        .getPortfolioLocations()
                                        .get(i13)
                                        .getLocationDetails()
                                        .get(i16)
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
                                              .getPortfolioLicense()
                                              .getPortfolio()
                                              .getPortfolioLocations()
                                              .get(i13)
                                              .getLocationDetails()
                                              .get(i16)
                                              .getPortfolioLocation()
                                              .getVersion());
                              if (isDelete && fetchType == FetchType.EAGER) {
                                whereQuery = " PortfolioLocation17.VERSION = ? ";
                                counter++;
                              } else {
                                whereQuery =
                                    " PortfolioLocation17.version = :PortfolioLocation17version"
                                        + j
                                        + " ";
                              }

                              isOpenParentheses = false;
                              whereValue =
                                  new java.lang.Integer(
                                      ""
                                          + model
                                              .getPortfolioLicense()
                                              .getPortfolio()
                                              .getPortfolioLocations()
                                              .get(i13)
                                              .getLocationDetails()
                                              .get(i16)
                                              .getPortfolioLocation()
                                              .getVersion());
                              query +=
                                  (andCondition
                                      + Facade.getStatement(
                                          statement,
                                          "PortfolioLocation17.version",
                                          "PortfolioLocation17version" + j,
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
                                      .getPortfolioLicense()
                                      .getPortfolio()
                                      .getPortfolioLocations()
                                      .get(i13)
                                      .getLocationDetails()
                                      .get(i16)
                                      .getFile()
                                  != null) {

                            if (model
                                    .getPortfolioLicense()
                                    .getPortfolio()
                                    .getPortfolioLocations()
                                    .get(i13)
                                    .getLocationDetails()
                                    .get(i16)
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
                                      .getPortfolioLicense()
                                      .getPortfolio()
                                      .getPortfolioLocations()
                                      .get(i13)
                                      .getLocationDetails()
                                      .get(i16)
                                      .getFile()
                                      .getId();
                              if (isDelete && fetchType == FetchType.EAGER) {
                                whereQuery = " File21.l4610flmtdataid = ? ";
                                counter++;
                              } else {
                                whereQuery = " File21.id = :File21id" + j + " ";
                              }

                              isOpenParentheses = false;
                              whereValue =
                                  model
                                      .getPortfolioLicense()
                                      .getPortfolio()
                                      .getPortfolioLocations()
                                      .get(i13)
                                      .getLocationDetails()
                                      .get(i16)
                                      .getFile()
                                      .getId();
                              query +=
                                  (andCondition
                                      + Facade.getStatement(
                                          statement,
                                          "File21.id",
                                          "File21id" + j,
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
                                        .getPortfolioLicense()
                                        .getPortfolio()
                                        .getPortfolioLocations()
                                        .get(i13)
                                        .getLocationDetails()
                                        .get(i16)
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
                                      .getPortfolioLicense()
                                      .getPortfolio()
                                      .getPortfolioLocations()
                                      .get(i13)
                                      .getLocationDetails()
                                      .get(i16)
                                      .getFile()
                                      .getExtension();
                              if (isDelete && fetchType == FetchType.EAGER) {
                                whereQuery = " File21.l4610extension = ? ";
                                counter++;
                              } else {
                                whereQuery = " File21.extension = :File21extension" + j + " ";
                              }

                              isOpenParentheses = false;
                              whereValue =
                                  model
                                      .getPortfolioLicense()
                                      .getPortfolio()
                                      .getPortfolioLocations()
                                      .get(i13)
                                      .getLocationDetails()
                                      .get(i16)
                                      .getFile()
                                      .getExtension();
                              query +=
                                  (andCondition
                                      + Facade.getStatement(
                                          statement,
                                          "File21.extension",
                                          "File21extension" + j,
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
                                        .getPortfolioLicense()
                                        .getPortfolio()
                                        .getPortfolioLocations()
                                        .get(i13)
                                        .getLocationDetails()
                                        .get(i16)
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
                                              .getPortfolioLicense()
                                              .getPortfolio()
                                              .getPortfolioLocations()
                                              .get(i13)
                                              .getLocationDetails()
                                              .get(i16)
                                              .getFile()
                                              .getVersion());
                              if (isDelete && fetchType == FetchType.EAGER) {
                                whereQuery = " File21.VERSION = ? ";
                                counter++;
                              } else {
                                whereQuery = " File21.version = :File21version" + j + " ";
                              }

                              isOpenParentheses = false;
                              whereValue =
                                  new java.lang.Integer(
                                      ""
                                          + model
                                              .getPortfolioLicense()
                                              .getPortfolio()
                                              .getPortfolioLocations()
                                              .get(i13)
                                              .getLocationDetails()
                                              .get(i16)
                                              .getFile()
                                              .getVersion());
                              query +=
                                  (andCondition
                                      + Facade.getStatement(
                                          statement,
                                          "File21.version",
                                          "File21version" + j,
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
                                      .getPortfolioLicense()
                                      .getPortfolio()
                                      .getPortfolioLocations()
                                      .get(i13)
                                      .getLocationDetails()
                                      .get(i16)
                                      .getAddress()
                                  != null) {
                            if (!isDelete
                                && true
                                && model
                                        .getPortfolioLicense()
                                        .getPortfolio()
                                        .getPortfolioLocations()
                                        .get(i13)
                                        .getLocationDetails()
                                        .get(i16)
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
                                      .getPortfolioLicense()
                                      .getPortfolio()
                                      .getPortfolioLocations()
                                      .get(i13)
                                      .getLocationDetails()
                                      .get(i16)
                                      .getAddress()
                                      .getAddressLine1();
                              if (isDelete && fetchType == FetchType.EAGER) {
                                whereQuery = " LocationDetails16.addressLine1 = ? ";
                                counter++;
                              } else {
                                whereQuery =
                                    " LocationDetails16.address.addressLine1 = :LocationDetails16addressaddressLine1"
                                        + j
                                        + " ";
                              }

                              isOpenParentheses = false;
                              whereValue =
                                  model
                                      .getPortfolioLicense()
                                      .getPortfolio()
                                      .getPortfolioLocations()
                                      .get(i13)
                                      .getLocationDetails()
                                      .get(i16)
                                      .getAddress()
                                      .getAddressLine1();
                              query +=
                                  (andCondition
                                      + Facade.getStatement(
                                          statement,
                                          "LocationDetails16.address.addressLine1",
                                          "LocationDetails16addressaddressLine1" + j,
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
                                        .getPortfolioLicense()
                                        .getPortfolio()
                                        .getPortfolioLocations()
                                        .get(i13)
                                        .getLocationDetails()
                                        .get(i16)
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
                                      .getPortfolioLicense()
                                      .getPortfolio()
                                      .getPortfolioLocations()
                                      .get(i13)
                                      .getLocationDetails()
                                      .get(i16)
                                      .getAddress()
                                      .getAddressLine2();
                              if (isDelete && fetchType == FetchType.EAGER) {
                                whereQuery = " LocationDetails16.addressLine2 = ? ";
                                counter++;
                              } else {
                                whereQuery =
                                    " LocationDetails16.address.addressLine2 = :LocationDetails16addressaddressLine2"
                                        + j
                                        + " ";
                              }

                              isOpenParentheses = false;
                              whereValue =
                                  model
                                      .getPortfolioLicense()
                                      .getPortfolio()
                                      .getPortfolioLocations()
                                      .get(i13)
                                      .getLocationDetails()
                                      .get(i16)
                                      .getAddress()
                                      .getAddressLine2();
                              query +=
                                  (andCondition
                                      + Facade.getStatement(
                                          statement,
                                          "LocationDetails16.address.addressLine2",
                                          "LocationDetails16addressaddressLine2" + j,
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
                                        .getPortfolioLicense()
                                        .getPortfolio()
                                        .getPortfolioLocations()
                                        .get(i13)
                                        .getLocationDetails()
                                        .get(i16)
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
                                      .getPortfolioLicense()
                                      .getPortfolio()
                                      .getPortfolioLocations()
                                      .get(i13)
                                      .getLocationDetails()
                                      .get(i16)
                                      .getAddress()
                                      .getCity();
                              if (isDelete && fetchType == FetchType.EAGER) {
                                whereQuery = " LocationDetails16.city = ? ";
                                counter++;
                              } else {
                                whereQuery =
                                    " LocationDetails16.address.city = :LocationDetails16addresscity"
                                        + j
                                        + " ";
                              }

                              isOpenParentheses = false;
                              whereValue =
                                  model
                                      .getPortfolioLicense()
                                      .getPortfolio()
                                      .getPortfolioLocations()
                                      .get(i13)
                                      .getLocationDetails()
                                      .get(i16)
                                      .getAddress()
                                      .getCity();
                              query +=
                                  (andCondition
                                      + Facade.getStatement(
                                          statement,
                                          "LocationDetails16.address.city",
                                          "LocationDetails16addresscity" + j,
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
                                        .getPortfolioLicense()
                                        .getPortfolio()
                                        .getPortfolioLocations()
                                        .get(i13)
                                        .getLocationDetails()
                                        .get(i16)
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
                                      .getPortfolioLicense()
                                      .getPortfolio()
                                      .getPortfolioLocations()
                                      .get(i13)
                                      .getLocationDetails()
                                      .get(i16)
                                      .getAddress()
                                      .getState();
                              if (isDelete && fetchType == FetchType.EAGER) {
                                whereQuery = " LocationDetails16.state = ? ";
                                counter++;
                              } else {
                                whereQuery =
                                    " LocationDetails16.address.state = :LocationDetails16addressstate"
                                        + j
                                        + " ";
                              }

                              isOpenParentheses = false;
                              whereValue =
                                  model
                                      .getPortfolioLicense()
                                      .getPortfolio()
                                      .getPortfolioLocations()
                                      .get(i13)
                                      .getLocationDetails()
                                      .get(i16)
                                      .getAddress()
                                      .getState();
                              query +=
                                  (andCondition
                                      + Facade.getStatement(
                                          statement,
                                          "LocationDetails16.address.state",
                                          "LocationDetails16addressstate" + j,
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
                                        .getPortfolioLicense()
                                        .getPortfolio()
                                        .getPortfolioLocations()
                                        .get(i13)
                                        .getLocationDetails()
                                        .get(i16)
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
                                      .getPortfolioLicense()
                                      .getPortfolio()
                                      .getPortfolioLocations()
                                      .get(i13)
                                      .getLocationDetails()
                                      .get(i16)
                                      .getAddress()
                                      .getCountry();
                              if (isDelete && fetchType == FetchType.EAGER) {
                                whereQuery = " LocationDetails16.country = ? ";
                                counter++;
                              } else {
                                whereQuery =
                                    " LocationDetails16.address.country = :LocationDetails16addresscountry"
                                        + j
                                        + " ";
                              }

                              isOpenParentheses = false;
                              whereValue =
                                  model
                                      .getPortfolioLicense()
                                      .getPortfolio()
                                      .getPortfolioLocations()
                                      .get(i13)
                                      .getLocationDetails()
                                      .get(i16)
                                      .getAddress()
                                      .getCountry();
                              query +=
                                  (andCondition
                                      + Facade.getStatement(
                                          statement,
                                          "LocationDetails16.address.country",
                                          "LocationDetails16addresscountry" + j,
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
                                        .getPortfolioLicense()
                                        .getPortfolio()
                                        .getPortfolioLocations()
                                        .get(i13)
                                        .getLocationDetails()
                                        .get(i16)
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
                                      .getPortfolioLicense()
                                      .getPortfolio()
                                      .getPortfolioLocations()
                                      .get(i13)
                                      .getLocationDetails()
                                      .get(i16)
                                      .getAddress()
                                      .getZipCode();
                              if (isDelete && fetchType == FetchType.EAGER) {
                                whereQuery = " LocationDetails16.zipCode = ? ";
                                counter++;
                              } else {
                                whereQuery =
                                    " LocationDetails16.address.zipCode = :LocationDetails16addresszipCode"
                                        + j
                                        + " ";
                              }

                              isOpenParentheses = false;
                              whereValue =
                                  model
                                      .getPortfolioLicense()
                                      .getPortfolio()
                                      .getPortfolioLocations()
                                      .get(i13)
                                      .getLocationDetails()
                                      .get(i16)
                                      .getAddress()
                                      .getZipCode();
                              query +=
                                  (andCondition
                                      + Facade.getStatement(
                                          statement,
                                          "LocationDetails16.address.zipCode",
                                          "LocationDetails16addresszipCode" + j,
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
                          if (length16 > 1) {
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
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getPortfolioLocations()
                                .get(i13)
                                .getProvince()
                            != null) {

                      andCondition = " AND ";
                      if (isOpenParentheses) {
                        andCondition = " ";
                      }
                      query += " ";
                      whereValue =
                          model
                              .getPortfolioLicense()
                              .getPortfolio()
                              .getPortfolioLocations()
                              .get(i13)
                              .getProvince();
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " PortfolioLocation13.province = ? ";
                        counter++;
                      } else {
                        whereQuery =
                            " PortfolioLocation13.province = :PortfolioLocation13province"
                                + j
                                + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          model
                              .getPortfolioLicense()
                              .getPortfolio()
                              .getPortfolioLocations()
                              .get(i13)
                              .getProvince();
                      query +=
                          (andCondition
                              + Facade.getStatement(
                                  statement,
                                  "PortfolioLocation13.province",
                                  "PortfolioLocation13province" + j,
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
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getPortfolioLocations()
                                .get(i13)
                                .getCity()
                            != null) {

                      andCondition = " AND ";
                      if (isOpenParentheses) {
                        andCondition = " ";
                      }
                      query += " ";
                      whereValue =
                          model
                              .getPortfolioLicense()
                              .getPortfolio()
                              .getPortfolioLocations()
                              .get(i13)
                              .getCity();
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " PortfolioLocation13.city = ? ";
                        counter++;
                      } else {
                        whereQuery =
                            " PortfolioLocation13.city = :PortfolioLocation13city" + j + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          model
                              .getPortfolioLicense()
                              .getPortfolio()
                              .getPortfolioLocations()
                              .get(i13)
                              .getCity();
                      query +=
                          (andCondition
                              + Facade.getStatement(
                                  statement,
                                  "PortfolioLocation13.city",
                                  "PortfolioLocation13city" + j,
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
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getPortfolioLocations()
                                .get(i13)
                                .getIndstrltwn()
                            != null) {

                      andCondition = " AND ";
                      if (isOpenParentheses) {
                        andCondition = " ";
                      }
                      query += " ";
                      whereValue =
                          model
                              .getPortfolioLicense()
                              .getPortfolio()
                              .getPortfolioLocations()
                              .get(i13)
                              .getIndstrltwn();
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " PortfolioLocation13.indstrltwn = ? ";
                        counter++;
                      } else {
                        whereQuery =
                            " PortfolioLocation13.indstrltwn = :PortfolioLocation13indstrltwn"
                                + j
                                + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          model
                              .getPortfolioLicense()
                              .getPortfolio()
                              .getPortfolioLocations()
                              .get(i13)
                              .getIndstrltwn();
                      query +=
                          (andCondition
                              + Facade.getStatement(
                                  statement,
                                  "PortfolioLocation13.indstrltwn",
                                  "PortfolioLocation13indstrltwn" + j,
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
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getPortfolioLocations()
                                .get(i13)
                                .getAddress()
                            != null) {

                      andCondition = " AND ";
                      if (isOpenParentheses) {
                        andCondition = " ";
                      }
                      query += " ";
                      whereValue =
                          model
                              .getPortfolioLicense()
                              .getPortfolio()
                              .getPortfolioLocations()
                              .get(i13)
                              .getAddress();
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " PortfolioLocation13.address = ? ";
                        counter++;
                      } else {
                        whereQuery =
                            " PortfolioLocation13.address = :PortfolioLocation13address" + j + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          model
                              .getPortfolioLicense()
                              .getPortfolio()
                              .getPortfolioLocations()
                              .get(i13)
                              .getAddress();
                      query +=
                          (andCondition
                              + Facade.getStatement(
                                  statement,
                                  "PortfolioLocation13.address",
                                  "PortfolioLocation13address" + j,
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
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getPortfolioLocations()
                                .get(i13)
                                .getStableStatusType()
                            != null) {

                      andCondition = " AND ";
                      if (isOpenParentheses) {
                        andCondition = " ";
                      }
                      query += " ";
                      whereValue =
                          model
                              .getPortfolioLicense()
                              .getPortfolio()
                              .getPortfolioLocations()
                              .get(i13)
                              .getStableStatusType();
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " PortfolioLocation13.stableStatusType = ? ";
                        counter++;
                      } else {
                        whereQuery =
                            " PortfolioLocation13.stableStatusType = :PortfolioLocation13stableStatusType"
                                + j
                                + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          model
                              .getPortfolioLicense()
                              .getPortfolio()
                              .getPortfolioLocations()
                              .get(i13)
                              .getStableStatusType();
                      query +=
                          (andCondition
                              + Facade.getStatement(
                                  statement,
                                  "PortfolioLocation13.stableStatusType",
                                  "PortfolioLocation13stableStatusType" + j,
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
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getPortfolioLocations()
                                .get(i13)
                                .getOwnerStatusType()
                            != null) {

                      andCondition = " AND ";
                      if (isOpenParentheses) {
                        andCondition = " ";
                      }
                      query += " ";
                      whereValue =
                          model
                              .getPortfolioLicense()
                              .getPortfolio()
                              .getPortfolioLocations()
                              .get(i13)
                              .getOwnerStatusType();
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " PortfolioLocation13.ownerStatusType = ? ";
                        counter++;
                      } else {
                        whereQuery =
                            " PortfolioLocation13.ownerStatusType = :PortfolioLocation13ownerStatusType"
                                + j
                                + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          model
                              .getPortfolioLicense()
                              .getPortfolio()
                              .getPortfolioLocations()
                              .get(i13)
                              .getOwnerStatusType();
                      query +=
                          (andCondition
                              + Facade.getStatement(
                                  statement,
                                  "PortfolioLocation13.ownerStatusType",
                                  "PortfolioLocation13ownerStatusType" + j,
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
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getPortfolioLocations()
                                .get(i13)
                                .getRegionalStatusType()
                            != null) {

                      andCondition = " AND ";
                      if (isOpenParentheses) {
                        andCondition = " ";
                      }
                      query += " ";
                      whereValue =
                          model
                              .getPortfolioLicense()
                              .getPortfolio()
                              .getPortfolioLocations()
                              .get(i13)
                              .getRegionalStatusType();
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " PortfolioLocation13.regionalStatusType = ? ";
                        counter++;
                      } else {
                        whereQuery =
                            " PortfolioLocation13.regionalStatusType = :PortfolioLocation13regionalStatusType"
                                + j
                                + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          model
                              .getPortfolioLicense()
                              .getPortfolio()
                              .getPortfolioLocations()
                              .get(i13)
                              .getRegionalStatusType();
                      query +=
                          (andCondition
                              + Facade.getStatement(
                                  statement,
                                  "PortfolioLocation13.regionalStatusType",
                                  "PortfolioLocation13regionalStatusType" + j,
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
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getPortfolioLocations()
                                .get(i13)
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
                                      .getPortfolioLicense()
                                      .getPortfolio()
                                      .getPortfolioLocations()
                                      .get(i13)
                                      .getVersion());
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " PortfolioLocation13.VERSION = ? ";
                        counter++;
                      } else {
                        whereQuery =
                            " PortfolioLocation13.version = :PortfolioLocation13version" + j + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          new java.lang.Integer(
                              ""
                                  + model
                                      .getPortfolioLicense()
                                      .getPortfolio()
                                      .getPortfolioLocations()
                                      .get(i13)
                                      .getVersion());
                      query +=
                          (andCondition
                              + Facade.getStatement(
                                  statement,
                                  "PortfolioLocation13.version",
                                  "PortfolioLocation13version" + j,
                                  mapVariable,
                                  whereQuery,
                                  whereValue,
                                  isExistRecord,
                                  isDelete,
                                  fetchType,
                                  counter));
                      whereQuery = "";
                    }
                    if (length13 > 1) {
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
                  && model.getPortfolioLicense().getPortfolio().getReceptions() != null) {
                int length22 = model.getPortfolioLicense().getPortfolio().getReceptions().size();

                if (length22 > 0) {
                  if (isOpenParentheses) {
                    query += "  ( ";
                  } else {
                    query += " AND ( ";
                  }
                  isOpenParentheses = true;

                  for (int i22 = 0; i22 < length22; i22++) {

                    java.lang.String beforeCondition22 = "";
                    java.lang.String prefix22 = " OR ";
                    if (i22 == 0) {
                      prefix22 = "";
                    }
                    if (length22 > 1) {

                      query += (prefix22 + "(");
                      isOpenParentheses = true;

                    } else {

                      query += (prefix22);
                      if (!prefix22.trim().equals("")) {
                        isOpenParentheses = false;
                      }
                    }

                    if (model
                            .getPortfolioLicense()
                            .getPortfolio()
                            .getReceptions()
                            .get(i22)
                            .getReceptionNo()
                        != null) {

                      andCondition = " AND ";
                      if (isOpenParentheses) {
                        andCondition = " ";
                      }
                      query += " ";
                      whereValue =
                          model
                              .getPortfolioLicense()
                              .getPortfolio()
                              .getReceptions()
                              .get(i22)
                              .getReceptionNo();
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " Reception22.receptionNo = ? ";
                        counter++;
                      } else {
                        whereQuery = " Reception22.receptionNo = :Reception22receptionNo" + j + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          model
                              .getPortfolioLicense()
                              .getPortfolio()
                              .getReceptions()
                              .get(i22)
                              .getReceptionNo();
                      query +=
                          (andCondition
                              + Facade.getStatement(
                                  statement,
                                  "Reception22.receptionNo",
                                  "Reception22receptionNo" + j,
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
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getReceptions()
                                .get(i22)
                                .getChannel()
                            != null) {

                      andCondition = " AND ";
                      if (isOpenParentheses) {
                        andCondition = " ";
                      }
                      query += " ";
                      whereValue =
                          model
                              .getPortfolioLicense()
                              .getPortfolio()
                              .getReceptions()
                              .get(i22)
                              .getChannel();
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " Reception22.channel = ? ";
                        counter++;
                      } else {
                        whereQuery = " Reception22.channel = :Reception22channel" + j + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          model
                              .getPortfolioLicense()
                              .getPortfolio()
                              .getReceptions()
                              .get(i22)
                              .getChannel();
                      query +=
                          (andCondition
                              + Facade.getStatement(
                                  statement,
                                  "Reception22.channel",
                                  "Reception22channel" + j,
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
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getReceptions()
                                .get(i22)
                                .getExternalResource()
                            != null) {

                      andCondition = " AND ";
                      if (isOpenParentheses) {
                        andCondition = " ";
                      }
                      query += " ";
                      whereValue =
                          model
                              .getPortfolioLicense()
                              .getPortfolio()
                              .getReceptions()
                              .get(i22)
                              .getExternalResource();
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " Reception22.externalResource = ? ";
                        counter++;
                      } else {
                        whereQuery =
                            " Reception22.externalResource = :Reception22externalResource"
                                + j
                                + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          model
                              .getPortfolioLicense()
                              .getPortfolio()
                              .getReceptions()
                              .get(i22)
                              .getExternalResource();
                      query +=
                          (andCondition
                              + Facade.getStatement(
                                  statement,
                                  "Reception22.externalResource",
                                  "Reception22externalResource" + j,
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
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getReceptions()
                                .get(i22)
                                .getReceptionTitle()
                            != null) {

                      andCondition = " AND ";
                      if (isOpenParentheses) {
                        andCondition = " ";
                      }
                      query += " ";

                      whereValue =
                          model
                                  .getPortfolioLicense()
                                  .getPortfolio()
                                  .getReceptions()
                                  .get(i22)
                                  .getReceptionTitle()
                              + "%";
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " Reception22.receptionTitle = ? ";
                        counter++;
                      } else {
                        whereQuery =
                            " Reception22.receptionTitle LIKE :Reception22receptionTitle" + j + " ";
                      }

                      isOpenParentheses = false;
                      if (isExistRecord || isDelete) {
                        if (isDelete && fetchType == FetchType.EAGER) {
                          whereQuery = " Reception22.receptionTitle = ? ";
                          counter++;
                        } else {
                          whereValue =
                              model
                                  .getPortfolioLicense()
                                  .getPortfolio()
                                  .getReceptions()
                                  .get(i22)
                                  .getReceptionTitle();
                        }
                      } else {
                        whereValue =
                            model
                                    .getPortfolioLicense()
                                    .getPortfolio()
                                    .getReceptions()
                                    .get(i22)
                                    .getReceptionTitle()
                                + "%";
                      }

                      query +=
                          (andCondition
                              + Facade.getStatement(
                                  statement,
                                  "Reception22.receptionTitle",
                                  "Reception22receptionTitle" + j,
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
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getReceptions()
                                .get(i22)
                                .getProductType()
                            != null) {

                      andCondition = " AND ";
                      if (isOpenParentheses) {
                        andCondition = " ";
                      }
                      query += " ";
                      whereValue =
                          model
                              .getPortfolioLicense()
                              .getPortfolio()
                              .getReceptions()
                              .get(i22)
                              .getProductType();
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " Reception22.productType = ? ";
                        counter++;
                      } else {
                        whereQuery = " Reception22.productType = :Reception22productType" + j + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          model
                              .getPortfolioLicense()
                              .getPortfolio()
                              .getReceptions()
                              .get(i22)
                              .getProductType();
                      query +=
                          (andCondition
                              + Facade.getStatement(
                                  statement,
                                  "Reception22.productType",
                                  "Reception22productType" + j,
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
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getReceptions()
                                .get(i22)
                                .getLongLoanType()
                            != null) {

                      andCondition = " AND ";
                      if (isOpenParentheses) {
                        andCondition = " ";
                      }
                      query += " ";
                      whereValue =
                          model
                              .getPortfolioLicense()
                              .getPortfolio()
                              .getReceptions()
                              .get(i22)
                              .getLongLoanType();
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " Reception22.longLoanType = ? ";
                        counter++;
                      } else {
                        whereQuery =
                            " Reception22.longLoanType = :Reception22longLoanType" + j + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          model
                              .getPortfolioLicense()
                              .getPortfolio()
                              .getReceptions()
                              .get(i22)
                              .getLongLoanType();
                      query +=
                          (andCondition
                              + Facade.getStatement(
                                  statement,
                                  "Reception22.longLoanType",
                                  "Reception22longLoanType" + j,
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
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getReceptions()
                                .get(i22)
                                .getFinancingPurpose()
                            != null) {

                      andCondition = " AND ";
                      if (isOpenParentheses) {
                        andCondition = " ";
                      }
                      query += " ";
                      whereValue =
                          model
                              .getPortfolioLicense()
                              .getPortfolio()
                              .getReceptions()
                              .get(i22)
                              .getFinancingPurpose();
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " Reception22.financingPurpose = ? ";
                        counter++;
                      } else {
                        whereQuery =
                            " Reception22.financingPurpose = :Reception22financingPurpose"
                                + j
                                + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          model
                              .getPortfolioLicense()
                              .getPortfolio()
                              .getReceptions()
                              .get(i22)
                              .getFinancingPurpose();
                      query +=
                          (andCondition
                              + Facade.getStatement(
                                  statement,
                                  "Reception22.financingPurpose",
                                  "Reception22financingPurpose" + j,
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
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getReceptions()
                                .get(i22)
                                .getLow141()
                            != null) {

                      andCondition = " AND ";
                      if (isOpenParentheses) {
                        andCondition = " ";
                      }
                      query += " ";
                      whereValue =
                          model
                              .getPortfolioLicense()
                              .getPortfolio()
                              .getReceptions()
                              .get(i22)
                              .getLow141();
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " Reception22.low141 = ? ";
                        counter++;
                      } else {
                        whereQuery = " Reception22.low141 = :Reception22low141" + j + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          model
                              .getPortfolioLicense()
                              .getPortfolio()
                              .getReceptions()
                              .get(i22)
                              .getLow141();
                      query +=
                          (andCondition
                              + Facade.getStatement(
                                  statement,
                                  "Reception22.low141",
                                  "Reception22low141" + j,
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
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getReceptions()
                                .get(i22)
                                .getShortTermLoanType()
                            != null) {

                      andCondition = " AND ";
                      if (isOpenParentheses) {
                        andCondition = " ";
                      }
                      query += " ";
                      whereValue =
                          model
                              .getPortfolioLicense()
                              .getPortfolio()
                              .getReceptions()
                              .get(i22)
                              .getShortTermLoanType();
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " Reception22.shortTermLoanType = ? ";
                        counter++;
                      } else {
                        whereQuery =
                            " Reception22.shortTermLoanType = :Reception22shortTermLoanType"
                                + j
                                + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          model
                              .getPortfolioLicense()
                              .getPortfolio()
                              .getReceptions()
                              .get(i22)
                              .getShortTermLoanType();
                      query +=
                          (andCondition
                              + Facade.getStatement(
                                  statement,
                                  "Reception22.shortTermLoanType",
                                  "Reception22shortTermLoanType" + j,
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
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getReceptions()
                                .get(i22)
                                .getDate()
                            != null) {

                      andCondition = " AND ";
                      if (isOpenParentheses) {
                        andCondition = " ";
                      }
                      query += " ";
                      whereValue =
                          model
                              .getPortfolioLicense()
                              .getPortfolio()
                              .getReceptions()
                              .get(i22)
                              .getDate();
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " Reception22.date1 = ? ";
                        counter++;
                      } else {
                        whereQuery = " Reception22.date = :Reception22date" + j + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          model
                              .getPortfolioLicense()
                              .getPortfolio()
                              .getReceptions()
                              .get(i22)
                              .getDate();
                      query +=
                          (andCondition
                              + Facade.getStatement(
                                  statement,
                                  "Reception22.date",
                                  "Reception22date" + j,
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
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getReceptions()
                                .get(i22)
                                .getRejLetterDate()
                            != null) {

                      andCondition = " AND ";
                      if (isOpenParentheses) {
                        andCondition = " ";
                      }
                      query += " ";
                      whereValue =
                          model
                              .getPortfolioLicense()
                              .getPortfolio()
                              .getReceptions()
                              .get(i22)
                              .getRejLetterDate();
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " Reception22.rejLetterDate = ? ";
                        counter++;
                      } else {
                        whereQuery =
                            " Reception22.rejLetterDate = :Reception22rejLetterDate" + j + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          model
                              .getPortfolioLicense()
                              .getPortfolio()
                              .getReceptions()
                              .get(i22)
                              .getRejLetterDate();
                      query +=
                          (andCondition
                              + Facade.getStatement(
                                  statement,
                                  "Reception22.rejLetterDate",
                                  "Reception22rejLetterDate" + j,
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
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getReceptions()
                                .get(i22)
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
                                  .getPortfolioLicense()
                                  .getPortfolio()
                                  .getReceptions()
                                  .get(i22)
                                  .getCustomerCode();
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " Reception22.customerCode = ? ";
                        counter++;
                      } else {
                        whereQuery =
                            " Reception22.customerCode LIKE :Reception22customerCode" + j + " ";
                      }

                      isOpenParentheses = false;
                      if (isExistRecord || isDelete) {
                        if (isDelete && fetchType == FetchType.EAGER) {
                          whereQuery = " Reception22.customerCode = ? ";
                          counter++;
                        } else {
                          whereValue =
                              model
                                  .getPortfolioLicense()
                                  .getPortfolio()
                                  .getReceptions()
                                  .get(i22)
                                  .getCustomerCode();
                        }
                      } else {
                        whereValue =
                            "%"
                                + model
                                    .getPortfolioLicense()
                                    .getPortfolio()
                                    .getReceptions()
                                    .get(i22)
                                    .getCustomerCode();
                      }

                      query +=
                          (andCondition
                              + Facade.getStatement(
                                  statement,
                                  "Reception22.customerCode",
                                  "Reception22customerCode" + j,
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
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getReceptions()
                                .get(i22)
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
                                  .getPortfolioLicense()
                                  .getPortfolio()
                                  .getReceptions()
                                  .get(i22)
                                  .getBranch()
                              + "%";
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " Reception22.branch = ? ";
                        counter++;
                      } else {
                        whereQuery = " Reception22.branch LIKE :Reception22branch" + j + " ";
                      }

                      isOpenParentheses = false;
                      if (isExistRecord || isDelete) {
                        if (isDelete && fetchType == FetchType.EAGER) {
                          whereQuery = " Reception22.branch = ? ";
                          counter++;
                        } else {
                          whereValue =
                              model
                                  .getPortfolioLicense()
                                  .getPortfolio()
                                  .getReceptions()
                                  .get(i22)
                                  .getBranch();
                        }
                      } else {
                        whereValue =
                            "%"
                                + model
                                    .getPortfolioLicense()
                                    .getPortfolio()
                                    .getReceptions()
                                    .get(i22)
                                    .getBranch()
                                + "%";
                      }

                      query +=
                          (andCondition
                              + Facade.getStatement(
                                  statement,
                                  "Reception22.branch",
                                  "Reception22branch" + j,
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
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getReceptions()
                                .get(i22)
                                .getReceptionState()
                            != null) {

                      andCondition = " AND ";
                      if (isOpenParentheses) {
                        andCondition = " ";
                      }
                      query += " ";
                      whereValue =
                          model
                              .getPortfolioLicense()
                              .getPortfolio()
                              .getReceptions()
                              .get(i22)
                              .getReceptionState();
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " Reception22.receptionState = ? ";
                        counter++;
                      } else {
                        whereQuery =
                            " Reception22.receptionState = :Reception22receptionState" + j + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          model
                              .getPortfolioLicense()
                              .getPortfolio()
                              .getReceptions()
                              .get(i22)
                              .getReceptionState();
                      query +=
                          (andCondition
                              + Facade.getStatement(
                                  statement,
                                  "Reception22.receptionState",
                                  "Reception22receptionState" + j,
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
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getReceptions()
                                .get(i22)
                                .getRepresentativeRefType()
                            != null) {

                      andCondition = " AND ";
                      if (isOpenParentheses) {
                        andCondition = " ";
                      }
                      query += " ";
                      whereValue =
                          model
                              .getPortfolioLicense()
                              .getPortfolio()
                              .getReceptions()
                              .get(i22)
                              .getRepresentativeRefType();
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " Reception22.representativeRefType = ? ";
                        counter++;
                      } else {
                        whereQuery =
                            " Reception22.representativeRefType = :Reception22representativeRefType"
                                + j
                                + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          model
                              .getPortfolioLicense()
                              .getPortfolio()
                              .getReceptions()
                              .get(i22)
                              .getRepresentativeRefType();
                      query +=
                          (andCondition
                              + Facade.getStatement(
                                  statement,
                                  "Reception22.representativeRefType",
                                  "Reception22representativeRefType" + j,
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
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getReceptions()
                                .get(i22)
                                .getApplicantCreditType()
                            != null) {

                      andCondition = " AND ";
                      if (isOpenParentheses) {
                        andCondition = " ";
                      }
                      query += " ";
                      whereValue =
                          model
                              .getPortfolioLicense()
                              .getPortfolio()
                              .getReceptions()
                              .get(i22)
                              .getApplicantCreditType();
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " Reception22.applicantCreditType = ? ";
                        counter++;
                      } else {
                        whereQuery =
                            " Reception22.applicantCreditType = :Reception22applicantCreditType"
                                + j
                                + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          model
                              .getPortfolioLicense()
                              .getPortfolio()
                              .getReceptions()
                              .get(i22)
                              .getApplicantCreditType();
                      query +=
                          (andCondition
                              + Facade.getStatement(
                                  statement,
                                  "Reception22.applicantCreditType",
                                  "Reception22applicantCreditType" + j,
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
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getReceptions()
                                .get(i22)
                                .getUnderTakeHisType()
                            != null) {

                      andCondition = " AND ";
                      if (isOpenParentheses) {
                        andCondition = " ";
                      }
                      query += " ";
                      whereValue =
                          model
                              .getPortfolioLicense()
                              .getPortfolio()
                              .getReceptions()
                              .get(i22)
                              .getUnderTakeHisType();
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " Reception22.underTakeHisType = ? ";
                        counter++;
                      } else {
                        whereQuery =
                            " Reception22.underTakeHisType = :Reception22underTakeHisType"
                                + j
                                + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          model
                              .getPortfolioLicense()
                              .getPortfolio()
                              .getReceptions()
                              .get(i22)
                              .getUnderTakeHisType();
                      query +=
                          (andCondition
                              + Facade.getStatement(
                                  statement,
                                  "Reception22.underTakeHisType",
                                  "Reception22underTakeHisType" + j,
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
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getReceptions()
                                .get(i22)
                                .getCheckRejectType()
                            != null) {

                      andCondition = " AND ";
                      if (isOpenParentheses) {
                        andCondition = " ";
                      }
                      query += " ";
                      whereValue =
                          model
                              .getPortfolioLicense()
                              .getPortfolio()
                              .getReceptions()
                              .get(i22)
                              .getCheckRejectType();
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " Reception22.checkRejectType = ? ";
                        counter++;
                      } else {
                        whereQuery =
                            " Reception22.checkRejectType = :Reception22checkRejectType" + j + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          model
                              .getPortfolioLicense()
                              .getPortfolio()
                              .getReceptions()
                              .get(i22)
                              .getCheckRejectType();
                      query +=
                          (andCondition
                              + Facade.getStatement(
                                  statement,
                                  "Reception22.checkRejectType",
                                  "Reception22checkRejectType" + j,
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
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getReceptions()
                                .get(i22)
                                .getCashFocusStatusType()
                            != null) {

                      andCondition = " AND ";
                      if (isOpenParentheses) {
                        andCondition = " ";
                      }
                      query += " ";
                      whereValue =
                          model
                              .getPortfolioLicense()
                              .getPortfolio()
                              .getReceptions()
                              .get(i22)
                              .getCashFocusStatusType();
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " Reception22.cashFocusStatusType = ? ";
                        counter++;
                      } else {
                        whereQuery =
                            " Reception22.cashFocusStatusType = :Reception22cashFocusStatusType"
                                + j
                                + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          model
                              .getPortfolioLicense()
                              .getPortfolio()
                              .getReceptions()
                              .get(i22)
                              .getCashFocusStatusType();
                      query +=
                          (andCondition
                              + Facade.getStatement(
                                  statement,
                                  "Reception22.cashFocusStatusType",
                                  "Reception22cashFocusStatusType" + j,
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
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getReceptions()
                                .get(i22)
                                .getEquation()
                            != null) {

                      andCondition = " AND ";
                      if (isOpenParentheses) {
                        andCondition = " ";
                      }
                      query += " ";
                      whereValue =
                          model
                              .getPortfolioLicense()
                              .getPortfolio()
                              .getReceptions()
                              .get(i22)
                              .getEquation();
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " Reception22.equation = ? ";
                        counter++;
                      } else {
                        whereQuery = " Reception22.equation = :Reception22equation" + j + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          model
                              .getPortfolioLicense()
                              .getPortfolio()
                              .getReceptions()
                              .get(i22)
                              .getEquation();
                      query +=
                          (andCondition
                              + Facade.getStatement(
                                  statement,
                                  "Reception22.equation",
                                  "Reception22equation" + j,
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
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getReceptions()
                                .get(i22)
                                .getIrrEstimate()
                            != null) {

                      andCondition = " AND ";
                      if (isOpenParentheses) {
                        andCondition = " ";
                      }
                      query += " ";
                      whereValue =
                          model
                              .getPortfolioLicense()
                              .getPortfolio()
                              .getReceptions()
                              .get(i22)
                              .getIrrEstimate();
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " Reception22.irrEstimate = ? ";
                        counter++;
                      } else {
                        whereQuery = " Reception22.irrEstimate = :Reception22irrEstimate" + j + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          model
                              .getPortfolioLicense()
                              .getPortfolio()
                              .getReceptions()
                              .get(i22)
                              .getIrrEstimate();
                      query +=
                          (andCondition
                              + Facade.getStatement(
                                  statement,
                                  "Reception22.irrEstimate",
                                  "Reception22irrEstimate" + j,
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
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getReceptions()
                                .get(i22)
                                .getHeadPoint()
                            != null) {

                      andCondition = " AND ";
                      if (isOpenParentheses) {
                        andCondition = " ";
                      }
                      query += " ";
                      whereValue =
                          model
                              .getPortfolioLicense()
                              .getPortfolio()
                              .getReceptions()
                              .get(i22)
                              .getHeadPoint();
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " Reception22.headPoint = ? ";
                        counter++;
                      } else {
                        whereQuery = " Reception22.headPoint = :Reception22headPoint" + j + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          model
                              .getPortfolioLicense()
                              .getPortfolio()
                              .getReceptions()
                              .get(i22)
                              .getHeadPoint();
                      query +=
                          (andCondition
                              + Facade.getStatement(
                                  statement,
                                  "Reception22.headPoint",
                                  "Reception22headPoint" + j,
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
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getReceptions()
                                .get(i22)
                                .getCashControl()
                            != null) {

                      andCondition = " AND ";
                      if (isOpenParentheses) {
                        andCondition = " ";
                      }
                      query += " ";
                      whereValue =
                          model
                              .getPortfolioLicense()
                              .getPortfolio()
                              .getReceptions()
                              .get(i22)
                              .getCashControl();
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " Reception22.cashControl = ? ";
                        counter++;
                      } else {
                        whereQuery = " Reception22.cashControl = :Reception22cashControl" + j + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          model
                              .getPortfolioLicense()
                              .getPortfolio()
                              .getReceptions()
                              .get(i22)
                              .getCashControl();
                      query +=
                          (andCondition
                              + Facade.getStatement(
                                  statement,
                                  "Reception22.cashControl",
                                  "Reception22cashControl" + j,
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
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getReceptions()
                                .get(i22)
                                .getExportMarketDsc()
                            != null) {

                      andCondition = " AND ";
                      if (isOpenParentheses) {
                        andCondition = " ";
                      }
                      query += " ";
                      whereValue =
                          model
                              .getPortfolioLicense()
                              .getPortfolio()
                              .getReceptions()
                              .get(i22)
                              .getExportMarketDsc();
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " Reception22.exportMarketDsc = ? ";
                        counter++;
                      } else {
                        whereQuery =
                            " Reception22.exportMarketDsc = :Reception22exportMarketDsc" + j + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          model
                              .getPortfolioLicense()
                              .getPortfolio()
                              .getReceptions()
                              .get(i22)
                              .getExportMarketDsc();
                      query +=
                          (andCondition
                              + Facade.getStatement(
                                  statement,
                                  "Reception22.exportMarketDsc",
                                  "Reception22exportMarketDsc" + j,
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
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getReceptions()
                                .get(i22)
                                .getInternalMarketDsc()
                            != null) {

                      andCondition = " AND ";
                      if (isOpenParentheses) {
                        andCondition = " ";
                      }
                      query += " ";
                      whereValue =
                          model
                              .getPortfolioLicense()
                              .getPortfolio()
                              .getReceptions()
                              .get(i22)
                              .getInternalMarketDsc();
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " Reception22.internalMarketDsc = ? ";
                        counter++;
                      } else {
                        whereQuery =
                            " Reception22.internalMarketDsc = :Reception22internalMarketDsc"
                                + j
                                + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          model
                              .getPortfolioLicense()
                              .getPortfolio()
                              .getReceptions()
                              .get(i22)
                              .getInternalMarketDsc();
                      query +=
                          (andCondition
                              + Facade.getStatement(
                                  statement,
                                  "Reception22.internalMarketDsc",
                                  "Reception22internalMarketDsc" + j,
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
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getReceptions()
                                .get(i22)
                                .getRowMaterialDsc()
                            != null) {

                      andCondition = " AND ";
                      if (isOpenParentheses) {
                        andCondition = " ";
                      }
                      query += " ";
                      whereValue =
                          model
                              .getPortfolioLicense()
                              .getPortfolio()
                              .getReceptions()
                              .get(i22)
                              .getRowMaterialDsc();
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " Reception22.rowMaterialDsc = ? ";
                        counter++;
                      } else {
                        whereQuery =
                            " Reception22.rowMaterialDsc = :Reception22rowMaterialDsc" + j + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          model
                              .getPortfolioLicense()
                              .getPortfolio()
                              .getReceptions()
                              .get(i22)
                              .getRowMaterialDsc();
                      query +=
                          (andCondition
                              + Facade.getStatement(
                                  statement,
                                  "Reception22.rowMaterialDsc",
                                  "Reception22rowMaterialDsc" + j,
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
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getReceptions()
                                .get(i22)
                                .getMarketProductDesc()
                            != null) {

                      andCondition = " AND ";
                      if (isOpenParentheses) {
                        andCondition = " ";
                      }
                      query += " ";
                      whereValue =
                          model
                              .getPortfolioLicense()
                              .getPortfolio()
                              .getReceptions()
                              .get(i22)
                              .getMarketProductDesc();
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " Reception22.marketProductDesc = ? ";
                        counter++;
                      } else {
                        whereQuery =
                            " Reception22.marketProductDesc = :Reception22marketProductDesc"
                                + j
                                + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          model
                              .getPortfolioLicense()
                              .getPortfolio()
                              .getReceptions()
                              .get(i22)
                              .getMarketProductDesc();
                      query +=
                          (andCondition
                              + Facade.getStatement(
                                  statement,
                                  "Reception22.marketProductDesc",
                                  "Reception22marketProductDesc" + j,
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
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getReceptions()
                                .get(i22)
                                .getProductiveEmployee()
                            != null) {

                      andCondition = " AND ";
                      if (isOpenParentheses) {
                        andCondition = " ";
                      }
                      query += " ";
                      whereValue =
                          model
                              .getPortfolioLicense()
                              .getPortfolio()
                              .getReceptions()
                              .get(i22)
                              .getProductiveEmployee();
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " Reception22.productiveEmployee = ? ";
                        counter++;
                      } else {
                        whereQuery =
                            " Reception22.productiveEmployee = :Reception22productiveEmployee"
                                + j
                                + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          model
                              .getPortfolioLicense()
                              .getPortfolio()
                              .getReceptions()
                              .get(i22)
                              .getProductiveEmployee();
                      query +=
                          (andCondition
                              + Facade.getStatement(
                                  statement,
                                  "Reception22.productiveEmployee",
                                  "Reception22productiveEmployee" + j,
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
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getReceptions()
                                .get(i22)
                                .getNonProductiveEmployee()
                            != null) {

                      andCondition = " AND ";
                      if (isOpenParentheses) {
                        andCondition = " ";
                      }
                      query += " ";
                      whereValue =
                          model
                              .getPortfolioLicense()
                              .getPortfolio()
                              .getReceptions()
                              .get(i22)
                              .getNonProductiveEmployee();
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " Reception22.nonProductiveEmployee = ? ";
                        counter++;
                      } else {
                        whereQuery =
                            " Reception22.nonProductiveEmployee = :Reception22nonProductiveEmployee"
                                + j
                                + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          model
                              .getPortfolioLicense()
                              .getPortfolio()
                              .getReceptions()
                              .get(i22)
                              .getNonProductiveEmployee();
                      query +=
                          (andCondition
                              + Facade.getStatement(
                                  statement,
                                  "Reception22.nonProductiveEmployee",
                                  "Reception22nonProductiveEmployee" + j,
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
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getReceptions()
                                .get(i22)
                                .getForecastProductiveEmployee()
                            != null) {

                      andCondition = " AND ";
                      if (isOpenParentheses) {
                        andCondition = " ";
                      }
                      query += " ";
                      whereValue =
                          model
                              .getPortfolioLicense()
                              .getPortfolio()
                              .getReceptions()
                              .get(i22)
                              .getForecastProductiveEmployee();
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " Reception22.forecastProductiveEmployee = ? ";
                        counter++;
                      } else {
                        whereQuery =
                            " Reception22.forecastProductiveEmployee = :Reception22forecastProductiveEmployee"
                                + j
                                + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          model
                              .getPortfolioLicense()
                              .getPortfolio()
                              .getReceptions()
                              .get(i22)
                              .getForecastProductiveEmployee();
                      query +=
                          (andCondition
                              + Facade.getStatement(
                                  statement,
                                  "Reception22.forecastProductiveEmployee",
                                  "Reception22forecastProductiveEmployee" + j,
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
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getReceptions()
                                .get(i22)
                                .getForecastNonProductiveEmployee()
                            != null) {

                      andCondition = " AND ";
                      if (isOpenParentheses) {
                        andCondition = " ";
                      }
                      query += " ";
                      whereValue =
                          model
                              .getPortfolioLicense()
                              .getPortfolio()
                              .getReceptions()
                              .get(i22)
                              .getForecastNonProductiveEmployee();
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " Reception22.forecastNonProductiveEmployee = ? ";
                        counter++;
                      } else {
                        whereQuery =
                            " Reception22.forecastNonProductiveEmployee = :Reception22forecastNonProductiveEmployee"
                                + j
                                + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          model
                              .getPortfolioLicense()
                              .getPortfolio()
                              .getReceptions()
                              .get(i22)
                              .getForecastNonProductiveEmployee();
                      query +=
                          (andCondition
                              + Facade.getStatement(
                                  statement,
                                  "Reception22.forecastNonProductiveEmployee",
                                  "Reception22forecastNonProductiveEmployee" + j,
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
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getReceptions()
                                .get(i22)
                                .getCusPrePayAmnt()
                            != null) {

                      andCondition = " AND ";
                      if (isOpenParentheses) {
                        andCondition = " ";
                      }
                      query += " ";
                      whereValue =
                          model
                              .getPortfolioLicense()
                              .getPortfolio()
                              .getReceptions()
                              .get(i22)
                              .getCusPrePayAmnt();
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " Reception22.cusPrePayAmnt = ? ";
                        counter++;
                      } else {
                        whereQuery =
                            " Reception22.cusPrePayAmnt = :Reception22cusPrePayAmnt" + j + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          model
                              .getPortfolioLicense()
                              .getPortfolio()
                              .getReceptions()
                              .get(i22)
                              .getCusPrePayAmnt();
                      query +=
                          (andCondition
                              + Facade.getStatement(
                                  statement,
                                  "Reception22.cusPrePayAmnt",
                                  "Reception22cusPrePayAmnt" + j,
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
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getReceptions()
                                .get(i22)
                                .getFinancialUser()
                            != null) {

                      andCondition = " AND ";
                      if (isOpenParentheses) {
                        andCondition = " ";
                      }
                      query += " ";
                      whereValue =
                          model
                              .getPortfolioLicense()
                              .getPortfolio()
                              .getReceptions()
                              .get(i22)
                              .getFinancialUser();
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " Reception22.financialUser = ? ";
                        counter++;
                      } else {
                        whereQuery =
                            " Reception22.financialUser = :Reception22financialUser" + j + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          model
                              .getPortfolioLicense()
                              .getPortfolio()
                              .getReceptions()
                              .get(i22)
                              .getFinancialUser();
                      query +=
                          (andCondition
                              + Facade.getStatement(
                                  statement,
                                  "Reception22.financialUser",
                                  "Reception22financialUser" + j,
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
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getReceptions()
                                .get(i22)
                                .getMarketUser()
                            != null) {

                      andCondition = " AND ";
                      if (isOpenParentheses) {
                        andCondition = " ";
                      }
                      query += " ";
                      whereValue =
                          model
                              .getPortfolioLicense()
                              .getPortfolio()
                              .getReceptions()
                              .get(i22)
                              .getMarketUser();
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " Reception22.marketUser = ? ";
                        counter++;
                      } else {
                        whereQuery = " Reception22.marketUser = :Reception22marketUser" + j + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          model
                              .getPortfolioLicense()
                              .getPortfolio()
                              .getReceptions()
                              .get(i22)
                              .getMarketUser();
                      query +=
                          (andCondition
                              + Facade.getStatement(
                                  statement,
                                  "Reception22.marketUser",
                                  "Reception22marketUser" + j,
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
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getReceptions()
                                .get(i22)
                                .getTechnicalUser()
                            != null) {

                      andCondition = " AND ";
                      if (isOpenParentheses) {
                        andCondition = " ";
                      }
                      query += " ";
                      whereValue =
                          model
                              .getPortfolioLicense()
                              .getPortfolio()
                              .getReceptions()
                              .get(i22)
                              .getTechnicalUser();
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " Reception22.technicalUser = ? ";
                        counter++;
                      } else {
                        whereQuery =
                            " Reception22.technicalUser = :Reception22technicalUser" + j + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          model
                              .getPortfolioLicense()
                              .getPortfolio()
                              .getReceptions()
                              .get(i22)
                              .getTechnicalUser();
                      query +=
                          (andCondition
                              + Facade.getStatement(
                                  statement,
                                  "Reception22.technicalUser",
                                  "Reception22technicalUser" + j,
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
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getReceptions()
                                .get(i22)
                                .getCurrentAssetReceptionNo()
                            != null) {

                      andCondition = " AND ";
                      if (isOpenParentheses) {
                        andCondition = " ";
                      }
                      query += " ";
                      whereValue =
                          model
                              .getPortfolioLicense()
                              .getPortfolio()
                              .getReceptions()
                              .get(i22)
                              .getCurrentAssetReceptionNo();
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " Reception22.currentAssetReceptionNo = ? ";
                        counter++;
                      } else {
                        whereQuery =
                            " Reception22.currentAssetReceptionNo = :Reception22currentAssetReceptionNo"
                                + j
                                + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          model
                              .getPortfolioLicense()
                              .getPortfolio()
                              .getReceptions()
                              .get(i22)
                              .getCurrentAssetReceptionNo();
                      query +=
                          (andCondition
                              + Facade.getStatement(
                                  statement,
                                  "Reception22.currentAssetReceptionNo",
                                  "Reception22currentAssetReceptionNo" + j,
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
                                .getPortfolioLicense()
                                .getPortfolio()
                                .getReceptions()
                                .get(i22)
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
                                      .getPortfolioLicense()
                                      .getPortfolio()
                                      .getReceptions()
                                      .get(i22)
                                      .getVersion());
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " Reception22.VERSION = ? ";
                        counter++;
                      } else {
                        whereQuery = " Reception22.version = :Reception22version" + j + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          new java.lang.Integer(
                              ""
                                  + model
                                      .getPortfolioLicense()
                                      .getPortfolio()
                                      .getReceptions()
                                      .get(i22)
                                      .getVersion());
                      query +=
                          (andCondition
                              + Facade.getStatement(
                                  statement,
                                  "Reception22.version",
                                  "Reception22version" + j,
                                  mapVariable,
                                  whereQuery,
                                  whereValue,
                                  isExistRecord,
                                  isDelete,
                                  fetchType,
                                  counter));
                      whereQuery = "";
                    }
                    if (length22 > 1) {
                      query += " ) ";
                    }
                  }

                  query += ")";
                  isOpenParentheses = false;
                }
              }
              if (!isDelete && model.getPortfolioLicense().getPortfolio().getAddress() != null) {
                if (!isDelete
                    && true
                    && model.getPortfolioLicense().getPortfolio().getAddress().getAddressLine1()
                        != null) {

                  andCondition = " AND ";
                  if (isOpenParentheses) {
                    andCondition = " ";
                  }
                  query += " ";
                  if (isDelete && fetchType == FetchType.EAGER) {
                    whereQuery = " Portfolio6.addressLine1 = ? ";
                    counter++;
                  } else {
                    whereQuery =
                        " Portfolio6.address.addressLine1 = :Portfolio6addressaddressLine1"
                            + j
                            + " ";
                  }

                  isOpenParentheses = false;
                  whereValue =
                      model.getPortfolioLicense().getPortfolio().getAddress().getAddressLine1();
                  query +=
                      (andCondition
                          + Facade.getStatement(
                              statement,
                              "Portfolio6.address.addressLine1",
                              "Portfolio6addressaddressLine1" + j,
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
                    && model.getPortfolioLicense().getPortfolio().getAddress().getAddressLine2()
                        != null) {

                  andCondition = " AND ";
                  if (isOpenParentheses) {
                    andCondition = " ";
                  }
                  query += " ";
                  if (isDelete && fetchType == FetchType.EAGER) {
                    whereQuery = " Portfolio6.addressLine2 = ? ";
                    counter++;
                  } else {
                    whereQuery =
                        " Portfolio6.address.addressLine2 = :Portfolio6addressaddressLine2"
                            + j
                            + " ";
                  }

                  isOpenParentheses = false;
                  whereValue =
                      model.getPortfolioLicense().getPortfolio().getAddress().getAddressLine2();
                  query +=
                      (andCondition
                          + Facade.getStatement(
                              statement,
                              "Portfolio6.address.addressLine2",
                              "Portfolio6addressaddressLine2" + j,
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
                    && model.getPortfolioLicense().getPortfolio().getAddress().getCity() != null) {

                  andCondition = " AND ";
                  if (isOpenParentheses) {
                    andCondition = " ";
                  }
                  query += " ";
                  if (isDelete && fetchType == FetchType.EAGER) {
                    whereQuery = " Portfolio6.city = ? ";
                    counter++;
                  } else {
                    whereQuery = " Portfolio6.address.city = :Portfolio6addresscity" + j + " ";
                  }

                  isOpenParentheses = false;
                  whereValue = model.getPortfolioLicense().getPortfolio().getAddress().getCity();
                  query +=
                      (andCondition
                          + Facade.getStatement(
                              statement,
                              "Portfolio6.address.city",
                              "Portfolio6addresscity" + j,
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
                    && model.getPortfolioLicense().getPortfolio().getAddress().getState() != null) {

                  andCondition = " AND ";
                  if (isOpenParentheses) {
                    andCondition = " ";
                  }
                  query += " ";
                  if (isDelete && fetchType == FetchType.EAGER) {
                    whereQuery = " Portfolio6.state = ? ";
                    counter++;
                  } else {
                    whereQuery = " Portfolio6.address.state = :Portfolio6addressstate" + j + " ";
                  }

                  isOpenParentheses = false;
                  whereValue = model.getPortfolioLicense().getPortfolio().getAddress().getState();
                  query +=
                      (andCondition
                          + Facade.getStatement(
                              statement,
                              "Portfolio6.address.state",
                              "Portfolio6addressstate" + j,
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
                    && model.getPortfolioLicense().getPortfolio().getAddress().getCountry()
                        != null) {

                  andCondition = " AND ";
                  if (isOpenParentheses) {
                    andCondition = " ";
                  }
                  query += " ";
                  if (isDelete && fetchType == FetchType.EAGER) {
                    whereQuery = " Portfolio6.country = ? ";
                    counter++;
                  } else {
                    whereQuery =
                        " Portfolio6.address.country = :Portfolio6addresscountry" + j + " ";
                  }

                  isOpenParentheses = false;
                  whereValue = model.getPortfolioLicense().getPortfolio().getAddress().getCountry();
                  query +=
                      (andCondition
                          + Facade.getStatement(
                              statement,
                              "Portfolio6.address.country",
                              "Portfolio6addresscountry" + j,
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
                    && model.getPortfolioLicense().getPortfolio().getAddress().getZipCode()
                        != null) {

                  andCondition = " AND ";
                  if (isOpenParentheses) {
                    andCondition = " ";
                  }
                  query += " ";
                  if (isDelete && fetchType == FetchType.EAGER) {
                    whereQuery = " Portfolio6.zipCode = ? ";
                    counter++;
                  } else {
                    whereQuery =
                        " Portfolio6.address.zipCode = :Portfolio6addresszipCode" + j + " ";
                  }

                  isOpenParentheses = false;
                  whereValue = model.getPortfolioLicense().getPortfolio().getAddress().getZipCode();
                  query +=
                      (andCondition
                          + Facade.getStatement(
                              statement,
                              "Portfolio6.address.zipCode",
                              "Portfolio6addresszipCode" + j,
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
                  && model.getPortfolioLicense().getPortfolio().getCustomerId() != null) {

                andCondition = " AND ";
                if (isOpenParentheses) {
                  andCondition = " ";
                }
                query += " ";
                if (isDelete && fetchType == FetchType.EAGER) {
                  whereQuery = " Portfolio6.customerId = ? ";
                  counter++;
                } else {
                  whereQuery = " Portfolio6.customerId = :Portfolio6customerId" + j + " ";
                }

                isOpenParentheses = false;
                whereValue = model.getPortfolioLicense().getPortfolio().getCustomerId();
                query +=
                    (andCondition
                        + Facade.getStatement(
                            statement,
                            "Portfolio6.customerId",
                            "Portfolio6customerId" + j,
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
                  && model.getPortfolioLicense().getPortfolio().getTitle() != null) {

                andCondition = " AND ";
                if (isOpenParentheses) {
                  andCondition = " ";
                }
                query += " ";
                if (isDelete && fetchType == FetchType.EAGER) {
                  whereQuery = " Portfolio6.title = ? ";
                  counter++;
                } else {
                  whereQuery = " Portfolio6.title = :Portfolio6title" + j + " ";
                }

                isOpenParentheses = false;
                whereValue = model.getPortfolioLicense().getPortfolio().getTitle();
                query +=
                    (andCondition
                        + Facade.getStatement(
                            statement,
                            "Portfolio6.title",
                            "Portfolio6title" + j,
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
                  && model.getPortfolioLicense().getPortfolio().getBranchCode() != null) {

                andCondition = " AND ";
                if (isOpenParentheses) {
                  andCondition = " ";
                }
                query += " ";
                if (isDelete && fetchType == FetchType.EAGER) {
                  whereQuery = " Portfolio6.branchCode = ? ";
                  counter++;
                } else {
                  whereQuery = " Portfolio6.branchCode = :Portfolio6branchCode" + j + " ";
                }

                isOpenParentheses = false;
                whereValue = model.getPortfolioLicense().getPortfolio().getBranchCode();
                query +=
                    (andCondition
                        + Facade.getStatement(
                            statement,
                            "Portfolio6.branchCode",
                            "Portfolio6branchCode" + j,
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
                  && model.getPortfolioLicense().getPortfolio().getEconomicType() != null) {

                andCondition = " AND ";
                if (isOpenParentheses) {
                  andCondition = " ";
                }
                query += " ";
                if (isDelete && fetchType == FetchType.EAGER) {
                  whereQuery = " Portfolio6.economicType = ? ";
                  counter++;
                } else {
                  whereQuery = " Portfolio6.economicType = :Portfolio6economicType" + j + " ";
                }

                isOpenParentheses = false;
                whereValue = model.getPortfolioLicense().getPortfolio().getEconomicType();
                query +=
                    (andCondition
                        + Facade.getStatement(
                            statement,
                            "Portfolio6.economicType",
                            "Portfolio6economicType" + j,
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
                  && model.getPortfolioLicense().getPortfolio().getIndustryType() != null) {

                andCondition = " AND ";
                if (isOpenParentheses) {
                  andCondition = " ";
                }
                query += " ";
                if (isDelete && fetchType == FetchType.EAGER) {
                  whereQuery = " Portfolio6.industryType = ? ";
                  counter++;
                } else {
                  whereQuery = " Portfolio6.industryType = :Portfolio6industryType" + j + " ";
                }

                isOpenParentheses = false;
                whereValue = model.getPortfolioLicense().getPortfolio().getIndustryType();
                query +=
                    (andCondition
                        + Facade.getStatement(
                            statement,
                            "Portfolio6.industryType",
                            "Portfolio6industryType" + j,
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
                  && model.getPortfolioLicense().getPortfolio().getTechnologyDescription()
                      != null) {

                andCondition = " AND ";
                if (isOpenParentheses) {
                  andCondition = " ";
                }
                query += " ";
                if (isDelete && fetchType == FetchType.EAGER) {
                  whereQuery = " Portfolio6.techDescription = ? ";
                  counter++;
                } else {
                  whereQuery =
                      " Portfolio6.technologyDescription = :Portfolio6technologyDescription"
                          + j
                          + " ";
                }

                isOpenParentheses = false;
                whereValue = model.getPortfolioLicense().getPortfolio().getTechnologyDescription();
                query +=
                    (andCondition
                        + Facade.getStatement(
                            statement,
                            "Portfolio6.technologyDescription",
                            "Portfolio6technologyDescription" + j,
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
                  && model.getPortfolioLicense().getPortfolio().getStateType() != null) {

                andCondition = " AND ";
                if (isOpenParentheses) {
                  andCondition = " ";
                }
                query += " ";
                if (isDelete && fetchType == FetchType.EAGER) {
                  whereQuery = " Portfolio6.stateType = ? ";
                  counter++;
                } else {
                  whereQuery = " Portfolio6.stateType = :Portfolio6stateType" + j + " ";
                }

                isOpenParentheses = false;
                whereValue = model.getPortfolioLicense().getPortfolio().getStateType();
                query +=
                    (andCondition
                        + Facade.getStatement(
                            statement,
                            "Portfolio6.stateType",
                            "Portfolio6stateType" + j,
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
                  && model.getPortfolioLicense().getPortfolio().getLifeType() != null) {

                andCondition = " AND ";
                if (isOpenParentheses) {
                  andCondition = " ";
                }
                query += " ";
                if (isDelete && fetchType == FetchType.EAGER) {
                  whereQuery = " Portfolio6.lifeType = ? ";
                  counter++;
                } else {
                  whereQuery = " Portfolio6.lifeType = :Portfolio6lifeType" + j + " ";
                }

                isOpenParentheses = false;
                whereValue = model.getPortfolioLicense().getPortfolio().getLifeType();
                query +=
                    (andCondition
                        + Facade.getStatement(
                            statement,
                            "Portfolio6.lifeType",
                            "Portfolio6lifeType" + j,
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
                  && model.getPortfolioLicense().getPortfolio().getStartDate() != null) {

                andCondition = " AND ";
                if (isOpenParentheses) {
                  andCondition = " ";
                }
                query += " ";
                if (isDelete && fetchType == FetchType.EAGER) {
                  whereQuery = " Portfolio6.startDate = ? ";
                  counter++;
                } else {
                  whereQuery = " Portfolio6.startDate = :Portfolio6startDate" + j + " ";
                }

                isOpenParentheses = false;
                whereValue = model.getPortfolioLicense().getPortfolio().getStartDate();
                query +=
                    (andCondition
                        + Facade.getStatement(
                            statement,
                            "Portfolio6.startDate",
                            "Portfolio6startDate" + j,
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
                  && model.getPortfolioLicense().getPortfolio().getEndDate() != null) {

                andCondition = " AND ";
                if (isOpenParentheses) {
                  andCondition = " ";
                }
                query += " ";
                if (isDelete && fetchType == FetchType.EAGER) {
                  whereQuery = " Portfolio6.endDate = ? ";
                  counter++;
                } else {
                  whereQuery = " Portfolio6.endDate = :Portfolio6endDate" + j + " ";
                }

                isOpenParentheses = false;
                whereValue = model.getPortfolioLicense().getPortfolio().getEndDate();
                query +=
                    (andCondition
                        + Facade.getStatement(
                            statement,
                            "Portfolio6.endDate",
                            "Portfolio6endDate" + j,
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
                  && model.getPortfolioLicense().getPortfolio().getRequestDate() != null) {

                andCondition = " AND ";
                if (isOpenParentheses) {
                  andCondition = " ";
                }
                query += " ";
                if (isDelete && fetchType == FetchType.EAGER) {
                  whereQuery = " Portfolio6.requestDate = ? ";
                  counter++;
                } else {
                  whereQuery = " Portfolio6.requestDate = :Portfolio6requestDate" + j + " ";
                }

                isOpenParentheses = false;
                whereValue = model.getPortfolioLicense().getPortfolio().getRequestDate();
                query +=
                    (andCondition
                        + Facade.getStatement(
                            statement,
                            "Portfolio6.requestDate",
                            "Portfolio6requestDate" + j,
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
                  && model.getPortfolioLicense().getPortfolio().getCapitalAMT() != null) {

                andCondition = " AND ";
                if (isOpenParentheses) {
                  andCondition = " ";
                }
                query += " ";
                if (isDelete && fetchType == FetchType.EAGER) {
                  whereQuery = " Portfolio6.capitalAMT = ? ";
                  counter++;
                } else {
                  whereQuery = " Portfolio6.capitalAMT = :Portfolio6capitalAMT" + j + " ";
                }

                isOpenParentheses = false;
                whereValue = model.getPortfolioLicense().getPortfolio().getCapitalAMT();
                query +=
                    (andCondition
                        + Facade.getStatement(
                            statement,
                            "Portfolio6.capitalAMT",
                            "Portfolio6capitalAMT" + j,
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
                  && model.getPortfolioLicense().getPortfolio().getInvestableAMT() != null) {

                andCondition = " AND ";
                if (isOpenParentheses) {
                  andCondition = " ";
                }
                query += " ";
                if (isDelete && fetchType == FetchType.EAGER) {
                  whereQuery = " Portfolio6.investableAMT = ? ";
                  counter++;
                } else {
                  whereQuery = " Portfolio6.investableAMT = :Portfolio6investableAMT" + j + " ";
                }

                isOpenParentheses = false;
                whereValue = model.getPortfolioLicense().getPortfolio().getInvestableAMT();
                query +=
                    (andCondition
                        + Facade.getStatement(
                            statement,
                            "Portfolio6.investableAMT",
                            "Portfolio6investableAMT" + j,
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
                  && model.getPortfolioLicense().getPortfolio().getAssessdAMT() != null) {

                andCondition = " AND ";
                if (isOpenParentheses) {
                  andCondition = " ";
                }
                query += " ";
                if (isDelete && fetchType == FetchType.EAGER) {
                  whereQuery = " Portfolio6.assessdAMT = ? ";
                  counter++;
                } else {
                  whereQuery = " Portfolio6.assessdAMT = :Portfolio6assessdAMT" + j + " ";
                }

                isOpenParentheses = false;
                whereValue = model.getPortfolioLicense().getPortfolio().getAssessdAMT();
                query +=
                    (andCondition
                        + Facade.getStatement(
                            statement,
                            "Portfolio6.assessdAMT",
                            "Portfolio6assessdAMT" + j,
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
                  && model.getPortfolioLicense().getPortfolio().getBankAPPRInvestAMT() != null) {

                andCondition = " AND ";
                if (isOpenParentheses) {
                  andCondition = " ";
                }
                query += " ";
                if (isDelete && fetchType == FetchType.EAGER) {
                  whereQuery = " Portfolio6.bankAPPRInvestAMT = ? ";
                  counter++;
                } else {
                  whereQuery =
                      " Portfolio6.bankAPPRInvestAMT = :Portfolio6bankAPPRInvestAMT" + j + " ";
                }

                isOpenParentheses = false;
                whereValue = model.getPortfolioLicense().getPortfolio().getBankAPPRInvestAMT();
                query +=
                    (andCondition
                        + Facade.getStatement(
                            statement,
                            "Portfolio6.bankAPPRInvestAMT",
                            "Portfolio6bankAPPRInvestAMT" + j,
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
                  && model.getPortfolioLicense().getPortfolio().getReagent() != null) {

                andCondition = " AND ";
                if (isOpenParentheses) {
                  andCondition = " ";
                }
                query += " ";
                if (isDelete && fetchType == FetchType.EAGER) {
                  whereQuery = " Portfolio6.reagent = ? ";
                  counter++;
                } else {
                  whereQuery = " Portfolio6.reagent = :Portfolio6reagent" + j + " ";
                }

                isOpenParentheses = false;
                whereValue = model.getPortfolioLicense().getPortfolio().getReagent();
                query +=
                    (andCondition
                        + Facade.getStatement(
                            statement,
                            "Portfolio6.reagent",
                            "Portfolio6reagent" + j,
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
                  && model.getPortfolioLicense().getPortfolio().getBusinessId() != null) {

                andCondition = " AND ";
                if (isOpenParentheses) {
                  andCondition = " ";
                }
                query += " ";
                if (isDelete && fetchType == FetchType.EAGER) {
                  whereQuery = " Portfolio6.businessId = ? ";
                  counter++;
                } else {
                  whereQuery = " Portfolio6.businessId = :Portfolio6businessId" + j + " ";
                }

                isOpenParentheses = false;
                whereValue = model.getPortfolioLicense().getPortfolio().getBusinessId();
                query +=
                    (andCondition
                        + Facade.getStatement(
                            statement,
                            "Portfolio6.businessId",
                            "Portfolio6businessId" + j,
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
                  && model.getPortfolioLicense().getPortfolio().getVersion() != 0) {

                andCondition = " AND ";
                if (isOpenParentheses) {
                  andCondition = " ";
                }
                query += " ";
                if (isDelete && fetchType == FetchType.EAGER) {
                  whereQuery = " Portfolio6.VERSION = ? ";
                  counter++;
                } else {
                  whereQuery = " Portfolio6.version = :Portfolio6version" + j + " ";
                }

                isOpenParentheses = false;
                whereValue =
                    new java.lang.Integer(
                        "" + model.getPortfolioLicense().getPortfolio().getVersion());
                query +=
                    (andCondition
                        + Facade.getStatement(
                            statement,
                            "Portfolio6.version",
                            "Portfolio6version" + j,
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
            if (!isExistRecord && !isDelete && model.getPortfolioLicense().getFile() != null) {

              if (model.getPortfolioLicense().getFile().getId() != null) {

                andCondition = " AND ";
                if (isOpenParentheses) {
                  andCondition = " ";
                }
                query += " ";
                if (isDelete && fetchType == FetchType.EAGER) {
                  whereQuery = " File24.l4610flmtdataid = ? ";
                  counter++;
                } else {
                  whereQuery = " File24.id = :File24id" + j + " ";
                }

                isOpenParentheses = false;
                whereValue = model.getPortfolioLicense().getFile().getId();
                query +=
                    (andCondition
                        + Facade.getStatement(
                            statement,
                            "File24.id",
                            "File24id" + j,
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
                  && model.getPortfolioLicense().getFile().getExtension() != null) {

                andCondition = " AND ";
                if (isOpenParentheses) {
                  andCondition = " ";
                }
                query += " ";
                if (isDelete && fetchType == FetchType.EAGER) {
                  whereQuery = " File24.l4610extension = ? ";
                  counter++;
                } else {
                  whereQuery = " File24.extension = :File24extension" + j + " ";
                }

                isOpenParentheses = false;
                whereValue = model.getPortfolioLicense().getFile().getExtension();
                query +=
                    (andCondition
                        + Facade.getStatement(
                            statement,
                            "File24.extension",
                            "File24extension" + j,
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
                  && model.getPortfolioLicense().getFile().getVersion() != 0) {

                andCondition = " AND ";
                if (isOpenParentheses) {
                  andCondition = " ";
                }
                query += " ";
                if (isDelete && fetchType == FetchType.EAGER) {
                  whereQuery = " File24.VERSION = ? ";
                  counter++;
                } else {
                  whereQuery = " File24.version = :File24version" + j + " ";
                }

                isOpenParentheses = false;
                whereValue =
                    new java.lang.Integer("" + model.getPortfolioLicense().getFile().getVersion());
                query +=
                    (andCondition
                        + Facade.getStatement(
                            statement,
                            "File24.version",
                            "File24version" + j,
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
                && model.getPortfolioLicense().getLicenseDetails() != null) {
              int length25 = model.getPortfolioLicense().getLicenseDetails().size();

              if (length25 > 0) {
                if (isOpenParentheses) {
                  query += "  ( ";
                } else {
                  query += " AND ( ";
                }
                isOpenParentheses = true;

                for (int i25 = 0; i25 < length25; i25++) {

                  java.lang.String beforeCondition25 = "";
                  java.lang.String prefix25 = " OR ";
                  if (i25 == 0) {
                    prefix25 = "";
                  }
                  if (length25 > 1) {

                    query += (prefix25 + "(");
                    isOpenParentheses = true;

                  } else {

                    query += (prefix25);
                    if (!prefix25.trim().equals("")) {
                      isOpenParentheses = false;
                    }
                  }

                  if (model.getPortfolioLicense().getLicenseDetails().get(i25).getLicenseDetailId()
                      != null) {

                    andCondition = " AND ";
                    if (isOpenParentheses) {
                      andCondition = " ";
                    }
                    query += " ";
                    whereValue =
                        model
                            .getPortfolioLicense()
                            .getLicenseDetails()
                            .get(i25)
                            .getLicenseDetailId();
                    if (isDelete && fetchType == FetchType.EAGER) {
                      whereQuery = " LicenseDetails25.id = ? ";
                      counter++;
                    } else {
                      whereQuery =
                          " LicenseDetails25.licenseDetailId = :LicenseDetails25licenseDetailId"
                              + j
                              + " ";
                    }

                    isOpenParentheses = false;
                    whereValue =
                        model
                            .getPortfolioLicense()
                            .getLicenseDetails()
                            .get(i25)
                            .getLicenseDetailId();
                    query +=
                        (andCondition
                            + Facade.getStatement(
                                statement,
                                "LicenseDetails25.licenseDetailId",
                                "LicenseDetails25licenseDetailId" + j,
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
                      && model.getPortfolioLicense().getLicenseDetails().get(i25).getFile()
                          != null) {

                    if (model.getPortfolioLicense().getLicenseDetails().get(i25).getFile().getId()
                        != null) {

                      andCondition = " AND ";
                      if (isOpenParentheses) {
                        andCondition = " ";
                      }
                      query += " ";
                      whereValue =
                          model
                              .getPortfolioLicense()
                              .getLicenseDetails()
                              .get(i25)
                              .getFile()
                              .getId();
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " File27.l4610flmtdataid = ? ";
                        counter++;
                      } else {
                        whereQuery = " File27.id = :File27id" + j + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          model
                              .getPortfolioLicense()
                              .getLicenseDetails()
                              .get(i25)
                              .getFile()
                              .getId();
                      query +=
                          (andCondition
                              + Facade.getStatement(
                                  statement,
                                  "File27.id",
                                  "File27id" + j,
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
                                .getPortfolioLicense()
                                .getLicenseDetails()
                                .get(i25)
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
                              .getPortfolioLicense()
                              .getLicenseDetails()
                              .get(i25)
                              .getFile()
                              .getExtension();
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " File27.l4610extension = ? ";
                        counter++;
                      } else {
                        whereQuery = " File27.extension = :File27extension" + j + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          model
                              .getPortfolioLicense()
                              .getLicenseDetails()
                              .get(i25)
                              .getFile()
                              .getExtension();
                      query +=
                          (andCondition
                              + Facade.getStatement(
                                  statement,
                                  "File27.extension",
                                  "File27extension" + j,
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
                                .getPortfolioLicense()
                                .getLicenseDetails()
                                .get(i25)
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
                                      .getPortfolioLicense()
                                      .getLicenseDetails()
                                      .get(i25)
                                      .getFile()
                                      .getVersion());
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " File27.VERSION = ? ";
                        counter++;
                      } else {
                        whereQuery = " File27.version = :File27version" + j + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          new java.lang.Integer(
                              ""
                                  + model
                                      .getPortfolioLicense()
                                      .getLicenseDetails()
                                      .get(i25)
                                      .getFile()
                                      .getVersion());
                      query +=
                          (andCondition
                              + Facade.getStatement(
                                  statement,
                                  "File27.version",
                                  "File27version" + j,
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
                      && model.getPortfolioLicense().getLicenseDetails().get(i25).getAddress()
                          != null) {
                    if (!isDelete
                        && true
                        && model
                                .getPortfolioLicense()
                                .getLicenseDetails()
                                .get(i25)
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
                              .getPortfolioLicense()
                              .getLicenseDetails()
                              .get(i25)
                              .getAddress()
                              .getAddressLine1();
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " LicenseDetails25.addressLine1 = ? ";
                        counter++;
                      } else {
                        whereQuery =
                            " LicenseDetails25.address.addressLine1 = :LicenseDetails25addressaddressLine1"
                                + j
                                + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          model
                              .getPortfolioLicense()
                              .getLicenseDetails()
                              .get(i25)
                              .getAddress()
                              .getAddressLine1();
                      query +=
                          (andCondition
                              + Facade.getStatement(
                                  statement,
                                  "LicenseDetails25.address.addressLine1",
                                  "LicenseDetails25addressaddressLine1" + j,
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
                                .getPortfolioLicense()
                                .getLicenseDetails()
                                .get(i25)
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
                              .getPortfolioLicense()
                              .getLicenseDetails()
                              .get(i25)
                              .getAddress()
                              .getAddressLine2();
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " LicenseDetails25.addressLine2 = ? ";
                        counter++;
                      } else {
                        whereQuery =
                            " LicenseDetails25.address.addressLine2 = :LicenseDetails25addressaddressLine2"
                                + j
                                + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          model
                              .getPortfolioLicense()
                              .getLicenseDetails()
                              .get(i25)
                              .getAddress()
                              .getAddressLine2();
                      query +=
                          (andCondition
                              + Facade.getStatement(
                                  statement,
                                  "LicenseDetails25.address.addressLine2",
                                  "LicenseDetails25addressaddressLine2" + j,
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
                                .getPortfolioLicense()
                                .getLicenseDetails()
                                .get(i25)
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
                              .getPortfolioLicense()
                              .getLicenseDetails()
                              .get(i25)
                              .getAddress()
                              .getCity();
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " LicenseDetails25.city = ? ";
                        counter++;
                      } else {
                        whereQuery =
                            " LicenseDetails25.address.city = :LicenseDetails25addresscity"
                                + j
                                + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          model
                              .getPortfolioLicense()
                              .getLicenseDetails()
                              .get(i25)
                              .getAddress()
                              .getCity();
                      query +=
                          (andCondition
                              + Facade.getStatement(
                                  statement,
                                  "LicenseDetails25.address.city",
                                  "LicenseDetails25addresscity" + j,
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
                                .getPortfolioLicense()
                                .getLicenseDetails()
                                .get(i25)
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
                              .getPortfolioLicense()
                              .getLicenseDetails()
                              .get(i25)
                              .getAddress()
                              .getState();
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " LicenseDetails25.state = ? ";
                        counter++;
                      } else {
                        whereQuery =
                            " LicenseDetails25.address.state = :LicenseDetails25addressstate"
                                + j
                                + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          model
                              .getPortfolioLicense()
                              .getLicenseDetails()
                              .get(i25)
                              .getAddress()
                              .getState();
                      query +=
                          (andCondition
                              + Facade.getStatement(
                                  statement,
                                  "LicenseDetails25.address.state",
                                  "LicenseDetails25addressstate" + j,
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
                                .getPortfolioLicense()
                                .getLicenseDetails()
                                .get(i25)
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
                              .getPortfolioLicense()
                              .getLicenseDetails()
                              .get(i25)
                              .getAddress()
                              .getCountry();
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " LicenseDetails25.country = ? ";
                        counter++;
                      } else {
                        whereQuery =
                            " LicenseDetails25.address.country = :LicenseDetails25addresscountry"
                                + j
                                + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          model
                              .getPortfolioLicense()
                              .getLicenseDetails()
                              .get(i25)
                              .getAddress()
                              .getCountry();
                      query +=
                          (andCondition
                              + Facade.getStatement(
                                  statement,
                                  "LicenseDetails25.address.country",
                                  "LicenseDetails25addresscountry" + j,
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
                                .getPortfolioLicense()
                                .getLicenseDetails()
                                .get(i25)
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
                              .getPortfolioLicense()
                              .getLicenseDetails()
                              .get(i25)
                              .getAddress()
                              .getZipCode();
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " LicenseDetails25.zipCode = ? ";
                        counter++;
                      } else {
                        whereQuery =
                            " LicenseDetails25.address.zipCode = :LicenseDetails25addresszipCode"
                                + j
                                + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          model
                              .getPortfolioLicense()
                              .getLicenseDetails()
                              .get(i25)
                              .getAddress()
                              .getZipCode();
                      query +=
                          (andCondition
                              + Facade.getStatement(
                                  statement,
                                  "LicenseDetails25.address.zipCode",
                                  "LicenseDetails25addresszipCode" + j,
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
                  if (length25 > 1) {
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
                && model.getPortfolioLicense().getIssueVerifierType() != null) {

              andCondition = " AND ";
              if (isOpenParentheses) {
                andCondition = " ";
              }
              query += " ";
              if (isDelete && fetchType == FetchType.EAGER) {
                whereQuery = " PortfolioLicense5.issueVerifierType = ? ";
                counter++;
              } else {
                whereQuery =
                    " PortfolioLicense5.issueVerifierType = :PortfolioLicense5issueVerifierType"
                        + j
                        + " ";
              }

              isOpenParentheses = false;
              whereValue = model.getPortfolioLicense().getIssueVerifierType();
              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "PortfolioLicense5.issueVerifierType",
                          "PortfolioLicense5issueVerifierType" + j,
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
                && model.getPortfolioLicense().getLicenseNumber() != null) {

              andCondition = " AND ";
              if (isOpenParentheses) {
                andCondition = " ";
              }
              query += " ";
              if (isDelete && fetchType == FetchType.EAGER) {
                whereQuery = " PortfolioLicense5.licenseNumber = ? ";
                counter++;
              } else {
                whereQuery =
                    " PortfolioLicense5.licenseNumber = :PortfolioLicense5licenseNumber" + j + " ";
              }

              isOpenParentheses = false;
              whereValue = model.getPortfolioLicense().getLicenseNumber();
              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "PortfolioLicense5.licenseNumber",
                          "PortfolioLicense5licenseNumber" + j,
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
                && model.getPortfolioLicense().getLicenseType() != null) {

              andCondition = " AND ";
              if (isOpenParentheses) {
                andCondition = " ";
              }
              query += " ";
              if (isDelete && fetchType == FetchType.EAGER) {
                whereQuery = " PortfolioLicense5.licenseType = ? ";
                counter++;
              } else {
                whereQuery =
                    " PortfolioLicense5.licenseType = :PortfolioLicense5licenseType" + j + " ";
              }

              isOpenParentheses = false;
              whereValue = model.getPortfolioLicense().getLicenseType();
              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "PortfolioLicense5.licenseType",
                          "PortfolioLicense5licenseType" + j,
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
                && model.getPortfolioLicense().getIssueDate() != null) {

              andCondition = " AND ";
              if (isOpenParentheses) {
                andCondition = " ";
              }
              query += " ";
              if (isDelete && fetchType == FetchType.EAGER) {
                whereQuery = " PortfolioLicense5.issueDate = ? ";
                counter++;
              } else {
                whereQuery = " PortfolioLicense5.issueDate = :PortfolioLicense5issueDate" + j + " ";
              }

              isOpenParentheses = false;
              whereValue = model.getPortfolioLicense().getIssueDate();
              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "PortfolioLicense5.issueDate",
                          "PortfolioLicense5issueDate" + j,
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
                && model.getPortfolioLicense().getEndDate() != null) {

              andCondition = " AND ";
              if (isOpenParentheses) {
                andCondition = " ";
              }
              query += " ";
              if (isDelete && fetchType == FetchType.EAGER) {
                whereQuery = " PortfolioLicense5.endDate = ? ";
                counter++;
              } else {
                whereQuery = " PortfolioLicense5.endDate = :PortfolioLicense5endDate" + j + " ";
              }

              isOpenParentheses = false;
              whereValue = model.getPortfolioLicense().getEndDate();
              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "PortfolioLicense5.endDate",
                          "PortfolioLicense5endDate" + j,
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
                && model.getPortfolioLicense().getVersion() != 0) {

              andCondition = " AND ";
              if (isOpenParentheses) {
                andCondition = " ";
              }
              query += " ";
              if (isDelete && fetchType == FetchType.EAGER) {
                whereQuery = " PortfolioLicense5.VERSION = ? ";
                counter++;
              } else {
                whereQuery = " PortfolioLicense5.version = :PortfolioLicense5version" + j + " ";
              }

              isOpenParentheses = false;
              whereValue = new java.lang.Integer("" + model.getPortfolioLicense().getVersion());
              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "PortfolioLicense5.version",
                          "PortfolioLicense5version" + j,
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
          if (model.getAddress() != null) {
            if (true && model.getAddress().getAddressLine1() != null) {

              andCondition = " AND ";
              if (isOpenParentheses) {
                andCondition = " ";
              }
              query += " ";
              if (isDelete && fetchType == FetchType.EAGER) {
                whereQuery = " ProductsOfProject0.addressLine1 = ? ";
                counter++;
              } else {
                whereQuery =
                    " ProductsOfProject0.address.addressLine1 = :ProductsOfProject0addressaddressLine1"
                        + j
                        + " ";
              }

              isOpenParentheses = false;
              whereValue = model.getAddress().getAddressLine1();
              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "ProductsOfProject0.address.addressLine1",
                          "ProductsOfProject0addressaddressLine1" + j,
                          mapVariable,
                          whereQuery,
                          whereValue,
                          isExistRecord,
                          isDelete,
                          fetchType,
                          counter));
              whereQuery = "";
            }
            if (!isExistRecord && true && model.getAddress().getAddressLine2() != null) {

              andCondition = " AND ";
              if (isOpenParentheses) {
                andCondition = " ";
              }
              query += " ";
              if (isDelete && fetchType == FetchType.EAGER) {
                whereQuery = " ProductsOfProject0.addressLine2 = ? ";
                counter++;
              } else {
                whereQuery =
                    " ProductsOfProject0.address.addressLine2 = :ProductsOfProject0addressaddressLine2"
                        + j
                        + " ";
              }

              isOpenParentheses = false;
              whereValue = model.getAddress().getAddressLine2();
              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "ProductsOfProject0.address.addressLine2",
                          "ProductsOfProject0addressaddressLine2" + j,
                          mapVariable,
                          whereQuery,
                          whereValue,
                          isExistRecord,
                          isDelete,
                          fetchType,
                          counter));
              whereQuery = "";
            }
            if (!isExistRecord && true && model.getAddress().getCity() != null) {

              andCondition = " AND ";
              if (isOpenParentheses) {
                andCondition = " ";
              }
              query += " ";
              if (isDelete && fetchType == FetchType.EAGER) {
                whereQuery = " ProductsOfProject0.city = ? ";
                counter++;
              } else {
                whereQuery =
                    " ProductsOfProject0.address.city = :ProductsOfProject0addresscity" + j + " ";
              }

              isOpenParentheses = false;
              whereValue = model.getAddress().getCity();
              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "ProductsOfProject0.address.city",
                          "ProductsOfProject0addresscity" + j,
                          mapVariable,
                          whereQuery,
                          whereValue,
                          isExistRecord,
                          isDelete,
                          fetchType,
                          counter));
              whereQuery = "";
            }
            if (!isExistRecord && true && model.getAddress().getState() != null) {

              andCondition = " AND ";
              if (isOpenParentheses) {
                andCondition = " ";
              }
              query += " ";
              if (isDelete && fetchType == FetchType.EAGER) {
                whereQuery = " ProductsOfProject0.state = ? ";
                counter++;
              } else {
                whereQuery =
                    " ProductsOfProject0.address.state = :ProductsOfProject0addressstate" + j + " ";
              }

              isOpenParentheses = false;
              whereValue = model.getAddress().getState();
              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "ProductsOfProject0.address.state",
                          "ProductsOfProject0addressstate" + j,
                          mapVariable,
                          whereQuery,
                          whereValue,
                          isExistRecord,
                          isDelete,
                          fetchType,
                          counter));
              whereQuery = "";
            }
            if (!isExistRecord && true && model.getAddress().getCountry() != null) {

              andCondition = " AND ";
              if (isOpenParentheses) {
                andCondition = " ";
              }
              query += " ";
              if (isDelete && fetchType == FetchType.EAGER) {
                whereQuery = " ProductsOfProject0.country = ? ";
                counter++;
              } else {
                whereQuery =
                    " ProductsOfProject0.address.country = :ProductsOfProject0addresscountry"
                        + j
                        + " ";
              }

              isOpenParentheses = false;
              whereValue = model.getAddress().getCountry();
              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "ProductsOfProject0.address.country",
                          "ProductsOfProject0addresscountry" + j,
                          mapVariable,
                          whereQuery,
                          whereValue,
                          isExistRecord,
                          isDelete,
                          fetchType,
                          counter));
              whereQuery = "";
            }
            if (!isExistRecord && true && model.getAddress().getZipCode() != null) {

              andCondition = " AND ";
              if (isOpenParentheses) {
                andCondition = " ";
              }
              query += " ";
              if (isDelete && fetchType == FetchType.EAGER) {
                whereQuery = " ProductsOfProject0.zipCode = ? ";
                counter++;
              } else {
                whereQuery =
                    " ProductsOfProject0.address.zipCode = :ProductsOfProject0addresszipCode"
                        + j
                        + " ";
              }

              isOpenParentheses = false;
              whereValue = model.getAddress().getZipCode();
              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "ProductsOfProject0.address.zipCode",
                          "ProductsOfProject0addresszipCode" + j,
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
          if (!isExistRecord && true && model.getNominalCapacity() != null) {

            andCondition = " AND ";
            if (isOpenParentheses) {
              andCondition = " ";
            }
            query += " ";
            if (isDelete && fetchType == FetchType.EAGER) {
              whereQuery = " ProductsOfProject0.nominalCapacity = ? ";
              counter++;
            } else {
              whereQuery =
                  " ProductsOfProject0.nominalCapacity = :ProductsOfProject0nominalCapacity"
                      + j
                      + " ";
            }

            isOpenParentheses = false;
            whereValue = model.getNominalCapacity();
            query +=
                (andCondition
                    + Facade.getStatement(
                        statement,
                        "ProductsOfProject0.nominalCapacity",
                        "ProductsOfProject0nominalCapacity" + j,
                        mapVariable,
                        whereQuery,
                        whereValue,
                        isExistRecord,
                        isDelete,
                        fetchType,
                        counter));
            whereQuery = "";
          }
          if (!isExistRecord && true && model.getPracticalCapacity() != null) {

            andCondition = " AND ";
            if (isOpenParentheses) {
              andCondition = " ";
            }
            query += " ";
            if (isDelete && fetchType == FetchType.EAGER) {
              whereQuery = " ProductsOfProject0.practicalCapacity = ? ";
              counter++;
            } else {
              whereQuery =
                  " ProductsOfProject0.practicalCapacity = :ProductsOfProject0practicalCapacity"
                      + j
                      + " ";
            }

            isOpenParentheses = false;
            whereValue = model.getPracticalCapacity();
            query +=
                (andCondition
                    + Facade.getStatement(
                        statement,
                        "ProductsOfProject0.practicalCapacity",
                        "ProductsOfProject0practicalCapacity" + j,
                        mapVariable,
                        whereQuery,
                        whereValue,
                        isExistRecord,
                        isDelete,
                        fetchType,
                        counter));
            whereQuery = "";
          }
          if (!isExistRecord && true && model.getPracCapacityPrcntg() != null) {

            andCondition = " AND ";
            if (isOpenParentheses) {
              andCondition = " ";
            }
            query += " ";
            if (isDelete && fetchType == FetchType.EAGER) {
              whereQuery = " ProductsOfProject0.pracCapacityPrcntg = ? ";
              counter++;
            } else {
              whereQuery =
                  " ProductsOfProject0.pracCapacityPrcntg = :ProductsOfProject0pracCapacityPrcntg"
                      + j
                      + " ";
            }

            isOpenParentheses = false;
            whereValue = model.getPracCapacityPrcntg();
            query +=
                (andCondition
                    + Facade.getStatement(
                        statement,
                        "ProductsOfProject0.pracCapacityPrcntg",
                        "ProductsOfProject0pracCapacityPrcntg" + j,
                        mapVariable,
                        whereQuery,
                        whereValue,
                        isExistRecord,
                        isDelete,
                        fetchType,
                        counter));
            whereQuery = "";
          }
          if (!isExistRecord && true && model.getTypeOfSelling() != null) {

            andCondition = " AND ";
            if (isOpenParentheses) {
              andCondition = " ";
            }
            query += " ";
            if (isDelete && fetchType == FetchType.EAGER) {
              whereQuery = " ProductsOfProject0.typeOfSelling = ? ";
              counter++;
            } else {
              whereQuery =
                  " ProductsOfProject0.typeOfSelling = :ProductsOfProject0typeOfSelling" + j + " ";
            }

            isOpenParentheses = false;
            whereValue = model.getTypeOfSelling();
            query +=
                (andCondition
                    + Facade.getStatement(
                        statement,
                        "ProductsOfProject0.typeOfSelling",
                        "ProductsOfProject0typeOfSelling" + j,
                        mapVariable,
                        whereQuery,
                        whereValue,
                        isExistRecord,
                        isDelete,
                        fetchType,
                        counter));
            whereQuery = "";
          }
          if (!isExistRecord && true && model.getMarketplace() != null) {

            andCondition = " AND ";
            if (isOpenParentheses) {
              andCondition = " ";
            }
            query += " ";
            if (isDelete && fetchType == FetchType.EAGER) {
              whereQuery = " ProductsOfProject0.marketplace = ? ";
              counter++;
            } else {
              whereQuery =
                  " ProductsOfProject0.marketplace = :ProductsOfProject0marketplace" + j + " ";
            }

            isOpenParentheses = false;
            whereValue = model.getMarketplace();
            query +=
                (andCondition
                    + Facade.getStatement(
                        statement,
                        "ProductsOfProject0.marketplace",
                        "ProductsOfProject0marketplace" + j,
                        mapVariable,
                        whereQuery,
                        whereValue,
                        isExistRecord,
                        isDelete,
                        fetchType,
                        counter));
            whereQuery = "";
          }
          if (!isExistRecord && true && model.getMainProduct() != null) {

            andCondition = " AND ";
            if (isOpenParentheses) {
              andCondition = " ";
            }
            query += " ";
            if (isDelete && fetchType == FetchType.EAGER) {
              whereQuery = " ProductsOfProject0.mainProduct = ? ";
              counter++;
            } else {
              whereQuery =
                  " ProductsOfProject0.mainProduct = :ProductsOfProject0mainProduct" + j + " ";
            }

            isOpenParentheses = false;
            whereValue = model.getMainProduct();
            query +=
                (andCondition
                    + Facade.getStatement(
                        statement,
                        "ProductsOfProject0.mainProduct",
                        "ProductsOfProject0mainProduct" + j,
                        mapVariable,
                        whereQuery,
                        whereValue,
                        isExistRecord,
                        isDelete,
                        fetchType,
                        counter));
            whereQuery = "";
          }
          if (!isExistRecord && true && model.getIntSellingPr() != null) {

            andCondition = " AND ";
            if (isOpenParentheses) {
              andCondition = " ";
            }
            query += " ";
            if (isDelete && fetchType == FetchType.EAGER) {
              whereQuery = " ProductsOfProject0.intSellingPr = ? ";
              counter++;
            } else {
              whereQuery =
                  " ProductsOfProject0.intSellingPr = :ProductsOfProject0intSellingPr" + j + " ";
            }

            isOpenParentheses = false;
            whereValue = model.getIntSellingPr();
            query +=
                (andCondition
                    + Facade.getStatement(
                        statement,
                        "ProductsOfProject0.intSellingPr",
                        "ProductsOfProject0intSellingPr" + j,
                        mapVariable,
                        whereQuery,
                        whereValue,
                        isExistRecord,
                        isDelete,
                        fetchType,
                        counter));
            whereQuery = "";
          }
          if (!isExistRecord && true && model.getExSellingPr() != null) {

            andCondition = " AND ";
            if (isOpenParentheses) {
              andCondition = " ";
            }
            query += " ";
            if (isDelete && fetchType == FetchType.EAGER) {
              whereQuery = " ProductsOfProject0.exSellingPr = ? ";
              counter++;
            } else {
              whereQuery =
                  " ProductsOfProject0.exSellingPr = :ProductsOfProject0exSellingPr" + j + " ";
            }

            isOpenParentheses = false;
            whereValue = model.getExSellingPr();
            query +=
                (andCondition
                    + Facade.getStatement(
                        statement,
                        "ProductsOfProject0.exSellingPr",
                        "ProductsOfProject0exSellingPr" + j,
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
              whereQuery = " ProductsOfProject0.description = ? ";
              counter++;
            } else {
              whereQuery =
                  " ProductsOfProject0.description = :ProductsOfProject0description" + j + " ";
            }

            isOpenParentheses = false;
            whereValue = model.getDescription();
            query +=
                (andCondition
                    + Facade.getStatement(
                        statement,
                        "ProductsOfProject0.description",
                        "ProductsOfProject0description" + j,
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
              whereQuery = " ProductsOfProject0.evaluationDate = ? ";
              counter++;
            } else {
              whereQuery =
                  " ProductsOfProject0.evaluationDate = :ProductsOfProject0evaluationDate"
                      + j
                      + " ";
            }

            isOpenParentheses = false;
            whereValue = model.getEvaluationDate();
            query +=
                (andCondition
                    + Facade.getStatement(
                        statement,
                        "ProductsOfProject0.evaluationDate",
                        "ProductsOfProject0evaluationDate" + j,
                        mapVariable,
                        whereQuery,
                        whereValue,
                        isExistRecord,
                        isDelete,
                        fetchType,
                        counter));
            whereQuery = "";
          }
          if (!isExistRecord && true && model.getCalSelection() != null) {

            andCondition = " AND ";
            if (isOpenParentheses) {
              andCondition = " ";
            }
            query += " ";
            if (isDelete && fetchType == FetchType.EAGER) {
              whereQuery = " ProductsOfProject0.calSelection = ? ";
              counter++;
            } else {
              whereQuery =
                  " ProductsOfProject0.calSelection = :ProductsOfProject0calSelection" + j + " ";
            }

            isOpenParentheses = false;
            whereValue = model.getCalSelection();
            query +=
                (andCondition
                    + Facade.getStatement(
                        statement,
                        "ProductsOfProject0.calSelection",
                        "ProductsOfProject0calSelection" + j,
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
              whereQuery = " ProductsOfProject0.VERSION = ? ";
              counter++;
            } else {
              whereQuery = " ProductsOfProject0.version = :ProductsOfProject0version" + j + " ";
            }

            isOpenParentheses = false;
            whereValue = new java.lang.Integer("" + model.getVersion());
            query +=
                (andCondition
                    + Facade.getStatement(
                        statement,
                        "ProductsOfProject0.version",
                        "ProductsOfProject0version" + j,
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
          && (!select.trim().equals("ProductsOfProject0") && select.indexOf("new") == -1)) {
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
