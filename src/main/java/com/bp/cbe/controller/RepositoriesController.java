package com.bp.cbe.controller;

import com.bp.cbe.domain.dto.RepositoryDto;
import com.bp.cbe.domain.dto.RepositoryWithMetricsDto;
import com.bp.cbe.service.RepositoriesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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
@RequestMapping(value = "/repositories")
public class RepositoriesController {
    private final RepositoriesService repositoriesService;

    /**
     * Obtain a record by ID
     *
     * @param id ID of the record to be searched
     * @return Record found
     */
    @GetMapping("/{id}")
    public ResponseEntity<RepositoryDto> getById(@PathVariable long id) {
        log.info("Get by id. id=" + id);
        return ResponseEntity.ok(this.repositoriesService.getById(id));
    }

    /**
     * Get all records
     *
     * @return List of all records found
     */
    @GetMapping
    public ResponseEntity<List<RepositoryDto>> getAll() {
        log.info("Get all.");
        return ResponseEntity.ok(this.repositoriesService.getAll());
    }

    @GetMapping("/metrics")
    public ResponseEntity<List<RepositoryWithMetricsDto>> getAllWithMetrics() {
        log.info("Get all with metrics.");
        return ResponseEntity.ok(this.repositoriesService.getAllWithMetrics());
    }

    /**
     * Create a new record
     *
     * @param data Object containing the new record information
     * @return Record created
     */
    @PostMapping
    public ResponseEntity<RepositoryDto> create(@RequestBody @Valid RepositoryDto data) {
        log.info("Create. data=" + data);
        return new ResponseEntity<>(this.repositoriesService.create(data), HttpStatus.CREATED);
    }

    /**
     * Update a record by its ID
     *
     * @param id   ID of the record to be updated
     * @param data Object that contains the registration information to be updated.
     * @return Record updated
     */
    @PutMapping("/{id}")
    public ResponseEntity<RepositoryDto> update(
            @PathVariable long id, @RequestBody @Valid RepositoryDto data) {
        log.info("Update. id=" + id + ", data=" + data);
        return ResponseEntity.ok(this.repositoriesService.update(id, data));
    }

    /**
     * Delete a record by its ID
     *
     * @param id ID of the record to be deleted
     * @return Record ID deleted
     */
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        log.info("Delete. id=" + id);
        this.repositoriesService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
