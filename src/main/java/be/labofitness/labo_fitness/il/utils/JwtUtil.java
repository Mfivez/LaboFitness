package be.labofitness.labo_fitness.il.utils;

import be.labofitness.labo_fitness.domain.entity.User;
import be.labofitness.labo_fitness.il.config.JwtConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {
    private final JwtConfig jwtConfig;
    private final JwtBuilder jwtBuilder;
    private final JwtParser jwtParser;

    public JwtUtil(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
        this.jwtBuilder = Jwts.builder().signWith(jwtConfig.secretKey);
        this.jwtParser = Jwts.parserBuilder().setSigningKey(jwtConfig.secretKey).build();
    }

    public String generateToken(User user) {
        return jwtBuilder
                .setSubject(user.getEmail())
                .claim("id", user.getId())
                .claim("email", user.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtConfig.expireAt * 1000L))
                .compact();
    }

    public Claims getClaims(String token) { return jwtParser.parseClaimsJws(token).getBody(); }

    public String getEmail(String token) { return getClaims(token).getSubject(); }

    public Long getId(String token) { return getClaims(token).get("id", Long.class); }

    public String getLogin(String token) { return getClaims(token).get("email", String.class); }

    public boolean validateToken(String token) {

        Claims claims = getClaims(token);
        Date now = new Date();
        return now.after(claims.getIssuedAt()) && now.before(claims.getExpiration());
    }

}
