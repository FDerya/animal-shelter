package animal.shelter.utility;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Base64;

@Component
public class JWTUtil {

    private final String JWT_KEY = "aWLkXoJCPvt/V8kPEYyhTdYvIvyd0t+gI+rCFqhJdaE=";
    private static final long JWT_EXPIRATION = 86400000; // 1 dag
    private static SecretKey key;

    public JWTUtil() {
        key = Keys.hmacShaKeyFor(Base64.getDecoder().decode(JWT_KEY));
    }

    // Generates a JWT token with the given user details.
    public static String generateToken(int id, String role, String email) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        Date exp = new Date(JWT_EXPIRATION);

        return Jwts.builder()
                .claim("idUser", id)
                .claim("role", role)
                .claim("email", email)
                .setIssuedAt(now)
                .setExpiration(exp)
                .signWith(key)
                .compact();
    }


    // Validates the given JWT token.
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Extracts the role from the given JWT token.
    public String getRoleFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().get("role", String.class);
    }

    // Extracts the user ID from the given JWT token.
    public int getUserIdFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().get("idUser", Integer.class);
    }
}
