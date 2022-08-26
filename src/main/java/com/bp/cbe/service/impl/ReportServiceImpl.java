package com.bp.cbe.service.impl;

import com.bp.cbe.domain.dto.RepositoryMetricsDto;
import com.bp.cbe.domain.dto.webclient.RepositoryStatusDetail;
import com.bp.cbe.helpers.webclient.RepositoryStatusWC;
import com.bp.cbe.repository.ReportRepository;
import com.bp.cbe.service.ReportService;
import com.bp.cbe.service.TribeService;
import com.bp.cbe.utils.Status;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final ReportRepository repository;
    private final TribeService tribeService;
    private final RepositoryStatusWC repositoryStatus;

    @Override
    public List<RepositoryMetricsDto> getDataByTribe(long tribeId) {
        this.tribeService.getById(tribeId);
        List<RepositoryMetricsDto> result = repository.getReport(tribeId);
        List<RepositoryStatusDetail> statusList = repositoryStatus.getAllStatus();
        result.forEach(e -> {
            e.setState(Status.getState(e.getState()));
            e.setVerificationState(Status.getVerificationState(e.getId(), statusList));
        });
        return result;
    }
}
