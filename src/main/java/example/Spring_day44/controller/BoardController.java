package example.Spring_day44.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import example.Spring_day44.model.dto.BoardDto;
import example.Spring_day44.service.BoardService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController @RequestMapping ("/api/board")
@NoArgsConstructor @AllArgsConstructor @Builder @Data
public class BoardController {
    @Autowired 
    private BoardService boardService;
    
    // 게시글 등록
    @PostMapping("")
    public boolean save(@RequestBody BoardDto boardDto) {
        return boardService.save(boardDto);
    }
    
    // 게시글 전체 조회
    @GetMapping("")
    public List<BoardDto> findAll() {
        return boardService.findAll();
    }
    

    // 게시글 삭제
    @DeleteMapping ("")
    public boolean delete(@RequestParam(name = "id") Integer id, @RequestParam(name = "password") String password){
        return boardService.delete(id, password);
    }
    
}
