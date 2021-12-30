package com.springboot.student.service;

import com.springboot.student.payload.StudentDto;

import java.util.List;

public interface StudentService {
    StudentDto createStudent(StudentDto studentDto);

    List<StudentDto> getAllStudents();

    StudentDto getStudentById(String id);
}
