package hu.cowork.advertising;

import hu.cowork.advertising.model.*;
import hu.cowork.advertising.service.AdvertisingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
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

    //endregion Get methods

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

    //endregion Post methods

    //region Put methods

    @Override
    public ResponseEntity<AdvertisingDto> setAdStatus(Long id) {
        return ResponseEntity.ok(advertisingService.setAdvertisingStatus(id));
    }

    @Override
    public ResponseEntity<AdvertisingDto> updateAdvertising(AdvertisingDto advertisingDto) {
        return ResponseEntity.ok(advertisingService.updateAdvertising(advertisingDto));
    }

    //endregion Put methods
}
