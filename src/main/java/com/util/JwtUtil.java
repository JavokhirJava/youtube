package com.util;

import com.dto.jwt.JwtDTO;
import com.enums.ProfileRole;
import com.exps.MethodNotAllowedException;
import io.jsonwebtoken.*;

import java.util.Date;

public class JwtUtil {
    private static final int tokenLiveTime = 1000 * 3600 * 24; // 1-day
    private static final String secretKey = "youtube123mazgi";

    public static String encode(String email , ProfileRole role) {
        JwtBuilder jwtBuilder = Jwts.builder();
        jwtBuilder.setIssuedAt(new Date());
        jwtBuilder.signWith(SignatureAlgorithm.HS512, secretKey);

        jwtBuilder.claim("email", email);
        jwtBuilder.claim("role", role);

        jwtBuilder.setExpiration(new Date(System.currentTimeMillis() + (tokenLiveTime)));
        jwtBuilder.setIssuer("Kun uz test portali");
        return jwtBuilder.compact();
    }

    public static String encode(String text) {
        JwtBuilder jwtBuilder = Jwts.builder();
        jwtBuilder.setIssuedAt(new Date());
        jwtBuilder.signWith(SignatureAlgorithm.HS512, secretKey);
        jwtBuilder.claim("email", text);
        jwtBuilder.setExpiration(new Date(System.currentTimeMillis() + (tokenLiveTime)));
        jwtBuilder.setIssuer("Kunuz test portali");
        return jwtBuilder.compact();
    }
    public static JwtDTO decode(String token) {
        JwtParser jwtParser = Jwts.parser();
        jwtParser.setSigningKey(secretKey);
        Jws<Claims> jws = jwtParser.parseClaimsJws(token);
        Claims claims = jws.getBody();
        String email = (String) claims.get("email");
        String role = (String) claims.get("role");
        ProfileRole profileRole = ProfileRole.valueOf(role);
        return new JwtDTO(email, profileRole);
    }
    public static String decodeEmailVerification(String token) {
        try {
            JwtParser jwtParser = Jwts.parser();
            jwtParser.setSigningKey(secretKey);
            Jws<Claims> jws = jwtParser.parseClaimsJws(token);
            Claims claims = jws.getBody();
            return (String) claims.get("email");
        } catch (JwtException e) {
            e.printStackTrace();
        }
        throw new MethodNotAllowedException("Jwt exception");
    }

}