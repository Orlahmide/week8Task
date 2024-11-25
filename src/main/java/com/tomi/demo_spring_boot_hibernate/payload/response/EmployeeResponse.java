package com.tomi.demo_spring_boot_hibernate.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeResponse implements Serializable {

    private Long id; // Include employee ID for easier identification
    private String firstName;
    private String lastName;
    private String email;
//    private Roles roles; // Include role information
    private BigDecimal salaryDetails; // Include salary details if relevant
}

