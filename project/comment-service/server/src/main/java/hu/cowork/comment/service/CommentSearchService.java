package hu.cowork.comment.service;

import hu.cowork.comment.entity.Comment;
import hu.cowork.comment.mapper.CommentMapper;
import hu.cowork.comment.repository.CommentRepository;
import hu.cowork.comment_service.model.PageDto;
import hu.cowork.comment_service.model.PageFilterDto;
import hu.cowork.comment_service.model.PageResultDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
@Service
public class CommentSearchService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    public PageResultDto search(PageDto pageDto) {
        handleDefaultValues(pageDto);
        Map<String, String> filters = pageDto.getFilters().stream()
                .collect(Collectors.toMap(PageFilterDto::getField, PageFilterDto::getFilter));

        Page<Comment> searchHits = commentRepository.search(
                filters.getOrDefault("id", null),
                filters.getOrDefault("userId", null),
                filters.getOrDefault("text", null),
                PageRequest.of(pageDto.getPage(), pageDto.getSize())
        );

        return new PageResultDto()
                .content(searchHits.get()
                        .map(commentMapper::toDto)
                        .collect(toList()))
                .totalElements(searchHits.getTotalElements());
    }

    private void handleDefaultValues(PageDto pageDto) {
        if(pageDto.getPage() == null) {
            pageDto.setPage(0);
        }
        if(pageDto.getSize() == null) {
            pageDto.setSize(20);
        }
        if(pageDto.getFilters() == null) {
            pageDto.setFilters(emptyList());
        }
    }
}
