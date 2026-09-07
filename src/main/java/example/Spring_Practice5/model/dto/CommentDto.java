package example.Spring_Practice5.model.dto;

import java.time.LocalDateTime;

import example.Spring_Practice5.model.entity.CommentEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor @AllArgsConstructor @Builder @Data 
public class CommentDto {
    private Integer commentId;
    private String author;
    private String password;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public CommentEntity toEntity(){
        return CommentEntity.builder()
                .author(this.author)
                .password(this.password)
                .content(this.content).build();
    }

    public static CommentDto from( CommentEntity entity){
        return CommentDto.builder()
                .commentId(entity.getCommentId())
                .author(entity.getAuthor())
                .password(entity.getPassword())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .content(entity.getContent()).build();
    }

    
}
