package com.grievancetrack.config;

import com.grievancetrack.entity.Category;
import com.grievancetrack.entity.Citizen;
import com.grievancetrack.entity.Department;
import com.grievancetrack.entity.Grievance;
import com.grievancetrack.enums.GrievanceStatus;
import com.grievancetrack.enums.Priority;
import com.grievancetrack.repository.CategoryRepository;
import com.grievancetrack.repository.CitizenRepository;
import com.grievancetrack.repository.DepartmentRepository;
import com.grievancetrack.repository.GrievanceRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initData(CitizenRepository citizenRepo, 
                                      DepartmentRepository deptRepo, 
                                      CategoryRepository catRepo, 
                                      GrievanceRepository grvRepo) {
        return args -> {
            if (citizenRepo.count() == 0) {
                // Add Citizens
                Citizen c1 = new Citizen(); c1.setName("Alice"); c1.setEmail("alice@example.com"); c1.setPhone("1234567890"); citizenRepo.save(c1);
                Citizen c2 = new Citizen(); c2.setName("Bob"); c2.setEmail("bob@example.com"); c2.setPhone("2345678901"); citizenRepo.save(c2);

                // Add Departments
                Department d1 = new Department(); d1.setName("Roads Department"); deptRepo.save(d1);
                Department d2 = new Department(); d2.setName("Water Department"); deptRepo.save(d2);

                // Add Categories
                Category cat1 = new Category(); cat1.setName("Road Damage"); cat1.setDepartment(d1); cat1.setSlaDays(3); catRepo.save(cat1);
                Category cat2 = new Category(); cat2.setName("Water Supply"); cat2.setDepartment(d2); cat2.setSlaDays(2); catRepo.save(cat2);

                // Add Grievances
                Grievance g1 = new Grievance();
                g1.setGrievanceNumber("GRV-2026-0001");
                g1.setCitizen(c1);
                g1.setCategory(cat1);
                g1.setDepartment(cat1.getDepartment());
                g1.setTitle("Pothole on Main St");
                g1.setDescription("Huge pothole.");
                g1.setPriority(Priority.MEDIUM);
                g1.setStatus(GrievanceStatus.IN_PROGRESS);
                g1.setSlaDays(cat1.getSlaDays());
                g1.setSlaDeadline(LocalDateTime.now().minusDays(1)); // Deliberately overdue
                grvRepo.save(g1);

                Grievance g2 = new Grievance();
                g2.setGrievanceNumber("GRV-2026-0002");
                g2.setCitizen(c2);
                g2.setCategory(cat2);
                g2.setDepartment(cat2.getDepartment());
                g2.setTitle("No water since morning");
                g2.setDescription("Please fix water supply.");
                g2.setPriority(Priority.HIGH);
                g2.setStatus(GrievanceStatus.OPEN);
                g2.setSlaDays(cat2.getSlaDays());
                g2.setSlaDeadline(LocalDateTime.now().plusDays(2)); 
                grvRepo.save(g2);
            }
        };
    }
}
