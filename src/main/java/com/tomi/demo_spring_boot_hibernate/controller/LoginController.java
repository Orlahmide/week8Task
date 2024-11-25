package com.tomi.demo_spring_boot_hibernate.controller;


import com.tomi.demo_spring_boot_hibernate.payload.request.LoginRequest;
import com.tomi.demo_spring_boot_hibernate.payload.response.APIResponse;
import com.tomi.demo_spring_boot_hibernate.payload.response.EmployeeResponse;
import com.tomi.demo_spring_boot_hibernate.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/employee/")
@RestController
@RequiredArgsConstructor
public class LoginController {

    //inject Employee service

    private final LoginService LoginService;

    @PostMapping("login")
    public ResponseEntity<APIResponse<EmployeeResponse>> login (
            @RequestBody LoginRequest request){

        return LoginService.login(request);  //This will help use get the request the user is sending in
    }

}
