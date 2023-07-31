package com.example.departmentservice.mapper;

import com.example.departmentservice.dto.DepartmentDto;
import com.example.departmentservice.entity.Department;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DepartmentMapper {
    DepartmentMapper instance = Mappers.getMapper(DepartmentMapper.class);

    DepartmentDto departmentToDepartmentDto(Department department);
    Department departmentDtoToDepartment(DepartmentDto departmentDto);
}
