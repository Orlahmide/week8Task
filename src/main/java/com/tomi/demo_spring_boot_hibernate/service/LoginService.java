package com.tomi.demo_spring_boot_hibernate.service;

import com.tomi.demo_spring_boot_hibernate.payload.request.LoginRequest;
import com.tomi.demo_spring_boot_hibernate.payload.response.APIResponse;
import com.tomi.demo_spring_boot_hibernate.entity.Employee;
import com.tomi.demo_spring_boot_hibernate.payload.response.EmployeeResponse;
import org.springframework.http.ResponseEntity;

public interface LoginService {

    ResponseEntity<APIResponse<EmployeeResponse>> login(LoginRequest loginRequest);
}
