package com.ltc.companymanagementsystem.service;

import com.ltc.companymanagementsystem.dto.request.EmployeeRequestDto;
import com.ltc.companymanagementsystem.dto.response.EmployeeResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EmployeeService {


    EmployeeResponseDto create (EmployeeRequestDto employeeRequestDto);

    EmployeeResponseDto getById(Long id);

    Page<EmployeeResponseDto> getAll(Pageable pageable);

    EmployeeResponseDto updateById(Long id, EmployeeRequestDto employeeRequestDto);

    void deleteById(Long id);


}
