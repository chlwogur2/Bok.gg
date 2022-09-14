package choi.bok.gg.domain.user.service;

import choi.bok.gg.domain.user.dto.UserSignUpDto;
import choi.bok.gg.domain.user.entity.User;
import choi.bok.gg.domain.user.repository.UserRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import javax.validation.Valid;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    /**
     * 회원가입 메소드
     */
    public UserSignUpDto signUp(UserSignUpDto userSignUpDto){

        // 가입 로직 성공하면 유저 저장
        userRepository.save(User.builder()
                .userLoginId(userSignUpDto.getUserLoginId())
                .password(userSignUpDto.getPassword())
                .summonerName(userSignUpDto.getSummonerName())
                .build());

        return userSignUpDto;
    }
}
