package example.day59_day13_practice.utill;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

@Component 
public class JwtUtill {
    @Value (" ${jwt.secret} ")
    private String key;
    private SecretKey secretKey;

    @PostConstruct 
    public void init(){
        this.secretKey = Keys.hmacShaKeyFor(key.getBytes(StandardCharsets.UTF_8));
    }

    // refresh Token 생성
    public String createRefresh(Long mno){
        return  Jwts.builder()
                .claim("type", "Refresh")
                .subject(mno+"").issuedAt(new Date())
                .expiration(new Date(new Date().getTime()+1000L*60*60))
                .signWith(secretKey).compact();
    }

    // access Token 생성
    public String createAccess(Long mno){
        return  Jwts.builder().claim("type", "Access")
                .subject(mno+"").issuedAt(new Date())
                .expiration(new Date(new Date().getTime()+1000L*60*30))
                .signWith(secretKey).compact();
    }

    // Token 검증
    public Long getMnoFromToken( String token){
        try{
            Claims claims = Jwts.parser()
                            .verifyWith(secretKey).build()
                            .parseSignedClaims(token).getPayload();
            return  Long.parseLong(claims.getSubject());
        }catch(Exception e) {return null;}
    }
    
}
