package choi.bok.gg.domain.comment.repository;

import choi.bok.gg.domain.comment.entity.Comment;
import choi.bok.gg.domain.account.entity.Account;
import choi.bok.gg.domain.account.repository.AccountRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CommentRepositoryTest {

    @Autowired
    CommentRepository commentRepository;
    @Autowired
    AccountRepository accountRepository;

    @Test
    void findCommentsByUser() {
        Account account = Account.builder().build();
        accountRepository.save(account);
        Comment comment = Comment.builder()
                .account(account).build();

        commentRepository.save(comment);

        List<Comment> commentsByUser = commentRepository.findCommentsByAccount(account);

        assertThat(commentsByUser.contains(comment));
    }

    @Test
    void findTop5ByOrderByIdDesc() {

    }
}