package com.bp.cbe.repository;

import com.bp.cbe.domain.entities.OrganizationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author: Bladimir Minga <bsminga@pichincha.com>
 * @version: 24/06/2022
 */
public interface OrganizationRepository extends JpaRepository<OrganizationEntity, Long> {
  Optional<OrganizationEntity> findByIdAndStatus(Long id, Integer status);

  List<OrganizationEntity> findAllByStatus(Integer status);
}
