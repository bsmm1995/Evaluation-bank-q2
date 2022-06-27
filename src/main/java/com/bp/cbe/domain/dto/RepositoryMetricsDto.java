package com.bp.cbe.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigInteger;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RepositoryMetricsDto implements Serializable {
  private long id;
  private String name;
  private String tribe;
  private String organization;
  private double coverage;
  private int codeSmells;
  private int bugs;
  private int vulnerabilities;
  private int hotspot;
  private String verificationState;
  private String state;

  public RepositoryMetricsDto(Object[] row) {
    this.id = ((BigInteger) row[0]).longValue();
    this.name = (String) row[1];
    this.tribe = (String) row[2];
    this.organization = (String) row[3];
    this.coverage = (double) row[4];
    this.codeSmells = (int) row[5];
    this.bugs = (int) row[6];
    this.vulnerabilities = (int) row[7];
    this.hotspot = (int) row[8];
    this.state = getState(((String) row[9]).toUpperCase());
  }

  private String getState(String state) {
    switch (state) {
      case "A":
        return "Archived";
      case "D":
        return "Disable";
      case "E":
        return "Enable";
      default:
        return "None";
    }
  }
}
