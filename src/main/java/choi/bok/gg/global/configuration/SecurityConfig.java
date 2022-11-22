package choi.bok.gg.global.configuration;

import choi.bok.gg.global.jwt.JwtTokenProvider;
import choi.bok.gg.global.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@PropertySource("classpath:jwt.properties")
public class SecurityConfig {
    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService();
    }

    @Bean
    public JwtTokenProvider jwtTokenProvider(){
        return new JwtTokenProvider();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
//                .csrf().ignoringAntMatchers("/")
                    .authorizeRequests()
                    .antMatchers("/", "/login", "/users/signup").permitAll()
                    .antMatchers("/users/admin").hasRole("USER")
                .and()
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

