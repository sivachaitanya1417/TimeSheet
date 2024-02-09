package com.pakricorn.employeeInfo.controller;

import com.pakricorn.employeeInfo.entity.Employee;
import com.pakricorn.employeeInfo.entity.Project;
import com.pakricorn.employeeInfo.service.EmployeeNotFoundException;
import com.pakricorn.employeeInfo.service.ProjectNotFoundException;
import com.pakricorn.employeeInfo.service.ProjectService;
import com.pakricorn.employeeInfo.service.ProjectServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/project")
public class ProjectController {

    @Autowired
    private final ProjectService projectService;

    @PostMapping("/add")
    public ResponseEntity<Void> addProject(@RequestBody Project project) {
        projectService.saveProject(project);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/list")
    public List<Project> getAllProjects(){
        return projectService.getAllProjects();
    }

    @GetMapping("/projects/{id}")
    public ResponseEntity<Project > getProjectById(@PathVariable Long id) {
        Project project = projectService.getProjectById(id);
        if(project != null) {
            return ResponseEntity.ok(project);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updateProject(@PathVariable Long id, @RequestBody Project project) throws ProjectNotFoundException {
        projectService.updateProject(id, project);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
    }

    @GetMapping("/archive")
    public List<Project> getDeletedProjects() {
        return projectService.getDeletedProjects();
    }

}
