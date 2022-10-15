package choi.bok.gg.domain.summoner.service;

import choi.bok.gg.domain.summoner.dto.TierRank;
import choi.bok.gg.domain.summoner.service.api.SummonerV4Api;
import choi.bok.gg.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Slf4j
public class SummonerService {

    private final SummonerV4Api summonerV4Api;

    public String getPuuid(String accountID) throws IOException {
        log.info("소환사의 puuid: {} ", summonerV4Api.summonerDtoByAccountId(accountID).getPuuid());
        return summonerV4Api.summonerDtoByAccountId(accountID).getPuuid();
    }


    // 롤 계정 정보 있는지 검사
    public boolean isSummoner(String summonerName) throws IOException{
        if (summonerV4Api.summonerDtoBySummonerName(summonerName) == null) {
            return false;
        } else return true;
    }

    public TierRank getSoloTier(String summonerId) throws IOException{
        String tier =  summonerV4Api.summonerTierByPuuid(summonerId).get(1).getTier();
        String rank =  summonerV4Api.summonerTierByPuuid(summonerId).get(1).getRank();
        return new TierRank(tier, rank);
    }
}
