package com.thechampion.participants.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "PARTICIPANTS_GROUP")
public class Group {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Date creationDate = new Date();
    @OneToMany(mappedBy = "group")
    private List<Participant> participants;

    public Group(String name){
        this.name = name;
    }
}
