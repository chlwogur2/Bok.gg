package choi.bok.gg.domain.comment.dto;

import lombok.Data;

@Data
public class CommentEditDto {
    private Long commentId;
    private String content;
}
