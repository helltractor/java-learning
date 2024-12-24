package com.helltractor.demo.util;

import com.helltractor.demo.constant.JwtConstant;
import io.jsonwebtoken.*;

import java.util.Date;
import java.util.Map;

public class JwtUtil {
    
    private static Date generateExp() {
        return generateExp(System.currentTimeMillis());
    }
    
    private static Date generateExp(long currentTimeMillis) {
        return new Date(currentTimeMillis + JwtConstant.JWT_TTL);
    }
    
    public static String createJWT(Map<String, Object> claims) {
        return Jwts.builder()
                .claims(claims)
                .expiration(generateExp())
                .signWith(SignatureAlgorithm.HS256, JwtConstant.JWT_SIGNING_KEY)
                .compact();
    }
    
    public static String createJWT(Claims claims) {
        return Jwts.builder()
                .claims(claims)
                .expiration(generateExp())
                .signWith(SignatureAlgorithm.HS256, JwtConstant.JWT_SIGNING_KEY)
                .compact();
    }
    
    public static String createJWT(Map<String, Object> claims, long currentTimeMillis) {
        return Jwts.builder()
                .claims(claims)
                .expiration(generateExp(currentTimeMillis))
                .signWith(SignatureAlgorithm.HS256, JwtConstant.JWT_SIGNING_KEY)
                .compact();
    }
    
    public static String createJWT(String subject) {
        return Jwts.builder()
                .subject(subject)
                .expiration(generateExp())
                .signWith(SignatureAlgorithm.HS256, JwtConstant.JWT_SIGNING_KEY)
                .compact();
    }
    
    public static Jws<Claims> parseJWT(String token) throws JwtException {
        return Jwts.parser()
                .setSigningKey(JwtConstant.JWT_SIGNING_KEY)
                .build()
                .parseClaimsJws(token);
    }
    
    public static boolean verifyJWTExp(String token) {
        try {
            Claims claims = parseJWT(token).getPayload();
            Date exp = claims.getExpiration();
            return exp.after(new Date());
        } catch (JwtException e) {
            return false;
        }
    }
    
    public static String updateJWTExp(String token) throws JwtException {
        Claims claims = parseJWT(token).getPayload();
        return createJWT(claims);
    }
    
}