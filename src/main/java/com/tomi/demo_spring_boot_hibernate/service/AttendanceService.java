package com.tomi.demo_spring_boot_hibernate.service;

import com.tomi.demo_spring_boot_hibernate.payload.dto.AttendanceDTO;
import com.tomi.demo_spring_boot_hibernate.payload.response.APIResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AttendanceService {

    ResponseEntity<APIResponse<String>> markPresent(Long employeeId);

    ResponseEntity<APIResponse<String>> markOnLeave(Long employeeId);

    ResponseEntity<APIResponse<List<AttendanceDTO>>> getAllAttendance ();

    ResponseEntity<APIResponse<List<AttendanceDTO>>> getAttendanceByUserId (Long id);
}

