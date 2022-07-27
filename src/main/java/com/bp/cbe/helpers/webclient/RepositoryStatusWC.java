package com.bp.cbe.helpers.webclient;

import com.bp.cbe.domain.dto.webclient.RepositoryStatusDetail;

import java.util.List;

public interface RepositoryStatusWC {
    List<RepositoryStatusDetail> getAllStatus();
}
