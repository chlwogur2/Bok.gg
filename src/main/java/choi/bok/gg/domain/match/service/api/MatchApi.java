package choi.bok.gg.domain.match.service.api;


import choi.bok.gg.domain.match.dto.MatchDto;
import choi.bok.gg.global.RiotApiKey;
import choi.bok.gg.global.api.ApiService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;


import java.io.IOException;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class MatchApi {

    private final String urlStr = "https://asia.api.riotgames.com/lol/match/v5/matches/";
    private final ObjectMapper objectMapper;
    private final ApiService apiService;
    private final MessageSource messageSource;

    /**
     * @param puuid 유저의 puuid
     * @throws IOException
     */
    public List<String> matchIdsByPuuid(String puuid, int start, int end) throws IOException {

        String urlStr = messageSource.getMessage("match.id.by-puuid", new Object[]{puuid, start, end}, null);
        HttpURLConnection urlConnection = apiService.makeConnection(urlStr);

        // UTF-8 인코딩 필요없음
        List<String> matchIds;

        matchIds = objectMapper.readValue(urlConnection.getInputStream(), List.class);

//        log.info("matchV5Api results = {}", matchIds);

        return matchIds;
    }

    public MatchDto matchByMatchId(String matchId) throws IOException{

        URL url = new URL(urlStr + matchId);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");
        urlConnection.setRequestProperty("X-Riot-Token", RiotApiKey.KEY);

        // JSON Array 매핑하는 걸로 바꿔야 함
        return objectMapper.readValue(urlConnection.getInputStream(), MatchDto.class);
    }
}
