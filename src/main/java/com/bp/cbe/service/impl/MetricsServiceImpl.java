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

/**
 * {@inheritDoc}
 */
@Service
@Slf4j
@AllArgsConstructor
public class MetricsServiceImpl implements MetricsService {

    private final MetricsRepository metricsRepository;

    @Override
    public MetricsDto getById(Long id) {
        return entityToDto(getEntityById(id));
    }

    @Override
    public List<MetricsDto> getAll() {
        return this.metricsRepository.findAll().stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public MetricsDto create(MetricsDto data) {
        MetricsEntity entity = dtoToEntity(data);
        entity.setId(null);
        return entityToDto(this.metricsRepository.save(entity));
    }

    @Override
    public MetricsDto update(Long id, MetricsDto data) {
        MetricsEntity entity = getEntityById(id);
        entity.setHotspot(data.getHotspot());
        entity.setCodeSmells(entity.getCodeSmells());
        entity.setCoverage(data.getCoverage());
        entity.setBugs(data.getBugs());
        entity.setVulnerabilities(data.getVulnerabilities());
        return entityToDto(this.metricsRepository.save(entity));
    }

    @Override
    public void deleteById(Long id) {
        this.metricsRepository.delete(getEntityById(id));
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
