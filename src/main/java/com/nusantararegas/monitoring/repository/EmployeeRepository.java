package com.nusantararegas.monitoring.repository;

import com.nusantararegas.monitoring.entity.Employee;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, String>, JpaSpecificationExecutor<Employee> {
}
