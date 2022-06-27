package com.bp.cbe.service;

import com.bp.cbe.domain.dto.MetricsDto;
import com.bp.cbe.domain.entities.MetricsEntity;
import com.bp.cbe.repository.MetricsRepository;
import com.bp.cbe.service.impl.MetricsServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MetricsServiceTest {
  private MetricsService metricsService;
  private MetricsRepository metricsRepositoryMock;
  MetricsEntity entity;
  MetricsDto metricsDto;
  long id = 1;

  @BeforeEach
  void setUp() {
    this.metricsRepositoryMock = Mockito.mock(MetricsRepository.class);
    this.metricsService = new MetricsServiceImpl(this.metricsRepositoryMock);
    this.entity = new MetricsEntity(id, 50.0, 1, 1, 1, 1, 1, null);
    metricsDto = new MetricsDto(id, 50.0, 1, 1, 1, 1, null);
  }

  @Test
  void getById() {
    when(this.metricsRepositoryMock.findById(id)).thenReturn(Optional.of(entity));
    MetricsDto result = this.metricsService.getById(id);
    assertNotNull(result);
    assertEquals(id, result.getId());
  }

  @Test
  void getAll() {
    when(this.metricsRepositoryMock.findAll()).thenReturn(List.of(entity));
    List<MetricsDto> result = this.metricsService.getAll();
    assertNotNull(result);
    assertEquals(1, result.size());
  }

  @Test
  void create() {
    when(this.metricsRepositoryMock.save(any())).thenReturn(entity);
    MetricsDto result = this.metricsService.create(metricsDto);
    assertNotNull(result);
    assertEquals(1, result.getId());
  }

  @Test
  void update() {
    when(this.metricsRepositoryMock.findById(id)).thenReturn(Optional.of(entity));
    when(this.metricsRepositoryMock.save(any())).thenReturn(entity);
    MetricsDto result = this.metricsService.update(id, metricsDto);
    assertNotNull(result);
    assertEquals(1, result.getId());
  }

  @Test
  void deleteById() {
    when(this.metricsRepositoryMock.findById(id)).thenReturn(Optional.of(entity));
    long result = this.metricsService.deleteById(id);
    assertEquals(1, result);
  }
}
