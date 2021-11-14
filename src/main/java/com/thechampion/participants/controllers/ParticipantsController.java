package com.thechampion.participants.controllers;

import com.thechampion.common.Error;
import com.thechampion.common.WebConstants;
import com.thechampion.participants.exceptions.InvalidGroupNumber;
import com.thechampion.participants.exceptions.InvalidParticipantsNumber;
import com.thechampion.participants.exceptions.NoParticipantsFound;
import com.thechampion.participants.models.Participant;
import com.thechampion.participants.services.ParticipantService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/participants/")
public class ParticipantsController {

    private ParticipantService participantService;

    @Autowired
    public ParticipantsController(ParticipantService participantService) {
        this.participantService = participantService;
    }

    /**
     * @param participant
     * @return saved Participant
     * @apiNote Submit a participant request
     */
    @PostMapping
    public Participant addParticipant(@RequestBody Participant participant) {
        return participantService.addParticipant(participant);
    }

    /**
     * @return List of Participant
     * @apiNote Get a list of all participants
     */
    @GetMapping
    public List<Participant> getAllParticipants() {
        List<Participant> allParticipant = participantService.getAllParticipant();
        if (allParticipant == null || allParticipant.isEmpty())
            throw new NoParticipantsFound("No participants found.");
        return allParticipant;
    }

    @PutMapping
    public void groupParticipants(@RequestParam(name = WebConstants.NUMBER_OF_GROUPS) int numberOfGroups) {
        participantService.groupParticipants(numberOfGroups);
    }

    @ExceptionHandler(NoParticipantsFound.class)
    public ResponseEntity<Error> handleNotFoundAppointments(NoParticipantsFound noAppointmentsFound) {
        Error error = new Error(noAppointmentsFound.getMessage(), 1);
        return new ResponseEntity<Error>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoParticipantsFound.class)
    public ResponseEntity<Error> handleInvalidNumberOfGroups(InvalidGroupNumber invalidGroupNumber) {
        Error error = new Error(invalidGroupNumber.getMessage(), 2);
        return new ResponseEntity<Error>(error, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(NoParticipantsFound.class)
    public ResponseEntity<Error> handleInvalidParticipantsNumber(InvalidParticipantsNumber invalidParticipantsNumber) {
        Error error = new Error(invalidParticipantsNumber.getMessage(), 3);
        return new ResponseEntity<Error>(error, HttpStatus.NOT_ACCEPTABLE);
    }


}
