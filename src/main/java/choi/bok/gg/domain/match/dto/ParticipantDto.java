package choi.bok.gg.domain.match.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

import java.util.Map;

/**
 * 공공 API 에서 받은 JSON 응답의 필드가 엄청 많을 때, 이것들을 전부 Object 매핑 해줘야 하나
 */

@Data   // 이걸 붙여줘야 인식을 한다
public class ParticipantDto {

    private int assists;
    // challenges 에서 가져온 애들
    private int kda; // 얘도
    private int deathsByEnemyChamps;
    // 여기까지

    private int champLevel;
    private int championId;
    private int championTransform;
    private String championName;
    private int consumablesPurchased;   // 영약 산 횟수
    private int damageDealtToObjectives;
    private int damageDealtToBuildings;
    private int damageDealtToTurrets;
    private int damageSelfMitigated;
    private int deaths;
    private int detectorWardsPlaced;
    private int doubleKills;
    private boolean eligibleForProgression;
    private boolean gameEndedInEarlySurrender;  // 조기 서렌
    private boolean gameEndedInSurrender;   // 서렌
    private int goldEarned;
    private int goldSpent;
    private String individualPosition;  // 포지션? 뭐임 이거
    private int item0;
    private int item1;
    private int item2;
    private int item3;
    private int item4;
    private int item5;
    private int item6;
    private int killingSprees;
    private int kills;
    private String lane;    // 챔피언 라인?
    private int largestKillingSpree;
    private int longestTimeSpentLiving;

    private int magicDamageDealt;
    private int magicDamageDealtToChampions;
    private int magicDamageTaken;

    private int neutralMinionsKilled;
    private int objectivesStolen;
    private int objectivesStolenAssists;
    private int participantId;

    private int pentaKills;

    private int physicalDamageDealt;
    private int physicalDamageDealtToChampions;
    private int physicalDamageTaken;

    private int profileIcon;

    private int quadraKills;

    private String puuid;

    private int sightWardsBoughtInGame;
    private int spell1Casts;
    private int spell2Casts;
    private int spell3Casts;
    private int spell4Casts;
    private int summoner1Casts;
    private int summoner1Id;
    private int summoner2Casts;
    private int summoner2Id;
    private String summonerId;
    private String summonerName;
    private String teamPosition;
    private int summonerLevel;

    private boolean teamEarlySurrendered;
    private int teamId;


    private int timePlayed;

    private int totalDamageDealt;
    private int totalDamageDealtToChampions;
    private int totalDamageShieldedOnTeammates;
    private int totalDamageTaken;

    private int totalHeal;
    private int totalHealsOnTeammates;

    private int totalMinionsKilled;

    private int totalTimeSpentDead;

    private int tripleKills;

    private int turretKills;


    private int visionScore;
    private int visionWardsBoughtInGame;
    private int wardsKilled;
    private int wardsPlaced;
    private boolean win;

    @SuppressWarnings("unchecked")
    @JsonProperty("challenges")
    private void unpackChallenges(Map<String, Integer> challenges){
        this.kda = challenges.get("kda");
        this.deathsByEnemyChamps = challenges.get("deathsByEnemyChamps");
    }
}
