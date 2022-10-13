package choi.bok.gg.domain.comment.entity;

import choi.bok.gg.domain.match.entity.Match;
import choi.bok.gg.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Table(name = "comment")
@Builder
@NoArgsConstructor
@AllArgsConstructor
// id 높은 것부터 내림차순 정렬
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

    private String content;

    // 대댓글 기능
    @ColumnDefault("0")
    private int depth;
}
