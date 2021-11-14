package com.thechampion.matches.models;

import com.thechampion.participants.models.Participant;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "TENNIS_MATCH")
public class Match {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne

    private Participant participant2;
    @ManyToOne
    private Participant participant1;
    private Date matchTime;
    @ManyToOne
    private Participant winner;
    private Integer round;
}
