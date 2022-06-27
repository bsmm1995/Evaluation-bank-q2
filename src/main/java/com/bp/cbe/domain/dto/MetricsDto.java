package com.bp.cbe.domain.dto;

import com.bp.cbe.domain.dto.outs.RepositoryOutDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MetricsDto implements Serializable {
  private long id;

  @NotNull(message = "Coverage cannot be null.")
  @Range(min = 0, max = 100, message = "Coverage should be a value between 0 and 100.")
  private double coverage;

  @NotNull(message = "Bugs cannot be null.")
  @Min(value = 0, message = "The number of bugs must not be less than zero.")
  private int bugs;

  @NotNull(message = "Vulnerabilities cannot be null.")
  @Min(value = 0, message = "The number of vulnerabilities should not be less than zero.")
  private int vulnerabilities;

  private int hotspot;
  private int codeSmells;

  private RepositoryOutDto repository;
}
