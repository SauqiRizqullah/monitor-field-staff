package com.nusantararegas.monitoring.service;

import com.nusantararegas.monitoring.dtos.AttendanceRequest;
import com.nusantararegas.monitoring.dtos.AttendanceResponse;
//import com.nusantararegas.monitoring.dtos.EmployeeStatusDto;
import com.nusantararegas.monitoring.dtos.OnDutyDto;
import com.nusantararegas.monitoring.entity.Attendance;
import com.nusantararegas.monitoring.entity.Employee;
import com.nusantararegas.monitoring.entity.Status;
import com.nusantararegas.monitoring.repository.AttendanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;

    private final EmployeeService employeeService;

    private final StatusService statusService;

    @Transactional(rollbackFor = Exception.class)
    public AttendanceResponse createAttendance(AttendanceRequest request) {
        // Implementation goes here
        Employee employee = employeeService.getById(request.getEmployeeId());
        Status status = statusService.getById(request.getStatusId());
        Attendance attendance = Attendance.builder()
                .employee(employee)
                .status(status)
                .date(new Date())
                .note(request.getNote())
                .build();

        attendanceRepository.save(attendance);
        return AttendanceResponse.builder()
                .attendanceId(attendance.getAttendanceId())
                .employeeName(attendance.getEmployee().getFullName())
                .status(attendance.getStatus().getStatusName())
                .date(String.valueOf(attendance.getDate()))
                .note(attendance.getNote())
                .timestamp(String.valueOf(attendance.getCreatedAt()))
                .build();
    }

    @Transactional(readOnly = true)
    public List<AttendanceResponse> getAllAttendances() {
        // Implementation goes here
        List<Attendance> attendances = new ArrayList<>();

        attendanceRepository.findAll().forEach(attendances::add);


        return attendances.stream().map(attendance -> {
            return AttendanceResponse.builder()
                    .attendanceId(attendance.getAttendanceId())
                    .employeeName(attendance.getEmployee().getFullName())
                    .status(attendance.getStatus().getStatusName())
                    .date(attendance.getDate().toString())
                    .note(attendance.getNote())
                    .timestamp(attendance.getCreatedAt().toString())
                    .build();
        }).toList();
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateAttendanceStatus(String attendanceId, AttendanceRequest attendanceRequest) {
        // Implementation goes here
        Attendance attendance = attendanceRepository.findById(attendanceId)
                .orElseThrow(() -> new RuntimeException("Attendance not found with id: " + attendanceId));

        attendance.setNote(attendanceRequest.getNote());

        attendanceRepository.save(attendance);
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteAttendance(String attendanceId) {
        // Implementation goes here
        attendanceRepository.deleteById(attendanceId);
    }

    @Transactional(readOnly = true)
    public List<OnDutyDto> getCurrentOnDuty() {
        List<Attendance> records = attendanceRepository.findLatestOnDuty();

        return records.stream()
                .map(r -> new OnDutyDto(
                        r.getEmployee().getEmployeeId(),
                        r.getEmployee().getFullName(),
                        r.getEmployee().getJobTitle(),
                        r.getNote()
                ))
                .toList();
    }

//    @Transactional(readOnly = true)
//    public List<EmployeeStatusDto> getAllEmployeeStatus() {
//
//        List<Attendance> latest = attendanceRepository.findLatestAttendancePerEmployee();
//
//        return latest.stream()
//                .map(a -> new EmployeeStatusDto(
//                        a.getEmployee().getEmployeeId(),
//                        a.getEmployee().getFullName(),
//                        a.getStatus().getStatusName(),
//                        a.getNote()
//                ))
//                .toList();
//    }
}
