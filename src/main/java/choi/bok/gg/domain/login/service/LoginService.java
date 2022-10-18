package choi.bok.gg.domain.login.service;

import choi.bok.gg.domain.user.dto.UserLoginDto;
import choi.bok.gg.domain.user.entity.User;
import choi.bok.gg.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User login(String userLoginId, String password){
        // 일단은 입력으로 들어온 UserLoginId 로 유저를 찾아봄
        Optional<User> findUser = userRepository.findUserByUserLoginId(userLoginId);

        log.info("로그인 시도하는 소환사: {}", findUser.get().getSummonerName());

        if (passwordEncoder.matches(password,findUser.get().getPassword())) return findUser.get();
        else return null;
    }
}
