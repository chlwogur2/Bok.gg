package choi.bok.gg.global.controller;

import choi.bok.gg.domain.user.dto.UserLoginDto;
import choi.bok.gg.global.session.SessionConst;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
public class HomeController {


    @GetMapping("/")
    public String homePage(
            @SessionAttribute(name = SessionConst.LOGIN_SESSION, required = false) UserLoginDto userLoginDto, Model model){

        // 세션에 회원 데이터가 없으면 일반 home 화면
        if (userLoginDto == null) {
            return "home";
        }

        // 로그인한 회원이면 로그인 home 화면
        model.addAttribute("userLoginDto", userLoginDto);
        return "loginHome";
    }
}
