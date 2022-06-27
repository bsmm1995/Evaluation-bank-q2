package com.bp.cbe.domain.dto.outs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MetricsOutDto implements Serializable {
  private long id;
  private double coverage;
  private int bugs;
  private int vulnerabilities;
  private int hotspot;
  private int codeSmells;
}
