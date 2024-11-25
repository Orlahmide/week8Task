package com.tomi.demo_spring_boot_hibernate.payload.dto;

import com.tomi.demo_spring_boot_hibernate.entity.enums.AttendanceStatus;


import com.tomi.demo_spring_boot_hibernate.entity.enums.Reasons;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AttendanceDTO {

    private long employeeId;
    private long attendanceId;
    private AttendanceStatus attendanceStatus; // Replaces separate 'present' and 'absent' fields
    private Reasons reasons;
    private LocalDateTime date;

    public AttendanceDTO(Long id, AttendanceStatus status, Reasons reason, LocalDateTime createdAt) {
    }
}
