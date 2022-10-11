package choi.bok.gg.domain.summoner.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class SummonerTierDto {

    private String puuid;
    private String summonerName;
    private String queueType;
    private String tier;
    private String rank;
    private String leaguePoints;
    private String wins;
    private String losses;
}
