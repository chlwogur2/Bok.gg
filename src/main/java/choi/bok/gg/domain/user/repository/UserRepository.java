package choi.bok.gg.domain.user.repository;

import choi.bok.gg.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // 메소드 이름으로 쿼리 생성
    Optional<User> findUserByUserLoginId(String userLoginId);

    Optional<User> findUserBySummonerName(String summonerName);
}
