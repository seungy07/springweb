package example.day08_P;

import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController  @RequiredArgsConstructor 
public class ApiController {
    private final Api_day08_service aService;

    @CrossOrigin (value = "http://localhost:5173")
    @GetMapping( value = "/day08" ,  produces = "application/json")
    public  Map<String,Object> get(){
        return aService.get();
    }
    
    
}
