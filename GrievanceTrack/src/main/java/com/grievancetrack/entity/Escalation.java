package com.grievancetrack.entity;

import com.grievancetrack.enums.EscalationLevel;
import com.grievancetrack.enums.GrievanceStatus;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "escalations")
public class Escalation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "grievance_id", nullable = false)
    private Grievance grievance;

    @Column(name = "escalated_at", nullable = false)
    private LocalDateTime escalatedAt = LocalDateTime.now();

    @Column(nullable = false)
    private String reason;

    @Enumerated(EnumType.STRING)
    @Column(name = "previous_status")
    private GrievanceStatus previousStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "escalation_level")
    private EscalationLevel escalationLevel;

    private String remarks;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Grievance getGrievance() { return grievance; }
    public void setGrievance(Grievance grievance) { this.grievance = grievance; }

    public LocalDateTime getEscalatedAt() { return escalatedAt; }
    public void setEscalatedAt(LocalDateTime escalatedAt) { this.escalatedAt = escalatedAt; }

    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }

    public GrievanceStatus getPreviousStatus() { return previousStatus; }
    public void setPreviousStatus(GrievanceStatus previousStatus) { this.previousStatus = previousStatus; }

    public EscalationLevel getEscalationLevel() { return escalationLevel; }
    public void setEscalationLevel(EscalationLevel escalationLevel) { this.escalationLevel = escalationLevel; }

    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }
}
