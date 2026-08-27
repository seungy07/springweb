package example.day36_day02_0826.활동6.Controller;

import org.springframework.web.bind.annotation.RestController;

import example.day36_day02_0826.활동6.model.dao.ListDao;
import example.day36_day02_0826.활동6.model.dto.ListDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
public class ListController {
    private ListDao ld = ListDao.getInstance();

    // 대기번호 등록
    @PostMapping("/list/save")
    public boolean save(ListDto listDto){
        boolean result = ld.save(listDto);
        return result;
    }

    // 전체조회
    @GetMapping("/list/findAll")
    public ArrayList<ListDto> findAll(){
        ArrayList<ListDto> result = ld.findAll();
        return result;
    }
    
    // 개별 수정
    @PutMapping("/list/update")
    public boolean update(String number, int n){
        ArrayList<ListDto> result = findAll();
        for(int i=0; i<=result.size()-1;i++){
            if(result.get(i).getNumber().equals(number)){
                boolean resultUpdate = ld.update(result.get(i), n);
                return resultUpdate;
            }
        }
        return false;
    }

    // 삭제
    @DeleteMapping("/list/delete")
    public boolean delete(String number){
        return ld.delete(number);
    }


    
}
