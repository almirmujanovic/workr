package com.example.jobapplicationsystem.service;

import com.example.jobapplicationsystem.model.Application;
import com.example.jobapplicationsystem.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    public List<Application> getAllApplications() {
        return applicationRepository.findAll();
    }

    public void saveApplication(Application application) {
        applicationRepository.save(application);
    }
}