package choi.bok.gg.domain.comment.controller;

import choi.bok.gg.domain.account.dto.AccountLoginDto;
import choi.bok.gg.domain.comment.dto.CommentDeleteDto;
import choi.bok.gg.domain.comment.dto.CommentPageDto;
import choi.bok.gg.domain.comment.dto.CommentWriteDto;
import choi.bok.gg.domain.comment.service.CommentService;
import choi.bok.gg.domain.match.exception.NoMatchFoundException;
import choi.bok.gg.domain.match.service.MatchService;
import choi.bok.gg.global.annotation.AuthUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/comments")
@Slf4j
public class CommentController {

    private final CommentService commentService;
    private final MatchService matchService;

    // AJAX 에서 요청 URL을 /comments로 보내고, 데이터를 matchId로
    @PostMapping
    public String commentsList(Model model, @RequestBody String matchId)  {
        String id = matchId.substring(0, matchId.length() - 1);

        try {
            // matchId 맨 뒤에 등호가 포함돼서 나오는 현상때문에 substring 으로 문자열 자름
            Page<CommentPageDto> comments = commentService.getMatchComments(id);
            log.info("예외 발생 안함");
            model.addAttribute("comments", comments);
            model.addAttribute("matchId", id);
            return "comment/commentList";

        } catch (NoMatchFoundException e) {
            log.info("예외 발생");
            String noComment = "작성된 댓글이 없습니다.";
            model.addAttribute("matchId", id);
            model.addAttribute("noComment", noComment);
            return "comment/noComment";
        }
    }

    @PostMapping("/first")
    public String firstComment(@AuthUser AccountLoginDto accountLoginDto, @RequestBody Map<String, Object> commentDto, Model model) {
        log.info("firstComment 함수 실행");

        CommentWriteDto c = CommentWriteDto.builder()
                .summonerName(accountLoginDto.getSummonerName())
                .matchId((String) commentDto.get("matchId"))
                .content((String) commentDto.get("content")).build();

        // 매치 저장
        matchService.saveMatch((String) commentDto.get("matchId"));

        // 댓글 저장
        commentService.writeComment(c);

        model.addAttribute("comments", commentService.getMatchComments(c.getMatchId()));
        model.addAttribute("matchId",  commentDto.get("matchId"));
        return "comment/commentList";
    }

    @DeleteMapping
    public String deleteComment(@RequestBody Map<String, Object> commentDeleteDto) {
        log.info("deleteComment 실행");
        CommentDeleteDto c = CommentDeleteDto.builder()
                .commentId(Long.valueOf((String) commentDeleteDto.get("commentId")))
                .matchId((String) commentDeleteDto.get("matchId")).build();

        commentService.deleteComment(c);
        return "comment/commentList";
    }

//    @PostMapping("/new")
//    public String newComment() {
//
//    }
}
