package example.day40_day04_0902;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service // 해당 클래스가 비지니스로직 담당하는 객체(빈) 등록
@RequiredArgsConstructor
// @Transactional 여기에 쓰기도 함
public class ExamService {
    // * 리포지토리객체 (주입) 불러오기
    private final ExamRepository examRepository;

    // [1]전체조회
    public List<ExamEntity> findAll(){
        // 리포지토리 호출
        return examRepository.findAll(); // 리포지토리.finAll() : (구현체) select 자동 지원(sql 안짜도 자동으로 해준다)
        // 반환값: 매핑되 테이블의 모든 레코드(엔티티) 반환
    }
    
    // [2] 저장 { "eno":2 , "ename" : "유재석"}
    public boolean save(ExamEntity examEntity){
        // 리포지토리 호출
        // 리포지토리 객체.save(저장할 entity) : insert 지원
        // save 반환값은 영속(매핑/저장)된 엔티티 반환
        ExamEntity saved = examRepository.save(examEntity);
        // 즉] 만약에 SAVE 된 엔티티가 PK가 존재하면 저장 성공
        if(saved.getEno() >= 1){ return true;}
        // PK 없으면 저장 실패
        return false; 
    }

    // [3] 삭제
    public boolean delete(int eno){
        examRepository.deleteById(eno); // 리포지토리객체.deleteById( 삭제할PK번호 ) : delete sql 지원
        // 리포지토리객체.delete( 삭제할엔티티 )
        // 반환 타입 없음 , 삭제여부 findxxx 이용하여 확인
        return true;
    }

    // [4] 수정 : 트랜잭션 필수  { "eno":2 , "ename" : "유재석"}
    @Transactional // 트랜잭션이란? 여러개 SQL 하나의 (논리)단위로 묶을때
    // 만약에 여러개 SQL중에 하나라도 SQL 오류이면 전체 ROLLBACK(취소) 모두 성공하면 COMMIT(완료)
    // 활용처: 계좌이체(출금/입금), 회원가입포인트지급( 회원가입/가입포인트지급) : 2개 이상 기능을 하나로 묶을 때 
    public boolean update(ExamEntity examEntity){
        // 1. 영속된 엔티티 조회[PK:수정할 번호]
        // 리포지토리객체.findById( 조회할 pk 번호 ) : select SQL 지원(1개만)
        // 반환타입 ; Optional< 엔티티 >
        // Optional 클래스란? 본문(객체) 감싼 클래스 (왜? null 예외 안전하게 사용 )
            // --> 만약에 조회 결과 엔티티가 없을떄 .getEno() 오류가 발생   (객체가 없을때 . 찍으면 안됨)
            // Optional<객체타입> 변수명; 객체 래핑하여 null 검사 지원
        Optional<ExamEntity> optional = examRepository.findById( examEntity.getEno() );
        // 2. 조회된 결과 엔티티 여부 확인
        if( optional.isPresent() ){ // 객체가 있으면 true , 없으면 false
            ExamEntity savedEntity = optional.get();  // 래핑된 optional에서 엔티티 꺼내기
            // 3. 만약에 엔티티가 존재하면 수정
            savedEntity.setEname(( examEntity.getEname() ));  // 영속성으로 꺼내온걸 수정해도 DB 데이터도 수정된다
            return true;
        }return false;

    }


}
