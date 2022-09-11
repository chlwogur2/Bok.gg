package choi.bok.gg.domain.login.controller;

import choi.bok.gg.domain.login.LoginForm;
import choi.bok.gg.domain.login.service.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@Slf4j
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    // 로그인 폼 보여주는 애
    @GetMapping("/login")
    public String loginForm(@ModelAttribute("loginForm") LoginForm loginForm){

        // TODO: 2022-09-12 [view 만들어야함]
        return "login/loginForm";
    }
}
