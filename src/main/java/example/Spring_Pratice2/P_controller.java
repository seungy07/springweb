package example.Spring_Pratice2;

import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;





@RestController
@RequiredArgsConstructor
@RequestMapping("/practice2")
public class P_controller {
    private final P_Service p_Service;

    // 게시물 전체 조회
    @GetMapping("")
    public List<P_Entity> testPrint(){
        return p_Service.testPrint();
    }
    // 게시물 개별 조회
    @GetMapping("/{no}")
    public P_Entity testDetail(@PathVariable(name="no") int no) {
        return p_Service.testDetail(no);
    }
    

    // 게시물 등록
    @PostMapping("")
    public boolean testWrite(@RequestBody P_Entity p_Entity){
        return p_Service.testWrite(p_Entity);
    }
    
    // 게시물 삭제
    @DeleteMapping("")
    public boolean testDelete(@RequestParam int no){
        return p_Service.testDelete(no);
    }

    // 게시물 수정
    @PutMapping("")
    public boolean testUpdate( @RequestBody P_Entity p_Entity) {
        return p_Service.testUpdate(p_Entity);
    }
    
}
