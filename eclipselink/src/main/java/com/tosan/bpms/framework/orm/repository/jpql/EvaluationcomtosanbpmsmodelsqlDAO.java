package com.tosan.bpms.framework.orm.repository.jpql;

import com.jpql.api.interfaces.*;
import com.jpql.api.interfaces.Parameter;
import javax.persistence.FetchType;
import javax.persistence.EntityManager;
/**
 * Created by kasra.haghpanah on 1/05/2019.
 */
public class EvaluationcomtosanbpmsmodelsqlDAO {

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

        com.tosan.bpms.model.sql.Evaluation model =
            (com.tosan.bpms.model.sql.Evaluation) fetchIterator.next();

        if (model != null) {

          String key = model.getEvaluationId() + "";
          existMap.put(key, model);
        }
      }
    }

    java.util.Iterator iterator = models.iterator();

    while (iterator.hasNext()) {
      com.tosan.bpms.model.sql.Evaluation model =
          (com.tosan.bpms.model.sql.Evaluation) iterator.next();

      if (model != null) {
        String key = model.getEvaluationId() + "";
        com.tosan.bpms.model.sql.Evaluation fetchModel =
            (com.tosan.bpms.model.sql.Evaluation) existMap.get(key);

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
    com.tosan.bpms.model.sql.Evaluation model = null;
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
    java.lang.String select = "SELECT Evaluation0";
    if (selectParam != null && !selectParam.equals("")) {
      select = selectParam;
    }
    if (!isExistRecord && !isDelete) {
      if (fetchType == FetchType.LAZY) {
        basicQuery =
            select
                + " FROM com.tosan.bpms.model.sql.Evaluation Evaluation0  LEFT JOIN "
                + fetch
                + " Evaluation0.file1 File1 LEFT JOIN "
                + fetch
                + " Evaluation0.file2 File2 JOIN "
                + fetch
                + " Evaluation0.reception Reception3 JOIN "
                + fetch
                + " Reception3.portfolio Portfolio4 WHERE 1=1 ";
      } else if (fetchType == FetchType.EAGER) {
        basicQuery =
            select
                + " FROM com.tosan.bpms.model.sql.Evaluation Evaluation0  LEFT JOIN "
                + fetch
                + " Evaluation0.file1 File1 LEFT JOIN "
                + fetch
                + " Evaluation0.file2 File2 JOIN "
                + fetch
                + " Evaluation0.reception Reception3 JOIN "
                + fetch
                + " Reception3.portfolio Portfolio4 JOIN "
                + fetch
                + " Portfolio4.portfolioLicenses PortfolioLicense5 LEFT JOIN "
                + fetch
                + " PortfolioLicense5.file File7 JOIN "
                + fetch
                + " PortfolioLicense5.licenseDetails LicenseDetails8 JOIN "
                + fetch
                + " LicenseDetails8.portfolioLicense PortfolioLicense9 LEFT JOIN "
                + fetch
                + " PortfolioLicense9.file File11 LEFT JOIN "
                + fetch
                + " LicenseDetails8.file File13 JOIN "
                + fetch
                + " Portfolio4.portfolioLocations PortfolioLocation14 LEFT JOIN "
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
                + " Portfolio4.receptions Reception23 WHERE 1=1 ";
      }
    } else if (isExistRecord) {
      basicQuery =
          "SELECT Evaluation0 FROM com.tosan.bpms.model.sql.Evaluation Evaluation0 WHERE 1=1 ";
    } else if (isDelete) {
      if (fetchType == FetchType.LAZY) {
        basicQuery = "DELETE FROM com.tosan.bpms.model.sql.Evaluation Evaluation0 WHERE 1=1 ";
      } else if (fetchType == FetchType.EAGER) {
        basicQuery = "DELETE Evaluation0 FROM Evaluation Evaluation0 WHERE 1=1 ";
      }
    }
    int modelsLength = models.size();
    java.util.Iterator modelsIterator = models.iterator();
    int j = 0;
    if (modelsLength > 0) {
      while (modelsIterator.hasNext()) {
        model = (com.tosan.bpms.model.sql.Evaluation) modelsIterator.next();
        query = "";
        isOpenParentheses = true;

        if (model != null) {

          if (isExistRecord == true && model.getEvaluationId() == null) {
            model.setEvaluationId(new java.lang.Integer("-1"));
            isChangeKey = true;
          }

          if (model.getEvaluationId() != null) {

            andCondition = " AND ";
            if (isOpenParentheses) {
              andCondition = " ";
            }
            query += " ";
            if (isDelete && fetchType == FetchType.EAGER) {
              whereQuery = " Evaluation0.evaluationId = ? ";
              counter++;
            } else {
              whereQuery = " Evaluation0.evaluationId = :Evaluation0evaluationId" + j + " ";
            }

            isOpenParentheses = false;
            whereValue = model.getEvaluationId();
            if (isExistRecord && isChangeKey) {
              model.setEvaluationId(null);
              isChangeKey = false;
            }

            query +=
                (andCondition
                    + Facade.getStatement(
                        statement,
                        "Evaluation0.evaluationId",
                        "Evaluation0evaluationId" + j,
                        mapVariable,
                        whereQuery,
                        whereValue,
                        isExistRecord,
                        isDelete,
                        fetchType,
                        counter));
            whereQuery = "";
          }
          if (!isExistRecord && !isDelete && model.getFile1() != null) {

            if (model.getFile1().getId() != null) {

              andCondition = " AND ";
              if (isOpenParentheses) {
                andCondition = " ";
              }
              query += " ";
              if (isDelete && fetchType == FetchType.EAGER) {
                whereQuery = " File1.l4610flmtdataid = ? ";
                counter++;
              } else {
                whereQuery = " File1.id = :File1id" + j + " ";
              }

              isOpenParentheses = false;
              whereValue = model.getFile1().getId();
              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "File1.id",
                          "File1id" + j,
                          mapVariable,
                          whereQuery,
                          whereValue,
                          isExistRecord,
                          isDelete,
                          fetchType,
                          counter));
              whereQuery = "";
            }
            if (!isExistRecord && !isDelete && true && model.getFile1().getExtension() != null) {

              andCondition = " AND ";
              if (isOpenParentheses) {
                andCondition = " ";
              }
              query += " ";
              if (isDelete && fetchType == FetchType.EAGER) {
                whereQuery = " File1.l4610extension = ? ";
                counter++;
              } else {
                whereQuery = " File1.extension = :File1extension" + j + " ";
              }

              isOpenParentheses = false;
              whereValue = model.getFile1().getExtension();
              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "File1.extension",
                          "File1extension" + j,
                          mapVariable,
                          whereQuery,
                          whereValue,
                          isExistRecord,
                          isDelete,
                          fetchType,
                          counter));
              whereQuery = "";
            }
            if (!isExistRecord && !isDelete && true && model.getFile1().getVersion() != 0) {

              andCondition = " AND ";
              if (isOpenParentheses) {
                andCondition = " ";
              }
              query += " ";
              if (isDelete && fetchType == FetchType.EAGER) {
                whereQuery = " File1.VERSION = ? ";
                counter++;
              } else {
                whereQuery = " File1.version = :File1version" + j + " ";
              }

              isOpenParentheses = false;
              whereValue = new java.lang.Integer("" + model.getFile1().getVersion());
              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "File1.version",
                          "File1version" + j,
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
          if (!isExistRecord && !isDelete && model.getFile2() != null) {

            if (model.getFile2().getId() != null) {

              andCondition = " AND ";
              if (isOpenParentheses) {
                andCondition = " ";
              }
              query += " ";
              if (isDelete && fetchType == FetchType.EAGER) {
                whereQuery = " File2.l4610flmtdataid = ? ";
                counter++;
              } else {
                whereQuery = " File2.id = :File2id" + j + " ";
              }

              isOpenParentheses = false;
              whereValue = model.getFile2().getId();
              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "File2.id",
                          "File2id" + j,
                          mapVariable,
                          whereQuery,
                          whereValue,
                          isExistRecord,
                          isDelete,
                          fetchType,
                          counter));
              whereQuery = "";
            }
            if (!isExistRecord && !isDelete && true && model.getFile2().getExtension() != null) {

              andCondition = " AND ";
              if (isOpenParentheses) {
                andCondition = " ";
              }
              query += " ";
              if (isDelete && fetchType == FetchType.EAGER) {
                whereQuery = " File2.l4610extension = ? ";
                counter++;
              } else {
                whereQuery = " File2.extension = :File2extension" + j + " ";
              }

              isOpenParentheses = false;
              whereValue = model.getFile2().getExtension();
              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "File2.extension",
                          "File2extension" + j,
                          mapVariable,
                          whereQuery,
                          whereValue,
                          isExistRecord,
                          isDelete,
                          fetchType,
                          counter));
              whereQuery = "";
            }
            if (!isExistRecord && !isDelete && true && model.getFile2().getVersion() != 0) {

              andCondition = " AND ";
              if (isOpenParentheses) {
                andCondition = " ";
              }
              query += " ";
              if (isDelete && fetchType == FetchType.EAGER) {
                whereQuery = " File2.VERSION = ? ";
                counter++;
              } else {
                whereQuery = " File2.version = :File2version" + j + " ";
              }

              isOpenParentheses = false;
              whereValue = new java.lang.Integer("" + model.getFile2().getVersion());
              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "File2.version",
                          "File2version" + j,
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
          if (!isExistRecord && !isDelete && model.getReception() != null) {

            if (model.getReception().getReceptionNo() != null) {

              andCondition = " AND ";
              if (isOpenParentheses) {
                andCondition = " ";
              }
              query += " ";
              if (isDelete && fetchType == FetchType.EAGER) {
                whereQuery = " Reception3.receptionNo = ? ";
                counter++;
              } else {
                whereQuery = " Reception3.receptionNo = :Reception3receptionNo" + j + " ";
              }

              isOpenParentheses = false;
              whereValue = model.getReception().getReceptionNo();
              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "Reception3.receptionNo",
                          "Reception3receptionNo" + j,
                          mapVariable,
                          whereQuery,
                          whereValue,
                          isExistRecord,
                          isDelete,
                          fetchType,
                          counter));
              whereQuery = "";
            }
            if (!isExistRecord && !isDelete && model.getReception().getPortfolio() != null) {

              if (model.getReception().getPortfolio().getPortfolioId() != null) {

                andCondition = " AND ";
                if (isOpenParentheses) {
                  andCondition = " ";
                }
                query += " ";
                if (isDelete && fetchType == FetchType.EAGER) {
                  whereQuery = " Portfolio4.portfolioId = ? ";
                  counter++;
                } else {
                  whereQuery = " Portfolio4.portfolioId = :Portfolio4portfolioId" + j + " ";
                }

                isOpenParentheses = false;
                whereValue = model.getReception().getPortfolio().getPortfolioId();
                query +=
                    (andCondition
                        + Facade.getStatement(
                            statement,
                            "Portfolio4.portfolioId",
                            "Portfolio4portfolioId" + j,
                            mapVariable,
                            whereQuery,
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
                  && model.getReception().getPortfolio().getPortfolioLicenses() != null) {
                int length5 = model.getReception().getPortfolio().getPortfolioLicenses().size();

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
                            .getReception()
                            .getPortfolio()
                            .getPortfolioLicenses()
                            .get(i5)
                            .getPortfolioLicenseId()
                        != null) {

                      andCondition = " AND ";
                      if (isOpenParentheses) {
                        andCondition = " ";
                      }
                      query += " ";
                      whereValue =
                          model
                              .getReception()
                              .getPortfolio()
                              .getPortfolioLicenses()
                              .get(i5)
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
                              .getReception()
                              .getPortfolio()
                              .getPortfolioLicenses()
                              .get(i5)
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
                                .getReception()
                                .getPortfolio()
                                .getPortfolioLicenses()
                                .get(i5)
                                .getFile()
                            != null) {

                      if (model
                              .getReception()
                              .getPortfolio()
                              .getPortfolioLicenses()
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
                                .getReception()
                                .getPortfolio()
                                .getPortfolioLicenses()
                                .get(i5)
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
                                .getReception()
                                .getPortfolio()
                                .getPortfolioLicenses()
                                .get(i5)
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
                                  .getReception()
                                  .getPortfolio()
                                  .getPortfolioLicenses()
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
                                .getReception()
                                .getPortfolio()
                                .getPortfolioLicenses()
                                .get(i5)
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
                                .getReception()
                                .getPortfolio()
                                .getPortfolioLicenses()
                                .get(i5)
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
                                  .getReception()
                                  .getPortfolio()
                                  .getPortfolioLicenses()
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
                                        .getReception()
                                        .getPortfolio()
                                        .getPortfolioLicenses()
                                        .get(i5)
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
                                        .getReception()
                                        .getPortfolio()
                                        .getPortfolioLicenses()
                                        .get(i5)
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
                        && fetchType == FetchType.EAGER
                        && model
                                .getReception()
                                .getPortfolio()
                                .getPortfolioLicenses()
                                .get(i5)
                                .getLicenseDetails()
                            != null) {
                      int length8 =
                          model
                              .getReception()
                              .getPortfolio()
                              .getPortfolioLicenses()
                              .get(i5)
                              .getLicenseDetails()
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
                                  .getReception()
                                  .getPortfolio()
                                  .getPortfolioLicenses()
                                  .get(i5)
                                  .getLicenseDetails()
                                  .get(i8)
                                  .getLicenseDetailId()
                              != null) {

                            andCondition = " AND ";
                            if (isOpenParentheses) {
                              andCondition = " ";
                            }
                            query += " ";
                            whereValue =
                                model
                                    .getReception()
                                    .getPortfolio()
                                    .getPortfolioLicenses()
                                    .get(i5)
                                    .getLicenseDetails()
                                    .get(i8)
                                    .getLicenseDetailId();
                            if (isDelete && fetchType == FetchType.EAGER) {
                              whereQuery = " LicenseDetails8.id = ? ";
                              counter++;
                            } else {
                              whereQuery =
                                  " LicenseDetails8.licenseDetailId = :LicenseDetails8licenseDetailId"
                                      + j
                                      + " ";
                            }

                            isOpenParentheses = false;
                            whereValue =
                                model
                                    .getReception()
                                    .getPortfolio()
                                    .getPortfolioLicenses()
                                    .get(i5)
                                    .getLicenseDetails()
                                    .get(i8)
                                    .getLicenseDetailId();
                            query +=
                                (andCondition
                                    + Facade.getStatement(
                                        statement,
                                        "LicenseDetails8.licenseDetailId",
                                        "LicenseDetails8licenseDetailId" + j,
                                        mapVariable,
                                        whereQuery,
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
                                      .getReception()
                                      .getPortfolio()
                                      .getPortfolioLicenses()
                                      .get(i5)
                                      .getLicenseDetails()
                                      .get(i8)
                                      .getPortfolioLicense()
                                  != null) {

                            if (model
                                    .getReception()
                                    .getPortfolio()
                                    .getPortfolioLicenses()
                                    .get(i5)
                                    .getLicenseDetails()
                                    .get(i8)
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
                                      .getReception()
                                      .getPortfolio()
                                      .getPortfolioLicenses()
                                      .get(i5)
                                      .getLicenseDetails()
                                      .get(i8)
                                      .getPortfolioLicense()
                                      .getPortfolioLicenseId();
                              if (isDelete && fetchType == FetchType.EAGER) {
                                whereQuery = " PortfolioLicense9.id = ? ";
                                counter++;
                              } else {
                                whereQuery =
                                    " PortfolioLicense9.portfolioLicenseId = :PortfolioLicense9portfolioLicenseId"
                                        + j
                                        + " ";
                              }

                              isOpenParentheses = false;
                              whereValue =
                                  model
                                      .getReception()
                                      .getPortfolio()
                                      .getPortfolioLicenses()
                                      .get(i5)
                                      .getLicenseDetails()
                                      .get(i8)
                                      .getPortfolioLicense()
                                      .getPortfolioLicenseId();
                              query +=
                                  (andCondition
                                      + Facade.getStatement(
                                          statement,
                                          "PortfolioLicense9.portfolioLicenseId",
                                          "PortfolioLicense9portfolioLicenseId" + j,
                                          mapVariable,
                                          whereQuery,
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
                                        .getReception()
                                        .getPortfolio()
                                        .getPortfolioLicenses()
                                        .get(i5)
                                        .getLicenseDetails()
                                        .get(i8)
                                        .getPortfolioLicense()
                                        .getFile()
                                    != null) {

                              if (model
                                      .getReception()
                                      .getPortfolio()
                                      .getPortfolioLicenses()
                                      .get(i5)
                                      .getLicenseDetails()
                                      .get(i8)
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
                                        .getReception()
                                        .getPortfolio()
                                        .getPortfolioLicenses()
                                        .get(i5)
                                        .getLicenseDetails()
                                        .get(i8)
                                        .getPortfolioLicense()
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
                                        .getReception()
                                        .getPortfolio()
                                        .getPortfolioLicenses()
                                        .get(i5)
                                        .getLicenseDetails()
                                        .get(i8)
                                        .getPortfolioLicense()
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
                                          .getReception()
                                          .getPortfolio()
                                          .getPortfolioLicenses()
                                          .get(i5)
                                          .getLicenseDetails()
                                          .get(i8)
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
                                        .getReception()
                                        .getPortfolio()
                                        .getPortfolioLicenses()
                                        .get(i5)
                                        .getLicenseDetails()
                                        .get(i8)
                                        .getPortfolioLicense()
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
                                        .getReception()
                                        .getPortfolio()
                                        .getPortfolioLicenses()
                                        .get(i5)
                                        .getLicenseDetails()
                                        .get(i8)
                                        .getPortfolioLicense()
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
                                          .getReception()
                                          .getPortfolio()
                                          .getPortfolioLicenses()
                                          .get(i5)
                                          .getLicenseDetails()
                                          .get(i8)
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
                                                .getReception()
                                                .getPortfolio()
                                                .getPortfolioLicenses()
                                                .get(i5)
                                                .getLicenseDetails()
                                                .get(i8)
                                                .getPortfolioLicense()
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
                                                .getReception()
                                                .getPortfolio()
                                                .getPortfolioLicenses()
                                                .get(i5)
                                                .getLicenseDetails()
                                                .get(i8)
                                                .getPortfolioLicense()
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
                            if (!isExistRecord
                                && !isDelete
                                && true
                                && model
                                        .getReception()
                                        .getPortfolio()
                                        .getPortfolioLicenses()
                                        .get(i5)
                                        .getLicenseDetails()
                                        .get(i8)
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
                                      .getReception()
                                      .getPortfolio()
                                      .getPortfolioLicenses()
                                      .get(i5)
                                      .getLicenseDetails()
                                      .get(i8)
                                      .getPortfolioLicense()
                                      .getIssueVerifierType();
                              if (isDelete && fetchType == FetchType.EAGER) {
                                whereQuery = " PortfolioLicense9.issueVerifierType = ? ";
                                counter++;
                              } else {
                                whereQuery =
                                    " PortfolioLicense9.issueVerifierType = :PortfolioLicense9issueVerifierType"
                                        + j
                                        + " ";
                              }

                              isOpenParentheses = false;
                              whereValue =
                                  model
                                      .getReception()
                                      .getPortfolio()
                                      .getPortfolioLicenses()
                                      .get(i5)
                                      .getLicenseDetails()
                                      .get(i8)
                                      .getPortfolioLicense()
                                      .getIssueVerifierType();
                              query +=
                                  (andCondition
                                      + Facade.getStatement(
                                          statement,
                                          "PortfolioLicense9.issueVerifierType",
                                          "PortfolioLicense9issueVerifierType" + j,
                                          mapVariable,
                                          whereQuery,
                                          whereValue,
                                          isExistRecord,
                                          isDelete,
                                          fetchType,
                                          counter));
                              whereQuery = "";
                            }
                            if (!isExistRecord
                                && !isDelete
                                && true
                                && model
                                        .getReception()
                                        .getPortfolio()
                                        .getPortfolioLicenses()
                                        .get(i5)
                                        .getLicenseDetails()
                                        .get(i8)
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
                                      .getReception()
                                      .getPortfolio()
                                      .getPortfolioLicenses()
                                      .get(i5)
                                      .getLicenseDetails()
                                      .get(i8)
                                      .getPortfolioLicense()
                                      .getLicenseNumber();
                              if (isDelete && fetchType == FetchType.EAGER) {
                                whereQuery = " PortfolioLicense9.licenseNumber = ? ";
                                counter++;
                              } else {
                                whereQuery =
                                    " PortfolioLicense9.licenseNumber = :PortfolioLicense9licenseNumber"
                                        + j
                                        + " ";
                              }

                              isOpenParentheses = false;
                              whereValue =
                                  model
                                      .getReception()
                                      .getPortfolio()
                                      .getPortfolioLicenses()
                                      .get(i5)
                                      .getLicenseDetails()
                                      .get(i8)
                                      .getPortfolioLicense()
                                      .getLicenseNumber();
                              query +=
                                  (andCondition
                                      + Facade.getStatement(
                                          statement,
                                          "PortfolioLicense9.licenseNumber",
                                          "PortfolioLicense9licenseNumber" + j,
                                          mapVariable,
                                          whereQuery,
                                          whereValue,
                                          isExistRecord,
                                          isDelete,
                                          fetchType,
                                          counter));
                              whereQuery = "";
                            }
                            if (!isExistRecord
                                && !isDelete
                                && true
                                && model
                                        .getReception()
                                        .getPortfolio()
                                        .getPortfolioLicenses()
                                        .get(i5)
                                        .getLicenseDetails()
                                        .get(i8)
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
                                      .getReception()
                                      .getPortfolio()
                                      .getPortfolioLicenses()
                                      .get(i5)
                                      .getLicenseDetails()
                                      .get(i8)
                                      .getPortfolioLicense()
                                      .getLicenseType();
                              if (isDelete && fetchType == FetchType.EAGER) {
                                whereQuery = " PortfolioLicense9.licenseType = ? ";
                                counter++;
                              } else {
                                whereQuery =
                                    " PortfolioLicense9.licenseType = :PortfolioLicense9licenseType"
                                        + j
                                        + " ";
                              }

                              isOpenParentheses = false;
                              whereValue =
                                  model
                                      .getReception()
                                      .getPortfolio()
                                      .getPortfolioLicenses()
                                      .get(i5)
                                      .getLicenseDetails()
                                      .get(i8)
                                      .getPortfolioLicense()
                                      .getLicenseType();
                              query +=
                                  (andCondition
                                      + Facade.getStatement(
                                          statement,
                                          "PortfolioLicense9.licenseType",
                                          "PortfolioLicense9licenseType" + j,
                                          mapVariable,
                                          whereQuery,
                                          whereValue,
                                          isExistRecord,
                                          isDelete,
                                          fetchType,
                                          counter));
                              whereQuery = "";
                            }
                            if (!isExistRecord
                                && !isDelete
                                && true
                                && model
                                        .getReception()
                                        .getPortfolio()
                                        .getPortfolioLicenses()
                                        .get(i5)
                                        .getLicenseDetails()
                                        .get(i8)
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
                                      .getReception()
                                      .getPortfolio()
                                      .getPortfolioLicenses()
                                      .get(i5)
                                      .getLicenseDetails()
                                      .get(i8)
                                      .getPortfolioLicense()
                                      .getIssueDate();
                              if (isDelete && fetchType == FetchType.EAGER) {
                                whereQuery = " PortfolioLicense9.issueDate = ? ";
                                counter++;
                              } else {
                                whereQuery =
                                    " PortfolioLicense9.issueDate = :PortfolioLicense9issueDate"
                                        + j
                                        + " ";
                              }

                              isOpenParentheses = false;
                              whereValue =
                                  model
                                      .getReception()
                                      .getPortfolio()
                                      .getPortfolioLicenses()
                                      .get(i5)
                                      .getLicenseDetails()
                                      .get(i8)
                                      .getPortfolioLicense()
                                      .getIssueDate();
                              query +=
                                  (andCondition
                                      + Facade.getStatement(
                                          statement,
                                          "PortfolioLicense9.issueDate",
                                          "PortfolioLicense9issueDate" + j,
                                          mapVariable,
                                          whereQuery,
                                          whereValue,
                                          isExistRecord,
                                          isDelete,
                                          fetchType,
                                          counter));
                              whereQuery = "";
                            }
                            if (!isExistRecord
                                && !isDelete
                                && true
                                && model
                                        .getReception()
                                        .getPortfolio()
                                        .getPortfolioLicenses()
                                        .get(i5)
                                        .getLicenseDetails()
                                        .get(i8)
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
                                      .getReception()
                                      .getPortfolio()
                                      .getPortfolioLicenses()
                                      .get(i5)
                                      .getLicenseDetails()
                                      .get(i8)
                                      .getPortfolioLicense()
                                      .getEndDate();
                              if (isDelete && fetchType == FetchType.EAGER) {
                                whereQuery = " PortfolioLicense9.endDate = ? ";
                                counter++;
                              } else {
                                whereQuery =
                                    " PortfolioLicense9.endDate = :PortfolioLicense9endDate"
                                        + j
                                        + " ";
                              }

                              isOpenParentheses = false;
                              whereValue =
                                  model
                                      .getReception()
                                      .getPortfolio()
                                      .getPortfolioLicenses()
                                      .get(i5)
                                      .getLicenseDetails()
                                      .get(i8)
                                      .getPortfolioLicense()
                                      .getEndDate();
                              query +=
                                  (andCondition
                                      + Facade.getStatement(
                                          statement,
                                          "PortfolioLicense9.endDate",
                                          "PortfolioLicense9endDate" + j,
                                          mapVariable,
                                          whereQuery,
                                          whereValue,
                                          isExistRecord,
                                          isDelete,
                                          fetchType,
                                          counter));
                              whereQuery = "";
                            }
                            if (!isExistRecord
                                && !isDelete
                                && true
                                && model
                                        .getReception()
                                        .getPortfolio()
                                        .getPortfolioLicenses()
                                        .get(i5)
                                        .getLicenseDetails()
                                        .get(i8)
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
                                              .getReception()
                                              .getPortfolio()
                                              .getPortfolioLicenses()
                                              .get(i5)
                                              .getLicenseDetails()
                                              .get(i8)
                                              .getPortfolioLicense()
                                              .getVersion());
                              if (isDelete && fetchType == FetchType.EAGER) {
                                whereQuery = " PortfolioLicense9.VERSION = ? ";
                                counter++;
                              } else {
                                whereQuery =
                                    " PortfolioLicense9.version = :PortfolioLicense9version"
                                        + j
                                        + " ";
                              }

                              isOpenParentheses = false;
                              whereValue =
                                  new java.lang.Integer(
                                      ""
                                          + model
                                              .getReception()
                                              .getPortfolio()
                                              .getPortfolioLicenses()
                                              .get(i5)
                                              .getLicenseDetails()
                                              .get(i8)
                                              .getPortfolioLicense()
                                              .getVersion());
                              query +=
                                  (andCondition
                                      + Facade.getStatement(
                                          statement,
                                          "PortfolioLicense9.version",
                                          "PortfolioLicense9version" + j,
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
                                      .getReception()
                                      .getPortfolio()
                                      .getPortfolioLicenses()
                                      .get(i5)
                                      .getLicenseDetails()
                                      .get(i8)
                                      .getFile()
                                  != null) {

                            if (model
                                    .getReception()
                                    .getPortfolio()
                                    .getPortfolioLicenses()
                                    .get(i5)
                                    .getLicenseDetails()
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
                                      .getReception()
                                      .getPortfolio()
                                      .getPortfolioLicenses()
                                      .get(i5)
                                      .getLicenseDetails()
                                      .get(i8)
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
                                      .getReception()
                                      .getPortfolio()
                                      .getPortfolioLicenses()
                                      .get(i5)
                                      .getLicenseDetails()
                                      .get(i8)
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
                                        .getReception()
                                        .getPortfolio()
                                        .getPortfolioLicenses()
                                        .get(i5)
                                        .getLicenseDetails()
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
                                      .getReception()
                                      .getPortfolio()
                                      .getPortfolioLicenses()
                                      .get(i5)
                                      .getLicenseDetails()
                                      .get(i8)
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
                                      .getReception()
                                      .getPortfolio()
                                      .getPortfolioLicenses()
                                      .get(i5)
                                      .getLicenseDetails()
                                      .get(i8)
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
                                        .getReception()
                                        .getPortfolio()
                                        .getPortfolioLicenses()
                                        .get(i5)
                                        .getLicenseDetails()
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
                                              .getReception()
                                              .getPortfolio()
                                              .getPortfolioLicenses()
                                              .get(i5)
                                              .getLicenseDetails()
                                              .get(i8)
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
                                              .getReception()
                                              .getPortfolio()
                                              .getPortfolioLicenses()
                                              .get(i5)
                                              .getLicenseDetails()
                                              .get(i8)
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
                                      .getReception()
                                      .getPortfolio()
                                      .getPortfolioLicenses()
                                      .get(i5)
                                      .getLicenseDetails()
                                      .get(i8)
                                      .getAddress()
                                  != null) {
                            if (!isDelete
                                && true
                                && model
                                        .getReception()
                                        .getPortfolio()
                                        .getPortfolioLicenses()
                                        .get(i5)
                                        .getLicenseDetails()
                                        .get(i8)
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
                                      .getReception()
                                      .getPortfolio()
                                      .getPortfolioLicenses()
                                      .get(i5)
                                      .getLicenseDetails()
                                      .get(i8)
                                      .getAddress()
                                      .getAddressLine1();
                              if (isDelete && fetchType == FetchType.EAGER) {
                                whereQuery = " LicenseDetails8.addressLine1 = ? ";
                                counter++;
                              } else {
                                whereQuery =
                                    " LicenseDetails8.address.addressLine1 = :LicenseDetails8addressaddressLine1"
                                        + j
                                        + " ";
                              }

                              isOpenParentheses = false;
                              whereValue =
                                  model
                                      .getReception()
                                      .getPortfolio()
                                      .getPortfolioLicenses()
                                      .get(i5)
                                      .getLicenseDetails()
                                      .get(i8)
                                      .getAddress()
                                      .getAddressLine1();
                              query +=
                                  (andCondition
                                      + Facade.getStatement(
                                          statement,
                                          "LicenseDetails8.address.addressLine1",
                                          "LicenseDetails8addressaddressLine1" + j,
                                          mapVariable,
                                          whereQuery,
                                          whereValue,
                                          isExistRecord,
                                          isDelete,
                                          fetchType,
                                          counter));
                              whereQuery = "";
                            }
                            if (!isExistRecord
                                && !isDelete
                                && true
                                && model
                                        .getReception()
                                        .getPortfolio()
                                        .getPortfolioLicenses()
                                        .get(i5)
                                        .getLicenseDetails()
                                        .get(i8)
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
                                      .getReception()
                                      .getPortfolio()
                                      .getPortfolioLicenses()
                                      .get(i5)
                                      .getLicenseDetails()
                                      .get(i8)
                                      .getAddress()
                                      .getAddressLine2();
                              if (isDelete && fetchType == FetchType.EAGER) {
                                whereQuery = " LicenseDetails8.addressLine2 = ? ";
                                counter++;
                              } else {
                                whereQuery =
                                    " LicenseDetails8.address.addressLine2 = :LicenseDetails8addressaddressLine2"
                                        + j
                                        + " ";
                              }

                              isOpenParentheses = false;
                              whereValue =
                                  model
                                      .getReception()
                                      .getPortfolio()
                                      .getPortfolioLicenses()
                                      .get(i5)
                                      .getLicenseDetails()
                                      .get(i8)
                                      .getAddress()
                                      .getAddressLine2();
                              query +=
                                  (andCondition
                                      + Facade.getStatement(
                                          statement,
                                          "LicenseDetails8.address.addressLine2",
                                          "LicenseDetails8addressaddressLine2" + j,
                                          mapVariable,
                                          whereQuery,
                                          whereValue,
                                          isExistRecord,
                                          isDelete,
                                          fetchType,
                                          counter));
                              whereQuery = "";
                            }
                            if (!isExistRecord
                                && !isDelete
                                && true
                                && model
                                        .getReception()
                                        .getPortfolio()
                                        .getPortfolioLicenses()
                                        .get(i5)
                                        .getLicenseDetails()
                                        .get(i8)
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
                                      .getReception()
                                      .getPortfolio()
                                      .getPortfolioLicenses()
                                      .get(i5)
                                      .getLicenseDetails()
                                      .get(i8)
                                      .getAddress()
                                      .getCity();
                              if (isDelete && fetchType == FetchType.EAGER) {
                                whereQuery = " LicenseDetails8.city = ? ";
                                counter++;
                              } else {
                                whereQuery =
                                    " LicenseDetails8.address.city = :LicenseDetails8addresscity"
                                        + j
                                        + " ";
                              }

                              isOpenParentheses = false;
                              whereValue =
                                  model
                                      .getReception()
                                      .getPortfolio()
                                      .getPortfolioLicenses()
                                      .get(i5)
                                      .getLicenseDetails()
                                      .get(i8)
                                      .getAddress()
                                      .getCity();
                              query +=
                                  (andCondition
                                      + Facade.getStatement(
                                          statement,
                                          "LicenseDetails8.address.city",
                                          "LicenseDetails8addresscity" + j,
                                          mapVariable,
                                          whereQuery,
                                          whereValue,
                                          isExistRecord,
                                          isDelete,
                                          fetchType,
                                          counter));
                              whereQuery = "";
                            }
                            if (!isExistRecord
                                && !isDelete
                                && true
                                && model
                                        .getReception()
                                        .getPortfolio()
                                        .getPortfolioLicenses()
                                        .get(i5)
                                        .getLicenseDetails()
                                        .get(i8)
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
                                      .getReception()
                                      .getPortfolio()
                                      .getPortfolioLicenses()
                                      .get(i5)
                                      .getLicenseDetails()
                                      .get(i8)
                                      .getAddress()
                                      .getState();
                              if (isDelete && fetchType == FetchType.EAGER) {
                                whereQuery = " LicenseDetails8.state = ? ";
                                counter++;
                              } else {
                                whereQuery =
                                    " LicenseDetails8.address.state = :LicenseDetails8addressstate"
                                        + j
                                        + " ";
                              }

                              isOpenParentheses = false;
                              whereValue =
                                  model
                                      .getReception()
                                      .getPortfolio()
                                      .getPortfolioLicenses()
                                      .get(i5)
                                      .getLicenseDetails()
                                      .get(i8)
                                      .getAddress()
                                      .getState();
                              query +=
                                  (andCondition
                                      + Facade.getStatement(
                                          statement,
                                          "LicenseDetails8.address.state",
                                          "LicenseDetails8addressstate" + j,
                                          mapVariable,
                                          whereQuery,
                                          whereValue,
                                          isExistRecord,
                                          isDelete,
                                          fetchType,
                                          counter));
                              whereQuery = "";
                            }
                            if (!isExistRecord
                                && !isDelete
                                && true
                                && model
                                        .getReception()
                                        .getPortfolio()
                                        .getPortfolioLicenses()
                                        .get(i5)
                                        .getLicenseDetails()
                                        .get(i8)
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
                                      .getReception()
                                      .getPortfolio()
                                      .getPortfolioLicenses()
                                      .get(i5)
                                      .getLicenseDetails()
                                      .get(i8)
                                      .getAddress()
                                      .getCountry();
                              if (isDelete && fetchType == FetchType.EAGER) {
                                whereQuery = " LicenseDetails8.country = ? ";
                                counter++;
                              } else {
                                whereQuery =
                                    " LicenseDetails8.address.country = :LicenseDetails8addresscountry"
                                        + j
                                        + " ";
                              }

                              isOpenParentheses = false;
                              whereValue =
                                  model
                                      .getReception()
                                      .getPortfolio()
                                      .getPortfolioLicenses()
                                      .get(i5)
                                      .getLicenseDetails()
                                      .get(i8)
                                      .getAddress()
                                      .getCountry();
                              query +=
                                  (andCondition
                                      + Facade.getStatement(
                                          statement,
                                          "LicenseDetails8.address.country",
                                          "LicenseDetails8addresscountry" + j,
                                          mapVariable,
                                          whereQuery,
                                          whereValue,
                                          isExistRecord,
                                          isDelete,
                                          fetchType,
                                          counter));
                              whereQuery = "";
                            }
                            if (!isExistRecord
                                && !isDelete
                                && true
                                && model
                                        .getReception()
                                        .getPortfolio()
                                        .getPortfolioLicenses()
                                        .get(i5)
                                        .getLicenseDetails()
                                        .get(i8)
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
                                      .getReception()
                                      .getPortfolio()
                                      .getPortfolioLicenses()
                                      .get(i5)
                                      .getLicenseDetails()
                                      .get(i8)
                                      .getAddress()
                                      .getZipCode();
                              if (isDelete && fetchType == FetchType.EAGER) {
                                whereQuery = " LicenseDetails8.zipCode = ? ";
                                counter++;
                              } else {
                                whereQuery =
                                    " LicenseDetails8.address.zipCode = :LicenseDetails8addresszipCode"
                                        + j
                                        + " ";
                              }

                              isOpenParentheses = false;
                              whereValue =
                                  model
                                      .getReception()
                                      .getPortfolio()
                                      .getPortfolioLicenses()
                                      .get(i5)
                                      .getLicenseDetails()
                                      .get(i8)
                                      .getAddress()
                                      .getZipCode();
                              query +=
                                  (andCondition
                                      + Facade.getStatement(
                                          statement,
                                          "LicenseDetails8.address.zipCode",
                                          "LicenseDetails8addresszipCode" + j,
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
                        && true
                        && model
                                .getReception()
                                .getPortfolio()
                                .getPortfolioLicenses()
                                .get(i5)
                                .getIssueVerifierType()
                            != null) {

                      andCondition = " AND ";
                      if (isOpenParentheses) {
                        andCondition = " ";
                      }
                      query += " ";
                      whereValue =
                          model
                              .getReception()
                              .getPortfolio()
                              .getPortfolioLicenses()
                              .get(i5)
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
                              .getReception()
                              .getPortfolio()
                              .getPortfolioLicenses()
                              .get(i5)
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
                                .getReception()
                                .getPortfolio()
                                .getPortfolioLicenses()
                                .get(i5)
                                .getLicenseNumber()
                            != null) {

                      andCondition = " AND ";
                      if (isOpenParentheses) {
                        andCondition = " ";
                      }
                      query += " ";
                      whereValue =
                          model
                              .getReception()
                              .getPortfolio()
                              .getPortfolioLicenses()
                              .get(i5)
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
                              .getReception()
                              .getPortfolio()
                              .getPortfolioLicenses()
                              .get(i5)
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
                                .getReception()
                                .getPortfolio()
                                .getPortfolioLicenses()
                                .get(i5)
                                .getLicenseType()
                            != null) {

                      andCondition = " AND ";
                      if (isOpenParentheses) {
                        andCondition = " ";
                      }
                      query += " ";
                      whereValue =
                          model
                              .getReception()
                              .getPortfolio()
                              .getPortfolioLicenses()
                              .get(i5)
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
                              .getReception()
                              .getPortfolio()
                              .getPortfolioLicenses()
                              .get(i5)
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
                                .getReception()
                                .getPortfolio()
                                .getPortfolioLicenses()
                                .get(i5)
                                .getIssueDate()
                            != null) {

                      andCondition = " AND ";
                      if (isOpenParentheses) {
                        andCondition = " ";
                      }
                      query += " ";
                      whereValue =
                          model
                              .getReception()
                              .getPortfolio()
                              .getPortfolioLicenses()
                              .get(i5)
                              .getIssueDate();
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " PortfolioLicense5.issueDate = ? ";
                        counter++;
                      } else {
                        whereQuery =
                            " PortfolioLicense5.issueDate = :PortfolioLicense5issueDate" + j + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          model
                              .getReception()
                              .getPortfolio()
                              .getPortfolioLicenses()
                              .get(i5)
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
                                .getReception()
                                .getPortfolio()
                                .getPortfolioLicenses()
                                .get(i5)
                                .getEndDate()
                            != null) {

                      andCondition = " AND ";
                      if (isOpenParentheses) {
                        andCondition = " ";
                      }
                      query += " ";
                      whereValue =
                          model
                              .getReception()
                              .getPortfolio()
                              .getPortfolioLicenses()
                              .get(i5)
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
                              .getReception()
                              .getPortfolio()
                              .getPortfolioLicenses()
                              .get(i5)
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
                                .getReception()
                                .getPortfolio()
                                .getPortfolioLicenses()
                                .get(i5)
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
                                      .getReception()
                                      .getPortfolio()
                                      .getPortfolioLicenses()
                                      .get(i5)
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
                                      .getReception()
                                      .getPortfolio()
                                      .getPortfolioLicenses()
                                      .get(i5)
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
                  && fetchType == FetchType.EAGER
                  && model.getReception().getPortfolio().getPortfolioLocations() != null) {
                int length14 = model.getReception().getPortfolio().getPortfolioLocations().size();

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
                            .getReception()
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
                              .getReception()
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
                              .getReception()
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
                                .getReception()
                                .getPortfolio()
                                .getPortfolioLocations()
                                .get(i14)
                                .getFile()
                            != null) {

                      if (model
                              .getReception()
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
                                .getReception()
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
                                .getReception()
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
                                  .getReception()
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
                                .getReception()
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
                                .getReception()
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
                                  .getReception()
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
                                        .getReception()
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
                                        .getReception()
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
                                .getReception()
                                .getPortfolio()
                                .getPortfolioLocations()
                                .get(i14)
                                .getLocationDetails()
                            != null) {
                      int length17 =
                          model
                              .getReception()
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
                                  .getReception()
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
                                    .getReception()
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
                                    .getReception()
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
                                      .getReception()
                                      .getPortfolio()
                                      .getPortfolioLocations()
                                      .get(i14)
                                      .getLocationDetails()
                                      .get(i17)
                                      .getPortfolioLocation()
                                  != null) {

                            if (model
                                    .getReception()
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
                                      .getReception()
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
                                      .getReception()
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
                                        .getReception()
                                        .getPortfolio()
                                        .getPortfolioLocations()
                                        .get(i14)
                                        .getLocationDetails()
                                        .get(i17)
                                        .getPortfolioLocation()
                                        .getFile()
                                    != null) {

                              if (model
                                      .getReception()
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
                                        .getReception()
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
                                        .getReception()
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
                                          .getReception()
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
                                        .getReception()
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
                                        .getReception()
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
                                          .getReception()
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
                                                .getReception()
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
                                                .getReception()
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
                                        .getReception()
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
                                      .getReception()
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
                                      .getReception()
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
                                        .getReception()
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
                                      .getReception()
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
                                      .getReception()
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
                                        .getReception()
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
                                      .getReception()
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
                                      .getReception()
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
                                        .getReception()
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
                                      .getReception()
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
                                      .getReception()
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
                                        .getReception()
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
                                      .getReception()
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
                                      .getReception()
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
                                        .getReception()
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
                                      .getReception()
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
                                      .getReception()
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
                                        .getReception()
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
                                      .getReception()
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
                                      .getReception()
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
                                        .getReception()
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
                                              .getReception()
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
                                              .getReception()
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
                                      .getReception()
                                      .getPortfolio()
                                      .getPortfolioLocations()
                                      .get(i14)
                                      .getLocationDetails()
                                      .get(i17)
                                      .getFile()
                                  != null) {

                            if (model
                                    .getReception()
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
                                      .getReception()
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
                                      .getReception()
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
                                        .getReception()
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
                                      .getReception()
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
                                      .getReception()
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
                                        .getReception()
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
                                              .getReception()
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
                                              .getReception()
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
                                      .getReception()
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
                                        .getReception()
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
                                      .getReception()
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
                                      .getReception()
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
                                        .getReception()
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
                                      .getReception()
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
                                      .getReception()
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
                                        .getReception()
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
                                      .getReception()
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
                                      .getReception()
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
                                        .getReception()
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
                                      .getReception()
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
                                      .getReception()
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
                                        .getReception()
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
                                      .getReception()
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
                                      .getReception()
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
                                        .getReception()
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
                                      .getReception()
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
                                      .getReception()
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
                                .getReception()
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
                              .getReception()
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
                              .getReception()
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
                                .getReception()
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
                              .getReception()
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
                              .getReception()
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
                                .getReception()
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
                              .getReception()
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
                              .getReception()
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
                                .getReception()
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
                              .getReception()
                              .getPortfolio()
                              .getPortfolioLocations()
                              .get(i14)
                              .getAddress();
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " PortfolioLocation14.address = ? ";
                        counter++;
                      } else {
                        whereQuery =
                            " PortfolioLocation14.address = :PortfolioLocation14address" + j + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          model
                              .getReception()
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
                                .getReception()
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
                              .getReception()
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
                              .getReception()
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
                                .getReception()
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
                              .getReception()
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
                              .getReception()
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
                                .getReception()
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
                              .getReception()
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
                              .getReception()
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
                                .getReception()
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
                                      .getReception()
                                      .getPortfolio()
                                      .getPortfolioLocations()
                                      .get(i14)
                                      .getVersion());
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " PortfolioLocation14.VERSION = ? ";
                        counter++;
                      } else {
                        whereQuery =
                            " PortfolioLocation14.version = :PortfolioLocation14version" + j + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          new java.lang.Integer(
                              ""
                                  + model
                                      .getReception()
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
                  && model.getReception().getPortfolio().getReceptions() != null) {
                int length23 = model.getReception().getPortfolio().getReceptions().size();

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
                            .getReception()
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
                              .getReception()
                              .getPortfolio()
                              .getReceptions()
                              .get(i23)
                              .getReceptionNo();
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " Reception23.receptionNo = ? ";
                        counter++;
                      } else {
                        whereQuery = " Reception23.receptionNo = :Reception23receptionNo" + j + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          model
                              .getReception()
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
                        && model.getReception().getPortfolio().getReceptions().get(i23).getChannel()
                            != null) {

                      andCondition = " AND ";
                      if (isOpenParentheses) {
                        andCondition = " ";
                      }
                      query += " ";
                      whereValue =
                          model.getReception().getPortfolio().getReceptions().get(i23).getChannel();
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " Reception23.channel = ? ";
                        counter++;
                      } else {
                        whereQuery = " Reception23.channel = :Reception23channel" + j + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          model.getReception().getPortfolio().getReceptions().get(i23).getChannel();
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
                                .getReception()
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
                              .getReception()
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
                              .getReception()
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
                                .getReception()
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
                                  .getReception()
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
                            " Reception23.receptionTitle LIKE :Reception23receptionTitle" + j + " ";
                      }

                      isOpenParentheses = false;
                      if (isExistRecord || isDelete) {
                        if (isDelete && fetchType == FetchType.EAGER) {
                          whereQuery = " Reception23.receptionTitle = ? ";
                          counter++;
                        } else {
                          whereValue =
                              model
                                  .getReception()
                                  .getPortfolio()
                                  .getReceptions()
                                  .get(i23)
                                  .getReceptionTitle();
                        }
                      } else {
                        whereValue =
                            model
                                    .getReception()
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
                                .getReception()
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
                              .getReception()
                              .getPortfolio()
                              .getReceptions()
                              .get(i23)
                              .getProductType();
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " Reception23.productType = ? ";
                        counter++;
                      } else {
                        whereQuery = " Reception23.productType = :Reception23productType" + j + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          model
                              .getReception()
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
                                .getReception()
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
                              .getReception()
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
                              .getReception()
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
                                .getReception()
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
                              .getReception()
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
                              .getReception()
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
                        && model.getReception().getPortfolio().getReceptions().get(i23).getLow141()
                            != null) {

                      andCondition = " AND ";
                      if (isOpenParentheses) {
                        andCondition = " ";
                      }
                      query += " ";
                      whereValue =
                          model.getReception().getPortfolio().getReceptions().get(i23).getLow141();
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " Reception23.low141 = ? ";
                        counter++;
                      } else {
                        whereQuery = " Reception23.low141 = :Reception23low141" + j + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          model.getReception().getPortfolio().getReceptions().get(i23).getLow141();
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
                                .getReception()
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
                              .getReception()
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
                              .getReception()
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
                        && model.getReception().getPortfolio().getReceptions().get(i23).getDate()
                            != null) {

                      andCondition = " AND ";
                      if (isOpenParentheses) {
                        andCondition = " ";
                      }
                      query += " ";
                      whereValue =
                          model.getReception().getPortfolio().getReceptions().get(i23).getDate();
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " Reception23.date1 = ? ";
                        counter++;
                      } else {
                        whereQuery = " Reception23.date = :Reception23date" + j + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          model.getReception().getPortfolio().getReceptions().get(i23).getDate();
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
                                .getReception()
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
                              .getReception()
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
                              .getReception()
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
                                .getReception()
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
                                  .getReception()
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
                                  .getReception()
                                  .getPortfolio()
                                  .getReceptions()
                                  .get(i23)
                                  .getCustomerCode();
                        }
                      } else {
                        whereValue =
                            "%"
                                + model
                                    .getReception()
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
                        && model.getReception().getPortfolio().getReceptions().get(i23).getBranch()
                            != null) {

                      andCondition = " AND ";
                      if (isOpenParentheses) {
                        andCondition = " ";
                      }
                      query += " ";

                      whereValue =
                          "%"
                              + model
                                  .getReception()
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
                                  .getReception()
                                  .getPortfolio()
                                  .getReceptions()
                                  .get(i23)
                                  .getBranch();
                        }
                      } else {
                        whereValue =
                            "%"
                                + model
                                    .getReception()
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
                                .getReception()
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
                              .getReception()
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
                              .getReception()
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
                                .getReception()
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
                              .getReception()
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
                              .getReception()
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
                                .getReception()
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
                              .getReception()
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
                              .getReception()
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
                                .getReception()
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
                              .getReception()
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
                              .getReception()
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
                                .getReception()
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
                              .getReception()
                              .getPortfolio()
                              .getReceptions()
                              .get(i23)
                              .getCheckRejectType();
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " Reception23.checkRejectType = ? ";
                        counter++;
                      } else {
                        whereQuery =
                            " Reception23.checkRejectType = :Reception23checkRejectType" + j + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          model
                              .getReception()
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
                                .getReception()
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
                              .getReception()
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
                              .getReception()
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
                                .getReception()
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
                              .getReception()
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
                              .getReception()
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
                                .getReception()
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
                              .getReception()
                              .getPortfolio()
                              .getReceptions()
                              .get(i23)
                              .getIrrEstimate();
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " Reception23.irrEstimate = ? ";
                        counter++;
                      } else {
                        whereQuery = " Reception23.irrEstimate = :Reception23irrEstimate" + j + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          model
                              .getReception()
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
                                .getReception()
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
                              .getReception()
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
                              .getReception()
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
                                .getReception()
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
                              .getReception()
                              .getPortfolio()
                              .getReceptions()
                              .get(i23)
                              .getCashControl();
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " Reception23.cashControl = ? ";
                        counter++;
                      } else {
                        whereQuery = " Reception23.cashControl = :Reception23cashControl" + j + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          model
                              .getReception()
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
                                .getReception()
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
                              .getReception()
                              .getPortfolio()
                              .getReceptions()
                              .get(i23)
                              .getExportMarketDsc();
                      if (isDelete && fetchType == FetchType.EAGER) {
                        whereQuery = " Reception23.exportMarketDsc = ? ";
                        counter++;
                      } else {
                        whereQuery =
                            " Reception23.exportMarketDsc = :Reception23exportMarketDsc" + j + " ";
                      }

                      isOpenParentheses = false;
                      whereValue =
                          model
                              .getReception()
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
                                .getReception()
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
                              .getReception()
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
                              .getReception()
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
                                .getReception()
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
                              .getReception()
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
                              .getReception()
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
                                .getReception()
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
                              .getReception()
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
                              .getReception()
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
                                .getReception()
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
                              .getReception()
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
                              .getReception()
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
                                .getReception()
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
                              .getReception()
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
                              .getReception()
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
                                .getReception()
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
                              .getReception()
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
                              .getReception()
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
                                .getReception()
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
                              .getReception()
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
                              .getReception()
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
                                .getReception()
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
                              .getReception()
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
                              .getReception()
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
                                .getReception()
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
                              .getReception()
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
                              .getReception()
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
                                .getReception()
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
                              .getReception()
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
                              .getReception()
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
                                .getReception()
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
                              .getReception()
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
                              .getReception()
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
                                .getReception()
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
                              .getReception()
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
                              .getReception()
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
                        && model.getReception().getPortfolio().getReceptions().get(i23).getVersion()
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
                                      .getReception()
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
                                      .getReception()
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
              if (!isDelete && model.getReception().getPortfolio().getAddress() != null) {
                if (!isDelete
                    && true
                    && model.getReception().getPortfolio().getAddress().getAddressLine1() != null) {

                  andCondition = " AND ";
                  if (isOpenParentheses) {
                    andCondition = " ";
                  }
                  query += " ";
                  if (isDelete && fetchType == FetchType.EAGER) {
                    whereQuery = " Portfolio4.addressLine1 = ? ";
                    counter++;
                  } else {
                    whereQuery =
                        " Portfolio4.address.addressLine1 = :Portfolio4addressaddressLine1"
                            + j
                            + " ";
                  }

                  isOpenParentheses = false;
                  whereValue = model.getReception().getPortfolio().getAddress().getAddressLine1();
                  query +=
                      (andCondition
                          + Facade.getStatement(
                              statement,
                              "Portfolio4.address.addressLine1",
                              "Portfolio4addressaddressLine1" + j,
                              mapVariable,
                              whereQuery,
                              whereValue,
                              isExistRecord,
                              isDelete,
                              fetchType,
                              counter));
                  whereQuery = "";
                }
                if (!isExistRecord
                    && !isDelete
                    && true
                    && model.getReception().getPortfolio().getAddress().getAddressLine2() != null) {

                  andCondition = " AND ";
                  if (isOpenParentheses) {
                    andCondition = " ";
                  }
                  query += " ";
                  if (isDelete && fetchType == FetchType.EAGER) {
                    whereQuery = " Portfolio4.addressLine2 = ? ";
                    counter++;
                  } else {
                    whereQuery =
                        " Portfolio4.address.addressLine2 = :Portfolio4addressaddressLine2"
                            + j
                            + " ";
                  }

                  isOpenParentheses = false;
                  whereValue = model.getReception().getPortfolio().getAddress().getAddressLine2();
                  query +=
                      (andCondition
                          + Facade.getStatement(
                              statement,
                              "Portfolio4.address.addressLine2",
                              "Portfolio4addressaddressLine2" + j,
                              mapVariable,
                              whereQuery,
                              whereValue,
                              isExistRecord,
                              isDelete,
                              fetchType,
                              counter));
                  whereQuery = "";
                }
                if (!isExistRecord
                    && !isDelete
                    && true
                    && model.getReception().getPortfolio().getAddress().getCity() != null) {

                  andCondition = " AND ";
                  if (isOpenParentheses) {
                    andCondition = " ";
                  }
                  query += " ";
                  if (isDelete && fetchType == FetchType.EAGER) {
                    whereQuery = " Portfolio4.city = ? ";
                    counter++;
                  } else {
                    whereQuery = " Portfolio4.address.city = :Portfolio4addresscity" + j + " ";
                  }

                  isOpenParentheses = false;
                  whereValue = model.getReception().getPortfolio().getAddress().getCity();
                  query +=
                      (andCondition
                          + Facade.getStatement(
                              statement,
                              "Portfolio4.address.city",
                              "Portfolio4addresscity" + j,
                              mapVariable,
                              whereQuery,
                              whereValue,
                              isExistRecord,
                              isDelete,
                              fetchType,
                              counter));
                  whereQuery = "";
                }
                if (!isExistRecord
                    && !isDelete
                    && true
                    && model.getReception().getPortfolio().getAddress().getState() != null) {

                  andCondition = " AND ";
                  if (isOpenParentheses) {
                    andCondition = " ";
                  }
                  query += " ";
                  if (isDelete && fetchType == FetchType.EAGER) {
                    whereQuery = " Portfolio4.state = ? ";
                    counter++;
                  } else {
                    whereQuery = " Portfolio4.address.state = :Portfolio4addressstate" + j + " ";
                  }

                  isOpenParentheses = false;
                  whereValue = model.getReception().getPortfolio().getAddress().getState();
                  query +=
                      (andCondition
                          + Facade.getStatement(
                              statement,
                              "Portfolio4.address.state",
                              "Portfolio4addressstate" + j,
                              mapVariable,
                              whereQuery,
                              whereValue,
                              isExistRecord,
                              isDelete,
                              fetchType,
                              counter));
                  whereQuery = "";
                }
                if (!isExistRecord
                    && !isDelete
                    && true
                    && model.getReception().getPortfolio().getAddress().getCountry() != null) {

                  andCondition = " AND ";
                  if (isOpenParentheses) {
                    andCondition = " ";
                  }
                  query += " ";
                  if (isDelete && fetchType == FetchType.EAGER) {
                    whereQuery = " Portfolio4.country = ? ";
                    counter++;
                  } else {
                    whereQuery =
                        " Portfolio4.address.country = :Portfolio4addresscountry" + j + " ";
                  }

                  isOpenParentheses = false;
                  whereValue = model.getReception().getPortfolio().getAddress().getCountry();
                  query +=
                      (andCondition
                          + Facade.getStatement(
                              statement,
                              "Portfolio4.address.country",
                              "Portfolio4addresscountry" + j,
                              mapVariable,
                              whereQuery,
                              whereValue,
                              isExistRecord,
                              isDelete,
                              fetchType,
                              counter));
                  whereQuery = "";
                }
                if (!isExistRecord
                    && !isDelete
                    && true
                    && model.getReception().getPortfolio().getAddress().getZipCode() != null) {

                  andCondition = " AND ";
                  if (isOpenParentheses) {
                    andCondition = " ";
                  }
                  query += " ";
                  if (isDelete && fetchType == FetchType.EAGER) {
                    whereQuery = " Portfolio4.zipCode = ? ";
                    counter++;
                  } else {
                    whereQuery =
                        " Portfolio4.address.zipCode = :Portfolio4addresszipCode" + j + " ";
                  }

                  isOpenParentheses = false;
                  whereValue = model.getReception().getPortfolio().getAddress().getZipCode();
                  query +=
                      (andCondition
                          + Facade.getStatement(
                              statement,
                              "Portfolio4.address.zipCode",
                              "Portfolio4addresszipCode" + j,
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
                  && model.getReception().getPortfolio().getCustomerId() != null) {

                andCondition = " AND ";
                if (isOpenParentheses) {
                  andCondition = " ";
                }
                query += " ";
                if (isDelete && fetchType == FetchType.EAGER) {
                  whereQuery = " Portfolio4.customerId = ? ";
                  counter++;
                } else {
                  whereQuery = " Portfolio4.customerId = :Portfolio4customerId" + j + " ";
                }

                isOpenParentheses = false;
                whereValue = model.getReception().getPortfolio().getCustomerId();
                query +=
                    (andCondition
                        + Facade.getStatement(
                            statement,
                            "Portfolio4.customerId",
                            "Portfolio4customerId" + j,
                            mapVariable,
                            whereQuery,
                            whereValue,
                            isExistRecord,
                            isDelete,
                            fetchType,
                            counter));
                whereQuery = "";
              }
              if (!isExistRecord
                  && !isDelete
                  && true
                  && model.getReception().getPortfolio().getTitle() != null) {

                andCondition = " AND ";
                if (isOpenParentheses) {
                  andCondition = " ";
                }
                query += " ";
                if (isDelete && fetchType == FetchType.EAGER) {
                  whereQuery = " Portfolio4.title = ? ";
                  counter++;
                } else {
                  whereQuery = " Portfolio4.title = :Portfolio4title" + j + " ";
                }

                isOpenParentheses = false;
                whereValue = model.getReception().getPortfolio().getTitle();
                query +=
                    (andCondition
                        + Facade.getStatement(
                            statement,
                            "Portfolio4.title",
                            "Portfolio4title" + j,
                            mapVariable,
                            whereQuery,
                            whereValue,
                            isExistRecord,
                            isDelete,
                            fetchType,
                            counter));
                whereQuery = "";
              }
              if (!isExistRecord
                  && !isDelete
                  && true
                  && model.getReception().getPortfolio().getBranchCode() != null) {

                andCondition = " AND ";
                if (isOpenParentheses) {
                  andCondition = " ";
                }
                query += " ";
                if (isDelete && fetchType == FetchType.EAGER) {
                  whereQuery = " Portfolio4.branchCode = ? ";
                  counter++;
                } else {
                  whereQuery = " Portfolio4.branchCode = :Portfolio4branchCode" + j + " ";
                }

                isOpenParentheses = false;
                whereValue = model.getReception().getPortfolio().getBranchCode();
                query +=
                    (andCondition
                        + Facade.getStatement(
                            statement,
                            "Portfolio4.branchCode",
                            "Portfolio4branchCode" + j,
                            mapVariable,
                            whereQuery,
                            whereValue,
                            isExistRecord,
                            isDelete,
                            fetchType,
                            counter));
                whereQuery = "";
              }
              if (!isExistRecord
                  && !isDelete
                  && true
                  && model.getReception().getPortfolio().getEconomicType() != null) {

                andCondition = " AND ";
                if (isOpenParentheses) {
                  andCondition = " ";
                }
                query += " ";
                if (isDelete && fetchType == FetchType.EAGER) {
                  whereQuery = " Portfolio4.economicType = ? ";
                  counter++;
                } else {
                  whereQuery = " Portfolio4.economicType = :Portfolio4economicType" + j + " ";
                }

                isOpenParentheses = false;
                whereValue = model.getReception().getPortfolio().getEconomicType();
                query +=
                    (andCondition
                        + Facade.getStatement(
                            statement,
                            "Portfolio4.economicType",
                            "Portfolio4economicType" + j,
                            mapVariable,
                            whereQuery,
                            whereValue,
                            isExistRecord,
                            isDelete,
                            fetchType,
                            counter));
                whereQuery = "";
              }
              if (!isExistRecord
                  && !isDelete
                  && true
                  && model.getReception().getPortfolio().getIndustryType() != null) {

                andCondition = " AND ";
                if (isOpenParentheses) {
                  andCondition = " ";
                }
                query += " ";
                if (isDelete && fetchType == FetchType.EAGER) {
                  whereQuery = " Portfolio4.industryType = ? ";
                  counter++;
                } else {
                  whereQuery = " Portfolio4.industryType = :Portfolio4industryType" + j + " ";
                }

                isOpenParentheses = false;
                whereValue = model.getReception().getPortfolio().getIndustryType();
                query +=
                    (andCondition
                        + Facade.getStatement(
                            statement,
                            "Portfolio4.industryType",
                            "Portfolio4industryType" + j,
                            mapVariable,
                            whereQuery,
                            whereValue,
                            isExistRecord,
                            isDelete,
                            fetchType,
                            counter));
                whereQuery = "";
              }
              if (!isExistRecord
                  && !isDelete
                  && true
                  && model.getReception().getPortfolio().getTechnologyDescription() != null) {

                andCondition = " AND ";
                if (isOpenParentheses) {
                  andCondition = " ";
                }
                query += " ";
                if (isDelete && fetchType == FetchType.EAGER) {
                  whereQuery = " Portfolio4.techDescription = ? ";
                  counter++;
                } else {
                  whereQuery =
                      " Portfolio4.technologyDescription = :Portfolio4technologyDescription"
                          + j
                          + " ";
                }

                isOpenParentheses = false;
                whereValue = model.getReception().getPortfolio().getTechnologyDescription();
                query +=
                    (andCondition
                        + Facade.getStatement(
                            statement,
                            "Portfolio4.technologyDescription",
                            "Portfolio4technologyDescription" + j,
                            mapVariable,
                            whereQuery,
                            whereValue,
                            isExistRecord,
                            isDelete,
                            fetchType,
                            counter));
                whereQuery = "";
              }
              if (!isExistRecord
                  && !isDelete
                  && true
                  && model.getReception().getPortfolio().getStateType() != null) {

                andCondition = " AND ";
                if (isOpenParentheses) {
                  andCondition = " ";
                }
                query += " ";
                if (isDelete && fetchType == FetchType.EAGER) {
                  whereQuery = " Portfolio4.stateType = ? ";
                  counter++;
                } else {
                  whereQuery = " Portfolio4.stateType = :Portfolio4stateType" + j + " ";
                }

                isOpenParentheses = false;
                whereValue = model.getReception().getPortfolio().getStateType();
                query +=
                    (andCondition
                        + Facade.getStatement(
                            statement,
                            "Portfolio4.stateType",
                            "Portfolio4stateType" + j,
                            mapVariable,
                            whereQuery,
                            whereValue,
                            isExistRecord,
                            isDelete,
                            fetchType,
                            counter));
                whereQuery = "";
              }
              if (!isExistRecord
                  && !isDelete
                  && true
                  && model.getReception().getPortfolio().getLifeType() != null) {

                andCondition = " AND ";
                if (isOpenParentheses) {
                  andCondition = " ";
                }
                query += " ";
                if (isDelete && fetchType == FetchType.EAGER) {
                  whereQuery = " Portfolio4.lifeType = ? ";
                  counter++;
                } else {
                  whereQuery = " Portfolio4.lifeType = :Portfolio4lifeType" + j + " ";
                }

                isOpenParentheses = false;
                whereValue = model.getReception().getPortfolio().getLifeType();
                query +=
                    (andCondition
                        + Facade.getStatement(
                            statement,
                            "Portfolio4.lifeType",
                            "Portfolio4lifeType" + j,
                            mapVariable,
                            whereQuery,
                            whereValue,
                            isExistRecord,
                            isDelete,
                            fetchType,
                            counter));
                whereQuery = "";
              }
              if (!isExistRecord
                  && !isDelete
                  && true
                  && model.getReception().getPortfolio().getStartDate() != null) {

                andCondition = " AND ";
                if (isOpenParentheses) {
                  andCondition = " ";
                }
                query += " ";
                if (isDelete && fetchType == FetchType.EAGER) {
                  whereQuery = " Portfolio4.startDate = ? ";
                  counter++;
                } else {
                  whereQuery = " Portfolio4.startDate = :Portfolio4startDate" + j + " ";
                }

                isOpenParentheses = false;
                whereValue = model.getReception().getPortfolio().getStartDate();
                query +=
                    (andCondition
                        + Facade.getStatement(
                            statement,
                            "Portfolio4.startDate",
                            "Portfolio4startDate" + j,
                            mapVariable,
                            whereQuery,
                            whereValue,
                            isExistRecord,
                            isDelete,
                            fetchType,
                            counter));
                whereQuery = "";
              }
              if (!isExistRecord
                  && !isDelete
                  && true
                  && model.getReception().getPortfolio().getEndDate() != null) {

                andCondition = " AND ";
                if (isOpenParentheses) {
                  andCondition = " ";
                }
                query += " ";
                if (isDelete && fetchType == FetchType.EAGER) {
                  whereQuery = " Portfolio4.endDate = ? ";
                  counter++;
                } else {
                  whereQuery = " Portfolio4.endDate = :Portfolio4endDate" + j + " ";
                }

                isOpenParentheses = false;
                whereValue = model.getReception().getPortfolio().getEndDate();
                query +=
                    (andCondition
                        + Facade.getStatement(
                            statement,
                            "Portfolio4.endDate",
                            "Portfolio4endDate" + j,
                            mapVariable,
                            whereQuery,
                            whereValue,
                            isExistRecord,
                            isDelete,
                            fetchType,
                            counter));
                whereQuery = "";
              }
              if (!isExistRecord
                  && !isDelete
                  && true
                  && model.getReception().getPortfolio().getRequestDate() != null) {

                andCondition = " AND ";
                if (isOpenParentheses) {
                  andCondition = " ";
                }
                query += " ";
                if (isDelete && fetchType == FetchType.EAGER) {
                  whereQuery = " Portfolio4.requestDate = ? ";
                  counter++;
                } else {
                  whereQuery = " Portfolio4.requestDate = :Portfolio4requestDate" + j + " ";
                }

                isOpenParentheses = false;
                whereValue = model.getReception().getPortfolio().getRequestDate();
                query +=
                    (andCondition
                        + Facade.getStatement(
                            statement,
                            "Portfolio4.requestDate",
                            "Portfolio4requestDate" + j,
                            mapVariable,
                            whereQuery,
                            whereValue,
                            isExistRecord,
                            isDelete,
                            fetchType,
                            counter));
                whereQuery = "";
              }
              if (!isExistRecord
                  && !isDelete
                  && true
                  && model.getReception().getPortfolio().getCapitalAMT() != null) {

                andCondition = " AND ";
                if (isOpenParentheses) {
                  andCondition = " ";
                }
                query += " ";
                if (isDelete && fetchType == FetchType.EAGER) {
                  whereQuery = " Portfolio4.capitalAMT = ? ";
                  counter++;
                } else {
                  whereQuery = " Portfolio4.capitalAMT = :Portfolio4capitalAMT" + j + " ";
                }

                isOpenParentheses = false;
                whereValue = model.getReception().getPortfolio().getCapitalAMT();
                query +=
                    (andCondition
                        + Facade.getStatement(
                            statement,
                            "Portfolio4.capitalAMT",
                            "Portfolio4capitalAMT" + j,
                            mapVariable,
                            whereQuery,
                            whereValue,
                            isExistRecord,
                            isDelete,
                            fetchType,
                            counter));
                whereQuery = "";
              }
              if (!isExistRecord
                  && !isDelete
                  && true
                  && model.getReception().getPortfolio().getInvestableAMT() != null) {

                andCondition = " AND ";
                if (isOpenParentheses) {
                  andCondition = " ";
                }
                query += " ";
                if (isDelete && fetchType == FetchType.EAGER) {
                  whereQuery = " Portfolio4.investableAMT = ? ";
                  counter++;
                } else {
                  whereQuery = " Portfolio4.investableAMT = :Portfolio4investableAMT" + j + " ";
                }

                isOpenParentheses = false;
                whereValue = model.getReception().getPortfolio().getInvestableAMT();
                query +=
                    (andCondition
                        + Facade.getStatement(
                            statement,
                            "Portfolio4.investableAMT",
                            "Portfolio4investableAMT" + j,
                            mapVariable,
                            whereQuery,
                            whereValue,
                            isExistRecord,
                            isDelete,
                            fetchType,
                            counter));
                whereQuery = "";
              }
              if (!isExistRecord
                  && !isDelete
                  && true
                  && model.getReception().getPortfolio().getAssessdAMT() != null) {

                andCondition = " AND ";
                if (isOpenParentheses) {
                  andCondition = " ";
                }
                query += " ";
                if (isDelete && fetchType == FetchType.EAGER) {
                  whereQuery = " Portfolio4.assessdAMT = ? ";
                  counter++;
                } else {
                  whereQuery = " Portfolio4.assessdAMT = :Portfolio4assessdAMT" + j + " ";
                }

                isOpenParentheses = false;
                whereValue = model.getReception().getPortfolio().getAssessdAMT();
                query +=
                    (andCondition
                        + Facade.getStatement(
                            statement,
                            "Portfolio4.assessdAMT",
                            "Portfolio4assessdAMT" + j,
                            mapVariable,
                            whereQuery,
                            whereValue,
                            isExistRecord,
                            isDelete,
                            fetchType,
                            counter));
                whereQuery = "";
              }
              if (!isExistRecord
                  && !isDelete
                  && true
                  && model.getReception().getPortfolio().getBankAPPRInvestAMT() != null) {

                andCondition = " AND ";
                if (isOpenParentheses) {
                  andCondition = " ";
                }
                query += " ";
                if (isDelete && fetchType == FetchType.EAGER) {
                  whereQuery = " Portfolio4.bankAPPRInvestAMT = ? ";
                  counter++;
                } else {
                  whereQuery =
                      " Portfolio4.bankAPPRInvestAMT = :Portfolio4bankAPPRInvestAMT" + j + " ";
                }

                isOpenParentheses = false;
                whereValue = model.getReception().getPortfolio().getBankAPPRInvestAMT();
                query +=
                    (andCondition
                        + Facade.getStatement(
                            statement,
                            "Portfolio4.bankAPPRInvestAMT",
                            "Portfolio4bankAPPRInvestAMT" + j,
                            mapVariable,
                            whereQuery,
                            whereValue,
                            isExistRecord,
                            isDelete,
                            fetchType,
                            counter));
                whereQuery = "";
              }
              if (!isExistRecord
                  && !isDelete
                  && true
                  && model.getReception().getPortfolio().getReagent() != null) {

                andCondition = " AND ";
                if (isOpenParentheses) {
                  andCondition = " ";
                }
                query += " ";
                if (isDelete && fetchType == FetchType.EAGER) {
                  whereQuery = " Portfolio4.reagent = ? ";
                  counter++;
                } else {
                  whereQuery = " Portfolio4.reagent = :Portfolio4reagent" + j + " ";
                }

                isOpenParentheses = false;
                whereValue = model.getReception().getPortfolio().getReagent();
                query +=
                    (andCondition
                        + Facade.getStatement(
                            statement,
                            "Portfolio4.reagent",
                            "Portfolio4reagent" + j,
                            mapVariable,
                            whereQuery,
                            whereValue,
                            isExistRecord,
                            isDelete,
                            fetchType,
                            counter));
                whereQuery = "";
              }
              if (!isExistRecord
                  && !isDelete
                  && true
                  && model.getReception().getPortfolio().getBusinessId() != null) {

                andCondition = " AND ";
                if (isOpenParentheses) {
                  andCondition = " ";
                }
                query += " ";
                if (isDelete && fetchType == FetchType.EAGER) {
                  whereQuery = " Portfolio4.businessId = ? ";
                  counter++;
                } else {
                  whereQuery = " Portfolio4.businessId = :Portfolio4businessId" + j + " ";
                }

                isOpenParentheses = false;
                whereValue = model.getReception().getPortfolio().getBusinessId();
                query +=
                    (andCondition
                        + Facade.getStatement(
                            statement,
                            "Portfolio4.businessId",
                            "Portfolio4businessId" + j,
                            mapVariable,
                            whereQuery,
                            whereValue,
                            isExistRecord,
                            isDelete,
                            fetchType,
                            counter));
                whereQuery = "";
              }
              if (!isExistRecord
                  && !isDelete
                  && true
                  && model.getReception().getPortfolio().getVersion() != 0) {

                andCondition = " AND ";
                if (isOpenParentheses) {
                  andCondition = " ";
                }
                query += " ";
                if (isDelete && fetchType == FetchType.EAGER) {
                  whereQuery = " Portfolio4.VERSION = ? ";
                  counter++;
                } else {
                  whereQuery = " Portfolio4.version = :Portfolio4version" + j + " ";
                }

                isOpenParentheses = false;
                whereValue =
                    new java.lang.Integer("" + model.getReception().getPortfolio().getVersion());
                query +=
                    (andCondition
                        + Facade.getStatement(
                            statement,
                            "Portfolio4.version",
                            "Portfolio4version" + j,
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
            if (!isExistRecord && !isDelete && true && model.getReception().getChannel() != null) {

              andCondition = " AND ";
              if (isOpenParentheses) {
                andCondition = " ";
              }
              query += " ";
              if (isDelete && fetchType == FetchType.EAGER) {
                whereQuery = " Reception3.channel = ? ";
                counter++;
              } else {
                whereQuery = " Reception3.channel = :Reception3channel" + j + " ";
              }

              isOpenParentheses = false;
              whereValue = model.getReception().getChannel();
              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "Reception3.channel",
                          "Reception3channel" + j,
                          mapVariable,
                          whereQuery,
                          whereValue,
                          isExistRecord,
                          isDelete,
                          fetchType,
                          counter));
              whereQuery = "";
            }
            if (!isExistRecord
                && !isDelete
                && true
                && model.getReception().getExternalResource() != null) {

              andCondition = " AND ";
              if (isOpenParentheses) {
                andCondition = " ";
              }
              query += " ";
              if (isDelete && fetchType == FetchType.EAGER) {
                whereQuery = " Reception3.externalResource = ? ";
                counter++;
              } else {
                whereQuery = " Reception3.externalResource = :Reception3externalResource" + j + " ";
              }

              isOpenParentheses = false;
              whereValue = model.getReception().getExternalResource();
              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "Reception3.externalResource",
                          "Reception3externalResource" + j,
                          mapVariable,
                          whereQuery,
                          whereValue,
                          isExistRecord,
                          isDelete,
                          fetchType,
                          counter));
              whereQuery = "";
            }
            if (!isExistRecord
                && !isDelete
                && true
                && model.getReception().getReceptionTitle() != null) {

              andCondition = " AND ";
              if (isOpenParentheses) {
                andCondition = " ";
              }
              query += " ";
              whereQuery = "Reception3.receptionTitle LIKE :Reception3receptionTitle" + j + " ";

              isOpenParentheses = false;
              if (isExistRecord || isDelete) {
                if (isDelete && fetchType == FetchType.EAGER) {
                  whereQuery = " Reception3.receptionTitle = ? ";
                  counter++;
                } else {
                  whereValue = model.getReception().getReceptionTitle();
                }
              } else {
                whereValue = model.getReception().getReceptionTitle() + "%";
              }

              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "Reception3.receptionTitle",
                          "Reception3receptionTitle" + j,
                          mapVariable,
                          whereQuery,
                          whereValue,
                          isExistRecord,
                          isDelete,
                          fetchType,
                          counter));
              whereQuery = "";
            }
            if (!isExistRecord
                && !isDelete
                && true
                && model.getReception().getProductType() != null) {

              andCondition = " AND ";
              if (isOpenParentheses) {
                andCondition = " ";
              }
              query += " ";
              if (isDelete && fetchType == FetchType.EAGER) {
                whereQuery = " Reception3.productType = ? ";
                counter++;
              } else {
                whereQuery = " Reception3.productType = :Reception3productType" + j + " ";
              }

              isOpenParentheses = false;
              whereValue = model.getReception().getProductType();
              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "Reception3.productType",
                          "Reception3productType" + j,
                          mapVariable,
                          whereQuery,
                          whereValue,
                          isExistRecord,
                          isDelete,
                          fetchType,
                          counter));
              whereQuery = "";
            }
            if (!isExistRecord
                && !isDelete
                && true
                && model.getReception().getLongLoanType() != null) {

              andCondition = " AND ";
              if (isOpenParentheses) {
                andCondition = " ";
              }
              query += " ";
              if (isDelete && fetchType == FetchType.EAGER) {
                whereQuery = " Reception3.longLoanType = ? ";
                counter++;
              } else {
                whereQuery = " Reception3.longLoanType = :Reception3longLoanType" + j + " ";
              }

              isOpenParentheses = false;
              whereValue = model.getReception().getLongLoanType();
              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "Reception3.longLoanType",
                          "Reception3longLoanType" + j,
                          mapVariable,
                          whereQuery,
                          whereValue,
                          isExistRecord,
                          isDelete,
                          fetchType,
                          counter));
              whereQuery = "";
            }
            if (!isExistRecord
                && !isDelete
                && true
                && model.getReception().getFinancingPurpose() != null) {

              andCondition = " AND ";
              if (isOpenParentheses) {
                andCondition = " ";
              }
              query += " ";
              if (isDelete && fetchType == FetchType.EAGER) {
                whereQuery = " Reception3.financingPurpose = ? ";
                counter++;
              } else {
                whereQuery = " Reception3.financingPurpose = :Reception3financingPurpose" + j + " ";
              }

              isOpenParentheses = false;
              whereValue = model.getReception().getFinancingPurpose();
              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "Reception3.financingPurpose",
                          "Reception3financingPurpose" + j,
                          mapVariable,
                          whereQuery,
                          whereValue,
                          isExistRecord,
                          isDelete,
                          fetchType,
                          counter));
              whereQuery = "";
            }
            if (!isExistRecord && !isDelete && true && model.getReception().getLow141() != null) {

              andCondition = " AND ";
              if (isOpenParentheses) {
                andCondition = " ";
              }
              query += " ";
              if (isDelete && fetchType == FetchType.EAGER) {
                whereQuery = " Reception3.low141 = ? ";
                counter++;
              } else {
                whereQuery = " Reception3.low141 = :Reception3low141" + j + " ";
              }

              isOpenParentheses = false;
              whereValue = model.getReception().getLow141();
              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "Reception3.low141",
                          "Reception3low141" + j,
                          mapVariable,
                          whereQuery,
                          whereValue,
                          isExistRecord,
                          isDelete,
                          fetchType,
                          counter));
              whereQuery = "";
            }
            if (!isExistRecord
                && !isDelete
                && true
                && model.getReception().getShortTermLoanType() != null) {

              andCondition = " AND ";
              if (isOpenParentheses) {
                andCondition = " ";
              }
              query += " ";
              if (isDelete && fetchType == FetchType.EAGER) {
                whereQuery = " Reception3.shortTermLoanType = ? ";
                counter++;
              } else {
                whereQuery =
                    " Reception3.shortTermLoanType = :Reception3shortTermLoanType" + j + " ";
              }

              isOpenParentheses = false;
              whereValue = model.getReception().getShortTermLoanType();
              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "Reception3.shortTermLoanType",
                          "Reception3shortTermLoanType" + j,
                          mapVariable,
                          whereQuery,
                          whereValue,
                          isExistRecord,
                          isDelete,
                          fetchType,
                          counter));
              whereQuery = "";
            }
            if (!isExistRecord && !isDelete && true && model.getReception().getDate() != null) {

              andCondition = " AND ";
              if (isOpenParentheses) {
                andCondition = " ";
              }
              query += " ";
              if (isDelete && fetchType == FetchType.EAGER) {
                whereQuery = " Reception3.date1 = ? ";
                counter++;
              } else {
                whereQuery = " Reception3.date = :Reception3date" + j + " ";
              }

              isOpenParentheses = false;
              whereValue = model.getReception().getDate();
              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "Reception3.date",
                          "Reception3date" + j,
                          mapVariable,
                          whereQuery,
                          whereValue,
                          isExistRecord,
                          isDelete,
                          fetchType,
                          counter));
              whereQuery = "";
            }
            if (!isExistRecord
                && !isDelete
                && true
                && model.getReception().getRejLetterDate() != null) {

              andCondition = " AND ";
              if (isOpenParentheses) {
                andCondition = " ";
              }
              query += " ";
              if (isDelete && fetchType == FetchType.EAGER) {
                whereQuery = " Reception3.rejLetterDate = ? ";
                counter++;
              } else {
                whereQuery = " Reception3.rejLetterDate = :Reception3rejLetterDate" + j + " ";
              }

              isOpenParentheses = false;
              whereValue = model.getReception().getRejLetterDate();
              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "Reception3.rejLetterDate",
                          "Reception3rejLetterDate" + j,
                          mapVariable,
                          whereQuery,
                          whereValue,
                          isExistRecord,
                          isDelete,
                          fetchType,
                          counter));
              whereQuery = "";
            }
            if (!isExistRecord
                && !isDelete
                && true
                && model.getReception().getCustomerCode() != null) {

              andCondition = " AND ";
              if (isOpenParentheses) {
                andCondition = " ";
              }
              query += " ";
              whereQuery = "Reception3.customerCode LIKE :Reception3customerCode" + j + " ";

              isOpenParentheses = false;
              if (isExistRecord || isDelete) {
                if (isDelete && fetchType == FetchType.EAGER) {
                  whereQuery = " Reception3.customerCode = ? ";
                  counter++;
                } else {
                  whereValue = model.getReception().getCustomerCode();
                }
              } else {
                whereValue = "%" + model.getReception().getCustomerCode();
              }

              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "Reception3.customerCode",
                          "Reception3customerCode" + j,
                          mapVariable,
                          whereQuery,
                          whereValue,
                          isExistRecord,
                          isDelete,
                          fetchType,
                          counter));
              whereQuery = "";
            }
            if (!isExistRecord && !isDelete && true && model.getReception().getBranch() != null) {

              andCondition = " AND ";
              if (isOpenParentheses) {
                andCondition = " ";
              }
              query += " ";
              whereQuery = "Reception3.branch LIKE :Reception3branch" + j + " ";

              isOpenParentheses = false;
              if (isExistRecord || isDelete) {
                if (isDelete && fetchType == FetchType.EAGER) {
                  whereQuery = " Reception3.branch = ? ";
                  counter++;
                } else {
                  whereValue = model.getReception().getBranch();
                }
              } else {
                whereValue = "%" + model.getReception().getBranch() + "%";
              }

              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "Reception3.branch",
                          "Reception3branch" + j,
                          mapVariable,
                          whereQuery,
                          whereValue,
                          isExistRecord,
                          isDelete,
                          fetchType,
                          counter));
              whereQuery = "";
            }
            if (!isExistRecord
                && !isDelete
                && true
                && model.getReception().getReceptionState() != null) {

              andCondition = " AND ";
              if (isOpenParentheses) {
                andCondition = " ";
              }
              query += " ";
              if (isDelete && fetchType == FetchType.EAGER) {
                whereQuery = " Reception3.receptionState = ? ";
                counter++;
              } else {
                whereQuery = " Reception3.receptionState = :Reception3receptionState" + j + " ";
              }

              isOpenParentheses = false;
              whereValue = model.getReception().getReceptionState();
              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "Reception3.receptionState",
                          "Reception3receptionState" + j,
                          mapVariable,
                          whereQuery,
                          whereValue,
                          isExistRecord,
                          isDelete,
                          fetchType,
                          counter));
              whereQuery = "";
            }
            if (!isExistRecord
                && !isDelete
                && true
                && model.getReception().getRepresentativeRefType() != null) {

              andCondition = " AND ";
              if (isOpenParentheses) {
                andCondition = " ";
              }
              query += " ";
              if (isDelete && fetchType == FetchType.EAGER) {
                whereQuery = " Reception3.representativeRefType = ? ";
                counter++;
              } else {
                whereQuery =
                    " Reception3.representativeRefType = :Reception3representativeRefType"
                        + j
                        + " ";
              }

              isOpenParentheses = false;
              whereValue = model.getReception().getRepresentativeRefType();
              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "Reception3.representativeRefType",
                          "Reception3representativeRefType" + j,
                          mapVariable,
                          whereQuery,
                          whereValue,
                          isExistRecord,
                          isDelete,
                          fetchType,
                          counter));
              whereQuery = "";
            }
            if (!isExistRecord
                && !isDelete
                && true
                && model.getReception().getApplicantCreditType() != null) {

              andCondition = " AND ";
              if (isOpenParentheses) {
                andCondition = " ";
              }
              query += " ";
              if (isDelete && fetchType == FetchType.EAGER) {
                whereQuery = " Reception3.applicantCreditType = ? ";
                counter++;
              } else {
                whereQuery =
                    " Reception3.applicantCreditType = :Reception3applicantCreditType" + j + " ";
              }

              isOpenParentheses = false;
              whereValue = model.getReception().getApplicantCreditType();
              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "Reception3.applicantCreditType",
                          "Reception3applicantCreditType" + j,
                          mapVariable,
                          whereQuery,
                          whereValue,
                          isExistRecord,
                          isDelete,
                          fetchType,
                          counter));
              whereQuery = "";
            }
            if (!isExistRecord
                && !isDelete
                && true
                && model.getReception().getUnderTakeHisType() != null) {

              andCondition = " AND ";
              if (isOpenParentheses) {
                andCondition = " ";
              }
              query += " ";
              if (isDelete && fetchType == FetchType.EAGER) {
                whereQuery = " Reception3.underTakeHisType = ? ";
                counter++;
              } else {
                whereQuery = " Reception3.underTakeHisType = :Reception3underTakeHisType" + j + " ";
              }

              isOpenParentheses = false;
              whereValue = model.getReception().getUnderTakeHisType();
              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "Reception3.underTakeHisType",
                          "Reception3underTakeHisType" + j,
                          mapVariable,
                          whereQuery,
                          whereValue,
                          isExistRecord,
                          isDelete,
                          fetchType,
                          counter));
              whereQuery = "";
            }
            if (!isExistRecord
                && !isDelete
                && true
                && model.getReception().getCheckRejectType() != null) {

              andCondition = " AND ";
              if (isOpenParentheses) {
                andCondition = " ";
              }
              query += " ";
              if (isDelete && fetchType == FetchType.EAGER) {
                whereQuery = " Reception3.checkRejectType = ? ";
                counter++;
              } else {
                whereQuery = " Reception3.checkRejectType = :Reception3checkRejectType" + j + " ";
              }

              isOpenParentheses = false;
              whereValue = model.getReception().getCheckRejectType();
              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "Reception3.checkRejectType",
                          "Reception3checkRejectType" + j,
                          mapVariable,
                          whereQuery,
                          whereValue,
                          isExistRecord,
                          isDelete,
                          fetchType,
                          counter));
              whereQuery = "";
            }
            if (!isExistRecord
                && !isDelete
                && true
                && model.getReception().getCashFocusStatusType() != null) {

              andCondition = " AND ";
              if (isOpenParentheses) {
                andCondition = " ";
              }
              query += " ";
              if (isDelete && fetchType == FetchType.EAGER) {
                whereQuery = " Reception3.cashFocusStatusType = ? ";
                counter++;
              } else {
                whereQuery =
                    " Reception3.cashFocusStatusType = :Reception3cashFocusStatusType" + j + " ";
              }

              isOpenParentheses = false;
              whereValue = model.getReception().getCashFocusStatusType();
              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "Reception3.cashFocusStatusType",
                          "Reception3cashFocusStatusType" + j,
                          mapVariable,
                          whereQuery,
                          whereValue,
                          isExistRecord,
                          isDelete,
                          fetchType,
                          counter));
              whereQuery = "";
            }
            if (!isExistRecord && !isDelete && true && model.getReception().getEquation() != null) {

              andCondition = " AND ";
              if (isOpenParentheses) {
                andCondition = " ";
              }
              query += " ";
              if (isDelete && fetchType == FetchType.EAGER) {
                whereQuery = " Reception3.equation = ? ";
                counter++;
              } else {
                whereQuery = " Reception3.equation = :Reception3equation" + j + " ";
              }

              isOpenParentheses = false;
              whereValue = model.getReception().getEquation();
              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "Reception3.equation",
                          "Reception3equation" + j,
                          mapVariable,
                          whereQuery,
                          whereValue,
                          isExistRecord,
                          isDelete,
                          fetchType,
                          counter));
              whereQuery = "";
            }
            if (!isExistRecord
                && !isDelete
                && true
                && model.getReception().getIrrEstimate() != null) {

              andCondition = " AND ";
              if (isOpenParentheses) {
                andCondition = " ";
              }
              query += " ";
              if (isDelete && fetchType == FetchType.EAGER) {
                whereQuery = " Reception3.irrEstimate = ? ";
                counter++;
              } else {
                whereQuery = " Reception3.irrEstimate = :Reception3irrEstimate" + j + " ";
              }

              isOpenParentheses = false;
              whereValue = model.getReception().getIrrEstimate();
              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "Reception3.irrEstimate",
                          "Reception3irrEstimate" + j,
                          mapVariable,
                          whereQuery,
                          whereValue,
                          isExistRecord,
                          isDelete,
                          fetchType,
                          counter));
              whereQuery = "";
            }
            if (!isExistRecord
                && !isDelete
                && true
                && model.getReception().getHeadPoint() != null) {

              andCondition = " AND ";
              if (isOpenParentheses) {
                andCondition = " ";
              }
              query += " ";
              if (isDelete && fetchType == FetchType.EAGER) {
                whereQuery = " Reception3.headPoint = ? ";
                counter++;
              } else {
                whereQuery = " Reception3.headPoint = :Reception3headPoint" + j + " ";
              }

              isOpenParentheses = false;
              whereValue = model.getReception().getHeadPoint();
              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "Reception3.headPoint",
                          "Reception3headPoint" + j,
                          mapVariable,
                          whereQuery,
                          whereValue,
                          isExistRecord,
                          isDelete,
                          fetchType,
                          counter));
              whereQuery = "";
            }
            if (!isExistRecord
                && !isDelete
                && true
                && model.getReception().getCashControl() != null) {

              andCondition = " AND ";
              if (isOpenParentheses) {
                andCondition = " ";
              }
              query += " ";
              if (isDelete && fetchType == FetchType.EAGER) {
                whereQuery = " Reception3.cashControl = ? ";
                counter++;
              } else {
                whereQuery = " Reception3.cashControl = :Reception3cashControl" + j + " ";
              }

              isOpenParentheses = false;
              whereValue = model.getReception().getCashControl();
              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "Reception3.cashControl",
                          "Reception3cashControl" + j,
                          mapVariable,
                          whereQuery,
                          whereValue,
                          isExistRecord,
                          isDelete,
                          fetchType,
                          counter));
              whereQuery = "";
            }
            if (!isExistRecord
                && !isDelete
                && true
                && model.getReception().getExportMarketDsc() != null) {

              andCondition = " AND ";
              if (isOpenParentheses) {
                andCondition = " ";
              }
              query += " ";
              if (isDelete && fetchType == FetchType.EAGER) {
                whereQuery = " Reception3.exportMarketDsc = ? ";
                counter++;
              } else {
                whereQuery = " Reception3.exportMarketDsc = :Reception3exportMarketDsc" + j + " ";
              }

              isOpenParentheses = false;
              whereValue = model.getReception().getExportMarketDsc();
              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "Reception3.exportMarketDsc",
                          "Reception3exportMarketDsc" + j,
                          mapVariable,
                          whereQuery,
                          whereValue,
                          isExistRecord,
                          isDelete,
                          fetchType,
                          counter));
              whereQuery = "";
            }
            if (!isExistRecord
                && !isDelete
                && true
                && model.getReception().getInternalMarketDsc() != null) {

              andCondition = " AND ";
              if (isOpenParentheses) {
                andCondition = " ";
              }
              query += " ";
              if (isDelete && fetchType == FetchType.EAGER) {
                whereQuery = " Reception3.internalMarketDsc = ? ";
                counter++;
              } else {
                whereQuery =
                    " Reception3.internalMarketDsc = :Reception3internalMarketDsc" + j + " ";
              }

              isOpenParentheses = false;
              whereValue = model.getReception().getInternalMarketDsc();
              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "Reception3.internalMarketDsc",
                          "Reception3internalMarketDsc" + j,
                          mapVariable,
                          whereQuery,
                          whereValue,
                          isExistRecord,
                          isDelete,
                          fetchType,
                          counter));
              whereQuery = "";
            }
            if (!isExistRecord
                && !isDelete
                && true
                && model.getReception().getRowMaterialDsc() != null) {

              andCondition = " AND ";
              if (isOpenParentheses) {
                andCondition = " ";
              }
              query += " ";
              if (isDelete && fetchType == FetchType.EAGER) {
                whereQuery = " Reception3.rowMaterialDsc = ? ";
                counter++;
              } else {
                whereQuery = " Reception3.rowMaterialDsc = :Reception3rowMaterialDsc" + j + " ";
              }

              isOpenParentheses = false;
              whereValue = model.getReception().getRowMaterialDsc();
              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "Reception3.rowMaterialDsc",
                          "Reception3rowMaterialDsc" + j,
                          mapVariable,
                          whereQuery,
                          whereValue,
                          isExistRecord,
                          isDelete,
                          fetchType,
                          counter));
              whereQuery = "";
            }
            if (!isExistRecord
                && !isDelete
                && true
                && model.getReception().getMarketProductDesc() != null) {

              andCondition = " AND ";
              if (isOpenParentheses) {
                andCondition = " ";
              }
              query += " ";
              if (isDelete && fetchType == FetchType.EAGER) {
                whereQuery = " Reception3.marketProductDesc = ? ";
                counter++;
              } else {
                whereQuery =
                    " Reception3.marketProductDesc = :Reception3marketProductDesc" + j + " ";
              }

              isOpenParentheses = false;
              whereValue = model.getReception().getMarketProductDesc();
              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "Reception3.marketProductDesc",
                          "Reception3marketProductDesc" + j,
                          mapVariable,
                          whereQuery,
                          whereValue,
                          isExistRecord,
                          isDelete,
                          fetchType,
                          counter));
              whereQuery = "";
            }
            if (!isExistRecord
                && !isDelete
                && true
                && model.getReception().getProductiveEmployee() != null) {

              andCondition = " AND ";
              if (isOpenParentheses) {
                andCondition = " ";
              }
              query += " ";
              if (isDelete && fetchType == FetchType.EAGER) {
                whereQuery = " Reception3.productiveEmployee = ? ";
                counter++;
              } else {
                whereQuery =
                    " Reception3.productiveEmployee = :Reception3productiveEmployee" + j + " ";
              }

              isOpenParentheses = false;
              whereValue = model.getReception().getProductiveEmployee();
              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "Reception3.productiveEmployee",
                          "Reception3productiveEmployee" + j,
                          mapVariable,
                          whereQuery,
                          whereValue,
                          isExistRecord,
                          isDelete,
                          fetchType,
                          counter));
              whereQuery = "";
            }
            if (!isExistRecord
                && !isDelete
                && true
                && model.getReception().getNonProductiveEmployee() != null) {

              andCondition = " AND ";
              if (isOpenParentheses) {
                andCondition = " ";
              }
              query += " ";
              if (isDelete && fetchType == FetchType.EAGER) {
                whereQuery = " Reception3.nonProductiveEmployee = ? ";
                counter++;
              } else {
                whereQuery =
                    " Reception3.nonProductiveEmployee = :Reception3nonProductiveEmployee"
                        + j
                        + " ";
              }

              isOpenParentheses = false;
              whereValue = model.getReception().getNonProductiveEmployee();
              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "Reception3.nonProductiveEmployee",
                          "Reception3nonProductiveEmployee" + j,
                          mapVariable,
                          whereQuery,
                          whereValue,
                          isExistRecord,
                          isDelete,
                          fetchType,
                          counter));
              whereQuery = "";
            }
            if (!isExistRecord
                && !isDelete
                && true
                && model.getReception().getForecastProductiveEmployee() != null) {

              andCondition = " AND ";
              if (isOpenParentheses) {
                andCondition = " ";
              }
              query += " ";
              if (isDelete && fetchType == FetchType.EAGER) {
                whereQuery = " Reception3.forecastProductiveEmployee = ? ";
                counter++;
              } else {
                whereQuery =
                    " Reception3.forecastProductiveEmployee = :Reception3forecastProductiveEmployee"
                        + j
                        + " ";
              }

              isOpenParentheses = false;
              whereValue = model.getReception().getForecastProductiveEmployee();
              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "Reception3.forecastProductiveEmployee",
                          "Reception3forecastProductiveEmployee" + j,
                          mapVariable,
                          whereQuery,
                          whereValue,
                          isExistRecord,
                          isDelete,
                          fetchType,
                          counter));
              whereQuery = "";
            }
            if (!isExistRecord
                && !isDelete
                && true
                && model.getReception().getForecastNonProductiveEmployee() != null) {

              andCondition = " AND ";
              if (isOpenParentheses) {
                andCondition = " ";
              }
              query += " ";
              if (isDelete && fetchType == FetchType.EAGER) {
                whereQuery = " Reception3.forecastNonProductiveEmployee = ? ";
                counter++;
              } else {
                whereQuery =
                    " Reception3.forecastNonProductiveEmployee = :Reception3forecastNonProductiveEmployee"
                        + j
                        + " ";
              }

              isOpenParentheses = false;
              whereValue = model.getReception().getForecastNonProductiveEmployee();
              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "Reception3.forecastNonProductiveEmployee",
                          "Reception3forecastNonProductiveEmployee" + j,
                          mapVariable,
                          whereQuery,
                          whereValue,
                          isExistRecord,
                          isDelete,
                          fetchType,
                          counter));
              whereQuery = "";
            }
            if (!isExistRecord
                && !isDelete
                && true
                && model.getReception().getCusPrePayAmnt() != null) {

              andCondition = " AND ";
              if (isOpenParentheses) {
                andCondition = " ";
              }
              query += " ";
              if (isDelete && fetchType == FetchType.EAGER) {
                whereQuery = " Reception3.cusPrePayAmnt = ? ";
                counter++;
              } else {
                whereQuery = " Reception3.cusPrePayAmnt = :Reception3cusPrePayAmnt" + j + " ";
              }

              isOpenParentheses = false;
              whereValue = model.getReception().getCusPrePayAmnt();
              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "Reception3.cusPrePayAmnt",
                          "Reception3cusPrePayAmnt" + j,
                          mapVariable,
                          whereQuery,
                          whereValue,
                          isExistRecord,
                          isDelete,
                          fetchType,
                          counter));
              whereQuery = "";
            }
            if (!isExistRecord
                && !isDelete
                && true
                && model.getReception().getFinancialUser() != null) {

              andCondition = " AND ";
              if (isOpenParentheses) {
                andCondition = " ";
              }
              query += " ";
              if (isDelete && fetchType == FetchType.EAGER) {
                whereQuery = " Reception3.financialUser = ? ";
                counter++;
              } else {
                whereQuery = " Reception3.financialUser = :Reception3financialUser" + j + " ";
              }

              isOpenParentheses = false;
              whereValue = model.getReception().getFinancialUser();
              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "Reception3.financialUser",
                          "Reception3financialUser" + j,
                          mapVariable,
                          whereQuery,
                          whereValue,
                          isExistRecord,
                          isDelete,
                          fetchType,
                          counter));
              whereQuery = "";
            }
            if (!isExistRecord
                && !isDelete
                && true
                && model.getReception().getMarketUser() != null) {

              andCondition = " AND ";
              if (isOpenParentheses) {
                andCondition = " ";
              }
              query += " ";
              if (isDelete && fetchType == FetchType.EAGER) {
                whereQuery = " Reception3.marketUser = ? ";
                counter++;
              } else {
                whereQuery = " Reception3.marketUser = :Reception3marketUser" + j + " ";
              }

              isOpenParentheses = false;
              whereValue = model.getReception().getMarketUser();
              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "Reception3.marketUser",
                          "Reception3marketUser" + j,
                          mapVariable,
                          whereQuery,
                          whereValue,
                          isExistRecord,
                          isDelete,
                          fetchType,
                          counter));
              whereQuery = "";
            }
            if (!isExistRecord
                && !isDelete
                && true
                && model.getReception().getTechnicalUser() != null) {

              andCondition = " AND ";
              if (isOpenParentheses) {
                andCondition = " ";
              }
              query += " ";
              if (isDelete && fetchType == FetchType.EAGER) {
                whereQuery = " Reception3.technicalUser = ? ";
                counter++;
              } else {
                whereQuery = " Reception3.technicalUser = :Reception3technicalUser" + j + " ";
              }

              isOpenParentheses = false;
              whereValue = model.getReception().getTechnicalUser();
              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "Reception3.technicalUser",
                          "Reception3technicalUser" + j,
                          mapVariable,
                          whereQuery,
                          whereValue,
                          isExistRecord,
                          isDelete,
                          fetchType,
                          counter));
              whereQuery = "";
            }
            if (!isExistRecord
                && !isDelete
                && true
                && model.getReception().getCurrentAssetReceptionNo() != null) {

              andCondition = " AND ";
              if (isOpenParentheses) {
                andCondition = " ";
              }
              query += " ";
              if (isDelete && fetchType == FetchType.EAGER) {
                whereQuery = " Reception3.currentAssetReceptionNo = ? ";
                counter++;
              } else {
                whereQuery =
                    " Reception3.currentAssetReceptionNo = :Reception3currentAssetReceptionNo"
                        + j
                        + " ";
              }

              isOpenParentheses = false;
              whereValue = model.getReception().getCurrentAssetReceptionNo();
              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "Reception3.currentAssetReceptionNo",
                          "Reception3currentAssetReceptionNo" + j,
                          mapVariable,
                          whereQuery,
                          whereValue,
                          isExistRecord,
                          isDelete,
                          fetchType,
                          counter));
              whereQuery = "";
            }
            if (!isExistRecord && !isDelete && true && model.getReception().getVersion() != 0) {

              andCondition = " AND ";
              if (isOpenParentheses) {
                andCondition = " ";
              }
              query += " ";
              if (isDelete && fetchType == FetchType.EAGER) {
                whereQuery = " Reception3.VERSION = ? ";
                counter++;
              } else {
                whereQuery = " Reception3.version = :Reception3version" + j + " ";
              }

              isOpenParentheses = false;
              whereValue = new java.lang.Integer("" + model.getReception().getVersion());
              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "Reception3.version",
                          "Reception3version" + j,
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
          && (!select.trim().equals("Evaluation0") && select.indexOf("new") == -1)) {
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
