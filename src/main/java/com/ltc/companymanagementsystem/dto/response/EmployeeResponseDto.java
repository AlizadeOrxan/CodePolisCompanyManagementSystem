package com.ltc.companymanagementsystem.dto.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonPropertyOrder({"id", "fullName", "salary" , "departmentName"})
public class EmployeeResponseDto {

    private Long id;

    private String fullName;

    private Double salary;

    private String departmentName;

}
