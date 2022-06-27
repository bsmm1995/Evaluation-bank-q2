package com.bp.cbe.service;

import com.bp.cbe.domain.dto.TribeDto;

import java.util.List;

/**
 * @author: Bladimir Minga <bsminga@pichincha.com>
 * @version: 24/06/2022
 */
public interface TribeService {

  /**
   * Obtain a record by ID
   *
   * @param id ID of the record to be searched
   * @return Record found
   */
  TribeDto getById(long id);

  /**
   * Get all records
   *
   * @return List of all records found
   */
  List<TribeDto> getAll();

  /**
   * Create a new record
   *
   * @param data Object containing the new record information
   * @return Record created
   */
  TribeDto create(TribeDto data);

  /**
   * Update a record by its ID
   *
   * @param id ID of the record to be updated
   * @param data Object that contains the registration information to be updated.
   * @return Record updated
   */
  TribeDto update(long id, TribeDto data);

  /**
   * Delete a record by its ID
   *
   * @param id ID of the record to be deleted
   * @return Record ID deleted
   */
  long deleteById(long id);
}
