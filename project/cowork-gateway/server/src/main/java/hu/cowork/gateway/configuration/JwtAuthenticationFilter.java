package hu.cowork.gateway.configuration;

import hu.cowork.gateway.authenticate.service.JwtService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hu.cowork.gateway.authenticate.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    private final UserDetailsService userDetailsService;

    private final TokenService tokenService;

    /**
     * This filter is actives for all requests to the server
     * First it checks if the JWT token is present and correct, if fails then returns.
     * Then decode the JWT token and checks if the user is already authenticated.
     * If authenticated then the chain continues, otherwise it will check if the token valid
     * Finally it's updating the SecurityContextHolder
     *
     * @param request     The incoming request, can obtain data from it
     * @param response    Can provide new datas within response
     * @param filterChain A filter chain used in the Chain-of-responsibility pattern.
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        // Check if the JWT token present in the request and start with 'Bearer '
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String username;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        // Subtract 'Bearer ' from the String
        jwt = authHeader.substring(7);
        // Decode token and extract the username from it (it will be in the payload part)
        username = jwtService.extractUsername(jwt);
        // Checks if the user is not null and that the user is authenticated
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            if (jwtService.isTokenValid(jwt, userDetails) && tokenService.isNotRevoked(jwt)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
