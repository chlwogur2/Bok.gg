package choi.bok.gg.global.api;

import choi.bok.gg.domain.match.dto.MatchDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class MatchApiService implements ApiService{

    private final ObjectMapper objectMapper;

    public List<String> getMatchIds(String urlStr) throws IOException {
        log.info("요청 url: {}", urlStr);
        HttpURLConnection urlConnection = makeConnection(urlStr);
        log.info("MatchIdsApi Response Code = {}", urlConnection.getResponseCode());

        List<String> matchIds;
        matchIds = objectMapper.readValue(urlConnection.getInputStream(), List.class);

        log.info("받아온 정보 = {}", matchIds);
        return matchIds;
    }

    public MatchDto matchByMatchId(String urlStr) throws IOException {
        log.info("요청 url: {}", urlStr);
        HttpURLConnection urlConnection = makeConnection(urlStr);
        log.info("MatchInfoApi Response Code = {}", urlConnection.getResponseCode());

        return objectMapper.readValue(urlConnection.getInputStream(), MatchDto.class);
    }
}
