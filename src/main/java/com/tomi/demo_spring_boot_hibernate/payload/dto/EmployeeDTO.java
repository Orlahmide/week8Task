package com.tomi.demo_spring_boot_hibernate.payload.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class EmployeeDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
}
