package com.nusantararegas.monitoring.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class StatusIdGenerator {

    @PersistenceContext
    private EntityManager em;

    @Transactional(readOnly = true)
    public String generateNextId() {
        String prefix = "STS";
        int startPos = prefix.length() + 1; // SUBSTRING position is 1-based
        String query = "SELECT COALESCE(MAX(CAST(SUBSTRING(status_id, " + startPos + ") AS INTEGER)), 0) FROM m_status";

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
