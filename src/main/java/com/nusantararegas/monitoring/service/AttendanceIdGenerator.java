package com.nusantararegas.monitoring.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class AttendanceIdGenerator {

    @PersistenceContext
    private EntityManager em;

    @Transactional(readOnly = true)
    public String generateNextId() {
        String prefix = "ATT";

        // Ambil angka setelah prefix ATT (3 huruf → mulai posisi ke-4)
        String query = "SELECT COALESCE(MAX(CAST(SUBSTRING(attendance_id, 4) AS INTEGER)), 0) FROM m_attendances";

        Object result = em.createNativeQuery(query).getSingleResult();

        int max = 0;
        if (result instanceof Number) {
            max = ((Number) result).intValue();
        } else {
            try {
                max = Integer.parseInt(String.valueOf(result));
            } catch (Exception ignored) {}
        }

        int next = max + 1;
        return prefix + String.format("%04d", next);
    }
}
