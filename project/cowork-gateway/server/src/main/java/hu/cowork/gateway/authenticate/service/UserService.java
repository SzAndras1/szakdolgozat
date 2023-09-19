package hu.cowork.gateway.authenticate.service;

import hu.cowork.cowork_gateway.model.AuthenticationResponseDto;
import hu.cowork.cowork_gateway.model.UserDto;
import hu.cowork.gateway.authenticate.entity.Token;
import hu.cowork.gateway.authenticate.repository.UserRepository;
import hu.cowork.gateway.authenticate.entity.User;
import hu.cowork.gateway.authenticate.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final UserMapper userMapper;

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    private final TokenService tokenService;

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
        User searchForUser = userRepository.findByUsername(userDto.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("Wrong credentials!"));
        if (!searchForUser.isAccountNonLocked()) {
            throw new AccessDeniedException("Locked account");
        }
        String jwtToken = jwtService.generateToken(searchForUser);
        String username = searchForUser.getUsername();
        tokenService.storeToken(new Token(username, jwtToken, false));
        AuthenticationResponseDto authenticationResponseDto = new AuthenticationResponseDto();
        authenticationResponseDto.setToken(jwtToken);
        return authenticationResponseDto;
    }

    public List<UserDto> getEveryUser() {
        return userRepository.findAll().stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    public UserDto manageAdmin(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("No user found."));
        if (user.getRole() == UserDto.RoleEnum.USER) {
            user.setRole(UserDto.RoleEnum.ADMIN);
        } else {
            user.setRole(UserDto.RoleEnum.USER);
        }
        return userMapper.toDto(userRepository.save(user));
    }

    public UserDto lockUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("No user found."));
        user.setIsNonLocked(!user.getIsNonLocked());
        return userMapper.toDto(userRepository.save(user));
    }

    public String deleteAll() {
        tokenService.deleteAll();
        return "ok";
    }
}