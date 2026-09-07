package example.Spring_Practice4.model.dto;

import java.time.LocalDateTime;

import example.Spring_Practice4.model.entity.EnrollEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor @NoArgsConstructor @Builder @Data 
public class EnrollDto {
    private Integer enrollId;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // 자바(JPA)에서 entity로 Fk 사용하지만 입력받을 경우 FK 번호 받음.
    private Integer courseId;
    private Integer studentId;
    // + 과정명 , 학생명
    private String courseName;
    private  String studentName;

    // + toEntity
    public EnrollEntity toEntity(){
        return EnrollEntity.builder()
                .status(this.status)
                // 학생 FK, 과정FK 은 서비스에서 엔티티로변환
                .build();
    }

    // + from
    public  static  EnrollDto from( EnrollEntity entity){
        return EnrollDto.builder()
            .enrollId(entity.getEnrollId())
            .status(entity.getStatus())
            .courseName(entity.getCourseEntity().getCourseName())
            .studentName(entity.getStudentEntity().getStudentName()).build();
    }

    
}
