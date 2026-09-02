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
    }
    
}
