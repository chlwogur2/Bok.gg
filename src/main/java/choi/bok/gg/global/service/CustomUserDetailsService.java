package choi.bok.gg.global.service;

import choi.bok.gg.domain.account.dto.AccountLoginDto;
import choi.bok.gg.domain.account.entity.Account;
import choi.bok.gg.domain.account.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.*;

// DB에서 유저 정보 받아와서 로그인 인증 구현
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String userLoginId) throws UsernameNotFoundException {

        Optional<Account> account = accountRepository.findByUserLoginId(userLoginId);

        if (account.isEmpty()){
            throw new UsernameNotFoundException("사용자 정보를 찾을 수 없습니다.");
        }

        AccountLoginDto accountLoginDto = new AccountLoginDto();
        accountLoginDto.from(account.get());

        Set<GrantedAuthority> roles = new HashSet<>();
        roles.add(new SimpleGrantedAuthority("ROLE_USER"));     // 후에 사용자별 인가가 생긴다면 account.role 꺼내오면됨

        log.info("accountLoginDto password = {}", accountLoginDto.getPassword());
        AccountContext accountContext = new AccountContext(accountLoginDto,roles);

        log.info(accountContext.getUsername());
        log.info(accountContext.getPassword());
        log.info(accountContext.getAccountLoginDto().toString());

        return accountContext;
    }
}
