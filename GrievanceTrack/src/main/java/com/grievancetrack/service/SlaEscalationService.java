package com.grievancetrack.service;

import com.grievancetrack.entity.Escalation;
import com.grievancetrack.entity.Grievance;
import com.grievancetrack.enums.EscalationLevel;
import com.grievancetrack.enums.GrievanceStatus;
import com.grievancetrack.repository.EscalationRepository;
import com.grievancetrack.repository.GrievanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class SlaEscalationService {

    @Autowired
    private GrievanceRepository grievanceRepository;

    @Autowired
    private EscalationRepository escalationRepository;

    @Transactional
    public void checkAndEscalateOverdueGrievances() {
        List<GrievanceStatus> excludedStatuses = Arrays.asList(GrievanceStatus.RESOLVED, GrievanceStatus.CLOSED, GrievanceStatus.ESCALATED);
        
        List<Grievance> overdueGrievances = grievanceRepository.findBySlaDeadlineBeforeAndStatusNotIn(
                LocalDateTime.now(), excludedStatuses);

        for (Grievance grievance : overdueGrievances) {
            escalateGrievance(grievance);
        }
    }

    private void escalateGrievance(Grievance grievance) {
        Escalation escalation = new Escalation();
        escalation.setGrievance(grievance);
        escalation.setReason("SLA Breached - Deadline was " + grievance.getSlaDeadline());
        escalation.setPreviousStatus(grievance.getStatus());
        escalation.setEscalationLevel(EscalationLevel.LEVEL_1);
        escalation.setRemarks("Auto-escalated by system");

        escalationRepository.save(escalation);

        grievance.setStatus(GrievanceStatus.ESCALATED);
        grievance.setEscalatedAt(LocalDateTime.now());
        grievance.setEscalationReason("SLA deadline exceeded");
        grievance.setUpdatedAt(LocalDateTime.now());

        grievanceRepository.save(grievance);
    }
}
