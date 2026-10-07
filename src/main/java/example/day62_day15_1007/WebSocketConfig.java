package example.day62_day15_1007;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;



@Configuration // 스프링 컨테이너 ( 설정클래스 )빈 등록
// @Controller @Service @Repository @RestController (컨트롤러+ResopnseBody 응답객체 - 자동직렬화 클래스 빈 등록) 
// @Component // 스프링 컨테이너 일반 객체 빈 등록  (스프링 아키텍쳐와 관계없는 클래스) 위 어노테이션 모두가 포함
// --> 스프링에서 해당 클래스들을 확인하여 빈(객체) 생성하여 컨테이너(메모리/저장소) 저장, 시점: 스프링서버 실행시 @SpringBootApplication
@EnableWebSocketMessageBroker // STOMP 프로토콜 브로커 기능을 사용하는 컴포넌트 등록
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    // implements : 인터페이스 구현하겠다는 키워드 vs extends : 클래스 확장/상속 받겠다는 뜻
    // 인터페이스 주 역할 : 추상메소드(구현안된) 가지고 있는 타입, 메소드/기능 통합

    // 2. 
    @Override // 오버라이딩: 같은 메소드명을 상속이면 재정의, 인터페이스면 정의하여 사용
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // 2-1 : 구독( 양방향 연결 ) 요청하는 방법/주소 정의
        // registry.enableSimpleBroker("/구독주소");
        registry.enableSimpleBroker("/sub"); // ("/연결주소")

        // 2-2 : 구독(양방향 연결)된 상태에서 메시지 주고 받는 방법/주소/엔드포인트 등록
        registry.setApplicationDestinationPrefixes("/pub"); // 엔드포인트 경로 지정 ("/발행주소")
        
    }

    // 3.  
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws-chat")
                .setAllowedOriginPatterns("*"); // CORS 정책 때문, 모든 도메인 허용 
    }
    
    
    
}

/*
    HTTP : 단방향통신 , 무상태  = 클라이언트 요청 1개당 응답 1개 (요청없이 응답 불가능)
        - CRUD
    WebSocket : 양방향통신, 상태유지(stateful) = 한번 연결 후 연결된 상태에서 양방향통신
        - 채팅, 알림, 실시간 통신
        - STOMP (브로커)

    1. 의존성 추가
    2. 브로커 설정 클래스
        - 구독 주소 : ws://localhost:8080/sub       , 특정 방/경로 구독
        - 발생 주소 : ws://localhost:8080/pub       , 특정 방/경로 메시지 발행
        - 소켓 주소 : ws://localhost:8080/ws-chat   , 백엔드-프론트 연결
        
*/