package choi.bok.gg.domain.summoner.service;

import choi.bok.gg.domain.summoner.dto.SummonerV4Dto;
import choi.bok.gg.domain.summoner.service.api.SummonerV4Api;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class SummonerService {

    private final SummonerV4Api summonerV4Api;

    public String getPuuid(String summonerName) throws IOException {
        return summonerV4Api.summonerV4ApiBySummonerName(summonerName).getPuuid();
    }


}
