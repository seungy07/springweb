package example.day52_day08_0918;

import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController @RequiredArgsConstructor 
public class ApiController {
    private final ApiService apiService;

    // 1.
    @GetMapping("/test1")
    public Map<String, Object> test1(){
        return apiService.test1();
    }

    // 2.
    @GetMapping("/test2")
    public Map<String, Object> test2() {
        return apiService.test2();
    }

    // 3.
    @GetMapping(value = "/test3", produces = "application/json")
    public List<Map<String, Object>> test3() {
        return apiService.test3();
    }
    
    
    
    
}
