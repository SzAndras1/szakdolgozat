package hu.cowork.advertising.service;

import hu.cowork.advertising.entity.Rating;
import hu.cowork.advertising.mapper.RatingMapper;
import hu.cowork.advertising.model.RatingDto;
import hu.cowork.advertising.repository.RatingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class RatingService {

    private final RatingRepository ratingRepository;

    private final RatingMapper ratingMapper;

    public float getOverAllRating(Long userId) {
        List<Rating> ratingList = ratingRepository.findAllByUserId(userId);
        if (ratingList.isEmpty()) {
            return 0;
        }
        float sum = 0;
        for (Rating rating : ratingList) {
            sum += rating.getRatingValue();
        }
        return sum / ratingList.size();
    }

    public List<RatingDto> getAdRatings(Long userId) {
        return ratingRepository.findAllByUserId(userId).stream()
                .map(ratingMapper::toDto)
                .collect(Collectors.toList());
    }

    public RatingDto createRating(RatingDto ratingDto){
        Rating savedRating = ratingMapper.toEntity(ratingDto);
        return ratingMapper.toDto(ratingRepository.save(savedRating));
    }

    public RatingDto updateRating(RatingDto ratingDto) {
        Rating updatedRating = ratingMapper.toEntity(ratingDto);
        return ratingMapper.toDto(ratingRepository.save(updatedRating));
    }

    public RatingDto deleteRating(Long id) {
        RatingDto deletedRating = ratingMapper.toDto(ratingRepository.findById(id).get());
        ratingRepository.deleteById(id);
        return deletedRating;
    }
}
