package choi.bok.gg.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * 회원가입 dto
 */
@Data
@AllArgsConstructor
public class UserSignUpDto {

    @NotEmpty
    private String userLoginId;
    @NotEmpty
    private String password;
    @NotEmpty
    private String summonerName;
}
