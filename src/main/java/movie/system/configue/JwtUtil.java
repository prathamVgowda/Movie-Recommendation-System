package movie.system.configue;

import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {

    private final String secret = "VGhpcy1pcy1hLXNlY3VyZS1qd3Qtc2VjcmV0LWtleS0yNTY=";

    // ===============================
    // Generate Access Token (15 min)
    // ===============================
    public String generateAccessToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 15 * 60 * 1000)) // 15 min
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    // ===============================
    // Generate Refresh Token (7 days)
    // ===============================
    public String generateRefreshToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3 * 60 * 60 * 1000)) // 3 hours	
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    // ===============================
    // Extract username from token
    // ===============================
    public String extractUsername(String token) {
        return getClaims(token).getSubject();
    }

    // ===============================
    // Extract expiration
    // ===============================
    public Date extractExpiration(String token) {
        return getClaims(token).getExpiration();
    }

    // ===============================
    // Check if token is expired
    // ===============================
    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // ===============================
    // Validate token (for refresh token use)
    // ===============================
    public boolean validateToken(String token, String username) {
        return extractUsername(token).equals(username) && !isTokenExpired(token);
    }

    // ===============================
    // Get Claims
    // ===============================
    private Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }
}
