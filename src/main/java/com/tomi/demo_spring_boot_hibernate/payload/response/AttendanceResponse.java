package com.tomi.demo_spring_boot_hibernate.payload.response;

import com.tomi.demo_spring_boot_hibernate.entity.enums.AttendanceStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AttendanceResponse implements Serializable {

    private AttendanceStatus attendanceStatus; // Replaces separate 'present' and 'absent' fields
    private String reasons;
    private String date; // ISO date format (e.g., "2024-11-22")
}
