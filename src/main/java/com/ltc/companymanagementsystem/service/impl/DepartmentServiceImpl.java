package com.ltc.companymanagementsystem.service.impl;


import com.ltc.companymanagementsystem.dto.request.DepartmentRequestDto;
import com.ltc.companymanagementsystem.dto.response.DepartmentResponseDto;
import com.ltc.companymanagementsystem.dto.response.EmployeeResponseDto;
import com.ltc.companymanagementsystem.entity.Department;
import com.ltc.companymanagementsystem.exception.DepartmentNotFoundException;
import com.ltc.companymanagementsystem.mapper.DepartmentMapper;
import com.ltc.companymanagementsystem.mapper.EmployeeMapper;
import com.ltc.companymanagementsystem.repo.DepartmentRepository;
import com.ltc.companymanagementsystem.service.DepartmentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository  departmentRepository;
    private final DepartmentMapper departmentMapper;
    private final EmployeeMapper employeeMapper;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository,
                                 DepartmentMapper departmentMapper,
                                 EmployeeMapper employeeMapper) {
        this.departmentRepository = departmentRepository;
        this.departmentMapper = departmentMapper;
        this.employeeMapper = employeeMapper;
    }



    @Override
    public DepartmentResponseDto create(DepartmentRequestDto departmentRequestDto) {

        Department department = departmentMapper.toEntity(departmentRequestDto);
//        Department saved = departmentRepository.save(department);

        return departmentMapper.toDto(departmentRepository.save(department));

        /*
        Entity
        id - 0 <- departmentRequestDto -> 1
        name - null <- departmentRequestDto -> Nurlan
        digerleri - null  <- departmentRequestDto -> email

       entity ->  save -> database -> dto

        id - 1 -> response
        name ; Nurlan ->
        digerleri [] -> response

         */

    }

    @Override
    public DepartmentResponseDto getById(Long id) {
        /*
        id : 1
        name : IT
        employee : null

        employeesDto [ id: 1
        fullName : Elmar
        salary : 2000
        departmentName : IT

         */
        Department department = departmentRepository.findById(id)
                .orElseThrow(()-> new DepartmentNotFoundException(
                        "Bele bir department yoxdur " + id
                ));

        DepartmentResponseDto departmentResponseDto = departmentMapper.toDto(department);

        departmentResponseDto.setEmployeesDto(department.getEmployees()
                .stream()
                .map(employeeMapper::toDto)
                .toList());

        return departmentResponseDto;

    }

    @Override
    public Page<DepartmentResponseDto> getAll(Pageable pageable) {

        return departmentRepository.findAll(pageable)
                .map(department -> {

                    DepartmentResponseDto departmentResponseDto = departmentMapper.toDto(department);

                    departmentResponseDto.setEmployeesDto(department.getEmployees()
                            .stream()
                            .map(employeeMapper::toDto)
                            .toList());
                    return departmentResponseDto;
                });
    }

    @Override
    public DepartmentResponseDto updateById(Long id, DepartmentRequestDto departmentRequestDto) {
        Department department = departmentRepository.findById(id).orElseThrow(()-> new DepartmentNotFoundException(
                "Bele bir department yoxdur " + id
        ));

        departmentMapper.updateFromDto(departmentRequestDto,department);

//        Department dp = departmentRepository.save(department);
//        return departmentMapper.toDto(dp);

        return departmentMapper.toDto(departmentRepository.save(department));
    }

    @Override
    public void deleteById(Long id) {
        if (!departmentRepository.existsById(id)) {
            throw new DepartmentNotFoundException(
                    "Bele bir department yoxdur " + id
            );
        }

        departmentRepository.deleteById(id);

    }
}
