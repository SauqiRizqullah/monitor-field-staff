package com.nusantararegas.monitoring.listener;

import com.nusantararegas.monitoring.entity.Attendance;
import com.nusantararegas.monitoring.service.AttendanceIdGenerator;
import com.nusantararegas.monitoring.utils.SpringContext;
import jakarta.persistence.PrePersist;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class AttendanceEntityListener {

    @PrePersist
    public void prePersist(Attendance attendance) {
        // Generate Attendance ID
        if (attendance.getAttendanceId() == null || attendance.getAttendanceId().isBlank()) {
            AttendanceIdGenerator generator = SpringContext.getBean(AttendanceIdGenerator.class);
            String id = generator.generateNextId();
            attendance.setAttendanceId(id);
        }

        // Set created_at jika belum ada
        if (attendance.getCreatedAt() == null) {
            attendance.setCreatedAt(LocalDateTime.now());
        }
    }


}
