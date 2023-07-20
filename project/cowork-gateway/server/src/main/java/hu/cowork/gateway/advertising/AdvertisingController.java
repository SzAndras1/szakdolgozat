package hu.cowork.gateway.advertising;

import hu.cowork.cowork_gateway.AdvertisingApi;
import hu.cowork.cowork_gateway.model.AdvertisingDto;
import hu.cowork.cowork_gateway.model.AdvertisingPageResultDto;
import hu.cowork.cowork_gateway.model.PageDto;
import hu.cowork.gateway.advertising.service.AdvertisingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class AdvertisingController implements AdvertisingApi {


    private final AdvertisingService advertisingService;

    @Override
    public ResponseEntity<AdvertisingDto> getAd(Long id) {
        return ResponseEntity.ok(advertisingService.getAd(id));
    }

    @Override
    public ResponseEntity<List<AdvertisingDto>> getUserAds(Long userId) {
        return ResponseEntity.ok(advertisingService.getUserAds(userId));
    }

    @Override
    public ResponseEntity<List<AdvertisingDto>> getFavoriteAds() {
        return ResponseEntity.ok(advertisingService.getFavoriteAds());
    }

    @Override
    public ResponseEntity<List<AdvertisingDto>> getHistory() {
        return ResponseEntity.ok(advertisingService.getHistory());
    }

    @Override
    public ResponseEntity<AdvertisingDto> createAd(AdvertisingDto advertisingDto) {
        return ResponseEntity.ok(advertisingService.createAd(advertisingDto));
    }

    @Override
    public ResponseEntity<AdvertisingPageResultDto> searchAdvertising(PageDto pageDto) {
        return ResponseEntity.ok(advertisingService.searchAdvertising(pageDto));
    }

    @Override
    public ResponseEntity<AdvertisingDto> updateAdvertising(AdvertisingDto advertisingDto) {
        return ResponseEntity.ok(advertisingService.updateAdvertising(advertisingDto));
    }

    @Override
    public ResponseEntity<AdvertisingDto> setAdStatus(Long id) {
        return ResponseEntity.ok(advertisingService.setAdStatus(id));
    }

    @Override
    public ResponseEntity<AdvertisingDto> setAdFavoriteStatus(Long id) {
        return ResponseEntity.ok(advertisingService.setAdFavoriteStatus(id));
    }

    @Override
    public ResponseEntity<AdvertisingDto> deleteAdvertising(Long id) {
        return ResponseEntity.ok(advertisingService.deleteAdvertising(id));
    }
}
