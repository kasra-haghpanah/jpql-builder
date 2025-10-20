package com.tosan.bpms.framework.orm.repository.jpql;

import com.jpql.api.interfaces.*;
import com.jpql.api.interfaces.Parameter;
import javax.persistence.FetchType;
import javax.persistence.EntityManager;
/**
 * Created by kasra.haghpanah on 1/05/2019.
 */
public class LocationDetailscomtosanbpmsmodelsqlDAO {

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

        com.tosan.bpms.model.sql.LocationDetails model =
            (com.tosan.bpms.model.sql.LocationDetails) fetchIterator.next();

        if (model != null) {

          String key = model.getLocationDetailId() + "";
          existMap.put(key, model);
        }
      }
    }

    java.util.Iterator iterator = models.iterator();

    while (iterator.hasNext()) {
      com.tosan.bpms.model.sql.LocationDetails model =
          (com.tosan.bpms.model.sql.LocationDetails) iterator.next();

      if (model != null) {
        String key = model.getLocationDetailId() + "";
        com.tosan.bpms.model.sql.LocationDetails fetchModel =
            (com.tosan.bpms.model.sql.LocationDetails) existMap.get(key);

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
    com.tosan.bpms.model.sql.LocationDetails model = null;
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
    java.lang.String select = "SELECT LocationDetails0";
    if (selectParam != null && !selectParam.equals("")) {
      select = selectParam;
    }
    if (!isExistRecord && !isDelete) {
      if (fetchType == FetchType.LAZY) {
        basicQuery =
            select
                + " FROM com.tosan.bpms.model.sql.LocationDetails LocationDetails0  JOIN "
                + fetch
                + " LocationDetails0.portfolioLocation PortfolioLocation1 JOIN "
                + fetch
                + " PortfolioLocation1.portfolio Portfolio2 LEFT JOIN "
                + fetch
                + " PortfolioLocation1.file File20 LEFT JOIN "
                + fetch
                + " LocationDetails0.file File24 WHERE 1=1 ";
      } else if (fetchType == FetchType.EAGER) {
        basicQuery =
            select
                + " FROM com.tosan.bpms.model.sql.LocationDetails LocationDetails0  JOIN "
                + fetch
                + " LocationDetails0.portfolioLocation PortfolioLocation1 JOIN "
                + fetch
                + " PortfolioLocation1.portfolio Portfolio2 JOIN "
                + fetch
                + " Portfolio2.portfolioLicenses PortfolioLicense3 LEFT JOIN "
                + fetch
                + " PortfolioLicense3.file File5 JOIN "
                + fetch
                + " PortfolioLicense3.licenseDetails LicenseDetails6 JOIN "
                + fetch
                + " LicenseDetails6.portfolioLicense PortfolioLicense7 LEFT JOIN "
                + fetch
                + " PortfolioLicense7.file File9 LEFT JOIN "
                + fetch
                + " LicenseDetails6.file File11 JOIN "
                + fetch
                + " Portfolio2.portfolioLocations PortfolioLocation12 LEFT JOIN "
                + fetch
                + " PortfolioLocation12.file File14 JOIN "
                + fetch
                + " PortfolioLocation12.locationDetails LocationDetails15 LEFT JOIN "
                + fetch
                + " LocationDetails15.file File17 JOIN "
                + fetch
                + " Portfolio2.receptions Reception18 LEFT JOIN "
                + fetch
                + " PortfolioLocation1.file File20 JOIN "
                + fetch
                + " PortfolioLocation1.locationDetails LocationDetails21 LEFT JOIN "
                + fetch
                + " LocationDetails21.file File23 LEFT JOIN "
                + fetch
                + " LocationDetails0.file File24 WHERE 1=1 ";
      }
    } else if (isExistRecord) {
      basicQuery =
          "SELECT LocationDetails0 FROM com.tosan.bpms.model.sql.LocationDetails LocationDetails0 WHERE 1=1 ";
    } else if (isDelete) {
      if (fetchType == FetchType.LAZY) {
        basicQuery =
            "DELETE FROM com.tosan.bpms.model.sql.LocationDetails LocationDetails0 WHERE 1=1 ";
      } else if (fetchType == FetchType.EAGER) {
        basicQuery = "DELETE LocationDetails0 FROM LocationDetail LocationDetails0 WHERE 1=1 ";
      }
    }
    int modelsLength = models.size();
    java.util.Iterator modelsIterator = models.iterator();
    int j = 0;
    if (modelsLength > 0) {
      while (modelsIterator.hasNext()) {
        model = (com.tosan.bpms.model.sql.LocationDetails) modelsIterator.next();
        query = "";
        isOpenParentheses = true;

        if (model != null) {

          if (isExistRecord == true && model.getLocationDetailId() == null) {
            model.setLocationDetailId(new java.lang.Integer("-1"));
            isChangeKey = true;
          }

          if (model.getLocationDetailId() != null) {

            andCondition = " AND ";
            if (isOpenParentheses) {
              andCondition = " ";
            }
            query += " ";
            if (isDelete && fetchType == FetchType.EAGER) {
              whereQuery = " LocationDetails0.id = ? ";
              counter++;
            } else {
              whereQuery =
                  " LocationDetails0.locationDetailId = :LocationDetails0locationDetailId"
                      + j
                      + " ";
            }

            isOpenParentheses = false;
            whereValue = model.getLocationDetailId();
            if (isExistRecord && isChangeKey) {
              model.setLocationDetailId(null);
              isChangeKey = false;
            }

            query +=
                (andCondition
                    + Facade.getStatement(
                        statement,
                        "LocationDetails0.locationDetailId",
                        "LocationDetails0locationDetailId" + j,
                        mapVariable,
                        whereQuery,
                        whereValue,
                        isExistRecord,
                        isDelete,
                        fetchType,
                        counter));
            whereQuery = "";
          }
          if (!isExistRecord && !isDelete && model.getPortfolioLocation() != null) {

            if (model.getPortfolioLocation().getPortfolioLocationId() != null) {

              andCondition = " AND ";
              if (isOpenParentheses) {
                andCondition = " ";
              }
              query += " ";
              if (isDelete && fetchType == FetchType.EAGER) {
                whereQuery = " PortfolioLocation1.id = ? ";
                counter++;
              } else {
                whereQuery =
                    " PortfolioLocation1.portfolioLocationId = :PortfolioLocation1portfolioLocationId"
                        + j
                        + " ";
              }

              isOpenParentheses = false;
              whereValue = model.getPortfolioLocation().getPortfolioLocationId();
              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "PortfolioLocation1.portfolioLocationId",
                          "PortfolioLocation1portfolioLocationId" + j,
                          mapVariable,
                          whereQuery,
                          whereValue,
                          isExistRecord,
                          isDelete,
                          fetchType,
                          counter));
              whereQuery = "";
            }
            if (!isExistRecord
                && !isDelete
                && model.getPortfolioLocation().getPortfolio() != null) {

              if (model.getPortfolioLocation().getPortfolio().getPortfolioId() != null) {

                andCondition = " AND ";
                if (isOpenParentheses) {
                  andCondition = " ";
                }
                query += " ";
                if (isDelete && fetchType == FetchType.EAGER) {
                  whereQuery = " Portfolio2.portfolioId = ? ";
                  counter++;
                } else {
                  whereQuery = " Portfolio2.portfolioId = :Portfolio2portfolioId" + j + " ";
                }

                isOpenParentheses = false;
                whereValue = model.getPortfolioLocation().getPortfolio().getPortfolioId();
                query +=
                    (andCondition
                        + Facade.getStatement(
                            statement,
                            "Portfolio2.portfolioId",
                            "Portfolio2portfolioId" + j,
                            mapVariable,
                            whereQuery,
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
                  && model.getPortfolioLocation().getPortfolio().getPortfolioLicenses() != null) {
                int length3 =
                    model.getPortfolioLocation().getPortfolio().getPortfolioLicenses().size();

                if (length3 > 0) {
                  if (isOpenParentheses) {
                    query += "  ( ";
                  } else {
                    query += " AND ( ";
                  }
                  isOpenParentheses = true;

                  for (int i3 = 0; i3 < length3; i3++) {

                    java.lang.String beforeCondition3 = "";
                    java.lang.String prefix3 = " OR ";
                    if (i3 == 0) {
                      prefix3 = "";
                    }
                    if (length3 > 1) {

                      query += (prefix3 + "(");
                      isOpenParentheses = true;

                    } else {

                      query += (prefix3);
                      if (!prefix3.trim().equals("")) {
                        isOpenParentheses = false;
                      }
                    }

                    if (model
                            .getPortfolioLocation()
                            .getPortfolio()
                            .getPortfolioLicenses()
                            .get(i3)
                            .getPortfolioLicenseId()
                        != null) {

                      andCondition = " AND ";
                      if (isOpenParentheses) {
                        andCondition = " ";
                      }
                      query += " ";
                      whereValue =
                          model
                              .getPortfolioLocation()
                              .getPortfolio()
                              .getPortfolioLicenses()
                              .get(i3)
                              .getPortfolioLicenseId();
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " PortfolioLicense3.id = ? ";
                        counter++;
                      } else {
                        whereQuery =
                            " PortfolioLicense3.portfolioLicenseId = :PortfolioLicense3portfolioLicenseId"
                                + j
                                + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          model
                              .getPortfolioLocation()
                              .getPortfolio()
                              .getPortfolioLicenses()
                              .get(i3)
                              .getPortfolioLicenseId();
                      query +=
                          (andCondition
                              + Facade.getStatement(
                                  statement,
                                  "PortfolioLicense3.portfolioLicenseId",
                                  "PortfolioLicense3portfolioLicenseId" + j,
                                  mapVariable,
                                  whereQuery,
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
                                .getPortfolioLocation()
                                .getPortfolio()
                                .getPortfolioLicenses()
                                .get(i3)
                                .getFile()
                            != null) {

                      if (model
                              .getPortfolioLocation()
                              .getPortfolio()
                              .getPortfolioLicenses()
                              .get(i3)
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
                                .getPortfolioLocation()
                                .getPortfolio()
                                .getPortfolioLicenses()
                                .get(i3)
                                .getFile()
                                .getId();
                        if (isDelete && fetchType == FetchType.EAGER) {
                          whereQuery = " File5.l4610flmtdataid = ? ";
                          counter++;
                        } else {
                          whereQuery = " File5.id = :File5id" + j + " ";
                        }

                        isOpenParentheses = false;
                        whereValue =
                            model
                                .getPortfolioLocation()
                                .getPortfolio()
                                .getPortfolioLicenses()
                                .get(i3)
                                .getFile()
                                .getId();
                        query +=
                            (andCondition
                                + Facade.getStatement(
                                    statement,
                                    "File5.id",
                                    "File5id" + j,
                                    mapVariable,
                                    whereQuery,
                                    whereValue,
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
                                  .getPortfolioLocation()
                                  .getPortfolio()
                                  .getPortfolioLicenses()
                                  .get(i3)
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
                                .getPortfolioLocation()
                                .getPortfolio()
                                .getPortfolioLicenses()
                                .get(i3)
                                .getFile()
                                .getExtension();
                        if (isDelete && fetchType == FetchType.EAGER) {
                          whereQuery = " File5.l4610extension = ? ";
                          counter++;
                        } else {
                          whereQuery = " File5.extension = :File5extension" + j + " ";
                        }

                        isOpenParentheses = false;
                        whereValue =
                            model
                                .getPortfolioLocation()
                                .getPortfolio()
                                .getPortfolioLicenses()
                                .get(i3)
                                .getFile()
                                .getExtension();
                        query +=
                            (andCondition
                                + Facade.getStatement(
                                    statement,
                                    "File5.extension",
                                    "File5extension" + j,
                                    mapVariable,
                                    whereQuery,
                                    whereValue,
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
                                  .getPortfolioLocation()
                                  .getPortfolio()
                                  .getPortfolioLicenses()
                                  .get(i3)
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
                                        .getPortfolioLocation()
                                        .getPortfolio()
                                        .getPortfolioLicenses()
                                        .get(i3)
                                        .getFile()
                                        .getVersion());
                        if (isDelete && fetchType == FetchType.EAGER) {
                          whereQuery = " File5.VERSION = ? ";
                          counter++;
                        } else {
                          whereQuery = " File5.version = :File5version" + j + " ";
                        }

                        isOpenParentheses = false;
                        whereValue =
                            new java.lang.Integer(
                                ""
                                    + model
                                        .getPortfolioLocation()
                                        .getPortfolio()
                                        .getPortfolioLicenses()
                                        .get(i3)
                                        .getFile()
                                        .getVersion());
                        query +=
                            (andCondition
                                + Facade.getStatement(
                                    statement,
                                    "File5.version",
                                    "File5version" + j,
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
                                .getPortfolioLocation()
                                .getPortfolio()
                                .getPortfolioLicenses()
                                .get(i3)
                                .getLicenseDetails()
                            != null) {
                      int length6 =
                          model
                              .getPortfolioLocation()
                              .getPortfolio()
                              .getPortfolioLicenses()
                              .get(i3)
                              .getLicenseDetails()
                              .size();

                      if (length6 > 0) {
                        if (isOpenParentheses) {
                          query += "  ( ";
                        } else {
                          query += " AND ( ";
                        }
                        isOpenParentheses = true;

                        for (int i6 = 0; i6 < length6; i6++) {

                          java.lang.String beforeCondition6 = "";
                          java.lang.String prefix6 = " OR ";
                          if (i6 == 0) {
                            prefix6 = "";
                          }
                          if (length6 > 1) {

                            query += (prefix6 + "(");
                            isOpenParentheses = true;

                          } else {

                            query += (prefix6);
                            if (!prefix6.trim().equals("")) {
                              isOpenParentheses = false;
                            }
                          }

                          if (model
                                  .getPortfolioLocation()
                                  .getPortfolio()
                                  .getPortfolioLicenses()
                                  .get(i3)
                                  .getLicenseDetails()
                                  .get(i6)
                                  .getLicenseDetailId()
                              != null) {

                            andCondition = " AND ";
                            if (isOpenParentheses) {
                              andCondition = " ";
                            }
                            query += " ";
                            whereValue =
                                model
                                    .getPortfolioLocation()
                                    .getPortfolio()
                                    .getPortfolioLicenses()
                                    .get(i3)
                                    .getLicenseDetails()
                                    .get(i6)
                                    .getLicenseDetailId();
                            if (isDelete && fetchType == FetchType.EAGER) {
                              whereQuery = " LicenseDetails6.id = ? ";
                              counter++;
                            } else {
                              whereQuery =
                                  " LicenseDetails6.licenseDetailId = :LicenseDetails6licenseDetailId"
                                      + j
                                      + " ";
                            }

                            isOpenParentheses = false;
                            whereValue =
                                model
                                    .getPortfolioLocation()
                                    .getPortfolio()
                                    .getPortfolioLicenses()
                                    .get(i3)
                                    .getLicenseDetails()
                                    .get(i6)
                                    .getLicenseDetailId();
                            query +=
                                (andCondition
                                    + Facade.getStatement(
                                        statement,
                                        "LicenseDetails6.licenseDetailId",
                                        "LicenseDetails6licenseDetailId" + j,
                                        mapVariable,
                                        whereQuery,
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
                                      .getPortfolioLocation()
                                      .getPortfolio()
                                      .getPortfolioLicenses()
                                      .get(i3)
                                      .getLicenseDetails()
                                      .get(i6)
                                      .getPortfolioLicense()
                                  != null) {

                            if (model
                                    .getPortfolioLocation()
                                    .getPortfolio()
                                    .getPortfolioLicenses()
                                    .get(i3)
                                    .getLicenseDetails()
                                    .get(i6)
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
                                      .getPortfolioLocation()
                                      .getPortfolio()
                                      .getPortfolioLicenses()
                                      .get(i3)
                                      .getLicenseDetails()
                                      .get(i6)
                                      .getPortfolioLicense()
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
                                      .getPortfolioLocation()
                                      .getPortfolio()
                                      .getPortfolioLicenses()
                                      .get(i3)
                                      .getLicenseDetails()
                                      .get(i6)
                                      .getPortfolioLicense()
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
                                        .getPortfolioLocation()
                                        .getPortfolio()
                                        .getPortfolioLicenses()
                                        .get(i3)
                                        .getLicenseDetails()
                                        .get(i6)
                                        .getPortfolioLicense()
                                        .getFile()
                                    != null) {

                              if (model
                                      .getPortfolioLocation()
                                      .getPortfolio()
                                      .getPortfolioLicenses()
                                      .get(i3)
                                      .getLicenseDetails()
                                      .get(i6)
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
                                        .getPortfolioLocation()
                                        .getPortfolio()
                                        .getPortfolioLicenses()
                                        .get(i3)
                                        .getLicenseDetails()
                                        .get(i6)
                                        .getPortfolioLicense()
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
                                        .getPortfolioLocation()
                                        .getPortfolio()
                                        .getPortfolioLicenses()
                                        .get(i3)
                                        .getLicenseDetails()
                                        .get(i6)
                                        .getPortfolioLicense()
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
                                          .getPortfolioLocation()
                                          .getPortfolio()
                                          .getPortfolioLicenses()
                                          .get(i3)
                                          .getLicenseDetails()
                                          .get(i6)
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
                                        .getPortfolioLocation()
                                        .getPortfolio()
                                        .getPortfolioLicenses()
                                        .get(i3)
                                        .getLicenseDetails()
                                        .get(i6)
                                        .getPortfolioLicense()
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
                                        .getPortfolioLocation()
                                        .getPortfolio()
                                        .getPortfolioLicenses()
                                        .get(i3)
                                        .getLicenseDetails()
                                        .get(i6)
                                        .getPortfolioLicense()
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
                                          .getPortfolioLocation()
                                          .getPortfolio()
                                          .getPortfolioLicenses()
                                          .get(i3)
                                          .getLicenseDetails()
                                          .get(i6)
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
                                                .getPortfolioLocation()
                                                .getPortfolio()
                                                .getPortfolioLicenses()
                                                .get(i3)
                                                .getLicenseDetails()
                                                .get(i6)
                                                .getPortfolioLicense()
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
                                                .getPortfolioLocation()
                                                .getPortfolio()
                                                .getPortfolioLicenses()
                                                .get(i3)
                                                .getLicenseDetails()
                                                .get(i6)
                                                .getPortfolioLicense()
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
                                && true
                                && model
                                        .getPortfolioLocation()
                                        .getPortfolio()
                                        .getPortfolioLicenses()
                                        .get(i3)
                                        .getLicenseDetails()
                                        .get(i6)
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
                                      .getPortfolioLocation()
                                      .getPortfolio()
                                      .getPortfolioLicenses()
                                      .get(i3)
                                      .getLicenseDetails()
                                      .get(i6)
                                      .getPortfolioLicense()
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
                                      .getPortfolioLocation()
                                      .getPortfolio()
                                      .getPortfolioLicenses()
                                      .get(i3)
                                      .getLicenseDetails()
                                      .get(i6)
                                      .getPortfolioLicense()
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
                                        .getPortfolioLocation()
                                        .getPortfolio()
                                        .getPortfolioLicenses()
                                        .get(i3)
                                        .getLicenseDetails()
                                        .get(i6)
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
                                      .getPortfolioLocation()
                                      .getPortfolio()
                                      .getPortfolioLicenses()
                                      .get(i3)
                                      .getLicenseDetails()
                                      .get(i6)
                                      .getPortfolioLicense()
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
                                      .getPortfolioLocation()
                                      .getPortfolio()
                                      .getPortfolioLicenses()
                                      .get(i3)
                                      .getLicenseDetails()
                                      .get(i6)
                                      .getPortfolioLicense()
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
                                        .getPortfolioLocation()
                                        .getPortfolio()
                                        .getPortfolioLicenses()
                                        .get(i3)
                                        .getLicenseDetails()
                                        .get(i6)
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
                                      .getPortfolioLocation()
                                      .getPortfolio()
                                      .getPortfolioLicenses()
                                      .get(i3)
                                      .getLicenseDetails()
                                      .get(i6)
                                      .getPortfolioLicense()
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
                                      .getPortfolioLocation()
                                      .getPortfolio()
                                      .getPortfolioLicenses()
                                      .get(i3)
                                      .getLicenseDetails()
                                      .get(i6)
                                      .getPortfolioLicense()
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
                                        .getPortfolioLocation()
                                        .getPortfolio()
                                        .getPortfolioLicenses()
                                        .get(i3)
                                        .getLicenseDetails()
                                        .get(i6)
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
                                      .getPortfolioLocation()
                                      .getPortfolio()
                                      .getPortfolioLicenses()
                                      .get(i3)
                                      .getLicenseDetails()
                                      .get(i6)
                                      .getPortfolioLicense()
                                      .getIssueDate();
                              if (isDelete && fetchType == FetchType.EAGER) {
                                whereQuery = " PortfolioLicense7.issueDate = ? ";
                                counter++;
                              } else {
                                whereQuery =
                                    " PortfolioLicense7.issueDate = :PortfolioLicense7issueDate"
                                        + j
                                        + " ";
                              }

                              isOpenParentheses = false;
                              whereValue =
                                  model
                                      .getPortfolioLocation()
                                      .getPortfolio()
                                      .getPortfolioLicenses()
                                      .get(i3)
                                      .getLicenseDetails()
                                      .get(i6)
                                      .getPortfolioLicense()
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
                                        .getPortfolioLocation()
                                        .getPortfolio()
                                        .getPortfolioLicenses()
                                        .get(i3)
                                        .getLicenseDetails()
                                        .get(i6)
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
                                      .getPortfolioLocation()
                                      .getPortfolio()
                                      .getPortfolioLicenses()
                                      .get(i3)
                                      .getLicenseDetails()
                                      .get(i6)
                                      .getPortfolioLicense()
                                      .getEndDate();
                              if (isDelete && fetchType == FetchType.EAGER) {
                                whereQuery = " PortfolioLicense7.endDate = ? ";
                                counter++;
                              } else {
                                whereQuery =
                                    " PortfolioLicense7.endDate = :PortfolioLicense7endDate"
                                        + j
                                        + " ";
                              }

                              isOpenParentheses = false;
                              whereValue =
                                  model
                                      .getPortfolioLocation()
                                      .getPortfolio()
                                      .getPortfolioLicenses()
                                      .get(i3)
                                      .getLicenseDetails()
                                      .get(i6)
                                      .getPortfolioLicense()
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
                                        .getPortfolioLocation()
                                        .getPortfolio()
                                        .getPortfolioLicenses()
                                        .get(i3)
                                        .getLicenseDetails()
                                        .get(i6)
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
                                              .getPortfolioLocation()
                                              .getPortfolio()
                                              .getPortfolioLicenses()
                                              .get(i3)
                                              .getLicenseDetails()
                                              .get(i6)
                                              .getPortfolioLicense()
                                              .getVersion());
                              if (isDelete && fetchType == FetchType.EAGER) {
                                whereQuery = " PortfolioLicense7.VERSION = ? ";
                                counter++;
                              } else {
                                whereQuery =
                                    " PortfolioLicense7.version = :PortfolioLicense7version"
                                        + j
                                        + " ";
                              }

                              isOpenParentheses = false;
                              whereValue =
                                  new java.lang.Integer(
                                      ""
                                          + model
                                              .getPortfolioLocation()
                                              .getPortfolio()
                                              .getPortfolioLicenses()
                                              .get(i3)
                                              .getLicenseDetails()
                                              .get(i6)
                                              .getPortfolioLicense()
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
                          }
                          if (!isExistRecord
                              && !isDelete
                              && model
                                      .getPortfolioLocation()
                                      .getPortfolio()
                                      .getPortfolioLicenses()
                                      .get(i3)
                                      .getLicenseDetails()
                                      .get(i6)
                                      .getFile()
                                  != null) {

                            if (model
                                    .getPortfolioLocation()
                                    .getPortfolio()
                                    .getPortfolioLicenses()
                                    .get(i3)
                                    .getLicenseDetails()
                                    .get(i6)
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
                                      .getPortfolioLocation()
                                      .getPortfolio()
                                      .getPortfolioLicenses()
                                      .get(i3)
                                      .getLicenseDetails()
                                      .get(i6)
                                      .getFile()
                                      .getId();
                              if (isDelete && fetchType == FetchType.EAGER) {
                                whereQuery = " File11.l4610flmtdataid = ? ";
                                counter++;
                              } else {
                                whereQuery = " File11.id = :File11id" + j + " ";
                              }

                              isOpenParentheses = false;
                              whereValue =
                                  model
                                      .getPortfolioLocation()
                                      .getPortfolio()
                                      .getPortfolioLicenses()
                                      .get(i3)
                                      .getLicenseDetails()
                                      .get(i6)
                                      .getFile()
                                      .getId();
                              query +=
                                  (andCondition
                                      + Facade.getStatement(
                                          statement,
                                          "File11.id",
                                          "File11id" + j,
                                          mapVariable,
                                          whereQuery,
                                          whereValue,
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
                                        .getPortfolioLocation()
                                        .getPortfolio()
                                        .getPortfolioLicenses()
                                        .get(i3)
                                        .getLicenseDetails()
                                        .get(i6)
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
                                      .getPortfolioLocation()
                                      .getPortfolio()
                                      .getPortfolioLicenses()
                                      .get(i3)
                                      .getLicenseDetails()
                                      .get(i6)
                                      .getFile()
                                      .getExtension();
                              if (isDelete && fetchType == FetchType.EAGER) {
                                whereQuery = " File11.l4610extension = ? ";
                                counter++;
                              } else {
                                whereQuery = " File11.extension = :File11extension" + j + " ";
                              }

                              isOpenParentheses = false;
                              whereValue =
                                  model
                                      .getPortfolioLocation()
                                      .getPortfolio()
                                      .getPortfolioLicenses()
                                      .get(i3)
                                      .getLicenseDetails()
                                      .get(i6)
                                      .getFile()
                                      .getExtension();
                              query +=
                                  (andCondition
                                      + Facade.getStatement(
                                          statement,
                                          "File11.extension",
                                          "File11extension" + j,
                                          mapVariable,
                                          whereQuery,
                                          whereValue,
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
                                        .getPortfolioLocation()
                                        .getPortfolio()
                                        .getPortfolioLicenses()
                                        .get(i3)
                                        .getLicenseDetails()
                                        .get(i6)
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
                                              .getPortfolioLocation()
                                              .getPortfolio()
                                              .getPortfolioLicenses()
                                              .get(i3)
                                              .getLicenseDetails()
                                              .get(i6)
                                              .getFile()
                                              .getVersion());
                              if (isDelete && fetchType == FetchType.EAGER) {
                                whereQuery = " File11.VERSION = ? ";
                                counter++;
                              } else {
                                whereQuery = " File11.version = :File11version" + j + " ";
                              }

                              isOpenParentheses = false;
                              whereValue =
                                  new java.lang.Integer(
                                      ""
                                          + model
                                              .getPortfolioLocation()
                                              .getPortfolio()
                                              .getPortfolioLicenses()
                                              .get(i3)
                                              .getLicenseDetails()
                                              .get(i6)
                                              .getFile()
                                              .getVersion());
                              query +=
                                  (andCondition
                                      + Facade.getStatement(
                                          statement,
                                          "File11.version",
                                          "File11version" + j,
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
                                      .getPortfolioLocation()
                                      .getPortfolio()
                                      .getPortfolioLicenses()
                                      .get(i3)
                                      .getLicenseDetails()
                                      .get(i6)
                                      .getAddress()
                                  != null) {
                            if (!isDelete
                                && true
                                && model
                                        .getPortfolioLocation()
                                        .getPortfolio()
                                        .getPortfolioLicenses()
                                        .get(i3)
                                        .getLicenseDetails()
                                        .get(i6)
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
                                      .getPortfolioLocation()
                                      .getPortfolio()
                                      .getPortfolioLicenses()
                                      .get(i3)
                                      .getLicenseDetails()
                                      .get(i6)
                                      .getAddress()
                                      .getAddressLine1();
                              if (isDelete && fetchType == FetchType.EAGER) {
                                whereQuery = " LicenseDetails6.addressLine1 = ? ";
                                counter++;
                              } else {
                                whereQuery =
                                    " LicenseDetails6.address.addressLine1 = :LicenseDetails6addressaddressLine1"
                                        + j
                                        + " ";
                              }

                              isOpenParentheses = false;
                              whereValue =
                                  model
                                      .getPortfolioLocation()
                                      .getPortfolio()
                                      .getPortfolioLicenses()
                                      .get(i3)
                                      .getLicenseDetails()
                                      .get(i6)
                                      .getAddress()
                                      .getAddressLine1();
                              query +=
                                  (andCondition
                                      + Facade.getStatement(
                                          statement,
                                          "LicenseDetails6.address.addressLine1",
                                          "LicenseDetails6addressaddressLine1" + j,
                                          mapVariable,
                                          whereQuery,
                                          whereValue,
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
                                        .getPortfolioLocation()
                                        .getPortfolio()
                                        .getPortfolioLicenses()
                                        .get(i3)
                                        .getLicenseDetails()
                                        .get(i6)
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
                                      .getPortfolioLocation()
                                      .getPortfolio()
                                      .getPortfolioLicenses()
                                      .get(i3)
                                      .getLicenseDetails()
                                      .get(i6)
                                      .getAddress()
                                      .getAddressLine2();
                              if (isDelete && fetchType == FetchType.EAGER) {
                                whereQuery = " LicenseDetails6.addressLine2 = ? ";
                                counter++;
                              } else {
                                whereQuery =
                                    " LicenseDetails6.address.addressLine2 = :LicenseDetails6addressaddressLine2"
                                        + j
                                        + " ";
                              }

                              isOpenParentheses = false;
                              whereValue =
                                  model
                                      .getPortfolioLocation()
                                      .getPortfolio()
                                      .getPortfolioLicenses()
                                      .get(i3)
                                      .getLicenseDetails()
                                      .get(i6)
                                      .getAddress()
                                      .getAddressLine2();
                              query +=
                                  (andCondition
                                      + Facade.getStatement(
                                          statement,
                                          "LicenseDetails6.address.addressLine2",
                                          "LicenseDetails6addressaddressLine2" + j,
                                          mapVariable,
                                          whereQuery,
                                          whereValue,
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
                                        .getPortfolioLocation()
                                        .getPortfolio()
                                        .getPortfolioLicenses()
                                        .get(i3)
                                        .getLicenseDetails()
                                        .get(i6)
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
                                      .getPortfolioLocation()
                                      .getPortfolio()
                                      .getPortfolioLicenses()
                                      .get(i3)
                                      .getLicenseDetails()
                                      .get(i6)
                                      .getAddress()
                                      .getCity();
                              if (isDelete && fetchType == FetchType.EAGER) {
                                whereQuery = " LicenseDetails6.city = ? ";
                                counter++;
                              } else {
                                whereQuery =
                                    " LicenseDetails6.address.city = :LicenseDetails6addresscity"
                                        + j
                                        + " ";
                              }

                              isOpenParentheses = false;
                              whereValue =
                                  model
                                      .getPortfolioLocation()
                                      .getPortfolio()
                                      .getPortfolioLicenses()
                                      .get(i3)
                                      .getLicenseDetails()
                                      .get(i6)
                                      .getAddress()
                                      .getCity();
                              query +=
                                  (andCondition
                                      + Facade.getStatement(
                                          statement,
                                          "LicenseDetails6.address.city",
                                          "LicenseDetails6addresscity" + j,
                                          mapVariable,
                                          whereQuery,
                                          whereValue,
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
                                        .getPortfolioLocation()
                                        .getPortfolio()
                                        .getPortfolioLicenses()
                                        .get(i3)
                                        .getLicenseDetails()
                                        .get(i6)
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
                                      .getPortfolioLocation()
                                      .getPortfolio()
                                      .getPortfolioLicenses()
                                      .get(i3)
                                      .getLicenseDetails()
                                      .get(i6)
                                      .getAddress()
                                      .getState();
                              if (isDelete && fetchType == FetchType.EAGER) {
                                whereQuery = " LicenseDetails6.state = ? ";
                                counter++;
                              } else {
                                whereQuery =
                                    " LicenseDetails6.address.state = :LicenseDetails6addressstate"
                                        + j
                                        + " ";
                              }

                              isOpenParentheses = false;
                              whereValue =
                                  model
                                      .getPortfolioLocation()
                                      .getPortfolio()
                                      .getPortfolioLicenses()
                                      .get(i3)
                                      .getLicenseDetails()
                                      .get(i6)
                                      .getAddress()
                                      .getState();
                              query +=
                                  (andCondition
                                      + Facade.getStatement(
                                          statement,
                                          "LicenseDetails6.address.state",
                                          "LicenseDetails6addressstate" + j,
                                          mapVariable,
                                          whereQuery,
                                          whereValue,
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
                                        .getPortfolioLocation()
                                        .getPortfolio()
                                        .getPortfolioLicenses()
                                        .get(i3)
                                        .getLicenseDetails()
                                        .get(i6)
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
                                      .getPortfolioLocation()
                                      .getPortfolio()
                                      .getPortfolioLicenses()
                                      .get(i3)
                                      .getLicenseDetails()
                                      .get(i6)
                                      .getAddress()
                                      .getCountry();
                              if (isDelete && fetchType == FetchType.EAGER) {
                                whereQuery = " LicenseDetails6.country = ? ";
                                counter++;
                              } else {
                                whereQuery =
                                    " LicenseDetails6.address.country = :LicenseDetails6addresscountry"
                                        + j
                                        + " ";
                              }

                              isOpenParentheses = false;
                              whereValue =
                                  model
                                      .getPortfolioLocation()
                                      .getPortfolio()
                                      .getPortfolioLicenses()
                                      .get(i3)
                                      .getLicenseDetails()
                                      .get(i6)
                                      .getAddress()
                                      .getCountry();
                              query +=
                                  (andCondition
                                      + Facade.getStatement(
                                          statement,
                                          "LicenseDetails6.address.country",
                                          "LicenseDetails6addresscountry" + j,
                                          mapVariable,
                                          whereQuery,
                                          whereValue,
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
                                        .getPortfolioLocation()
                                        .getPortfolio()
                                        .getPortfolioLicenses()
                                        .get(i3)
                                        .getLicenseDetails()
                                        .get(i6)
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
                                      .getPortfolioLocation()
                                      .getPortfolio()
                                      .getPortfolioLicenses()
                                      .get(i3)
                                      .getLicenseDetails()
                                      .get(i6)
                                      .getAddress()
                                      .getZipCode();
                              if (isDelete && fetchType == FetchType.EAGER) {
                                whereQuery = " LicenseDetails6.zipCode = ? ";
                                counter++;
                              } else {
                                whereQuery =
                                    " LicenseDetails6.address.zipCode = :LicenseDetails6addresszipCode"
                                        + j
                                        + " ";
                              }

                              isOpenParentheses = false;
                              whereValue =
                                  model
                                      .getPortfolioLocation()
                                      .getPortfolio()
                                      .getPortfolioLicenses()
                                      .get(i3)
                                      .getLicenseDetails()
                                      .get(i6)
                                      .getAddress()
                                      .getZipCode();
                              query +=
                                  (andCondition
                                      + Facade.getStatement(
                                          statement,
                                          "LicenseDetails6.address.zipCode",
                                          "LicenseDetails6addresszipCode" + j,
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
                          if (length6 > 1) {
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
                                .getPortfolioLocation()
                                .getPortfolio()
                                .getPortfolioLicenses()
                                .get(i3)
                                .getIssueVerifierType()
                            != null) {

                      andCondition = " AND ";
                      if (isOpenParentheses) {
                        andCondition = " ";
                      }
                      query += " ";
                      whereValue =
                          model
                              .getPortfolioLocation()
                              .getPortfolio()
                              .getPortfolioLicenses()
                              .get(i3)
                              .getIssueVerifierType();
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " PortfolioLicense3.issueVerifierType = ? ";
                        counter++;
                      } else {
                        whereQuery =
                            " PortfolioLicense3.issueVerifierType = :PortfolioLicense3issueVerifierType"
                                + j
                                + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          model
                              .getPortfolioLocation()
                              .getPortfolio()
                              .getPortfolioLicenses()
                              .get(i3)
                              .getIssueVerifierType();
                      query +=
                          (andCondition
                              + Facade.getStatement(
                                  statement,
                                  "PortfolioLicense3.issueVerifierType",
                                  "PortfolioLicense3issueVerifierType" + j,
                                  mapVariable,
                                  whereQuery,
                                  whereValue,
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
                                .getPortfolioLocation()
                                .getPortfolio()
                                .getPortfolioLicenses()
                                .get(i3)
                                .getLicenseNumber()
                            != null) {

                      andCondition = " AND ";
                      if (isOpenParentheses) {
                        andCondition = " ";
                      }
                      query += " ";
                      whereValue =
                          model
                              .getPortfolioLocation()
                              .getPortfolio()
                              .getPortfolioLicenses()
                              .get(i3)
                              .getLicenseNumber();
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " PortfolioLicense3.licenseNumber = ? ";
                        counter++;
                      } else {
                        whereQuery =
                            " PortfolioLicense3.licenseNumber = :PortfolioLicense3licenseNumber"
                                + j
                                + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          model
                              .getPortfolioLocation()
                              .getPortfolio()
                              .getPortfolioLicenses()
                              .get(i3)
                              .getLicenseNumber();
                      query +=
                          (andCondition
                              + Facade.getStatement(
                                  statement,
                                  "PortfolioLicense3.licenseNumber",
                                  "PortfolioLicense3licenseNumber" + j,
                                  mapVariable,
                                  whereQuery,
                                  whereValue,
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
                                .getPortfolioLocation()
                                .getPortfolio()
                                .getPortfolioLicenses()
                                .get(i3)
                                .getLicenseType()
                            != null) {

                      andCondition = " AND ";
                      if (isOpenParentheses) {
                        andCondition = " ";
                      }
                      query += " ";
                      whereValue =
                          model
                              .getPortfolioLocation()
                              .getPortfolio()
                              .getPortfolioLicenses()
                              .get(i3)
                              .getLicenseType();
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " PortfolioLicense3.licenseType = ? ";
                        counter++;
                      } else {
                        whereQuery =
                            " PortfolioLicense3.licenseType = :PortfolioLicense3licenseType"
                                + j
                                + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          model
                              .getPortfolioLocation()
                              .getPortfolio()
                              .getPortfolioLicenses()
                              .get(i3)
                              .getLicenseType();
                      query +=
                          (andCondition
                              + Facade.getStatement(
                                  statement,
                                  "PortfolioLicense3.licenseType",
                                  "PortfolioLicense3licenseType" + j,
                                  mapVariable,
                                  whereQuery,
                                  whereValue,
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
                                .getPortfolioLocation()
                                .getPortfolio()
                                .getPortfolioLicenses()
                                .get(i3)
                                .getIssueDate()
                            != null) {

                      andCondition = " AND ";
                      if (isOpenParentheses) {
                        andCondition = " ";
                      }
                      query += " ";
                      whereValue =
                          model
                              .getPortfolioLocation()
                              .getPortfolio()
                              .getPortfolioLicenses()
                              .get(i3)
                              .getIssueDate();
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " PortfolioLicense3.issueDate = ? ";
                        counter++;
                      } else {
                        whereQuery =
                            " PortfolioLicense3.issueDate = :PortfolioLicense3issueDate" + j + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          model
                              .getPortfolioLocation()
                              .getPortfolio()
                              .getPortfolioLicenses()
                              .get(i3)
                              .getIssueDate();
                      query +=
                          (andCondition
                              + Facade.getStatement(
                                  statement,
                                  "PortfolioLicense3.issueDate",
                                  "PortfolioLicense3issueDate" + j,
                                  mapVariable,
                                  whereQuery,
                                  whereValue,
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
                                .getPortfolioLocation()
                                .getPortfolio()
                                .getPortfolioLicenses()
                                .get(i3)
                                .getEndDate()
                            != null) {

                      andCondition = " AND ";
                      if (isOpenParentheses) {
                        andCondition = " ";
                      }
                      query += " ";
                      whereValue =
                          model
                              .getPortfolioLocation()
                              .getPortfolio()
                              .getPortfolioLicenses()
                              .get(i3)
                              .getEndDate();
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " PortfolioLicense3.endDate = ? ";
                        counter++;
                      } else {
                        whereQuery =
                            " PortfolioLicense3.endDate = :PortfolioLicense3endDate" + j + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          model
                              .getPortfolioLocation()
                              .getPortfolio()
                              .getPortfolioLicenses()
                              .get(i3)
                              .getEndDate();
                      query +=
                          (andCondition
                              + Facade.getStatement(
                                  statement,
                                  "PortfolioLicense3.endDate",
                                  "PortfolioLicense3endDate" + j,
                                  mapVariable,
                                  whereQuery,
                                  whereValue,
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
                                .getPortfolioLocation()
                                .getPortfolio()
                                .getPortfolioLicenses()
                                .get(i3)
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
                                      .getPortfolioLocation()
                                      .getPortfolio()
                                      .getPortfolioLicenses()
                                      .get(i3)
                                      .getVersion());
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " PortfolioLicense3.VERSION = ? ";
                        counter++;
                      } else {
                        whereQuery =
                            " PortfolioLicense3.version = :PortfolioLicense3version" + j + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          new java.lang.Integer(
                              ""
                                  + model
                                      .getPortfolioLocation()
                                      .getPortfolio()
                                      .getPortfolioLicenses()
                                      .get(i3)
                                      .getVersion());
                      query +=
                          (andCondition
                              + Facade.getStatement(
                                  statement,
                                  "PortfolioLicense3.version",
                                  "PortfolioLicense3version" + j,
                                  mapVariable,
                                  whereQuery,
                                  whereValue,
                                  isExistRecord,
                                  isDelete,
                                  fetchType,
                                  counter));
                      whereQuery = "";
                    }
                    if (length3 > 1) {
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
                  && model.getPortfolioLocation().getPortfolio().getPortfolioLocations() != null) {
                int length12 =
                    model.getPortfolioLocation().getPortfolio().getPortfolioLocations().size();

                if (length12 > 0) {
                  if (isOpenParentheses) {
                    query += "  ( ";
                  } else {
                    query += " AND ( ";
                  }
                  isOpenParentheses = true;

                  for (int i12 = 0; i12 < length12; i12++) {

                    java.lang.String beforeCondition12 = "";
                    java.lang.String prefix12 = " OR ";
                    if (i12 == 0) {
                      prefix12 = "";
                    }
                    if (length12 > 1) {

                      query += (prefix12 + "(");
                      isOpenParentheses = true;

                    } else {

                      query += (prefix12);
                      if (!prefix12.trim().equals("")) {
                        isOpenParentheses = false;
                      }
                    }

                    if (model
                            .getPortfolioLocation()
                            .getPortfolio()
                            .getPortfolioLocations()
                            .get(i12)
                            .getPortfolioLocationId()
                        != null) {

                      andCondition = " AND ";
                      if (isOpenParentheses) {
                        andCondition = " ";
                      }
                      query += " ";
                      whereValue =
                          model
                              .getPortfolioLocation()
                              .getPortfolio()
                              .getPortfolioLocations()
                              .get(i12)
                              .getPortfolioLocationId();
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " PortfolioLocation12.id = ? ";
                        counter++;
                      } else {
                        whereQuery =
                            " PortfolioLocation12.portfolioLocationId = :PortfolioLocation12portfolioLocationId"
                                + j
                                + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          model
                              .getPortfolioLocation()
                              .getPortfolio()
                              .getPortfolioLocations()
                              .get(i12)
                              .getPortfolioLocationId();
                      query +=
                          (andCondition
                              + Facade.getStatement(
                                  statement,
                                  "PortfolioLocation12.portfolioLocationId",
                                  "PortfolioLocation12portfolioLocationId" + j,
                                  mapVariable,
                                  whereQuery,
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
                                .getPortfolioLocation()
                                .getPortfolio()
                                .getPortfolioLocations()
                                .get(i12)
                                .getFile()
                            != null) {

                      if (model
                              .getPortfolioLocation()
                              .getPortfolio()
                              .getPortfolioLocations()
                              .get(i12)
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
                                .getPortfolioLocation()
                                .getPortfolio()
                                .getPortfolioLocations()
                                .get(i12)
                                .getFile()
                                .getId();
                        if (isDelete && fetchType == FetchType.EAGER) {
                          whereQuery = " File14.l4610flmtdataid = ? ";
                          counter++;
                        } else {
                          whereQuery = " File14.id = :File14id" + j + " ";
                        }

                        isOpenParentheses = false;
                        whereValue =
                            model
                                .getPortfolioLocation()
                                .getPortfolio()
                                .getPortfolioLocations()
                                .get(i12)
                                .getFile()
                                .getId();
                        query +=
                            (andCondition
                                + Facade.getStatement(
                                    statement,
                                    "File14.id",
                                    "File14id" + j,
                                    mapVariable,
                                    whereQuery,
                                    whereValue,
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
                                  .getPortfolioLocation()
                                  .getPortfolio()
                                  .getPortfolioLocations()
                                  .get(i12)
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
                                .getPortfolioLocation()
                                .getPortfolio()
                                .getPortfolioLocations()
                                .get(i12)
                                .getFile()
                                .getExtension();
                        if (isDelete && fetchType == FetchType.EAGER) {
                          whereQuery = " File14.l4610extension = ? ";
                          counter++;
                        } else {
                          whereQuery = " File14.extension = :File14extension" + j + " ";
                        }

                        isOpenParentheses = false;
                        whereValue =
                            model
                                .getPortfolioLocation()
                                .getPortfolio()
                                .getPortfolioLocations()
                                .get(i12)
                                .getFile()
                                .getExtension();
                        query +=
                            (andCondition
                                + Facade.getStatement(
                                    statement,
                                    "File14.extension",
                                    "File14extension" + j,
                                    mapVariable,
                                    whereQuery,
                                    whereValue,
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
                                  .getPortfolioLocation()
                                  .getPortfolio()
                                  .getPortfolioLocations()
                                  .get(i12)
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
                                        .getPortfolioLocation()
                                        .getPortfolio()
                                        .getPortfolioLocations()
                                        .get(i12)
                                        .getFile()
                                        .getVersion());
                        if (isDelete && fetchType == FetchType.EAGER) {
                          whereQuery = " File14.VERSION = ? ";
                          counter++;
                        } else {
                          whereQuery = " File14.version = :File14version" + j + " ";
                        }

                        isOpenParentheses = false;
                        whereValue =
                            new java.lang.Integer(
                                ""
                                    + model
                                        .getPortfolioLocation()
                                        .getPortfolio()
                                        .getPortfolioLocations()
                                        .get(i12)
                                        .getFile()
                                        .getVersion());
                        query +=
                            (andCondition
                                + Facade.getStatement(
                                    statement,
                                    "File14.version",
                                    "File14version" + j,
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
                                .getPortfolioLocation()
                                .getPortfolio()
                                .getPortfolioLocations()
                                .get(i12)
                                .getLocationDetails()
                            != null) {
                      int length15 =
                          model
                              .getPortfolioLocation()
                              .getPortfolio()
                              .getPortfolioLocations()
                              .get(i12)
                              .getLocationDetails()
                              .size();

                      if (length15 > 0) {
                        if (isOpenParentheses) {
                          query += "  ( ";
                        } else {
                          query += " AND ( ";
                        }
                        isOpenParentheses = true;

                        for (int i15 = 0; i15 < length15; i15++) {

                          java.lang.String beforeCondition15 = "";
                          java.lang.String prefix15 = " OR ";
                          if (i15 == 0) {
                            prefix15 = "";
                          }
                          if (length15 > 1) {

                            query += (prefix15 + "(");
                            isOpenParentheses = true;

                          } else {

                            query += (prefix15);
                            if (!prefix15.trim().equals("")) {
                              isOpenParentheses = false;
                            }
                          }

                          if (model
                                  .getPortfolioLocation()
                                  .getPortfolio()
                                  .getPortfolioLocations()
                                  .get(i12)
                                  .getLocationDetails()
                                  .get(i15)
                                  .getLocationDetailId()
                              != null) {

                            andCondition = " AND ";
                            if (isOpenParentheses) {
                              andCondition = " ";
                            }
                            query += " ";
                            whereValue =
                                model
                                    .getPortfolioLocation()
                                    .getPortfolio()
                                    .getPortfolioLocations()
                                    .get(i12)
                                    .getLocationDetails()
                                    .get(i15)
                                    .getLocationDetailId();
                            if (isDelete && fetchType == FetchType.EAGER) {
                              whereQuery = " LocationDetails15.id = ? ";
                              counter++;
                            } else {
                              whereQuery =
                                  " LocationDetails15.locationDetailId = :LocationDetails15locationDetailId"
                                      + j
                                      + " ";
                            }

                            isOpenParentheses = false;
                            whereValue =
                                model
                                    .getPortfolioLocation()
                                    .getPortfolio()
                                    .getPortfolioLocations()
                                    .get(i12)
                                    .getLocationDetails()
                                    .get(i15)
                                    .getLocationDetailId();
                            query +=
                                (andCondition
                                    + Facade.getStatement(
                                        statement,
                                        "LocationDetails15.locationDetailId",
                                        "LocationDetails15locationDetailId" + j,
                                        mapVariable,
                                        whereQuery,
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
                                      .getPortfolioLocation()
                                      .getPortfolio()
                                      .getPortfolioLocations()
                                      .get(i12)
                                      .getLocationDetails()
                                      .get(i15)
                                      .getFile()
                                  != null) {

                            if (model
                                    .getPortfolioLocation()
                                    .getPortfolio()
                                    .getPortfolioLocations()
                                    .get(i12)
                                    .getLocationDetails()
                                    .get(i15)
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
                                      .getPortfolioLocation()
                                      .getPortfolio()
                                      .getPortfolioLocations()
                                      .get(i12)
                                      .getLocationDetails()
                                      .get(i15)
                                      .getFile()
                                      .getId();
                              if (isDelete && fetchType == FetchType.EAGER) {
                                whereQuery = " File17.l4610flmtdataid = ? ";
                                counter++;
                              } else {
                                whereQuery = " File17.id = :File17id" + j + " ";
                              }

                              isOpenParentheses = false;
                              whereValue =
                                  model
                                      .getPortfolioLocation()
                                      .getPortfolio()
                                      .getPortfolioLocations()
                                      .get(i12)
                                      .getLocationDetails()
                                      .get(i15)
                                      .getFile()
                                      .getId();
                              query +=
                                  (andCondition
                                      + Facade.getStatement(
                                          statement,
                                          "File17.id",
                                          "File17id" + j,
                                          mapVariable,
                                          whereQuery,
                                          whereValue,
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
                                        .getPortfolioLocation()
                                        .getPortfolio()
                                        .getPortfolioLocations()
                                        .get(i12)
                                        .getLocationDetails()
                                        .get(i15)
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
                                      .getPortfolioLocation()
                                      .getPortfolio()
                                      .getPortfolioLocations()
                                      .get(i12)
                                      .getLocationDetails()
                                      .get(i15)
                                      .getFile()
                                      .getExtension();
                              if (isDelete && fetchType == FetchType.EAGER) {
                                whereQuery = " File17.l4610extension = ? ";
                                counter++;
                              } else {
                                whereQuery = " File17.extension = :File17extension" + j + " ";
                              }

                              isOpenParentheses = false;
                              whereValue =
                                  model
                                      .getPortfolioLocation()
                                      .getPortfolio()
                                      .getPortfolioLocations()
                                      .get(i12)
                                      .getLocationDetails()
                                      .get(i15)
                                      .getFile()
                                      .getExtension();
                              query +=
                                  (andCondition
                                      + Facade.getStatement(
                                          statement,
                                          "File17.extension",
                                          "File17extension" + j,
                                          mapVariable,
                                          whereQuery,
                                          whereValue,
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
                                        .getPortfolioLocation()
                                        .getPortfolio()
                                        .getPortfolioLocations()
                                        .get(i12)
                                        .getLocationDetails()
                                        .get(i15)
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
                                              .getPortfolioLocation()
                                              .getPortfolio()
                                              .getPortfolioLocations()
                                              .get(i12)
                                              .getLocationDetails()
                                              .get(i15)
                                              .getFile()
                                              .getVersion());
                              if (isDelete && fetchType == FetchType.EAGER) {
                                whereQuery = " File17.VERSION = ? ";
                                counter++;
                              } else {
                                whereQuery = " File17.version = :File17version" + j + " ";
                              }

                              isOpenParentheses = false;
                              whereValue =
                                  new java.lang.Integer(
                                      ""
                                          + model
                                              .getPortfolioLocation()
                                              .getPortfolio()
                                              .getPortfolioLocations()
                                              .get(i12)
                                              .getLocationDetails()
                                              .get(i15)
                                              .getFile()
                                              .getVersion());
                              query +=
                                  (andCondition
                                      + Facade.getStatement(
                                          statement,
                                          "File17.version",
                                          "File17version" + j,
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
                                      .getPortfolioLocation()
                                      .getPortfolio()
                                      .getPortfolioLocations()
                                      .get(i12)
                                      .getLocationDetails()
                                      .get(i15)
                                      .getAddress()
                                  != null) {
                            if (!isDelete
                                && true
                                && model
                                        .getPortfolioLocation()
                                        .getPortfolio()
                                        .getPortfolioLocations()
                                        .get(i12)
                                        .getLocationDetails()
                                        .get(i15)
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
                                      .getPortfolioLocation()
                                      .getPortfolio()
                                      .getPortfolioLocations()
                                      .get(i12)
                                      .getLocationDetails()
                                      .get(i15)
                                      .getAddress()
                                      .getAddressLine1();
                              if (isDelete && fetchType == FetchType.EAGER) {
                                whereQuery = " LocationDetails15.addressLine1 = ? ";
                                counter++;
                              } else {
                                whereQuery =
                                    " LocationDetails15.address.addressLine1 = :LocationDetails15addressaddressLine1"
                                        + j
                                        + " ";
                              }

                              isOpenParentheses = false;
                              whereValue =
                                  model
                                      .getPortfolioLocation()
                                      .getPortfolio()
                                      .getPortfolioLocations()
                                      .get(i12)
                                      .getLocationDetails()
                                      .get(i15)
                                      .getAddress()
                                      .getAddressLine1();
                              query +=
                                  (andCondition
                                      + Facade.getStatement(
                                          statement,
                                          "LocationDetails15.address.addressLine1",
                                          "LocationDetails15addressaddressLine1" + j,
                                          mapVariable,
                                          whereQuery,
                                          whereValue,
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
                                        .getPortfolioLocation()
                                        .getPortfolio()
                                        .getPortfolioLocations()
                                        .get(i12)
                                        .getLocationDetails()
                                        .get(i15)
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
                                      .getPortfolioLocation()
                                      .getPortfolio()
                                      .getPortfolioLocations()
                                      .get(i12)
                                      .getLocationDetails()
                                      .get(i15)
                                      .getAddress()
                                      .getAddressLine2();
                              if (isDelete && fetchType == FetchType.EAGER) {
                                whereQuery = " LocationDetails15.addressLine2 = ? ";
                                counter++;
                              } else {
                                whereQuery =
                                    " LocationDetails15.address.addressLine2 = :LocationDetails15addressaddressLine2"
                                        + j
                                        + " ";
                              }

                              isOpenParentheses = false;
                              whereValue =
                                  model
                                      .getPortfolioLocation()
                                      .getPortfolio()
                                      .getPortfolioLocations()
                                      .get(i12)
                                      .getLocationDetails()
                                      .get(i15)
                                      .getAddress()
                                      .getAddressLine2();
                              query +=
                                  (andCondition
                                      + Facade.getStatement(
                                          statement,
                                          "LocationDetails15.address.addressLine2",
                                          "LocationDetails15addressaddressLine2" + j,
                                          mapVariable,
                                          whereQuery,
                                          whereValue,
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
                                        .getPortfolioLocation()
                                        .getPortfolio()
                                        .getPortfolioLocations()
                                        .get(i12)
                                        .getLocationDetails()
                                        .get(i15)
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
                                      .getPortfolioLocation()
                                      .getPortfolio()
                                      .getPortfolioLocations()
                                      .get(i12)
                                      .getLocationDetails()
                                      .get(i15)
                                      .getAddress()
                                      .getCity();
                              if (isDelete && fetchType == FetchType.EAGER) {
                                whereQuery = " LocationDetails15.city = ? ";
                                counter++;
                              } else {
                                whereQuery =
                                    " LocationDetails15.address.city = :LocationDetails15addresscity"
                                        + j
                                        + " ";
                              }

                              isOpenParentheses = false;
                              whereValue =
                                  model
                                      .getPortfolioLocation()
                                      .getPortfolio()
                                      .getPortfolioLocations()
                                      .get(i12)
                                      .getLocationDetails()
                                      .get(i15)
                                      .getAddress()
                                      .getCity();
                              query +=
                                  (andCondition
                                      + Facade.getStatement(
                                          statement,
                                          "LocationDetails15.address.city",
                                          "LocationDetails15addresscity" + j,
                                          mapVariable,
                                          whereQuery,
                                          whereValue,
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
                                        .getPortfolioLocation()
                                        .getPortfolio()
                                        .getPortfolioLocations()
                                        .get(i12)
                                        .getLocationDetails()
                                        .get(i15)
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
                                      .getPortfolioLocation()
                                      .getPortfolio()
                                      .getPortfolioLocations()
                                      .get(i12)
                                      .getLocationDetails()
                                      .get(i15)
                                      .getAddress()
                                      .getState();
                              if (isDelete && fetchType == FetchType.EAGER) {
                                whereQuery = " LocationDetails15.state = ? ";
                                counter++;
                              } else {
                                whereQuery =
                                    " LocationDetails15.address.state = :LocationDetails15addressstate"
                                        + j
                                        + " ";
                              }

                              isOpenParentheses = false;
                              whereValue =
                                  model
                                      .getPortfolioLocation()
                                      .getPortfolio()
                                      .getPortfolioLocations()
                                      .get(i12)
                                      .getLocationDetails()
                                      .get(i15)
                                      .getAddress()
                                      .getState();
                              query +=
                                  (andCondition
                                      + Facade.getStatement(
                                          statement,
                                          "LocationDetails15.address.state",
                                          "LocationDetails15addressstate" + j,
                                          mapVariable,
                                          whereQuery,
                                          whereValue,
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
                                        .getPortfolioLocation()
                                        .getPortfolio()
                                        .getPortfolioLocations()
                                        .get(i12)
                                        .getLocationDetails()
                                        .get(i15)
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
                                      .getPortfolioLocation()
                                      .getPortfolio()
                                      .getPortfolioLocations()
                                      .get(i12)
                                      .getLocationDetails()
                                      .get(i15)
                                      .getAddress()
                                      .getCountry();
                              if (isDelete && fetchType == FetchType.EAGER) {
                                whereQuery = " LocationDetails15.country = ? ";
                                counter++;
                              } else {
                                whereQuery =
                                    " LocationDetails15.address.country = :LocationDetails15addresscountry"
                                        + j
                                        + " ";
                              }

                              isOpenParentheses = false;
                              whereValue =
                                  model
                                      .getPortfolioLocation()
                                      .getPortfolio()
                                      .getPortfolioLocations()
                                      .get(i12)
                                      .getLocationDetails()
                                      .get(i15)
                                      .getAddress()
                                      .getCountry();
                              query +=
                                  (andCondition
                                      + Facade.getStatement(
                                          statement,
                                          "LocationDetails15.address.country",
                                          "LocationDetails15addresscountry" + j,
                                          mapVariable,
                                          whereQuery,
                                          whereValue,
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
                                        .getPortfolioLocation()
                                        .getPortfolio()
                                        .getPortfolioLocations()
                                        .get(i12)
                                        .getLocationDetails()
                                        .get(i15)
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
                                      .getPortfolioLocation()
                                      .getPortfolio()
                                      .getPortfolioLocations()
                                      .get(i12)
                                      .getLocationDetails()
                                      .get(i15)
                                      .getAddress()
                                      .getZipCode();
                              if (isDelete && fetchType == FetchType.EAGER) {
                                whereQuery = " LocationDetails15.zipCode = ? ";
                                counter++;
                              } else {
                                whereQuery =
                                    " LocationDetails15.address.zipCode = :LocationDetails15addresszipCode"
                                        + j
                                        + " ";
                              }

                              isOpenParentheses = false;
                              whereValue =
                                  model
                                      .getPortfolioLocation()
                                      .getPortfolio()
                                      .getPortfolioLocations()
                                      .get(i12)
                                      .getLocationDetails()
                                      .get(i15)
                                      .getAddress()
                                      .getZipCode();
                              query +=
                                  (andCondition
                                      + Facade.getStatement(
                                          statement,
                                          "LocationDetails15.address.zipCode",
                                          "LocationDetails15addresszipCode" + j,
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
                          if (length15 > 1) {
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
                                .getPortfolioLocation()
                                .getPortfolio()
                                .getPortfolioLocations()
                                .get(i12)
                                .getProvince()
                            != null) {

                      andCondition = " AND ";
                      if (isOpenParentheses) {
                        andCondition = " ";
                      }
                      query += " ";
                      whereValue =
                          model
                              .getPortfolioLocation()
                              .getPortfolio()
                              .getPortfolioLocations()
                              .get(i12)
                              .getProvince();
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " PortfolioLocation12.province = ? ";
                        counter++;
                      } else {
                        whereQuery =
                            " PortfolioLocation12.province = :PortfolioLocation12province"
                                + j
                                + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          model
                              .getPortfolioLocation()
                              .getPortfolio()
                              .getPortfolioLocations()
                              .get(i12)
                              .getProvince();
                      query +=
                          (andCondition
                              + Facade.getStatement(
                                  statement,
                                  "PortfolioLocation12.province",
                                  "PortfolioLocation12province" + j,
                                  mapVariable,
                                  whereQuery,
                                  whereValue,
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
                                .getPortfolioLocation()
                                .getPortfolio()
                                .getPortfolioLocations()
                                .get(i12)
                                .getCity()
                            != null) {

                      andCondition = " AND ";
                      if (isOpenParentheses) {
                        andCondition = " ";
                      }
                      query += " ";
                      whereValue =
                          model
                              .getPortfolioLocation()
                              .getPortfolio()
                              .getPortfolioLocations()
                              .get(i12)
                              .getCity();
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " PortfolioLocation12.city = ? ";
                        counter++;
                      } else {
                        whereQuery =
                            " PortfolioLocation12.city = :PortfolioLocation12city" + j + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          model
                              .getPortfolioLocation()
                              .getPortfolio()
                              .getPortfolioLocations()
                              .get(i12)
                              .getCity();
                      query +=
                          (andCondition
                              + Facade.getStatement(
                                  statement,
                                  "PortfolioLocation12.city",
                                  "PortfolioLocation12city" + j,
                                  mapVariable,
                                  whereQuery,
                                  whereValue,
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
                                .getPortfolioLocation()
                                .getPortfolio()
                                .getPortfolioLocations()
                                .get(i12)
                                .getIndstrltwn()
                            != null) {

                      andCondition = " AND ";
                      if (isOpenParentheses) {
                        andCondition = " ";
                      }
                      query += " ";
                      whereValue =
                          model
                              .getPortfolioLocation()
                              .getPortfolio()
                              .getPortfolioLocations()
                              .get(i12)
                              .getIndstrltwn();
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " PortfolioLocation12.indstrltwn = ? ";
                        counter++;
                      } else {
                        whereQuery =
                            " PortfolioLocation12.indstrltwn = :PortfolioLocation12indstrltwn"
                                + j
                                + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          model
                              .getPortfolioLocation()
                              .getPortfolio()
                              .getPortfolioLocations()
                              .get(i12)
                              .getIndstrltwn();
                      query +=
                          (andCondition
                              + Facade.getStatement(
                                  statement,
                                  "PortfolioLocation12.indstrltwn",
                                  "PortfolioLocation12indstrltwn" + j,
                                  mapVariable,
                                  whereQuery,
                                  whereValue,
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
                                .getPortfolioLocation()
                                .getPortfolio()
                                .getPortfolioLocations()
                                .get(i12)
                                .getAddress()
                            != null) {

                      andCondition = " AND ";
                      if (isOpenParentheses) {
                        andCondition = " ";
                      }
                      query += " ";
                      whereValue =
                          model
                              .getPortfolioLocation()
                              .getPortfolio()
                              .getPortfolioLocations()
                              .get(i12)
                              .getAddress();
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " PortfolioLocation12.address = ? ";
                        counter++;
                      } else {
                        whereQuery =
                            " PortfolioLocation12.address = :PortfolioLocation12address" + j + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          model
                              .getPortfolioLocation()
                              .getPortfolio()
                              .getPortfolioLocations()
                              .get(i12)
                              .getAddress();
                      query +=
                          (andCondition
                              + Facade.getStatement(
                                  statement,
                                  "PortfolioLocation12.address",
                                  "PortfolioLocation12address" + j,
                                  mapVariable,
                                  whereQuery,
                                  whereValue,
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
                                .getPortfolioLocation()
                                .getPortfolio()
                                .getPortfolioLocations()
                                .get(i12)
                                .getStableStatusType()
                            != null) {

                      andCondition = " AND ";
                      if (isOpenParentheses) {
                        andCondition = " ";
                      }
                      query += " ";
                      whereValue =
                          model
                              .getPortfolioLocation()
                              .getPortfolio()
                              .getPortfolioLocations()
                              .get(i12)
                              .getStableStatusType();
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " PortfolioLocation12.stableStatusType = ? ";
                        counter++;
                      } else {
                        whereQuery =
                            " PortfolioLocation12.stableStatusType = :PortfolioLocation12stableStatusType"
                                + j
                                + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          model
                              .getPortfolioLocation()
                              .getPortfolio()
                              .getPortfolioLocations()
                              .get(i12)
                              .getStableStatusType();
                      query +=
                          (andCondition
                              + Facade.getStatement(
                                  statement,
                                  "PortfolioLocation12.stableStatusType",
                                  "PortfolioLocation12stableStatusType" + j,
                                  mapVariable,
                                  whereQuery,
                                  whereValue,
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
                                .getPortfolioLocation()
                                .getPortfolio()
                                .getPortfolioLocations()
                                .get(i12)
                                .getOwnerStatusType()
                            != null) {

                      andCondition = " AND ";
                      if (isOpenParentheses) {
                        andCondition = " ";
                      }
                      query += " ";
                      whereValue =
                          model
                              .getPortfolioLocation()
                              .getPortfolio()
                              .getPortfolioLocations()
                              .get(i12)
                              .getOwnerStatusType();
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " PortfolioLocation12.ownerStatusType = ? ";
                        counter++;
                      } else {
                        whereQuery =
                            " PortfolioLocation12.ownerStatusType = :PortfolioLocation12ownerStatusType"
                                + j
                                + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          model
                              .getPortfolioLocation()
                              .getPortfolio()
                              .getPortfolioLocations()
                              .get(i12)
                              .getOwnerStatusType();
                      query +=
                          (andCondition
                              + Facade.getStatement(
                                  statement,
                                  "PortfolioLocation12.ownerStatusType",
                                  "PortfolioLocation12ownerStatusType" + j,
                                  mapVariable,
                                  whereQuery,
                                  whereValue,
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
                                .getPortfolioLocation()
                                .getPortfolio()
                                .getPortfolioLocations()
                                .get(i12)
                                .getRegionalStatusType()
                            != null) {

                      andCondition = " AND ";
                      if (isOpenParentheses) {
                        andCondition = " ";
                      }
                      query += " ";
                      whereValue =
                          model
                              .getPortfolioLocation()
                              .getPortfolio()
                              .getPortfolioLocations()
                              .get(i12)
                              .getRegionalStatusType();
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " PortfolioLocation12.regionalStatusType = ? ";
                        counter++;
                      } else {
                        whereQuery =
                            " PortfolioLocation12.regionalStatusType = :PortfolioLocation12regionalStatusType"
                                + j
                                + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          model
                              .getPortfolioLocation()
                              .getPortfolio()
                              .getPortfolioLocations()
                              .get(i12)
                              .getRegionalStatusType();
                      query +=
                          (andCondition
                              + Facade.getStatement(
                                  statement,
                                  "PortfolioLocation12.regionalStatusType",
                                  "PortfolioLocation12regionalStatusType" + j,
                                  mapVariable,
                                  whereQuery,
                                  whereValue,
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
                                .getPortfolioLocation()
                                .getPortfolio()
                                .getPortfolioLocations()
                                .get(i12)
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
                                      .getPortfolioLocation()
                                      .getPortfolio()
                                      .getPortfolioLocations()
                                      .get(i12)
                                      .getVersion());
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " PortfolioLocation12.VERSION = ? ";
                        counter++;
                      } else {
                        whereQuery =
                            " PortfolioLocation12.version = :PortfolioLocation12version" + j + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          new java.lang.Integer(
                              ""
                                  + model
                                      .getPortfolioLocation()
                                      .getPortfolio()
                                      .getPortfolioLocations()
                                      .get(i12)
                                      .getVersion());
                      query +=
                          (andCondition
                              + Facade.getStatement(
                                  statement,
                                  "PortfolioLocation12.version",
                                  "PortfolioLocation12version" + j,
                                  mapVariable,
                                  whereQuery,
                                  whereValue,
                                  isExistRecord,
                                  isDelete,
                                  fetchType,
                                  counter));
                      whereQuery = "";
                    }
                    if (length12 > 1) {
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
                  && model.getPortfolioLocation().getPortfolio().getReceptions() != null) {
                int length18 = model.getPortfolioLocation().getPortfolio().getReceptions().size();

                if (length18 > 0) {
                  if (isOpenParentheses) {
                    query += "  ( ";
                  } else {
                    query += " AND ( ";
                  }
                  isOpenParentheses = true;

                  for (int i18 = 0; i18 < length18; i18++) {

                    java.lang.String beforeCondition18 = "";
                    java.lang.String prefix18 = " OR ";
                    if (i18 == 0) {
                      prefix18 = "";
                    }
                    if (length18 > 1) {

                      query += (prefix18 + "(");
                      isOpenParentheses = true;

                    } else {

                      query += (prefix18);
                      if (!prefix18.trim().equals("")) {
                        isOpenParentheses = false;
                      }
                    }

                    if (model
                            .getPortfolioLocation()
                            .getPortfolio()
                            .getReceptions()
                            .get(i18)
                            .getReceptionNo()
                        != null) {

                      andCondition = " AND ";
                      if (isOpenParentheses) {
                        andCondition = " ";
                      }
                      query += " ";
                      whereValue =
                          model
                              .getPortfolioLocation()
                              .getPortfolio()
                              .getReceptions()
                              .get(i18)
                              .getReceptionNo();
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " Reception18.receptionNo = ? ";
                        counter++;
                      } else {
                        whereQuery = " Reception18.receptionNo = :Reception18receptionNo" + j + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          model
                              .getPortfolioLocation()
                              .getPortfolio()
                              .getReceptions()
                              .get(i18)
                              .getReceptionNo();
                      query +=
                          (andCondition
                              + Facade.getStatement(
                                  statement,
                                  "Reception18.receptionNo",
                                  "Reception18receptionNo" + j,
                                  mapVariable,
                                  whereQuery,
                                  whereValue,
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
                                .getPortfolioLocation()
                                .getPortfolio()
                                .getReceptions()
                                .get(i18)
                                .getChannel()
                            != null) {

                      andCondition = " AND ";
                      if (isOpenParentheses) {
                        andCondition = " ";
                      }
                      query += " ";
                      whereValue =
                          model
                              .getPortfolioLocation()
                              .getPortfolio()
                              .getReceptions()
                              .get(i18)
                              .getChannel();
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " Reception18.channel = ? ";
                        counter++;
                      } else {
                        whereQuery = " Reception18.channel = :Reception18channel" + j + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          model
                              .getPortfolioLocation()
                              .getPortfolio()
                              .getReceptions()
                              .get(i18)
                              .getChannel();
                      query +=
                          (andCondition
                              + Facade.getStatement(
                                  statement,
                                  "Reception18.channel",
                                  "Reception18channel" + j,
                                  mapVariable,
                                  whereQuery,
                                  whereValue,
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
                                .getPortfolioLocation()
                                .getPortfolio()
                                .getReceptions()
                                .get(i18)
                                .getExternalResource()
                            != null) {

                      andCondition = " AND ";
                      if (isOpenParentheses) {
                        andCondition = " ";
                      }
                      query += " ";
                      whereValue =
                          model
                              .getPortfolioLocation()
                              .getPortfolio()
                              .getReceptions()
                              .get(i18)
                              .getExternalResource();
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " Reception18.externalResource = ? ";
                        counter++;
                      } else {
                        whereQuery =
                            " Reception18.externalResource = :Reception18externalResource"
                                + j
                                + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          model
                              .getPortfolioLocation()
                              .getPortfolio()
                              .getReceptions()
                              .get(i18)
                              .getExternalResource();
                      query +=
                          (andCondition
                              + Facade.getStatement(
                                  statement,
                                  "Reception18.externalResource",
                                  "Reception18externalResource" + j,
                                  mapVariable,
                                  whereQuery,
                                  whereValue,
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
                                .getPortfolioLocation()
                                .getPortfolio()
                                .getReceptions()
                                .get(i18)
                                .getReceptionTitle()
                            != null) {

                      andCondition = " AND ";
                      if (isOpenParentheses) {
                        andCondition = " ";
                      }
                      query += " ";

                      whereValue =
                          model
                                  .getPortfolioLocation()
                                  .getPortfolio()
                                  .getReceptions()
                                  .get(i18)
                                  .getReceptionTitle()
                              + "%";
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " Reception18.receptionTitle = ? ";
                        counter++;
                      } else {
                        whereQuery =
                            " Reception18.receptionTitle LIKE :Reception18receptionTitle" + j + " ";
                      }

                      isOpenParentheses = false;
                      if (isExistRecord || isDelete) {
                        if (isDelete && fetchType == FetchType.EAGER) {
                          whereQuery = " Reception18.receptionTitle = ? ";
                          counter++;
                        } else {
                          whereValue =
                              model
                                  .getPortfolioLocation()
                                  .getPortfolio()
                                  .getReceptions()
                                  .get(i18)
                                  .getReceptionTitle();
                        }
                      } else {
                        whereValue =
                            model
                                    .getPortfolioLocation()
                                    .getPortfolio()
                                    .getReceptions()
                                    .get(i18)
                                    .getReceptionTitle()
                                + "%";
                      }

                      query +=
                          (andCondition
                              + Facade.getStatement(
                                  statement,
                                  "Reception18.receptionTitle",
                                  "Reception18receptionTitle" + j,
                                  mapVariable,
                                  whereQuery,
                                  whereValue,
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
                                .getPortfolioLocation()
                                .getPortfolio()
                                .getReceptions()
                                .get(i18)
                                .getProductType()
                            != null) {

                      andCondition = " AND ";
                      if (isOpenParentheses) {
                        andCondition = " ";
                      }
                      query += " ";
                      whereValue =
                          model
                              .getPortfolioLocation()
                              .getPortfolio()
                              .getReceptions()
                              .get(i18)
                              .getProductType();
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " Reception18.productType = ? ";
                        counter++;
                      } else {
                        whereQuery = " Reception18.productType = :Reception18productType" + j + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          model
                              .getPortfolioLocation()
                              .getPortfolio()
                              .getReceptions()
                              .get(i18)
                              .getProductType();
                      query +=
                          (andCondition
                              + Facade.getStatement(
                                  statement,
                                  "Reception18.productType",
                                  "Reception18productType" + j,
                                  mapVariable,
                                  whereQuery,
                                  whereValue,
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
                                .getPortfolioLocation()
                                .getPortfolio()
                                .getReceptions()
                                .get(i18)
                                .getLongLoanType()
                            != null) {

                      andCondition = " AND ";
                      if (isOpenParentheses) {
                        andCondition = " ";
                      }
                      query += " ";
                      whereValue =
                          model
                              .getPortfolioLocation()
                              .getPortfolio()
                              .getReceptions()
                              .get(i18)
                              .getLongLoanType();
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " Reception18.longLoanType = ? ";
                        counter++;
                      } else {
                        whereQuery =
                            " Reception18.longLoanType = :Reception18longLoanType" + j + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          model
                              .getPortfolioLocation()
                              .getPortfolio()
                              .getReceptions()
                              .get(i18)
                              .getLongLoanType();
                      query +=
                          (andCondition
                              + Facade.getStatement(
                                  statement,
                                  "Reception18.longLoanType",
                                  "Reception18longLoanType" + j,
                                  mapVariable,
                                  whereQuery,
                                  whereValue,
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
                                .getPortfolioLocation()
                                .getPortfolio()
                                .getReceptions()
                                .get(i18)
                                .getFinancingPurpose()
                            != null) {

                      andCondition = " AND ";
                      if (isOpenParentheses) {
                        andCondition = " ";
                      }
                      query += " ";
                      whereValue =
                          model
                              .getPortfolioLocation()
                              .getPortfolio()
                              .getReceptions()
                              .get(i18)
                              .getFinancingPurpose();
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " Reception18.financingPurpose = ? ";
                        counter++;
                      } else {
                        whereQuery =
                            " Reception18.financingPurpose = :Reception18financingPurpose"
                                + j
                                + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          model
                              .getPortfolioLocation()
                              .getPortfolio()
                              .getReceptions()
                              .get(i18)
                              .getFinancingPurpose();
                      query +=
                          (andCondition
                              + Facade.getStatement(
                                  statement,
                                  "Reception18.financingPurpose",
                                  "Reception18financingPurpose" + j,
                                  mapVariable,
                                  whereQuery,
                                  whereValue,
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
                                .getPortfolioLocation()
                                .getPortfolio()
                                .getReceptions()
                                .get(i18)
                                .getLow141()
                            != null) {

                      andCondition = " AND ";
                      if (isOpenParentheses) {
                        andCondition = " ";
                      }
                      query += " ";
                      whereValue =
                          model
                              .getPortfolioLocation()
                              .getPortfolio()
                              .getReceptions()
                              .get(i18)
                              .getLow141();
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " Reception18.low141 = ? ";
                        counter++;
                      } else {
                        whereQuery = " Reception18.low141 = :Reception18low141" + j + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          model
                              .getPortfolioLocation()
                              .getPortfolio()
                              .getReceptions()
                              .get(i18)
                              .getLow141();
                      query +=
                          (andCondition
                              + Facade.getStatement(
                                  statement,
                                  "Reception18.low141",
                                  "Reception18low141" + j,
                                  mapVariable,
                                  whereQuery,
                                  whereValue,
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
                                .getPortfolioLocation()
                                .getPortfolio()
                                .getReceptions()
                                .get(i18)
                                .getShortTermLoanType()
                            != null) {

                      andCondition = " AND ";
                      if (isOpenParentheses) {
                        andCondition = " ";
                      }
                      query += " ";
                      whereValue =
                          model
                              .getPortfolioLocation()
                              .getPortfolio()
                              .getReceptions()
                              .get(i18)
                              .getShortTermLoanType();
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " Reception18.shortTermLoanType = ? ";
                        counter++;
                      } else {
                        whereQuery =
                            " Reception18.shortTermLoanType = :Reception18shortTermLoanType"
                                + j
                                + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          model
                              .getPortfolioLocation()
                              .getPortfolio()
                              .getReceptions()
                              .get(i18)
                              .getShortTermLoanType();
                      query +=
                          (andCondition
                              + Facade.getStatement(
                                  statement,
                                  "Reception18.shortTermLoanType",
                                  "Reception18shortTermLoanType" + j,
                                  mapVariable,
                                  whereQuery,
                                  whereValue,
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
                                .getPortfolioLocation()
                                .getPortfolio()
                                .getReceptions()
                                .get(i18)
                                .getDate()
                            != null) {

                      andCondition = " AND ";
                      if (isOpenParentheses) {
                        andCondition = " ";
                      }
                      query += " ";
                      whereValue =
                          model
                              .getPortfolioLocation()
                              .getPortfolio()
                              .getReceptions()
                              .get(i18)
                              .getDate();
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " Reception18.date1 = ? ";
                        counter++;
                      } else {
                        whereQuery = " Reception18.date = :Reception18date" + j + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          model
                              .getPortfolioLocation()
                              .getPortfolio()
                              .getReceptions()
                              .get(i18)
                              .getDate();
                      query +=
                          (andCondition
                              + Facade.getStatement(
                                  statement,
                                  "Reception18.date",
                                  "Reception18date" + j,
                                  mapVariable,
                                  whereQuery,
                                  whereValue,
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
                                .getPortfolioLocation()
                                .getPortfolio()
                                .getReceptions()
                                .get(i18)
                                .getRejLetterDate()
                            != null) {

                      andCondition = " AND ";
                      if (isOpenParentheses) {
                        andCondition = " ";
                      }
                      query += " ";
                      whereValue =
                          model
                              .getPortfolioLocation()
                              .getPortfolio()
                              .getReceptions()
                              .get(i18)
                              .getRejLetterDate();
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " Reception18.rejLetterDate = ? ";
                        counter++;
                      } else {
                        whereQuery =
                            " Reception18.rejLetterDate = :Reception18rejLetterDate" + j + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          model
                              .getPortfolioLocation()
                              .getPortfolio()
                              .getReceptions()
                              .get(i18)
                              .getRejLetterDate();
                      query +=
                          (andCondition
                              + Facade.getStatement(
                                  statement,
                                  "Reception18.rejLetterDate",
                                  "Reception18rejLetterDate" + j,
                                  mapVariable,
                                  whereQuery,
                                  whereValue,
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
                                .getPortfolioLocation()
                                .getPortfolio()
                                .getReceptions()
                                .get(i18)
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
                                  .getPortfolioLocation()
                                  .getPortfolio()
                                  .getReceptions()
                                  .get(i18)
                                  .getCustomerCode();
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " Reception18.customerCode = ? ";
                        counter++;
                      } else {
                        whereQuery =
                            " Reception18.customerCode LIKE :Reception18customerCode" + j + " ";
                      }

                      isOpenParentheses = false;
                      if (isExistRecord || isDelete) {
                        if (isDelete && fetchType == FetchType.EAGER) {
                          whereQuery = " Reception18.customerCode = ? ";
                          counter++;
                        } else {
                          whereValue =
                              model
                                  .getPortfolioLocation()
                                  .getPortfolio()
                                  .getReceptions()
                                  .get(i18)
                                  .getCustomerCode();
                        }
                      } else {
                        whereValue =
                            "%"
                                + model
                                    .getPortfolioLocation()
                                    .getPortfolio()
                                    .getReceptions()
                                    .get(i18)
                                    .getCustomerCode();
                      }

                      query +=
                          (andCondition
                              + Facade.getStatement(
                                  statement,
                                  "Reception18.customerCode",
                                  "Reception18customerCode" + j,
                                  mapVariable,
                                  whereQuery,
                                  whereValue,
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
                                .getPortfolioLocation()
                                .getPortfolio()
                                .getReceptions()
                                .get(i18)
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
                                  .getPortfolioLocation()
                                  .getPortfolio()
                                  .getReceptions()
                                  .get(i18)
                                  .getBranch()
                              + "%";
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " Reception18.branch = ? ";
                        counter++;
                      } else {
                        whereQuery = " Reception18.branch LIKE :Reception18branch" + j + " ";
                      }

                      isOpenParentheses = false;
                      if (isExistRecord || isDelete) {
                        if (isDelete && fetchType == FetchType.EAGER) {
                          whereQuery = " Reception18.branch = ? ";
                          counter++;
                        } else {
                          whereValue =
                              model
                                  .getPortfolioLocation()
                                  .getPortfolio()
                                  .getReceptions()
                                  .get(i18)
                                  .getBranch();
                        }
                      } else {
                        whereValue =
                            "%"
                                + model
                                    .getPortfolioLocation()
                                    .getPortfolio()
                                    .getReceptions()
                                    .get(i18)
                                    .getBranch()
                                + "%";
                      }

                      query +=
                          (andCondition
                              + Facade.getStatement(
                                  statement,
                                  "Reception18.branch",
                                  "Reception18branch" + j,
                                  mapVariable,
                                  whereQuery,
                                  whereValue,
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
                                .getPortfolioLocation()
                                .getPortfolio()
                                .getReceptions()
                                .get(i18)
                                .getReceptionState()
                            != null) {

                      andCondition = " AND ";
                      if (isOpenParentheses) {
                        andCondition = " ";
                      }
                      query += " ";
                      whereValue =
                          model
                              .getPortfolioLocation()
                              .getPortfolio()
                              .getReceptions()
                              .get(i18)
                              .getReceptionState();
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " Reception18.receptionState = ? ";
                        counter++;
                      } else {
                        whereQuery =
                            " Reception18.receptionState = :Reception18receptionState" + j + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          model
                              .getPortfolioLocation()
                              .getPortfolio()
                              .getReceptions()
                              .get(i18)
                              .getReceptionState();
                      query +=
                          (andCondition
                              + Facade.getStatement(
                                  statement,
                                  "Reception18.receptionState",
                                  "Reception18receptionState" + j,
                                  mapVariable,
                                  whereQuery,
                                  whereValue,
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
                                .getPortfolioLocation()
                                .getPortfolio()
                                .getReceptions()
                                .get(i18)
                                .getRepresentativeRefType()
                            != null) {

                      andCondition = " AND ";
                      if (isOpenParentheses) {
                        andCondition = " ";
                      }
                      query += " ";
                      whereValue =
                          model
                              .getPortfolioLocation()
                              .getPortfolio()
                              .getReceptions()
                              .get(i18)
                              .getRepresentativeRefType();
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " Reception18.representativeRefType = ? ";
                        counter++;
                      } else {
                        whereQuery =
                            " Reception18.representativeRefType = :Reception18representativeRefType"
                                + j
                                + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          model
                              .getPortfolioLocation()
                              .getPortfolio()
                              .getReceptions()
                              .get(i18)
                              .getRepresentativeRefType();
                      query +=
                          (andCondition
                              + Facade.getStatement(
                                  statement,
                                  "Reception18.representativeRefType",
                                  "Reception18representativeRefType" + j,
                                  mapVariable,
                                  whereQuery,
                                  whereValue,
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
                                .getPortfolioLocation()
                                .getPortfolio()
                                .getReceptions()
                                .get(i18)
                                .getApplicantCreditType()
                            != null) {

                      andCondition = " AND ";
                      if (isOpenParentheses) {
                        andCondition = " ";
                      }
                      query += " ";
                      whereValue =
                          model
                              .getPortfolioLocation()
                              .getPortfolio()
                              .getReceptions()
                              .get(i18)
                              .getApplicantCreditType();
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " Reception18.applicantCreditType = ? ";
                        counter++;
                      } else {
                        whereQuery =
                            " Reception18.applicantCreditType = :Reception18applicantCreditType"
                                + j
                                + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          model
                              .getPortfolioLocation()
                              .getPortfolio()
                              .getReceptions()
                              .get(i18)
                              .getApplicantCreditType();
                      query +=
                          (andCondition
                              + Facade.getStatement(
                                  statement,
                                  "Reception18.applicantCreditType",
                                  "Reception18applicantCreditType" + j,
                                  mapVariable,
                                  whereQuery,
                                  whereValue,
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
                                .getPortfolioLocation()
                                .getPortfolio()
                                .getReceptions()
                                .get(i18)
                                .getUnderTakeHisType()
                            != null) {

                      andCondition = " AND ";
                      if (isOpenParentheses) {
                        andCondition = " ";
                      }
                      query += " ";
                      whereValue =
                          model
                              .getPortfolioLocation()
                              .getPortfolio()
                              .getReceptions()
                              .get(i18)
                              .getUnderTakeHisType();
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " Reception18.underTakeHisType = ? ";
                        counter++;
                      } else {
                        whereQuery =
                            " Reception18.underTakeHisType = :Reception18underTakeHisType"
                                + j
                                + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          model
                              .getPortfolioLocation()
                              .getPortfolio()
                              .getReceptions()
                              .get(i18)
                              .getUnderTakeHisType();
                      query +=
                          (andCondition
                              + Facade.getStatement(
                                  statement,
                                  "Reception18.underTakeHisType",
                                  "Reception18underTakeHisType" + j,
                                  mapVariable,
                                  whereQuery,
                                  whereValue,
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
                                .getPortfolioLocation()
                                .getPortfolio()
                                .getReceptions()
                                .get(i18)
                                .getCheckRejectType()
                            != null) {

                      andCondition = " AND ";
                      if (isOpenParentheses) {
                        andCondition = " ";
                      }
                      query += " ";
                      whereValue =
                          model
                              .getPortfolioLocation()
                              .getPortfolio()
                              .getReceptions()
                              .get(i18)
                              .getCheckRejectType();
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " Reception18.checkRejectType = ? ";
                        counter++;
                      } else {
                        whereQuery =
                            " Reception18.checkRejectType = :Reception18checkRejectType" + j + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          model
                              .getPortfolioLocation()
                              .getPortfolio()
                              .getReceptions()
                              .get(i18)
                              .getCheckRejectType();
                      query +=
                          (andCondition
                              + Facade.getStatement(
                                  statement,
                                  "Reception18.checkRejectType",
                                  "Reception18checkRejectType" + j,
                                  mapVariable,
                                  whereQuery,
                                  whereValue,
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
                                .getPortfolioLocation()
                                .getPortfolio()
                                .getReceptions()
                                .get(i18)
                                .getCashFocusStatusType()
                            != null) {

                      andCondition = " AND ";
                      if (isOpenParentheses) {
                        andCondition = " ";
                      }
                      query += " ";
                      whereValue =
                          model
                              .getPortfolioLocation()
                              .getPortfolio()
                              .getReceptions()
                              .get(i18)
                              .getCashFocusStatusType();
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " Reception18.cashFocusStatusType = ? ";
                        counter++;
                      } else {
                        whereQuery =
                            " Reception18.cashFocusStatusType = :Reception18cashFocusStatusType"
                                + j
                                + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          model
                              .getPortfolioLocation()
                              .getPortfolio()
                              .getReceptions()
                              .get(i18)
                              .getCashFocusStatusType();
                      query +=
                          (andCondition
                              + Facade.getStatement(
                                  statement,
                                  "Reception18.cashFocusStatusType",
                                  "Reception18cashFocusStatusType" + j,
                                  mapVariable,
                                  whereQuery,
                                  whereValue,
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
                                .getPortfolioLocation()
                                .getPortfolio()
                                .getReceptions()
                                .get(i18)
                                .getEquation()
                            != null) {

                      andCondition = " AND ";
                      if (isOpenParentheses) {
                        andCondition = " ";
                      }
                      query += " ";
                      whereValue =
                          model
                              .getPortfolioLocation()
                              .getPortfolio()
                              .getReceptions()
                              .get(i18)
                              .getEquation();
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " Reception18.equation = ? ";
                        counter++;
                      } else {
                        whereQuery = " Reception18.equation = :Reception18equation" + j + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          model
                              .getPortfolioLocation()
                              .getPortfolio()
                              .getReceptions()
                              .get(i18)
                              .getEquation();
                      query +=
                          (andCondition
                              + Facade.getStatement(
                                  statement,
                                  "Reception18.equation",
                                  "Reception18equation" + j,
                                  mapVariable,
                                  whereQuery,
                                  whereValue,
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
                                .getPortfolioLocation()
                                .getPortfolio()
                                .getReceptions()
                                .get(i18)
                                .getIrrEstimate()
                            != null) {

                      andCondition = " AND ";
                      if (isOpenParentheses) {
                        andCondition = " ";
                      }
                      query += " ";
                      whereValue =
                          model
                              .getPortfolioLocation()
                              .getPortfolio()
                              .getReceptions()
                              .get(i18)
                              .getIrrEstimate();
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " Reception18.irrEstimate = ? ";
                        counter++;
                      } else {
                        whereQuery = " Reception18.irrEstimate = :Reception18irrEstimate" + j + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          model
                              .getPortfolioLocation()
                              .getPortfolio()
                              .getReceptions()
                              .get(i18)
                              .getIrrEstimate();
                      query +=
                          (andCondition
                              + Facade.getStatement(
                                  statement,
                                  "Reception18.irrEstimate",
                                  "Reception18irrEstimate" + j,
                                  mapVariable,
                                  whereQuery,
                                  whereValue,
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
                                .getPortfolioLocation()
                                .getPortfolio()
                                .getReceptions()
                                .get(i18)
                                .getHeadPoint()
                            != null) {

                      andCondition = " AND ";
                      if (isOpenParentheses) {
                        andCondition = " ";
                      }
                      query += " ";
                      whereValue =
                          model
                              .getPortfolioLocation()
                              .getPortfolio()
                              .getReceptions()
                              .get(i18)
                              .getHeadPoint();
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " Reception18.headPoint = ? ";
                        counter++;
                      } else {
                        whereQuery = " Reception18.headPoint = :Reception18headPoint" + j + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          model
                              .getPortfolioLocation()
                              .getPortfolio()
                              .getReceptions()
                              .get(i18)
                              .getHeadPoint();
                      query +=
                          (andCondition
                              + Facade.getStatement(
                                  statement,
                                  "Reception18.headPoint",
                                  "Reception18headPoint" + j,
                                  mapVariable,
                                  whereQuery,
                                  whereValue,
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
                                .getPortfolioLocation()
                                .getPortfolio()
                                .getReceptions()
                                .get(i18)
                                .getCashControl()
                            != null) {

                      andCondition = " AND ";
                      if (isOpenParentheses) {
                        andCondition = " ";
                      }
                      query += " ";
                      whereValue =
                          model
                              .getPortfolioLocation()
                              .getPortfolio()
                              .getReceptions()
                              .get(i18)
                              .getCashControl();
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " Reception18.cashControl = ? ";
                        counter++;
                      } else {
                        whereQuery = " Reception18.cashControl = :Reception18cashControl" + j + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          model
                              .getPortfolioLocation()
                              .getPortfolio()
                              .getReceptions()
                              .get(i18)
                              .getCashControl();
                      query +=
                          (andCondition
                              + Facade.getStatement(
                                  statement,
                                  "Reception18.cashControl",
                                  "Reception18cashControl" + j,
                                  mapVariable,
                                  whereQuery,
                                  whereValue,
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
                                .getPortfolioLocation()
                                .getPortfolio()
                                .getReceptions()
                                .get(i18)
                                .getExportMarketDsc()
                            != null) {

                      andCondition = " AND ";
                      if (isOpenParentheses) {
                        andCondition = " ";
                      }
                      query += " ";
                      whereValue =
                          model
                              .getPortfolioLocation()
                              .getPortfolio()
                              .getReceptions()
                              .get(i18)
                              .getExportMarketDsc();
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " Reception18.exportMarketDsc = ? ";
                        counter++;
                      } else {
                        whereQuery =
                            " Reception18.exportMarketDsc = :Reception18exportMarketDsc" + j + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          model
                              .getPortfolioLocation()
                              .getPortfolio()
                              .getReceptions()
                              .get(i18)
                              .getExportMarketDsc();
                      query +=
                          (andCondition
                              + Facade.getStatement(
                                  statement,
                                  "Reception18.exportMarketDsc",
                                  "Reception18exportMarketDsc" + j,
                                  mapVariable,
                                  whereQuery,
                                  whereValue,
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
                                .getPortfolioLocation()
                                .getPortfolio()
                                .getReceptions()
                                .get(i18)
                                .getInternalMarketDsc()
                            != null) {

                      andCondition = " AND ";
                      if (isOpenParentheses) {
                        andCondition = " ";
                      }
                      query += " ";
                      whereValue =
                          model
                              .getPortfolioLocation()
                              .getPortfolio()
                              .getReceptions()
                              .get(i18)
                              .getInternalMarketDsc();
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " Reception18.internalMarketDsc = ? ";
                        counter++;
                      } else {
                        whereQuery =
                            " Reception18.internalMarketDsc = :Reception18internalMarketDsc"
                                + j
                                + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          model
                              .getPortfolioLocation()
                              .getPortfolio()
                              .getReceptions()
                              .get(i18)
                              .getInternalMarketDsc();
                      query +=
                          (andCondition
                              + Facade.getStatement(
                                  statement,
                                  "Reception18.internalMarketDsc",
                                  "Reception18internalMarketDsc" + j,
                                  mapVariable,
                                  whereQuery,
                                  whereValue,
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
                                .getPortfolioLocation()
                                .getPortfolio()
                                .getReceptions()
                                .get(i18)
                                .getRowMaterialDsc()
                            != null) {

                      andCondition = " AND ";
                      if (isOpenParentheses) {
                        andCondition = " ";
                      }
                      query += " ";
                      whereValue =
                          model
                              .getPortfolioLocation()
                              .getPortfolio()
                              .getReceptions()
                              .get(i18)
                              .getRowMaterialDsc();
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " Reception18.rowMaterialDsc = ? ";
                        counter++;
                      } else {
                        whereQuery =
                            " Reception18.rowMaterialDsc = :Reception18rowMaterialDsc" + j + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          model
                              .getPortfolioLocation()
                              .getPortfolio()
                              .getReceptions()
                              .get(i18)
                              .getRowMaterialDsc();
                      query +=
                          (andCondition
                              + Facade.getStatement(
                                  statement,
                                  "Reception18.rowMaterialDsc",
                                  "Reception18rowMaterialDsc" + j,
                                  mapVariable,
                                  whereQuery,
                                  whereValue,
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
                                .getPortfolioLocation()
                                .getPortfolio()
                                .getReceptions()
                                .get(i18)
                                .getMarketProductDesc()
                            != null) {

                      andCondition = " AND ";
                      if (isOpenParentheses) {
                        andCondition = " ";
                      }
                      query += " ";
                      whereValue =
                          model
                              .getPortfolioLocation()
                              .getPortfolio()
                              .getReceptions()
                              .get(i18)
                              .getMarketProductDesc();
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " Reception18.marketProductDesc = ? ";
                        counter++;
                      } else {
                        whereQuery =
                            " Reception18.marketProductDesc = :Reception18marketProductDesc"
                                + j
                                + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          model
                              .getPortfolioLocation()
                              .getPortfolio()
                              .getReceptions()
                              .get(i18)
                              .getMarketProductDesc();
                      query +=
                          (andCondition
                              + Facade.getStatement(
                                  statement,
                                  "Reception18.marketProductDesc",
                                  "Reception18marketProductDesc" + j,
                                  mapVariable,
                                  whereQuery,
                                  whereValue,
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
                                .getPortfolioLocation()
                                .getPortfolio()
                                .getReceptions()
                                .get(i18)
                                .getProductiveEmployee()
                            != null) {

                      andCondition = " AND ";
                      if (isOpenParentheses) {
                        andCondition = " ";
                      }
                      query += " ";
                      whereValue =
                          model
                              .getPortfolioLocation()
                              .getPortfolio()
                              .getReceptions()
                              .get(i18)
                              .getProductiveEmployee();
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " Reception18.productiveEmployee = ? ";
                        counter++;
                      } else {
                        whereQuery =
                            " Reception18.productiveEmployee = :Reception18productiveEmployee"
                                + j
                                + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          model
                              .getPortfolioLocation()
                              .getPortfolio()
                              .getReceptions()
                              .get(i18)
                              .getProductiveEmployee();
                      query +=
                          (andCondition
                              + Facade.getStatement(
                                  statement,
                                  "Reception18.productiveEmployee",
                                  "Reception18productiveEmployee" + j,
                                  mapVariable,
                                  whereQuery,
                                  whereValue,
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
                                .getPortfolioLocation()
                                .getPortfolio()
                                .getReceptions()
                                .get(i18)
                                .getNonProductiveEmployee()
                            != null) {

                      andCondition = " AND ";
                      if (isOpenParentheses) {
                        andCondition = " ";
                      }
                      query += " ";
                      whereValue =
                          model
                              .getPortfolioLocation()
                              .getPortfolio()
                              .getReceptions()
                              .get(i18)
                              .getNonProductiveEmployee();
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " Reception18.nonProductiveEmployee = ? ";
                        counter++;
                      } else {
                        whereQuery =
                            " Reception18.nonProductiveEmployee = :Reception18nonProductiveEmployee"
                                + j
                                + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          model
                              .getPortfolioLocation()
                              .getPortfolio()
                              .getReceptions()
                              .get(i18)
                              .getNonProductiveEmployee();
                      query +=
                          (andCondition
                              + Facade.getStatement(
                                  statement,
                                  "Reception18.nonProductiveEmployee",
                                  "Reception18nonProductiveEmployee" + j,
                                  mapVariable,
                                  whereQuery,
                                  whereValue,
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
                                .getPortfolioLocation()
                                .getPortfolio()
                                .getReceptions()
                                .get(i18)
                                .getForecastProductiveEmployee()
                            != null) {

                      andCondition = " AND ";
                      if (isOpenParentheses) {
                        andCondition = " ";
                      }
                      query += " ";
                      whereValue =
                          model
                              .getPortfolioLocation()
                              .getPortfolio()
                              .getReceptions()
                              .get(i18)
                              .getForecastProductiveEmployee();
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " Reception18.forecastProductiveEmployee = ? ";
                        counter++;
                      } else {
                        whereQuery =
                            " Reception18.forecastProductiveEmployee = :Reception18forecastProductiveEmployee"
                                + j
                                + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          model
                              .getPortfolioLocation()
                              .getPortfolio()
                              .getReceptions()
                              .get(i18)
                              .getForecastProductiveEmployee();
                      query +=
                          (andCondition
                              + Facade.getStatement(
                                  statement,
                                  "Reception18.forecastProductiveEmployee",
                                  "Reception18forecastProductiveEmployee" + j,
                                  mapVariable,
                                  whereQuery,
                                  whereValue,
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
                                .getPortfolioLocation()
                                .getPortfolio()
                                .getReceptions()
                                .get(i18)
                                .getForecastNonProductiveEmployee()
                            != null) {

                      andCondition = " AND ";
                      if (isOpenParentheses) {
                        andCondition = " ";
                      }
                      query += " ";
                      whereValue =
                          model
                              .getPortfolioLocation()
                              .getPortfolio()
                              .getReceptions()
                              .get(i18)
                              .getForecastNonProductiveEmployee();
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " Reception18.forecastNonProductiveEmployee = ? ";
                        counter++;
                      } else {
                        whereQuery =
                            " Reception18.forecastNonProductiveEmployee = :Reception18forecastNonProductiveEmployee"
                                + j
                                + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          model
                              .getPortfolioLocation()
                              .getPortfolio()
                              .getReceptions()
                              .get(i18)
                              .getForecastNonProductiveEmployee();
                      query +=
                          (andCondition
                              + Facade.getStatement(
                                  statement,
                                  "Reception18.forecastNonProductiveEmployee",
                                  "Reception18forecastNonProductiveEmployee" + j,
                                  mapVariable,
                                  whereQuery,
                                  whereValue,
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
                                .getPortfolioLocation()
                                .getPortfolio()
                                .getReceptions()
                                .get(i18)
                                .getCusPrePayAmnt()
                            != null) {

                      andCondition = " AND ";
                      if (isOpenParentheses) {
                        andCondition = " ";
                      }
                      query += " ";
                      whereValue =
                          model
                              .getPortfolioLocation()
                              .getPortfolio()
                              .getReceptions()
                              .get(i18)
                              .getCusPrePayAmnt();
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " Reception18.cusPrePayAmnt = ? ";
                        counter++;
                      } else {
                        whereQuery =
                            " Reception18.cusPrePayAmnt = :Reception18cusPrePayAmnt" + j + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          model
                              .getPortfolioLocation()
                              .getPortfolio()
                              .getReceptions()
                              .get(i18)
                              .getCusPrePayAmnt();
                      query +=
                          (andCondition
                              + Facade.getStatement(
                                  statement,
                                  "Reception18.cusPrePayAmnt",
                                  "Reception18cusPrePayAmnt" + j,
                                  mapVariable,
                                  whereQuery,
                                  whereValue,
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
                                .getPortfolioLocation()
                                .getPortfolio()
                                .getReceptions()
                                .get(i18)
                                .getFinancialUser()
                            != null) {

                      andCondition = " AND ";
                      if (isOpenParentheses) {
                        andCondition = " ";
                      }
                      query += " ";
                      whereValue =
                          model
                              .getPortfolioLocation()
                              .getPortfolio()
                              .getReceptions()
                              .get(i18)
                              .getFinancialUser();
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " Reception18.financialUser = ? ";
                        counter++;
                      } else {
                        whereQuery =
                            " Reception18.financialUser = :Reception18financialUser" + j + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          model
                              .getPortfolioLocation()
                              .getPortfolio()
                              .getReceptions()
                              .get(i18)
                              .getFinancialUser();
                      query +=
                          (andCondition
                              + Facade.getStatement(
                                  statement,
                                  "Reception18.financialUser",
                                  "Reception18financialUser" + j,
                                  mapVariable,
                                  whereQuery,
                                  whereValue,
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
                                .getPortfolioLocation()
                                .getPortfolio()
                                .getReceptions()
                                .get(i18)
                                .getMarketUser()
                            != null) {

                      andCondition = " AND ";
                      if (isOpenParentheses) {
                        andCondition = " ";
                      }
                      query += " ";
                      whereValue =
                          model
                              .getPortfolioLocation()
                              .getPortfolio()
                              .getReceptions()
                              .get(i18)
                              .getMarketUser();
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " Reception18.marketUser = ? ";
                        counter++;
                      } else {
                        whereQuery = " Reception18.marketUser = :Reception18marketUser" + j + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          model
                              .getPortfolioLocation()
                              .getPortfolio()
                              .getReceptions()
                              .get(i18)
                              .getMarketUser();
                      query +=
                          (andCondition
                              + Facade.getStatement(
                                  statement,
                                  "Reception18.marketUser",
                                  "Reception18marketUser" + j,
                                  mapVariable,
                                  whereQuery,
                                  whereValue,
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
                                .getPortfolioLocation()
                                .getPortfolio()
                                .getReceptions()
                                .get(i18)
                                .getTechnicalUser()
                            != null) {

                      andCondition = " AND ";
                      if (isOpenParentheses) {
                        andCondition = " ";
                      }
                      query += " ";
                      whereValue =
                          model
                              .getPortfolioLocation()
                              .getPortfolio()
                              .getReceptions()
                              .get(i18)
                              .getTechnicalUser();
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " Reception18.technicalUser = ? ";
                        counter++;
                      } else {
                        whereQuery =
                            " Reception18.technicalUser = :Reception18technicalUser" + j + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          model
                              .getPortfolioLocation()
                              .getPortfolio()
                              .getReceptions()
                              .get(i18)
                              .getTechnicalUser();
                      query +=
                          (andCondition
                              + Facade.getStatement(
                                  statement,
                                  "Reception18.technicalUser",
                                  "Reception18technicalUser" + j,
                                  mapVariable,
                                  whereQuery,
                                  whereValue,
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
                                .getPortfolioLocation()
                                .getPortfolio()
                                .getReceptions()
                                .get(i18)
                                .getCurrentAssetReceptionNo()
                            != null) {

                      andCondition = " AND ";
                      if (isOpenParentheses) {
                        andCondition = " ";
                      }
                      query += " ";
                      whereValue =
                          model
                              .getPortfolioLocation()
                              .getPortfolio()
                              .getReceptions()
                              .get(i18)
                              .getCurrentAssetReceptionNo();
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " Reception18.currentAssetReceptionNo = ? ";
                        counter++;
                      } else {
                        whereQuery =
                            " Reception18.currentAssetReceptionNo = :Reception18currentAssetReceptionNo"
                                + j
                                + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          model
                              .getPortfolioLocation()
                              .getPortfolio()
                              .getReceptions()
                              .get(i18)
                              .getCurrentAssetReceptionNo();
                      query +=
                          (andCondition
                              + Facade.getStatement(
                                  statement,
                                  "Reception18.currentAssetReceptionNo",
                                  "Reception18currentAssetReceptionNo" + j,
                                  mapVariable,
                                  whereQuery,
                                  whereValue,
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
                                .getPortfolioLocation()
                                .getPortfolio()
                                .getReceptions()
                                .get(i18)
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
                                      .getPortfolioLocation()
                                      .getPortfolio()
                                      .getReceptions()
                                      .get(i18)
                                      .getVersion());
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " Reception18.VERSION = ? ";
                        counter++;
                      } else {
                        whereQuery = " Reception18.version = :Reception18version" + j + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          new java.lang.Integer(
                              ""
                                  + model
                                      .getPortfolioLocation()
                                      .getPortfolio()
                                      .getReceptions()
                                      .get(i18)
                                      .getVersion());
                      query +=
                          (andCondition
                              + Facade.getStatement(
                                  statement,
                                  "Reception18.version",
                                  "Reception18version" + j,
                                  mapVariable,
                                  whereQuery,
                                  whereValue,
                                  isExistRecord,
                                  isDelete,
                                  fetchType,
                                  counter));
                      whereQuery = "";
                    }
                    if (length18 > 1) {
                      query += " ) ";
                    }
                  }

                  query += ")";
                  isOpenParentheses = false;
                }
              }
              if (!isDelete && model.getPortfolioLocation().getPortfolio().getAddress() != null) {
                if (!isDelete
                    && true
                    && model.getPortfolioLocation().getPortfolio().getAddress().getAddressLine1()
                        != null) {

                  andCondition = " AND ";
                  if (isOpenParentheses) {
                    andCondition = " ";
                  }
                  query += " ";
                  if (isDelete && fetchType == FetchType.EAGER) {
                    whereQuery = " Portfolio2.addressLine1 = ? ";
                    counter++;
                  } else {
                    whereQuery =
                        " Portfolio2.address.addressLine1 = :Portfolio2addressaddressLine1"
                            + j
                            + " ";
                  }

                  isOpenParentheses = false;
                  whereValue =
                      model.getPortfolioLocation().getPortfolio().getAddress().getAddressLine1();
                  query +=
                      (andCondition
                          + Facade.getStatement(
                              statement,
                              "Portfolio2.address.addressLine1",
                              "Portfolio2addressaddressLine1" + j,
                              mapVariable,
                              whereQuery,
                              whereValue,
                              isExistRecord,
                              isDelete,
                              fetchType,
                              counter));
                  whereQuery = "";
                }
                if (!isExistRecord
                    && !isDelete
                    && true
                    && model.getPortfolioLocation().getPortfolio().getAddress().getAddressLine2()
                        != null) {

                  andCondition = " AND ";
                  if (isOpenParentheses) {
                    andCondition = " ";
                  }
                  query += " ";
                  if (isDelete && fetchType == FetchType.EAGER) {
                    whereQuery = " Portfolio2.addressLine2 = ? ";
                    counter++;
                  } else {
                    whereQuery =
                        " Portfolio2.address.addressLine2 = :Portfolio2addressaddressLine2"
                            + j
                            + " ";
                  }

                  isOpenParentheses = false;
                  whereValue =
                      model.getPortfolioLocation().getPortfolio().getAddress().getAddressLine2();
                  query +=
                      (andCondition
                          + Facade.getStatement(
                              statement,
                              "Portfolio2.address.addressLine2",
                              "Portfolio2addressaddressLine2" + j,
                              mapVariable,
                              whereQuery,
                              whereValue,
                              isExistRecord,
                              isDelete,
                              fetchType,
                              counter));
                  whereQuery = "";
                }
                if (!isExistRecord
                    && !isDelete
                    && true
                    && model.getPortfolioLocation().getPortfolio().getAddress().getCity() != null) {

                  andCondition = " AND ";
                  if (isOpenParentheses) {
                    andCondition = " ";
                  }
                  query += " ";
                  if (isDelete && fetchType == FetchType.EAGER) {
                    whereQuery = " Portfolio2.city = ? ";
                    counter++;
                  } else {
                    whereQuery = " Portfolio2.address.city = :Portfolio2addresscity" + j + " ";
                  }

                  isOpenParentheses = false;
                  whereValue = model.getPortfolioLocation().getPortfolio().getAddress().getCity();
                  query +=
                      (andCondition
                          + Facade.getStatement(
                              statement,
                              "Portfolio2.address.city",
                              "Portfolio2addresscity" + j,
                              mapVariable,
                              whereQuery,
                              whereValue,
                              isExistRecord,
                              isDelete,
                              fetchType,
                              counter));
                  whereQuery = "";
                }
                if (!isExistRecord
                    && !isDelete
                    && true
                    && model.getPortfolioLocation().getPortfolio().getAddress().getState()
                        != null) {

                  andCondition = " AND ";
                  if (isOpenParentheses) {
                    andCondition = " ";
                  }
                  query += " ";
                  if (isDelete && fetchType == FetchType.EAGER) {
                    whereQuery = " Portfolio2.state = ? ";
                    counter++;
                  } else {
                    whereQuery = " Portfolio2.address.state = :Portfolio2addressstate" + j + " ";
                  }

                  isOpenParentheses = false;
                  whereValue = model.getPortfolioLocation().getPortfolio().getAddress().getState();
                  query +=
                      (andCondition
                          + Facade.getStatement(
                              statement,
                              "Portfolio2.address.state",
                              "Portfolio2addressstate" + j,
                              mapVariable,
                              whereQuery,
                              whereValue,
                              isExistRecord,
                              isDelete,
                              fetchType,
                              counter));
                  whereQuery = "";
                }
                if (!isExistRecord
                    && !isDelete
                    && true
                    && model.getPortfolioLocation().getPortfolio().getAddress().getCountry()
                        != null) {

                  andCondition = " AND ";
                  if (isOpenParentheses) {
                    andCondition = " ";
                  }
                  query += " ";
                  if (isDelete && fetchType == FetchType.EAGER) {
                    whereQuery = " Portfolio2.country = ? ";
                    counter++;
                  } else {
                    whereQuery =
                        " Portfolio2.address.country = :Portfolio2addresscountry" + j + " ";
                  }

                  isOpenParentheses = false;
                  whereValue =
                      model.getPortfolioLocation().getPortfolio().getAddress().getCountry();
                  query +=
                      (andCondition
                          + Facade.getStatement(
                              statement,
                              "Portfolio2.address.country",
                              "Portfolio2addresscountry" + j,
                              mapVariable,
                              whereQuery,
                              whereValue,
                              isExistRecord,
                              isDelete,
                              fetchType,
                              counter));
                  whereQuery = "";
                }
                if (!isExistRecord
                    && !isDelete
                    && true
                    && model.getPortfolioLocation().getPortfolio().getAddress().getZipCode()
                        != null) {

                  andCondition = " AND ";
                  if (isOpenParentheses) {
                    andCondition = " ";
                  }
                  query += " ";
                  if (isDelete && fetchType == FetchType.EAGER) {
                    whereQuery = " Portfolio2.zipCode = ? ";
                    counter++;
                  } else {
                    whereQuery =
                        " Portfolio2.address.zipCode = :Portfolio2addresszipCode" + j + " ";
                  }

                  isOpenParentheses = false;
                  whereValue =
                      model.getPortfolioLocation().getPortfolio().getAddress().getZipCode();
                  query +=
                      (andCondition
                          + Facade.getStatement(
                              statement,
                              "Portfolio2.address.zipCode",
                              "Portfolio2addresszipCode" + j,
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
                  && model.getPortfolioLocation().getPortfolio().getCustomerId() != null) {

                andCondition = " AND ";
                if (isOpenParentheses) {
                  andCondition = " ";
                }
                query += " ";
                if (isDelete && fetchType == FetchType.EAGER) {
                  whereQuery = " Portfolio2.customerId = ? ";
                  counter++;
                } else {
                  whereQuery = " Portfolio2.customerId = :Portfolio2customerId" + j + " ";
                }

                isOpenParentheses = false;
                whereValue = model.getPortfolioLocation().getPortfolio().getCustomerId();
                query +=
                    (andCondition
                        + Facade.getStatement(
                            statement,
                            "Portfolio2.customerId",
                            "Portfolio2customerId" + j,
                            mapVariable,
                            whereQuery,
                            whereValue,
                            isExistRecord,
                            isDelete,
                            fetchType,
                            counter));
                whereQuery = "";
              }
              if (!isExistRecord
                  && !isDelete
                  && true
                  && model.getPortfolioLocation().getPortfolio().getTitle() != null) {

                andCondition = " AND ";
                if (isOpenParentheses) {
                  andCondition = " ";
                }
                query += " ";
                if (isDelete && fetchType == FetchType.EAGER) {
                  whereQuery = " Portfolio2.title = ? ";
                  counter++;
                } else {
                  whereQuery = " Portfolio2.title = :Portfolio2title" + j + " ";
                }

                isOpenParentheses = false;
                whereValue = model.getPortfolioLocation().getPortfolio().getTitle();
                query +=
                    (andCondition
                        + Facade.getStatement(
                            statement,
                            "Portfolio2.title",
                            "Portfolio2title" + j,
                            mapVariable,
                            whereQuery,
                            whereValue,
                            isExistRecord,
                            isDelete,
                            fetchType,
                            counter));
                whereQuery = "";
              }
              if (!isExistRecord
                  && !isDelete
                  && true
                  && model.getPortfolioLocation().getPortfolio().getBranchCode() != null) {

                andCondition = " AND ";
                if (isOpenParentheses) {
                  andCondition = " ";
                }
                query += " ";
                if (isDelete && fetchType == FetchType.EAGER) {
                  whereQuery = " Portfolio2.branchCode = ? ";
                  counter++;
                } else {
                  whereQuery = " Portfolio2.branchCode = :Portfolio2branchCode" + j + " ";
                }

                isOpenParentheses = false;
                whereValue = model.getPortfolioLocation().getPortfolio().getBranchCode();
                query +=
                    (andCondition
                        + Facade.getStatement(
                            statement,
                            "Portfolio2.branchCode",
                            "Portfolio2branchCode" + j,
                            mapVariable,
                            whereQuery,
                            whereValue,
                            isExistRecord,
                            isDelete,
                            fetchType,
                            counter));
                whereQuery = "";
              }
              if (!isExistRecord
                  && !isDelete
                  && true
                  && model.getPortfolioLocation().getPortfolio().getEconomicType() != null) {

                andCondition = " AND ";
                if (isOpenParentheses) {
                  andCondition = " ";
                }
                query += " ";
                if (isDelete && fetchType == FetchType.EAGER) {
                  whereQuery = " Portfolio2.economicType = ? ";
                  counter++;
                } else {
                  whereQuery = " Portfolio2.economicType = :Portfolio2economicType" + j + " ";
                }

                isOpenParentheses = false;
                whereValue = model.getPortfolioLocation().getPortfolio().getEconomicType();
                query +=
                    (andCondition
                        + Facade.getStatement(
                            statement,
                            "Portfolio2.economicType",
                            "Portfolio2economicType" + j,
                            mapVariable,
                            whereQuery,
                            whereValue,
                            isExistRecord,
                            isDelete,
                            fetchType,
                            counter));
                whereQuery = "";
              }
              if (!isExistRecord
                  && !isDelete
                  && true
                  && model.getPortfolioLocation().getPortfolio().getIndustryType() != null) {

                andCondition = " AND ";
                if (isOpenParentheses) {
                  andCondition = " ";
                }
                query += " ";
                if (isDelete && fetchType == FetchType.EAGER) {
                  whereQuery = " Portfolio2.industryType = ? ";
                  counter++;
                } else {
                  whereQuery = " Portfolio2.industryType = :Portfolio2industryType" + j + " ";
                }

                isOpenParentheses = false;
                whereValue = model.getPortfolioLocation().getPortfolio().getIndustryType();
                query +=
                    (andCondition
                        + Facade.getStatement(
                            statement,
                            "Portfolio2.industryType",
                            "Portfolio2industryType" + j,
                            mapVariable,
                            whereQuery,
                            whereValue,
                            isExistRecord,
                            isDelete,
                            fetchType,
                            counter));
                whereQuery = "";
              }
              if (!isExistRecord
                  && !isDelete
                  && true
                  && model.getPortfolioLocation().getPortfolio().getTechnologyDescription()
                      != null) {

                andCondition = " AND ";
                if (isOpenParentheses) {
                  andCondition = " ";
                }
                query += " ";
                if (isDelete && fetchType == FetchType.EAGER) {
                  whereQuery = " Portfolio2.techDescription = ? ";
                  counter++;
                } else {
                  whereQuery =
                      " Portfolio2.technologyDescription = :Portfolio2technologyDescription"
                          + j
                          + " ";
                }

                isOpenParentheses = false;
                whereValue = model.getPortfolioLocation().getPortfolio().getTechnologyDescription();
                query +=
                    (andCondition
                        + Facade.getStatement(
                            statement,
                            "Portfolio2.technologyDescription",
                            "Portfolio2technologyDescription" + j,
                            mapVariable,
                            whereQuery,
                            whereValue,
                            isExistRecord,
                            isDelete,
                            fetchType,
                            counter));
                whereQuery = "";
              }
              if (!isExistRecord
                  && !isDelete
                  && true
                  && model.getPortfolioLocation().getPortfolio().getStateType() != null) {

                andCondition = " AND ";
                if (isOpenParentheses) {
                  andCondition = " ";
                }
                query += " ";
                if (isDelete && fetchType == FetchType.EAGER) {
                  whereQuery = " Portfolio2.stateType = ? ";
                  counter++;
                } else {
                  whereQuery = " Portfolio2.stateType = :Portfolio2stateType" + j + " ";
                }

                isOpenParentheses = false;
                whereValue = model.getPortfolioLocation().getPortfolio().getStateType();
                query +=
                    (andCondition
                        + Facade.getStatement(
                            statement,
                            "Portfolio2.stateType",
                            "Portfolio2stateType" + j,
                            mapVariable,
                            whereQuery,
                            whereValue,
                            isExistRecord,
                            isDelete,
                            fetchType,
                            counter));
                whereQuery = "";
              }
              if (!isExistRecord
                  && !isDelete
                  && true
                  && model.getPortfolioLocation().getPortfolio().getLifeType() != null) {

                andCondition = " AND ";
                if (isOpenParentheses) {
                  andCondition = " ";
                }
                query += " ";
                if (isDelete && fetchType == FetchType.EAGER) {
                  whereQuery = " Portfolio2.lifeType = ? ";
                  counter++;
                } else {
                  whereQuery = " Portfolio2.lifeType = :Portfolio2lifeType" + j + " ";
                }

                isOpenParentheses = false;
                whereValue = model.getPortfolioLocation().getPortfolio().getLifeType();
                query +=
                    (andCondition
                        + Facade.getStatement(
                            statement,
                            "Portfolio2.lifeType",
                            "Portfolio2lifeType" + j,
                            mapVariable,
                            whereQuery,
                            whereValue,
                            isExistRecord,
                            isDelete,
                            fetchType,
                            counter));
                whereQuery = "";
              }
              if (!isExistRecord
                  && !isDelete
                  && true
                  && model.getPortfolioLocation().getPortfolio().getStartDate() != null) {

                andCondition = " AND ";
                if (isOpenParentheses) {
                  andCondition = " ";
                }
                query += " ";
                if (isDelete && fetchType == FetchType.EAGER) {
                  whereQuery = " Portfolio2.startDate = ? ";
                  counter++;
                } else {
                  whereQuery = " Portfolio2.startDate = :Portfolio2startDate" + j + " ";
                }

                isOpenParentheses = false;
                whereValue = model.getPortfolioLocation().getPortfolio().getStartDate();
                query +=
                    (andCondition
                        + Facade.getStatement(
                            statement,
                            "Portfolio2.startDate",
                            "Portfolio2startDate" + j,
                            mapVariable,
                            whereQuery,
                            whereValue,
                            isExistRecord,
                            isDelete,
                            fetchType,
                            counter));
                whereQuery = "";
              }
              if (!isExistRecord
                  && !isDelete
                  && true
                  && model.getPortfolioLocation().getPortfolio().getEndDate() != null) {

                andCondition = " AND ";
                if (isOpenParentheses) {
                  andCondition = " ";
                }
                query += " ";
                if (isDelete && fetchType == FetchType.EAGER) {
                  whereQuery = " Portfolio2.endDate = ? ";
                  counter++;
                } else {
                  whereQuery = " Portfolio2.endDate = :Portfolio2endDate" + j + " ";
                }

                isOpenParentheses = false;
                whereValue = model.getPortfolioLocation().getPortfolio().getEndDate();
                query +=
                    (andCondition
                        + Facade.getStatement(
                            statement,
                            "Portfolio2.endDate",
                            "Portfolio2endDate" + j,
                            mapVariable,
                            whereQuery,
                            whereValue,
                            isExistRecord,
                            isDelete,
                            fetchType,
                            counter));
                whereQuery = "";
              }
              if (!isExistRecord
                  && !isDelete
                  && true
                  && model.getPortfolioLocation().getPortfolio().getRequestDate() != null) {

                andCondition = " AND ";
                if (isOpenParentheses) {
                  andCondition = " ";
                }
                query += " ";
                if (isDelete && fetchType == FetchType.EAGER) {
                  whereQuery = " Portfolio2.requestDate = ? ";
                  counter++;
                } else {
                  whereQuery = " Portfolio2.requestDate = :Portfolio2requestDate" + j + " ";
                }

                isOpenParentheses = false;
                whereValue = model.getPortfolioLocation().getPortfolio().getRequestDate();
                query +=
                    (andCondition
                        + Facade.getStatement(
                            statement,
                            "Portfolio2.requestDate",
                            "Portfolio2requestDate" + j,
                            mapVariable,
                            whereQuery,
                            whereValue,
                            isExistRecord,
                            isDelete,
                            fetchType,
                            counter));
                whereQuery = "";
              }
              if (!isExistRecord
                  && !isDelete
                  && true
                  && model.getPortfolioLocation().getPortfolio().getCapitalAMT() != null) {

                andCondition = " AND ";
                if (isOpenParentheses) {
                  andCondition = " ";
                }
                query += " ";
                if (isDelete && fetchType == FetchType.EAGER) {
                  whereQuery = " Portfolio2.capitalAMT = ? ";
                  counter++;
                } else {
                  whereQuery = " Portfolio2.capitalAMT = :Portfolio2capitalAMT" + j + " ";
                }

                isOpenParentheses = false;
                whereValue = model.getPortfolioLocation().getPortfolio().getCapitalAMT();
                query +=
                    (andCondition
                        + Facade.getStatement(
                            statement,
                            "Portfolio2.capitalAMT",
                            "Portfolio2capitalAMT" + j,
                            mapVariable,
                            whereQuery,
                            whereValue,
                            isExistRecord,
                            isDelete,
                            fetchType,
                            counter));
                whereQuery = "";
              }
              if (!isExistRecord
                  && !isDelete
                  && true
                  && model.getPortfolioLocation().getPortfolio().getInvestableAMT() != null) {

                andCondition = " AND ";
                if (isOpenParentheses) {
                  andCondition = " ";
                }
                query += " ";
                if (isDelete && fetchType == FetchType.EAGER) {
                  whereQuery = " Portfolio2.investableAMT = ? ";
                  counter++;
                } else {
                  whereQuery = " Portfolio2.investableAMT = :Portfolio2investableAMT" + j + " ";
                }

                isOpenParentheses = false;
                whereValue = model.getPortfolioLocation().getPortfolio().getInvestableAMT();
                query +=
                    (andCondition
                        + Facade.getStatement(
                            statement,
                            "Portfolio2.investableAMT",
                            "Portfolio2investableAMT" + j,
                            mapVariable,
                            whereQuery,
                            whereValue,
                            isExistRecord,
                            isDelete,
                            fetchType,
                            counter));
                whereQuery = "";
              }
              if (!isExistRecord
                  && !isDelete
                  && true
                  && model.getPortfolioLocation().getPortfolio().getAssessdAMT() != null) {

                andCondition = " AND ";
                if (isOpenParentheses) {
                  andCondition = " ";
                }
                query += " ";
                if (isDelete && fetchType == FetchType.EAGER) {
                  whereQuery = " Portfolio2.assessdAMT = ? ";
                  counter++;
                } else {
                  whereQuery = " Portfolio2.assessdAMT = :Portfolio2assessdAMT" + j + " ";
                }

                isOpenParentheses = false;
                whereValue = model.getPortfolioLocation().getPortfolio().getAssessdAMT();
                query +=
                    (andCondition
                        + Facade.getStatement(
                            statement,
                            "Portfolio2.assessdAMT",
                            "Portfolio2assessdAMT" + j,
                            mapVariable,
                            whereQuery,
                            whereValue,
                            isExistRecord,
                            isDelete,
                            fetchType,
                            counter));
                whereQuery = "";
              }
              if (!isExistRecord
                  && !isDelete
                  && true
                  && model.getPortfolioLocation().getPortfolio().getBankAPPRInvestAMT() != null) {

                andCondition = " AND ";
                if (isOpenParentheses) {
                  andCondition = " ";
                }
                query += " ";
                if (isDelete && fetchType == FetchType.EAGER) {
                  whereQuery = " Portfolio2.bankAPPRInvestAMT = ? ";
                  counter++;
                } else {
                  whereQuery =
                      " Portfolio2.bankAPPRInvestAMT = :Portfolio2bankAPPRInvestAMT" + j + " ";
                }

                isOpenParentheses = false;
                whereValue = model.getPortfolioLocation().getPortfolio().getBankAPPRInvestAMT();
                query +=
                    (andCondition
                        + Facade.getStatement(
                            statement,
                            "Portfolio2.bankAPPRInvestAMT",
                            "Portfolio2bankAPPRInvestAMT" + j,
                            mapVariable,
                            whereQuery,
                            whereValue,
                            isExistRecord,
                            isDelete,
                            fetchType,
                            counter));
                whereQuery = "";
              }
              if (!isExistRecord
                  && !isDelete
                  && true
                  && model.getPortfolioLocation().getPortfolio().getReagent() != null) {

                andCondition = " AND ";
                if (isOpenParentheses) {
                  andCondition = " ";
                }
                query += " ";
                if (isDelete && fetchType == FetchType.EAGER) {
                  whereQuery = " Portfolio2.reagent = ? ";
                  counter++;
                } else {
                  whereQuery = " Portfolio2.reagent = :Portfolio2reagent" + j + " ";
                }

                isOpenParentheses = false;
                whereValue = model.getPortfolioLocation().getPortfolio().getReagent();
                query +=
                    (andCondition
                        + Facade.getStatement(
                            statement,
                            "Portfolio2.reagent",
                            "Portfolio2reagent" + j,
                            mapVariable,
                            whereQuery,
                            whereValue,
                            isExistRecord,
                            isDelete,
                            fetchType,
                            counter));
                whereQuery = "";
              }
              if (!isExistRecord
                  && !isDelete
                  && true
                  && model.getPortfolioLocation().getPortfolio().getBusinessId() != null) {

                andCondition = " AND ";
                if (isOpenParentheses) {
                  andCondition = " ";
                }
                query += " ";
                if (isDelete && fetchType == FetchType.EAGER) {
                  whereQuery = " Portfolio2.businessId = ? ";
                  counter++;
                } else {
                  whereQuery = " Portfolio2.businessId = :Portfolio2businessId" + j + " ";
                }

                isOpenParentheses = false;
                whereValue = model.getPortfolioLocation().getPortfolio().getBusinessId();
                query +=
                    (andCondition
                        + Facade.getStatement(
                            statement,
                            "Portfolio2.businessId",
                            "Portfolio2businessId" + j,
                            mapVariable,
                            whereQuery,
                            whereValue,
                            isExistRecord,
                            isDelete,
                            fetchType,
                            counter));
                whereQuery = "";
              }
              if (!isExistRecord
                  && !isDelete
                  && true
                  && model.getPortfolioLocation().getPortfolio().getVersion() != 0) {

                andCondition = " AND ";
                if (isOpenParentheses) {
                  andCondition = " ";
                }
                query += " ";
                if (isDelete && fetchType == FetchType.EAGER) {
                  whereQuery = " Portfolio2.VERSION = ? ";
                  counter++;
                } else {
                  whereQuery = " Portfolio2.version = :Portfolio2version" + j + " ";
                }

                isOpenParentheses = false;
                whereValue =
                    new java.lang.Integer(
                        "" + model.getPortfolioLocation().getPortfolio().getVersion());
                query +=
                    (andCondition
                        + Facade.getStatement(
                            statement,
                            "Portfolio2.version",
                            "Portfolio2version" + j,
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
            if (!isExistRecord && !isDelete && model.getPortfolioLocation().getFile() != null) {

              if (model.getPortfolioLocation().getFile().getId() != null) {

                andCondition = " AND ";
                if (isOpenParentheses) {
                  andCondition = " ";
                }
                query += " ";
                if (isDelete && fetchType == FetchType.EAGER) {
                  whereQuery = " File20.l4610flmtdataid = ? ";
                  counter++;
                } else {
                  whereQuery = " File20.id = :File20id" + j + " ";
                }

                isOpenParentheses = false;
                whereValue = model.getPortfolioLocation().getFile().getId();
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
                  && model.getPortfolioLocation().getFile().getExtension() != null) {

                andCondition = " AND ";
                if (isOpenParentheses) {
                  andCondition = " ";
                }
                query += " ";
                if (isDelete && fetchType == FetchType.EAGER) {
                  whereQuery = " File20.l4610extension = ? ";
                  counter++;
                } else {
                  whereQuery = " File20.extension = :File20extension" + j + " ";
                }

                isOpenParentheses = false;
                whereValue = model.getPortfolioLocation().getFile().getExtension();
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
                  && model.getPortfolioLocation().getFile().getVersion() != 0) {

                andCondition = " AND ";
                if (isOpenParentheses) {
                  andCondition = " ";
                }
                query += " ";
                if (isDelete && fetchType == FetchType.EAGER) {
                  whereQuery = " File20.VERSION = ? ";
                  counter++;
                } else {
                  whereQuery = " File20.version = :File20version" + j + " ";
                }

                isOpenParentheses = false;
                whereValue =
                    new java.lang.Integer("" + model.getPortfolioLocation().getFile().getVersion());
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
                && fetchType == FetchType.EAGER
                && model.getPortfolioLocation().getLocationDetails() != null) {
              int length21 = model.getPortfolioLocation().getLocationDetails().size();

              if (length21 > 0) {
                if (isOpenParentheses) {
                  query += "  ( ";
                } else {
                  query += " AND ( ";
                }
                isOpenParentheses = true;

                for (int i21 = 0; i21 < length21; i21++) {

                  java.lang.String beforeCondition21 = "";
                  java.lang.String prefix21 = " OR ";
                  if (i21 == 0) {
                    prefix21 = "";
                  }
                  if (length21 > 1) {

                    query += (prefix21 + "(");
                    isOpenParentheses = true;

                  } else {

                    query += (prefix21);
                    if (!prefix21.trim().equals("")) {
                      isOpenParentheses = false;
                    }
                  }

                  if (model
                          .getPortfolioLocation()
                          .getLocationDetails()
                          .get(i21)
                          .getLocationDetailId()
                      != null) {

                    andCondition = " AND ";
                    if (isOpenParentheses) {
                      andCondition = " ";
                    }
                    query += " ";
                    whereValue =
                        model
                            .getPortfolioLocation()
                            .getLocationDetails()
                            .get(i21)
                            .getLocationDetailId();
                    if (isDelete && fetchType == FetchType.EAGER) {
                      whereQuery = " LocationDetails21.id = ? ";
                      counter++;
                    } else {
                      whereQuery =
                          " LocationDetails21.locationDetailId = :LocationDetails21locationDetailId"
                              + j
                              + " ";
                    }

                    isOpenParentheses = false;
                    whereValue =
                        model
                            .getPortfolioLocation()
                            .getLocationDetails()
                            .get(i21)
                            .getLocationDetailId();
                    query +=
                        (andCondition
                            + Facade.getStatement(
                                statement,
                                "LocationDetails21.locationDetailId",
                                "LocationDetails21locationDetailId" + j,
                                mapVariable,
                                whereQuery,
                                whereValue,
                                isExistRecord,
                                isDelete,
                                fetchType,
                                counter));
                    whereQuery = "";
                  }
                  if (!isExistRecord
                      && !isDelete
                      && model.getPortfolioLocation().getLocationDetails().get(i21).getFile()
                          != null) {

                    if (model.getPortfolioLocation().getLocationDetails().get(i21).getFile().getId()
                        != null) {

                      andCondition = " AND ";
                      if (isOpenParentheses) {
                        andCondition = " ";
                      }
                      query += " ";
                      whereValue =
                          model
                              .getPortfolioLocation()
                              .getLocationDetails()
                              .get(i21)
                              .getFile()
                              .getId();
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " File23.l4610flmtdataid = ? ";
                        counter++;
                      } else {
                        whereQuery = " File23.id = :File23id" + j + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          model
                              .getPortfolioLocation()
                              .getLocationDetails()
                              .get(i21)
                              .getFile()
                              .getId();
                      query +=
                          (andCondition
                              + Facade.getStatement(
                                  statement,
                                  "File23.id",
                                  "File23id" + j,
                                  mapVariable,
                                  whereQuery,
                                  whereValue,
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
                                .getPortfolioLocation()
                                .getLocationDetails()
                                .get(i21)
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
                              .getPortfolioLocation()
                              .getLocationDetails()
                              .get(i21)
                              .getFile()
                              .getExtension();
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " File23.l4610extension = ? ";
                        counter++;
                      } else {
                        whereQuery = " File23.extension = :File23extension" + j + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          model
                              .getPortfolioLocation()
                              .getLocationDetails()
                              .get(i21)
                              .getFile()
                              .getExtension();
                      query +=
                          (andCondition
                              + Facade.getStatement(
                                  statement,
                                  "File23.extension",
                                  "File23extension" + j,
                                  mapVariable,
                                  whereQuery,
                                  whereValue,
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
                                .getPortfolioLocation()
                                .getLocationDetails()
                                .get(i21)
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
                                      .getPortfolioLocation()
                                      .getLocationDetails()
                                      .get(i21)
                                      .getFile()
                                      .getVersion());
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " File23.VERSION = ? ";
                        counter++;
                      } else {
                        whereQuery = " File23.version = :File23version" + j + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          new java.lang.Integer(
                              ""
                                  + model
                                      .getPortfolioLocation()
                                      .getLocationDetails()
                                      .get(i21)
                                      .getFile()
                                      .getVersion());
                      query +=
                          (andCondition
                              + Facade.getStatement(
                                  statement,
                                  "File23.version",
                                  "File23version" + j,
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
                      && model.getPortfolioLocation().getLocationDetails().get(i21).getAddress()
                          != null) {
                    if (!isDelete
                        && true
                        && model
                                .getPortfolioLocation()
                                .getLocationDetails()
                                .get(i21)
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
                              .getPortfolioLocation()
                              .getLocationDetails()
                              .get(i21)
                              .getAddress()
                              .getAddressLine1();
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " LocationDetails21.addressLine1 = ? ";
                        counter++;
                      } else {
                        whereQuery =
                            " LocationDetails21.address.addressLine1 = :LocationDetails21addressaddressLine1"
                                + j
                                + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          model
                              .getPortfolioLocation()
                              .getLocationDetails()
                              .get(i21)
                              .getAddress()
                              .getAddressLine1();
                      query +=
                          (andCondition
                              + Facade.getStatement(
                                  statement,
                                  "LocationDetails21.address.addressLine1",
                                  "LocationDetails21addressaddressLine1" + j,
                                  mapVariable,
                                  whereQuery,
                                  whereValue,
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
                                .getPortfolioLocation()
                                .getLocationDetails()
                                .get(i21)
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
                              .getPortfolioLocation()
                              .getLocationDetails()
                              .get(i21)
                              .getAddress()
                              .getAddressLine2();
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " LocationDetails21.addressLine2 = ? ";
                        counter++;
                      } else {
                        whereQuery =
                            " LocationDetails21.address.addressLine2 = :LocationDetails21addressaddressLine2"
                                + j
                                + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          model
                              .getPortfolioLocation()
                              .getLocationDetails()
                              .get(i21)
                              .getAddress()
                              .getAddressLine2();
                      query +=
                          (andCondition
                              + Facade.getStatement(
                                  statement,
                                  "LocationDetails21.address.addressLine2",
                                  "LocationDetails21addressaddressLine2" + j,
                                  mapVariable,
                                  whereQuery,
                                  whereValue,
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
                                .getPortfolioLocation()
                                .getLocationDetails()
                                .get(i21)
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
                              .getPortfolioLocation()
                              .getLocationDetails()
                              .get(i21)
                              .getAddress()
                              .getCity();
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " LocationDetails21.city = ? ";
                        counter++;
                      } else {
                        whereQuery =
                            " LocationDetails21.address.city = :LocationDetails21addresscity"
                                + j
                                + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          model
                              .getPortfolioLocation()
                              .getLocationDetails()
                              .get(i21)
                              .getAddress()
                              .getCity();
                      query +=
                          (andCondition
                              + Facade.getStatement(
                                  statement,
                                  "LocationDetails21.address.city",
                                  "LocationDetails21addresscity" + j,
                                  mapVariable,
                                  whereQuery,
                                  whereValue,
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
                                .getPortfolioLocation()
                                .getLocationDetails()
                                .get(i21)
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
                              .getPortfolioLocation()
                              .getLocationDetails()
                              .get(i21)
                              .getAddress()
                              .getState();
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " LocationDetails21.state = ? ";
                        counter++;
                      } else {
                        whereQuery =
                            " LocationDetails21.address.state = :LocationDetails21addressstate"
                                + j
                                + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          model
                              .getPortfolioLocation()
                              .getLocationDetails()
                              .get(i21)
                              .getAddress()
                              .getState();
                      query +=
                          (andCondition
                              + Facade.getStatement(
                                  statement,
                                  "LocationDetails21.address.state",
                                  "LocationDetails21addressstate" + j,
                                  mapVariable,
                                  whereQuery,
                                  whereValue,
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
                                .getPortfolioLocation()
                                .getLocationDetails()
                                .get(i21)
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
                              .getPortfolioLocation()
                              .getLocationDetails()
                              .get(i21)
                              .getAddress()
                              .getCountry();
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " LocationDetails21.country = ? ";
                        counter++;
                      } else {
                        whereQuery =
                            " LocationDetails21.address.country = :LocationDetails21addresscountry"
                                + j
                                + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          model
                              .getPortfolioLocation()
                              .getLocationDetails()
                              .get(i21)
                              .getAddress()
                              .getCountry();
                      query +=
                          (andCondition
                              + Facade.getStatement(
                                  statement,
                                  "LocationDetails21.address.country",
                                  "LocationDetails21addresscountry" + j,
                                  mapVariable,
                                  whereQuery,
                                  whereValue,
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
                                .getPortfolioLocation()
                                .getLocationDetails()
                                .get(i21)
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
                              .getPortfolioLocation()
                              .getLocationDetails()
                              .get(i21)
                              .getAddress()
                              .getZipCode();
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " LocationDetails21.zipCode = ? ";
                        counter++;
                      } else {
                        whereQuery =
                            " LocationDetails21.address.zipCode = :LocationDetails21addresszipCode"
                                + j
                                + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          model
                              .getPortfolioLocation()
                              .getLocationDetails()
                              .get(i21)
                              .getAddress()
                              .getZipCode();
                      query +=
                          (andCondition
                              + Facade.getStatement(
                                  statement,
                                  "LocationDetails21.address.zipCode",
                                  "LocationDetails21addresszipCode" + j,
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
                  if (length21 > 1) {
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
                && model.getPortfolioLocation().getProvince() != null) {

              andCondition = " AND ";
              if (isOpenParentheses) {
                andCondition = " ";
              }
              query += " ";
              if (isDelete && fetchType == FetchType.EAGER) {
                whereQuery = " PortfolioLocation1.province = ? ";
                counter++;
              } else {
                whereQuery = " PortfolioLocation1.province = :PortfolioLocation1province" + j + " ";
              }

              isOpenParentheses = false;
              whereValue = model.getPortfolioLocation().getProvince();
              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "PortfolioLocation1.province",
                          "PortfolioLocation1province" + j,
                          mapVariable,
                          whereQuery,
                          whereValue,
                          isExistRecord,
                          isDelete,
                          fetchType,
                          counter));
              whereQuery = "";
            }
            if (!isExistRecord
                && !isDelete
                && true
                && model.getPortfolioLocation().getCity() != null) {

              andCondition = " AND ";
              if (isOpenParentheses) {
                andCondition = " ";
              }
              query += " ";
              if (isDelete && fetchType == FetchType.EAGER) {
                whereQuery = " PortfolioLocation1.city = ? ";
                counter++;
              } else {
                whereQuery = " PortfolioLocation1.city = :PortfolioLocation1city" + j + " ";
              }

              isOpenParentheses = false;
              whereValue = model.getPortfolioLocation().getCity();
              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "PortfolioLocation1.city",
                          "PortfolioLocation1city" + j,
                          mapVariable,
                          whereQuery,
                          whereValue,
                          isExistRecord,
                          isDelete,
                          fetchType,
                          counter));
              whereQuery = "";
            }
            if (!isExistRecord
                && !isDelete
                && true
                && model.getPortfolioLocation().getIndstrltwn() != null) {

              andCondition = " AND ";
              if (isOpenParentheses) {
                andCondition = " ";
              }
              query += " ";
              if (isDelete && fetchType == FetchType.EAGER) {
                whereQuery = " PortfolioLocation1.indstrltwn = ? ";
                counter++;
              } else {
                whereQuery =
                    " PortfolioLocation1.indstrltwn = :PortfolioLocation1indstrltwn" + j + " ";
              }

              isOpenParentheses = false;
              whereValue = model.getPortfolioLocation().getIndstrltwn();
              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "PortfolioLocation1.indstrltwn",
                          "PortfolioLocation1indstrltwn" + j,
                          mapVariable,
                          whereQuery,
                          whereValue,
                          isExistRecord,
                          isDelete,
                          fetchType,
                          counter));
              whereQuery = "";
            }
            if (!isExistRecord
                && !isDelete
                && true
                && model.getPortfolioLocation().getAddress() != null) {

              andCondition = " AND ";
              if (isOpenParentheses) {
                andCondition = " ";
              }
              query += " ";
              if (isDelete && fetchType == FetchType.EAGER) {
                whereQuery = " PortfolioLocation1.address = ? ";
                counter++;
              } else {
                whereQuery = " PortfolioLocation1.address = :PortfolioLocation1address" + j + " ";
              }

              isOpenParentheses = false;
              whereValue = model.getPortfolioLocation().getAddress();
              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "PortfolioLocation1.address",
                          "PortfolioLocation1address" + j,
                          mapVariable,
                          whereQuery,
                          whereValue,
                          isExistRecord,
                          isDelete,
                          fetchType,
                          counter));
              whereQuery = "";
            }
            if (!isExistRecord
                && !isDelete
                && true
                && model.getPortfolioLocation().getStableStatusType() != null) {

              andCondition = " AND ";
              if (isOpenParentheses) {
                andCondition = " ";
              }
              query += " ";
              if (isDelete && fetchType == FetchType.EAGER) {
                whereQuery = " PortfolioLocation1.stableStatusType = ? ";
                counter++;
              } else {
                whereQuery =
                    " PortfolioLocation1.stableStatusType = :PortfolioLocation1stableStatusType"
                        + j
                        + " ";
              }

              isOpenParentheses = false;
              whereValue = model.getPortfolioLocation().getStableStatusType();
              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "PortfolioLocation1.stableStatusType",
                          "PortfolioLocation1stableStatusType" + j,
                          mapVariable,
                          whereQuery,
                          whereValue,
                          isExistRecord,
                          isDelete,
                          fetchType,
                          counter));
              whereQuery = "";
            }
            if (!isExistRecord
                && !isDelete
                && true
                && model.getPortfolioLocation().getOwnerStatusType() != null) {

              andCondition = " AND ";
              if (isOpenParentheses) {
                andCondition = " ";
              }
              query += " ";
              if (isDelete && fetchType == FetchType.EAGER) {
                whereQuery = " PortfolioLocation1.ownerStatusType = ? ";
                counter++;
              } else {
                whereQuery =
                    " PortfolioLocation1.ownerStatusType = :PortfolioLocation1ownerStatusType"
                        + j
                        + " ";
              }

              isOpenParentheses = false;
              whereValue = model.getPortfolioLocation().getOwnerStatusType();
              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "PortfolioLocation1.ownerStatusType",
                          "PortfolioLocation1ownerStatusType" + j,
                          mapVariable,
                          whereQuery,
                          whereValue,
                          isExistRecord,
                          isDelete,
                          fetchType,
                          counter));
              whereQuery = "";
            }
            if (!isExistRecord
                && !isDelete
                && true
                && model.getPortfolioLocation().getRegionalStatusType() != null) {

              andCondition = " AND ";
              if (isOpenParentheses) {
                andCondition = " ";
              }
              query += " ";
              if (isDelete && fetchType == FetchType.EAGER) {
                whereQuery = " PortfolioLocation1.regionalStatusType = ? ";
                counter++;
              } else {
                whereQuery =
                    " PortfolioLocation1.regionalStatusType = :PortfolioLocation1regionalStatusType"
                        + j
                        + " ";
              }

              isOpenParentheses = false;
              whereValue = model.getPortfolioLocation().getRegionalStatusType();
              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "PortfolioLocation1.regionalStatusType",
                          "PortfolioLocation1regionalStatusType" + j,
                          mapVariable,
                          whereQuery,
                          whereValue,
                          isExistRecord,
                          isDelete,
                          fetchType,
                          counter));
              whereQuery = "";
            }
            if (!isExistRecord
                && !isDelete
                && true
                && model.getPortfolioLocation().getVersion() != 0) {

              andCondition = " AND ";
              if (isOpenParentheses) {
                andCondition = " ";
              }
              query += " ";
              if (isDelete && fetchType == FetchType.EAGER) {
                whereQuery = " PortfolioLocation1.VERSION = ? ";
                counter++;
              } else {
                whereQuery = " PortfolioLocation1.version = :PortfolioLocation1version" + j + " ";
              }

              isOpenParentheses = false;
              whereValue = new java.lang.Integer("" + model.getPortfolioLocation().getVersion());
              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "PortfolioLocation1.version",
                          "PortfolioLocation1version" + j,
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
                whereQuery = " File24.l4610flmtdataid = ? ";
                counter++;
              } else {
                whereQuery = " File24.id = :File24id" + j + " ";
              }

              isOpenParentheses = false;
              whereValue = model.getFile().getId();
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
            if (!isExistRecord && !isDelete && true && model.getFile().getExtension() != null) {

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
              whereValue = model.getFile().getExtension();
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
            if (!isExistRecord && !isDelete && true && model.getFile().getVersion() != 0) {

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
              whereValue = new java.lang.Integer("" + model.getFile().getVersion());
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
          if (model.getAddress() != null) {
            if (true && model.getAddress().getAddressLine1() != null) {

              andCondition = " AND ";
              if (isOpenParentheses) {
                andCondition = " ";
              }
              query += " ";
              if (isDelete && fetchType == FetchType.EAGER) {
                whereQuery = " LocationDetails0.addressLine1 = ? ";
                counter++;
              } else {
                whereQuery =
                    " LocationDetails0.address.addressLine1 = :LocationDetails0addressaddressLine1"
                        + j
                        + " ";
              }

              isOpenParentheses = false;
              whereValue = model.getAddress().getAddressLine1();
              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "LocationDetails0.address.addressLine1",
                          "LocationDetails0addressaddressLine1" + j,
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
                whereQuery = " LocationDetails0.addressLine2 = ? ";
                counter++;
              } else {
                whereQuery =
                    " LocationDetails0.address.addressLine2 = :LocationDetails0addressaddressLine2"
                        + j
                        + " ";
              }

              isOpenParentheses = false;
              whereValue = model.getAddress().getAddressLine2();
              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "LocationDetails0.address.addressLine2",
                          "LocationDetails0addressaddressLine2" + j,
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
                whereQuery = " LocationDetails0.city = ? ";
                counter++;
              } else {
                whereQuery =
                    " LocationDetails0.address.city = :LocationDetails0addresscity" + j + " ";
              }

              isOpenParentheses = false;
              whereValue = model.getAddress().getCity();
              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "LocationDetails0.address.city",
                          "LocationDetails0addresscity" + j,
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
                whereQuery = " LocationDetails0.state = ? ";
                counter++;
              } else {
                whereQuery =
                    " LocationDetails0.address.state = :LocationDetails0addressstate" + j + " ";
              }

              isOpenParentheses = false;
              whereValue = model.getAddress().getState();
              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "LocationDetails0.address.state",
                          "LocationDetails0addressstate" + j,
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
                whereQuery = " LocationDetails0.country = ? ";
                counter++;
              } else {
                whereQuery =
                    " LocationDetails0.address.country = :LocationDetails0addresscountry" + j + " ";
              }

              isOpenParentheses = false;
              whereValue = model.getAddress().getCountry();
              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "LocationDetails0.address.country",
                          "LocationDetails0addresscountry" + j,
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
                whereQuery = " LocationDetails0.zipCode = ? ";
                counter++;
              } else {
                whereQuery =
                    " LocationDetails0.address.zipCode = :LocationDetails0addresszipCode" + j + " ";
              }

              isOpenParentheses = false;
              whereValue = model.getAddress().getZipCode();
              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "LocationDetails0.address.zipCode",
                          "LocationDetails0addresszipCode" + j,
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
          && (!select.trim().equals("LocationDetails0") && select.indexOf("new") == -1)) {
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
