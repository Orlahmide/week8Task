package com.tomi.demo_spring_boot_hibernate.service.impl;

import com.tomi.demo_spring_boot_hibernate.entity.Employee;
import com.tomi.demo_spring_boot_hibernate.entity.Leave;
import com.tomi.demo_spring_boot_hibernate.entity.enums.LeaveStatus;
import com.tomi.demo_spring_boot_hibernate.payload.request.LeaveRequest;
import com.tomi.demo_spring_boot_hibernate.payload.response.APIResponse;
import com.tomi.demo_spring_boot_hibernate.payload.response.LeaveResponse;
import com.tomi.demo_spring_boot_hibernate.repository.EmployeeRepository;
import com.tomi.demo_spring_boot_hibernate.repository.LeaveRepository;
import com.tomi.demo_spring_boot_hibernate.service.LeaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
public class LeaveServiceImpl implements LeaveService {

    private final EmployeeRepository employeeRepository;
    private final LeaveRepository leaveRepository;

    @Override
    public ResponseEntity<APIResponse<LeaveResponse>> applyForLeave(LeaveRequest leaveRequest, Long employeeId) {

        Employee employee = employeeRepository.findById(employeeId).orElse(null);

        if (employee == null) {
            return new ResponseEntity<>(
                    new APIResponse<>("Employee not found", null, false),
                    HttpStatus.NOT_FOUND
            );
        }

        LocalDate startDate = leaveRequest.getStartDate();
        LocalDate endDate = leaveRequest.getEndDate();
        long leaveDuration = ChronoUnit.DAYS.between(startDate, endDate) + 1;

        Leave leave = Leave.builder()
                .employee(employee)
                .startDate(leaveRequest.getStartDate())
                .endDate(leaveRequest.getEndDate())
                .leaveDuration(leaveDuration)
                .leaveReason(leaveRequest.getReason())
                .leaveStatus(LeaveStatus.PENDING)
                .build();
        leaveRepository.save(leave);


        LeaveResponse leaveResponse = LeaveResponse.builder()
                .leaveId(leave.getId())
                .startDate(leaveRequest.getStartDate())
                .endDate(leaveRequest.getEndDate())
                .leaveDuration(leaveDuration)
                .status(leave.getLeaveStatus())
                .reason(leaveRequest.getReason()).build();


        return new ResponseEntity<>(
                new APIResponse<>("Leave applied for successfully", leaveResponse, true, LocalDateTime.now()),
                HttpStatus.OK);
    }
}
