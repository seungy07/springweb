package example.day62_day15_1007;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

/*
    WebSocket + Stomp : *양방향,클라이언트 <--> 서버,  채팅 구현 (실시간 대화 방)
    SSE : *단방향, 클라이언트 <-- 서버,  알림
*/

@Service  
public class NoticeService {
    /*
        1. 연결된 클라이언트들의 정보 보관 리스트
            * 서버가 클라이언트에게 비동기 요청 보내기 위한 HTTP 응답 파이프 라인
            * 동기/비동기 : 동기화(하나씩 메소드를 순차적으로 실행), 비동기화(하나의 메소드와 여러 메소드를 동시 실행)
            * ArrayList() 동기화 지원X , Vector() 동기화 지원O 
        ==> 동기화 필요 목적 : 하나의 서버가 구독과 메시지 전송 동시 다발적으로 실행시 순차처리를 위해
        CopyOnWriteArrayList() : 여러개의 요청들을 동시에 접속,종료,메시지 전송, 동시성 제공(쓰레드)
    */
    private final List<SseEmitter> emitters = new CopyOnWriteArrayList<>();

    // 2. 클라이언트 구독 처리 (SseEmitter 생성하여 리스트에 저장)
    public SseEmitter subscribe(){
        SseEmitter emitter = new SseEmitter(); // SseEmitter 객체 생성
        emitters.add(emitter); // 리스트에 저장 (중재자(브로커) 없기 때문에 직접 저장을 해줘야 한다)
        // 2-4 : 안전하게 클라리언트 연결이 비정상이면 리스트에서 삭제
        // * 리액트에서 중복되는 구독 신청으로 문제발생
        emitter.onCompletion(()->emitters.remove(emitter));
        emitter.onTimeout(()-> emitters.remove(emitter));
        emitter.onError((e)-> emitters.remove(emitter));

        return emitter; // 생성된 객체를 반환
    }

    // 3. 메시지 전송 (서버가 클라이언트에게 메시지 전송)
    public void onMessage( String message ) {
        // 3-1 : 현재 리스트에 저장/구독된 emitter 들에게 메시지 보내기
        for( SseEmitter emitter : emitters){
            // 3-2 : send( SseEmitter.event().name("구독식별").data(내용물) ) ;
            try{
                emitter.send( SseEmitter.event().name("notice").data(message) );
                // 3-3 : 메시지 전송 실패시 실패한 emitter 삭제
            }catch(IOException e){ emitters.remove(emitter); }
        }
    }
    


    
}
