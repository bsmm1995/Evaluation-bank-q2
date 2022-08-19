package com.bp.cbe.controller;

import com.bp.cbe.domain.dto.RepositoryMetricsDto;
import com.bp.cbe.service.ReportService;
import com.bp.cbe.service.impl.ReportServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReportControllerTest {
    private ReportController reportController;
    private ReportService reportServiceMock;

    @BeforeEach
    void setUp() {
        reportServiceMock = Mockito.mock(ReportServiceImpl.class);
        reportController = new ReportController(reportServiceMock);
    }

    @Test
    void getDataByTribe() {
        long id = 1;
        RepositoryMetricsDto dto = new RepositoryMetricsDto();
        dto.setId(id);
        dto.setCoverage(0.80);
        dto.setBugs(10);

        when(this.reportServiceMock.getDataByTribe(id)).thenReturn(List.of(dto));
        ResponseEntity<List<RepositoryMetricsDto>> result = this.reportController.getDataByTribe(1);

        assertNotNull(result.getBody());
        assertEquals(1, result.getBody().size());
    }
}
