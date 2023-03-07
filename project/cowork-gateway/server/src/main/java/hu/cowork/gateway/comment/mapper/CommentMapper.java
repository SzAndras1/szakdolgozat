package hu.cowork.gateway.comment.mapper;

import hu.cowork.cowork_gateway.model.CommentDto;
import hu.cowork.cowork_gateway.model.PageDto;
import hu.cowork.comment_service.model.PageResultDto;
import org.mapstruct.Mapper;
import hu.cowork.cowork_gateway.model.CommentPageResultDto;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    hu.cowork.comment_service.model.CommentDto toSendCommentDto(CommentDto commentDto);

    CommentDto toReceiveCommentDto(hu.cowork.comment_service.model.CommentDto commentDto);

    hu.cowork.comment_service.model.PageDto toSendPageDto(PageDto pageDto);

    CommentPageResultDto toReceivePageResultDto(PageResultDto pageResultDto);
}
