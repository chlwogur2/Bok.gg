package choi.bok.gg.domain.summoner.service;

import choi.bok.gg.domain.summoner.dto.TierRank;
import choi.bok.gg.domain.summoner.service.api.SummonerApi;
import choi.bok.gg.domain.summoner.service.api.SummonerKrApi;
import choi.bok.gg.global.api.ApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class SummonerService {


    private final Map<String, SummonerApi> apiMap;

    public String getPuuidByAccountId(String accountID, String locale) throws IOException {
        log.info("소환사의 puuid: {} ", apiMap.get(locale).summonerDtoByAccountId(accountID).getPuuid());
        return apiMap.get(locale).summonerDtoByAccountId(accountID).getPuuid();
    }

    public String getPuuidBySummonerName(String summonerName, String locale) throws IOException {
        log.info("소환사의 puuid: {} ", apiMap.get(locale).summonerDtoBySummonerName(summonerName).getPuuid());
        return apiMap.get(locale).summonerDtoBySummonerName(summonerName).getPuuid();
    }


    // 롤 계정 정보 있는지 검사
    public boolean isSummoner(String summonerName, String locale) throws IOException{
        if (apiMap.get(locale).summonerDtoBySummonerName(summonerName) == null) {
            return false;
        } else return true;
    }

    public TierRank getSoloTier(String accountId, String locale) throws IOException{
        String tier =  apiMap.get(locale).summonerTierByAccountId(accountId).get(1).getTier();
        String rank =  apiMap.get(locale).summonerTierByAccountId(accountId).get(1).getRank();
        return new TierRank(tier, rank);
    }
}
