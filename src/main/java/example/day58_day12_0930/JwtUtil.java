package example.day58_day12_0930;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Date;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component // 스프링 MVC 패턴 객체가 아닌 일반 객체(빈) 생성
public class JwtUtil {
    @Value ("${jwt.secret}")  // {속서명}  속성값 주입  , propertis 파일내 api 인증키 또는 개발자 보안데이터들 넣어 안전하게 사용 목적
    private String key;   // 오류뜸
    
    // sha알고리즘 + 비밀키(임의로) 조합 -> hmacSha
    private SecretKey secretKey;
    @PostConstruct // 객체 생성시 의존성(@Value)가 완료 된 후에 아래 메소드가 1번 호출 되도록하는 어노테이션
    public void init(){     //hmacSha 알고리믖ㅁ
        this.secretKey = Keys.hmacShaKeyFor( key.getBytes(StandardCharsets.UTF_8) );
    }

    // [3] JWT Refresh 토큰 생성 메소드
    public String createRefreshToken( Long mno ){
        return Jwts.builder() // 토큰 생성 시작
                .claim("type", "REFRESH")
                .subject(mno+"")
                .issuedAt(new Date())
                .expiration( new Date(new Date().getTime()+ 1000L*60*60*24*7)) // 액세스 토큰보다 만료기간을 길게 7일
                .signWith(secretKey)
                .compact(); // 생성된 토큰 문자열 반환
    }

    // [1] JWT 액세스 토큰 생성 메소드
    public String createAccessToken( Long mno ){
        String jwt = Jwts.builder() // 토큰 생성시작
                    .claim("type", "ACCESS")
                    .subject( mno+"" ) // 토큰에 들어갈 내용(payload)들( 주로 식별번호, 권한)
                    .issuedAt(new Date() ) // 토큰 생성 시간 , new Date() 현재 시간
                    .expiration( new Date( new Date().getTime() + 1000L * 60 * 30 ) )   // 토큰 만료 시간
                    // new Date() 현재시간, new Date().getTime() 현재시간초, * 60(1분) * 60(1시간)  
                    .signWith(secretKey) // 비밀키로 전자서명
                    .compact(); // 토큰 생성끝, 토큰 정보 문자열로 반환
        System.out.println(jwt);
        return jwt;
    }

    // [2] JWT 토큰 검증 메소드
    public Long getMnoFromToken( String token){
        // 만약에 token 파싱(가져오기) 실패이면 예외 발생
        try{
        Claims claims = Jwts.parser() // 파싱
                        .verifyWith( secretKey ) //전자서명 이용한 검증
                        .build()
                        .parseSignedClaims(token) // 파싱할 토큰
                        .getPayload(); // JWT 안에 payload

        Long mno = Long.parseLong(claims.getSubject()) ; // payload 안에 subject 꺼내기 (문자열 타입 --> Long 타입 변환)
        System.out.println(mno);
        return mno;  // 토큰 검증이 성공이면 회원번호 반환
        }catch(Exception e){return null;} // 토큰이 없거나 문제가 있으면 null 반환
    }
    

}
