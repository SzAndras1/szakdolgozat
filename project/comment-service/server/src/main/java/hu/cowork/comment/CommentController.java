package hu.cowork.comment;

import hu.cowork.comment.service.CommentService;
import hu.cowork.comment_service.CommentApi;
import hu.cowork.comment_service.model.CommentDto;
import hu.cowork.comment_service.model.PageDto;
import hu.cowork.comment_service.model.PageResultDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class CommentController implements CommentApi {

    public static final String COMMENT_API_PATH = "/api/comment/v1/comments";

    private final CommentService commentService;

    @Override
    public ResponseEntity<CommentDto> createComment(CommentDto commentDto) {
        CommentDto savedComment = commentService.createComment(commentDto);

        URI location = ServletUriComponentsBuilder
                .fromPath(COMMENT_API_PATH)
                .path("/{id}")
                .buildAndExpand(commentDto.getId())
                .toUri();

        return ResponseEntity.created(location).body(savedComment);
    }

    @Override
    public ResponseEntity<List<CommentDto>> getAnAdvertEveryComment(Long adId) {
        return ResponseEntity.ok(commentService.getAnAdvertEveryComment(adId));
    }

    @Override
    public ResponseEntity<CommentDto> getComment(Long id) {
        return ResponseEntity.ok(commentService.getComment(id));
    }

    @Override
    public ResponseEntity<PageResultDto> searchComment(PageDto pageDto) {
        return ResponseEntity.ok(commentService.searchComment(pageDto));
    }

    @Override
    public ResponseEntity<CommentDto> updateComment(Long id, CommentDto commentDto) {
        return ResponseEntity.ok(commentService.updateComment(id, commentDto));
    }
}
