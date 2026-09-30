package example.day58_day12_0930;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

import java.time.Duration;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import  example.day58_day12_0930.JwtUtil;

@RestController @RequestMapping ("/api/member")
@RequiredArgsConstructor 
@CrossOrigin (origins = "http://localhost:5173/", allowCredentials = "true" )  // CORS 허용, 쿠키허용, 도메인이 다른 경우 쿠키/세션 유지
public class MemberController {
    private final MemberService memberService;

    private final JwtUtil jwtUtil;

    // [1] 회원가입
    @PostMapping("/signup")
    public boolean signUp(@RequestBody MemberDto memberDto) {
        return memberService.signUp(memberDto);
    }

    private final RedisTokenService redisTokenService;
    // [2] 로그인 + 
    @PostMapping("/login")
    public MemberDto login(@RequestBody MemberDto memberDto, HttpServletResponse response) {

        // 1. 서비스에게 인증/로그인 확인 (기존 유지 )
        MemberDto result = memberService.login(memberDto);
        if( result == null ) return  null; // 로그인 실패
        
        // 4. 토큰(token) **2개** 발급 요청
        String accessToken = jwtUtil.createAccessToken( result.getMno() );
        String refreshToken = jwtUtil.createRefreshToken( result.getMno() );

        // 5. refreshToken만 redis 저장
        redisTokenService.setRefreshToken(result.getMno(), refreshToken);

        // 2. 로그인 성공시 쿠키 2개 생성 / 발급,  쿠키만료기간 == 토큰만료기간 동일 권장
        ResponseCookie cookie1 = ResponseCookie.from("accessToken", accessToken).path("/").maxAge(Duration.ofMinutes(30))
                                                .httpOnly(true).secure(false).sameSite("Lax").build();

        ResponseCookie cookie2 = ResponseCookie.from("refreshToken", refreshToken).path("/").maxAge(Duration.ofDays(7))
                                                .httpOnly(true).secure(false).sameSite("Lax").build();       
                                                
        // 3. 응답 헤더에 쿠키 2개 등록  response.setHeader()
        response.addHeader( HttpHeaders.SET_COOKIE, cookie1.toString() );
        response.addHeader( HttpHeaders.SET_COOKIE, cookie2.toString() );
        
        return result;
        
    }
    
    // [3] 내 정보 조회 + 쿠키
    @GetMapping("/myinfo")
    public MemberDto getMyInfo( @CookieValue (value = "accessToken", required = false) String token ) {  //@CookieValue (value = "쿠키명")  요청한 브라우저의 쿠키 가져오기 loginMno
        // 1.만약에 loginMno가 없다면 , 만약에 token 없다면 
        // if(loginMno == null){ return null;}
        if(token == null){ return null;}
        // *** 쿠키에 저장된 token 이용하여 회원번호 찾기 
        Long loginMno = jwtUtil.getMnoFromToken(token);

        // 2.로그인 중이면 서비스에게 회원정보 요청
        // 참고: 문자 -> 기본타입  변환  방법1) 기본타입.parse타입(문자) ,Long.parseLong(loginMno
        return memberService.getMyInfo( loginMno );
    }
    

    // [4] 로그아웃 + 쿠키
    @PostMapping("/logout")
    public boolean logout(@CookieValue(value = "accessToken", required = false) String accessToken, HttpServletResponse response) {
        // 1. 만역에 accessToken 존재하면 회원번호 조회
        if(accessToken != null){
            Long mno = jwtUtil.getMnoFromToken(accessToken);  // 토큰의 주인 찾기
            // 2. 회원번호가 조회되면 레디스내 refresh 삭제
            redisTokenService.deleteRefreshToken(mno);  // refresh 토큰 삭제
        }

        // 3. 쿠키 2개 삭제
        ResponseCookie cookie1 = ResponseCookie.from("accessToken", "").path("/").maxAge(0).httpOnly(true).secure(false).build();
        ResponseCookie cookie2 = ResponseCookie.from("refreshToken", "").path("/").maxAge(0).httpOnly(true).secure(false).build();

        // 3. 응답 헤더에 쿠키 2개 등록  response.setHeader()
        response.addHeader( HttpHeaders.SET_COOKIE, cookie1.toString() );
        response.addHeader( HttpHeaders.SET_COOKIE, cookie2.toString() );

        return true;
        
    }
    
    


    
    
}
