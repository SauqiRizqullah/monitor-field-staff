package com.nusantararegas.monitoring.service;

import com.nusantararegas.monitoring.entity.Status;
import com.nusantararegas.monitoring.repository.StatusRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class StatusService {

    private final StatusRepository statusRepository;

    public long countStatuses() {
        return statusRepository.count();
    }

    @Transactional(rollbackFor = Exception.class)
    public Status createStatus(Status request) {
        log.info("Creating new status...");
        // ID will be set by the EntityListener if missing
        Status saved = statusRepository.save(request);
        log.info("Status created with id={}", saved.getStatusId());
        return saved;
    }

    @Transactional(readOnly = true)
    public Status getById(String id) {
        return statusRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Status ID not found"));
    }

    @Transactional(readOnly = true)
    public List<Status> getAllStatuses() {
        log.info("Fetching all statuses...");
        List<Status> status = new ArrayList<>();
        statusRepository.findAll().forEach(status::add);
        return status;
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteStatus(String id) {
        statusRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Status ID not found"));
        statusRepository.deleteById(id);
        log.info("Deleted status id={}", id);
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateStatus(String id, Status request) {
        Status existing = statusRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Status ID not found"));
        existing.setStatusName(request.getStatusName());
        statusRepository.save(existing);
        log.info("Updated status id={}", id);
    }
}
