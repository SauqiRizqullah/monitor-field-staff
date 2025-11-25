// language: java
package com.nusantararegas.monitoring.listener;

import com.nusantararegas.monitoring.entity.User;
import com.nusantararegas.monitoring.service.UserIdGenerator;
import com.nusantararegas.monitoring.utils.SpringContext;

import jakarta.persistence.PrePersist;

public class UserEntityListener {

    @PrePersist
    public void prePersist(User user) {
        if (user.getId() == null || user.getId().isBlank()) {
            UserIdGenerator generator = SpringContext.getBean(UserIdGenerator.class);
            String id = generator.generateNextId();
            user.setId(id);
        }
    }
}
