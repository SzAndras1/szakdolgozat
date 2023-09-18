package hu.cowork.gateway.authenticate.service;

import hu.cowork.cowork_gateway.model.AuthenticationResponseDto;
import hu.cowork.cowork_gateway.model.UserDto;
import hu.cowork.gateway.authenticate.repository.UserRepository;
import hu.cowork.gateway.authenticate.entity.User;
import hu.cowork.gateway.authenticate.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final UserMapper userMapper;

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    public UserDto register(UserDto userDto) {
        if (userRepository.findByUsername(userDto.getUsername()).isPresent()) {
            throw new IllegalArgumentException(userDto.getUsername() + " username is already exists");
        }
        userDto.setRole(UserDto.RoleEnum.USER);
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        User user = userMapper.toEntity(userDto);
        return userMapper.toDto(userRepository.save(user));
    }

    public AuthenticationResponseDto login(UserDto userDto) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                userDto.getUsername(),
                userDto.getPassword()
        ));
        Optional<User> searchForUser = userRepository.findByUsername(userDto.getUsername());
        if (searchForUser.isEmpty()) {
            throw new IllegalArgumentException("Wrong credentials!");
        }
        String jwtToken = jwtService.generateToken(searchForUser.get());
        AuthenticationResponseDto authenticationResponseDto = new AuthenticationResponseDto();
        authenticationResponseDto.setToken(jwtToken);
        return authenticationResponseDto;
    }

    public List<UserDto> getEveryUser() {
        return userRepository.findAll().stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    public UserDto grantAdmin(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("No user found."));
        if (user.getRole().equals(UserDto.RoleEnum.ADMIN)) {
            throw new IllegalArgumentException("User is already ADMIN");
        }
        user.setRole(UserDto.RoleEnum.ADMIN);
        return userMapper.toDto(userRepository.save(user));
    }

    public UserDto revokeAdmin(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("No user found."));
        if (user.getRole().equals(UserDto.RoleEnum.USER)) {
            throw new IllegalArgumentException("User is not an ADMIN");
        }
        user.setRole(UserDto.RoleEnum.USER);
        return userMapper.toDto(userRepository.save(user));
    }

    // TODO: Lock/Unlock user API
    // TODO: Logout
    // TODO: Store tokens with Redis and handle them
}