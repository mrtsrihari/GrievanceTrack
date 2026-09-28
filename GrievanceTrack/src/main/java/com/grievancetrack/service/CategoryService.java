package com.grievancetrack.service;

import com.grievancetrack.entity.Category;
import com.grievancetrack.entity.Department;
import com.grievancetrack.exception.ResourceNotFoundException;
import com.grievancetrack.repository.CategoryRepository;
import com.grievancetrack.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired private CategoryRepository categoryRepository;
    @Autowired private DepartmentRepository departmentRepository;

    public Category createCategory(Category category) {
        Department dept = departmentRepository.findById(category.getDepartment().getId())
            .orElseThrow(() -> new ResourceNotFoundException("Department not found"));
        category.setDepartment(dept);
        return categoryRepository.save(category);
    }
    
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
    
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category not found"));
    }
}
