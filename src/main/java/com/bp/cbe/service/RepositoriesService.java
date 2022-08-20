package com.bp.cbe.service;

import com.bp.cbe.domain.dto.RepositoryDto;
import com.bp.cbe.domain.dto.RepositoryWithMetricsDto;

import java.util.List;

/**
 * @author: Bladimir Minga <bsminga@pichincha.com>
 * @version: 24/06/2022
 */
public interface RepositoriesService extends Service<RepositoryDto, Long> {
    List<RepositoryWithMetricsDto> getAllWithMetrics();
}
