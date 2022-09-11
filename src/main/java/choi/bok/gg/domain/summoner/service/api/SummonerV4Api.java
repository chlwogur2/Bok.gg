package choi.bok.gg.domain.summoner.service.api;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class SummonerV4Api {

    // Api 키는 HTTP Authorization 헤더에 삽입 "X-Riot-Token"
    private final String urlStr = "https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-name";

    public SummonerV4Api() throws IOException {
        // URL 객체 생성 (절대경로)
        URL url = new URL(urlStr);

        // OpenConnection() 메소드로 연결
        // url 주소의 원격 객체에 접속한 뒤 --> 통신할 수 있는 URLconnection 객체 리턴
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");




    }
}
