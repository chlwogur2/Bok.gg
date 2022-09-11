package choi.bok.gg.domain.likes.repository;

import choi.bok.gg.domain.likes.entity.Likes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikesRepository extends JpaRepository<Likes, Long> {
}
