package choi.bok.gg.domain.match.service.api;


import choi.bok.gg.global.RiotApiKey;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
@Slf4j
@RequiredArgsConstructor
public class MatchV5Api {

    private final String urlStr = "https://asia.api.riotgames.com/lol/match/v5/matches/by-puuid/";

    // api 리턴 값이 String[] 이라 objectMapper 필요 없음

    /**
     * @param puuid 유저의 puuid
     * @param start Defaults to 0. Start index
     * @param end   Defaults to 20. Valid values: 0 to 100.
     * @throws IOException
     */
    public String[]  matchV5ApiByPuuid(String puuid, int start, int end) throws IOException {
        int count = end - start;    // Number of match ids to return
        URL url = new URL(urlStr + puuid + "/ids?start=" + start + "&count=" + end);

        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");
        urlConnection.setRequestProperty("X-Riot-Token", RiotApiKey.KEY);

        // UTF-8 인코딩 필요없음
        BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

        String[] matchIds = new String[count];

        for (int i = 0; i < count; i++) {
            matchIds[i] = br.readLine();
        }

        log.info("matchV5Api results = {}", matchIds);

        return matchIds;
    }
}
