package hu.cowork.advertising.service;

import hu.cowork.advertising.entity.Rating;
import hu.cowork.advertising.mapper.RatingMapper;
import hu.cowork.advertising.model.RatingDto;
import hu.cowork.advertising.repository.RatingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RatingService {

    private final RatingRepository ratingRepository;

    private final RatingMapper ratingMapper;

    public RatingDto createRating(RatingDto ratingDto){
        Rating savedRating = ratingMapper.toEntity(ratingDto);
        return ratingMapper.toDto(ratingRepository.save(savedRating));
    }

    public Integer getOverAllRating(Long userId) {
        List<Rating> ratingList = ratingRepository.findAllByUserId(userId);
        int sum = 0;
        for (Rating rating : ratingList) {
            sum += rating.getRatingValue();
        }
        return sum / ratingList.size();
    }
}
