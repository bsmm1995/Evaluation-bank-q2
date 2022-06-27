package com.bp.cbe.service.impl;

import com.bp.cbe.domain.dto.TribeDto;
import com.bp.cbe.domain.entities.TribeEntity;
import com.bp.cbe.exceptions.NotFoundException;
import com.bp.cbe.repository.TribeRepository;
import com.bp.cbe.service.OrganizationService;
import com.bp.cbe.service.TribeService;
import com.bp.cbe.utils.Mapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/** {@inheritDoc} */
@Service
@Slf4j
@AllArgsConstructor
public class TribeServiceImpl implements TribeService {

  private final TribeRepository tribeRepository;
  private final OrganizationService organizationService;

  /** {@inheritDoc} */
  @Override
  public TribeDto getById(long id) {
    return entityToDto(getEntityById(id));
  }

  /** {@inheritDoc} */
  @Override
  public List<TribeDto> getAll() {
    return this.tribeRepository.findAll().stream()
        .map(this::entityToDto)
        .collect(Collectors.toList());
  }

  /** {@inheritDoc} */
  @Override
  public TribeDto create(TribeDto data) {
    this.organizationService.getById(data.getOrganization().getId());
    TribeEntity entity = dtoToEntity(data);
    return entityToDto(this.tribeRepository.save(entity));
  }

  /** {@inheritDoc} */
  @Override
  public TribeDto update(long id, TribeDto data) {
    getEntityById(id);
    this.organizationService.getById(data.getOrganization().getId());
    TribeEntity entity = dtoToEntity(data);
    entity.setId(id);
    return entityToDto(this.tribeRepository.save(entity));
  }

  /** {@inheritDoc} */
  @Override
  public long deleteById(long id) {
    getEntityById(id);
    this.tribeRepository.deleteById(id);
    return id;
  }

  private TribeEntity getEntityById(long id) {
    Optional<TribeEntity> optional = this.tribeRepository.findById(id);
    return optional.orElseThrow(
        () -> new NotFoundException(String.format("The tribe with id %d does not exist.", id)));
  }

  private TribeDto entityToDto(TribeEntity entity) {
    return Mapper.modelMapper().map(entity, TribeDto.class);
  }

  private TribeEntity dtoToEntity(TribeDto dto) {
    return Mapper.modelMapper().map(dto, TribeEntity.class);
  }
}
