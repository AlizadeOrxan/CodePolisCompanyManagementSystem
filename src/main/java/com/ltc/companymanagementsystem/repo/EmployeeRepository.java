package com.ltc.companymanagementsystem.repo;

import com.ltc.companymanagementsystem.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {

//    @Query("SELECT e FROM Employee e WHERE e.salary > :salary" )
//    List<Employee> findBySalaryGreaterThan(@Param("salary") Double salary);


    @Query(value = "SELECT * FROM employees e  WHERE e.salary > :salary", nativeQuery = true)
    List<Employee> findBySalaryGreaterThan(@Param("salary") Double salary);





}
