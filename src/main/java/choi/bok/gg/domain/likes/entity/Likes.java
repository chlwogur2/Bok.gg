package choi.bok.gg.domain.likes.entity;

import choi.bok.gg.domain.comment.entity.Comment;
import choi.bok.gg.domain.match.entity.Match;
import choi.bok.gg.domain.user.entity.User;

import javax.persistence.*;

@Entity
@Table(name = "likes")
public class Likes {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "likes_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "match_id")
    private Match match;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id")
    private Comment comment;

}
