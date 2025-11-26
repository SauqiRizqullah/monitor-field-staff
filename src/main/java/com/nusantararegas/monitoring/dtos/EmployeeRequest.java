package com.nusantararegas.monitoring.dtos;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeRequest {
    private String fullName;
    private String jobTitle;
    private String phoneNumber;
}
