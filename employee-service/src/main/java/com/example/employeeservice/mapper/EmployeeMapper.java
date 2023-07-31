package com.example.employeeservice.mapper;

import com.example.employeeservice.dto.EmployeeDto;
import com.example.employeeservice.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EmployeeMapper {

    EmployeeMapper instance = Mappers.getMapper(EmployeeMapper.class);

    EmployeeDto EmployeeToEmployeeDto(Employee employee);

    Employee EmployeeDtoToEmployee(EmployeeDto employeeDto);
}
