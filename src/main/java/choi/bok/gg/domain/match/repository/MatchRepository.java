package choi.bok.gg.domain.match.repository;

import choi.bok.gg.domain.match.entity.Match;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MatchRepository extends JpaRepository<Match, Long> {

    @Query("select m from Match m")
    Page<Match> findMatchPage(Pageable pageable);

    Optional<Match> findMatchByMatchId(String MatchId);
}
