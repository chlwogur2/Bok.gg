package choi.bok.gg.domain.user.controller;

import choi.bok.gg.domain.summoner.service.SummonerService;
import choi.bok.gg.domain.user.dto.UserLoginIdDto;
import choi.bok.gg.domain.user.dto.UserSignUpDto;
import choi.bok.gg.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

@Controller
@Slf4j
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final SummonerService summonerService;

    @GetMapping("/signup")
    public String signUpPage(@ModelAttribute UserSignUpDto userSignUpDto){
        log.info("회원가입");
        return "users/userSignUpForm";
    }


    @PostMapping("/signup")
    public String signUp(UserLoginIdDto userLoginIdDto, @Valid @ModelAttribute UserSignUpDto userSignUpDto, BindingResult bindingResult) throws IOException {

        log.info("AJAX로 넘어온거: " + userLoginIdDto.toString());
        log.info(userService.findUserByLoginId(userLoginIdDto.getLoginId()).toString());
        // TODO 아이디 중복 검사 성공시, 실패시 메세지 표현
        if (!userService.findUserByLoginId(userLoginIdDto.getLoginId()).isEmpty()) {
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
        if (!summonerService.isSummoner(userSignUpDto.getSummonerName())) {
            log.info("소환사 존재 안함");
            bindingResult.reject("NoSummoner");
            return "users/userSignUpForm";
        }



        userService.signUp(userSignUpDto);
        return "redirect:/";
    }

    @GetMapping("/mypage")
    public String myPage() {
        return "users/myPage";
    }
}
