package com.nusantararegas.monitoring.controller;

import com.nusantararegas.monitoring.dtos.AttendanceRequest;
import com.nusantararegas.monitoring.dtos.AttendanceResponse;
//import com.nusantararegas.monitoring.dtos.EmployeeStatusDto;
import com.nusantararegas.monitoring.dtos.OnDutyDto;
import com.nusantararegas.monitoring.service.AttendanceService;
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

    @GetMapping("/on-duty")
    public List<OnDutyDto> getOnDuty() {
        return attendanceService.getCurrentOnDuty();
    }

//    @GetMapping("/status/all")
//    public List<EmployeeStatusDto> getAllStatus() {
//        return attendanceService.getAllEmployeeStatus();
//    }

}
