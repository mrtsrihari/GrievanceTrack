package com.grievancetrack.service;

import com.grievancetrack.entity.Citizen;
import com.grievancetrack.exception.ResourceNotFoundException;
import com.grievancetrack.repository.CitizenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CitizenService {
    @Autowired private CitizenRepository citizenRepository;

    public Citizen createCitizen(Citizen citizen) {
        return citizenRepository.save(citizen);
    }
    
    public List<Citizen> getAllCitizens() {
        return citizenRepository.findAll();
    }
    
    public Citizen getCitizenById(Long id) {
        return citizenRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Citizen not found"));
    }
}
