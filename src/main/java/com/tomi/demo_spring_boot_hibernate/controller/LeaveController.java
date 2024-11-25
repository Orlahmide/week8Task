package com.tomi.demo_spring_boot_hibernate.controller;


import com.tomi.demo_spring_boot_hibernate.payload.request.LeaveRequest;
import com.tomi.demo_spring_boot_hibernate.payload.response.APIResponse;
import com.tomi.demo_spring_boot_hibernate.payload.response.LeaveResponse;
import com.tomi.demo_spring_boot_hibernate.service.LeaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/employee/")
@RestController
@RequiredArgsConstructor

public class LeaveController {

    private final LeaveService leaveService;

    @PostMapping("{employeeId}/leave")
    public ResponseEntity<APIResponse<LeaveResponse>> applyForLeave
            (@RequestBody LeaveRequest leaveRequest, @PathVariable Long employeeId){

        return leaveService.applyForLeave(leaveRequest,employeeId);
    }

}
