package example.Spring_Practice4.model.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.annotation.Generated;
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

@Entity @Table (name = "student")
@NoArgsConstructor @AllArgsConstructor @Builder @Data 
public class StudentEntity extends BaseTime{
    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer studentId;
    @Column 
    private String studentName;

    @OneToMany( mappedBy = "studentEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<EnrollEntity> enrollEntities = new ArrayList<>();
    
}
