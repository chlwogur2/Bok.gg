package choi.bok.gg.domain.likes.entity;

import choi.bok.gg.domain.comment.entity.Comment;
import choi.bok.gg.domain.account.entity.Account;

import javax.persistence.*;

@Entity
@Table(name = "likes")
public class Likes {

    @Id @GeneratedValue
    @Column(name = "likes_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Account account;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id")
    private Comment comment;

}
