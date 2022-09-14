package choi.bok.gg.domain.login.service;

import choi.bok.gg.domain.user.dto.UserLoginDto;
import choi.bok.gg.domain.user.entity.User;
import choi.bok.gg.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserRepository userRepository;

    public User login(String userLoginId, String password){
        // 일단은 입력으로 들어온 UserLoginId 로 유저를 찾아봄
        Optional<User> findUser = userRepository.findUserByUserLoginId(userLoginId);

        // TODO: UserLoginDto 로 어떻게 바꿈?
        return  findUser.filter(u -> u.getPassword().equals(password))   // 패스워드가 같으면 User 객체 리턴
                .orElse(null);  // 다르면 null 리턴
    }
}
