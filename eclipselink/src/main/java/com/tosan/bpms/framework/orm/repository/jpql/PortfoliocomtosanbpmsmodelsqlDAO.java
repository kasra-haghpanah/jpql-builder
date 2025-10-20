package com.tosan.bpms.framework.orm.repository.jpql;

import com.jpql.api.interfaces.*;
import com.jpql.api.interfaces.Parameter;
import javax.persistence.FetchType;
import javax.persistence.EntityManager;
/**
 * Created by kasra.haghpanah on 1/05/2019.
 */
public class PortfoliocomtosanbpmsmodelsqlDAO {

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

        com.tosan.bpms.model.sql.Portfolio model =
            (com.tosan.bpms.model.sql.Portfolio) fetchIterator.next();

        if (model != null) {

          String key = model.getPortfolioId() + "";
          existMap.put(key, model);
        }
      }
    }

    java.util.Iterator iterator = models.iterator();

    while (iterator.hasNext()) {
      com.tosan.bpms.model.sql.Portfolio model =
          (com.tosan.bpms.model.sql.Portfolio) iterator.next();

      if (model != null) {
        String key = model.getPortfolioId() + "";
        com.tosan.bpms.model.sql.Portfolio fetchModel =
            (com.tosan.bpms.model.sql.Portfolio) existMap.get(key);

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
    com.tosan.bpms.model.sql.Portfolio model = null;
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
    java.lang.String select = "SELECT Portfolio0";
    if (selectParam != null && !selectParam.equals("")) {
      select = selectParam;
    }
    if (!isExistRecord && !isDelete) {
      if (fetchType == FetchType.LAZY) {
        basicQuery = select + " FROM com.tosan.bpms.model.sql.Portfolio Portfolio0  WHERE 1=1 ";
      } else if (fetchType == FetchType.EAGER) {
        basicQuery =
            select
                + " FROM com.tosan.bpms.model.sql.Portfolio Portfolio0  JOIN "
                + fetch
                + " Portfolio0.portfolioLicenses PortfolioLicense1 LEFT JOIN "
                + fetch
                + " PortfolioLicense1.file File3 JOIN "
                + fetch
                + " PortfolioLicense1.licenseDetails LicenseDetails4 JOIN "
                + fetch
                + " LicenseDetails4.portfolioLicense PortfolioLicense5 LEFT JOIN "
                + fetch
                + " PortfolioLicense5.file File7 LEFT JOIN "
                + fetch
                + " LicenseDetails4.file File9 JOIN "
                + fetch
                + " Portfolio0.portfolioLocations PortfolioLocation10 LEFT JOIN "
                + fetch
                + " PortfolioLocation10.file File12 JOIN "
                + fetch
                + " PortfolioLocation10.locationDetails LocationDetails13 JOIN "
                + fetch
                + " LocationDetails13.portfolioLocation PortfolioLocation14 LEFT JOIN "
                + fetch
                + " PortfolioLocation14.file File16 LEFT JOIN "
                + fetch
                + " LocationDetails13.file File18 JOIN "
                + fetch
                + " Portfolio0.receptions Reception19 WHERE 1=1 ";
      }
    } else if (isExistRecord) {
      basicQuery =
          "SELECT Portfolio0 FROM com.tosan.bpms.model.sql.Portfolio Portfolio0 WHERE 1=1 ";
    } else if (isDelete) {
      if (fetchType == FetchType.LAZY) {
        basicQuery = "DELETE FROM com.tosan.bpms.model.sql.Portfolio Portfolio0 WHERE 1=1 ";
      } else if (fetchType == FetchType.EAGER) {
        basicQuery =
            "DELETE Portfolio0,PortfolioLicense1,LicenseDetails4,PortfolioLocation10,LocationDetails13,Reception19 FROM Portfolio Portfolio0 LEFT JOIN PortfolioLicense PortfolioLicense1 ON( PortfolioLicense1.portfolioId = Portfolio0.portfolioId )  LEFT JOIN LicenseDetails LicenseDetails4 ON( LicenseDetails4.portfolioLicenseId = PortfolioLicense1.id )  LEFT JOIN PortfolioLocation PortfolioLocation10 ON( PortfolioLocation10.portfolioId = Portfolio0.portfolioId )  LEFT JOIN LocationDetail LocationDetails13 ON( LocationDetails13.portfolioLocationId = PortfolioLocation10.id )  LEFT JOIN Reception Reception19 ON( Reception19.portfolioId = Portfolio0.portfolioId )  WHERE 1=1 ";
      }
    }
    int modelsLength = models.size();
    java.util.Iterator modelsIterator = models.iterator();
    int j = 0;
    if (modelsLength > 0) {
      while (modelsIterator.hasNext()) {
        model = (com.tosan.bpms.model.sql.Portfolio) modelsIterator.next();
        query = "";
        isOpenParentheses = true;

        if (model != null) {

          if (isExistRecord == true && model.getPortfolioId() == null) {
            model.setPortfolioId(new java.lang.Integer("-1"));
            isChangeKey = true;
          }

          if (model.getPortfolioId() != null) {

            andCondition = " AND ";
            if (isOpenParentheses) {
              andCondition = " ";
            }
            query += " ";
            if (isDelete && fetchType == FetchType.EAGER) {
              whereQuery = " Portfolio0.portfolioId = ? ";
              counter++;
            } else {
              whereQuery = " Portfolio0.portfolioId = :Portfolio0portfolioId" + j + " ";
            }

            isOpenParentheses = false;
            whereValue = model.getPortfolioId();
            if (isExistRecord && isChangeKey) {
              model.setPortfolioId(null);
              isChangeKey = false;
            }

            query +=
                (andCondition
                    + Facade.getStatement(
                        statement,
                        "Portfolio0.portfolioId",
                        "Portfolio0portfolioId" + j,
                        mapVariable,
                        whereQuery,
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
              && model.getPortfolioLicenses() != null) {
            int length1 = model.getPortfolioLicenses().size();

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

                if (model.getPortfolioLicenses().get(i1).getPortfolioLicenseId() != null) {

                  andCondition = " AND ";
                  if (isOpenParentheses) {
                    andCondition = " ";
                  }
                  query += " ";
                  whereValue = model.getPortfolioLicenses().get(i1).getPortfolioLicenseId();
                  if (isDelete && fetchType == FetchType.EAGER) {
                    whereQuery = " PortfolioLicense1.id = ? ";
                    counter++;
                  } else {
                    whereQuery =
                        " PortfolioLicense1.portfolioLicenseId = :PortfolioLicense1portfolioLicenseId"
                            + j
                            + " ";
                  }

                  isOpenParentheses = false;
                  whereValue = model.getPortfolioLicenses().get(i1).getPortfolioLicenseId();
                  query +=
                      (andCondition
                          + Facade.getStatement(
                              statement,
                              "PortfolioLicense1.portfolioLicenseId",
                              "PortfolioLicense1portfolioLicenseId" + j,
                              mapVariable,
                              whereQuery,
                              whereValue,
                              isExistRecord,
                              isDelete,
                              fetchType,
                              counter));
                  whereQuery = "";
                }
                if (!isExistRecord
                    && !isDelete
                    && model.getPortfolioLicenses().get(i1).getFile() != null) {

                  if (model.getPortfolioLicenses().get(i1).getFile().getId() != null) {

                    andCondition = " AND ";
                    if (isOpenParentheses) {
                      andCondition = " ";
                    }
                    query += " ";
                    whereValue = model.getPortfolioLicenses().get(i1).getFile().getId();
                    if (isDelete && fetchType == FetchType.EAGER) {
                      whereQuery = " File3.l4610flmtdataid = ? ";
                      counter++;
                    } else {
                      whereQuery = " File3.id = :File3id" + j + " ";
                    }

                    isOpenParentheses = false;
                    whereValue = model.getPortfolioLicenses().get(i1).getFile().getId();
                    query +=
                        (andCondition
                            + Facade.getStatement(
                                statement,
                                "File3.id",
                                "File3id" + j,
                                mapVariable,
                                whereQuery,
                                whereValue,
                                isExistRecord,
                                isDelete,
                                fetchType,
                                counter));
                    whereQuery = "";
                  }
                  if (!isExistRecord
                      && !isDelete
                      && true
                      && model.getPortfolioLicenses().get(i1).getFile().getExtension() != null) {

                    andCondition = " AND ";
                    if (isOpenParentheses) {
                      andCondition = " ";
                    }
                    query += " ";
                    whereValue = model.getPortfolioLicenses().get(i1).getFile().getExtension();
                    if (isDelete && fetchType == FetchType.EAGER) {
                      whereQuery = " File3.l4610extension = ? ";
                      counter++;
                    } else {
                      whereQuery = " File3.extension = :File3extension" + j + " ";
                    }

                    isOpenParentheses = false;
                    whereValue = model.getPortfolioLicenses().get(i1).getFile().getExtension();
                    query +=
                        (andCondition
                            + Facade.getStatement(
                                statement,
                                "File3.extension",
                                "File3extension" + j,
                                mapVariable,
                                whereQuery,
                                whereValue,
                                isExistRecord,
                                isDelete,
                                fetchType,
                                counter));
                    whereQuery = "";
                  }
                  if (!isExistRecord
                      && !isDelete
                      && true
                      && model.getPortfolioLicenses().get(i1).getFile().getVersion() != 0) {

                    andCondition = " AND ";
                    if (isOpenParentheses) {
                      andCondition = " ";
                    }
                    query += " ";
                    whereValue =
                        new java.lang.Integer(
                            "" + model.getPortfolioLicenses().get(i1).getFile().getVersion());
                    if (isDelete && fetchType == FetchType.EAGER) {
                      whereQuery = " File3.VERSION = ? ";
                      counter++;
                    } else {
                      whereQuery = " File3.version = :File3version" + j + " ";
                    }

                    isOpenParentheses = false;
                    whereValue =
                        new java.lang.Integer(
                            "" + model.getPortfolioLicenses().get(i1).getFile().getVersion());
                    query +=
                        (andCondition
                            + Facade.getStatement(
                                statement,
                                "File3.version",
                                "File3version" + j,
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
                    && model.getPortfolioLicenses().get(i1).getLicenseDetails() != null) {
                  int length4 = model.getPortfolioLicenses().get(i1).getLicenseDetails().size();

                  if (length4 > 0) {
                    if (isOpenParentheses) {
                      query += "  ( ";
                    } else {
                      query += " AND ( ";
                    }
                    isOpenParentheses = true;

                    for (int i4 = 0; i4 < length4; i4++) {

                      java.lang.String beforeCondition4 = "";
                      java.lang.String prefix4 = " OR ";
                      if (i4 == 0) {
                        prefix4 = "";
                      }
                      if (length4 > 1) {

                        query += (prefix4 + "(");
                        isOpenParentheses = true;

                      } else {

                        query += (prefix4);
                        if (!prefix4.trim().equals("")) {
                          isOpenParentheses = false;
                        }
                      }

                      if (model
                              .getPortfolioLicenses()
                              .get(i1)
                              .getLicenseDetails()
                              .get(i4)
                              .getLicenseDetailId()
                          != null) {

                        andCondition = " AND ";
                        if (isOpenParentheses) {
                          andCondition = " ";
                        }
                        query += " ";
                        whereValue =
                            model
                                .getPortfolioLicenses()
                                .get(i1)
                                .getLicenseDetails()
                                .get(i4)
                                .getLicenseDetailId();
                        if (isDelete && fetchType == FetchType.EAGER) {
                          whereQuery = " LicenseDetails4.id = ? ";
                          counter++;
                        } else {
                          whereQuery =
                              " LicenseDetails4.licenseDetailId = :LicenseDetails4licenseDetailId"
                                  + j
                                  + " ";
                        }

                        isOpenParentheses = false;
                        whereValue =
                            model
                                .getPortfolioLicenses()
                                .get(i1)
                                .getLicenseDetails()
                                .get(i4)
                                .getLicenseDetailId();
                        query +=
                            (andCondition
                                + Facade.getStatement(
                                    statement,
                                    "LicenseDetails4.licenseDetailId",
                                    "LicenseDetails4licenseDetailId" + j,
                                    mapVariable,
                                    whereQuery,
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
                                  .getPortfolioLicenses()
                                  .get(i1)
                                  .getLicenseDetails()
                                  .get(i4)
                                  .getPortfolioLicense()
                              != null) {

                        if (model
                                .getPortfolioLicenses()
                                .get(i1)
                                .getLicenseDetails()
                                .get(i4)
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
                                  .getPortfolioLicenses()
                                  .get(i1)
                                  .getLicenseDetails()
                                  .get(i4)
                                  .getPortfolioLicense()
                                  .getPortfolioLicenseId();
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
                          whereValue =
                              model
                                  .getPortfolioLicenses()
                                  .get(i1)
                                  .getLicenseDetails()
                                  .get(i4)
                                  .getPortfolioLicense()
                                  .getPortfolioLicenseId();
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
                        if (!isExistRecord
                            && !isDelete
                            && model
                                    .getPortfolioLicenses()
                                    .get(i1)
                                    .getLicenseDetails()
                                    .get(i4)
                                    .getPortfolioLicense()
                                    .getFile()
                                != null) {

                          if (model
                                  .getPortfolioLicenses()
                                  .get(i1)
                                  .getLicenseDetails()
                                  .get(i4)
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
                                    .getPortfolioLicenses()
                                    .get(i1)
                                    .getLicenseDetails()
                                    .get(i4)
                                    .getPortfolioLicense()
                                    .getFile()
                                    .getId();
                            if (isDelete && fetchType == FetchType.EAGER) {
                              whereQuery = " File7.l4610flmtdataid = ? ";
                              counter++;
                            } else {
                              whereQuery = " File7.id = :File7id" + j + " ";
                            }

                            isOpenParentheses = false;
                            whereValue =
                                model
                                    .getPortfolioLicenses()
                                    .get(i1)
                                    .getLicenseDetails()
                                    .get(i4)
                                    .getPortfolioLicense()
                                    .getFile()
                                    .getId();
                            query +=
                                (andCondition
                                    + Facade.getStatement(
                                        statement,
                                        "File7.id",
                                        "File7id" + j,
                                        mapVariable,
                                        whereQuery,
                                        whereValue,
                                        isExistRecord,
                                        isDelete,
                                        fetchType,
                                        counter));
                            whereQuery = "";
                          }
                          if (!isExistRecord
                              && !isDelete
                              && true
                              && model
                                      .getPortfolioLicenses()
                                      .get(i1)
                                      .getLicenseDetails()
                                      .get(i4)
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
                                    .getPortfolioLicenses()
                                    .get(i1)
                                    .getLicenseDetails()
                                    .get(i4)
                                    .getPortfolioLicense()
                                    .getFile()
                                    .getExtension();
                            if (isDelete && fetchType == FetchType.EAGER) {
                              whereQuery = " File7.l4610extension = ? ";
                              counter++;
                            } else {
                              whereQuery = " File7.extension = :File7extension" + j + " ";
                            }

                            isOpenParentheses = false;
                            whereValue =
                                model
                                    .getPortfolioLicenses()
                                    .get(i1)
                                    .getLicenseDetails()
                                    .get(i4)
                                    .getPortfolioLicense()
                                    .getFile()
                                    .getExtension();
                            query +=
                                (andCondition
                                    + Facade.getStatement(
                                        statement,
                                        "File7.extension",
                                        "File7extension" + j,
                                        mapVariable,
                                        whereQuery,
                                        whereValue,
                                        isExistRecord,
                                        isDelete,
                                        fetchType,
                                        counter));
                            whereQuery = "";
                          }
                          if (!isExistRecord
                              && !isDelete
                              && true
                              && model
                                      .getPortfolioLicenses()
                                      .get(i1)
                                      .getLicenseDetails()
                                      .get(i4)
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
                                            .getPortfolioLicenses()
                                            .get(i1)
                                            .getLicenseDetails()
                                            .get(i4)
                                            .getPortfolioLicense()
                                            .getFile()
                                            .getVersion());
                            if (isDelete && fetchType == FetchType.EAGER) {
                              whereQuery = " File7.VERSION = ? ";
                              counter++;
                            } else {
                              whereQuery = " File7.version = :File7version" + j + " ";
                            }

                            isOpenParentheses = false;
                            whereValue =
                                new java.lang.Integer(
                                    ""
                                        + model
                                            .getPortfolioLicenses()
                                            .get(i1)
                                            .getLicenseDetails()
                                            .get(i4)
                                            .getPortfolioLicense()
                                            .getFile()
                                            .getVersion());
                            query +=
                                (andCondition
                                    + Facade.getStatement(
                                        statement,
                                        "File7.version",
                                        "File7version" + j,
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
                                    .getPortfolioLicenses()
                                    .get(i1)
                                    .getLicenseDetails()
                                    .get(i4)
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
                                  .getPortfolioLicenses()
                                  .get(i1)
                                  .getLicenseDetails()
                                  .get(i4)
                                  .getPortfolioLicense()
                                  .getIssueVerifierType();
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
                          whereValue =
                              model
                                  .getPortfolioLicenses()
                                  .get(i1)
                                  .getLicenseDetails()
                                  .get(i4)
                                  .getPortfolioLicense()
                                  .getIssueVerifierType();
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
                            && model
                                    .getPortfolioLicenses()
                                    .get(i1)
                                    .getLicenseDetails()
                                    .get(i4)
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
                                  .getPortfolioLicenses()
                                  .get(i1)
                                  .getLicenseDetails()
                                  .get(i4)
                                  .getPortfolioLicense()
                                  .getLicenseNumber();
                          if (isDelete && fetchType == FetchType.EAGER) {
                            whereQuery = " PortfolioLicense5.licenseNumber = ? ";
                            counter++;
                          } else {
                            whereQuery =
                                " PortfolioLicense5.licenseNumber = :PortfolioLicense5licenseNumber"
                                    + j
                                    + " ";
                          }

                          isOpenParentheses = false;
                          whereValue =
                              model
                                  .getPortfolioLicenses()
                                  .get(i1)
                                  .getLicenseDetails()
                                  .get(i4)
                                  .getPortfolioLicense()
                                  .getLicenseNumber();
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
                            && model
                                    .getPortfolioLicenses()
                                    .get(i1)
                                    .getLicenseDetails()
                                    .get(i4)
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
                                  .getPortfolioLicenses()
                                  .get(i1)
                                  .getLicenseDetails()
                                  .get(i4)
                                  .getPortfolioLicense()
                                  .getLicenseType();
                          if (isDelete && fetchType == FetchType.EAGER) {
                            whereQuery = " PortfolioLicense5.licenseType = ? ";
                            counter++;
                          } else {
                            whereQuery =
                                " PortfolioLicense5.licenseType = :PortfolioLicense5licenseType"
                                    + j
                                    + " ";
                          }

                          isOpenParentheses = false;
                          whereValue =
                              model
                                  .getPortfolioLicenses()
                                  .get(i1)
                                  .getLicenseDetails()
                                  .get(i4)
                                  .getPortfolioLicense()
                                  .getLicenseType();
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
                            && model
                                    .getPortfolioLicenses()
                                    .get(i1)
                                    .getLicenseDetails()
                                    .get(i4)
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
                                  .getPortfolioLicenses()
                                  .get(i1)
                                  .getLicenseDetails()
                                  .get(i4)
                                  .getPortfolioLicense()
                                  .getIssueDate();
                          if (isDelete && fetchType == FetchType.EAGER) {
                            whereQuery = " PortfolioLicense5.issueDate = ? ";
                            counter++;
                          } else {
                            whereQuery =
                                " PortfolioLicense5.issueDate = :PortfolioLicense5issueDate"
                                    + j
                                    + " ";
                          }

                          isOpenParentheses = false;
                          whereValue =
                              model
                                  .getPortfolioLicenses()
                                  .get(i1)
                                  .getLicenseDetails()
                                  .get(i4)
                                  .getPortfolioLicense()
                                  .getIssueDate();
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
                            && model
                                    .getPortfolioLicenses()
                                    .get(i1)
                                    .getLicenseDetails()
                                    .get(i4)
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
                                  .getPortfolioLicenses()
                                  .get(i1)
                                  .getLicenseDetails()
                                  .get(i4)
                                  .getPortfolioLicense()
                                  .getEndDate();
                          if (isDelete && fetchType == FetchType.EAGER) {
                            whereQuery = " PortfolioLicense5.endDate = ? ";
                            counter++;
                          } else {
                            whereQuery =
                                " PortfolioLicense5.endDate = :PortfolioLicense5endDate" + j + " ";
                          }

                          isOpenParentheses = false;
                          whereValue =
                              model
                                  .getPortfolioLicenses()
                                  .get(i1)
                                  .getLicenseDetails()
                                  .get(i4)
                                  .getPortfolioLicense()
                                  .getEndDate();
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
                            && model
                                    .getPortfolioLicenses()
                                    .get(i1)
                                    .getLicenseDetails()
                                    .get(i4)
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
                                          .getPortfolioLicenses()
                                          .get(i1)
                                          .getLicenseDetails()
                                          .get(i4)
                                          .getPortfolioLicense()
                                          .getVersion());
                          if (isDelete && fetchType == FetchType.EAGER) {
                            whereQuery = " PortfolioLicense5.VERSION = ? ";
                            counter++;
                          } else {
                            whereQuery =
                                " PortfolioLicense5.version = :PortfolioLicense5version" + j + " ";
                          }

                          isOpenParentheses = false;
                          whereValue =
                              new java.lang.Integer(
                                  ""
                                      + model
                                          .getPortfolioLicenses()
                                          .get(i1)
                                          .getLicenseDetails()
                                          .get(i4)
                                          .getPortfolioLicense()
                                          .getVersion());
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
                      if (!isExistRecord
                          && !isDelete
                          && model
                                  .getPortfolioLicenses()
                                  .get(i1)
                                  .getLicenseDetails()
                                  .get(i4)
                                  .getFile()
                              != null) {

                        if (model
                                .getPortfolioLicenses()
                                .get(i1)
                                .getLicenseDetails()
                                .get(i4)
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
                                  .getPortfolioLicenses()
                                  .get(i1)
                                  .getLicenseDetails()
                                  .get(i4)
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
                                  .getPortfolioLicenses()
                                  .get(i1)
                                  .getLicenseDetails()
                                  .get(i4)
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
                                    .getPortfolioLicenses()
                                    .get(i1)
                                    .getLicenseDetails()
                                    .get(i4)
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
                                  .getPortfolioLicenses()
                                  .get(i1)
                                  .getLicenseDetails()
                                  .get(i4)
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
                                  .getPortfolioLicenses()
                                  .get(i1)
                                  .getLicenseDetails()
                                  .get(i4)
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
                                    .getPortfolioLicenses()
                                    .get(i1)
                                    .getLicenseDetails()
                                    .get(i4)
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
                                          .getPortfolioLicenses()
                                          .get(i1)
                                          .getLicenseDetails()
                                          .get(i4)
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
                                          .getPortfolioLicenses()
                                          .get(i1)
                                          .getLicenseDetails()
                                          .get(i4)
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
                      if (!isDelete
                          && model
                                  .getPortfolioLicenses()
                                  .get(i1)
                                  .getLicenseDetails()
                                  .get(i4)
                                  .getAddress()
                              != null) {
                        if (!isDelete
                            && true
                            && model
                                    .getPortfolioLicenses()
                                    .get(i1)
                                    .getLicenseDetails()
                                    .get(i4)
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
                                  .getPortfolioLicenses()
                                  .get(i1)
                                  .getLicenseDetails()
                                  .get(i4)
                                  .getAddress()
                                  .getAddressLine1();
                          if (isDelete && fetchType == FetchType.EAGER) {
                            whereQuery = " LicenseDetails4.addressLine1 = ? ";
                            counter++;
                          } else {
                            whereQuery =
                                " LicenseDetails4.address.addressLine1 = :LicenseDetails4addressaddressLine1"
                                    + j
                                    + " ";
                          }

                          isOpenParentheses = false;
                          whereValue =
                              model
                                  .getPortfolioLicenses()
                                  .get(i1)
                                  .getLicenseDetails()
                                  .get(i4)
                                  .getAddress()
                                  .getAddressLine1();
                          query +=
                              (andCondition
                                  + Facade.getStatement(
                                      statement,
                                      "LicenseDetails4.address.addressLine1",
                                      "LicenseDetails4addressaddressLine1" + j,
                                      mapVariable,
                                      whereQuery,
                                      whereValue,
                                      isExistRecord,
                                      isDelete,
                                      fetchType,
                                      counter));
                          whereQuery = "";
                        }
                        if (!isExistRecord
                            && !isDelete
                            && true
                            && model
                                    .getPortfolioLicenses()
                                    .get(i1)
                                    .getLicenseDetails()
                                    .get(i4)
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
                                  .getPortfolioLicenses()
                                  .get(i1)
                                  .getLicenseDetails()
                                  .get(i4)
                                  .getAddress()
                                  .getAddressLine2();
                          if (isDelete && fetchType == FetchType.EAGER) {
                            whereQuery = " LicenseDetails4.addressLine2 = ? ";
                            counter++;
                          } else {
                            whereQuery =
                                " LicenseDetails4.address.addressLine2 = :LicenseDetails4addressaddressLine2"
                                    + j
                                    + " ";
                          }

                          isOpenParentheses = false;
                          whereValue =
                              model
                                  .getPortfolioLicenses()
                                  .get(i1)
                                  .getLicenseDetails()
                                  .get(i4)
                                  .getAddress()
                                  .getAddressLine2();
                          query +=
                              (andCondition
                                  + Facade.getStatement(
                                      statement,
                                      "LicenseDetails4.address.addressLine2",
                                      "LicenseDetails4addressaddressLine2" + j,
                                      mapVariable,
                                      whereQuery,
                                      whereValue,
                                      isExistRecord,
                                      isDelete,
                                      fetchType,
                                      counter));
                          whereQuery = "";
                        }
                        if (!isExistRecord
                            && !isDelete
                            && true
                            && model
                                    .getPortfolioLicenses()
                                    .get(i1)
                                    .getLicenseDetails()
                                    .get(i4)
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
                                  .getPortfolioLicenses()
                                  .get(i1)
                                  .getLicenseDetails()
                                  .get(i4)
                                  .getAddress()
                                  .getCity();
                          if (isDelete && fetchType == FetchType.EAGER) {
                            whereQuery = " LicenseDetails4.city = ? ";
                            counter++;
                          } else {
                            whereQuery =
                                " LicenseDetails4.address.city = :LicenseDetails4addresscity"
                                    + j
                                    + " ";
                          }

                          isOpenParentheses = false;
                          whereValue =
                              model
                                  .getPortfolioLicenses()
                                  .get(i1)
                                  .getLicenseDetails()
                                  .get(i4)
                                  .getAddress()
                                  .getCity();
                          query +=
                              (andCondition
                                  + Facade.getStatement(
                                      statement,
                                      "LicenseDetails4.address.city",
                                      "LicenseDetails4addresscity" + j,
                                      mapVariable,
                                      whereQuery,
                                      whereValue,
                                      isExistRecord,
                                      isDelete,
                                      fetchType,
                                      counter));
                          whereQuery = "";
                        }
                        if (!isExistRecord
                            && !isDelete
                            && true
                            && model
                                    .getPortfolioLicenses()
                                    .get(i1)
                                    .getLicenseDetails()
                                    .get(i4)
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
                                  .getPortfolioLicenses()
                                  .get(i1)
                                  .getLicenseDetails()
                                  .get(i4)
                                  .getAddress()
                                  .getState();
                          if (isDelete && fetchType == FetchType.EAGER) {
                            whereQuery = " LicenseDetails4.state = ? ";
                            counter++;
                          } else {
                            whereQuery =
                                " LicenseDetails4.address.state = :LicenseDetails4addressstate"
                                    + j
                                    + " ";
                          }

                          isOpenParentheses = false;
                          whereValue =
                              model
                                  .getPortfolioLicenses()
                                  .get(i1)
                                  .getLicenseDetails()
                                  .get(i4)
                                  .getAddress()
                                  .getState();
                          query +=
                              (andCondition
                                  + Facade.getStatement(
                                      statement,
                                      "LicenseDetails4.address.state",
                                      "LicenseDetails4addressstate" + j,
                                      mapVariable,
                                      whereQuery,
                                      whereValue,
                                      isExistRecord,
                                      isDelete,
                                      fetchType,
                                      counter));
                          whereQuery = "";
                        }
                        if (!isExistRecord
                            && !isDelete
                            && true
                            && model
                                    .getPortfolioLicenses()
                                    .get(i1)
                                    .getLicenseDetails()
                                    .get(i4)
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
                                  .getPortfolioLicenses()
                                  .get(i1)
                                  .getLicenseDetails()
                                  .get(i4)
                                  .getAddress()
                                  .getCountry();
                          if (isDelete && fetchType == FetchType.EAGER) {
                            whereQuery = " LicenseDetails4.country = ? ";
                            counter++;
                          } else {
                            whereQuery =
                                " LicenseDetails4.address.country = :LicenseDetails4addresscountry"
                                    + j
                                    + " ";
                          }

                          isOpenParentheses = false;
                          whereValue =
                              model
                                  .getPortfolioLicenses()
                                  .get(i1)
                                  .getLicenseDetails()
                                  .get(i4)
                                  .getAddress()
                                  .getCountry();
                          query +=
                              (andCondition
                                  + Facade.getStatement(
                                      statement,
                                      "LicenseDetails4.address.country",
                                      "LicenseDetails4addresscountry" + j,
                                      mapVariable,
                                      whereQuery,
                                      whereValue,
                                      isExistRecord,
                                      isDelete,
                                      fetchType,
                                      counter));
                          whereQuery = "";
                        }
                        if (!isExistRecord
                            && !isDelete
                            && true
                            && model
                                    .getPortfolioLicenses()
                                    .get(i1)
                                    .getLicenseDetails()
                                    .get(i4)
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
                                  .getPortfolioLicenses()
                                  .get(i1)
                                  .getLicenseDetails()
                                  .get(i4)
                                  .getAddress()
                                  .getZipCode();
                          if (isDelete && fetchType == FetchType.EAGER) {
                            whereQuery = " LicenseDetails4.zipCode = ? ";
                            counter++;
                          } else {
                            whereQuery =
                                " LicenseDetails4.address.zipCode = :LicenseDetails4addresszipCode"
                                    + j
                                    + " ";
                          }

                          isOpenParentheses = false;
                          whereValue =
                              model
                                  .getPortfolioLicenses()
                                  .get(i1)
                                  .getLicenseDetails()
                                  .get(i4)
                                  .getAddress()
                                  .getZipCode();
                          query +=
                              (andCondition
                                  + Facade.getStatement(
                                      statement,
                                      "LicenseDetails4.address.zipCode",
                                      "LicenseDetails4addresszipCode" + j,
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
                      if (length4 > 1) {
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
                    && model.getPortfolioLicenses().get(i1).getIssueVerifierType() != null) {

                  andCondition = " AND ";
                  if (isOpenParentheses) {
                    andCondition = " ";
                  }
                  query += " ";
                  whereValue = model.getPortfolioLicenses().get(i1).getIssueVerifierType();
                  if (isDelete && fetchType == FetchType.EAGER) {
                    whereQuery = " PortfolioLicense1.issueVerifierType = ? ";
                    counter++;
                  } else {
                    whereQuery =
                        " PortfolioLicense1.issueVerifierType = :PortfolioLicense1issueVerifierType"
                            + j
                            + " ";
                  }

                  isOpenParentheses = false;
                  whereValue = model.getPortfolioLicenses().get(i1).getIssueVerifierType();
                  query +=
                      (andCondition
                          + Facade.getStatement(
                              statement,
                              "PortfolioLicense1.issueVerifierType",
                              "PortfolioLicense1issueVerifierType" + j,
                              mapVariable,
                              whereQuery,
                              whereValue,
                              isExistRecord,
                              isDelete,
                              fetchType,
                              counter));
                  whereQuery = "";
                }
                if (!isExistRecord
                    && !isDelete
                    && true
                    && model.getPortfolioLicenses().get(i1).getLicenseNumber() != null) {

                  andCondition = " AND ";
                  if (isOpenParentheses) {
                    andCondition = " ";
                  }
                  query += " ";
                  whereValue = model.getPortfolioLicenses().get(i1).getLicenseNumber();
                  if (isDelete && fetchType == FetchType.EAGER) {
                    whereQuery = " PortfolioLicense1.licenseNumber = ? ";
                    counter++;
                  } else {
                    whereQuery =
                        " PortfolioLicense1.licenseNumber = :PortfolioLicense1licenseNumber"
                            + j
                            + " ";
                  }

                  isOpenParentheses = false;
                  whereValue = model.getPortfolioLicenses().get(i1).getLicenseNumber();
                  query +=
                      (andCondition
                          + Facade.getStatement(
                              statement,
                              "PortfolioLicense1.licenseNumber",
                              "PortfolioLicense1licenseNumber" + j,
                              mapVariable,
                              whereQuery,
                              whereValue,
                              isExistRecord,
                              isDelete,
                              fetchType,
                              counter));
                  whereQuery = "";
                }
                if (!isExistRecord
                    && !isDelete
                    && true
                    && model.getPortfolioLicenses().get(i1).getLicenseType() != null) {

                  andCondition = " AND ";
                  if (isOpenParentheses) {
                    andCondition = " ";
                  }
                  query += " ";
                  whereValue = model.getPortfolioLicenses().get(i1).getLicenseType();
                  if (isDelete && fetchType == FetchType.EAGER) {
                    whereQuery = " PortfolioLicense1.licenseType = ? ";
                    counter++;
                  } else {
                    whereQuery =
                        " PortfolioLicense1.licenseType = :PortfolioLicense1licenseType" + j + " ";
                  }

                  isOpenParentheses = false;
                  whereValue = model.getPortfolioLicenses().get(i1).getLicenseType();
                  query +=
                      (andCondition
                          + Facade.getStatement(
                              statement,
                              "PortfolioLicense1.licenseType",
                              "PortfolioLicense1licenseType" + j,
                              mapVariable,
                              whereQuery,
                              whereValue,
                              isExistRecord,
                              isDelete,
                              fetchType,
                              counter));
                  whereQuery = "";
                }
                if (!isExistRecord
                    && !isDelete
                    && true
                    && model.getPortfolioLicenses().get(i1).getIssueDate() != null) {

                  andCondition = " AND ";
                  if (isOpenParentheses) {
                    andCondition = " ";
                  }
                  query += " ";
                  whereValue = model.getPortfolioLicenses().get(i1).getIssueDate();
                  if (isDelete && fetchType == FetchType.EAGER) {
                    whereQuery = " PortfolioLicense1.issueDate = ? ";
                    counter++;
                  } else {
                    whereQuery =
                        " PortfolioLicense1.issueDate = :PortfolioLicense1issueDate" + j + " ";
                  }

                  isOpenParentheses = false;
                  whereValue = model.getPortfolioLicenses().get(i1).getIssueDate();
                  query +=
                      (andCondition
                          + Facade.getStatement(
                              statement,
                              "PortfolioLicense1.issueDate",
                              "PortfolioLicense1issueDate" + j,
                              mapVariable,
                              whereQuery,
                              whereValue,
                              isExistRecord,
                              isDelete,
                              fetchType,
                              counter));
                  whereQuery = "";
                }
                if (!isExistRecord
                    && !isDelete
                    && true
                    && model.getPortfolioLicenses().get(i1).getEndDate() != null) {

                  andCondition = " AND ";
                  if (isOpenParentheses) {
                    andCondition = " ";
                  }
                  query += " ";
                  whereValue = model.getPortfolioLicenses().get(i1).getEndDate();
                  if (isDelete && fetchType == FetchType.EAGER) {
                    whereQuery = " PortfolioLicense1.endDate = ? ";
                    counter++;
                  } else {
                    whereQuery = " PortfolioLicense1.endDate = :PortfolioLicense1endDate" + j + " ";
                  }

                  isOpenParentheses = false;
                  whereValue = model.getPortfolioLicenses().get(i1).getEndDate();
                  query +=
                      (andCondition
                          + Facade.getStatement(
                              statement,
                              "PortfolioLicense1.endDate",
                              "PortfolioLicense1endDate" + j,
                              mapVariable,
                              whereQuery,
                              whereValue,
                              isExistRecord,
                              isDelete,
                              fetchType,
                              counter));
                  whereQuery = "";
                }
                if (!isExistRecord
                    && !isDelete
                    && true
                    && model.getPortfolioLicenses().get(i1).getVersion() != 0) {

                  andCondition = " AND ";
                  if (isOpenParentheses) {
                    andCondition = " ";
                  }
                  query += " ";
                  whereValue =
                      new java.lang.Integer("" + model.getPortfolioLicenses().get(i1).getVersion());
                  if (isDelete && fetchType == FetchType.EAGER) {
                    whereQuery = " PortfolioLicense1.VERSION = ? ";
                    counter++;
                  } else {
                    whereQuery = " PortfolioLicense1.version = :PortfolioLicense1version" + j + " ";
                  }

                  isOpenParentheses = false;
                  whereValue =
                      new java.lang.Integer("" + model.getPortfolioLicenses().get(i1).getVersion());
                  query +=
                      (andCondition
                          + Facade.getStatement(
                              statement,
                              "PortfolioLicense1.version",
                              "PortfolioLicense1version" + j,
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

          if (!isExistRecord
              && !isDelete
              && fetchType == FetchType.EAGER
              && model.getPortfolioLocations() != null) {
            int length10 = model.getPortfolioLocations().size();

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

                if (model.getPortfolioLocations().get(i10).getPortfolioLocationId() != null) {

                  andCondition = " AND ";
                  if (isOpenParentheses) {
                    andCondition = " ";
                  }
                  query += " ";
                  whereValue = model.getPortfolioLocations().get(i10).getPortfolioLocationId();
                  if (isDelete && fetchType == FetchType.EAGER) {
                    whereQuery = " PortfolioLocation10.id = ? ";
                    counter++;
                  } else {
                    whereQuery =
                        " PortfolioLocation10.portfolioLocationId = :PortfolioLocation10portfolioLocationId"
                            + j
                            + " ";
                  }

                  isOpenParentheses = false;
                  whereValue = model.getPortfolioLocations().get(i10).getPortfolioLocationId();
                  query +=
                      (andCondition
                          + Facade.getStatement(
                              statement,
                              "PortfolioLocation10.portfolioLocationId",
                              "PortfolioLocation10portfolioLocationId" + j,
                              mapVariable,
                              whereQuery,
                              whereValue,
                              isExistRecord,
                              isDelete,
                              fetchType,
                              counter));
                  whereQuery = "";
                }
                if (!isExistRecord
                    && !isDelete
                    && model.getPortfolioLocations().get(i10).getFile() != null) {

                  if (model.getPortfolioLocations().get(i10).getFile().getId() != null) {

                    andCondition = " AND ";
                    if (isOpenParentheses) {
                      andCondition = " ";
                    }
                    query += " ";
                    whereValue = model.getPortfolioLocations().get(i10).getFile().getId();
                    if (isDelete && fetchType == FetchType.EAGER) {
                      whereQuery = " File12.l4610flmtdataid = ? ";
                      counter++;
                    } else {
                      whereQuery = " File12.id = :File12id" + j + " ";
                    }

                    isOpenParentheses = false;
                    whereValue = model.getPortfolioLocations().get(i10).getFile().getId();
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
                      && model.getPortfolioLocations().get(i10).getFile().getExtension() != null) {

                    andCondition = " AND ";
                    if (isOpenParentheses) {
                      andCondition = " ";
                    }
                    query += " ";
                    whereValue = model.getPortfolioLocations().get(i10).getFile().getExtension();
                    if (isDelete && fetchType == FetchType.EAGER) {
                      whereQuery = " File12.l4610extension = ? ";
                      counter++;
                    } else {
                      whereQuery = " File12.extension = :File12extension" + j + " ";
                    }

                    isOpenParentheses = false;
                    whereValue = model.getPortfolioLocations().get(i10).getFile().getExtension();
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
                      && model.getPortfolioLocations().get(i10).getFile().getVersion() != 0) {

                    andCondition = " AND ";
                    if (isOpenParentheses) {
                      andCondition = " ";
                    }
                    query += " ";
                    whereValue =
                        new java.lang.Integer(
                            "" + model.getPortfolioLocations().get(i10).getFile().getVersion());
                    if (isDelete && fetchType == FetchType.EAGER) {
                      whereQuery = " File12.VERSION = ? ";
                      counter++;
                    } else {
                      whereQuery = " File12.version = :File12version" + j + " ";
                    }

                    isOpenParentheses = false;
                    whereValue =
                        new java.lang.Integer(
                            "" + model.getPortfolioLocations().get(i10).getFile().getVersion());
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
                if (!isExistRecord
                    && !isDelete
                    && fetchType == FetchType.EAGER
                    && model.getPortfolioLocations().get(i10).getLocationDetails() != null) {
                  int length13 = model.getPortfolioLocations().get(i10).getLocationDetails().size();

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
                              .getPortfolioLocations()
                              .get(i10)
                              .getLocationDetails()
                              .get(i13)
                              .getLocationDetailId()
                          != null) {

                        andCondition = " AND ";
                        if (isOpenParentheses) {
                          andCondition = " ";
                        }
                        query += " ";
                        whereValue =
                            model
                                .getPortfolioLocations()
                                .get(i10)
                                .getLocationDetails()
                                .get(i13)
                                .getLocationDetailId();
                        if (isDelete && fetchType == FetchType.EAGER) {
                          whereQuery = " LocationDetails13.id = ? ";
                          counter++;
                        } else {
                          whereQuery =
                              " LocationDetails13.locationDetailId = :LocationDetails13locationDetailId"
                                  + j
                                  + " ";
                        }

                        isOpenParentheses = false;
                        whereValue =
                            model
                                .getPortfolioLocations()
                                .get(i10)
                                .getLocationDetails()
                                .get(i13)
                                .getLocationDetailId();
                        query +=
                            (andCondition
                                + Facade.getStatement(
                                    statement,
                                    "LocationDetails13.locationDetailId",
                                    "LocationDetails13locationDetailId" + j,
                                    mapVariable,
                                    whereQuery,
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
                                  .getPortfolioLocations()
                                  .get(i10)
                                  .getLocationDetails()
                                  .get(i13)
                                  .getPortfolioLocation()
                              != null) {

                        if (model
                                .getPortfolioLocations()
                                .get(i10)
                                .getLocationDetails()
                                .get(i13)
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
                                  .getPortfolioLocations()
                                  .get(i10)
                                  .getLocationDetails()
                                  .get(i13)
                                  .getPortfolioLocation()
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
                                  .getPortfolioLocations()
                                  .get(i10)
                                  .getLocationDetails()
                                  .get(i13)
                                  .getPortfolioLocation()
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
                                    .getPortfolioLocations()
                                    .get(i10)
                                    .getLocationDetails()
                                    .get(i13)
                                    .getPortfolioLocation()
                                    .getFile()
                                != null) {

                          if (model
                                  .getPortfolioLocations()
                                  .get(i10)
                                  .getLocationDetails()
                                  .get(i13)
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
                                    .getPortfolioLocations()
                                    .get(i10)
                                    .getLocationDetails()
                                    .get(i13)
                                    .getPortfolioLocation()
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
                                    .getPortfolioLocations()
                                    .get(i10)
                                    .getLocationDetails()
                                    .get(i13)
                                    .getPortfolioLocation()
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
                                      .getPortfolioLocations()
                                      .get(i10)
                                      .getLocationDetails()
                                      .get(i13)
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
                                    .getPortfolioLocations()
                                    .get(i10)
                                    .getLocationDetails()
                                    .get(i13)
                                    .getPortfolioLocation()
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
                                    .getPortfolioLocations()
                                    .get(i10)
                                    .getLocationDetails()
                                    .get(i13)
                                    .getPortfolioLocation()
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
                                      .getPortfolioLocations()
                                      .get(i10)
                                      .getLocationDetails()
                                      .get(i13)
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
                                            .getPortfolioLocations()
                                            .get(i10)
                                            .getLocationDetails()
                                            .get(i13)
                                            .getPortfolioLocation()
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
                                            .getPortfolioLocations()
                                            .get(i10)
                                            .getLocationDetails()
                                            .get(i13)
                                            .getPortfolioLocation()
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
                            && true
                            && model
                                    .getPortfolioLocations()
                                    .get(i10)
                                    .getLocationDetails()
                                    .get(i13)
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
                                  .getPortfolioLocations()
                                  .get(i10)
                                  .getLocationDetails()
                                  .get(i13)
                                  .getPortfolioLocation()
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
                                  .getPortfolioLocations()
                                  .get(i10)
                                  .getLocationDetails()
                                  .get(i13)
                                  .getPortfolioLocation()
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
                                    .getPortfolioLocations()
                                    .get(i10)
                                    .getLocationDetails()
                                    .get(i13)
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
                                  .getPortfolioLocations()
                                  .get(i10)
                                  .getLocationDetails()
                                  .get(i13)
                                  .getPortfolioLocation()
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
                                  .getPortfolioLocations()
                                  .get(i10)
                                  .getLocationDetails()
                                  .get(i13)
                                  .getPortfolioLocation()
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
                                    .getPortfolioLocations()
                                    .get(i10)
                                    .getLocationDetails()
                                    .get(i13)
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
                                  .getPortfolioLocations()
                                  .get(i10)
                                  .getLocationDetails()
                                  .get(i13)
                                  .getPortfolioLocation()
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
                                  .getPortfolioLocations()
                                  .get(i10)
                                  .getLocationDetails()
                                  .get(i13)
                                  .getPortfolioLocation()
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
                                    .getPortfolioLocations()
                                    .get(i10)
                                    .getLocationDetails()
                                    .get(i13)
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
                                  .getPortfolioLocations()
                                  .get(i10)
                                  .getLocationDetails()
                                  .get(i13)
                                  .getPortfolioLocation()
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
                                  .getPortfolioLocations()
                                  .get(i10)
                                  .getLocationDetails()
                                  .get(i13)
                                  .getPortfolioLocation()
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
                                    .getPortfolioLocations()
                                    .get(i10)
                                    .getLocationDetails()
                                    .get(i13)
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
                                  .getPortfolioLocations()
                                  .get(i10)
                                  .getLocationDetails()
                                  .get(i13)
                                  .getPortfolioLocation()
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
                                  .getPortfolioLocations()
                                  .get(i10)
                                  .getLocationDetails()
                                  .get(i13)
                                  .getPortfolioLocation()
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
                                    .getPortfolioLocations()
                                    .get(i10)
                                    .getLocationDetails()
                                    .get(i13)
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
                                  .getPortfolioLocations()
                                  .get(i10)
                                  .getLocationDetails()
                                  .get(i13)
                                  .getPortfolioLocation()
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
                                  .getPortfolioLocations()
                                  .get(i10)
                                  .getLocationDetails()
                                  .get(i13)
                                  .getPortfolioLocation()
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
                                    .getPortfolioLocations()
                                    .get(i10)
                                    .getLocationDetails()
                                    .get(i13)
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
                                  .getPortfolioLocations()
                                  .get(i10)
                                  .getLocationDetails()
                                  .get(i13)
                                  .getPortfolioLocation()
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
                                  .getPortfolioLocations()
                                  .get(i10)
                                  .getLocationDetails()
                                  .get(i13)
                                  .getPortfolioLocation()
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
                                    .getPortfolioLocations()
                                    .get(i10)
                                    .getLocationDetails()
                                    .get(i13)
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
                                          .getPortfolioLocations()
                                          .get(i10)
                                          .getLocationDetails()
                                          .get(i13)
                                          .getPortfolioLocation()
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
                                          .getPortfolioLocations()
                                          .get(i10)
                                          .getLocationDetails()
                                          .get(i13)
                                          .getPortfolioLocation()
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
                      }
                      if (!isExistRecord
                          && !isDelete
                          && model
                                  .getPortfolioLocations()
                                  .get(i10)
                                  .getLocationDetails()
                                  .get(i13)
                                  .getFile()
                              != null) {

                        if (model
                                .getPortfolioLocations()
                                .get(i10)
                                .getLocationDetails()
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
                                  .getPortfolioLocations()
                                  .get(i10)
                                  .getLocationDetails()
                                  .get(i13)
                                  .getFile()
                                  .getId();
                          if (isDelete && fetchType == FetchType.EAGER) {
                            whereQuery = " File18.l4610flmtdataid = ? ";
                            counter++;
                          } else {
                            whereQuery = " File18.id = :File18id" + j + " ";
                          }

                          isOpenParentheses = false;
                          whereValue =
                              model
                                  .getPortfolioLocations()
                                  .get(i10)
                                  .getLocationDetails()
                                  .get(i13)
                                  .getFile()
                                  .getId();
                          query +=
                              (andCondition
                                  + Facade.getStatement(
                                      statement,
                                      "File18.id",
                                      "File18id" + j,
                                      mapVariable,
                                      whereQuery,
                                      whereValue,
                                      isExistRecord,
                                      isDelete,
                                      fetchType,
                                      counter));
                          whereQuery = "";
                        }
                        if (!isExistRecord
                            && !isDelete
                            && true
                            && model
                                    .getPortfolioLocations()
                                    .get(i10)
                                    .getLocationDetails()
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
                                  .getPortfolioLocations()
                                  .get(i10)
                                  .getLocationDetails()
                                  .get(i13)
                                  .getFile()
                                  .getExtension();
                          if (isDelete && fetchType == FetchType.EAGER) {
                            whereQuery = " File18.l4610extension = ? ";
                            counter++;
                          } else {
                            whereQuery = " File18.extension = :File18extension" + j + " ";
                          }

                          isOpenParentheses = false;
                          whereValue =
                              model
                                  .getPortfolioLocations()
                                  .get(i10)
                                  .getLocationDetails()
                                  .get(i13)
                                  .getFile()
                                  .getExtension();
                          query +=
                              (andCondition
                                  + Facade.getStatement(
                                      statement,
                                      "File18.extension",
                                      "File18extension" + j,
                                      mapVariable,
                                      whereQuery,
                                      whereValue,
                                      isExistRecord,
                                      isDelete,
                                      fetchType,
                                      counter));
                          whereQuery = "";
                        }
                        if (!isExistRecord
                            && !isDelete
                            && true
                            && model
                                    .getPortfolioLocations()
                                    .get(i10)
                                    .getLocationDetails()
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
                                          .getPortfolioLocations()
                                          .get(i10)
                                          .getLocationDetails()
                                          .get(i13)
                                          .getFile()
                                          .getVersion());
                          if (isDelete && fetchType == FetchType.EAGER) {
                            whereQuery = " File18.VERSION = ? ";
                            counter++;
                          } else {
                            whereQuery = " File18.version = :File18version" + j + " ";
                          }

                          isOpenParentheses = false;
                          whereValue =
                              new java.lang.Integer(
                                  ""
                                      + model
                                          .getPortfolioLocations()
                                          .get(i10)
                                          .getLocationDetails()
                                          .get(i13)
                                          .getFile()
                                          .getVersion());
                          query +=
                              (andCondition
                                  + Facade.getStatement(
                                      statement,
                                      "File18.version",
                                      "File18version" + j,
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
                                  .getPortfolioLocations()
                                  .get(i10)
                                  .getLocationDetails()
                                  .get(i13)
                                  .getAddress()
                              != null) {
                        if (!isDelete
                            && true
                            && model
                                    .getPortfolioLocations()
                                    .get(i10)
                                    .getLocationDetails()
                                    .get(i13)
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
                                  .getPortfolioLocations()
                                  .get(i10)
                                  .getLocationDetails()
                                  .get(i13)
                                  .getAddress()
                                  .getAddressLine1();
                          if (isDelete && fetchType == FetchType.EAGER) {
                            whereQuery = " LocationDetails13.addressLine1 = ? ";
                            counter++;
                          } else {
                            whereQuery =
                                " LocationDetails13.address.addressLine1 = :LocationDetails13addressaddressLine1"
                                    + j
                                    + " ";
                          }

                          isOpenParentheses = false;
                          whereValue =
                              model
                                  .getPortfolioLocations()
                                  .get(i10)
                                  .getLocationDetails()
                                  .get(i13)
                                  .getAddress()
                                  .getAddressLine1();
                          query +=
                              (andCondition
                                  + Facade.getStatement(
                                      statement,
                                      "LocationDetails13.address.addressLine1",
                                      "LocationDetails13addressaddressLine1" + j,
                                      mapVariable,
                                      whereQuery,
                                      whereValue,
                                      isExistRecord,
                                      isDelete,
                                      fetchType,
                                      counter));
                          whereQuery = "";
                        }
                        if (!isExistRecord
                            && !isDelete
                            && true
                            && model
                                    .getPortfolioLocations()
                                    .get(i10)
                                    .getLocationDetails()
                                    .get(i13)
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
                                  .getPortfolioLocations()
                                  .get(i10)
                                  .getLocationDetails()
                                  .get(i13)
                                  .getAddress()
                                  .getAddressLine2();
                          if (isDelete && fetchType == FetchType.EAGER) {
                            whereQuery = " LocationDetails13.addressLine2 = ? ";
                            counter++;
                          } else {
                            whereQuery =
                                " LocationDetails13.address.addressLine2 = :LocationDetails13addressaddressLine2"
                                    + j
                                    + " ";
                          }

                          isOpenParentheses = false;
                          whereValue =
                              model
                                  .getPortfolioLocations()
                                  .get(i10)
                                  .getLocationDetails()
                                  .get(i13)
                                  .getAddress()
                                  .getAddressLine2();
                          query +=
                              (andCondition
                                  + Facade.getStatement(
                                      statement,
                                      "LocationDetails13.address.addressLine2",
                                      "LocationDetails13addressaddressLine2" + j,
                                      mapVariable,
                                      whereQuery,
                                      whereValue,
                                      isExistRecord,
                                      isDelete,
                                      fetchType,
                                      counter));
                          whereQuery = "";
                        }
                        if (!isExistRecord
                            && !isDelete
                            && true
                            && model
                                    .getPortfolioLocations()
                                    .get(i10)
                                    .getLocationDetails()
                                    .get(i13)
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
                                  .getPortfolioLocations()
                                  .get(i10)
                                  .getLocationDetails()
                                  .get(i13)
                                  .getAddress()
                                  .getCity();
                          if (isDelete && fetchType == FetchType.EAGER) {
                            whereQuery = " LocationDetails13.city = ? ";
                            counter++;
                          } else {
                            whereQuery =
                                " LocationDetails13.address.city = :LocationDetails13addresscity"
                                    + j
                                    + " ";
                          }

                          isOpenParentheses = false;
                          whereValue =
                              model
                                  .getPortfolioLocations()
                                  .get(i10)
                                  .getLocationDetails()
                                  .get(i13)
                                  .getAddress()
                                  .getCity();
                          query +=
                              (andCondition
                                  + Facade.getStatement(
                                      statement,
                                      "LocationDetails13.address.city",
                                      "LocationDetails13addresscity" + j,
                                      mapVariable,
                                      whereQuery,
                                      whereValue,
                                      isExistRecord,
                                      isDelete,
                                      fetchType,
                                      counter));
                          whereQuery = "";
                        }
                        if (!isExistRecord
                            && !isDelete
                            && true
                            && model
                                    .getPortfolioLocations()
                                    .get(i10)
                                    .getLocationDetails()
                                    .get(i13)
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
                                  .getPortfolioLocations()
                                  .get(i10)
                                  .getLocationDetails()
                                  .get(i13)
                                  .getAddress()
                                  .getState();
                          if (isDelete && fetchType == FetchType.EAGER) {
                            whereQuery = " LocationDetails13.state = ? ";
                            counter++;
                          } else {
                            whereQuery =
                                " LocationDetails13.address.state = :LocationDetails13addressstate"
                                    + j
                                    + " ";
                          }

                          isOpenParentheses = false;
                          whereValue =
                              model
                                  .getPortfolioLocations()
                                  .get(i10)
                                  .getLocationDetails()
                                  .get(i13)
                                  .getAddress()
                                  .getState();
                          query +=
                              (andCondition
                                  + Facade.getStatement(
                                      statement,
                                      "LocationDetails13.address.state",
                                      "LocationDetails13addressstate" + j,
                                      mapVariable,
                                      whereQuery,
                                      whereValue,
                                      isExistRecord,
                                      isDelete,
                                      fetchType,
                                      counter));
                          whereQuery = "";
                        }
                        if (!isExistRecord
                            && !isDelete
                            && true
                            && model
                                    .getPortfolioLocations()
                                    .get(i10)
                                    .getLocationDetails()
                                    .get(i13)
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
                                  .getPortfolioLocations()
                                  .get(i10)
                                  .getLocationDetails()
                                  .get(i13)
                                  .getAddress()
                                  .getCountry();
                          if (isDelete && fetchType == FetchType.EAGER) {
                            whereQuery = " LocationDetails13.country = ? ";
                            counter++;
                          } else {
                            whereQuery =
                                " LocationDetails13.address.country = :LocationDetails13addresscountry"
                                    + j
                                    + " ";
                          }

                          isOpenParentheses = false;
                          whereValue =
                              model
                                  .getPortfolioLocations()
                                  .get(i10)
                                  .getLocationDetails()
                                  .get(i13)
                                  .getAddress()
                                  .getCountry();
                          query +=
                              (andCondition
                                  + Facade.getStatement(
                                      statement,
                                      "LocationDetails13.address.country",
                                      "LocationDetails13addresscountry" + j,
                                      mapVariable,
                                      whereQuery,
                                      whereValue,
                                      isExistRecord,
                                      isDelete,
                                      fetchType,
                                      counter));
                          whereQuery = "";
                        }
                        if (!isExistRecord
                            && !isDelete
                            && true
                            && model
                                    .getPortfolioLocations()
                                    .get(i10)
                                    .getLocationDetails()
                                    .get(i13)
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
                                  .getPortfolioLocations()
                                  .get(i10)
                                  .getLocationDetails()
                                  .get(i13)
                                  .getAddress()
                                  .getZipCode();
                          if (isDelete && fetchType == FetchType.EAGER) {
                            whereQuery = " LocationDetails13.zipCode = ? ";
                            counter++;
                          } else {
                            whereQuery =
                                " LocationDetails13.address.zipCode = :LocationDetails13addresszipCode"
                                    + j
                                    + " ";
                          }

                          isOpenParentheses = false;
                          whereValue =
                              model
                                  .getPortfolioLocations()
                                  .get(i10)
                                  .getLocationDetails()
                                  .get(i13)
                                  .getAddress()
                                  .getZipCode();
                          query +=
                              (andCondition
                                  + Facade.getStatement(
                                      statement,
                                      "LocationDetails13.address.zipCode",
                                      "LocationDetails13addresszipCode" + j,
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
                    && true
                    && model.getPortfolioLocations().get(i10).getProvince() != null) {

                  andCondition = " AND ";
                  if (isOpenParentheses) {
                    andCondition = " ";
                  }
                  query += " ";
                  whereValue = model.getPortfolioLocations().get(i10).getProvince();
                  if (isDelete && fetchType == FetchType.EAGER) {
                    whereQuery = " PortfolioLocation10.province = ? ";
                    counter++;
                  } else {
                    whereQuery =
                        " PortfolioLocation10.province = :PortfolioLocation10province" + j + " ";
                  }

                  isOpenParentheses = false;
                  whereValue = model.getPortfolioLocations().get(i10).getProvince();
                  query +=
                      (andCondition
                          + Facade.getStatement(
                              statement,
                              "PortfolioLocation10.province",
                              "PortfolioLocation10province" + j,
                              mapVariable,
                              whereQuery,
                              whereValue,
                              isExistRecord,
                              isDelete,
                              fetchType,
                              counter));
                  whereQuery = "";
                }
                if (!isExistRecord
                    && !isDelete
                    && true
                    && model.getPortfolioLocations().get(i10).getCity() != null) {

                  andCondition = " AND ";
                  if (isOpenParentheses) {
                    andCondition = " ";
                  }
                  query += " ";
                  whereValue = model.getPortfolioLocations().get(i10).getCity();
                  if (isDelete && fetchType == FetchType.EAGER) {
                    whereQuery = " PortfolioLocation10.city = ? ";
                    counter++;
                  } else {
                    whereQuery = " PortfolioLocation10.city = :PortfolioLocation10city" + j + " ";
                  }

                  isOpenParentheses = false;
                  whereValue = model.getPortfolioLocations().get(i10).getCity();
                  query +=
                      (andCondition
                          + Facade.getStatement(
                              statement,
                              "PortfolioLocation10.city",
                              "PortfolioLocation10city" + j,
                              mapVariable,
                              whereQuery,
                              whereValue,
                              isExistRecord,
                              isDelete,
                              fetchType,
                              counter));
                  whereQuery = "";
                }
                if (!isExistRecord
                    && !isDelete
                    && true
                    && model.getPortfolioLocations().get(i10).getIndstrltwn() != null) {

                  andCondition = " AND ";
                  if (isOpenParentheses) {
                    andCondition = " ";
                  }
                  query += " ";
                  whereValue = model.getPortfolioLocations().get(i10).getIndstrltwn();
                  if (isDelete && fetchType == FetchType.EAGER) {
                    whereQuery = " PortfolioLocation10.indstrltwn = ? ";
                    counter++;
                  } else {
                    whereQuery =
                        " PortfolioLocation10.indstrltwn = :PortfolioLocation10indstrltwn"
                            + j
                            + " ";
                  }

                  isOpenParentheses = false;
                  whereValue = model.getPortfolioLocations().get(i10).getIndstrltwn();
                  query +=
                      (andCondition
                          + Facade.getStatement(
                              statement,
                              "PortfolioLocation10.indstrltwn",
                              "PortfolioLocation10indstrltwn" + j,
                              mapVariable,
                              whereQuery,
                              whereValue,
                              isExistRecord,
                              isDelete,
                              fetchType,
                              counter));
                  whereQuery = "";
                }
                if (!isExistRecord
                    && !isDelete
                    && true
                    && model.getPortfolioLocations().get(i10).getAddress() != null) {

                  andCondition = " AND ";
                  if (isOpenParentheses) {
                    andCondition = " ";
                  }
                  query += " ";
                  whereValue = model.getPortfolioLocations().get(i10).getAddress();
                  if (isDelete && fetchType == FetchType.EAGER) {
                    whereQuery = " PortfolioLocation10.address = ? ";
                    counter++;
                  } else {
                    whereQuery =
                        " PortfolioLocation10.address = :PortfolioLocation10address" + j + " ";
                  }

                  isOpenParentheses = false;
                  whereValue = model.getPortfolioLocations().get(i10).getAddress();
                  query +=
                      (andCondition
                          + Facade.getStatement(
                              statement,
                              "PortfolioLocation10.address",
                              "PortfolioLocation10address" + j,
                              mapVariable,
                              whereQuery,
                              whereValue,
                              isExistRecord,
                              isDelete,
                              fetchType,
                              counter));
                  whereQuery = "";
                }
                if (!isExistRecord
                    && !isDelete
                    && true
                    && model.getPortfolioLocations().get(i10).getStableStatusType() != null) {

                  andCondition = " AND ";
                  if (isOpenParentheses) {
                    andCondition = " ";
                  }
                  query += " ";
                  whereValue = model.getPortfolioLocations().get(i10).getStableStatusType();
                  if (isDelete && fetchType == FetchType.EAGER) {
                    whereQuery = " PortfolioLocation10.stableStatusType = ? ";
                    counter++;
                  } else {
                    whereQuery =
                        " PortfolioLocation10.stableStatusType = :PortfolioLocation10stableStatusType"
                            + j
                            + " ";
                  }

                  isOpenParentheses = false;
                  whereValue = model.getPortfolioLocations().get(i10).getStableStatusType();
                  query +=
                      (andCondition
                          + Facade.getStatement(
                              statement,
                              "PortfolioLocation10.stableStatusType",
                              "PortfolioLocation10stableStatusType" + j,
                              mapVariable,
                              whereQuery,
                              whereValue,
                              isExistRecord,
                              isDelete,
                              fetchType,
                              counter));
                  whereQuery = "";
                }
                if (!isExistRecord
                    && !isDelete
                    && true
                    && model.getPortfolioLocations().get(i10).getOwnerStatusType() != null) {

                  andCondition = " AND ";
                  if (isOpenParentheses) {
                    andCondition = " ";
                  }
                  query += " ";
                  whereValue = model.getPortfolioLocations().get(i10).getOwnerStatusType();
                  if (isDelete && fetchType == FetchType.EAGER) {
                    whereQuery = " PortfolioLocation10.ownerStatusType = ? ";
                    counter++;
                  } else {
                    whereQuery =
                        " PortfolioLocation10.ownerStatusType = :PortfolioLocation10ownerStatusType"
                            + j
                            + " ";
                  }

                  isOpenParentheses = false;
                  whereValue = model.getPortfolioLocations().get(i10).getOwnerStatusType();
                  query +=
                      (andCondition
                          + Facade.getStatement(
                              statement,
                              "PortfolioLocation10.ownerStatusType",
                              "PortfolioLocation10ownerStatusType" + j,
                              mapVariable,
                              whereQuery,
                              whereValue,
                              isExistRecord,
                              isDelete,
                              fetchType,
                              counter));
                  whereQuery = "";
                }
                if (!isExistRecord
                    && !isDelete
                    && true
                    && model.getPortfolioLocations().get(i10).getRegionalStatusType() != null) {

                  andCondition = " AND ";
                  if (isOpenParentheses) {
                    andCondition = " ";
                  }
                  query += " ";
                  whereValue = model.getPortfolioLocations().get(i10).getRegionalStatusType();
                  if (isDelete && fetchType == FetchType.EAGER) {
                    whereQuery = " PortfolioLocation10.regionalStatusType = ? ";
                    counter++;
                  } else {
                    whereQuery =
                        " PortfolioLocation10.regionalStatusType = :PortfolioLocation10regionalStatusType"
                            + j
                            + " ";
                  }

                  isOpenParentheses = false;
                  whereValue = model.getPortfolioLocations().get(i10).getRegionalStatusType();
                  query +=
                      (andCondition
                          + Facade.getStatement(
                              statement,
                              "PortfolioLocation10.regionalStatusType",
                              "PortfolioLocation10regionalStatusType" + j,
                              mapVariable,
                              whereQuery,
                              whereValue,
                              isExistRecord,
                              isDelete,
                              fetchType,
                              counter));
                  whereQuery = "";
                }
                if (!isExistRecord
                    && !isDelete
                    && true
                    && model.getPortfolioLocations().get(i10).getVersion() != 0) {

                  andCondition = " AND ";
                  if (isOpenParentheses) {
                    andCondition = " ";
                  }
                  query += " ";
                  whereValue =
                      new java.lang.Integer(
                          "" + model.getPortfolioLocations().get(i10).getVersion());
                  if (isDelete && fetchType == FetchType.EAGER) {
                    whereQuery = " PortfolioLocation10.VERSION = ? ";
                    counter++;
                  } else {
                    whereQuery =
                        " PortfolioLocation10.version = :PortfolioLocation10version" + j + " ";
                  }

                  isOpenParentheses = false;
                  whereValue =
                      new java.lang.Integer(
                          "" + model.getPortfolioLocations().get(i10).getVersion());
                  query +=
                      (andCondition
                          + Facade.getStatement(
                              statement,
                              "PortfolioLocation10.version",
                              "PortfolioLocation10version" + j,
                              mapVariable,
                              whereQuery,
                              whereValue,
                              isExistRecord,
                              isDelete,
                              fetchType,
                              counter));
                  whereQuery = "";
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
              && fetchType == FetchType.EAGER
              && model.getReceptions() != null) {
            int length19 = model.getReceptions().size();

            if (length19 > 0) {
              if (isOpenParentheses) {
                query += "  ( ";
              } else {
                query += " AND ( ";
              }
              isOpenParentheses = true;

              for (int i19 = 0; i19 < length19; i19++) {

                java.lang.String beforeCondition19 = "";
                java.lang.String prefix19 = " OR ";
                if (i19 == 0) {
                  prefix19 = "";
                }
                if (length19 > 1) {

                  query += (prefix19 + "(");
                  isOpenParentheses = true;

                } else {

                  query += (prefix19);
                  if (!prefix19.trim().equals("")) {
                    isOpenParentheses = false;
                  }
                }

                if (model.getReceptions().get(i19).getReceptionNo() != null) {

                  andCondition = " AND ";
                  if (isOpenParentheses) {
                    andCondition = " ";
                  }
                  query += " ";
                  whereValue = model.getReceptions().get(i19).getReceptionNo();
                  if (isDelete && fetchType == FetchType.EAGER) {
                    whereQuery = " Reception19.receptionNo = ? ";
                    counter++;
                  } else {
                    whereQuery = " Reception19.receptionNo = :Reception19receptionNo" + j + " ";
                  }

                  isOpenParentheses = false;
                  whereValue = model.getReceptions().get(i19).getReceptionNo();
                  query +=
                      (andCondition
                          + Facade.getStatement(
                              statement,
                              "Reception19.receptionNo",
                              "Reception19receptionNo" + j,
                              mapVariable,
                              whereQuery,
                              whereValue,
                              isExistRecord,
                              isDelete,
                              fetchType,
                              counter));
                  whereQuery = "";
                }
                if (!isExistRecord
                    && !isDelete
                    && true
                    && model.getReceptions().get(i19).getChannel() != null) {

                  andCondition = " AND ";
                  if (isOpenParentheses) {
                    andCondition = " ";
                  }
                  query += " ";
                  whereValue = model.getReceptions().get(i19).getChannel();
                  if (isDelete && fetchType == FetchType.EAGER) {
                    whereQuery = " Reception19.channel = ? ";
                    counter++;
                  } else {
                    whereQuery = " Reception19.channel = :Reception19channel" + j + " ";
                  }

                  isOpenParentheses = false;
                  whereValue = model.getReceptions().get(i19).getChannel();
                  query +=
                      (andCondition
                          + Facade.getStatement(
                              statement,
                              "Reception19.channel",
                              "Reception19channel" + j,
                              mapVariable,
                              whereQuery,
                              whereValue,
                              isExistRecord,
                              isDelete,
                              fetchType,
                              counter));
                  whereQuery = "";
                }
                if (!isExistRecord
                    && !isDelete
                    && true
                    && model.getReceptions().get(i19).getExternalResource() != null) {

                  andCondition = " AND ";
                  if (isOpenParentheses) {
                    andCondition = " ";
                  }
                  query += " ";
                  whereValue = model.getReceptions().get(i19).getExternalResource();
                  if (isDelete && fetchType == FetchType.EAGER) {
                    whereQuery = " Reception19.externalResource = ? ";
                    counter++;
                  } else {
                    whereQuery =
                        " Reception19.externalResource = :Reception19externalResource" + j + " ";
                  }

                  isOpenParentheses = false;
                  whereValue = model.getReceptions().get(i19).getExternalResource();
                  query +=
                      (andCondition
                          + Facade.getStatement(
                              statement,
                              "Reception19.externalResource",
                              "Reception19externalResource" + j,
                              mapVariable,
                              whereQuery,
                              whereValue,
                              isExistRecord,
                              isDelete,
                              fetchType,
                              counter));
                  whereQuery = "";
                }
                if (!isExistRecord
                    && !isDelete
                    && true
                    && model.getReceptions().get(i19).getReceptionTitle() != null) {

                  andCondition = " AND ";
                  if (isOpenParentheses) {
                    andCondition = " ";
                  }
                  query += " ";

                  whereValue = model.getReceptions().get(i19).getReceptionTitle() + "%";
                  if (isDelete && fetchType == FetchType.EAGER) {
                    whereQuery = " Reception19.receptionTitle = ? ";
                    counter++;
                  } else {
                    whereQuery =
                        " Reception19.receptionTitle LIKE :Reception19receptionTitle" + j + " ";
                  }

                  isOpenParentheses = false;
                  if (isExistRecord || isDelete) {
                    if (isDelete && fetchType == FetchType.EAGER) {
                      whereQuery = " Reception19.receptionTitle = ? ";
                      counter++;
                    } else {
                      whereValue = model.getReceptions().get(i19).getReceptionTitle();
                    }
                  } else {
                    whereValue = model.getReceptions().get(i19).getReceptionTitle() + "%";
                  }

                  query +=
                      (andCondition
                          + Facade.getStatement(
                              statement,
                              "Reception19.receptionTitle",
                              "Reception19receptionTitle" + j,
                              mapVariable,
                              whereQuery,
                              whereValue,
                              isExistRecord,
                              isDelete,
                              fetchType,
                              counter));
                  whereQuery = "";
                }
                if (!isExistRecord
                    && !isDelete
                    && true
                    && model.getReceptions().get(i19).getProductType() != null) {

                  andCondition = " AND ";
                  if (isOpenParentheses) {
                    andCondition = " ";
                  }
                  query += " ";
                  whereValue = model.getReceptions().get(i19).getProductType();
                  if (isDelete && fetchType == FetchType.EAGER) {
                    whereQuery = " Reception19.productType = ? ";
                    counter++;
                  } else {
                    whereQuery = " Reception19.productType = :Reception19productType" + j + " ";
                  }

                  isOpenParentheses = false;
                  whereValue = model.getReceptions().get(i19).getProductType();
                  query +=
                      (andCondition
                          + Facade.getStatement(
                              statement,
                              "Reception19.productType",
                              "Reception19productType" + j,
                              mapVariable,
                              whereQuery,
                              whereValue,
                              isExistRecord,
                              isDelete,
                              fetchType,
                              counter));
                  whereQuery = "";
                }
                if (!isExistRecord
                    && !isDelete
                    && true
                    && model.getReceptions().get(i19).getLongLoanType() != null) {

                  andCondition = " AND ";
                  if (isOpenParentheses) {
                    andCondition = " ";
                  }
                  query += " ";
                  whereValue = model.getReceptions().get(i19).getLongLoanType();
                  if (isDelete && fetchType == FetchType.EAGER) {
                    whereQuery = " Reception19.longLoanType = ? ";
                    counter++;
                  } else {
                    whereQuery = " Reception19.longLoanType = :Reception19longLoanType" + j + " ";
                  }

                  isOpenParentheses = false;
                  whereValue = model.getReceptions().get(i19).getLongLoanType();
                  query +=
                      (andCondition
                          + Facade.getStatement(
                              statement,
                              "Reception19.longLoanType",
                              "Reception19longLoanType" + j,
                              mapVariable,
                              whereQuery,
                              whereValue,
                              isExistRecord,
                              isDelete,
                              fetchType,
                              counter));
                  whereQuery = "";
                }
                if (!isExistRecord
                    && !isDelete
                    && true
                    && model.getReceptions().get(i19).getFinancingPurpose() != null) {

                  andCondition = " AND ";
                  if (isOpenParentheses) {
                    andCondition = " ";
                  }
                  query += " ";
                  whereValue = model.getReceptions().get(i19).getFinancingPurpose();
                  if (isDelete && fetchType == FetchType.EAGER) {
                    whereQuery = " Reception19.financingPurpose = ? ";
                    counter++;
                  } else {
                    whereQuery =
                        " Reception19.financingPurpose = :Reception19financingPurpose" + j + " ";
                  }

                  isOpenParentheses = false;
                  whereValue = model.getReceptions().get(i19).getFinancingPurpose();
                  query +=
                      (andCondition
                          + Facade.getStatement(
                              statement,
                              "Reception19.financingPurpose",
                              "Reception19financingPurpose" + j,
                              mapVariable,
                              whereQuery,
                              whereValue,
                              isExistRecord,
                              isDelete,
                              fetchType,
                              counter));
                  whereQuery = "";
                }
                if (!isExistRecord
                    && !isDelete
                    && true
                    && model.getReceptions().get(i19).getLow141() != null) {

                  andCondition = " AND ";
                  if (isOpenParentheses) {
                    andCondition = " ";
                  }
                  query += " ";
                  whereValue = model.getReceptions().get(i19).getLow141();
                  if (isDelete && fetchType == FetchType.EAGER) {
                    whereQuery = " Reception19.low141 = ? ";
                    counter++;
                  } else {
                    whereQuery = " Reception19.low141 = :Reception19low141" + j + " ";
                  }

                  isOpenParentheses = false;
                  whereValue = model.getReceptions().get(i19).getLow141();
                  query +=
                      (andCondition
                          + Facade.getStatement(
                              statement,
                              "Reception19.low141",
                              "Reception19low141" + j,
                              mapVariable,
                              whereQuery,
                              whereValue,
                              isExistRecord,
                              isDelete,
                              fetchType,
                              counter));
                  whereQuery = "";
                }
                if (!isExistRecord
                    && !isDelete
                    && true
                    && model.getReceptions().get(i19).getShortTermLoanType() != null) {

                  andCondition = " AND ";
                  if (isOpenParentheses) {
                    andCondition = " ";
                  }
                  query += " ";
                  whereValue = model.getReceptions().get(i19).getShortTermLoanType();
                  if (isDelete && fetchType == FetchType.EAGER) {
                    whereQuery = " Reception19.shortTermLoanType = ? ";
                    counter++;
                  } else {
                    whereQuery =
                        " Reception19.shortTermLoanType = :Reception19shortTermLoanType" + j + " ";
                  }

                  isOpenParentheses = false;
                  whereValue = model.getReceptions().get(i19).getShortTermLoanType();
                  query +=
                      (andCondition
                          + Facade.getStatement(
                              statement,
                              "Reception19.shortTermLoanType",
                              "Reception19shortTermLoanType" + j,
                              mapVariable,
                              whereQuery,
                              whereValue,
                              isExistRecord,
                              isDelete,
                              fetchType,
                              counter));
                  whereQuery = "";
                }
                if (!isExistRecord
                    && !isDelete
                    && true
                    && model.getReceptions().get(i19).getDate() != null) {

                  andCondition = " AND ";
                  if (isOpenParentheses) {
                    andCondition = " ";
                  }
                  query += " ";
                  whereValue = model.getReceptions().get(i19).getDate();
                  if (isDelete && fetchType == FetchType.EAGER) {
                    whereQuery = " Reception19.date1 = ? ";
                    counter++;
                  } else {
                    whereQuery = " Reception19.date = :Reception19date" + j + " ";
                  }

                  isOpenParentheses = false;
                  whereValue = model.getReceptions().get(i19).getDate();
                  query +=
                      (andCondition
                          + Facade.getStatement(
                              statement,
                              "Reception19.date",
                              "Reception19date" + j,
                              mapVariable,
                              whereQuery,
                              whereValue,
                              isExistRecord,
                              isDelete,
                              fetchType,
                              counter));
                  whereQuery = "";
                }
                if (!isExistRecord
                    && !isDelete
                    && true
                    && model.getReceptions().get(i19).getRejLetterDate() != null) {

                  andCondition = " AND ";
                  if (isOpenParentheses) {
                    andCondition = " ";
                  }
                  query += " ";
                  whereValue = model.getReceptions().get(i19).getRejLetterDate();
                  if (isDelete && fetchType == FetchType.EAGER) {
                    whereQuery = " Reception19.rejLetterDate = ? ";
                    counter++;
                  } else {
                    whereQuery = " Reception19.rejLetterDate = :Reception19rejLetterDate" + j + " ";
                  }

                  isOpenParentheses = false;
                  whereValue = model.getReceptions().get(i19).getRejLetterDate();
                  query +=
                      (andCondition
                          + Facade.getStatement(
                              statement,
                              "Reception19.rejLetterDate",
                              "Reception19rejLetterDate" + j,
                              mapVariable,
                              whereQuery,
                              whereValue,
                              isExistRecord,
                              isDelete,
                              fetchType,
                              counter));
                  whereQuery = "";
                }
                if (!isExistRecord
                    && !isDelete
                    && true
                    && model.getReceptions().get(i19).getCustomerCode() != null) {

                  andCondition = " AND ";
                  if (isOpenParentheses) {
                    andCondition = " ";
                  }
                  query += " ";

                  whereValue = "%" + model.getReceptions().get(i19).getCustomerCode();
                  if (isDelete && fetchType == FetchType.EAGER) {
                    whereQuery = " Reception19.customerCode = ? ";
                    counter++;
                  } else {
                    whereQuery =
                        " Reception19.customerCode LIKE :Reception19customerCode" + j + " ";
                  }

                  isOpenParentheses = false;
                  if (isExistRecord || isDelete) {
                    if (isDelete && fetchType == FetchType.EAGER) {
                      whereQuery = " Reception19.customerCode = ? ";
                      counter++;
                    } else {
                      whereValue = model.getReceptions().get(i19).getCustomerCode();
                    }
                  } else {
                    whereValue = "%" + model.getReceptions().get(i19).getCustomerCode();
                  }

                  query +=
                      (andCondition
                          + Facade.getStatement(
                              statement,
                              "Reception19.customerCode",
                              "Reception19customerCode" + j,
                              mapVariable,
                              whereQuery,
                              whereValue,
                              isExistRecord,
                              isDelete,
                              fetchType,
                              counter));
                  whereQuery = "";
                }
                if (!isExistRecord
                    && !isDelete
                    && true
                    && model.getReceptions().get(i19).getBranch() != null) {

                  andCondition = " AND ";
                  if (isOpenParentheses) {
                    andCondition = " ";
                  }
                  query += " ";

                  whereValue = "%" + model.getReceptions().get(i19).getBranch() + "%";
                  if (isDelete && fetchType == FetchType.EAGER) {
                    whereQuery = " Reception19.branch = ? ";
                    counter++;
                  } else {
                    whereQuery = " Reception19.branch LIKE :Reception19branch" + j + " ";
                  }

                  isOpenParentheses = false;
                  if (isExistRecord || isDelete) {
                    if (isDelete && fetchType == FetchType.EAGER) {
                      whereQuery = " Reception19.branch = ? ";
                      counter++;
                    } else {
                      whereValue = model.getReceptions().get(i19).getBranch();
                    }
                  } else {
                    whereValue = "%" + model.getReceptions().get(i19).getBranch() + "%";
                  }

                  query +=
                      (andCondition
                          + Facade.getStatement(
                              statement,
                              "Reception19.branch",
                              "Reception19branch" + j,
                              mapVariable,
                              whereQuery,
                              whereValue,
                              isExistRecord,
                              isDelete,
                              fetchType,
                              counter));
                  whereQuery = "";
                }
                if (!isExistRecord
                    && !isDelete
                    && true
                    && model.getReceptions().get(i19).getReceptionState() != null) {

                  andCondition = " AND ";
                  if (isOpenParentheses) {
                    andCondition = " ";
                  }
                  query += " ";
                  whereValue = model.getReceptions().get(i19).getReceptionState();
                  if (isDelete && fetchType == FetchType.EAGER) {
                    whereQuery = " Reception19.receptionState = ? ";
                    counter++;
                  } else {
                    whereQuery =
                        " Reception19.receptionState = :Reception19receptionState" + j + " ";
                  }

                  isOpenParentheses = false;
                  whereValue = model.getReceptions().get(i19).getReceptionState();
                  query +=
                      (andCondition
                          + Facade.getStatement(
                              statement,
                              "Reception19.receptionState",
                              "Reception19receptionState" + j,
                              mapVariable,
                              whereQuery,
                              whereValue,
                              isExistRecord,
                              isDelete,
                              fetchType,
                              counter));
                  whereQuery = "";
                }
                if (!isExistRecord
                    && !isDelete
                    && true
                    && model.getReceptions().get(i19).getRepresentativeRefType() != null) {

                  andCondition = " AND ";
                  if (isOpenParentheses) {
                    andCondition = " ";
                  }
                  query += " ";
                  whereValue = model.getReceptions().get(i19).getRepresentativeRefType();
                  if (isDelete && fetchType == FetchType.EAGER) {
                    whereQuery = " Reception19.representativeRefType = ? ";
                    counter++;
                  } else {
                    whereQuery =
                        " Reception19.representativeRefType = :Reception19representativeRefType"
                            + j
                            + " ";
                  }

                  isOpenParentheses = false;
                  whereValue = model.getReceptions().get(i19).getRepresentativeRefType();
                  query +=
                      (andCondition
                          + Facade.getStatement(
                              statement,
                              "Reception19.representativeRefType",
                              "Reception19representativeRefType" + j,
                              mapVariable,
                              whereQuery,
                              whereValue,
                              isExistRecord,
                              isDelete,
                              fetchType,
                              counter));
                  whereQuery = "";
                }
                if (!isExistRecord
                    && !isDelete
                    && true
                    && model.getReceptions().get(i19).getApplicantCreditType() != null) {

                  andCondition = " AND ";
                  if (isOpenParentheses) {
                    andCondition = " ";
                  }
                  query += " ";
                  whereValue = model.getReceptions().get(i19).getApplicantCreditType();
                  if (isDelete && fetchType == FetchType.EAGER) {
                    whereQuery = " Reception19.applicantCreditType = ? ";
                    counter++;
                  } else {
                    whereQuery =
                        " Reception19.applicantCreditType = :Reception19applicantCreditType"
                            + j
                            + " ";
                  }

                  isOpenParentheses = false;
                  whereValue = model.getReceptions().get(i19).getApplicantCreditType();
                  query +=
                      (andCondition
                          + Facade.getStatement(
                              statement,
                              "Reception19.applicantCreditType",
                              "Reception19applicantCreditType" + j,
                              mapVariable,
                              whereQuery,
                              whereValue,
                              isExistRecord,
                              isDelete,
                              fetchType,
                              counter));
                  whereQuery = "";
                }
                if (!isExistRecord
                    && !isDelete
                    && true
                    && model.getReceptions().get(i19).getUnderTakeHisType() != null) {

                  andCondition = " AND ";
                  if (isOpenParentheses) {
                    andCondition = " ";
                  }
                  query += " ";
                  whereValue = model.getReceptions().get(i19).getUnderTakeHisType();
                  if (isDelete && fetchType == FetchType.EAGER) {
                    whereQuery = " Reception19.underTakeHisType = ? ";
                    counter++;
                  } else {
                    whereQuery =
                        " Reception19.underTakeHisType = :Reception19underTakeHisType" + j + " ";
                  }

                  isOpenParentheses = false;
                  whereValue = model.getReceptions().get(i19).getUnderTakeHisType();
                  query +=
                      (andCondition
                          + Facade.getStatement(
                              statement,
                              "Reception19.underTakeHisType",
                              "Reception19underTakeHisType" + j,
                              mapVariable,
                              whereQuery,
                              whereValue,
                              isExistRecord,
                              isDelete,
                              fetchType,
                              counter));
                  whereQuery = "";
                }
                if (!isExistRecord
                    && !isDelete
                    && true
                    && model.getReceptions().get(i19).getCheckRejectType() != null) {

                  andCondition = " AND ";
                  if (isOpenParentheses) {
                    andCondition = " ";
                  }
                  query += " ";
                  whereValue = model.getReceptions().get(i19).getCheckRejectType();
                  if (isDelete && fetchType == FetchType.EAGER) {
                    whereQuery = " Reception19.checkRejectType = ? ";
                    counter++;
                  } else {
                    whereQuery =
                        " Reception19.checkRejectType = :Reception19checkRejectType" + j + " ";
                  }

                  isOpenParentheses = false;
                  whereValue = model.getReceptions().get(i19).getCheckRejectType();
                  query +=
                      (andCondition
                          + Facade.getStatement(
                              statement,
                              "Reception19.checkRejectType",
                              "Reception19checkRejectType" + j,
                              mapVariable,
                              whereQuery,
                              whereValue,
                              isExistRecord,
                              isDelete,
                              fetchType,
                              counter));
                  whereQuery = "";
                }
                if (!isExistRecord
                    && !isDelete
                    && true
                    && model.getReceptions().get(i19).getCashFocusStatusType() != null) {

                  andCondition = " AND ";
                  if (isOpenParentheses) {
                    andCondition = " ";
                  }
                  query += " ";
                  whereValue = model.getReceptions().get(i19).getCashFocusStatusType();
                  if (isDelete && fetchType == FetchType.EAGER) {
                    whereQuery = " Reception19.cashFocusStatusType = ? ";
                    counter++;
                  } else {
                    whereQuery =
                        " Reception19.cashFocusStatusType = :Reception19cashFocusStatusType"
                            + j
                            + " ";
                  }

                  isOpenParentheses = false;
                  whereValue = model.getReceptions().get(i19).getCashFocusStatusType();
                  query +=
                      (andCondition
                          + Facade.getStatement(
                              statement,
                              "Reception19.cashFocusStatusType",
                              "Reception19cashFocusStatusType" + j,
                              mapVariable,
                              whereQuery,
                              whereValue,
                              isExistRecord,
                              isDelete,
                              fetchType,
                              counter));
                  whereQuery = "";
                }
                if (!isExistRecord
                    && !isDelete
                    && true
                    && model.getReceptions().get(i19).getEquation() != null) {

                  andCondition = " AND ";
                  if (isOpenParentheses) {
                    andCondition = " ";
                  }
                  query += " ";
                  whereValue = model.getReceptions().get(i19).getEquation();
                  if (isDelete && fetchType == FetchType.EAGER) {
                    whereQuery = " Reception19.equation = ? ";
                    counter++;
                  } else {
                    whereQuery = " Reception19.equation = :Reception19equation" + j + " ";
                  }

                  isOpenParentheses = false;
                  whereValue = model.getReceptions().get(i19).getEquation();
                  query +=
                      (andCondition
                          + Facade.getStatement(
                              statement,
                              "Reception19.equation",
                              "Reception19equation" + j,
                              mapVariable,
                              whereQuery,
                              whereValue,
                              isExistRecord,
                              isDelete,
                              fetchType,
                              counter));
                  whereQuery = "";
                }
                if (!isExistRecord
                    && !isDelete
                    && true
                    && model.getReceptions().get(i19).getIrrEstimate() != null) {

                  andCondition = " AND ";
                  if (isOpenParentheses) {
                    andCondition = " ";
                  }
                  query += " ";
                  whereValue = model.getReceptions().get(i19).getIrrEstimate();
                  if (isDelete && fetchType == FetchType.EAGER) {
                    whereQuery = " Reception19.irrEstimate = ? ";
                    counter++;
                  } else {
                    whereQuery = " Reception19.irrEstimate = :Reception19irrEstimate" + j + " ";
                  }

                  isOpenParentheses = false;
                  whereValue = model.getReceptions().get(i19).getIrrEstimate();
                  query +=
                      (andCondition
                          + Facade.getStatement(
                              statement,
                              "Reception19.irrEstimate",
                              "Reception19irrEstimate" + j,
                              mapVariable,
                              whereQuery,
                              whereValue,
                              isExistRecord,
                              isDelete,
                              fetchType,
                              counter));
                  whereQuery = "";
                }
                if (!isExistRecord
                    && !isDelete
                    && true
                    && model.getReceptions().get(i19).getHeadPoint() != null) {

                  andCondition = " AND ";
                  if (isOpenParentheses) {
                    andCondition = " ";
                  }
                  query += " ";
                  whereValue = model.getReceptions().get(i19).getHeadPoint();
                  if (isDelete && fetchType == FetchType.EAGER) {
                    whereQuery = " Reception19.headPoint = ? ";
                    counter++;
                  } else {
                    whereQuery = " Reception19.headPoint = :Reception19headPoint" + j + " ";
                  }

                  isOpenParentheses = false;
                  whereValue = model.getReceptions().get(i19).getHeadPoint();
                  query +=
                      (andCondition
                          + Facade.getStatement(
                              statement,
                              "Reception19.headPoint",
                              "Reception19headPoint" + j,
                              mapVariable,
                              whereQuery,
                              whereValue,
                              isExistRecord,
                              isDelete,
                              fetchType,
                              counter));
                  whereQuery = "";
                }
                if (!isExistRecord
                    && !isDelete
                    && true
                    && model.getReceptions().get(i19).getCashControl() != null) {

                  andCondition = " AND ";
                  if (isOpenParentheses) {
                    andCondition = " ";
                  }
                  query += " ";
                  whereValue = model.getReceptions().get(i19).getCashControl();
                  if (isDelete && fetchType == FetchType.EAGER) {
                    whereQuery = " Reception19.cashControl = ? ";
                    counter++;
                  } else {
                    whereQuery = " Reception19.cashControl = :Reception19cashControl" + j + " ";
                  }

                  isOpenParentheses = false;
                  whereValue = model.getReceptions().get(i19).getCashControl();
                  query +=
                      (andCondition
                          + Facade.getStatement(
                              statement,
                              "Reception19.cashControl",
                              "Reception19cashControl" + j,
                              mapVariable,
                              whereQuery,
                              whereValue,
                              isExistRecord,
                              isDelete,
                              fetchType,
                              counter));
                  whereQuery = "";
                }
                if (!isExistRecord
                    && !isDelete
                    && true
                    && model.getReceptions().get(i19).getExportMarketDsc() != null) {

                  andCondition = " AND ";
                  if (isOpenParentheses) {
                    andCondition = " ";
                  }
                  query += " ";
                  whereValue = model.getReceptions().get(i19).getExportMarketDsc();
                  if (isDelete && fetchType == FetchType.EAGER) {
                    whereQuery = " Reception19.exportMarketDsc = ? ";
                    counter++;
                  } else {
                    whereQuery =
                        " Reception19.exportMarketDsc = :Reception19exportMarketDsc" + j + " ";
                  }

                  isOpenParentheses = false;
                  whereValue = model.getReceptions().get(i19).getExportMarketDsc();
                  query +=
                      (andCondition
                          + Facade.getStatement(
                              statement,
                              "Reception19.exportMarketDsc",
                              "Reception19exportMarketDsc" + j,
                              mapVariable,
                              whereQuery,
                              whereValue,
                              isExistRecord,
                              isDelete,
                              fetchType,
                              counter));
                  whereQuery = "";
                }
                if (!isExistRecord
                    && !isDelete
                    && true
                    && model.getReceptions().get(i19).getInternalMarketDsc() != null) {

                  andCondition = " AND ";
                  if (isOpenParentheses) {
                    andCondition = " ";
                  }
                  query += " ";
                  whereValue = model.getReceptions().get(i19).getInternalMarketDsc();
                  if (isDelete && fetchType == FetchType.EAGER) {
                    whereQuery = " Reception19.internalMarketDsc = ? ";
                    counter++;
                  } else {
                    whereQuery =
                        " Reception19.internalMarketDsc = :Reception19internalMarketDsc" + j + " ";
                  }

                  isOpenParentheses = false;
                  whereValue = model.getReceptions().get(i19).getInternalMarketDsc();
                  query +=
                      (andCondition
                          + Facade.getStatement(
                              statement,
                              "Reception19.internalMarketDsc",
                              "Reception19internalMarketDsc" + j,
                              mapVariable,
                              whereQuery,
                              whereValue,
                              isExistRecord,
                              isDelete,
                              fetchType,
                              counter));
                  whereQuery = "";
                }
                if (!isExistRecord
                    && !isDelete
                    && true
                    && model.getReceptions().get(i19).getRowMaterialDsc() != null) {

                  andCondition = " AND ";
                  if (isOpenParentheses) {
                    andCondition = " ";
                  }
                  query += " ";
                  whereValue = model.getReceptions().get(i19).getRowMaterialDsc();
                  if (isDelete && fetchType == FetchType.EAGER) {
                    whereQuery = " Reception19.rowMaterialDsc = ? ";
                    counter++;
                  } else {
                    whereQuery =
                        " Reception19.rowMaterialDsc = :Reception19rowMaterialDsc" + j + " ";
                  }

                  isOpenParentheses = false;
                  whereValue = model.getReceptions().get(i19).getRowMaterialDsc();
                  query +=
                      (andCondition
                          + Facade.getStatement(
                              statement,
                              "Reception19.rowMaterialDsc",
                              "Reception19rowMaterialDsc" + j,
                              mapVariable,
                              whereQuery,
                              whereValue,
                              isExistRecord,
                              isDelete,
                              fetchType,
                              counter));
                  whereQuery = "";
                }
                if (!isExistRecord
                    && !isDelete
                    && true
                    && model.getReceptions().get(i19).getMarketProductDesc() != null) {

                  andCondition = " AND ";
                  if (isOpenParentheses) {
                    andCondition = " ";
                  }
                  query += " ";
                  whereValue = model.getReceptions().get(i19).getMarketProductDesc();
                  if (isDelete && fetchType == FetchType.EAGER) {
                    whereQuery = " Reception19.marketProductDesc = ? ";
                    counter++;
                  } else {
                    whereQuery =
                        " Reception19.marketProductDesc = :Reception19marketProductDesc" + j + " ";
                  }

                  isOpenParentheses = false;
                  whereValue = model.getReceptions().get(i19).getMarketProductDesc();
                  query +=
                      (andCondition
                          + Facade.getStatement(
                              statement,
                              "Reception19.marketProductDesc",
                              "Reception19marketProductDesc" + j,
                              mapVariable,
                              whereQuery,
                              whereValue,
                              isExistRecord,
                              isDelete,
                              fetchType,
                              counter));
                  whereQuery = "";
                }
                if (!isExistRecord
                    && !isDelete
                    && true
                    && model.getReceptions().get(i19).getProductiveEmployee() != null) {

                  andCondition = " AND ";
                  if (isOpenParentheses) {
                    andCondition = " ";
                  }
                  query += " ";
                  whereValue = model.getReceptions().get(i19).getProductiveEmployee();
                  if (isDelete && fetchType == FetchType.EAGER) {
                    whereQuery = " Reception19.productiveEmployee = ? ";
                    counter++;
                  } else {
                    whereQuery =
                        " Reception19.productiveEmployee = :Reception19productiveEmployee"
                            + j
                            + " ";
                  }

                  isOpenParentheses = false;
                  whereValue = model.getReceptions().get(i19).getProductiveEmployee();
                  query +=
                      (andCondition
                          + Facade.getStatement(
                              statement,
                              "Reception19.productiveEmployee",
                              "Reception19productiveEmployee" + j,
                              mapVariable,
                              whereQuery,
                              whereValue,
                              isExistRecord,
                              isDelete,
                              fetchType,
                              counter));
                  whereQuery = "";
                }
                if (!isExistRecord
                    && !isDelete
                    && true
                    && model.getReceptions().get(i19).getNonProductiveEmployee() != null) {

                  andCondition = " AND ";
                  if (isOpenParentheses) {
                    andCondition = " ";
                  }
                  query += " ";
                  whereValue = model.getReceptions().get(i19).getNonProductiveEmployee();
                  if (isDelete && fetchType == FetchType.EAGER) {
                    whereQuery = " Reception19.nonProductiveEmployee = ? ";
                    counter++;
                  } else {
                    whereQuery =
                        " Reception19.nonProductiveEmployee = :Reception19nonProductiveEmployee"
                            + j
                            + " ";
                  }

                  isOpenParentheses = false;
                  whereValue = model.getReceptions().get(i19).getNonProductiveEmployee();
                  query +=
                      (andCondition
                          + Facade.getStatement(
                              statement,
                              "Reception19.nonProductiveEmployee",
                              "Reception19nonProductiveEmployee" + j,
                              mapVariable,
                              whereQuery,
                              whereValue,
                              isExistRecord,
                              isDelete,
                              fetchType,
                              counter));
                  whereQuery = "";
                }
                if (!isExistRecord
                    && !isDelete
                    && true
                    && model.getReceptions().get(i19).getForecastProductiveEmployee() != null) {

                  andCondition = " AND ";
                  if (isOpenParentheses) {
                    andCondition = " ";
                  }
                  query += " ";
                  whereValue = model.getReceptions().get(i19).getForecastProductiveEmployee();
                  if (isDelete && fetchType == FetchType.EAGER) {
                    whereQuery = " Reception19.forecastProductiveEmployee = ? ";
                    counter++;
                  } else {
                    whereQuery =
                        " Reception19.forecastProductiveEmployee = :Reception19forecastProductiveEmployee"
                            + j
                            + " ";
                  }

                  isOpenParentheses = false;
                  whereValue = model.getReceptions().get(i19).getForecastProductiveEmployee();
                  query +=
                      (andCondition
                          + Facade.getStatement(
                              statement,
                              "Reception19.forecastProductiveEmployee",
                              "Reception19forecastProductiveEmployee" + j,
                              mapVariable,
                              whereQuery,
                              whereValue,
                              isExistRecord,
                              isDelete,
                              fetchType,
                              counter));
                  whereQuery = "";
                }
                if (!isExistRecord
                    && !isDelete
                    && true
                    && model.getReceptions().get(i19).getForecastNonProductiveEmployee() != null) {

                  andCondition = " AND ";
                  if (isOpenParentheses) {
                    andCondition = " ";
                  }
                  query += " ";
                  whereValue = model.getReceptions().get(i19).getForecastNonProductiveEmployee();
                  if (isDelete && fetchType == FetchType.EAGER) {
                    whereQuery = " Reception19.forecastNonProductiveEmployee = ? ";
                    counter++;
                  } else {
                    whereQuery =
                        " Reception19.forecastNonProductiveEmployee = :Reception19forecastNonProductiveEmployee"
                            + j
                            + " ";
                  }

                  isOpenParentheses = false;
                  whereValue = model.getReceptions().get(i19).getForecastNonProductiveEmployee();
                  query +=
                      (andCondition
                          + Facade.getStatement(
                              statement,
                              "Reception19.forecastNonProductiveEmployee",
                              "Reception19forecastNonProductiveEmployee" + j,
                              mapVariable,
                              whereQuery,
                              whereValue,
                              isExistRecord,
                              isDelete,
                              fetchType,
                              counter));
                  whereQuery = "";
                }
                if (!isExistRecord
                    && !isDelete
                    && true
                    && model.getReceptions().get(i19).getCusPrePayAmnt() != null) {

                  andCondition = " AND ";
                  if (isOpenParentheses) {
                    andCondition = " ";
                  }
                  query += " ";
                  whereValue = model.getReceptions().get(i19).getCusPrePayAmnt();
                  if (isDelete && fetchType == FetchType.EAGER) {
                    whereQuery = " Reception19.cusPrePayAmnt = ? ";
                    counter++;
                  } else {
                    whereQuery = " Reception19.cusPrePayAmnt = :Reception19cusPrePayAmnt" + j + " ";
                  }

                  isOpenParentheses = false;
                  whereValue = model.getReceptions().get(i19).getCusPrePayAmnt();
                  query +=
                      (andCondition
                          + Facade.getStatement(
                              statement,
                              "Reception19.cusPrePayAmnt",
                              "Reception19cusPrePayAmnt" + j,
                              mapVariable,
                              whereQuery,
                              whereValue,
                              isExistRecord,
                              isDelete,
                              fetchType,
                              counter));
                  whereQuery = "";
                }
                if (!isExistRecord
                    && !isDelete
                    && true
                    && model.getReceptions().get(i19).getFinancialUser() != null) {

                  andCondition = " AND ";
                  if (isOpenParentheses) {
                    andCondition = " ";
                  }
                  query += " ";
                  whereValue = model.getReceptions().get(i19).getFinancialUser();
                  if (isDelete && fetchType == FetchType.EAGER) {
                    whereQuery = " Reception19.financialUser = ? ";
                    counter++;
                  } else {
                    whereQuery = " Reception19.financialUser = :Reception19financialUser" + j + " ";
                  }

                  isOpenParentheses = false;
                  whereValue = model.getReceptions().get(i19).getFinancialUser();
                  query +=
                      (andCondition
                          + Facade.getStatement(
                              statement,
                              "Reception19.financialUser",
                              "Reception19financialUser" + j,
                              mapVariable,
                              whereQuery,
                              whereValue,
                              isExistRecord,
                              isDelete,
                              fetchType,
                              counter));
                  whereQuery = "";
                }
                if (!isExistRecord
                    && !isDelete
                    && true
                    && model.getReceptions().get(i19).getMarketUser() != null) {

                  andCondition = " AND ";
                  if (isOpenParentheses) {
                    andCondition = " ";
                  }
                  query += " ";
                  whereValue = model.getReceptions().get(i19).getMarketUser();
                  if (isDelete && fetchType == FetchType.EAGER) {
                    whereQuery = " Reception19.marketUser = ? ";
                    counter++;
                  } else {
                    whereQuery = " Reception19.marketUser = :Reception19marketUser" + j + " ";
                  }

                  isOpenParentheses = false;
                  whereValue = model.getReceptions().get(i19).getMarketUser();
                  query +=
                      (andCondition
                          + Facade.getStatement(
                              statement,
                              "Reception19.marketUser",
                              "Reception19marketUser" + j,
                              mapVariable,
                              whereQuery,
                              whereValue,
                              isExistRecord,
                              isDelete,
                              fetchType,
                              counter));
                  whereQuery = "";
                }
                if (!isExistRecord
                    && !isDelete
                    && true
                    && model.getReceptions().get(i19).getTechnicalUser() != null) {

                  andCondition = " AND ";
                  if (isOpenParentheses) {
                    andCondition = " ";
                  }
                  query += " ";
                  whereValue = model.getReceptions().get(i19).getTechnicalUser();
                  if (isDelete && fetchType == FetchType.EAGER) {
                    whereQuery = " Reception19.technicalUser = ? ";
                    counter++;
                  } else {
                    whereQuery = " Reception19.technicalUser = :Reception19technicalUser" + j + " ";
                  }

                  isOpenParentheses = false;
                  whereValue = model.getReceptions().get(i19).getTechnicalUser();
                  query +=
                      (andCondition
                          + Facade.getStatement(
                              statement,
                              "Reception19.technicalUser",
                              "Reception19technicalUser" + j,
                              mapVariable,
                              whereQuery,
                              whereValue,
                              isExistRecord,
                              isDelete,
                              fetchType,
                              counter));
                  whereQuery = "";
                }
                if (!isExistRecord
                    && !isDelete
                    && true
                    && model.getReceptions().get(i19).getCurrentAssetReceptionNo() != null) {

                  andCondition = " AND ";
                  if (isOpenParentheses) {
                    andCondition = " ";
                  }
                  query += " ";
                  whereValue = model.getReceptions().get(i19).getCurrentAssetReceptionNo();
                  if (isDelete && fetchType == FetchType.EAGER) {
                    whereQuery = " Reception19.currentAssetReceptionNo = ? ";
                    counter++;
                  } else {
                    whereQuery =
                        " Reception19.currentAssetReceptionNo = :Reception19currentAssetReceptionNo"
                            + j
                            + " ";
                  }

                  isOpenParentheses = false;
                  whereValue = model.getReceptions().get(i19).getCurrentAssetReceptionNo();
                  query +=
                      (andCondition
                          + Facade.getStatement(
                              statement,
                              "Reception19.currentAssetReceptionNo",
                              "Reception19currentAssetReceptionNo" + j,
                              mapVariable,
                              whereQuery,
                              whereValue,
                              isExistRecord,
                              isDelete,
                              fetchType,
                              counter));
                  whereQuery = "";
                }
                if (!isExistRecord
                    && !isDelete
                    && true
                    && model.getReceptions().get(i19).getVersion() != 0) {

                  andCondition = " AND ";
                  if (isOpenParentheses) {
                    andCondition = " ";
                  }
                  query += " ";
                  whereValue =
                      new java.lang.Integer("" + model.getReceptions().get(i19).getVersion());
                  if (isDelete && fetchType == FetchType.EAGER) {
                    whereQuery = " Reception19.VERSION = ? ";
                    counter++;
                  } else {
                    whereQuery = " Reception19.version = :Reception19version" + j + " ";
                  }

                  isOpenParentheses = false;
                  whereValue =
                      new java.lang.Integer("" + model.getReceptions().get(i19).getVersion());
                  query +=
                      (andCondition
                          + Facade.getStatement(
                              statement,
                              "Reception19.version",
                              "Reception19version" + j,
                              mapVariable,
                              whereQuery,
                              whereValue,
                              isExistRecord,
                              isDelete,
                              fetchType,
                              counter));
                  whereQuery = "";
                }
                if (length19 > 1) {
                  query += " ) ";
                }
              }

              query += ")";
              isOpenParentheses = false;
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
                whereQuery = " Portfolio0.addressLine1 = ? ";
                counter++;
              } else {
                whereQuery =
                    " Portfolio0.address.addressLine1 = :Portfolio0addressaddressLine1" + j + " ";
              }

              isOpenParentheses = false;
              whereValue = model.getAddress().getAddressLine1();
              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "Portfolio0.address.addressLine1",
                          "Portfolio0addressaddressLine1" + j,
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
                whereQuery = " Portfolio0.addressLine2 = ? ";
                counter++;
              } else {
                whereQuery =
                    " Portfolio0.address.addressLine2 = :Portfolio0addressaddressLine2" + j + " ";
              }

              isOpenParentheses = false;
              whereValue = model.getAddress().getAddressLine2();
              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "Portfolio0.address.addressLine2",
                          "Portfolio0addressaddressLine2" + j,
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
                whereQuery = " Portfolio0.city = ? ";
                counter++;
              } else {
                whereQuery = " Portfolio0.address.city = :Portfolio0addresscity" + j + " ";
              }

              isOpenParentheses = false;
              whereValue = model.getAddress().getCity();
              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "Portfolio0.address.city",
                          "Portfolio0addresscity" + j,
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
                whereQuery = " Portfolio0.state = ? ";
                counter++;
              } else {
                whereQuery = " Portfolio0.address.state = :Portfolio0addressstate" + j + " ";
              }

              isOpenParentheses = false;
              whereValue = model.getAddress().getState();
              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "Portfolio0.address.state",
                          "Portfolio0addressstate" + j,
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
                whereQuery = " Portfolio0.country = ? ";
                counter++;
              } else {
                whereQuery = " Portfolio0.address.country = :Portfolio0addresscountry" + j + " ";
              }

              isOpenParentheses = false;
              whereValue = model.getAddress().getCountry();
              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "Portfolio0.address.country",
                          "Portfolio0addresscountry" + j,
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
                whereQuery = " Portfolio0.zipCode = ? ";
                counter++;
              } else {
                whereQuery = " Portfolio0.address.zipCode = :Portfolio0addresszipCode" + j + " ";
              }

              isOpenParentheses = false;
              whereValue = model.getAddress().getZipCode();
              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "Portfolio0.address.zipCode",
                          "Portfolio0addresszipCode" + j,
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
          if (!isExistRecord && true && model.getCustomerId() != null) {

            andCondition = " AND ";
            if (isOpenParentheses) {
              andCondition = " ";
            }
            query += " ";
            if (isDelete && fetchType == FetchType.EAGER) {
              whereQuery = " Portfolio0.customerId = ? ";
              counter++;
            } else {
              whereQuery = " Portfolio0.customerId = :Portfolio0customerId" + j + " ";
            }

            isOpenParentheses = false;
            whereValue = model.getCustomerId();
            query +=
                (andCondition
                    + Facade.getStatement(
                        statement,
                        "Portfolio0.customerId",
                        "Portfolio0customerId" + j,
                        mapVariable,
                        whereQuery,
                        whereValue,
                        isExistRecord,
                        isDelete,
                        fetchType,
                        counter));
            whereQuery = "";
          }
          if (!isExistRecord && true && model.getTitle() != null) {

            andCondition = " AND ";
            if (isOpenParentheses) {
              andCondition = " ";
            }
            query += " ";
            if (isDelete && fetchType == FetchType.EAGER) {
              whereQuery = " Portfolio0.title = ? ";
              counter++;
            } else {
              whereQuery = " Portfolio0.title = :Portfolio0title" + j + " ";
            }

            isOpenParentheses = false;
            whereValue = model.getTitle();
            query +=
                (andCondition
                    + Facade.getStatement(
                        statement,
                        "Portfolio0.title",
                        "Portfolio0title" + j,
                        mapVariable,
                        whereQuery,
                        whereValue,
                        isExistRecord,
                        isDelete,
                        fetchType,
                        counter));
            whereQuery = "";
          }
          if (!isExistRecord && true && model.getBranchCode() != null) {

            andCondition = " AND ";
            if (isOpenParentheses) {
              andCondition = " ";
            }
            query += " ";
            if (isDelete && fetchType == FetchType.EAGER) {
              whereQuery = " Portfolio0.branchCode = ? ";
              counter++;
            } else {
              whereQuery = " Portfolio0.branchCode = :Portfolio0branchCode" + j + " ";
            }

            isOpenParentheses = false;
            whereValue = model.getBranchCode();
            query +=
                (andCondition
                    + Facade.getStatement(
                        statement,
                        "Portfolio0.branchCode",
                        "Portfolio0branchCode" + j,
                        mapVariable,
                        whereQuery,
                        whereValue,
                        isExistRecord,
                        isDelete,
                        fetchType,
                        counter));
            whereQuery = "";
          }
          if (!isExistRecord && true && model.getEconomicType() != null) {

            andCondition = " AND ";
            if (isOpenParentheses) {
              andCondition = " ";
            }
            query += " ";
            if (isDelete && fetchType == FetchType.EAGER) {
              whereQuery = " Portfolio0.economicType = ? ";
              counter++;
            } else {
              whereQuery = " Portfolio0.economicType = :Portfolio0economicType" + j + " ";
            }

            isOpenParentheses = false;
            whereValue = model.getEconomicType();
            query +=
                (andCondition
                    + Facade.getStatement(
                        statement,
                        "Portfolio0.economicType",
                        "Portfolio0economicType" + j,
                        mapVariable,
                        whereQuery,
                        whereValue,
                        isExistRecord,
                        isDelete,
                        fetchType,
                        counter));
            whereQuery = "";
          }
          if (!isExistRecord && true && model.getIndustryType() != null) {

            andCondition = " AND ";
            if (isOpenParentheses) {
              andCondition = " ";
            }
            query += " ";
            if (isDelete && fetchType == FetchType.EAGER) {
              whereQuery = " Portfolio0.industryType = ? ";
              counter++;
            } else {
              whereQuery = " Portfolio0.industryType = :Portfolio0industryType" + j + " ";
            }

            isOpenParentheses = false;
            whereValue = model.getIndustryType();
            query +=
                (andCondition
                    + Facade.getStatement(
                        statement,
                        "Portfolio0.industryType",
                        "Portfolio0industryType" + j,
                        mapVariable,
                        whereQuery,
                        whereValue,
                        isExistRecord,
                        isDelete,
                        fetchType,
                        counter));
            whereQuery = "";
          }
          if (!isExistRecord && true && model.getTechnologyDescription() != null) {

            andCondition = " AND ";
            if (isOpenParentheses) {
              andCondition = " ";
            }
            query += " ";
            if (isDelete && fetchType == FetchType.EAGER) {
              whereQuery = " Portfolio0.techDescription = ? ";
              counter++;
            } else {
              whereQuery =
                  " Portfolio0.technologyDescription = :Portfolio0technologyDescription" + j + " ";
            }

            isOpenParentheses = false;
            whereValue = model.getTechnologyDescription();
            query +=
                (andCondition
                    + Facade.getStatement(
                        statement,
                        "Portfolio0.technologyDescription",
                        "Portfolio0technologyDescription" + j,
                        mapVariable,
                        whereQuery,
                        whereValue,
                        isExistRecord,
                        isDelete,
                        fetchType,
                        counter));
            whereQuery = "";
          }
          if (!isExistRecord && true && model.getStateType() != null) {

            andCondition = " AND ";
            if (isOpenParentheses) {
              andCondition = " ";
            }
            query += " ";
            if (isDelete && fetchType == FetchType.EAGER) {
              whereQuery = " Portfolio0.stateType = ? ";
              counter++;
            } else {
              whereQuery = " Portfolio0.stateType = :Portfolio0stateType" + j + " ";
            }

            isOpenParentheses = false;
            whereValue = model.getStateType();
            query +=
                (andCondition
                    + Facade.getStatement(
                        statement,
                        "Portfolio0.stateType",
                        "Portfolio0stateType" + j,
                        mapVariable,
                        whereQuery,
                        whereValue,
                        isExistRecord,
                        isDelete,
                        fetchType,
                        counter));
            whereQuery = "";
          }
          if (!isExistRecord && true && model.getLifeType() != null) {

            andCondition = " AND ";
            if (isOpenParentheses) {
              andCondition = " ";
            }
            query += " ";
            if (isDelete && fetchType == FetchType.EAGER) {
              whereQuery = " Portfolio0.lifeType = ? ";
              counter++;
            } else {
              whereQuery = " Portfolio0.lifeType = :Portfolio0lifeType" + j + " ";
            }

            isOpenParentheses = false;
            whereValue = model.getLifeType();
            query +=
                (andCondition
                    + Facade.getStatement(
                        statement,
                        "Portfolio0.lifeType",
                        "Portfolio0lifeType" + j,
                        mapVariable,
                        whereQuery,
                        whereValue,
                        isExistRecord,
                        isDelete,
                        fetchType,
                        counter));
            whereQuery = "";
          }
          if (!isExistRecord && true && model.getStartDate() != null) {

            andCondition = " AND ";
            if (isOpenParentheses) {
              andCondition = " ";
            }
            query += " ";
            if (isDelete && fetchType == FetchType.EAGER) {
              whereQuery = " Portfolio0.startDate = ? ";
              counter++;
            } else {
              whereQuery = " Portfolio0.startDate = :Portfolio0startDate" + j + " ";
            }

            isOpenParentheses = false;
            whereValue = model.getStartDate();
            query +=
                (andCondition
                    + Facade.getStatement(
                        statement,
                        "Portfolio0.startDate",
                        "Portfolio0startDate" + j,
                        mapVariable,
                        whereQuery,
                        whereValue,
                        isExistRecord,
                        isDelete,
                        fetchType,
                        counter));
            whereQuery = "";
          }
          if (!isExistRecord && true && model.getEndDate() != null) {

            andCondition = " AND ";
            if (isOpenParentheses) {
              andCondition = " ";
            }
            query += " ";
            if (isDelete && fetchType == FetchType.EAGER) {
              whereQuery = " Portfolio0.endDate = ? ";
              counter++;
            } else {
              whereQuery = " Portfolio0.endDate = :Portfolio0endDate" + j + " ";
            }

            isOpenParentheses = false;
            whereValue = model.getEndDate();
            query +=
                (andCondition
                    + Facade.getStatement(
                        statement,
                        "Portfolio0.endDate",
                        "Portfolio0endDate" + j,
                        mapVariable,
                        whereQuery,
                        whereValue,
                        isExistRecord,
                        isDelete,
                        fetchType,
                        counter));
            whereQuery = "";
          }
          if (!isExistRecord && true && model.getRequestDate() != null) {

            andCondition = " AND ";
            if (isOpenParentheses) {
              andCondition = " ";
            }
            query += " ";
            if (isDelete && fetchType == FetchType.EAGER) {
              whereQuery = " Portfolio0.requestDate = ? ";
              counter++;
            } else {
              whereQuery = " Portfolio0.requestDate = :Portfolio0requestDate" + j + " ";
            }

            isOpenParentheses = false;
            whereValue = model.getRequestDate();
            query +=
                (andCondition
                    + Facade.getStatement(
                        statement,
                        "Portfolio0.requestDate",
                        "Portfolio0requestDate" + j,
                        mapVariable,
                        whereQuery,
                        whereValue,
                        isExistRecord,
                        isDelete,
                        fetchType,
                        counter));
            whereQuery = "";
          }
          if (!isExistRecord && true && model.getCapitalAMT() != null) {

            andCondition = " AND ";
            if (isOpenParentheses) {
              andCondition = " ";
            }
            query += " ";
            if (isDelete && fetchType == FetchType.EAGER) {
              whereQuery = " Portfolio0.capitalAMT = ? ";
              counter++;
            } else {
              whereQuery = " Portfolio0.capitalAMT = :Portfolio0capitalAMT" + j + " ";
            }

            isOpenParentheses = false;
            whereValue = model.getCapitalAMT();
            query +=
                (andCondition
                    + Facade.getStatement(
                        statement,
                        "Portfolio0.capitalAMT",
                        "Portfolio0capitalAMT" + j,
                        mapVariable,
                        whereQuery,
                        whereValue,
                        isExistRecord,
                        isDelete,
                        fetchType,
                        counter));
            whereQuery = "";
          }
          if (!isExistRecord && true && model.getInvestableAMT() != null) {

            andCondition = " AND ";
            if (isOpenParentheses) {
              andCondition = " ";
            }
            query += " ";
            if (isDelete && fetchType == FetchType.EAGER) {
              whereQuery = " Portfolio0.investableAMT = ? ";
              counter++;
            } else {
              whereQuery = " Portfolio0.investableAMT = :Portfolio0investableAMT" + j + " ";
            }

            isOpenParentheses = false;
            whereValue = model.getInvestableAMT();
            query +=
                (andCondition
                    + Facade.getStatement(
                        statement,
                        "Portfolio0.investableAMT",
                        "Portfolio0investableAMT" + j,
                        mapVariable,
                        whereQuery,
                        whereValue,
                        isExistRecord,
                        isDelete,
                        fetchType,
                        counter));
            whereQuery = "";
          }
          if (!isExistRecord && true && model.getAssessdAMT() != null) {

            andCondition = " AND ";
            if (isOpenParentheses) {
              andCondition = " ";
            }
            query += " ";
            if (isDelete && fetchType == FetchType.EAGER) {
              whereQuery = " Portfolio0.assessdAMT = ? ";
              counter++;
            } else {
              whereQuery = " Portfolio0.assessdAMT = :Portfolio0assessdAMT" + j + " ";
            }

            isOpenParentheses = false;
            whereValue = model.getAssessdAMT();
            query +=
                (andCondition
                    + Facade.getStatement(
                        statement,
                        "Portfolio0.assessdAMT",
                        "Portfolio0assessdAMT" + j,
                        mapVariable,
                        whereQuery,
                        whereValue,
                        isExistRecord,
                        isDelete,
                        fetchType,
                        counter));
            whereQuery = "";
          }
          if (!isExistRecord && true && model.getBankAPPRInvestAMT() != null) {

            andCondition = " AND ";
            if (isOpenParentheses) {
              andCondition = " ";
            }
            query += " ";
            if (isDelete && fetchType == FetchType.EAGER) {
              whereQuery = " Portfolio0.bankAPPRInvestAMT = ? ";
              counter++;
            } else {
              whereQuery = " Portfolio0.bankAPPRInvestAMT = :Portfolio0bankAPPRInvestAMT" + j + " ";
            }

            isOpenParentheses = false;
            whereValue = model.getBankAPPRInvestAMT();
            query +=
                (andCondition
                    + Facade.getStatement(
                        statement,
                        "Portfolio0.bankAPPRInvestAMT",
                        "Portfolio0bankAPPRInvestAMT" + j,
                        mapVariable,
                        whereQuery,
                        whereValue,
                        isExistRecord,
                        isDelete,
                        fetchType,
                        counter));
            whereQuery = "";
          }
          if (!isExistRecord && true && model.getReagent() != null) {

            andCondition = " AND ";
            if (isOpenParentheses) {
              andCondition = " ";
            }
            query += " ";
            if (isDelete && fetchType == FetchType.EAGER) {
              whereQuery = " Portfolio0.reagent = ? ";
              counter++;
            } else {
              whereQuery = " Portfolio0.reagent = :Portfolio0reagent" + j + " ";
            }

            isOpenParentheses = false;
            whereValue = model.getReagent();
            query +=
                (andCondition
                    + Facade.getStatement(
                        statement,
                        "Portfolio0.reagent",
                        "Portfolio0reagent" + j,
                        mapVariable,
                        whereQuery,
                        whereValue,
                        isExistRecord,
                        isDelete,
                        fetchType,
                        counter));
            whereQuery = "";
          }
          if (!isExistRecord && true && model.getBusinessId() != null) {

            andCondition = " AND ";
            if (isOpenParentheses) {
              andCondition = " ";
            }
            query += " ";
            if (isDelete && fetchType == FetchType.EAGER) {
              whereQuery = " Portfolio0.businessId = ? ";
              counter++;
            } else {
              whereQuery = " Portfolio0.businessId = :Portfolio0businessId" + j + " ";
            }

            isOpenParentheses = false;
            whereValue = model.getBusinessId();
            query +=
                (andCondition
                    + Facade.getStatement(
                        statement,
                        "Portfolio0.businessId",
                        "Portfolio0businessId" + j,
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
              whereQuery = " Portfolio0.VERSION = ? ";
              counter++;
            } else {
              whereQuery = " Portfolio0.version = :Portfolio0version" + j + " ";
            }

            isOpenParentheses = false;
            whereValue = new java.lang.Integer("" + model.getVersion());
            query +=
                (andCondition
                    + Facade.getStatement(
                        statement,
                        "Portfolio0.version",
                        "Portfolio0version" + j,
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
          && (!select.trim().equals("Portfolio0") && select.indexOf("new") == -1)) {
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
