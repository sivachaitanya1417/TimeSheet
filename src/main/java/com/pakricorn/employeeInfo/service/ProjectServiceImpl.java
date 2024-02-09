package com.pakricorn.employeeInfo.service;

import com.pakricorn.employeeInfo.entity.Employee;
import com.pakricorn.employeeInfo.entity.Project;
import com.pakricorn.employeeInfo.repository.ProjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProjectServiceImpl implements ProjectService{
    @Autowired
    private final ProjectRepository projectRepository;
    @Override
    public Project saveProject(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public List<Project> getAllProjects() {
        return projectRepository.findByDeletedFalse();
    }

    @Override
    public Project getProjectById(Long id) {
        return projectRepository.findById(id).orElse(null);
    }

    @Override
    public Project updateProject(Long id, Project updateProject) throws ProjectNotFoundException {
        Project existingProject = getProjectById(id);
        if(existingProject != null){
            existingProject.setProjectTitle(updateProject.getProjectTitle());
            existingProject.setProjectDescription(updateProject.getProjectDescription());
            existingProject.setTeamMember(updateProject.getTeamMember());
            existingProject.setSupervisor(updateProject.getSupervisor());

            return projectRepository.save(existingProject);
        }
        else {
            throw new ProjectNotFoundException("project not found with" + id);
        }

    }

    @Override
    public List<Project> getDeletedProjects() {
        return projectRepository.findByDeletedTrue();
    }

    @Override
    public void deleteProject(Long id) {
        Optional<Project> optionalProject = projectRepository.findById(id);
        if (optionalProject.isPresent()) {
            Project project = optionalProject.get();
            project.setDeleted(Boolean.TRUE);
            projectRepository.save(project);
        }
    }
}
