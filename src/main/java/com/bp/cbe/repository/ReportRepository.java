package com.bp.cbe.repository;

import com.bp.cbe.domain.dto.RepositoryMetricsDto;

import java.util.List;

/**
 * @author: Bladimir Minga <bsminga@pichincha.com>
 * @version: 24/06/2022
 */
public interface ReportRepository {

  /**
   * Obtain the repositories of a tribe
   *
   * @param tribeId ID of the tribe from which the repositories are to be obtained
   * @return Report data found
   */
  List<Object[]> getReport(long tribeId);

  /**
   * Obtain the repositories of a tribe
   *
   * @param tribeId ID of the tribe from which the repositories are to be obtained
   * @return Report data found
   */
  List<RepositoryMetricsDto> getReportByCriteria(long tribeId);
}
