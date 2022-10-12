package choi.bok.gg.domain.player.entity;

import choi.bok.gg.domain.match.entity.Match;
import choi.bok.gg.domain.user.entity.User;

import javax.persistence.*;

@Entity
@Table(name = "player")
public class Player {

    @Id
    @Column(name = "player_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "match_id")
    private Match match;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
