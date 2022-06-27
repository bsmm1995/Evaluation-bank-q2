package com.bp.cbe.domain.dto;

import com.bp.cbe.domain.dto.outs.TribeOutDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RepositoryDto implements Serializable {
  private long id;

  @NotEmpty(message = "The repository name cannot be null or empty.")
  @Size(max = 50, message = "The repository name cannot be longer than 50 characters.")
  private String name;

  @NotEmpty(message = "The repository state name cannot be null or empty.")
  @Size(max = 1, message = "The repository state cannot be longer than one character.")
  private String state;

  @PastOrPresent private LocalDateTime createTime;

  private TribeOutDto tribe;
}
