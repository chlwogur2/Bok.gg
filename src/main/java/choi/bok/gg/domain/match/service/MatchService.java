package choi.bok.gg.domain.match.service;

import choi.bok.gg.domain.account.dto.AccountLoginDto;
import choi.bok.gg.domain.match.dto.MatchResultDto;
import choi.bok.gg.domain.match.dto.api.MatchDto;
import choi.bok.gg.domain.match.dto.api.ParticipantDto;
import choi.bok.gg.domain.match.entity.Match;
import choi.bok.gg.domain.match.exception.NoMatchFoundException;
import choi.bok.gg.domain.match.repository.MatchRepository;
import choi.bok.gg.domain.match.service.api.MatchApi;
import choi.bok.gg.domain.match.exception.NoMatchResultsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.TimeZone;

@Service
@RequiredArgsConstructor
public class MatchService {
    private final MatchApi matchApi;
    private final MatchRepository matchRepository;


    // DB에서, MatchId로 매치 조회, 댓글 조회 기능에만 쓰일 듯 하다.
    public Match getMatchByMatchId(String matchId) throws NoMatchResultsException {
        //TODO 작성된 댓글이 없다는 로직을 댓글로 분리해야 할 것 같다.
        return matchRepository.findMatchByMatchId(matchId).orElseThrow(() -> new NoMatchFoundException("작성된 댓글이 없습니다."));
    }

    // API로 Match ID 여러 개 가져오는 용도
    public List<String> getMatchIdsByPuuid(String puuid) throws IOException {
        return matchApi.matchIdsByPuuid(puuid, 0, 10);
    }

    // API로 Match ID 하나 당 Match 정보 보여줌
    public MatchDto getMatchDtoByMatchId(String matchId) throws IOException {
        return matchApi.matchByMatchId(matchId);
    }

    // 유저가 댓글을 달은 매치만 저장
    public void saveMatch(MatchDto matchDto) {
        String matchId = matchDto.getMatchMetadataDto().getMatchId();
        matchRepository.save(Match.builder()
                .matchId(matchId).build());
    }

    public void saveMatch(String matchId) {
        matchRepository.save(Match.builder()
                .matchId(matchId).build());
    }

    // 현재 로그인 세션의 플레이어가 매치를 이겼는지 졌는지
    public MatchResultDto getMatchResult(AccountLoginDto accountLoginDto, MatchDto matchDto) {
        String summonerName = accountLoginDto.getSummonerName();
        for (ParticipantDto p : matchDto.getMatchInfo().getParticipants()){
            if (summonerName.equals(p.getSummonerName())){
                return MatchResultDto.builder()
                        .win(p.isWin())
                        .matchId(matchDto.getMatchMetadataDto().getMatchId())
                        .gameEndDateTime(getGameEndTime(matchDto))
                        .championName(p.getChampionName())
                        .championId(p.getChampionId())
                        .kda(p.getKda())
                        .kill(p.getKills())
                        .death(p.getDeaths())
                        .assist(p.getAssists()).build();
            }
        }
        throw new NoMatchResultsException("매치 기록이 없습니다.");
    }

    // 매치가 끝난 정보
    public LocalDateTime getGameEndTime(MatchDto matchDto) {
        long gameEndTimestamp = matchDto.getMatchInfo().getGameEndTimestamp();
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(gameEndTimestamp), TimeZone.getDefault().toZoneId());
    }
}
