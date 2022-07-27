package com.bp.cbe.service.impl;

import com.bp.cbe.domain.dto.RepositoryMetricsDto;
import com.bp.cbe.domain.dto.webclient.RepositoryStatusDetail;
import com.bp.cbe.domain.enums.VerificationCode;
import com.bp.cbe.helpers.webclient.RepositoryStatusWC;
import com.bp.cbe.repository.ReportRepository;
import com.bp.cbe.service.ReportService;
import com.bp.cbe.service.TribeService;
import com.bp.cbe.utils.Status;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

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
        List<RepositoryStatusDetail> status = repositoryStatus.getAllStatus();
        result.forEach(e -> {
            e.setState(Status.getState(e.getState()));
            e.setVerificationState(getVerificationState(e.getId(), status));
        });
        return result;
    }

    private String getVerificationState(long id, List<RepositoryStatusDetail> status) {
        AtomicReference<String> verificationStatus = new AtomicReference<>("None");
        status.stream().filter(s -> s.getId() == id).findFirst().flatMap(detail -> Arrays.stream(VerificationCode.values()).filter(e -> e.getCode() == detail.getState()).findFirst()).ifPresent(verificationCode -> verificationStatus.set(verificationCode.getDescription()));
        return verificationStatus.get();
    }
}
