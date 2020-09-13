package io.a97lynk.studentservice.model;

import lombok.*;

import java.util.Date;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Student {

    private int id;

    private String name;

    private String hometown;

    private Date dateOfBirth;

}
