package hu.cowork.gateway.comment;

import hu.cowork.cowork_gateway.CommentApi;
import hu.cowork.cowork_gateway.model.CommentDto;
import hu.cowork.cowork_gateway.model.CommentPageResultDto;
import hu.cowork.cowork_gateway.model.PageDto;
import hu.cowork.gateway.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CommentController implements CommentApi {

    private final CommentService commentService;

    @Override
    public ResponseEntity<CommentDto> getComment(Long id) {
        return ResponseEntity.ok(commentService.getComment(id));
    }

    @Override
    public ResponseEntity<List<CommentDto>> getAnAdvertEveryComment(Long adId) {
        return ResponseEntity.ok(commentService.getAnAdvertEveryComment(adId));
    }

    @Override
    public ResponseEntity<CommentDto> createComment(CommentDto commentDto) {
        return ResponseEntity.ok(commentService.createComment(commentDto));
    }

    @Override
    public ResponseEntity<CommentPageResultDto> searchComment(PageDto pageDto) {
        return ResponseEntity.ok(commentService.searchComment(pageDto));
    }

    @Override
    public ResponseEntity<CommentDto> updateComment(CommentDto commentDto) {
        return ResponseEntity.ok(commentService.updateComment(commentDto));
    }

    @Override
    public ResponseEntity<CommentDto> deleteComment(Long id) {
        return ResponseEntity.ok(commentService.deleteComment(id));
    }
}
