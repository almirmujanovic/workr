package com.example.jobapplicationsystem.service;

import com.example.jobapplicationsystem.model.Job;
import com.example.jobapplicationsystem.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    // ✅ Return `Job` instead of `JobDto`
    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    public Job getJobById(Long id) {
        return jobRepository.findById(id).orElse(null);
    }

    public void saveJob(Job job) {
        jobRepository.save(job);
    }

    public void updateJob(Long id, Job job) {
        job.setId(id);
        jobRepository.save(job);
    }

    public void deleteJob(Long id) {
        jobRepository.deleteById(id);
    }
}
