package com.pakricorn.employeeInfo.controller;

import com.pakricorn.employeeInfo.entity.Employee;
import com.pakricorn.employeeInfo.service.EmployeeNotFoundException;
import com.pakricorn.employeeInfo.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/employee")
@AllArgsConstructor
public class EmployeeController {

    @Autowired
    private  final EmployeeService employeeService;

    @PostMapping("/add")
    public ResponseEntity<Void> addEmployee(@RequestBody Employee employee) {
        employeeService.saveEmployee(employee);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/list")
    public List<Employee> getAllEmployees(){
        return employeeService.getAllEmployees();
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee > getEmployeeById(@PathVariable String id) {
        Employee employee = employeeService.getEmployeeById(id);
        if(employee != null) {
            return ResponseEntity.ok(employee);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updateEmployee(@PathVariable String id, @RequestBody Employee employee) throws EmployeeNotFoundException {
        employeeService.updateEmployee(id, employee);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteEmployee(@PathVariable String id) {
        employeeService.deleteEmployee(id);
    }

    @GetMapping("/archive")
    public List<Employee> getDeletedEmployees() {
        return employeeService.getDeletedEmployees();
    }

    @PostMapping("/upload-emp-data")
    public ResponseEntity<?> uploadEmpData(@RequestParam("file") MultipartFile file){
        employeeService.saveEmp2Db(file);
        return  ResponseEntity.ok(Map.of("Message","employees data uploaded successfully"));
    }

}
