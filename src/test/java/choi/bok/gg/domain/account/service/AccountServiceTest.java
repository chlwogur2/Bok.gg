package choi.bok.gg.domain.account.service;

import choi.bok.gg.domain.account.entity.Account;
import choi.bok.gg.domain.account.repository.AccountRepository;
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

@ExtendWith(MockitoExtension.class)
@Transactional
class AccountServiceTest {

    @InjectMocks
    private AccountService accountService;

    @Mock
    private AccountRepository accountRepository;

    @Test
    @DisplayName("유저 로그인 ID로 유저 조회 성공")
    void findUserByLoginId() {

        Optional<Account> user = Optional.of(Account.builder()
                .userLoginId("hello")
                .summonerName("olleh").build());

        accountRepository.save(user.get());

        String userLoginId = "hello";

        Mockito.doReturn(user).when(accountRepository)
                .findUserByUserLoginId(userLoginId);

        assertThat(accountService.findUserByLoginId("hello").get()).isEqualTo(user.get());
    }

    @Test
    void findSummonerName() {
    }

    @Test
    void findAccountId() {
    }
}