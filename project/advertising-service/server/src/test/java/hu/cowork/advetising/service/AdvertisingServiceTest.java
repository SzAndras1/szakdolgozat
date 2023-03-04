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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.*;

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

    // region Get method tests
    @Test
    public void getAdvertisingShouldReturnWithTheCorrectResult() {
        Long id = 1L;
        Advertising advertising = new Advertising(id, 1L, "smth", "smth", 1L, "smth", true, false);
        AdvertisingDto expected = new AdvertisingDto()
                .id(id)
                .userId(1L)
                .text("smth")
                .email("smth")
                .priority(1L)
                .address("smth")
                .isActive(true)
                .isFavorite(false);

        given(advertisingRepository.findById(id)).willReturn(Optional.of(advertising));
        AdvertisingDto result = advertisingService.getAdvertising(id);

        assertThat(result, is(equalTo(expected)));
    }

    @Test
    public void getAdvertisingShouldReturnWithException(){
        Long id = 1L;
        given(advertisingRepository.findById(id)).willThrow(new NullPointerException());

        assertThrows(NullPointerException.class, () ->advertisingService.getAdvertising(id));
    }

    @Test
    public void getUserAdvertisingShouldReturnWithTheCorrectResult(){
        long userId = 1L;
        boolean isActive = false;
        List<Advertising> advertisingList = List.of(
                new Advertising(1L,userId,"smth","smth",1L,"smth",isActive,false),
                new Advertising(2L,userId,"smth","smth",1L,"smth",isActive,false)
        );
        List<AdvertisingDto> expectedList = new ArrayList<>(){{
            add(new AdvertisingDto()
                    .id(1L)
                    .userId(userId)
                    .text("smth")
                    .email("smth")
                    .priority(1L)
                    .address("smth")
                    .isActive(isActive)
                    .isFavorite(false));
            add(new AdvertisingDto()
                    .id(2L)
                    .userId(userId)
                    .text("smth")
                    .email("smth")
                    .priority(1L)
                    .address("smth")
                    .isActive(isActive)
                    .isFavorite(false));
        }};
        when(advertisingRepository.findAllByUserIdAndIsActive(userId, isActive)).thenReturn(advertisingList);

        List<AdvertisingDto> resultList = advertisingService.getUserAdvertising(userId,isActive);

        assertThat(resultList, is(equalTo(expectedList)));
    }

    @Test
    public void getUserAdvertisingShouldReturnWithException(){
        Long userId = 1L;
        boolean isActive = true;
        given(advertisingRepository.findAllByUserIdAndIsActive(userId,isActive)).willThrow(new NullPointerException());

        assertThrows(NullPointerException.class, () ->advertisingService.getUserAdvertising(userId,isActive));
    }

    // endregion

    // region Create method tests

    @Test
    public void createAdvertisingShouldReturnWithTheCorrectResult(){
        Advertising advertising = new Advertising(1L, 1L, "smth", "smth", 1L, "smth", true, false);
        AdvertisingDto expected = new AdvertisingDto()
                .id(1L)
                .userId(1L)
                .text("smth")
                .email("smth")
                .priority(1L)
                .address("smth")
                .isActive(true)
                .isFavorite(false);
        given(advertisingRepository.save(advertising)).willReturn(advertising);

        AdvertisingDto result = advertisingService.createAdvertising(expected);

        assertThat(result, is(equalTo(expected)));
    }

    @Test
    public void createAdvertisingShouldReturnWithException(){
        Advertising advertising = new Advertising(1L, 1L, "smth", "smth", 1L, "smth", true, false);
        AdvertisingDto expected = new AdvertisingDto()
                .id(1L)
                .userId(1L)
                .text("smth")
                .email("smth")
                .priority(1L)
                .address("smth")
                .isActive(true)
                .isFavorite(false);
        given(advertisingRepository.save(advertising)).willThrow(new NullPointerException());

        assertThrows(NullPointerException.class, () ->advertisingService.createAdvertising(expected));
    }

    // endregion

    // region Update method tests

    @Test
    public void updateAdvertisingShouldReturnWithTheCorrectResult(){
        Advertising advertising = new Advertising(1L, 1L, "smth", "smth", 1L, "smth", true, false);
        AdvertisingDto expected = new AdvertisingDto()
                .id(1L)
                .userId(1L)
                .text("smth")
                .email("smth")
                .priority(1L)
                .address("smth")
                .isActive(true)
                .isFavorite(false);
        given(advertisingRepository.save(advertising)).willReturn(advertising);

        AdvertisingDto result = advertisingService.updateAdvertising(expected);

        assertThat(result, is(equalTo(expected)));
    }

    @Test
    public void updateAdvertisingShouldReturnWithException(){
        Advertising advertising = new Advertising(1L, 1L, "smth", "smth", 1L, "smth", true, false);
        AdvertisingDto expected = new AdvertisingDto()
                .id(1L)
                .userId(1L)
                .text("smth")
                .email("smth")
                .priority(1L)
                .address("smth")
                .isActive(true)
                .isFavorite(false);
        given(advertisingRepository.save(advertising)).willThrow(new NullPointerException());

        assertThrows(NullPointerException.class, () ->advertisingService.updateAdvertising(expected));
    }

    @Test
    public void setAdvertisingStatusShouldReturnWithTheCorrectResult(){
        long id = 1L;
        Advertising advertising = new Advertising(id, 1L, "smth", "smth", 1L, "smth", true, false);
        AdvertisingDto expected = new AdvertisingDto()
                .id(id)
                .userId(1L)
                .text("smth")
                .email("smth")
                .priority(1L)
                .address("smth")
                .isActive(false)
                .isFavorite(false);
        given(advertisingRepository.findById(id)).willReturn(Optional.of(advertising));
        given(advertisingRepository.save(advertising)).willReturn(advertising);

        AdvertisingDto result = advertisingService.setAdvertisingStatus(id);

        assertThat(result, is(equalTo(expected)));
    }

    @Test
    public void setAdvertisingStatusShouldReturnWithException(){
        long id = 1L;
        Advertising advertising = new Advertising(id, 1L, "smth", "smth", 1L, "smth", true, false);
        AdvertisingDto expected = new AdvertisingDto()
                .id(id)
                .userId(1L)
                .text("smth")
                .email("smth")
                .priority(1L)
                .address("smth")
                .isActive(false)
                .isFavorite(false);
        given(advertisingRepository.findById(id)).willReturn(Optional.of(advertising));
        given(advertisingRepository.save(advertising)).willThrow(new NullPointerException());

        assertThrows(NullPointerException.class, () ->advertisingService.setAdvertisingStatus(id));
    }

    // endregion

    // region Delete method tests

    @Test
    public void deleteAdvertisingShouldReturnWithTheCorrectResult(){
        long id = 1L;
        Advertising advertising = new Advertising(id, 1L, "smth", "smth", 1L, "smth", true, false);
        AdvertisingDto expected = new AdvertisingDto()
                .id(id)
                .userId(1L)
                .text("smth")
                .email("smth")
                .priority(1L)
                .address("smth")
                .isActive(true)
                .isFavorite(false);
        given(advertisingRepository.findById(id)).willReturn(Optional.of(advertising));
        doNothing().when(advertisingRepository).deleteById(id);

        AdvertisingDto result = advertisingService.deleteAdvertising(id);

        assertThat(result, is(equalTo(expected)));
    }

    @Test
    public void deleteAdvertisingShouldReturnWithException(){
        long id = 1L;
        Advertising advertising = new Advertising(id, 1L, "smth", "smth", 1L, "smth", true, false);
        doThrow(NullPointerException.class).when(advertisingRepository).deleteById(id);

        assertThrows(NullPointerException.class, () ->advertisingService.deleteAdvertising(id));
    }

    // endregion

}
