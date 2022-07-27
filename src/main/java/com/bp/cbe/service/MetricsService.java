package com.bp.cbe.service;

import com.bp.cbe.domain.dto.MetricsDto;

import java.util.List;

/**
 * @author: Bladimir Minga <bsminga@pichincha.com>
 * @version: 24/06/2022
 */
public interface MetricsService {

    MetricsDto getById(long id);

    List<MetricsDto> getAll();

    MetricsDto create(MetricsDto data);

    MetricsDto update(long id, MetricsDto data);

    long deleteById(long id);
}
