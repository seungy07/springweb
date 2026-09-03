package example.day41_day05_0903;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
public class TestController {
    @Autowired private TestService testService;

    // 1. 전체조회
    @GetMapping("/test")
    public List<TestDto> 전체조회(){
        return testService.전체조회();
    }

    // 2. 등록
    @PostMapping("/test")
    public boolean 등록(@RequestBody TestDto testDto ){
        return testService.등록(testDto);
    }
    

    // 3. 수정
    @PutMapping("/test")
    public boolean 수정(@RequestBody TestDto testDto) {
        return testService.수정(testDto);
    }
    
}
