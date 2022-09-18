package choi.bok.gg.domain.match.service;

import choi.bok.gg.domain.match.dto.MatchV5Dto;
import choi.bok.gg.domain.match.entity.Match;
import choi.bok.gg.domain.match.service.api.MatchV5Api;
import choi.bok.gg.domain.summoner.service.SummonerService;
import choi.bok.gg.domain.summoner.service.api.SummonerV4Api;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MatchService {
    private final MatchV5Api matchV5Api;

    public List<String> getMatchIds(String puuid) throws IOException {
        return matchV5Api.matchIdsByPuuid(puuid, 0, 10);
    }

    // 이거 리턴 값 어떻게 설정?
    public MatchV5Dto getMatch(String matchId) throws IOException {
        return matchV5Api.matchByMatchId(matchId);
    }



//    public Page<MatchResponseDto> findAllMatch() {
//
//        PageRequest pageRequest = PageRequest.of(0, 3);
//        return matchRepository.findAll(pageRequest).map(MatchResponseDto::from);
//    }
}
