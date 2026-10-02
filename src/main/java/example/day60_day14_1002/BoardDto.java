package example.day60_day14_1002;

import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class BoardDto {
    private Long id;
    private String title;
    private String content;
    
    // DTO : 클라이언트와 요청/응답한 값들을 자바형식으로 구성
    // 파일 업로드 (파일은 문자가 아닌 바이트 이므로 특정한 인터페이스 ) String [x] 
    private MultipartFile file;  // 바이트로 받은 파일, 업로드/등록 용
    // private List<MultipartFile> files;   // 첨부파일 여러개를 받을 때
    // 파일 이름 (업로드된 파일명)
    private String fileName;  // 출력용

    private LocalDateTime createDate; // 프론트에 전달할 작성일자
    public BoardEntity toEntity() {
        return BoardEntity.builder()
                .title(title)
                .content(content)
                .build();
    }
    public static BoardDto fromEntity(BoardEntity entity) {
        return BoardDto.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .content(entity.getContent())
                .fileName(entity.getFileName())
                .createDate(entity.getCreateDate())
                .build();
    }
}