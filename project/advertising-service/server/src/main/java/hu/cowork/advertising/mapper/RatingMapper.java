package hu.cowork.advertising.mapper;

import hu.cowork.advertising.entity.Rating;
import hu.cowork.advertising.model.RatingDto;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

@Mapper(componentModel = "spring")
public interface RatingMapper extends Converter<Rating, RatingDto> {
    RatingDto toDto(Rating rating);

    Rating toEntity(RatingDto ratingDto);

}
