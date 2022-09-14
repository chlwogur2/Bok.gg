package choi.bok.gg.domain.summoner.service.api;

import choi.bok.gg.domain.summoner.dto.SummonerV4Dto;
import choi.bok.gg.global.RiotApiKey;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Slf4j
@RequiredArgsConstructor
@Component
public class SummonerV4Api {

    // Api 키는 HTTP Authorization 헤더에 삽입 "X-Riot-Token"
    private final String urlStr = "https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-name/";

    @Autowired
    private final ObjectMapper objectMapper;

    public SummonerV4Dto summonerV4ApiBySummonerName(String summonerName) throws IOException {

        // 한글이 깨지는 문제로 인해 소환사 이름을 인코딩을 해야함
        String encoded = URLEncoder.encode(summonerName, StandardCharsets.UTF_8);
        // URL 객체 생성 (절대경로)
        URL url = new URL(urlStr + encoded);

        // OpenConnection() 메소드로 연결
        // url 주소의 원격 객체에 접속한 뒤 --> 통신할 수 있는 URLconnection 객체 리턴
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");

        // 발급받은 API 키 Request 헤더에 설정
        urlConnection.setRequestProperty("X-Riot-Token", RiotApiKey.KEY);
//        urlConnection.setRequestProperty("Origin", "https://developer.riotgames.com");
//        urlConnection.setRequestProperty("Accept-Charset", "application/x-www-form-urlencoded; charset=UTF-8");
//        urlConnection.setRequestProperty("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7");
//        urlConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/105.0.0.0 Safari/537.36");

        log.info("Response Code = {}", urlConnection.getResponseCode());

        /**
         *  여기서부터 응답값 읽어오기
         */
        // InputStreamReader 클래스로 읽어오기
        // BufferedReader 는 InputStreamReader 를 입력값으로 사용
        BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));

//        String line;
//        StringBuilder sb = new StringBuilder();
//        while ( (line = br.readLine()) != null ) {
//            sb.append(line);
//        }
//        log.info(sb.toString());
        SummonerV4Dto summonerV4Dto = objectMapper.readValue(urlConnection.getInputStream(), SummonerV4Dto.class);

        // 연결 해제
        urlConnection.disconnect();
        log.info("받아온 정보 = {}", summonerV4Dto);
        return summonerV4Dto;
    }
}
