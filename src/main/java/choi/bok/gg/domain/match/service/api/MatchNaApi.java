package choi.bok.gg.domain.match.service.api;

import choi.bok.gg.domain.match.dto.api.MatchDto;
import choi.bok.gg.global.api.MatchApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

@Slf4j
@RequiredArgsConstructor
@Component
public class MatchNaApi implements MatchApi{

    private final MatchApiService matchApiService;
    private final MessageSource messageSource;

    @Override
    public List<String> matchIdsByPuuid(String puuid, int start, int end) throws IOException {
        String urlStr = messageSource.getMessage("match.id.by-puuid", new Object[]{puuid, start, end}, Locale.ENGLISH);
        return matchApiService.getMatchIds(urlStr);
    }

    @Override
    public MatchDto matchByMatchId(String matchId) throws IOException {
        String urlStr = messageSource.getMessage("match.info.by-match-id", new Object[]{matchId}, Locale.ENGLISH);
        return matchApiService.getMatchDto(urlStr);
    }
}
