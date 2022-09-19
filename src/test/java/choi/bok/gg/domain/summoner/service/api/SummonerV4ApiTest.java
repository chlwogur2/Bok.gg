package choi.bok.gg.domain.summoner.service.api;

import choi.bok.gg.domain.summoner.dto.SummonerV4Dto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;

import java.io.IOException;

// 이거 빼면 objectMapper 가 지금 빈으로 등록되어 있어서 NPE 뜸
@SpringBootTest
class SummonerV4ApiTest {

    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    MessageSource messageSource;

    @Test
    void summonerV4ApiBySummonerName() throws IOException {
        SummonerV4Api summonerV4Api = new SummonerV4Api(messageSource,objectMapper);
        SummonerV4Dto result = summonerV4Api.summonerV4ApiBySummonerName("재 렉");
        Assertions.assertThat(result.getName()).isEqualTo("재 렉");
    }
}