package com.springboot.student.repository;

import com.springboot.student.entity.Student;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
public class StudentRepositoryTests {

    @Autowired
    private StudentRepository studentRepository;

    // JUnit test for save student operation
    @Test
    public void givenStudentObject_whenSave_thenReturnSavedStudent(){
        // given - precondition or setup
        Student student = Student.builder()
                .id("1")
                .firstname("sharad")
                .lastname("atule")
                .mobile("8982317438")
                .email("sharadatule@gmail.com")
                .build();

        // when - action or the behaviour that we are going to test
        Student savedStudent = studentRepository.save(student);

        // then - verify the output
        Assertions.assertThat(savedStudent).isNotNull();
        Assertions.assertThat(savedStudent.getId()).isGreaterThan("0");

    }

    // JUnit test for get all students operation
    @Test
    public void givenStudentsList_whenFindAll_thenStudentsList(){

        // given - precondition or setup
        Student student = Student.builder()
                .id("1")
                .firstname("sharad")
                .lastname("atule")
                .mobile("8982317438")
                .email("sharadatule@gmail.com")
                .build();

        Student student1 = Student.builder()
                .id("2")
                .firstname("ramesh")
                .lastname("fadtare")
                .mobile("9078457687")
                .email("rameshfadtare@gmail.com")
                .build();

        studentRepository.save(student);
        studentRepository.save(student1);

        // when action or the behaviour that we are going test
        List<Student> studentList = studentRepository.findAll();

        // then - verify the output
        Assertions.assertThat(studentList).isNotNull();
        Assertions.assertThat(studentList.size()).isEqualTo(2);
    }

    // JUnit test for get student by id operation
    @Test
    public void givenStudentObject_whenFindById_thenReturnEmployeeObject(){

        // given precondition or setup
        Student student = Student.builder()
                .id("1")
                .firstname("sharad")
                .lastname("atule")
                .mobile("8982317438")
                .email("sharadatule@gmail.com")
                .build();
        studentRepository.save(student);

        // when - action or the behaviour that we are going test
        Student studentDB = studentRepository.findById(student.getId()).get();

        // then - verify the output
        Assertions.assertThat(studentDB).isNotNull();
    }
}
