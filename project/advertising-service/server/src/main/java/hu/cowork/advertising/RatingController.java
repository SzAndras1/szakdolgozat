package hu.cowork.advertising;

import hu.cowork.advertising.model.RatingDto;
import hu.cowork.advertising.service.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class RatingController implements RatingApi {

    public static final String RATING_API_PATH = "/api/advertising/v1/ratings";

    private final RatingService ratingService;

    //region Get methods

    @Override
    public ResponseEntity<Float> getOverallRating(Long userId) {
        return ResponseEntity.ok(ratingService.getOverAllRating(userId));
    }
    @Override
    public ResponseEntity<List<RatingDto>> getAdRatings(Long id) {
        return ResponseEntity.ok(ratingService.getAdRatings(id));
    }

    //endregion

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

    @Override
    public ResponseEntity<RatingDto> updateRating(RatingDto ratingDto) {
        return ResponseEntity.ok(ratingService.updateRating(ratingDto));
    }

    @Override
    public ResponseEntity<RatingDto> deleteRating(Long id) {
        return ResponseEntity.ok(ratingService.deleteRating(id));
    }
}
