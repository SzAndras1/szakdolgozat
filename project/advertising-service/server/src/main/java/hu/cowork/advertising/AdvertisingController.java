package hu.cowork.advertising;

import hu.cowork.advertising.model.AdvertisingDto;
import hu.cowork.advertising.model.PageDto;
import hu.cowork.advertising.model.PageResultDto;
import hu.cowork.advertising.service.AdvertisingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class AdvertisingController implements AdvertisingApi {

    public static final String ADVERTISING_API_PATH = "/api/advertising/v1/advertisement";

    private final AdvertisingService advertisingService;


    //region Get methods

    @Override
    public ResponseEntity<AdvertisingDto> getAd(Long id) {
        return ResponseEntity.ok(advertisingService.getAdvertising(id));
    }

    @Override
    public ResponseEntity<List<AdvertisingDto>> getUserAds(Long userId) {
        return ResponseEntity.ok(advertisingService.getUserAdvertising(userId));
    }

    @Override
    public ResponseEntity<List<AdvertisingDto>> getFavoriteAds() {
        return ResponseEntity.ok(advertisingService.getFavoriteAds());
    }

    @Override
    public ResponseEntity<List<AdvertisingDto>> getHistory() {
        return ResponseEntity.ok(advertisingService.getHistory());
    }

    //endregion

    //region Post methods
    @Override
    public ResponseEntity<AdvertisingDto> createAd(AdvertisingDto advertisingDto) {
        AdvertisingDto savedAdvertising = advertisingService.createAdvertising(advertisingDto);

        URI location = ServletUriComponentsBuilder
                .fromPath(ADVERTISING_API_PATH)
                .path("/{id}")
                .buildAndExpand(advertisingDto.getId())
                .toUri();

        return ResponseEntity.created(location).body(savedAdvertising);
    }

    @Override
    public ResponseEntity<PageResultDto> searchAdvertising(PageDto pageDto) {
        return ResponseEntity.ok(advertisingService.searchAdvertising(pageDto));
    }

    @Override
    public ResponseEntity<String> uploadImage(String adId, List<MultipartFile> images) {
        try {
            return ResponseEntity.ok(advertisingService.uploadImage(adId, images));
        } catch (RuntimeException runtimeException) {
            return ResponseEntity.internalServerError().body(runtimeException.getMessage());
        }
    }
    //endregion

    //region Put methods

    @Override
    public ResponseEntity<AdvertisingDto> setAdStatus(Long id) {
        return ResponseEntity.ok(advertisingService.setAdvertisingStatus(id));
    }

    @Override
    public ResponseEntity<AdvertisingDto> setAdFavoriteStatus(Long id) {
        return ResponseEntity.ok(advertisingService.setAdFavoriteStatus(id));
    }

    @Override
    public ResponseEntity<AdvertisingDto> updateAdvertising(AdvertisingDto advertisingDto) {
        return ResponseEntity.ok(advertisingService.updateAdvertising(advertisingDto));
    }
    //endregion

    @Override
    public ResponseEntity<AdvertisingDto> deleteAdvertising(Long id) {
        return ResponseEntity.ok(advertisingService.deleteAdvertising(id));
    }
}
