package com.tomi.demo_spring_boot_hibernate.controller;


import com.tomi.demo_spring_boot_hibernate.payload.dto.AttendanceDTO;
import com.tomi.demo_spring_boot_hibernate.payload.dto.EmployeeDTO;
import com.tomi.demo_spring_boot_hibernate.payload.response.APIResponse;
import com.tomi.demo_spring_boot_hibernate.service.AttendanceService;
import com.tomi.demo_spring_boot_hibernate.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/employee/")
@RestController
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceService attendanceService;

    @PutMapping("/{employeeId}/present")
    ResponseEntity<APIResponse<String>> markPresent(@PathVariable Long employeeId){

        return attendanceService.markPresent(employeeId);
    }

    @PutMapping("/{employeeId}/on_leave")
    ResponseEntity<APIResponse<String>> markOnLeave(@PathVariable Long employeeId){

        return attendanceService.markOnLeave(employeeId);
    }

    @GetMapping("all.attendance")
    ResponseEntity<APIResponse<List<AttendanceDTO>>> getAllAttendance (){

        return attendanceService.getAllAttendance();
    }

    @GetMapping("/{Id}/getAttendance")
    ResponseEntity<APIResponse<List<AttendanceDTO>>> getAttendanceByUserId (@PathVariable Long Id){

        return attendanceService.getAttendanceByUserId(Id);
    }


}
