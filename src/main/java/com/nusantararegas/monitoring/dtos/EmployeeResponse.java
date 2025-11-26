package com.nusantararegas.monitoring.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class EmployeeResponse {
    private String employeeId;
    private String fullName;
    private String jobTitle;
    private String phoneNumber;
}
