package choi.bok.gg.domain.comment.entity;

import choi.bok.gg.domain.likes.entity.Likes;
import choi.bok.gg.domain.match.entity.Match;
import choi.bok.gg.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "comment")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
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

    @OneToMany(mappedBy = "comment", cascade = CascadeType.REMOVE)
    private List<Likes> likes = new ArrayList<>();

    // 대댓글 기능
    @ColumnDefault("0")
    private int depth;
}
