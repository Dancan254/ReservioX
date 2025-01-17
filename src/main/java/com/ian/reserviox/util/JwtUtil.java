package com.ian.reserviox.util;

import com.ian.reserviox.enums.UserRole;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {
    public static final String SECRET = "secret";

    private String createToken(Map<String, Object> claims, String userName, UserRole userRole) {
        Map<String, Object> mutableClaims = new HashMap<>(claims);
        mutableClaims.put("role", userRole.name());
        return Jwts.builder().setClaims(mutableClaims).setSubject(userName)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, SECRET).compact();
    }

    public String generateToken(String username, UserRole userRole) {
        return createToken(new HashMap<>(), username, userRole);
    }

    public String extractUsername(String token){
        return extractAllClaims(token).getSubject();
    }
    private Date extractExpiration(String token){
        return extractClaim(token, Claims::getExpiration);
    }
    private Boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }
    public Boolean validateToken(String token, String username){
        return (extractUsername(token).equals(username) && !isTokenExpired(token));
    }
    private Claims extractAllClaims(String token){
        return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
    }
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
}