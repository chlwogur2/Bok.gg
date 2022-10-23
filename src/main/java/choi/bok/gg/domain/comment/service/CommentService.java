package choi.bok.gg.domain.comment.service;

import choi.bok.gg.domain.comment.dto.CommentDeleteDto;
import choi.bok.gg.domain.comment.dto.CommentPageDto;
import choi.bok.gg.domain.comment.dto.CommentWriteDto;
import choi.bok.gg.domain.comment.entity.Comment;
import choi.bok.gg.domain.comment.repository.CommentRepository;
import choi.bok.gg.domain.match.repository.MatchRepository;
import choi.bok.gg.domain.account.dto.AccountLoginDto;
import choi.bok.gg.domain.account.entity.Account;
import choi.bok.gg.domain.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
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

    public void writeComment(CommentWriteDto commentWriteDto) {
        commentRepository.save(Comment.builder()
                .account(accountRepository.findUserBySummonerName(commentWriteDto.getSummonerName()).get())
                .match(matchRepository.findMatchByMatchId(commentWriteDto.getMatchId()).get())
                .content(commentWriteDto.getContent())
                .depth(commentWriteDto.getDepth()).build());
    }

    public void editComment() {

    }

    public void deleteComment(CommentDeleteDto commentDeleteDto) {
        commentRepository.delete(commentRepository.findById(commentDeleteDto.getCommentId()).get());
    }

    public Page<CommentPageDto> findRecentFiveComments(AccountLoginDto accountLoginDto){
        Optional<Account> user = accountRepository.findUserByUserLoginId(accountLoginDto.getUserLoginId());
        PageRequest pageRequest = PageRequest.of(0, 5, Sort.by(Sort.Order.desc("id")));
        return commentRepository.findCommentsPaging(user.get(), pageRequest).map(CommentPageDto::from);
    }
}
