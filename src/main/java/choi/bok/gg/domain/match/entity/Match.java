package choi.bok.gg.domain.match.entity;

import choi.bok.gg.domain.user.entity.User;

import javax.persistence.*;

@Entity
@Table(name = "match")
public class Match {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "match_id")
    private Long id;

    // 내 사이트에 가입된 유저만 매치 히스토리 확인 가능
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}

