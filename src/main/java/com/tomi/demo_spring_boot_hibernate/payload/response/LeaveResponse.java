package com.tomi.demo_spring_boot_hibernate.payload.response;

import com.tomi.demo_spring_boot_hibernate.entity.enums.LeaveStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LeaveResponse implements Serializable {

    private Long leaveId;
    private LocalDate startDate; // ISO date format
    private LocalDate endDate; // ISO date format
    private Long leaveDuration;
    private LeaveStatus status; // e.g., APPROVED, PENDING, REJECTED
    private String reason;
}
