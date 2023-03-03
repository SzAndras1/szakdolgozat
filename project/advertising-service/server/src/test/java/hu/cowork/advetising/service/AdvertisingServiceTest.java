package hu.cowork.advetising.service;

import hu.cowork.advertising.entity.Advertising;
import hu.cowork.advertising.mapper.AdvertisingMapperImpl;
import hu.cowork.advertising.model.AdvertisingDto;
import hu.cowork.advertising.repository.AdvertisingRepository;
import hu.cowork.advertising.service.AdvertisingSearchService;
import hu.cowork.advertising.service.AdvertisingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class AdvertisingServiceTest {
    AdvertisingService advertisingService;

    @Mock
    AdvertisingRepository advertisingRepository;

    @Mock
    AdvertisingSearchService advertisingSearchService;

    @BeforeEach
    public void init() {
        advertisingService = new AdvertisingService(advertisingRepository, advertisingSearchService, new AdvertisingMapperImpl());
    }

    @Test
    public void getAdvertisingShouldReturnWithTheCorrectResult() {
        Long id = 1L;
        Advertising advertising = new Advertising(id, 1L, "smth", "smth", 1L, "smth", true, false);
        AdvertisingDto expected = new AdvertisingDto()
                .id(1L)
                .userId(1L)
                .text("smth")
                .email("smth")
                .priority(1L)
                .address("smth")
                .isActive(true)
                .isFavorite(false);

        given(advertisingRepository.findById(id)).willReturn(Optional.of(advertising));

        AdvertisingDto result = advertisingService.getAdvertising(1L);

        assertThat(result, is(equalTo(expected)));
    }

    @Test
    public void getAdvertisingShouldReturnWithException(){
        Long id = 1L;
        given(advertisingRepository.findById(id)).willThrow(new NullPointerException());

        assertThrows(NullPointerException.class, () ->advertisingService.getAdvertising(id));
    }
}
