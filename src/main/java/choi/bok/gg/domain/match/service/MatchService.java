package choi.bok.gg.domain.match.service;

import choi.bok.gg.domain.match.dto.MatchDto;
import choi.bok.gg.domain.match.entity.Match;
import choi.bok.gg.domain.match.repository.MatchRepository;
import choi.bok.gg.domain.match.service.api.MatchApi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MatchService {
    private final MatchApi matchApi;
    private final MatchRepository matchRepository;

    public List<String> getMatchIdsByPuuid(String puuid) throws IOException {
        return matchApi.matchIdsByPuuid(puuid, 0, 10);
    }

    public MatchDto getMatchByMatchId(String matchId) throws IOException {
        return matchApi.matchByMatchId(matchId);
    }

    public void saveMatch(MatchDto matchDto) {
        String matchId = matchDto.getMatchMetadataDto().getMatchId();
        Timestamp matchTime = new Timestamp(matchDto.getMatchInfo().getGameStartTimestamp());
        matchRepository.save(Match.builder()
                .matchId(matchId)
                .gameTime(matchTime).build());
    }

//    public Page<ResponseMatchDto> matchPaging(Pageable pageable) {
//        return matchRepository.matchPaging();
//    }

}
