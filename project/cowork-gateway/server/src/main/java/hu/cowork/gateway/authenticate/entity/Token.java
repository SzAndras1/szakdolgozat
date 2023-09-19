package hu.cowork.gateway.authenticate.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;

@AllArgsConstructor
@Data
@RedisHash("Token")
public class Token implements Serializable {

    @Indexed
    private String username;

    @Id
    @Indexed
    private String token;

    private boolean revoked;
}
