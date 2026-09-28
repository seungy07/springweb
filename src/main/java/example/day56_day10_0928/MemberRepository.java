package example.day56_day10_0928;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;


@Repository 
public interface  MemberRepository extends JpaRepository<MemberEntity, Long> {
    
    // JPA 기본적인 CRUD 메소드 제공
    // * 메소드쿼리(망명규칙) 또는 네이티브쿼리 추가 정의
    // findByXXX : XXX에 필드명을 넣어서 조회 추상 메소드 만들기, findBy속서명and속성명   카멜표기법
    MemberEntity findByMid(String mid); // mid 일치하면 엔티티 조회
    Optional<MemberEntity> findByMname(String mname); // manme 일치하면 엔티티 조회
    
}
