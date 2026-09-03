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
    @GeneratedValue( strategy = GenerationType.IDENTITY) // auto_inrement
    private Integer no;
    private String content;
    private String writer;
}
/*
    JPA 영속성이란? 자바의 데이터가 사라지지 않게 데이터베이스에 저장
        -> 자바 프로그램(휘발성/영구저장불가능)
        -> 데이터베이스(비휘발성/영구저장가능)
*/