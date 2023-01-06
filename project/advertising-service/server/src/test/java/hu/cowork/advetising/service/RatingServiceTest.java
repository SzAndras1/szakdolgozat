package hu.cowork.advetising.service;

import hu.cowork.advertising.entity.Rating;
import hu.cowork.advertising.mapper.RatingMapper;
import hu.cowork.advertising.mapper.RatingMapperImpl;
import hu.cowork.advertising.repository.RatingRepository;
import hu.cowork.advertising.service.RatingService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class RatingServiceTest {

    @InjectMocks
    RatingService ratingService;

    @Mock
    RatingRepository ratingRepository;

    RatingMapper ratingMapper;

    @BeforeEach
    public void init() {
        ratingMapper = new RatingMapperImpl();
    }

    @Test
    public void getOverAllRatingShouldReturnWithTheCorrectResult() {
        Long userId = 1L;
        given(ratingRepository.findAllByUserId(userId)).willReturn(List.of(
                new Rating(1L, userId, 1),
                new Rating(2L, userId, 2),
                new Rating(3L, userId, 0)
        ));

        Integer result = ratingService.getOverAllRating(userId);

        assertThat(result, is(1));
    }

    @Test
    public void getOverAllRatingShouldReturnWithException() {
        Long userId = 1L;
        given(ratingRepository.findAllByUserId(userId)).willThrow(new NullPointerException());

        assertThrows(NullPointerException.class, () ->ratingService.getOverAllRating(userId));
    }


}
