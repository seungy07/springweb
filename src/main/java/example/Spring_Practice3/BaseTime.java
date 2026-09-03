package example.Spring_Practice3;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@MappedSuperclass
@EntityListeners (AuditingEntityListener.class)
public class BaseTime {
    @CreatedDate
    private LocalDateTime createDate;
    @LastModifiedDate
    private LocalDateTime updateTime;
    
}
// [조건 2] BaseTime 설계 :  모든 엔티티의 생성일·수정일을 자동으로 기록하기 위한 상속 전용 클래스이다.
// 다음과 같은 조건으로 클래스를 작성한다.
// 1. @MappedSuperclass : 엔티티 상속용 클래스 지정
// 2. @EntityListeners(AuditingEntityListener.class) : JPA 감사 기능 활성화
// 3. @CreatedDate, @LastModifiedDate : insert / update 시 자동 시간 기록
// 4. 엔티티에서 extends BaseTime 으로 상속받아 사용한다.