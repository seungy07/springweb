package example.day40_day04_0902;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service // 해당 클래스가 비지니스로직 담당하는 객체(빈) 등록
@RequiredArgsConstructor
public class ExamService {
    // * 리포지토리객체 (주입) 불러오기
    private final ExamRepository examRepository;

    // [1]전체조회
    public List<ExamEntity> findAll(){
        // 리포지토리 호출
        return examRepository.findAll(); // 리포지토리.finAll() : (구현체) select 자동 지원(sql 안짜도 자동으로 해준다)
        // 반환값: 매핑되 테이블의 모든 레코드(엔티티) 반환
    }
    
    // [2] 저장
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
}
