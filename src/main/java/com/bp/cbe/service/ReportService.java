package com.bp.cbe.service;

import com.bp.cbe.domain.dto.RepositoryMetricsDto;

import java.util.List;

/**
 * @author: Bladimir Minga <bsminga@pichincha.com>
 * @version: 24/06/2022
 */
public interface ReportService {

  List<RepositoryMetricsDto> getDataByTribe(long tribeId);
}
