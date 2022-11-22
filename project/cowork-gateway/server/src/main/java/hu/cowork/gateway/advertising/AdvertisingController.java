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

    public static final String ACCOUNTS_API_PATH = "/api/home-banking-gateway/v1/accounts";

    private final AdvertisingService accountService;
/*
    @Override
    public ResponseEntity<AccountDto> createAccount(AccountDto accountDto) {
        AccountDto account = accountService.createAccount(accountDto);

        URI location = ServletUriComponentsBuilder
                .fromPath(ACCOUNTS_API_PATH)
                .path("/{reference}")
                .buildAndExpand(account.getReference())
                .toUri();

        return ResponseEntity.created(location).body(account);
    }*/

    @Override
    public ResponseEntity<AdvertisingDto> createAd(AdvertisingDto advertisingDto) {
        return null;
    }

    @Override
    public ResponseEntity<AdvertisingDto> getAd(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<AdvertisingPageResultDto> searchAdvertising(PageDto pageDto) {
        return ResponseEntity.ok(accountService.searchAdvertising(pageDto));
    }

    @Override
    public ResponseEntity<AdvertisingDto> updateAdvertising(AdvertisingDto advertisingDto) {
        return null;
    }
}
