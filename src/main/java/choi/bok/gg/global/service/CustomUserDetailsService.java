package choi.bok.gg.global.service;

import choi.bok.gg.domain.user.entity.User;
import choi.bok.gg.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

// DB에서 유저 정보 받아와서 로그인 인증 구현
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userLoginId) throws UsernameNotFoundException {

        Optional<User> findUser = userRepository.findUserByUserLoginId(userLoginId);

        if (findUser.isEmpty()){
            throw new UsernameNotFoundException("사용자 정보를 찾을 수 없습니다.");
        }

        return null;
    }
}
