package com.pakricorn.employeeInfo.service;


import com.pakricorn.employeeInfo.entity.Employee;
import com.pakricorn.employeeInfo.entity.Project;

import java.util.List;

public interface ProjectService {

    Project saveProject(Project project);

    List<Project> getAllProjects();

    Project getProjectById(Long id);

     Project updateProject(Long id, Project project) throws ProjectNotFoundException ;

    List<Project> getDeletedProjects();

     void deleteProject(Long id);

}
