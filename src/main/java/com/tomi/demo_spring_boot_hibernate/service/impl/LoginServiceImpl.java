package com.tomi.demo_spring_boot_hibernate.service.impl;

import com.tomi.demo_spring_boot_hibernate.entity.Employee;
import com.tomi.demo_spring_boot_hibernate.payload.request.LoginRequest;
import com.tomi.demo_spring_boot_hibernate.payload.response.APIResponse;
import com.tomi.demo_spring_boot_hibernate.payload.response.EmployeeResponse;
import com.tomi.demo_spring_boot_hibernate.repository.EmployeeRepository;
import com.tomi.demo_spring_boot_hibernate.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {


    private final EmployeeRepository employeeRepository;


    @Override
    public ResponseEntity<APIResponse<EmployeeResponse>> login(LoginRequest loginRequest) {
        // Check if email exists
        if (!employeeRepository.existsByEmail(loginRequest.getEmail())) {
            // Return email not found message with 404 Not Found status
            APIResponse<EmployeeResponse> response = new APIResponse<>(
                    "Email not found. Please create an account.", null, false
            );
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        // Fetch employee details from the database
        Employee employee = employeeRepository.findByEmail(loginRequest.getEmail());

        // Check if the password matches
        if (employee != null && loginRequest.getPassword().equals( employee.getPassword())){
            // Build the response
            EmployeeResponse employeeResponse = EmployeeResponse.builder()
                    .firstName(employee.getFirstName())
                    .lastName(employee.getLastName())
                    .email(employee.getEmail())
                    .id(employee.getId())
                    .build();
            APIResponse<EmployeeResponse> response = new APIResponse<>(
                    "Login successful.", employeeResponse, true
            );

            // Return success with 200 OK status
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            // Return incorrect password message with 401 Unauthorized status
            APIResponse<EmployeeResponse> response = new APIResponse<>(
                    "Incorrect password. Please try again.", null, false
            );
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }


    }
}
