package com.grievancetrack.service;

import com.grievancetrack.dto.GrievanceRequest;
import com.grievancetrack.dto.StatusUpdateRequest;
import com.grievancetrack.entity.Category;
import com.grievancetrack.entity.Citizen;
import com.grievancetrack.entity.Grievance;
import com.grievancetrack.enums.GrievanceStatus;
import com.grievancetrack.exception.BadRequestException;
import com.grievancetrack.exception.ResourceNotFoundException;
import com.grievancetrack.repository.CategoryRepository;
import com.grievancetrack.repository.CitizenRepository;
import com.grievancetrack.repository.GrievanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class GrievanceService {

    @Autowired
    private GrievanceRepository grievanceRepository;

    @Autowired
    private CitizenRepository citizenRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public Grievance createGrievance(GrievanceRequest request) {
        Citizen citizen = citizenRepository.findById(request.getCitizenId())
                .orElseThrow(() -> new ResourceNotFoundException("Citizen not found"));

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        Grievance grievance = new Grievance();
        
        // Generate unique number
        grievance.setGrievanceNumber("GRV-" + LocalDateTime.now().getYear() + "-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        
        grievance.setCitizen(citizen);
        grievance.setCategory(category);
        grievance.setDepartment(category.getDepartment());
        grievance.setTitle(request.getTitle());
        grievance.setDescription(request.getDescription());
        grievance.setLocation(request.getLocation());
        grievance.setPriority(request.getPriority());
        grievance.setStatus(GrievanceStatus.OPEN);
        
        grievance.setSlaDays(category.getSlaDays());
        grievance.setSlaDeadline(LocalDateTime.now().plusDays(category.getSlaDays()));

        return grievanceRepository.save(grievance);
    }

    public List<Grievance> getAllGrievances() {
        return grievanceRepository.findAll();
    }

    public Grievance getGrievanceById(Long id) {
        return grievanceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Grievance not found"));
    }

    public Grievance updateStatus(Long id, StatusUpdateRequest request) {
        Grievance grievance = getGrievanceById(id);
        
        if (grievance.getStatus() == GrievanceStatus.RESOLVED || grievance.getStatus() == GrievanceStatus.CLOSED) {
            if (request.getStatus() != GrievanceStatus.CLOSED && request.getStatus() != GrievanceStatus.RESOLVED) {
                throw new BadRequestException("Cannot change status of a resolved or closed grievance to an active status.");
            }
        }
        
        if (request.getStatus() == GrievanceStatus.RESOLVED) {
            grievance.setResolvedAt(LocalDateTime.now());
        }

        grievance.setStatus(request.getStatus());
        grievance.setUpdatedAt(LocalDateTime.now());
        return grievanceRepository.save(grievance);
    }
}
