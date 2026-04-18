package com.ltc.companymanagementsystem.service;

import com.ltc.companymanagementsystem.dto.request.DepartmentRequestDto;
import com.ltc.companymanagementsystem.dto.response.DepartmentResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DepartmentService {

    DepartmentResponseDto create (DepartmentRequestDto departmentRequestDto);

    DepartmentResponseDto getById(Long id);

    Page<DepartmentResponseDto> getAll(Pageable pageable);

    DepartmentResponseDto updateById(Long id, DepartmentRequestDto departmentResponseDto);

    void deleteById(Long id);
}
