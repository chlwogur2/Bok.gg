package choi.bok.gg.global.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;

/**
 * 토큰 생성, 유효성 검증 함수 구현 클래스
 * JWT 라이브러리 이용
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String secretKey;

    // 1시간
    private final Long tokenValidTime = 60L * 60L * 1000L;

    // 현재 시각
    Date now = new Date();

    private final UserDetailsService userDetailsService;

    // 객체 초기화 시, secretKey를 Base64인코딩
    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    // 토큰 생성
    public String createToken(String userLoginId) {
        Claims claims = Jwts.claims().setSubject(userLoginId);  // JWT 토큰 이름
        // 권한 정보는 아직 x
        return Jwts.builder()
                .setClaims(claims)  // 토큰에 들어가는 정보
                .setExpiration(new Date(now.getTime() + tokenValidTime))    // 현재 시간에 1시간 더한 것이 만료시간
                .setIssuedAt(now)   // 토큰 발행 시간
                .signWith(SignatureAlgorithm.HS256, secretKey).compact();
    }


    // 토큰에서 회원 정보 추출
    public String getAccount(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    // 인증 정보 조회
    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(this.getAccount(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    // 토큰 유효성, 만료일자 확인
    public boolean validateToken(String jwtToken) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
            return !claims.getBody().getExpiration().before(new Date());    // 현재 시각보다 만료일이 이전인지 check
        } catch (Exception e) {
            return false;
        }
    }

    // Request Header에서 Token 값 가져오기
    public String resolveToken(HttpServletRequest request) {
        return request.getHeader("X-AUTH-TOKEN");
    }
}
