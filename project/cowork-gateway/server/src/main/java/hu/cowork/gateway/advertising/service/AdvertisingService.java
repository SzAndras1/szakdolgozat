package hu.cowork.gateway.advertising.service;

import hu.cowork.advertising.AdvertisingApiClient;
import hu.cowork.cowork_gateway.model.AdvertisingDto;
import hu.cowork.cowork_gateway.model.AdvertisingPageResultDto;
import hu.cowork.cowork_gateway.model.PageDto;
import hu.cowork.gateway.advertising.mapper.AdvertisingMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AdvertisingService {

    private final AdvertisingApiClient advertisingApiClient;
    private final AdvertisingMapper advertisingMapper;

    public AdvertisingDto getAd(Long id) {
        return advertisingMapper.toReceiveAdvertingDto(
                advertisingApiClient.getAd(id).getBody());
    }

    public List<AdvertisingDto> getUserAds(Long userId) {
        return advertisingApiClient.getUserAds(userId).getBody().stream()
                .map(advertisingMapper::toReceiveAdvertingDto)
                .collect(Collectors.toList());
    }

    public List<AdvertisingDto> getFavoriteAds() {
        return advertisingApiClient.getFavoriteAds().getBody().stream()
                .map(advertisingMapper::toReceiveAdvertingDto)
                .collect(Collectors.toList());
    }

    public List<AdvertisingDto> getHistory() {
        return Objects.requireNonNull(advertisingApiClient.getHistory().getBody()).stream()
                .map(advertisingMapper::toReceiveAdvertingDto)
                .collect(Collectors.toList());
    }

    public AdvertisingDto createAd(AdvertisingDto advertisingDto) {

        return advertisingMapper.toReceiveAdvertingDto(
                advertisingApiClient.createAd(advertisingMapper.toSendAdvertingDto(advertisingDto)).getBody());
    }

    public AdvertisingPageResultDto searchAdvertising(PageDto pageDto) {
        hu.cowork.advertising.model.PageDto toSend = advertisingMapper.toSendPageDto(pageDto);
        return advertisingMapper.toReceivePageResultDto(advertisingApiClient.searchAdvertising(toSend).getBody());
    }

    public AdvertisingDto updateAdvertising(AdvertisingDto advertisingDto) {
        return advertisingMapper.toReceiveAdvertingDto(
                advertisingApiClient.updateAdvertising(advertisingMapper.toSendAdvertingDto(advertisingDto)).getBody());
    }

    public AdvertisingDto setAdStatus(Long id) {
        return advertisingMapper.toReceiveAdvertingDto(
                advertisingApiClient.setAdStatus(id).getBody());
    }

    public AdvertisingDto setAdFavoriteStatus(Long id) {
        return advertisingMapper.toReceiveAdvertingDto(
                advertisingApiClient.setAdFavoriteStatus(id).getBody());
    }

    public AdvertisingDto deleteAdvertising(Long id) {
        return advertisingMapper.toReceiveAdvertingDto(
                advertisingApiClient.deleteAdvertising(id).getBody());
    }
}
