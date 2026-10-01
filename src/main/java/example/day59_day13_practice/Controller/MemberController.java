package example.day59_day13_practice.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import example.day59_day13_practice.Service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController @RequestMapping ("/api")
@RequiredArgsConstructor 
public class MemberController {
    private  final MemberService memberService;
    private  final StringRedisTemplate stringRedisTemplate;

    // 세션
    @GetMapping("/session/add")
    public String sessionAdd(@RequestParam(name = "data") String data, HttpSession session) {
        boolean result = memberService.sessionAdd(data,session);
        return result ? "세션 저장 성공" : "세션 저장 실패";
    }

    @GetMapping("/session/all")
    public List<String> sessionAll(HttpServletRequest request) {
        HttpSession session = request.getSession(false); // 기존세션 반환, 세션없을시 ) false -> null반환  () -> 새 세션 생성
        if(session != null){
            return memberService.sessionAll(session);
        }else{ return null; }
    }
    

    // 쿠키
    @GetMapping("/cookie/add")
    public String cookieAdd(
        @RequestParam(name = "data") String data,
        @CookieValue (value = "datas", required=false) String oldCookie,
        HttpServletResponse response ) {
        if( oldCookie == null ){ oldCookie = ""; }
        boolean result = memberService.cookieAdd(data,oldCookie,response);
        if(result){return "쿠키저장성공";}
        else{return "쿠키정장실패";}
    }
    
    @GetMapping("/cookie/all")
    public List<String> cookieAll(@CookieValue (value = "datas", required = false)String datas) {
        return memberService.cookieAll(datas);
    }
    

    // 레디스
    @GetMapping("/redis/add")
    public String rediaAdd(@RequestParam(name = "data") String data) {
        stringRedisTemplate.opsForValue().set(data, data);
        return "레디스 저장 성공";
    }

    @GetMapping("/redis/all")
    public List<String> redisAll() {
        Set<String> keys = stringRedisTemplate.keys("*");
        List<String> list = new ArrayList<>();
        for(String key:keys){
            String data = stringRedisTemplate.opsForValue().get(key);
            list.add(key);
        }
        return list;
    }
    
    
    
}
