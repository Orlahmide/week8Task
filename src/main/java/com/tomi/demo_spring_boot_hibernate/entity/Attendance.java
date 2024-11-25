package com.tomi.demo_spring_boot_hibernate.entity;

import com.tomi.demo_spring_boot_hibernate.entity.enums.Reasons;
import com.tomi.demo_spring_boot_hibernate.entity.enums.AttendanceStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "attendance_tbl")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Attendance extends BaseClass {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AttendanceStatus status = AttendanceStatus.ABSENT; // Indicates if the employee is PRESENT or ABSENT

    @Enumerated(EnumType.STRING)
    private Reasons reason; // Nullable; applicable only when status is ABSENT

    private LocalDate attendanceDay;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;
}

