package com.grievancetrack.entity;

import com.grievancetrack.enums.GrievanceStatus;
import com.grievancetrack.enums.Priority;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "grievances")
public class Grievance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "grievance_number", nullable = false, unique = true)
    private String grievanceNumber;

    @ManyToOne(optional = false)
    @JoinColumn(name = "citizen_id", nullable = false)
    private Citizen citizen;

    @ManyToOne(optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToOne(optional = false)
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    @Column(nullable = false)
    private String title;

    @Column(length = 2000)
    private String description;

    private String location;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GrievanceStatus status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Priority priority;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();

    @Column(name = "sla_days")
    private Integer slaDays;

    @Column(name = "sla_deadline")
    private LocalDateTime slaDeadline;

    @Column(name = "resolved_at")
    private LocalDateTime resolvedAt;

    @Column(name = "escalated_at")
    private LocalDateTime escalatedAt;

    @Column(name = "escalation_reason")
    private String escalationReason;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getGrievanceNumber() { return grievanceNumber; }
    public void setGrievanceNumber(String grievanceNumber) { this.grievanceNumber = grievanceNumber; }

    public Citizen getCitizen() { return citizen; }
    public void setCitizen(Citizen citizen) { this.citizen = citizen; }

    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }

    public Department getDepartment() { return department; }
    public void setDepartment(Department department) { this.department = department; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public GrievanceStatus getStatus() { return status; }
    public void setStatus(GrievanceStatus status) { this.status = status; }

    public Priority getPriority() { return priority; }
    public void setPriority(Priority priority) { this.priority = priority; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    public Integer getSlaDays() { return slaDays; }
    public void setSlaDays(Integer slaDays) { this.slaDays = slaDays; }

    public LocalDateTime getSlaDeadline() { return slaDeadline; }
    public void setSlaDeadline(LocalDateTime slaDeadline) { this.slaDeadline = slaDeadline; }

    public LocalDateTime getResolvedAt() { return resolvedAt; }
    public void setResolvedAt(LocalDateTime resolvedAt) { this.resolvedAt = resolvedAt; }

    public LocalDateTime getEscalatedAt() { return escalatedAt; }
    public void setEscalatedAt(LocalDateTime escalatedAt) { this.escalatedAt = escalatedAt; }

    public String getEscalationReason() { return escalationReason; }
    public void setEscalationReason(String escalationReason) { this.escalationReason = escalationReason; }
}
