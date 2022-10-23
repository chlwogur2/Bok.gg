package choi.bok.gg.domain.account.service;

import choi.bok.gg.domain.summoner.service.api.SummonerKrApi;
import choi.bok.gg.domain.account.dto.AccountSignUpDto;
import choi.bok.gg.domain.account.entity.Account;
import choi.bok.gg.domain.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final SummonerKrApi summonerKrApi;
    private final PasswordEncoder passwordEncoder;
    /**
     * 회원가입 메소드
     */
    public AccountSignUpDto signUp(AccountSignUpDto accountSignUpDto) throws IOException {
        // 가입 로직 성공하면 유저 저장
        accountRepository.save(Account.builder()
                .userLoginId(accountSignUpDto.getUserLoginId())
                .password(passwordEncoder.encode(accountSignUpDto.getPassword()))
                .summonerName(accountSignUpDto.getSummonerName())  // 소환사 이름은 평문으로 저장
                .accountId(summonerKrApi.summonerDtoBySummonerName(accountSignUpDto.getSummonerName()).getAccountId())
                .build());

        return accountSignUpDto;
    }

    // 로그인 ID가 DB에 존재하는지 검색
    public Optional<Account> findUserByLoginId(String userLoginId){
        return accountRepository.findUserByUserLoginId(userLoginId);
    }

    // 로그인 ID로 소환사명 검색
    public String findSummonerName(String userLoginId){
        return accountRepository.findUserByUserLoginId(userLoginId).get().getSummonerName();
    }

    // 로그인 ID로 account accountID 검색
    public String findAccountId(String userLoginId) {
        return accountRepository.findUserByUserLoginId(userLoginId).get().getAccountId();
    }
}
