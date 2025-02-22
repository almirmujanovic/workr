package com.example.jobapplicationsystem.repository;

import com.example.jobapplicationsystem.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {
}