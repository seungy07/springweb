package example.Spring_P_Smart_Clotset;


import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ClothesDto {
    private Integer mno;
    private Integer cno;
    
    private Integer clno;
    private String clcolor;
    private String clname;
    private String retype;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    // dto를 entity로
    public ClothesEntity toEntity(){
        return ClothesEntity.builder()
                            .clno(this.clno)
                            .clcolor(this.clcolor)
                            .clname(this.clname)
                            .retype(this.retype)
                            .build();
    }

    // entity를 dto로
    public ClothesDto from(ClothesEntity clothesEntity){
        return ClothesDto.builder()
                        .mno(clothesEntity.getUserEntity().getMno())
                        .cno(clothesEntity.getCategoriesEntity().getCno())
                        .clno(clothesEntity.getClno())
                        .clcolor(clothesEntity.getClcolor())
                        .clname(clothesEntity.getClname())
                        .retype(clothesEntity.getRetype())
                        .createDate(clothesEntity.getCreateDate())
                        .updateDate(clothesEntity.getUpdateTime())
                        .build();
    }
}
