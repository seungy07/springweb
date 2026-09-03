package example.day41_day05_0903;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor@AllArgsConstructor@Builder
@Setter@Getter@ToString
public class TestDto {  // 서로 계층간 이동객체( Controller에서는 Entity 사용금지)
    // 엔티티와 동일하게 멤버변수 구성; 기능별로 DTO 구성 예] 등록 DTO, 조회DTO, 수정 DTO
    private Integer no;
    private String name;
    private String descri;
    private Integer price;
    private LocalDateTime createDate;
    private LocalDateTime updateTime;
    
    // DTO --> ENTITY 함수 : C -> S ( D->E ) , toEntity( ) , 주로 save/update 목적
    // thisㄹㄴ 해당 메소드 호출한 인스턴스 가리킴.
    public TestEntity toEntity(){
        return TestEntity.builder()  // 빌터패턴이란? new 대신에 객체생성을 메소드 방식 지원
                .name(this.name)
                .descri(this.descri)
                .price(this.price)
                .build();
    }

    // Entity ---> DTO 함수 : S -> C ( E->D ), from( Entity entity ), 주로 find
    // static ? 인스턴스 없이 호출가능 메소드/멤버변수
    // static 은 인서턴스 없이 사용하는 메소드
    public static TestDto from( TestEntity testEntity ){
        return TestDto.builder() // 빌더시작, 순서/개수 상관없이 자유롭게 객체생성가능
                .name( testEntity.getName())
                .descri( testEntity.getDescri())
                .createDate( testEntity.getCreateDate())
                .no(testEntity.getNo())
                .price(testEntity.getPrice())
                .updateTime(testEntity.getUpdateTime())
                .build();
    }
    
    
}
