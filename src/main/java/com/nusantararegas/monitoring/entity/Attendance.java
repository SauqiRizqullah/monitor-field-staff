package com.nusantararegas.monitoring.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "m_attendances")
@EntityListeners(com.nusantararegas.monitoring.listener.AttendanceEntityListener.class)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Attendance {
    @Id
    @Column(name = "attendance_id", nullable = false)
    private String attendanceId;
    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false, unique = true)
    private Employee employee;
    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false)
    private Status status;
    @Column(name = "date", nullable = false)
    private Date date;
    @Column(name = "note")
    private String note;
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
}
