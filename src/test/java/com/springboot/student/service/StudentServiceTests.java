package com.springboot.student.service;

import com.springboot.student.entity.Student;
import com.springboot.student.exception.ResourceNotFoundException;
import com.springboot.student.payload.StudentDto;
import com.springboot.student.repository.StudentRepository;
import com.springboot.student.service.impl.StudentServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static java.util.List.*;
import static org.hamcrest.Matchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import org.mockito.ArgumentMatchers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTests {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentServiceImpl studentService;

    private Student student;

    @BeforeEach
    public void setup() {
        //studentRepository = Mockito.mock(StudentRepository.class);
        //studentService = new StudentServiceImpl(studentRepository);
        Student student = Student.builder()
                .id("1")
                .firstname("sharad")
                .lastname("atule")
                .mobile("9422713662")
                .email("sharadatule@gmail.com")
                .build();
    }

    // JUnit test for saveStudent method
    @Test
    public void givenStudentObject_whenSaveStudent_thenReturnStudentObject(){

        // given - precondition or setup

        given(studentRepository.findById(student.getId()))
                .willReturn(Optional.empty());

        given(studentRepository.save(student))
                .willReturn(student);

        System.out.println(studentRepository);
        System.out.println(studentService);

        // when - action or the behaviour that we are going test
        StudentDto savedStudent = studentService.createStudent(new StudentDto());

        System.out.println(savedStudent);

        // then - verify the output
        Assertions.assertThat(savedStudent).isNotNull();
    }

    // JUnit test for saveStudent method which throws exception
    @Test
    public void givenExistingEmail_whenSaveStudent_thenThrowsException(){

        // given - precondition or setup

        given(studentRepository.findById(student.getId()))
                .willReturn(Optional.of(student));

       // given(studentRepository.save(student))
               // .willReturn(student);

        System.out.println(studentRepository);
        System.out.println(studentService);

        // when - action or the behaviour that we are going test
        org.junit.jupiter.api.Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            studentService.createStudent(new StudentDto());
        });
       // StudentDto savedStudent = studentService.createStudent(new StudentDto());

        //System.out.println(savedStudent);

        // then - verify the output
        //Assertions.assertThat(savedStudent).isNotNull();

        // then -
        verify(studentRepository, never()).save(ArgumentMatchers.any(Student.class));
    }

    // JUnit test for getAllStudents method
   /* @Test
    public void givenStudentsList_whenGetAllStudents_thenReturnStudentsList(){

        // given - precondition or setup

        Student student1 = Student.builder()
                .id("2")
                .firstname("ramesh")
                .lastname("fadtare")
                .mobile("9403535945")
                .email("rameshfadtare@gmail.com")
                .build();

        given(studentRepository.findAll()).willReturn(List.of(student, student1));

        // when - action or behaviour that we are going test
        List<StudentDto> studentList = studentService.getAllStudents();

        // then - verify the output
        Assertions.assertThat(studentList).isNotNull();
        Assertions.assertThat(studentList.size()).isEqualTo(2);
    } */

    // JUnit test for find the student by id
    @Test
    public void givenStudentId_whenGetStudentById_thenReturnStudentObject(){

        // given - precondition or setup
        given(studentRepository.findById("1")).willReturn(Optional.of(student));

        // when - action or behaviour that we are going to test
        Class<? extends StudentDto> savedStudent = studentService.getStudentById(student.getId()).getClass();

        // then - verify the output
        Assertions.assertThat(savedStudent).isNotNull();
    }
}
