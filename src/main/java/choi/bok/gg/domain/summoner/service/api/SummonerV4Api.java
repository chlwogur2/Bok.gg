package choi.bok.gg.domain.summoner.service.api;

import choi.bok.gg.domain.summoner.dto.SummonerDto;
import choi.bok.gg.domain.summoner.dto.SummonerTierDto;
import choi.bok.gg.global.api.ApiService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class SummonerV4Api {

    private final ApiService apiService;
    private final MessageSource messageSource;
    // Api 키는 HTTP Authorization 헤더에 삽입 "X-Riot-Token"

    private final ObjectMapper objectMapper;
    

    public SummonerDto summonerDtoByAccountId(String accountId) throws IOException{
        String urlStr = messageSource.getMessage("summoner.by-accountid", new Object[]{accountId}, null);
        HttpURLConnection urlConnection = apiService.makeConnection(urlStr);
        if (urlConnection.getResponseCode() == 404) {
            return null;
        }

        SummonerDto summonerDto = objectMapper.readValue(urlConnection.getInputStream(), SummonerDto.class);

        urlConnection.disconnect();
        log.info("받아온 정보 = {}", summonerDto);
        return summonerDto;

    }

    public SummonerDto summonerDtoBySummonerName(String summonerName) throws IOException {

        // 한글이 깨지는 문제로 인해 소환사 이름을 인코딩을 해야함
        log.info("인코딩 전 소환사 이름: {}", summonerName);
        String encoded = URLEncoder.encode(summonerName, StandardCharsets.UTF_8);
        log.info("인코딩된 소환사 이름: {}", encoded);
        String urlStr = messageSource.getMessage("summoner.puuid.by-summoner-name", new Object[]{encoded}, null);
        log.info("요청 url: {}", urlStr);
        HttpURLConnection urlConnection = apiService.makeConnection(urlStr);

        log.info("SummonerV4Api Response Code = {}", urlConnection.getResponseCode());
        
        if (urlConnection.getResponseCode() == 404) {
            return null;
        }

        /**
         *  여기서부터 응답값 읽어오기
         */
        SummonerDto summonerDto = objectMapper.readValue(urlConnection.getInputStream(), SummonerDto.class);

        // 연결 해제
        urlConnection.disconnect();
        log.info("받아온 정보 = {}", summonerDto);
        return summonerDto;
    }

    public List<SummonerTierDto> summonerTierByPuuid(String summonerId) throws IOException {
        String urlStr = messageSource.getMessage("summoner.tier.by-accountid", new Object[]{summonerId}, null);
        HttpURLConnection urlConnection = apiService.makeConnection(urlStr);
        List<SummonerTierDto> summonerTierDto = objectMapper.readValue(urlConnection.getInputStream(), new TypeReference<>() {});
        urlConnection.disconnect();
        return summonerTierDto;
    }
}
