package hu.cowork.gateway.advertising.mapper;

import hu.cowork.cowork_gateway.model.RatingDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RatingMapper {
    hu.cowork.advertising.model.RatingDto toSendRatingDto (RatingDto ratingDto);

    RatingDto toReceiveRatingDto(hu.cowork.advertising.model.RatingDto ratingDto);
}
