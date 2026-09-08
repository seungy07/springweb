package example.Spring_day44.model.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import example.Spring_day44.model.entity.BoardEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor @AllArgsConstructor @Builder @Data 
public class BoardDto {
    private Integer id;
    private String author;
    private String password;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private List<CommentDto> comments = new ArrayList<>();

    // toEntity
    public BoardEntity toEntity(){
        return BoardEntity.builder()
                .author(this.author)
                .password(this.password)
                .content(this.content)
                .build();
    }

    // from
    public static BoardDto from( BoardEntity entity){
        return BoardDto.builder()
                .id(entity.getId())
                .author(entity.getAuthor())
                .content(entity.getContent())
                .password(entity.getPassword())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
    
}
