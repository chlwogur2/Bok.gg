package choi.bok.gg.domain.summoner.service;

import choi.bok.gg.domain.summoner.dto.TierRank;
import choi.bok.gg.domain.summoner.service.api.SummonerApi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Slf4j
public class SummonerService {

    private final SummonerApi summonerApi;

    public String getPuuidByAccountId(String accountID) throws IOException {
        log.info("소환사의 puuid: {} ", summonerApi.summonerDtoByAccountId(accountID).getPuuid());
        return summonerApi.summonerDtoByAccountId(accountID).getPuuid();
    }

    public String getPuuidBySummonerName(String summonerName) throws IOException {
        return summonerApi.summonerDtoBySummonerName(summonerName).getPuuid();
    }


    // 롤 계정 정보 있는지 검사
    public boolean isSummoner(String summonerName) throws IOException{
        if (summonerApi.summonerDtoBySummonerName(summonerName) == null) {
            return false;
        } else return true;
    }

    public TierRank getSoloTier(String summonerId) throws IOException{
        String tier =  summonerApi.summonerTierByPuuid(summonerId).get(1).getTier();
        String rank =  summonerApi.summonerTierByPuuid(summonerId).get(1).getRank();
        return new TierRank(tier, rank);
    }
}
