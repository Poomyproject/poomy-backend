package com.poomy.mainserver.config;

import com.poomy.mainserver.util.exception.AccessDeniedHandlerImpl;
import com.poomy.mainserver.util.exception.AuthenticationExceptionHandler;
import com.poomy.mainserver.util.jwt.JWTFilter;
import com.poomy.mainserver.util.jwt.JWTUtil;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

    private final JWTUtil jwtUtil;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf((auth) -> auth.disable());

        http.formLogin((auth) -> auth.disable());

        http.httpBasic((auth) -> auth.disable());

        http.authorizeHttpRequests((auth) -> auth
                .requestMatchers(
                        // swagger
                        "/v2/api-docs", "/v3/api-docs", "/v3/api-docs/**", "/swagger-resources",
                        "/swagger-resources/**", "/configuration/ui", "/configuration/security", "/swagger-ui/**",
                        "/webjars/**", "/swagger-ui.html").permitAll()
                .requestMatchers("/api/users/login/**").permitAll()
                .anyRequest().authenticated());

        http.exceptionHandling(exceptionHandling -> exceptionHandling
                .authenticationEntryPoint(new AuthenticationExceptionHandler())
                .accessDeniedHandler(new AccessDeniedHandlerImpl()));

        http.addFilterBefore(new JWTFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class);

        http.sessionManagement((session) -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)); //중요!!!

        return http.build();
    }
}
