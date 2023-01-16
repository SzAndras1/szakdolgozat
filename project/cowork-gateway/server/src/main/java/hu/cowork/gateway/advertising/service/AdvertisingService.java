package hu.cowork.gateway.advertising.service;

import hu.cowork.advertising.AdvertisingApiClient;
import hu.cowork.cowork_gateway.model.AdvertisingDto;
import hu.cowork.cowork_gateway.model.AdvertisingPageResultDto;
import hu.cowork.cowork_gateway.model.PageDto;
import hu.cowork.gateway.advertising.mapper.AdvertisingMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AdvertisingService {

    private final AdvertisingApiClient advertisingApiClient;
    private final AdvertisingMapper advertisingMapper;

    public AdvertisingDto createAd(AdvertisingDto advertisingDto) {

        return advertisingMapper.toReceiveAdvertingDto(
                advertisingApiClient.createAd(advertisingMapper.toSendAdvertingDto(advertisingDto)).getBody());
    }


    public AdvertisingDto getAd(Long id) {
        return advertisingMapper.toReceiveAdvertingDto(
                advertisingApiClient.getAd(id).getBody());
    }


    public AdvertisingPageResultDto searchAdvertising(PageDto pageDto) {
        hu.cowork.advertising.model.PageDto toSend = advertisingMapper.toSendPageDto(pageDto);
        return advertisingMapper.toReceivePageResultDto(advertisingApiClient.searchAdvertising(toSend).getBody());
    }


    public AdvertisingDto updateAdvertising(AdvertisingDto advertisingDto) {
        return advertisingMapper.toReceiveAdvertingDto(
                advertisingApiClient.updateAdvertising(advertisingMapper.toSendAdvertingDto(advertisingDto)).getBody());
    }

    public List<AdvertisingDto> getUserAds(Long userId) {
        return advertisingApiClient.getUserAds(userId).getBody().stream()
                .map(advertisingMapper::toReceiveAdvertingDto)
                .collect(Collectors.toList());
    }
}
