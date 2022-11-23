package choi.bok.gg.global.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import java.util.Base64;
import java.util.Date;

/**
 * 토큰 생성, 유효성 검증 함수 구현 클래스
 * JWT 라이브러리 이용
 */
@Slf4j
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String secretKey;

    private final Long tokenValidTime = 60L * 60L * 1000L;    // 1시간
    Date now = new Date();  // 현재 시각

    // 객체 초기화 시, secretKey를 Base64인코딩
    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    // 토큰 생성
    public String createToken(String userLoginId) {
        Claims claims = Jwts.claims().setSubject(userLoginId);  // JWT Payload에 저장되는 정보 단위
        // 권한 정보는 아직 x
        return Jwts.builder()
                .setClaims(claims)  // 토큰에 들어가는 정보
                .setExpiration(new Date(now.getTime() + tokenValidTime))    // 현재 시간에 1시간 더한 것이 만료시간
                .setIssuedAt(now)   // 토큰 발행 시간
                .signWith(SignatureAlgorithm.HS256, secretKey).compact();
    }

    // 토큰에서 회원 정보 추출
    public String getAuthentication(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }
}
