package choi.bok.gg.domain.match.service;

import choi.bok.gg.domain.match.service.api.MatchV5Api;
import choi.bok.gg.domain.summoner.service.api.SummonerV4Api;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MatchService {

    private final SummonerV4Api summonerV4Api;
    private final MatchV5Api matchV5Api;

//    public Page<MatchResponseDto> findAllMatch() {
//
//        PageRequest pageRequest = PageRequest.of(0, 3);
//        return matchRepository.findAll(pageRequest).map(MatchResponseDto::from);
//    }
}
