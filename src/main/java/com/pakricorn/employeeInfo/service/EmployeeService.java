package com.pakricorn.employeeInfo.service;

import com.pakricorn.employeeInfo.entity.Employee;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface EmployeeService {

    Employee saveEmployee(Employee employee);

    List<Employee> getAllEmployees();

    Employee getEmployeeById(String id);

    Employee updateEmployee(String id, Employee employee) throws EmployeeNotFoundException;

    void deleteEmployee(String id);

     List<Employee> getDeletedEmployees();

    void saveEmp2Db(MultipartFile file);

}
