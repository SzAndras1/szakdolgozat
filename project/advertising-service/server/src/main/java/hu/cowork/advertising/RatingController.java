package hu.cowork.advertising;

import hu.cowork.advertising.model.RatingDto;
import hu.cowork.advertising.service.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RequiredArgsConstructor
@RestController
public class RatingController implements RatingApi {

    public static final String RATING_API_PATH = "/api/advertising/v1/ratings";

    private final RatingService ratingService;

    @Override
    public ResponseEntity<RatingDto> createRating(RatingDto ratingDto) {
        RatingDto savedRating = ratingService.createRating(ratingDto);

        URI location = ServletUriComponentsBuilder
                .fromPath(RATING_API_PATH)
                .path("/{id}")
                .buildAndExpand(ratingDto.getId())
                .toUri();

        return ResponseEntity.created(location).body(savedRating);
    }
}
