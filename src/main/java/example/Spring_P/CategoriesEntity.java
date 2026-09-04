package example.Spring_P;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity 
@Table(name = "categories")
@NoArgsConstructor 
@AllArgsConstructor 
@Builder@Data 
public class CategoriesEntity extends BaseTime {
    @Id
    private Integer cno;
    private String cname;

    // 양방향 참조
    @OneToMany(mappedBy = "CategoriesEntity")
    @ToString.Exclude
    @Builder.Default
    private List<ClothesEntity> closetList = new ArrayList<>();
}