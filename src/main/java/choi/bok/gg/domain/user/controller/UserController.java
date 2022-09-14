package choi.bok.gg.domain.user.controller;

import choi.bok.gg.domain.user.dto.UserSignUpDto;
import choi.bok.gg.domain.user.entity.User;
import choi.bok.gg.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Slf4j
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 매치 기록 페이징
    @GetMapping("/matches")
    public void userHistory() {

    }

    @GetMapping("/add")
    public String signUpPage(@ModelAttribute UserSignUpDto userSignUpDto){
        return "users/addUserForm";
    }

    @PostMapping("/add")
    public String signUp(@Valid @ModelAttribute UserSignUpDto userSignUpDto, BindingResult bindingResult) {
        // 가입 입력에 에러가 발생하면 null 리턴
        if (bindingResult.hasErrors()) {
            return "users/addUserForm";
        }
        userService.signUp(userSignUpDto);
        return "redirect:/";
    }
}
