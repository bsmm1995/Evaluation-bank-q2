package com.bp.cbe.domain.dto.webclient;

import lombok.Data;

import java.util.List;

@Data
public class ResponseWC {
    private List<RepositoryStatus> repositories;
}
