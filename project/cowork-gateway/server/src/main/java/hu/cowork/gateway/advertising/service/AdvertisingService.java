package hu.cowork.gateway.advertising.service;

import hu.cowork.advertising.AdvertisingApiClient;
import hu.cowork.cowork_gateway.model.AdvertisingDto;
import hu.cowork.cowork_gateway.model.AdvertisingPageResultDto;
import hu.cowork.cowork_gateway.model.PageDto;
import hu.cowork.gateway.advertising.mapper.AdvertisingMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AdvertisingService {

    private final AdvertisingApiClient advertisingApi;
    private final AdvertisingMapper advertisingMapper;
    
    public AdvertisingDto createAd(AdvertisingDto advertisingDto) {
        return null;
    }


    public AdvertisingDto getAd(Long id) {
        return null;
    }


    public AdvertisingPageResultDto searchAdvertising(PageDto pageDto) {
        hu.cowork.advertising.model.PageDto toSend = advertisingMapper.toSendPageDto(pageDto);
        return advertisingMapper.toReceivePageResultDto(advertisingApi.searchAdvertising(toSend).getBody());
    }


    public AdvertisingDto updateAdvertising(AdvertisingDto advertisingDto) {
        return null;
    }
}
