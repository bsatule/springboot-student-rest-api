package com.springboot.student.payload;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;

@Data
public class ProjectDto {

    private String id;
    private String name;
    private String duration;
}
