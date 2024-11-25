package com.tomi.demo_spring_boot_hibernate.payload.request;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LeaveRequest {

    @NotNull(message = "Start date is required")
    private LocalDate startDate; // Use ISO format (e.g., "YYYY-MM-DD")

    @NotNull(message = "End date is required")
    private LocalDate endDate;

    @Size(max = 255, message = "Reason must not exceed 255 characters")
    private String reason; // Optional: Employee can provide a reason for leave
}
