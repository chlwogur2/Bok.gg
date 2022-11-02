package choi.bok.gg.domain.comment.dto;

import choi.bok.gg.domain.comment.entity.Comment;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommentPageDto {

    // 소환사 이름은 또 따로 처리
    private String summoner;
    private String content;

    public static CommentPageDto from(Comment comment) {
        return CommentPageDto.builder()
                .content(comment.getContent())
                .summoner(comment.getAccount().getSummonerName())
                .build();
    }
}
