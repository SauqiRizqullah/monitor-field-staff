package com.nusantararegas.monitoring.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "m_employees")
@EntityListeners(com.nusantararegas.monitoring.listener.EmployeeEntityListener.class)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    @Id
    @Column(name = "employee_id", nullable = false)
    private String employeeId;
    @Column(name = "full_name", nullable = false, unique = true)
    private String fullName;
    @Column(name = "job_title", nullable = false)
    private String jobTitle;
    @Column(name = "phone_number", nullable = false, unique = true)
    private String phoneNumber;
    @CreationTimestamp
    @Column(updatable = false, name = "created_at")
    private Date createdAt;
}
