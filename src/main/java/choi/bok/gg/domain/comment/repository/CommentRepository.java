package choi.bok.gg.domain.comment.repository;

import choi.bok.gg.domain.comment.entity.Comment;
import choi.bok.gg.domain.account.entity.Account;
import choi.bok.gg.domain.match.entity.Match;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("select c from Comment c where c.account = :account ")
    List<Comment> findCommentsByAccount(@Param("account") Account account);

    Page<Comment> findByAccount(Account account, Pageable pageable);

    Page<Comment> findByMatch(Match match, Pageable pageable);
}
