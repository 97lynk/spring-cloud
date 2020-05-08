package io.a97lynk.courseservice.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter @Setter @NoArgsConstructor
@Entity
public class Course implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @Column(name = "startdate")
    private Date startDate;

    private double price;

    @Column(name = "noofmember")
    private int noOfMember;
    
    private boolean closed;
}
