package choi.bok.gg.domain.match.dto;

import lombok.Data;

import java.util.List;

@Data
public class MatchInfoDto {

    private long gameCreation;  // Unix timestamp for when the game is created on the game server (i.e., the loading screen).
    private long gameDuration;
    private long gameEndTimestamp;

    private long gameId;
    private String gameMode;
    private String gameName;
    private long gameStartTimestamp;
    private String gameType;
    private String gameVersion;
    private int mapId;
    private List<ParticipantDto> participants;
    private String platformId;
    private int queueId;
    private List<TeamDto> teams;
    private String tournamentCode;
}
