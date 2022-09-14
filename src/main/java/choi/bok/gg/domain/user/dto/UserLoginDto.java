package choi.bok.gg.domain.user.dto;

import choi.bok.gg.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

/**
 * 로그인 dto
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginDto {

    @NotEmpty
    private String userLoginId;

    @NotEmpty
    private String password;

    public UserLoginDto from(User user){
        return new UserLoginDto(user.getUserLoginId(), user.getPassword());
    }

}
