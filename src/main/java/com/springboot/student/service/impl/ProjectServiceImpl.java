package com.springboot.student.service.impl;

import com.springboot.student.entity.Project;
import com.springboot.student.entity.Student;
import com.springboot.student.exception.ResourceNotFoundException;
import com.springboot.student.exception.StudentAPIException;
import com.springboot.student.payload.ProjectDto;
import com.springboot.student.repository.ProjectRepository;
import com.springboot.student.repository.StudentRepository;
import com.springboot.student.service.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {

    private ProjectRepository projectRepository;
    private StudentRepository studentRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository, StudentRepository studentRepository) {
        this.projectRepository = projectRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public ProjectDto createProject(String studentId, ProjectDto projectDto) {
        Project project = mapToEntity(projectDto);

        // retrieve student entity by id
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new ResourceNotFoundException("Student", "id", studentId));

        // set student to project entity
        project.setStudent(student);

        // save project entity to the database
        Project newProject = projectRepository.save(project);

        return mapToDTO(newProject);
    }

    @Override
    public List<ProjectDto> getProjectsByStudentId(String studentId) {

        // retrieve projects by studentId
        List<Project> projects = projectRepository.findByStudentId(studentId);

        // convert list of project entities to list of comment dto's
        return projects.stream().map(project -> mapToDTO(project)).collect(Collectors.toList());
    }

    @Override
    public ProjectDto getProjectById(String studentId, String projectId) {
        // retrieve student entity by id
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new ResourceNotFoundException("Student", "id", studentId));

        // retrieve project by id
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new ResourceNotFoundException("Project", "id", projectId));

        if(!project.getStudent().getId().equals(student.getId())){
            throw new StudentAPIException(HttpStatus.BAD_REQUEST, "Project does not belong to Student");
        }

        return mapToDTO(project);
    }

    private ProjectDto mapToDTO(Project project){
        ProjectDto projectDto = new ProjectDto();
        projectDto.setId(project.getId());
        projectDto.setName(project.getName());
        projectDto.setDuration(project.getDuration());

        return projectDto;

    }

    private Project mapToEntity(ProjectDto projectDto){
        Project project = new Project();
        project.setId(projectDto.getId());
        project.setName(projectDto.getName());
        project.setDuration(projectDto.getDuration());

        return project;
    }
}
