package com.pakricorn.employeeInfo.repository;

import com.pakricorn.employeeInfo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface EmployeeRepository extends JpaRepository<Employee, String> {

    List<Employee> findByDeletedFalse();

    List<Employee> findByDeletedTrue();
}
