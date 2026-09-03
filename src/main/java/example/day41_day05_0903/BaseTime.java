package example.day41_day05_0903;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;

// @Entity로 테이블로 만들지 않고 상속받은 자식들에 쓸수있게 한다
@Getter
@NoArgsConstructor
@MappedSuperclass // 공통 매핑 정보가 필요할 때 부모 클래스에 선언
@EntityListeners( AuditingEntityListener.class ) // 이벤트(Persist,update 등) 감지하는 리스너 등록
//                  리스너 구현체를 객체 등록
public class BaseTime {
    // 1. 레코드 생성시점
    @CreatedDate  // 엔티티가 영속화(insert) 될 때의 현재 일시를 자동으로 기록
    private LocalDateTime createdate;

    // 2. 레코드 변경시점
    @LastModifiedDate // 엔티티 데이터가 변경될(update) 떄의 변경 일시를 자동으로 갱신
    private LocalDateTime upDateTime;
}
