package com.tomi.demo_spring_boot_hibernate.service.impl;

import com.tomi.demo_spring_boot_hibernate.entity.Employee;
import com.tomi.demo_spring_boot_hibernate.entity.enums.Roles;
import com.tomi.demo_spring_boot_hibernate.payload.dto.EmployeeDTO;
import com.tomi.demo_spring_boot_hibernate.payload.request.EmployeeRequest;
import com.tomi.demo_spring_boot_hibernate.payload.response.APIResponse;
import com.tomi.demo_spring_boot_hibernate.payload.response.EmployeeResponse;
import com.tomi.demo_spring_boot_hibernate.repository.EmployeeRepository;
import com.tomi.demo_spring_boot_hibernate.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static java.time.LocalTime.now;


@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public ResponseEntity<APIResponse<EmployeeResponse>> registerEmployee(EmployeeRequest request) {

        //first check if the employee already has an account

        boolean isEmailPresent = employeeRepository.existsByEmail(request.getEmail());

        if (isEmailPresent){
            throw new RuntimeException("Email already exists");
        }

        Employee newEmployee = Employee.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(request.getPassword())
                .salaryDetails(request.getSalaryDetails())
                .roles(Roles.USER)
                .build();

        employeeRepository.save(newEmployee);

        EmployeeResponse response = EmployeeResponse.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .salaryDetails(request.getSalaryDetails())
                .build();


        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new APIResponse<>("Account Created Successfully", response, true, LocalDateTime.now()));
    }

    @Override
    public ResponseEntity<APIResponse<List<EmployeeDTO>>> getAllEmployee() {

        List<Employee> allEmployee = employeeRepository.findAll();

        if (allEmployee.isEmpty()) {
            return new ResponseEntity<>(
                    new APIResponse<>("No employees found", null, false, LocalDateTime.now()),
                    HttpStatus.NOT_FOUND
            );

        }

        List<EmployeeDTO> employeeDTOs = allEmployee.stream()
                .map(employee -> new EmployeeDTO(
                        employee.getId(),
                        employee.getFirstName(),
                        employee.getLastName(),
                        employee.getEmail()))
                .toList();

        return new ResponseEntity<>(
                new APIResponse<>("Employees retrieved successfully", employeeDTOs, true, LocalDateTime.now()),
                HttpStatus.OK
        );

    }

    @Override
    public ResponseEntity<APIResponse<List<EmployeeDTO>>> getEmployee(Long id) {

        Optional<Employee> singleEmployee = employeeRepository.findById(id);

        if (singleEmployee.isEmpty()) {
            return new ResponseEntity<>(
                    new APIResponse<>("No employees found", null, false, LocalDateTime.now()),
                    HttpStatus.NOT_FOUND
            );

        }

        List<EmployeeDTO> employeeDTOs = singleEmployee.stream()
                .map(employee -> new EmployeeDTO(
                        employee.getId(),
                        employee.getFirstName(),
                        employee.getLastName(),
                        employee.getEmail()))
                .toList();

        return new ResponseEntity<>(
                new APIResponse<>("Employees retrieved successfully", employeeDTOs, true, LocalDateTime.now()),
                HttpStatus.OK
        );


    }

    @Override
    public ResponseEntity<APIResponse<String>> deleteEmployee(Long id) {

        Optional<Employee> employeeOptional = employeeRepository.findById(id);

        if (employeeOptional.isEmpty()) {
            return new ResponseEntity<>(
                    new APIResponse<>("Employee not found", null, false),
                    HttpStatus.NOT_FOUND
            );
        }

        // Delete the employee
        employeeRepository.deleteById(id);

        return new ResponseEntity<>(
                new APIResponse<>("Employee deleted successfully", null, true),
                HttpStatus.OK
        );
    }


}
