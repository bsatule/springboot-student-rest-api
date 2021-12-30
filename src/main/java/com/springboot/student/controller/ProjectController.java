package com.springboot.student.controller;

import com.springboot.student.payload.ProjectDto;
import com.springboot.student.service.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class ProjectController {

    private ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

        @PostMapping("/students/{studentId}/projects")
    public ResponseEntity<ProjectDto> createProject(@PathVariable(value = "studentId") String studentId,
                                                    @RequestBody ProjectDto projectDto){
        return new ResponseEntity<>(projectService.createProject(studentId, projectDto), HttpStatus.CREATED);

    }

    @GetMapping("/students/{studentId}/projects")
    public List<ProjectDto> getProjectsByStudentId(@PathVariable(value = "studentId") String studentId){
        return projectService.getProjectsByStudentId(studentId);
    }

    @GetMapping("/students/{studentId}/projects/{projectId}")
    public ResponseEntity<ProjectDto> getProjectById(@PathVariable(value = "studentId") String studentId, @PathVariable(value = "projectId") String projectId){

        ProjectDto projectDto = projectService.getProjectById(projectId, studentId);
        return new ResponseEntity<>(projectDto, HttpStatus.OK);

    }
}
