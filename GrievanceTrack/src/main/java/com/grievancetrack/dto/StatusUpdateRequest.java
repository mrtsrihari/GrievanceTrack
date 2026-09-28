package com.grievancetrack.dto;

import com.grievancetrack.enums.GrievanceStatus;
import jakarta.validation.constraints.NotNull;

public class StatusUpdateRequest {
    
    @NotNull(message = "Status is required")
    private GrievanceStatus status;

    public GrievanceStatus getStatus() { return status; }
    public void setStatus(GrievanceStatus status) { this.status = status; }
}
