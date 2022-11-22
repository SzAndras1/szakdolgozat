package hu.cowork.advertising.mapper;

import hu.cowork.advertising.model.AdvertisingDto;
import hu.cowork.advertising.entity.Advertising;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

@Mapper(componentModel = "spring")
public interface AdvertisingMapper extends Converter<Advertising, AdvertisingDto> {

    AdvertisingDto toDto(Advertising advertising);

    Advertising toEntity(AdvertisingDto advertisingDto);
}
