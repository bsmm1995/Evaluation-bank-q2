package com.bp.cbe.service;

import com.bp.cbe.domain.dto.OrganizationDto;

import java.util.List;

/**
 * @author: Bladimir Minga <bsminga@pichincha.com>
 * @version: 24/06/2022
 */
public interface OrganizationService {

    OrganizationDto getById(long id);

    List<OrganizationDto> getAll();

    OrganizationDto create(OrganizationDto data);

    OrganizationDto update(long id, OrganizationDto data);

    long deleteById(long id);
}
