package choi.bok.gg.global.configuration;

import choi.bok.gg.global.filter.JwtAuthenticationFilter;
import choi.bok.gg.global.jwt.JwtTokenProvider;
import choi.bok.gg.global.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
@PropertySource("classpath:jwt.properties")
public class SecurityConfig {

    private final JwtTokenProvider jwtTokenProvider;
    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
//                .csrf().ignoringAntMatchers("/")
                .authorizeRequests()
                .antMatchers("/", "/login", "/users/signup").permitAll()
                .antMatchers("/users/admin").hasRole("USER")
                .and()
                    .addFilter(new JwtAuthenticationFilter(jwtTokenProvider))
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login_proc")
                .usernameParameter("userLoginId")
                .passwordParameter("password")
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .and();
        return http.build();
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}

