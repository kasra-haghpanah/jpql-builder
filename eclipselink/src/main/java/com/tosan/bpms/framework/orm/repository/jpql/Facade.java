package com.tosan.bpms.framework.orm.repository.jpql;

import com.jpql.api.interfaces.*;
import com.jpql.api.interfaces.Parameter;
import javax.persistence.FetchType;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Iterator;
import java.util.Set;
/**
 * Created by kasra.haghpanah on 1/05/2019.
 */
public class Facade {

  public static Long getTime(java.util.Date date) {
    if (date == null) {
      return null;
    }
    return date.getTime();
  }

  public static java.util.List save(EntityManager entityManager, java.util.List models) {
    if (models == null || models.size() == 0) {
      return null;
    }
    java.util.Iterator iterator = models.iterator();
    Object node = iterator.next();
    if (false) {
    } else if (node instanceof com.tosan.bpms.model.sql.Evaluation) {
      return EvaluationcomtosanbpmsmodelsqlDAO.save(entityManager, models);
    } else if (node instanceof com.tosan.bpms.model.sql.MaterialOfProject) {
      return MaterialOfProjectcomtosanbpmsmodelsqlDAO.save(entityManager, models);
    } else if (node instanceof com.tosan.bpms.model.sql.Portfolio) {
      return PortfoliocomtosanbpmsmodelsqlDAO.save(entityManager, models);
    } else if (node instanceof com.tosan.bpms.model.sql.Project) {
      return ProjectcomtosanbpmsmodelsqlDAO.save(entityManager, models);
    } else if (node instanceof com.tosan.bpms.model.sql.Employee) {
      return EmployeecomtosanbpmsmodelsqlDAO.save(entityManager, models);
    } else if (node instanceof com.tosan.bpms.model.sql.OngoingPrjct) {
      return OngoingPrjctcomtosanbpmsmodelsqlDAO.save(entityManager, models);
    } else if (node instanceof com.tosan.bpms.model.sql.LocationDetails) {
      return LocationDetailscomtosanbpmsmodelsqlDAO.save(entityManager, models);
    } else if (node instanceof com.tosan.bpms.model.sql.LicenseDetails) {
      return LicenseDetailscomtosanbpmsmodelsqlDAO.save(entityManager, models);
    } else if (node instanceof com.tosan.bpms.model.sql.Reception) {
      return ReceptioncomtosanbpmsmodelsqlDAO.save(entityManager, models);
    } else if (node instanceof com.tosan.bpms.model.sql.User) {
      return UsercomtosanbpmsmodelsqlDAO.save(entityManager, models);
    } else if (node instanceof com.tosan.bpms.model.sql.Group) {
      return GroupcomtosanbpmsmodelsqlDAO.save(entityManager, models);
    } else if (node instanceof com.tosan.bpms.model.sql.Article) {
      return ArticlecomtosanbpmsmodelsqlDAO.save(entityManager, models);
    } else if (node instanceof com.tosan.bpms.model.sql.Economic) {
      return EconomiccomtosanbpmsmodelsqlDAO.save(entityManager, models);
    } else if (node instanceof com.tosan.bpms.model.sql.File) {
      return FilecomtosanbpmsmodelsqlDAO.save(entityManager, models);
    } else if (node instanceof com.tosan.bpms.model.sql.Industry) {
      return IndustrycomtosanbpmsmodelsqlDAO.save(entityManager, models);
    } else if (node instanceof com.tosan.bpms.model.sql.MaterialOfProject) {
      return MaterialOfProjectcomtosanbpmsmodelsqlDAO.save(entityManager, models);
    } else if (node instanceof com.tosan.bpms.model.sql.Measurment) {
      return MeasurmentcomtosanbpmsmodelsqlDAO.save(entityManager, models);
    } else if (node instanceof com.tosan.bpms.model.sql.PortfolioLicense) {
      return PortfolioLicensecomtosanbpmsmodelsqlDAO.save(entityManager, models);
    } else if (node instanceof com.tosan.bpms.model.sql.PortfolioLocation) {
      return PortfolioLocationcomtosanbpmsmodelsqlDAO.save(entityManager, models);
    } else if (node instanceof com.tosan.bpms.model.sql.ProductsOfProject) {
      return ProductsOfProjectcomtosanbpmsmodelsqlDAO.save(entityManager, models);
    } else if (node instanceof com.tosan.bpms.model.sql.Log) {
      return LogcomtosanbpmsmodelsqlDAO.save(entityManager, models);
    } else if (node instanceof com.tosan.bpms.model.sql.LogBox) {
      return LogBoxcomtosanbpmsmodelsqlDAO.save(entityManager, models);
    }
    return null;
  }

  public static java.util.List getByFilter(
      EntityManager entityManager,
      java.util.List models,
      boolean isFetch,
      int page,
      int size,
      String selectParam,
      Parameter jpql,
      QueryParameter queryCondition,
      Cast cast,
      boolean isExistRecord,
      String orAnd,
      FetchType fetchType,
      final java.util.Map statement,
      boolean isDelete,
      String furthermore) {

    if (models == null || models.size() == 0) {
      return null;
    }
    java.util.Iterator iterator = models.iterator();
    Object node = iterator.next();
    if (false) {
    } else if (node instanceof com.tosan.bpms.model.sql.Evaluation) {
      return EvaluationcomtosanbpmsmodelsqlDAO.getByFilter(
          entityManager,
          models,
          isFetch,
          page,
          size,
          selectParam,
          jpql,
          queryCondition,
          cast,
          isExistRecord,
          orAnd,
          fetchType,
          statement,
          isDelete,
          furthermore);
    } else if (node instanceof com.tosan.bpms.model.sql.MaterialOfProject) {
      return MaterialOfProjectcomtosanbpmsmodelsqlDAO.getByFilter(
          entityManager,
          models,
          isFetch,
          page,
          size,
          selectParam,
          jpql,
          queryCondition,
          cast,
          isExistRecord,
          orAnd,
          fetchType,
          statement,
          isDelete,
          furthermore);
    } else if (node instanceof com.tosan.bpms.model.sql.Portfolio) {
      return PortfoliocomtosanbpmsmodelsqlDAO.getByFilter(
          entityManager,
          models,
          isFetch,
          page,
          size,
          selectParam,
          jpql,
          queryCondition,
          cast,
          isExistRecord,
          orAnd,
          fetchType,
          statement,
          isDelete,
          furthermore);
    } else if (node instanceof com.tosan.bpms.model.sql.Project) {
      return ProjectcomtosanbpmsmodelsqlDAO.getByFilter(
          entityManager,
          models,
          isFetch,
          page,
          size,
          selectParam,
          jpql,
          queryCondition,
          cast,
          isExistRecord,
          orAnd,
          fetchType,
          statement,
          isDelete,
          furthermore);
    } else if (node instanceof com.tosan.bpms.model.sql.Employee) {
      return EmployeecomtosanbpmsmodelsqlDAO.getByFilter(
          entityManager,
          models,
          isFetch,
          page,
          size,
          selectParam,
          jpql,
          queryCondition,
          cast,
          isExistRecord,
          orAnd,
          fetchType,
          statement,
          isDelete,
          furthermore);
    } else if (node instanceof com.tosan.bpms.model.sql.OngoingPrjct) {
      return OngoingPrjctcomtosanbpmsmodelsqlDAO.getByFilter(
          entityManager,
          models,
          isFetch,
          page,
          size,
          selectParam,
          jpql,
          queryCondition,
          cast,
          isExistRecord,
          orAnd,
          fetchType,
          statement,
          isDelete,
          furthermore);
    } else if (node instanceof com.tosan.bpms.model.sql.LocationDetails) {
      return LocationDetailscomtosanbpmsmodelsqlDAO.getByFilter(
          entityManager,
          models,
          isFetch,
          page,
          size,
          selectParam,
          jpql,
          queryCondition,
          cast,
          isExistRecord,
          orAnd,
          fetchType,
          statement,
          isDelete,
          furthermore);
    } else if (node instanceof com.tosan.bpms.model.sql.LicenseDetails) {
      return LicenseDetailscomtosanbpmsmodelsqlDAO.getByFilter(
          entityManager,
          models,
          isFetch,
          page,
          size,
          selectParam,
          jpql,
          queryCondition,
          cast,
          isExistRecord,
          orAnd,
          fetchType,
          statement,
          isDelete,
          furthermore);
    } else if (node instanceof com.tosan.bpms.model.sql.Reception) {
      return ReceptioncomtosanbpmsmodelsqlDAO.getByFilter(
          entityManager,
          models,
          isFetch,
          page,
          size,
          selectParam,
          jpql,
          queryCondition,
          cast,
          isExistRecord,
          orAnd,
          fetchType,
          statement,
          isDelete,
          furthermore);
    } else if (node instanceof com.tosan.bpms.model.sql.User) {
      return UsercomtosanbpmsmodelsqlDAO.getByFilter(
          entityManager,
          models,
          isFetch,
          page,
          size,
          selectParam,
          jpql,
          queryCondition,
          cast,
          isExistRecord,
          orAnd,
          fetchType,
          statement,
          isDelete,
          furthermore);
    } else if (node instanceof com.tosan.bpms.model.sql.Group) {
      return GroupcomtosanbpmsmodelsqlDAO.getByFilter(
          entityManager,
          models,
          isFetch,
          page,
          size,
          selectParam,
          jpql,
          queryCondition,
          cast,
          isExistRecord,
          orAnd,
          fetchType,
          statement,
          isDelete,
          furthermore);
    } else if (node instanceof com.tosan.bpms.model.sql.Article) {
      return ArticlecomtosanbpmsmodelsqlDAO.getByFilter(
          entityManager,
          models,
          isFetch,
          page,
          size,
          selectParam,
          jpql,
          queryCondition,
          cast,
          isExistRecord,
          orAnd,
          fetchType,
          statement,
          isDelete,
          furthermore);
    } else if (node instanceof com.tosan.bpms.model.sql.Economic) {
      return EconomiccomtosanbpmsmodelsqlDAO.getByFilter(
          entityManager,
          models,
          isFetch,
          page,
          size,
          selectParam,
          jpql,
          queryCondition,
          cast,
          isExistRecord,
          orAnd,
          fetchType,
          statement,
          isDelete,
          furthermore);
    } else if (node instanceof com.tosan.bpms.model.sql.File) {
      return FilecomtosanbpmsmodelsqlDAO.getByFilter(
          entityManager,
          models,
          isFetch,
          page,
          size,
          selectParam,
          jpql,
          queryCondition,
          cast,
          isExistRecord,
          orAnd,
          fetchType,
          statement,
          isDelete,
          furthermore);
    } else if (node instanceof com.tosan.bpms.model.sql.Industry) {
      return IndustrycomtosanbpmsmodelsqlDAO.getByFilter(
          entityManager,
          models,
          isFetch,
          page,
          size,
          selectParam,
          jpql,
          queryCondition,
          cast,
          isExistRecord,
          orAnd,
          fetchType,
          statement,
          isDelete,
          furthermore);
    } else if (node instanceof com.tosan.bpms.model.sql.MaterialOfProject) {
      return MaterialOfProjectcomtosanbpmsmodelsqlDAO.getByFilter(
          entityManager,
          models,
          isFetch,
          page,
          size,
          selectParam,
          jpql,
          queryCondition,
          cast,
          isExistRecord,
          orAnd,
          fetchType,
          statement,
          isDelete,
          furthermore);
    } else if (node instanceof com.tosan.bpms.model.sql.Measurment) {
      return MeasurmentcomtosanbpmsmodelsqlDAO.getByFilter(
          entityManager,
          models,
          isFetch,
          page,
          size,
          selectParam,
          jpql,
          queryCondition,
          cast,
          isExistRecord,
          orAnd,
          fetchType,
          statement,
          isDelete,
          furthermore);
    } else if (node instanceof com.tosan.bpms.model.sql.PortfolioLicense) {
      return PortfolioLicensecomtosanbpmsmodelsqlDAO.getByFilter(
          entityManager,
          models,
          isFetch,
          page,
          size,
          selectParam,
          jpql,
          queryCondition,
          cast,
          isExistRecord,
          orAnd,
          fetchType,
          statement,
          isDelete,
          furthermore);
    } else if (node instanceof com.tosan.bpms.model.sql.PortfolioLocation) {
      return PortfolioLocationcomtosanbpmsmodelsqlDAO.getByFilter(
          entityManager,
          models,
          isFetch,
          page,
          size,
          selectParam,
          jpql,
          queryCondition,
          cast,
          isExistRecord,
          orAnd,
          fetchType,
          statement,
          isDelete,
          furthermore);
    } else if (node instanceof com.tosan.bpms.model.sql.ProductsOfProject) {
      return ProductsOfProjectcomtosanbpmsmodelsqlDAO.getByFilter(
          entityManager,
          models,
          isFetch,
          page,
          size,
          selectParam,
          jpql,
          queryCondition,
          cast,
          isExistRecord,
          orAnd,
          fetchType,
          statement,
          isDelete,
          furthermore);
    } else if (node instanceof com.tosan.bpms.model.sql.Log) {
      return LogcomtosanbpmsmodelsqlDAO.getByFilter(
          entityManager,
          models,
          isFetch,
          page,
          size,
          selectParam,
          jpql,
          queryCondition,
          cast,
          isExistRecord,
          orAnd,
          fetchType,
          statement,
          isDelete,
          furthermore);
    } else if (node instanceof com.tosan.bpms.model.sql.LogBox) {
      return LogBoxcomtosanbpmsmodelsqlDAO.getByFilter(
          entityManager,
          models,
          isFetch,
          page,
          size,
          selectParam,
          jpql,
          queryCondition,
          cast,
          isExistRecord,
          orAnd,
          fetchType,
          statement,
          isDelete,
          furthermore);
    }
    return null;
  }

  public static String getStatement(
      final java.util.Map<String, String> map,
      String key,
      String variable,
      final java.util.List mapVariable,
      String whereQuery,
      Object whereValue,
      boolean isExistRecord,
      boolean isDelete,
      FetchType fetchType,
      int counter) {

    if (isDelete && fetchType == FetchType.EAGER) {
      mapVariable.add(counter + "");
      mapVariable.add(whereValue);
      return whereQuery;
    }
    if (isExistRecord && !isDelete) {
      mapVariable.add(variable);
      mapVariable.add(whereValue);
      return whereQuery;
    }

    if (map == null || map.keySet().size() == 0) {
      mapVariable.add(variable);
      mapVariable.add(whereValue);
      return whereQuery;
    }

    String query = map.get(key);
    if (query == null) {
      mapVariable.add(variable);
      mapVariable.add(whereValue);
      return whereQuery;
    }

    if (query.indexOf("?") > -1) {
      query = query.replaceAll("\\?", " :" + variable);
      mapVariable.add(variable);
      mapVariable.add(whereValue);
    }

    return query;
  }
}
