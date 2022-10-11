package choi.bok.gg.domain.match.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class MatchInfoDto {

    private long gameCreation;  // Unix timestamp for when the game is created on the game server (i.e., the loading screen).
    private long gameDuration;
    private long gameEndTimestamp;
    private long gameId;
    private String gameMode;
    private long gameStartTimestamp;
    private String gameType;
    private String gameVersion;
    private int mapId;
    private List<ParticipantDto> participants;  // 얘가 제일 긺
    private String platformId;  // KR
    private List<TeamDto> teams;    // 챔피언 뭐 픽했고, 뭐 벤했는지, 이겼는지 졌는데, 오브젝트

}
