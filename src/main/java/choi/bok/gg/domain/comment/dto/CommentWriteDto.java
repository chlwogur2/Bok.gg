package choi.bok.gg.domain.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@Builder
public class CommentWriteDto {

    @NotNull
    private String summonerName;
    @NotNull
    private String matchId;
    @NotNull
    private String content;
    private int depth;
}
