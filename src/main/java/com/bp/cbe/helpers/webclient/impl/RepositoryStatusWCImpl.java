package com.bp.cbe.helpers.webclient.impl;

import com.bp.cbe.domain.dto.webclient.RepositoryStatusDetail;
import com.bp.cbe.domain.dto.webclient.RepositoryStatus;
import com.bp.cbe.helpers.webclient.RepositoryStatusWC;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Objects;

@Service
public class RepositoryStatusWCImpl implements RepositoryStatusWC {
    private final WebClient wClient;

    public RepositoryStatusWCImpl() {
        this.wClient = WebClient.builder().baseUrl("https://253b6042-ec17-4ee8-8d7d-ea9d62805337.mock.pstmn.io").build();
    }

    @Override
    public List<RepositoryStatusDetail> getAllStatus() {
        Mono<RepositoryStatus> mono = this.wClient.get().uri("/v1/repositories/verified")
                .retrieve().bodyToMono(RepositoryStatus.class);
        return Objects.requireNonNull(mono.block()).getRepositories();
    }
}
