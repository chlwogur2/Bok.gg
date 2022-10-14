package choi.bok.gg.domain.user.service;

import choi.bok.gg.domain.summoner.service.api.SummonerV4Api;
import choi.bok.gg.domain.user.dto.UserSignUpDto;
import choi.bok.gg.domain.user.entity.User;
import choi.bok.gg.domain.user.repository.UserRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import javax.validation.Valid;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final SummonerV4Api summonerV4Api;
    /**
     * 회원가입 메소드
     */
    public UserSignUpDto signUp(UserSignUpDto userSignUpDto) throws IOException {
        String encoded = URLEncoder.encode(userSignUpDto.getSummonerName(), StandardCharsets.UTF_8);
        // 가입 로직 성공하면 유저 저장
        userRepository.save(User.builder()
                .userLoginId(userSignUpDto.getUserLoginId())
                .password(userSignUpDto.getPassword())
                .summonerName(encoded)
                .accountId(summonerV4Api.summonerDtoBySummonerName(userSignUpDto.getSummonerName()).getAccountId())
                .build());

        return userSignUpDto;
    }

    // 로그인 ID가 DB에 존재하는지 검색
    public Optional<User> findUserByLoginId(String userLoginId){
        return userRepository.findUserByUserLoginId(userLoginId);
    }

    // 로그인 ID로 소환사명 검색
    public String findSummonerName(String userLoginId){
        return userRepository.findUserByUserLoginId(userLoginId).get().getSummonerName();
    }

    // 로그인 ID로 user accountID 검색
    public String findAccountId(String userLoginId) {
        return userRepository.findUserByUserLoginId(userLoginId).get().getAccountId();
    }
}
