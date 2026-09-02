package example.day40_day04_0902;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




/*
    controller -> service -> repository(리모콘/DAO) -> entity (조작/DB)
*/

@RestController
@RequiredArgsConstructor // final 멤버변수 생성자 자동 생성
public class ExamController {
    // * 서비스객체 (주입) 불러오기 , @Autowired @RequiredArgsConstructor 두개중 선택 final 주의 
    private final ExamService examService;

    // [1] 전체조회
    @GetMapping("/day04/exam")
    public List<ExamEntity> findAll(){
        // 서비스 호출
        return examService.findAll();
    }

    // [2] 저장  { "eno":2 , "ename" : "유재석"}
    @PostMapping("/day04/exam")
    public boolean save(@RequestBody ExamEntity examEntity) {
        return examService.save(examEntity);
    }

    // [3] 삭제
    @DeleteMapping("/day04/exam")
    public boolean delete(@RequestParam int eno){ // (@RequestParam( name = "no") int no)
        return examService.delete(eno);
    }

    // [4] 수정  { "eno":2 , "ename" : "유재석"}
    @PutMapping("/day04/exam")
    public boolean update(@RequestBody ExamEntity examEntity){
        return examService.update(examEntity);
    }
    
    


    
}
