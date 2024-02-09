package com.pakricorn.employeeInfo.repository;

import com.pakricorn.employeeInfo.entity.Employee;
import com.pakricorn.employeeInfo.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project,Long> {

    List<Project> findByDeletedFalse();

    List<Project> findByDeletedTrue();

    Project getProjectById(long id);



}
