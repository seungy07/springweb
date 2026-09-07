package example.Spring_Practice5.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import example.Spring_Practice5.model.dto.BoardDto;
import example.Spring_Practice5.service.BoardService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController @RequestMapping("/api/board")
public class BoardController {
    @Autowired 
    private BoardService boardService;

    // 게시글 저장
    @PostMapping("")
    public boolean save(@RequestBody BoardDto boardDto) {
        return boardService.save(boardDto);
    }
    
    // 게시글 전체 출력
    @GetMapping("")
    public List<BoardDto> findAll() {
        return boardService.findAll();
    }
    
    // 게시글 삭제
    @DeleteMapping ("")
    public  boolean delete(@RequestParam(name = "boardId") Integer boardId, @RequestParam(name = "password") String password){
        return boardService.delete(boardId,password);
    }
}
