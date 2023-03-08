package com.example.employeeservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {
    private Long Id;
    @NotEmpty(message = "FirstName must be non null value")
    private String firstName;
    @NotEmpty(message = "LastName must be non null value")
    private String lastName;
    @NotEmpty(message = "Email must be non null value")
    @Email(message = "Email is not valid")
    private String email;
}
