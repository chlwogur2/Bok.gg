package choi.bok.gg.domain.comment.service;

import choi.bok.gg.domain.comment.dto.CommentDeleteDto;
import choi.bok.gg.domain.comment.dto.CommentEditDto;
import choi.bok.gg.domain.comment.dto.CommentPageDto;
import choi.bok.gg.domain.comment.dto.CommentWriteDto;
import choi.bok.gg.domain.comment.entity.Comment;
import choi.bok.gg.domain.comment.repository.CommentRepository;
import choi.bok.gg.domain.match.entity.Match;
import choi.bok.gg.domain.match.exception.NoMatchFoundException;
import choi.bok.gg.domain.match.repository.MatchRepository;
import choi.bok.gg.domain.account.dto.AccountLoginDto;
import choi.bok.gg.domain.account.entity.Account;
import choi.bok.gg.domain.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final AccountRepository accountRepository;
    private final MatchRepository matchRepository;


    @Transactional
    public void writeComment(@NotNull CommentWriteDto commentWriteDto) {
        Match match = matchRepository.findMatchByMatchId(commentWriteDto.getMatchId()).get();
        commentRepository.save(Comment.builder()
                .account(accountRepository.findBySummonerName(commentWriteDto.getSummonerName()).get())
                .match(match)
                .content(commentWriteDto.getContent())
                .depth(commentWriteDto.getDepth()).build());
        match.increaseCommentCount();
    }

    public void editComment(@NotNull CommentEditDto commentEditDto) {

    }

    public void deleteComment(@NotNull CommentDeleteDto commentDeleteDto) {
        Match match = matchRepository.findMatchByMatchId(commentDeleteDto.getMatchId()).get();
        commentRepository.delete(commentRepository.findById(commentDeleteDto.getCommentId()).get());
        match.decreaseCommentCount();
        // 댓글 삭제 시, 매치에 남은 댓글 수가 0이면 매치를 DB에서 삭제
        if (match.getCommentCount() == 0) matchRepository.delete(match);
    }

    // 최근 유저가 작성한 Comment 5개 가져오기
    public Page<CommentPageDto> getRecentFiveComments(@NotNull AccountLoginDto accountLoginDto){
        Optional<Account> user = accountRepository.findByUserLoginId(accountLoginDto.getUserLoginId());
        PageRequest pageRequest = PageRequest.of(0, 5, Sort.by(Sort.Order.desc("id")));
        return commentRepository.findByAccount(user.get(), pageRequest).map(CommentPageDto::from);
    }

    // 각각의 매치에 기록된 Comment 가져오기
    public Page<CommentPageDto> getMatchComments(@NotNull String matchId) {
        Match match = matchRepository.findMatchByMatchId(matchId).orElseThrow(() -> new NoMatchFoundException("작성된 댓글이 없습니다."));
        PageRequest pageRequest = PageRequest.of(0, 5, Sort.by(Sort.Order.desc("id")));
        return commentRepository.findByMatch(match, pageRequest).map(CommentPageDto::from);
    }
}
