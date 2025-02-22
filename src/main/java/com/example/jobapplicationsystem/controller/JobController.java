package com.example.jobapplicationsystem.controller;

import com.example.jobapplicationsystem.dto.JobDto;
import com.example.jobapplicationsystem.service.JobService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/jobs")
public class JobController {

    @Autowired
    private JobService jobService;

    @GetMapping
    public String listJobs(Model model) {
        model.addAttribute("jobs", jobService.getAllJobs());
        return "job-list";
    }

    @PreAuthorize("hasRole('ADMIN')") // ✅ Only admins can add jobs
    @GetMapping("/new")
    public String showJobForm(Model model) {
        model.addAttribute("job", new JobDto());
        return "job-form";
    }

    @PreAuthorize("hasRole('ADMIN')") // ✅ Only admins can save jobs
    @PostMapping
    public String saveJob(@ModelAttribute JobDto jobDto) {
        jobService.saveJob(jobDto);
        return "redirect:/jobs";
    }
}
