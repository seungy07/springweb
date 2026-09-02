package example.Spring_Pratice2;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "practice2")
@Data@AllArgsConstructor@NoArgsConstructor@Builder
public class P_Entity {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Integer no;
    private String content;
    private String writer;
}
