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
    public OrganizationDto getById(Long id) {
        return entityToDto(getEntityById(id));
    }

    @Override
    public List<OrganizationDto> getAll() {
        return this.organizationRepository.findAll().stream().map(this::entityToDto).collect(Collectors.toList());
    }

    @Override
    public OrganizationDto create(OrganizationDto data) {
        OrganizationEntity entity = dtoToEntity(data);
        return entityToDto(this.organizationRepository.save(entity));
    }

    @Override
    public OrganizationDto update(Long id, OrganizationDto data) {
        OrganizationEntity entity = getEntityById(id);
        entity.setName(data.getName());
        return entityToDto(this.organizationRepository.save(entity));
    }

    @Override
    public void deleteById(Long id) {
        this.organizationRepository.delete(getEntityById(id));
    }

    private OrganizationEntity getEntityById(long id) {
        Optional<OrganizationEntity> optional = this.organizationRepository.findById(id);
        return optional.orElseThrow(() -> new NotFoundException(String.format("The organization with id %d does not exist.", id)));
    }

    private OrganizationDto entityToDto(OrganizationEntity entity) {
        return Mapper.modelMapper().map(entity, OrganizationDto.class);
    }

    private OrganizationEntity dtoToEntity(OrganizationDto dto) {
        return Mapper.modelMapper().map(dto, OrganizationEntity.class);
    }
}
