package com.tomi.demo_spring_boot_hibernate.payload.request;

import com.tomi.demo_spring_boot_hibernate.entity.enums.AttendanceStatus;
import com.tomi.demo_spring_boot_hibernate.entity.enums.Reasons;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AttendanceRequest {

    @Enumerated(EnumType.STRING)
    private AttendanceStatus status; // Indicates if PRESENT or ABSENT

    @Enumerated(EnumType.STRING)
    private Reasons reason; // Applicable if status is ABSENT
}
