package hu.cowork.gateway.authenticate.service;

import hu.cowork.gateway.authenticate.entity.Token;
import hu.cowork.gateway.authenticate.repository.TokenRepository;
import liquibase.repackaged.org.apache.commons.collections4.IterableUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TokenService {

    final private TokenRepository tokenRepository;

    public List<Token> getEveryToken() {
        return IterableUtils.toList(tokenRepository.findAll());
    }

    public Token getToken(String jwt) {
        return tokenRepository.findByToken(jwt).orElse(null);
    }

    public void storeToken(Token token) {
        tokenRepository.save(token);
    }

    public void revokeUserTokens(String username) {
        List<Token> tokens = tokenRepository.findAllByUsername(username);
        tokens.stream()
                .filter(t -> !t.isRevoked())
                .forEach(t -> {
                    t.setRevoked(true);
                    tokenRepository.save(t);
                });
    }

    public boolean isNotRevoked(String jwt) {
        Optional<Token> token = tokenRepository.findByToken(jwt);
        return !token.get().isRevoked();
    }
}
