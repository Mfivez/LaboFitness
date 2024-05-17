package be.labofitness.labo_fitness.il.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtConfig {
    private byte[] secret = "3R#V1LR6#^AmYh^vK8wAFGC56sU8$$qh#c6nyE^H".getBytes(StandardCharsets.UTF_8);
    public int expireAt = 86400; // 1 day
    public SecretKey secretKey = new SecretKeySpec(secret, "HmacSHA256");
}
