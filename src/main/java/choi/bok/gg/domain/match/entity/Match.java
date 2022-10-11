package choi.bok.gg.domain.match.entity;

import choi.bok.gg.domain.user.entity.User;

import javax.persistence.*;

@Entity
@Table(name = "match")
public class Match {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "match_id")
    private Long id;

    // DB에는 matchId만 저장하자
    @Column(name = "match_api_id")
    private String matchId;
}

