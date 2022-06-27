package com.bp.cbe.repository;

import com.bp.cbe.domain.entities.RepositoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author: Bladimir Minga <bsminga@pichincha.com>
 * @version: 24/06/2022
 */
public interface RepositoriesRepository extends JpaRepository<RepositoryEntity, Long> {
  Optional<RepositoryEntity> findByIdAndStatus(Long id, Integer status);

  List<RepositoryEntity> findAllByStatus(Integer status);
}
