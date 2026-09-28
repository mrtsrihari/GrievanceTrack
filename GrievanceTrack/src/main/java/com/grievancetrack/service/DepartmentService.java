package com.grievancetrack.service;

import com.grievancetrack.entity.Department;
import com.grievancetrack.exception.ResourceNotFoundException;
import com.grievancetrack.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {
    @Autowired private DepartmentRepository departmentRepository;

    public Department createDepartment(Department department) {
        return departmentRepository.save(department);
    }
    
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }
    
    public Department getDepartmentById(Long id) {
        return departmentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Department not found"));
    }
}
