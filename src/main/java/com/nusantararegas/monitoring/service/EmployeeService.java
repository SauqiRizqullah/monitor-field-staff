package com.nusantararegas.monitoring.service;

import com.nusantararegas.monitoring.dtos.EmployeeRequest;
import com.nusantararegas.monitoring.dtos.EmployeeResponse;
import com.nusantararegas.monitoring.dtos.SearchEmployeeRequest;
import com.nusantararegas.monitoring.entity.Employee;
import com.nusantararegas.monitoring.repository.EmployeeRepository;
import com.nusantararegas.monitoring.specification.EmployeeSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public long countEmployees() {
        return employeeRepository.count();
    }

    @Transactional(rollbackFor = Exception.class)
    public EmployeeResponse createEmployee(EmployeeRequest request) {
        // Implementation for creating an employee
        Employee employee = Employee.builder()
                .fullName(request.getFullName())
                .jobTitle(request.getJobTitle())
                .phoneNumber(request.getPhoneNumber())
                .createdAt(new Date())
                .build();

        employeeRepository.save(employee);
        return EmployeeResponse.builder()
                .employeeId(employee.getEmployeeId())
                .fullName(employee.getFullName())
                .jobTitle(employee.getJobTitle())
                .phoneNumber(employee.getPhoneNumber())
                .build(); // Placeholder return
    }

    @Transactional(readOnly = true)
    public Employee getById(String id) {
        return employeeRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee's ID was not found!!!"));
    }

    @Transactional(readOnly = true)
    public Page<Employee> getAllEmployees(SearchEmployeeRequest request) {
        log.info("Getting All Employee Data!!!");
        log.info("");

        // 1. Page <= 0 maka set ke page = 1
        if (request.getPage() == null || request.getPage() <= 0) {
            log.info("Setting page from the first...");
            request.setPage(1);
        }

        // 2. Validasi kolom sortBy
        String validSortBy;
        if ("fullName".equalsIgnoreCase(request.getSortBy())) {
            log.info("Sorting by full name...");
            validSortBy = request.getSortBy();
        } else if ("jobTitle".equalsIgnoreCase(request.getSortBy())) {
            log.info("Sorting by job title...");
            validSortBy = request.getSortBy();
        } else {
            log.info("Sorting by employee id...");
            validSortBy = "employeeId"; // kolom default
        }

        // 3. Membuat rule Sort
        log.info("Creating sortBy rule...");
        Sort sort = Sort.by(
                Sort.Direction.fromString(request.getDirection()),
                validSortBy
        );

        // 4. Membuat Pageable object
        log.info("Creating a page object...");
        Pageable pageable = PageRequest.of(request.getPage() - 1, request.getSize(), sort);

        // 5. Membuat Specification
        log.info("Creating specification for employee...");
        Specification<Employee> specification = EmployeeSpecification.getSpecification(request);

        log.info("Successfully Employee Repository!!!");
        return employeeRepository.findAll(specification, pageable);
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteEmployee(String id){
        employeeRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee's ID was not found!!!"));
        employeeRepository.deleteById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateEmployee(String id, EmployeeRequest request){
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee's ID was not found!!!"));
        employee.setFullName(request.getFullName());
        employee.setJobTitle(request.getJobTitle());
        employee.setPhoneNumber(request.getPhoneNumber());
        employeeRepository.save(employee);
    }
}
