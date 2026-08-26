package example.day36_day02_0826.controller;

import org.springframework.web.bind.annotation.RestController;

import example.day36_day02_0826.model.dao.BoardDao;
import example.day36_day02_0826.model.dto.BoardDto;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


/*

*/
@RestController
public class BoardController {
    private BoardDao bd = BoardDao.getInstance();
    
    // [1] 등록
    @PostMapping( "/board/save" )
    public boolean save( BoardDto boardDto ){
        boolean result = bd.save(boardDto);
        return result;
    }
    
}
