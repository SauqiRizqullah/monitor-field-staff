// language: java
package com.nusantararegas.monitoring.listener;

import com.nusantararegas.monitoring.entity.Employee;
import com.nusantararegas.monitoring.service.EmployeeIdGenerator;
import com.nusantararegas.monitoring.utils.SpringContext;

import jakarta.persistence.PrePersist;

public class EmployeeEntityListener {

    @PrePersist
    public void prePersist(Employee employee) {
        if (employee.getEmployeeId() == null || employee.getEmployeeId().isBlank()) {
            EmployeeIdGenerator generator = SpringContext.getBean(EmployeeIdGenerator.class);
            String id = generator.generateNextId();
            employee.setEmployeeId(id);
        }
    }
}
