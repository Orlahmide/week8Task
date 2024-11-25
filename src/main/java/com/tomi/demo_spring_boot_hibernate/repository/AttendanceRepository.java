package com.tomi.demo_spring_boot_hibernate.repository;

import com.tomi.demo_spring_boot_hibernate.entity.Attendance;
import com.tomi.demo_spring_boot_hibernate.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AttendanceRepository extends JpaRepository<Attendance, Long > {
    Optional<Attendance> findByEmployee(Employee employee);


    List<Attendance> findByemployee_Id(Long id);
    

    List<Attendance> findByEmployee_Id(Long id);


    Optional<Attendance> findByEmployeeAndAttendanceDay(Employee employee, LocalDate today);
}
