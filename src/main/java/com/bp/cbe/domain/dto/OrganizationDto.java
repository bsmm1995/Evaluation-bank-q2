package com.bp.cbe.domain.dto;

import com.bp.cbe.domain.dto.outs.TribeOutDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrganizationDto implements Serializable {
  private long id;

  @NotEmpty(message = "The name of the organization cannot be null and void.")
  @Size(max = 50, message = "The name of the organization cannot be longer than 50 characters.")
  private String name;

  private List<TribeOutDto> tribes;
}
