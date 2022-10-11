package choi.bok.gg.domain.summoner.service.api;

import choi.bok.gg.domain.summoner.dto.SummonerDto;
import choi.bok.gg.domain.summoner.dto.SummonerTierDto;
import choi.bok.gg.global.api.ApiService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;

import java.io.IOException;
import java.util.List;

// 이거 빼면 objectMapper 가 지금 빈으로 등록되어 있어서 NPE 뜸
@SpringBootTest
class SummonerV4ApiTest {

    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    MessageSource messageSource;
    @Autowired
    ApiService apiService;
    @Autowired
    SummonerV4Api summonerV4Api = new SummonerV4Api(apiService, messageSource, objectMapper);

    @Test
    void summonerV4DtoDySummonerName() throws IOException {
        SummonerDto summonerDto = summonerV4Api.summonerDtoBySummonerName("재 렉");
        Assertions.assertThat(summonerDto.getName()).isEqualTo("재 렉");
    }

    @Test
    void 없는소환사이름() throws IOException{
        Assertions.assertThat(summonerV4Api.summonerDtoBySummonerName("dkanrjskdlqfur")).isNull();
    }

    @Test
    void 그님티() throws IOException{
        SummonerDto summonerDto = summonerV4Api.summonerDtoBySummonerName("재 렉");
        List<SummonerTierDto> summonerTierDtos = summonerV4Api.summonerTierByPuuid(summonerDto.getId());
        Assertions.assertThat(summonerTierDtos.get(1).getTier()).isEqualTo("SILVER");
    }
}