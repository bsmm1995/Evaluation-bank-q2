package com.bp.cbe.domain.dto;

import com.bp.cbe.domain.dto.outs.OrganizationOutDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TribeDto implements Serializable {
    private long id;

    @NotEmpty(message = "The tribe name cannot be null or empty.")
    private String name;

    private OrganizationOutDto organization;
}
