package choi.bok.gg.domain.summoner.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;


@SpringBootTest
class SummonerServiceTest {

    @Autowired
    SummonerService summonerService;

    @Test
    void getPuuidByAccountId() {

    }

    @Test
    void getPuuidBySummonerName() throws IOException {
        System.out.println(summonerService.getPuuidBySummonerName("재 렉", "summonerKrApi"));
        Assertions.assertThat(summonerService.getPuuidBySummonerName("재 렉", "summonerNaApi")).isNull();
    }

    @Test
    void isSummoner() {
    }

    @Test
    void getSoloTier() {
    }
}