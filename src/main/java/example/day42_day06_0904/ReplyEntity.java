package example.day42_day06_0904;

import org.hibernate.engine.internal.Cascade;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "reply")
@AllArgsConstructor@NoArgsConstructor@Builder@Data
public class ReplyEntity {
    @Id
    private Integer rno;
    private String rname;

    // 단방향 참조
    @ManyToOne( cascade = CascadeType.ALL ,fetch = FetchType.EAGER )
    @JoinColumn(name = "bno")
    private BoardEntity boardEntity;
    
}
/*  - 영속성이란? 자바는 (휘발성)영구저장이 불가능 하므로 DB 매핑/연결하여 영속성(영구저장) 표현
        - Entity entity = new Entity();  -> 객체
        - repository.save(), repository.findAll(), repository.findById() 등등 결과 영속된
        - 즉] Entity 영속된entity = repository.save( 비영속 entity )

    - @ManyToOne( cascade = 영속성 제약조건 , fetch = 불러오는시기 )
        CascadeType.REMOVE : 만일 부모 엔티틱 삭제되면 자식 엔티티 같이 삭제.
        CascadeType.MERGE : 만일 부모 엔티티가 수정되면 자식 엔티티 수정 같이 반영.
        CascadeType.DETACH : 만일 부모 엔티티가 영속(연결)해제 하면 자식 엔티티 같이 해제.
        CascadeType.REFRESH : 만약 부모 엔티티가 재호출(갱신) 되면 자식 엔티티 같이 갱신.
        CascadeType.PERSIST : 만약 부모 엔티티가 저장 하면 자식 엔티티 같이 저장.
        CascadeType.ALL : 위 속성들을 모두 사용

    - fetch 
        FetchType.LAZY : 해당 엔티티 조회시 자식(참조) 엔티티 불러오지 않는다. (자식이 많을시 속도)
            - 초기 로딩 빠르다. 재사용성 느리다, 필요한 정보만 불러온다.<지연로딩>
        FetchType.EAGER : 해당 엔티티 조회시 자식(참조) 엔티티 (즉시)불러온다. ( 부모조회시 자식(손자)까지 포함 )
            - 기본값, 초기 로딩 느리다, 재사용성 빠르다 , 불필요한 정보까지 불러온다(성능저하)
    

*/