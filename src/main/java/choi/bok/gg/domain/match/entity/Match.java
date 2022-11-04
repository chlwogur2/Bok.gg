package choi.bok.gg.domain.match.entity;


import choi.bok.gg.domain.comment.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.ArrayList;
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
    @Id @GeneratedValue
    private Long id;

    @NotNull
    @Column(name = "match_api_id")
    private String matchId;

    @OneToMany(mappedBy = "match", cascade = CascadeType.REMOVE)    
    private List<Comment> comments = new ArrayList<>();
    
    // 만약 0이 되면, DB에서 매치 엔티티 삭제
    @ColumnDefault("0")
    @Column(name = "match_comment_count")
    private int commentCount;

    public void increaseCommentCount() {
        this.commentCount++;
    }

    public void decreaseCommentCount() {
        this.commentCount--;
    }
}

