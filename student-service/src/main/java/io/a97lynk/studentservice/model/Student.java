package io.a97lynk.studentservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Student {

    private int id;

    private String name;

    private String hometown;

    private Date dateOfBirth;

}
