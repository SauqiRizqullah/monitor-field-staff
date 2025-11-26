package com.nusantararegas.monitoring.entity;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Entity
@Table(name = "m_status")
@EntityListeners(com.nusantararegas.monitoring.listener.StatusEntityListener.class)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Status {
    @Id
    @Column(name = "status_id", nullable = false)
    private String statusId;
    @Column(name = "status_name", nullable = false, unique = true)
    private String statusName;
}
