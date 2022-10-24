package choi.bok.gg.domain.match.service;

import choi.bok.gg.domain.account.dto.AccountLoginDto;
import choi.bok.gg.domain.match.dto.api.MatchDto;
import choi.bok.gg.domain.match.dto.api.ParticipantDto;
import choi.bok.gg.domain.match.entity.Match;
import choi.bok.gg.domain.match.repository.MatchRepository;
import choi.bok.gg.domain.match.service.api.MatchKrApi;
import choi.bok.gg.global.exception.NoMatchResultsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MatchService {
    private final MatchKrApi matchKrApi;
    private final MatchRepository matchRepository;

    // Match ID 여러 개 가져오는 용도
    public List<String> getMatchIdsByPuuid(String puuid) throws IOException {
        return matchKrApi.matchIdsByPuuid(puuid, 0, 5);
    }

    // Match ID 하나 당 Match 정보 보여줌
    public MatchDto getMatchByMatchId(String matchId) throws IOException {
        return matchKrApi.matchByMatchId(matchId);
    }

    public void saveMatch(MatchDto matchDto) {
        String matchId = matchDto.getMatchMetadataDto().getMatchId();
        Timestamp matchTime = new Timestamp(matchDto.getMatchInfo().getGameEndTimestamp());
        matchRepository.save(Match.builder()
                .matchId(matchId)
                .gameTime(matchTime).build());
    }

    // 현재 로그인 세션의 플레이어가 매치를 이겼는지 졌는지
    public boolean isWin(AccountLoginDto accountLoginDto, MatchDto matchDto) {
        String summonerName = accountLoginDto.getSummonerName();
        for (ParticipantDto p : matchDto.getMatchInfo().getParticipants()){
            if (summonerName.equals(p.getSummonerName())){
                return p.isWin();
            }
        }
        throw new NoMatchResultsException("매치 기록이 없습니다.");
    }

    // 현재 로그인 세션의 플레이어가 플레이한 챔피언
    public String getChampionName(AccountLoginDto accountLoginDto, MatchDto matchDto) {
        String summonerName = accountLoginDto.getSummonerName();
        for (ParticipantDto p : matchDto.getMatchInfo().getParticipants()) {
            if (summonerName.equals(p.getSummonerName())) {
                return p.getChampionName();
            }
        }
        throw new NoMatchResultsException("매치 기록이 없습니다.");
    }
}
