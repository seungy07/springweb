package example.Spring_day44.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import example.Spring_day44.model.dto.CommentDto;
import example.Spring_day44.service.CommentService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController @RequestMapping ("/api/board/comments")
@NoArgsConstructor @AllArgsConstructor @Builder @Data 
public class CommentController {
    @Autowired private CommentService commentService;

    // 댓글 등록
    @PostMapping("")
    public boolean save(@RequestBody CommentDto commentDto) {
        return commentService.save(commentDto);
    }
    

    // 댓글 삭제
    @DeleteMapping ("")
    public boolean delete(@RequestParam(name = "commentId") Integer commentId, @RequestParam(name = "password") String password){
        return commentService.delete(commentId,password);
    }
    
}
