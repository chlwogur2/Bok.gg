package choi.bok.gg.domain.comment.service;

import choi.bok.gg.domain.comment.dto.CommentWriteDto;
import choi.bok.gg.domain.comment.entity.Comment;
import choi.bok.gg.domain.comment.repository.CommentRepository;
import choi.bok.gg.domain.match.entity.Match;
import choi.bok.gg.domain.match.repository.MatchRepository;
import choi.bok.gg.domain.user.entity.User;
import choi.bok.gg.domain.user.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CommentServiceTest {

    @Autowired
    CommentService commentService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    MatchRepository matchRepository;


    @Test
    void writeComment() {

        User user = User.builder()
                .summonerName("wqw").build();
        Match match = Match.builder()
                .matchId("wqw").build();

        userRepository.save(user);
        matchRepository.save(match);

        CommentWriteDto commentWriteDto = new CommentWriteDto("wqw","wqw","hello",0);
        commentService.writeComment(commentWriteDto);

        Assertions.assertThat(commentRepository.findCommentsByUser(userRepository.findUserBySummonerName("wqw").get()).get(0).getContent()).isEqualTo("hello");
    }

    @Test
    void deleteComment() {
    }
}