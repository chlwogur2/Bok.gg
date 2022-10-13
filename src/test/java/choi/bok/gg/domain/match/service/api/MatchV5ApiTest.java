package choi.bok.gg.domain.match.service.api;

import choi.bok.gg.domain.match.dto.MatchDto;
import choi.bok.gg.domain.match.service.MatchService;
import choi.bok.gg.domain.summoner.service.SummonerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

@SpringBootTest
class MatchV5ApiTest {

    @Autowired MatchService matchService;
    @Autowired SummonerService summonerService;

    @Test
    void matchV5ApiByPuuid() throws IOException {

        String puuid = summonerService.getPuuid("재 렉");

        List<String> matchIds = matchService.getMatchIds(puuid);

        for(String s : matchIds) System.out.println(s);

    }

    @Test
    void matchByMatchId() throws IOException{
        String puuid = summonerService.getPuuid("재 렉");
        List<String> matchIds = matchService.getMatchIds(puuid);
        MatchDto result = matchService.getMatch(matchIds.get(0));
        System.out.println(result);
    }
}