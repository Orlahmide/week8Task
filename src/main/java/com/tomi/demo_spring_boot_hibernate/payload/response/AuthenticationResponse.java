package com.tomi.demo_spring_boot_hibernate.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthenticationResponse implements Serializable {

    private String token; // JWT or other authentication token
    private String message;
    private EmployeeResponse employeeDetails; // Embed employee details
}
