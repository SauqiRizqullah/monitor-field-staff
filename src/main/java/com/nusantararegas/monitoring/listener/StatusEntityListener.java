package com.nusantararegas.monitoring.listener;

import com.nusantararegas.monitoring.entity.Status;
import com.nusantararegas.monitoring.service.StatusIdGenerator;
import com.nusantararegas.monitoring.utils.SpringContext;
import jakarta.persistence.PrePersist;

public class StatusEntityListener {

    @PrePersist
    public void prePersist(Status status) {
        if (status.getStatusId() == null || status.getStatusId().isBlank()) {
            StatusIdGenerator generator = SpringContext.getBean(StatusIdGenerator.class);
            String id = generator.generateNextId();
            status.setStatusId(id);
        }
    }
}

