package choi.bok.gg.domain.account.dto;

import choi.bok.gg.domain.account.entity.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

/**
 * 로그인 dto
 * 세션에 들어가는 내용
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountLoginDto {

    @NotEmpty
    private String userLoginId;

    @NotEmpty
    private String summonerName;

    @NotEmpty
    private String password;

    public AccountLoginDto from(Account account) {
        setUserLoginId(account.getUserLoginId());
        setPassword(account.getPassword());
        setSummonerName(account.getSummonerName());
        return this;
    }
}
