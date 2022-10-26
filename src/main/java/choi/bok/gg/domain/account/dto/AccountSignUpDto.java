package choi.bok.gg.domain.account.dto;

import choi.bok.gg.domain.account.entity.Account;
import choi.bok.gg.global.api.RiotLocale;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 회원가입 dto
 */
@Data
@AllArgsConstructor
public class AccountSignUpDto {

    @NotNull
    @NotEmpty
    private String userLoginId;

    @NotEmpty
    @Size(min = 8, max = 16)
    private String password;

    @NotNull
    @NotEmpty
    private String summonerName;

    @NotNull
    private RiotLocale locale;
}
