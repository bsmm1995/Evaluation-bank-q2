package com.bp.cbe.service.impl;

import com.bp.cbe.domain.dto.OrganizationDto;
import com.bp.cbe.domain.entities.OrganizationEntity;
import com.bp.cbe.exceptions.NotFoundException;
import com.bp.cbe.repository.OrganizationRepository;
import com.bp.cbe.service.OrganizationService;
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
public class OrganizationServiceImpl implements OrganizationService {
    private final OrganizationRepository organizationRepository;

    @Override
    public OrganizationDto getById(long id) {
        return entityToDto(getEntityById(id));
    }

    @Override
    public List<OrganizationDto> getAll() {
        return this.organizationRepository.findAll().stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public OrganizationDto create(OrganizationDto data) {
        OrganizationEntity entity = dtoToEntity(data);
        return entityToDto(this.organizationRepository.save(entity));
    }

    @Override
    public OrganizationDto update(long id, OrganizationDto data) {
        getEntityById(id);
        OrganizationEntity entity = dtoToEntity(data);
        entity.setId(id);
        return entityToDto(this.organizationRepository.save(entity));
    }

    @Override
    public long deleteById(long id) {
        getEntityById(id);
        this.organizationRepository.deleteById(id);
        return id;
    }

    private OrganizationEntity getEntityById(long id) {
        Optional<OrganizationEntity> optional = this.organizationRepository.findById(id);
        return optional.orElseThrow(
                () -> new NotFoundException(String.format("The organization with id %d does not exist.", id)));
    }

    private OrganizationDto entityToDto(OrganizationEntity entity) {
        return Mapper.modelMapper().map(entity, OrganizationDto.class);
    }

    private OrganizationEntity dtoToEntity(OrganizationDto dto) {
        return Mapper.modelMapper().map(dto, OrganizationEntity.class);
    }
}
