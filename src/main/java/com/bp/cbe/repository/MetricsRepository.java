package com.bp.cbe.repository;

import com.bp.cbe.domain.entities.MetricsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author: Bladimir Minga <bsminga@pichincha.com>
 * @version: 24/06/2022
 */
public interface MetricsRepository extends JpaRepository<MetricsEntity, Long> {
  Optional<MetricsEntity> findByIdAndStatus(Long id, Integer status);

  List<MetricsEntity> findAllByStatus(Integer status);
}
