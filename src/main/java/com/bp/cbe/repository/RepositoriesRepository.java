package com.bp.cbe.repository;

import com.bp.cbe.domain.entities.RepositoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RepositoriesRepository extends JpaRepository<RepositoryEntity, Long> {
  Optional<RepositoryEntity> findByIdAndStatus(Long id, Integer status);

  List<RepositoryEntity> findAllByStatus(Integer status);
}
