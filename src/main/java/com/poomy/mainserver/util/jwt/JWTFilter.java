package com.poomy.mainserver.util.jwt;

import com.poomy.mainserver.user.dto.CustomUserDetails;
import com.poomy.mainserver.user.entity.User;
import com.poomy.mainserver.user.type.UserRoleType;
import com.poomy.mainserver.util.exception.ErrorCode;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@AllArgsConstructor
public class JWTFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        try {
            String accessToken = request.getHeader("accessToken");

            if (accessToken == null || !accessToken.startsWith("Bearer ")) {
                log.info("token null");
                filterChain.doFilter(request, response);
                return;
            }

            String token = accessToken.split(" ")[1];

            if (jwtUtil.isExpired(token)) {
                log.info("token expired");
                filterChain.doFilter(request, response);
                return;
            }

            String googleEmail = jwtUtil.getGoogleEmail(token);
            String role = jwtUtil.getRole(token);

            User user = User.builder()
                    .googleEmail(googleEmail)
                    .role(UserRoleType.valueOf(role))
                    .build();

            CustomUserDetails customUserDetails = new CustomUserDetails(user);

            Authentication authToken = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authToken);
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException e){
            log.error(e.getMessage());
            SecurityContextHolder.clearContext();
            setRequestAttributes(request, ErrorCode.INVALID_TOKEN, e);
        } catch(Exception e){
            log.error(e.getMessage());
            setRequestAttributes(request, ErrorCode.INTERNAL_SERVER_ERROR, e);
        }

        filterChain.doFilter(request, response);
    }

    private void setRequestAttributes(HttpServletRequest request, ErrorCode invalidToken, Exception e) {
        request.setAttribute("exception", invalidToken.getCode());
        request.setAttribute("error", e.toString());
        request.setAttribute("errorMessage", e.getMessage());
    }
}
