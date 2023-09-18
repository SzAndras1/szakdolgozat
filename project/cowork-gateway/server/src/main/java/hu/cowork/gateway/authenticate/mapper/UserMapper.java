package hu.cowork.gateway.authenticate.mapper;


import hu.cowork.cowork_gateway.model.UserDto;
import hu.cowork.gateway.authenticate.entity.User;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

@Mapper(componentModel = "spring")
public interface UserMapper extends Converter<User, UserDto> {

    UserDto toDto(User user);

    User toEntity(UserDto userDto);
}
