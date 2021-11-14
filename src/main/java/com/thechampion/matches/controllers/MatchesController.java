package com.thechampion.matches.controllers;

import com.thechampion.common.Error;
import com.thechampion.common.WebConstants;
import com.thechampion.matches.exceptions.NoMatchesFound;
import com.thechampion.matches.models.Match;
import com.thechampion.matches.services.MatchService;
import com.thechampion.participants.exceptions.NoParticipantsFound;
import com.thechampion.participants.models.Group;
import com.thechampion.participants.models.Participant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/matches/")
public class MatchesController {

    private MatchService matchService;

    @Autowired
    public MatchesController(MatchService matchService) {
        this.matchService = matchService;
    }

    @PostMapping
    public void updateMatchWinner(@RequestBody Match match) {
        matchService.saveOrUpdateMatch(match);
    }

    @GetMapping
    public List<Match> getMatchesOfRound1() {
        List<Match> matchesPerRound = matchService.getMatchesPerRound(1);
        if (matchesPerRound == null || matchesPerRound.isEmpty())
            throw new NoMatchesFound("No Matches found for Round 1");
        return matchesPerRound;
    }

    @ExceptionHandler(NoParticipantsFound.class)
    public ResponseEntity<Error> handleNoMatchesFound(NoMatchesFound noMatchesFound) {
        Error error = new Error(noMatchesFound.getMessage(), 4);
        return new ResponseEntity<Error>(error, HttpStatus.NOT_FOUND);
    }

}
