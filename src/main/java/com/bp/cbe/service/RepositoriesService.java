package com.bp.cbe.service;

import com.bp.cbe.domain.dto.RepositoryDto;
import com.bp.cbe.domain.dto.RepositoryWithMetricsDto;

import java.util.List;

/**
 * @author: Bladimir Minga <bsminga@pichincha.com>
 * @version: 24/06/2022
 */
public interface RepositoriesService {

    RepositoryDto getById(long id);

    List<RepositoryDto> getAll();

    List<RepositoryWithMetricsDto> getAllWithMetrics();

    RepositoryDto create(RepositoryDto data);

    RepositoryDto update(long id, RepositoryDto data);

    long deleteById(long id);
}
