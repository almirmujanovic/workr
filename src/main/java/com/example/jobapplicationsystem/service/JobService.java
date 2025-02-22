package com.example.jobapplicationsystem.service;

import com.example.jobapplicationsystem.dto.JobDto;
import com.example.jobapplicationsystem.model.Job;
import com.example.jobapplicationsystem.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    public List<JobDto> getAllJobs() {
        return jobRepository.findAll().stream()
                .map(job -> new JobDto(job.getId(), job.getTitle(), job.getDescription()))
                .collect(Collectors.toList());
    }

    public JobDto getJobById(Long id) {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job not found"));
        return new JobDto(job.getId(), job.getTitle(), job.getDescription());
    }

    public void saveJob(JobDto jobDto) {
        Job job = new Job();
        job.setTitle(jobDto.getTitle());
        job.setDescription(jobDto.getDescription());
        jobRepository.save(job);
    }

    public void updateJob(Long id, JobDto jobDto) {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job not found"));
        job.setTitle(jobDto.getTitle());
        job.setDescription(jobDto.getDescription());
        jobRepository.save(job);
    }

    public void deleteJob(Long id) {
        jobRepository.deleteById(id);
    }
}
