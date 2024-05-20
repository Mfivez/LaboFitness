package be.labofitness.labo_fitness.il.config;


import jakarta.annotation.PostConstruct;
import nonapi.io.github.classgraph.json.JSONUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtConfig {
    @Value("${JWT_SECRET}")
    private String secretFromEnv;

    @Value("${JWT_EXPIRE_AT}")
    private int expireAtFromEnv;

    @Value("${JWT_ALGORITHM}")
    private String algorithm;

    private byte[] secret;
    public int expireAt; // 1 day
    public SecretKey secretKey;

    @PostConstruct
    public void init() {
        secret = secretFromEnv.getBytes(StandardCharsets.UTF_8);
        expireAt = expireAtFromEnv;
        secretKey = new SecretKeySpec(secret, algorithm);
    }
}
