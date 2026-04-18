package com.ltc.companymanagementsystem.mapper;

import com.ltc.companymanagementsystem.dto.request.DepartmentRequestDto;
import com.ltc.companymanagementsystem.dto.response.DepartmentResponseDto;
import com.ltc.companymanagementsystem.entity.Department;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {

    Department toEntity(DepartmentRequestDto departmentRequestDto);
    DepartmentResponseDto toDto(Department department);
    void updateFromDto(DepartmentRequestDto departmentRequestDto, @MappingTarget Department department);


    /*
    DepartmentResponseDto create (DepartmentReqeust dto){
    Department department = new Department;
    department.setName(dto.getName)

    Department dp = repo.save(department);

    DeparmtnetResponse ....res...  = new Resp ;
    res.setId(
    res.set(dp.getName)

    return res;

    DEpartment dp = dpmapper.toentity(dto)
    Department var = repo.save(dp)
    returnt dpmapper.toDto(var)


    }

    Department department = new Department ();
    department.set()

    Employe emp = new Employe ();
    emp.setId(1l,department)

    department.set(emp)

    database.save(department)



    Employee emp = new Employee();
    set.
    set.department()

    Department dep = new Department ();

    set.(1L,emp)




     */




}
