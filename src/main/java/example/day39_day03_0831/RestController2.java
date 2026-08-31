package example.day39_day03_0831;

import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




// @Component // 1. 스프링 컨테이너에 객체(빈) 등록
// @Controller // 2. HTTP 서블릿 지원 + @Component 포함
@RestController // 3. 응답content-type 자동  application/json 설정(@ResponseBody 포함) + @Controller
// 활용 : VIEW/화면(HTML) -> @Controller , JSON(값) -> @RestController
@RequestMapping("/day03")  // *클래스내 메소드들의 공통URL 정의
public class RestController2 {
    // 1. 해당 클래스가 @RestController 이면 @ResponseBody 생략가능
    @GetMapping("/task5") // 중복없는 url 정의
    public String task5() { return "서버에서 응답받은 메시지"; }
    
    // ------------------------요청 매개변수 --------------
    // 2. @RequestParam이란? 요청 content-type: (HTML) form 또는 쿼리스트링의 매개변수 매핑/연결, 생략가능 
    @GetMapping("/task6") // 클래스내 동일한 URL에 대해서는 @RequestMapping("동일 경로 부분") 정의한다
    public int task6(@RequestParam String name, @RequestParam int age ) {
        System.out.println(name + "  " + age);
        return 6;
    }
    
    // 3.
    @GetMapping("/task7")
    public int task7( String name , // @RequestParam 생략가능 
        @RequestParam(name = "age") int age,   // @RequestParam(name = "매핑할 매개변수명")
        @RequestParam( required =  false, defaultValue = "10" ) int count 
        // @RequestParam( required =  "필수여부", defaultValue = "기본값" )      
    ){
        System.out.println(name); System.out.println(age); System.out.println(count);
        return 7;
    }

    @DeleteMapping("/task8")
    public int task8( Map<String,Object> map ){ // @RequestParam 생략
        System.out.println(map);
        return 8;
    }
    // 5. 
    @DeleteMapping("/task9")
    public int task9( @ModelAttribute ExamDto examDto ){ // @RequestParam 있으면 오류,  @ModelAttribute 기본값 
        System.out.println(examDto);
        return 9;
    }
    // 6.
    @GetMapping("/task10/{name}/{age}")
    public int task10( @PathVariable(name="name") String name, @PathVariable( name ="age") int age ){
        System.out.println(name); System.out.println(age);
        return 10;
    }
    // 7.
    @PostMapping("/task11")
    public int task11( @RequestBody ExamDto examDto ){
        System.out.println( examDto );
        return 11;
    }
}
/*
    요청 Request  -----> 
                HTTP
                <----- 응답 Response
    * HTTP content-type  : 전송하는 데이터의 타입 뜻
    쿼리스트링이란?     URL?매개변수명=값&매개변수명=값
        - GET / POST / PUT / DELETE 모두 사용가능
    [경로구분자]란? URL/값1/값2

    1. @RequestParam  : 쿼리스트링 또는 content-type:'application/x-www-form-urlencoded'
    2. @ModelAttribute(생략가능) : 쿼리스트링 또는 content-type:'application/x-www-form-urlencoded' (+ DTO 매핑)
    3. @PathVariable : 경로구분자
    4. @RequestBody : content-type: 'application/json'
            -> POST / PUT 메소드만 body(본문) 지원.
    // 하나는 쿼리, 여러개는 바디
*/