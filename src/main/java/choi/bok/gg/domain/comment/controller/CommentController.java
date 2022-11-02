package choi.bok.gg.domain.comment.controller;

import choi.bok.gg.domain.comment.dto.CommentPageDto;
import choi.bok.gg.domain.comment.service.CommentService;
import choi.bok.gg.domain.match.dto.MatchResultDto;
import choi.bok.gg.domain.match.service.MatchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class CommentController {

    private final CommentService commentService;

    // AJAX 에서 요청 URL을 /comments/matchId로 보내고, 데이터를 matchId로
    @PostMapping("/comments")
    public String commentsList(Model model, @RequestBody String matchId) {
        System.out.println(matchId);
        commentService.getMatchComments(matchId);

        return "/hello";
    }
}
