package choi.bok.gg.domain.summoner.service.api;

import choi.bok.gg.domain.summoner.dto.SummonerDto;
import choi.bok.gg.domain.summoner.dto.SummonerTierDto;
import choi.bok.gg.global.api.SummonerApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
@Primary
public class SummonerKRApi implements SummonerApi {

    private final SummonerApiService summonerApiService;
    private final MessageSource messageSource;

    @Override
    public SummonerDto summonerDtoByAccountId(String accountId) throws IOException{
        String urlStr = messageSource.getMessage("summoner.by-accountid", new Object[]{accountId}, null);
        return summonerApiService.getSummonerDto(urlStr);
    }
    @Override
    public SummonerDto summonerDtoBySummonerName(String summonerName) throws IOException {
        // 한글이 깨지는 문제로 인해 소환사 이름을 인코딩을 해야함
        log.info("인코딩 전 소환사 이름: {}", summonerName);
        String encoded = URLEncoder.encode(summonerName, StandardCharsets.UTF_8);
        log.info("인코딩된 소환사 이름: {}", encoded);
        String urlStr = messageSource.getMessage("summoner.puuid.by-summoner-name", new Object[]{encoded}, null);
        return summonerApiService.getSummonerDto(urlStr);
    }

    @Override
    public List<SummonerTierDto> summonerTierByAccountId(String accountId) throws IOException {
        String urlStr = messageSource.getMessage("summoner.tier.by-accountid", new Object[]{accountId}, null);
        return summonerApiService.getSummonerTierDto(urlStr);
    }
}
