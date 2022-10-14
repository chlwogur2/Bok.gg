package choi.bok.gg.domain.comment.repository;

import choi.bok.gg.domain.comment.dto.CommentWriteDto;
import choi.bok.gg.domain.comment.service.CommentService;
import choi.bok.gg.domain.match.entity.Match;
import choi.bok.gg.domain.match.repository.MatchRepository;
import choi.bok.gg.domain.user.dto.UserLoginDto;
import choi.bok.gg.domain.user.entity.User;
import choi.bok.gg.domain.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CommentRepositoryTest {

    @Autowired
    CommentRepository commentRepository;
    @Autowired
    CommentService commentService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    MatchRepository matchRepository;

    @Test
    void findCommentsByUser() {
    }

    @Test
    void findTop5ByOrderByIdDesc() {

        ArrayList<CommentWriteDto> comments = new ArrayList<>();

        User user = User.builder()
                .userLoginId("temp")
                .summonerName("hello").build();
        Match match = Match.builder()
                .matchId("matchId").build();

        UserLoginDto userLoginDto = new UserLoginDto("temp","hello","1234");

        userRepository.save(user);
        matchRepository.save(match);

        for (int i = 1; i <= 10; i++) {
            comments.add(new CommentWriteDto("hello", "matchId", "abcde" + i, 0));
        }

        comments.forEach(c -> commentService.writeComment(c));

        commentService.findRecentFiveComments(userLoginDto).forEach(c -> System.out.println(c.getContent()));

    }
}