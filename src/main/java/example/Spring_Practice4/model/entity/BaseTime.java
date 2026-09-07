package example.Spring_Practice4.model.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter @NoArgsConstructor  // 1.자식클래스가 호출할 수 있도록
@MappedSuperclass // 테이블이 아닌 상속용도
@EntityListeners( AuditingEntityListener.class )
public class BaseTime {
    @CreatedDate 
    private  LocalDateTime createdAt;
    @LastModifiedDate 
    private LocalDateTime updatedAt;
    
}
