package choi.bok.gg.domain.comment.dto;

import lombok.Data;

@Data
public class CommentDeleteDto {

    private String matchId;
    private Long commentId;
}
