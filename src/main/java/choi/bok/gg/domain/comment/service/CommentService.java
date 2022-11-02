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

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final AccountRepository accountRepository;
    private final MatchRepository matchRepository;



    public void writeComment(@NotNull CommentWriteDto commentWriteDto) {
        commentRepository.save(Comment.builder()
                .account(accountRepository.findBySummonerName(commentWriteDto.getSummonerName()).get())
                .match(matchRepository.findMatchByMatchId(commentWriteDto.getMatchId()).get())
                .content(commentWriteDto.getContent())
                .depth(commentWriteDto.getDepth()).build());
    }

    public void editComment(@NotNull CommentEditDto commentEditDto) {

    }

    public void deleteComment(@NotNull CommentDeleteDto commentDeleteDto) {
        commentRepository.delete(commentRepository.findById(commentDeleteDto.getCommentId()).get());
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
