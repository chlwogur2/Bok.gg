package choi.bok.gg.domain.match.repository;

import choi.bok.gg.domain.match.entity.Match;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRepository extends JpaRepository<Match, Long> {

    Page<Match> findAll(Pageable pageable);
}
