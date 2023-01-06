package hu.cowork.gateway.advertising.mapper;

import hu.cowork.advertising.model.PageResultDto;
import hu.cowork.cowork_gateway.model.AdvertisingDto;
import hu.cowork.cowork_gateway.model.AdvertisingPageResultDto;
import hu.cowork.cowork_gateway.model.PageDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AdvertisingMapper  {

    hu.cowork.advertising.model.PageDto toSendPageDto(PageDto pageDto);

    AdvertisingPageResultDto toReceivePageResultDto(PageResultDto pageResultDto);

    hu.cowork.advertising.model.AdvertisingDto toSendAdvertingDto(AdvertisingDto advertisingDto);
    AdvertisingDto toReceiveAdvertingDto(hu.cowork.advertising.model.AdvertisingDto advertisingDto);

}
