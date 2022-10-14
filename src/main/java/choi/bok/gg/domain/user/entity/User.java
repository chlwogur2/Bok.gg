package choi.bok.gg.domain.user.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 내 사이트를 이용하는 회원
 */
@Entity
@Getter
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long id;

    private String userLoginId;

    private String password;

    @Column(name = "summoner_name")
    private String summonerName;    // 아예 회원가입할때부터 각자의 롤 아이디를 적도록 하자

    @Column(name = "account_id")
    private String accountId;   // 회원가입부터 Riot accountId 저장
}
