package hu.cowork.advertising;

import hu.cowork.advertising.model.*;
import hu.cowork.advertising.service.AdvertisingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

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
    public ResponseEntity<AdvertisingDtoList> getUserAds(Long userId) {
        AdvertisingDtoList result = new AdvertisingDtoList();
        result.setContent(advertisingService.getUserAdvertising(userId));
        return ResponseEntity.ok(result);
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
    public ResponseEntity<AdvertisingDto> setStatus(ActivationLink activationLink) {
        return ResponseEntity.ok(advertisingService.setAdvertisingStatus(activationLink));
    }
    //endregion

    //region Put methods
    @Override
    public ResponseEntity<AdvertisingDto> updateAdvertising(AdvertisingDto advertisingDto) {
        return ResponseEntity.ok(advertisingService.updateAdvertising(advertisingDto));
    }
    //endregion



}
