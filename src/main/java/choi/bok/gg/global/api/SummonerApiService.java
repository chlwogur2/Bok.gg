package choi.bok.gg.global.api;

import choi.bok.gg.domain.summoner.dto.SummonerDto;
import choi.bok.gg.domain.summoner.dto.SummonerTierDto;
import choi.bok.gg.global.RiotApiKey;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SummonerApiService implements ApiService{

    private final ObjectMapper objectMapper;

    public SummonerDto getSummonerDto(String urlStr) throws IOException {
        log.info("요청 url: {}", urlStr);
        HttpURLConnection urlConnection = makeConnection(urlStr);
        log.info("SummonerApi Response Code = {}", urlConnection.getResponseCode());

        if (urlConnection.getResponseCode() == 404) {
            return null;
        }

        SummonerDto summonerDto = objectMapper.readValue(urlConnection.getInputStream(), SummonerDto.class);

        urlConnection.disconnect();
        log.info("받아온 정보 = {}", summonerDto);
        return summonerDto;
    }

    public List<SummonerTierDto> getSummonerTierDto(String urlStr) throws IOException{
        log.info("요청 url: {}", urlStr);
        HttpURLConnection urlConnection = makeConnection(urlStr);
        log.info("SummonerTierApi Response Code = {}", urlConnection.getResponseCode());
        List<SummonerTierDto> summonerTierDto = objectMapper.readValue(urlConnection.getInputStream(), new TypeReference<>() {});
        urlConnection.disconnect();
        log.info("받아온 정보 = {}", summonerTierDto.get(1).toString());
        return summonerTierDto;
    }

}
