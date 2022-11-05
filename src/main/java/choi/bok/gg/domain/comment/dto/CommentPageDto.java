package choi.bok.gg.domain.comment.dto;

import choi.bok.gg.domain.comment.entity.Comment;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommentPageDto {

    private Long commentId;
    private String summoner;
    private String content;
    public static CommentPageDto from(Comment comment) {
        return CommentPageDto.builder()
                .commentId(comment.getId())
                .content(comment.getContent())
                .summoner(comment.getAccount().getSummonerName())
                .build();
    }
}
