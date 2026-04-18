package com.ltc.companymanagementsystem.service.impl;

import com.ltc.companymanagementsystem.dto.request.EmployeeRequestDto;
import com.ltc.companymanagementsystem.dto.response.EmployeeResponseDto;
import com.ltc.companymanagementsystem.entity.Department;
import com.ltc.companymanagementsystem.entity.Employee;
import com.ltc.companymanagementsystem.exception.DepartmentNotFoundException;
import com.ltc.companymanagementsystem.exception.EmployeeNotFoundException;
import com.ltc.companymanagementsystem.mapper.EmployeeMapper;
import com.ltc.companymanagementsystem.repo.DepartmentRepository;
import com.ltc.companymanagementsystem.repo.EmployeeRepository;
import com.ltc.companymanagementsystem.service.EmployeeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;
    private final DepartmentRepository departmentRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository,
                               EmployeeMapper employeeMapper,
                               DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public EmployeeResponseDto create(EmployeeRequestDto employeeRequestDto) {

        Department department = departmentRepository.
                findById(employeeRequestDto.getDepartmentId())
                .orElseThrow(()-> new DepartmentNotFoundException("Bele bir department yoxdur"));

        Employee employee = employeeMapper.toEntity(employeeRequestDto);
        employee.setDepartment(department);

//        Employee save = employeeRepository.save(employee);
//
//        return employeeMapper.toDto(save);

        return employeeMapper.toDto(employeeRepository.save(employee));


    }

    @Override
    public EmployeeResponseDto getById(Long id) {
//        if (!employeeRepository.existsById(id)){
//            throw new EmployeeNotFoundException("Bele bir employee yoxdur");
//        }

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(()-> new EmployeeNotFoundException("Bele bir employee yoxdur"));


        return employeeMapper.toDto(employee);

    }

    @Override
    public Page<EmployeeResponseDto> getAll(Pageable pageable) {
        return employeeRepository.findAll(pageable)
                .map(employeeMapper::toDto);
    }

    @Override
    public EmployeeResponseDto updateById(Long id, EmployeeRequestDto employeeRequestDto) {

        Employee employee = employeeRepository.findById(id).orElseThrow(()
                -> new EmployeeNotFoundException("Bele bir employee yoxdur"));

        Department department = departmentRepository.findById(employeeRequestDto.
                getDepartmentId()).orElseThrow(
                () -> new DepartmentNotFoundException("Bele bir department yoxdur")
        );

        employeeMapper.updateEntity(employeeRequestDto, employee);
        employee.setDepartment(department);

        return employeeMapper.toDto(employeeRepository.save(employee));
    }

    @Override
    public void deleteById(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new EmployeeNotFoundException("Bele bir employee yoxdur");
        }
        employeeRepository.deleteById(id);
    }

    /*
    {
    id : 1
    name : IT
    employee : [
    id : 1
    fullname : Vusal
    salary : 2000
    ,

    id : 2
    name : Finance
    employee :
    id : 2
    fullname : Murad
    salary : 3000
    ,id 3 : Elmar
    salary : 4000

    id : 3
    name : QA
    employees : [
    id 5 :sq]


    }
     */

}
