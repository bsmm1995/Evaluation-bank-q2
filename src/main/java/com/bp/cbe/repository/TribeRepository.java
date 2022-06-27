package com.bp.cbe.repository;

import com.bp.cbe.domain.entities.TribeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author: Bladimir Minga <bsminga@pichincha.com>
 * @version: 24/06/2022
 */
public interface TribeRepository extends JpaRepository<TribeEntity, Long> {
  Optional<TribeEntity> findByIdAndStatus(Long id, Integer status);

  List<TribeEntity> findAllByStatus(Integer status);
}
