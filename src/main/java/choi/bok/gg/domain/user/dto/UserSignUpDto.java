package choi.bok.gg.domain.user.dto;

import choi.bok.gg.domain.user.entity.User;
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

    public User toEntity(UserSignUpDto userSignUpDto) {
        return User.builder()
                .userLoginId(userSignUpDto.getUserLoginId())
                .password(userSignUpDto.getPassword())
                .summonerName(userSignUpDto.getSummonerName())
                .build();
    }
}
