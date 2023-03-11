package hu.cowork.advetising.fixture;

import hu.cowork.advertising.entity.Rating;
import hu.cowork.advertising.model.RatingDto;

public class RatingFixtures {
    public static Rating simpleRating(Long id) {
        return new Rating(id, 1L, 5);
    }

    public static RatingDto simpleRatingDto(Long id) {
        return new RatingDto()
                .id(id)
                .userId(1L)
                .ratingValue(5);
    }
}
