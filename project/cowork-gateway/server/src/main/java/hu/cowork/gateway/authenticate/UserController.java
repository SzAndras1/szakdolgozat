package hu.cowork.gateway.authenticate;

import hu.cowork.cowork_gateway.UserApi;
import hu.cowork.cowork_gateway.model.AuthenticationResponseDto;
import hu.cowork.cowork_gateway.model.UserDto;
import hu.cowork.gateway.authenticate.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class UserController implements UserApi {

    public static final String USER_API_PATH = "/api/v1/authentication";

    private final UserService userService;

    @Override
    public ResponseEntity<UserDto> register(UserDto userDto) {
        UserDto savedUser = userService.register(userDto);
        URI location = ServletUriComponentsBuilder
                    .fromPath(USER_API_PATH)
                    .path("/{id}")
                    .buildAndExpand(savedUser.getId())
                    .toUri();
        return ResponseEntity.created(location).body(savedUser);
    }

    @Override
    public ResponseEntity<AuthenticationResponseDto> login(UserDto userDto) {
        return ResponseEntity.ok(userService.login(userDto));
    }

    @Override
    public ResponseEntity<List<UserDto>> getEveryUser() {
        return ResponseEntity.ok(userService.getEveryUser());
    }
}
