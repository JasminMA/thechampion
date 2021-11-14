package com.thechampion.matches.services;

import com.thechampion.matches.models.Match;
import com.thechampion.matches.repositories.MatchRepository;
import com.thechampion.participants.models.Participant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.plaf.PanelUI;
import java.util.List;

@Service
public class MatchServiceImpl implements MatchService {
    private MatchRepository matchRepository;


    @Autowired
    public MatchServiceImpl(MatchRepository matchRepository){
        this.matchRepository = matchRepository;
    }

    @Override
    public List<Match> getMatchesPerRound(Integer roundNumber){
        return matchRepository.findByRound(roundNumber);
    }

    @Override
    public void saveOrUpdateMatch(Match match){
        matchRepository.save(match);
    }

}
