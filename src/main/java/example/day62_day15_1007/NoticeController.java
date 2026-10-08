package example.day62_day15_1007;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController 
@RequestMapping ("/api/sse")
@RequiredArgsConstructor @CrossOrigin (origins = "*")
public class NoticeController {
    // 1. 알림서비스 호출
    private final NoticeService noticeService;
    
    // 1. 알림 구독 매핑
    @GetMapping(value = "/subscribe", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter subscribe() {
        return noticeService.subscribe();
    }
    
    // 2. 알림 메시지 전송 *테스트
    @GetMapping("/message")
    public void onMessage(@RequestParam(name = "msg") String msg ){
        noticeService.onMessage(msg);
    }
    
}
