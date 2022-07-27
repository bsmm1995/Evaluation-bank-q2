package com.bp.cbe.controller;

import com.bp.cbe.domain.dto.webclient.RepositoryStatus;
import com.bp.cbe.helpers.webclient.RepositoryStatusWC;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping(value = "/repositories-status")
@Valid
public class RepositoriesStatus {
    private final RepositoryStatusWC repositoryStatusWC;

    @GetMapping
    public ResponseEntity<List<RepositoryStatus>> getById() {
        return ResponseEntity.ok(repositoryStatusWC.getAllStatus());
    }
}
