package choi.bok.gg.domain.login.controller;

import choi.bok.gg.domain.login.service.LoginService;
import choi.bok.gg.domain.user.dto.UserLoginDto;
import choi.bok.gg.domain.user.entity.User;
import choi.bok.gg.global.session.SessionConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@Slf4j
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    // 로그인 폼 보여주는 애
    @GetMapping("/login")
    public String loginForm(@ModelAttribute UserLoginDto userLoginDto){

        // TODO: 2022-09-12 [view 만들어야함]
        return "login/loginForm";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute UserLoginDto userLoginDto, BindingResult bindingResult,
                        @RequestParam(defaultValue = "/") String redirectURL,
                        HttpServletRequest request){

        // 사용자의 입력이 애초에 틀렸을 때
        if (bindingResult.hasErrors()){
            return "login/loginForm";
        }

        // 로그인 로직에 넣어봄
        User loginUser = loginService.login(userLoginDto.getUserLoginId(), userLoginDto.getPassword());

        // 로그인 실패 시, reject 전달
        if (loginUser == null){
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "login/loginForm";
        }

        // 로그인 성공 처리 (없으면 새로 세션을 생성해줌)
        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_SESSION, userLoginDto); // 넘어온 userLoginDto 세션에 저장

        return "redirect:" + redirectURL;


    }
}
