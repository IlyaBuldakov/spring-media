package com.htc.application.services.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.htc.application.dto.errors.InternalServerErrorResponse;
import com.htc.application.dto.login.LoginResponse;
import com.htc.application.security.UserAuthentication;
import com.htc.application.services.JwtService;
import com.htc.domain.entities.user.Role;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.regex.Pattern;

/**
 * Реализация {@link JwtService}.
 */
@Service
public class JwtServiceImpl implements JwtService {


    public JwtServiceImpl(@Value("${authentication.key}") String key,
                          @Qualifier("userDetailsServiceImpl") UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
        this.algorithm = Algorithm.HMAC256(key);
    }

    /**
     * Алгоритм подписи JWT.
     */
    private final Algorithm algorithm;

    private final Pattern TOKEN_PATTERN = Pattern.compile("Bearer (?<token>[^\\s.]+.[^\\s.]+.[^\\s.]+)");

    private final UserDetailsService userDetailsService;

    /**
     * Время жизни Access-токена (10 минут).
     */
    private final static int ACCESS_TOKEN_LIFETIME_SECONDS = 10 * 60;

    /**
     * Время жизни Refresh-токена (30 дней).
     */
    private final static int REFRESH_TOKEN_LIFETIME_SECONDS = 30 * 24 * 60 * 60;

    @Override
    public String createJwtToken(int id, Role role, String email, int lifeTime) {
        var expiredTime = new Date();
        expiredTime.setTime(expiredTime.getTime() + (lifeTime * 1000L));
        return JWT.create()
                .withSubject(email)
                .withClaim("id", id)
                .withClaim("role", role.toString())
                .withExpiresAt(expiredTime)
                .sign(algorithm);
    }

    @Override
    public LoginResponse createPairOfTokens(int id, Role role, String email) {
        return new LoginResponse(
                createJwtToken(id, role, email, ACCESS_TOKEN_LIFETIME_SECONDS),
                createJwtToken(id, role, email, REFRESH_TOKEN_LIFETIME_SECONDS)
        );
    }

    @Override
    public String getTokenFromHeader(String header) {
        var matcher = TOKEN_PATTERN.matcher(header);
        matcher.find();
        return matcher.group("token");
    }

    public boolean isTokenValid(String token) {
        try {
            JWT.require(algorithm)
                    .acceptLeeway(60)
                    .build()
                    .verify(token);
        } catch (JWTVerificationException exception) {
            return false;
        }
        return true;
    }

    private String getEmailFromToken(String token) {
        return JWT.decode(token).getSubject();
    }

    private int getIdFromToken(String token) {
        return JWT.decode(token).getClaim("id").asInt();
    }

    @Override
    public Authentication getAuthentication(String token) {
        var email = getEmailFromToken(token);
        var id = getIdFromToken(token);
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);
        return new UserAuthentication(id, userDetails, "", userDetails.getAuthorities());
    }

    @Override
    public LoginResponse getNewAccessToken(String refreshToken) {
        DecodedJWT decodedJWT = JWT.decode(refreshToken);
        int id = decodedJWT.getClaim("id").asInt();
        Role role = Role.lookup(decodedJWT.getClaim("role").asString());
        String email = decodedJWT.getSubject();
        if (isTokenValid(refreshToken)) {
            if (role != null) {
                return new LoginResponse(
                        createJwtToken(id, role, email, ACCESS_TOKEN_LIFETIME_SECONDS),
                        refreshToken);
            }
        }
        throw new InternalServerErrorResponse();
    }
}
