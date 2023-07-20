package hu.cowork.gateway.advertising.service;

import hu.cowork.advertising.RatingApiClient;
import hu.cowork.cowork_gateway.model.RatingDto;
import hu.cowork.gateway.advertising.mapper.RatingMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class RatingService {

    private final RatingApiClient ratingApiClient;

    private final RatingMapper ratingMapper;

    public float getOverallRating(Long userId) {
        return ratingApiClient.getOverallRating(userId).getBody();
    }

    public List<RatingDto> getAdRatings(Long userId) {
        return ratingApiClient.getAdRatings(userId).getBody().stream()
                .map(ratingMapper::toReceiveRatingDto)
                .collect(Collectors.toList());
    }

    public RatingDto createRating(RatingDto ratingDto) {
        return ratingMapper.toReceiveRatingDto(
                ratingApiClient.createRating(ratingMapper.toSendRatingDto(ratingDto)).getBody());
    }

    public RatingDto updateRating(RatingDto ratingDto) {
        return ratingMapper.toReceiveRatingDto(
                ratingApiClient.updateRating(ratingMapper.toSendRatingDto(ratingDto)).getBody());
    }

    public RatingDto deleteRating(Long id) {
        return ratingMapper.toReceiveRatingDto(
                ratingApiClient.deleteRating(id).getBody());
    }
}
