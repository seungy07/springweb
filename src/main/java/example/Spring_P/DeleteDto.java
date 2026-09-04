package example.Spring_P;

import java.time.LocalDateTime;
import java.util.ArrayList;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@NoArgsConstructor@AllArgsConstructor@Builder
@Data
public class DeleteDto {
    private Integer clno;
    private String clcolor;
    private String clname;
    private String retype;
    private Integer mno;
    private Integer cno;
    private LocalDateTime createDate;
    private LocalDateTime updateTime;

}
