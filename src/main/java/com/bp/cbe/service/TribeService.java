package com.bp.cbe.service;

import com.bp.cbe.domain.dto.TribeDto;

import java.util.List;

/**
 * @author: Bladimir Minga <bsminga@pichincha.com>
 * @version: 24/06/2022
 */
public interface TribeService {

    TribeDto getById(long id);

    List<TribeDto> getAll();

    TribeDto create(TribeDto data);

    TribeDto update(long id, TribeDto data);

    long deleteById(long id);
}
