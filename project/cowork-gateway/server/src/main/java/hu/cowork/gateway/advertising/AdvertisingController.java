package hu.cowork.gateway.advertising;

import hu.cowork.cowork_gateway.AdvertisingApi;
import hu.cowork.cowork_gateway.model.AdvertisingDto;
import hu.cowork.cowork_gateway.model.AdvertisingPageResultDto;
import hu.cowork.cowork_gateway.model.PageDto;
import hu.cowork.gateway.advertising.service.AdvertisingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AdvertisingController implements AdvertisingApi {


    private final AdvertisingService advertisingService;

    @Override
    public ResponseEntity<AdvertisingDto> createAd(AdvertisingDto advertisingDto) {
        return ResponseEntity.ok(advertisingService.createAd(advertisingDto));
    }

    @Override
    public ResponseEntity<AdvertisingDto> getAd(Long id) {
        return ResponseEntity.ok(advertisingService.getAd(id));
    }

    @Override
    public ResponseEntity<AdvertisingPageResultDto> searchAdvertising(PageDto pageDto) {
        return ResponseEntity.ok(advertisingService.searchAdvertising(pageDto));
    }

    @Override
    public ResponseEntity<AdvertisingDto> updateAdvertising(AdvertisingDto advertisingDto) {
        return ResponseEntity.ok(advertisingService.updateAdvertising(advertisingDto));
    }
}
