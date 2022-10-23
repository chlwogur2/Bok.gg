package choi.bok.gg.domain.account.dto;

import lombok.Data;

@Data
// 아이디 중복 검사 용 DTO인데 쓸모가 로직을 손봐야할 것 같다.
public class AccountLoginIdDto {
    private String loginId;
}
