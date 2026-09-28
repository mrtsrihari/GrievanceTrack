package com.grievancetrack.controller;

import com.grievancetrack.entity.Escalation;
import com.grievancetrack.service.EscalationService;
import com.grievancetrack.service.SlaEscalationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/escalations")
public class EscalationController {

    @Autowired
    private EscalationService escalationService;
    
    @Autowired
    private SlaEscalationService slaEscalationService;

    @GetMapping
    public ResponseEntity<List<Escalation>> getAllEscalations() {
        return ResponseEntity.ok(escalationService.getAllEscalations());
    }

    @GetMapping("/grievance/{grievanceId}")
    public ResponseEntity<List<Escalation>> getEscalationsByGrievance(@PathVariable Long grievanceId) {
        return ResponseEntity.ok(escalationService.getEscalationsByGrievance(grievanceId));
    }
    
    @PostMapping("/check")
    public ResponseEntity<Map<String, String>> checkEscalations() {
        slaEscalationService.checkAndEscalateOverdueGrievances();
        return ResponseEntity.ok(Map.of("message", "SLA escalation check completed successfully."));
    }
}
