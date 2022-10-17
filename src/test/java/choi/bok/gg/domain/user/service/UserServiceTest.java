package choi.bok.gg.domain.user.service;

import choi.bok.gg.domain.user.entity.User;
import choi.bok.gg.domain.user.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@Transactional
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Test
    @DisplayName("유저 로그인 ID로 유저 조회 성공")
    void findUserByLoginId() {

        Optional<User> user = Optional.of(User.builder()
                .userLoginId("hello")
                .summonerName("olleh").build());

        userRepository.save(user.get());

        String userLoginId = "hello";

        Mockito.doReturn(user).when(userRepository)
                .findUserByUserLoginId(userLoginId);

        assertThat(userService.findUserByLoginId("hello").get()).isEqualTo(user.get());
    }

    @Test
    void findSummonerName() {
    }

    @Test
    void findAccountId() {
    }
}