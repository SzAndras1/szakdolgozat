package hu.cowork.gateway.comment.service;

import hu.cowork.comment_service.CommentApiClient;
import hu.cowork.cowork_gateway.model.CommentDto;
import hu.cowork.cowork_gateway.model.CommentPageResultDto;
import hu.cowork.cowork_gateway.model.PageDto;
import hu.cowork.gateway.comment.mapper.CommentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentApiClient commentApiClient;

    private final CommentMapper commentMapper;

    public CommentDto getComment(Long id) {
        return commentMapper.toReceiveCommentDto(
                commentApiClient.getComment(id).getBody());
    }

    public List<CommentDto> getAnAdvertEveryComment(Long adId) {
        return commentApiClient.getAnAdvertEveryComment(adId).getBody().stream()
                        .map(commentMapper::toReceiveCommentDto)
                        .collect(Collectors.toList());
    }

    public CommentDto createComment(CommentDto commentDto) {
        return commentMapper.toReceiveCommentDto(
                commentApiClient.createComment(commentMapper.toSendCommentDto(commentDto)).getBody());
    }

    public CommentPageResultDto searchComment(PageDto pageDto) {
        hu.cowork.comment_service.model.PageDto toSend = commentMapper.toSendPageDto(pageDto);
        return commentMapper.toReceivePageResultDto(commentApiClient.searchComment(toSend).getBody());
    }

    public CommentDto updateComment(CommentDto commentDto) {
        return commentMapper.toReceiveCommentDto(
                commentApiClient.updateComment(commentMapper.toSendCommentDto(commentDto)).getBody());
    }

    public CommentDto deleteComment(Long id) {
        return commentMapper.toReceiveCommentDto(
                commentApiClient.deleteComment(id).getBody());
    }
}
