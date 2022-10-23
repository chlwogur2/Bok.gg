package choi.bok.gg.domain.account.repository;

import choi.bok.gg.domain.account.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findUserByUserLoginId(String userLoginId);

    Optional<Account> findUserBySummonerName(String summonerName);
}
