package com.bp.cbe.domain.dto.outs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TribeOutDto implements Serializable {
  private long idTribe;
  private String name;
}
