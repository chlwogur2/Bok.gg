package choi.bok.gg.domain.match.service.api;


import choi.bok.gg.domain.match.dto.MatchV5Dto;
import choi.bok.gg.global.RiotApiKey;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


import java.io.IOException;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class MatchV5Api {

    private final String urlStr = "https://asia.api.riotgames.com/lol/match/v5/matches/";
    private final ObjectMapper objectMapper;

    /**
     * @param puuid 유저의 puuid
     * @param start Defaults to 0. Start index
     * @param end   Defaults to 20. Valid values: 0 to 100.
     * @throws IOException
     */
    // TODO String[] 로 넘어오는게 아니고, [...] 로 둘러싸인 String 요소 하나로 취급됨 -> 리턴 타입 변경
    public List<String> matchIdsByPuuid(String puuid, int start, int end) throws IOException {
        // 좀 더 손보자
        URL url = new URL(urlStr + "by-puuid/" + puuid + "/ids?start=" + start + "&count=" + end);

        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");
        urlConnection.setRequestProperty("X-Riot-Token", RiotApiKey.KEY);

        // UTF-8 인코딩 필요없음
        List<String> matchIds;

        matchIds = objectMapper.readValue(urlConnection.getInputStream(), List.class);

        log.info("matchV5Api results = {}", matchIds);

        return matchIds;
    }

    public MatchV5Dto matchByMatchId(String matchId) throws IOException{

        URL url = new URL(urlStr + matchId);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");
        urlConnection.setRequestProperty("X-Riot-Token", RiotApiKey.KEY);

        // JSON Array 매핑하는 걸로 바꿔야 함
        return objectMapper.readValue(urlConnection.getInputStream(), MatchV5Dto.class);
    }
}
