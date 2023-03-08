package com.example.departmentservice.services.impl;

import com.example.departmentservice.dto.DepartmentDto;
import com.example.departmentservice.enteties.Department;
import com.example.departmentservice.exception.DepartmentCodeAlreadyExistsException;
import com.example.departmentservice.exception.ResourceNotFoundException;
import com.example.departmentservice.mapper.DepartmentMapper;
import com.example.departmentservice.repositoties.DepartmentRepository;
import com.example.departmentservice.services.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepository departmentRepository;

    @Override
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {
        if(departmentRepository.findByDepartmentCode(departmentDto.getDepartmentCode()).isPresent()){
            throw new DepartmentCodeAlreadyExistsException("Department Code already exists");
        }

        Department department = DepartmentMapper.instance.departmentDtoToDepartment(departmentDto);
        Department savedDepartment = departmentRepository.save(department);

        return DepartmentMapper.instance.departmentToDepartmentDto(savedDepartment);
    }

    @Override
    public DepartmentDto getDepartmentByCode(String departmentCode) {
        Department department = departmentRepository.findByDepartmentCode(departmentCode)
                .orElseThrow(() -> new ResourceNotFoundException("Department","departmentCode", departmentCode));
        return DepartmentMapper.instance.departmentToDepartmentDto(department);
    }
}
