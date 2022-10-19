package choi.bok.gg.global.api;

import choi.bok.gg.global.RiotApiKey;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public interface ApiService {

    default HttpURLConnection makeConnection(String urlStr) throws IOException {
        // URL 객체 생성 (절대경로)
        URL url = new URL(urlStr);

        // OpenConnection() 메소드로 연결
        // url 주소의 원격 객체에 접속한 뒤 --> 통신할 수 있는 URLconnection 객체 리턴
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        urlConnection.setRequestMethod("GET");
        // 발급받은 API 키 Request 헤더에 설정
        urlConnection.setRequestProperty("X-Riot-Token", RiotApiKey.KEY);
        return urlConnection;
    }
}
