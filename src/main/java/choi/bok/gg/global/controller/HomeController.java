package choi.bok.gg.global.controller;

import choi.bok.gg.domain.match.dto.MatchDto;
import choi.bok.gg.domain.match.service.MatchService;
import choi.bok.gg.domain.summoner.service.SummonerService;
import choi.bok.gg.domain.account.dto.AccountLoginDto;
import choi.bok.gg.domain.account.service.AccountService;
import choi.bok.gg.global.session.SessionConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class HomeController {

    private final MatchService matchService;
    private final SummonerService summonerService;
    private final AccountService accountService;
    @GetMapping("/")
    public String homePage(
            @SessionAttribute(name = SessionConst.LOGIN_SESSION, required = false) AccountLoginDto accountLoginDto, Model model) throws IOException {

        // 세션에 회원 데이터가 없으면 일반 home 화면
        if (accountLoginDto == null) {
            log.info("nullController");
            return "home/home";
        }

        // 세션의 회원 데이터에서 userLoginId -> 소환사 이름
        String accountId = accountService.findAccountId(accountLoginDto.getUserLoginId());
        List<String> matchIds = matchService.getMatchIdsByPuuid(summonerService.getPuuidByAccountId(accountId, "summonerKrApi"));
        List<MatchDto> matches = new ArrayList<>();
        // TODO 최근 기록이 10개 이하인 경우?
        for (String m : matchIds) matches.add(matchService.getMatchByMatchId(m));

        // 로그인한 회원이면 로그인 home 화면
        model.addAttribute("userLoginDto", accountLoginDto);
        model.addAttribute("matchListDto", matches);
        return "home/loginHome";
    }
}
