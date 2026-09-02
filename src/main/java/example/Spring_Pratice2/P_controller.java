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
    public List<P_Entity> findAll(){
        return p_Service.findAll();
    }

    // 게시물 등록
    @PostMapping("")
    public boolean save(@RequestBody P_Entity p_Entity){
        return p_Service.save(p_Entity);
    }
    
    // 게시물 삭제
    @DeleteMapping("")
    public boolean delete(@RequestParam int no){
        return p_Service.delete(no);
    }

    // 게시물 수정
    @PutMapping("")
    public boolean update( @RequestBody P_Entity p_Entity) {
        return p_Service.update(p_Entity);
    }
    
}
