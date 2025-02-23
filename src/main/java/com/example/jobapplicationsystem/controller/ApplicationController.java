package com.example.jobapplicationsystem.controller;

import com.example.jobapplicationsystem.model.Application;
import com.example.jobapplicationsystem.model.Job;
import com.example.jobapplicationsystem.model.User;
import com.example.jobapplicationsystem.service.ApplicationService;
import com.example.jobapplicationsystem.service.JobService;
import com.example.jobapplicationsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/applications")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private JobService jobService; // ✅ Now returns `Job`, NOT `JobDto`

    @Autowired
    private UserService userService;

    // ✅ List all job applications (ADMIN ONLY)
    @GetMapping
    public String viewApplications(Model model) {
        List<Application> applications = applicationService.getAllApplications();
        model.addAttribute("applicationsList", applications);
        return "applications";
    }

    // ✅ Show job details where users can apply
    @GetMapping("/apply/{jobId}")
    public String showApplyForm(@PathVariable Long jobId, Model model) {
        Job job = jobService.getJobById(jobId); // ✅ Directly get `Job`
        if (job == null) {
            return "redirect:/jobs?error=JobNotFound";
        }
        model.addAttribute("job", job);
        return "job-details";
    }

    // ✅ Apply for a job (USER ONLY)
    @PostMapping("/apply/{jobId}")
    public String applyForJob(@PathVariable Long jobId,
                              @AuthenticationPrincipal UserDetails userDetails,
                              @RequestParam String applicationText) {

        Job job = jobService.getJobById(jobId);
        if (job == null) {
            return "redirect:/jobs?error=JobNotFound";
        }

        User user = userService.findByUsername(userDetails.getUsername());

        Application application = new Application();
        application.setJob(job);
        application.setUser(user);
        application.setApplicationText(applicationText);

        applicationService.saveApplication(application);

        // ✅ Redirect back with success message
        return "redirect:/jobs/details/" + jobId + "?success=ApplicationSubmitted";
    }

}
