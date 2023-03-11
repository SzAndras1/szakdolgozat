package hu.cowork.advetising.fixture;

import hu.cowork.advertising.entity.Advertising;
import hu.cowork.advertising.model.AdvertisingDto;

public class AdvertisingFixtures {

    public static Advertising simpleAdvertising(Long id) {
        return new Advertising(id, 1L, "smth", "smth", 1L, "smth", true, false, null);
    }

    public static AdvertisingDto simpleAdvertisingDto(Long id) {
        return new AdvertisingDto()
                .id(id)
                .userId(1L)
                .text("smth")
                .email("smth")
                .priority(1L)
                .address("smth")
                .isActive(true)
                .isFavorite(false);
    }
}
