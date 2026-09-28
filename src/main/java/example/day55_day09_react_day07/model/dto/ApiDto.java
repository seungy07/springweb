package example.day55_day09_react_day07.model.dto;

import java.time.LocalDateTime;

import example.day55_day09_react_day07.model.entity.ApiEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor @NoArgsConstructor @Builder @Data 
public class ApiDto {
    private Integer idx;
    private String subject;
    private String name;
    private String regdate;
    private String content;
    

    // toEntity
    public ApiEntity toEntity(){
        return ApiEntity.builder()
                .subject(this.subject)
                .name(this.name)
                .regdate(LocalDateTime.now().toString())
                .content(this.content).build();
    }

    // from
    public static ApiDto from(ApiEntity apiEntity){
        return ApiDto.builder()
                .subject(apiEntity.getSubject())
                .name(apiEntity.getName())
                .idx(apiEntity.getIdx())
                .regdate(apiEntity.getRegdate())
                .content(apiEntity.getContent()).build();
    }
    
}
