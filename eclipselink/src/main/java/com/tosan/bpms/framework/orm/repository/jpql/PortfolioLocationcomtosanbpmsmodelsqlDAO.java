package com.tosan.bpms.framework.orm.repository.jpql;

import com.jpql.api.interfaces.*;
import com.jpql.api.interfaces.Parameter;
import javax.persistence.FetchType;
import javax.persistence.EntityManager;
/**
 * Created by kasra.haghpanah on 1/05/2019.
 */
public class PortfolioLocationcomtosanbpmsmodelsqlDAO {

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

        com.tosan.bpms.model.sql.PortfolioLocation model =
            (com.tosan.bpms.model.sql.PortfolioLocation) fetchIterator.next();

        if (model != null) {

          String key = model.getPortfolioLocationId() + "";
          existMap.put(key, model);
        }
      }
    }

    java.util.Iterator iterator = models.iterator();

    while (iterator.hasNext()) {
      com.tosan.bpms.model.sql.PortfolioLocation model =
          (com.tosan.bpms.model.sql.PortfolioLocation) iterator.next();

      if (model != null) {
        String key = model.getPortfolioLocationId() + "";
        com.tosan.bpms.model.sql.PortfolioLocation fetchModel =
            (com.tosan.bpms.model.sql.PortfolioLocation) existMap.get(key);

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
    com.tosan.bpms.model.sql.PortfolioLocation model = null;
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
    java.lang.String select = "SELECT PortfolioLocation0";
    if (selectParam != null && !selectParam.equals("")) {
      select = selectParam;
    }
    if (!isExistRecord && !isDelete) {
      if (fetchType == FetchType.LAZY) {
        basicQuery =
            select
                + " FROM com.tosan.bpms.model.sql.PortfolioLocation PortfolioLocation0  JOIN "
                + fetch
                + " PortfolioLocation0.portfolio Portfolio1 LEFT JOIN "
                + fetch
                + " PortfolioLocation0.file File19 WHERE 1=1 ";
      } else if (fetchType == FetchType.EAGER) {
        basicQuery =
            select
                + " FROM com.tosan.bpms.model.sql.PortfolioLocation PortfolioLocation0  JOIN "
                + fetch
                + " PortfolioLocation0.portfolio Portfolio1 JOIN "
                + fetch
                + " Portfolio1.portfolioLicenses PortfolioLicense2 LEFT JOIN "
                + fetch
                + " PortfolioLicense2.file File4 JOIN "
                + fetch
                + " PortfolioLicense2.licenseDetails LicenseDetails5 JOIN "
                + fetch
                + " LicenseDetails5.portfolioLicense PortfolioLicense6 LEFT JOIN "
                + fetch
                + " PortfolioLicense6.file File8 LEFT JOIN "
                + fetch
                + " LicenseDetails5.file File10 JOIN "
                + fetch
                + " Portfolio1.portfolioLocations PortfolioLocation11 LEFT JOIN "
                + fetch
                + " PortfolioLocation11.file File13 JOIN "
                + fetch
                + " PortfolioLocation11.locationDetails LocationDetails14 LEFT JOIN "
                + fetch
                + " LocationDetails14.file File16 JOIN "
                + fetch
                + " Portfolio1.receptions Reception17 LEFT JOIN "
                + fetch
                + " PortfolioLocation0.file File19 JOIN "
                + fetch
                + " PortfolioLocation0.locationDetails LocationDetails20 LEFT JOIN "
                + fetch
                + " LocationDetails20.file File22 WHERE 1=1 ";
      }
    } else if (isExistRecord) {
      basicQuery =
          "SELECT PortfolioLocation0 FROM com.tosan.bpms.model.sql.PortfolioLocation PortfolioLocation0 WHERE 1=1 ";
    } else if (isDelete) {
      if (fetchType == FetchType.LAZY) {
        basicQuery =
            "DELETE FROM com.tosan.bpms.model.sql.PortfolioLocation PortfolioLocation0 WHERE 1=1 ";
      } else if (fetchType == FetchType.EAGER) {
        basicQuery =
            "DELETE PortfolioLocation0,LocationDetails20 FROM PortfolioLocation PortfolioLocation0 LEFT JOIN LocationDetail LocationDetails20 ON( LocationDetails20.portfolioLocationId = PortfolioLocation0.id )  WHERE 1=1 ";
      }
    }
    int modelsLength = models.size();
    java.util.Iterator modelsIterator = models.iterator();
    int j = 0;
    if (modelsLength > 0) {
      while (modelsIterator.hasNext()) {
        model = (com.tosan.bpms.model.sql.PortfolioLocation) modelsIterator.next();
        query = "";
        isOpenParentheses = true;

        if (model != null) {

          if (isExistRecord == true && model.getPortfolioLocationId() == null) {
            model.setPortfolioLocationId(new java.lang.Integer("-1"));
            isChangeKey = true;
          }

          if (model.getPortfolioLocationId() != null) {

            andCondition = " AND ";
            if (isOpenParentheses) {
              andCondition = " ";
            }
            query += " ";
            if (isDelete && fetchType == FetchType.EAGER) {
              whereQuery = " PortfolioLocation0.id = ? ";
              counter++;
            } else {
              whereQuery =
                  " PortfolioLocation0.portfolioLocationId = :PortfolioLocation0portfolioLocationId"
                      + j
                      + " ";
            }

            isOpenParentheses = false;
            whereValue = model.getPortfolioLocationId();
            if (isExistRecord && isChangeKey) {
              model.setPortfolioLocationId(null);
              isChangeKey = false;
            }

            query +=
                (andCondition
                    + Facade.getStatement(
                        statement,
                        "PortfolioLocation0.portfolioLocationId",
                        "PortfolioLocation0portfolioLocationId" + j,
                        mapVariable,
                        whereQuery,
                        whereValue,
                        isExistRecord,
                        isDelete,
                        fetchType,
                        counter));
            whereQuery = "";
          }
          if (!isExistRecord && !isDelete && model.getPortfolio() != null) {

            if (model.getPortfolio().getPortfolioId() != null) {

              andCondition = " AND ";
              if (isOpenParentheses) {
                andCondition = " ";
              }
              query += " ";
              if (isDelete && fetchType == FetchType.EAGER) {
                whereQuery = " Portfolio1.portfolioId = ? ";
                counter++;
              } else {
                whereQuery = " Portfolio1.portfolioId = :Portfolio1portfolioId" + j + " ";
              }

              isOpenParentheses = false;
              whereValue = model.getPortfolio().getPortfolioId();
              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "Portfolio1.portfolioId",
                          "Portfolio1portfolioId" + j,
                          mapVariable,
                          whereQuery,
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
                && model.getPortfolio().getPortfolioLicenses() != null) {
              int length2 = model.getPortfolio().getPortfolioLicenses().size();

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

                  if (model.getPortfolio().getPortfolioLicenses().get(i2).getPortfolioLicenseId()
                      != null) {

                    andCondition = " AND ";
                    if (isOpenParentheses) {
                      andCondition = " ";
                    }
                    query += " ";
                    whereValue =
                        model.getPortfolio().getPortfolioLicenses().get(i2).getPortfolioLicenseId();
                    if (isDelete && fetchType == FetchType.EAGER) {
                      whereQuery = " PortfolioLicense2.id = ? ";
                      counter++;
                    } else {
                      whereQuery =
                          " PortfolioLicense2.portfolioLicenseId = :PortfolioLicense2portfolioLicenseId"
                              + j
                              + " ";
                    }

                    isOpenParentheses = false;
                    whereValue =
                        model.getPortfolio().getPortfolioLicenses().get(i2).getPortfolioLicenseId();
                    query +=
                        (andCondition
                            + Facade.getStatement(
                                statement,
                                "PortfolioLicense2.portfolioLicenseId",
                                "PortfolioLicense2portfolioLicenseId" + j,
                                mapVariable,
                                whereQuery,
                                whereValue,
                                isExistRecord,
                                isDelete,
                                fetchType,
                                counter));
                    whereQuery = "";
                  }
                  if (!isExistRecord
                      && !isDelete
                      && model.getPortfolio().getPortfolioLicenses().get(i2).getFile() != null) {

                    if (model.getPortfolio().getPortfolioLicenses().get(i2).getFile().getId()
                        != null) {

                      andCondition = " AND ";
                      if (isOpenParentheses) {
                        andCondition = " ";
                      }
                      query += " ";
                      whereValue =
                          model.getPortfolio().getPortfolioLicenses().get(i2).getFile().getId();
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " File4.l4610flmtdataid = ? ";
                        counter++;
                      } else {
                        whereQuery = " File4.id = :File4id" + j + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          model.getPortfolio().getPortfolioLicenses().get(i2).getFile().getId();
                      query +=
                          (andCondition
                              + Facade.getStatement(
                                  statement,
                                  "File4.id",
                                  "File4id" + j,
                                  mapVariable,
                                  whereQuery,
                                  whereValue,
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
                                .getPortfolio()
                                .getPortfolioLicenses()
                                .get(i2)
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
                              .getPortfolio()
                              .getPortfolioLicenses()
                              .get(i2)
                              .getFile()
                              .getExtension();
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " File4.l4610extension = ? ";
                        counter++;
                      } else {
                        whereQuery = " File4.extension = :File4extension" + j + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          model
                              .getPortfolio()
                              .getPortfolioLicenses()
                              .get(i2)
                              .getFile()
                              .getExtension();
                      query +=
                          (andCondition
                              + Facade.getStatement(
                                  statement,
                                  "File4.extension",
                                  "File4extension" + j,
                                  mapVariable,
                                  whereQuery,
                                  whereValue,
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
                                .getPortfolio()
                                .getPortfolioLicenses()
                                .get(i2)
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
                                      .getPortfolio()
                                      .getPortfolioLicenses()
                                      .get(i2)
                                      .getFile()
                                      .getVersion());
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " File4.VERSION = ? ";
                        counter++;
                      } else {
                        whereQuery = " File4.version = :File4version" + j + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          new java.lang.Integer(
                              ""
                                  + model
                                      .getPortfolio()
                                      .getPortfolioLicenses()
                                      .get(i2)
                                      .getFile()
                                      .getVersion());
                      query +=
                          (andCondition
                              + Facade.getStatement(
                                  statement,
                                  "File4.version",
                                  "File4version" + j,
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
                      && model.getPortfolio().getPortfolioLicenses().get(i2).getLicenseDetails()
                          != null) {
                    int length5 =
                        model
                            .getPortfolio()
                            .getPortfolioLicenses()
                            .get(i2)
                            .getLicenseDetails()
                            .size();

                    if (length5 > 0) {
                      if (isOpenParentheses) {
                        query += "  ( ";
                      } else {
                        query += " AND ( ";
                      }
                      isOpenParentheses = true;

                      for (int i5 = 0; i5 < length5; i5++) {

                        java.lang.String beforeCondition5 = "";
                        java.lang.String prefix5 = " OR ";
                        if (i5 == 0) {
                          prefix5 = "";
                        }
                        if (length5 > 1) {

                          query += (prefix5 + "(");
                          isOpenParentheses = true;

                        } else {

                          query += (prefix5);
                          if (!prefix5.trim().equals("")) {
                            isOpenParentheses = false;
                          }
                        }

                        if (model
                                .getPortfolio()
                                .getPortfolioLicenses()
                                .get(i2)
                                .getLicenseDetails()
                                .get(i5)
                                .getLicenseDetailId()
                            != null) {

                          andCondition = " AND ";
                          if (isOpenParentheses) {
                            andCondition = " ";
                          }
                          query += " ";
                          whereValue =
                              model
                                  .getPortfolio()
                                  .getPortfolioLicenses()
                                  .get(i2)
                                  .getLicenseDetails()
                                  .get(i5)
                                  .getLicenseDetailId();
                          if (isDelete && fetchType == FetchType.EAGER) {
                            whereQuery = " LicenseDetails5.id = ? ";
                            counter++;
                          } else {
                            whereQuery =
                                " LicenseDetails5.licenseDetailId = :LicenseDetails5licenseDetailId"
                                    + j
                                    + " ";
                          }

                          isOpenParentheses = false;
                          whereValue =
                              model
                                  .getPortfolio()
                                  .getPortfolioLicenses()
                                  .get(i2)
                                  .getLicenseDetails()
                                  .get(i5)
                                  .getLicenseDetailId();
                          query +=
                              (andCondition
                                  + Facade.getStatement(
                                      statement,
                                      "LicenseDetails5.licenseDetailId",
                                      "LicenseDetails5licenseDetailId" + j,
                                      mapVariable,
                                      whereQuery,
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
                                    .getPortfolio()
                                    .getPortfolioLicenses()
                                    .get(i2)
                                    .getLicenseDetails()
                                    .get(i5)
                                    .getPortfolioLicense()
                                != null) {

                          if (model
                                  .getPortfolio()
                                  .getPortfolioLicenses()
                                  .get(i2)
                                  .getLicenseDetails()
                                  .get(i5)
                                  .getPortfolioLicense()
                                  .getPortfolioLicenseId()
                              != null) {

                            andCondition = " AND ";
                            if (isOpenParentheses) {
                              andCondition = " ";
                            }
                            query += " ";
                            whereValue =
                                model
                                    .getPortfolio()
                                    .getPortfolioLicenses()
                                    .get(i2)
                                    .getLicenseDetails()
                                    .get(i5)
                                    .getPortfolioLicense()
                                    .getPortfolioLicenseId();
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
                                model
                                    .getPortfolio()
                                    .getPortfolioLicenses()
                                    .get(i2)
                                    .getLicenseDetails()
                                    .get(i5)
                                    .getPortfolioLicense()
                                    .getPortfolioLicenseId();
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
                              && model
                                      .getPortfolio()
                                      .getPortfolioLicenses()
                                      .get(i2)
                                      .getLicenseDetails()
                                      .get(i5)
                                      .getPortfolioLicense()
                                      .getFile()
                                  != null) {

                            if (model
                                    .getPortfolio()
                                    .getPortfolioLicenses()
                                    .get(i2)
                                    .getLicenseDetails()
                                    .get(i5)
                                    .getPortfolioLicense()
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
                                      .getPortfolio()
                                      .getPortfolioLicenses()
                                      .get(i2)
                                      .getLicenseDetails()
                                      .get(i5)
                                      .getPortfolioLicense()
                                      .getFile()
                                      .getId();
                              if (isDelete && fetchType == FetchType.EAGER) {
                                whereQuery = " File8.l4610flmtdataid = ? ";
                                counter++;
                              } else {
                                whereQuery = " File8.id = :File8id" + j + " ";
                              }

                              isOpenParentheses = false;
                              whereValue =
                                  model
                                      .getPortfolio()
                                      .getPortfolioLicenses()
                                      .get(i2)
                                      .getLicenseDetails()
                                      .get(i5)
                                      .getPortfolioLicense()
                                      .getFile()
                                      .getId();
                              query +=
                                  (andCondition
                                      + Facade.getStatement(
                                          statement,
                                          "File8.id",
                                          "File8id" + j,
                                          mapVariable,
                                          whereQuery,
                                          whereValue,
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
                                        .getPortfolio()
                                        .getPortfolioLicenses()
                                        .get(i2)
                                        .getLicenseDetails()
                                        .get(i5)
                                        .getPortfolioLicense()
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
                                      .getPortfolio()
                                      .getPortfolioLicenses()
                                      .get(i2)
                                      .getLicenseDetails()
                                      .get(i5)
                                      .getPortfolioLicense()
                                      .getFile()
                                      .getExtension();
                              if (isDelete && fetchType == FetchType.EAGER) {
                                whereQuery = " File8.l4610extension = ? ";
                                counter++;
                              } else {
                                whereQuery = " File8.extension = :File8extension" + j + " ";
                              }

                              isOpenParentheses = false;
                              whereValue =
                                  model
                                      .getPortfolio()
                                      .getPortfolioLicenses()
                                      .get(i2)
                                      .getLicenseDetails()
                                      .get(i5)
                                      .getPortfolioLicense()
                                      .getFile()
                                      .getExtension();
                              query +=
                                  (andCondition
                                      + Facade.getStatement(
                                          statement,
                                          "File8.extension",
                                          "File8extension" + j,
                                          mapVariable,
                                          whereQuery,
                                          whereValue,
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
                                        .getPortfolio()
                                        .getPortfolioLicenses()
                                        .get(i2)
                                        .getLicenseDetails()
                                        .get(i5)
                                        .getPortfolioLicense()
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
                                              .getPortfolio()
                                              .getPortfolioLicenses()
                                              .get(i2)
                                              .getLicenseDetails()
                                              .get(i5)
                                              .getPortfolioLicense()
                                              .getFile()
                                              .getVersion());
                              if (isDelete && fetchType == FetchType.EAGER) {
                                whereQuery = " File8.VERSION = ? ";
                                counter++;
                              } else {
                                whereQuery = " File8.version = :File8version" + j + " ";
                              }

                              isOpenParentheses = false;
                              whereValue =
                                  new java.lang.Integer(
                                      ""
                                          + model
                                              .getPortfolio()
                                              .getPortfolioLicenses()
                                              .get(i2)
                                              .getLicenseDetails()
                                              .get(i5)
                                              .getPortfolioLicense()
                                              .getFile()
                                              .getVersion());
                              query +=
                                  (andCondition
                                      + Facade.getStatement(
                                          statement,
                                          "File8.version",
                                          "File8version" + j,
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
                                      .getPortfolio()
                                      .getPortfolioLicenses()
                                      .get(i2)
                                      .getLicenseDetails()
                                      .get(i5)
                                      .getPortfolioLicense()
                                      .getIssueVerifierType()
                                  != null) {

                            andCondition = " AND ";
                            if (isOpenParentheses) {
                              andCondition = " ";
                            }
                            query += " ";
                            whereValue =
                                model
                                    .getPortfolio()
                                    .getPortfolioLicenses()
                                    .get(i2)
                                    .getLicenseDetails()
                                    .get(i5)
                                    .getPortfolioLicense()
                                    .getIssueVerifierType();
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
                                model
                                    .getPortfolio()
                                    .getPortfolioLicenses()
                                    .get(i2)
                                    .getLicenseDetails()
                                    .get(i5)
                                    .getPortfolioLicense()
                                    .getIssueVerifierType();
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
                              && model
                                      .getPortfolio()
                                      .getPortfolioLicenses()
                                      .get(i2)
                                      .getLicenseDetails()
                                      .get(i5)
                                      .getPortfolioLicense()
                                      .getLicenseNumber()
                                  != null) {

                            andCondition = " AND ";
                            if (isOpenParentheses) {
                              andCondition = " ";
                            }
                            query += " ";
                            whereValue =
                                model
                                    .getPortfolio()
                                    .getPortfolioLicenses()
                                    .get(i2)
                                    .getLicenseDetails()
                                    .get(i5)
                                    .getPortfolioLicense()
                                    .getLicenseNumber();
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
                            whereValue =
                                model
                                    .getPortfolio()
                                    .getPortfolioLicenses()
                                    .get(i2)
                                    .getLicenseDetails()
                                    .get(i5)
                                    .getPortfolioLicense()
                                    .getLicenseNumber();
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
                              && model
                                      .getPortfolio()
                                      .getPortfolioLicenses()
                                      .get(i2)
                                      .getLicenseDetails()
                                      .get(i5)
                                      .getPortfolioLicense()
                                      .getLicenseType()
                                  != null) {

                            andCondition = " AND ";
                            if (isOpenParentheses) {
                              andCondition = " ";
                            }
                            query += " ";
                            whereValue =
                                model
                                    .getPortfolio()
                                    .getPortfolioLicenses()
                                    .get(i2)
                                    .getLicenseDetails()
                                    .get(i5)
                                    .getPortfolioLicense()
                                    .getLicenseType();
                            if (isDelete && fetchType == FetchType.EAGER) {
                              whereQuery = " PortfolioLicense6.licenseType = ? ";
                              counter++;
                            } else {
                              whereQuery =
                                  " PortfolioLicense6.licenseType = :PortfolioLicense6licenseType"
                                      + j
                                      + " ";
                            }

                            isOpenParentheses = false;
                            whereValue =
                                model
                                    .getPortfolio()
                                    .getPortfolioLicenses()
                                    .get(i2)
                                    .getLicenseDetails()
                                    .get(i5)
                                    .getPortfolioLicense()
                                    .getLicenseType();
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
                              && model
                                      .getPortfolio()
                                      .getPortfolioLicenses()
                                      .get(i2)
                                      .getLicenseDetails()
                                      .get(i5)
                                      .getPortfolioLicense()
                                      .getIssueDate()
                                  != null) {

                            andCondition = " AND ";
                            if (isOpenParentheses) {
                              andCondition = " ";
                            }
                            query += " ";
                            whereValue =
                                model
                                    .getPortfolio()
                                    .getPortfolioLicenses()
                                    .get(i2)
                                    .getLicenseDetails()
                                    .get(i5)
                                    .getPortfolioLicense()
                                    .getIssueDate();
                            if (isDelete && fetchType == FetchType.EAGER) {
                              whereQuery = " PortfolioLicense6.issueDate = ? ";
                              counter++;
                            } else {
                              whereQuery =
                                  " PortfolioLicense6.issueDate = :PortfolioLicense6issueDate"
                                      + j
                                      + " ";
                            }

                            isOpenParentheses = false;
                            whereValue =
                                model
                                    .getPortfolio()
                                    .getPortfolioLicenses()
                                    .get(i2)
                                    .getLicenseDetails()
                                    .get(i5)
                                    .getPortfolioLicense()
                                    .getIssueDate();
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
                              && model
                                      .getPortfolio()
                                      .getPortfolioLicenses()
                                      .get(i2)
                                      .getLicenseDetails()
                                      .get(i5)
                                      .getPortfolioLicense()
                                      .getEndDate()
                                  != null) {

                            andCondition = " AND ";
                            if (isOpenParentheses) {
                              andCondition = " ";
                            }
                            query += " ";
                            whereValue =
                                model
                                    .getPortfolio()
                                    .getPortfolioLicenses()
                                    .get(i2)
                                    .getLicenseDetails()
                                    .get(i5)
                                    .getPortfolioLicense()
                                    .getEndDate();
                            if (isDelete && fetchType == FetchType.EAGER) {
                              whereQuery = " PortfolioLicense6.endDate = ? ";
                              counter++;
                            } else {
                              whereQuery =
                                  " PortfolioLicense6.endDate = :PortfolioLicense6endDate"
                                      + j
                                      + " ";
                            }

                            isOpenParentheses = false;
                            whereValue =
                                model
                                    .getPortfolio()
                                    .getPortfolioLicenses()
                                    .get(i2)
                                    .getLicenseDetails()
                                    .get(i5)
                                    .getPortfolioLicense()
                                    .getEndDate();
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
                              && model
                                      .getPortfolio()
                                      .getPortfolioLicenses()
                                      .get(i2)
                                      .getLicenseDetails()
                                      .get(i5)
                                      .getPortfolioLicense()
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
                                            .getPortfolio()
                                            .getPortfolioLicenses()
                                            .get(i2)
                                            .getLicenseDetails()
                                            .get(i5)
                                            .getPortfolioLicense()
                                            .getVersion());
                            if (isDelete && fetchType == FetchType.EAGER) {
                              whereQuery = " PortfolioLicense6.VERSION = ? ";
                              counter++;
                            } else {
                              whereQuery =
                                  " PortfolioLicense6.version = :PortfolioLicense6version"
                                      + j
                                      + " ";
                            }

                            isOpenParentheses = false;
                            whereValue =
                                new java.lang.Integer(
                                    ""
                                        + model
                                            .getPortfolio()
                                            .getPortfolioLicenses()
                                            .get(i2)
                                            .getLicenseDetails()
                                            .get(i5)
                                            .getPortfolioLicense()
                                            .getVersion());
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
                        if (!isExistRecord
                            && !isDelete
                            && model
                                    .getPortfolio()
                                    .getPortfolioLicenses()
                                    .get(i2)
                                    .getLicenseDetails()
                                    .get(i5)
                                    .getFile()
                                != null) {

                          if (model
                                  .getPortfolio()
                                  .getPortfolioLicenses()
                                  .get(i2)
                                  .getLicenseDetails()
                                  .get(i5)
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
                                    .getPortfolio()
                                    .getPortfolioLicenses()
                                    .get(i2)
                                    .getLicenseDetails()
                                    .get(i5)
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
                                    .getPortfolio()
                                    .getPortfolioLicenses()
                                    .get(i2)
                                    .getLicenseDetails()
                                    .get(i5)
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
                                      .getPortfolio()
                                      .getPortfolioLicenses()
                                      .get(i2)
                                      .getLicenseDetails()
                                      .get(i5)
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
                                    .getPortfolio()
                                    .getPortfolioLicenses()
                                    .get(i2)
                                    .getLicenseDetails()
                                    .get(i5)
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
                                    .getPortfolio()
                                    .getPortfolioLicenses()
                                    .get(i2)
                                    .getLicenseDetails()
                                    .get(i5)
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
                                      .getPortfolio()
                                      .getPortfolioLicenses()
                                      .get(i2)
                                      .getLicenseDetails()
                                      .get(i5)
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
                                            .getPortfolio()
                                            .getPortfolioLicenses()
                                            .get(i2)
                                            .getLicenseDetails()
                                            .get(i5)
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
                                            .getPortfolio()
                                            .getPortfolioLicenses()
                                            .get(i2)
                                            .getLicenseDetails()
                                            .get(i5)
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
                        if (!isDelete
                            && model
                                    .getPortfolio()
                                    .getPortfolioLicenses()
                                    .get(i2)
                                    .getLicenseDetails()
                                    .get(i5)
                                    .getAddress()
                                != null) {
                          if (!isDelete
                              && true
                              && model
                                      .getPortfolio()
                                      .getPortfolioLicenses()
                                      .get(i2)
                                      .getLicenseDetails()
                                      .get(i5)
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
                                    .getPortfolio()
                                    .getPortfolioLicenses()
                                    .get(i2)
                                    .getLicenseDetails()
                                    .get(i5)
                                    .getAddress()
                                    .getAddressLine1();
                            if (isDelete && fetchType == FetchType.EAGER) {
                              whereQuery = " LicenseDetails5.addressLine1 = ? ";
                              counter++;
                            } else {
                              whereQuery =
                                  " LicenseDetails5.address.addressLine1 = :LicenseDetails5addressaddressLine1"
                                      + j
                                      + " ";
                            }

                            isOpenParentheses = false;
                            whereValue =
                                model
                                    .getPortfolio()
                                    .getPortfolioLicenses()
                                    .get(i2)
                                    .getLicenseDetails()
                                    .get(i5)
                                    .getAddress()
                                    .getAddressLine1();
                            query +=
                                (andCondition
                                    + Facade.getStatement(
                                        statement,
                                        "LicenseDetails5.address.addressLine1",
                                        "LicenseDetails5addressaddressLine1" + j,
                                        mapVariable,
                                        whereQuery,
                                        whereValue,
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
                                      .getPortfolio()
                                      .getPortfolioLicenses()
                                      .get(i2)
                                      .getLicenseDetails()
                                      .get(i5)
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
                                    .getPortfolio()
                                    .getPortfolioLicenses()
                                    .get(i2)
                                    .getLicenseDetails()
                                    .get(i5)
                                    .getAddress()
                                    .getAddressLine2();
                            if (isDelete && fetchType == FetchType.EAGER) {
                              whereQuery = " LicenseDetails5.addressLine2 = ? ";
                              counter++;
                            } else {
                              whereQuery =
                                  " LicenseDetails5.address.addressLine2 = :LicenseDetails5addressaddressLine2"
                                      + j
                                      + " ";
                            }

                            isOpenParentheses = false;
                            whereValue =
                                model
                                    .getPortfolio()
                                    .getPortfolioLicenses()
                                    .get(i2)
                                    .getLicenseDetails()
                                    .get(i5)
                                    .getAddress()
                                    .getAddressLine2();
                            query +=
                                (andCondition
                                    + Facade.getStatement(
                                        statement,
                                        "LicenseDetails5.address.addressLine2",
                                        "LicenseDetails5addressaddressLine2" + j,
                                        mapVariable,
                                        whereQuery,
                                        whereValue,
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
                                      .getPortfolio()
                                      .getPortfolioLicenses()
                                      .get(i2)
                                      .getLicenseDetails()
                                      .get(i5)
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
                                    .getPortfolio()
                                    .getPortfolioLicenses()
                                    .get(i2)
                                    .getLicenseDetails()
                                    .get(i5)
                                    .getAddress()
                                    .getCity();
                            if (isDelete && fetchType == FetchType.EAGER) {
                              whereQuery = " LicenseDetails5.city = ? ";
                              counter++;
                            } else {
                              whereQuery =
                                  " LicenseDetails5.address.city = :LicenseDetails5addresscity"
                                      + j
                                      + " ";
                            }

                            isOpenParentheses = false;
                            whereValue =
                                model
                                    .getPortfolio()
                                    .getPortfolioLicenses()
                                    .get(i2)
                                    .getLicenseDetails()
                                    .get(i5)
                                    .getAddress()
                                    .getCity();
                            query +=
                                (andCondition
                                    + Facade.getStatement(
                                        statement,
                                        "LicenseDetails5.address.city",
                                        "LicenseDetails5addresscity" + j,
                                        mapVariable,
                                        whereQuery,
                                        whereValue,
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
                                      .getPortfolio()
                                      .getPortfolioLicenses()
                                      .get(i2)
                                      .getLicenseDetails()
                                      .get(i5)
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
                                    .getPortfolio()
                                    .getPortfolioLicenses()
                                    .get(i2)
                                    .getLicenseDetails()
                                    .get(i5)
                                    .getAddress()
                                    .getState();
                            if (isDelete && fetchType == FetchType.EAGER) {
                              whereQuery = " LicenseDetails5.state = ? ";
                              counter++;
                            } else {
                              whereQuery =
                                  " LicenseDetails5.address.state = :LicenseDetails5addressstate"
                                      + j
                                      + " ";
                            }

                            isOpenParentheses = false;
                            whereValue =
                                model
                                    .getPortfolio()
                                    .getPortfolioLicenses()
                                    .get(i2)
                                    .getLicenseDetails()
                                    .get(i5)
                                    .getAddress()
                                    .getState();
                            query +=
                                (andCondition
                                    + Facade.getStatement(
                                        statement,
                                        "LicenseDetails5.address.state",
                                        "LicenseDetails5addressstate" + j,
                                        mapVariable,
                                        whereQuery,
                                        whereValue,
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
                                      .getPortfolio()
                                      .getPortfolioLicenses()
                                      .get(i2)
                                      .getLicenseDetails()
                                      .get(i5)
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
                                    .getPortfolio()
                                    .getPortfolioLicenses()
                                    .get(i2)
                                    .getLicenseDetails()
                                    .get(i5)
                                    .getAddress()
                                    .getCountry();
                            if (isDelete && fetchType == FetchType.EAGER) {
                              whereQuery = " LicenseDetails5.country = ? ";
                              counter++;
                            } else {
                              whereQuery =
                                  " LicenseDetails5.address.country = :LicenseDetails5addresscountry"
                                      + j
                                      + " ";
                            }

                            isOpenParentheses = false;
                            whereValue =
                                model
                                    .getPortfolio()
                                    .getPortfolioLicenses()
                                    .get(i2)
                                    .getLicenseDetails()
                                    .get(i5)
                                    .getAddress()
                                    .getCountry();
                            query +=
                                (andCondition
                                    + Facade.getStatement(
                                        statement,
                                        "LicenseDetails5.address.country",
                                        "LicenseDetails5addresscountry" + j,
                                        mapVariable,
                                        whereQuery,
                                        whereValue,
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
                                      .getPortfolio()
                                      .getPortfolioLicenses()
                                      .get(i2)
                                      .getLicenseDetails()
                                      .get(i5)
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
                                    .getPortfolio()
                                    .getPortfolioLicenses()
                                    .get(i2)
                                    .getLicenseDetails()
                                    .get(i5)
                                    .getAddress()
                                    .getZipCode();
                            if (isDelete && fetchType == FetchType.EAGER) {
                              whereQuery = " LicenseDetails5.zipCode = ? ";
                              counter++;
                            } else {
                              whereQuery =
                                  " LicenseDetails5.address.zipCode = :LicenseDetails5addresszipCode"
                                      + j
                                      + " ";
                            }

                            isOpenParentheses = false;
                            whereValue =
                                model
                                    .getPortfolio()
                                    .getPortfolioLicenses()
                                    .get(i2)
                                    .getLicenseDetails()
                                    .get(i5)
                                    .getAddress()
                                    .getZipCode();
                            query +=
                                (andCondition
                                    + Facade.getStatement(
                                        statement,
                                        "LicenseDetails5.address.zipCode",
                                        "LicenseDetails5addresszipCode" + j,
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
                        if (length5 > 1) {
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
                      && model.getPortfolio().getPortfolioLicenses().get(i2).getIssueVerifierType()
                          != null) {

                    andCondition = " AND ";
                    if (isOpenParentheses) {
                      andCondition = " ";
                    }
                    query += " ";
                    whereValue =
                        model.getPortfolio().getPortfolioLicenses().get(i2).getIssueVerifierType();
                    if (isDelete && fetchType == FetchType.EAGER) {
                      whereQuery = " PortfolioLicense2.issueVerifierType = ? ";
                      counter++;
                    } else {
                      whereQuery =
                          " PortfolioLicense2.issueVerifierType = :PortfolioLicense2issueVerifierType"
                              + j
                              + " ";
                    }

                    isOpenParentheses = false;
                    whereValue =
                        model.getPortfolio().getPortfolioLicenses().get(i2).getIssueVerifierType();
                    query +=
                        (andCondition
                            + Facade.getStatement(
                                statement,
                                "PortfolioLicense2.issueVerifierType",
                                "PortfolioLicense2issueVerifierType" + j,
                                mapVariable,
                                whereQuery,
                                whereValue,
                                isExistRecord,
                                isDelete,
                                fetchType,
                                counter));
                    whereQuery = "";
                  }
                  if (!isExistRecord
                      && !isDelete
                      && true
                      && model.getPortfolio().getPortfolioLicenses().get(i2).getLicenseNumber()
                          != null) {

                    andCondition = " AND ";
                    if (isOpenParentheses) {
                      andCondition = " ";
                    }
                    query += " ";
                    whereValue =
                        model.getPortfolio().getPortfolioLicenses().get(i2).getLicenseNumber();
                    if (isDelete && fetchType == FetchType.EAGER) {
                      whereQuery = " PortfolioLicense2.licenseNumber = ? ";
                      counter++;
                    } else {
                      whereQuery =
                          " PortfolioLicense2.licenseNumber = :PortfolioLicense2licenseNumber"
                              + j
                              + " ";
                    }

                    isOpenParentheses = false;
                    whereValue =
                        model.getPortfolio().getPortfolioLicenses().get(i2).getLicenseNumber();
                    query +=
                        (andCondition
                            + Facade.getStatement(
                                statement,
                                "PortfolioLicense2.licenseNumber",
                                "PortfolioLicense2licenseNumber" + j,
                                mapVariable,
                                whereQuery,
                                whereValue,
                                isExistRecord,
                                isDelete,
                                fetchType,
                                counter));
                    whereQuery = "";
                  }
                  if (!isExistRecord
                      && !isDelete
                      && true
                      && model.getPortfolio().getPortfolioLicenses().get(i2).getLicenseType()
                          != null) {

                    andCondition = " AND ";
                    if (isOpenParentheses) {
                      andCondition = " ";
                    }
                    query += " ";
                    whereValue =
                        model.getPortfolio().getPortfolioLicenses().get(i2).getLicenseType();
                    if (isDelete && fetchType == FetchType.EAGER) {
                      whereQuery = " PortfolioLicense2.licenseType = ? ";
                      counter++;
                    } else {
                      whereQuery =
                          " PortfolioLicense2.licenseType = :PortfolioLicense2licenseType"
                              + j
                              + " ";
                    }

                    isOpenParentheses = false;
                    whereValue =
                        model.getPortfolio().getPortfolioLicenses().get(i2).getLicenseType();
                    query +=
                        (andCondition
                            + Facade.getStatement(
                                statement,
                                "PortfolioLicense2.licenseType",
                                "PortfolioLicense2licenseType" + j,
                                mapVariable,
                                whereQuery,
                                whereValue,
                                isExistRecord,
                                isDelete,
                                fetchType,
                                counter));
                    whereQuery = "";
                  }
                  if (!isExistRecord
                      && !isDelete
                      && true
                      && model.getPortfolio().getPortfolioLicenses().get(i2).getIssueDate()
                          != null) {

                    andCondition = " AND ";
                    if (isOpenParentheses) {
                      andCondition = " ";
                    }
                    query += " ";
                    whereValue = model.getPortfolio().getPortfolioLicenses().get(i2).getIssueDate();
                    if (isDelete && fetchType == FetchType.EAGER) {
                      whereQuery = " PortfolioLicense2.issueDate = ? ";
                      counter++;
                    } else {
                      whereQuery =
                          " PortfolioLicense2.issueDate = :PortfolioLicense2issueDate" + j + " ";
                    }

                    isOpenParentheses = false;
                    whereValue = model.getPortfolio().getPortfolioLicenses().get(i2).getIssueDate();
                    query +=
                        (andCondition
                            + Facade.getStatement(
                                statement,
                                "PortfolioLicense2.issueDate",
                                "PortfolioLicense2issueDate" + j,
                                mapVariable,
                                whereQuery,
                                whereValue,
                                isExistRecord,
                                isDelete,
                                fetchType,
                                counter));
                    whereQuery = "";
                  }
                  if (!isExistRecord
                      && !isDelete
                      && true
                      && model.getPortfolio().getPortfolioLicenses().get(i2).getEndDate() != null) {

                    andCondition = " AND ";
                    if (isOpenParentheses) {
                      andCondition = " ";
                    }
                    query += " ";
                    whereValue = model.getPortfolio().getPortfolioLicenses().get(i2).getEndDate();
                    if (isDelete && fetchType == FetchType.EAGER) {
                      whereQuery = " PortfolioLicense2.endDate = ? ";
                      counter++;
                    } else {
                      whereQuery =
                          " PortfolioLicense2.endDate = :PortfolioLicense2endDate" + j + " ";
                    }

                    isOpenParentheses = false;
                    whereValue = model.getPortfolio().getPortfolioLicenses().get(i2).getEndDate();
                    query +=
                        (andCondition
                            + Facade.getStatement(
                                statement,
                                "PortfolioLicense2.endDate",
                                "PortfolioLicense2endDate" + j,
                                mapVariable,
                                whereQuery,
                                whereValue,
                                isExistRecord,
                                isDelete,
                                fetchType,
                                counter));
                    whereQuery = "";
                  }
                  if (!isExistRecord
                      && !isDelete
                      && true
                      && model.getPortfolio().getPortfolioLicenses().get(i2).getVersion() != 0) {

                    andCondition = " AND ";
                    if (isOpenParentheses) {
                      andCondition = " ";
                    }
                    query += " ";
                    whereValue =
                        new java.lang.Integer(
                            "" + model.getPortfolio().getPortfolioLicenses().get(i2).getVersion());
                    if (isDelete && fetchType == FetchType.EAGER) {
                      whereQuery = " PortfolioLicense2.VERSION = ? ";
                      counter++;
                    } else {
                      whereQuery =
                          " PortfolioLicense2.version = :PortfolioLicense2version" + j + " ";
                    }

                    isOpenParentheses = false;
                    whereValue =
                        new java.lang.Integer(
                            "" + model.getPortfolio().getPortfolioLicenses().get(i2).getVersion());
                    query +=
                        (andCondition
                            + Facade.getStatement(
                                statement,
                                "PortfolioLicense2.version",
                                "PortfolioLicense2version" + j,
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
                && fetchType == FetchType.EAGER
                && model.getPortfolio().getPortfolioLocations() != null) {
              int length11 = model.getPortfolio().getPortfolioLocations().size();

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

                  if (model.getPortfolio().getPortfolioLocations().get(i11).getPortfolioLocationId()
                      != null) {

                    andCondition = " AND ";
                    if (isOpenParentheses) {
                      andCondition = " ";
                    }
                    query += " ";
                    whereValue =
                        model
                            .getPortfolio()
                            .getPortfolioLocations()
                            .get(i11)
                            .getPortfolioLocationId();
                    if (isDelete && fetchType == FetchType.EAGER) {
                      whereQuery = " PortfolioLocation11.id = ? ";
                      counter++;
                    } else {
                      whereQuery =
                          " PortfolioLocation11.portfolioLocationId = :PortfolioLocation11portfolioLocationId"
                              + j
                              + " ";
                    }

                    isOpenParentheses = false;
                    whereValue =
                        model
                            .getPortfolio()
                            .getPortfolioLocations()
                            .get(i11)
                            .getPortfolioLocationId();
                    query +=
                        (andCondition
                            + Facade.getStatement(
                                statement,
                                "PortfolioLocation11.portfolioLocationId",
                                "PortfolioLocation11portfolioLocationId" + j,
                                mapVariable,
                                whereQuery,
                                whereValue,
                                isExistRecord,
                                isDelete,
                                fetchType,
                                counter));
                    whereQuery = "";
                  }
                  if (!isExistRecord
                      && !isDelete
                      && model.getPortfolio().getPortfolioLocations().get(i11).getFile() != null) {

                    if (model.getPortfolio().getPortfolioLocations().get(i11).getFile().getId()
                        != null) {

                      andCondition = " AND ";
                      if (isOpenParentheses) {
                        andCondition = " ";
                      }
                      query += " ";
                      whereValue =
                          model.getPortfolio().getPortfolioLocations().get(i11).getFile().getId();
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " File13.l4610flmtdataid = ? ";
                        counter++;
                      } else {
                        whereQuery = " File13.id = :File13id" + j + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          model.getPortfolio().getPortfolioLocations().get(i11).getFile().getId();
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
                                .getPortfolio()
                                .getPortfolioLocations()
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
                              .getPortfolio()
                              .getPortfolioLocations()
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
                              .getPortfolio()
                              .getPortfolioLocations()
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
                                .getPortfolio()
                                .getPortfolioLocations()
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
                                      .getPortfolio()
                                      .getPortfolioLocations()
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
                                      .getPortfolio()
                                      .getPortfolioLocations()
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
                  if (!isExistRecord
                      && !isDelete
                      && fetchType == FetchType.EAGER
                      && model.getPortfolio().getPortfolioLocations().get(i11).getLocationDetails()
                          != null) {
                    int length14 =
                        model
                            .getPortfolio()
                            .getPortfolioLocations()
                            .get(i11)
                            .getLocationDetails()
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
                                .getPortfolio()
                                .getPortfolioLocations()
                                .get(i11)
                                .getLocationDetails()
                                .get(i14)
                                .getLocationDetailId()
                            != null) {

                          andCondition = " AND ";
                          if (isOpenParentheses) {
                            andCondition = " ";
                          }
                          query += " ";
                          whereValue =
                              model
                                  .getPortfolio()
                                  .getPortfolioLocations()
                                  .get(i11)
                                  .getLocationDetails()
                                  .get(i14)
                                  .getLocationDetailId();
                          if (isDelete && fetchType == FetchType.EAGER) {
                            whereQuery = " LocationDetails14.id = ? ";
                            counter++;
                          } else {
                            whereQuery =
                                " LocationDetails14.locationDetailId = :LocationDetails14locationDetailId"
                                    + j
                                    + " ";
                          }

                          isOpenParentheses = false;
                          whereValue =
                              model
                                  .getPortfolio()
                                  .getPortfolioLocations()
                                  .get(i11)
                                  .getLocationDetails()
                                  .get(i14)
                                  .getLocationDetailId();
                          query +=
                              (andCondition
                                  + Facade.getStatement(
                                      statement,
                                      "LocationDetails14.locationDetailId",
                                      "LocationDetails14locationDetailId" + j,
                                      mapVariable,
                                      whereQuery,
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
                                    .getPortfolio()
                                    .getPortfolioLocations()
                                    .get(i11)
                                    .getLocationDetails()
                                    .get(i14)
                                    .getFile()
                                != null) {

                          if (model
                                  .getPortfolio()
                                  .getPortfolioLocations()
                                  .get(i11)
                                  .getLocationDetails()
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
                                    .getPortfolio()
                                    .getPortfolioLocations()
                                    .get(i11)
                                    .getLocationDetails()
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
                                    .getPortfolio()
                                    .getPortfolioLocations()
                                    .get(i11)
                                    .getLocationDetails()
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
                                      .getPortfolio()
                                      .getPortfolioLocations()
                                      .get(i11)
                                      .getLocationDetails()
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
                                    .getPortfolio()
                                    .getPortfolioLocations()
                                    .get(i11)
                                    .getLocationDetails()
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
                                    .getPortfolio()
                                    .getPortfolioLocations()
                                    .get(i11)
                                    .getLocationDetails()
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
                                      .getPortfolio()
                                      .getPortfolioLocations()
                                      .get(i11)
                                      .getLocationDetails()
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
                                            .getPortfolio()
                                            .getPortfolioLocations()
                                            .get(i11)
                                            .getLocationDetails()
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
                                            .getPortfolio()
                                            .getPortfolioLocations()
                                            .get(i11)
                                            .getLocationDetails()
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
                        if (!isDelete
                            && model
                                    .getPortfolio()
                                    .getPortfolioLocations()
                                    .get(i11)
                                    .getLocationDetails()
                                    .get(i14)
                                    .getAddress()
                                != null) {
                          if (!isDelete
                              && true
                              && model
                                      .getPortfolio()
                                      .getPortfolioLocations()
                                      .get(i11)
                                      .getLocationDetails()
                                      .get(i14)
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
                                    .getPortfolio()
                                    .getPortfolioLocations()
                                    .get(i11)
                                    .getLocationDetails()
                                    .get(i14)
                                    .getAddress()
                                    .getAddressLine1();
                            if (isDelete && fetchType == FetchType.EAGER) {
                              whereQuery = " LocationDetails14.addressLine1 = ? ";
                              counter++;
                            } else {
                              whereQuery =
                                  " LocationDetails14.address.addressLine1 = :LocationDetails14addressaddressLine1"
                                      + j
                                      + " ";
                            }

                            isOpenParentheses = false;
                            whereValue =
                                model
                                    .getPortfolio()
                                    .getPortfolioLocations()
                                    .get(i11)
                                    .getLocationDetails()
                                    .get(i14)
                                    .getAddress()
                                    .getAddressLine1();
                            query +=
                                (andCondition
                                    + Facade.getStatement(
                                        statement,
                                        "LocationDetails14.address.addressLine1",
                                        "LocationDetails14addressaddressLine1" + j,
                                        mapVariable,
                                        whereQuery,
                                        whereValue,
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
                                      .getPortfolio()
                                      .getPortfolioLocations()
                                      .get(i11)
                                      .getLocationDetails()
                                      .get(i14)
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
                                    .getPortfolio()
                                    .getPortfolioLocations()
                                    .get(i11)
                                    .getLocationDetails()
                                    .get(i14)
                                    .getAddress()
                                    .getAddressLine2();
                            if (isDelete && fetchType == FetchType.EAGER) {
                              whereQuery = " LocationDetails14.addressLine2 = ? ";
                              counter++;
                            } else {
                              whereQuery =
                                  " LocationDetails14.address.addressLine2 = :LocationDetails14addressaddressLine2"
                                      + j
                                      + " ";
                            }

                            isOpenParentheses = false;
                            whereValue =
                                model
                                    .getPortfolio()
                                    .getPortfolioLocations()
                                    .get(i11)
                                    .getLocationDetails()
                                    .get(i14)
                                    .getAddress()
                                    .getAddressLine2();
                            query +=
                                (andCondition
                                    + Facade.getStatement(
                                        statement,
                                        "LocationDetails14.address.addressLine2",
                                        "LocationDetails14addressaddressLine2" + j,
                                        mapVariable,
                                        whereQuery,
                                        whereValue,
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
                                      .getPortfolio()
                                      .getPortfolioLocations()
                                      .get(i11)
                                      .getLocationDetails()
                                      .get(i14)
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
                                    .getPortfolio()
                                    .getPortfolioLocations()
                                    .get(i11)
                                    .getLocationDetails()
                                    .get(i14)
                                    .getAddress()
                                    .getCity();
                            if (isDelete && fetchType == FetchType.EAGER) {
                              whereQuery = " LocationDetails14.city = ? ";
                              counter++;
                            } else {
                              whereQuery =
                                  " LocationDetails14.address.city = :LocationDetails14addresscity"
                                      + j
                                      + " ";
                            }

                            isOpenParentheses = false;
                            whereValue =
                                model
                                    .getPortfolio()
                                    .getPortfolioLocations()
                                    .get(i11)
                                    .getLocationDetails()
                                    .get(i14)
                                    .getAddress()
                                    .getCity();
                            query +=
                                (andCondition
                                    + Facade.getStatement(
                                        statement,
                                        "LocationDetails14.address.city",
                                        "LocationDetails14addresscity" + j,
                                        mapVariable,
                                        whereQuery,
                                        whereValue,
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
                                      .getPortfolio()
                                      .getPortfolioLocations()
                                      .get(i11)
                                      .getLocationDetails()
                                      .get(i14)
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
                                    .getPortfolio()
                                    .getPortfolioLocations()
                                    .get(i11)
                                    .getLocationDetails()
                                    .get(i14)
                                    .getAddress()
                                    .getState();
                            if (isDelete && fetchType == FetchType.EAGER) {
                              whereQuery = " LocationDetails14.state = ? ";
                              counter++;
                            } else {
                              whereQuery =
                                  " LocationDetails14.address.state = :LocationDetails14addressstate"
                                      + j
                                      + " ";
                            }

                            isOpenParentheses = false;
                            whereValue =
                                model
                                    .getPortfolio()
                                    .getPortfolioLocations()
                                    .get(i11)
                                    .getLocationDetails()
                                    .get(i14)
                                    .getAddress()
                                    .getState();
                            query +=
                                (andCondition
                                    + Facade.getStatement(
                                        statement,
                                        "LocationDetails14.address.state",
                                        "LocationDetails14addressstate" + j,
                                        mapVariable,
                                        whereQuery,
                                        whereValue,
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
                                      .getPortfolio()
                                      .getPortfolioLocations()
                                      .get(i11)
                                      .getLocationDetails()
                                      .get(i14)
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
                                    .getPortfolio()
                                    .getPortfolioLocations()
                                    .get(i11)
                                    .getLocationDetails()
                                    .get(i14)
                                    .getAddress()
                                    .getCountry();
                            if (isDelete && fetchType == FetchType.EAGER) {
                              whereQuery = " LocationDetails14.country = ? ";
                              counter++;
                            } else {
                              whereQuery =
                                  " LocationDetails14.address.country = :LocationDetails14addresscountry"
                                      + j
                                      + " ";
                            }

                            isOpenParentheses = false;
                            whereValue =
                                model
                                    .getPortfolio()
                                    .getPortfolioLocations()
                                    .get(i11)
                                    .getLocationDetails()
                                    .get(i14)
                                    .getAddress()
                                    .getCountry();
                            query +=
                                (andCondition
                                    + Facade.getStatement(
                                        statement,
                                        "LocationDetails14.address.country",
                                        "LocationDetails14addresscountry" + j,
                                        mapVariable,
                                        whereQuery,
                                        whereValue,
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
                                      .getPortfolio()
                                      .getPortfolioLocations()
                                      .get(i11)
                                      .getLocationDetails()
                                      .get(i14)
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
                                    .getPortfolio()
                                    .getPortfolioLocations()
                                    .get(i11)
                                    .getLocationDetails()
                                    .get(i14)
                                    .getAddress()
                                    .getZipCode();
                            if (isDelete && fetchType == FetchType.EAGER) {
                              whereQuery = " LocationDetails14.zipCode = ? ";
                              counter++;
                            } else {
                              whereQuery =
                                  " LocationDetails14.address.zipCode = :LocationDetails14addresszipCode"
                                      + j
                                      + " ";
                            }

                            isOpenParentheses = false;
                            whereValue =
                                model
                                    .getPortfolio()
                                    .getPortfolioLocations()
                                    .get(i11)
                                    .getLocationDetails()
                                    .get(i14)
                                    .getAddress()
                                    .getZipCode();
                            query +=
                                (andCondition
                                    + Facade.getStatement(
                                        statement,
                                        "LocationDetails14.address.zipCode",
                                        "LocationDetails14addresszipCode" + j,
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
                      && true
                      && model.getPortfolio().getPortfolioLocations().get(i11).getProvince()
                          != null) {

                    andCondition = " AND ";
                    if (isOpenParentheses) {
                      andCondition = " ";
                    }
                    query += " ";
                    whereValue =
                        model.getPortfolio().getPortfolioLocations().get(i11).getProvince();
                    if (isDelete && fetchType == FetchType.EAGER) {
                      whereQuery = " PortfolioLocation11.province = ? ";
                      counter++;
                    } else {
                      whereQuery =
                          " PortfolioLocation11.province = :PortfolioLocation11province" + j + " ";
                    }

                    isOpenParentheses = false;
                    whereValue =
                        model.getPortfolio().getPortfolioLocations().get(i11).getProvince();
                    query +=
                        (andCondition
                            + Facade.getStatement(
                                statement,
                                "PortfolioLocation11.province",
                                "PortfolioLocation11province" + j,
                                mapVariable,
                                whereQuery,
                                whereValue,
                                isExistRecord,
                                isDelete,
                                fetchType,
                                counter));
                    whereQuery = "";
                  }
                  if (!isExistRecord
                      && !isDelete
                      && true
                      && model.getPortfolio().getPortfolioLocations().get(i11).getCity() != null) {

                    andCondition = " AND ";
                    if (isOpenParentheses) {
                      andCondition = " ";
                    }
                    query += " ";
                    whereValue = model.getPortfolio().getPortfolioLocations().get(i11).getCity();
                    if (isDelete && fetchType == FetchType.EAGER) {
                      whereQuery = " PortfolioLocation11.city = ? ";
                      counter++;
                    } else {
                      whereQuery = " PortfolioLocation11.city = :PortfolioLocation11city" + j + " ";
                    }

                    isOpenParentheses = false;
                    whereValue = model.getPortfolio().getPortfolioLocations().get(i11).getCity();
                    query +=
                        (andCondition
                            + Facade.getStatement(
                                statement,
                                "PortfolioLocation11.city",
                                "PortfolioLocation11city" + j,
                                mapVariable,
                                whereQuery,
                                whereValue,
                                isExistRecord,
                                isDelete,
                                fetchType,
                                counter));
                    whereQuery = "";
                  }
                  if (!isExistRecord
                      && !isDelete
                      && true
                      && model.getPortfolio().getPortfolioLocations().get(i11).getIndstrltwn()
                          != null) {

                    andCondition = " AND ";
                    if (isOpenParentheses) {
                      andCondition = " ";
                    }
                    query += " ";
                    whereValue =
                        model.getPortfolio().getPortfolioLocations().get(i11).getIndstrltwn();
                    if (isDelete && fetchType == FetchType.EAGER) {
                      whereQuery = " PortfolioLocation11.indstrltwn = ? ";
                      counter++;
                    } else {
                      whereQuery =
                          " PortfolioLocation11.indstrltwn = :PortfolioLocation11indstrltwn"
                              + j
                              + " ";
                    }

                    isOpenParentheses = false;
                    whereValue =
                        model.getPortfolio().getPortfolioLocations().get(i11).getIndstrltwn();
                    query +=
                        (andCondition
                            + Facade.getStatement(
                                statement,
                                "PortfolioLocation11.indstrltwn",
                                "PortfolioLocation11indstrltwn" + j,
                                mapVariable,
                                whereQuery,
                                whereValue,
                                isExistRecord,
                                isDelete,
                                fetchType,
                                counter));
                    whereQuery = "";
                  }
                  if (!isExistRecord
                      && !isDelete
                      && true
                      && model.getPortfolio().getPortfolioLocations().get(i11).getAddress()
                          != null) {

                    andCondition = " AND ";
                    if (isOpenParentheses) {
                      andCondition = " ";
                    }
                    query += " ";
                    whereValue = model.getPortfolio().getPortfolioLocations().get(i11).getAddress();
                    if (isDelete && fetchType == FetchType.EAGER) {
                      whereQuery = " PortfolioLocation11.address = ? ";
                      counter++;
                    } else {
                      whereQuery =
                          " PortfolioLocation11.address = :PortfolioLocation11address" + j + " ";
                    }

                    isOpenParentheses = false;
                    whereValue = model.getPortfolio().getPortfolioLocations().get(i11).getAddress();
                    query +=
                        (andCondition
                            + Facade.getStatement(
                                statement,
                                "PortfolioLocation11.address",
                                "PortfolioLocation11address" + j,
                                mapVariable,
                                whereQuery,
                                whereValue,
                                isExistRecord,
                                isDelete,
                                fetchType,
                                counter));
                    whereQuery = "";
                  }
                  if (!isExistRecord
                      && !isDelete
                      && true
                      && model.getPortfolio().getPortfolioLocations().get(i11).getStableStatusType()
                          != null) {

                    andCondition = " AND ";
                    if (isOpenParentheses) {
                      andCondition = " ";
                    }
                    query += " ";
                    whereValue =
                        model.getPortfolio().getPortfolioLocations().get(i11).getStableStatusType();
                    if (isDelete && fetchType == FetchType.EAGER) {
                      whereQuery = " PortfolioLocation11.stableStatusType = ? ";
                      counter++;
                    } else {
                      whereQuery =
                          " PortfolioLocation11.stableStatusType = :PortfolioLocation11stableStatusType"
                              + j
                              + " ";
                    }

                    isOpenParentheses = false;
                    whereValue =
                        model.getPortfolio().getPortfolioLocations().get(i11).getStableStatusType();
                    query +=
                        (andCondition
                            + Facade.getStatement(
                                statement,
                                "PortfolioLocation11.stableStatusType",
                                "PortfolioLocation11stableStatusType" + j,
                                mapVariable,
                                whereQuery,
                                whereValue,
                                isExistRecord,
                                isDelete,
                                fetchType,
                                counter));
                    whereQuery = "";
                  }
                  if (!isExistRecord
                      && !isDelete
                      && true
                      && model.getPortfolio().getPortfolioLocations().get(i11).getOwnerStatusType()
                          != null) {

                    andCondition = " AND ";
                    if (isOpenParentheses) {
                      andCondition = " ";
                    }
                    query += " ";
                    whereValue =
                        model.getPortfolio().getPortfolioLocations().get(i11).getOwnerStatusType();
                    if (isDelete && fetchType == FetchType.EAGER) {
                      whereQuery = " PortfolioLocation11.ownerStatusType = ? ";
                      counter++;
                    } else {
                      whereQuery =
                          " PortfolioLocation11.ownerStatusType = :PortfolioLocation11ownerStatusType"
                              + j
                              + " ";
                    }

                    isOpenParentheses = false;
                    whereValue =
                        model.getPortfolio().getPortfolioLocations().get(i11).getOwnerStatusType();
                    query +=
                        (andCondition
                            + Facade.getStatement(
                                statement,
                                "PortfolioLocation11.ownerStatusType",
                                "PortfolioLocation11ownerStatusType" + j,
                                mapVariable,
                                whereQuery,
                                whereValue,
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
                              .getPortfolio()
                              .getPortfolioLocations()
                              .get(i11)
                              .getRegionalStatusType()
                          != null) {

                    andCondition = " AND ";
                    if (isOpenParentheses) {
                      andCondition = " ";
                    }
                    query += " ";
                    whereValue =
                        model
                            .getPortfolio()
                            .getPortfolioLocations()
                            .get(i11)
                            .getRegionalStatusType();
                    if (isDelete && fetchType == FetchType.EAGER) {
                      whereQuery = " PortfolioLocation11.regionalStatusType = ? ";
                      counter++;
                    } else {
                      whereQuery =
                          " PortfolioLocation11.regionalStatusType = :PortfolioLocation11regionalStatusType"
                              + j
                              + " ";
                    }

                    isOpenParentheses = false;
                    whereValue =
                        model
                            .getPortfolio()
                            .getPortfolioLocations()
                            .get(i11)
                            .getRegionalStatusType();
                    query +=
                        (andCondition
                            + Facade.getStatement(
                                statement,
                                "PortfolioLocation11.regionalStatusType",
                                "PortfolioLocation11regionalStatusType" + j,
                                mapVariable,
                                whereQuery,
                                whereValue,
                                isExistRecord,
                                isDelete,
                                fetchType,
                                counter));
                    whereQuery = "";
                  }
                  if (!isExistRecord
                      && !isDelete
                      && true
                      && model.getPortfolio().getPortfolioLocations().get(i11).getVersion() != 0) {

                    andCondition = " AND ";
                    if (isOpenParentheses) {
                      andCondition = " ";
                    }
                    query += " ";
                    whereValue =
                        new java.lang.Integer(
                            ""
                                + model
                                    .getPortfolio()
                                    .getPortfolioLocations()
                                    .get(i11)
                                    .getVersion());
                    if (isDelete && fetchType == FetchType.EAGER) {
                      whereQuery = " PortfolioLocation11.VERSION = ? ";
                      counter++;
                    } else {
                      whereQuery =
                          " PortfolioLocation11.version = :PortfolioLocation11version" + j + " ";
                    }

                    isOpenParentheses = false;
                    whereValue =
                        new java.lang.Integer(
                            ""
                                + model
                                    .getPortfolio()
                                    .getPortfolioLocations()
                                    .get(i11)
                                    .getVersion());
                    query +=
                        (andCondition
                            + Facade.getStatement(
                                statement,
                                "PortfolioLocation11.version",
                                "PortfolioLocation11version" + j,
                                mapVariable,
                                whereQuery,
                                whereValue,
                                isExistRecord,
                                isDelete,
                                fetchType,
                                counter));
                    whereQuery = "";
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
                && fetchType == FetchType.EAGER
                && model.getPortfolio().getReceptions() != null) {
              int length17 = model.getPortfolio().getReceptions().size();

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

                  if (model.getPortfolio().getReceptions().get(i17).getReceptionNo() != null) {

                    andCondition = " AND ";
                    if (isOpenParentheses) {
                      andCondition = " ";
                    }
                    query += " ";
                    whereValue = model.getPortfolio().getReceptions().get(i17).getReceptionNo();
                    if (isDelete && fetchType == FetchType.EAGER) {
                      whereQuery = " Reception17.receptionNo = ? ";
                      counter++;
                    } else {
                      whereQuery = " Reception17.receptionNo = :Reception17receptionNo" + j + " ";
                    }

                    isOpenParentheses = false;
                    whereValue = model.getPortfolio().getReceptions().get(i17).getReceptionNo();
                    query +=
                        (andCondition
                            + Facade.getStatement(
                                statement,
                                "Reception17.receptionNo",
                                "Reception17receptionNo" + j,
                                mapVariable,
                                whereQuery,
                                whereValue,
                                isExistRecord,
                                isDelete,
                                fetchType,
                                counter));
                    whereQuery = "";
                  }
                  if (!isExistRecord
                      && !isDelete
                      && true
                      && model.getPortfolio().getReceptions().get(i17).getChannel() != null) {

                    andCondition = " AND ";
                    if (isOpenParentheses) {
                      andCondition = " ";
                    }
                    query += " ";
                    whereValue = model.getPortfolio().getReceptions().get(i17).getChannel();
                    if (isDelete && fetchType == FetchType.EAGER) {
                      whereQuery = " Reception17.channel = ? ";
                      counter++;
                    } else {
                      whereQuery = " Reception17.channel = :Reception17channel" + j + " ";
                    }

                    isOpenParentheses = false;
                    whereValue = model.getPortfolio().getReceptions().get(i17).getChannel();
                    query +=
                        (andCondition
                            + Facade.getStatement(
                                statement,
                                "Reception17.channel",
                                "Reception17channel" + j,
                                mapVariable,
                                whereQuery,
                                whereValue,
                                isExistRecord,
                                isDelete,
                                fetchType,
                                counter));
                    whereQuery = "";
                  }
                  if (!isExistRecord
                      && !isDelete
                      && true
                      && model.getPortfolio().getReceptions().get(i17).getExternalResource()
                          != null) {

                    andCondition = " AND ";
                    if (isOpenParentheses) {
                      andCondition = " ";
                    }
                    query += " ";
                    whereValue =
                        model.getPortfolio().getReceptions().get(i17).getExternalResource();
                    if (isDelete && fetchType == FetchType.EAGER) {
                      whereQuery = " Reception17.externalResource = ? ";
                      counter++;
                    } else {
                      whereQuery =
                          " Reception17.externalResource = :Reception17externalResource" + j + " ";
                    }

                    isOpenParentheses = false;
                    whereValue =
                        model.getPortfolio().getReceptions().get(i17).getExternalResource();
                    query +=
                        (andCondition
                            + Facade.getStatement(
                                statement,
                                "Reception17.externalResource",
                                "Reception17externalResource" + j,
                                mapVariable,
                                whereQuery,
                                whereValue,
                                isExistRecord,
                                isDelete,
                                fetchType,
                                counter));
                    whereQuery = "";
                  }
                  if (!isExistRecord
                      && !isDelete
                      && true
                      && model.getPortfolio().getReceptions().get(i17).getReceptionTitle()
                          != null) {

                    andCondition = " AND ";
                    if (isOpenParentheses) {
                      andCondition = " ";
                    }
                    query += " ";

                    whereValue =
                        model.getPortfolio().getReceptions().get(i17).getReceptionTitle() + "%";
                    if (isDelete && fetchType == FetchType.EAGER) {
                      whereQuery = " Reception17.receptionTitle = ? ";
                      counter++;
                    } else {
                      whereQuery =
                          " Reception17.receptionTitle LIKE :Reception17receptionTitle" + j + " ";
                    }

                    isOpenParentheses = false;
                    if (isExistRecord || isDelete) {
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " Reception17.receptionTitle = ? ";
                        counter++;
                      } else {
                        whereValue =
                            model.getPortfolio().getReceptions().get(i17).getReceptionTitle();
                      }
                    } else {
                      whereValue =
                          model.getPortfolio().getReceptions().get(i17).getReceptionTitle() + "%";
                    }

                    query +=
                        (andCondition
                            + Facade.getStatement(
                                statement,
                                "Reception17.receptionTitle",
                                "Reception17receptionTitle" + j,
                                mapVariable,
                                whereQuery,
                                whereValue,
                                isExistRecord,
                                isDelete,
                                fetchType,
                                counter));
                    whereQuery = "";
                  }
                  if (!isExistRecord
                      && !isDelete
                      && true
                      && model.getPortfolio().getReceptions().get(i17).getProductType() != null) {

                    andCondition = " AND ";
                    if (isOpenParentheses) {
                      andCondition = " ";
                    }
                    query += " ";
                    whereValue = model.getPortfolio().getReceptions().get(i17).getProductType();
                    if (isDelete && fetchType == FetchType.EAGER) {
                      whereQuery = " Reception17.productType = ? ";
                      counter++;
                    } else {
                      whereQuery = " Reception17.productType = :Reception17productType" + j + " ";
                    }

                    isOpenParentheses = false;
                    whereValue = model.getPortfolio().getReceptions().get(i17).getProductType();
                    query +=
                        (andCondition
                            + Facade.getStatement(
                                statement,
                                "Reception17.productType",
                                "Reception17productType" + j,
                                mapVariable,
                                whereQuery,
                                whereValue,
                                isExistRecord,
                                isDelete,
                                fetchType,
                                counter));
                    whereQuery = "";
                  }
                  if (!isExistRecord
                      && !isDelete
                      && true
                      && model.getPortfolio().getReceptions().get(i17).getLongLoanType() != null) {

                    andCondition = " AND ";
                    if (isOpenParentheses) {
                      andCondition = " ";
                    }
                    query += " ";
                    whereValue = model.getPortfolio().getReceptions().get(i17).getLongLoanType();
                    if (isDelete && fetchType == FetchType.EAGER) {
                      whereQuery = " Reception17.longLoanType = ? ";
                      counter++;
                    } else {
                      whereQuery = " Reception17.longLoanType = :Reception17longLoanType" + j + " ";
                    }

                    isOpenParentheses = false;
                    whereValue = model.getPortfolio().getReceptions().get(i17).getLongLoanType();
                    query +=
                        (andCondition
                            + Facade.getStatement(
                                statement,
                                "Reception17.longLoanType",
                                "Reception17longLoanType" + j,
                                mapVariable,
                                whereQuery,
                                whereValue,
                                isExistRecord,
                                isDelete,
                                fetchType,
                                counter));
                    whereQuery = "";
                  }
                  if (!isExistRecord
                      && !isDelete
                      && true
                      && model.getPortfolio().getReceptions().get(i17).getFinancingPurpose()
                          != null) {

                    andCondition = " AND ";
                    if (isOpenParentheses) {
                      andCondition = " ";
                    }
                    query += " ";
                    whereValue =
                        model.getPortfolio().getReceptions().get(i17).getFinancingPurpose();
                    if (isDelete && fetchType == FetchType.EAGER) {
                      whereQuery = " Reception17.financingPurpose = ? ";
                      counter++;
                    } else {
                      whereQuery =
                          " Reception17.financingPurpose = :Reception17financingPurpose" + j + " ";
                    }

                    isOpenParentheses = false;
                    whereValue =
                        model.getPortfolio().getReceptions().get(i17).getFinancingPurpose();
                    query +=
                        (andCondition
                            + Facade.getStatement(
                                statement,
                                "Reception17.financingPurpose",
                                "Reception17financingPurpose" + j,
                                mapVariable,
                                whereQuery,
                                whereValue,
                                isExistRecord,
                                isDelete,
                                fetchType,
                                counter));
                    whereQuery = "";
                  }
                  if (!isExistRecord
                      && !isDelete
                      && true
                      && model.getPortfolio().getReceptions().get(i17).getLow141() != null) {

                    andCondition = " AND ";
                    if (isOpenParentheses) {
                      andCondition = " ";
                    }
                    query += " ";
                    whereValue = model.getPortfolio().getReceptions().get(i17).getLow141();
                    if (isDelete && fetchType == FetchType.EAGER) {
                      whereQuery = " Reception17.low141 = ? ";
                      counter++;
                    } else {
                      whereQuery = " Reception17.low141 = :Reception17low141" + j + " ";
                    }

                    isOpenParentheses = false;
                    whereValue = model.getPortfolio().getReceptions().get(i17).getLow141();
                    query +=
                        (andCondition
                            + Facade.getStatement(
                                statement,
                                "Reception17.low141",
                                "Reception17low141" + j,
                                mapVariable,
                                whereQuery,
                                whereValue,
                                isExistRecord,
                                isDelete,
                                fetchType,
                                counter));
                    whereQuery = "";
                  }
                  if (!isExistRecord
                      && !isDelete
                      && true
                      && model.getPortfolio().getReceptions().get(i17).getShortTermLoanType()
                          != null) {

                    andCondition = " AND ";
                    if (isOpenParentheses) {
                      andCondition = " ";
                    }
                    query += " ";
                    whereValue =
                        model.getPortfolio().getReceptions().get(i17).getShortTermLoanType();
                    if (isDelete && fetchType == FetchType.EAGER) {
                      whereQuery = " Reception17.shortTermLoanType = ? ";
                      counter++;
                    } else {
                      whereQuery =
                          " Reception17.shortTermLoanType = :Reception17shortTermLoanType"
                              + j
                              + " ";
                    }

                    isOpenParentheses = false;
                    whereValue =
                        model.getPortfolio().getReceptions().get(i17).getShortTermLoanType();
                    query +=
                        (andCondition
                            + Facade.getStatement(
                                statement,
                                "Reception17.shortTermLoanType",
                                "Reception17shortTermLoanType" + j,
                                mapVariable,
                                whereQuery,
                                whereValue,
                                isExistRecord,
                                isDelete,
                                fetchType,
                                counter));
                    whereQuery = "";
                  }
                  if (!isExistRecord
                      && !isDelete
                      && true
                      && model.getPortfolio().getReceptions().get(i17).getDate() != null) {

                    andCondition = " AND ";
                    if (isOpenParentheses) {
                      andCondition = " ";
                    }
                    query += " ";
                    whereValue = model.getPortfolio().getReceptions().get(i17).getDate();
                    if (isDelete && fetchType == FetchType.EAGER) {
                      whereQuery = " Reception17.date1 = ? ";
                      counter++;
                    } else {
                      whereQuery = " Reception17.date = :Reception17date" + j + " ";
                    }

                    isOpenParentheses = false;
                    whereValue = model.getPortfolio().getReceptions().get(i17).getDate();
                    query +=
                        (andCondition
                            + Facade.getStatement(
                                statement,
                                "Reception17.date",
                                "Reception17date" + j,
                                mapVariable,
                                whereQuery,
                                whereValue,
                                isExistRecord,
                                isDelete,
                                fetchType,
                                counter));
                    whereQuery = "";
                  }
                  if (!isExistRecord
                      && !isDelete
                      && true
                      && model.getPortfolio().getReceptions().get(i17).getRejLetterDate() != null) {

                    andCondition = " AND ";
                    if (isOpenParentheses) {
                      andCondition = " ";
                    }
                    query += " ";
                    whereValue = model.getPortfolio().getReceptions().get(i17).getRejLetterDate();
                    if (isDelete && fetchType == FetchType.EAGER) {
                      whereQuery = " Reception17.rejLetterDate = ? ";
                      counter++;
                    } else {
                      whereQuery =
                          " Reception17.rejLetterDate = :Reception17rejLetterDate" + j + " ";
                    }

                    isOpenParentheses = false;
                    whereValue = model.getPortfolio().getReceptions().get(i17).getRejLetterDate();
                    query +=
                        (andCondition
                            + Facade.getStatement(
                                statement,
                                "Reception17.rejLetterDate",
                                "Reception17rejLetterDate" + j,
                                mapVariable,
                                whereQuery,
                                whereValue,
                                isExistRecord,
                                isDelete,
                                fetchType,
                                counter));
                    whereQuery = "";
                  }
                  if (!isExistRecord
                      && !isDelete
                      && true
                      && model.getPortfolio().getReceptions().get(i17).getCustomerCode() != null) {

                    andCondition = " AND ";
                    if (isOpenParentheses) {
                      andCondition = " ";
                    }
                    query += " ";

                    whereValue =
                        "%" + model.getPortfolio().getReceptions().get(i17).getCustomerCode();
                    if (isDelete && fetchType == FetchType.EAGER) {
                      whereQuery = " Reception17.customerCode = ? ";
                      counter++;
                    } else {
                      whereQuery =
                          " Reception17.customerCode LIKE :Reception17customerCode" + j + " ";
                    }

                    isOpenParentheses = false;
                    if (isExistRecord || isDelete) {
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " Reception17.customerCode = ? ";
                        counter++;
                      } else {
                        whereValue =
                            model.getPortfolio().getReceptions().get(i17).getCustomerCode();
                      }
                    } else {
                      whereValue =
                          "%" + model.getPortfolio().getReceptions().get(i17).getCustomerCode();
                    }

                    query +=
                        (andCondition
                            + Facade.getStatement(
                                statement,
                                "Reception17.customerCode",
                                "Reception17customerCode" + j,
                                mapVariable,
                                whereQuery,
                                whereValue,
                                isExistRecord,
                                isDelete,
                                fetchType,
                                counter));
                    whereQuery = "";
                  }
                  if (!isExistRecord
                      && !isDelete
                      && true
                      && model.getPortfolio().getReceptions().get(i17).getBranch() != null) {

                    andCondition = " AND ";
                    if (isOpenParentheses) {
                      andCondition = " ";
                    }
                    query += " ";

                    whereValue =
                        "%" + model.getPortfolio().getReceptions().get(i17).getBranch() + "%";
                    if (isDelete && fetchType == FetchType.EAGER) {
                      whereQuery = " Reception17.branch = ? ";
                      counter++;
                    } else {
                      whereQuery = " Reception17.branch LIKE :Reception17branch" + j + " ";
                    }

                    isOpenParentheses = false;
                    if (isExistRecord || isDelete) {
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " Reception17.branch = ? ";
                        counter++;
                      } else {
                        whereValue = model.getPortfolio().getReceptions().get(i17).getBranch();
                      }
                    } else {
                      whereValue =
                          "%" + model.getPortfolio().getReceptions().get(i17).getBranch() + "%";
                    }

                    query +=
                        (andCondition
                            + Facade.getStatement(
                                statement,
                                "Reception17.branch",
                                "Reception17branch" + j,
                                mapVariable,
                                whereQuery,
                                whereValue,
                                isExistRecord,
                                isDelete,
                                fetchType,
                                counter));
                    whereQuery = "";
                  }
                  if (!isExistRecord
                      && !isDelete
                      && true
                      && model.getPortfolio().getReceptions().get(i17).getReceptionState()
                          != null) {

                    andCondition = " AND ";
                    if (isOpenParentheses) {
                      andCondition = " ";
                    }
                    query += " ";
                    whereValue = model.getPortfolio().getReceptions().get(i17).getReceptionState();
                    if (isDelete && fetchType == FetchType.EAGER) {
                      whereQuery = " Reception17.receptionState = ? ";
                      counter++;
                    } else {
                      whereQuery =
                          " Reception17.receptionState = :Reception17receptionState" + j + " ";
                    }

                    isOpenParentheses = false;
                    whereValue = model.getPortfolio().getReceptions().get(i17).getReceptionState();
                    query +=
                        (andCondition
                            + Facade.getStatement(
                                statement,
                                "Reception17.receptionState",
                                "Reception17receptionState" + j,
                                mapVariable,
                                whereQuery,
                                whereValue,
                                isExistRecord,
                                isDelete,
                                fetchType,
                                counter));
                    whereQuery = "";
                  }
                  if (!isExistRecord
                      && !isDelete
                      && true
                      && model.getPortfolio().getReceptions().get(i17).getRepresentativeRefType()
                          != null) {

                    andCondition = " AND ";
                    if (isOpenParentheses) {
                      andCondition = " ";
                    }
                    query += " ";
                    whereValue =
                        model.getPortfolio().getReceptions().get(i17).getRepresentativeRefType();
                    if (isDelete && fetchType == FetchType.EAGER) {
                      whereQuery = " Reception17.representativeRefType = ? ";
                      counter++;
                    } else {
                      whereQuery =
                          " Reception17.representativeRefType = :Reception17representativeRefType"
                              + j
                              + " ";
                    }

                    isOpenParentheses = false;
                    whereValue =
                        model.getPortfolio().getReceptions().get(i17).getRepresentativeRefType();
                    query +=
                        (andCondition
                            + Facade.getStatement(
                                statement,
                                "Reception17.representativeRefType",
                                "Reception17representativeRefType" + j,
                                mapVariable,
                                whereQuery,
                                whereValue,
                                isExistRecord,
                                isDelete,
                                fetchType,
                                counter));
                    whereQuery = "";
                  }
                  if (!isExistRecord
                      && !isDelete
                      && true
                      && model.getPortfolio().getReceptions().get(i17).getApplicantCreditType()
                          != null) {

                    andCondition = " AND ";
                    if (isOpenParentheses) {
                      andCondition = " ";
                    }
                    query += " ";
                    whereValue =
                        model.getPortfolio().getReceptions().get(i17).getApplicantCreditType();
                    if (isDelete && fetchType == FetchType.EAGER) {
                      whereQuery = " Reception17.applicantCreditType = ? ";
                      counter++;
                    } else {
                      whereQuery =
                          " Reception17.applicantCreditType = :Reception17applicantCreditType"
                              + j
                              + " ";
                    }

                    isOpenParentheses = false;
                    whereValue =
                        model.getPortfolio().getReceptions().get(i17).getApplicantCreditType();
                    query +=
                        (andCondition
                            + Facade.getStatement(
                                statement,
                                "Reception17.applicantCreditType",
                                "Reception17applicantCreditType" + j,
                                mapVariable,
                                whereQuery,
                                whereValue,
                                isExistRecord,
                                isDelete,
                                fetchType,
                                counter));
                    whereQuery = "";
                  }
                  if (!isExistRecord
                      && !isDelete
                      && true
                      && model.getPortfolio().getReceptions().get(i17).getUnderTakeHisType()
                          != null) {

                    andCondition = " AND ";
                    if (isOpenParentheses) {
                      andCondition = " ";
                    }
                    query += " ";
                    whereValue =
                        model.getPortfolio().getReceptions().get(i17).getUnderTakeHisType();
                    if (isDelete && fetchType == FetchType.EAGER) {
                      whereQuery = " Reception17.underTakeHisType = ? ";
                      counter++;
                    } else {
                      whereQuery =
                          " Reception17.underTakeHisType = :Reception17underTakeHisType" + j + " ";
                    }

                    isOpenParentheses = false;
                    whereValue =
                        model.getPortfolio().getReceptions().get(i17).getUnderTakeHisType();
                    query +=
                        (andCondition
                            + Facade.getStatement(
                                statement,
                                "Reception17.underTakeHisType",
                                "Reception17underTakeHisType" + j,
                                mapVariable,
                                whereQuery,
                                whereValue,
                                isExistRecord,
                                isDelete,
                                fetchType,
                                counter));
                    whereQuery = "";
                  }
                  if (!isExistRecord
                      && !isDelete
                      && true
                      && model.getPortfolio().getReceptions().get(i17).getCheckRejectType()
                          != null) {

                    andCondition = " AND ";
                    if (isOpenParentheses) {
                      andCondition = " ";
                    }
                    query += " ";
                    whereValue = model.getPortfolio().getReceptions().get(i17).getCheckRejectType();
                    if (isDelete && fetchType == FetchType.EAGER) {
                      whereQuery = " Reception17.checkRejectType = ? ";
                      counter++;
                    } else {
                      whereQuery =
                          " Reception17.checkRejectType = :Reception17checkRejectType" + j + " ";
                    }

                    isOpenParentheses = false;
                    whereValue = model.getPortfolio().getReceptions().get(i17).getCheckRejectType();
                    query +=
                        (andCondition
                            + Facade.getStatement(
                                statement,
                                "Reception17.checkRejectType",
                                "Reception17checkRejectType" + j,
                                mapVariable,
                                whereQuery,
                                whereValue,
                                isExistRecord,
                                isDelete,
                                fetchType,
                                counter));
                    whereQuery = "";
                  }
                  if (!isExistRecord
                      && !isDelete
                      && true
                      && model.getPortfolio().getReceptions().get(i17).getCashFocusStatusType()
                          != null) {

                    andCondition = " AND ";
                    if (isOpenParentheses) {
                      andCondition = " ";
                    }
                    query += " ";
                    whereValue =
                        model.getPortfolio().getReceptions().get(i17).getCashFocusStatusType();
                    if (isDelete && fetchType == FetchType.EAGER) {
                      whereQuery = " Reception17.cashFocusStatusType = ? ";
                      counter++;
                    } else {
                      whereQuery =
                          " Reception17.cashFocusStatusType = :Reception17cashFocusStatusType"
                              + j
                              + " ";
                    }

                    isOpenParentheses = false;
                    whereValue =
                        model.getPortfolio().getReceptions().get(i17).getCashFocusStatusType();
                    query +=
                        (andCondition
                            + Facade.getStatement(
                                statement,
                                "Reception17.cashFocusStatusType",
                                "Reception17cashFocusStatusType" + j,
                                mapVariable,
                                whereQuery,
                                whereValue,
                                isExistRecord,
                                isDelete,
                                fetchType,
                                counter));
                    whereQuery = "";
                  }
                  if (!isExistRecord
                      && !isDelete
                      && true
                      && model.getPortfolio().getReceptions().get(i17).getEquation() != null) {

                    andCondition = " AND ";
                    if (isOpenParentheses) {
                      andCondition = " ";
                    }
                    query += " ";
                    whereValue = model.getPortfolio().getReceptions().get(i17).getEquation();
                    if (isDelete && fetchType == FetchType.EAGER) {
                      whereQuery = " Reception17.equation = ? ";
                      counter++;
                    } else {
                      whereQuery = " Reception17.equation = :Reception17equation" + j + " ";
                    }

                    isOpenParentheses = false;
                    whereValue = model.getPortfolio().getReceptions().get(i17).getEquation();
                    query +=
                        (andCondition
                            + Facade.getStatement(
                                statement,
                                "Reception17.equation",
                                "Reception17equation" + j,
                                mapVariable,
                                whereQuery,
                                whereValue,
                                isExistRecord,
                                isDelete,
                                fetchType,
                                counter));
                    whereQuery = "";
                  }
                  if (!isExistRecord
                      && !isDelete
                      && true
                      && model.getPortfolio().getReceptions().get(i17).getIrrEstimate() != null) {

                    andCondition = " AND ";
                    if (isOpenParentheses) {
                      andCondition = " ";
                    }
                    query += " ";
                    whereValue = model.getPortfolio().getReceptions().get(i17).getIrrEstimate();
                    if (isDelete && fetchType == FetchType.EAGER) {
                      whereQuery = " Reception17.irrEstimate = ? ";
                      counter++;
                    } else {
                      whereQuery = " Reception17.irrEstimate = :Reception17irrEstimate" + j + " ";
                    }

                    isOpenParentheses = false;
                    whereValue = model.getPortfolio().getReceptions().get(i17).getIrrEstimate();
                    query +=
                        (andCondition
                            + Facade.getStatement(
                                statement,
                                "Reception17.irrEstimate",
                                "Reception17irrEstimate" + j,
                                mapVariable,
                                whereQuery,
                                whereValue,
                                isExistRecord,
                                isDelete,
                                fetchType,
                                counter));
                    whereQuery = "";
                  }
                  if (!isExistRecord
                      && !isDelete
                      && true
                      && model.getPortfolio().getReceptions().get(i17).getHeadPoint() != null) {

                    andCondition = " AND ";
                    if (isOpenParentheses) {
                      andCondition = " ";
                    }
                    query += " ";
                    whereValue = model.getPortfolio().getReceptions().get(i17).getHeadPoint();
                    if (isDelete && fetchType == FetchType.EAGER) {
                      whereQuery = " Reception17.headPoint = ? ";
                      counter++;
                    } else {
                      whereQuery = " Reception17.headPoint = :Reception17headPoint" + j + " ";
                    }

                    isOpenParentheses = false;
                    whereValue = model.getPortfolio().getReceptions().get(i17).getHeadPoint();
                    query +=
                        (andCondition
                            + Facade.getStatement(
                                statement,
                                "Reception17.headPoint",
                                "Reception17headPoint" + j,
                                mapVariable,
                                whereQuery,
                                whereValue,
                                isExistRecord,
                                isDelete,
                                fetchType,
                                counter));
                    whereQuery = "";
                  }
                  if (!isExistRecord
                      && !isDelete
                      && true
                      && model.getPortfolio().getReceptions().get(i17).getCashControl() != null) {

                    andCondition = " AND ";
                    if (isOpenParentheses) {
                      andCondition = " ";
                    }
                    query += " ";
                    whereValue = model.getPortfolio().getReceptions().get(i17).getCashControl();
                    if (isDelete && fetchType == FetchType.EAGER) {
                      whereQuery = " Reception17.cashControl = ? ";
                      counter++;
                    } else {
                      whereQuery = " Reception17.cashControl = :Reception17cashControl" + j + " ";
                    }

                    isOpenParentheses = false;
                    whereValue = model.getPortfolio().getReceptions().get(i17).getCashControl();
                    query +=
                        (andCondition
                            + Facade.getStatement(
                                statement,
                                "Reception17.cashControl",
                                "Reception17cashControl" + j,
                                mapVariable,
                                whereQuery,
                                whereValue,
                                isExistRecord,
                                isDelete,
                                fetchType,
                                counter));
                    whereQuery = "";
                  }
                  if (!isExistRecord
                      && !isDelete
                      && true
                      && model.getPortfolio().getReceptions().get(i17).getExportMarketDsc()
                          != null) {

                    andCondition = " AND ";
                    if (isOpenParentheses) {
                      andCondition = " ";
                    }
                    query += " ";
                    whereValue = model.getPortfolio().getReceptions().get(i17).getExportMarketDsc();
                    if (isDelete && fetchType == FetchType.EAGER) {
                      whereQuery = " Reception17.exportMarketDsc = ? ";
                      counter++;
                    } else {
                      whereQuery =
                          " Reception17.exportMarketDsc = :Reception17exportMarketDsc" + j + " ";
                    }

                    isOpenParentheses = false;
                    whereValue = model.getPortfolio().getReceptions().get(i17).getExportMarketDsc();
                    query +=
                        (andCondition
                            + Facade.getStatement(
                                statement,
                                "Reception17.exportMarketDsc",
                                "Reception17exportMarketDsc" + j,
                                mapVariable,
                                whereQuery,
                                whereValue,
                                isExistRecord,
                                isDelete,
                                fetchType,
                                counter));
                    whereQuery = "";
                  }
                  if (!isExistRecord
                      && !isDelete
                      && true
                      && model.getPortfolio().getReceptions().get(i17).getInternalMarketDsc()
                          != null) {

                    andCondition = " AND ";
                    if (isOpenParentheses) {
                      andCondition = " ";
                    }
                    query += " ";
                    whereValue =
                        model.getPortfolio().getReceptions().get(i17).getInternalMarketDsc();
                    if (isDelete && fetchType == FetchType.EAGER) {
                      whereQuery = " Reception17.internalMarketDsc = ? ";
                      counter++;
                    } else {
                      whereQuery =
                          " Reception17.internalMarketDsc = :Reception17internalMarketDsc"
                              + j
                              + " ";
                    }

                    isOpenParentheses = false;
                    whereValue =
                        model.getPortfolio().getReceptions().get(i17).getInternalMarketDsc();
                    query +=
                        (andCondition
                            + Facade.getStatement(
                                statement,
                                "Reception17.internalMarketDsc",
                                "Reception17internalMarketDsc" + j,
                                mapVariable,
                                whereQuery,
                                whereValue,
                                isExistRecord,
                                isDelete,
                                fetchType,
                                counter));
                    whereQuery = "";
                  }
                  if (!isExistRecord
                      && !isDelete
                      && true
                      && model.getPortfolio().getReceptions().get(i17).getRowMaterialDsc()
                          != null) {

                    andCondition = " AND ";
                    if (isOpenParentheses) {
                      andCondition = " ";
                    }
                    query += " ";
                    whereValue = model.getPortfolio().getReceptions().get(i17).getRowMaterialDsc();
                    if (isDelete && fetchType == FetchType.EAGER) {
                      whereQuery = " Reception17.rowMaterialDsc = ? ";
                      counter++;
                    } else {
                      whereQuery =
                          " Reception17.rowMaterialDsc = :Reception17rowMaterialDsc" + j + " ";
                    }

                    isOpenParentheses = false;
                    whereValue = model.getPortfolio().getReceptions().get(i17).getRowMaterialDsc();
                    query +=
                        (andCondition
                            + Facade.getStatement(
                                statement,
                                "Reception17.rowMaterialDsc",
                                "Reception17rowMaterialDsc" + j,
                                mapVariable,
                                whereQuery,
                                whereValue,
                                isExistRecord,
                                isDelete,
                                fetchType,
                                counter));
                    whereQuery = "";
                  }
                  if (!isExistRecord
                      && !isDelete
                      && true
                      && model.getPortfolio().getReceptions().get(i17).getMarketProductDesc()
                          != null) {

                    andCondition = " AND ";
                    if (isOpenParentheses) {
                      andCondition = " ";
                    }
                    query += " ";
                    whereValue =
                        model.getPortfolio().getReceptions().get(i17).getMarketProductDesc();
                    if (isDelete && fetchType == FetchType.EAGER) {
                      whereQuery = " Reception17.marketProductDesc = ? ";
                      counter++;
                    } else {
                      whereQuery =
                          " Reception17.marketProductDesc = :Reception17marketProductDesc"
                              + j
                              + " ";
                    }

                    isOpenParentheses = false;
                    whereValue =
                        model.getPortfolio().getReceptions().get(i17).getMarketProductDesc();
                    query +=
                        (andCondition
                            + Facade.getStatement(
                                statement,
                                "Reception17.marketProductDesc",
                                "Reception17marketProductDesc" + j,
                                mapVariable,
                                whereQuery,
                                whereValue,
                                isExistRecord,
                                isDelete,
                                fetchType,
                                counter));
                    whereQuery = "";
                  }
                  if (!isExistRecord
                      && !isDelete
                      && true
                      && model.getPortfolio().getReceptions().get(i17).getProductiveEmployee()
                          != null) {

                    andCondition = " AND ";
                    if (isOpenParentheses) {
                      andCondition = " ";
                    }
                    query += " ";
                    whereValue =
                        model.getPortfolio().getReceptions().get(i17).getProductiveEmployee();
                    if (isDelete && fetchType == FetchType.EAGER) {
                      whereQuery = " Reception17.productiveEmployee = ? ";
                      counter++;
                    } else {
                      whereQuery =
                          " Reception17.productiveEmployee = :Reception17productiveEmployee"
                              + j
                              + " ";
                    }

                    isOpenParentheses = false;
                    whereValue =
                        model.getPortfolio().getReceptions().get(i17).getProductiveEmployee();
                    query +=
                        (andCondition
                            + Facade.getStatement(
                                statement,
                                "Reception17.productiveEmployee",
                                "Reception17productiveEmployee" + j,
                                mapVariable,
                                whereQuery,
                                whereValue,
                                isExistRecord,
                                isDelete,
                                fetchType,
                                counter));
                    whereQuery = "";
                  }
                  if (!isExistRecord
                      && !isDelete
                      && true
                      && model.getPortfolio().getReceptions().get(i17).getNonProductiveEmployee()
                          != null) {

                    andCondition = " AND ";
                    if (isOpenParentheses) {
                      andCondition = " ";
                    }
                    query += " ";
                    whereValue =
                        model.getPortfolio().getReceptions().get(i17).getNonProductiveEmployee();
                    if (isDelete && fetchType == FetchType.EAGER) {
                      whereQuery = " Reception17.nonProductiveEmployee = ? ";
                      counter++;
                    } else {
                      whereQuery =
                          " Reception17.nonProductiveEmployee = :Reception17nonProductiveEmployee"
                              + j
                              + " ";
                    }

                    isOpenParentheses = false;
                    whereValue =
                        model.getPortfolio().getReceptions().get(i17).getNonProductiveEmployee();
                    query +=
                        (andCondition
                            + Facade.getStatement(
                                statement,
                                "Reception17.nonProductiveEmployee",
                                "Reception17nonProductiveEmployee" + j,
                                mapVariable,
                                whereQuery,
                                whereValue,
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
                              .getPortfolio()
                              .getReceptions()
                              .get(i17)
                              .getForecastProductiveEmployee()
                          != null) {

                    andCondition = " AND ";
                    if (isOpenParentheses) {
                      andCondition = " ";
                    }
                    query += " ";
                    whereValue =
                        model
                            .getPortfolio()
                            .getReceptions()
                            .get(i17)
                            .getForecastProductiveEmployee();
                    if (isDelete && fetchType == FetchType.EAGER) {
                      whereQuery = " Reception17.forecastProductiveEmployee = ? ";
                      counter++;
                    } else {
                      whereQuery =
                          " Reception17.forecastProductiveEmployee = :Reception17forecastProductiveEmployee"
                              + j
                              + " ";
                    }

                    isOpenParentheses = false;
                    whereValue =
                        model
                            .getPortfolio()
                            .getReceptions()
                            .get(i17)
                            .getForecastProductiveEmployee();
                    query +=
                        (andCondition
                            + Facade.getStatement(
                                statement,
                                "Reception17.forecastProductiveEmployee",
                                "Reception17forecastProductiveEmployee" + j,
                                mapVariable,
                                whereQuery,
                                whereValue,
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
                              .getPortfolio()
                              .getReceptions()
                              .get(i17)
                              .getForecastNonProductiveEmployee()
                          != null) {

                    andCondition = " AND ";
                    if (isOpenParentheses) {
                      andCondition = " ";
                    }
                    query += " ";
                    whereValue =
                        model
                            .getPortfolio()
                            .getReceptions()
                            .get(i17)
                            .getForecastNonProductiveEmployee();
                    if (isDelete && fetchType == FetchType.EAGER) {
                      whereQuery = " Reception17.forecastNonProductiveEmployee = ? ";
                      counter++;
                    } else {
                      whereQuery =
                          " Reception17.forecastNonProductiveEmployee = :Reception17forecastNonProductiveEmployee"
                              + j
                              + " ";
                    }

                    isOpenParentheses = false;
                    whereValue =
                        model
                            .getPortfolio()
                            .getReceptions()
                            .get(i17)
                            .getForecastNonProductiveEmployee();
                    query +=
                        (andCondition
                            + Facade.getStatement(
                                statement,
                                "Reception17.forecastNonProductiveEmployee",
                                "Reception17forecastNonProductiveEmployee" + j,
                                mapVariable,
                                whereQuery,
                                whereValue,
                                isExistRecord,
                                isDelete,
                                fetchType,
                                counter));
                    whereQuery = "";
                  }
                  if (!isExistRecord
                      && !isDelete
                      && true
                      && model.getPortfolio().getReceptions().get(i17).getCusPrePayAmnt() != null) {

                    andCondition = " AND ";
                    if (isOpenParentheses) {
                      andCondition = " ";
                    }
                    query += " ";
                    whereValue = model.getPortfolio().getReceptions().get(i17).getCusPrePayAmnt();
                    if (isDelete && fetchType == FetchType.EAGER) {
                      whereQuery = " Reception17.cusPrePayAmnt = ? ";
                      counter++;
                    } else {
                      whereQuery =
                          " Reception17.cusPrePayAmnt = :Reception17cusPrePayAmnt" + j + " ";
                    }

                    isOpenParentheses = false;
                    whereValue = model.getPortfolio().getReceptions().get(i17).getCusPrePayAmnt();
                    query +=
                        (andCondition
                            + Facade.getStatement(
                                statement,
                                "Reception17.cusPrePayAmnt",
                                "Reception17cusPrePayAmnt" + j,
                                mapVariable,
                                whereQuery,
                                whereValue,
                                isExistRecord,
                                isDelete,
                                fetchType,
                                counter));
                    whereQuery = "";
                  }
                  if (!isExistRecord
                      && !isDelete
                      && true
                      && model.getPortfolio().getReceptions().get(i17).getFinancialUser() != null) {

                    andCondition = " AND ";
                    if (isOpenParentheses) {
                      andCondition = " ";
                    }
                    query += " ";
                    whereValue = model.getPortfolio().getReceptions().get(i17).getFinancialUser();
                    if (isDelete && fetchType == FetchType.EAGER) {
                      whereQuery = " Reception17.financialUser = ? ";
                      counter++;
                    } else {
                      whereQuery =
                          " Reception17.financialUser = :Reception17financialUser" + j + " ";
                    }

                    isOpenParentheses = false;
                    whereValue = model.getPortfolio().getReceptions().get(i17).getFinancialUser();
                    query +=
                        (andCondition
                            + Facade.getStatement(
                                statement,
                                "Reception17.financialUser",
                                "Reception17financialUser" + j,
                                mapVariable,
                                whereQuery,
                                whereValue,
                                isExistRecord,
                                isDelete,
                                fetchType,
                                counter));
                    whereQuery = "";
                  }
                  if (!isExistRecord
                      && !isDelete
                      && true
                      && model.getPortfolio().getReceptions().get(i17).getMarketUser() != null) {

                    andCondition = " AND ";
                    if (isOpenParentheses) {
                      andCondition = " ";
                    }
                    query += " ";
                    whereValue = model.getPortfolio().getReceptions().get(i17).getMarketUser();
                    if (isDelete && fetchType == FetchType.EAGER) {
                      whereQuery = " Reception17.marketUser = ? ";
                      counter++;
                    } else {
                      whereQuery = " Reception17.marketUser = :Reception17marketUser" + j + " ";
                    }

                    isOpenParentheses = false;
                    whereValue = model.getPortfolio().getReceptions().get(i17).getMarketUser();
                    query +=
                        (andCondition
                            + Facade.getStatement(
                                statement,
                                "Reception17.marketUser",
                                "Reception17marketUser" + j,
                                mapVariable,
                                whereQuery,
                                whereValue,
                                isExistRecord,
                                isDelete,
                                fetchType,
                                counter));
                    whereQuery = "";
                  }
                  if (!isExistRecord
                      && !isDelete
                      && true
                      && model.getPortfolio().getReceptions().get(i17).getTechnicalUser() != null) {

                    andCondition = " AND ";
                    if (isOpenParentheses) {
                      andCondition = " ";
                    }
                    query += " ";
                    whereValue = model.getPortfolio().getReceptions().get(i17).getTechnicalUser();
                    if (isDelete && fetchType == FetchType.EAGER) {
                      whereQuery = " Reception17.technicalUser = ? ";
                      counter++;
                    } else {
                      whereQuery =
                          " Reception17.technicalUser = :Reception17technicalUser" + j + " ";
                    }

                    isOpenParentheses = false;
                    whereValue = model.getPortfolio().getReceptions().get(i17).getTechnicalUser();
                    query +=
                        (andCondition
                            + Facade.getStatement(
                                statement,
                                "Reception17.technicalUser",
                                "Reception17technicalUser" + j,
                                mapVariable,
                                whereQuery,
                                whereValue,
                                isExistRecord,
                                isDelete,
                                fetchType,
                                counter));
                    whereQuery = "";
                  }
                  if (!isExistRecord
                      && !isDelete
                      && true
                      && model.getPortfolio().getReceptions().get(i17).getCurrentAssetReceptionNo()
                          != null) {

                    andCondition = " AND ";
                    if (isOpenParentheses) {
                      andCondition = " ";
                    }
                    query += " ";
                    whereValue =
                        model.getPortfolio().getReceptions().get(i17).getCurrentAssetReceptionNo();
                    if (isDelete && fetchType == FetchType.EAGER) {
                      whereQuery = " Reception17.currentAssetReceptionNo = ? ";
                      counter++;
                    } else {
                      whereQuery =
                          " Reception17.currentAssetReceptionNo = :Reception17currentAssetReceptionNo"
                              + j
                              + " ";
                    }

                    isOpenParentheses = false;
                    whereValue =
                        model.getPortfolio().getReceptions().get(i17).getCurrentAssetReceptionNo();
                    query +=
                        (andCondition
                            + Facade.getStatement(
                                statement,
                                "Reception17.currentAssetReceptionNo",
                                "Reception17currentAssetReceptionNo" + j,
                                mapVariable,
                                whereQuery,
                                whereValue,
                                isExistRecord,
                                isDelete,
                                fetchType,
                                counter));
                    whereQuery = "";
                  }
                  if (!isExistRecord
                      && !isDelete
                      && true
                      && model.getPortfolio().getReceptions().get(i17).getVersion() != 0) {

                    andCondition = " AND ";
                    if (isOpenParentheses) {
                      andCondition = " ";
                    }
                    query += " ";
                    whereValue =
                        new java.lang.Integer(
                            "" + model.getPortfolio().getReceptions().get(i17).getVersion());
                    if (isDelete && fetchType == FetchType.EAGER) {
                      whereQuery = " Reception17.VERSION = ? ";
                      counter++;
                    } else {
                      whereQuery = " Reception17.version = :Reception17version" + j + " ";
                    }

                    isOpenParentheses = false;
                    whereValue =
                        new java.lang.Integer(
                            "" + model.getPortfolio().getReceptions().get(i17).getVersion());
                    query +=
                        (andCondition
                            + Facade.getStatement(
                                statement,
                                "Reception17.version",
                                "Reception17version" + j,
                                mapVariable,
                                whereQuery,
                                whereValue,
                                isExistRecord,
                                isDelete,
                                fetchType,
                                counter));
                    whereQuery = "";
                  }
                  if (length17 > 1) {
                    query += " ) ";
                  }
                }

                query += ")";
                isOpenParentheses = false;
              }
            }
            if (!isDelete && model.getPortfolio().getAddress() != null) {
              if (!isDelete
                  && true
                  && model.getPortfolio().getAddress().getAddressLine1() != null) {

                andCondition = " AND ";
                if (isOpenParentheses) {
                  andCondition = " ";
                }
                query += " ";
                if (isDelete && fetchType == FetchType.EAGER) {
                  whereQuery = " Portfolio1.addressLine1 = ? ";
                  counter++;
                } else {
                  whereQuery =
                      " Portfolio1.address.addressLine1 = :Portfolio1addressaddressLine1" + j + " ";
                }

                isOpenParentheses = false;
                whereValue = model.getPortfolio().getAddress().getAddressLine1();
                query +=
                    (andCondition
                        + Facade.getStatement(
                            statement,
                            "Portfolio1.address.addressLine1",
                            "Portfolio1addressaddressLine1" + j,
                            mapVariable,
                            whereQuery,
                            whereValue,
                            isExistRecord,
                            isDelete,
                            fetchType,
                            counter));
                whereQuery = "";
              }
              if (!isExistRecord
                  && !isDelete
                  && true
                  && model.getPortfolio().getAddress().getAddressLine2() != null) {

                andCondition = " AND ";
                if (isOpenParentheses) {
                  andCondition = " ";
                }
                query += " ";
                if (isDelete && fetchType == FetchType.EAGER) {
                  whereQuery = " Portfolio1.addressLine2 = ? ";
                  counter++;
                } else {
                  whereQuery =
                      " Portfolio1.address.addressLine2 = :Portfolio1addressaddressLine2" + j + " ";
                }

                isOpenParentheses = false;
                whereValue = model.getPortfolio().getAddress().getAddressLine2();
                query +=
                    (andCondition
                        + Facade.getStatement(
                            statement,
                            "Portfolio1.address.addressLine2",
                            "Portfolio1addressaddressLine2" + j,
                            mapVariable,
                            whereQuery,
                            whereValue,
                            isExistRecord,
                            isDelete,
                            fetchType,
                            counter));
                whereQuery = "";
              }
              if (!isExistRecord
                  && !isDelete
                  && true
                  && model.getPortfolio().getAddress().getCity() != null) {

                andCondition = " AND ";
                if (isOpenParentheses) {
                  andCondition = " ";
                }
                query += " ";
                if (isDelete && fetchType == FetchType.EAGER) {
                  whereQuery = " Portfolio1.city = ? ";
                  counter++;
                } else {
                  whereQuery = " Portfolio1.address.city = :Portfolio1addresscity" + j + " ";
                }

                isOpenParentheses = false;
                whereValue = model.getPortfolio().getAddress().getCity();
                query +=
                    (andCondition
                        + Facade.getStatement(
                            statement,
                            "Portfolio1.address.city",
                            "Portfolio1addresscity" + j,
                            mapVariable,
                            whereQuery,
                            whereValue,
                            isExistRecord,
                            isDelete,
                            fetchType,
                            counter));
                whereQuery = "";
              }
              if (!isExistRecord
                  && !isDelete
                  && true
                  && model.getPortfolio().getAddress().getState() != null) {

                andCondition = " AND ";
                if (isOpenParentheses) {
                  andCondition = " ";
                }
                query += " ";
                if (isDelete && fetchType == FetchType.EAGER) {
                  whereQuery = " Portfolio1.state = ? ";
                  counter++;
                } else {
                  whereQuery = " Portfolio1.address.state = :Portfolio1addressstate" + j + " ";
                }

                isOpenParentheses = false;
                whereValue = model.getPortfolio().getAddress().getState();
                query +=
                    (andCondition
                        + Facade.getStatement(
                            statement,
                            "Portfolio1.address.state",
                            "Portfolio1addressstate" + j,
                            mapVariable,
                            whereQuery,
                            whereValue,
                            isExistRecord,
                            isDelete,
                            fetchType,
                            counter));
                whereQuery = "";
              }
              if (!isExistRecord
                  && !isDelete
                  && true
                  && model.getPortfolio().getAddress().getCountry() != null) {

                andCondition = " AND ";
                if (isOpenParentheses) {
                  andCondition = " ";
                }
                query += " ";
                if (isDelete && fetchType == FetchType.EAGER) {
                  whereQuery = " Portfolio1.country = ? ";
                  counter++;
                } else {
                  whereQuery = " Portfolio1.address.country = :Portfolio1addresscountry" + j + " ";
                }

                isOpenParentheses = false;
                whereValue = model.getPortfolio().getAddress().getCountry();
                query +=
                    (andCondition
                        + Facade.getStatement(
                            statement,
                            "Portfolio1.address.country",
                            "Portfolio1addresscountry" + j,
                            mapVariable,
                            whereQuery,
                            whereValue,
                            isExistRecord,
                            isDelete,
                            fetchType,
                            counter));
                whereQuery = "";
              }
              if (!isExistRecord
                  && !isDelete
                  && true
                  && model.getPortfolio().getAddress().getZipCode() != null) {

                andCondition = " AND ";
                if (isOpenParentheses) {
                  andCondition = " ";
                }
                query += " ";
                if (isDelete && fetchType == FetchType.EAGER) {
                  whereQuery = " Portfolio1.zipCode = ? ";
                  counter++;
                } else {
                  whereQuery = " Portfolio1.address.zipCode = :Portfolio1addresszipCode" + j + " ";
                }

                isOpenParentheses = false;
                whereValue = model.getPortfolio().getAddress().getZipCode();
                query +=
                    (andCondition
                        + Facade.getStatement(
                            statement,
                            "Portfolio1.address.zipCode",
                            "Portfolio1addresszipCode" + j,
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
                && model.getPortfolio().getCustomerId() != null) {

              andCondition = " AND ";
              if (isOpenParentheses) {
                andCondition = " ";
              }
              query += " ";
              if (isDelete && fetchType == FetchType.EAGER) {
                whereQuery = " Portfolio1.customerId = ? ";
                counter++;
              } else {
                whereQuery = " Portfolio1.customerId = :Portfolio1customerId" + j + " ";
              }

              isOpenParentheses = false;
              whereValue = model.getPortfolio().getCustomerId();
              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "Portfolio1.customerId",
                          "Portfolio1customerId" + j,
                          mapVariable,
                          whereQuery,
                          whereValue,
                          isExistRecord,
                          isDelete,
                          fetchType,
                          counter));
              whereQuery = "";
            }
            if (!isExistRecord && !isDelete && true && model.getPortfolio().getTitle() != null) {

              andCondition = " AND ";
              if (isOpenParentheses) {
                andCondition = " ";
              }
              query += " ";
              if (isDelete && fetchType == FetchType.EAGER) {
                whereQuery = " Portfolio1.title = ? ";
                counter++;
              } else {
                whereQuery = " Portfolio1.title = :Portfolio1title" + j + " ";
              }

              isOpenParentheses = false;
              whereValue = model.getPortfolio().getTitle();
              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "Portfolio1.title",
                          "Portfolio1title" + j,
                          mapVariable,
                          whereQuery,
                          whereValue,
                          isExistRecord,
                          isDelete,
                          fetchType,
                          counter));
              whereQuery = "";
            }
            if (!isExistRecord
                && !isDelete
                && true
                && model.getPortfolio().getBranchCode() != null) {

              andCondition = " AND ";
              if (isOpenParentheses) {
                andCondition = " ";
              }
              query += " ";
              if (isDelete && fetchType == FetchType.EAGER) {
                whereQuery = " Portfolio1.branchCode = ? ";
                counter++;
              } else {
                whereQuery = " Portfolio1.branchCode = :Portfolio1branchCode" + j + " ";
              }

              isOpenParentheses = false;
              whereValue = model.getPortfolio().getBranchCode();
              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "Portfolio1.branchCode",
                          "Portfolio1branchCode" + j,
                          mapVariable,
                          whereQuery,
                          whereValue,
                          isExistRecord,
                          isDelete,
                          fetchType,
                          counter));
              whereQuery = "";
            }
            if (!isExistRecord
                && !isDelete
                && true
                && model.getPortfolio().getEconomicType() != null) {

              andCondition = " AND ";
              if (isOpenParentheses) {
                andCondition = " ";
              }
              query += " ";
              if (isDelete && fetchType == FetchType.EAGER) {
                whereQuery = " Portfolio1.economicType = ? ";
                counter++;
              } else {
                whereQuery = " Portfolio1.economicType = :Portfolio1economicType" + j + " ";
              }

              isOpenParentheses = false;
              whereValue = model.getPortfolio().getEconomicType();
              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "Portfolio1.economicType",
                          "Portfolio1economicType" + j,
                          mapVariable,
                          whereQuery,
                          whereValue,
                          isExistRecord,
                          isDelete,
                          fetchType,
                          counter));
              whereQuery = "";
            }
            if (!isExistRecord
                && !isDelete
                && true
                && model.getPortfolio().getIndustryType() != null) {

              andCondition = " AND ";
              if (isOpenParentheses) {
                andCondition = " ";
              }
              query += " ";
              if (isDelete && fetchType == FetchType.EAGER) {
                whereQuery = " Portfolio1.industryType = ? ";
                counter++;
              } else {
                whereQuery = " Portfolio1.industryType = :Portfolio1industryType" + j + " ";
              }

              isOpenParentheses = false;
              whereValue = model.getPortfolio().getIndustryType();
              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "Portfolio1.industryType",
                          "Portfolio1industryType" + j,
                          mapVariable,
                          whereQuery,
                          whereValue,
                          isExistRecord,
                          isDelete,
                          fetchType,
                          counter));
              whereQuery = "";
            }
            if (!isExistRecord
                && !isDelete
                && true
                && model.getPortfolio().getTechnologyDescription() != null) {

              andCondition = " AND ";
              if (isOpenParentheses) {
                andCondition = " ";
              }
              query += " ";
              if (isDelete && fetchType == FetchType.EAGER) {
                whereQuery = " Portfolio1.techDescription = ? ";
                counter++;
              } else {
                whereQuery =
                    " Portfolio1.technologyDescription = :Portfolio1technologyDescription"
                        + j
                        + " ";
              }

              isOpenParentheses = false;
              whereValue = model.getPortfolio().getTechnologyDescription();
              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "Portfolio1.technologyDescription",
                          "Portfolio1technologyDescription" + j,
                          mapVariable,
                          whereQuery,
                          whereValue,
                          isExistRecord,
                          isDelete,
                          fetchType,
                          counter));
              whereQuery = "";
            }
            if (!isExistRecord
                && !isDelete
                && true
                && model.getPortfolio().getStateType() != null) {

              andCondition = " AND ";
              if (isOpenParentheses) {
                andCondition = " ";
              }
              query += " ";
              if (isDelete && fetchType == FetchType.EAGER) {
                whereQuery = " Portfolio1.stateType = ? ";
                counter++;
              } else {
                whereQuery = " Portfolio1.stateType = :Portfolio1stateType" + j + " ";
              }

              isOpenParentheses = false;
              whereValue = model.getPortfolio().getStateType();
              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "Portfolio1.stateType",
                          "Portfolio1stateType" + j,
                          mapVariable,
                          whereQuery,
                          whereValue,
                          isExistRecord,
                          isDelete,
                          fetchType,
                          counter));
              whereQuery = "";
            }
            if (!isExistRecord && !isDelete && true && model.getPortfolio().getLifeType() != null) {

              andCondition = " AND ";
              if (isOpenParentheses) {
                andCondition = " ";
              }
              query += " ";
              if (isDelete && fetchType == FetchType.EAGER) {
                whereQuery = " Portfolio1.lifeType = ? ";
                counter++;
              } else {
                whereQuery = " Portfolio1.lifeType = :Portfolio1lifeType" + j + " ";
              }

              isOpenParentheses = false;
              whereValue = model.getPortfolio().getLifeType();
              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "Portfolio1.lifeType",
                          "Portfolio1lifeType" + j,
                          mapVariable,
                          whereQuery,
                          whereValue,
                          isExistRecord,
                          isDelete,
                          fetchType,
                          counter));
              whereQuery = "";
            }
            if (!isExistRecord
                && !isDelete
                && true
                && model.getPortfolio().getStartDate() != null) {

              andCondition = " AND ";
              if (isOpenParentheses) {
                andCondition = " ";
              }
              query += " ";
              if (isDelete && fetchType == FetchType.EAGER) {
                whereQuery = " Portfolio1.startDate = ? ";
                counter++;
              } else {
                whereQuery = " Portfolio1.startDate = :Portfolio1startDate" + j + " ";
              }

              isOpenParentheses = false;
              whereValue = model.getPortfolio().getStartDate();
              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "Portfolio1.startDate",
                          "Portfolio1startDate" + j,
                          mapVariable,
                          whereQuery,
                          whereValue,
                          isExistRecord,
                          isDelete,
                          fetchType,
                          counter));
              whereQuery = "";
            }
            if (!isExistRecord && !isDelete && true && model.getPortfolio().getEndDate() != null) {

              andCondition = " AND ";
              if (isOpenParentheses) {
                andCondition = " ";
              }
              query += " ";
              if (isDelete && fetchType == FetchType.EAGER) {
                whereQuery = " Portfolio1.endDate = ? ";
                counter++;
              } else {
                whereQuery = " Portfolio1.endDate = :Portfolio1endDate" + j + " ";
              }

              isOpenParentheses = false;
              whereValue = model.getPortfolio().getEndDate();
              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "Portfolio1.endDate",
                          "Portfolio1endDate" + j,
                          mapVariable,
                          whereQuery,
                          whereValue,
                          isExistRecord,
                          isDelete,
                          fetchType,
                          counter));
              whereQuery = "";
            }
            if (!isExistRecord
                && !isDelete
                && true
                && model.getPortfolio().getRequestDate() != null) {

              andCondition = " AND ";
              if (isOpenParentheses) {
                andCondition = " ";
              }
              query += " ";
              if (isDelete && fetchType == FetchType.EAGER) {
                whereQuery = " Portfolio1.requestDate = ? ";
                counter++;
              } else {
                whereQuery = " Portfolio1.requestDate = :Portfolio1requestDate" + j + " ";
              }

              isOpenParentheses = false;
              whereValue = model.getPortfolio().getRequestDate();
              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "Portfolio1.requestDate",
                          "Portfolio1requestDate" + j,
                          mapVariable,
                          whereQuery,
                          whereValue,
                          isExistRecord,
                          isDelete,
                          fetchType,
                          counter));
              whereQuery = "";
            }
            if (!isExistRecord
                && !isDelete
                && true
                && model.getPortfolio().getCapitalAMT() != null) {

              andCondition = " AND ";
              if (isOpenParentheses) {
                andCondition = " ";
              }
              query += " ";
              if (isDelete && fetchType == FetchType.EAGER) {
                whereQuery = " Portfolio1.capitalAMT = ? ";
                counter++;
              } else {
                whereQuery = " Portfolio1.capitalAMT = :Portfolio1capitalAMT" + j + " ";
              }

              isOpenParentheses = false;
              whereValue = model.getPortfolio().getCapitalAMT();
              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "Portfolio1.capitalAMT",
                          "Portfolio1capitalAMT" + j,
                          mapVariable,
                          whereQuery,
                          whereValue,
                          isExistRecord,
                          isDelete,
                          fetchType,
                          counter));
              whereQuery = "";
            }
            if (!isExistRecord
                && !isDelete
                && true
                && model.getPortfolio().getInvestableAMT() != null) {

              andCondition = " AND ";
              if (isOpenParentheses) {
                andCondition = " ";
              }
              query += " ";
              if (isDelete && fetchType == FetchType.EAGER) {
                whereQuery = " Portfolio1.investableAMT = ? ";
                counter++;
              } else {
                whereQuery = " Portfolio1.investableAMT = :Portfolio1investableAMT" + j + " ";
              }

              isOpenParentheses = false;
              whereValue = model.getPortfolio().getInvestableAMT();
              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "Portfolio1.investableAMT",
                          "Portfolio1investableAMT" + j,
                          mapVariable,
                          whereQuery,
                          whereValue,
                          isExistRecord,
                          isDelete,
                          fetchType,
                          counter));
              whereQuery = "";
            }
            if (!isExistRecord
                && !isDelete
                && true
                && model.getPortfolio().getAssessdAMT() != null) {

              andCondition = " AND ";
              if (isOpenParentheses) {
                andCondition = " ";
              }
              query += " ";
              if (isDelete && fetchType == FetchType.EAGER) {
                whereQuery = " Portfolio1.assessdAMT = ? ";
                counter++;
              } else {
                whereQuery = " Portfolio1.assessdAMT = :Portfolio1assessdAMT" + j + " ";
              }

              isOpenParentheses = false;
              whereValue = model.getPortfolio().getAssessdAMT();
              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "Portfolio1.assessdAMT",
                          "Portfolio1assessdAMT" + j,
                          mapVariable,
                          whereQuery,
                          whereValue,
                          isExistRecord,
                          isDelete,
                          fetchType,
                          counter));
              whereQuery = "";
            }
            if (!isExistRecord
                && !isDelete
                && true
                && model.getPortfolio().getBankAPPRInvestAMT() != null) {

              andCondition = " AND ";
              if (isOpenParentheses) {
                andCondition = " ";
              }
              query += " ";
              if (isDelete && fetchType == FetchType.EAGER) {
                whereQuery = " Portfolio1.bankAPPRInvestAMT = ? ";
                counter++;
              } else {
                whereQuery =
                    " Portfolio1.bankAPPRInvestAMT = :Portfolio1bankAPPRInvestAMT" + j + " ";
              }

              isOpenParentheses = false;
              whereValue = model.getPortfolio().getBankAPPRInvestAMT();
              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "Portfolio1.bankAPPRInvestAMT",
                          "Portfolio1bankAPPRInvestAMT" + j,
                          mapVariable,
                          whereQuery,
                          whereValue,
                          isExistRecord,
                          isDelete,
                          fetchType,
                          counter));
              whereQuery = "";
            }
            if (!isExistRecord && !isDelete && true && model.getPortfolio().getReagent() != null) {

              andCondition = " AND ";
              if (isOpenParentheses) {
                andCondition = " ";
              }
              query += " ";
              if (isDelete && fetchType == FetchType.EAGER) {
                whereQuery = " Portfolio1.reagent = ? ";
                counter++;
              } else {
                whereQuery = " Portfolio1.reagent = :Portfolio1reagent" + j + " ";
              }

              isOpenParentheses = false;
              whereValue = model.getPortfolio().getReagent();
              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "Portfolio1.reagent",
                          "Portfolio1reagent" + j,
                          mapVariable,
                          whereQuery,
                          whereValue,
                          isExistRecord,
                          isDelete,
                          fetchType,
                          counter));
              whereQuery = "";
            }
            if (!isExistRecord
                && !isDelete
                && true
                && model.getPortfolio().getBusinessId() != null) {

              andCondition = " AND ";
              if (isOpenParentheses) {
                andCondition = " ";
              }
              query += " ";
              if (isDelete && fetchType == FetchType.EAGER) {
                whereQuery = " Portfolio1.businessId = ? ";
                counter++;
              } else {
                whereQuery = " Portfolio1.businessId = :Portfolio1businessId" + j + " ";
              }

              isOpenParentheses = false;
              whereValue = model.getPortfolio().getBusinessId();
              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "Portfolio1.businessId",
                          "Portfolio1businessId" + j,
                          mapVariable,
                          whereQuery,
                          whereValue,
                          isExistRecord,
                          isDelete,
                          fetchType,
                          counter));
              whereQuery = "";
            }
            if (!isExistRecord && !isDelete && true && model.getPortfolio().getVersion() != 0) {

              andCondition = " AND ";
              if (isOpenParentheses) {
                andCondition = " ";
              }
              query += " ";
              if (isDelete && fetchType == FetchType.EAGER) {
                whereQuery = " Portfolio1.VERSION = ? ";
                counter++;
              } else {
                whereQuery = " Portfolio1.version = :Portfolio1version" + j + " ";
              }

              isOpenParentheses = false;
              whereValue = new java.lang.Integer("" + model.getPortfolio().getVersion());
              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "Portfolio1.version",
                          "Portfolio1version" + j,
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
          if (!isExistRecord && !isDelete && model.getFile() != null) {

            if (model.getFile().getId() != null) {

              andCondition = " AND ";
              if (isOpenParentheses) {
                andCondition = " ";
              }
              query += " ";
              if (isDelete && fetchType == FetchType.EAGER) {
                whereQuery = " File19.l4610flmtdataid = ? ";
                counter++;
              } else {
                whereQuery = " File19.id = :File19id" + j + " ";
              }

              isOpenParentheses = false;
              whereValue = model.getFile().getId();
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
            if (!isExistRecord && !isDelete && true && model.getFile().getExtension() != null) {

              andCondition = " AND ";
              if (isOpenParentheses) {
                andCondition = " ";
              }
              query += " ";
              if (isDelete && fetchType == FetchType.EAGER) {
                whereQuery = " File19.l4610extension = ? ";
                counter++;
              } else {
                whereQuery = " File19.extension = :File19extension" + j + " ";
              }

              isOpenParentheses = false;
              whereValue = model.getFile().getExtension();
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
            if (!isExistRecord && !isDelete && true && model.getFile().getVersion() != 0) {

              andCondition = " AND ";
              if (isOpenParentheses) {
                andCondition = " ";
              }
              query += " ";
              if (isDelete && fetchType == FetchType.EAGER) {
                whereQuery = " File19.VERSION = ? ";
                counter++;
              } else {
                whereQuery = " File19.version = :File19version" + j + " ";
              }

              isOpenParentheses = false;
              whereValue = new java.lang.Integer("" + model.getFile().getVersion());
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
              && fetchType == FetchType.EAGER
              && model.getLocationDetails() != null) {
            int length20 = model.getLocationDetails().size();

            if (length20 > 0) {
              if (isOpenParentheses) {
                query += "  ( ";
              } else {
                query += " AND ( ";
              }
              isOpenParentheses = true;

              for (int i20 = 0; i20 < length20; i20++) {

                java.lang.String beforeCondition20 = "";
                java.lang.String prefix20 = " OR ";
                if (i20 == 0) {
                  prefix20 = "";
                }
                if (length20 > 1) {

                  query += (prefix20 + "(");
                  isOpenParentheses = true;

                } else {

                  query += (prefix20);
                  if (!prefix20.trim().equals("")) {
                    isOpenParentheses = false;
                  }
                }

                if (model.getLocationDetails().get(i20).getLocationDetailId() != null) {

                  andCondition = " AND ";
                  if (isOpenParentheses) {
                    andCondition = " ";
                  }
                  query += " ";
                  whereValue = model.getLocationDetails().get(i20).getLocationDetailId();
                  if (isDelete && fetchType == FetchType.EAGER) {
                    whereQuery = " LocationDetails20.id = ? ";
                    counter++;
                  } else {
                    whereQuery =
                        " LocationDetails20.locationDetailId = :LocationDetails20locationDetailId"
                            + j
                            + " ";
                  }

                  isOpenParentheses = false;
                  whereValue = model.getLocationDetails().get(i20).getLocationDetailId();
                  query +=
                      (andCondition
                          + Facade.getStatement(
                              statement,
                              "LocationDetails20.locationDetailId",
                              "LocationDetails20locationDetailId" + j,
                              mapVariable,
                              whereQuery,
                              whereValue,
                              isExistRecord,
                              isDelete,
                              fetchType,
                              counter));
                  whereQuery = "";
                }
                if (!isExistRecord
                    && !isDelete
                    && model.getLocationDetails().get(i20).getFile() != null) {

                  if (model.getLocationDetails().get(i20).getFile().getId() != null) {

                    andCondition = " AND ";
                    if (isOpenParentheses) {
                      andCondition = " ";
                    }
                    query += " ";
                    whereValue = model.getLocationDetails().get(i20).getFile().getId();
                    if (isDelete && fetchType == FetchType.EAGER) {
                      whereQuery = " File22.l4610flmtdataid = ? ";
                      counter++;
                    } else {
                      whereQuery = " File22.id = :File22id" + j + " ";
                    }

                    isOpenParentheses = false;
                    whereValue = model.getLocationDetails().get(i20).getFile().getId();
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
                      && model.getLocationDetails().get(i20).getFile().getExtension() != null) {

                    andCondition = " AND ";
                    if (isOpenParentheses) {
                      andCondition = " ";
                    }
                    query += " ";
                    whereValue = model.getLocationDetails().get(i20).getFile().getExtension();
                    if (isDelete && fetchType == FetchType.EAGER) {
                      whereQuery = " File22.l4610extension = ? ";
                      counter++;
                    } else {
                      whereQuery = " File22.extension = :File22extension" + j + " ";
                    }

                    isOpenParentheses = false;
                    whereValue = model.getLocationDetails().get(i20).getFile().getExtension();
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
                      && model.getLocationDetails().get(i20).getFile().getVersion() != 0) {

                    andCondition = " AND ";
                    if (isOpenParentheses) {
                      andCondition = " ";
                    }
                    query += " ";
                    whereValue =
                        new java.lang.Integer(
                            "" + model.getLocationDetails().get(i20).getFile().getVersion());
                    if (isDelete && fetchType == FetchType.EAGER) {
                      whereQuery = " File22.VERSION = ? ";
                      counter++;
                    } else {
                      whereQuery = " File22.version = :File22version" + j + " ";
                    }

                    isOpenParentheses = false;
                    whereValue =
                        new java.lang.Integer(
                            "" + model.getLocationDetails().get(i20).getFile().getVersion());
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
                if (!isDelete && model.getLocationDetails().get(i20).getAddress() != null) {
                  if (!isDelete
                      && true
                      && model.getLocationDetails().get(i20).getAddress().getAddressLine1()
                          != null) {

                    andCondition = " AND ";
                    if (isOpenParentheses) {
                      andCondition = " ";
                    }
                    query += " ";
                    whereValue = model.getLocationDetails().get(i20).getAddress().getAddressLine1();
                    if (isDelete && fetchType == FetchType.EAGER) {
                      whereQuery = " LocationDetails20.addressLine1 = ? ";
                      counter++;
                    } else {
                      whereQuery =
                          " LocationDetails20.address.addressLine1 = :LocationDetails20addressaddressLine1"
                              + j
                              + " ";
                    }

                    isOpenParentheses = false;
                    whereValue = model.getLocationDetails().get(i20).getAddress().getAddressLine1();
                    query +=
                        (andCondition
                            + Facade.getStatement(
                                statement,
                                "LocationDetails20.address.addressLine1",
                                "LocationDetails20addressaddressLine1" + j,
                                mapVariable,
                                whereQuery,
                                whereValue,
                                isExistRecord,
                                isDelete,
                                fetchType,
                                counter));
                    whereQuery = "";
                  }
                  if (!isExistRecord
                      && !isDelete
                      && true
                      && model.getLocationDetails().get(i20).getAddress().getAddressLine2()
                          != null) {

                    andCondition = " AND ";
                    if (isOpenParentheses) {
                      andCondition = " ";
                    }
                    query += " ";
                    whereValue = model.getLocationDetails().get(i20).getAddress().getAddressLine2();
                    if (isDelete && fetchType == FetchType.EAGER) {
                      whereQuery = " LocationDetails20.addressLine2 = ? ";
                      counter++;
                    } else {
                      whereQuery =
                          " LocationDetails20.address.addressLine2 = :LocationDetails20addressaddressLine2"
                              + j
                              + " ";
                    }

                    isOpenParentheses = false;
                    whereValue = model.getLocationDetails().get(i20).getAddress().getAddressLine2();
                    query +=
                        (andCondition
                            + Facade.getStatement(
                                statement,
                                "LocationDetails20.address.addressLine2",
                                "LocationDetails20addressaddressLine2" + j,
                                mapVariable,
                                whereQuery,
                                whereValue,
                                isExistRecord,
                                isDelete,
                                fetchType,
                                counter));
                    whereQuery = "";
                  }
                  if (!isExistRecord
                      && !isDelete
                      && true
                      && model.getLocationDetails().get(i20).getAddress().getCity() != null) {

                    andCondition = " AND ";
                    if (isOpenParentheses) {
                      andCondition = " ";
                    }
                    query += " ";
                    whereValue = model.getLocationDetails().get(i20).getAddress().getCity();
                    if (isDelete && fetchType == FetchType.EAGER) {
                      whereQuery = " LocationDetails20.city = ? ";
                      counter++;
                    } else {
                      whereQuery =
                          " LocationDetails20.address.city = :LocationDetails20addresscity"
                              + j
                              + " ";
                    }

                    isOpenParentheses = false;
                    whereValue = model.getLocationDetails().get(i20).getAddress().getCity();
                    query +=
                        (andCondition
                            + Facade.getStatement(
                                statement,
                                "LocationDetails20.address.city",
                                "LocationDetails20addresscity" + j,
                                mapVariable,
                                whereQuery,
                                whereValue,
                                isExistRecord,
                                isDelete,
                                fetchType,
                                counter));
                    whereQuery = "";
                  }
                  if (!isExistRecord
                      && !isDelete
                      && true
                      && model.getLocationDetails().get(i20).getAddress().getState() != null) {

                    andCondition = " AND ";
                    if (isOpenParentheses) {
                      andCondition = " ";
                    }
                    query += " ";
                    whereValue = model.getLocationDetails().get(i20).getAddress().getState();
                    if (isDelete && fetchType == FetchType.EAGER) {
                      whereQuery = " LocationDetails20.state = ? ";
                      counter++;
                    } else {
                      whereQuery =
                          " LocationDetails20.address.state = :LocationDetails20addressstate"
                              + j
                              + " ";
                    }

                    isOpenParentheses = false;
                    whereValue = model.getLocationDetails().get(i20).getAddress().getState();
                    query +=
                        (andCondition
                            + Facade.getStatement(
                                statement,
                                "LocationDetails20.address.state",
                                "LocationDetails20addressstate" + j,
                                mapVariable,
                                whereQuery,
                                whereValue,
                                isExistRecord,
                                isDelete,
                                fetchType,
                                counter));
                    whereQuery = "";
                  }
                  if (!isExistRecord
                      && !isDelete
                      && true
                      && model.getLocationDetails().get(i20).getAddress().getCountry() != null) {

                    andCondition = " AND ";
                    if (isOpenParentheses) {
                      andCondition = " ";
                    }
                    query += " ";
                    whereValue = model.getLocationDetails().get(i20).getAddress().getCountry();
                    if (isDelete && fetchType == FetchType.EAGER) {
                      whereQuery = " LocationDetails20.country = ? ";
                      counter++;
                    } else {
                      whereQuery =
                          " LocationDetails20.address.country = :LocationDetails20addresscountry"
                              + j
                              + " ";
                    }

                    isOpenParentheses = false;
                    whereValue = model.getLocationDetails().get(i20).getAddress().getCountry();
                    query +=
                        (andCondition
                            + Facade.getStatement(
                                statement,
                                "LocationDetails20.address.country",
                                "LocationDetails20addresscountry" + j,
                                mapVariable,
                                whereQuery,
                                whereValue,
                                isExistRecord,
                                isDelete,
                                fetchType,
                                counter));
                    whereQuery = "";
                  }
                  if (!isExistRecord
                      && !isDelete
                      && true
                      && model.getLocationDetails().get(i20).getAddress().getZipCode() != null) {

                    andCondition = " AND ";
                    if (isOpenParentheses) {
                      andCondition = " ";
                    }
                    query += " ";
                    whereValue = model.getLocationDetails().get(i20).getAddress().getZipCode();
                    if (isDelete && fetchType == FetchType.EAGER) {
                      whereQuery = " LocationDetails20.zipCode = ? ";
                      counter++;
                    } else {
                      whereQuery =
                          " LocationDetails20.address.zipCode = :LocationDetails20addresszipCode"
                              + j
                              + " ";
                    }

                    isOpenParentheses = false;
                    whereValue = model.getLocationDetails().get(i20).getAddress().getZipCode();
                    query +=
                        (andCondition
                            + Facade.getStatement(
                                statement,
                                "LocationDetails20.address.zipCode",
                                "LocationDetails20addresszipCode" + j,
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
                if (length20 > 1) {
                  query += " ) ";
                }
              }

              query += ")";
              isOpenParentheses = false;
            }
          }

          if (!isExistRecord && true && model.getProvince() != null) {

            andCondition = " AND ";
            if (isOpenParentheses) {
              andCondition = " ";
            }
            query += " ";
            if (isDelete && fetchType == FetchType.EAGER) {
              whereQuery = " PortfolioLocation0.province = ? ";
              counter++;
            } else {
              whereQuery = " PortfolioLocation0.province = :PortfolioLocation0province" + j + " ";
            }

            isOpenParentheses = false;
            whereValue = model.getProvince();
            query +=
                (andCondition
                    + Facade.getStatement(
                        statement,
                        "PortfolioLocation0.province",
                        "PortfolioLocation0province" + j,
                        mapVariable,
                        whereQuery,
                        whereValue,
                        isExistRecord,
                        isDelete,
                        fetchType,
                        counter));
            whereQuery = "";
          }
          if (!isExistRecord && true && model.getCity() != null) {

            andCondition = " AND ";
            if (isOpenParentheses) {
              andCondition = " ";
            }
            query += " ";
            if (isDelete && fetchType == FetchType.EAGER) {
              whereQuery = " PortfolioLocation0.city = ? ";
              counter++;
            } else {
              whereQuery = " PortfolioLocation0.city = :PortfolioLocation0city" + j + " ";
            }

            isOpenParentheses = false;
            whereValue = model.getCity();
            query +=
                (andCondition
                    + Facade.getStatement(
                        statement,
                        "PortfolioLocation0.city",
                        "PortfolioLocation0city" + j,
                        mapVariable,
                        whereQuery,
                        whereValue,
                        isExistRecord,
                        isDelete,
                        fetchType,
                        counter));
            whereQuery = "";
          }
          if (!isExistRecord && true && model.getIndstrltwn() != null) {

            andCondition = " AND ";
            if (isOpenParentheses) {
              andCondition = " ";
            }
            query += " ";
            if (isDelete && fetchType == FetchType.EAGER) {
              whereQuery = " PortfolioLocation0.indstrltwn = ? ";
              counter++;
            } else {
              whereQuery =
                  " PortfolioLocation0.indstrltwn = :PortfolioLocation0indstrltwn" + j + " ";
            }

            isOpenParentheses = false;
            whereValue = model.getIndstrltwn();
            query +=
                (andCondition
                    + Facade.getStatement(
                        statement,
                        "PortfolioLocation0.indstrltwn",
                        "PortfolioLocation0indstrltwn" + j,
                        mapVariable,
                        whereQuery,
                        whereValue,
                        isExistRecord,
                        isDelete,
                        fetchType,
                        counter));
            whereQuery = "";
          }
          if (!isExistRecord && true && model.getAddress() != null) {

            andCondition = " AND ";
            if (isOpenParentheses) {
              andCondition = " ";
            }
            query += " ";
            if (isDelete && fetchType == FetchType.EAGER) {
              whereQuery = " PortfolioLocation0.address = ? ";
              counter++;
            } else {
              whereQuery = " PortfolioLocation0.address = :PortfolioLocation0address" + j + " ";
            }

            isOpenParentheses = false;
            whereValue = model.getAddress();
            query +=
                (andCondition
                    + Facade.getStatement(
                        statement,
                        "PortfolioLocation0.address",
                        "PortfolioLocation0address" + j,
                        mapVariable,
                        whereQuery,
                        whereValue,
                        isExistRecord,
                        isDelete,
                        fetchType,
                        counter));
            whereQuery = "";
          }
          if (!isExistRecord && true && model.getStableStatusType() != null) {

            andCondition = " AND ";
            if (isOpenParentheses) {
              andCondition = " ";
            }
            query += " ";
            if (isDelete && fetchType == FetchType.EAGER) {
              whereQuery = " PortfolioLocation0.stableStatusType = ? ";
              counter++;
            } else {
              whereQuery =
                  " PortfolioLocation0.stableStatusType = :PortfolioLocation0stableStatusType"
                      + j
                      + " ";
            }

            isOpenParentheses = false;
            whereValue = model.getStableStatusType();
            query +=
                (andCondition
                    + Facade.getStatement(
                        statement,
                        "PortfolioLocation0.stableStatusType",
                        "PortfolioLocation0stableStatusType" + j,
                        mapVariable,
                        whereQuery,
                        whereValue,
                        isExistRecord,
                        isDelete,
                        fetchType,
                        counter));
            whereQuery = "";
          }
          if (!isExistRecord && true && model.getOwnerStatusType() != null) {

            andCondition = " AND ";
            if (isOpenParentheses) {
              andCondition = " ";
            }
            query += " ";
            if (isDelete && fetchType == FetchType.EAGER) {
              whereQuery = " PortfolioLocation0.ownerStatusType = ? ";
              counter++;
            } else {
              whereQuery =
                  " PortfolioLocation0.ownerStatusType = :PortfolioLocation0ownerStatusType"
                      + j
                      + " ";
            }

            isOpenParentheses = false;
            whereValue = model.getOwnerStatusType();
            query +=
                (andCondition
                    + Facade.getStatement(
                        statement,
                        "PortfolioLocation0.ownerStatusType",
                        "PortfolioLocation0ownerStatusType" + j,
                        mapVariable,
                        whereQuery,
                        whereValue,
                        isExistRecord,
                        isDelete,
                        fetchType,
                        counter));
            whereQuery = "";
          }
          if (!isExistRecord && true && model.getRegionalStatusType() != null) {

            andCondition = " AND ";
            if (isOpenParentheses) {
              andCondition = " ";
            }
            query += " ";
            if (isDelete && fetchType == FetchType.EAGER) {
              whereQuery = " PortfolioLocation0.regionalStatusType = ? ";
              counter++;
            } else {
              whereQuery =
                  " PortfolioLocation0.regionalStatusType = :PortfolioLocation0regionalStatusType"
                      + j
                      + " ";
            }

            isOpenParentheses = false;
            whereValue = model.getRegionalStatusType();
            query +=
                (andCondition
                    + Facade.getStatement(
                        statement,
                        "PortfolioLocation0.regionalStatusType",
                        "PortfolioLocation0regionalStatusType" + j,
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
              whereQuery = " PortfolioLocation0.VERSION = ? ";
              counter++;
            } else {
              whereQuery = " PortfolioLocation0.version = :PortfolioLocation0version" + j + " ";
            }

            isOpenParentheses = false;
            whereValue = new java.lang.Integer("" + model.getVersion());
            query +=
                (andCondition
                    + Facade.getStatement(
                        statement,
                        "PortfolioLocation0.version",
                        "PortfolioLocation0version" + j,
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
          && (!select.trim().equals("PortfolioLocation0") && select.indexOf("new") == -1)) {
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
