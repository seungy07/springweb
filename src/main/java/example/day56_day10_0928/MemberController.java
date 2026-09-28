package example.day56_day10_0928;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController @RequestMapping ("/api/member")
public class MemberController {

    @GetMapping("")
    public String test( HttpServletRequest request ){
        // 1) HttpServletRequest request : HTTP 요청이 들어오면 요청 정보가 담겨 있는 서블릿 객체
        System.out.println( request.getRemoteAddr() ); // 요청한 클라이언트의 IP (로그/위치추적)
        System.out.println( request.getHeader("User-Agent") ); // 요청한 클라이언트 브라우저 정보
        System.out.println( request.getSession() ); // 요청한 클라이언트의 세션객체 정보 확인

        // 2) 세션객체란? 톰캣 서버내 브라우저 마다 독립적인 저장소 (엣지/크롬 등등)
        // 주로 : 로그인성공정보, 인증번호, 비회원제 장바구니 등등 일시적인 휘발성 저장소(메모리)
        HttpSession session = request.getSession();  // 세션 객체내 여러개 정보 저장 가능
        System.out.println( session.getId() ); // 세션 식별번호 : Id 
        System.out.println( session.getCreationTime() ); // 세션 생성시간 : CreationTime
        System.out.println( session.getLastAccessedTime() ); // 세션 마지막 접근시간 
        System.out.println( session.getMaxInactiveInterval() ); // 세션 생명주기( 기본값 30분 )

        // 3) 세션 정보 저장(로그인) / 호출(마이페이지) / 삭제(로그아웃)
        session.setAttribute("data", "사과"); // map( key: value ) 구조
        // data 이름(key)으로 사과(data)저장, 주의할점: value 타입은 Object이라 타입변환 필요
        System.out.println( session.getAttribute("data")); // key 이용한 value 호출
        session.invalidate(); // 세션 (해당 브라우저만) 초기화
        
        return session.getId();

    }
    
    
}
