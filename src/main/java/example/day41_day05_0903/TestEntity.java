package example.day41_day05_0903;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table( name = "test")
@NoArgsConstructor@AllArgsConstructor@Builder
@Getter@Setter@ToString
public class TestEntity extends BaseTime {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Integer no;

    @Column(name="name", nullable = false , length = 100 , unique = true ) // 제약조건설정 컬러명설정
    private String name;

    @Column( columnDefinition = "varchar(100) not null default '제품설명" ) // columnDefinition로 컬럼 정의를 직접적으로 설정가능
    private String desc; // 설명

    @Column( insertable = true, updatable = true)
    private Integer price; 

    // + 제품 등록일 + 제품 수정일 --> baseTime 상속 
    
}
/*
    - @Column(name="필드명") : 생략시 자동으로 멤버변수명 지정
    @Column(name="name", nullable =  null허용여부 ) : not null 허용 여부
    @Column(name="name", nullable = false , length = 문자열길이(최대255) ) : varchar 로 설정
    @Column(name="name", nullable = false , length = 100 , unique = 중복여부 ) : 중복 불가능T/가능F
    @Column( columnDefinition = "SQL구문" )
    @Column( insertable = "insert여부", updatable = update여부 )
*/