package com.tomi.demo_spring_boot_hibernate.controller;


import com.tomi.demo_spring_boot_hibernate.payload.dto.EmployeeDTO;
import com.tomi.demo_spring_boot_hibernate.payload.request.EmployeeRequest;
import com.tomi.demo_spring_boot_hibernate.payload.response.APIResponse;
import com.tomi.demo_spring_boot_hibernate.payload.response.EmployeeResponse;
import com.tomi.demo_spring_boot_hibernate.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/employee/")
@RestController
@RequiredArgsConstructor
public class EmployeeController {

    //inject Employee service

    private final EmployeeService employeeService;

    @PostMapping("register")
    public ResponseEntity<APIResponse<EmployeeResponse>> registerEmployer(
            @RequestBody EmployeeRequest request){

        return employeeService.registerEmployee(request);  //This will help use get the request the user is sending in
    }

    @GetMapping("all.employee")
    ResponseEntity<APIResponse<List<EmployeeDTO>>> getAllEmployee (){

        return employeeService.getAllEmployee();
    }

    @GetMapping("/{Id}/getEmployee")
    ResponseEntity<APIResponse<List<EmployeeDTO>>> getEmployee (@PathVariable Long Id){

        return employeeService.getEmployee(Id);
    }

    @DeleteMapping(("/{id}/getDelete"))
    ResponseEntity<APIResponse<String>> deleteEmployee(@PathVariable Long id){

        return employeeService.deleteEmployee(id);
    }


}
