package com.thechampion.participants.services;

import com.thechampion.matches.services.MatchService;
import com.thechampion.participants.exceptions.InvalidGroupNumber;
import com.thechampion.participants.exceptions.InvalidParticipantsNumber;
import com.thechampion.participants.models.Group;
import com.thechampion.participants.models.Participant;
import com.thechampion.participants.repositories.GroupRepository;
import com.thechampion.participants.repositories.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParticipantServiceImpl implements ParticipantService {

    private ParticipantRepository participantRepository;
    private GroupRepository groupRepository;
    private MatchService matchService;

    @Autowired
    public ParticipantServiceImpl(ParticipantRepository participantRepository,
                                  GroupRepository groupRepository,
                                  MatchService matchService) {
        this.participantRepository = participantRepository;
        this.groupRepository = groupRepository;
        this.matchService = matchService;
    }

    @Override
    public Participant addParticipant(Participant participant) {
        //TODO  check if already exist
        return participantRepository.save(participant);
    }

    @Override
    public List<Participant> getAllParticipant() {
        return participantRepository.findAll();
    }

    @Override
    public void groupParticipants(Integer numberOfGroups) {

        List<Participant> allParticipant = getAllParticipant();

        validateGroupNumber(allParticipant, numberOfGroups);

        assignParticipantsToGroups(numberOfGroups, allParticipant);
    }

    private void validateGroupNumber(List<Participant> allParticipant, Integer numberOfGroups) {
        if (allParticipant != null && allParticipant.size() > numberOfGroups) {
            throw new InvalidGroupNumber("Number of groups greater than number of participants");
        }
        if (allParticipant.size() % numberOfGroups != 0) {
            throw new InvalidGroupNumber("All participants can't be divided equally to the given group number");
        }

        if (allParticipant.size() % 4 != 0) {
            int remaining = 4 - allParticipant.size() % 4;
            throw new InvalidParticipantsNumber("Invalid Participants number please make sure to " +
                    "add " + remaining + " Participant");
        }
    }

    private void assignParticipantsToGroups(Integer numberOfGroups, List<Participant> allParticipant) {
        for (int counter = 1; counter <= numberOfGroups; counter++) {
            Group group = createGroup(counter);
            for (int pCounter = counter; pCounter < counter * numberOfGroups; pCounter++) {
                Participant participant = allParticipant.get(pCounter - 1);
                participant.setGroup(group);
            }
        }
        participantRepository.saveAll(allParticipant);
    }

    private Group createGroup(int counter) {
        Group group = new Group("Group_Number" + counter);
        groupRepository.save(group);
        return group;
    }
}
