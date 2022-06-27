package com.bp.cbe.repository.impl;

import com.bp.cbe.domain.dto.RepositoryMetricsDto;
import com.bp.cbe.domain.entities.MetricsEntity;
import com.bp.cbe.domain.entities.OrganizationEntity;
import com.bp.cbe.domain.entities.RepositoryEntity;
import com.bp.cbe.domain.entities.TribeEntity;
import com.bp.cbe.repository.ReportRepository;
import lombok.AllArgsConstructor;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

/** {@inheritDoc} */
@Repository
@AllArgsConstructor
public class ReportRepositoryImpl implements ReportRepository {
  private final EntityManager entityManager;

  /** {@inheritDoc} */
  @Override
  public List<RepositoryMetricsDto> getReport(long tribeId) {

    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

    CriteriaQuery<RepositoryMetricsDto> query =
        criteriaBuilder.createQuery(RepositoryMetricsDto.class);

    Root<RepositoryEntity> root = query.from(RepositoryEntity.class);

    Join<RepositoryEntity, TribeEntity> tribe = root.join("tribe", JoinType.INNER);

    Join<RepositoryEntity, MetricsEntity> metrics = root.join("metrics", JoinType.INNER);

    Join<TribeEntity, OrganizationEntity> organization = tribe.join("organization", JoinType.INNER);

    query.multiselect(
        root.get("id").alias("id"),
        root.get("name").alias("name"),
        root.get("state").alias("state"),
        tribe.get("name").alias("tribe"),
        organization.get("name").alias("organization"),
        metrics.get("coverage").alias("coverage"),
        metrics.get("codeSmells").alias("codeSmells"),
        metrics.get("bugs").alias("bugs"),
        metrics.get("vulnerabilities").alias("vulnerabilities"),
        metrics.get("hotspot").alias("hotspot"));

    query.where(
        criteriaBuilder.and(
            criteriaBuilder.equal(root.get("state"), "E"),
            criteriaBuilder.equal(tribe.get("id"), tribeId),
            criteriaBuilder.gt(metrics.get("coverage"), 0.75),
            criteriaBuilder.between(root.get("createTime"), getStartDate(), getEndDate())));

    query.orderBy(criteriaBuilder.desc(root.get("createTime")));

    TypedQuery<RepositoryMetricsDto> typedQuery = entityManager.createQuery(query);

    return typedQuery.getResultList();
  }

  /** This is the old way in which criteria was used. BUT THIS FORM IS DEPRECATED */
  public List getReportByCriteriaDeprecated(long tribeId) {
    Session session = entityManager.unwrap(Session.class);

    Criteria criteria = session.createCriteria(RepositoryEntity.class, "REP");
    criteria.createAlias("REP.tribe", "TRI");
    criteria.createAlias("REP.tribe.organization", "ORG");
    criteria.createAlias("REP.metrics", "MET");
    criteria.add(Restrictions.eq("TRI.id", tribeId));
    criteria.add(Restrictions.eq("REP.state", "E"));
    criteria.add(Restrictions.gt("MET.coverage", 0.75));
    criteria.add(
        Restrictions.and(
            Restrictions.and(
                Restrictions.ge("REP.createTime", getStartDate()),
                Restrictions.le("REP.createTime", getEndDate()))));
    criteria.addOrder(Order.desc("REP.createTime"));

    ProjectionList projections = Projections.projectionList();
    projections.add(Projections.property("REP.id"), "id");
    projections.add(Projections.property("REP.name"), "name");
    projections.add(Projections.property("REP.state"), "state");
    projections.add(Projections.property("TRI.name"), "tribe");
    projections.add(Projections.property("ORG.name"), "organization");
    projections.add(Projections.property("MET.coverage"), "coverage");
    projections.add(Projections.property("MET.codeSmells"), "codeSmells");
    projections.add(Projections.property("MET.bugs"), "bugs");
    projections.add(Projections.property("MET.vulnerabilities"), "vulnerabilities");
    projections.add(Projections.property("MET.hotspot"), "hotspot");

    criteria.setProjection(projections);
    criteria.setResultTransformer(Transformers.aliasToBean(RepositoryMetricsDto.class));

    return criteria.list();
  }

  /** Query using a manual way of generating the query. THIS FORM IS NOT RECOMMENDED */
  public List getReportSqlBuilder(long tribeId) {
    StringBuilder sql = new StringBuilder();
    sql.append(
            "SELECT REP.ID_REPOSITORY, REP.NAME, TRI.NAME AS TRIBE, ORG.NAME AS ORGANIZATION, MET.COVERAGE, ")
        .append("MET.CODE_SMELLS, MET.BUGS, MET.VULNERABILITIES, MET.HOTSPOT, REP.STATE ")
        .append("FROM REPOSITORY REP ")
        .append("INNER JOIN TRIBE TRI ON TRI.ID_TRIBE = REP.ID_TRIBE ")
        .append("INNER JOIN ORGANIZATION ORG ON ORG.ID_ORGANIZATION = TRI.ID_ORGANIZATION ")
        .append("INNER JOIN METRICS MET ON MET.ID_REPOSITORY = REP.ID_REPOSITORY  ")
        .append("WHERE ")
        .append("TRI.ID_TRIBE = :tribeId AND ")
        .append("REP.STATE = 'E' AND ")
        .append("MET.COVERAGE > 0.75 AND ")
        .append("REP.CREATE_TIME BETWEEN :startDate AND :endDate ")
        .append("ORDER BY CREATE_TIME ");

    Query query = entityManager.createNativeQuery(sql.toString());
    query.setParameter("tribeId", tribeId);
    query.setParameter("startDate", getStartDate());
    query.setParameter("endDate", getEndDate());

    return query.getResultList();
  }

  private LocalDateTime getStartDate() {
    return LocalDateTime.of(LocalDate.now().getYear(), Month.JANUARY, 1, 0, 0, 0);
  }

  private LocalDateTime getEndDate() {
    return LocalDateTime.of(LocalDate.now().getYear(), Month.DECEMBER, 31, 23, 59, 59);
  }
}
