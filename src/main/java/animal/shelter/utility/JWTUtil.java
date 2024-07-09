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
    private final long JWT_EXPIRATION = 86400000; // 1 dag
    private static SecretKey key;

    public JWTUtil() {
        key = Keys.hmacShaKeyFor(Base64.getDecoder().decode(JWT_KEY));
    }

    public static String generateToken(int id, String role, String email) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        long expMillis = nowMillis + 86400000; // 1 day
        Date exp = new Date(expMillis);

        return Jwts.builder()
                .claim("idUser", id)
                .claim("role", role)
                .claim("email", email)
                .setIssuedAt(now)
                .setExpiration(exp)
                .signWith(key)
                .compact();
    }


    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getRoleFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().get("role", String.class);
    }

    public int getUserIdFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().get("idUser", Integer.class);
    }
}
