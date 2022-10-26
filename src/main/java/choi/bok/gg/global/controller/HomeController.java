package choi.bok.gg.global.controller;

import choi.bok.gg.domain.match.dto.MatchResultDto;
import choi.bok.gg.domain.match.dto.api.MatchDto;
import choi.bok.gg.domain.match.service.MatchService;
import choi.bok.gg.domain.summoner.service.SummonerService;
import choi.bok.gg.domain.account.dto.AccountLoginDto;
import choi.bok.gg.domain.account.service.AccountService;
import choi.bok.gg.global.annotation.AuthUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
            @AuthUser AccountLoginDto accountLoginDto, Model model) throws Exception{

        if (accountLoginDto == null) {
            log.info("로그인 실패");
            return "home/home";
        }

        // 세션의 회원 데이터에서 userLoginId -> 소환사 이름
        String accountId = accountService.findAccountId(accountLoginDto.getUserLoginId());
        List<String> matchIds = matchService.getMatchIdsByPuuid(summonerService.getPuuidByAccountId(accountId, "summonerKrApi"));

        // 승패 결과
        //TODO: Service와 Controller 사이에서 실행되는 로직들을 제공하는 FACADE 객체를 만들어보자
        List<MatchResultDto> matchResults = new ArrayList<>();
        for (String ids : matchIds){
            MatchDto matchDto = matchService.getMatchByMatchId(ids);
            MatchResultDto matchResultDto = matchService.getMatchResult(accountLoginDto, matchDto);
            matchResults.add(matchResultDto);
//            if (matchService.isWin(accountLoginDto,matchDto)) matchResults.add(true);
//            else matchResults.add(false);
        }

        // 로그인한 회원이면 로그인 home 화면
        model.addAttribute("accountLoginDto", accountLoginDto);
        model.addAttribute("matchResults", matchResults);
        return "home/loginHome";
    }

}
