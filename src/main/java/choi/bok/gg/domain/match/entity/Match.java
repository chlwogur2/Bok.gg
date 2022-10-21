package choi.bok.gg.domain.match.entity;


import choi.bok.gg.domain.comment.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "matches")
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
// 매치와 댓글 사이의 관계가 필요하므로 Match 엔티티가 필요함
// 댓글은 그냥
public class Match {

    @Column(name = "match_id")
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "match_api_id")
    private String matchId;

    // 이거를 기준으로 DB에서 정렬
    @Column(name = "game_time")
    private Timestamp gameTime;

    @OneToMany(mappedBy = "match", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Comment> comments;
}

