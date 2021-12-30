package com.springboot.student.service;

import com.springboot.student.payload.ProjectDto;

import java.util.List;

public interface ProjectService {
    ProjectDto createProject(String studentId, ProjectDto projectDto);

    List<ProjectDto> getProjectsByStudentId(String studentId);

    ProjectDto getProjectById(String studentId, String projectId);
}
