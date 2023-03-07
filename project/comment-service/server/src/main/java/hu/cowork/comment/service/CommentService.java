package hu.cowork.comment.service;

import hu.cowork.comment.entity.Comment;
import hu.cowork.comment.mapper.CommentMapper;
import hu.cowork.comment.repository.CommentRepository;
import hu.cowork.comment_service.model.CommentDto;
import hu.cowork.comment_service.model.PageDto;
import hu.cowork.comment_service.model.PageResultDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final CommentSearchService commentSearchService;
    private final CommentMapper commentMapper;

    public CommentDto getComment(Long id) {
        return commentMapper.toDto(commentRepository.findByUserId(id).get());
    }

    public List<CommentDto> getAnAdvertEveryComment(Long adId) {
        return commentRepository.findAllByAdId(adId).stream()
                .map(commentMapper::toDto)
                .collect(Collectors.toList());
    }

    public CommentDto createComment(CommentDto commentDto) {
        Comment comment = commentMapper.toEntity(commentDto);
        return commentMapper.toDto(commentRepository.save(comment));
    }

    public PageResultDto searchComment(PageDto pageDto) {
        return commentSearchService.search(pageDto);
    }

    public CommentDto updateComment(CommentDto commentDto) {
        Comment comment = commentMapper.toEntity(commentDto);
        return commentMapper.toDto(commentRepository.save(comment));
    }

    public CommentDto deleteComment(Long id) {
        CommentDto commentDto = commentMapper.toDto(commentRepository.findById(id).get());
        commentRepository.deleteById(id);
        return commentDto;
    }
}
