package com.bp.cbe.service.impl;

import com.bp.cbe.domain.dto.RepositoryDto;
import com.bp.cbe.domain.dto.RepositoryWithMetricsDto;
import com.bp.cbe.domain.entities.RepositoryEntity;
import com.bp.cbe.exceptions.NotFoundException;
import com.bp.cbe.repository.RepositoriesRepository;
import com.bp.cbe.service.RepositoriesService;
import com.bp.cbe.service.TribeService;
import com.bp.cbe.utils.Mapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class RepositoriesServiceImpl implements RepositoriesService {

    private final RepositoriesRepository repositoriesRepository;
    private final TribeService tribeService;

    @Override
    public RepositoryDto getById(Long id) {
        return entityToDto(getEntityById(id));
    }

    @Override
    public List<RepositoryDto> getAll() {
        return this.repositoriesRepository.findAll().stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<RepositoryWithMetricsDto> getAllWithMetrics() {
        return this.repositoriesRepository.findAll().stream()
                .map(element -> Mapper.modelMapper().map(element, RepositoryWithMetricsDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public RepositoryDto create(RepositoryDto data) {
        this.tribeService.getById(data.getTribe().getIdTribe());
        RepositoryEntity entity = dtoToEntity(data);
        return entityToDto(this.repositoriesRepository.save(entity));
    }

    @Override
    public RepositoryDto update(Long id, RepositoryDto data) {
        this.tribeService.getById(data.getTribe().getIdTribe());
        RepositoryEntity entity = getEntityById(id);
        entity.setName(data.getName());
        return entityToDto(this.repositoriesRepository.save(entity));
    }

    @Override
    public void deleteById(Long id) {
        this.repositoriesRepository.delete(getEntityById(id));
    }

    private RepositoryEntity getEntityById(long id) {
        Optional<RepositoryEntity> optional = this.repositoriesRepository.findById(id);
        return optional.orElseThrow(
                () ->
                        new NotFoundException(String.format("The repository with id %d does not exist.", id)));
    }

    private RepositoryDto entityToDto(RepositoryEntity entity) {
        return Mapper.modelMapper().map(entity, RepositoryDto.class);
    }

    private RepositoryEntity dtoToEntity(RepositoryDto dto) {
        return Mapper.modelMapper().map(dto, RepositoryEntity.class);
    }
}
