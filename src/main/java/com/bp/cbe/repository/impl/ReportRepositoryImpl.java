package com.bp.cbe.repository.impl;

import com.bp.cbe.domain.dto.RepositoryMetricsDto;
import com.bp.cbe.domain.entities.MetricsEntity;
import com.bp.cbe.domain.entities.OrganizationEntity;
import com.bp.cbe.domain.entities.RepositoryEntity;
import com.bp.cbe.domain.entities.TribeEntity;
import com.bp.cbe.repository.ReportRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Repository
@AllArgsConstructor
public class ReportRepositoryImpl implements ReportRepository {
    private final EntityManager entityManager;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<RepositoryMetricsDto> getReport(long tribeId) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<RepositoryMetricsDto> query = criteriaBuilder.createQuery(RepositoryMetricsDto.class);

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

    private LocalDateTime getStartDate() {
        return LocalDateTime.of(LocalDate.now().getYear(), Month.JANUARY, 1, 0, 0, 0);
    }

    private LocalDateTime getEndDate() {
        return LocalDateTime.of(LocalDate.now().getYear(), Month.DECEMBER, 31, 23, 59, 59);
    }
}
