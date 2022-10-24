package choi.bok.gg.domain.match.service.api;

import choi.bok.gg.domain.match.dto.api.MatchDto;

import java.io.IOException;
import java.util.List;

public interface MatchApi {

    // 소환사의 puuid로 매치 id를 end - start + 1개 가져옴
    List<String> matchIdsByPuuid(String puuid, int start, int end) throws IOException;

    // 매치 id로 매치 정보를 가져옴
    MatchDto matchByMatchId(String matchId) throws IOException;
}
