package choi.bok.gg.domain.account.controller;

import choi.bok.gg.domain.summoner.service.SummonerService;
import choi.bok.gg.domain.account.dto.AccountLoginDto;
import choi.bok.gg.domain.account.dto.AccountLoginIdDto;
import choi.bok.gg.domain.account.dto.AccountSignUpDto;
import choi.bok.gg.domain.account.service.AccountService;
import choi.bok.gg.global.session.SessionConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

@Controller
@Slf4j
@RequestMapping("/users")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;
    private final SummonerService summonerService;

    @GetMapping("/signup")
    public String signUpPage(@ModelAttribute AccountSignUpDto accountSignUpDto){
        log.info("회원가입");
        return "users/userSignUpForm";
    }


    @PostMapping("/signup")
    public String signUp(AccountLoginIdDto accountLoginIdDto, @Valid @ModelAttribute AccountSignUpDto accountSignUpDto, BindingResult bindingResult) throws IOException {

        log.info("AJAX로 넘어온거: " + accountLoginIdDto.toString());
        log.info(accountService.findUserByLoginId(accountLoginIdDto.getLoginId()).toString());
        // TODO 아이디 중복 검사 성공시, 실패시 메세지 표현
        if (!accountService.findUserByLoginId(accountLoginIdDto.getLoginId()).isEmpty()) {
            bindingResult.reject("Duplicated.userLoginId");
            log.info("중복검사 실행");
            return "/users/userSignUpForm";
//            return "users/userSignUpForm :: #duplicatedResult";
        }

        log.info("중복검사 실행 안함");

        // 가입 입력에 에러가 발생하면 null 리턴
        if (bindingResult.hasErrors()) {
            return "users/userSignUpForm";
        }


        // 소환사이름이 없을 경우
        if (!summonerService.isSummoner(accountSignUpDto.getSummonerName(), "summonerKrApi")) {
            log.info("소환사 존재 안함");
            bindingResult.reject("NoSummoner");
            return "users/userSignUpForm";
        }

        accountService.signUp(accountSignUpDto);
        return "redirect:/";
    }

    @GetMapping("/mypage")
    public String myPage(@SessionAttribute(name = SessionConst.LOGIN_SESSION) AccountLoginDto accountLoginDto, Model model) {
        model.addAttribute("userLoginDto", accountLoginDto);
        return "users/myPage";
    }
}
