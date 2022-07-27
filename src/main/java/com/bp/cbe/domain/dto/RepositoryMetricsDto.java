package com.bp.cbe.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class RepositoryMetricsDto implements Serializable {
    private long id;
    private String name;
    private String state;
    private String tribe;
    private String organization;
    private double coverage;
    private int codeSmells;
    private int bugs;
    private int vulnerabilities;
    private int hotspot;

    public RepositoryMetricsDto(long id, String name, String state, String tribe, String organization, double coverage, int codeSmells, int bugs, int vulnerabilities, int hotspot) {
        this.id = id;
        this.name = name;
        this.state = state;
        this.tribe = tribe;
        this.organization = organization;
        this.coverage = coverage;
        this.codeSmells = codeSmells;
        this.bugs = bugs;
        this.vulnerabilities = vulnerabilities;
        this.hotspot = hotspot;
    }

    private String verificationState;
}
