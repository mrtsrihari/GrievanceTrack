package com.grievancetrack.controller;

import com.grievancetrack.dto.GrievanceRequest;
import com.grievancetrack.dto.StatusUpdateRequest;
import com.grievancetrack.entity.Grievance;
import com.grievancetrack.enums.GrievanceStatus;
import com.grievancetrack.repository.GrievanceRepository;
import com.grievancetrack.service.GrievanceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/grievances")
public class GrievanceController {

    @Autowired
    private GrievanceService grievanceService;
    
    @Autowired
    private GrievanceRepository grievanceRepository; // direct repo use for simple queries

    @PostMapping
    public ResponseEntity<Grievance> createGrievance(@Valid @RequestBody GrievanceRequest request) {
        return new ResponseEntity<>(grievanceService.createGrievance(request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Grievance>> getAllGrievances() {
        return ResponseEntity.ok(grievanceService.getAllGrievances());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Grievance> getGrievanceById(@PathVariable Long id) {
        return ResponseEntity.ok(grievanceService.getGrievanceById(id));
    }
    
    @PatchMapping("/{id}/status")
    public ResponseEntity<Grievance> updateStatus(@PathVariable Long id, @Valid @RequestBody StatusUpdateRequest request) {
        return ResponseEntity.ok(grievanceService.updateStatus(id, request));
    }
    
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Grievance>> getByStatus(@PathVariable GrievanceStatus status) {
        return ResponseEntity.ok(grievanceRepository.findByStatus(status));
    }
    
    @GetMapping("/department/{departmentId}")
    public ResponseEntity<List<Grievance>> getByDepartment(@PathVariable Long departmentId) {
        return ResponseEntity.ok(grievanceRepository.findByDepartmentId(departmentId));
    }
    
    @GetMapping("/citizen/{citizenId}")
    public ResponseEntity<List<Grievance>> getByCitizen(@PathVariable Long citizenId) {
        return ResponseEntity.ok(grievanceRepository.findByCitizenId(citizenId));
    }
    
    @GetMapping("/escalated")
    public ResponseEntity<List<Grievance>> getEscalated() {
        return ResponseEntity.ok(grievanceRepository.findByStatus(GrievanceStatus.ESCALATED));
    }
}
