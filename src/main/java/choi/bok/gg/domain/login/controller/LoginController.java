package choi.bok.gg.domain.login.controller;

import choi.bok.gg.domain.login.service.LoginService;
import choi.bok.gg.domain.account.dto.AccountLoginDto;
import choi.bok.gg.domain.account.entity.Account;
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
    public String loginForm(@ModelAttribute AccountLoginDto accountLoginDto){
        log.info("로그인 폼");
        return "login/loginForm";
    }

    @PostMapping("/login_proc")
    public String login(@Valid @ModelAttribute AccountLoginDto accountLoginDto, BindingResult bindingResult,
                        @RequestParam(defaultValue = "/") String redirectURL,
                        HttpServletRequest request){

        log.info("로그인 컨트롤러 진입");

        // 사용자의 입력이 애초에 틀렸을 때
        if (bindingResult.hasErrors()){
            log.info("사용자 입력 틀림 {}", bindingResult.getFieldErrors());
            return "login/loginForm";
        }

        // 로그인 로직에 넣어봄
        Account loginAccount = loginService.login(accountLoginDto.getUserLoginId(), accountLoginDto.getPassword());

        // 로그인 실패 시, reject 전달
        if (loginAccount == null){
            log.info("로그인 실패");
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "login/loginForm";
        }

        // 로그인 성공 처리 (없으면 새로 세션을 생성해줌)
        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_SESSION, accountLoginDto); // 넘어온 accountLoginDto 세션에 저장
        log.info("로그인 성공");
        return "redirect:" + redirectURL;

    }
}
