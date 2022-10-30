package choi.bok.gg.domain.match.service.api;

import choi.bok.gg.domain.match.dto.api.MatchDto;
import choi.bok.gg.domain.match.service.MatchService;
import choi.bok.gg.domain.summoner.service.SummonerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

@SpringBootTest
class MatchKrApiTest {

    @Autowired MatchService matchService;
    @Autowired SummonerService summonerService;

    @Test
    void matchV5ApiByPuuid() throws IOException {

        String puuid = summonerService.getPuuidBySummonerName("재 렉", "summonerKrApi");

        List<String> matchIds = matchService.getMatchIdsByPuuid(puuid);

        for(String s : matchIds) System.out.println(s);

    }

    @Test
    void matchByMatchId() throws IOException{
        String puuid = summonerService.getPuuidBySummonerName("재 렉", "summonerKrApi");
        List<String> matchIds = matchService.getMatchIdsByPuuid(puuid);
        MatchDto result = matchService.getMatchDtoByMatchId(matchIds.get(0));
        System.out.println(result);
    }
}