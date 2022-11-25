package choi.bok.gg.global.filter;

import choi.bok.gg.global.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Filter를 중첩 호출한 경우 매번 Filter의 내용이 수행되는 것을 방지하기 위해 OncePerRequestFilter도 있다.
 */
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 헤더에서 토큰 받아오기 ("X-AUTH-TOKEN")
        String token = jwtTokenProvider.resolveToken(request);

        // 토큰 유효성 검사
        if (token != null && jwtTokenProvider.validateToken(token)){
            // 토큰으로부터 유저 정보 받아옴
            Authentication authentication = jwtTokenProvider.getAuthentication(token);
            // SecurityContext 에 객체 저장
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }
}
