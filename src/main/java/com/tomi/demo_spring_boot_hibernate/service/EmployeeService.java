package com.tomi.demo_spring_boot_hibernate.service;

import com.tomi.demo_spring_boot_hibernate.payload.dto.EmployeeDTO;
import com.tomi.demo_spring_boot_hibernate.payload.request.EmployeeRequest;
import com.tomi.demo_spring_boot_hibernate.payload.response.APIResponse;
import com.tomi.demo_spring_boot_hibernate.payload.response.EmployeeResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EmployeeService {

    ResponseEntity<APIResponse<EmployeeResponse>> registerEmployee (EmployeeRequest request);

    ResponseEntity<APIResponse<List<EmployeeDTO>>> getAllEmployee ();

    ResponseEntity<APIResponse<List<EmployeeDTO>>> getEmployee (Long id);

    ResponseEntity<APIResponse<String>> deleteEmployee(Long id);

    


}
