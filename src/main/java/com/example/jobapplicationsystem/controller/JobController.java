package com.example.jobapplicationsystem.controller;

import com.example.jobapplicationsystem.dto.JobDto;
import com.example.jobapplicationsystem.model.Job;
import com.example.jobapplicationsystem.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/jobs")
public class JobController {

    @Autowired
    private JobService jobService;

    // ✅ List all jobs (returns DTOs)
    @GetMapping
    public String listJobs(Model model) {
        List<JobDto> jobDtos = jobService.getAllJobs()
                .stream()
                .map(this::convertToDto) // Convert Job to JobDto before sending to view
                .collect(Collectors.toList());

        model.addAttribute("jobs", jobDtos);
        return "job-list";
    }

    // ✅ Show job form
    @GetMapping("/new")
    public String showJobForm(Model model) {
        model.addAttribute("job", new JobDto());
        return "job-form";
    }

    // ✅ Save a new job
    @PostMapping
    public String saveJob(@ModelAttribute JobDto jobDto) {
        Job job = convertToEntity(jobDto); // Convert JobDto to Job before saving
        jobService.saveJob(job);
        return "redirect:/jobs";
    }

    // ✅ Show edit form
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Job job = jobService.getJobById(id);
        if (job == null) {
            return "redirect:/jobs?error=JobNotFound";
        }
        JobDto jobDto = convertToDto(job); // Convert Job to JobDto
        model.addAttribute("job", jobDto);
        return "job-form";
    }

    // ✅ Update job
    @PostMapping("/update/{id}")
    public String updateJob(@PathVariable Long id, @ModelAttribute JobDto jobDto) {
        Job job = convertToEntity(jobDto); // Convert DTO to Model
        jobService.updateJob(id, job);
        return "redirect:/jobs";
    }

    // ✅ Delete job
    @GetMapping("/delete/{id}")
    public String deleteJob(@PathVariable Long id) {
        jobService.deleteJob(id);
        return "redirect:/jobs";
    }

    // ✅ View job details
    @GetMapping("/details/{id}")
    public String jobDetails(@PathVariable Long id, Model model) {
        Job job = jobService.getJobById(id);
        if (job == null) {
            return "redirect:/jobs?error=JobNotFound";
        }
        JobDto jobDto = convertToDto(job); // Convert Job to JobDto
        model.addAttribute("job", jobDto);
        return "job-details";
    }

    // ✅ Helper method to convert JobDto → Job Model
    private Job convertToEntity(JobDto jobDto) {
        Job job = new Job();
        job.setId(jobDto.getId());
        job.setTitle(jobDto.getTitle());
        job.setDescription(jobDto.getDescription());
        return job;
    }

    // ✅ Helper method to convert Job Model → JobDto
    private JobDto convertToDto(Job job) {
        return new JobDto(job.getId(), job.getTitle(), job.getDescription());
    }
}
