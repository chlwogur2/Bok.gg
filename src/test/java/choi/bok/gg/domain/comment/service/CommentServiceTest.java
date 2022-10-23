package choi.bok.gg.domain.comment.service;

import choi.bok.gg.domain.comment.dto.CommentWriteDto;
import choi.bok.gg.domain.comment.repository.CommentRepository;
import choi.bok.gg.domain.match.entity.Match;
import choi.bok.gg.domain.match.repository.MatchRepository;
import choi.bok.gg.domain.account.entity.Account;
import choi.bok.gg.domain.account.repository.AccountRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


@Transactional
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
                .summonerName("wqw").build();
        Match match = Match.builder()
                .matchId("wqw").build();

        accountRepository.save(account);
        matchRepository.save(match);

        CommentWriteDto commentWriteDto = new CommentWriteDto("wqw","wqw","hello",0);
        commentService.writeComment(commentWriteDto);

        Assertions.assertThat(
                commentRepository.findCommentsByAccount(
                        accountRepository.findUserBySummonerName("wqw").get()).get(0).getContent())
                .isEqualTo("hello");
    }

    @Test
    void deleteComment() {
    }
}