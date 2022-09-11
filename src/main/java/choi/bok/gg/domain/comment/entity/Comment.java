package choi.bok.gg.domain.comment.entity;

import choi.bok.gg.domain.match.entity.Match;
import choi.bok.gg.domain.user.entity.User;

import javax.persistence.*;

@Entity
@Table(name = "comment")
public class Comment {

    @Id @GeneratedValue
    @Column(name = "comment_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "match_id")
    private Match match;
}
