package example.Spring_Practice5.model.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity @Table (name = "board")
@NoArgsConstructor @AllArgsConstructor @Builder @Data 
public class BoardEntity extends BaseTime {
    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer boardId;
    @Column 
    private String author;
    @Column
    private String password;
    @Column
    private String content;

    @OneToMany (mappedBy = "boardentity" , cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString .Exclude
    @Builder.Default
    private List<CommentEntity> commentEntities = new ArrayList<>();


    
}
