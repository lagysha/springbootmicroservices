package com.example.departmentservice.repository;

import com.example.departmentservice.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department,Long> {
    Optional<Department> findByDepartmentCode(String departmentCode);
}
