package devops_tracker.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component

public class JwtUtil {

    private final String SECRET =
            "mysecretkeymysecretkeymysecretkey";

    private final SecretKey SECRET_KEY =
            Keys.hmacShaKeyFor(SECRET.getBytes());

    // GENERATE TOKEN
    public String generateToken(String username) {

        return Jwts.builder()

                .subject(username)

                .issuedAt(new Date())

                .expiration(
                        new Date(System.currentTimeMillis() + 1000 * 60 * 60)
                )

                .signWith(SECRET_KEY, SignatureAlgorithm.HS256)

                .compact();
    }

    // EXTRACT USERNAME
    public String extractUsername(String token) {

        return extractClaims(token).getSubject();
    }

    // VALIDATE TOKEN
    public boolean validateToken(String token, String username) {

        return extractUsername(token).equals(username);
    }

    // EXTRACT CLAIMS
    private Claims extractClaims(String token) {

        return Jwts.parser()

                .verifyWith(SECRET_KEY)

                .build()

                .parseSignedClaims(token)

                .getPayload();
    }
}