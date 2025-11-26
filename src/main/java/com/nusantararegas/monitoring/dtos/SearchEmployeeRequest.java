package com.nusantararegas.monitoring.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchEmployeeRequest {
    private Integer page;
    private Integer size;

    private String sortBy;
    private String direction;

    private String fullName;
    private String jobTitle;
}
