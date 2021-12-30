package com.springboot.student.repository;

import com.springboot.student.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, String> {
    List<Project> findByStudentId(String studentId);
}
