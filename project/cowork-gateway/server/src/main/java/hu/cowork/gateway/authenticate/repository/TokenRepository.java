package hu.cowork.gateway.authenticate.repository;

import hu.cowork.gateway.authenticate.entity.Token;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TokenRepository extends CrudRepository<Token, String> {

    Optional<Token> findByToken(String token);

    List<Token> findAllByUsername(String username);
}

