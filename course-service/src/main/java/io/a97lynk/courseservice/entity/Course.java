package io.a97lynk.courseservice.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter @Setter @NoArgsConstructor
public class Course {

    private int id;

    private String name;

    private Date startDate;

    private double price;

    private int noOfMember;
}
