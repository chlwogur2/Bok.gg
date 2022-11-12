package choi.bok.gg.global.controller;

import choi.bok.gg.domain.account.service.AccountService;
import choi.bok.gg.domain.match.dto.api.MatchDto;
import choi.bok.gg.domain.match.service.MatchService;
import choi.bok.gg.domain.summoner.service.SummonerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StopWatch;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class HomeControllerTest {
    @Autowired
    MatchService matchService;

    @Autowired
    SummonerService summonerService;

    @Autowired
    AccountService accountService;

    StopWatch stopWatch;
    String accountId;
    List<String> matchIds = new ArrayList<>();

    @BeforeEach
    void setUp() throws IOException {
        stopWatch = new StopWatch("API Loading time");
        accountId = accountService.findAccountIdByLoginId("qwer");
        matchIds.addAll(matchService.getMatchIdsByPuuid(summonerService.getPuuidByAccountId(accountId, "summonerKRApi")));
        stopWatch.start("동기 방식");
    }

    @Test
    void 동기방식() throws IOException {
        for (String ids : matchIds){
            MatchDto matchDto = matchService.getMatchDtoByMatchId(ids);
        }
        stopWatch.stop();
        System.out.println("걸린 시간: " + stopWatch.getTotalTimeMillis());
        System.out.println(stopWatch.prettyPrint());
    }

}