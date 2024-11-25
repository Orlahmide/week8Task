package com.tomi.demo_spring_boot_hibernate.repository;


import com.tomi.demo_spring_boot_hibernate.entity.Leave;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaveRepository extends JpaRepository<Leave, Long> {

}
