package com.tomi.demo_spring_boot_hibernate.service;

import com.tomi.demo_spring_boot_hibernate.payload.request.LeaveRequest;
import com.tomi.demo_spring_boot_hibernate.payload.response.APIResponse;
import com.tomi.demo_spring_boot_hibernate.payload.response.LeaveResponse;
import org.springframework.http.ResponseEntity;

public interface LeaveService {

    public ResponseEntity<APIResponse<LeaveResponse>> applyForLeave (LeaveRequest leaveRequest, Long employeeId);
}
