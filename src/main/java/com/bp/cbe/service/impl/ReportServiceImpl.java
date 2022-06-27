package com.bp.cbe.service.impl;

import com.bp.cbe.domain.dto.RepositoryMetricsDto;
import com.bp.cbe.repository.ReportRepository;
import com.bp.cbe.service.ReportService;
import com.bp.cbe.service.TribeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/** {@inheritDoc} */
@Service
@Slf4j
@AllArgsConstructor
public class ReportServiceImpl implements ReportService {

  private final ReportRepository repository;
  private final TribeService tribeService;

  /** {@inheritDoc} */
  @Override
  public List<RepositoryMetricsDto> getDataByTribe(long tribeId) {
    this.tribeService.getById(tribeId);
    return repository.getReport(tribeId);
  }
}
