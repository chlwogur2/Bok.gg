package choi.bok.gg.domain.account.service;

import choi.bok.gg.domain.account.entity.Account;
import choi.bok.gg.domain.account.repository.AccountRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    @InjectMocks  // Mock 객체를 주입받는 객체
    private AccountService accountService;

    @Mock  // 실제가 아닌 Mock
    private AccountRepository accountRepository;

    Optional<Account> user = Optional.of(Account.builder()
            .accountId("ac")
            .userLoginId("hello")
            .summonerName("재 렉").build());


    @Test
    @DisplayName("유저 로그인 ID로 유저 조회")
    void findByLoginId() {
        //given
        when(accountRepository.findByUserLoginId(any())).thenReturn(user);

        String userLoginId = "hello";

        //when
        Optional<Account> account = accountService.findAccountByLoginId(userLoginId);

        //then
        assertThat(account).isEqualTo(user);
    }

    @Test
    @DisplayName("유저 로그인 ID로 소환사 이름 조회")
    void findSummonerName() {
        //given
        when(accountRepository.findByUserLoginId("hello")).thenReturn(user);

        String userLoginId = "hello";

        //when
        String name = accountService.findSummonerNameByLoginId(userLoginId);

        //then
        assertThat(user.get().getSummonerName()).isEqualTo(name);
    }

    @Test
    @DisplayName("유저 로그인 ID로 accountId 조회")
    void findAccountId() {
        //given
        when(accountRepository.findByUserLoginId("hello")).thenReturn(user);
        String userLoginId = "hello";

        //when
        String accountId = accountService.findAccountIdByLoginId(userLoginId);

        //then
        assertThat(user.get().getAccountId()).isEqualTo(accountId);
    }

    @Test
    @DisplayName("유저 로그인 ID로 유저 조회 실패")
    void failFindSummonerName() {
        String name = "없는 이름";
        assertThat(accountService.findAccountByLoginId(name)).isEmpty();
    }

}