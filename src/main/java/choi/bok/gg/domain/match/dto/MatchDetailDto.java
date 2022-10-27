package choi.bok.gg.domain.match.dto;

import choi.bok.gg.domain.account.dto.AccountLoginDto;
import choi.bok.gg.domain.match.dto.api.MatchDto;
import choi.bok.gg.domain.match.dto.api.ParticipantDto;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

// 매치 상세 페이지에 들어갈 정보 모음
@Data
@Builder
public class MatchDetailDto {

    private boolean win;
    private String championName;
    private String matchId;
    private LocalDateTime gameEndDateTime;

    private List<ParticipantDto> participants;
    private int kill;
    private int death;
    private int assist;
    private int kda;

}
