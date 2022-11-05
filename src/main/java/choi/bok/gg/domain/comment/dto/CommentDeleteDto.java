package choi.bok.gg.domain.comment.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommentDeleteDto {

    private String matchId;
    private Long commentId;
}
