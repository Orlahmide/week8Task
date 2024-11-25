package com.tomi.demo_spring_boot_hibernate.entity;

import com.tomi.demo_spring_boot_hibernate.entity.enums.Roles;
import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "employee_tbl")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Employee extends BaseClass {

    @NotBlank(message = "First name cannot be blank")
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotBlank(message = "Last name cannot be blank")
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email cannot be blank")
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank(message = "Password cannot be blank")
    private String password;

    @Positive(message = "Salary must be positive")
    @Column(name = "salary_details", nullable = false, precision = 10, scale = 2)
    private BigDecimal salaryDetails;

    @Column(name = "remaining_leave_days", nullable = false)
    private int remainingLeaveDays = 10; // Default leave balance

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Roles roles;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<Attendance> attendances;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<Leave> leaves;
}
