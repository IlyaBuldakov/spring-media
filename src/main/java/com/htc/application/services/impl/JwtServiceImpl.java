package com.htc.application.services.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.htc.application.dto.login.LoginResponse;
import com.htc.application.services.JwtService;
import com.htc.domain.entities.user.Role;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.regex.Pattern;

/**
 * Реализация {@link JwtService}.
 */
@Service
public class JwtServiceImpl implements JwtService {


    public JwtServiceImpl(@Value("${authentication.key}") String key) {
        this.algorithm = Algorithm.HMAC256(key);
    }

    /**
     * Алгоритм подписи JWT.
     */
    private final Algorithm algorithm;

    private final Pattern TOKEN_PATTERN = Pattern.compile("Bearer (?<token>[^\\s.]+.[^\\s.]+.[^\\s.]+)");

    /**
     * Время жизни Access-токена (10 минут).
     */
    private final int ACCESS_TOKEN_LIFETIME_SECONDS = 10 * 60;

    /**
     * Время жизни Refresh-токена (30 дней).
     */
    private final int REFRESH_TOKEN_LIFETIME_SECONDS = 30 * 24 * 60 * 60;

    @Override
    public String createJwtToken(int id, Role role, int lifeTime) {
        var expiredTime = new Date();
        expiredTime.setTime(expiredTime.getTime() + (lifeTime * 1000L));
        return JWT.create()
                .withClaim("id", id)
                .withClaim("role", role.toString())
                .withExpiresAt(expiredTime)
                .sign(algorithm);
    }

    @Override
    public LoginResponse createPairOfTokens(int id, Role role) {
        return new LoginResponse(
                createJwtToken(id, role, ACCESS_TOKEN_LIFETIME_SECONDS),
                createJwtToken(id, role, REFRESH_TOKEN_LIFETIME_SECONDS)
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

    @Override
    public DecodedJWT decodeToken(String token) {
        return JWT.require(algorithm)
                .build()
                .verify(token);
    }
}
