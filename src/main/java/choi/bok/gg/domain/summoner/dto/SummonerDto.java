package choi.bok.gg.domain.summoner.dto;

import lombok.Data;

/**
 * 굳이 Summoner를 Entity로서 DB에 저장할 필요가 없는 듯
 */
@Data
// Summoner-V4, /lol/summoner/v4/summoners/by-name/{summonerName}
public class SummonerDto {

    private String id;

    private String accountId;

    private String puuid;

    private String name;

    private int profileIconId;

    private long revisionDate;

    private long summonerLevel;
}
