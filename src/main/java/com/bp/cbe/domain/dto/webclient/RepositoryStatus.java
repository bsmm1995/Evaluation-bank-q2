package com.bp.cbe.domain.dto.webclient;

import lombok.Data;

import java.util.List;

@Data
public class RepositoryStatus {
    private List<RepositoryStatusDetail> repositories;
}
