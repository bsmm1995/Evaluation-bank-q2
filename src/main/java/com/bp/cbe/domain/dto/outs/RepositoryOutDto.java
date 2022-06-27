package com.bp.cbe.domain.dto.outs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RepositoryOutDto implements Serializable {
  private long id;
  private String name;
}
