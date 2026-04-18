package com.ltc.companymanagementsystem.controller;

import com.ltc.companymanagementsystem.dto.request.ApiResponseDto;
import com.ltc.companymanagementsystem.dto.request.DepartmentRequestDto;
import com.ltc.companymanagementsystem.dto.response.DepartmentResponseDto;
import com.ltc.companymanagementsystem.service.impl.DepartmentServiceImpl;
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
@RequestMapping(path = "/departments")
@Tag(name = "Department controller",description = "Department haqqinda olan datalarin CRUD controlleri")
public class DepartmentController {

    private final DepartmentServiceImpl departmentService;

    public DepartmentController(DepartmentServiceImpl departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping("/create")
    @Operation(summary = "Create emeliyyati icra olunacaq ")
    @ApiResponses(value = {
            @ApiResponse(description = "Server ishlemir " , responseCode = "999")

    })
    public ResponseEntity<ApiResponseDto<DepartmentResponseDto>> create(@Valid @RequestBody DepartmentRequestDto departmentRequestDto){

        DepartmentResponseDto departmentResponseDto =
                departmentService.create(departmentRequestDto);

        ApiResponseDto<DepartmentResponseDto> apiResponseDto =
                new ApiResponseDto<>("Datalar muveffeqiyyetle yaradildi",departmentResponseDto);

        return ResponseEntity.status(HttpStatus.OK).body(apiResponseDto);

    }

    @GetMapping("/getAll")
    public ResponseEntity<Page<DepartmentResponseDto>> getAll(@ParameterObject @PageableDefault(
            size = 10,
            page = 0 ,
            sort = "id" , direction = Sort.Direction.DESC
    ) Pageable pageable) {

        Page<DepartmentResponseDto> getAllDtos = departmentService.getAll(pageable);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(getAllDtos);

    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentResponseDto> getById (@PathVariable Long id) {
        DepartmentResponseDto departmentResponseDto = departmentService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(departmentResponseDto);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<DepartmentResponseDto> updateById( @PathVariable Long id ,@Valid @RequestBody DepartmentRequestDto departmentRequestDto) {
        DepartmentResponseDto departmentResponseDto = departmentService.updateById(id, departmentRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(departmentResponseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(Long id) {
        departmentService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }
}
