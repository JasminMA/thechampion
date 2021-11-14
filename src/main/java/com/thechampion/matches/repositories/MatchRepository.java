package com.thechampion.matches.repositories;

import com.thechampion.matches.models.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<Match,Long> {
    List<Match> findByRound(Integer roundNumber);
}
