package example.day39_day03_0831;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.Data;


// @Component // [싱글톤 대체] 스프링 컨테이너의 개체(빈) 등록
@Controller // [서블릿 대신]<- 원래 상속받아야함. HTTP 통신 지원하는 서블릿 제공 + @Component
public class RestController1 {
    // 1.
    @GetMapping( value = "/day03/task1" )  // HTTP 요청 Url 매핑/연결
    @ResponseBody  // HTTP 응답 JSON 자동 타입 변환 - HTTP content-type
    public int task1(){
        System.out.println("RestController1.task1()"); // soutm + 엔터 : 현재 메소드명 출력
        return 10;  // 
    }
    // 2.
    @GetMapping("/day03/task2") // value 생략 가능
    @ResponseBody
    public String task2( ) {
        System.out.println("RestController1.task2()");
        return "안녕하세yo";  // Content-Type: text/plain
    }
    // 3. 
    @GetMapping("/day03/task3")
    @ResponseBody
    public Map<String,Object> task3(){ // map 컬렉션 프레임워크  [ {key : value}, {key : value} ]
        Map<String,Object> map = new HashMap<>();
        map.put("유재석", 100);
        map.put("강호동", 90);
        return map;   // Content - type : 	application/json
    }
    // 4.
    @GetMapping("/day03/task4")
    @ResponseBody
    public ExamDto task4() {
        ExamDto dto = new ExamDto(); dto.setName("유유유"); dto.setAge(20);
        return dto;   //  Content-Type:	application/json   
    }
}
// DTO
@Data  // 롬복
class ExamDto{String name;int age; }
/*
    @ResponseBody 란? 자바의 타입 --> HTTP content-type 변환하여 동일하게 사용/직렬화
    - String : text/plain;
    - 그외 : application/json 
*/