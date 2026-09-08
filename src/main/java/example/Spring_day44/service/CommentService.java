package example.Spring_day44.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import example.Spring_day44.model.dto.CommentDto;
import example.Spring_day44.model.entity.BoardEntity;
import example.Spring_day44.model.entity.CommentEntity;
import example.Spring_day44.model.repository.BoardRepository;
import example.Spring_day44.model.repository.CommentRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Service 
@NoArgsConstructor @AllArgsConstructor @Builder @Data 
public class CommentService {
    @Autowired 
    CommentRepository commentRepository;
    @Autowired 
    BoardRepository boardRepository;
    

    // 댓글 등록
    public boolean save(CommentDto commentDto){
        // 해당 게시물 조회 
        Integer boaedId = commentDto.getBoardId();
        BoardEntity boardEntity = boardRepository.findById(boaedId).orElse(null);

        CommentEntity commentEntity = commentDto.toEntity();
        commentEntity.setBoardEntity(boardEntity);
        CommentEntity saved = commentRepository.save(commentEntity);
        if(saved.getId() >= 1){return true;}
        return false;
        
    }

    // 댓글 삭제
    public boolean delete(Integer commentId, String password){
        // 해당 번호 댓글 확인
        Optional<CommentEntity> optional = commentRepository.findById(commentId);
        if(optional.isPresent()){
            CommentEntity entity = optional.get();
            if(entity.getPassword().equals(password)){
                commentRepository.deleteById(commentId);
                return true;
            }
        }
        return false;
    }
}
