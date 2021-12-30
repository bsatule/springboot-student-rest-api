package com.springboot.student.controller;

import com.springboot.student.payload.StudentDto;
import com.springboot.student.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    //@PreAuthorize("hasRole('ADMIN')")
    //@PreAuthorize("hasRole('USER')")
    // create student post
    @PostMapping
    public ResponseEntity<StudentDto> createStudent(@RequestBody StudentDto studentDto){
        return new ResponseEntity<>(studentService.createStudent(studentDto), HttpStatus.CREATED);

    }

    @PreAuthorize("hasRole('ADMIN')")
    // get all students rest api
    @GetMapping
    public List<StudentDto> getAllStudents(){
        return studentService.getAllStudents();

    }

    @PreAuthorize("hasRole('ADMIN')")
    // get student by id rest api
    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable (name = "id") String id){
        return ResponseEntity.ok(studentService.getStudentById(id));

    }
}
