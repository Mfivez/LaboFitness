package be.labofitness.labo_fitness.il.config;

import be.labofitness.labo_fitness.il.utils.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserDetailsService userService;


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
                            //authorities
                    );
                    SecurityContextHolder.getContext().setAuthentication(userPassAuthToken);
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}
