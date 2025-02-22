package com.example.jobapplicationsystem.repository;

import com.example.jobapplicationsystem.model.Application;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
}