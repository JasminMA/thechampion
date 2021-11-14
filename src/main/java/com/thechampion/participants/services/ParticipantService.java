package com.thechampion.participants.services;

import com.thechampion.participants.models.Group;
import com.thechampion.participants.models.Participant;

import java.util.List;

public interface ParticipantService {
    Participant addParticipant(Participant participant);

    List<Participant> getAllParticipant();

    void groupParticipants(Integer numberOfGroups);
}
