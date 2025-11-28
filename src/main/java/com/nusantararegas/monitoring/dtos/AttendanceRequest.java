package com.nusantararegas.monitoring.dtos;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AttendanceRequest {
    private String employeeId;
    private String statusId;
    private String note;
}
