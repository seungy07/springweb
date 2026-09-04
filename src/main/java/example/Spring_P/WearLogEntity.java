package example.Spring_P;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "wearlog")
@NoArgsConstructor@AllArgsConstructor@Builder@Data
public class WearLogEntity extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer w_no;
    @Column( name = "착용일", nullable = false, length = 200, unique = false)
    private String w_context;

    // 의류테이블 참조 의류번호
    @ManyToOne
    @JoinColumn(name = "cl_no")
    private ClothesEntity clothesEntity;
    
}
