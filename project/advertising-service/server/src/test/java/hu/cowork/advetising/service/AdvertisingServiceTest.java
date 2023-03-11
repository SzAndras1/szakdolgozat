package hu.cowork.advetising.service;

import hu.cowork.advertising.entity.Advertising;
import hu.cowork.advertising.mapper.AdvertisingMapperImpl;
import hu.cowork.advertising.model.AdvertisingDto;
import hu.cowork.advertising.repository.AdvertisingRepository;
import hu.cowork.advertising.service.AdvertisingSearchService;
import hu.cowork.advertising.service.AdvertisingService;
import hu.cowork.advetising.fixture.AdvertisingFixtures;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

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
        Advertising advertising = AdvertisingFixtures.simpleAdvertising(id);
        AdvertisingDto expected = AdvertisingFixtures.simpleAdvertisingDto(id);
        given(advertisingRepository.findById(id)).willReturn(Optional.of(advertising));

        AdvertisingDto result = advertisingService.getAdvertising(id);

        assertThat(result, is(equalTo(expected)));
    }

    @Test
    public void getUserAdvertisingShouldReturnWithTheCorrectResult(){
        Long userId = 2L;
        List<Advertising> advertisingList = List.of(
                AdvertisingFixtures.simpleAdvertising(1L),
                AdvertisingFixtures.simpleAdvertising(2L)
        );
        advertisingList.get(0).setUserId(userId);
        advertisingList.get(1).setUserId(userId);
        List<AdvertisingDto> expectedList = List.of(
                AdvertisingFixtures.simpleAdvertisingDto(1L),
                AdvertisingFixtures.simpleAdvertisingDto(2L));
        expectedList.get(0).setUserId(userId);
        expectedList.get(1).setUserId(userId);
        when(advertisingRepository.findAllByUserId(userId)).thenReturn(advertisingList);

        List<AdvertisingDto> resultList = advertisingService.getUserAdvertising(userId);

        assertThat(resultList, is(equalTo(expectedList)));
    }

    // endregion

    // region Create method tests

    @Test
    public void createAdvertisingShouldReturnWithTheCorrectResult(){
        Long id = 1L;
        Advertising advertising = AdvertisingFixtures.simpleAdvertising(id);
        AdvertisingDto expected = AdvertisingFixtures.simpleAdvertisingDto(id);
        given(advertisingRepository.save(advertising)).willReturn(advertising);

        AdvertisingDto result = advertisingService.createAdvertising(expected);

        assertThat(result, is(equalTo(expected)));
    }

    // endregion

    // region Update method tests

    @Test
    public void updateAdvertisingShouldReturnWithTheCorrectResult(){
        Long id = 1L;
        Advertising advertising = AdvertisingFixtures.simpleAdvertising(id);
        AdvertisingDto expected = AdvertisingFixtures.simpleAdvertisingDto(id);
        given(advertisingRepository.save(advertising)).willReturn(advertising);

        AdvertisingDto result = advertisingService.updateAdvertising(expected);

        assertThat(result, is(equalTo(expected)));
    }

    @Test
    public void setAdvertisingStatusShouldReturnWithTheCorrectResult(){
        Long id = 1L;
        Advertising advertising = AdvertisingFixtures.simpleAdvertising(id);
        AdvertisingDto expected = AdvertisingFixtures.simpleAdvertisingDto(id);
        expected.setIsActive(false);
        given(advertisingRepository.findById(id)).willReturn(Optional.of(advertising));
        given(advertisingRepository.save(advertising)).willReturn(advertising);

        AdvertisingDto result = advertisingService.setAdvertisingStatus(id);

        assertThat(result, is(equalTo(expected)));
    }

    // endregion

    // region Delete method tests

    @Test
    public void deleteAdvertisingShouldReturnWithTheCorrectResult(){
        Long id = 1L;
        Advertising advertising = AdvertisingFixtures.simpleAdvertising(id);
        AdvertisingDto expected = AdvertisingFixtures.simpleAdvertisingDto(id);
        given(advertisingRepository.findById(id)).willReturn(Optional.of(advertising));
        doNothing().when(advertisingRepository).deleteById(id);

        AdvertisingDto result = advertisingService.deleteAdvertising(id);

        assertThat(result, is(equalTo(expected)));
    }

    // endregion

}
