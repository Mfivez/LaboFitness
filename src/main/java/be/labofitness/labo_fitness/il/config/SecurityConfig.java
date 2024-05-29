package be.labofitness.labo_fitness.il.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.beans.Encoder;
import java.beans.XMLEncoder;

/**
 * Configuration class for defining security-related settings.
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    /**
     * Overrides the configure method to specify authentication manager settings.
     *
     * @param config {@link AuthenticationConfiguration} instance used to obtain {@code authentication manager}.
     * @return The configured {@link AuthenticationManager}.
     * @throws Exception if an error occurs during configuration.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{
        return config.getAuthenticationManager();
    }

    /**
     * Configures the {@link SecurityFilterChain}.
     *
     * @param http {@link HttpSecurity} instance used to configure security settings.
     * @return The configured {@link SecurityFilterChain}.
     * @throws Exception if an error occurs during configuration.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll());

        return http.build();
    }

    /**
     * Creates a {@link PasswordEncoder} bean for encoding passwords.
     *
     * @return {@link PasswordEncoder} instance for encoding passwords.
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}