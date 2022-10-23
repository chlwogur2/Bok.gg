package choi.bok.gg.global.service;

import choi.bok.gg.domain.account.entity.Account;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;


@Getter
// DB 에서 넘긴 유저 정보
public class UserContext extends User {

    private final Account account;

    public UserContext(Account account, Collection<? extends GrantedAuthority> authorities) {
        super(account.getUserLoginId(), account.getPassword(), authorities);
        this.account = account;
    }
}
