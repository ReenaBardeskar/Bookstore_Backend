//package com.example.bookStoreWebApp.security;
//
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import java.util.Date;
//import org.springframework.stereotype.Component;
//
//@Component
//public class JwtTokenUtil {
//
//    private final String secretKey = "your-secret-key"; // Use a more secure key in production
//    private final long expirationTime = 864_000_000; // 10 days in milliseconds
//
//    public String generateToken(String username) {
//        return Jwts.builder()
//                .setSubject(username)
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
//                .signWith(SignatureAlgorithm.HS256, secretKey)
//                .compact();
//    }
//
//    public String getUsernameFromToken(String token) {
//        return Jwts.parser()
//                .setSigningKey(secretKey)
//                .parseClaimsJws(token)
//                .getBody()
//                .getSubject();
//    }
//
//    public boolean isTokenExpired(String token) {
//        return Jwts.parser()
//                .setSigningKey(secretKey)
//                .parseClaimsJws(token)
//                .getBody()
//                .getExpiration()
//                .before(new Date());
//    }
//
//    public boolean validateToken(String token, String username) {
//        return (username.equals(getUsernameFromToken(token)) && !isTokenExpired(token));
//    }
//}
