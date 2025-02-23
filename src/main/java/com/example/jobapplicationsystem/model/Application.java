package com.example.jobapplicationsystem.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job; // ✅ FIXED: Ensure it's `Job`, not `JobDto`

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @NotBlank(message = "Application text is mandatory")
    private String applicationText;

    // ✅ Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Job getJob() { return job; }
    public void setJob(Job job) { this.job = job; } // ✅ FIXED: Ensure `Job` is used

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public String getApplicationText() { return applicationText; }
    public void setApplicationText(String applicationText) { this.applicationText = applicationText; }
}
