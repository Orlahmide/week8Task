package com.tomi.demo_spring_boot_hibernate.service.impl;

import com.tomi.demo_spring_boot_hibernate.entity.Attendance;
import com.tomi.demo_spring_boot_hibernate.entity.Employee;
import com.tomi.demo_spring_boot_hibernate.entity.enums.AttendanceStatus;
import com.tomi.demo_spring_boot_hibernate.payload.dto.AttendanceDTO;
import com.tomi.demo_spring_boot_hibernate.payload.response.APIResponse;
import com.tomi.demo_spring_boot_hibernate.repository.AttendanceRepository;
import com.tomi.demo_spring_boot_hibernate.repository.EmployeeRepository;
import com.tomi.demo_spring_boot_hibernate.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public ResponseEntity<APIResponse<String>> markPresent(Long employeeId) {
        return updateAttendanceStatus(employeeId, AttendanceStatus.PRESENT, "marked as present");
    }

    @Override
    public ResponseEntity<APIResponse<String>> markOnLeave(Long employeeId) {
        return updateAttendanceStatus(employeeId, AttendanceStatus.ON_LEAVE, "marked as on leave");
    }

    @Override
    public ResponseEntity<APIResponse<List<AttendanceDTO>>> getAllAttendance() {
        List<Attendance> allAttendance = attendanceRepository.findAll();

        if (allAttendance.isEmpty()) {
            return new ResponseEntity<>(
                    new APIResponse<>("No attendance records found", null, false, LocalDateTime.now()),
                    HttpStatus.NOT_FOUND
            );
        }

        List<AttendanceDTO> attendanceDTOs = allAttendance.stream()
                .map(attendance -> new AttendanceDTO(
                        attendance.getEmployee().getId(),
                        attendance.getId(),
                        attendance.getStatus(),
                        attendance.getReason(),
                        attendance.getCreatedAt()))
                .toList();

        return new ResponseEntity<>(
                new APIResponse<>("Attendance records retrieved successfully", attendanceDTOs, true, LocalDateTime.now()),
                HttpStatus.OK
        );
    }

    @Override
    public ResponseEntity<APIResponse<List<AttendanceDTO>>> getAttendanceByUserId(Long id) {
        List<Attendance> attendanceList = attendanceRepository.findByEmployee_Id(id);

        if (attendanceList.isEmpty()) {
            return new ResponseEntity<>(
                    new APIResponse<>("No attendance records found for the user", null, false, LocalDateTime.now()),
                    HttpStatus.NOT_FOUND
            );
        }

        List<AttendanceDTO> attendanceDTOs = attendanceList.stream()
                .map(attendance -> new AttendanceDTO(
                        attendance.getEmployee().getId(),
                        attendance.getId(),
                        attendance.getStatus(),
                        attendance.getReason(),
                        attendance.getCreatedAt()))
                .toList();

        return new ResponseEntity<>(
                new APIResponse<>("Attendance records retrieved successfully", attendanceDTOs, true, LocalDateTime.now()),
                HttpStatus.OK
        );
    }

    private ResponseEntity<APIResponse<String>> updateAttendanceStatus(Long employeeId, AttendanceStatus status, String successMessage) {
        // Check if the employee exists
        Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);
        if (employeeOptional.isEmpty()) {
            return new ResponseEntity<>(
                    new APIResponse<>("Employee not found", null, false),
                    HttpStatus.NOT_FOUND
            );
        }

        Employee employee = employeeOptional.get();

        // Check if attendance exists for today
        LocalDate today = LocalDate.now();
        Optional<Attendance> attendanceOptional = attendanceRepository.findByEmployeeAndAttendanceDay(employee, today);

        Attendance attendance;
        attendance = attendanceOptional.orElseGet(() -> Attendance.builder()
                .employee(employee)
                .attendanceDay(today)
                .status(AttendanceStatus.ABSENT) // Default status
                .build());

        // Update the attendance status
        attendance.setStatus(status);
        attendanceRepository.save(attendance);

        return new ResponseEntity<>(
                new APIResponse<>("Attendance successfully " + successMessage, null, true),
                HttpStatus.OK
        );
    }
}
