package com.thechampion.participants.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
public class Participant {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @Column(unique = true)
    private String nationalID;
    private String address;
    private String mobileNumber;
    private Integer age;
    private Date creationDate = new Date();
    @ManyToOne
    private Group group;
}
