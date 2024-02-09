package com.pakricorn.employeeInfo.service;

import com.pakricorn.employeeInfo.entity.Employee;
import com.pakricorn.employeeInfo.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private final EmployeeRepository employeeRepository;
    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findByDeletedFalse();
    }

    @Override
    public Employee getEmployeeById(String id) {
        return employeeRepository.findById(id).orElse(null);
    }





    @Override
    public Employee updateEmployee(String id, Employee updateEmp) throws EmployeeNotFoundException {
        Employee existingEmployee = getEmployeeById(id);
        if(existingEmployee != null){
            existingEmployee.setFirstname(updateEmp.getFirstname());
            existingEmployee.setLastname(updateEmp.getLastname());
            existingEmployee.setAddress(updateEmp.getAddress());
            existingEmployee.setMobileNumber(updateEmp.getMobileNumber());
            existingEmployee.setEmail(updateEmp.getEmail());
            existingEmployee.setAadhaar(updateEmp.getAadhaar());
            existingEmployee.setPanNumber(updateEmp.getPanNumber());
            existingEmployee.setProjectId(updateEmp.getProjectId());

          return employeeRepository.save(existingEmployee);
        }
        else {
            throw new EmployeeNotFoundException("employee not found with" + id);
        }
    }

    @Override
    public void deleteEmployee(String id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();
            employee.setDeleted(Boolean.TRUE);
            employeeRepository.save(employee);
        }
    }

    @Override
    public List<Employee> getDeletedEmployees() {
        return employeeRepository.findByDeletedTrue();
    }

    public void saveEmp2Db(MultipartFile file){

        if(ExcelUploadService.isValidExcelFile(file)){
            try {
                List<Employee> employees = ExcelUploadService.getEmpData(file.getInputStream());
                employeeRepository.saveAll(employees);
            } catch (IOException e) {
                throw new IllegalArgumentException("This File is not a valid Excel file");
            }
        }
    }

}
