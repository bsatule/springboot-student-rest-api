package com.springboot.student.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.student.entity.Student;
import com.springboot.student.payload.StudentDto;
import com.springboot.student.service.StudentService;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcResultHandlersDsl;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

@WebMvcTest
public class StudentControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    private void givenStudentObject_whenCreateEmployee_thenReturnSavedEmployee() throws Exception {

        // given - precondition or setup
        Student student = Student.builder()
                .id("1")
                .firstname("sharad")
                .lastname("atule")
                .mobile("7448883465")
                .email("sharadatule@gmail.com")
                .build();
        BDDMockito.given(studentService.createStudent(ArgumentMatchers.any(StudentDto.class)))
                .willAnswer((invocation) -> invocation.getArgument(0));

        // when - action or behaviour that we are going to test
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.post("/api/students")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(student)));

        // then - verify the result or output using assert statements
        response.andDo(MockMvcResultHandlers.print()).
                andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName",
                        CoreMatchers.is(student.getFirstname())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName",
                        CoreMatchers.is(student.getLastname())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email",
                        CoreMatchers.is(student.getEmail())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.mobile",
                        CoreMatchers.is(student.getMobile())));


    }

    // Junit test for Get All Students Rest API

   /* @Test
    public void givenListOfStudents_whenGetAllStudentsReturnStudentsList_then(){

        // given - precondition or setup
        List<Student> listOfStudents = new ArrayList<>();
        listOfStudents.add(Student.builder().id("1").firstname("sharad").lastname("atule").mobile("9422713663").email("sharadatule@gmail.com").build());
        listOfStudents.add(Student.builder().id("2").firstname("ramesh").lastname("fadtare").mobile("9403535945").email("rameshfadtare@gmail.com").build());
        BDDMockito.given(studentService.getAllStudents()).willReturn(listOfStudents);

        // when - action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(get("api/students"));

        // then - verify the output
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()",
                        CoreMatchers.is(listOfStudents.size())));
    } */
}
