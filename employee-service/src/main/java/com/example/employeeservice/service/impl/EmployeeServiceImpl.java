package com.example.employeeservice.service.impl;

import com.example.employeeservice.dto.APIResponseDto;
import com.example.employeeservice.dto.DepartmentDto;
import com.example.employeeservice.dto.EmployeeDto;
import com.example.employeeservice.dto.OrganizationDto;
import com.example.employeeservice.entity.Employee;
import com.example.employeeservice.exception.EmailAlreadyExistsException;
import com.example.employeeservice.exception.ResourceNotFoundException;
import com.example.employeeservice.mapper.EmployeeMapper;
import com.example.employeeservice.repository.EmployeeRepository;
import com.example.employeeservice.client.APIClient;
import com.example.employeeservice.service.EmployeeService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;
    private WebClient webClient;
    private APIClient apiClient;
//    private RestTemplate restTemplate;

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {

        Optional<Employee> optionalUser = employeeRepository.findByEmail(employeeDto.getEmail());
        if (optionalUser.isPresent()) {
            throw new EmailAlreadyExistsException("Email Already Exists");
        }

        Employee employee = EmployeeMapper.instance.EmployeeDtoToEmployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);

        return EmployeeMapper.instance.EmployeeToEmployeeDto(savedEmployee);
    }

    @Override
    @CircuitBreaker(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartment")
    //@Retry(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartment")
    public APIResponseDto getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));

//        DepartmentDto departmentDTO  = restTemplate.
//         getForEntity("http://localhost:8080/api/departments/" + employee.getDepartmentCode(), DepartmentDto.class).getBody();
        DepartmentDto departmentDto = webClient.get()
                .uri("http://localhost:8080/api/departments/" + employee.getDepartmentCode())
                .retrieve()
                .bodyToMono(DepartmentDto.class)
                .block();
        OrganizationDto organizationDto = webClient.get()
                .uri("http://localhost:8083/api/organizations/" + employee.getOrganizationCode())
                .retrieve()
                .bodyToMono(OrganizationDto.class)
                .block();
//        DepartmentDto departmentDto = apiClient.getDepartment(employee.getDepartmentCode());
        return new APIResponseDto(EmployeeMapper.instance.EmployeeToEmployeeDto(employee), departmentDto,organizationDto);
    }

    public APIResponseDto getDefaultDepartment(Long id, Exception exception) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));

        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setDepartmentName("R&D Department");
        departmentDto.setDepartmentCode("RD001");
        departmentDto.setDepartmentDescription("Research and Development Department");

        OrganizationDto organizationDto = new OrganizationDto();
        organizationDto.setOrganizationName("Default organization");
        organizationDto.setOrganizationDescription("No description");
        organizationDto.setOrganizationCode("Not code provided");

        return new APIResponseDto(EmployeeMapper.instance.EmployeeToEmployeeDto(employee), departmentDto,organizationDto);
    }
}
