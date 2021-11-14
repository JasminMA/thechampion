package com.thechampion.matches.services;

import com.thechampion.matches.models.Match;
import com.thechampion.participants.models.Participant;

import java.util.List;

public interface MatchService {
    List<Match> getMatchesPerRound(Integer roundNumber);

    void saveOrUpdateMatch(Match match);
}
