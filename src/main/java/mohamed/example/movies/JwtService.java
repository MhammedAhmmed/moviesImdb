package mohamed.example.movies;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Base64;
import java.util.function.Function;

public class JwtService {
    private static final String secret_key = "bb146475e68053b7063436fa5999241bcbbf93a298b845e05e0558394af25437af2bb6ae00ab67edaf89be43a741dd3fa99ac4235d9f013ade13fb45cce186ae";

    public String extractUsrName(String token){
        return extractClaim(token, Claims::getSubject); // subject is the email
    }
    <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    private Claims extractAllClaims(String token){
        return Jwts.
                parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJwt(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secret_key);
        return Keys.hmacShaKeyFor(keyBytes);        // Algorithm for SignIn
    }
}
