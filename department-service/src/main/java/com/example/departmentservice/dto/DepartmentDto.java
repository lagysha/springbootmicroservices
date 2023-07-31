package com.example.departmentservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Schema(
        description = "DepartmentDto Model Information"
)
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDto {
    private Long id;
    @Schema(
            description = "Department Name"
    )
    @NotEmpty(message = "DepartmentName must be non null value")
    private String departmentName;
    @Schema(
            description = "Department Description"
    )
    @NotEmpty(message = "DepartmentDescription must be non null value")
    private String departmentDescription;
    @Schema(
            description = "Department Code"
    )
    @NotEmpty(message = "DepartmentCode must be non null value")
    private String departmentCode;
}
