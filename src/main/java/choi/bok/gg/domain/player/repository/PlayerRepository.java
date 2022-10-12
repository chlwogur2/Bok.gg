package choi.bok.gg.domain.player.repository;

import choi.bok.gg.domain.player.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player,Long> {
}
