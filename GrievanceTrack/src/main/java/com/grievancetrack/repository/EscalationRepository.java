package com.grievancetrack.repository;

import com.grievancetrack.entity.Escalation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EscalationRepository extends JpaRepository<Escalation, Long> {
    List<Escalation> findByGrievanceId(Long grievanceId);
}
