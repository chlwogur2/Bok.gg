package choi.bok.gg.domain.summoner.service.api;

import choi.bok.gg.domain.summoner.dto.SummonerDto;
import choi.bok.gg.domain.summoner.dto.SummonerTierDto;
import choi.bok.gg.global.api.SummonerApiService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;

import java.io.IOException;
import java.util.List;

// 이거 빼면 objectMapper 가 지금 빈으로 등록되어 있어서 NPE 뜸
@SpringBootTest
class SummonerKrApiTest {

    @Autowired
    MessageSource messageSource;
    @Autowired
    SummonerApiService summonerApiService;
    @Autowired
    SummonerKrApi summonerKrApi = new SummonerKrApi(summonerApiService, messageSource);

    @Test
    void summonerV4DtoDySummonerName() throws IOException {
        SummonerDto summonerDto = summonerKrApi.summonerDtoBySummonerName("재 렉");
        System.out.println(summonerDto.toString());
        Assertions.assertThat(summonerDto.getName()).isEqualTo("재 렉");
    }

    @Test
    void 없는소환사이름() throws IOException{
        Assertions.assertThat(summonerKrApi.summonerDtoBySummonerName("dkanrjskdlqfur")).isNull();
    }

    @Test
    void 그님티() throws IOException{
        SummonerDto summonerDto = summonerKrApi.summonerDtoBySummonerName("재 렉");
        List<SummonerTierDto> summonerTierDtos = summonerKrApi.summonerTierByAccountId(summonerDto.getId());
        Assertions.assertThat(summonerTierDtos.get(1).getTier()).isEqualTo("SILVER");
    }
}