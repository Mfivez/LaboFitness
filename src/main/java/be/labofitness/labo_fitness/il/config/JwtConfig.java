package be.labofitness.labo_fitness.il.config;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

/**
 * Configuration class for {@code JWT (JSON Web Token)} properties.
 * <br>Reads {@code JWT configuration properties} from {@code application.properties} or {@code application.yml}.
 */
@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtConfig {

    /** The secret key for signing JWT tokens. */
    @Value("${JWT_SECRET}")
    private String secretFromEnv;

    /** The expiration time (in milliseconds) for JWT tokens. */
    @Value("${JWT_EXPIRE_AT}")
    private int expireAtFromEnv;

    /** The algorithm used for JWT token signing. */
    @Value("${JWT_ALGORITHM}")
    private String algorithm;

    /** The expiration time (in seconds) for JWT tokens. */
    public int expireAt; // 1 day

    /** The secret key as {@link SecretKey} object. */
    public SecretKey secretKey;

    /**
     * <p>Initializes the {@code JWT parameters} based on the configuration values specified in the {@code application.properties}.</p>
     *
     * <p>This method is automatically executed after the {@code JwtConfig} object is constructed, thanks to the <code>@PostConstruct</code> annotation.</p>
     *
     * <p>It retrieves the configuration values for {@code the secret}, {@code expiration time}, and {@code JWT algorithm}, and then uses them to initialize the {@code JWT parameters}.</p>
     *
     */
    @PostConstruct
    public void init() {
        byte[] secret = secretFromEnv.getBytes(StandardCharsets.UTF_8); //TODO MAYBE HERE IF PROBLEMS
        expireAt = expireAtFromEnv;
        secretKey = new SecretKeySpec(secret, algorithm);
    }
}
