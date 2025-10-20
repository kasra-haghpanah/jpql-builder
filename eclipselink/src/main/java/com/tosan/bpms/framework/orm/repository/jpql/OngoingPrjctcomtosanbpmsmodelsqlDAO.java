package com.tosan.bpms.framework.orm.repository.jpql;

import com.jpql.api.interfaces.*;
import com.jpql.api.interfaces.Parameter;
import javax.persistence.FetchType;
import javax.persistence.EntityManager;
/**
 * Created by kasra.haghpanah on 1/05/2019.
 */
public class OngoingPrjctcomtosanbpmsmodelsqlDAO {

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

        com.tosan.bpms.model.sql.OngoingPrjct model =
            (com.tosan.bpms.model.sql.OngoingPrjct) fetchIterator.next();

        if (model != null) {

          String key =
              model.getOngoingPrjctId().getReceptionNo()
                  + "-"
                  + Facade.getTime(model.getOngoingPrjctId().getCntrlPrjctDate())
                  + "-"
                  + Facade.getTime(model.getOngoingPrjctId().getLastPaymentDate())
                  + "-"
                  + model.getOngoingPrjctId().getSubInvestSerial()
                  + "";
          existMap.put(key, model);
        }
      }
    }

    java.util.Iterator iterator = models.iterator();

    while (iterator.hasNext()) {
      com.tosan.bpms.model.sql.OngoingPrjct model =
          (com.tosan.bpms.model.sql.OngoingPrjct) iterator.next();

      if (model != null) {
        String key =
            model.getOngoingPrjctId().getReceptionNo()
                + "-"
                + Facade.getTime(model.getOngoingPrjctId().getCntrlPrjctDate())
                + "-"
                + Facade.getTime(model.getOngoingPrjctId().getLastPaymentDate())
                + "-"
                + model.getOngoingPrjctId().getSubInvestSerial()
                + "";
        com.tosan.bpms.model.sql.OngoingPrjct fetchModel =
            (com.tosan.bpms.model.sql.OngoingPrjct) existMap.get(key);

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
    com.tosan.bpms.model.sql.OngoingPrjct model = null;
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
    java.lang.String select = "SELECT OngoingPrjct0";
    if (selectParam != null && !selectParam.equals("")) {
      select = selectParam;
    }
    if (!isExistRecord && !isDelete) {
      if (fetchType == FetchType.LAZY) {
        basicQuery =
            select + " FROM com.tosan.bpms.model.sql.OngoingPrjct OngoingPrjct0  WHERE 1=1 ";
      } else if (fetchType == FetchType.EAGER) {
        basicQuery =
            select + " FROM com.tosan.bpms.model.sql.OngoingPrjct OngoingPrjct0  WHERE 1=1 ";
      }
    } else if (isExistRecord) {
      basicQuery =
          "SELECT OngoingPrjct0 FROM com.tosan.bpms.model.sql.OngoingPrjct OngoingPrjct0 WHERE 1=1 ";
    } else if (isDelete) {
      if (fetchType == FetchType.LAZY) {
        basicQuery = "DELETE FROM com.tosan.bpms.model.sql.OngoingPrjct OngoingPrjct0 WHERE 1=1 ";
      } else if (fetchType == FetchType.EAGER) {
        basicQuery = "DELETE OngoingPrjct OngoingPrjct0 WHERE 1=1 ";
      }
    }
    int modelsLength = models.size();
    java.util.Iterator modelsIterator = models.iterator();
    int j = 0;
    if (modelsLength > 0) {
      while (modelsIterator.hasNext()) {
        model = (com.tosan.bpms.model.sql.OngoingPrjct) modelsIterator.next();
        query = "";
        isOpenParentheses = true;

        if (model != null) {

          if (model.getOngoingPrjctId() != null) {
            if (isExistRecord == true && model.getOngoingPrjctId().getReceptionNo() == null) {
              model.getOngoingPrjctId().setReceptionNo("");
              isChangeKey = true;
            }

            if (model.getOngoingPrjctId().getReceptionNo() != null) {

              andCondition = " AND ";
              if (isOpenParentheses) {
                andCondition = " ";
              }
              query += " ";
              if (isExistRecord || isDelete) {
                if (isDelete && fetchType == FetchType.EAGER) {
                  whereQuery = " OngoingPrjct0.receptionNo = ? ";
                  counter++;
                } else {
                  whereQuery =
                      " OngoingPrjct0.ongoingPrjctId.receptionNo = :OngoingPrjct0ongoingPrjctIdreceptionNo"
                          + j
                          + " ";
                }
              } else {

                whereQuery =
                    " OngoingPrjct0.ongoingPrjctId.receptionNo LIKE :OngoingPrjct0ongoingPrjctIdreceptionNo"
                        + j
                        + " ";
              }

              isOpenParentheses = false;
              if (isExistRecord || isDelete) {
                if (isDelete && fetchType == FetchType.EAGER) {
                  whereQuery = " OngoingPrjct0.receptionNo = ? ";
                  counter++;
                } else {
                  whereValue = model.getOngoingPrjctId().getReceptionNo();
                }
              } else {
                whereValue = "%" + model.getOngoingPrjctId().getReceptionNo() + "%";
              }
              if (isExistRecord && isChangeKey) {
                model.getOngoingPrjctId().setReceptionNo(null);
                isChangeKey = false;
              }

              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "OngoingPrjct0.ongoingPrjctId.receptionNo",
                          "OngoingPrjct0ongoingPrjctIdreceptionNo" + j,
                          mapVariable,
                          whereQuery,
                          whereValue,
                          isExistRecord,
                          isDelete,
                          fetchType,
                          counter));
              whereQuery = "";
            }
            if (model.getOngoingPrjctId().getCntrlPrjctDate() != null) {

              andCondition = " AND ";
              if (isOpenParentheses) {
                andCondition = " ";
              }
              query += " ";
              if (isDelete && fetchType == FetchType.EAGER) {
                whereQuery = " OngoingPrjct0.cntrlPrjctDate = ? ";
                counter++;
              } else {
                whereQuery =
                    " OngoingPrjct0.ongoingPrjctId.cntrlPrjctDate = :OngoingPrjct0ongoingPrjctIdcntrlPrjctDate"
                        + j
                        + " ";
              }

              isOpenParentheses = false;
              whereValue = model.getOngoingPrjctId().getCntrlPrjctDate();
              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "OngoingPrjct0.ongoingPrjctId.cntrlPrjctDate",
                          "OngoingPrjct0ongoingPrjctIdcntrlPrjctDate" + j,
                          mapVariable,
                          whereQuery,
                          whereValue,
                          isExistRecord,
                          isDelete,
                          fetchType,
                          counter));
              whereQuery = "";
            }
            if (model.getOngoingPrjctId().getLastPaymentDate() != null) {

              andCondition = " AND ";
              if (isOpenParentheses) {
                andCondition = " ";
              }
              query += " ";
              if (isDelete && fetchType == FetchType.EAGER) {
                whereQuery = " OngoingPrjct0.lastPaymentDate = ? ";
                counter++;
              } else {
                whereQuery =
                    " OngoingPrjct0.ongoingPrjctId.lastPaymentDate = :OngoingPrjct0ongoingPrjctIdlastPaymentDate"
                        + j
                        + " ";
              }

              isOpenParentheses = false;
              whereValue = model.getOngoingPrjctId().getLastPaymentDate();
              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "OngoingPrjct0.ongoingPrjctId.lastPaymentDate",
                          "OngoingPrjct0ongoingPrjctIdlastPaymentDate" + j,
                          mapVariable,
                          whereQuery,
                          whereValue,
                          isExistRecord,
                          isDelete,
                          fetchType,
                          counter));
              whereQuery = "";
            }
            if (model.getOngoingPrjctId().getSubInvestSerial() != null) {

              andCondition = " AND ";
              if (isOpenParentheses) {
                andCondition = " ";
              }
              query += " ";
              if (isDelete && fetchType == FetchType.EAGER) {
                whereQuery = " OngoingPrjct0.investId = ? ";
                counter++;
              } else {
                whereQuery =
                    " OngoingPrjct0.ongoingPrjctId.subInvestSerial = :OngoingPrjct0ongoingPrjctIdsubInvestSerial"
                        + j
                        + " ";
              }

              isOpenParentheses = false;
              whereValue = model.getOngoingPrjctId().getSubInvestSerial();
              if (isExistRecord && isChangeKey) {
                model.getOngoingPrjctId().setSubInvestSerial(null);
                isChangeKey = false;
              }

              query +=
                  (andCondition
                      + Facade.getStatement(
                          statement,
                          "OngoingPrjct0.ongoingPrjctId.subInvestSerial",
                          "OngoingPrjct0ongoingPrjctIdsubInvestSerial" + j,
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
          if (!isExistRecord && true && model.getInvestType() != null) {

            andCondition = " AND ";
            if (isOpenParentheses) {
              andCondition = " ";
            }
            query += " ";
            if (isDelete && fetchType == FetchType.EAGER) {
              whereQuery = " OngoingPrjct0.investType = ? ";
              counter++;
            } else {
              whereQuery = " OngoingPrjct0.investType = :OngoingPrjct0investType" + j + " ";
            }

            isOpenParentheses = false;
            whereValue = model.getInvestType();
            query +=
                (andCondition
                    + Facade.getStatement(
                        statement,
                        "OngoingPrjct0.investType",
                        "OngoingPrjct0investType" + j,
                        mapVariable,
                        whereQuery,
                        whereValue,
                        isExistRecord,
                        isDelete,
                        fetchType,
                        counter));
            whereQuery = "";
          }
          if (!isExistRecord && true && model.getCurrency() != null) {

            andCondition = " AND ";
            if (isOpenParentheses) {
              andCondition = " ";
            }
            query += " ";
            if (isDelete && fetchType == FetchType.EAGER) {
              whereQuery = " OngoingPrjct0.currency = ? ";
              counter++;
            } else {
              whereQuery = " OngoingPrjct0.currency = :OngoingPrjct0currency" + j + " ";
            }

            isOpenParentheses = false;
            whereValue = model.getCurrency();
            query +=
                (andCondition
                    + Facade.getStatement(
                        statement,
                        "OngoingPrjct0.currency",
                        "OngoingPrjct0currency" + j,
                        mapVariable,
                        whereQuery,
                        whereValue,
                        isExistRecord,
                        isDelete,
                        fetchType,
                        counter));
            whereQuery = "";
          }
          if (!isExistRecord && true && model.getPriceDone() != null) {

            andCondition = " AND ";
            if (isOpenParentheses) {
              andCondition = " ";
            }
            query += " ";
            if (isDelete && fetchType == FetchType.EAGER) {
              whereQuery = " OngoingPrjct0.priceDone = ? ";
              counter++;
            } else {
              whereQuery = " OngoingPrjct0.priceDone = :OngoingPrjct0priceDone" + j + " ";
            }

            isOpenParentheses = false;
            whereValue = model.getPriceDone();
            query +=
                (andCondition
                    + Facade.getStatement(
                        statement,
                        "OngoingPrjct0.priceDone",
                        "OngoingPrjct0priceDone" + j,
                        mapVariable,
                        whereQuery,
                        whereValue,
                        isExistRecord,
                        isDelete,
                        fetchType,
                        counter));
            whereQuery = "";
          }
          if (!isExistRecord && true && model.getCtd() != null) {

            andCondition = " AND ";
            if (isOpenParentheses) {
              andCondition = " ";
            }
            query += " ";
            if (isDelete && fetchType == FetchType.EAGER) {
              whereQuery = " OngoingPrjct0.ctd = ? ";
              counter++;
            } else {
              whereQuery = " OngoingPrjct0.ctd = :OngoingPrjct0ctd" + j + " ";
            }

            isOpenParentheses = false;
            whereValue = model.getCtd();
            query +=
                (andCondition
                    + Facade.getStatement(
                        statement,
                        "OngoingPrjct0.ctd",
                        "OngoingPrjct0ctd" + j,
                        mapVariable,
                        whereQuery,
                        whereValue,
                        isExistRecord,
                        isDelete,
                        fetchType,
                        counter));
            whereQuery = "";
          }
          if (!isExistRecord && true && model.getPriceNeeded() != null) {

            andCondition = " AND ";
            if (isOpenParentheses) {
              andCondition = " ";
            }
            query += " ";
            if (isDelete && fetchType == FetchType.EAGER) {
              whereQuery = " OngoingPrjct0.priceNeeded = ? ";
              counter++;
            } else {
              whereQuery = " OngoingPrjct0.priceNeeded = :OngoingPrjct0priceNeeded" + j + " ";
            }

            isOpenParentheses = false;
            whereValue = model.getPriceNeeded();
            query +=
                (andCondition
                    + Facade.getStatement(
                        statement,
                        "OngoingPrjct0.priceNeeded",
                        "OngoingPrjct0priceNeeded" + j,
                        mapVariable,
                        whereQuery,
                        whereValue,
                        isExistRecord,
                        isDelete,
                        fetchType,
                        counter));
            whereQuery = "";
          }
          if (!isExistRecord && true && model.getCtc() != null) {

            andCondition = " AND ";
            if (isOpenParentheses) {
              andCondition = " ";
            }
            query += " ";
            if (isDelete && fetchType == FetchType.EAGER) {
              whereQuery = " OngoingPrjct0.ctc = ? ";
              counter++;
            } else {
              whereQuery = " OngoingPrjct0.ctc = :OngoingPrjct0ctc" + j + " ";
            }

            isOpenParentheses = false;
            whereValue = model.getCtc();
            query +=
                (andCondition
                    + Facade.getStatement(
                        statement,
                        "OngoingPrjct0.ctc",
                        "OngoingPrjct0ctc" + j,
                        mapVariable,
                        whereQuery,
                        whereValue,
                        isExistRecord,
                        isDelete,
                        fetchType,
                        counter));
            whereQuery = "";
          }
          if (!isExistRecord && true && model.getExists() != null) {

            andCondition = " AND ";
            if (isOpenParentheses) {
              andCondition = " ";
            }
            query += " ";
            if (isDelete && fetchType == FetchType.EAGER) {
              whereQuery = " OngoingPrjct0.exists1 = ? ";
              counter++;
            } else {
              whereQuery = " OngoingPrjct0.exists = :OngoingPrjct0exists" + j + " ";
            }

            isOpenParentheses = false;
            whereValue = model.getExists();
            query +=
                (andCondition
                    + Facade.getStatement(
                        statement,
                        "OngoingPrjct0.exists",
                        "OngoingPrjct0exists" + j,
                        mapVariable,
                        whereQuery,
                        whereValue,
                        isExistRecord,
                        isDelete,
                        fetchType,
                        counter));
            whereQuery = "";
          }
          if (!isExistRecord && true && model.getCac() != null) {

            andCondition = " AND ";
            if (isOpenParentheses) {
              andCondition = " ";
            }
            query += " ";
            if (isDelete && fetchType == FetchType.EAGER) {
              whereQuery = " OngoingPrjct0.cac = ? ";
              counter++;
            } else {
              whereQuery = " OngoingPrjct0.cac = :OngoingPrjct0cac" + j + " ";
            }

            isOpenParentheses = false;
            whereValue = model.getCac();
            query +=
                (andCondition
                    + Facade.getStatement(
                        statement,
                        "OngoingPrjct0.cac",
                        "OngoingPrjct0cac" + j,
                        mapVariable,
                        whereQuery,
                        whereValue,
                        isExistRecord,
                        isDelete,
                        fetchType,
                        counter));
            whereQuery = "";
          }
          if (!isExistRecord && true && model.getTotalBC() != null) {

            andCondition = " AND ";
            if (isOpenParentheses) {
              andCondition = " ";
            }
            query += " ";
            if (isDelete && fetchType == FetchType.EAGER) {
              whereQuery = " OngoingPrjct0.totalBC = ? ";
              counter++;
            } else {
              whereQuery = " OngoingPrjct0.totalBC = :OngoingPrjct0totalBC" + j + " ";
            }

            isOpenParentheses = false;
            whereValue = model.getTotalBC();
            query +=
                (andCondition
                    + Facade.getStatement(
                        statement,
                        "OngoingPrjct0.totalBC",
                        "OngoingPrjct0totalBC" + j,
                        mapVariable,
                        whereQuery,
                        whereValue,
                        isExistRecord,
                        isDelete,
                        fetchType,
                        counter));
            whereQuery = "";
          }
          if (!isExistRecord && true && model.getCostVariation() != null) {

            andCondition = " AND ";
            if (isOpenParentheses) {
              andCondition = " ";
            }
            query += " ";
            if (isDelete && fetchType == FetchType.EAGER) {
              whereQuery = " OngoingPrjct0.costVariation = ? ";
              counter++;
            } else {
              whereQuery = " OngoingPrjct0.costVariation = :OngoingPrjct0costVariation" + j + " ";
            }

            isOpenParentheses = false;
            whereValue = model.getCostVariation();
            query +=
                (andCondition
                    + Facade.getStatement(
                        statement,
                        "OngoingPrjct0.costVariation",
                        "OngoingPrjct0costVariation" + j,
                        mapVariable,
                        whereQuery,
                        whereValue,
                        isExistRecord,
                        isDelete,
                        fetchType,
                        counter));
            whereQuery = "";
          }
          if (!isExistRecord && true && model.getBgnDate() != null) {

            andCondition = " AND ";
            if (isOpenParentheses) {
              andCondition = " ";
            }
            query += " ";
            if (isDelete && fetchType == FetchType.EAGER) {
              whereQuery = " OngoingPrjct0.bgnDate = ? ";
              counter++;
            } else {
              whereQuery = " OngoingPrjct0.bgnDate = :OngoingPrjct0bgnDate" + j + " ";
            }

            isOpenParentheses = false;
            whereValue = model.getBgnDate();
            query +=
                (andCondition
                    + Facade.getStatement(
                        statement,
                        "OngoingPrjct0.bgnDate",
                        "OngoingPrjct0bgnDate" + j,
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
              whereQuery = " OngoingPrjct0.endDate = ? ";
              counter++;
            } else {
              whereQuery = " OngoingPrjct0.endDate = :OngoingPrjct0endDate" + j + " ";
            }

            isOpenParentheses = false;
            whereValue = model.getEndDate();
            query +=
                (andCondition
                    + Facade.getStatement(
                        statement,
                        "OngoingPrjct0.endDate",
                        "OngoingPrjct0endDate" + j,
                        mapVariable,
                        whereQuery,
                        whereValue,
                        isExistRecord,
                        isDelete,
                        fetchType,
                        counter));
            whereQuery = "";
          }
          if (!isExistRecord && true && model.getRlStrtDate() != null) {

            andCondition = " AND ";
            if (isOpenParentheses) {
              andCondition = " ";
            }
            query += " ";
            if (isDelete && fetchType == FetchType.EAGER) {
              whereQuery = " OngoingPrjct0.rlStrtDate = ? ";
              counter++;
            } else {
              whereQuery = " OngoingPrjct0.rlStrtDate = :OngoingPrjct0rlStrtDate" + j + " ";
            }

            isOpenParentheses = false;
            whereValue = model.getRlStrtDate();
            query +=
                (andCondition
                    + Facade.getStatement(
                        statement,
                        "OngoingPrjct0.rlStrtDate",
                        "OngoingPrjct0rlStrtDate" + j,
                        mapVariable,
                        whereQuery,
                        whereValue,
                        isExistRecord,
                        isDelete,
                        fetchType,
                        counter));
            whereQuery = "";
          }
          if (!isExistRecord && true && model.getRlEndDate() != null) {

            andCondition = " AND ";
            if (isOpenParentheses) {
              andCondition = " ";
            }
            query += " ";
            if (isDelete && fetchType == FetchType.EAGER) {
              whereQuery = " OngoingPrjct0.rlEndDate = ? ";
              counter++;
            } else {
              whereQuery = " OngoingPrjct0.rlEndDate = :OngoingPrjct0rlEndDate" + j + " ";
            }

            isOpenParentheses = false;
            whereValue = model.getRlEndDate();
            query +=
                (andCondition
                    + Facade.getStatement(
                        statement,
                        "OngoingPrjct0.rlEndDate",
                        "OngoingPrjct0rlEndDate" + j,
                        mapVariable,
                        whereQuery,
                        whereValue,
                        isExistRecord,
                        isDelete,
                        fetchType,
                        counter));
            whereQuery = "";
          }
          if (!isExistRecord && true && model.getVariation() != null) {

            andCondition = " AND ";
            if (isOpenParentheses) {
              andCondition = " ";
            }
            query += " ";
            if (isDelete && fetchType == FetchType.EAGER) {
              whereQuery = " OngoingPrjct0.variation = ? ";
              counter++;
            } else {
              whereQuery = " OngoingPrjct0.variation = :OngoingPrjct0variation" + j + " ";
            }

            isOpenParentheses = false;
            whereValue = model.getVariation();
            query +=
                (andCondition
                    + Facade.getStatement(
                        statement,
                        "OngoingPrjct0.variation",
                        "OngoingPrjct0variation" + j,
                        mapVariable,
                        whereQuery,
                        whereValue,
                        isExistRecord,
                        isDelete,
                        fetchType,
                        counter));
            whereQuery = "";
          }
          if (!isExistRecord && true && model.getFcstPredict() != null) {

            andCondition = " AND ";
            if (isOpenParentheses) {
              andCondition = " ";
            }
            query += " ";
            if (isDelete && fetchType == FetchType.EAGER) {
              whereQuery = " OngoingPrjct0.fcstPredict = ? ";
              counter++;
            } else {
              whereQuery = " OngoingPrjct0.fcstPredict = :OngoingPrjct0fcstPredict" + j + " ";
            }

            isOpenParentheses = false;
            whereValue = model.getFcstPredict();
            query +=
                (andCondition
                    + Facade.getStatement(
                        statement,
                        "OngoingPrjct0.fcstPredict",
                        "OngoingPrjct0fcstPredict" + j,
                        mapVariable,
                        whereQuery,
                        whereValue,
                        isExistRecord,
                        isDelete,
                        fetchType,
                        counter));
            whereQuery = "";
          }
          if (!isExistRecord && true && model.getOngoingDate() != null) {

            andCondition = " AND ";
            if (isOpenParentheses) {
              andCondition = " ";
            }
            query += " ";
            if (isDelete && fetchType == FetchType.EAGER) {
              whereQuery = " OngoingPrjct0.ongoingDate = ? ";
              counter++;
            } else {
              whereQuery = " OngoingPrjct0.ongoingDate = :OngoingPrjct0ongoingDate" + j + " ";
            }

            isOpenParentheses = false;
            whereValue = model.getOngoingDate();
            query +=
                (andCondition
                    + Facade.getStatement(
                        statement,
                        "OngoingPrjct0.ongoingDate",
                        "OngoingPrjct0ongoingDate" + j,
                        mapVariable,
                        whereQuery,
                        whereValue,
                        isExistRecord,
                        isDelete,
                        fetchType,
                        counter));
            whereQuery = "";
          }
          if (!isExistRecord && true && model.getOngoingCst() != null) {

            andCondition = " AND ";
            if (isOpenParentheses) {
              andCondition = " ";
            }
            query += " ";
            if (isDelete && fetchType == FetchType.EAGER) {
              whereQuery = " OngoingPrjct0.ongoingCst = ? ";
              counter++;
            } else {
              whereQuery = " OngoingPrjct0.ongoingCst = :OngoingPrjct0ongoingCst" + j + " ";
            }

            isOpenParentheses = false;
            whereValue = model.getOngoingCst();
            query +=
                (andCondition
                    + Facade.getStatement(
                        statement,
                        "OngoingPrjct0.ongoingCst",
                        "OngoingPrjct0ongoingCst" + j,
                        mapVariable,
                        whereQuery,
                        whereValue,
                        isExistRecord,
                        isDelete,
                        fetchType,
                        counter));
            whereQuery = "";
          }
          if (!isExistRecord && true && model.getOngoingPrjct() != null) {

            andCondition = " AND ";
            if (isOpenParentheses) {
              andCondition = " ";
            }
            query += " ";
            if (isDelete && fetchType == FetchType.EAGER) {
              whereQuery = " OngoingPrjct0.ongoingPrjct = ? ";
              counter++;
            } else {
              whereQuery = " OngoingPrjct0.ongoingPrjct = :OngoingPrjct0ongoingPrjct" + j + " ";
            }

            isOpenParentheses = false;
            whereValue = model.getOngoingPrjct();
            query +=
                (andCondition
                    + Facade.getStatement(
                        statement,
                        "OngoingPrjct0.ongoingPrjct",
                        "OngoingPrjct0ongoingPrjct" + j,
                        mapVariable,
                        whereQuery,
                        whereValue,
                        isExistRecord,
                        isDelete,
                        fetchType,
                        counter));
            whereQuery = "";
          }
          if (!isExistRecord && true && model.getPaymntPredict() != null) {

            andCondition = " AND ";
            if (isOpenParentheses) {
              andCondition = " ";
            }
            query += " ";
            if (isDelete && fetchType == FetchType.EAGER) {
              whereQuery = " OngoingPrjct0.paymntPredict = ? ";
              counter++;
            } else {
              whereQuery = " OngoingPrjct0.paymntPredict = :OngoingPrjct0paymntPredict" + j + " ";
            }

            isOpenParentheses = false;
            whereValue = model.getPaymntPredict();
            query +=
                (andCondition
                    + Facade.getStatement(
                        statement,
                        "OngoingPrjct0.paymntPredict",
                        "OngoingPrjct0paymntPredict" + j,
                        mapVariable,
                        whereQuery,
                        whereValue,
                        isExistRecord,
                        isDelete,
                        fetchType,
                        counter));
            whereQuery = "";
          }
          if (!isExistRecord && true && model.getPaymentAmount() != null) {

            andCondition = " AND ";
            if (isOpenParentheses) {
              andCondition = " ";
            }
            query += " ";
            if (isDelete && fetchType == FetchType.EAGER) {
              whereQuery = " OngoingPrjct0.paymentAmount = ? ";
              counter++;
            } else {
              whereQuery = " OngoingPrjct0.paymentAmount = :OngoingPrjct0paymentAmount" + j + " ";
            }

            isOpenParentheses = false;
            whereValue = model.getPaymentAmount();
            query +=
                (andCondition
                    + Facade.getStatement(
                        statement,
                        "OngoingPrjct0.paymentAmount",
                        "OngoingPrjct0paymentAmount" + j,
                        mapVariable,
                        whereQuery,
                        whereValue,
                        isExistRecord,
                        isDelete,
                        fetchType,
                        counter));
            whereQuery = "";
          }
          if (!isExistRecord && true && model.getDoneInPeriod() != null) {

            andCondition = " AND ";
            if (isOpenParentheses) {
              andCondition = " ";
            }
            query += " ";
            if (isDelete && fetchType == FetchType.EAGER) {
              whereQuery = " OngoingPrjct0.doneInPeriod = ? ";
              counter++;
            } else {
              whereQuery = " OngoingPrjct0.doneInPeriod = :OngoingPrjct0doneInPeriod" + j + " ";
            }

            isOpenParentheses = false;
            whereValue = model.getDoneInPeriod();
            query +=
                (andCondition
                    + Facade.getStatement(
                        statement,
                        "OngoingPrjct0.doneInPeriod",
                        "OngoingPrjct0doneInPeriod" + j,
                        mapVariable,
                        whereQuery,
                        whereValue,
                        isExistRecord,
                        isDelete,
                        fetchType,
                        counter));
            whereQuery = "";
          }
          if (!isExistRecord && true && model.getSubInvestType() != null) {

            andCondition = " AND ";
            if (isOpenParentheses) {
              andCondition = " ";
            }
            query += " ";
            if (isDelete && fetchType == FetchType.EAGER) {
              whereQuery = " OngoingPrjct0.subInvestType = ? ";
              counter++;
            } else {
              whereQuery = " OngoingPrjct0.subInvestType = :OngoingPrjct0subInvestType" + j + " ";
            }

            isOpenParentheses = false;
            whereValue = model.getSubInvestType();
            query +=
                (andCondition
                    + Facade.getStatement(
                        statement,
                        "OngoingPrjct0.subInvestType",
                        "OngoingPrjct0subInvestType" + j,
                        mapVariable,
                        whereQuery,
                        whereValue,
                        isExistRecord,
                        isDelete,
                        fetchType,
                        counter));
            whereQuery = "";
          }
          if (!isExistRecord && true && model.getRemaindAmount() != null) {

            andCondition = " AND ";
            if (isOpenParentheses) {
              andCondition = " ";
            }
            query += " ";
            if (isDelete && fetchType == FetchType.EAGER) {
              whereQuery = " OngoingPrjct0.remaindAmount = ? ";
              counter++;
            } else {
              whereQuery = " OngoingPrjct0.remaindAmount = :OngoingPrjct0remaindAmount" + j + " ";
            }

            isOpenParentheses = false;
            whereValue = model.getRemaindAmount();
            query +=
                (andCondition
                    + Facade.getStatement(
                        statement,
                        "OngoingPrjct0.remaindAmount",
                        "OngoingPrjct0remaindAmount" + j,
                        mapVariable,
                        whereQuery,
                        whereValue,
                        isExistRecord,
                        isDelete,
                        fetchType,
                        counter));
            whereQuery = "";
          }
          if (!isExistRecord && true && model.getDesc() != null) {

            andCondition = " AND ";
            if (isOpenParentheses) {
              andCondition = " ";
            }
            query += " ";
            if (isDelete && fetchType == FetchType.EAGER) {
              whereQuery = " OngoingPrjct0.desc1 = ? ";
              counter++;
            } else {
              whereQuery = " OngoingPrjct0.desc = :OngoingPrjct0desc" + j + " ";
            }

            isOpenParentheses = false;
            whereValue = model.getDesc();
            query +=
                (andCondition
                    + Facade.getStatement(
                        statement,
                        "OngoingPrjct0.desc",
                        "OngoingPrjct0desc" + j,
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
              whereQuery = " OngoingPrjct0.VERSION = ? ";
              counter++;
            } else {
              whereQuery = " OngoingPrjct0.version = :OngoingPrjct0version" + j + " ";
            }

            isOpenParentheses = false;
            whereValue = new java.lang.Integer("" + model.getVersion());
            query +=
                (andCondition
                    + Facade.getStatement(
                        statement,
                        "OngoingPrjct0.version",
                        "OngoingPrjct0version" + j,
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
          && (!select.trim().equals("OngoingPrjct0") && select.indexOf("new") == -1)) {
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
