package example.Spring_Practice3;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequiredArgsConstructor
@RequestMapping("/api/movie")
public class P3_Controller {
    private final P3_Service p3_Service;

    // 영화 전체 조회: 모든 영화 목록을 조회
    @GetMapping("")
    public List<P3_Dto> AllPrint() {
        return  p3_Service.AllPrint();
    }
    // 영화 개별 조회: 영화번호(movieid)를 기준으로 특정 영화 상세 정보 조회
    @GetMapping("/{movieId}")
    public P3_Dto findOne(@PathVariable(name="movieId") int movieId) {
        return p3_Service.findOne(movieId);
    }

    // 영화 등록: 새로운 영화 정보를 입력받아 DB에 저장
    @PostMapping("")
    public boolean save(@RequestBody P3_Dto p3_Dto) {
        return p3_Service.save(p3_Dto);
    }

    // 특정 영화 삭제: 영화번호(movieid)를 기준으로 해당 영화 삭제
    @DeleteMapping("")
    public boolean delete(@RequestParam int movieId){
        return p3_Service.delete(movieId);
    }

    // 특정 영화 수정: 영화번호(movieid)를 기준으로 영화번호 외 모든 영화 정보 수정
    @PutMapping("")
    public boolean update(@RequestBody P3_Dto p3_Dto) {
        return p3_Service.update(p3_Dto);
    }
}
// [조건 6] 컨트롤러 설계 : REST API 형식의 컨트롤러 클래스를 작성한다.
// /api/movie 경로를 기준으로 CRUD 요청을 처리할 것.
// [조건 5] 서비스 구현 : Service 클래스를 작성하여 아래 기능을 구현한다.
// 영화 전체 조회: 모든 영화 목록을 조회
// 영화 개별 조회: 영화번호(movieid)를 기준으로 특정 영화 상세 정보 조회
// 영화 등록: 새로운 영화 정보를 입력받아 DB에 저장
// 특정 영화 삭제: 영화번호(movieid)를 기준으로 해당 영화 삭제
// 특정 영화 수정: 영화번호(movieid)를 기준으로 영화번호 외 모든 영화 정보 수정
