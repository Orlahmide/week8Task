package com.tomi.demo_spring_boot_hibernate.repository;

import com.tomi.demo_spring_boot_hibernate.entity.Employee;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    boolean existsByEmail(@NotBlank(message = "Email is required") String email);

    Employee findByEmail(@NotBlank(message = "Email is required") String email);
}
