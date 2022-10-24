package choi.bok.gg.domain.match.dto;

import lombok.Data;

import java.time.LocalDateTime;

// View 에 들어갈 매치 정보 모음

@Data
public class MatchResultDto {

    private boolean win;
    private String championName;
    private LocalDateTime gameEndDateTime;
}
