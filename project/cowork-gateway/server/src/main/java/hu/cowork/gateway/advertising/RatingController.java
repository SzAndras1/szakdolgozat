package hu.cowork.gateway.advertising;

import hu.cowork.cowork_gateway.RatingApi;
import hu.cowork.cowork_gateway.model.RatingDto;
import hu.cowork.gateway.advertising.service.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class RatingController implements RatingApi {

    private final RatingService ratingService;

    @Override
    public ResponseEntity<List<RatingDto>> getAdRatings(Long userId) {
        return ResponseEntity.ok(ratingService.getAdRatings(userId));
    }

    @Override
    public ResponseEntity<Integer> getOverallRating(Long userId) {
        return ResponseEntity.ok(ratingService.getOverallRating(userId));
    }

    @Override
    public ResponseEntity<RatingDto> createRating(RatingDto ratingDto) {
        return ResponseEntity.ok(ratingService.createRating(ratingDto));
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
