package example.day57_day11_0929;
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

import  example.day57_day11_0929.JwtUtil;

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

    // [2] 로그인 + 쿠키변경( 회원 식별(번호) 쿠키에 담아 클라이언트에 전송 )
    @PostMapping("/login")
    public MemberDto login(@RequestBody MemberDto memberDto, HttpServletResponse response) {

        // 1. 서비스에게 인증/로그인 확인 (기존 유지 )
        MemberDto result = memberService.login(memberDto);
        if( result == null ) return  null; // 로그인 실패
        
        // 2. 로그인 성공시 쿠키 생성/발급
        // 쿠키는 세션과 다르게 클라이언트 내 저장 되므로 회원번호만 저장 (민감한 저장보는 쿠키에 넣지 말자)
        // ResponseCookie cookie = ResponseCookie.from("쿠키명","쿠키값").build();
        // *참고: 정수 -> 문자 타입변환   방법1)  정수+""   방법2) String.valueOf(정수), **쿠키 값은 String 타입** 

        // 4. 토큰 발급 요청
        String token = jwtUtil.createToken( result.getMno() ); // mno --> jwt

        ResponseCookie cookie = ResponseCookie.from("login_member", token )  /* result.getMno()+"" */  // 쿠키값을 jwt 안전하게 변경
                                .path("/") // 쿠키 사용할 경로, "/" 동일한 도메인 내 전체
                                .maxAge( Duration.ofDays(1) )  // Duration.ofXXX( 수 ) , 쿠키의 유효기간
                                .httpOnly(true) // JS 이용ㅎㄴ 탈취 방지, XSS 공격
                                .secure(false)  // Https 에서만 사용,  개발단계: false  배포단계: true
                                .sameSite("Lax")  // CSRF 공격방어
                                .build();  // 쿠키 생성끝

        // 3. 응답 헤더에 쿠키 등록  response.setHeader()
        response.setHeader( HttpHeaders.SET_COOKIE, cookie.toString() );
        
        return result;
        
    }
    
    // [3] 내 정보 조회 + 쿠키
    @GetMapping("/myinfo")
    public MemberDto getMyInfo( @CookieValue (value = "login_member", required = false) String token ) {  //@CookieValue (value = "쿠키명")  요청한 브라우저의 쿠키 가져오기 loginMno
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
    public boolean logout( HttpServletResponse response) {
        // 1. 삭제할 쿠키명과 동일한 이름으로 maxAge(0)하여 재발급
        ResponseCookie cookie = ResponseCookie.from("login_member", "")
                                .path("/") // 모든곳에서 로그아웃 가능하도록, 전체
                                .maxAge(0) // 바로 삭제
                                .httpOnly(true)
                                .secure(false)
                                .build();

        // 2.응답객체내 헤더에 쿠키 
        response.setHeader(HttpHeaders.SET_COOKIE, cookie.toString() );
        return  true;
        
    }
    
    


    
    
}
