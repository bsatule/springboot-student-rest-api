package com.springboot.student.service.impl;

import com.springboot.student.entity.Student;
import com.springboot.student.exception.ResourceNotFoundException;
import com.springboot.student.payload.StudentDto;
import com.springboot.student.repository.StudentRepository;
import com.springboot.student.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService{

    private StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public StudentDto createStudent(StudentDto studentDto) {

        // convert DTO to Entity
        Student student = mapToEntity(studentDto);

        Student newStudent = studentRepository.save(student);

        // convert Entity to DTO
        StudentDto studentResponse = mapToDto(newStudent);

        return studentResponse;
    }

    @Override
    public List<StudentDto> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return students.stream().map(student -> mapToDto(student)).collect(Collectors.toList());
    }

    @Override
    public StudentDto getStudentById(String id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student", "id", id));
        return mapToDto(student);
    }

    // convert Entity to DTO
    private StudentDto mapToDto(Student student){
        StudentDto studentDto = new StudentDto();
        studentDto.setId(student.getId());
        studentDto.setFirstname(student.getFirstname());
        studentDto.setLastname(student.getLastname());
        studentDto.setId(student.getMobile());
        studentDto.setEmail(student.getEmail());

        return studentDto;
    }

    // convert DTO to Entity
    private Student mapToEntity(StudentDto studentDto){
        Student student = new Student();
        student.setId(studentDto.getId());
        student.setFirstname(studentDto.getFirstname());
        student.setLastname(studentDto.getLastname());
        student.setMobile(studentDto.getMobile());
        student.setEmail(studentDto.getEmail());

        return student;

    }
}
