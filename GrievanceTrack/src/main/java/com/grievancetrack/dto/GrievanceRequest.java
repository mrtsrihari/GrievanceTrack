package com.grievancetrack.dto;

import com.grievancetrack.enums.Priority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class GrievanceRequest {
    
    @NotNull(message = "Citizen ID is required")
    private Long citizenId;

    @NotNull(message = "Category ID is required")
    private Long categoryId;

    @NotBlank(message = "Title is required")
    @Size(max = 255)
    private String title;

    @NotBlank(message = "Description is required")
    @Size(max = 2000)
    private String description;

    @NotBlank(message = "Location is required")
    private String location;

    @NotNull(message = "Priority is required")
    private Priority priority;

    public Long getCitizenId() { return citizenId; }
    public void setCitizenId(Long citizenId) { this.citizenId = citizenId; }
    public Long getCategoryId() { return categoryId; }
    public void setCategoryId(Long categoryId) { this.categoryId = categoryId; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public Priority getPriority() { return priority; }
    public void setPriority(Priority priority) { this.priority = priority; }
}
