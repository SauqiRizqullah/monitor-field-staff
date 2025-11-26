package com.nusantararegas.monitoring.controller;

import com.nusantararegas.monitoring.entity.Status;
import com.nusantararegas.monitoring.service.StatusService;
import com.nusantararegas.monitoring.dtos.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/statuses")
@RequiredArgsConstructor
public class StatusController {

    private final StatusService statusService;

    @PostMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<Status> createStatus(@RequestBody Status request) {
        Status createdStatus = statusService.createStatus(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdStatus);
    }

    @GetMapping(path = "/{statusId}", produces = "application/json")
    public ResponseEntity<Status> getStatusById(@PathVariable String statusId) {
        Status status = statusService.getById(statusId);
        return ResponseEntity.ok(status);
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<CommonResponse<List<Status>>> getAllStatuses() {
        List<Status> statuses = statusService.getAllStatuses();

        CommonResponse<List<Status>> response = CommonResponse.<List<Status>>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Success")
                .data(statuses)
                .build();

        return ResponseEntity.ok(response);
    }
}

