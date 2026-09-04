package example.Spring_P;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale.Category;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "clothes")
@NoArgsConstructor@AllArgsConstructor@Builder@Data
public class ClothesEntity extends BaseTime {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Integer cl_no;

    // 참조 FK 회원 테이블 회원번호
    @ManyToOne
    @JoinColumn(name = "m_no" ) // 회원테이블( 회원 번호 )
    private UserEntity userEntity; // 회원 테이블 엔티티

    // 참조 카테고리테이블 카테고리번
    @ManyToOne
    @JoinColumn(name = "c_no") // 카테고리테이블(카테고리 번호)
    private  CategoriesEntity CategoriesEntity;

    // 양방향 착용기록 테이블
    @OneToMany( mappedBy = "clothesEntity")
    @ToString.Exclude
    @Builder.Default    
    private List<WearLogEntity> wearLogEntity = new ArrayList<>(); 

    

    @Column( name = "색상", nullable = false, length = 30, unique = false)
    private String cl_color;
    @Column( name = "의류이름", nullable = true, length = 100, unique = false)
    private String cl_name;
    @Column( name = "처리유형", nullable = true, length = 30, unique = false)
    private String re_type;
    
}
