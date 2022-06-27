package com.bp.cbe.service.impl;

import com.bp.cbe.domain.dto.MetricsDto;
import com.bp.cbe.domain.entities.MetricsEntity;
import com.bp.cbe.exceptions.NotFoundException;
import com.bp.cbe.repository.MetricsRepository;
import com.bp.cbe.service.MetricsService;
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
public class MetricsServiceImpl implements MetricsService {

  private final MetricsRepository metricsRepository;

  /** {@inheritDoc} */
  @Override
  public MetricsDto getById(long id) {
    return entityToDto(getEntityById(id));
  }

  /** {@inheritDoc} */
  @Override
  public List<MetricsDto> getAll() {
    return this.metricsRepository.findAll().stream()
        .map(this::entityToDto)
        .collect(Collectors.toList());
  }

  /** {@inheritDoc} */
  @Override
  public MetricsDto create(MetricsDto data) {
    MetricsEntity entity = dtoToEntity(data);
    entity.setId(null);
    return entityToDto(this.metricsRepository.save(entity));
  }

  /** {@inheritDoc} */
  @Override
  public MetricsDto update(long id, MetricsDto data) {
    getEntityById(id);
    MetricsEntity entity = dtoToEntity(data);
    entity.setId(id);
    return entityToDto(this.metricsRepository.save(entity));
  }

  /** {@inheritDoc} */
  @Override
  public long deleteById(long id) {
    getEntityById(id);
    this.metricsRepository.deleteById(id);
    return id;
  }

  private MetricsEntity getEntityById(long id) {
    Optional<MetricsEntity> optional = this.metricsRepository.findById(id);
    return optional.orElseThrow(
        () -> new NotFoundException(String.format("The metrics with id %d does not exist.", id)));
  }

  private MetricsDto entityToDto(MetricsEntity entity) {
    return Mapper.modelMapper().map(entity, MetricsDto.class);
  }

  private MetricsEntity dtoToEntity(MetricsDto dto) {
    return Mapper.modelMapper().map(dto, MetricsEntity.class);
  }
}
