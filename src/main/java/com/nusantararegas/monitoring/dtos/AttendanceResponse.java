package com.nusantararegas.monitoring.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class AttendanceResponse {
    private String attendanceId;

    private String employeeName;

    private String status;

    private String date;

    private String note;

    private String timestamp;
}
