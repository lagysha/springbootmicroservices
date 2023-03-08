package com.example.departmentservice.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDto {
    private Long id;
    @NotEmpty(message = "DepartmentName must be non null value")
    private String departmentName;
    @NotEmpty(message = "DepartmentDescription must be non null value")
    private String departmentDescription;
    @NotEmpty(message = "DepartmentCode must be non null value")
    private String departmentCode;
}
