package choi.bok.gg.domain.user.repository;

import choi.bok.gg.domain.user.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    void findUserByUserLoginId() {

        String userLoginId = "hello";
        User user = User.builder()
                .userLoginId(userLoginId)
                .build();

        System.out.println(user);
        System.out.println(userRepository);
        userRepository.save(user);

        User findUser = userRepository.findUserByUserLoginId(userLoginId).get();

        assertThat(findUser).isEqualTo(user);
        assertThat(findUser.getUserLoginId()).isEqualTo("hello");

    }
}