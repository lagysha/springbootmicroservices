package com.example.employeeservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Schema(
        description = "EmployeeDto Model Information"
)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmployeeDto {
    private Long Id;
    @Schema(
            description = "Employee First Name"
    )
    @NotEmpty(message = "FirstName must be non null value")
    private String firstName;
    @Schema(
            description = "Employee Last Name"
    )
    @NotEmpty(message = "LastName must be non null value")
    private String lastName;
    @Schema(
            description = "Employee Email"
    )
    @NotEmpty(message = "Email must be non null value")
    @Email(message = "Email is not valid")
    private String email;
    @Schema(
            description = "Employee's Department Code"
    )
    private String departmentCode;
    @Schema(
            description = "Employee's Organization Code"
    )
    private String organizationCode;
}
