package choi.bok.gg.domain.account.repository;

import choi.bok.gg.domain.account.entity.Account;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AccountRepositoryTest {

    @Autowired
    AccountRepository accountRepository;

    @Test
    void findByUserLoginId() {

        String userLoginId = "hello";
        Account account = Account.builder()
                .userLoginId(userLoginId)
                .build();

        accountRepository.save(account);

        Account findAccount = accountRepository.findByUserLoginId(userLoginId).get();

        assertThat(findAccount).isEqualTo(account);
        assertThat(findAccount.getUserLoginId()).isEqualTo("hello");
    }

    @Test
    void findBySummonerId(){
        String korean = URLEncoder.encode("곱창", StandardCharsets.UTF_8);

        Account accountKorean = Account.builder()
                .summonerName(korean).build();
        Account accountAmerican = Account.builder()
                .summonerName("qwer").build();



        accountRepository.save(accountKorean);
        accountRepository.save(accountAmerican);

        Account findKorean = accountRepository.findBySummonerName(korean).get();
        Account findAmerican = accountRepository.findBySummonerName("qwer").get();

        assertThat(findAmerican).isEqualTo(accountAmerican);
        assertThat(findKorean).isEqualTo(accountKorean);
    }
}