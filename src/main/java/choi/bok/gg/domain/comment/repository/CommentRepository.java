package choi.bok.gg.domain.comment.repository;

import choi.bok.gg.domain.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
