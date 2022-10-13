package choi.bok.gg.domain.comment.repository;

import choi.bok.gg.domain.comment.entity.Comment;
import choi.bok.gg.domain.match.entity.Match;
import choi.bok.gg.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("select c from Comment c where c.user = :user ")
    List<Comment> findCommentsByUser(@Param("user") User user);

    Comment findByUserAndMatch(User user, Match match);
}
