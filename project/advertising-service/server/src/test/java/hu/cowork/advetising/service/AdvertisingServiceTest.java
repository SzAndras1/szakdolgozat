package hu.cowork.advetising.service;

import hu.cowork.advertising.entity.Advertising;
import hu.cowork.advertising.mapper.AdvertisingMapper;
import hu.cowork.advertising.mapper.AdvertisingMapperImpl;
import hu.cowork.advertising.model.AdvertisingDto;
import hu.cowork.advertising.repository.AdvertisingRepository;
import hu.cowork.advertising.service.AdvertisingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class AdvertisingServiceTest {
    @InjectMocks
    AdvertisingService advertisingService;

    @Mock
    AdvertisingRepository advertisingRepository;

    AdvertisingMapper advertisingMapper;

    @BeforeEach
    public void init() { advertisingMapper = new AdvertisingMapperImpl();}

    @Test
    public void getAdvertisingShouldReturnWithTheCorrectResult() {
        Long id = 1L;
        given(advertisingRepository.findById(id)).willReturn(
                Optional.of(new Advertising(id, 1L, "smth", "smth", 1L, "smth", true))
        );

        AdvertisingDto result = advertisingService.getAdvertising(1L);

        assertThat(result.getId(), is(id));
    }

    @Test
    public void getAdvertisingShouldReturnWithException(){
        Long id = 1L;
        given(advertisingRepository.findById(id)).willThrow(new NullPointerException());

        assertThrows(NullPointerException.class, () ->advertisingService.getAdvertising(id));
    }
}
