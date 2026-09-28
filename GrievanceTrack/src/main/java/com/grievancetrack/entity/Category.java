package com.grievancetrack.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    private String description;

    @ManyToOne(optional = false)
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    @Column(name = "sla_days", nullable = false)
    private Integer slaDays;

    @Column(nullable = false)
    private Boolean active = true;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Department getDepartment() { return department; }
    public void setDepartment(Department department) { this.department = department; }

    public Integer getSlaDays() { return slaDays; }
    public void setSlaDays(Integer slaDays) { this.slaDays = slaDays; }

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
}
