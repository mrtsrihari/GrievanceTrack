package com.grievancetrack.repository;

import com.grievancetrack.entity.Grievance;
import com.grievancetrack.enums.GrievanceStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.time.LocalDateTime;

@Repository
public interface GrievanceRepository extends JpaRepository<Grievance, Long> {
    List<Grievance> findByStatus(GrievanceStatus status);
    List<Grievance> findByDepartmentId(Long departmentId);
    List<Grievance> findByCitizenId(Long citizenId);
    List<Grievance> findByCategoryId(Long categoryId);
    List<Grievance> findByStatusAndSlaDeadlineBefore(GrievanceStatus status, LocalDateTime dateTime);
    List<Grievance> findBySlaDeadlineBeforeAndStatusNotIn(LocalDateTime dateTime, List<GrievanceStatus> statuses);
}
