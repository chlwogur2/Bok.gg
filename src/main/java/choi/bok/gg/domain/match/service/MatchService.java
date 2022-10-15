package choi.bok.gg.domain.match.service;

import choi.bok.gg.domain.match.dto.MatchDto;
import choi.bok.gg.domain.match.entity.Match;
import choi.bok.gg.domain.match.repository.MatchRepository;
import choi.bok.gg.domain.match.service.api.MatchV5Api;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MatchService {
    private final MatchV5Api matchV5Api;
    private final MatchRepository matchRepository;

    public List<String> getMatchIds(String puuid) throws IOException {
        return matchV5Api.matchIdsByPuuid(puuid, 0, 10);
    }

    public MatchDto getMatch(String matchId) throws IOException {
        return matchV5Api.matchByMatchId(matchId);
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
