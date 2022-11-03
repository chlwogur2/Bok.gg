package choi.bok.gg.domain.comment.controller;

import choi.bok.gg.domain.comment.dto.CommentPageDto;
import choi.bok.gg.domain.comment.service.CommentService;
import choi.bok.gg.domain.match.dto.MatchResultDto;
import choi.bok.gg.domain.match.exception.NoMatchFoundException;
import choi.bok.gg.domain.match.service.MatchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@Slf4j
public class CommentController {

    private final CommentService commentService;

    // AJAX 에서 요청 URL을 /comments/matchId로 보내고, 데이터를 matchId로
    @PostMapping("/comments")
    public String commentsList(Model model, @RequestBody String matchId)  {
        String id = matchId.substring(0, matchId.length() - 1);

        try {
            // matchId 맨 뒤에 등호가 포함돼서 나오는 현상때문에 substring 으로 문자열 자름
            Page<CommentPageDto> comments = commentService.getMatchComments(id);
            model.addAttribute("comments", comments);
            return "home/loginHome :: #commentList";

        } catch (NoMatchFoundException e) {
            log.info("예외 발생");
            String noComment = "작성된 댓글이 없습니다.";
            model.addAttribute("matchId", id);
            model.addAttribute("noComment", noComment);
            return "noComment";
        }
    }
}
