package example.day55_day09_react_day07.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import example.day55_day09_react_day07.model.dto.ApiDto;
import example.day55_day09_react_day07.service.ApiService;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@CrossOrigin (origins = "http://localhost:5173")
@RestController 
@RequiredArgsConstructor 
@RequestMapping ("/api")
public class ApiController {
    private final ApiService apiService;

    @GetMapping("")
    public List<ApiDto> findAll() {
        return apiService.findAll();
    }

    @PostMapping("")
    public boolean save(@RequestBody ApiDto apiDto) {
        return apiService.save(apiDto);
    }
    
    
    
}
