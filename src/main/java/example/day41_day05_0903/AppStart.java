package example.day41_day05_0903;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@SpringBootApplication
@EnableJpaAuditing // JPA Auditing, JPA Entity 감시 기능을 활성화하는 트리거 어노테이션
public class AppStart {
    public static void main(String[] args) {
        SpringApplication.run( AppStart.class );
    }
    
}
