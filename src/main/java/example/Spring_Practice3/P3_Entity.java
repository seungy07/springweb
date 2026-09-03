package example.Spring_Practice3;

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
@Table( name = "movie")
@NoArgsConstructor@AllArgsConstructor@Builder
@Getter@Setter@ToString
public class P3_Entity extends BaseTime {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Integer movieid;
    @Column( name = "제목", nullable = false, length = 30, unique = true)
    private String title;
    @Column(name = "감독", nullable = false, length = 30, unique = false)
    private String director;
    @Column(name = "개봉일", nullable = false, length = 100, unique = false)
    private String releasedate;
    @Column(name = "평점", nullable = false, unique = false)
    private Integer rating;

    
}
/* [조건 1] 엔티티 설계 : “영화(Movie)” 정보를 저장할 엔티티 클래스를 생성한다.
    각 필드의 역할과 데이터 타입을 적절히 설정한다.
    항목을 포함할 것: 영화번호(movieid), 영화제목(title), 감독(director), 개봉일(releasedate), 평점(rating)
    공통 상속 엔티티(BaseTime)를 적용하여 createdDate, updatedDate 자동 기록 기능을 포함한다*/