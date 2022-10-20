package choi.bok.gg.domain.match.service.api;


import choi.bok.gg.domain.match.dto.MatchDto;
import choi.bok.gg.global.api.MatchApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;


import java.io.IOException;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class MatchKrApi implements MatchApi{


    private final MatchApiService matchApiService;
    private final MessageSource messageSource;

    public List<String> matchIdsByPuuid(String puuid, int start, int end) throws IOException {

        String urlStr = messageSource.getMessage("match.id.by-puuid", new Object[]{puuid, start, end}, null);
        return matchApiService.getMatchIds(urlStr);
    }

    public MatchDto matchByMatchId(String matchId) throws IOException{

        String urlStr = messageSource.getMessage("match.info.by-match-id", new Object[]{matchId}, null);
        return matchApiService.getMatchDto(urlStr);
    }
}
