package example.day39_day03_0831;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication  
// 1. 내장톰캣 지원  ( @EnableAutoConfiguration )
// 2. IOC/DI 컴포넌트 등록 (싱글톤) ( @ComponentScan )
public class AppStart {
    public static void main(String[] args) {
        SpringApplication.run(AppStart.class); // 클래스명.class 리플렉션
        // 즉] Springboot 어노테이션을 포함한 정보
        // 실행 --> http://localhost:8080 ,내장 톰캣과 IOC 컴포넌트들을 등록
    }
    

}
