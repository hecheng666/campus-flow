package com.campusflow.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import com.campusflow.config.JwtProperties;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

@Component
public class JwtUtils {

    private final JwtProperties jwtProperties;

    public JwtUtils(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }

    /**
     * 用 UTF-8 字节生成 HMAC-SHA 密钥，要求密钥长度不少于 32 字节
     */
    private SecretKey signingKey() {
        return Keys.hmacShaKeyFor(jwtProperties.getSecret().getBytes(StandardCharsets.UTF_8));
    }

    /**
     * 生成 JWT 令牌
     */
    public String generateJwt(Map<String, Object> claims) {
        return Jwts.builder()
                .addClaims(claims)
                .signWith(signingKey(), SignatureAlgorithm.HS256)
                .setExpiration(new Date(System.currentTimeMillis() + jwtProperties.getExpire()))
                .compact();
    }

    /**
     * 解析 JWT 令牌，签名或有效期校验失败时抛出异常
     */
    public Claims parseJWT(String jwt) {
        return Jwts.parser()
                .setSigningKey(signingKey())
                .parseClaimsJws(jwt)
                .getBody();
    }
}
