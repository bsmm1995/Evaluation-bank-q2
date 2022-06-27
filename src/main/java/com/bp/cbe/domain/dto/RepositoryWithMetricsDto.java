package com.bp.cbe.domain.dto;

import com.bp.cbe.domain.dto.outs.MetricsOutDto;
import com.bp.cbe.domain.dto.outs.TribeOutDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RepositoryWithMetricsDto implements Serializable {
  private long id;
  private String name;
  private String state;
  private LocalDateTime createTime;
  private TribeOutDto tribe;
  private MetricsOutDto metrics;
}
