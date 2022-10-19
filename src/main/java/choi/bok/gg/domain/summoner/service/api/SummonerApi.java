package choi.bok.gg.domain.summoner.service.api;

import choi.bok.gg.domain.summoner.dto.SummonerDto;
import choi.bok.gg.domain.summoner.dto.SummonerTierDto;

import java.io.IOException;
import java.util.List;

public interface SummonerApi {

    // 유저 AccountId로 소환사 정보 가져옴
    SummonerDto summonerDtoByAccountId(String accountId) throws IOException;

    // 소환사 이름으로 소환사 정보 가져옴
    SummonerDto summonerDtoBySummonerName(String summonerName) throws IOException;

    // 유저 puuId로 소환사 랭킹 정보 가져옴
    List<SummonerTierDto> summonerTierByAccountId(String accountId) throws IOException;
}
