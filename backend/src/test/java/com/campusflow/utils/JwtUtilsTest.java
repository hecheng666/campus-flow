package com.campusflow.utils;

import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;
import com.campusflow.config.JwtProperties;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class JwtUtilsTest {

    private JwtUtils newJwtUtils() {
        JwtProperties properties = new JwtProperties();
        properties.setSecret("campus-flow-test-secret-key-must-be-long-enough-2026");
        properties.setExpire(60_000);
        return new JwtUtils(properties);
    }

    @Test
    void shouldGenerateAndParseToken() {
        JwtUtils jwtUtils = newJwtUtils();

        String token = jwtUtils.generateJwt(Map.of("id", 1, "username", "admin"));
        Claims claims = jwtUtils.parseJWT(token);

        assertEquals("1", claims.get("id").toString());
        assertEquals("admin", claims.get("username"));
    }

    @Test
    void shouldRejectTamperedToken() {
        JwtUtils jwtUtils = newJwtUtils();
        String token = jwtUtils.generateJwt(Map.of("id", 1));

        assertThrows(Exception.class, () -> jwtUtils.parseJWT(token + "tampered"));
    }
}
