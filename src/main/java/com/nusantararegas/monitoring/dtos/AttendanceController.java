package com.nusantararegas.monitoring.dtos;

import com.nusantararegas.monitoring.service.AttendanceService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/attendances")
public class AttendanceController {

    private final AttendanceService attendanceService;

    @PostMapping(produces = "application/json")
    public ResponseEntity<AttendanceResponse> createAttendance(@RequestBody AttendanceRequest attendanceRequest) {
        AttendanceResponse attendance = attendanceService.createAttendance(attendanceRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(attendance);
    };

    @GetMapping(produces = "application/json")
    public List<AttendanceResponse> getAllAttendances() {
        return attendanceService.getAllAttendances();
    }

    @PutMapping(produces = "application/json", path = "/{attendanceId}")
    public String updateAttendance(@PathVariable String attendanceId, @RequestBody AttendanceRequest attendanceRequest) {
        attendanceService.updateAttendanceStatus(attendanceId, attendanceRequest);

        return "Attendance updated successfully";
    }

    @DeleteMapping(produces = "application/json", path = "/{attendanceId}")
    public String deleteAttendance(@PathVariable String attendanceId) {
        attendanceService.deleteAttendance(attendanceId);
        return "Attendance deleted successfully";
    }

}
