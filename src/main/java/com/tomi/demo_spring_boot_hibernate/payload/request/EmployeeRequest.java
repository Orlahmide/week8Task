package com.tomi.demo_spring_boot_hibernate.payload.request;

import com.tomi.demo_spring_boot_hibernate.entity.enums.Roles;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRequest {

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @Email(message = "Email must be valid")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;

    @Positive(message = "Salary must be a positive number")
    private BigDecimal salaryDetails;

    @NotNull(message = "Role is required")
    private Roles role; // Enum for roles like ADMIN, EMPLOYEE, etc.
}

