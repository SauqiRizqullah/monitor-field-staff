// language: java
package com.nusantararegas.monitoring.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class EmployeeIdGenerator {

    @PersistenceContext
    private EntityManager em;

    @Transactional(readOnly = true)
    public String generateNextId() {
        String prefix = "EMP";
        // SUBSTRING starts after the prefix (3 chars): position 4 (1-based)
        String query = "SELECT COALESCE(MAX(CAST(SUBSTRING(employee_id, 4) AS INTEGER)), 0) FROM m_employees";
        Object result = em.createNativeQuery(query).getSingleResult();
        int max = 0;
        if (result instanceof Number) {
            max = ((Number) result).intValue();
        } else {
            try {
                max = Integer.parseInt(String.valueOf(result));
            } catch (Exception ignored) {
            }
        }
        int next = max + 1;
        return prefix + String.format("%04d", next);
    }
}
