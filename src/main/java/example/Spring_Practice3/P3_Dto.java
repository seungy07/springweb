package example.Spring_Practice3;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor@AllArgsConstructor@Builder
@Setter@Getter@ToString
public class P3_Dto {
    private Integer movieid;
    private String title;
    private String director;
    private String releasedate;
    private Integer rating;
    private LocalDateTime createDate;
    private LocalDateTime updateTime;

    // entity -> dto
    public static P3_Dto from(P3_Entity p3_Entity){
        return P3_Dto.builder()
            .movieid(p3_Entity.getMovieid())
            .title( p3_Entity.getTitle() )
            .director( p3_Entity.getDirector() )
            .releasedate(p3_Entity.getReleasedate())
            .rating(p3_Entity.getRating())
            .createDate(p3_Entity.getCreateDate())
            .updateTime(p3_Entity.getUpdateTime())
            .build();
    } 

    // dto -> entity
    public P3_Entity toEntity(){
        return P3_Entity.builder()
                .title(this.title)
                .director(this.director)
                .releasedate(this.releasedate)
                .rating(this.rating)
                .build();
    }

    
}

// [조건 3] DTO 설계 : 영화 정보를 전송하기 위한 DTO 클래스를 생성한다.
// 필드는 Entity와 동일하게 구성하되, 아래 메서드를 포함할 것.
// createdDate, updatedDate 포함한다.
// public MovieEntity toEntity() : DTO → Entity 변환
// public static MovieDto from( ) : Entity → DTO 변환
