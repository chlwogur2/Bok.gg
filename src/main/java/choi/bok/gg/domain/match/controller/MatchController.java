package choi.bok.gg.domain.match.controller;

import choi.bok.gg.domain.match.dto.api.MatchDto;
import choi.bok.gg.domain.match.service.MatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@RequestMapping("/match")
@RequiredArgsConstructor
public class MatchController {

    private final MatchService matchService;

    @GetMapping("/{matchId}")
    public String matchDetail(@PathVariable String matchId) throws IOException {

        //TODO Service <-> Controller 사이에서 로직을 처리하는 별도의 클래스를 만들어야겠다.
        MatchDto matchDto = matchService.getMatchDtoByMatchId(matchId);

        return "match/matchDetail";
    }
}
