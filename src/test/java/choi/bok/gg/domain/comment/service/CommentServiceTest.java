package choi.bok.gg.domain.comment.service;

import choi.bok.gg.domain.account.dto.AccountLoginDto;
import choi.bok.gg.domain.comment.dto.CommentPageDto;
import choi.bok.gg.domain.comment.dto.CommentWriteDto;
import choi.bok.gg.domain.comment.repository.CommentRepository;
import choi.bok.gg.domain.match.entity.Match;
import choi.bok.gg.domain.match.repository.MatchRepository;
import choi.bok.gg.domain.account.entity.Account;
import choi.bok.gg.domain.account.repository.AccountRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@SpringBootTest
class CommentServiceTest {

    @Autowired
    CommentService commentService;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    MatchRepository matchRepository;


    @Test
    void writeComment() {

        Account account = Account.builder()
                .summonerName("wqw")
                .password("1234")
                .userLoginId("hello").build();
        Match match = Match.builder()
                .matchId("wqw").build();

        accountRepository.save(account);
        matchRepository.save(match);

        CommentWriteDto commentWriteDto = new CommentWriteDto("wqw","wqw","hello",0);
        commentService.writeComment(commentWriteDto);

        Assertions.assertThat(
                commentRepository.findCommentsByAccount(
                        accountRepository.findBySummonerName("wqw").get()).get(0).getContent())
                .isEqualTo("hello");
    }

    @Test
    void fiveComment() {
        Account account = Account.builder()
                .summonerName("wqw")
                .password("1234")
                .userLoginId("hello").build();
        Match match = Match.builder()
                .matchId("wqw").build();

        AccountLoginDto accountLoginDto = new AccountLoginDto("hello", "wqw", "1234");

        accountRepository.save(account);
        matchRepository.save(match);

        for (int i = 0; i < 10; i++) {
            CommentWriteDto commentWriteDto = new CommentWriteDto("wqw","wqw", Integer.toString(i),0);
            commentService.writeComment(commentWriteDto);
        }

        Page<CommentPageDto> recentFiveComments = commentService.findRecentFiveComments(accountLoginDto);


        Assertions.assertThat(recentFiveComments.getNumber()).isEqualTo(0);
        for (CommentPageDto c : recentFiveComments) System.out.println("content = " + c.getContent());
        Assertions.assertThat(recentFiveComments.getSize()).isEqualTo(5);
        Assertions.assertThat(recentFiveComments.getTotalPages()).isEqualTo(2);
    }

    @Test
    void deleteComment() {
    }
}