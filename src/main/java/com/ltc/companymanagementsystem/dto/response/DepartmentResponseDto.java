package com.ltc.companymanagementsystem.dto.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DepartmentResponseDto {

    private Long id ;
    private String name ;
    private List<EmployeeResponseDto> employeesDto;

}
