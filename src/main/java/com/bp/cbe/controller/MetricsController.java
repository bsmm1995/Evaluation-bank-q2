package com.bp.cbe.controller;

import com.bp.cbe.domain.dto.MetricsDto;
import com.bp.cbe.service.MetricsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author: Bladimir Minga <bsminga@pichincha.com>
 * @version: 24/06/2022
 */
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = "/metrics")
@Valid
public class MetricsController {
    private final MetricsService metricsService;

    /**
     * Obtain a record by ID
     *
     * @param id ID of the record to be searched
     * @return Record found
     */
    @GetMapping("/{id}")
    public ResponseEntity<MetricsDto> getById(@PathVariable long id) {
        log.info("Get by id. id=" + id);
        return ResponseEntity.ok(this.metricsService.getById(id));
    }

    /**
     * Get all records
     *
     * @return List of all records found
     */
    @GetMapping
    public ResponseEntity<List<MetricsDto>> getAll() {
        log.info("Get all.");
        return ResponseEntity.ok(this.metricsService.getAll());
    }

    /**
     * Create a new record
     *
     * @param data Object containing the new record information
     * @return Record created
     */
    @PostMapping
    public ResponseEntity<MetricsDto> create(@RequestBody @Valid MetricsDto data) {
        log.info("Create. data=" + data);
        return new ResponseEntity<>(this.metricsService.create(data), HttpStatus.CREATED);
    }

    /**
     * Update a record by its ID
     *
     * @param id   ID of the record to be updated
     * @param data Object that contains the registration information to be updated.
     * @return Record updated
     */
    @PutMapping("/{id}")
    public ResponseEntity<MetricsDto> update(
            @PathVariable long id, @RequestBody @Valid MetricsDto data) {
        log.info("Update. id=" + id + ", data=" + data);
        return ResponseEntity.ok(this.metricsService.update(id, data));
    }

    /**
     * Delete a record by its ID
     *
     * @param id ID of the record to be deleted
     * @return Record ID deleted
     */
    @DeleteMapping(value = "/{id}", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<Long> delete(@PathVariable long id) {
        log.info("Delete. id=" + id);
        return ResponseEntity.ok(this.metricsService.deleteById(id));
    }
}
