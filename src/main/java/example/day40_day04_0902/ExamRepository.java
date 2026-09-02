package example.day40_day04_0902;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // 리포지토리(DB조작) 담당하는 객체(빈) 등록
public interface ExamRepository extends JpaRepository< ExamEntity, Integer >{
    // 구현체란? 해당 인터페이스(추상) 구현한 객체
    // <제네릭 타입> 이란? 해당 클래스내 사용할 매개 타입  // < 조작할 엔티티명, 엔티티PK타입 >
}
