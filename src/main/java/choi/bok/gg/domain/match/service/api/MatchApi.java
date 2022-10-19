package choi.bok.gg.domain.match.service.api;


import choi.bok.gg.domain.match.dto.MatchDto;
import choi.bok.gg.global.api.MatchApiService;
import choi.bok.gg.global.api.SummonerApiService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;


import java.io.IOException;

import java.net.HttpURLConnection;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class MatchApi {


    private final MatchApiService matchApiService;
    private final MessageSource messageSource;

    /**
     * @param puuid 유저의 puuid
     * @throws IOException
     */
    public List<String> matchIdsByPuuid(String puuid, int start, int end) throws IOException {

        String urlStr = messageSource.getMessage("match.id.by-puuid", new Object[]{puuid, start, end}, null);
        return matchApiService.getMatchIds(urlStr);
    }

    public MatchDto matchByMatchId(String matchId) throws IOException{

        String urlStr = messageSource.getMessage("match.info.by-match-id", new Object[]{matchId}, null);
        return matchApiService.matchByMatchId(urlStr);
    }
}
