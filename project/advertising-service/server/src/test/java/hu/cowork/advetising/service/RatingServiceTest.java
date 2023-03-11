package hu.cowork.advetising.service;

import hu.cowork.advertising.mapper.RatingMapperImpl;
import hu.cowork.advertising.repository.RatingRepository;
import hu.cowork.advertising.service.RatingService;
import hu.cowork.advetising.fixture.RatingFixtures;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class RatingServiceTest {

    RatingService ratingService;

    @Mock
    RatingRepository ratingRepository;

    @BeforeEach
    public void init() {
            ratingService = new RatingService(ratingRepository, new RatingMapperImpl());
    }

    @Test
    public void getOverAllRatingShouldReturnWithTheCorrectResult() {
        Long userId = 1L;
        given(ratingRepository.findAllByUserId(userId)).willReturn(List.of(
                RatingFixtures.simpleRating(1L),
                RatingFixtures.simpleRating(2L),
                RatingFixtures.simpleRating(3L)
        ));

        Integer result = ratingService.getOverAllRating(userId);

        assertThat(result, is(5));
    }

}
