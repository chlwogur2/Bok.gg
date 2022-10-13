package choi.bok.gg.domain.comment.dto;

import lombok.Data;

@Data
public class CommentWriteDto {

    private Long userId;
    private Long matchId;
    private String content;
    private int depth;
}
