package com.ltc.companymanagementsystem.mapper;

import com.ltc.companymanagementsystem.dto.request.EmployeeRequestDto;
import com.ltc.companymanagementsystem.dto.response.EmployeeResponseDto;
import com.ltc.companymanagementsystem.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {


    @Mapping(source = "departmentId" , target = "department.id")
    Employee toEntity(EmployeeRequestDto employeeRequestDto);



    @Mapping(source = "department.name" , target = "departmentName")
    EmployeeResponseDto toDto(Employee employee);

    @Mapping(target = "id" , ignore = true)
    @Mapping(target = "department", ignore = true)
    void updateEntity(EmployeeRequestDto employeeRequestDto, @MappingTarget Employee employee);

}
