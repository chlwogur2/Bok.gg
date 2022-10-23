package choi.bok.gg.domain.account.entity;

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
@Table(name = "account")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Account {

    @Id @GeneratedValue
    @Column(name = "account_id")
    private Long id;

    @Column(nullable = false)
    private String userLoginId;

    @Column(nullable = false)
    private String password;

    @Column(name = "summoner_name", nullable = false)
    private String summonerName;    // 아예 회원가입할때부터 각자의 롤 아이디를 적도록 하자

    @Column(name = "riot_id")
    private String accountId;   // 회원가입부터 Riot accountId 저장
}
