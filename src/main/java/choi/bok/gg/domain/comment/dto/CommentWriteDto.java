package choi.bok.gg.domain.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommentWriteDto {

    private String summonerName;
    private String matchId;
    private String content;
    private int depth;
}
