package com.springboot.student.payload;


import lombok.Data;

@Data
public class StudentDto {
    private String id;
    private String firstname;
    private String lastname;
    private String mobile;
    private String email;
}
