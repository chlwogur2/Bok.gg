package choi.bok.gg.domain.comment.service;

import choi.bok.gg.domain.comment.dto.CommentDeleteDto;
import choi.bok.gg.domain.comment.dto.CommentWriteDto;
import choi.bok.gg.domain.comment.entity.Comment;
import choi.bok.gg.domain.comment.repository.CommentRepository;
import choi.bok.gg.domain.match.entity.Match;
import choi.bok.gg.domain.match.repository.MatchRepository;
import choi.bok.gg.domain.user.entity.User;
import choi.bok.gg.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final MatchRepository matchRepository;

    public void writeComment(CommentWriteDto commentWriteDto) {
        commentRepository.save(Comment.builder()
                .user(userRepository.findUserBySummonerName(commentWriteDto.getSummonerName()).get())
                .match(matchRepository.findMatchByMatchId(commentWriteDto.getMatchId()).get())
                .content(commentWriteDto.getContent())
                .depth(commentWriteDto.getDepth()).build());
    }

    public void editComment() {

    }

    public void deleteComment(CommentDeleteDto commentDeleteDto) {
        commentRepository.delete(commentRepository.findById(commentDeleteDto.getCommentId()).get());
    }
}
