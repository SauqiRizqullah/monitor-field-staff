package com.nusantararegas.monitoring.controller;

import com.nusantararegas.monitoring.dtos.*;
import com.nusantararegas.monitoring.entity.Employee;
import com.nusantararegas.monitoring.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/employees")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<EmployeeResponse> createEmployee(@RequestBody EmployeeRequest employeeRequest) {
        EmployeeResponse employee = employeeService.createEmployee(employeeRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(employee);
    }

    @GetMapping(path = "/{employeeId}", produces = "application/json")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable String employeeId) {
        Employee employee = employeeService.getById(employeeId);
        return ResponseEntity.ok(employee);
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<CommonResponse<Page<Employee>>> getAllEmployees(
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "size", defaultValue = "10") Integer size,
            @RequestParam(name = "sortBy", defaultValue = "employeeId") String sortBy,
            @RequestParam(name = "direction", defaultValue = "ASC") String direction,
            @RequestParam(name = "fullName", required = false) String fullName,
            @RequestParam(name = "jobTitle", required = false) String jobTitle
    ) {

        // 1. Buat request object untuk filtering employee
        SearchEmployeeRequest request = SearchEmployeeRequest.builder()
                .page(page)
                .size(size)
                .sortBy(sortBy)
                .direction(direction)
                .fullName(fullName)
                .jobTitle(jobTitle)
                .build();

        // 2. Ambil page employees
        Page<Employee> allEmployees = employeeService.getAllEmployees(request);

        // 3. Buat paging object
        PagingResponse pagingResponse = PagingResponse.builder()
                .page(allEmployees.getPageable().getPageNumber() + 1)
                .size(allEmployees.getPageable().getPageSize())
                .totalPages(allEmployees.getTotalPages())
                .totalElements(allEmployees.getTotalElements())
                .hasNext(allEmployees.hasNext())
                .hasPrevious(allEmployees.hasPrevious())
                .build();

        // 4. Bungkus CommonResponse
        CommonResponse<Page<Employee>> response = CommonResponse.<Page<Employee>>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Success")
                .data(allEmployees)
                .paging(pagingResponse)
                .build();

        // 5. Return final response
        return ResponseEntity.ok(response);
    }


    @PutMapping(path = "/{employeeId}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> updateEmployee(@PathVariable String employeeId,
                                                 @RequestBody EmployeeRequest employeeRequest) {
        employeeService.updateEmployee(employeeId, employeeRequest);
        return ResponseEntity.ok("Employee updated successfully");
    }

    @DeleteMapping(path = "/{employeeId}", produces = "application/json")
    public ResponseEntity<String> deleteEmployee(@PathVariable String employeeId) {
        employeeService.deleteEmployee(employeeId);
        return ResponseEntity.ok("Employee deleted successfully");
    }
}
