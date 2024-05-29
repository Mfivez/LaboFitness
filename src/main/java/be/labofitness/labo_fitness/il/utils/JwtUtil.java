package be.labofitness.labo_fitness.il.utils;
import be.labofitness.labo_fitness.domain.entity.User;
import be.labofitness.labo_fitness.il.config.JwtConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Utility class for {@code JWT (JSON Web Token)} operations.
 */
@Component
public class JwtUtil {

    private final JwtConfig jwtConfig;
    private final JwtBuilder jwtBuilder;
    private final JwtParser jwtParser;

    /**
     * Constructs a {@link JwtUtil} instance with the provided {@link JwtConfig}.
     *
     * @param jwtConfig The {@link JwtConfig} instance containing {@code JWT configuration}.
     */
    public JwtUtil(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
        this.jwtBuilder = Jwts.builder().signWith(jwtConfig.secretKey);
        this.jwtParser = Jwts.parserBuilder().setSigningKey(jwtConfig.secretKey).build();
    }

    /**
     * Generates a {@code JWT token} for the provided {@link User}.
     *
     * @param user The {@link User} for whom the token is generated.
     * @return The generated {@code JWT token}.
     */
    public String generateToken(User user) {

        return jwtBuilder
                .setSubject(user.getUsername())
                .claim("id", user.getId())
                .claim("authorities", user.getAuthorities())
                .setIssuedAt(new Date())
                //TODO modified x1000
                .setExpiration(new Date(System.currentTimeMillis() + jwtConfig.expireAt * 1000L))
                .compact();
    }

    /**
     * Retrieves the claims from the provided {@code JWT token}.
     *
     * @param token The {@code JWT token}.
     * @return The {@link Claims} extracted from the {@code JWT token}
     */
    public Claims getClaims(String token) { return jwtParser.parseClaimsJws(token).getBody(); }

    /**
     * Retrieves the email from the provided {@code JWT token}.
     *
     * @param token The {@code JWT token}.
     * @return The email extracted from the {@code JWT token}.
     */
    public String getEmail(String token) { return getClaims(token).getSubject(); }

    /**
     * Validates the provided {@code JWT token}.
     *
     * @param token The {@code JWT token} to validate.
     * @return True if the {@code JWT token} is valid, false otherwise.
     */
    public boolean validateToken(String token) {
        Claims claims = getClaims(token);
        Date now = new Date();
        return now.after(claims.getIssuedAt()) && now.before(claims.getExpiration());
    }
    /*
    * 		      if (!client.getEmail().equals(request.email())) {
            if (!userRepository.existsByEmail(request.email())) {  client.setEmail(request.email());  }
            else{
                throw new PasswordNotMatchingException("Email already exists");
            }
    *
    * */




}
