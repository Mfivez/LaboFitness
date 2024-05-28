package be.labofitness.labo_fitness.il.config;
import be.labofitness.labo_fitness.il.utils.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * <p>Filter responsible for processing {@code JWT authentication}.</p>
 * <p>This filter intercepts incoming requests and extracts {@code JWT tokens}
 * from the {@code "Authorization" header}.</p>
 * <p>It then validates the tokens using the {@link JwtUtil} class and sets
 * the authenticated {@link User} in the {@code Spring Security context}.</p>
 */
@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserDetailsService userService;

    /**
     * <p>Overrides the {@code doFilterInternal} method to perform {@code JWT authentication}
     * for each incoming request.</p>
     * <p>Extracts the {@code JWT token} from the "Authorization" header and validates it.</p>
     * <p>If the {@code token} is valid, it loads the {@code user details}  from the  {@code database}
     * and sets the authenticated {@link User} in the {@code Security context}.</p>
     * <p>Once the authentication is completed, the request is allowed to proceed down the filter chain.</p>
     *
     * @param request     the incoming HTTP request
     * @param response    the HTTP response
     * @param filterChain the filter chain
     * @throws ServletException if an error occurs during the filter execution
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    )
            throws ServletException, IOException
    {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            if (!token.isEmpty()) {
                if (jwtUtil.validateToken(token)) {

                    String email = jwtUtil.getEmail(token);
                    UserDetails user = userService.loadUserByUsername(email);
                    UsernamePasswordAuthenticationToken userPassAuthToken = new UsernamePasswordAuthenticationToken(
                            user,
                            token,
                            user.getAuthorities()
                    );
                    SecurityContextHolder.getContext().setAuthentication(userPassAuthToken);
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}
