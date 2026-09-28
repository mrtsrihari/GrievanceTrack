package com.grievancetrack.service;

import com.grievancetrack.entity.Escalation;
import com.grievancetrack.repository.EscalationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EscalationService {
    @Autowired private EscalationRepository escalationRepository;

    public List<Escalation> getAllEscalations() {
        return escalationRepository.findAll();
    }
    
    public List<Escalation> getEscalationsByGrievance(Long grievanceId) {
        return escalationRepository.findByGrievanceId(grievanceId);
    }
}
