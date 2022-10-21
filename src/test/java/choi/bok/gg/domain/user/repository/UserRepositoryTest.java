package choi.bok.gg.domain.user.repository;

import choi.bok.gg.domain.user.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    void findUserByUserLoginId() {

        String userLoginId = "hello";
        User user = User.builder()
                .userLoginId(userLoginId)
                .build();

        userRepository.save(user);

        User findUser = userRepository.findUserByUserLoginId(userLoginId).get();

        assertThat(findUser).isEqualTo(user);
        assertThat(findUser.getUserLoginId()).isEqualTo("hello");
    }

    @Test
    void findUserBySummonerId(){
        String korean = URLEncoder.encode("곱창", StandardCharsets.UTF_8);

        User userKorean = User.builder()
                .summonerName(korean).build();
        User userAmerican = User.builder()
                .summonerName("qwer").build();



        userRepository.save(userKorean);
        userRepository.save(userAmerican);

        User findKorean = userRepository.findUserBySummonerName(korean).get();
        User findAmerican = userRepository.findUserBySummonerName("qwer").get();

        assertThat(findAmerican).isEqualTo(userAmerican);
        assertThat(findKorean).isEqualTo(userKorean);
    }
}