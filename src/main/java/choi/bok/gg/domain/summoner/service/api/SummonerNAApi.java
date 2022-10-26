package choi.bok.gg.domain.summoner.service.api;

import choi.bok.gg.domain.summoner.dto.SummonerDto;
import choi.bok.gg.domain.summoner.dto.SummonerTierDto;
import choi.bok.gg.global.api.SummonerApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

@Slf4j
@RequiredArgsConstructor
@Component
@Qualifier("SummonerNAApi")
public class SummonerNAApi implements SummonerApi{

    private final SummonerApiService summonerApiService;
    private final MessageSource messageSource;
    @Override
    public SummonerDto summonerDtoByAccountId(String accountId) throws IOException {
        String urlStr = messageSource.getMessage("summoner.by-accountid", new Object[]{accountId}, Locale.ENGLISH);
        return summonerApiService.getSummonerDto(urlStr);
    }

    @Override
    public SummonerDto summonerDtoBySummonerName(String summonerName) throws IOException {
        // 북미 서버는 소환사 이름 인코딩 필요 없음
        String urlStr = messageSource.getMessage("summoner.puuid.by-summoner-name", new Object[]{summonerName}, Locale.ENGLISH);
        return summonerApiService.getSummonerDto(urlStr);
    }

    @Override
    public List<SummonerTierDto> summonerTierByAccountId(String accountId) throws IOException {
        String urlStr = messageSource.getMessage("summoner.tier.by-accountid", new Object[]{accountId}, Locale.ENGLISH);
        return summonerApiService.getSummonerTierDto(urlStr);
    }

}
