package choi.bok.gg.global.service;

import choi.bok.gg.domain.account.dto.AccountLoginDto;
import choi.bok.gg.domain.account.entity.Account;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;


@Getter
// DB 에서 넘긴 유저 정보
public class AccountContext extends User {

    private final AccountLoginDto accountLoginDto;

    public AccountContext(AccountLoginDto accountLoginDto, Collection<? extends GrantedAuthority> authorities) {
        super(accountLoginDto.getUserLoginId(), accountLoginDto.getPassword(), authorities);
        this.accountLoginDto = accountLoginDto;
    }
}
