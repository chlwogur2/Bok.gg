package choi.bok.gg.domain.login.service;

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
        Optional<User> findUser = userRepository.findUserByUserLoginId(userLoginId);

        return findUser.filter(u -> u.getPassword().equals(password))   // 패스워드가 같으면 User 객체 리턴
                .orElse(null);  // 다르면 null 리턴
    }
}
