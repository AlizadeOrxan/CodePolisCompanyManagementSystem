package com.ltc.companymanagementsystem.controller;

import com.ltc.companymanagementsystem.dto.request.ApiResponseDto;
import com.ltc.companymanagementsystem.dto.request.DepartmentRequestDto;
import com.ltc.companymanagementsystem.dto.request.EmployeeRequestDto;
import com.ltc.companymanagementsystem.dto.response.DepartmentResponseDto;
import com.ltc.companymanagementsystem.dto.response.EmployeeResponseDto;
import com.ltc.companymanagementsystem.service.impl.EmployeeServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employees")
@Tag(name = "Employee Controller " , description = "Department haqqinda CRUD yazilib")
public class EmployeeController {

    private final EmployeeServiceImpl employeeService;

    public EmployeeController(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/create")
    @Operation(summary = "Create emeliyyati icra olunacaq ")
    @ApiResponses(value = {
            @ApiResponse(description = "Server ishlemir " , responseCode = "999")

    })
    public ResponseEntity<ApiResponseDto<EmployeeResponseDto>> create(@Valid @RequestBody EmployeeRequestDto employeeRequestDto){

        EmployeeResponseDto employeeResponseDto = employeeService.create(employeeRequestDto);

        ApiResponseDto<EmployeeResponseDto> apiResponseDto =
                new ApiResponseDto<>("Datalar muveffeqiyyetle yaradildi",employeeResponseDto);

        return ResponseEntity.status(HttpStatus.OK).body(apiResponseDto);

    }

    @GetMapping("/getAll")
    public ResponseEntity<Page<EmployeeResponseDto>> getAll(@ParameterObject @PageableDefault(
            size = 10,
            page = 0 ,
            sort = "id" , direction = Sort.Direction.DESC
    ) Pageable pageable) {

        Page<EmployeeResponseDto> getAllDtos = employeeService.getAll(pageable);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(getAllDtos);

    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponseDto> getById (@PathVariable Long id) {
        EmployeeResponseDto employeeResponseDto = employeeService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(employeeResponseDto);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<EmployeeResponseDto> updateById( @PathVariable Long id ,@Valid @RequestBody EmployeeRequestDto employeeRequestDto) {
        EmployeeResponseDto employeeResponseDto = employeeService.updateById(id, employeeRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(employeeResponseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(Long id) {
        employeeService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }
}
