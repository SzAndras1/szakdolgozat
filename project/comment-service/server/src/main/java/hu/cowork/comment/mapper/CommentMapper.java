package hu.cowork.comment.mapper;

import hu.cowork.comment.entity.Comment;
import hu.cowork.comment_service.model.CommentDto;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

@Mapper(componentModel = "spring")
public interface CommentMapper extends Converter<Comment, CommentDto> {

    CommentDto toDto(Comment comment);

    Comment toEntity(CommentDto commentDto);
}
