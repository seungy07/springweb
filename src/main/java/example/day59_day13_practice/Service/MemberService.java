package example.day59_day13_practice.Service;


import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;

import example.day59_day13_practice.model.repository.MemberRepository;
import io.jsonwebtoken.lang.Arrays;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Service @RequiredArgsConstructor 
public class MemberService {
    private final MemberRepository memberRepository;

    // 세션
    public boolean sessionAdd(String data, HttpSession session){
        // 세션에 저장된 배열에서  기존배열 꺼내기
        List<String> datas = (List<String>) session.getAttribute("datas");
        if(datas == null){ datas = new ArrayList<>();}  // 최초 요청이면 배열 만들기
        datas.add(data);
        session.setAttribute("datas", datas);
        return true;
    }

    public List<String> sessionAll(HttpSession session){
        List<String> datas = (List<String>) session.getAttribute("datas");
        return  datas == null ? null:datas;
    }

    // 쿠키   (한글과 특수문자는 꺠지기 떄문에 추후 인코드 추가 ) URLEncoder.encode(json, StandardCharsets.UTF_8)
    public boolean cookieAdd(String data, String oldCookie, HttpServletResponse response){
        String value;
        if(oldCookie.isEmpty()){ value = data; }
        else{ value = oldCookie +"-"+data;}
        String encoderValue = URLEncoder.encode(value, StandardCharsets.UTF_8);


        ResponseCookie cookie = ResponseCookie.from("datas", encoderValue)
                                .path("/").maxAge(Duration.ofHours(1))
                                .httpOnly(true).secure(false).build();
        response.addHeader( HttpHeaders.SET_COOKIE , cookie.toString());

        return true;
    }

    public List<String> cookieAll(String datas){
        if(datas == null || datas.isEmpty()){return  null;}
        String[] array = datas.split("-");
        return Arrays.asList(array); // 배열 -> 리스트 변환
    }

    // 레디스
    
}
