package choi.bok.gg.domain.match.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

// LoginHome 에 들어갈 매치 정보 모음
@Data
@Builder
public class MatchResultDto {

    private boolean win;
    private String championName;
    private int championId;
    private String matchId;
    private LocalDateTime gameEndDateTime;
    private int kill;
    private int death;
    private int assist;
    private int kda;
}
