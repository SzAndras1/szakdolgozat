package hu.cowork.gateway.advertising.service;

import hu.cowork.advertising.RatingApiClient;
import hu.cowork.cowork_gateway.model.RatingDto;
import hu.cowork.gateway.advertising.mapper.RatingMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RatingService {

    private final RatingApiClient ratingApiClient;

    private final RatingMapper ratingMapper;

    public RatingDto createRating(RatingDto ratingDto) {
        return ratingMapper.toReceiveRatingDto(
                ratingApiClient.createRating(ratingMapper.toSendRatingDto(ratingDto)).getBody());
    }

    public Integer getOverallRating(Long userId) {
        return ratingApiClient.getOverallRating(userId).getBody();
    }

    public RatingDto updateRating(RatingDto ratingDto) {
        return ratingMapper.toReceiveRatingDto(
                ratingApiClient.updateRating(ratingMapper.toSendRatingDto(ratingDto)).getBody());
    }

    public RatingDto deleteRating(RatingDto ratingDto) {
        return ratingMapper.toReceiveRatingDto(
                ratingApiClient.deleteRating(ratingMapper.toSendRatingDto(ratingDto)).getBody());
    }
}
