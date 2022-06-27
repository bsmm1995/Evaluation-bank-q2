package com.bp.cbe.service;

import com.bp.cbe.domain.dto.RepositoryDto;
import com.bp.cbe.domain.dto.RepositoryWithMetricsDto;

import java.util.List;

/**
 * @author: Bladimir Minga <bsminga@pichincha.com>
 * @version: 24/06/2022
 */
public interface RepositoriesService {

  /**
   * Obtain a record by ID
   *
   * @param id ID of the record to be searched
   * @return Record found
   */
  RepositoryDto getById(long id);

  /**
   * Get all records
   *
   * @return List of all records found
   */
  List<RepositoryDto> getAll();

  List<RepositoryWithMetricsDto> getAllWithMetrics();

  /**
   * Create a new record
   *
   * @param data Object containing the new record information
   * @return Record created
   */
  RepositoryDto create(RepositoryDto data);

  /**
   * Update a record by its ID
   *
   * @param id ID of the record to be updated
   * @param data Object that contains the registration information to be updated.
   * @return Record updated
   */
  RepositoryDto update(long id, RepositoryDto data);

  /**
   * Delete a record by its ID
   *
   * @param id ID of the record to be deleted
   * @return Record ID deleted
   */
  long deleteById(long id);
}
