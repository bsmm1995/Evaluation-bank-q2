package com.bp.cbe.controller;

import com.bp.cbe.domain.dto.RepositoryMetricsDto;
import com.bp.cbe.service.ReportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * @author: Bladimir Minga <bsminga@pichincha.com>
 * @version: 24/06/2022
 */
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = "/reports")
public class ReportController {
    private final ReportService reportService;

    /**
     * Obtain the repositories of a tribe
     *
     * @param tribeId ID of the tribe from which the repositories are to be obtained
     * @return Report data found
     */
    @GetMapping("/repositories/{tribeId}")
    public ResponseEntity<List<RepositoryMetricsDto>> getDataByTribe(@PathVariable long tribeId) {
        log.info("Get by id. tribeId=" + tribeId);
        return ResponseEntity.ok(this.reportService.getDataByTribe(tribeId));
    }
}
