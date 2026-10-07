package example.day62_day15_1007;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor @AllArgsConstructor @Builder @Data 
public class MessageDto {
    private String type; // 메시지 형식, TALK / ENTER 접속
    private String roomId; // 방번호
    private String sender; // 보낸 사람
    private String content; // 보낸 내용
    private String date; // 보낸 시간
    
}
